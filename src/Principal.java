import java.io.File;
import java.util.Scanner;

import afds.*;

public class Principal {

    public static void main(String[] args) {
        Principal t = new Principal();
        t.faca1();
    }

    /**
     * Esse m�todo l� o arquivo AFD.XML passado come imprime seu conteudo formatado.
     * 
     * @param w
     */
    @SuppressWarnings("empty-statement")
    public void faca1() {
        AFD a = new AFD();

        try {

            a.ler("./automatos/AFD03.XML");
            System.out.println("Autômato M = " + a);

            File myObj = new File("./tokens/token03.txt");
            Scanner myReader = new Scanner(myObj);
            String data = "";

            // carrega conteúdo numa string
            while (myReader.hasNextLine()) {
                data += myReader.nextLine() + '\n';
            }

            System.out.println("String data: " + data + " " + data.length());

            // analisa tokens
            String token = String.valueOf(data.charAt(0));
            Estado estado = a.getEstadoInicial();
            Simbolo simbolo = new Simbolo(String.valueOf(data.charAt(0)));

            int i;
            for (i = 1; i < data.length(); i++) {

                if (a.p(estado, simbolo) == null) {
                    System.out.println("Erro léxico... abortando programa");
                    break;

                } else {

                    if (a.getEstadosFinais().pertence(a.p(estado, simbolo))) {

                        System.out.println("Token: " + removeLastChar(token));
                        estado = a.getEstadoInicial();
                        token = "";

                    } else {
                        estado = a.p(estado, simbolo);
                    }

                    token += String.valueOf(data.charAt(i));
                    simbolo.setSimbolo(String.valueOf(data.charAt(i)));
                }
            }
            myReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
        }
    }

    public static String removeLastChar(String s) {

        return (s == null || s.length() == 0) ? null : (s.substring(0, s.length() - 1));
    }
}
