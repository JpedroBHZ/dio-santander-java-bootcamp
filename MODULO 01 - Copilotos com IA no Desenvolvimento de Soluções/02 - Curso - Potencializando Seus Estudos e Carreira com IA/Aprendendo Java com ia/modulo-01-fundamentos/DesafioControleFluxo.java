import java.util.Scanner;

/**
 * MÓDULO 1: Fundamentos do Java
 * DESAFIO MODELO: Controle de Fluxo e Exceções Personalizadas
 * 
 * Este arquivo demonstra conceitos fundamentais de:
 * 1. Laços de repetição (for) e controle de iteração.
 * 2. Condicionais (if-else).
 * 3. Criação de Exceções customizadas (Checked Exception).
 * 4. Estrutura de tratamento try-catch e lançamento de exceções com 'throw'.
 * 
 * Como Executar no Terminal do Windows:
 * 1. Abra o CMD ou PowerShell e navegue até esta pasta.
 * 2. Execute o comando: java DesafioControleFluxo.java
 */
public class DesafioControleFluxo {

    public static void main(String[] args) {
        Scanner terminal = new Scanner(System.in);
        
        System.out.println("=== 🔀 DESAFIO CONTROLE DE FLUXO ===");
        System.out.println("Este programa receberá dois números inteiros e imprimirá uma sequência de números incrementados.\n");

        System.out.print("Digite o primeiro parâmetro (Inteiro): ");
        int parametroUm = terminal.nextInt();

        System.out.print("Digite o segundo parâmetro (Inteiro): ");
        int parametroDois = terminal.nextInt();
        
        System.out.println("\n------------------------------------------------");
        System.out.println("=== 🔍 INICIANDO CONTAGEM... ===");
        System.out.println("------------------------------------------------\n");

        try {
            // Chamando o método que contém a lógica de negócio e validação
            contar(parametroUm, parametroDois);
            
        } catch (ParametrosInvalidosException exception) {
            // Captura e exibe a mensagem da exceção customizada caso a validação falhe
            System.err.println("❌ Erro de Validação: " + exception.getMessage());
        }

        terminal.close();
    }

    /**
     * Realiza a validação e a impressão dos números baseando-se na diferença
     * entre o segundo e o primeiro parâmetro.
     * 
     * @param parametroUm Primeiro número
     * @param parametroDois Segundo número (deve ser maior que o primeiro)
     * @throws ParametrosInvalidosException Exceção lançada se o primeiro for maior ou igual ao segundo
     */
    static void contar(int parametroUm, int parametroDois) throws ParametrosInvalidosException {
        // Validação da regra de negócio: o primeiro parâmetro NÃO pode ser maior ou igual ao segundo
        if (parametroUm >= parametroDois) {
            throw new ParametrosInvalidosException("O segundo parâmetro deve ser maior que o primeiro!");
        }

        int contagem = parametroDois - parametroUm;
        
        System.out.printf("Diferença calculada: %d. Imprimindo %d ocorrências:%n", contagem, contagem);
        
        // Realiza o laço de repetição (for) para imprimir os números
        for (int i = 1; i <= contagem; i++) {
            System.out.printf("Imprimindo o número %d%n", i);
        }
        
        System.out.println("\n✅ Contagem concluída com sucesso!");
    }
}

/**
 * Exceção personalizada que representa a quebra da regra de validação do controle de fluxo.
 * É uma Checked Exception (herda diretamente de Exception), o que obriga o compilador a exigir
 * um bloco try-catch ou declaração 'throws' na assinatura do método que a chama.
 */
class ParametrosInvalidosException extends Exception {
    
    // Construtor padrão sem mensagem
    public ParametrosInvalidosException() {
        super("Os parâmetros fornecidos são inválidos.");
    }

    // Construtor que aceita uma mensagem customizada
    public ParametrosInvalidosException(String mensagem) {
        super(mensagem);
    }
}
