import org.apache.pdfbox.multipdf.PDFMergerUtility;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class PdfCombiner{

    public static final String ANSI_RED = "\u001B[31m"; // cor vermelha
    public static final String ANSI_RESET = "\u001B[0m"; // reset
    public static final String ANSI_GREEN = "\u001B[32m"; // cor verde

    public static void main(String[] args) {
        ClearConsole Clearconsole = new ClearConsole();
        Scanner Leitura = new Scanner(System.in); // Instanciando a entrada de dados por meio da leitura

        boolean Execute = true;

        while(Execute == true) {

            System.out.println("Combinador de Pdf's!");
            System.out.println("\n1 - Executar Programa\n2 - Sair");

            String OpcaoUsuario = Leitura.nextLine();

             switch(OpcaoUsuario){

                case "1":
                    Clearconsole.Limpador();
                    System.out.println("Digite o caminho do diretório que contém os pdf's:");
                    String caminhoDaPasta = Leitura.nextLine();

                    Clearconsole.Limpador();
                    System.out.println("Digite o nome do arquivo final");
                    String NomeArquivoFinal = "\\"+ Leitura.nextLine() + ".pdf";

                    Clearconsole.Limpador();
                    System.out.println("Digite o caminho final.");
                    String destino = Leitura.nextLine() + NomeArquivoFinal;


                    File pasta = new File(caminhoDaPasta);
                    File[] arquivos = pasta.listFiles((dir, name) -> name.toLowerCase().endsWith(".pdf"));

                    if (arquivos == null || arquivos.length == 0) {
                        System.out.println("Nenhum PDF encontrado na pasta.\n");
                        Leitura.nextLine();
                        Clearconsole.Limpador();
                    }

                    PDFMergerUtility merger = new PDFMergerUtility();
                    merger.setDestinationFileName(destino);

                    try {
                        Clearconsole.Limpador();
                        for (File pdf : arquivos) {
                            System.out.println("Adicionando: " + pdf.getName());
                            merger.addSource(pdf);
                        }

                        merger.mergeDocuments(null);
                        System.out.println(ANSI_GREEN + "PDFs unidos com sucesso! Arquivo final: " + ANSI_RESET + destino);
                        System.out.println("Pressione ENTER para continuar. . .");
                        Leitura.nextLine();
                        Clearconsole.Limpador();
                        main(args);

                    } catch (IOException e) {
                        System.err.println(ANSI_RED + "Erro ao unir PDFs: \n" + e.getMessage());
                        System.out.println("Pressione ENTER para continuar. . .");
                        Leitura.nextLine();
                        Clearconsole.Limpador();
                    }


                case "2":
                    Execute = false;
                    break;

                 default:
                     System.out.println( ANSI_RED + "Comando não reconhecido!\n" + ANSI_RESET);
            }

        }



    }
}