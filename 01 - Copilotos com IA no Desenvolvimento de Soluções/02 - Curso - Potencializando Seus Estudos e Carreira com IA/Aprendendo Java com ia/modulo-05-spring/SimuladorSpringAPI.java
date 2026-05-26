import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

/**
 * MÓDULO 5: Spring Boot & APIs RESTful (Arquitetura de Camadas)
 * DESAFIO MODELO: Simulador de Alta Fidelidade de Spring Boot REST API
 * 
 * Este arquivo simula como uma API real do Spring Boot funciona nos bastidores:
 * 1. Injeção de Dependências (IoC/DI) simulada.
 * 2. Arquitetura em camadas (Controller -> Service -> Repository).
 * 3. Uso de DTOs (Data Transfer Objects) para Request e Response.
 * 4. Tratamento Global de Exceções (simulando @ControllerAdvice).
 * 5. Mapeamento de Entidades e Persistência em Memória (simulando Spring Data JPA).
 * 
 * Como Executar no Terminal do Windows:
 * 1. Abra o CMD ou PowerShell e navegue até esta pasta.
 * 2. Execute o comando: java SimuladorSpringAPI.java
 */
public class SimuladorSpringAPI {

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("🍃 BOOTING SPRING BOOT APPLICATION...");
        System.out.println("=================================================");
        System.out.println("[Spring] Inicializando ApplicationContext...");
        System.out.println("[Spring] Escaneando anotações @Component, @Service, @Repository...");
        
        // Simulação da Injeção de Dependências (DI):
        // O Spring instancia automaticamente e injeta as dependências de baixo para cima!
        UsuarioRepository repository = new UsuarioRepository();
        UsuarioService service = new UsuarioService(repository); // Injetando Repository no Service
        UsuarioController controller = new UsuarioController(service); // Injetando Service no Controller

        System.out.println("[Spring] Injeção de Dependências concluída com sucesso!");
        System.out.println("[Spring] Tomcat started on port(s): 8080 (http)\n");

        // Alimentar o banco de dados simulado inicial
        repository.save(new Usuario(null, "João Pedro", "joao@email.com", "senha123"));
        repository.save(new Usuario(null, "Maria Clara", "maria@email.com", "senha456"));

        Scanner scanner = new Scanner(System.in);
        boolean rodando = true;

        System.out.println("=== 🌐 SIMULADOR DE API REST SPRING BOOT ===");
        System.out.println("Você interagirá com a API como se estivesse enviando requisições HTTP (Postman/Insomnia).\n");

        while (rodando) {
            System.out.println("\n--- Escolha um Endpoint / Operação HTTP ---");
            System.out.println("1. [HTTP GET]    /api/usuarios          (Listar Todos)");
            System.out.println("2. [HTTP GET]    /api/usuarios/{id}     (Buscar por ID)");
            System.out.println("3. [HTTP POST]   /api/usuarios          (Criar Novo Usuário - DTO)");
            System.out.println("4. [HTTP DELETE] /api/usuarios/{id}     (Deletar por ID)");
            System.out.println("5. Fechar Servidor (Sair)");
            System.out.print("Escolha uma opção: ");
            
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1" -> {
                    System.out.println("\n📬 [GET] Request enviado para /api/usuarios...");
                    // Chama o método do controller e exibe a resposta formatada em JSON fictício
                    String jsonResposta = controller.listarTodos();
                    System.out.println("📥 [HTTP RESPONSE 200 OK]:");
                    System.out.println(jsonResposta);
                }
                case "2" -> {
                    System.out.print("Informe o ID a ser buscado: ");
                    String idStr = scanner.nextLine();
                    System.out.printf("%n📬 [GET] Request enviado para /api/usuarios/%s...%n", idStr);
                    
                    // Simulação do Controller recebendo a chamada
                    String jsonResposta = controller.buscarPorId(idStr);
                    System.out.println(jsonResposta);
                }
                case "3" -> {
                    System.out.println("\n📝 Preparando Payload [HTTP POST] (JSON)...");
                    System.out.print("Nome do novo usuário: ");
                    String nome = scanner.nextLine();
                    System.out.print("Email do novo usuário: ");
                    String email = scanner.nextLine();
                    System.out.print("Senha do novo usuário: ");
                    String senha = scanner.nextLine();

                    // Instanciando o DTO de Entrada (Request DTO)
                    UsuarioRequestDTO requestDTO = new UsuarioRequestDTO(nome, email, senha);
                    System.out.println("\n📬 [POST] Enviando request para /api/usuarios com o body JSON...");
                    
                    String jsonResposta = controller.criar(requestDTO);
                    System.out.println(jsonResposta);
                }
                case "4" -> {
                    System.out.print("Informe o ID a ser deletado: ");
                    String idStr = scanner.nextLine();
                    System.out.printf("%n📬 [DELETE] Request enviado para /api/usuarios/%s...%n", idStr);
                    
                    String jsonResposta = controller.deletar(idStr);
                    System.out.println(jsonResposta);
                }
                case "5" -> {
                    System.out.println("\n[Spring] Shutting down Tomcat...");
                    System.out.println("[Spring] Closing ApplicationContext... Bye!");
                    rodando = false;
                }
                default -> System.out.println("❌ Opção inválida! Escolha um endpoint HTTP de 1 a 5.");
            }
        }
        scanner.close();
    }
}

