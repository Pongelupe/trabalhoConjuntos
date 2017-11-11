package br.com.trabalhoConjuntos.main;

import java.util.HashSet;

import br.com.trabalhoConjuntos.model.ConjuntoHandler;
import br.com.trabalhoConjuntos.model.NoConjunto;

public class Main {

	public static void main(String[] args) {
		HashSet<String> conjuntoA = new HashSet<String>();
		HashSet<String> conjuntoB = new HashSet<String>();

		conjuntoA.add("2");
		conjuntoA.add("3");
		conjuntoA.add("4");
		conjuntoA.add("1");

		conjuntoB.add("8");
		conjuntoB.add("94");
		conjuntoB.add("4");
		conjuntoB.add("2");

		ConjuntoHandler handle = new ConjuntoHandler(conjuntoA, conjuntoB);
		System.out.println("A: " + new NoConjunto(conjuntoA, conjuntoA.size()));
		System.out.println("B: " + new NoConjunto(conjuntoB, conjuntoB.size()));
		System.out.println("União: " + handle.uniao());
		System.out.println("Interceção: " + handle.intercecao());
		System.out.println("A - B: " + handle.aMenosB());
		System.out.println("B - A: " + handle.bMenosA());
	}

}
