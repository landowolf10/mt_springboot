package com.example.mt.client;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.mt.card.CardStatus;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Clients> fetchAllCards() {
        return clientRepository.findAll();
    }

    /*@Override
    public ResponseEntity<Resource> getImageByCategoryAndCardName(String category, String cardName) {
        try {
            String externalFolderPath = "http://res.cloudinary.com/dbwgqd2ap/image/upload/v1709750439/cards/" + category
                    + '/' + imageName;

            //Path imagePath = Paths.get(externalFolderPath, category, imageName);
            System.out.println("Image path: " + externalFolderPath);
            Resource resource = new FileSystemResource(externalFolderPath);

            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(clientRepository.getImageByCategoryAndCardName(category, cardName));
    }*/

    @Override
    public List<String> getCardNamesByCategory(String category) {
        List<Clients> clientsList = clientRepository.getCardNameByCategory(category);
        List<String> cardNames = new ArrayList<>();

        for (Clients client : clientsList) {
            cardNames.add(client.getCardName()); // Assuming getCardName() retrieves the card name from a Clients object
        }

        System.out.println("Image names: " + cardNames);

        return cardNames;
    }

    @Override
    public Clients registerUser(Clients client, MultipartFile imageFile) {
        try {
            // Save the image file to a folder
            String imagePath = saveImage(imageFile, client.getCategory(), client.getPremium());

            System.out.println("Uploaded image path: " + imagePath);

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

    /*private String saveImage(MultipartFile file, String category, String isPremium) throws IOException {
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dbwgqd2ap",
                "api_key", "916662365919522",
                "api_secret", "jA_BDVGQcd0BAkzbmx-hD1q6-SA"
        ));

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

        Map uploadedFile = cloudinary.uploader().upload(new File("https://upload.wikimedia.org/wikipedia/commons/a/ae/Olympic_flag.jpg"),
                ObjectUtils.asMap("public_id", "olympic_flag"));

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
    }*/

    private String saveImage(MultipartFile file, String category, String isPremium) throws IOException {
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dbwgqd2ap",
                "api_key", "916662365919522",
                "api_secret", "jA_BDVGQcd0BAkzbmx-hD1q6-SA"
        ));

        // Get the file name
        String fileName = file.getOriginalFilename();

        // Define the folder path where you want to save the image
        String folderPath = "cards/" + category + "/";

        // Set options for uploading
        Map<String, Object> options = new HashMap<>();
        options.put("folder", folderPath);

        // Upload the file to Cloudinary
        Map uploadedFile = cloudinary.uploader().upload(file.getBytes(), options);

        if (isPremium.equalsIgnoreCase("Yes")) {
            String premiumPath = "cards/Premium/";

            // Set options for premium uploading
            Map<String, Object> premiumOptions = new HashMap<>();
            premiumOptions.put("folder", premiumPath);

            // Upload the file to Cloudinary for premium
            Map uploadedPremiumFile = cloudinary.uploader().upload(file.getBytes(), premiumOptions);
        }

        // Return the image path
        return uploadedFile.get("url").toString();
    }

}
