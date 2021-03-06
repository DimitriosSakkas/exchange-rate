package com.bk.exchangerate.model.client;

import com.bk.exchangerate.model.RateValue;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

@Data
public class RatesClient {

    @JsonProperty("rates")
    private TreeMap<LocalDate, Map<RateValue, Float>> rates;
    @JsonProperty("start_at")
    private LocalDate startAt;
    @JsonProperty("end_at")
    private LocalDate endAt;
    @JsonProperty("base")
    private RateValue base;

}
