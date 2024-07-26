package com.example.mt.card;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<CardStatus, Integer> {
    @Query("SELECT new com.example.mt.card.GeneralStatusCountDTO(" +
            "SUM(CASE WHEN cs.status = 'Visited' THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN cs.status = 'Downloaded' THEN 1 ELSE 0 END)) " +
            "FROM CardStatus cs " +
            "WHERE (cs.status = 'Visited' OR cs.status = 'Downloaded')")
    GeneralStatusCountDTO countByStatus();

    @Query("SELECT new com.example.mt.card.GeneralStatusCountDateDTO(cs.date, " +
            "SUM(CASE WHEN cs.status = 'Visited' THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN cs.status = 'Downloaded' THEN 1 ELSE 0 END)) " +
            "FROM CardStatus cs " +
            "WHERE (cs.status = 'Visited' OR cs.status = 'Downloaded') AND cs.date = :date " +
            "GROUP BY cs.date")
    GeneralStatusCountDateDTO countByStatusAndDate(LocalDate date);

    @Query("SELECT new com.example.mt.card.GeneralStatusCountDTO(" +
            "SUM(CASE WHEN cs.status = 'Visited' THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN cs.status = 'Downloaded' THEN 1 ELSE 0 END)) " +
            "FROM CardStatus cs " +
            "WHERE (cs.status = 'Visited' OR cs.status = 'Downloaded') AND cs.date BETWEEN :startDate AND :endDate")
    GeneralStatusCountDTO countByStatusAndDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT new com.example.mt.card.GeneralStatusCountDTO(" +
            "SUM(CASE WHEN cs.status = 'Visited' THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN cs.status = 'Downloaded' THEN 1 ELSE 0 END)) " +
            "FROM CardStatus cs " +
            "WHERE (cs.status = 'Visited' OR cs.status = 'Downloaded') AND cs.city = :city")
    GeneralStatusCountDTO countByCityAndStatus(String city);

    @Query("SELECT new com.example.mt.card.StatusCountDTO(cs.clientId, " +
            "SUM(CASE WHEN cs.status = 'Visited' THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN cs.status = 'Downloaded' THEN 1 ELSE 0 END)) " +
            "FROM CardStatus cs " +
            "WHERE (cs.status = 'Visited' OR cs.status = 'Downloaded') AND cs.clientId = :clientId " +
            "GROUP BY cs.clientId")
    StatusCountDTO countByClientIdAndStatus(int clientId);

    @Query("SELECT new com.example.mt.card.GeneralStatusCountDateDTO(cs.date, " +
            "SUM(CASE WHEN cs.status = 'Visited' THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN cs.status = 'Downloaded' THEN 1 ELSE 0 END)) " +
            "FROM CardStatus cs " +
            "WHERE (cs.status = 'Visited' OR cs.status = 'Downloaded') AND cs.clientId = :clientId AND cs.date = :date " +
            "GROUP BY cs.date")
    GeneralStatusCountDateDTO countByDateAndStatusAndClientId(int clientId, LocalDate date);

    @Query("SELECT new com.example.mt.card.GeneralStatusCountDTO(" +
            "SUM(CASE WHEN cs.status = 'Visited' THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN cs.status = 'Downloaded' THEN 1 ELSE 0 END)) " +
            "FROM CardStatus cs " +
            "WHERE (cs.status = 'Visited' OR cs.status = 'Downloaded') AND cs.clientId = :clientId AND " +
            "cs.date BETWEEN :startDate AND :endDate")
    GeneralStatusCountDTO countByStatusAndClientIdAndDateBetween(int clientId, LocalDate startDate, LocalDate endDate);

    @Query("SELECT new com.example.mt.card.GeneralStatusCountDTO(" +
            "SUM(CASE WHEN cs.status = 'Visited' THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN cs.status = 'Downloaded' THEN 1 ELSE 0 END)) " +
            "FROM CardStatus cs " +
            "WHERE (cs.status = 'Visited' OR cs.status = 'Downloaded') AND cs.clientId = :clientId AND cs.city = :city")
    GeneralStatusCountDTO countByCityAndStatusAndClientId(int clientId, String city);
}
