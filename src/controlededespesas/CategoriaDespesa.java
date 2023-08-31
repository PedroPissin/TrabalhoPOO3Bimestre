package controlededespesas;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public abstract class CategoriaDespesa implements Serializable {
	private static final long serialVersionUID = 987456321L;
	
	protected String nome;
    protected List<Despesa> despesas;

    public CategoriaDespesa(String nome) {
        this.nome = nome;
        this.despesas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public List<Despesa> getDespesas() {
        return despesas;
    }

    public void adicionarDespesa(Despesa despesa) {
        despesas.add(despesa);
    }

    public double calcularTotalDespesas() {
        double total = 0;
        for (Despesa despesa : despesas) {
            total += despesa.getValor();
        }
        return total;
    }

    // Métodos abstratos ou comportamentos comuns a todas as categorias
    public abstract String getDescricaoCategoria();
    
    public static CategoriaDespesa criarCategoria(String nome) {
        if (nome.equalsIgnoreCase("Transporte")) {
            return new CategoriaTransporte();
        } else if (nome.equalsIgnoreCase("Eventual")) {
            return new CategoriaEventual();
        } else if (nome.equalsIgnoreCase("Supérfluo")) {
            return new CategoriaSuperfluo();
        } else {
            return new CategoriaDespesaCustomizada(nome);
        }
    }
}