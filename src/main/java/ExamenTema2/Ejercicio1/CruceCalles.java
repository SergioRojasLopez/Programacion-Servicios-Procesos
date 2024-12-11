package ExamenTema2.Ejercicio1;

public class CruceCalles {
    public static void main(String[] args) {
        GestionCruce gestorCruce = new GestionCruce();

        // Crear y lanzar coches
        for (int i = 0; i < 10; i++) {
            new Thread(new CocheNorte(gestorCruce)).start();
            new Thread(new CocheOeste(gestorCruce)).start();
        }

        // Lanzar control de semáforos
        new Thread(new ControlSemaforos(gestorCruce)).start();
    }
}
