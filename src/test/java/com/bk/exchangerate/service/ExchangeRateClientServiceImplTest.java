package com.bk.exchangerate.service;

import com.bk.exchangerate.client.ExchangeRateClient;
import com.bk.exchangerate.mockdata.MockRate;
import com.bk.exchangerate.model.client.RatesClient;
import com.bk.exchangerate.model.dao.ExchangeRateDao;
import com.bk.exchangerate.model.dto.ExchangeRateDto;
import com.bk.exchangerate.repository.ExchangeRateRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class ExchangeRateClientServiceImplTest {

    @InjectMocks
    private ExchangeRateClientServiceImpl exchangeRateClientService;
    @Mock
    private ExchangeRateClient exchangeRateClient;
    @Mock
    private ExchangeRateRepository exchangeRateRepository;

    private RatesClient ratesClientDaily;
    private RatesClient ratesClientMonthly;

    @BeforeEach
    public void init() {
        ratesClientMonthly = MockRate.createRatesClientMonthly();
    }

    @Test
    public void shouldGetExchangeRates() {
        // given
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
        //Assertions.assertEquals(2, result.size());
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
