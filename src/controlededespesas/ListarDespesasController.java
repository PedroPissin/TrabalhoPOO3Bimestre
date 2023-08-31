package controlededespesas;

import java.util.List;
import javax.swing.JOptionPane;
import controlededespesas.Despesa;

public class ListarDespesasController {
    private static final String DESPESAS_ARQUIVO = "despesas.dat";

    public static void listarDespesas() {
        List<Despesa> listaDeDespesas = Despesa.carregarDespesasDeArquivo(DESPESAS_ARQUIVO);
        if (listaDeDespesas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma despesa cadastrada.");
            return;
        }

        String[] opcoesDespesas = new String[listaDeDespesas.size() + 2];
        for (int i = 0; i < listaDeDespesas.size(); i++) {
            opcoesDespesas[i] = listaDeDespesas.get(i).getDescricao();
        }
        opcoesDespesas[opcoesDespesas.length - 2] = "(((Listar Por Período Pagas e em Aberto)))";
        opcoesDespesas[opcoesDespesas.length - 1] = "(((Voltar ao Menu Principal)))";

        int selecaoDespesa = JOptionPane.showOptionDialog(
            null,
            "Selecione uma despesa para editar ou excluir, ou escolha 'Listar Por Período Pagas e em Aberto' ou 'Voltar' para retornar ao Menu Principal:",
            "Listar Despesas",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            opcoesDespesas,
            opcoesDespesas[0]
        );

        if (selecaoDespesa >= 0 && selecaoDespesa < listaDeDespesas.size()) {
            Despesa despesaSelecionada = listaDeDespesas.get(selecaoDespesa);
            int opcaoAcao = JOptionPane.showOptionDialog(
                null,
                "Escolha uma ação para a despesa \"" + despesaSelecionada.getDescricao() + "\":",
                "Ações",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                new String[] {"Editar Despesa", "Excluir Despesa", "Voltar"},
                "Editar Despesa"
            );

            switch (opcaoAcao) {
                case 0:
                    editarDespesa(despesaSelecionada);
                    break;
                case 1:
                    excluirDespesa(despesaSelecionada);
                    break;
                case 2:
                    listarDespesas();
                    break;
                default:
                    break;
            }
        } else if (selecaoDespesa == listaDeDespesas.size()) {
            // Opção "Listar Por Período Pagas e em Aberto" foi selecionada
            Despesa.listarDespesasPorPeriodo();
            listarDespesas();
        } else if (selecaoDespesa == listaDeDespesas.size() + 1) {
            // Opção "Voltar" foi selecionada
            Controller.exibirMenuPrincipal();
        }
    }

    public static List<Despesa> getListaDeDespesas() {
        return Controller.listaDeDespesas;
    }

    private static void editarDespesa(Despesa despesa) {
        System.out.println("Iniciando edição da despesa: " + despesa.getDescricao());

        String novaDescricao = JOptionPane.showInputDialog("Nova descrição da despesa:");
        if (novaDescricao != null) {
            despesa.setDescricao(novaDescricao);
        }

        String novoValorInput = JOptionPane.showInputDialog("Novo valor da despesa:");
        if (novoValorInput != null) {
            double novoValor = Double.parseDouble(novoValorInput);
            despesa.setValor(novoValor);
        }

        String novaDataVencimento = JOptionPane.showInputDialog("Nova data de vencimento da despesa:");
        if (novaDataVencimento != null) {
            despesa.setDataVencimento(novaDataVencimento);
        }

        // Atualizar a categoria da despesa
        TipoDespesa novoTipoDespesa = Controller.selecionarOuCriarTipoDespesa();
        despesa.setTipoDespesa(novoTipoDespesa);

        String novaCategoria = JOptionPane.showInputDialog("Nova categoria da despesa:");
        if (novaCategoria != null) {
            CategoriaDespesa novaCategoriaDespesa = CategoriaDespesa.criarCategoria(novaCategoria);
            despesa.setCategoriaDespesa(novaCategoriaDespesa);
        }

        // Atualizar a despesa na lista
        int index = Controller.listaDeDespesas.indexOf(despesa);
        if (index != -1) {
            Controller.listaDeDespesas.set(index, despesa);
            System.out.println("Despesa editada com sucesso: " + despesa.getDescricao());
        } else {
            System.out.println("Despesa não encontrada na lista.");
        }

        // Salvar as despesas atualizadas no arquivo
        Despesa.salvarDespesasEmArquivo(Controller.listaDeDespesas, DESPESAS_ARQUIVO);

        JOptionPane.showMessageDialog(null, "Despesa editada com sucesso:\n" + despesa.toString());
    }

    private static void excluirDespesa(Despesa despesa) {
        int confirmacaoExclusao = JOptionPane.showConfirmDialog(
            null,
            "Tem certeza que deseja excluir a despesa:\n" + despesa.toString() + "?",
            "Confirmar Exclusão",
            JOptionPane.YES_NO_OPTION
        );

        if (confirmacaoExclusao == JOptionPane.YES_OPTION) {
            Controller.listaDeDespesas.remove(despesa);
            Despesa.salvarDespesasEmArquivo(Controller.listaDeDespesas, DESPESAS_ARQUIVO);
            JOptionPane.showMessageDialog(null, "Despesa excluída com sucesso.");
            listarDespesas();
        }
    }
}
