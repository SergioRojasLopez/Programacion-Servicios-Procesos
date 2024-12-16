package ExamenTema2.Ejercicio1;

public class ControlSemaforos_Ejercicio1 implements Runnable {
    private final GestionCruce_Ejercicio1 gestorCruce;

    public ControlSemaforos_Ejercicio1(GestionCruce_Ejercicio1 gestorCruce) {
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
