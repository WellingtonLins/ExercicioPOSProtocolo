package br.edu.devmedia.exercicioposprotocolo;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Document Servidor
 *
 * @Date 03/07/2016 @Time 16:24:13
 * @author Wellington Lins Claudino Duarte
 * @author Michelle Oliveira
 */
public class Servidor {

    public static void main(String[] args) throws IOException {

        try {
            ServerSocket servidor = new ServerSocket(10999);

            Socket socket = servidor.accept();

            InputStream input = socket.getInputStream();
            byte[] arrayDeBytes = new byte[1024];
            input.read(arrayDeBytes);

            String entrada = new String(arrayDeBytes);
       
            System.out.println(entrada);
            
    
            socket.close();
        } catch (IOException e) {

        }
    }
}
