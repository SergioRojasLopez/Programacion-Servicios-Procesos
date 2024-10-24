package EjerciciosTema1.Tarea2;

import java.util.Map;

public class T1_Tarea2_2 {
    public static void main(String[] args) {
        // Muestra el entorno de ejecución
        mostrarEntornoDeEjecucion();
    }

    // Método para mostrar el entorno de ejecución
    private static void mostrarEntornoDeEjecucion() {
        ProcessBuilder pb = new ProcessBuilder(); // Crea un ProcessBuilder

        // Obtiene el mapa del entorno
        Map<String, String> entorno = pb.environment();

        // Itera sobre el mapa e imprime cada entrada
        System.out.println("Entorno de ejecución:");
        for (Map.Entry<String, String> entrada : entorno.entrySet()) {
            System.out.println(entrada.getKey() + " = " + entrada.getValue());
        }
    }
}
