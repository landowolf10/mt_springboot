package com.example.mt.card;

import java.time.LocalDate;
import java.util.List;

public interface CardService {
    CardStatus insertCardStatus(CardStatus card);
    GeneralStatusCountDTO countByStatus();
    GeneralStatusCountDateDTO countByStatusAndDate(LocalDate date);
    GeneralStatusCountDTO countByStatusAndDateBetween(LocalDate startDate, LocalDate endDate);
    GeneralStatusCountDTO countByCityAndStatus(String city);
    StatusCountDTO countByClientIdAndStatus(int clientId);
    GeneralStatusCountDateDTO countByDateAndStatusAndClientId(int clientId, LocalDate date);
    GeneralStatusCountDTO countByStatusAndClientIdAndDateBetween(int clientId, LocalDate startDate, LocalDate endDate);
    GeneralStatusCountDTO countByCityAndStatusAndClientId(int clientId, String city);
}
