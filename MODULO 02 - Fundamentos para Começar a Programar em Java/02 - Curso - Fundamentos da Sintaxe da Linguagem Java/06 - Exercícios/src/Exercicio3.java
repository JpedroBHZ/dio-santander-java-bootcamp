import java.util.Scanner;

public class Exercicio3 {
    static void main(String[] args) {
        var scanner = new Scanner(System.in);

        System.out.println("Informe a base do retângulo:");
        var base = scanner.nextDouble();

        System.out.println("Informe a altura do retângulo:");
        var altura = scanner.nextDouble();

        var area = base * altura;

        System.out.printf("A área do retângulo é %.2f\n", area);
    }
}
