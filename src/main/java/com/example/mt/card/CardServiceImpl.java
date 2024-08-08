package com.example.mt.card;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {
    private static final Logger logger = LoggerFactory.getLogger(CardServiceImpl.class);

    private final CardRepository cardRepository;

    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public CardStatus insertCardStatus(CardStatus card) {
        return cardRepository.save(card);
    }

    @Override
    public GeneralStatusCountDTO countByStatus() {
        return cardRepository.countByStatus();
    }

    @Override
    public GeneralStatusCountDateDTO countByStatusAndDate(LocalDate date) {
        GeneralStatusCountDateDTO result = cardRepository.countByStatusAndDate(date);
        if (result == null) {
            return new GeneralStatusCountDateDTO(date, 0L, 0L);
        }
        return result;
    }

    @Override
    public GeneralStatusCountDTO countByStatusAndDateBetween(LocalDate startDate, LocalDate endDate) {
        GeneralStatusCountDTO result = cardRepository.countByStatusAndDateBetween(startDate, endDate);
        if (result == null) {
            return new GeneralStatusCountDTO(0L, 0L);
        }
        return result;
    }

    @Override
    public GeneralStatusCountDTO countByCityAndStatus(String city) {
        return cardRepository.countByCityAndStatus(city);
    }

    @Override
    public StatusCountDTO countByClientIdAndStatus(int clientId) {
        return cardRepository.countByClientIdAndStatus(clientId);
    }

    @Override
    public GeneralStatusCountDateDTO countByDateAndStatusAndClientId(int clientId, LocalDate date) {
        return cardRepository.countByDateAndStatusAndClientId(clientId, date);
    }

    @Override
    public GeneralStatusCountDTO countByStatusAndClientIdAndDateBetween(int clientId, LocalDate startDate, LocalDate endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        //LocalDate maxThreeMonths = endDate.minusMonths(3);

        //System.out.println("Start date: " + startDate);
        //System.out.println("End date: " + LocalDate.parse(maxThreeMonths.format(formatter)));

        if (endDate.isBefore(startDate.minusMonths(3)) || startDate.isBefore(endDate.minusMonths(3)))
            System.out.println("Not more than 3 months");

        return cardRepository.countByStatusAndClientIdAndDateBetween(clientId, startDate, endDate);
    }

    @Override
    public GeneralStatusCountDTO countByCityAndStatusAndClientId(int clientId, String city) {
        return cardRepository.countByCityAndStatusAndClientId(clientId, city);
    }
}
