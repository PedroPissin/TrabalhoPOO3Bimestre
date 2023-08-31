package controlededespesas;

import java.io.Serializable;


public class CategoriaTransporte extends CategoriaDespesa implements Serializable {
	private static final long serialVersionUID = 173928460L;
	
	public CategoriaTransporte() {
        super("Transporte");
    }

    @Override
    public String getDescricaoCategoria() {
        return "Despesas relacionadas a transporte.";
    }

    // Métodos específicos da categoria Transporte
    // ...
}