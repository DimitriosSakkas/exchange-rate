package com.bk.exchangerate.service;

import com.bk.exchangerate.client.ExchangeRateClient;
import com.bk.exchangerate.mapper.Mapper;
import com.bk.exchangerate.model.RateValue;
import com.bk.exchangerate.model.client.RatesClient;
import com.bk.exchangerate.model.dao.ExchangeRateDao;
import com.bk.exchangerate.model.dto.ExchangeRateDto;
import com.bk.exchangerate.model.dto.ExchangeRateTrend;
import com.bk.exchangerate.repository.ExchangeRateRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ExchangeRateClientServiceImpl implements ExchangeRateClientService {

    private final ExchangeRateClient exchangeRateClient;
    private final ExchangeRateRepository exchangeRateRepository;


    public ExchangeRateClientServiceImpl(ExchangeRateClient exchangeRateClient,
                                         ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateClient = exchangeRateClient;
        this.exchangeRateRepository = exchangeRateRepository;

    }

    @Override
    public ExchangeRateDto getExchangeRates(final LocalDate requestedDate,
                                            final RateValue baseCurrency,
                                            final RateValue targetCurrency) {

        final RatesClient ratesClient = exchangeRateClient.getExchangeRates(
                requestedDate.minusDays(5),
                requestedDate,
                Arrays.asList(baseCurrency, targetCurrency));

        final float exchangeRateOfRequestedDate = calculateExchangeRate(
                ratesClient,
                ratesClient.getEndAt(),
                baseCurrency,
                targetCurrency);

        final List<Float> exchangeRates = new ArrayList<>();
        for (LocalDate date : ratesClient.getRates().keySet()) {
            if (!(date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                exchangeRates.add(calculateExchangeRate(
                        ratesClient,
                        date,
                        baseCurrency,
                        targetCurrency));
            }
        }

        final ExchangeRateDto dto = new ExchangeRateDto();
        dto.setExchangeRate(exchangeRateOfRequestedDate);
        dto.setExchangeRateAverage(calculateAverage(exchangeRates));
        dto.setExchangeRateTrend(calculateExchangeRateTrend(exchangeRates));
        return dto;
    }

    @Override
    public RatesClient getRates(int year, int month, int day) {
        if (day != 0) {
            final LocalDate date = LocalDate.of(year, month, day);
            return exchangeRateClient.getExchangeRates(date, date, Collections.emptyList());
        } else {
            final LocalDate startAt = LocalDate.of(year, month, 1);
            final LocalDate endAt = YearMonth.of(year, month).atEndOfMonth();
            return exchangeRateClient.getExchangeRates(startAt, endAt, Collections.emptyList());
        }

    }

    @Override
    public ExchangeRateDto saveExchangeRates(final ExchangeRateDto dto,
                                             final LocalDate date,
                                             final RateValue baseCurrency,
                                             final RateValue targetCurrency) {
        ExchangeRateDao dao = exchangeRateRepository.findByDate(date);
        if (dao == null) {
            return Mapper.mapToExchangeRateDto(exchangeRateRepository.save(Mapper.mapToExchangeRateDao(dto, date, baseCurrency, targetCurrency)));
        } else {
            return Mapper.mapToExchangeRateDto(dao);
        }
    }

    private float calculateExchangeRate(final RatesClient ratesClient,
                                        final LocalDate date,
                                        final RateValue baseCurrency,
                                        final RateValue targetCurrency) {
        return ratesClient.getRates().get(date).get(baseCurrency)
                / ratesClient.getRates().get(date).get(targetCurrency);
    }

    private float calculateAverage(final List<Float> exchangeRates) {
        return (float) exchangeRates.stream().mapToDouble(d -> d).summaryStatistics().getAverage();

    }

    private ExchangeRateTrend calculateExchangeRateTrend(List<Float> exchangeRates) {
        List<Float> check = new ArrayList<>();
        for (int i = 1; i < exchangeRates.size(); i++) {
            check.add(exchangeRates.get(i - 1) - exchangeRates.get(i));
        }

        if (check.stream().allMatch(t -> t > 0)) {
            return ExchangeRateTrend.DESCENDING;
        } else if (check.stream().allMatch(t -> t < 0)) {
            return ExchangeRateTrend.ASCENDING;
        } else if (check.stream().allMatch(t -> t == 0)) {
            return ExchangeRateTrend.CONSTANT;
        } else {
            return ExchangeRateTrend.UNDEFINED;
        }
    }

}
