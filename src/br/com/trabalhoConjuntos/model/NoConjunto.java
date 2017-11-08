package br.com.trabalhoConjuntos.model;

import java.util.Set;

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

}
