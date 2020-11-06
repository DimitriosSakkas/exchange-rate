package com.bk.exchangerate.mockdata;

import com.bk.exchangerate.model.dto.ExchangeRateDto;
import com.bk.exchangerate.model.dto.ExchangeRateTrend;

public class MockRate {

    public static final float mockExchangeRate = 0.03243323f;
    public static final float mockExchangeRateAverage = 0.03252309f;
    public static final ExchangeRateTrend mockExchangeRateTrend = ExchangeRateTrend.ASCENDING;

    public static ExchangeRateDto createExchangeRateDto() {
        ExchangeRateDto dto = new ExchangeRateDto();
        dto.setExchangeRate(mockExchangeRate);
        dto.setExchangeRateAverage(mockExchangeRateAverage);
        dto.setExchangeRateTrend(mockExchangeRateTrend);
        return dto;
    }
}
