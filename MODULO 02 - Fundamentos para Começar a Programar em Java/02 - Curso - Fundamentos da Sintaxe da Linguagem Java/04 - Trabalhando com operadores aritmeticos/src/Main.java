import java.util.Scanner;

// Exemplo 1: Operações básicas
class Exemplo1 {
    public static void executar(Scanner scanner) {
        System.out.println("--- Exemplo 1: Multiplicação ---");
        System.out.println("Informe o primeiro número:");
        var value1 = scanner.nextInt();
        System.out.println("Informe o segundo número:");
        var value2 = scanner.nextInt();
        System.out.printf("%d * %d = %d\n\n", value1, value2, value1 * value2);
    }
}

// Exemplo 2: Potentiação
class Exemplo2 {
    public static void executar(Scanner scanner) {
        System.out.println("--- Exemplo 2: Potência ---");
        System.out.println("Informe o número:");
        var value1 = scanner.nextInt();
        System.out.printf("A potência de %d elevado a 10 é %.0f\n\n", value1, Math.pow(value1, 10));
    }
}

// Exemplo 3: Pós e Pré-incremento
class Exemplo3 {
    public static void executar() {
        System.out.println("--- Exemplo 3: Incremento ---");
        var value = 50;
        System.out.println(10 + ++value); // Incrementa antes de somar
        System.out.println(10 + value);   // Usa o valor já incrementado
    }
}

public class Main {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        
        Exemplo3.executar();

        scanner.close();
    }
}