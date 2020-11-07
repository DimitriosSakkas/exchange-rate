package com.bk.exchangerate.repository;

import com.bk.exchangerate.model.dao.ExchangeRateDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRateDao, Long> {

    ExchangeRateDao findByDate(LocalDate date);
}
