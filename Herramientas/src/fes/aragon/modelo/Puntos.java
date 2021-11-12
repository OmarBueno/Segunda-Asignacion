package fes.aragon.modelo;

/**
 * Clase que almacena puntos (x,Y)
 * 
 * @author Equipo 9 Bueno Zaldivar Omar Alejandro y Sol Martinez Edith
 *
 */
public class Puntos {
	private int xx;
	private int yy;

	/**
	 * Contructor
	 * 
	 * @param xx punto x
	 * @param yy punto y
	 */
	public Puntos(int xx, int yy) {
		super();
		this.xx = xx;
		this.yy = yy;
	}

	/**
	 * Metodo que devuelve el punto x
	 * 
	 * @return x
	 */
	public int getXx() {
		return xx;
	}

	/**
	 * Metodo que cambia el valor de x
	 * 
	 * @param xx valor x
	 */
	public void setXx(int xx) {
		this.xx = xx;
	}

	/**
	 * Metodo que devuelve el punto y
	 * 
	 * @return y
	 */
	public int getYy() {
		return yy;
	}

	/**
	 * Metodo que cambia el valor de y
	 * 
	 * @param y valor y
	 */
	public void setYy(int yy) {
		this.yy = yy;
	}

}
