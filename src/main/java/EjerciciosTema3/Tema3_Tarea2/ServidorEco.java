package EjerciciosTema3.Tema3_Tarea2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorEco {

    public static void main(String[] args) {
        final int PUERTO = 12345;

        try (ServerSocket servidorSocket = new ServerSocket(PUERTO)) {
            System.out.println("Servidor de Eco escuchando en el puerto " + PUERTO);

            while (true) {
                System.out.println("Esperando una conexión...");
                Socket conexionCliente = servidorSocket.accept();
                System.out.println("Cliente conectado desde: " + conexionCliente.getInetAddress());

                // Crear un hilo para manejar al cliente conectado
                Thread hiloCliente = new Thread(new ClienteHandler(conexionCliente));
                hiloCliente.start();
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}