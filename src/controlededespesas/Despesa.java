package controlededespesas;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import controlededespesas.PagamentoManager;
import controlededespesas.TipoDespesa;
import java.util.Objects;
import javax.swing.JOptionPane;
import java.util.stream.Collectors;


public class Despesa implements Serializable, Pagavel {
    
	private static final String DESPESAS_ARQUIVO = "despesas.dat";
	private static final long serialVersionUID = 987654321L;
	private String descricao;
    private double valor;
    private String dataVencimento;
    private String categoria;
    private TipoDespesa tipoDespesa;
    private CategoriaDespesa categoriaDespesa;
    private double valorRestante;
    private List<Pagamento> pagamentos;
    
    public Despesa(String descricao, double valor, String dataVencimento, String categoria, TipoDespesa tipoDespesa, CategoriaDespesa categoriaDespesa) {
        this.descricao = descricao;
        this.valor = valor;
        this.dataVencimento = dataVencimento;
        this.categoria = categoria;
        this.tipoDespesa = tipoDespesa;
        this.categoriaDespesa = categoriaDespesa;
        this.valorRestante = valor;
        this.pagamentos = new ArrayList<>();
    }

    public double getValorRestante() {
        return valorRestante;
    }

    public double getValorTotal() {
        return valor;
    }
    
    public void setDescricao(String descricao) {
        // Implementação do método
    }

    public void setValor(double valor) {
        // Implementação do método
    }

    public void setDataVencimento(String dataVencimento) {
        // Implementação do método
    }

    public void setCategoriaDespesa(CategoriaDespesa categoriaDespesa) {
        // Implementação do método
    }
    
    public void inicializarPagamentos() {
        this.pagamentos = new ArrayList<>();
    }
    
    
    public void setValorRestante(double valorRestante) {
        this.valorRestante = valorRestante;
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void adicionarPagamento(Pagamento pagamento) {
        pagamentos.add(pagamento);
    }
    @Override
    public double getValor() {
        return valor;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Despesa despesa = (Despesa) o;
        return Objects.equals(descricao, despesa.descricao) &&
               Objects.equals(dataVencimento, despesa.dataVencimento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descricao, dataVencimento);
    }
    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public String getDataVencimento() {
        return dataVencimento;
    }

    @Override
    public boolean estaPaga() {
        // Implemente a lógica para verificar se a despesa está paga
        return false;
    }

    @Override
    public void marcarComoPaga() {
        // Implemente a lógica para marcar a despesa como paga
    }

    
    public static List<Despesa> listarDespesasPagas(List<Despesa> listaDeDespesas) {
        List<Despesa> despesasPagas = new ArrayList<>();
        for (Despesa despesa : listaDeDespesas) {
            if (despesa.estaPaga()) {
                despesasPagas.add(despesa);
            }
        }
        return despesasPagas;
    }

    public static List<Despesa> listarDespesasEmAberto(List<Despesa> listaDeDespesas) {
        List<Despesa> despesasEmAberto = new ArrayList<>();
        for (Despesa despesa : listaDeDespesas) {
            if (!despesa.estaPaga()) {
                despesasEmAberto.add(despesa);
            }
        }
        return despesasEmAberto;
    }
    

    public static void listarDespesasPorPeriodo() {
        String[] opcoesPeriodo = { "Despesas Pagas", "Despesas em Aberto", "Voltar" };

        int selecaoPeriodo = JOptionPane.showOptionDialog(
            null,
            "Selecione uma opção:",
            "Listar Despesas por Período",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            opcoesPeriodo,
            opcoesPeriodo[0]
        );

        switch (selecaoPeriodo) {
            case 0:
                listarDespesasPagas();
                break;
            case 1:
                listarDespesasEmAberto();
                break;
            case 2:
                ListarDespesasController.listarDespesas(); // Chama o método da classe ListarDespesasController
                break;
            default:
                break;
        }
    }

    public static void listarDespesasPagas() {
        List<Despesa> despesasPagas = Controller.getListaDeDespesas().stream()
                .filter(Despesa::estaPaga)
                .collect(Collectors.toList());

        if (despesasPagas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma despesa paga encontrada.");
        } else {
            StringBuilder despesasPagasStr = new StringBuilder("Despesas Pagas:\n");
            for (Despesa despesa : despesasPagas) {
                despesasPagasStr.append(despesa.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(null, despesasPagasStr.toString());
        }
    }

    public static void listarDespesasEmAberto() {
        List<Despesa> despesasEmAberto = Controller.getListaDeDespesas().stream()
                .filter(despesa -> !despesa.estaPaga())
                .collect(Collectors.toList());

        if (despesasEmAberto.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma despesa em aberto encontrada.");
        } else {
            StringBuilder despesasEmAbertoStr = new StringBuilder("Despesas em Aberto:\n");
            for (Despesa despesa : despesasEmAberto) {
                despesasEmAbertoStr.append(despesa.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(null, despesasEmAbertoStr.toString());
        }
    }
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public TipoDespesa getTipoDespesa() {
        return tipoDespesa;
    }

    public void setTipoDespesa(TipoDespesa tipoDespesa) {
        this.tipoDespesa = tipoDespesa;
    }

    public static void salvarDespesasEmArquivo(List<Despesa> despesas, String arquivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(despesas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    public static List<Despesa> carregarDespesasDeArquivo(String arquivo) {
        List<Despesa> despesas = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            despesas = (List<Despesa>) ois.readObject();
        } catch (FileNotFoundException e) {
            // Arquivo não encontrado, cria um novo arquivo vazio
            salvarDespesasEmArquivo(despesas, arquivo);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return despesas;
    }
}
