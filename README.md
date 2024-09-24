

---

# Classificador de Intenções para Restaurante com IA

Este projeto é um modelo de classificação de intenções baseado em IA, projetado para entender e classificar mensagens de usuários relacionadas a operações de restaurante. O modelo pode identificar intenções como "visualizar o cardápio" ou "criar um prato" com base na entrada do usuário. Ele utiliza aprendizado profundo e processamento de linguagem natural (NLP) com a biblioteca DeepLearning4J (DL4J) para realizar o reconhecimento de intenções.

## Tecnologias Utilizadas

- **Java**: Linguagem de programação principal utilizada para desenvolver o projeto.
- **DeepLearning4J (DL4J)**: Uma biblioteca de aprendizado profundo para Java que oferece ferramentas para construir redes neurais, utilizada aqui para criar e treinar o modelo de classificação de intenções.
- **ND4J**: N-Dimensional Arrays para Java, usado como backend para operações numéricas no DL4J.
- **ParagraphVectors**: Uma implementação dentro do DL4J que permite treinar representações vetoriais de frases, parágrafos ou documentos inteiros, permitindo a classificação de intenções do usuário.
- **SLF4J**: Simple Logging Facade for Java usado para logs ao longo do projeto.

## Como o Projeto Funciona

1. **Preparação dos Dados**:
    - O projeto lê as intenções do usuário a partir de dois arquivos de texto, `view_menu.txt` e `create_dish.txt`.
    - Cada arquivo contém múltiplos exemplos de mensagens de usuários representando diferentes intenções (por exemplo, "Mostrar o cardápio", "Criar um novo prato").

2. **Processamento de Dados**:
    - As mensagens são carregadas e cada uma é transformada em um `LabelledDocument`. Um `LabelledDocument` é um objeto que contém o conteúdo da mensagem e o rótulo associado (intenção).

3. **Treinamento do Modelo**:
    - O modelo `ParagraphVectors` é configurado e treinado usando os documentos rotulados. O modelo aprende a diferenciar as diferentes intenções analisando os dados de texto.
    - Durante o treinamento, o modelo passa por múltiplas iterações (épocas) para refinar sua compreensão das intenções.

4. **Teste**:
    - Após o treinamento, o modelo pode prever a intenção de uma nova mensagem, encontrando o rótulo de intenção mais similar a partir dos dados treinados usando `nearestLabels`.

5. **Salvando o Modelo**:
    - Após o término do treinamento, o modelo é salvo em um arquivo `model.zip`, que pode ser recarregado para futuras previsões.

## Estrutura do Código

- `DocumentGenerator.java`: Responsável por carregar os dados de treinamento dos arquivos de texto e gerar uma lista de objetos `LabelledDocument`.
- `TrainModel.java`: Contém a lógica para configurar, treinar e salvar o modelo ParagraphVectors.
- `TestModel.java`: Usado para testar o modelo treinado com novas entradas de usuários para prever intenções.

---
