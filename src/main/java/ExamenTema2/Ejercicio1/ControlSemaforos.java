package ExamenTema2.Ejercicio1;

public class ControlSemaforos implements Runnable {
    private final GestionCruce gestorCruce;

    public ControlSemaforos(GestionCruce gestorCruce) {
        this.gestorCruce = gestorCruce;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(5000); // Cambiar cada 5 segundos
                gestorCruce.cambiaSemaforos();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
