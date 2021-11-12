package fes.aragon.utilerias.dinamicas.listasimple;

public class Nodo<E> {
	private E dato;
	private Nodo<E> siguiente;

	/**
	 * Clase que crea aun Nodo
	 * 
	 * @author Equipo 9 Bueno Zaldivar Omar Alejandro y Sol Martinez Edith
	 *
	 * @param <E> Tipo de nodo a crear
	 */
	public Nodo(E dato) {
		this(dato, null);
	}

	/**
	 * Creacion del nodo
	 * 
	 * @param dato      Dato a ingresar
	 * @param siguiente Referencia del nodo
	 */
	public Nodo(E dato, Nodo<E> siguiente) {
		this.dato = dato;
		this.siguiente = siguiente;
	}

	/**
	 * Metodo que devuelve el valor del nodo
	 * 
	 * @return Valor del nodo
	 */
	public E getDato() {
		return dato;
	}

	/**
	 * Metodo que cambia el valor del nodo
	 * 
	 * @param dato Dato a cambiar
	 */
	public void setDato(E dato) {
		this.dato = dato;
	}

	/**
	 * Metodo que obtiene la referencia del siguiente nodo
	 * 
	 * @return Siguiente nodo
	 */
	public Nodo<E> getSiguiente() {
		return siguiente;
	}

	/**
	 * Metodo que cambia el siguiente nodo
	 * 
	 * @param siguiente Siguiente nodo
	 */
	public void setSiguiente(Nodo<E> siguiente) {
		this.siguiente = siguiente;
	}

}
