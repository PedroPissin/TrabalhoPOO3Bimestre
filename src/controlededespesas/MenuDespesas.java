package controlededespesas;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;


public class MenuDespesas implements Serializable {
	private static final long serialVersionUID = 178725460L;
	
	
    public static void listarDespesas(List<Despesa> despesas) {
        String[] opcoesListagem = {
            "Todas as Despesas",
            "Despesas Pagas",
            "Despesas Pendentes"
        };

        int selecao = JOptionPane.showOptionDialog(
            null,
            "Selecione o tipo de listagem:",
            "Listar Despesas",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            opcoesListagem,
            opcoesListagem[0]
        );

        List<Despesa> despesasExibidas = new ArrayList<>();

        switch (selecao) {
            case 0:
                despesasExibidas = despesas;
                break;
            case 1:
                for (Despesa despesa : despesas) {
                    if (!despesa.getPagamentos().isEmpty()) {
                        despesasExibidas.add(despesa);
                    }
                }
                break;
            case 2:
                for (Despesa despesa : despesas) {
                    if (despesa.getPagamentos().isEmpty()) {
                        despesasExibidas.add(despesa);
                    }
                }
                break;
        }

        if (despesasExibidas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma despesa encontrada para exibir.");
        } else {
            String[] opcoesDespesas = new String[despesasExibidas.size()];

            for (int i = 0; i < despesasExibidas.size(); i++) {
                opcoesDespesas[i] = despesasExibidas.get(i).getDescricao();
            }

            int selecaoDespesa = JOptionPane.showOptionDialog(
                null,
                "Selecione a despesa para mais opções:",
                "Despesas",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                opcoesDespesas,
                opcoesDespesas[0]
            );

            if (selecaoDespesa >= 0 && selecaoDespesa < despesasExibidas.size()) {
                Despesa despesaSelecionada = despesasExibidas.get(selecaoDespesa);
                exibirSubMenuDespesa(despesaSelecionada);
            }
        }
    }

    public static void exibirSubMenuDespesa(Despesa despesa) {
        String[] opcoesSubMenu = {
            "Editar Despesa",
            "Excluir Despesa",
            "Gerenciar Tipo de Despesa",
            "Voltar ao Menu Principal"
        };

        int selecaoSubMenu = JOptionPane.showOptionDialog(
            null,
            "Selecione uma opção para a despesa:\n" + despesa.getDescricao(),
            "Despesa",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            opcoesSubMenu,
            opcoesSubMenu[0]
        );

        switch (selecaoSubMenu) {
            case 0:
                editarDespesa(despesa);
                break;
            case 1:
                excluirDespesa(despesa);
                break;
            case 2:
                gerenciarTipoDespesa(despesa);
                break;
            case 3:
                break;
        }
    }

    public static void editarDespesa(Despesa despesa) {
        // Implementar lógica para editar despesa
    }

    public static void excluirDespesa(Despesa despesa) {
        // Implementar lógica para excluir despesa
    }

    public static void gerenciarTipoDespesa(Despesa despesa) {
        String[] opcoesTipoDespesa = {
            "Criar Tipo de Despesa",
            "Listar Tipos de Despesa",
            "Editar Tipo de Despesa",
            "Excluir Tipo de Despesa",
            "Voltar"
        };

        int selecaoTipoDespesa = JOptionPane.showOptionDialog(
            null,
            "Selecione uma opção para gerenciar os Tipos de Despesa:",
            "Gerenciar Tipo de Despesa",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            opcoesTipoDespesa,
            opcoesTipoDespesa[0]
        );

        switch (selecaoTipoDespesa) {
            case 0:
                criarTipoDespesa();
                break;
            case 1:
                listarTiposDespesa();
                break;
            case 2:
                editarTipoDespesa();
                break;
            case 3:
                excluirTipoDespesa();
                break;
            case 4:
                break;
        }
    }

    public static void criarTipoDespesa() {
        // Implementar lógica para criar tipo de despesa
    }

    public static void listarTiposDespesa() {
        // Implementar lógica para listar tipos de despesa
    }

    public static void editarTipoDespesa() {
        // Implementar lógica para editar tipo de despesa
    }

    public static void excluirTipoDespesa() {
        // Implementar lógica para excluir tipo de despesa
    }
}