import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * Eine einfache Implementierung zur Bestimmung einer topologischen Sortierung eines gerichteten azyklischen Graphen.
 */
public class TopologicalOrder {
    private final List<Integer> topologicalOrder; // die topologische Sortierung

    /**
     * Berechnet eine topologische Sortierung eines gerichteten Graphen.
     *
     * @param G der gerichtete Graph.
     */
    public TopologicalOrder(DirectedGraph G) {
        this.topologicalOrder = new LinkedList<>(topologicalSort(G));
    }

    /**
     * Gibt an, ob ein Graph eine topologische Sortierung besitzt.
     *
     * @return true, wenn der Graph eine topologische Sortierung besitzt.
     */
    public boolean hasTopologicalOrder() {
        return (!topologicalOrder.isEmpty());
    }

    /**
     * Gibt die topologische Sortierung des Graphen an.
     *
     * @return die topologische Sortierung des Graphen in Form einer verketteten Liste.
     */
    public List<Integer> getTopologicalOrder() {
        return topologicalOrder;
    }

    /**
     * Berechnet die topologische Sortierung eines einfachen azyklisch gerichteten Graphen.
     * Startet bei Knoten 1 und führt eine Depth-First-Search durch und fügt den Knoten jeweils die Zeitstempel
     * DiscoveryTime d(v) und FinishingTime f(v) hinzu. In dem Moment, wo die FinishingTime eines Knoten gesetzt wird,
     * wird er der Liste mit der topologischen Sortierung hinzugefügt.
     *
     * @param G der Graph, dessen topologische Sortierung durchgeführt werden soll.
     * @return die topologische Sortierung des Graphen in Form einer Liste.
     */
    private List<Integer> topologicalSort(DirectedGraph G) {
        int time = 0; // initialisiere den Zeitzähler mit 0.
        int s = 1; // starte die DFS bei Knoten 1.

        List<Integer> output = new LinkedList<>(); // erzeuge eine neue Liste für die topologische Sortierung (den output).

        Stack<Integer> Q = new Stack<>();
        Q.push(s);
        G.getVertices().get(s - 1).setDiscoveryTime(++time); //Startknoten mit d(v) = 1 versehen

        while (!Q.isEmpty()) {
            time++; // erhöhe den Zeitzähler.
            int v = Q.peek();
            boolean found = false; // Hilfsvariable: gibt später an, ob ein zu v adjazenter Knoten gefunden wurde, welcher noch nicht besucht wurde.

            for (int i = 0; i < G.getAdjacentVerticesOut(v).size(); i++) { //Adjazenzliste von v durchsuchen
                if (!G.getVertices().get(G.getAdjacentVerticesOut(v).get(i) - 1).isDiscovered()) { // falls adjazenter Knoten gefunden wurde, der noch nicht besucht wurde
                    G.getVertices().get(G.getAdjacentVerticesOut(v).get(i) - 1).setDiscoveryTime(time); // setze seine d(v)
                    Q.push(G.getAdjacentVerticesOut(v).get(i)); // füge ihn dem Stack hinzu.
                    found = true; // es wurde ein solcher Knoten gefunden.
                    break; // beende die Suche
                }
            }
            if (!found) { // falls kein solcher Knoten gefunden wurde
                G.getVertices().get(v - 1).setFinishingTime(time); // setze f(v)
                output.add(0, Q.pop()); // füge v der topologischen Sortierung hinzu
            }
        }
        return output; // die topologische Sortierung zurück geben
    }

    /**
     * Ein Programm, welches einen azyklischen gerichteten Graphen aus einer Datei einliest und eine
     * topologische Sortierung dieses Graphen bestimmt und anschließend auf dem Bildschirm ausgibt.
     * Das Programm liest die Datei zunächst ein, erstellt daraus eine Adjazenzlisten-Repräsentation des Graphen und
     * bestimmt dann die topologische Sortierung dessen.
     *
     * @param args der Dateipfad zur einzulesenen Datei.
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File(args[0])); // erzeuge neuen Scanner um die Datei zeilenweise einzulesen

        String line;
        line = in.nextLine();

        while (line.charAt(0) != 'p') { // gehe zeilenweise durch die Datei, bis die Zeile gefunden wurde, welche die Anzahl er Knoten des Graphen beschreibt.
            line = in.nextLine();
        }

        int[] p = translateCommand(line); // parse die Parameter für den Graphen.
        DirectedGraph G = new DirectedGraph(p[0]); // erstelle einen neuen gerichteten Graphen nach den Parametern.

        while (in.hasNextLine()) { // lese die Datei zeilenweise weiter ein, um die Kanten hinzuzufügen.
            line = in.nextLine();
            if (line.charAt(0) == 'c') continue; // überspringe Kommentare
            int[] a = translateCommand(line); // parse die Parameter (v,w) für die neue Kante.
            G.addArc(a[0], a[1]); // füge die neue Kante dem Graphen hinzu.
        }

        TopologicalOrder order = new TopologicalOrder(G); // bestimme die topologische Sortierung des Graphen.
        System.out.println(order); // gebe die Sortierung auf dem Bildschirm aus.
    }

    /**
     * Hilfsmethode zum Parsen der zwei benötigten Zahlen aus den Zeilen der Datei.
     *
     * @param l die Zeile als String.
     * @return ein integer Array der Länge 2 mit den beiden geparsten Zahlen.
     */
    private static int[] translateCommand(String l) {
        int[] output = new int[2]; // erzeuge neues Feld für den Output der zwei Zahlen
        int startIndex;
        if (l.charAt(0) == 'p')
            startIndex = 5; // falls die zu übersetzende Zeile einen Graphen beschreibt, überspringe "p to "
        else startIndex = 2; // falls sie eine Kante beschreibt, überspringe "a "

        l = l.substring(startIndex); // nur wichtigen Teil des Strings berücksichtigen, also "p to" bzw "a " "wegschneiden"
        String[] numbers = l.split(" "); // Zahlen sind jetzt als zwei Strings in array.
        output[0] = Integer.parseInt(numbers[0]); // integer aus erstem String parsen
        output[1] = Integer.parseInt(numbers[1]); // integer aus zweitem String parsen

        return output;
    }


    /**
     * Hilfsmethode um die topologische Sortierung im Format 1,2,5,3,4 ausgeben zu können.
     *
     * @return die topologische Sortierung als String
     */
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i : topologicalOrder) {
            if (i != topologicalOrder.size() - 1) output.append(i).append(",");
            else output.append(i);
        }
        return output.toString();
    }
}
