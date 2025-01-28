package EjerciciosTema3.Tema3_Tarea1;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ServerHora {

    public static void main(String[] args) {
        final int puerto = 12345;

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor TCP en espera de conexiones en el puerto " + puerto);

            while (true) {
                try (Socket cliente = servidor.accept();
                     DataOutputStream salida = new DataOutputStream(cliente.getOutputStream())) {

                    System.out.println("Cliente conectado desde: " + cliente.getInetAddress());

                    // Obtener la fecha y hora actual
                    String fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                    // Enviar la fecha y hora al cliente
                    salida.writeUTF(fechaHora);

                    System.out.println("Fecha y hora enviada al cliente.");
                } catch (IOException e) {
                    System.err.println("Error al manejar al cliente: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error al iniciar el servidor: " + e.getMessage());
        }
    }
}

