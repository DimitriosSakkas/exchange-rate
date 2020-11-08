package com.bk.exchangerate.repository;

import com.bk.exchangerate.model.dao.ExchangeRateDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRateDao, Long> {

    Optional<ExchangeRateDao> findByDate(LocalDate date);
}
