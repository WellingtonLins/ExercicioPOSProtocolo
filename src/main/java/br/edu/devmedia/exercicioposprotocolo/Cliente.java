package br.edu.devmedia.exercicioposprotocolo;

import java.io.IOException;
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
            String mensagem = "<mensagem>\n"
                    + "<para>michelle</para>\n"
                    + "<de>wellington</de>\n"
                    + "<criadoEm> 21/11/2017 13:00:00</criadoEm>\n"
                    + "<corpo> Oi, sou um Home</corpo>\n"
                    + "<tamanho>%d</tamanho>\n"
                    + "</mensagem>";

            Integer x = mensagem.length();
//        System.out.println("Valor antes" + x);
            x += x.toString().length();

            mensagem = String.format(mensagem, x);

//        System.out.println("Valor depois" + x);
            socket.getOutputStream().write(mensagem.getBytes());

        } catch (IOException e) {
            System.out.println("Erro ao enviar!");
        }
    }
}