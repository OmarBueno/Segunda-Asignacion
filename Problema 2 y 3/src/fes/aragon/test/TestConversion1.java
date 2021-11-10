package fes.aragon.test;

import fes.aragon.utilerias.dinamicas.pila.Pila;

public class TestConversion1 {

	public static void main(String[] args) throws Exception {
		Pila<Character> pila = new Pila<>();
		String cadena = "(5/3/(4+2))";
		String posfija = "";
		for (int i = 0; i < cadena.length(); i++) {
			char c = cadena.charAt(i);
			System.out.println("c comun: " + c);
			if (c >= '0' && c <= '9') {
				posfija += c;
				posfija+=' ';
				System.out.println("c del if " + c);
			} else {
				if (!pila.estaVacia()) {
					System.out.println("elemento superior:" + pila.elementoSuperior());
					System.out.println("c del else " + c);
				}
				while (!pila.estaVacia() && precedencia(pila.elementoSuperior(), c)) {
					System.out.println("Entro al while");
					posfija += pila.extraer();
					posfija+=' ';
				}
				if (pila.estaVacia() || c != ')') {
					pila.insertar(c);
				} else {
					pila.extraer();
				}
			}
		}
		while (!pila.estaVacia()) {
			posfija += pila.extraer();
			posfija+=' ';
		}
		System.out.println("Salida: " + posfija);

	}

	public static boolean precedencia(char op1, char op2) {
		if (op2 == '(' && op1 == ')') {
			return true;
		} else if (op1 == '(' && op2 == ')') {
			return false;
		} else if (op2 == ')') {
			return true;
		} else if (op1 == '(') {
			return false;
		} else if (op2 == '(') {
			return false;
		} else if (op1 == '/') {
			return true;
		} else if (op1 == '*') {
			return true;
		} else if (op1 == '+' && op2 == '*') {
			return false;
		} else if (op1 == '+' && op2 == '/') {
			return false;
		} else if (op1 == '+' && op2 == '+') {
			return true;
		} else if (op1 == '+' && op2 == '-') {
			return false;
		} else if (op1 == '-' && op2 == '*') {
			return false;
		} else if (op1 == '-' && op2 == '/') {
			return false;
		} else if (op1 == '-' && op2 == '+') {
			return false;
		} else if (op1 == '-' && op2 == '-') {
			return true;
		} else {
			return false;
		}
	}

}
