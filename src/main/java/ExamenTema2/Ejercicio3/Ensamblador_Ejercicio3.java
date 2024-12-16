package ExamenTema2.Ejercicio3;

class Ensamblador_Ejercicio3 implements Runnable {
    private final Sincronizador_Ejercicio3 sincronizador;

    public Ensamblador_Ejercicio3(Sincronizador_Ejercicio3 sincronizador) {
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