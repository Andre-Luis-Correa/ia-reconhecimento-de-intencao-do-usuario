package com.menumaster;

import java.util.Scanner;

public class ChatBot {
    public static void main(String[] args) {
        IntentModel model = new IntentModel();
        model.trainModel(); // Treina o modelo inicialmente

        System.out.println("ChatBot inicializado! Escreva sua mensagem.");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Você: ");
            String userMessage = TextFormatter.removeAccentsAndToLower(scanner.nextLine());

            // Obter a intenção prevista pelo modelo
            String predictedIntent = model.predictIntent(userMessage);
            // Obter a resposta baseada na intenção
            String response = model.getResponse(predictedIntent);

            System.out.println("Bot: [" + predictedIntent + "] " + response);
        }
    }
}