package fes.aragon.test;

import fes.aragon.utilerias.dinamicas.pila.Pila;

public class TestPos {

	public static void main(String[] args) throws Exception {
		Pila<Integer> pila = new Pila<>();
		String cadena = "32+63-*1+";
		String aux = "";
		int resultado = 0;
		for (int i = 0; i < cadena.length(); i++) {
			aux = "";
			char c = cadena.charAt(i);
			aux += c;
			if (c != '+' && c != '-' && c != '*') {
				pila.insertar(Integer.parseInt(aux));
			} else {
				int op1 = pila.extraer();
				int op2 = pila.extraer();
				if (c == '+') {
					resultado = op2 + op1;
					pila.insertar(resultado);
				} else if (c == '-') {
					resultado = op2 - op1;
					pila.insertar(resultado);
				} else if (c == '*') {
					resultado = op2 * op1;
					pila.insertar(resultado);
				}
			}
		}
		System.out.println("El resultado es: " + resultado);
	}

}
