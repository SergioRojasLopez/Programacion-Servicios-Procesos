package ServidorHora;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ClienteHora {

    public static void main(String[] args) {
        final String servidor = "127.0.0.1";
        final int puerto = 12345;

        try (Socket socket = new Socket(servidor, puerto);
             DataInputStream entrada = new DataInputStream(socket.getInputStream())) {

            // Leer la fecha y hora del servidor
            String fechaHora = entrada.readUTF();

            System.out.println("Fecha y Hora recibida del Servidor: " + fechaHora);

        } catch (IOException e) {
            System.err.println("Error al conectarse al servidor: " + e.getMessage());
        }
    }
}
