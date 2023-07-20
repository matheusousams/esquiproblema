package esquiproblema;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.DecimalFormat;

public class Elevador implements Runnable{
    
    Filas filas;

    int vazio = 4;
    int cont = 1;

    String elevador = "";
    String tempoNaFila = "";

    public Elevador(Filas f) {
        filas = f;
    } 

   
    public Filas getFilas() {
        return filas;
    }

    public void setFilas(Filas filas) {
        this.filas = filas;
    }

    @Override
    public synchronized void run() {
        while(true) {
        try {
            this.wait(4000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Elevador.class.getName()).log(Level.SEVERE, null, ex);
        }

            if(filas.getTotalEsquiadores() == 0) {
               System.exit(0);    
            }
            
            boolean leftTriple = false;
            boolean rightTriple = false;
            Esquiador esquiador;

            int random = new Random().nextInt(2);

            if (random == 0) {
                if (filas.getLeftTriple().size() > 2 && vazio > 2) {
                    for (int i = 2; i >= 0; i--) {
                        esquiador = filas.getLeftTriple().remove();
                        vazio--;
                        filas.removeEsquiador();
                        tempoNaFila = tempoNaFila + "\nTempo do esquiador na fila LeftTriple: " + esquiador.tempoNaFila() + " segundos.";
                    }
                    elevador = "LT LT LT";
                    leftTriple = true;
                }
            } else {
                if (filas.getRightTriple().size() > 2 && vazio > 2) {
                    for (int i = 2; i >= 0; i--) {
                        esquiador = filas.getRightTriple().remove();
                        vazio--;
                        tempoNaFila = tempoNaFila + "\nTempo do esquiador na fila RightTriple: " + esquiador.tempoNaFila() + " segundos.";
                    }

                    elevador = "RT RT RT";
                    rightTriple = true;
                }
            }

            if (!leftTriple && !rightTriple) {

                if (filas.getLeftSingle().size() >= 2 && filas.getRightSingle().size() >= 2)
                {
                    random = new Random().nextInt(2);

                    //Utilizada para alternar as filas.
                    boolean chave = random == 0;

                    while (vazio > 0 && (filas.getLeftSingle().size() >= 2 || filas.getRightSingle().size() >= 2)) {

                        if (chave) {
                            if (filas.getLeftSingle().size() >= 2) {
                                for (int i = 1; i >= 0; i--) {
                                    esquiador = filas.getLeftSingle().remove();
                                    vazio--;
                                    filas.removeEsquiador();
                                    tempoNaFila = tempoNaFila + "\nTempo do esquiador na fila LeftSingle: " + esquiador.tempoNaFila() + " segundos.";
                                }

                                elevador = "LS LS " + elevador;
                            }

                            chave = false;
                        } else {
                            if (filas.getRightSingle().size() >= 2) {
                                for (int i = 1; i >= 0; i--) {
                                    esquiador = filas.getRightSingle().remove();
                                    vazio--;
                                    filas.removeEsquiador();
                                    tempoNaFila = tempoNaFila + "\nTempo do esquiador na fila RightSingle: " + esquiador.tempoNaFila() + " segundos.";
                                }

                                elevador = elevador + "RS RS";
                            }

                            chave = true;
                        }
                    }
                }
            } else {
                if (leftTriple && filas.getRightSingle().size() > 0) {
                    esquiador = filas.getRightSingle().remove();
                    vazio--;
                    filas.removeEsquiador();
                    tempoNaFila = tempoNaFila + "\nTempo do esquiador na fila RightSingle: " + esquiador.tempoNaFila() + " segundos.";

                    elevador += " RS";
                }

                if (rightTriple && filas.getLeftSingle().size() > 0) {
                    esquiador = filas.getLeftSingle().remove();
                    vazio--;
                    filas.removeEsquiador();
                    tempoNaFila = tempoNaFila + "\nTempo em fila de esquiador em LeftSingle: " + esquiador.tempoNaFila() + " segundos.";

                    elevador = "LS " + elevador;
                }
            }

            if (!"".equals(elevador)) {
                print();            
            } else {
                System.out.println(elevador + " saiu vazio");
            }

            elevador = "";
            tempoNaFila = "";

            cont++;
            vazio = 4;

        }     
       
    }

    private void print() {
        DecimalFormat df=new DecimalFormat("#.##");
        double taxa = (filas.getEsquiadores() - filas.getTotalEsquiadores()) / (cont * 4.0);
        double media = (filas.getEsquiadores()/filas.getTotalEsquiadores());
        String print = "\nElevador: " + cont;
        print += "\nFaltam " + filas.getTotalEsquiadores() + " esquiadores";
        print += "\n" + elevador;
        print += "\nTempo em fila dos Esquiadores: ";
        print += "\n" + tempoNaFila;
        print += "\n============================================";
        print += "\nFilas após a ida do elevador:";
        print += "\nLeftSingle: " + filas.getLeftSingle().size();
        print += "\nRightSingle: " + filas.getRightSingle().size();
        print += "\nLeftTriple: " + filas.getLeftTriple().size();
        print += "\nRightTriple: " + filas.getRightTriple().size();
        print += "\nTaxa de Ocupação: " + df.format(taxa);
        //print += "\n: " + df.format(media);
        print += "\n============================================";

        System.out.println(print); 
    }

    private void exit(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}


