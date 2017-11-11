package br.com.trabalhoConjuntos.model;

import java.util.HashSet;
import java.util.Set;

public class ConjuntoHandler {

	private Set<String> conjuntoA;
	private Set<String> conjuntoB;

	public ConjuntoHandler(Set<String> conjuntoA, Set<String> conjuntoB) {
		this.conjuntoA = conjuntoA;
		this.conjuntoB = conjuntoB;
	}

	public int contaElementos(Set<String> conjunto) {
		return conjunto.size();
	}

	public NoConjunto uniao() {
		HashSet<String> uniaoAB = new HashSet<String>(conjuntoA);
		uniaoAB.addAll(conjuntoB);

		return new NoConjunto(uniaoAB, uniaoAB.size());
	}

	public NoConjunto intercecao() {
		HashSet<String> intercecaoAB = new HashSet<String>(conjuntoA);
		intercecaoAB.removeIf(s -> !conjuntoB.contains(s));

		return new NoConjunto(intercecaoAB, intercecaoAB.size());
	}

	public NoConjunto aMenosB() {
		return subtracaoConjuntos(conjuntoA);
	}

	public NoConjunto bMenosA() {
		return subtracaoConjuntos(conjuntoB);
	}

	private NoConjunto subtracaoConjuntos(Set<String> conjunto) {
		Set<String> intercecaoAB = intercecao().getConjunto();
		HashSet<String> resutante = new HashSet<String>(conjunto);
		resutante.removeAll(intercecaoAB);

		return new NoConjunto(resutante, resutante.size());
	}

}
