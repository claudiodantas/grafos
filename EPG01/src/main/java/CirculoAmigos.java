import java.util.List;
import java.util.Set;

import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.util.Pair;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

/**
 * Classe que possui o método responsável por retornar círculos de amigos a partir de uma representação feita
 * em grafos utilizando classes da biblioteca JGrapht. Cada círculo representa um componente de um grafo.
 *
 * @author Daniel Gomes de Lima - 118210357
 * @author Francicláudio Dantas da Silva - 118210343
 * @author Gustavo Farias de Souza Silva - 118210480
 */
public class CirculoAmigos {

	/**
	 * Constrói um grafo, com a classe SimpleGraph, a partir do primeiro e do segundo valor dos pares contidos
	 * na lista de pares recebida como parâmetro. Cada valor (diferente de null) é convertido para um vértice
	 * do grafo e é conectado com o valor correspondente ao par através da inserção de uma aresta (exceto para
	 * vértices com grau 0). Em seguida, utilizando a classe ConnectivityInspector, é retornada uma lista de
	 * conjuntos, onde cada conjunto representa vértices que estão conectados entre si.
	 *
	 * @param paresAmigos a lista contendo os pares de valores que serão relacionados.
	 * @return uma lista de conjuntos de vértices que representa os círculos de amigos.
	 */
	public static List <Set <String>> retornaCirculos (List<Pair<String,String>> paresAmigos) {

		SimpleGraph<String, DefaultEdge> grafo = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);

		//Adicionando os vértices ao grafo e relacionando-os através de arestas (ou não, no caso de vértices com grau 0)
		for (int i = 0; i < paresAmigos.size(); i++) {
			if (paresAmigos.get(i).getFirst() == null){
				if (!(paresAmigos.get(i).getSecond() == null))
					grafo.addVertex(paresAmigos.get(i).getSecond());
			} else {
				if (!(paresAmigos.get(i).getSecond() == null)){
					grafo.addVertex(paresAmigos.get(i).getFirst());
					grafo.addVertex(paresAmigos.get(i).getSecond());
					grafo.addEdge(paresAmigos.get(i).getFirst(), paresAmigos.get(i).getSecond());
				} else {
					grafo.addVertex(paresAmigos.get(i).getFirst());
				}
			}
		}

		ConnectivityInspector<String, String> conIns = new ConnectivityInspector(grafo);
		return conIns.connectedSets();
	}

}