// =========================================================================
// CAMADA 1: CONTROLLER (Exposição da API REST e Tratamento Global de Erros)
// =========================================================================
/**
 * @RestController
 * @RequestMapping("/api/usuarios")
 * 
 * Camada responsável por receber requisições HTTP, validar dados de entrada, 
 * delegar para a camada de Serviço e retornar a resposta adequada (JSON + HTTP Status).
 */
class UsuarioController {
    
    // Injeção de dependência via Construtor (Boa prática recomendada pelo Spring)
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    public String listarTodos() {
        List<UsuarioResponseDTO> usuarios = service.listarTodos();
        return formatarListaParaJson(usuarios);
    }

    public String buscarPorId(String idStr) {
        try {
            Long id = parseId(idStr);
            UsuarioResponseDTO response = service.buscarPorId(id);
            return "📥 [HTTP RESPONSE 200 OK]:\n" + formatarObjetoParaJson(response);
        } catch (Exception e) {
            // Simula o @ControllerAdvice capturando a exceção e formatando o retorno de erro
            return tratarExcecaoGlobal(e, 404, "NOT_FOUND");
        }
    }

    public String criar(UsuarioRequestDTO request) {
        try {
            // Validações básicas (simulando @Valid / Bean Validation)
            if (request.nome() == null || request.nome().isBlank()) {
                throw new IllegalArgumentException("O nome do usuário é obrigatório.");
            }
            if (request.email() == null || !request.email().contains("@")) {
                throw new IllegalArgumentException("O e-mail informado é inválido.");
            }

            UsuarioResponseDTO response = service.criar(request);
            return "📥 [HTTP RESPONSE 201 CREATED]:\n" + formatarObjetoParaJson(response);
        } catch (Exception e) {
            return tratarExcecaoGlobal(e, 400, "BAD_REQUEST");
        }
    }

    public String deletar(String idStr) {
        try {
            Long id = parseId(idStr);
            service.deletar(id);
            return "📥 [HTTP RESPONSE 204 NO CONTENT]:\n{ \"mensagem\": \"Usuário deletado com sucesso!\" }";
        } catch (Exception e) {
            return tratarExcecaoGlobal(e, 404, "NOT_FOUND");
        }
    }

