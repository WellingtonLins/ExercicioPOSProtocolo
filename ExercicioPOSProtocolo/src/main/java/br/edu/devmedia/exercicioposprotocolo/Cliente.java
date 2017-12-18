package br.edu.devmedia.exercicioposprotocolo;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * Document Cliente
 *
 * @Date 03/07/2016 @Time 16:20:40
 * @author Wellington Lins Claudino Duarte
 * @author Michelle Oliveira
 */
public class Cliente {

    public static void main(String[] args) throws IOException {

        try {
            Socket socket = new Socket("127.0.0.1", 10999);
            String mensagem = "<mensagem>"
                    + "<para>michelle</para>"
                    + "<de>wellington</de>"
                    + "<criadoEm> 21/11/2017 13:00:00</criadoEm>"
                    + "<corpo> Oi, sou um Home</corpo>"
                    + "<tamanho>%d</tamanho>"
                    + "</mensagem>";

            Integer x = mensagem.length(); //154 com %d, 152 sem %d
            System.out.println("Valor antes" + x);
            x += x.toString().length() - 2; //x = 154

            mensagem = String.format(mensagem, x);

            System.out.println("Valor depois" + x);
            socket.getOutputStream().write(mensagem.getBytes());

            InputStream in = socket.getInputStream();
            byte[] b = new byte[1024];
            in.read(b);
            
            System.out.println("From Server: \n" + new String(b));

        } catch (IOException ex) {
            System.err.println("Erro ao enviar!" + ex);
        }
    }
}
