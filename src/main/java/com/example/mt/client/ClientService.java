package com.example.mt.client;

import com.example.mt.card.CardStatus;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public interface ClientService {
    //ResponseEntity<Resource> getImageByCategoryAndCardName(String category, String cardName);
    List<Clients> fetchAllCards();
    //List<String> getCardNameByCardName(String category);
    List<String> getCardNamesByCategory(String category);
    Clients registerUser(Clients client, MultipartFile imageFile);
    List<Clients> fetchAllClients();
    List<Clients> fetchClientsByCategory(String category);
    List<Clients> fetchClientsByPremium(String isPremium);
    Integer countByCategory(String category);
}
