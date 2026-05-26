import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * MÓDULO 3: Collections API & Streams API (Java Moderno)
 * DESAFIO MODELO: Processamento Funcional de Dados
 * 
 * Este arquivo demonstra conceitos avançados de:
 * 1. Java Records (Sintaxe moderna introduzida no Java 14 para classes imutáveis de dados).
 * 2. Manipulação avançada de Collections (List, Set e Map).
 * 3. Uso completo da Stream API (programação funcional e declarativa).
 * 4. Lambdas e Method References (ex: 'Produto::preco', 'System.out::println').
 * 
 * Como Executar no Terminal do Windows:
 * 1. Abra o CMD ou PowerShell e navegue até esta pasta.
 * 2. Execute o comando: java ExemploStreams.java
 */
public class ExemploStreams {

    public static void main(String[] args) {
        System.out.println("=== ⚡ JAVA MODERNO: COLLECTIONS & STREAM API ===\n");

        // 1. Criando uma lista de produtos usando nossa Record.
        // Records geram automaticamente construtor, getters, equals, hashCode e toString!
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto("Notebook Gamer", 4500.00, "Eletrônicos"));
        produtos.add(new Produto("Smartphone 5G", 2500.00, "Eletrônicos"));
        produtos.add(new Produto("Teclado Mecânico", 350.00, "Periféricos"));
        produtos.add(new Produto("Mouse Wireless", 180.00, "Periféricos"));
        produtos.add(new Produto("Cadeira Ergonômica", 1200.00, "Escritório"));
        produtos.add(new Produto("Monitor 27' 4K", 1999.90, "Eletrônicos"));
        produtos.add(new Produto("Garrafa Térmica", 90.00, "Cozinha"));

        // =========================================================================
        // EXEMPLO 1: Filtrar produtos de 'Eletrônicos' e ordenar por preço (decrescente)
        // =========================================================================
        System.out.println("--- 🔍 1. Filtrando Eletrônicos e ordenando por preço (maior para menor) ---");
        
        List<Produto> eletronicosCaros = produtos.stream()
            // Filtro (filter): Mantém apenas produtos da categoria "Eletrônicos"
            .filter(p -> p.categoria().equalsIgnoreCase("Eletrônicos"))
            // Ordenação (sorted): Ordena por preço decrescente utilizando Method Reference
            .sorted(Comparator.comparingDouble(Produto::preco).reversed())
            // Acumulação (collect): Coleta os dados de volta para uma Lista
            .collect(Collectors.toList());

        // Imprimindo o resultado usando Method Reference
        eletronicosCaros.forEach(System.out::println);
        System.out.println();

        // =========================================================================
        // EXEMPLO 2: Transformar a lista obtendo apenas os nomes dos produtos da categoria 'Periféricos'
        // =========================================================================
        System.out.println("--- 🏷️ 2. Nomes dos produtos na categoria 'Periféricos' (Letras Maiúsculas) ---");
        
        List<String> nomesPerifericos = produtos.stream()
            .filter(p -> p.categoria().equalsIgnoreCase("Periféricos"))
            // Mapeamento (map): Transforma cada Produto em uma String contendo seu nome em maiúsculo
            .map(p -> p.nome().toUpperCase())
            .collect(Collectors.toList());

        nomesPerifericos.forEach(System.out::println);
        System.out.println();

        // =========================================================================
        // EXEMPLO 3: Calcular a média de preço dos produtos da categoria 'Eletrônicos'
        // =========================================================================
        System.out.println("--- 📊 3. Estatísticas e Médias de Preço ---");
        
        double mediaPrecoEletronicos = produtos.stream()
            .filter(p -> p.categoria().equalsIgnoreCase("Eletrônicos"))
            // Converte a Stream para um DoubleStream primitivo de preços
            .mapToDouble(Produto::preco)
            // Calcula a média automaticamente (retorna um OptionalDouble, tratamos com orElse se vazio)
            .average()
            .orElse(0.0);

        System.out.printf("A média de preço dos Eletrônicos é: R$ %.2f%n%n", mediaPrecoEletronicos);

        // =========================================================================
        // EXEMPLO 4: Agrupar produtos por Categoria usando Collectors.groupingBy
        // =========================================================================
        System.out.println("--- 📁 4. Agrupamento de Produtos por Categoria (Map) ---");
        
        Map<String, List<Produto>> produtosPorCategoria = produtos.stream()
            // Agrupa os produtos em um Map onde a Chave é a Categoria e o Valor é a lista de Produtos daquela categoria
            .collect(Collectors.groupingBy(Produto::categoria));

        produtosPorCategoria.forEach((categoria, lista) -> {
            System.out.printf("Categoria: [%s]%n", categoria);
            lista.forEach(p -> System.out.printf("  -> %s (R$ %.2f)%n", p.nome(), p.preco()));
        });
        System.out.println();

        // =========================================================================
        // EXEMPLO 5: Verificar se há algum produto com preço menor que R$ 100,00
        // =========================================================================
        System.out.println("--- 🏷️ 5. Validação de Dados na Stream ---");
        
        boolean possuiProdutoBarato = produtos.stream()
            // anyMatch: retorna true se Pelo Menos Um elemento satisfizer a condição
            .anyMatch(p -> p.preco() < 100.00);

        System.out.println("Existe algum produto custando menos de R$ 100,00? " + (possuiProdutoBarato ? "Sim! ✅" : "Não. ❌"));
    }
}

/**
 * Declaração de Record 'Produto'.
 * Records foram introduzidos no Java 14 como um recurso padrão para criar classes de dados
 * puras e imutáveis com código extremamente reduzido. Substitui o uso extensivo de Boilerplate
 * (construtores, getters, toString, equals e hashCode redundantes).
 */
record Produto(String nome, double preco, String categoria) {
    // Você também pode adicionar construtores personalizados ou validações se desejar
    public Produto {
        if (preco < 0) {
            throw new IllegalArgumentException("O preço de um produto não pode ser negativo.");
        }
    }
}
