package ExamenTema2.Ejercicio3;

class ProductorPatas implements Runnable {
    private final Sincronizador sincronizador;

    public ProductorPatas(Sincronizador sincronizador) {
        this.sincronizador = sincronizador;
    }

    @Override
    public void run() {
        try {
            while (!sincronizador.haAlcanzadoLimite()) {
                sincronizador.ponPata();
                Thread.sleep(500); // Simula el tiempo de producción
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

