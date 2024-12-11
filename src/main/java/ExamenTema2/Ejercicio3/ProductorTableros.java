package ExamenTema2.Ejercicio3;

class ProductorTableros implements Runnable {
    private final Sincronizador sincronizador;

    public ProductorTableros(Sincronizador sincronizador) {
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
