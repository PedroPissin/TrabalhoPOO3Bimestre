package controlededespesas;

import javax.swing.JOptionPane;
import java.util.List;
import gerenciador.GerenciadorTiposDespesa;
import controlededespesas.Controller;


public class PagamentoManager {
	public static void anotarPagamento(List<Despesa> listaDeDespesas, GerenciadorTiposDespesa gerenciadorTiposDespesa, String arquivoDespesas) {
	    Pagavel pagavel = selecionarPagavelParaPagamento(listaDeDespesas);

	    if (pagavel != null) {
	        if (!pagavel.estaPaga()) {
	            pagavel.marcarComoPaga();

	            if (pagavel instanceof Despesa) {
	                Despesa despesa = (Despesa) pagavel;

	                if (despesa.getPagamentos() == null) {
	                    despesa.inicializarPagamentos(); // Inicializa a lista de pagamentos, se necessário
	                }

	                double valorPagamento = 0.0;
	                while (true) {
	                    String valorInput = JOptionPane.showInputDialog("Digite o valor do pagamento");
	                    if (valorInput == null) {
	                        Controller.exibirMenuPrincipal(); // Voltar para o menu principal
	                        return;
	                    }
	                    try {
	                        valorPagamento = Double.parseDouble(valorInput);
	                        if (valorPagamento > despesa.getValorRestante()) {
	                            JOptionPane.showMessageDialog(null, "O valor do pagamento excede o valor restante da despesa.");
	                        } else {
	                            break; // Saia do loop se o valor for válido
	                        }
	                    } catch (NumberFormatException e) {
	                        JOptionPane.showMessageDialog(null, "Valor inválido. Digite um valor numérico válido.");
	                    }
	                }

	                String dataPagamento;
	                while (true) {
	                    dataPagamento = JOptionPane.showInputDialog("Digite a data do pagamento:");
	                    if (dataPagamento == null) {
	                        Controller.exibirMenuPrincipal(); // Voltar para o menu principal
	                        return;
	                    }
	                    // Você pode adicionar validações para a data aqui, se necessário
	                    break; // Saia do loop se a data for inserida
	                }

	                Pagamento pagamento = new Pagamento(dataPagamento, valorPagamento);
	                despesa.adicionarPagamento(pagamento);

	                // Atualizar o valor restante
	                double valorRestante = despesa.getValorRestante();
	                valorRestante -= valorPagamento;
	                despesa.setValorRestante(valorRestante);

	                if (valorRestante <= 0) {
	                    JOptionPane.showMessageDialog(null, "Despesa já foi paga.");
	                } else {
	                    JOptionPane.showMessageDialog(null, "Pagamento registrado com sucesso.");
	                }
	            }
	        } else {
	            JOptionPane.showMessageDialog(null, "Este item já está pago.");
	        }
	    }

	    Controller.exibirMenuPrincipal(); // Voltar para o menu principal após concluir
	}
	    
	


	public static Pagavel selecionarPagavelParaPagamento(List<Despesa> listaDeDespesas) {
	    if (listaDeDespesas.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "Nenhuma despesa disponível para pagamento.");
	        return null;
	    }

	    String[] opcoesPagaveis = new String[listaDeDespesas.size()];
	    for (int i = 0; i < listaDeDespesas.size(); i++) {
	        opcoesPagaveis[i] = listaDeDespesas.get(i).getDescricao();
	    }

	    int escolha = JOptionPane.showOptionDialog(
	        null,
	        "Escolha uma despesa para anotar o pagamento:",
	        "Anotar Pagamento",
	        JOptionPane.DEFAULT_OPTION,
	        JOptionPane.PLAIN_MESSAGE,
	        null,
	        opcoesPagaveis,
	        opcoesPagaveis[0]
	    );

	    if (escolha >= 0 && escolha < listaDeDespesas.size()) {
	        return listaDeDespesas.get(escolha); // Retorne a despesa selecionada
	    } else {
	        return null;
	    }
	}

    public static TipoDespesa selecionarTipoDespesa(GerenciadorTiposDespesa gerenciadorTiposDespesa) {
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
    
