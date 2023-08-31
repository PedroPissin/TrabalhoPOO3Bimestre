package controlededespesas;

import javax.swing.JOptionPane;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;


public class TipoDespesa implements Serializable {
	private static final long serialVersionUID = 1121314152L;
	
	private String nome;

    public TipoDespesa(String nome) {
        this.nome = nome;
    }
    public TipoDespesa() {
        // Construtor vazio
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}




class GerenciadorTiposDespesa {
    private static List<TipoDespesa> tiposDespesa = new ArrayList<>();

    public static void criarTipoDespesa() {
        String nome = JOptionPane.showInputDialog("Digite o nome do novo tipo de despesa:");
        TipoDespesa tipoDespesa = new TipoDespesa(nome);
        tiposDespesa.add(tipoDespesa);
        salvarTiposDespesaEmArquivo();
    }

    public static void listarTiposDespesa() {
        StringBuilder tipos = new StringBuilder("Tipos de Despesa:\n");
        for (TipoDespesa tipoDespesa : tiposDespesa) {
            tipos.append("- ").append(tipoDespesa.getNome()).append("\n");
        }
        JOptionPane.showMessageDialog(null, tipos.toString());
    }

    public static void excluirTipoDespesa() {
        String nome = JOptionPane.showInputDialog("Digite o nome do tipo de despesa a ser excluído:");
        TipoDespesa tipoEncontrado = null;
        for (TipoDespesa tipoDespesa : tiposDespesa) {
            if (tipoDespesa.getNome().equalsIgnoreCase(nome)) {
                tipoEncontrado = tipoDespesa;
                break;
            }
        }
        if (tipoEncontrado != null) {
            tiposDespesa.remove(tipoEncontrado);
            salvarTiposDespesaEmArquivo();
            JOptionPane.showMessageDialog(null, "Tipo de despesa excluído: " + nome);
        } else {
            JOptionPane.showMessageDialog(null, "Tipo de despesa não encontrado.");
        }
    }

    private static void salvarTiposDespesaEmArquivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("tiposdespesa.txt"))) {
            for (TipoDespesa tipoDespesa : tiposDespesa) {
                writer.write(tipoDespesa.getNome());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar tipos de despesa em arquivo.");
        }
    }

    public static void carregarTiposDespesaDeArquivo() {
        File arquivo = new File("tiposdespesa.txt");
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
}

class Util {
    public static List<String> lerLinhasArquivo(File arquivo) throws IOException {
        List<String> linhas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                linhas.add(linha);
            }
        }
        return linhas;
 
    }


}