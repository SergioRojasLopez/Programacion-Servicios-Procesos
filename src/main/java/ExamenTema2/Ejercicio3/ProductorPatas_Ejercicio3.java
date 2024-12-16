package ExamenTema2.Ejercicio3;

class ProductorPatas_Ejercicio3 implements Runnable {
    private final Sincronizador_Ejercicio3 sincronizador;

    public ProductorPatas_Ejercicio3(Sincronizador_Ejercicio3 sincronizador) {
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

