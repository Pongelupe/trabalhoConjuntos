package br.com.conjuntos.model;

import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

public class NoConjunto {
	private final Set<String> conjunto;
	private final int elementos;

	public Set<String> getConjunto() {
		return conjunto;
	}

	public int getElementos() {
		return elementos;
	}

	public NoConjunto(Set<String> conjunto, int elementos) {
		this.conjunto = conjunto;
		this.elementos = elementos;
	}

	@Override
	public String toString() {
		return "NoConjunto [conjunto=" + conjunto + ", elementos=" + elementos + "]";
	}

	public JSONObject toJson() throws JSONException {
		JSONObject json = new JSONObject();
		json.put("elementos", elementos);
		json.put("conjunto", conjunto);

		return json;
	}

}
