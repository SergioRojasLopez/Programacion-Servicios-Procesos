package ExamenTema2.Ejercicio1;

public class CocheNorte implements Runnable {
    private final GestionCruce gestorCruce;

    public CocheNorte(GestionCruce gestorCruce) {
        this.gestorCruce = gestorCruce;
    }

    @Override
    public void run() {
        try {
            gestorCruce.llegaNorte();
            Thread.sleep(1000); // Tiempo pasando el cruce
            gestorCruce.sale();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
