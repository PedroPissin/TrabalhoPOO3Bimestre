package controlededespesas;

import java.io.Serializable;

public class CategoriaSuperfluo extends CategoriaDespesa implements Serializable {
	private static final long serialVersionUID = 134679258L;
	
	public CategoriaSuperfluo() {
        super("Supérfluo");
    }

    @Override
    public String getDescricaoCategoria() {
        return "Despesas supérfluas e não essenciais.";
    }

    // Métodos específicos da categoria Supérfluo
    // ...
}