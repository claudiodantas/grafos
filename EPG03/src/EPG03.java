import org.jgrapht.Graph;
import org.jgrapht.alg.scoring.BetweennessCentrality;
import org.jgrapht.alg.scoring.ClosenessCentrality;
import org.jgrapht.alg.scoring.ClusteringCoefficient;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.io.EdgeProvider;
import org.jgrapht.io.GmlImporter;
import org.jgrapht.io.ImportException;
import org.jgrapht.io.VertexProvider;

import java.io.*;
import java.util.*;

/**
 * Classe que possui os métodos responsáveis por identificar as formigas mais influentes e aquelas que melhor atuaram
 * na transmissão de informações umas para as outras. Além disso também tem um método que identifica se há semelhança
 * no padrão de comunicação das formigas e se é possível observar a formação de grupos isolados de formigas que se
 * comunicam entre si. Todos esses métodos são desenvolvidos a partir de um grafo ponderado gerado por um arquivo .gml.
 *
 * @author Daniel Gomes de Lima - 118210357
 * @author Francicláudio Dantas da Silva - 118210343
 * @author Gustavo Farias de Souza Silva - 118210480
 */
public class EPG03 {

    /**
     * Grafo ponderado representando formigas como vértices, o tempo de interação entre elas como o peso das arestas,
     * e seus relacionamentos.
     */
    Graph<String, DefaultWeightedEdge> formigas;

    //-------importando o grafo--------
    /**
     * Constrói um grafo ponderado a partir de um arquivo .gml.
     *
     * @param graph um grafo ponderado.
     * @param filename o caminho do arquivo a ser utilizado para gerar o grafo.
     *
     * @return Um grafo ponderado gerado a partir de um arquivo .gml.
     */
    public static Graph<String, DefaultWeightedEdge> importDefaultGraphGML (Graph<String,DefaultWeightedEdge> graph, String filename) {
        VertexProvider<String> vp1 = (label, attributes) -> label;
        EdgeProvider<String, DefaultWeightedEdge> ep1 = (from, to, label, attributes) -> new DefaultWeightedEdge();
        GmlImporter<String, DefaultWeightedEdge> gmlImporter = new GmlImporter<>(vp1, ep1);
        try {
            gmlImporter.importGraph(graph, readFile(filename));
        } catch (ImportException e) {
            throw new RuntimeException(e);
        }
        return graph;
    }

    /**
     * Faz a conversão do caminho do arquivo, passado como parâmetro, para uma StringReader, utilizando as classes
     * StringBuilder e BufferedReader.
     *
     * @param filename caminho do arquivo.
     * @return uma StringReader referente ao caminho do arquivo .gml.
     */
    static StringReader readFile(String filename) {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                contentBuilder.append(sCurrentLine).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringReader readergml = new StringReader(contentBuilder.toString());
        return readergml;
    }
    //------Fim da importação do grafo-------

    /**
     * Constrói a classe a partir do nome do caminho do arquivo passado como parâmetro. Um grafo ponderado é criado
     * através do método de importação de um arquivo .gml da classe GmlImporter.
     *
     * @param fileName nome do arquivo .gml.
     */
    public EPG03(String fileName) {
        // Importando o grafo.
        this.formigas = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
        this.formigas = importDefaultGraphGML(this.formigas, fileName);
    }

    /**
     * QUESTÃO 1: Identifica as 5 formigas que melhor atuaram na condução de informações, utilizando a classe
     * BetweennessCentrality.
     *
     * @return Um ArrayList com as 5 melhores formigas.
     */
    public ArrayList<String> bestAnts(){
        BetweennessCentrality<String, DefaultWeightedEdge> centralidade = new BetweennessCentrality(this.formigas);
        Map<String, Double> map = centralidade.getScores();
        Double[] arr = new Double[this.formigas.vertexSet().size()];
        int i  = 0;
        for (String vertice : this.formigas.vertexSet()) {
            arr[i] = centralidade.getVertexScore(vertice);
            i++;
        }

        Arrays.sort(arr);

        ArrayList<String> result = new ArrayList<>();
        ArrayList<Double> top5 = new ArrayList<>();
        for (int j = 0; j < 5 ; j++) {
            top5.add(arr[j]);
        }

        for (String vertice:map.keySet()) {
            if(top5.contains(map.get(vertice))){
                result.add(vertice);
            }
        }

        return result;
    }

