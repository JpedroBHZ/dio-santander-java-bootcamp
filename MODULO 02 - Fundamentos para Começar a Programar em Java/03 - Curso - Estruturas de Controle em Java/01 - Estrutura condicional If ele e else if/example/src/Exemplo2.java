import java.util.Scanner;

public class Exemplo2 {
    static void main(String[] args) {
                var scanner = new Scanner(System.in);

                System.out.println("Informe a nota do aluno (0 a 10):");
                var score = scanner.nextDouble();

                if (score >= 9.0) {
                    System.out.println("Desempenho: Excelente!");
                } else if (score >= 7.0) {
                    System.out.println("Desempenho: Aprovado.");
                } else if (score >= 5.0) {
                    System.out.println("Desempenho: Em recuperação.");
                } else {
                    System.out.println("Desempenho: Reprovado.");
        }
    }
}
