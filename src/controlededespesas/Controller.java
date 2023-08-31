package controlededespesas;

import javax.swing.JOptionPane;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import gerenciador.GerenciadorTiposDespesa;
import gerenciador.GerenciadorUsuarios;
import controlededespesas.Despesa;


public class Controller implements Serializable{
	private static final long serialVersionUID = 134676438L;
	
	
	
	private static final String DESPESAS_ARQUIVO = "despesas.dat";
    public static List<Despesa> listaDeDespesas = Despesa.carregarDespesasDeArquivo(DESPESAS_ARQUIVO);
    private static GerenciadorTiposDespesa gerenciadorTiposDespesa = new GerenciadorTiposDespesa();
    private static GerenciadorUsuarios gerenciadorUsuarios = new GerenciadorUsuarios();

    
    public static void exibirTelaLogin() {
        String username = JOptionPane.showInputDialog("Usuário:");
        String senha = JOptionPane.showInputDialog("Senha:");
        
        if (gerenciadorUsuarios.autenticarUsuario(username, senha)) {
            JOptionPane.showMessageDialog(null, "Autenticação bem-sucedida. Bem-vindo(a)!");
            exibirMenuPrincipal();
        } else {
            JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos. Tente novamente.");
            exibirTelaLogin();
        }
    }
    
    
    public static void main(String[] args) {
        gerenciadorTiposDespesa.carregarTiposDespesaDeArquivo();
        GerenciadorUsuarios gerenciadorUsuarios = new GerenciadorUsuarios();
        gerenciadorUsuarios.carregarUsuariosDeArquivo();
        exibirTelaLogin();
        exibirMenuPrincipal();
    }

    
    public static void exibirMenuPrincipal() {
        
    	Despesa.carregarDespesasDeArquivo(DESPESAS_ARQUIVO);
        gerenciadorTiposDespesa.carregarTiposDespesaDeArquivo();
        gerenciadorUsuarios.carregarUsuariosDeArquivo();
    
    	
    	String[] opcoesMenuPrincipal = {
            "Cadastrar Despesa",
            "Anotar Pagamento",
            "Gerenciar Tipos de Despesa",
            "Gerenciar Usuários",
            "Listar Despesas",
            "Sair"
        };

        int opcao = JOptionPane.showOptionDialog(
            null,
            "Escolha uma opção:",
            "Menu Principal",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            opcoesMenuPrincipal,
            opcoesMenuPrincipal[0]
        );

        switch (opcao) {
            case 0:
                cadastrarDespesa();
                break;
            case 1:
            	PagamentoManager.anotarPagamento(listaDeDespesas, gerenciadorTiposDespesa, DESPESAS_ARQUIVO);
            	break;
            case 2:
            	gerenciadorTiposDespesa.exibirMenuGerenciarTiposDespesa();
                break;
            case 3:
                gerenciarUsuarios();
                break;
            case 4:
                ListarDespesasController.listarDespesas();
                break;
            case 5:
                int confirmacaoSaida = JOptionPane.showConfirmDialog(null, "Deseja realmente sair?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (confirmacaoSaida == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "Programa encerrado.");
                    System.exit(0); // Encerra o programa
                }
                break;
            default:
                break;
        }
    }

    
    
    public static List<Despesa> getListaDeDespesas() {
        return listaDeDespesas;
    }
    
    
    public static void cadastrarDespesa() {
        String descricao = JOptionPane.showInputDialog("Descrição da despesa:");
        if (descricao == null) {
            Controller.exibirMenuPrincipal(); // Volta para o menu principal
            return;
        }
        String valorInput = JOptionPane.showInputDialog("Valor da despesa:");
        if (valorInput == null) {
            Controller.exibirMenuPrincipal(); // Volta para o menu principal
            return;
        }
        
        String dataVencimento = JOptionPane.showInputDialog("Data de vencimento da despesa:");
        if (dataVencimento == null) {
            Controller.exibirMenuPrincipal(); // Volta para o menu principal
            return;
        }
        String categoria = JOptionPane.showInputDialog("Categoria da despesa \nOpções já disponíveis> Transporte, Eventual e Supérfluo. \nDigitando algo diferente disso você criará uma categoria nova");
        if (categoria == null) {
            Controller.exibirMenuPrincipal(); // Volta para o menu principal
            return;
        }
        

        double valor = Double.parseDouble(valorInput);
        
        TipoDespesa tipoDespesa = selecionarOuCriarTipoDespesa();

        CategoriaDespesa transporte = new CategoriaTransporte();
        CategoriaDespesa eventual = new CategoriaEventual();
        CategoriaDespesa superfluo = new CategoriaSuperfluo();
         
        CategoriaDespesa categoriaDespesa;
        if (categoria.equalsIgnoreCase("Transporte")) {
            categoriaDespesa = transporte;
        } else if (categoria.equalsIgnoreCase("Eventual")) {
            categoriaDespesa = eventual;
        } else if (categoria.equalsIgnoreCase("Supérfluo")) {
            categoriaDespesa = superfluo;
        } else {
        	categoriaDespesa = new CategoriaDespesaCustomizada(categoria);
        }

        Despesa despesa = new Despesa(descricao, valor, dataVencimento, categoria, tipoDespesa, categoriaDespesa);
        listaDeDespesas.add(despesa);
        Despesa.salvarDespesasEmArquivo(listaDeDespesas, DESPESAS_ARQUIVO);
        
        JOptionPane.showMessageDialog(null, "Despesa cadastrada com sucesso:\n" + despesa.toString());
    }
   

    public static Pagavel selecionarPagavelParaPagamento() {
        // Implemente a lógica para selecionar um item pagável (Despesa ou Usuário) para pagamento
        // Pode ser através de um diálogo de seleção ou outra forma que você preferir
        return null; // Retorne o item selecionado
    }

    public static void gerenciarTiposDespesa() {
        int opcao = gerenciadorTiposDespesa.exibirMenuGerenciarTiposDespesa();
        switch (opcao) {
            case 0:
                gerenciadorTiposDespesa.criarTipoDespesa();
                break;
            case 1:
                gerenciadorTiposDespesa.listarTiposDespesa();
                break;
            case 2:
                gerenciadorTiposDespesa.editarTipoDespesa();
                break;
            case 3:
                gerenciadorTiposDespesa.excluirTipoDespesa();
                break;
            default:
                break;
        }
    }

    public static void gerenciarUsuarios() {
        int opcao = gerenciadorUsuarios.exibirMenuGerenciarUsuarios();
        switch (opcao) {
            case 0:
                gerenciadorUsuarios.cadastrarUsuario();
                break;
            case 1:
                gerenciadorUsuarios.listarUsuarios();
                break;
            case 2:
                gerenciadorUsuarios.editarUsuario();
                break;
            default:
                break;
        }
    }

    public static TipoDespesa selecionarOuCriarTipoDespesa() {
        String[] opcoes = {
            "Selecionar Tipo de Despesa Existente",
            "Criar Novo Tipo de Despesa"
        };

        int escolha = JOptionPane.showOptionDialog(
            null,
            "Escolha uma opção para o tipo de despesa:",
            "Tipo de Despesa",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            opcoes,
            opcoes[0]
        );

        if (escolha == 0) {
            return gerenciadorTiposDespesa.selecionarTipoDespesaExistente();
        } else {
            return gerenciadorTiposDespesa.criarTipoDespesa();
        }
    }

   



}
