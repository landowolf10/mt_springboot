package com.example.mt.client;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/selected")
    public ResponseEntity<Clients> getClientByClientId(@RequestParam("clientId") Long clientId) {
        return ResponseEntity.ok(clientService.getClientByClientId(clientId));
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/cards")
    public List<Clients> getAllClients() {
        return clientService.fetchAllCards();
    }

    /*@ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/cards")
    public List<Clients> getAllCards() {
        return clientService.fetchAllCards();
    }*/

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
            @RequestParam("category") String category
    ) {
        return clientService.fetchClientsByCategory(category);
    }

    @GetMapping("/category/premium")
    public List<Clients> fetchClientsByPremium(
            @RequestParam("isPremium") String isPremium
    ) {
        return clientService.fetchClientsByPremium(isPremium);
    }

    @GetMapping()
    public Integer countByCategory(@RequestParam("category") String category) {
        return clientService.countByCategory(category);
    }
}
