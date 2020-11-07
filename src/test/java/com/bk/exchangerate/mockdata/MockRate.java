package com.bk.exchangerate.mockdata;

import com.bk.exchangerate.model.RateValue;
import com.bk.exchangerate.model.client.RatesClient;
import com.bk.exchangerate.model.dao.ExchangeRateDao;
import com.bk.exchangerate.model.dto.ExchangeRateDto;
import com.bk.exchangerate.model.dto.ExchangeRateTrend;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MockRate {

    public static final String date1 = "2020-09-07";
    public static final String date2 = "2020-09-08";
    public static final String date3 = "2020-09-09";
    public static final String date4 = "2020-09-10";
    public static final String date5 = "2020-09-11";
    public static final String dateSaturday = "2020-09-05";
    public static final String dateInValid = "1999-12-30";
    public static final int yearDate5 = 2020;
    public static final int monthDate5 = 9;
    public static final int dayDate5 = 11;
    public static final String dateInValid2 = LocalDate.now().toString();
    public static final RateValue baseCurrency = RateValue.AUD;
    public static final RateValue targetCurrency = RateValue.BGN;
    public static final RateValue base = RateValue.EUR;

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

    public static ExchangeRateDao createExchangeRateDao() {
        ExchangeRateDao dao = new ExchangeRateDao();
        dao.setExchangeRate(mockExchangeRate);
        dao.setExchangeRateAverage(mockExchangeRateAverage);
        dao.setExchangeRateTrend(mockExchangeRateTrend);
        dao.setDate(LocalDate.parse(MockRate.date5));
        dao.setId(1L);
        dao.setBaseCurrency(baseCurrency);
        dao.setTargetCurrency(targetCurrency);
        return dao;
    }

    public static RatesClient createRatesClient(TreeMap<LocalDate, Map<RateValue, Float>> rates) {
        RatesClient ratesClient = new RatesClient();
        ratesClient.setBase(base);
        ratesClient.setStartAt(LocalDate.parse(MockRate.date1));
        ratesClient.setEndAt(LocalDate.parse(MockRate.date5));
        ratesClient.setRates(rates);
        return ratesClient;
    }

    public static TreeMap<LocalDate, Map<RateValue, Float>> createRates(
            Map<RateValue, Float> rate1,
            Map<RateValue, Float> rate2,
            Map<RateValue, Float> rate3,
            Map<RateValue, Float> rate4,
            Map<RateValue, Float> rate5
    ) {
        TreeMap<LocalDate, Map<RateValue, Float>> rates = new TreeMap<>();
        rates.put(LocalDate.parse(MockRate.date1), rate1);
        rates.put(LocalDate.parse(MockRate.date2), rate2);
        rates.put(LocalDate.parse(MockRate.date3), rate3);
        rates.put(LocalDate.parse(MockRate.date4), rate4);
        rates.put(LocalDate.parse(MockRate.date5), rate5);
        return rates;
    }

    public static Map<RateValue, Float> createRate(float baseCurrencyValue, float targetCurrencyValue) {
        Map<RateValue, Float> rate = new HashMap<>();
        rate.put(baseCurrency, baseCurrencyValue);
        rate.put(targetCurrency, targetCurrencyValue);
        return rate;
    }


}
