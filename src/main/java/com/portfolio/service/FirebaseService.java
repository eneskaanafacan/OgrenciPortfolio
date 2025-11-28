package com.portfolio.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.portfolio.model.ContactMessage;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class FirebaseService {

    @PostConstruct
    public void initialize() {
        try {
            String firebaseConfigJson = System.getenv("FIREBASE_CONFIG_JSON");
            java.io.InputStream serviceAccount;

            if (firebaseConfigJson != null && !firebaseConfigJson.isEmpty()) {
                serviceAccount = new java.io.ByteArrayInputStream(
                        firebaseConfigJson.getBytes(java.nio.charset.StandardCharsets.UTF_8));
                System.out.println("Using Firebase configuration from environment variable.");
            } else {
                serviceAccount = new FileInputStream("firebase-service-account.json");
                System.out.println("Using Firebase configuration from local file.");
            }

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                System.out.println("Firebase initialized successfully.");
            }
        } catch (IOException e) {
            System.err.println("Firebase initialization failed: " + e.getMessage());
            // Don't crash the app, just log the error
            e.printStackTrace();
        }
    }

    public void saveMessage(ContactMessage message) {
        Firestore db = FirestoreClient.getFirestore();

        Map<String, Object> docData = new HashMap<>();
        docData.put("name", message.getName());
        docData.put("email", message.getEmail());
        docData.put("message", message.getMessage());
        docData.put("timestamp", System.currentTimeMillis());

        try {
            db.collection("messages").add(docData).get();
            System.out.println("Message saved to Firestore: " + message);
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error saving message to Firestore: " + e.getMessage());
        }
    }
}
