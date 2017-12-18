package br.edu.ifpb.server.protocolo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

/**
 *
 * @author Michelle Oliveira
 * @mail miolivc@outlook.com
 * @since 13/12/2017
 */
public class Loader {

    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(10999);

        System.out.println("Server is running...");
        
        while (true) {
            
            Socket socket = server.accept();
            
            InputStream input = socket.getInputStream();
            byte[] arrayDeBytes = new byte[1024];
            input.read(arrayDeBytes);

            String entrada = new String(arrayDeBytes);
            System.out.println("=> Entrada do Sistema: \n" + entrada);

            OutputStream out = socket.getOutputStream();

            if (verificaTags(entrada) && verificaTamanhoMensagem(entrada)) {
                out.write(geradorResposta(
                        extractRemetente(entrada), true));
            } else {
                out.write(geradorResposta(
                        extractRemetente(entrada), false));
            }
        }
        
    }

    public static boolean verificaTamanhoMensagem(String message) {
        int inicioValorTam = message.indexOf("<tamanho>");
        int fimValorTam = message.indexOf("</tamanho>");

        CharSequence subSequence = message.subSequence(inicioValorTam + 9, fimValorTam);
        int lenght = Integer.parseInt(subSequence.toString());
        int extractedText = (message.trim().length());
        
        System.out.println("Tamanho da mensagem recebida: " + extractedText);
        System.out.println("Tamanho extraido da mensagem: " + lenght);

        return (lenght == extractedText);
    }
    
    public static boolean verificaTags(String message) {
        return message.contains("<mensagem>") && message.contains("</mensagem>")
                && message.contains("<para>") && message.contains("</para>")
                && message.contains("<de>") && message.contains("</de>")
                && message.contains("<criadoEm>") && message.contains("</criadoEm>")
                && message.contains("<corpo>") && message.contains("</corpo>")
                && message.contains("<tamanho>") && message.contains("</tamanho>");
    } 
    
    public static String extractRemetente(String message) {
        int inicioValorTam = message.indexOf("<de>");
        int fimValorTam = message.indexOf("</de>");

        CharSequence subSequence = message.subSequence(inicioValorTam + 4, fimValorTam);
        
        String remetente = subSequence.toString();
        System.out.println("Remetente: " + remetente);
        
        return remetente;
    }

    public static byte[] geradorResposta(String remetente, boolean status) {
        String mensagem = "<mensagem>"
                    + "<para>%s</para>"
                    + "<de>server</de>"
                    + "<criadoEm>%s</criadoEm>"
                    + "<corpo>%s</corpo>"
                    + "<tamanho>%d</tamanho>"
                    + "</mensagem>";
        
        String body = "Fail!";
        String criadoEm = LocalDateTime.now().toString();
        
        if (status) {
            body = "Sucesso ao receber mensagem!" ;
        }
        
        Integer x = (mensagem.length() - 8) + remetente.length() + criadoEm.length() + body.length() ; 
        System.out.println("Valor antes" + x);
        
        mensagem = String.format(mensagem, remetente, criadoEm, body, (x += x.toString().length()));
        System.out.println("Valor depois" + x);
        System.out.println("Answer: " + mensagem);
        
        return mensagem.getBytes();
    }
    
}
