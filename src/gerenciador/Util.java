package gerenciador;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Util {
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