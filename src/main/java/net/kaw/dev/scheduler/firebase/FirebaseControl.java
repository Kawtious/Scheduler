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

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FirebaseControl {

    private static String getMainCollection() {
        return "itson";
    }

    private static CollectionReference getCollectionReference() {
        Firestore firestore = Firebase.getInstance().getFirestore();
        return firestore.collection(getMainCollection());
    }

    private static DocumentReference getDocumentReference(String documentString) {
        return getCollectionReference().document(documentString);
    }

    private static DocumentSnapshot getDocumentSnapshot(DocumentReference docRef) throws InterruptedException, ExecutionException, TimeoutException {
//        System.out.println("Retrieving document");
        // asynchronously retrieve the document
        ApiFuture<DocumentSnapshot> future = docRef.get();
        // ...

//        System.out.println("Retrieved document!");
//        System.out.println("Retrieving document snapshot...");
        // future.get() blocks on response
        DocumentSnapshot document = future.get(5, TimeUnit.MINUTES);

//        System.out.println("Retrieved document snapshot!");
        return document;
    }

    private static void update(String documentString, Map<String, Object> data) throws InterruptedException, ExecutionException, TimeoutException {
        if (data == null) {
            return;
        }

        DocumentReference docRef = getDocumentReference(documentString);
        // ...
        // future.get() blocks on response
        DocumentSnapshot document = getDocumentSnapshot(docRef);

        ApiFuture<WriteResult> result;

        Map<String, Object> map = new HashMap<>();

        map.put((String) data.get("id"), data);

        if (document.exists()) {
            result = docRef.update(map);
        } else {
            result = docRef.set(map);
        }

        System.out.println("Update time : " + result.get().getUpdateTime());
    }

    private static Map<String, Object> get(String documentString) throws InterruptedException, ExecutionException, TimeoutException {
        DocumentReference docRef = getDocumentReference(documentString);
        // ...
        // future.get() blocks on response
        DocumentSnapshot document = getDocumentSnapshot(docRef);

        if (document.exists()) {
            return document.getData();
        } else {
            System.out.println("No such document!");
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> get(String documentString, String id) throws InterruptedException, ExecutionException, TimeoutException {
        DocumentReference docRef = getDocumentReference(documentString);
        // ...
        // future.get() blocks on response
        DocumentSnapshot document = getDocumentSnapshot(docRef);

        if (document.exists()) {
            Map<String, Object> data = document.getData();

            if (data != null) {
                return (Map<String, Object>) data.get(id);
            }
        } else {
            System.out.println("No such document!");
        }

        return null;
    }

    private static void remove(String documentString, String id) throws InterruptedException, ExecutionException, TimeoutException {
        DocumentReference docRef = getDocumentReference(documentString);
        // ...
        // future.get() blocks on response
        DocumentSnapshot document = getDocumentSnapshot(docRef);

        ApiFuture<WriteResult> result;

        if (document.exists()) {
            result = docRef.update(id, FieldValue.delete());
            System.out.println("Update time : " + result.get().getUpdateTime());
        }
    }

    public static class Career {

        public static final String DOCUMENT_STRING = "careers";

        public static void update(Map<String, Object> data) throws InterruptedException, ExecutionException, TimeoutException {
            FirebaseControl.update(DOCUMENT_STRING, data);
        }

        public static Map<String, Object> get() throws InterruptedException, ExecutionException, TimeoutException {
            return FirebaseControl.get(DOCUMENT_STRING);
        }

        public static Map<String, Object> get(String id) throws InterruptedException, ExecutionException, TimeoutException {
            return FirebaseControl.get(DOCUMENT_STRING, id);
        }

        public static void remove(String id) throws InterruptedException, ExecutionException, TimeoutException {
            FirebaseControl.remove(DOCUMENT_STRING, id);
        }

    }

    public static class Teacher {

        public static final String DOCUMENT_STRING = "teachers";

        public static void update(Map<String, Object> data) throws InterruptedException, ExecutionException, TimeoutException {
            FirebaseControl.update(DOCUMENT_STRING, data);
        }

        public static Map<String, Object> get() throws InterruptedException, ExecutionException, TimeoutException {
            return FirebaseControl.get(DOCUMENT_STRING);
        }

        public static Map<String, Object> get(String id) throws InterruptedException, ExecutionException, TimeoutException {
            return FirebaseControl.get(DOCUMENT_STRING, id);
        }

        public static void remove(String id) throws InterruptedException, ExecutionException, TimeoutException {
            FirebaseControl.remove(DOCUMENT_STRING, id);
        }

    }

    public static class Subject {

        public static final String DOCUMENT_STRING = "subjects";

        public static void update(Map<String, Object> data) throws InterruptedException, ExecutionException, TimeoutException {
            FirebaseControl.update(DOCUMENT_STRING, data);
        }

        public static Map<String, Object> get() throws InterruptedException, ExecutionException, TimeoutException {
            return FirebaseControl.get(DOCUMENT_STRING);
        }

        public static Map<String, Object> get(String id) throws InterruptedException, ExecutionException, TimeoutException {
            return FirebaseControl.get(DOCUMENT_STRING, id);
        }

        public static void remove(String id) throws InterruptedException, ExecutionException, TimeoutException {
            FirebaseControl.remove(DOCUMENT_STRING, id);
        }

    }

    public static class ScheduleType {

        public static final String DOCUMENT_STRING = "scheduleTypes";

        public static void update(Map<String, Object> data) throws InterruptedException, ExecutionException, TimeoutException {
            FirebaseControl.update(DOCUMENT_STRING, data);
        }

        public static Map<String, Object> get() throws InterruptedException, ExecutionException, TimeoutException {
            return FirebaseControl.get(DOCUMENT_STRING);
        }

        public static Map<String, Object> get(String id) throws InterruptedException, ExecutionException, TimeoutException {
            return FirebaseControl.get(DOCUMENT_STRING, id);
        }

        public static void remove(String id) throws InterruptedException, ExecutionException, TimeoutException {
            FirebaseControl.remove(DOCUMENT_STRING, id);
        }

    }

    public static class Schedule {

        public static final String DOCUMENT_STRING = "schedules";

        public static void update(Map<String, Object> data) throws InterruptedException, ExecutionException, TimeoutException {
            FirebaseControl.update(DOCUMENT_STRING, data);
        }

        public static Map<String, Object> get() throws InterruptedException, ExecutionException, TimeoutException {
            return FirebaseControl.get(DOCUMENT_STRING);
        }

        public static Map<String, Object> get(String id) throws InterruptedException, ExecutionException, TimeoutException {
            return FirebaseControl.get(DOCUMENT_STRING, id);
        }

        public static void remove(String id) throws InterruptedException, ExecutionException, TimeoutException {
            FirebaseControl.remove(DOCUMENT_STRING, id);
        }

    }

    public static class Group {

        public static final String DOCUMENT_STRING = "groups";

        public static void update(Map<String, Object> data) throws InterruptedException, ExecutionException, TimeoutException {
            FirebaseControl.update(DOCUMENT_STRING, data);
        }

        public static Map<String, Object> get() throws InterruptedException, ExecutionException, TimeoutException {
            return FirebaseControl.get(DOCUMENT_STRING);
        }

        public static Map<String, Object> get(String id) throws InterruptedException, ExecutionException, TimeoutException {
            return FirebaseControl.get(DOCUMENT_STRING, id);
        }

        public static void remove(String id) throws InterruptedException, ExecutionException, TimeoutException {
            FirebaseControl.remove(DOCUMENT_STRING, id);
        }

    }

    public static class Classroom {

        public static final String DOCUMENT_STRING = "classrooms";

        public static void update(Map<String, Object> data) throws InterruptedException, ExecutionException, TimeoutException {
            FirebaseControl.update(DOCUMENT_STRING, data);
        }

        public static Map<String, Object> get() throws InterruptedException, ExecutionException, TimeoutException {
            return FirebaseControl.get(DOCUMENT_STRING);
        }

        public static Map<String, Object> get(String id) throws InterruptedException, ExecutionException, TimeoutException {
            return FirebaseControl.get(DOCUMENT_STRING, id);
        }

        public static void remove(String id) throws InterruptedException, ExecutionException, TimeoutException {
            FirebaseControl.remove(DOCUMENT_STRING, id);
        }

    }

}
