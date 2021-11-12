package fes.aragon.except;

/**
 * Clase para manejo de excepciones de Lista Simple
 * 
 * @author Equipo 9 Bueno Zaldivar Omar Alejandro y Sol Martinez Edith
 *
 */
public class IndiceFueraDeRango extends Exception {
	private static final long serialVersionUID = 1L;

	public IndiceFueraDeRango(String msg) {
		super(msg);
	}
}
