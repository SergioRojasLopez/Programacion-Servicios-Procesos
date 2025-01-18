import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread {
    private Socket clientSocket;
    private BufferedReader input;
    private PrintWriter output;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
        try {
            this.input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            this.output = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            System.err.println("Error al configurar el cliente: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            String message;
            while ((message = input.readLine()) != null) {
                System.out.println("Mensaje del cliente: " + message);
                output.println("Eco: " + message); // Enviar eco al cliente
            }
        } catch (IOException e) {
            System.err.println("Error en la comunicación con el cliente: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Error al cerrar el socket del cliente: " + e.getMessage());
            }
        }
    }
}
