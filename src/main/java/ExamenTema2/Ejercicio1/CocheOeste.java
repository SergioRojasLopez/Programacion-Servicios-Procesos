package ExamenTema2.Ejercicio1;

public class CocheOeste implements Runnable{
    private final GestionCruce gestorCruce;

    public CocheOeste(GestionCruce gestorCruce) {
        this.gestorCruce = gestorCruce;
    }

    @Override
    public void run() {
        try {
            gestorCruce.llegaOeste();
            Thread.sleep(1000); // Tiempo pasando el cruce
            gestorCruce.sale();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
