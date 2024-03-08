package com.example.mt.card;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/card")
@CrossOrigin(origins = "http://localhost:4200")
public class CardController {
    private final CardService cardService;

    CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/api_status")
    public String status() {
        return "Server is up and running.";
    }

    @PostMapping("/cards")
    public CardStatus registerCardStatus(@RequestBody CardStatus card) {
        return cardService.insertCardStatus(card);
    }

    @GetMapping("/cards/{status}")
    public Integer countByStatus(@PathVariable String status) {
        return cardService.countByStatus(status);
    }

    @GetMapping("/status")
    public Integer countByClientIdAndStatus(@RequestParam("clientId") int clientId, @RequestParam("status")
        String status) {
        return cardService.countByClientIdAndStatus(clientId, status);
    }

    @GetMapping("/status/date")
    public Integer countByDateAndStatusAndClientId(@RequestParam("date") LocalDate date, @RequestParam("status") String
            status, @RequestParam("clientId") int clientId) {
        return cardService.countByDateAndStatusAndClientId(date, status, clientId);
    }

    @GetMapping("/status/range/{startDate}/{endDate}")
    public Integer countByStatusAndClientIdAndDateBetween(@PathVariable LocalDate startDate, @PathVariable
        LocalDate endDate, @RequestParam("status") String status, @RequestParam("clientId") int clientId) {
        return cardService.countByStatusAndClientIdAndDateBetween(startDate, endDate, status, clientId);
    }

    @GetMapping("/status/city")
    public Integer countByCityAndClientId(@RequestParam("city") String city, @RequestParam("status") String status,
                                                   @RequestParam("clientId") int clientId) {
        return cardService.countByCityAndClientId(city, status, clientId);
    }
}
