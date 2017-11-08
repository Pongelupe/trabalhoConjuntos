package br.com.trabalhoConjuntos.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConjuntoHandler {

	private Set<String> conjuntoA;
	private Set<String> conjuntoB;

	public ConjuntoHandler(Set<String> conjuntoA, Set<String> conjuntoB) {
		this.conjuntoA = conjuntoA;
		this.conjuntoB = conjuntoB;
	}

	public int contaElementos(List<String> conjunto) {
		return conjunto.size();
	}

	public NoConjunto intercecao() {
		HashSet<String> intercecaoAB = new HashSet<String>(conjuntoA);
		intercecaoAB.addAll(conjuntoB);

		return new NoConjunto(intercecaoAB, intercecaoAB.size());
	}

}
