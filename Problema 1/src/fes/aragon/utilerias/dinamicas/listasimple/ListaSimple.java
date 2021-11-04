package fes.aragon.utilerias.dinamicas.listasimple;

import java.util.Random;

import fes.aragon.except.IndiceFueraDeRango;

public class ListaSimple<E> {
	protected Nodo<E> cabeza, cola;
	protected int longitud = 0;

	/**
	 * Clase que crea una lista simplemente enlazada
	 *
	 * @author Equipo 9 Bueno Zaldivar Omar Alejandro y Sol Martinez Edith
	 *
	 * @param <E> Tipo de datos a almacenar
	 */
	/**
	 * Creacion de la lista
	 */
	public ListaSimple() {
		cabeza = cola = null;
	}

	/**
	 * Metodo que agrega un dato al inicio de la lista
	 *
	 * @param dato Primer dato de la lista
	 */
	public void agregarEnCabeza(E dato) {
		cabeza = new Nodo<E>(dato, cabeza);
		if (cola == null) {
			cola = cabeza;
		}
		longitud++;
	}

	/**
	 * Metodo que agrega un dato al final de la lista
	 *
	 * @param dato ultimo dato de la lista
	 */
	public void agregarEnCola(E dato) {
		if (cabeza == null) {
			cabeza = cola = new Nodo<E>(dato);
		} else {
			cola.setSiguiente(new Nodo<E>(dato));
			cola = cola.getSiguiente();
		}
		longitud++;
	}

	/**
	 * Metodo que elimina el dato de la cabeza si solo hay un dato elimina la lista
	 */
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

	/**
	 * Metodo que elimina el dato de la cola si solo hay un dato elimina la lista
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
				longitud--;
			}

		}
	}

	public E obtenerCola() {
		return cola.getDato();
	}

	/**
	 * Metodo que devuelve la longitud de la lista
	 *
	 * @return longitud de la lista
	 */
	public int getLongitud() {
		return longitud;
	}

	/**
	 * Metodo que indica si la lista es vacia
	 *
	 * @return True si es vacia False si no es vacia
	 */
	public boolean esVacia() {
		if (longitud == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Metodo que devuelve el valor del nodo indicado
	 *
	 * @param i Indice a obtener
	 * @return Valor del nodo
	 * @throws IndiceFueraDeRango Advertencia de rango
	 */
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

	/**
	 * Indica si un elemento esta en la lista
	 *
	 * @param x Dato a buscar
	 * @return Retorna el indice si lo encuentra sino un -1
	 */
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

	/**
	 * Elimina el dato en el indice indicado
	 *
	 * @param i Indice a eliminar
	 * @return True si lo logra eliminar False si no lo logra eliminarlo
	 */
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

	/**
	 * Elimina el dato en el la fila y columna indicados
	 *
	 * @param i       fila
	 * @param columna columna
	 * @return True si lo logra eliminar False si no lo logra eliminarlo
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

	/**
	 * Metodo que asgna un dato en el indice indicado
	 *
	 * @param dato dato a asignarr
	 * @param i    indice
	 * @throws IndiceFueraDeRango Advertencia de rango
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

	/**
	 * Cambia la primera incidencia de dato con el nuevo dato si el boolean es true
	 * cambiara todos los datos si es False solo la primera coincidencia
	 *
	 * @param dato      Data a cambiar
	 * @param nuevoDato Dato que remplaza al dato anterior
	 * @param condicion True: todas las coincidencias False: solo la primera
	 *                  coincidencia
	 * @throws IndiceFueraDeRango Advertencia de rango
	 */
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
				}
			}
		}
		if (e == null) {
			System.out.println("No se encontro el dato");
		} else {
			System.out.println("Se actualizo correctamente");
		}
	}

	/**
	 * Metodo que rellena la lista de numeros aleatorios de 0 a 100
	 * 
	 * @param ndatos Numero de elementos a agregar
	 */
	@SuppressWarnings("unchecked")
	public void rellenar(int ndatos) {
		Random rd = new Random();
		E e = null;
		for (int i = 0; i < ndatos; i++) {
			e = (E) (Integer) rd.nextInt(101);
			agregarEnCola(e);
		}
	}

	/**
	 * ;Metodo que limpia la lista
	 */
	public void limpiar() {
		cabeza = cola = null;
		longitud = 0;
	}

	/**
	 * Metodo que imprime todos los elementos de todas las listas
	 *
	 */
	public void imprimirElementos() {
		if (cabeza == null) {
		} else {
			for (Nodo<E> tmp = cabeza; tmp != null; tmp = tmp.getSiguiente()) {
				System.out.println(tmp.getDato());
			}
		}
	}

}
