package ExamenTema2.Ejercicio2;

public class GestionRecursos {
    public static void main(String[] args) {
        Monitor monitor = new Monitor();

        new Thread(new Tarea(monitor, "T1", 1)).start();
        new Thread(new Tarea(monitor, "T2", 2)).start();
        new Thread(new Tarea(monitor, "T3", 3)).start();
    }
}
