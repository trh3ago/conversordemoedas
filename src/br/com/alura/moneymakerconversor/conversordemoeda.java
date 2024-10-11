import br.com.alura.moneymakerconversor.Moeda;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public static class Conversordemoeda {

    // Política de nomeação dos campos para o Gson
    private static final FieldNamingPolicy namingPolicy = FieldNamingPolicy.UPPER_CAMEL_CASE;

    // Instância do Gson com a política de nomeação configurada
    private static final Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(namingPolicy)
            .create();


    // URL da API de taxas de câmbio com chave de API
    String endereco;

    {
        endereco = "https://v6.exchangerate-api.com/v6/8920bac4d9e791a31fd64812/latest/USD/BRL";
    }

    // Cria um cliente HTTP para enviar solicitações
    HttpClient client = HttpClient.newHttpClient();

    // Cria uma solicitação HTTP para o endpoint da API
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(endereco)) // Define a URI da API
            .build();

    // Envia a solicitação e recebe a resposta em formato String
    HttpResponse<String> response = client
            .send(request, HttpResponse.BodyHandlers.ofString());

    // Armazena o corpo da resposta JSON em uma String
    String json = response.body();

    // Converte o JSON recebido para o objeto `Moeda`
    Moeda moeda; // Converte as taxas de câmbio para o object `Moeda`

    // Configura o formato decimal para exibir até 14 casas decimais
    DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.ENGLISH); // Define os símbolos decimais para o formato inglês (ponto como separador)
    DecimalFormat decimalFormat = new DecimalFormat("0.00000000000000", dfs); // Define o formato para o valor numérico

    // Loop para exibir o menu até que o usuário escolha sair (opção 7)
    {

        // Exibe o menu de opções ao usuário
        System.out.println("\n");
        System.out.println("***********************************************************");
        System.out.println("Seja bem vindo(a) ao Conversor de Moedas!");
        System.out.println("1) Dólar =>> Peso argentino");
        System.out.println("2) Peso argentino =>> Dólar");
        System.out.println("3) Dólar =>> Real brasileiro");
        System.out.println("4) Real brasileiro =>> Dólar");
        System.out.println("5) Dólar =>> Peso colombiano");
        System.out.println("6) Peso colombiano =>> Dólar");
        System.out.println("7) Sair");
        System.out.println("Escolha uma opção válida:");
        System.out.print("***********************************************************");
        System.out.print("\n");
        Scanner leitura = new Scanner(System.in);

        int opcao = leitura.nextInt(); // Lê a opção escolhida pelo usuário

        if (opcao == 1) {
            // Converte de Dólar para Peso Argentino
            System.out.println("Digite o valor que deseja converter:");
            double quantidade = leitura.nextDouble(); // Lê a quantidade a ser convertida
            double taxaConversao = moeda.ARS(); // Obtém a taxa de conversão do objeto `Moeda`
            double resultado = converterDolarParaDemaisMoedas(quantidade, taxaConversao); // Realiza a conversão

            // Exibe o resultado formatado
            System.out.print("\n");
            System.out.print("Valor " + quantidade + " [USD] corresponde ao valor final de =>>> " + decimalFormat.format(resultado) + " [ARS]");

        } else if (opcao == 2) {
            // Converte de Peso Argentino para Dólar
            System.out.println("Digite o valor que deseja converter:");
            double quantidade = leitura.nextDouble(); // Lê a quantidade a ser convertida
            double taxaConversao = moeda.ARS(); // Obtém a taxa de conversão
            double resultado = converterArsParaUsd(quantidade, taxaConversao); // Realiza a conversão

            // Exibe o resultado formatado
            System.out.print("\n");
            System.out.print("Valor " + quantidade + " [ARS] corresponde ao valor final de =>>> " + decimalFormat.format(resultado) + " [USD]");
        } else if (opcao == 3) {
            // Converte de Dólar para Real Brasileiro
            System.out.println("Digite o valor que deseja converter:");
            double quantidade = leitura.nextDouble(); // Lê a quantidade a ser convertida
            // Obtém a taxa de conversão
            double taxaConversao = moeda.BRL();
            double resultado = converterDolarParaDemaisMoedas(quantidade, taxaConversao); // Realiza a conversão

            // Exibe o resultado formatado
            System.out.print("\n");
            System.out.print("Valor " + quantidade + " [USD] corresponde ao valor final de =>>> " + decimalFormat.format(resultado) + " [BRL]");
        } else if (opcao == 4) {
            // Converte de Real Brasileiro para Dólar
            System.out.println("Digite o valor que deseja converter:");
            double quantidade = leitura.nextDouble(); // Lê a quantidade a ser convertida
            double taxaConversao = moeda.BRL(); // Obtém a taxa de conversão
            double resultado = converterBrlParaUsd(quantidade, taxaConversao); // Realiza a conversão

            // Exibe o resultado formatado
            System.out.print("\n");
            System.out.print("Valor " + quantidade + " [BRL] corresponde ao valor final de =>>> " + decimalFormat.format(resultado) + " [USD]");
        } else if (opcao == 5) {
            // Converte de Dólar para Peso Colombiano
            System.out.println("Digite o valor que deseja converter:");
            double quantidade = leitura.nextDouble(); // Lê a quantidade a ser convertida
            double taxaConversao = moeda.COP(); // Obtém a taxa de conversão
            double resultado = converterDolarParaDemaisMoedas(quantidade, taxaConversao); // Realiza a conversão

            // Exibe o resultado formatado
            System.out.print("\n");
            System.out.print("Valor " + quantidade + " [USD] corresponde ao valor final de =>>> " + decimalFormat.format(resultado) + " [COP]");
        } else if (opcao == 6) {
            // Converte de Peso Colombiano para Dólar
            System.out.println("Digite o valor que deseja converter:");
            double quantidade = leitura.nextDouble(); // Lê a quantidade a ser convertida
            // Obtém a taxa de conversão
            double taxaConversao;
            taxaConversao = moeda.COP();
            double resultado = converterCopParaUsd(quantidade, taxaConversao); // Realiza a conversão

            // Exibe o resultado formatado
            System.out.print("\n");
            System.out.print("Valor " + quantidade + " [COP] corresponde ao valor final de =>>> " + decimalFormat.format(resultado) + " [USD]");
        } else if (opcao == 7) {
            // Encerra o programa
            System.out.print("\n");
            System.out.println("Programa encerrado!");
        } else {
            // Caso o usuário escolha uma opção inválida
            System.out.print("\n");
            System.out.println("Opção inválida, tente novamente!");

        }

    }

    /**
     */
    public void conversordemoedas() {
    }

    public Conversordemoeda() throws IOException, InterruptedException {
        moeda = gson.fromJson(
                gson.fromJson(json, JsonObject.class) // Converte a String JSON para um JsonObject
                        .getAsJsonObject("conversion_rates") // Obtain o objeto "conversion_rates" que contém as taxas de câmbio
                        .toString(), Moeda.class);
    }

    public void menu() {
    }
}

// Método que realiza a conversão de Dólar para outras moedas
static double converterDolarParaDemaisMoedas(double quantidade, double taxaConversao) {
    return quantidade * taxaConversao; // Multiplica a quantidade pela taxa de conversão
}

// Método que realiza a conversão de Peso Argentino para Dólar
static double converterArsParaUsd(double quantidade, double taxaConversao) {
    return quantidade / taxaConversao; // Divide a quantidade pela taxa de conversão
}

// Método que realiza a conversão de Real Brasileiro para Dólar
static double converterBrlParaUsd(double quantidade, double taxaConversao) {
    return quantidade / taxaConversao; // Divide a quantidade pela taxa de conversão
}

// Método que realiza a conversão de Peso Colombiano para Dólar
static double converterCopParaUsd(double quantidade, double taxaConversao) {
    return quantidade / taxaConversao; // Divide a quantidade pela taxa de conversão
}

public void conversormain() {
}


// Aqui você pode adicionar o método main
public static void main(String[] ignoredArgs) {
    try {
        new Conversordemoeda().menu();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
