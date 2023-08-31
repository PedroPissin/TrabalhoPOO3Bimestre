package controlededespesas;

import java.io.Serializable;

public class CategoriaDespesaCustomizada extends CategoriaDespesa implements Serializable {
	    private static final long serialVersionUID = 1020304052L;

	    public CategoriaDespesaCustomizada(String nome) {
	        super(nome);
	    }

	    @Override
	    public String getDescricaoCategoria() {
	        return "Categoria Customizada: " + nome;
	    }
	}


