package gerenciador;

import javax.swing.JOptionPane;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import controlededespesas.TipoDespesa;

public class GerenciadorTiposDespesa {
    private List<TipoDespesa> tiposDespesa = new ArrayList<>();
    private static final String TIPOS_DESPESA_ARQUIVO = "tiposdespesa.txt";

    public TipoDespesa selecionarTipoDespesaExistente() {
        List<TipoDespesa> tiposDespesaExistentes = new ArrayList<>(tiposDespesa);

        String[] opcoesTiposDespesa = new String[tiposDespesaExistentes.size()];
        for (int i = 0; i < tiposDespesaExistentes.size(); i++) {
            opcoesTiposDespesa[i] = tiposDespesaExistentes.get(i).getNome();
        }

        int selecao = JOptionPane.showOptionDialog(
            null,
            "Selecione um tipo de despesa:",
            "Selecionar Tipo de Despesa Existente",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            opcoesTiposDespesa,
            opcoesTiposDespesa[0]
        );

        if (selecao >= 0 && selecao < tiposDespesaExistentes.size()) {
            return tiposDespesaExistentes.get(selecao);
        } else {
            return null; // Ou lance uma exceção indicando erro, dependendo da sua lógica
        }
    }

    public void carregarTiposDespesaDeArquivo() {
        File arquivo = new File(TIPOS_DESPESA_ARQUIVO);
        if (arquivo.exists()) {
            try {
                tiposDespesa.clear();
                List<String> linhas = Util.lerLinhasArquivo(arquivo);
                for (String linha : linhas) {
                    TipoDespesa tipoDespesa = new TipoDespesa(linha);
                    tiposDespesa.add(tipoDespesa);
                }
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro ao carregar tipos de despesa do arquivo.");
            }
        }
    }

    public TipoDespesa criarTipoDespesa() {
        String nomeTipoDespesa = JOptionPane.showInputDialog("Digite o nome do novo tipo de despesa:");
        if (nomeTipoDespesa == null) {
            // O usuário pressionou "Cancelar", então retorne ao menu de gerenciamento
            return null;
        }
        TipoDespesa novoTipoDespesa = new TipoDespesa(nomeTipoDespesa);

        tiposDespesa.add(novoTipoDespesa); // Adicione o novo tipo de despesa à lista

        salvarTiposDespesaEmArquivo();

        JOptionPane.showMessageDialog(null, "Tipo de despesa criada com sucesso.");

        return null;
    }

    public void listarTiposDespesa() {
        if (tiposDespesa.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum tipo de despesa cadastrado.");
            return;
        }

        StringBuilder tipos = new StringBuilder("Tipos de Despesa:\n");
        for (TipoDespesa tipo : tiposDespesa) {
            tipos.append("- ").append(tipo.getNome()).append("\n");
        }

        JOptionPane.showMessageDialog(null, tipos.toString());
        exibirMenuGerenciarTiposDespesa();
    
    }

    public void editarTipoDespesa() {
        if (tiposDespesa.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum tipo de despesa cadastrado.");
            return;
        }

        TipoDespesa tipoDespesaSelecionado = selecionarTipoDespesaExistente();
        if (tipoDespesaSelecionado == null) {
            return; // Usuário cancelou a operação
        }

        String novoNome = JOptionPane.showInputDialog("Digite o novo nome para o tipo de despesa:");
        if (novoNome != null && !novoNome.isEmpty()) {
            tipoDespesaSelecionado.setNome(novoNome);
            salvarTiposDespesaEmArquivo();
            JOptionPane.showMessageDialog(null, "Tipo de despesa editado com sucesso.");
        }
    
        exibirMenuGerenciarTiposDespesa();
    }

    public void excluirTipoDespesa() {
        if (tiposDespesa.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum tipo de despesa cadastrado.");
            return;
        }

        TipoDespesa tipoDespesaSelecionado = selecionarTipoDespesaExistente();
        if (tipoDespesaSelecionado == null) {
            return; // Usuário cancelou a operação
        }

        int confirmacao = JOptionPane.showConfirmDialog(
            null,
            "Tem certeza que deseja excluir o tipo de despesa selecionado?",
            "Confirmar Exclusão",
            JOptionPane.YES_NO_OPTION
        );

        if (confirmacao == JOptionPane.YES_OPTION) {
            tiposDespesa.remove(tipoDespesaSelecionado);
            salvarTiposDespesaEmArquivo();
            JOptionPane.showMessageDialog(null, "Tipo de despesa excluído com sucesso.");
        }
    
        exibirMenuGerenciarTiposDespesa();
    
    }


    public int exibirMenuGerenciarTiposDespesa() {
        int opcao = 0;
        do {
            String[] opcoesMenu = {
                "Criar Tipo de Despesa",
                "Listar Tipos de Despesa",
                "Editar Tipo de Despesa",
                "Excluir Tipo de Despesa",
                "Voltar"
            };

            opcao = JOptionPane.showOptionDialog(
                null,
                "Escolha uma opção para gerenciar tipos de despesa:",
                "Gerenciar Tipos de Despesa",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                opcoesMenu,
                opcoesMenu[0]
            );

            switch (opcao) {
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
                    break; // Nenhuma ação necessária, apenas sair do loop
                default:
                    opcao = -1; // Defina um valor inválido para continuar o loop
                    break;
            }
        } while (opcao == -1);

        return opcao; // Você pode retornar a opção escolhida, se necessário
    }

    public void salvarTiposDespesaEmArquivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TIPOS_DESPESA_ARQUIVO))) {
            for (TipoDespesa tipoDespesa : tiposDespesa) {
                writer.write(tipoDespesa.getNome());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar tipos de despesa em arquivo.");
        }
    }

    public TipoDespesa selecionarOuCriarTipoDespesa() {
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
            return selecionarTipoDespesaExistente();
        } else {
            criarTipoDespesa();
            // Retorne aqui o tipo de despesa criado ou selecionado após a criação
            // Por exemplo:
            return tiposDespesa.get(tiposDespesa.size() - 1);
        }
    }
}
