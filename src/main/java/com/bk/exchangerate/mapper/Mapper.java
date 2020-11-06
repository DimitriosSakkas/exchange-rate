package com.bk.exchangerate.mapper;

import com.bk.exchangerate.model.RateValue;
import com.bk.exchangerate.model.dao.ExchangeRateDao;
import com.bk.exchangerate.model.dto.ExchangeRateDto;

import java.time.LocalDate;

public class Mapper {

    public static ExchangeRateDao mapToExchangeRateDao(final ExchangeRateDto dto,
                                                       final LocalDate date,
                                                       final RateValue baseCurrency,
                                                       final RateValue targetCurrency) {
        ExchangeRateDao dao = new ExchangeRateDao();
        dao.setDate(date);
        dao.setExchangeRate(dto.getExchangeRate());
        dao.setBaseCurrency(baseCurrency);
        dao.setTargetCurrency(targetCurrency);
        dao.setExchangeRateAverage(dto.getExchangeRateAverage());
        dao.setExchangeRateTrend(dto.getExchangeRateTrend());
        return dao;
    }

}