    /**
     * QUESTÃO 2: Identifica as 5 formigas mais influentes do grafo, utilizando a classe ClosenessCentrality.
     *
     * @return um ArrayList com as 5 formigas mais influentes.
     */
    public ArrayList<String> influentes(){
        ClosenessCentrality<String, DefaultWeightedEdge> centralidade = new ClosenessCentrality(this.formigas);
        Map<String, Double> map = centralidade.getScores();
        Double[] arr = new Double[this.formigas.vertexSet().size()];
        int i  = 0;
        for (String vertice : this.formigas.vertexSet()) {
            arr[i] = centralidade.getVertexScore(vertice);
            i++;
        }
        Arrays.sort(arr);
        ArrayList<String> result = new ArrayList<>();
        ArrayList<Double> top5 = new ArrayList<>();
        for (int j = 0; j < 5 ; j++) {
            top5.add(arr[j]);
        }
        for (String vertice:map.keySet()) {
            if(top5.contains(map.get(vertice))){
                result.add(vertice);
            }
        }

        return result;
    }

    /**
     * QUESTÃO 3: Identifica se existem agrupamentos no grafo, utilizando a classe ClusteringCoefficient que recebe
     * o grafo de formigas como parâmetro.
     *
     * @return True, se existirem, False, se não existerem.
     */
    public boolean temAgrupamentos(){
        ClusteringCoefficient<String, DefaultWeightedEdge> agrupamentos = new ClusteringCoefficient(this.formigas);
        double coeficiente = agrupamentos.getGlobalClusteringCoefficient();
        if(coeficiente > 0){
            return true;
        }
        return false;
    }

    /**
     * QUESTÃO 4: Calcula o grau de assortatividade do grafo de formigas. utilizando a classe
     * AssortativityCoefficientMetric.
     *
     * @return Uma String informando se o grafo tem uma assortatividade perfeita, se não possui assortatividade ou se
     * o grafo é completamente não assortitativo.
     */
    public String calculaAssortatividade() {
        // from: https://github.com/Infeligo/jgrapht-metrics/blob/master/src/main/java/org/jgrapht/metrics/AssortativityCoefficientMetric.java
        double edgeCount = this.formigas.edgeSet().size();
        double n1 = 0, n2 = 0, dn = 0;

        for (DefaultWeightedEdge e : this.formigas.edgeSet()) {
            int d1 = this.formigas.degreeOf(this.formigas.getEdgeSource(e));
            int d2 = this.formigas.degreeOf(this.formigas.getEdgeTarget(e));

            n1 += d1 * d2;
            n2 += d1 + d2;
            dn += d1 * d1 + d2 * d2;
        }
        n1 /= edgeCount;
        n2 = (n2 / (2 * edgeCount)) * (n2 / (2 * edgeCount));
        dn /= (2 * edgeCount);
        double coeficiente = (n1 - n2) / (dn - n2);

        if(coeficiente == 1){
            return "O grafo possui perfeita assortatividade";
        } else if(coeficiente == 0){
            return "o grafo nao possui assortatividade";
        } else{
            return "o grafo é completamente nao assortativo";
        }
    }


    /**
     * Utilizado para mostrar os resultados dos métodos.
     */
    public static void main(String[] args){
        EPG03 grafo = new EPG03(".\\src\\sources\\antcolony1000.gml");

        System.out.println("QUESTÃO 1: " + grafo.bestAnts());
        System.out.println("QUESTÃO 2: " + grafo.influentes());
        System.out.println("QUESTÃO 3: " + grafo.temAgrupamentos());
        System.out.println("QUESTÃO 4: " + grafo.calculaAssortatividade());


    }
}
