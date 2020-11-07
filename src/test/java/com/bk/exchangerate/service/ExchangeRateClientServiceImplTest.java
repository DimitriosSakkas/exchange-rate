package com.bk.exchangerate.service;

import com.bk.exchangerate.client.ExchangeRateClient;
import com.bk.exchangerate.mockdata.MockRate;
import com.bk.exchangerate.model.RateValue;
import com.bk.exchangerate.model.client.RatesClient;
import com.bk.exchangerate.model.dao.ExchangeRateDao;
import com.bk.exchangerate.model.dto.ExchangeRateDto;
import com.bk.exchangerate.model.dto.ExchangeRateTrend;
import com.bk.exchangerate.repository.ExchangeRateRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class ExchangeRateClientServiceImplTest {

    @InjectMocks
    private ExchangeRateClientServiceImpl exchangeRateClientService;
    @Mock
    private ExchangeRateClient exchangeRateClient;
    @Mock
    private ExchangeRateRepository exchangeRateRepository;

    @Test
    public void shouldGetExchangeRatesWhichAreInAscendingOrder() {
        // given
        Map<RateValue, Float> targetCurrency1 = MockRate.createRate(1f, 1f);
        Map<RateValue, Float> targetCurrency2 = MockRate.createRate(1f, 0.9f);
        Map<RateValue, Float> targetCurrency3 = MockRate.createRate(1f, 0.8f);
        Map<RateValue, Float> targetCurrency4 = MockRate.createRate(1f, 0.7f);
        Map<RateValue, Float> targetCurrency5 = MockRate.createRate(1f, 0.6f);
        RatesClient ratesClientDaily = MockRate.createRatesClient(
                MockRate
                        .createRates(
                                targetCurrency1,
                                targetCurrency2,
                                targetCurrency3,
                                targetCurrency4,
                                targetCurrency5));
        Mockito
                .when(exchangeRateClient
                        .getExchangeRates(
                                ArgumentMatchers.any(LocalDate.class),
                                ArgumentMatchers.any(LocalDate.class),
                                ArgumentMatchers.any()))
                .thenReturn(ratesClientDaily);

        // when
        ExchangeRateDto result = exchangeRateClientService
                .getExchangeRates(
                        LocalDate.parse(MockRate.date5),
                        MockRate.baseCurrency,
                        MockRate.targetCurrency);

        // then
        Assertions.assertEquals(ExchangeRateTrend.ASCENDING, result.getExchangeRateTrend());
    }

    @Test
    public void shouldGetExchangeRatesWhichAreInDescendingOrder() {
        // given
        Map<RateValue, Float> targetCurrency1 = MockRate.createRate(1f, 1f);
        Map<RateValue, Float> targetCurrency2 = MockRate.createRate(1f, 2f);
        Map<RateValue, Float> targetCurrency3 = MockRate.createRate(1f, 3f);
        Map<RateValue, Float> targetCurrency4 = MockRate.createRate(1f, 4f);
        Map<RateValue, Float> targetCurrency5 = MockRate.createRate(1f, 5f);
        RatesClient ratesClientDaily = MockRate.createRatesClient(
                MockRate
                        .createRates(
                                targetCurrency1,
                                targetCurrency2,
                                targetCurrency3,
                                targetCurrency4,
                                targetCurrency5));
        Mockito
                .when(exchangeRateClient
                        .getExchangeRates(
                                ArgumentMatchers.any(LocalDate.class),
                                ArgumentMatchers.any(LocalDate.class),
                                ArgumentMatchers.any()))
                .thenReturn(ratesClientDaily);

        // when
        ExchangeRateDto result = exchangeRateClientService
                .getExchangeRates(
                        LocalDate.parse(MockRate.date5),
                        MockRate.baseCurrency,
                        MockRate.targetCurrency);

        // then
        Assertions.assertEquals(ExchangeRateTrend.DESCENDING, result.getExchangeRateTrend());
    }

    @Test
    public void shouldGetExchangeRatesWhichAreInConstantOrder() {
        // given
        Map<RateValue, Float> targetCurrency1 = MockRate.createRate(1f, 1f);
        Map<RateValue, Float> targetCurrency2 = MockRate.createRate(1f, 1f);
        Map<RateValue, Float> targetCurrency3 = MockRate.createRate(1f, 1f);
        Map<RateValue, Float> targetCurrency4 = MockRate.createRate(1f, 1f);
        Map<RateValue, Float> targetCurrency5 = MockRate.createRate(1f, 1f);
        RatesClient ratesClientDaily = MockRate.createRatesClient(
                MockRate
                        .createRates(
                                targetCurrency1,
                                targetCurrency2,
                                targetCurrency3,
                                targetCurrency4,
                                targetCurrency5));
        Mockito
                .when(exchangeRateClient
                        .getExchangeRates(
                                ArgumentMatchers.any(LocalDate.class),
                                ArgumentMatchers.any(LocalDate.class),
                                ArgumentMatchers.any()))
                .thenReturn(ratesClientDaily);

        // when
        ExchangeRateDto result = exchangeRateClientService
                .getExchangeRates(
                        LocalDate.parse(MockRate.date5),
                        MockRate.baseCurrency,
                        MockRate.targetCurrency);

        // then
        Assertions.assertEquals(ExchangeRateTrend.CONSTANT, result.getExchangeRateTrend());
    }

    @Test
    public void shouldGetExchangeRatesWhichAreInUndefinedOrder() {
        // given
        Map<RateValue, Float> targetCurrency1 = MockRate.createRate(1f, 1f);
        Map<RateValue, Float> targetCurrency2 = MockRate.createRate(1f, 2f);
        Map<RateValue, Float> targetCurrency3 = MockRate.createRate(1f, 0.5f);
        Map<RateValue, Float> targetCurrency4 = MockRate.createRate(1f, 1f);
        Map<RateValue, Float> targetCurrency5 = MockRate.createRate(1f, 1f);
        RatesClient ratesClientDaily = MockRate.createRatesClient(
                MockRate
                        .createRates(
                                targetCurrency1,
                                targetCurrency2,
                                targetCurrency3,
                                targetCurrency4,
                                targetCurrency5));
        Mockito
                .when(exchangeRateClient
                        .getExchangeRates(
                                ArgumentMatchers.any(LocalDate.class),
                                ArgumentMatchers.any(LocalDate.class),
                                ArgumentMatchers.any()))
                .thenReturn(ratesClientDaily);

        // when
        ExchangeRateDto result = exchangeRateClientService
                .getExchangeRates(
                        LocalDate.parse(MockRate.date5),
                        MockRate.baseCurrency,
                        MockRate.targetCurrency);

        // then
        Assertions.assertEquals(ExchangeRateTrend.UNDEFINED, result.getExchangeRateTrend());
    }

    @Test
    public void shouldGetRatesDaily() {
        // given
        RatesClient ratesClientDaily = new RatesClient();
        Mockito
                .when(exchangeRateClient
                        .getExchangeRates(
                                ArgumentMatchers.any(LocalDate.class),
                                ArgumentMatchers.any(LocalDate.class),
                                ArgumentMatchers.any()))
                .thenReturn(ratesClientDaily);

        // when
        RatesClient result = exchangeRateClientService
                .getRates(MockRate.yearDate5, MockRate.monthDate5, MockRate.dayDate5);

        // then
        Assertions.assertEquals(ratesClientDaily, result);
    }

    @Test
    public void shouldGetRatesMonthly() {
        // given
        RatesClient ratesClientMonthly = new RatesClient();
        Mockito
                .when(exchangeRateClient
                        .getExchangeRates(
                                ArgumentMatchers.any(LocalDate.class),
                                ArgumentMatchers.any(LocalDate.class),
                                ArgumentMatchers.any()))
                .thenReturn(ratesClientMonthly);

        // when
        RatesClient result = exchangeRateClientService
                .getRates(MockRate.yearDate5, MockRate.monthDate5, 0);

        // then
        Assertions.assertEquals(ratesClientMonthly, result);
    }

    @Test
    public void shouldSaveExchangeRates() {
        // given
        ExchangeRateDao dao = MockRate.createExchangeRateDao();
        ExchangeRateDto dto = MockRate.createExchangeRateDto();
        Mockito
                .when(exchangeRateRepository
                        .save(ArgumentMatchers.any(ExchangeRateDao.class)))
                .thenReturn(dao);
        Mockito
                .when(exchangeRateRepository
                        .findByDate(ArgumentMatchers.any(LocalDate.class)))
                .thenReturn(null);

        // when
        ExchangeRateDto result = exchangeRateClientService
                .saveExchangeRates(
                        dto,
                        LocalDate.parse(MockRate.date5),
                        MockRate.baseCurrency,
                        MockRate.targetCurrency);

        // then
        Assertions.assertEquals(dto, result);
    }

    @Test
    public void shouldNotSaveExchangeRatesIfAlreadyExists() {
        // given
        ExchangeRateDao dao = MockRate.createExchangeRateDao();
        ExchangeRateDto dto = MockRate.createExchangeRateDto();
        Mockito
                .when(exchangeRateRepository
                        .findByDate(ArgumentMatchers.any(LocalDate.class)))
                .thenReturn(dao);

        // when
        ExchangeRateDto result = exchangeRateClientService
                .saveExchangeRates(
                        dto,
                        LocalDate.parse(MockRate.date5),
                        MockRate.baseCurrency,
                        MockRate.targetCurrency);

        // then
        Assertions.assertEquals(dto, result);
    }
}
