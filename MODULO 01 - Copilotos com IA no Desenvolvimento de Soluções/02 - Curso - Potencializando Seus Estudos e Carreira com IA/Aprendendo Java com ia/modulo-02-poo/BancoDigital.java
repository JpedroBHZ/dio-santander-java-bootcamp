/**
 * MÓDULO 2: Programação Orientada a Objetos (POO)
 * DESAFIO MODELO: Banco Digital com POO
 * 
 * Este arquivo unifica em um único exemplo prático os 4 pilares de POO:
 * 1. Abstração: Representação de Contas, Clientes e transações bancárias.
 * 2. Encapsulamento: Atributos privados/protegidos expostos via métodos públicos (Getters/Setters).
 * 3. Herança: Contas Corrente e Poupança herdando a base comum da classe abstrata Conta.
 * 4. Polimorfismo: Impressão de extratos diferentes usando o mesmo contrato IConta.
 * 
 * Como Executar no Terminal do Windows:
 * 1. Abra o CMD ou PowerShell e navegue até esta pasta.
 * 2. Execute o comando: java BancoDigital.java
 */
public class BancoDigital {

    public static void main(String[] args) {
        System.out.println("=== 🏦 INICIALIZANDO O SISTEMA BANCO DIGITAL POO ===\n");

        // Criando clientes (Abstração e Encapsulamento)
        Cliente cliente1 = new Cliente("João Pedro", "123.456.789-00");
        Cliente cliente2 = new Cliente("Maria Souza", "987.654.321-99");

        // Criando contas para os clientes (Polimorfismo e Herança)
        IConta contaCorrenteJoao = new ContaCorrente(cliente1);
        IConta contaPoupancaJoao = new ContaPoupanca(cliente1);
        IConta contaCorrenteMaria = new ContaCorrente(cliente2);

        // Realizando operações bancárias nas contas (Encapsulamento das regras de negócio)
        System.out.println("--- 💸 Realizando transações no banco ---");
        
        contaCorrenteJoao.depositar(1500.00);
        System.out.println("Depósito de R$ 1500,00 realizado na conta corrente do João.");

        // João saca um valor
        contaCorrenteJoao.sacar(200.00);
        System.out.println("Saque de R$ 200,00 realizado na conta corrente do João.");

        // João transfere um valor para a sua poupança
        contaCorrenteJoao.transferir(500.00, contaPoupancaJoao);
        System.out.println("Transferência de R$ 500,00 da conta corrente para a poupança do João.");

        // João transfere um valor para a conta corrente da Maria
        contaCorrenteJoao.transferir(300.00, contaCorrenteMaria);
        System.out.println("Transferência de R$ 300,00 do João para a Maria.");

        System.out.println("\n------------------------------------------------");
        System.out.println("=== 📄 IMPRIMINDO EXTRATOS BANCÁRIOS ===");
        System.out.println("------------------------------------------------\n");

        // Polimorfismo em ação: chamamos o mesmo método 'imprimirExtrato()' mas o comportamento 
        // varia dependendo de qual subclasse (ContaCorrente ou ContaPoupanca) está por trás da interface.
        contaCorrenteJoao.imprimirExtrato();
        contaPoupancaJoao.imprimirExtrato();
        contaCorrenteMaria.imprimirExtrato();
    }
}

/**
 * Interface que define o contrato de comportamento para qualquer tipo de Conta Bancária.
 * Isto garante o pilar da Abstração e padroniza as ações disponíveis.
 */
interface IConta {
    void sacar(double valor);
    void depositar(double valor);
    void transferir(double valor, IConta contaDestino);
    void imprimirExtrato();
}

/**
 * Classe que representa um Cliente do banco.
 * Demonstra o Encapsulamento puro: todos os atributos são privados ('private') e só podem
 * ser lidos/escritos através de métodos específicos (getters/setters).
 */
class Cliente {
    private String nome;
    private String cpf;

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }
}

/**
 * Classe abstrata Conta. Ela serve de molde base para classes filhas específicas.
 * Não pode ser instanciada diretamente (pilar da Abstração).
 */
abstract class Conta implements IConta {
    
    // Atributos constantes estáticos de classe para controle interno de números
    private static final int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1000;

    // Atributos protegidos ('protected') para que as classes filhas (herança) possam acessá-los diretamente
    protected int agencia;
    protected int numero;
    protected double saldo;
    protected Cliente cliente;

    public Conta(Cliente cliente) {
        this.agencia = AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++; // Cada nova conta criada incrementa o sequencial automaticamente
        this.cliente = cliente;
        this.saldo = 0.0;
    }

    @Override
    public void sacar(double valor) {
        if (valor <= 0) {
            System.err.println("❌ Erro: O valor do saque deve ser positivo.");
            return;
        }
        if (this.saldo < valor) {
            System.err.println("❌ Erro: Saldo insuficiente para realizar a transação.");
            return;
        }
        this.saldo -= valor;
    }

    @Override
    public void depositar(double valor) {
        if (valor <= 0) {
            System.err.println("❌ Erro: O valor do depósito deve ser positivo.");
            return;
        }
        this.saldo += valor;
    }

    @Override
    public void transferir(double valor, IConta contaDestino) {
        if (this.saldo < valor) {
            System.err.println("❌ Erro: Saldo insuficiente para transferência.");
            return;
        }
        // Retiramos da conta atual e depositamos na conta destino informada
        this.sacar(valor);
        contaDestino.depositar(valor);
    }

    public int getAgencia() {
        return agencia;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    // Método utilitário compartilhado para formatar a saída comum de extratos
    protected void imprimirInformacoesComuns() {
        System.out.printf("Titular: %s%n", this.cliente.getNome());
        System.out.printf("Agência: %04d%n", this.agencia);
        System.out.printf("Número : %d%n", this.numero);
        System.out.printf("Saldo  : R$ %.2f%n", this.saldo);
    }
}

/**
 * ContaCorrente herda de Conta (pilar da Herança).
 */
class ContaCorrente extends Conta {

    public ContaCorrente(Cliente cliente) {
        super(cliente); // Invoca o construtor da classe pai (Conta)
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("=== Extrato Conta Corrente ===");
        super.imprimirInformacoesComuns();
        System.out.println("==============================\n");
    }
}

/**
 * ContaPoupanca herda de Conta (pilar da Herança).
 */
class ContaPoupanca extends Conta {

    public ContaPoupanca(Cliente cliente) {
        super(cliente); // Invoca o construtor da classe pai (Conta)
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("=== Extrato Conta Poupança ===");
        super.imprimirInformacoesComuns();
        System.out.println("==============================\n");
    }
}
