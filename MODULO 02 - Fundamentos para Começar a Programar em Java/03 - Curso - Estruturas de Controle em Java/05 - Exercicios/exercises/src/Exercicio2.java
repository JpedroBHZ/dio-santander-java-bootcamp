import java.util.Scanner;

public class Exercicio2 {
    static void main(String[] args) {
        var scanner = new Scanner(System.in);

        System.out.println("Informe o seu peso em kg (ex: 70,5):");
        var weight = scanner.nextDouble();

        System.out.println("Informe a sua altura em metros (ex: 1,75):");
        var height = scanner.nextDouble();

        var imc = weight / (height * height);

        System.out.printf("Seu IMC é: %.2f\n", imc);

        if (imc <= 18.5) {
            System.out.println("Abaixo do peso");
        } else if (imc <= 24.9) {
            System.out.println("Peso ideal");
        } else if (imc <= 29.9) {
            System.out.println("Levemente acima do peso");
        } else if (imc <= 34.9) {
            System.out.println("Obesidade grau I");
        } else if (imc <= 39.9) {
            System.out.println("Obesidade grau II (Severa)");
        } else {
            System.out.println("Obesidade III (Mórbida)");
        }
    }
}
