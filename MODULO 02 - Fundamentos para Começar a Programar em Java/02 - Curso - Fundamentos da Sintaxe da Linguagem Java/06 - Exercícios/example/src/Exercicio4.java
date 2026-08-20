import java.util.Scanner;

public class Exercicio4 {
    static void main(String[] args) {
        var scanner = new Scanner(System.in);

        System.out.println("Informe o nome da primeira pessoa:");
        var nome1 = scanner.next();
        System.out.println("Informe a idade da primeira pessoa:");
        var idade1 = scanner.nextInt();

        System.out.println("Informe o nome da segunda pessoa:");
        var nome2 = scanner.next();
        System.out.println("Informe a idade da segunda pessoa:");
        var idade2 = scanner.nextInt();

        var ageDifference = Math.abs(idade1 - idade2);

        System.out.printf("A diferença de idade entre %s e %s é de %d anos.\n", nome1, nome2, ageDifference);
    }
}
