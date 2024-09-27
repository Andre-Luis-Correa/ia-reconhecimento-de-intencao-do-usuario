package com.menumaster;

import java.text.Normalizer;

public class TextFormatter {

    public static String removeAccentsAndToLower(String input) {
        if (input == null) {
            return null;
        }

        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);

        return normalized.replaceAll("\\p{M}", "").toLowerCase();
    }

    public static void main(String[] args) {
        // Exemplo de uso
        String original = "Olá, Mundo! É um EXEMPLO com acentuação.";
        String formatted = TextFormatter.removeAccentsAndToLower(original);

        System.out.println("Original: " + original);
        System.out.println("Formatado: " + formatted);
    }
}
