package fes.aragon.herramientas;

import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;

import fes.aragon.except.IndiceFueraDeRango;
import fes.aragon.utilerias.dinamicas.listasimple.ListaSimple;
import fes.aragon.utilerias.dinamicas.pila.Pila;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

public class Herramientas {
	private static ListaSimple<Integer> listaAux = new ListaSimple<>();

	public static void apagarHilos(ScheduledExecutorService... hilos) {
		for (ScheduledExecutorService hilo : hilos) {
			if (hilo != null) {
				hilo.shutdown();
			}
		}
	}

	public static void limpiarSeries(XYChart.Series<String, Number>... series) {
		for (Series<String, Number> serie : series) {
			serie.getData().clear();
		}
	}

	public static void llenarDatos(NumberAxis y, CategoryAxis x, BarChart<String, Number> area, String metodo) {
		y.setLabel("Valor");
		y.setAnimated(false);
		x.setLabel("Datos");
		x.setAnimated(false);
		area.setTitle("Método " + metodo);
		area.setAnimated(false);
	}

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
