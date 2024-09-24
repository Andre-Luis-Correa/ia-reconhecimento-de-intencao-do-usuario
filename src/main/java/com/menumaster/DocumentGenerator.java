package com.menumaster;

import org.deeplearning4j.text.documentiterator.LabelledDocument;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DocumentGenerator {

    public static List<LabelledDocument> generateLabelledDocuments() {
        List<LabelledDocument> labelledDocuments = new ArrayList<>();

        // Faz a leitura e processamento do arquivo "view_menu.txt"
        labelledDocuments.addAll(readDocumentsFromFile("src/main/resources/intents/view_menu.txt", "view_menu"));

        // Faz a leitura e processamento do arquivo "create_dish.txt"
        labelledDocuments.addAll(readDocumentsFromFile("src/main/resources/intents/create_dish.txt", "create_dish"));

        return labelledDocuments;
    }

    private static List<LabelledDocument> readDocumentsFromFile(String filePath, String label) {
        List<LabelledDocument> documents = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                LabelledDocument doc = new LabelledDocument();
                doc.setContent(line);
                doc.setLabel(label);
                documents.add(doc);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return documents;
    }
}
