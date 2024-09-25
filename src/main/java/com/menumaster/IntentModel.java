package com.menumaster;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.paragraphvectors.ParagraphVectors;
import org.deeplearning4j.text.documentiterator.LabelAwareIterator;
import org.deeplearning4j.text.documentiterator.LabelledDocument;
import org.deeplearning4j.text.documentiterator.SimpleLabelAwareIterator;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;

import java.io.FileReader;
import java.util.List;

public class IntentModel {

    private ParagraphVectors vec;

    public void trainModel() {
        List<LabelledDocument> documents = LabelledDocumentGenerator.generateLabelledDocuments();
        SimpleLabelAwareIterator iterator = new SimpleLabelAwareIterator(documents);
        TokenizerFactory tokenizerFactory = new DefaultTokenizerFactory();

        vec = new ParagraphVectors.Builder()
                .iterate(iterator)
                .tokenizerFactory(tokenizerFactory)
                .epochs(10)
                .layerSize(100)
                .learningRate(0.025)
                .build();

        vec.fit();
        WordVectorSerializer.writeParagraphVectors(vec, "src/main/resources/model_trained.zip");
    }

    public void loadModel() {
        try {
            vec = WordVectorSerializer.readParagraphVectors("src/main/resources/model_trained.zip");
            vec.setTokenizerFactory(new DefaultTokenizerFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String predictIntent(String message) {
        return vec.nearestLabels(message, 1).iterator().next();
    }

    public String getResponse(String intent) {
        try (FileReader reader = new FileReader("src/main/resources/intents/" + intent + ".json")) {
            JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
            JsonArray responses = json.get("responses").getAsJsonArray();
            return responses.get((int) (Math.random() * responses.size())).getAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Desculpe, não consegui entender.";
    }
}
