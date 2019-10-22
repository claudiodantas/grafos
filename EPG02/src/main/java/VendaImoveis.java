package main.java;

import java.util.Set;
import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

/**
 * Classe que possui o método responsável por retornar o imóvel mais próximo de um determinado ponto de interesse.
 * A proximidade entre os imóveis é calculada através do tamanho dos caminhos de um grafo simples ponderado, utilizando a
 * classe SimpleWeightedGraph (para criar o grafo) e DijkstraShortestPath (para retornar o vértice com menor caminho até
 * o ponto de interesse).
 *
 * @author Daniel Gomes de Lima - 118210357
 * @author Francicláudio Dantas da Silva - 118210343
 * @author Gustavo Farias de Souza Silva - 118210480
 */
public class VendaImoveis {

	/**
	 * Variável que representa o grafo simples ponderado.
	 */
	Graph<String,DefaultWeightedEdge> distrito;

	/**
	 * Inicializa o grafo a partir de um arquivo .csv recebido como parâmetro.
	 *
	 * @param fileName o caminho do arquivo .csv
	 */
	public VendaImoveis(String fileName) {
		this.distrito = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		this.distrito = MyJGraphTUtil.importWeightedGraphCSV(this.distrito, fileName);
	}

	/**
	 * Retorna o imovel (contido nos imoveis disponiveis) mais proximo ao ponto de interesse recebido como parametro
	 * utilizando a classe DijkstraShortestPath.
	 *
	 * @param pontodeInteresse o ponto de interesse do comprador.
	 * @param imoveis os imóveis disponíveis para a venda.
	 *
	 * @return o imóvel com menor caminho até o ponto de interesse.
	 */
	public String localizaImovel (String pontodeInteresse, Set<String> imoveis) {
		DijkstraShortestPath<String, DefaultWeightedEdge> dijkstraShortestPath = new DijkstraShortestPath<>(this.distrito);
		String result = null;

		if(this.distrito.containsVertex(pontodeInteresse)) {
			if (imoveis.size() > 0) {
				double menor = 0.0;
				boolean flag = true;

				for (String casas : imoveis) {
					if (flag) {
						menor = dijkstraShortestPath.getPath(pontodeInteresse, casas).getWeight();
						result = dijkstraShortestPath.getPath(pontodeInteresse, casas).getEndVertex();
						flag = false;

					} else if (dijkstraShortestPath.getPath(pontodeInteresse, casas).getWeight() < menor) {
						menor = dijkstraShortestPath.getPath(pontodeInteresse, casas).getWeight();
						result = dijkstraShortestPath.getPath(pontodeInteresse, casas).getEndVertex();
					}
				}
			}
		}

		return result;
	}
}
