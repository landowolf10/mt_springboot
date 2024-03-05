package com.example.mt.client;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Clients registerUser(Clients client, MultipartFile imageFile) {
        try {
            // Save the image file to a folder
            String imagePath = saveImage(imageFile, client.getCategory(), client.getPremium());

            // Set the image path in the client object
            client.setImage(imagePath);

            // Save the client details along with the image path
            return clientRepository.save(client);
        } catch (IOException e) {
            // Handle exception if image saving fails
            e.printStackTrace();
            throw new RuntimeException("Failed to save image or update client data.");
        }

    }

    @Override
    public List<Clients> fetchAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public List<Clients> fetchClientsByCategory(String category, String isPremium) {
        if (isPremium.equalsIgnoreCase("Yes"))
            return clientRepository.getClientByPremium(isPremium);

        return clientRepository.getClientByCategory(category);
    }

    @Override
    public Integer countByCategory(String category) {
        return clientRepository.countByCategory(category);
    }

    private String saveImage(MultipartFile file, String category, String isPremium) throws IOException {
        // Define the folder path where you want to save the image
        String folderPath = "./src/main/resources/cards/" + category + "/";

        // Create the folder if it doesn't exist
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // Get the file name
        String fileName = file.getOriginalFilename();

        System.out.println("Is premium? " + isPremium);

        // Save the file to the specified folder
        File saveFile = new File(folderPath + fileName);

        try (FileOutputStream fos = new FileOutputStream(saveFile)) {
            fos.write(file.getBytes());
        }

        if (isPremium.equalsIgnoreCase("Yes")) {
            String premiumPath = "./src/main/resources/cards/Premium/";

            File premiumFolder = new File(premiumPath);
            if (!premiumFolder.exists()) {
                premiumFolder.mkdirs();
            }

            // Save the file to the specified folder
            File saveFilePremium = new File(premiumPath + fileName);

            try (FileOutputStream fos = new FileOutputStream(saveFilePremium)) {
                fos.write(file.getBytes());
            }
        }

        // Return the image path
        return folderPath + fileName;
    }
}
