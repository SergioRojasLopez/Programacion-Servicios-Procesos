package ExamenTema2.Ejercicio2;

public class Tarea implements Runnable {
    private final Monitor monitor;
    private final String threadName;
    private final int recursos;

    public Tarea(Monitor monitor, String threadName, int recursos) {
        this.monitor = monitor;
        this.threadName = threadName;
        this.recursos = recursos;
    }

    @Override
    public void run() {
        try {
            if (recursos == 1) {
                monitor.requiereR1(threadName);
                Thread.sleep(2000);
                monitor.liberaR1(threadName);
            } else if (recursos == 2) {
                monitor.requiereR2_R3(threadName);
                Thread.sleep(2000);
                monitor.liberaR2_R3(threadName);
            } else {
                monitor.requiereR1_R2_R3(threadName);
                Thread.sleep(2000);
                monitor.liberaR1_R2_R3(threadName);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}