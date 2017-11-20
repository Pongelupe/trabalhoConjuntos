package br.com.conjuntos.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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

	public ConjuntoHandler(JSONObject json) throws JSONException {

		this.conjuntoA = getConunto(json.getString("conjuntoA"));
		this.conjuntoB = getConunto(json.getString("conjuntoB"));
	}

	private HashSet<String> getConunto(String string) {
		return new HashSet<String>(Arrays.asList(string.split(",")));
	}

	public int contaElementos(Set<String> conjunto) {
		return conjunto.size();
	}

	public NoConjunto uniao() {
		HashSet<String> uniaoAB = new HashSet<String>(conjuntoA);

		return uniao(uniaoAB, conjuntoB);
	}

	private NoConjunto uniao(Set<String> conjuntoA, Set<String> conjuntoB) {
		conjuntoA.addAll(conjuntoB);
		return new NoConjunto(conjuntoA, conjuntoA.size());
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

	public NoConjunto diferencaSimetrica() {
		NoConjunto aMenosB = aMenosB();
		NoConjunto bMenosA = bMenosA();

		return uniao(aMenosB.getConjunto(), bMenosA.getConjunto());
	}

	public NoConjunto conjuntoDasPartes(Set<String> conjunto) {
		TreeSet<String> conjuntoPartes = new TreeSet<String>(Comparator.comparing(String::length));

		final int tamanho = conjunto.size();

		for (int i = 0; i < (1 << tamanho); i++) {
			StringBuilder sb = new StringBuilder("{");

			for (int j = 0; j < tamanho; j++)
				if ((i & (1 << j)) > 0)
					sb.append(conjunto.toArray()[j] + ",");

			sb.append("}");
			conjuntoPartes.add(sb.toString().replace(",}", "}"));
		}

		return new NoConjunto(conjuntoPartes, conjuntoPartes.size());
	}

	private NoConjunto complemento(Set<String> conjunto) {
		Set<String> universo = criaUniverso();
		universo.removeAll(conjunto);
		return new NoConjunto(universo, universo.size());
	}

	private Set<String> criaUniverso() {
		String alfabeto = "abcdefghijklmnopqrstuvxwyz";
		HashSet<String> set = new HashSet<String>(Arrays.asList(alfabeto.split("")));
		set.addAll(Arrays.asList(alfabeto.toUpperCase().split("")));

		for (int i = 0; i < 200; i++)
			set.add((i - 100) + "");

		return set;
	}

	private NoConjunto complementoA() {
		return complemento(conjuntoA);
	}

	private NoConjunto complementoB() {
		return complemento(conjuntoB);
	}

	private NoConjunto conjuntoDasPartesA() {
		return conjuntoDasPartes(conjuntoA);
	}

	private NoConjunto conjuntoDasPartesB() {
		return conjuntoDasPartes(conjuntoB);
	}

	private NoConjunto subtracaoConjuntos(Set<String> conjunto) {
		Set<String> intercecaoAB = intercecao().getConjunto();
		HashSet<String> resutante = new HashSet<String>(conjunto);
		resutante.removeAll(intercecaoAB);

		return new NoConjunto(resutante, resutante.size());
	}

	private NoConjunto produtoCartesiano() {
		HashSet<String> produtoCartesiano = new HashSet<String>();

		conjuntoA.forEach(x -> {
			String produto = "(" + x + ",y)";
			conjuntoB.forEach(y -> {
				produtoCartesiano.add(produto.replace("y", y));
			});
		});

		return new NoConjunto(produtoCartesiano, produtoCartesiano.size());
	}

	public void execute() {
		operacoesRealizadas = new HashMap<String, NoConjunto>();

		operacoesRealizadas.put(Operations.UNIAO.name(), uniao());
		operacoesRealizadas.put(Operations.INTERCECAO.name(), intercecao());
		operacoesRealizadas.put(Operations.AMENOSB.name(), aMenosB());
		operacoesRealizadas.put(Operations.BMENOSA.name(), bMenosA());
		operacoesRealizadas.put(Operations.PRODUTO_CATESIANO.name(), produtoCartesiano());
		operacoesRealizadas.put(Operations.CONJUNTOS_DAS_PARTES_A.name(), conjuntoDasPartesA());
		operacoesRealizadas.put(Operations.CONJUNTOS_DAS_PARTES_B.name(), conjuntoDasPartesB());
		operacoesRealizadas.put(Operations.DIFERENCA_SIMETRICA.name(), diferencaSimetrica());
		operacoesRealizadas.put(Operations.COMPLEMENTO_A.name(), complementoA());
		operacoesRealizadas.put(Operations.COMPLEMENTO_B.name(), complementoB());

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
		UNIAO, INTERCECAO, AMENOSB, BMENOSA, COMPLEMENTO_A, COMPLEMENTO_B, PRODUTO_CATESIANO, CONJUNTOS_DAS_PARTES_A, CONJUNTOS_DAS_PARTES_B, DIFERENCA_SIMETRICA;
	}

}