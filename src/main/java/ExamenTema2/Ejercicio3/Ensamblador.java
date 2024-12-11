package ExamenTema2.Ejercicio3;

class Ensamblador implements Runnable {
    private final Sincronizador sincronizador;

    public Ensamblador(Sincronizador sincronizador) {
        this.sincronizador = sincronizador;
    }

    @Override
    public void run() {
        try {
            while (!sincronizador.haAlcanzadoLimite()) {
                sincronizador.cogePatasyTablero();
                Thread.sleep(1500); // Simula el tiempo de ensamblaje
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}