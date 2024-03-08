package com.example.mt.card;

import org.apache.coyote.Response;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface CardService {
    CardStatus insertCardStatus(CardStatus card);
    Integer countByStatus(String status);
    Integer countByClientIdAndStatus(int clientId, String status);
    Integer countByDateAndStatusAndClientId(LocalDate date, String status, int clientId);
    Integer countByStatusAndClientIdAndDateBetween(LocalDate startDate, LocalDate endDate, String status, int clientId);
    Integer countByCityAndClientId(String city, String status, int clientId);
}
