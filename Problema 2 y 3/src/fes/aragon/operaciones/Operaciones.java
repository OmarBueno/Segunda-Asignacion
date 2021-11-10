package fes.aragon.operaciones;

import javax.swing.JOptionPane;

import fes.aragon.utilerias.dinamicas.pila.Pila;

public class Operaciones {

	public static double evaluarPosfija(String expresion) {
		Pila<Double> pila = new Pila<>();
		String cadena = expresion;
		String tmp = "";
		try {
			double resultado = 0.0;
			for (int i = 0; i < cadena.length(); i++) {
				char c = cadena.charAt(i);
				if (c != '+' && c != '-' && c != '*' && c != '/' && c != ' ') {
					char d = cadena.charAt(i + 1);
					tmp = "";
					tmp += c;
					while (d != '+' && d != '-' && d != '*' && c != '/' && d != ' ') {
						tmp += d;
						i++;
						d = cadena.charAt(i + 1);
					}
					pila.insertar(Double.parseDouble(tmp));
				} else if (c == ' ') {
				} else {
					double op1 = pila.extraer();
					double op2 = pila.extraer();
					if (c == '+') {
						resultado = op2 + op1;
						pila.insertar(resultado);
					} else if (c == '-') {
						resultado = op2 - op1;
						pila.insertar(resultado);
					} else if (c == '*') {
						resultado = op2 * op1;
						pila.insertar(resultado);
					} else if (c == '/') {
						resultado = op2 / op1;
						pila.insertar(resultado);
					}
					i++;
				}
			}
			return resultado;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Error en la entrada, debe ingresar al menos dos numero y un operando \n "
							+ "separados por un espacio",
					"Error", JOptionPane.ERROR_MESSAGE);
			return 0;
		}
	}

	public static String interPosfija(String expresion) throws Exception {
		Pila<Character> pila = new Pila<>();
		String cadena = expresion;
		String posfija = "";
		for (int i = 0; i < cadena.length(); i++) {
			char c = cadena.charAt(i);
			if (c >= '0' && c <= '9') {
				posfija += c;
				if (i == cadena.length() - 1) {
					break;
				}
				char d = cadena.charAt(i + 1);
				while (d >= '0' && d <= '9') {
					posfija += d;
					i++;
					if (i == cadena.length() - 1) {
						break;
					}
					d = cadena.charAt(i + 1);
				}
				posfija += ' ';
			} else {
				while (!pila.estaVacia() && precedencia(pila.elementoSuperior(), c)) {
					posfija += pila.extraer();
					posfija += ' ';
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
			posfija += ' ';
		}
		return posfija;
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
