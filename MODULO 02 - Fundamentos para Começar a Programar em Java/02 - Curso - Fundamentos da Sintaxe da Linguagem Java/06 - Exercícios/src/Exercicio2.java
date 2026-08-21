import java.util.Scanner;

public class Exercicio2 {
    static void main(String[] args) {
        var scanner = new Scanner(System.in);

        System.out.println("Informe o tamanho do lado do quadrado:");
        var lado = scanner.nextDouble();
        var area = lado * lado;

        System.out.printf("A área do quadrado é %.2f\n", area);
    }
}
