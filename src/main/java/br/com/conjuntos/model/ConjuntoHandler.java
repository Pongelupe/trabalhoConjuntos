package br.com.conjuntos.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

public class ConjuntoHandler {

	private Set<String> conjuntoA;
	private Set<String> conjuntoB;

	private Map<String, NoConjunto> operacoesRealizadas;

	public ConjuntoHandler(Set<String> conjuntoA, Set<String> conjuntoB) {
		this.conjuntoA = conjuntoA;
		this.conjuntoB = conjuntoB;
	}

	public ConjuntoHandler(JSONObject json) {
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

	public void execute() {
		operacoesRealizadas = new HashMap<String, NoConjunto>();

		operacoesRealizadas.put(Operations.UNIAO.name(), uniao());
		operacoesRealizadas.put(Operations.INTERCECAO.name(), intercecao());
		operacoesRealizadas.put(Operations.AMENOSB.name(), aMenosB());
		operacoesRealizadas.put(Operations.BMENOSA.name(), bMenosA());
	}

	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		operacoesRealizadas.forEach((k, v) -> {
			try {
				json.put(k, v.toJson());
			} catch (JSONException e) {
				e.printStackTrace();
			}
		});
		return json;
	}

	enum Operations {
		UNIAO, INTERCECAO, AMENOSB, BMENOSA;
	}

}