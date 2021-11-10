package fes.aragon.test;

import javax.swing.JOptionPane;

import fes.aragon.utilerias.dinamicas.pila.Pila;

public class TestPos {

	public static void main(String[] args) throws Exception {
		Pila<Double> pila = new Pila<>();
		String cadena = "5 4 3 +";
		String tmp = "";
		try {
			double resultado = 0.0;
			for (int i = 0; i < cadena.length(); i++) {
				System.out.println("i:"+i);
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
					System.out.println("i2:"+i);
					pila.insertar(Double.parseDouble(tmp));
				}
				else if(c==' '){
				}
				else {
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
			System.out.println("El resultado es: " + resultado);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "No acepto letras :c Me dan amsiedad" + "Revise la entrada:" + tmp);
		}
	}

}
