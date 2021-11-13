package fes.aragon.problemaUno.herramientas;

import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;

import fes.aragon.except.IndiceFueraDeRango;
import fes.aragon.utilerias.dinamicas.listasimple.ListaSimple;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

/**
 * Clase auxiliar para el problema 1
 * 
 * @author Equipo 9 Bueno Zaldivar Omar Alejandro y Sol Martinez Edith
 *
 */
public class Herramientas {

	/**
	 * Metodo que apaga los hilos del programa
	 * 
	 * @param hilos Hilos a apagar
	 */
	public static void apagarHilos(ScheduledExecutorService... hilos) {
		for (ScheduledExecutorService hilo : hilos) {
			if (hilo != null) {
				hilo.shutdown();
			}
		}
	}

	/**
	 * Metodo que limpia las series de las graficas
	 * 
	 * @param series Series a limpiar
	 */
	@SuppressWarnings("unchecked")
	public static void limpiarSeries(XYChart.Series<String, Number>... series) {
		for (Series<String, Number> serie : series) {
			serie.getData().clear();
		}
	}

	/**
	 * Metodo que llena los datos de las graficas
	 * 
	 * @param y      Eje Y
	 * @param x      Eje X
	 * @param area   Grafica
	 * @param metodo Nombre del metodo de la grafica
	 */
	public static void llenarDatos(NumberAxis y, CategoryAxis x, BarChart<String, Number> area, String metodo) {
		y.setLabel("Valor");
		y.setAnimated(false);
		x.setLabel("Datos");
		x.setAnimated(false);
		area.setTitle("Método " + metodo);
		area.setAnimated(false);
	}

	/**
	 * Metodo que llena la grafica
	 * 
	 * @param lista  lista de datos
	 * @param color  Colores de las bvarras
	 * @param series Series de la grafica
	 * @throws IndiceFueraDeRango Error en el llenado
	 */
	@SuppressWarnings("unchecked")
	public static void llenarGrafica(ListaSimple<Integer> lista, String[] color,
			XYChart.Series<String, Number>... series) throws IndiceFueraDeRango {
		for (Series<String, Number> serie : series) {
			for (int i = 0; i < lista.getLongitud(); i++) {
				serie.getData().add(new XYChart.Data<>(String.valueOf(i), lista.obtenerNodo(i)));
				serie.getData().get(i).getNode().setStyle(color[new Random().nextInt(4)]);
			}
		}
	}

}