    // Auxiliares de conversão e simulação de JSON
    private Long parseId(String idStr) {
        try {
            return Long.parseLong(idStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("O ID informado deve ser um número inteiro válido.");
        }
    }

    private String tratarExcecaoGlobal(Exception e, int status, String erro) {
        System.out.printf("⚠️  [Spring - ExceptionHandler] Capturou erro: %s%n", e.getMessage());
        return String.format("📥 [HTTP RESPONSE %d %s]:%n{%n  \"status\": %d,%n  \"error\": \"%s\",%n  \"message\": \"%s\"%n}", 
                status, erro, status, erro, e.getMessage());
    }

    private String formatarObjetoParaJson(UsuarioResponseDTO dto) {
        return String.format("{%n  \"id\": %d,%n  \"nome\": \"%s\",%n  \"email\": \"%s\"%n}", dto.id(), dto.nome(), dto.email());
    }

    private String formatarListaParaJson(List<UsuarioResponseDTO> lista) {
        if (lista.isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder("[\n");
        for (int i = 0; i < lista.size(); i++) {
            sb.append("  ").append(formatarObjetoParaJson(lista.get(i)).replace("\n", "\n  "));
            if (i < lista.size() - 1) sb.append(",\n");
        }
        sb.append("\n]");
        return sb.toString();
    }
}

// =========================================================================
// CAMADA 2: SERVICE (Regras de Negócio e Casos de Uso)
// =========================================================================
/**
 * @Service
 * 
 * Camada onde reside a lógica de negócios da aplicação. Não sabe nada sobre HTTP ou Banco de Dados diretamente.
 * Recebe DTOs da Controller, processa as regras de negócio e invoca a persistência.
 */
class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public List<UsuarioResponseDTO> listarTodos() {
        System.out.println("[Service] Executando regra de listagem de todos os usuários...");
        List<Usuario> usuarios = repository.findAll();
        
        // Conversão de Entidade para DTO de Resposta (Boa prática: nunca retornar a Entidade pura)
        return usuarios.stream()
                .map(u -> new UsuarioResponseDTO(u.getId(), u.getNome(), u.getEmail()))
                .toList();
    }

    public UsuarioResponseDTO buscarPorId(Long id) {
        System.out.printf("[Service] Executando busca de usuário para o ID: %d...%n", id);
        
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID fornecido: " + id));

        return new UsuarioResponseDTO(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }

    public UsuarioResponseDTO criar(UsuarioRequestDTO request) {
        System.out.printf("[Service] Criando novo usuário. Validando e-mail único: %s...%n", request.email());

        // Regra de negócio: E-mail não pode ser duplicado
        boolean emailExiste = repository.findAll().stream()
                .anyMatch(u -> u.getEmail().equalsIgnoreCase(request.email()));
        if (emailExiste) {
            throw new IllegalStateException("O e-mail informado já está cadastrado no sistema.");
        }

        // Converte DTO para Entidade
        Usuario novoUsuario = new Usuario(null, request.nome(), request.email(), request.senha());
        
        // Salva no banco de dados através da camada Repository
        Usuario usuarioSalvo = repository.save(novoUsuario);

        // Retorna DTO de Resposta
        return new UsuarioResponseDTO(usuarioSalvo.getId(), usuarioSalvo.getNome(), usuarioSalvo.getEmail());
    }

    public void deletar(Long id) {
        System.out.printf("[Service] Deletando usuário ID: %d...%n", id);
        
        // Verifica se existe antes de deletar
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Impossível deletar. Usuário não encontrado com o ID: " + id));
        
        repository.delete(usuario);
    }
}

// =========================================================================
// CAMADA 3: REPOSITORY (Mapeamento de Entidades e Acesso ao Banco de Dados)
// =========================================================================
/**
 * @Repository
 * 
 * Camada de Acesso a Dados (DAO). Em uma aplicação real, você apenas declararia uma
 * interface herdando 'JpaRepository<Usuario, Long>' e o Spring geraria toda a implementação.
 */
class UsuarioRepository {

    private final Map<Long, Usuario> bancoDadosSimulado = new HashMap<>();
    private Long idSequencial = 1L;

    public List<Usuario> findAll() {
        System.out.println("[Repository/JPA] Executando Query: SELECT * FROM tb_usuario");
        return new ArrayList<>(bancoDadosSimulado.values());
    }

    public Optional<Usuario> findById(Long id) {
        System.out.printf("[Repository/JPA] Executando Query: SELECT * FROM tb_usuario WHERE id = %d%n", id);
        return Optional.ofNullable(bancoDadosSimulado.get(id));
    }

    public Usuario save(Usuario usuario) {
        if (usuario.getId() == null) {
            // Inserção
            usuario.setId(idSequencial++);
            System.out.printf("[Repository/JPA] Executando Query: INSERT INTO tb_usuario (id, nome, email, senha) VALUES (%d, '%s', '%s', '%s')%n",
                    usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha());
        } else {
            // Atualização
            System.out.printf("[Repository/JPA] Executando Query: UPDATE tb_usuario SET nome='%s', email='%s' WHERE id=%d%n",
                    usuario.getNome(), usuario.getEmail(), usuario.getId());
        }
        bancoDadosSimulado.put(usuario.getId(), usuario);
        return usuario;
    }

    public void delete(Usuario usuario) {
        System.out.printf("[Repository/JPA] Executando Query: DELETE FROM tb_usuario WHERE id = %d%n", usuario.getId());
        bancoDadosSimulado.remove(usuario.getId());
    }
}

// =========================================================================
// DTOs & ENTIDADES
// =========================================================================
/**
 * Entidade de Banco de Dados (representa uma tabela do banco).
 * Possui campos internos e confidenciais, como senha. Nunca deve ser enviada diretamente
 * para as requisições web.
 */
class Usuario {
    private Long id;
    private String nome;
    private String email;
    private String senha; // Informação sensível!

    public Usuario(Long id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}

/**
 * DTO de Entrada (Data Transfer Object) para criação de Usuário.
 * Representa os dados estritos que o cliente web deve enviar.
 */
record UsuarioRequestDTO(String nome, String email, String senha) {}

/**
 * DTO de Saída para representação externa do Usuário na API.
 * Repare que ela NÃO expõe o campo 'senha' por questões cruciais de segurança!
 */
record UsuarioResponseDTO(Long id, String nome, String email) {}
