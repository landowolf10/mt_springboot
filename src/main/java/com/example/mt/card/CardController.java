package com.example.mt.card;

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

    //To create a new status in DB (visited and downloaded).
    @PostMapping("/cards")
    public CardStatus registerCardStatus(@RequestBody CardStatus card) {
        return cardService.insertCardStatus(card);
    }

    //Gets all the visited and downloaded cards in general.
    @GetMapping("/cards/count")
    public  ResponseEntity<GeneralStatusCountDTO> countByStatus() {
        return ResponseEntity.ok(cardService.countByStatus());
    }

    //Gets all the visited and downloaded cards in general of a specific date.
    @GetMapping("/cards")
    public ResponseEntity<GeneralStatusCountDateDTO> countByStatusAndDate(@RequestParam("date") LocalDate date) {
        return ResponseEntity.ok(cardService.countByStatusAndDate(date));
    }

    //Gets all the visited and downloaded cards in general given a range of dates.
    @GetMapping("/status/range")
    public ResponseEntity<GeneralStatusCountDTO> countByStatusAndDateBetween(
            @RequestParam("startDate") LocalDate startDate,
            @RequestParam("endDate") LocalDate endDate) {
        return ResponseEntity.ok(cardService.countByStatusAndDateBetween(startDate, endDate));
    }

    //Gets all the visited and downloaded cards in general of a city.
    @GetMapping("/status/{city}")
    public ResponseEntity<GeneralStatusCountDTO> countByCityAndStatus(@PathVariable String city) {
        return ResponseEntity.ok(cardService.countByCityAndStatus(city));
    }

    //Gets the visited/downloaded cards by clientId.
    @GetMapping("/status")
    public ResponseEntity<StatusCountDTO> countByClientIdAndStatus(@RequestParam("clientId") int clientId) {
        return ResponseEntity.ok(cardService.countByClientIdAndStatus(clientId));
    }

    //Gets the visited and downloaded cards of a specific date and clientId.
    @GetMapping("/status/date")
    public ResponseEntity<GeneralStatusCountDateDTO>  countByDateAndStatusAndClientId(
            @RequestParam("clientId") int clientId,
            @RequestParam("date") LocalDate date) {
        return ResponseEntity.ok(cardService.countByDateAndStatusAndClientId(clientId, date));
    }

    @GetMapping("/status/date/range")
    public ResponseEntity<GeneralStatusCountDTO> countByStatusAndClientIdAndDateBetween(
            @RequestParam("clientId") int clientId,
            @RequestParam("startDate") LocalDate startDate,
            @RequestParam("endDate") LocalDate endDate) {
        return ResponseEntity.ok(cardService.countByStatusAndClientIdAndDateBetween(clientId, startDate, endDate));
    }

    @GetMapping("/status/city")
    public ResponseEntity<GeneralStatusCountDTO> countByCityAndClientId(
            @RequestParam("clientId") int clientId,
            @RequestParam("city") String city) {
        return ResponseEntity.ok(cardService.countByCityAndStatusAndClientId(clientId, city));
    }
}
