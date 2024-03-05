package com.example.mt.client;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public interface ClientService {
    Clients registerUser(Clients client, MultipartFile imageFile);
    List<Clients> fetchAllClients();
    List<Clients> fetchClientsByCategory(String category, String isPremium);
    Integer countByCategory(String category);
}
