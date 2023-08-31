package controlededespesas;

public interface Pagavel {
    double getValor();
    String getDescricao();
    String getDataVencimento();
    boolean estaPaga();
    void marcarComoPaga();
}