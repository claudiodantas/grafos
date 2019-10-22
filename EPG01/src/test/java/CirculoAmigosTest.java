import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.alg.util.Pair;
import org.junit.Test;

public class CirculoAmigosTest {

	@Test
	public void test1() {
		ArrayList<Pair<String,String>> listaAmigos = new ArrayList <Pair<String,String>> ();
		listaAmigos.add(new Pair<String, String>("a","b"));
		listaAmigos.add(new Pair<String, String>("c","d"));
		listaAmigos.add(new Pair<String, String>("e","f"));
		listaAmigos.add(new Pair<String, String>("f","g"));
		listaAmigos.add(new Pair<String, String>("i","j"));

		// Circulos que devem ser encontrados
		Set <String> c1 = new HashSet<String> (); c1.add("a"); c1.add("b");
		Set <String> c2 = new HashSet<String> (); c2.add("c"); c2.add("d");
		Set <String> c3 = new HashSet<String> (); c3.add("e"); c3.add("f"); c3.add("g");
		Set <String> c4 = new HashSet<String> (); c4.add("i"); c4.add("j");

		// Calculando círculos
		List <Set<String>> circuloAmigos = CirculoAmigos.retornaCirculos(listaAmigos);

		assertTrue(circuloAmigos.contains(c1));
		assertTrue(circuloAmigos.contains(c2));
		assertTrue(circuloAmigos.contains(c3));
		assertTrue(circuloAmigos.contains(c4));
		assertEquals(circuloAmigos.size(),4);
	}

	@Test
	public void test2 () {
		ArrayList<Pair<String,String>> listaAmigos = new ArrayList <Pair<String,String>> ();
		listaAmigos.add(new Pair<String, String>("a","b"));
		listaAmigos.add(new Pair<String, String>("c","b"));
		listaAmigos.add(new Pair<String, String>("e","a"));
		listaAmigos.add(new Pair<String, String>("f","b"));
		listaAmigos.add(new Pair<String, String>("f","a"));

		// Circulos que devem ser encontrados
		Set <String> c1 = new HashSet<String> (); c1.add("a"); c1.add("b"); c1.add("c"); c1.add("e"); c1.add("f");

		// Calculando círculos
		List <Set<String>> circuloAmigos = CirculoAmigos.retornaCirculos(listaAmigos);

		assertTrue(circuloAmigos.contains(c1));
		assertEquals(circuloAmigos.size(),1);
	}

	@Test
	public void test3 () {
		ArrayList<Pair<String,String>> listaAmigos = new ArrayList <Pair<String,String>> ();

		//Calculando círculos
		List <Set<String>> circuloAmigos = CirculoAmigos.retornaCirculos(listaAmigos);

		assertEquals(circuloAmigos.size(),0);
	}


	//---------------Testes do grupo--------------------

	//Três círculos de amigos com tamanhos diferentes
	@Test
	public void test4(){
		ArrayList<Pair<String,String>> listaAmigos = new ArrayList <Pair<String,String>> ();
		listaAmigos.add(new Pair<String, String>("m","g"));
		listaAmigos.add(new Pair<String, String>("g","h"));
		listaAmigos.add(new Pair<String, String>("h","i"));
		listaAmigos.add(new Pair<String, String>("a","b"));
		listaAmigos.add(new Pair<String, String>("z","w"));
		listaAmigos.add(new Pair<String, String>("x","z"));

		//Circulos que devem ser encontrados
		Set<String> c1 = new HashSet<String>(); c1.add("m"); c1.add("g"); c1.add("h"); c1.add("i");
		Set<String> c2 = new HashSet<String>();  c2.add("a"); c2.add("b");
		Set<String> c3 = new HashSet<String>(); c3.add("z"); c3.add("w"); c3.add("x");

		//Calculando círculos
		List <Set<String>> circuloAmigos = CirculoAmigos.retornaCirculos(listaAmigos);

		assertTrue(circuloAmigos.contains(c1));
		assertTrue(circuloAmigos.contains(c2));
		assertTrue(circuloAmigos.contains(c3));

		assertEquals(circuloAmigos.size(),3);
	}

	//Círculo único de amigos
	@Test
	public void test5(){
		ArrayList<Pair<String,String>> listaAmigos = new ArrayList <Pair<String,String>> ();
		listaAmigos.add(new Pair<String, String>("a","c"));
		listaAmigos.add(new Pair<String, String>("c","j"));
		listaAmigos.add(new Pair<String, String>("c","d"));
		listaAmigos.add(new Pair<String, String>("d","n"));
		listaAmigos.add(new Pair<String, String>("n","o"));

		//Circulo que deve ser encontrado
		Set<String> c1 = new HashSet<String>(); c1.add("a"); c1.add("c"); c1.add("j"); c1.add("d"); c1.add("n"); c1.add("o");

		//Calculando círculo
		List <Set<String>> circuloAmigos = CirculoAmigos.retornaCirculos(listaAmigos);

		assertTrue(circuloAmigos.contains(c1));
		assertEquals(circuloAmigos.size(), 1);
	}

	//Circulos com tamanhos iguais
	@Test
	public void test6(){
		ArrayList<Pair<String,String>> listaAmigos = new ArrayList <Pair<String,String>> ();
		listaAmigos.add(new Pair<String, String>("a","b"));
		listaAmigos.add(new Pair<String, String>("c","d"));
		listaAmigos.add(new Pair<String, String>("e","f"));

		//Circulos que devem ser encontrado
		Set<String> c1 = new HashSet<String>(); c1.add("a"); c1.add("b");
		Set<String> c2 = new HashSet<String>(); c2.add("c"); c2.add("d");
		Set<String> c3 = new HashSet<String>(); c3.add("e"); c3.add("f");

		//Calculando círculos
		List <Set<String>> circuloAmigos = CirculoAmigos.retornaCirculos(listaAmigos);

		assertTrue(circuloAmigos.contains(c1));
		assertTrue(circuloAmigos.contains(c2));
		assertTrue(circuloAmigos.contains(c3));
		assertEquals(circuloAmigos.size(), 3);
	}

	//EXTRA
	//Grafo com componentes triviais
	@Test
	public void test7(){
		ArrayList<Pair<String,String>> listaAmigos = new ArrayList <Pair<String,String>> ();
		listaAmigos.add(new Pair<String, String>("a",null));
		listaAmigos.add(new Pair<String, String>(null,"c"));
		listaAmigos.add(new Pair<String, String>("e",null));

		//Circulos que devem ser encontrados
		Set<String> c1 = new HashSet<String>(); c1.add("a");
		Set<String> c2 = new HashSet<String>(); c2.add("c");
		Set<String> c3 = new HashSet<String>(); c3.add("e");

		//Calculando círculos
		List <Set<String>> circuloAmigos = CirculoAmigos.retornaCirculos(listaAmigos);

		assertTrue(circuloAmigos.contains(c1));
		assertTrue(circuloAmigos.contains(c2));
		assertTrue(circuloAmigos.contains(c3));
		assertEquals(circuloAmigos.size(), 3);
	}

}

