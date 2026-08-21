import java.util.Scanner;

public class Exercicio1 {
    static void main(String[] args) {
        var scanner = new Scanner(System.in);

        System.out.println("Informe um número para ver a tabuada:");
        var number = scanner.nextInt();

        System.out.printf("--- Tabuada do %d ---\n", number);
        for (var i = 1; i <= 10; i++) {
            System.out.printf("%d x %d = %d\n", number, i, number * i);
        }
    }
}
