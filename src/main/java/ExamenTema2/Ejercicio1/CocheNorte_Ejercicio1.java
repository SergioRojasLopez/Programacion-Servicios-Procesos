package ExamenTema2.Ejercicio1;

public class CocheNorte_Ejercicio1 implements Runnable {
    private final GestionCruce_Ejercicio1 gestorCruce;

    public CocheNorte_Ejercicio1(GestionCruce_Ejercicio1 gestorCruce) {
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
