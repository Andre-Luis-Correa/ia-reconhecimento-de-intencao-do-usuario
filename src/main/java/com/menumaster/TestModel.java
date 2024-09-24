package com.menumaster;


import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.paragraphvectors.ParagraphVectors;
import org.deeplearning4j.text.documentiterator.LabelledDocument;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;

import java.util.Collection;

public class TestModel {
    public static void main(String[] args) {
        try {
            // Carregar o modelo treinado
            ParagraphVectors vec = WordVectorSerializer.readParagraphVectors("src/main/resources/model.zip");

            /// Definir a TokenizerFactory para o ParagraphVectors
            TokenizerFactory tokenizerFactory = new DefaultTokenizerFactory();
            vec.setTokenizerFactory(tokenizerFactory);

            // Testar com um novo documento
            LabelledDocument testDoc = new LabelledDocument();
            testDoc.setContent("vc tem o que no cardápio hoje disponível?");

            // Encontrar a intenção mais próxima
            Collection<String> nearestLabels = vec.nearestLabels(testDoc.getContent(), 1);
            if (!nearestLabels.isEmpty()) {
                String nearestLabel = nearestLabels.iterator().next();
                System.out.println("Intenção prevista: " + nearestLabel);
            } else {
                System.out.println("Nenhuma intenção encontrada.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
