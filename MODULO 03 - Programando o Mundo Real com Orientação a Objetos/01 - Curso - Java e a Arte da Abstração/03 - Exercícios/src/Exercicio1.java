import java.util.Scanner;

public class Exercicio1 {

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);

        System.out.println("=== ABERTURA DE CONTA BANCÁRIA ===");
        System.out.println("Informe o valor do depósito inicial:");
        var depositoInicial = scanner.nextDouble();

        // Regras para definição do Cheque Especial
        double limiteChequeEspecial;
        if (depositoInicial <= 500.0) {
            limiteChequeEspecial = 50.0;
        } else {
            limiteChequeEspecial = depositoInicial * 0.50; // 50% do valor depositado
        }

        var saldo = depositoInicial;
        var chequeEspecialUsado = 0.0; // Controla quanto do limite está em uso
        var taxaPendente = 0.0;        // Taxa de 20% cobrada quando usa o cheque especial

        var executando = true;

        while (executando) {
            System.out.println("\n===== MENU CONTA BANCÁRIA =====");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Consultar cheque especial");
            System.out.println("3. Depositar dinheiro");
            System.out.println("4. Sacar dinheiro");
            System.out.println("5. Pagar um boleto");
            System.out.println("6. Verificar se está usando cheque especial");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            var opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> {
                    System.out.printf("Saldo disponível em conta: R$ %.2f\n", saldo);
                    if (taxaPendente > 0) {
                        System.out.printf("Atenção: Existe uma taxa pendente de R$ %.2f referente ao uso do cheque especial.\n", taxaPendente);
                    }
                }
                case 2 -> {
                    var disponivel = limiteChequeEspecial - chequeEspecialUsado;
                    System.out.printf("Limite total do Cheque Especial: R$ %.2f\n", limiteChequeEspecial);
                    System.out.printf("Cheque Especial em uso: R$ %.2f\n", chequeEspecialUsado);
                    System.out.printf("Cheque Especial disponível: R$ %.2f\n", disponivel);
                }
                case 3 -> {
                    System.out.print("Informe o valor a depositar: R$ ");
                    var valorDeposito = scanner.nextDouble();

                    if (valorDeposito <= 0) {
                        System.out.println("Valor inválido para depósito.");
                        break;
                    }

                    // Se houver dívida de cheque especial ou taxa, o depósito abate primeiro esses débitos
                    if (taxaPendente > 0 || chequeEspecialUsado > 0) {
                        var totalDevido = chequeEspecialUsado + taxaPendente;

                        if (valorDeposito >= totalDevido) {
                            valorDeposito -= totalDevido;
                            chequeEspecialUsado = 0.0;
                            taxaPendente = 0.0;
                            saldo += valorDeposito;
                            System.out.println("Depósito realizado! Débitos de cheque especial e taxas foram quitados integralmente.");
                        } else {
                            // Abate primeiro a taxa, depois o uso do cheque especial
                            if (valorDeposito <= taxaPendente) {
                                taxaPendente -= valorDeposito;
                            } else {
                                var restante = valorDeposito - taxaPendente;
                                taxaPendente = 0.0;
                                chequeEspecialUsado -= restante;
                            }
                            System.out.println("Depósito realizado! O valor foi utilizado para abater débitos pendentes.");
                        }
                    } else {
                        saldo += valorDeposito;
                        System.out.println("Depósito realizado com sucesso!");
                    }
                }
                case 4 -> {
                    System.out.print("Informe o valor do saque: R$ ");
                    var valorSaque = scanner.nextDouble();

                    var totalDisponivel = saldo + (limiteChequeEspecial - chequeEspecialUsado);

                    if (valorSaque <= 0 || valorSaque > totalDisponivel) {
                        System.out.println("Operação cancelada: Saldo + Cheque Especial insuficiente.");
                    } else if (valorSaque <= saldo) {
                        saldo -= valorSaque;
                        System.out.println("Saque realizado com sucesso!");
                    } else {
                        // Precisa usar o cheque especial
                        var restante = valorSaque - saldo;
                        saldo = 0.0;

                        // Se é a primeira vez usando o cheque especial nesta rodada, gera a taxa de 20%
                        var valorAnterior = chequeEspecialUsado;
                        chequeEspecialUsado += restante;

                        if (valorAnterior == 0) {
                            taxaPendente = chequeEspecialUsado * 0.20; // Taxa de 20%
                            System.out.printf("Atenção: Você entrou no cheque especial! Uma taxa de R$ %.2f (20%%) foi gerada e será cobrada assim que houver depósito.\n", taxaPendente);
                        } else {
                            // Se já usava, a taxa recai sobre o novo limite utilizado
                            taxaPendente += restante * 0.20;
                        }
                        System.out.println("Saque realizado utilizando o Cheque Especial.");
                    }
                }
                case 5 -> {
                    System.out.print("Informe o valor do boleto: R$ ");
                    var valorBoleto = scanner.nextDouble();

                    var totalDisponivel = saldo + (limiteChequeEspecial - chequeEspecialUsado);

                    if (valorBoleto <= 0 || valorBoleto > totalDisponivel) {
                        System.out.println("Operação cancelada: Saldo + Cheque Especial insuficiente para pagar o boleto.");
                    } else if (valorBoleto <= saldo) {
                        saldo -= valorBoleto;
                        System.out.println("Boleto pago com sucesso!");
                    } else {
                        var restante = valorBoleto - saldo;
                        saldo = 0.0;

                        var valorAnterior = chequeEspecialUsado;
                        chequeEspecialUsado += restante;

                        if (valorAnterior == 0) {
                            taxaPendente = chequeEspecialUsado * 0.20;
                            System.out.printf("Boleto pago utilizando o Cheque Especial! Taxa gerada: R$ %.2f (20%%).\n", taxaPendente);
                        } else {
                            taxaPendente += restante * 0.20;
                            System.out.println("Boleto pago utilizando o Cheque Especial!");
                        }
                    }
                }
                case 6 -> {
                    if (chequeEspecialUsado > 0) {
                        System.out.printf("A conta ESTÁ utilizando o cheque especial. Valor em uso: R$ %.2f\n", chequeEspecialUsado);
                    } else {
                        System.out.println("A conta NÃO está utilizando o cheque especial.");
                    }
                }
                case 0 -> {
                    System.out.println("Encerrando o sistema... Até logo!");
                    executando = false;
                }
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }
}