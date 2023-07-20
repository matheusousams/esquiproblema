package esquiproblema;

public class GeraEsquiador implements Runnable{
    
    Filas filas;

    public GeraEsquiador(Filas filas) {
        this.filas = filas;
    }

    public Filas getFilas() {
        return filas;
    }

    public void setFilas(Filas filas) {
        this.filas = filas;
    }

    /**
     *
     */
    @Override
    public void run() {
    }
}