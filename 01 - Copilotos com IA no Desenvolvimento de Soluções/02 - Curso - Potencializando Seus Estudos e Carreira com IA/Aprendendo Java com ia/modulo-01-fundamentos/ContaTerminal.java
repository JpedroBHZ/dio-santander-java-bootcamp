import java.util.Locale;
import java.util.Scanner;

/**
 * MÓDULO 1: Fundamentos do Java
 * DESAFIO MODELO: Conta Terminal (Simulador de Conta Bancária)
 * 
 * Este arquivo demonstra conceitos fundamentais de:
 * 1. Declaração de variáveis de tipos primitivos (int) e referências (String, double).
 * 2. Uso da classe Scanner para leitura de dados via terminal.
 * 3. Formatação e concatenação de Strings.
 * 
 * Como Executar no Terminal do Windows:
 * 1. Abra o CMD ou PowerShell e navegue até esta pasta.
 * 2. Execute o comando: java ContaTerminal.java
 */
public class ContaTerminal {

    public static void main(String[] args) {
        // Criamos o objeto Scanner para receber entradas do usuário pelo console.
        // O Locale.US serve para que o Scanner reconheça o ponto (.) como separador de decimais para double.
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        System.out.println("=== 🏦 BEM-VINDO AO SIMULADOR DE CONTA BANCÁRIA ===");
        System.out.println("Por favor, digite as informações solicitadas nos campos abaixo.\n");

        // 1. Solicitando e lendo o número da Agência (Texto com hífen, ex: 067-8)
        System.out.print("Por favor, digite o número da Agência (Ex: 067-8): ");
        String agencia = scanner.nextLine();

        // 2. Solicitando e lendo o número da Conta (Inteiro, ex: 1021)
        System.out.print("Por favor, digite o número da Conta (Ex: 1021): ");
        int numero = scanner.nextInt();
        
        // Consumir a quebra de linha pendente após ler o inteiro (nextInt não consome o \n)
        scanner.nextLine();

        // 3. Solicitando e lendo o Nome do Cliente (Texto, ex: MARIO ANDRADE)
        System.out.print("Por favor, digite o seu Nome Completo: ");
        String nomeCliente = scanner.nextLine();

        // 4. Solicitando e lendo o Saldo Inicial (Decimal, ex: 237.48)
        System.out.print("Por favor, digite o seu Saldo Inicial (Use ponto para centavos, Ex: 237.48): ");
        double saldo = scanner.nextDouble();

        System.out.println("\n------------------------------------------------");
        System.out.println("=== 🔍 PROCESSANDO CADASTRO DA CONTA... ===");
        System.out.println("------------------------------------------------\n");

        // Exibindo a mensagem formatada final utilizando concatenação simples (exigido no desafio)
        String mensagemConcat = "Olá " + nomeCliente + ", obrigado por criar uma conta em nosso banco, "
                + "sua agência é " + agencia + ", conta " + numero + " e seu saldo " + saldo 
                + " já está disponível para saque.";

        System.out.println(mensagemConcat);

        System.out.println("\n--- Dica do Desenvolvedor Backend ---");
        // Também podemos fazer o mesmo usando System.out.printf para manter o código limpo e elegante!
        System.out.printf("Exemplo com printf: Olá %s, sua conta de número %d (Agência %s) possui R$ %.2f de saldo.%n", 
                nomeCliente, numero, agencia, saldo);

        // Sempre feche o Scanner para evitar vazamento de recursos (Resource Leak)
        scanner.close();
    }
}
