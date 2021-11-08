package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import fes.aragon.except.IndiceFueraDeRango;
import fes.aragon.herramientas.*;
import fes.aragon.utilerias.dinamicas.listasimple.ListaSimple;
import javafx.application.Platform;
import javafx.event.ActionEvent;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class OrdenamientosController implements Initializable {
	@FXML
	private Button accion;
	@FXML
	private Button bntAleatorio;
	@FXML
	private Button btnSalir;
	@FXML
	private BarChart<String, Number> areaBurbuja;
	@FXML
	private BarChart<String, Number> areaSeleccion;
	@FXML
	private BarChart<String, Number> areaInsercion;
	@FXML
	private BarChart<String, Number> areaSacudida;
	@FXML
	private BarChart<String, Number> areaQuick;
	@FXML
	private BarChart<String, Number> areaQuickI;
	ListaSimple<Integer> listaB = new ListaSimple<>();
	ListaSimple<Integer> listaS = new ListaSimple<>();
	ListaSimple<Integer> listaI = new ListaSimple<>();
	ListaSimple<Integer> listaSa = new ListaSimple<>();
	ListaSimple<Integer> listaQ = new ListaSimple<>();
	ListaSimple<Integer> listaQI = new ListaSimple<>();
	final String[] color = { "-fx-bar-fill: #36B2B9", "-fx-bar-fill: #3671B9", "-fx-bar-fill: #B936B2",
			"-fx-bar-fill: #B93671" };
	final CategoryAxis xAxisB = new CategoryAxis();
	final NumberAxis yAxisB = new NumberAxis();
	private XYChart.Series<String, Number> seriesB;
	private ScheduledExecutorService scheduledExecutorServiceB;
	final CategoryAxis xAxisS = new CategoryAxis();
	final NumberAxis yAxisS = new NumberAxis();
	private XYChart.Series<String, Number> seriesS;
	private ScheduledExecutorService scheduledExecutorServiceS;
	final CategoryAxis xAxisSa = new CategoryAxis();
	final NumberAxis yAxisSa = new NumberAxis();
	private XYChart.Series<String, Number> seriesSa;
	private ScheduledExecutorService scheduledExecutorServiceSa;
	final CategoryAxis xAxisI = new CategoryAxis();
	final NumberAxis yAxisI = new NumberAxis();
	private XYChart.Series<String, Number> seriesI;
	private ScheduledExecutorService scheduledExecutorServiceI;
	final CategoryAxis xAxisQ = new CategoryAxis();
	final NumberAxis yAxisQ = new NumberAxis();
	private XYChart.Series<String, Number> seriesQ;
	private ScheduledExecutorService scheduledExecutorServiceQ;
	final CategoryAxis xAxisQI = new CategoryAxis();
	final NumberAxis yAxisQI = new NumberAxis();
	private XYChart.Series<String, Number> seriesQI;
	private ScheduledExecutorService scheduledExecutorServiceQI;

	@FXML
	public void evento(ActionEvent event) {
		// Metodo burbuja
		scheduledExecutorServiceB = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutorServiceB.scheduleAtFixedRate(() -> {
			Platform.runLater(() -> {
				Ordenamientos.burbuja(listaB, seriesB);
			});
		}, 0, 1, TimeUnit.SECONDS);
		// Metodo seleccion
		scheduledExecutorServiceS = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutorServiceS.scheduleAtFixedRate(() -> {
			Platform.runLater(() -> {
				Ordenamientos.seleccion(listaS, seriesS);
			});
		}, 0, 1, TimeUnit.SECONDS);
		// Metodo insercion
		scheduledExecutorServiceI = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutorServiceI.scheduleAtFixedRate(() -> {
			Platform.runLater(() -> {
				Ordenamientos.insercion(listaI, seriesI);
			});
		}, 0, 1, TimeUnit.SECONDS);
		// Metodo quick sort recursivo
		scheduledExecutorServiceQ = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutorServiceQ.scheduleAtFixedRate(() -> {
			Platform.runLater(() -> {
				try {
					Ordenamientos.reduceRecursivo(0, listaQ.getLongitud() - 1, listaQ, seriesQ);
				} catch (IndiceFueraDeRango e) {
					e.printStackTrace();
				}
			});
		}, 0, 1, TimeUnit.SECONDS);
		// Metodo quick sort iterativo
		scheduledExecutorServiceQI = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutorServiceQI.scheduleAtFixedRate(() -> {
			Platform.runLater(() -> {
				Ordenamientos.rapidoIterativo(listaQI, seriesQI);
			});
		}, 0, 1, TimeUnit.SECONDS);
		scheduledExecutorServiceSa = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutorServiceSa.scheduleAtFixedRate(() -> {
			Platform.runLater(() -> {
				Ordenamientos.sacudida(listaSa, seriesSa);
			});
		}, 0, 1, TimeUnit.SECONDS);
	}

	@SuppressWarnings("unchecked")
	@FXML
	public void aleatorios(ActionEvent event) throws IndiceFueraDeRango {
		numerosAleatorios();
		Herramientas.limpiarSeries(seriesB, seriesS, seriesSa, seriesI, seriesQ, seriesQI);
		Herramientas.llenarGrafica(listaB, color, seriesB, seriesS, seriesSa, seriesI, seriesQ, seriesQI);
		Herramientas.apagarHilos(scheduledExecutorServiceB, scheduledExecutorServiceS, scheduledExecutorServiceSa,
				scheduledExecutorServiceI, scheduledExecutorServiceQ, scheduledExecutorServiceQI);
	}

	@FXML
	public void eventoSalir(ActionEvent event) {
		Herramientas.apagarHilos(scheduledExecutorServiceB, scheduledExecutorServiceS, scheduledExecutorServiceSa,
				scheduledExecutorServiceI, scheduledExecutorServiceQ, scheduledExecutorServiceQI);
		Platform.exit();
	}
	//Posible metodo
	private void numerosAleatorios() throws IndiceFueraDeRango {
		listaB = new ListaSimple<>();
		listaS = new ListaSimple<>();
		listaI = new ListaSimple<>();
		listaQ = new ListaSimple<>();
		listaSa = new ListaSimple<>();
		listaQI = new ListaSimple<>();
		Random rd = new Random();
		for (int i = 0; i < 100; i++) {
			listaB.agregarEnCola(rd.nextInt(100) + 10);
		}
		for (int i = 0; i < listaB.getLongitud(); i++) {
			listaS.agregarEnCola(listaB.obtenerNodo(i));
			listaI.agregarEnCola(listaB.obtenerNodo(i));
			listaQ.agregarEnCola(listaB.obtenerNodo(i));
			listaSa.agregarEnCola(listaB.obtenerNodo(i));
			listaQI.agregarEnCola(listaB.obtenerNodo(i));
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			numerosAleatorios();
		} catch (IndiceFueraDeRango e) {
			e.printStackTrace();
		}
		Herramientas.llenarDatos(yAxisB, xAxisB, areaBurbuja, "Burbuja");
		seriesB = new XYChart.Series<>();
		seriesB.setName("Conjunto de datos");
		areaBurbuja.getData().add(seriesB);
		for (int i = 0; i < listaB.getLongitud(); i++) {
			try {
				final XYChart.Data<String, Number> dato = new XYChart.Data<>(String.valueOf(i), listaB.obtenerNodo(i));
				seriesB.getData().add(dato);
				dato.getNode().setStyle(color[new Random().nextInt(4)]);
			} catch (IndiceFueraDeRango e) {
				e.printStackTrace();
			}
		}
		Herramientas.llenarDatos(yAxisS, xAxisS, areaSeleccion, "Seleccion");
		seriesS = new XYChart.Series<>();
		seriesS.setName("Conjunto de datos");
		areaSeleccion.getData().add(seriesS);
		for (int i = 0; i < listaB.getLongitud(); i++) {
			try {
				final XYChart.Data<String, Number> dato = new XYChart.Data<>(String.valueOf(i), listaB.obtenerNodo(i));
				seriesS.getData().add(dato);
				dato.getNode().setStyle(color[new Random().nextInt(4)]);
			} catch (IndiceFueraDeRango e) {
				e.printStackTrace();
			}
		}
		Herramientas.llenarDatos(yAxisI, xAxisI, areaInsercion, "Insercion");
		seriesI = new XYChart.Series<>();
		seriesI.setName("Conjunto de datos");
		areaInsercion.getData().add(seriesI);
		for (int i = 0; i < listaB.getLongitud(); i++) {
			try {
				final XYChart.Data<String, Number> dato = new XYChart.Data<>(String.valueOf(i), listaB.obtenerNodo(i));
				seriesI.getData().add(dato);
				dato.getNode().setStyle(color[new Random().nextInt(4)]);
			} catch (IndiceFueraDeRango e) {
				e.printStackTrace();
			}
		}
		Herramientas.llenarDatos(yAxisSa, xAxisSa, areaSacudida, "Sacudida");
		seriesSa = new XYChart.Series<>();
		seriesSa.setName("Conjunto de datos");
		areaSacudida.getData().add(seriesSa);
		for (int i = 0; i < listaB.getLongitud(); i++) {
			try {
				final XYChart.Data<String, Number> dato = new XYChart.Data<>(String.valueOf(i), listaSa.obtenerNodo(i));
				seriesSa.getData().add(dato);
				dato.getNode().setStyle(color[new Random().nextInt(4)]);
			} catch (IndiceFueraDeRango e) {
				e.printStackTrace();
			}
		}
		Herramientas.llenarDatos(yAxisQ, xAxisQ, areaQuick, "Quick sort recursivo");
		seriesQ = new XYChart.Series<>();
		seriesQ.setName("Conjunto de datos");
		areaQuick.getData().add(seriesQ);
		for (int i = 0; i < listaB.getLongitud(); i++) {
			try {
				final XYChart.Data<String, Number> dato = new XYChart.Data<>(String.valueOf(i), listaB.obtenerNodo(i));
				seriesQ.getData().add(dato);
				dato.getNode().setStyle(color[new Random().nextInt(4)]);
			} catch (IndiceFueraDeRango e) {
				e.printStackTrace();
			}
		}
		Herramientas.llenarDatos(yAxisQI, xAxisQI, areaQuickI, "Quick sort iterativo");
		seriesQI = new XYChart.Series<>();
		seriesQI.setName("Conjunto de datos");
		areaQuickI.getData().add(seriesQI);
		for (int i = 0; i < listaB.getLongitud(); i++) {
			try {
				final XYChart.Data<String, Number> dato = new XYChart.Data<>(String.valueOf(i), listaQI.obtenerNodo(i));
				seriesQI.getData().add(dato);
				dato.getNode().setStyle(color[new Random().nextInt(4)]);
			} catch (IndiceFueraDeRango e) {
				e.printStackTrace();
			}
		}

	}
}
