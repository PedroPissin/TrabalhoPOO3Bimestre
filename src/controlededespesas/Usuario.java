package controlededespesas;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;


public class Usuario implements Serializable, Pagavel {
    
	private static final long serialVersionUID = 123456789L;	
	private String username;
    private String password;

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
        
    }

    @Override
    public double getValor() {
        return 0.0;
    }
    @Override
    public String getDescricao() {
        return username; // Use o username como descrição do usuário
    }

    @Override
    public String getDataVencimento() {
        // Como o usuário não tem data de vencimento, você pode retornar uma string vazia aqui
        return "";
    }

    @Override
    public boolean estaPaga() {
        return false; // Usuário não tem pagamento, portanto sempre retorna falso
    }

    @Override
    public void marcarComoPaga() {
        // Implemente a lógica para marcar a dívida do usuário como paga
    }
    
    public boolean verificarSenha(String senha) {
        String senhaCriptografada = criptografarSenha(senha);
        return senhaCriptografada.equals(this.password);
    }
    
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    private String criptografarSenha(String senha) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(senha.getBytes(StandardCharsets.UTF_8));

            StringBuilder hash = new StringBuilder();
            for (byte b : hashBytes) {
                hash.append(String.format("%02x", b));
            }

            return hash.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null; // Trate o erro de forma adequada no seu código
        }
    }
    
    public void setPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hashText = new StringBuilder();

            for (byte b : hashBytes) {
                hashText.append(String.format("%02x", b));
            }

            this.password = hashText.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
