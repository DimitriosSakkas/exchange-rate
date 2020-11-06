package com.bk.exchangerate.model.dto;

import lombok.Data;

@Data
public class ExchangeRateDto {

    private float exchangeRate;
    private float exchangeRateAverage;
    private ExchangeRateTrend exchangeRateTrend;
}
