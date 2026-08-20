import java.util.Scanner;

public class Main {
    private final static String welcomeMessage = "Olá, informe o seu nome";

    static void main(String[] args) {
        var scanner = new Scanner(System.in);
        System.out.println(welcomeMessage);
        var name = scanner.next();
        System.out.println("Informe sua idade");
        var age = scanner.nextInt();
        System.out.printf("Olá %s sua idade é %s \n", name, age);
    }
}
