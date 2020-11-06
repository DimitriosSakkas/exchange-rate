package com.bk.exchangerate.controller;

import com.bk.exchangerate.mockdata.MockRate;
import com.bk.exchangerate.model.RateValue;
import com.bk.exchangerate.model.dto.ExchangeRateDto;
import com.bk.exchangerate.service.ExchangeRateClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

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
    private static final String baseUrlDaily = baseUrlMonthly + "/{dd}";
    private static final String dateValid = "2010-01-01";
    private static final String dateInValid1 = "1999-12-30";
    private static final RateValue baseCurrency = RateValue.AUD;
    private static final RateValue targetCurrency = RateValue.BGN;
    private ExchangeRateDto dto;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
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
                        .getExchangeRates(LocalDate.parse(dateValid), baseCurrency, targetCurrency))
                .thenReturn(dto);

        // when & then
        mockMvc.perform(put(baseUrlExchangeRates, dateValid, baseCurrency, targetCurrency))
                .andExpect(status().isOk());
        //.andExpect(dto));
    }

    @Test
    public void shouldGetDailyRates() throws Exception {
        // given
        Mockito
                .when(exchangeRateClientService
                        .getExchangeRates(LocalDate.parse(dateValid), baseCurrency, targetCurrency))
                .thenReturn(dto);

        // when & then
        mockMvc.perform(get(baseUrlDaily, dateValid, baseCurrency, targetCurrency))
                .andExpect(status().isOk());
        //.andExpect(dto));
    }

    @Test
    public void shouldGetMonthlyRates() throws Exception {
        // given
        Mockito
                .when(exchangeRateClientService
                        .getExchangeRates(LocalDate.parse(dateValid), baseCurrency, targetCurrency))
                .thenReturn(dto);

        // when & then
        mockMvc.perform(get(baseUrlMonthly, dateValid, baseCurrency, targetCurrency))
                .andExpect(status().isOk());
        //.andExpect(dto));
    }

}
