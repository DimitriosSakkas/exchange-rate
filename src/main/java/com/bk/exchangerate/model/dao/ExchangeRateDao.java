package com.bk.exchangerate.model.dao;

import com.bk.exchangerate.model.RateValue;
import com.bk.exchangerate.model.dto.ExchangeRateTrend;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "rates")
@Data
public class ExchangeRateDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDate date;

    @Column(nullable = false)
    private float exchangeRate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RateValue baseCurrency;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RateValue targetCurrency;

    @Column(nullable = false)
    private float exchangeRateAverage;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ExchangeRateTrend exchangeRateTrend;

}
