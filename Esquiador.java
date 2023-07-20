package esquiproblema;

import esquiproblema.Filas;

public class Esquiador implements Runnable{

    Filas filas;
    long tempoDeEntrada;
    String entradas="\n";

    public Esquiador(Filas filas) {
        this.filas = filas;
    }

    public Filas getFilas() {
        return filas;
    }

    public void setFilas(Filas filas) {
        this.filas = filas;
    }

    public long tempoNaFila() {
        long TempoDeSaida = System.currentTimeMillis();
        return (TempoDeSaida - tempoDeEntrada) / 1000;
    }

    @Override
    public synchronized void run() {
        if (filas.getLeftSingle().size() < (filas.getLeftTriple().size() * 2)
                && filas.getLeftSingle().size() < (filas.getRightTriple().size() * 2)
                && filas.getLeftSingle().size() < filas.getRightSingle().size()) {
            filas.getLeftSingle().add(this);
            tempoDeEntrada = System.currentTimeMillis();

            entradas+="\nEsquiador entrou na fila: LeftSingle";

        } else if (filas.getRightSingle().size() < (filas.getLeftTriple().size() * 2)
                && filas.getRightSingle().size() < (filas.getRightTriple().size() * 2)
                && filas.getRightSingle().size() <= filas.getLeftSingle().size()) {
            filas.getRightSingle().add(this);
            tempoDeEntrada = System.currentTimeMillis();

            entradas+="\nEsquiador entrou na fila: RightSingle";
        } else if (filas.getLeftTriple().size() <= filas.getRightTriple().size()) {
            filas.getLeftTriple().add(this);
            tempoDeEntrada = System.currentTimeMillis();

            entradas+="\nEsquiador entrou na fila: LeftTriple";
        } else {
            filas.getRightTriple().add(this);
            tempoDeEntrada = System.currentTimeMillis();

            entradas+="\nEsquiador entrou na fila: RightTriple";
        }

        String print = entradas;
        print+="\nTotal de esquiadores por fila:";
        print+="\nLeftSingle: " + filas.getLeftSingle().size();
        print+="\nRightSingle: " + filas.getRightSingle().size();
        print+="\nLeftTriple: " + filas.getLeftTriple().size();
        print+="\nRightTriple: " + filas.getRightTriple().size();
        print+="\n============================================";
        System.out.println(print);
        
    }
}