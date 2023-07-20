package esquiproblema;

import java.util.LinkedList;
import java.util.Queue;

//import java.util.LinkedList;

public class Filas {
    private Queue<Esquiador> leftSingle;
    private Queue<Esquiador> leftTriple;
    private Queue<Esquiador> rightTriple;
    private Queue<Esquiador> rightSingle;
    private int esquiadores_inicial = 0;
    private int esquiadores_faltam = 0;

    public Filas(int e) {
        this.leftSingle = new LinkedList<>();
        this.leftTriple = new LinkedList<>();
        this.rightTriple = new LinkedList<>();
        this.rightSingle = new LinkedList<>();
        this.esquiadores_inicial = e;
        this.esquiadores_faltam = e;
    }

    public Queue<Esquiador> getLeftSingle() {
        return leftSingle;
    }

    public void setLeftSingle(Queue<Esquiador> leftSingle) {
        this.leftSingle = leftSingle;
    }

    public Queue<Esquiador> getLeftTriple() {
        return leftTriple;
    }

    public void setLeftTriple(Queue<Esquiador> leftTriple) {
        this.leftTriple = leftTriple;
    }

    public Queue<Esquiador> getRightTriple() {
        return rightTriple;
    }

    public void setRightTriple(Queue<Esquiador> rightTriple) {
        this.rightTriple = rightTriple;
    }

    public Queue<Esquiador> getRightSingle() {
        return rightSingle;
    }

    public void setRightSingle(Queue<Esquiador> rightSingle) {
        this.rightSingle = rightSingle;
    }

    public int getTotalEsquiadores() {
        return this.esquiadores_faltam;
    }

    public int removeEsquiador() {
        return this.esquiadores_faltam--;
    }

    public int getEsquiadores() {
        return this.esquiadores_inicial;
    }
}