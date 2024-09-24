package com.menumaster;

import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.paragraphvectors.ParagraphVectors;
import org.deeplearning4j.text.documentiterator.LabelledDocument;
import org.deeplearning4j.text.documentiterator.SimpleLabelAwareIterator;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;

import java.util.List;

public class NLPModel {

    public static void main(String[] args) {
        try {
            // Um LabelledDocument representa um documento de texto associado a um ou mais rótulos (labels).
            // É utilizado para treinamento de modelos de aprendizado de máquina
            // para associar o conteúdo do documento a uma categoria ou intenção específica.
            // Cada LabelledDocument contém um conteúdo de texto e uma lista de rótulos que o descrevem.
            List<LabelledDocument> labelledDocuments = DocumentGenerator.generateLabelledDocuments();

            // Criar o iterador para percorrer o LabelledDocument
            SimpleLabelAwareIterator iterator = new SimpleLabelAwareIterator(labelledDocuments);

            // TokenizerFactory é uma interface que define como o texto deve ser dividido em tokens (palavras ou partes de palavras)
            // para processamento pelo modelo de aprendizado de máquina. O DefaultTokenizerFactory é uma implementação padrão dessa
            // interface que divide o texto em tokens com base em espaços e pontuações, permitindo que o modelo compreenda o texto
            // de forma estruturada. Isso é essencial para treinar ou utilizar o modelo ParagraphVectors com representações de texto.
            TokenizerFactory tokenizerFactory = new DefaultTokenizerFactory();

            // Configurar e treinar o modelo ParagraphVectors
            ParagraphVectors vec = new ParagraphVectors.Builder()
                    // Define o iterador de documentos que o modelo deve usar para treinamento.
                    // 'iterator' é o LabelAwareIterator que fornece os documentos de entrada com seus rótulos.
                    .iterate(iterator)

                    // Define a TokenizerFactory para dividir o texto dos documentos em tokens (palavras ou partes de palavras).
                    // O 'tokenizerFactory' especificado deve ser usado durante o treinamento.
                    .tokenizerFactory(tokenizerFactory)

                    // Define o número de épocas (passagens) que o modelo deve treinar sobre o conjunto de dados.
                    // Neste caso, o modelo será treinado por 10 épocas.
                    .epochs(10)

                    // Define o tamanho da camada oculta do modelo. 'layerSize' indica o número de dimensões dos vetores de palavras.
                    // Aqui, cada palavra será representada por um vetor de 100 dimensões.
                    .layerSize(100)

                    // Define a taxa de aprendizado inicial para o modelo durante o treinamento.
                    // A taxa de aprendizado determina a velocidade com que o modelo ajusta seus parâmetros a cada passo.
                    // Aqui, a taxa de aprendizado inicial é 0.025.
                    .learningRate(0.025)

                    // Constrói o modelo ParagraphVectors com os parâmetros configurados.
                    .build();

            // Inicia o processo de treinamento do modelo com os documentos fornecidos pelo iterador.
            vec.fit();

            // Salva o modelo treinado em um arquivo zip para uso futuro.
            // O modelo é armazenado no caminho especificado: "src/main/resources/model.zip".
            WordVectorSerializer.writeParagraphVectors(vec, "src/main/resources/model.zip");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
