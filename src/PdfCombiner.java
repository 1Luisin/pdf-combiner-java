import org.apache.pdfbox.multipdf.PDFMergerUtility;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class PdfCombiner{
    public static void main(String[] args) {

        Scanner Leitura = new Scanner(System.in); // Instanciando a entrada de dados por meio da leitura

        boolean Execute = true;
        while(Execute == true) {

            System.out.println("Combinador de Pdf's!\n1 - Executar Programa\n2 - Sair");

            int OpcaoUsuario = Leitura.nextInt();

             switch(OpcaoUsuario){

                case 1:
                    System.out.println("Digite o caminho do diretório que contém os pdf's:");
                    String caminhoDaPasta = Leitura.nextLine();

                    System.out.println("Digite o nome do arquivo final");
                    String NomeArquivoFinal = "\\"+ Leitura.nextLine() + ".pdf";

                    System.out.println("Digite o caminho final.");
                    String destino = Leitura.nextLine() + NomeArquivoFinal;



                    File pasta = new File(caminhoDaPasta);
                    File[] arquivos = pasta.listFiles((dir, name) -> name.toLowerCase().endsWith(".pdf"));
                    if (arquivos == null || arquivos.length == 0) {
                        System.out.println("Nenhum PDF encontrado na pasta.");
                        main(args);
                    }

                    PDFMergerUtility merger = new PDFMergerUtility();
                    merger.setDestinationFileName(destino);

                    try {

                        for (File pdf : arquivos) {
                            System.out.println("Adicionando: " + pdf.getName());
                            merger.addSource(pdf);
                        }

                        merger.mergeDocuments(null);
                        System.out.println("PDFs unidos com sucesso! Arquivo final: " + destino);
                    } catch (IOException e) {
                        System.err.println("Erro ao unir PDFs: " + e.getMessage());
                    }


                case 2:
                    Execute = false;
                    break;

            }

        }



    }
}