import java.util.Scanner;

public class Exercicio3 {
    static void main(String[] args) {
        var scanner = new Scanner(System.in);

        System.out.println("Informe o primeiro número (menor):");
        var num1 = scanner.nextInt();

        System.out.println("Informe o segundo número (maior que o primeiro):");
        var num2 = scanner.nextInt();

        System.out.println("Escolha uma opção (1 - Par | 2 - Ímpar):");
        var option = scanner.nextInt();

        // O resto deve ser 0 para Par e diferente de 0 (ou 1) para Ímpar
        var expectedRemainder = (option == 1) ? 0 : 1;

        System.out.printf("Números no intervalo entre %d e %d em ordem decrescente:\n", num2, num1);

        // Iteração decrescente do maior (num2) até o menor (num1)
        for (var i = num2; i >= num1; i--) {
            if (Math.abs(i % 2) == expectedRemainder) {
                System.out.println(i);
            }
        }
    }
}
