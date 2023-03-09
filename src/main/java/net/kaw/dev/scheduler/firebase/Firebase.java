/*
 * MIT License
 * 
 * Copyright (c) 2023 Kawtious
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package net.kaw.dev.scheduler.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Firebase {

    private static Firebase instance;

    public static Firebase getInstance() {
        try {
            if (instance == null) {
                instance = new Firebase();
            }
        } catch (IOException ex) {
            Logger.getLogger(Firebase.class.getName()).log(Level.SEVERE, null, ex);
        }

        return instance;
    }

    private Firebase() throws IOException {
        InputStream serviceAccount = getSecret();

        FirebaseApp.initializeApp(getOptions(getDatabaseUrl(), serviceAccount));
    }

    private FirebaseOptions getOptions(String databaseUrl, InputStream serviceAccount) throws IOException {
        return FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl(databaseUrl)
                .build();
    }

    public Firestore getFirestore() {
        return FirestoreClient.getFirestore();
    }

    private String getDatabaseUrl() {
        return "https://scheduler-160e2-default-rtdb.firebaseio.com/";
    }

    private InputStream getSecret() {
        String secretPath = "secret/scheduler-secret.json";

        String secret = "";

        try (final BufferedReader reader = new BufferedReader(new FileReader(secretPath))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line = reader.readLine();
            String ls = System.getProperty("line.separator");

            while (line != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
                line = reader.readLine();
            }   // delete the last new line separator

            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            secret = stringBuilder.toString();

            InputStream stream = new ByteArrayInputStream(secret.getBytes(StandardCharsets.UTF_8));

            return stream;
        } catch (IOException ex) {
            Logger.getLogger(Firebase.class.getName()).log(Level.SEVERE, null, ex);
        }

        InputStream stream = new ByteArrayInputStream(secret.getBytes(StandardCharsets.UTF_8));

        return stream;
    }

}
