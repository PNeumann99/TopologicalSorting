import java.util.LinkedList;
import java.util.List;

/**
 * Eine einfache Implementierung von Adjazenzlisten-Repräsentationen einfach gerichteter Graphen.
 *
 * @author akubf
 */

public class DirectedGraph {
    private final int vertexCount; // die Anzahl der Knoten. Ist schneller als vertices.size().
    private final List<Vertex> vertices; // die Liste der Knoten des Graphen.

    /**
     * Erzeugt einen neuen gerichteten Graphen mit n isolierten Knoten V = {1,2,...,n}.
     *
     * @param n die Anzahl der Knoten des neuen Graphen.
     */
    public DirectedGraph(int n) {
        vertices = new LinkedList<>();
        vertexCount = n;
        for (int i = 0; i < n; i++) {
            vertices.add(new Vertex());
        }
    }

    /**
     * Eine Methode welche die Anzahl der Knoten des Graphen bestimmt.
     *
     * @return die Anzahl der Graphen.
     */
    public int getVertexCount() {
        return this.vertexCount;
    }

    /**
     * Getter Methode für die Liste der Knoten des Graphen.
     *
     * @return die Liste der Knoten des Graphen.
     */
    public List<Vertex> getVertices() {
        return vertices;
    }

    /**
     * Fügt dem Graphen eine neue Kante (v,w) hinzu.
     *
     * @param v der Startknoten der neuen Kante.
     * @param w der Endknoten der neuen Kante.
     */
    public void addArc(int v, int w) {
        vertices.get(v - 1).addArc(vertices.get(w - 1));
    }

    /**
     * Bestimmt die Menge {w: (v,w) aus E} der zu v adjazenten Knoten.
     *
     * @param v der Index bzw. Name des Knotens, dessen adjazente Knoten bestimmt werden sollen.
     * @return eine Liste mit den Indizes bzw. Namen aller zu v adjazenten Knoten.
     */
    public List<Integer> getAdjacentVertices(int v) {
        List<Integer> output = new LinkedList<>();

        output.addAll(getAdjacentVerticesOut(v));
        output.addAll(getAdjacentVerticesIn(v));

        return output;
    }

    /**
     * Bestimmt die Menge der zu v adjazenten Knoten, welche die Endknoten der von v ausgehenden inzidenten Kanten sind.
     *
     * @param v der Name bzw. der Index des Knoten v.
     * @return eine Liste mit den Indizes bzw. Namen aller dieser Knoten.
     */
    public List<Integer> getAdjacentVerticesOut(int v) {
        List<Integer> output = new LinkedList<>();

        // iteriere über die Liste der von ihm ausgehenden inzidenten Kanten.
        for (Arc a : vertices.get(v - 1).getArcsOut()) {
            output.add(vertices.indexOf(a.getW()) + 1); // bestimme jeweils w der Kante und füge ihn dem output hinzu.
        }
        return output;
    }

    /**
     * Bestimmt die Menge der zu v adjazenten Knoten, welche die Startknoten der in v eingehenden inzidenten Kanten sind.
     *
     * @param v der Name bzw. der Index des Knoten v.
     * @return eine Liste mit den Indizes bzw. Namen aller dieser Knoten.
     */
    public List<Integer> getAdjacentVerticesIn(int v) {
        List<Integer> output = new LinkedList<>();

        // iteriere über die Liste der zu ihm eingehenden inzidenten Kanten.
        for (Arc b : vertices.get(v - 1).getArcsIn()) {
            output.add(vertices.indexOf(b.getV()) + 1); // bestimme jeweils v der Kante und füge ihn dem output hinzu
        }
        return output;
    }
}
