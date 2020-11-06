package com.bk.exchangerate.controller;

import com.bk.exchangerate.exception.WrongDateException;
import com.bk.exchangerate.model.RateValue;
import com.bk.exchangerate.model.client.RatesClient;
import com.bk.exchangerate.model.dto.ExchangeRateDto;
import com.bk.exchangerate.service.ExchangeRateClientService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/exchange-rate")
public class ExchangeRateController {

    private final ExchangeRateClientService exchangeRateClientService;

    public ExchangeRateController(ExchangeRateClientService exchangeRateClientService) {
        this.exchangeRateClientService = exchangeRateClientService;
    }

    @ExceptionHandler({WrongDateException.class})
    @PutMapping("/{date}/{baseCurrency}/{targetCurrency}")
    public ExchangeRateDto getExchangeRates(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                            @PathVariable RateValue baseCurrency,
                                            @PathVariable RateValue targetCurrency) {

        checkDate(date);
        ExchangeRateDto dto = exchangeRateClientService.getExchangeRates(date, baseCurrency, targetCurrency);
        exchangeRateClientService.saveExchangeRates(dto, date, baseCurrency, targetCurrency);
        return dto;
    }

    @GetMapping("/history/daily/{yyyy}/{MM}/{dd}")
    public RatesClient getDailyRates(@PathVariable(value = "yyyy") int year,
                                     @PathVariable(value = "MM") int month,
                                     @PathVariable(value = "dd") int day) {

        return exchangeRateClientService.getRates(year, month, day);
    }

    @GetMapping("/history/monthly/{yyyy}/{MM}")
    public RatesClient getMonthlyRates(@PathVariable(value = "yyyy") int year,
                                       @PathVariable(value = "MM") int month) {

        return exchangeRateClientService.getRates(year, month, 0);
    }

    private void checkDate(LocalDate date) {
        LocalDate before2000 = LocalDate.of(2000, 1, 1);
        LocalDate yesterday = LocalDate.now().minusDays(1);

        if (date.isBefore(before2000) || date.isAfter(yesterday)) {
            throw new WrongDateException("The following date is before 2000-01-01 or after yesterday, " + date);
        }
    }
}
