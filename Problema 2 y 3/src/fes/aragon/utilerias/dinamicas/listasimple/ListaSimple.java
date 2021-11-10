package fes.aragon.utilerias.dinamicas.listasimple;

import java.util.Random;

import fes.aragon.except.IndiceFueraDeRango;

public class ListaSimple<E> {
	protected Nodo<E> cabeza, cola;
	protected int longitud = 0;

	public ListaSimple() {
		cabeza = cola = null;
	}

	public void agregarEnCabeza(E dato) {
		cabeza = new Nodo<E>(dato, cabeza);
		if (cola == null) {
			cola = cabeza;
		}
		longitud++;
	}

	public void agregarEnCola(E dato) {
		if (cabeza == null) {
			cabeza = cola = new Nodo<E>(dato);
		} else {
			cola.setSiguiente(new Nodo<E>(dato));
			cola = cola.getSiguiente();
		}
		longitud++;
	}

	public void eliminarEnCabeza() {
		if (cabeza == null) {
			System.out.println("No existe elemento en cabeza");
		} else {
			if (cabeza.getSiguiente() != null) {
				// cabeza.setDato(cabeza.getSiguiente().getDato());
				cabeza = cabeza.getSiguiente();
				longitud--;
			} else {
				// System.out.println("No existe un nodo siguiente");
				cabeza = null;
				longitud--;
			}
		}
	}
	/*
	 * cola = null; Nodo<E> tmp; for (tmp = cabeza; tmp != null; tmp =
	 * tmp.getSiguiente()) { if (tmp.getSiguiente().getSiguiente() == null) {
	 * tmp.setSiguiente(null); cola = tmp; } } longitud--;
	 */

	public void eliminarEnCola() {
		if (cola == null) {
			System.out.println("No existe elemento en cola");
		} else {
			if (cola != cabeza) {
				Nodo<E> tmp;
				for (tmp = cabeza; tmp.getSiguiente() != cola; tmp = tmp.getSiguiente())
					;
				tmp.setSiguiente(null);
				cola = tmp;
				longitud--;
			} else {
				cola = cabeza = null;
				longitud --;			}

		}
	}
	
	public E obtenerCola() {
		return cola.getDato();
	}

	public int getLongitud() {
		return longitud;
	}

	public boolean esVacia() {
		if (longitud == 0) {
			return true;
		} else {
			return false;
		}
	}

	public E obtenerNodo(int i) throws IndiceFueraDeRango {
		int aux = 0;
		E e = null;
		if (i < longitud && i >= 0) {
			for (Nodo<E> tmp = cabeza; aux <= i; tmp = tmp.getSiguiente(), aux++) {
				e = (E) tmp.getDato();
			}
		} else {
			throw new IndiceFueraDeRango("Fallo funcion obtenerNodo Indice fuera de rango");
		}

		return e;
	}

	public int estaEnLista(E x) {
		int aux = 0;
		for (Nodo<E> tmp = cabeza; tmp != null; tmp = tmp.getSiguiente(), aux++) {
			if (tmp.getDato().equals(x)) {
				break;
			}
		}
		if (aux < longitud) {
			return aux;
		} else {
			return -1;
		}

	}

	public boolean eliminarEnIndice(int i) {
		if (i == 0) {
			eliminarEnCabeza();
			return true;
		} else if (i == longitud - 1) {
			eliminarEnCola();
			return true;
		} else if (i > 0 && i < longitud - 1) {
			int aux = 0;
			Nodo<E> tmp;
			for (tmp = cabeza; aux < i - 1; tmp = tmp.getSiguiente(), aux++)
				;
			tmp.setSiguiente(tmp.getSiguiente().getSiguiente());
			return true;
		} else {
			return false;
		}
	}

	/*
	 * int aux = 0; for (Nodo<E> tmp = cabeza; aux < i; tmp = tmp.getSiguiente(),
	 * aux++) { if (aux == i - 1) {
	 * tmp.setSiguiente(tmp.getSiguiente().getSiguiente()); }
	 * 
	 * } return true;
	 */
	public void insertarEnIndice(E dato, int i) throws IndiceFueraDeRango {
		if (i == 0) {
			agregarEnCabeza(dato);
		} else if (i > 0 && i < longitud) {
			int aux = 0;
			Nodo<E> tmp;
			for (tmp = cabeza; aux < i; tmp = tmp.getSiguiente(), aux++)
				;
			Nodo<E> tmp2 = new Nodo<E>(tmp.getDato(), tmp.getSiguiente());
			tmp.setDato(dato);
			tmp.setSiguiente(tmp2);
			if (i == getLongitud() - 1) {
				cola = tmp2;
			}
			longitud++;
		} else {
			throw new IndiceFueraDeRango("Fallo funcion insertarEnIndice Indice fuera de rango");
		}
	}
	/*
	 * int aux = 0; for (Nodo<E> tmp = cabeza; aux <= i; tmp = tmp.getSiguiente(),
	 * aux++) { if (aux == i) { Nodo<E> tmp2 = new Nodo<E>(tmp.getDato(),
	 * tmp.getSiguiente()); tmp.setDato(dato); tmp.setSiguiente(tmp2); cola = tmp2;
	 * } } longitud++;
	 */

	public void asignar(E dato, int i) throws IndiceFueraDeRango {
		int aux = 0;
		if (i < longitud && i >= 0) {
			for (Nodo<E> tmp = cabeza; aux <= i; tmp = tmp.getSiguiente(), aux++) {
				if (aux == i) {
					tmp.setDato(dato);
				}
			}
		} else {
			throw new IndiceFueraDeRango("Fallo funcion asignar Indice fuera de rango");
		}
	}

	public void asignar(E dato, E nuevoDato, boolean condicion) throws IndiceFueraDeRango {
		E e = null;
		if (condicion == false) {
			for (Nodo<E> tmp = cabeza; tmp != null; tmp = tmp.getSiguiente()) {
				if (tmp.getDato().equals(dato)) {
					e = tmp.getDato();
					tmp.setDato(nuevoDato);
					break;
				}
			}
		} else {
			for (Nodo<E> tmp = cabeza; tmp != null; tmp = tmp.getSiguiente()) {
				if (tmp.getDato().equals(dato)) {
					e = tmp.getDato();
					tmp.setDato(nuevoDato);
					// continue;
				}
			}
		}
		if (e == null) {
			System.out.println("No se encontro el dato");
		} else {
			System.out.println("Se actualizo correctamente");
		}
	}

	@SuppressWarnings("unchecked")
	public void rellenar(int ndatos) {
		Random rd = new Random();
		E e = null;
		for (int i = 0; i < ndatos; i++) {
			e = (E) (Integer) rd.nextInt(101);
			agregarEnCola(e);
		}
	}
	
	public void limpiar() {
		cabeza = cola = null;
		longitud = 0;
	}

	public void imprimirElementos() {
		if (cabeza == null) {

		} else {
			for (Nodo<E> tmp = cabeza; tmp != null; tmp = tmp.getSiguiente()) {
				System.out.println(tmp.getDato());
			}
		}
	}

}
