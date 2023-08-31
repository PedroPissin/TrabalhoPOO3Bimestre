package controlededespesas;

import java.io.Serializable;

public class CategoriaEventual extends CategoriaDespesa implements Serializable {
	private static final long serialVersionUID = 159753852L;
	
	public CategoriaEventual() {
        super("Eventual");
    }

    @Override
    public String getDescricaoCategoria() {
        return "Despesas eventuais e não recorrentes.";
    }

    // Métodos específicos da categoria Eventual
    // ...
}