package gerenciador;

import javax.swing.JOptionPane;
import controlededespesas.Usuario;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import controlededespesas.Controller;


public class GerenciadorUsuarios {
    private List<Usuario> usuarios = new ArrayList<>();
    private static final String USUARIOS_ARQUIVO = "usuarios.txt";

    public GerenciadorUsuarios() {
        carregarUsuariosDeArquivo();
    }

    public void cadastrarUsuario() {
        String username = JOptionPane.showInputDialog("Digite o nome de usuário:");
        if (username == null) {
            exibirMenuGerenciarUsuarios(); // Volta para o menu de gerenciar usuários
            return;
        }
        String password = JOptionPane.showInputDialog("Digite a senha:");

        Usuario usuario = new Usuario(username, password);
        usuario.setPassword(password); // Criptografa a senha
        usuarios.add(usuario);
        salvarUsuariosEmArquivo();

        JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso.");
        exibirMenuGerenciarUsuarios();
    
    }

    public void editarUsuario() {
        if (usuarios.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum usuário cadastrado.");
            return;
        }

        String username = JOptionPane.showInputDialog("Digite o nome de usuário a ser editado:");
        Usuario usuarioEncontrado = null;

        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equalsIgnoreCase(username)) {
                usuarioEncontrado = usuario;
                break;
            }
        }

        if (usuarioEncontrado != null) {
            int opcao = JOptionPane.showOptionDialog(
                null,
                "Escolha o que deseja editar:",
                "Editar Usuário",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                new String[] {"Nome", "Senha"},
                "Nome"
            );

            if (opcao == 0) { // Editar nome
                String novoNome = JOptionPane.showInputDialog("Digite o novo nome de usuário:");
                usuarioEncontrado.setUsername(novoNome);
                salvarUsuariosEmArquivo();
                JOptionPane.showMessageDialog(null, "Nome do usuário atualizado com sucesso.");
            } else if (opcao == 1) { // Editar senha
                String novaSenha = JOptionPane.showInputDialog("Digite a nova senha:");
                usuarioEncontrado.setPassword(novaSenha); // Criptografa a senha
                salvarUsuariosEmArquivo();
                JOptionPane.showMessageDialog(null, "Senha do usuário atualizada com sucesso.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Usuário não encontrado.");
        }
    
        exibirMenuGerenciarUsuarios();
    }

    public void excluirUsuario() {
        if (usuarios.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum usuário cadastrado.");
            return;
        }

        String username = JOptionPane.showInputDialog("Digite o nome de usuário a ser excluído:");
        Usuario usuarioEncontrado = null;

        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equalsIgnoreCase(username)) {
                usuarioEncontrado = usuario;
                break;
            }
        }

        if (usuarioEncontrado != null) {
            String senha = JOptionPane.showInputDialog("Digite a senha do usuário para confirmar a exclusão:");
            if (usuarioEncontrado.verificarSenha(senha)) {
                usuarios.remove(usuarioEncontrado);
                salvarUsuariosEmArquivo();
                JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso.");
            } else {
                JOptionPane.showMessageDialog(null, "Senha incorreta. A exclusão não foi realizada.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Usuário não encontrado.");
        }
    
        exibirMenuGerenciarUsuarios();
    
  }

    public void listarUsuarios() {
        if (usuarios.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum usuário cadastrado.");
            return;
        }

        StringBuilder lista = new StringBuilder("Usuários cadastrados:\n");
        for (Usuario usuario : usuarios) {
            lista.append("- ").append(usuario.getUsername()).append("\n");
        }

        JOptionPane.showMessageDialog(null, lista.toString());
        exibirMenuGerenciarUsuarios();
    }

    public int exibirMenuGerenciarUsuarios() {
        String[] opcoesMenu = {
            "Cadastrar Usuário",
            "Listar Usuários",
            "Editar Usuário",
            "Excluir Usuário",
            "Voltar"
        };

        int escolha = JOptionPane.showOptionDialog(
            null,
            "Escolha uma opção para gerenciar usuários:",
            "Gerenciar Usuários",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            opcoesMenu,
            opcoesMenu[0]
        );
        
        if (escolha == 0) {
            cadastrarUsuario();
        } else if (escolha == 1) {
            listarUsuarios();
        } else if (escolha == 2) {
            editarUsuario();
        } else if (escolha == 3) {
            excluirUsuario();
        } else if (escolha == 4) { // Ação para a opção "Voltar"
        	Controller.exibirMenuPrincipal();;
        }

        return escolha; // Adicione essa linha para retornar a escolha feita pelo usuário
    }

    
    private void salvarUsuariosEmArquivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USUARIOS_ARQUIVO))) {
            oos.writeObject(usuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void carregarUsuariosDeArquivo() {
        File arquivo = new File(USUARIOS_ARQUIVO);
        if (arquivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USUARIOS_ARQUIVO))) {
                Object obj = ois.readObject();
                
                if (obj instanceof List<?>) {
                    List<?> tempList = (List<?>) obj;
                    if (!tempList.isEmpty() && tempList.get(0) instanceof Usuario) {
                        List<Usuario> usuarioList = new ArrayList<>();
                        for (Object item : tempList) {
                            usuarioList.add((Usuario) item);
                        }
                        usuarios = usuarioList;
                    } else {
                        // Tratar o caso em que o objeto lido não contém instâncias de Usuario
                    }
                } else {
                    // Tratar o caso em que o objeto lido não é uma List<?>
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean autenticarUsuario(String username, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equalsIgnoreCase(username) && usuario.verificarSenha(senha)) {
                return true; // Autenticação bem-sucedida
            }
        }
        return false; // Autenticação falhou
    }
    // Outros métodos da classe
}
