import java.util.Scanner;

public class Exercicio3 {

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);

        // Capacidades e níveis iniciais
        var agua = 30; // Capacidade máxima: 30L
        var shampoo = 10; // Capacidade máxima: 10L

        var temPet = false;
        var petLimpo = false;
        var maquinaSujou = false; // Fica true se o pet for retirado sem estar limpo

        var executando = true;

        while (executando) {
            System.out.println("\n===== MÁQUINA DE BANHO PETSHOP =====");
            System.out.println("Status da máquina: " + (maquinaSujou ? "SUJA (Necessita limpeza!)" : "PRONTA/PARCIAL"));
            System.out.println("Pet na máquina: " + (temPet ? (petLimpo ? "Sim (Limpo)" : "Sim (Sujo)") : "Não"));
            System.out.println("Água: " + agua + "L / 30L | Shampoo: " + shampoo + "L / 10L");
            System.out.println("-------------------------------------");
            System.out.println("1. Colocar pet na máquina");
            System.out.println("2. Dar banho no pet");
            System.out.println("3. Retirar pet da máquina");
            System.out.println("4. Limpar máquina");
            System.out.println("5. Abastecer com água (+2L)");
            System.out.println("6. Abastecer com shampoo (+2L)");
            System.out.println("7. Verificar nível de água");
            System.out.println("8. Verificar nível de shampoo");
            System.out.println("9. Verificar se tem pet no banho");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            var opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> {
                    if (temPet) {
                        System.out.println("Atenção: Já existe um pet dentro da máquina!");
                    } else if (maquinaSujou) {
                        System.out.println("Atenção: A máquina está suja porque o último pet foi retirado sem banho! Limpe a máquina antes.");
                    } else {
                        temPet = true;
                        petLimpo = false;
                        System.out.println("Pet colocado na máquina com sucesso!");
                    }
                }
                case 2 -> {
                    if (!temPet) {
                        System.out.println("Não há nenhum pet na máquina para dar banho.");
                    } else if (petLimpo) {
                        System.out.println("Este pet já tomou banho e está limpo!");
                    } else if (agua < 10 || shampoo < 2) {
                        System.out.println("Recursos insuficientes para o banho! Necessário pelo menos 10L de água e 2L de shampoo.");
                    } else {
                        agua -= 10;
                        shampoo -= 2;
                        petLimpo = true;
                        System.out.println("Banho concluído com sucesso! Consumidos 10L de água e 2L de shampoo.");
                    }
                }
                case 3 -> {
                    if (!temPet) {
                        System.out.println("Não há nenhum pet na máquina para ser retirado.");
                    } else {
                        if (!petLimpo) {
                            maquinaSujou = true;
                            System.out.println("Aviso: O pet foi retirado SEM estar limpo. A máquina precisará ser limpa antes do próximo pet!");
                        } else {
                            System.out.println("Pet limpo retirado da máquina com sucesso!");
                        }
                        temPet = false;
                        petLimpo = false;
                    }
                }
                case 4 -> {
                    if (temPet) {
                        System.out.println("Não é possível limpar a máquina com um pet dentro! Retire-o primeiro.");
                    } else if (agua < 3 || shampoo < 1) {
                        System.out.println("Recursos insuficientes para limpar a máquina! Necessário 3L de água e 1L de shampoo.");
                    } else {
                        agua -= 3;
                        shampoo -= 1;
                        maquinaSujou = false;
                        System.out.println("Máquina higienizada com sucesso! Consumidos 3L de água e 1L de shampoo.");
                    }
                }
                case 5 -> {
                    if (agua + 2 > 30) {
                        System.out.println("A máquina já atingiu a capacidade máxima de água (30L).");
                    } else {
                        agua += 2;
                        System.out.printf("Abastecido 2L de água. Nível atual: %dL\n", agua);
                    }
                }
                case 6 -> {
                    if (shampoo + 2 > 10) {
                        System.out.println("A máquina já atingiu a capacidade máxima de shampoo (10L).");
                    } else {
                        shampoo += 2;
                        System.out.printf("Abastecido 2L de shampoo. Nível atual: %dL\n", shampoo);
                    }
                }
                case 7 -> System.out.printf("Nível atual de água: %dL de 30L\n", agua);
                case 8 -> System.out.printf("Nível atual de shampoo: %dL de 10L\n", shampoo);
                case 9 -> {
                    if (temPet) {
                        System.out.println("SIM, há um pet na máquina de banho.");
                    } else {
                        System.out.println("NÃO há nenhum pet na máquina no momento.");
                    }
                }
                case 0 -> {
                    System.out.println("Encerrando o sistema de banho do Petshop...");
                    executando = false;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }
}