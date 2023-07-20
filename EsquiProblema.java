package esquiproblema;

import java.util.logging.Level;
import java.util.logging.Logger;

public class EsquiProblema {
    
    public static void main(String[] args) {
        
        Filas filas = new Filas(120);

        new Thread(new Elevador(filas)).start();

        while (filas.getTotalEsquiadores() > 0) {
            try {

                new Thread(new Esquiador(filas)).start();

                Thread.sleep(1000);

            } catch (InterruptedException ex) {
                Logger.getLogger(EsquiProblema.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}