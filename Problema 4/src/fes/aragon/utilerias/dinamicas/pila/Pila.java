package fes.aragon.utilerias.dinamicas.pila;

import fes.aragon.utilerias.dinamicas.listasimple.ListaSimple;

public class Pila<E> {
	private ListaSimple<E> pila = new ListaSimple<>();
	private int datos =0;

	public void borrar() {
		pila = new ListaSimple<>();
	}

	public boolean estaVacia() {
		return pila.esVacia();
	}

	public void insertar(E dato) {
		pila.agregarEnCola(dato);
		datos++;
	}

	public E extraer() throws Exception {
		E tmp = null;
		if (!estaVacia()) {
			tmp = pila.obtenerCola();
			pila.eliminarEnCola();
			datos--;
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
	
	public int getDatos() {
		return datos;
	}

}
