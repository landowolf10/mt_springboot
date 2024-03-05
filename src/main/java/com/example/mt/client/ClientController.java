package com.example.mt.client;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/client")
@CrossOrigin(origins = "http://localhost:4200")
public class ClientController {
    private final ClientService clientService;

    ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    /*@GetMapping("/cards")
    public List<CardStatus> getAllCards() {
        return cardService.fetchAllCards();
    }*/

    @PostMapping("/register")
    public Clients registerUser(@ModelAttribute Clients client,
                                @RequestParam("imageFile") MultipartFile imageFile) {
        return clientService.registerUser(client, imageFile);
    }

    @GetMapping("/clients")
    public List<Clients> fetchAllClients() {
        return clientService.fetchAllClients();
    }

    @GetMapping("/category")
    public List<Clients> fetchClientsByCategory(
            @RequestParam("category") String category,
            @RequestParam("isPremium") String isPremium
    ) {
        return clientService.fetchClientsByCategory(category, isPremium);
    }

    @GetMapping()
    public Integer countByCategory(@RequestParam("category") String category) {
        return clientService.countByCategory(category);
    }
}
