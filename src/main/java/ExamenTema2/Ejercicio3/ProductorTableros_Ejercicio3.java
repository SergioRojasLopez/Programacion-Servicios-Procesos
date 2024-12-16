package ExamenTema2.Ejercicio3;

class ProductorTableros_Ejercicio3 implements Runnable {
    private final Sincronizador_Ejercicio3 sincronizador;

    public ProductorTableros_Ejercicio3(Sincronizador_Ejercicio3 sincronizador) {
        this.sincronizador = sincronizador;
    }

    @Override
    public void run() {
        try {
            while (!sincronizador.haAlcanzadoLimite()) {
                sincronizador.ponTablero();
                Thread.sleep(1000); // Simula el tiempo de producción
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
