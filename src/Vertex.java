import java.util.LinkedList;
import java.util.List;

/**
 * Eine einfache Implementierung von Knoten eines einfachen gerichteten Graphen.
 */
public class Vertex {
    private int degree;

    private int discoveryTime;
    private int finishingTime;

    private List<Arc> arcsOut; // Adjazenzliste der vom Knoten ausgehenden inzidenten Kanten.
    private List<Arc> arcsIn; // Adjazenzliste der zum Knoten eingehenden inzidenten Kanten.

    /**
     * Erzeugt einen neuen isolierten Knoten.
     */
    public Vertex() {
        this.degree = 0;
        this.discoveryTime = -1;
        this.finishingTime = -1;
        this.arcsOut = new LinkedList<>();
        this.arcsIn = new LinkedList<>();
    }

    /**
     * Setzt die discoverytime d(v) eines Knotens. Kann nur einmal gesetzt werden.
     *
     * @param n der Wert, welcher d(v) zugewiesen werden soll.
     */
    public void setDiscoveryTime(int n) {
        if (this.discoveryTime == -1) this.discoveryTime = n;
    }

    /**
     * Setzt die finishingtime f(v) eines Knotens. Kann nur einmal gesetzt werden.
     *
     * @param n der Wert, welcher f(v) zugewiesen werden soll.
     */
    public void setFinishingTime(int n) {
        if (this.finishingTime == -1) this.finishingTime = n;
    }

    /**
     * Gibt d(v) des Knotens zurück.
     *
     * @return d(v) des Knotens.
     */
    public int getDiscoveryTime() {
        return discoveryTime;
    }

    /**
     * Gibt f(v) des Knotens zurück.
     *
     * @return f(v) des Knotens.
     */
    public int getFinishingTime() {
        return finishingTime;
    }

    /**
     * Überprüft, ob ein Knoten bereits besucht wurde.
     *
     * @return true, falls Knotn schon besucht wurde, wenn er also einen Wert für d(v) != -1 hat.
     */
    public boolean isDiscovered() {
        return (this.discoveryTime != -1);
    }

    /**
     * Bestimmt den Grad des Knotens.
     *
     * @return den Grad des Knotens.
     */
    public int getDegree() {
        return this.degree;
    }

    /**
     * Gibt die Adjazenzliste der von diesem Knoten ausgehenden inzidenten Kanten zurück.
     *
     * @return die Adjazenzliste.
     */
    public List<Arc> getArcsOut() {
        return this.arcsOut;
    }

    /**
     * Gibt die Adjazenzlsite der in diesen Knoten eingehenden inzidenten Kanten zurück.
     *
     * @return die Adjazenzliste.
     */
    public List<Arc> getArcsIn() {
        return this.arcsIn;
    }

    /**
     * Fügt eine neue von diesem Knoten ausgehende inzidente Kante hinzu.
     *
     * @param w der zu v adjazente Knoten, der den Endknoten der neuen Kante darstellt.
     */
    public void addArc(Vertex w) {
        Arc arc = new Arc(this, w); // erzeuge neue Kante von v (dieser Knoten) nach w.
        arcsOut.add(arc); // füge die Kante der Adjazenzliste der von v ausgehenden inzidenten Kanten hinzu
        w.arcsIn.add(new Arc(this, w)); // füge die Kante der Liste zu w eingehender inzidenter Kanten hinzu

        degree++; // erhöhe den Grad von v.
        w.degree++; // erhöhe den Grad von w.
    }


}
