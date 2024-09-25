package com.menumaster;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.deeplearning4j.text.documentiterator.LabelledDocument;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LabelledDocumentGenerator {

    public static List<LabelledDocument> generateLabelledDocuments() {
        List<LabelledDocument> labelledDocuments = new ArrayList<>();
        File folder = new File("src/main/resources/intents");
        System.out.println("Quantidade de arquivos a ser processados: " + folder.listFiles().length);
        for (File file : folder.listFiles()) {
            try (FileReader reader = new FileReader(file)) {
                JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
                String intent = json.get("intent").getAsString();
                JsonArray messages = json.get("messages").getAsJsonArray();

                for (int i = 0; i < messages.size(); i++) {
                    LabelledDocument doc = new LabelledDocument();
                    String rawMessage = messages.get(i).getAsString().toLowerCase();
                    doc.setContent(rawMessage);
                    doc.setLabel(intent);
                    labelledDocuments.add(doc);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return labelledDocuments;
    }
}

