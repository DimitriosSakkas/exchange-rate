package com.bk.exchangerate.mockdata;

import com.bk.exchangerate.model.RateValue;
import com.bk.exchangerate.model.client.RatesClient;
import com.bk.exchangerate.model.dao.ExchangeRateDao;
import com.bk.exchangerate.model.dto.ExchangeRateDto;
import com.bk.exchangerate.model.dto.ExchangeRateTrend;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

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
    public static final float baseCurrencyValue = 1.541f;
    public static final RateValue targetCurrency = RateValue.BGN;
    public static final float targetCurrencyValue = 9.3952f;
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


    public static RatesClient createRatesClientDaily() {
        RatesClient ratesClient = new RatesClient();
        return ratesClient;
    }

    public static RatesClient createRatesClientMonthly() {
        RatesClient ratesClient = new RatesClient();
        ratesClient.setBase(base);
        ratesClient.setStartAt(LocalDate.parse(MockRate.date1));
        //ratesClient.setEndAt(LocalDate.parse(MockRate.endAt));
        // ratesClient.setRates();
        return ratesClient;
    }

    private static Map<LocalDate, Map<RateValue, Float>> createRatesDaily() {
        Map<RateValue, Float> rateValueFloatMap = new HashMap<>();
        rateValueFloatMap.put(baseCurrency, baseCurrencyValue);
        rateValueFloatMap.put(targetCurrency, targetCurrencyValue);
        Map<LocalDate, Map<RateValue, Float>> rates = new HashMap<>();
        return rates;
    }

    private static Map<LocalDate, Map<RateValue, Float>> createRatesMonthly() {
        Map<RateValue, Float> rateValueFloatMap = new HashMap<>();
        rateValueFloatMap.put(baseCurrency, baseCurrencyValue);
        rateValueFloatMap.put(targetCurrency, targetCurrencyValue);
        Map<LocalDate, Map<RateValue, Float>> rates = new HashMap<>();
        return rates;
    }
}
