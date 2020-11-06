package com.bk.exchangerate.client;

import com.bk.exchangerate.model.RateValue;
import com.bk.exchangerate.model.client.RatesClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@FeignClient(name = "exchangerateclient", url = "https://api.exchangeratesapi.io/history")
public interface ExchangeRateClient {

    @GetMapping(produces = "application/json")
    RatesClient getExchangeRates(@RequestParam("start_at") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startAt,
                                 @RequestParam("end_at") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endAt,
                                 @RequestParam("symbols") List<RateValue> symbols);

}
