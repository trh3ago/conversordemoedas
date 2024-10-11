package br.com.alura.moneymakerconversor;

// Define uma classe `record` chamada `Moeda`, que é uma forma concisa de criar classes imutáveis em Java.
public record Moeda(double USD, double ARS, double BRL, double COP) {
    // Este `record` armazena quatro campos do tipo `double`, cada um representando a taxa de câmbio para diferentes moedas:
    // USD: Dólar Americano
    // ARS: Peso Argentino
    // BRL: Real Brasileiro
    // COP: Peso Colombiano

    // O `record` automaticamente gera um construtor, métodos `getter`, `equals()`, `hashCode()`, e `toString()`.
    // Como o `record` é imutável, os valores dessas variáveis não podem ser alterados após serem definidos.
}

