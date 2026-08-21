import java.util.Scanner;

public class Exercicio2 {

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);

        var ligado = false;
        var velocidade = 0;
        var marcha = 0; // 0 = Ponto Morto

        var executando = true;

        while (executando) {
            System.out.println("\n===== PAINEL DO CARRO =====");
            System.out.println("Status: " + (ligado ? "LIGADO" : "DESLIGADO"));
            System.out.println("Velocidade: " + velocidade + " km/h | Marcha: " + (marcha == 0 ? "Ponto Morto (0)" : marcha + "ª"));
            System.out.println("---------------------------");
            System.out.println("1. Ligar o carro");
            System.out.println("2. Desligar o carro");
            System.out.println("3. Acelerar");
            System.out.println("4. Diminuir velocidade");
            System.out.println("5. Trocar a marcha");
            System.out.println("6. Virar para esquerda/direita");
            System.out.println("7. Verificar velocidade");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            var opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> {
                    if (ligado) {
                        System.out.println("O carro já está ligado.");
                    } else {
                        ligado = true;
                        System.out.println("Carro ligado com sucesso!");
                    }
                }
                case 2 -> {
                    if (!ligado) {
                        System.out.println("O carro já está desligado.");
                    } else if (marcha != 0 || velocidade != 0) {
                        System.out.println("Atenção: O carro só pode ser desligado em ponto morto (marcha 0) e com velocidade de 0 km/h!");
                    } else {
                        ligado = false;
                        System.out.println("Carro desligado com sucesso!");
                    }
                }
                case 3 -> {
                    if (!ligado) {
                        System.out.println("O carro está desligado! Ligue o carro primeiro.");
                        break;
                    }

                    if (marcha == 0) {
                        System.out.println("O carro está em ponto morto (marcha 0) e não pode acelerar. Engate a 1ª marcha!");
                        break;
                    }

                    var limiteMaximo = marcha * 20; // 1ª -> 20km, 2ª -> 40km ... 6ª -> 120km

                    if (velocidade + 1 > limiteMaximo) {
                        System.out.printf("Velocidade máxima da %dª marcha atingida (%d km/h). Suba a marcha para continuar acelerando.\n", marcha, limiteMaximo);
                    } else if (velocidade + 1 > 120) {
                        System.out.println("Velocidade máxima do veículo (120 km/h) atingida!");
                    } else {
                        velocidade++;
                        System.out.printf("Acelerou! Velocidade atual: %d km/h\n", velocidade);
                    }
                }
                case 4 -> {
                    if (!ligado) {
                        System.out.println("O carro está desligado!");
                        break;
                    }

                    if (velocidade == 0) {
                        System.out.println("O carro já está parado (0 km/h).");
                        break;
                    }

                    var limiteMinimo = (marcha == 1) ? 0 : (marcha - 1) * 20 + 1; // ex: 2ª marcha mínimo 21km

                    if (velocidade - 1 < limiteMinimo) {
                        System.out.printf("Não é possível reduzir mais a velocidade na %dª marcha (mínimo %d km/h). Reduza a marcha primeiro.\n", marcha, limiteMinimo);
                    } else {
                        velocidade--;
                        System.out.printf("Desacelerou! Velocidade atual: %d km/h\n", velocidade);
                    }
                }
                case 5 -> {
                    if (!ligado) {
                        System.out.println("O carro está desligado!");
                        break;
                    }

                    System.out.println("Informe a marcha desejada (0 para Ponto Morto, 1 a 6):");
                    var novaMarcha = scanner.nextInt();

                    if (novaMarcha < 0 || novaMarcha > 6) {
                        System.out.println("Marcha inválida! O carro possui apenas marchas de 0 a 6.");
                    } else if (Math.abs(novaMarcha - marcha) > 1) {
                        System.out.println("Não é permitido pular marchas! Alterne apenas para a marcha imediatamente acima ou abaixo.");
                    } else {
                        // Validação do limite de velocidade para engatar a nova marcha
                        var limiteMinimo = (novaMarcha == 0 || novaMarcha == 1) ? 0 : (novaMarcha - 1) * 20 + 1;
                        var limiteMaximo = novaMarcha * 20;

                        if (novaMarcha != 0 && (velocidade < limiteMinimo || velocidade > limiteMaximo)) {
                            System.out.printf("Não é possível engatar a %dª marcha na velocidade atual (%d km/h). Ela exige entre %d km/h e %d km/h.\n", novaMarcha, velocidade, limiteMinimo, limiteMaximo);
                        } else if (novaMarcha == 0 && velocidade != 0) {
                            System.out.println("Não é possível colocar em ponto morto com o carro em movimento! Pare o carro primeiro.");
                        } else {
                            marcha = novaMarcha;
                            System.out.println("Marcha alterada com sucesso para: " + (marcha == 0 ? "Ponto Morto" : marcha + "ª marcha"));
                        }
                    }
                }
                case 6 -> {
                    if (!ligado) {
                        System.out.println("O carro está desligado!");
                        break;
                    }

                    if (velocidade >= 1 && velocidade <= 40) {
                        System.out.print("Deseja virar para qual lado? (1 - Esquerda | 2 - Direita): ");
                        var lado = scanner.nextInt();
                        var direcao = (lado == 1) ? "esquerda" : "direita";
                        System.out.printf("Carro virou para a %s com segurança a %d km/h.\n", direcao, velocidade);
                    } else {
                        System.out.printf("Manobra negada! O carro só pode virar se estiver entre 1 km/h e 40 km/h (velocidade atual: %d km/h).\n", velocidade);
                    }
                }
                case 7 -> {
                    if (!ligado) {
                        System.out.println("O carro está desligado!");
                    } else {
                        System.out.printf("Velocidade atual do veículo: %d km/h\n", velocidade);
                    }
                }
                case 0 -> {
                    System.out.println("Saindo do simulador de carro...");
                    executando = false;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }
}