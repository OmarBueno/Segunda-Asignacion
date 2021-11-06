package fes.aragon.utilerias.dinamicas.pila;

import fes.aragon.utilerias.dinamicas.listasimple.ListaSimple;

public class Pila<E> {
	private ListaSimple<E> pila = new ListaSimple<>();

	public void borrar() {
		pila = new ListaSimple<>();
	}

	public boolean estaVacia() {
		return pila.esVacia();
	}

	public void insertar(E dato) {
		pila.agregarEnCola(dato);
	}

	public E extraer() throws Exception {
		E tmp = null;
		if (!estaVacia()) {
			tmp = pila.obtenerCola();
			pila.eliminarEnCola();
		} else {
			throw new Exception("Pila Vacia");
		}
		return tmp;

	}

	public E elementoSuperior() throws Exception {
		E tmp = null;
		if (!estaVacia()) {
			tmp = pila.obtenerCola();
		} else {
			throw new Exception("Pila Vacia");
		}
		return tmp;
	}

}
