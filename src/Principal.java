
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import afds.*;

public class Principal {
	
    public static void main(String[] args) {				
	Principal t = new Principal();		
        t.faca1("ababa");
 //       t.faca2();
    }

    public static String removeLastChar(String s) {
        return (s == null || s.length() == 0)
          ? null 
          : (s.substring(0, s.length() - 1));
    }

    /**
     *  Esse m�todo l� o arquivo AFD.XML e imprime
     *  seu conteudo formatado.
     * @param w
     */    
    @SuppressWarnings("empty-statement")
    public void faca1(String w) {
        AFD a = new AFD();
        try {
               a.ler("./automatos/AFD03.XML");
               System.out.println(a);

                //ler arquivo texto
                try {
                    File myObj = new File("./tokens/token02.txt");
                    Scanner myReader = new Scanner(myObj);
                    String data = "";

                    while (myReader.hasNextLine()) {
                        data += myReader.nextLine() + '\n';
                    }

                    //analisar tokens
                    String token = String.valueOf(data.charAt(0));
                    int i = 1;
                    Estado estado = a.getEstadoInicial();
                    Simbolo simbolo = new Simbolo(String.valueOf(data.charAt(0)));
                    while(true){
                        
                        if(a.p(estado, simbolo) == null){
                            System.out.println("Erro léxico... abortando programa");
                            break;
                        }else{

                            if(a.getEstadosFinais().pertence(a.p(estado, simbolo))){
                                System.out.println("Token: " + removeLastChar(token));
                                estado = a.getEstadoInicial();
                                token = "";
                            }else{
                                estado = a.p(estado, simbolo);
                            }
                            token += String.valueOf(data.charAt(i));
                            simbolo.setSimbolo(String.valueOf(data.charAt(i)));
                            i++;
                        }
                    }

                    myReader.close();
                  } catch (FileNotFoundException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                  }


               if (a.Aceita(w))
                   System.out.println("Aceitou "+w);


               System.out.println("Pe(q0,"+w+"):"+a.pe(a.getEstadoInicial(),w));
        } catch (Exception e){
               System.out.println(e); 
        } 
    }
}
