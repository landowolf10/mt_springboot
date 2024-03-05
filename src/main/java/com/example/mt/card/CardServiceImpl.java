package com.example.mt.card;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {
    private static final Logger logger = LoggerFactory.getLogger(CardServiceImpl.class);

    private final CardRepository cardRepository;

    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public ResponseEntity<Resource> getImageByCategory(String category, String imageName) {
        try {
            String externalFolderPath = "./src/main/resources/cards/";

            Path imagePath = Paths.get(externalFolderPath, category, imageName);
            Resource resource = new FileSystemResource(imagePath);

            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public List<String> getCardNamesByCategory(String category) {
        String imageFolderPath = "C:/Users/orlan/Documents/Projects/Angular/mini-tourist/src/assets/cards/" + category;

        System.out.println("Image folder path: " + imageFolderPath);

        File imageFolder = new File(imageFolderPath);
        File[] imageFiles = imageFolder.listFiles();

        List<String> imageNames;

        imageNames = new ArrayList<>();
        if (imageFiles != null) {
            Arrays.stream(imageFiles)
                    .filter(File::isFile)
                    .forEach(file -> imageNames.add(file.getName()));


        }

        System.out.println("Image names: " + imageNames);

        return  imageNames;
    }

    @Override
    public List<CardStatus> fetchAllCards() {
        return cardRepository.findAll();
    }

    @Override
    public CardStatus insertCardStatus(CardStatus card) {
        return cardRepository.save(card);
    }

    @Override
    public Integer countByStatus(String status) {
        return cardRepository.countByStatus(status);
    }

    @Override
    public Integer countByClientIdAndStatus(int clientId, String status) {
        return cardRepository.countByClientIdAndStatus(clientId, status);
    }

    @Override
    public Integer countByDateAndStatusAndClientId(LocalDate date, String status, int clientId) {
        return cardRepository.countByDateAndStatusAndClientId(date, status, clientId);
    }

    @Override
    public Integer countByStatusAndClientIdAndDateBetween(LocalDate startDate, LocalDate endDate, String status,
                                                          int clientId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        //LocalDate maxThreeMonths = endDate.minusMonths(3);

        //System.out.println("Start date: " + startDate);
        //System.out.println("End date: " + LocalDate.parse(maxThreeMonths.format(formatter)));

        if (endDate.isBefore(startDate.minusMonths(3)) || startDate.isBefore(endDate.minusMonths(3)))
            System.out.println("Not more than 3 months");

        return cardRepository.countByStatusAndClientIdAndDateBetween(startDate, endDate, status, clientId);
    }

    @Override
    public Integer countByCityAndClientId(String city, String status, int clientId) {
        return cardRepository.countByCityAndStatusAndClientId(city, status, clientId);
    }
}
