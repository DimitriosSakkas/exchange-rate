package com.bk.exchangerate.model.dto;

import java.util.List;

public enum ExchangeRateTrend {
    DESCENDING, ASCENDING, CONSTANT, UNDEFINED;

    public static ExchangeRateTrend getExchangeRateTrend(List<Float> check) {
        if (check.stream().allMatch(t -> t > 0)) {
            return ExchangeRateTrend.DESCENDING;
        } else if (check.stream().allMatch(t -> t < 0)) {
            return ExchangeRateTrend.ASCENDING;
        } else if (check.stream().allMatch(t -> t == 0)) {
            return ExchangeRateTrend.CONSTANT;
        } else {
            return ExchangeRateTrend.UNDEFINED;
        }
    }
}
