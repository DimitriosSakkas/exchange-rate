package com.bk.exchangerate.service;

import com.bk.exchangerate.model.RateValue;
import com.bk.exchangerate.model.client.RatesClient;
import com.bk.exchangerate.model.dto.ExchangeRateDto;

import java.time.LocalDate;

public interface ExchangeRateClientService {

    ExchangeRateDto getExchangeRates(final LocalDate date,
                                     final RateValue baseCurrency,
                                     final RateValue targetCurrency);

    RatesClient getRates(final int year, final int month, final int day);

    ExchangeRateDto saveExchangeRates(final ExchangeRateDto dto,
                                      final LocalDate date,
                                      final RateValue baseCurrency,
                                      final RateValue targetCurrency);

}
