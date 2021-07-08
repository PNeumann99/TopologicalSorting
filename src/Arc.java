/**
 * Eine einfache Implementierung von Kanten eines einfachen gerichteten Graphen.
 */
public class Arc {
    private final Vertex v; // Startknoten der Kante
    private final Vertex w; // Endknoten der Kante

    /**
     * Erzeugt eine neue Kante.
     *
     * @param v der Startknoten der Kante.
     * @param w der Endknoten der Kante.
     */
    public Arc(Vertex v, Vertex w) {
        this.v = v;
        this.w = w;
    }

    /**
     * Gibt den Startknoten der Kante zurück.
     *
     * @return den Startknoten.
     */
    public Vertex getV() {
        return this.v;
    }

    /**
     * Gibt den Endknoten der Kante zurück.
     *
     * @return den Endknoten.
     */
    public Vertex getW() {
        return this.w;
    }
}
