package com.example.mt.client;

import com.example.mt.card.CardStatus;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
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

    /*@GetMapping("/{category}/{cardName}")
    public ResponseEntity<Resource> getImageByCategoryAndCardName(@PathVariable
                                                                      String category, @PathVariable String cardName) {
        return clientService.getImageByCategoryAndCardName(category, cardName);
    }*/

    @GetMapping("/cards")
    public List<Clients> getAllCards() {
        return clientService.fetchAllCards();
    }

    @GetMapping("/list/{category}")
    public List<String> getCardNamesByCategory(@PathVariable String category) {
        return clientService.getCardNamesByCategory(category);
    }

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
