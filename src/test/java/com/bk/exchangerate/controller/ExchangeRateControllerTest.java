package com.bk.exchangerate.controller;

import com.bk.exchangerate.mockdata.MockRate;
import com.bk.exchangerate.model.client.RatesClient;
import com.bk.exchangerate.model.dto.ExchangeRateDto;
import com.bk.exchangerate.service.ExchangeRateClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = ExchangeRateController.class)
public class ExchangeRateControllerTest {

    private static final String baseUrl = "/api/exchange-rate";
    private static final String baseUrlExchangeRates = baseUrl + "/{date}/{baseCurrency}/{targetCurrency}";
    private static final String baseUrlMonthly = baseUrl + "/history/monthly/{yyyy}/{MM}";
    private static final String baseUrlDaily = baseUrl + "/history/daily/{yyyy}/{MM}/{dd}";
    private ExchangeRateDto dto;

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ExchangeRateClientService exchangeRateClientService;

    @BeforeEach
    public void init() {
        dto = MockRate.createExchangeRateDto();
    }

    @Test
    public void shouldGetExchangeRatesAndSaveItInDb() throws Exception {
        // given
        Mockito
                .when(exchangeRateClientService
                        .getExchangeRates(
                                LocalDate.parse(MockRate.date5),
                                MockRate.baseCurrency,
                                MockRate.targetCurrency))
                .thenReturn(dto);
        Mockito
                .when(exchangeRateClientService
                        .saveExchangeRates(
                                dto,
                                LocalDate.parse(MockRate.date5),
                                MockRate.baseCurrency,
                                MockRate.targetCurrency))
                .thenReturn(dto);

        // when & then
        mockMvc
                .perform(put(baseUrlExchangeRates, MockRate.date5, MockRate.baseCurrency, MockRate.targetCurrency))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists());
    }

    @Test
    public void throwNoWorkingDayException() throws Exception {
        // when & then
        mockMvc
                .perform(put(baseUrlExchangeRates, MockRate.dateSaturday, MockRate.baseCurrency, MockRate.targetCurrency))
                .andExpect(status()
                        .isBadRequest());
    }

    @Test
    public void throwWrongDateExceptionExceptionBefore2000() throws Exception {
        // when & then
        mockMvc
                .perform(put(baseUrlExchangeRates, MockRate.dateInValid, MockRate.baseCurrency, MockRate.targetCurrency))
                .andExpect(status()
                        .isBadRequest());
    }

    @Test
    public void throwWrongDateExceptionExceptionAfterYesterday() throws Exception {
        // when & then
        mockMvc
                .perform(put(baseUrlExchangeRates, MockRate.dateInValid2, MockRate.baseCurrency, MockRate.targetCurrency))
                .andExpect(status()
                        .isBadRequest());
    }

    @Test
    public void shouldGetDailyRates() throws Exception {
        // given
        Mockito
                .when(exchangeRateClientService
                        .getRates(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt()))
                .thenReturn(new RatesClient());

        // when & then
        mockMvc
                .perform(get(baseUrlDaily, MockRate.yearDate5, MockRate.monthDate5, MockRate.dayDate5))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists());
    }

    @Test
    public void shouldGetMonthlyRates() throws Exception {
        // given
        Mockito
                .when(exchangeRateClientService
                        .getRates(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt()))
                .thenReturn(new RatesClient());

        // when & then
        mockMvc
                .perform(get(baseUrlMonthly, MockRate.yearDate5, MockRate.monthDate5))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists());
    }

}
