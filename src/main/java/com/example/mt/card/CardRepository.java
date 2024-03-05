package com.example.mt.card;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface CardRepository extends JpaRepository<CardStatus, Integer> {
    Integer countByStatus(String status);
    Integer countByClientIdAndStatus(int clientId, String status);
    Integer countByDateAndStatusAndClientId(LocalDate date, String status, int clientId);
    @Query("SELECT COUNT(c.clientId) FROM CardStatus c WHERE c.date BETWEEN :startDate AND :endDate AND " +
            "c.status = :status AND c.clientId = :clientId ")
    Integer countByStatusAndClientIdAndDateBetween(LocalDate startDate, LocalDate endDate, String status, int clientId);
    Integer countByCityAndStatusAndClientId(String city, String status, int clientId);
}
