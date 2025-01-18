package ExamenTema2.Ejercicio1;

public class CruceCalles_Ejercicio1 {
    public static void main(String[] args) {
        GestionCruce_Ejercicio1 gestorCruce = new GestionCruce_Ejercicio1();

        // Crear y lanzar coches
        for (int i = 0; i < 10; i++) {
            new Thread(new CocheNorte_Ejercicio1(gestorCruce)).start();
            new Thread(new CocheOeste_Ejercicio1(gestorCruce)).start();
        }

        // Lanzar control de semáforos
        new Thread(new ControlSemaforos_Ejercicio1(gestorCruce)).start();
    }
}
