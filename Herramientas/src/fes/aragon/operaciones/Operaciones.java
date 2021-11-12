package fes.aragon.operaciones;

import javax.swing.JOptionPane;

import fes.aragon.utilerias.dinamicas.pila.Pila;

/**
 * Clase que realiza operaciones para expresiones posfijas e interfijas
 * 
 * @author Equipo 9 Bueno Zaldivar Omar Alejandro y Sol Martinez Edith
 *
 */
public class Operaciones {
	/**
	 * metodo que evalua si una cadena esta correctamente cerrada por parentesis
	 * 
	 * @param cadena Cadena a evaluar
	 * @return True si esta bien cerrada, False si no esta bien cerrada
	 */
	public static boolean evaluarParentesis(String cadena) {
		Pila<Character> pila = new Pila<>();
		for (int i = 0; i < cadena.length(); i++) {
			char c = cadena.charAt(i);
			if (c == '[' || c == '(' || c == '{') {
				pila.insertar(c);
			}
			if (c == ']' || c == ')' || c == '}') {
				try {
					char cPila = pila.extraer();
					if (c == ']' && cPila != '[') {
						break;
					} else if (c == ')' && cPila != '(') {
						break;
					} else if (c == '}' && cPila != '{') {
						break;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if (pila.estaVacia()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Expresion que evalua una expresion posfija
	 * 
	 * @param expresion Expresion a evaluar
	 * @return resultado de la evaluacion
	 */
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

	/**
	 * Metodo que convierte una expresion interfija a una posfija
	 * 
	 * @param expresion Expresion a convertir
	 * @return Expresion posfija
	 * @throws Exception Error en el metodo
	 */
	public static String interPosfija(String expresion) throws Exception {
		Pila<Character> pila = new Pila<>();
		String cadena = expresion;
		if (evaluarParentesis(cadena)) {
			String posfija = "";
			for (int i = 0; i < cadena.length(); i++) {
				char c = cadena.charAt(i);
				if (c >= '0' && c <= '9') {
					posfija += c;
					if (i == cadena.length() - 1) {
						posfija += ' ';
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
				} else if (c >= '(' && c <= '/') {
					while (!pila.estaVacia() && precedencia(pila.elementoSuperior(), c)) {
						posfija += pila.extraer();
						posfija += ' ';
					}
					if (pila.estaVacia() || c != ')') {
						pila.insertar(c);
					} else {
						pila.extraer();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Error en la entrada, debe ingresar solo digitos y operadores",
							"Error", JOptionPane.ERROR_MESSAGE);
					posfija = "";
					break;
				}
			}
			while (!pila.estaVacia()) {
				posfija += pila.extraer();
				posfija += ' ';
			}
			return posfija;
		} else {
			JOptionPane.showMessageDialog(null, "Revise parentesis", "Error", JOptionPane.ERROR_MESSAGE);
			return "";
		}
	}

	/**
	 * Metodo que evalua la precedencia de dos operadores
	 * 
	 * @param op1 operador 1
	 * @param op2 operador 2
	 * @return True si tiene mayor precedencia el primero, False si no tiene mayor
	 *         precedencia
	 */
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
