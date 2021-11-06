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
import fes.aragon.utilerias.dinamicas.listasimple.ListaSimple;
import fes.aragon.utilerias.dinamicas.pila.Pila;
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
		scheduledExecutorServiceB = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutorServiceB.scheduleAtFixedRate(() -> {
			Platform.runLater(() -> {
				for (int i = 0; i < listaB.getLongitud(); i++) {
					for (int j = listaB.getLongitud() - 1; j > i; j--) {
						try {
							if (listaB.obtenerNodo(j - 1) > listaB.obtenerNodo(j)) {
								Integer aux = listaB.obtenerNodo(j - 1);
								listaB.asignar(listaB.obtenerNodo(j), j - 1);
								listaB.asignar(aux, j);
								String tmpEstilo = seriesB.getData().get(j).getNode().getStyle();
								String tmpEstiloDos = seriesB.getData().get(j - 1).getNode().getStyle();
								seriesB.getData().get(j).getNode().setStyle(tmpEstiloDos);
								seriesB.getData().get(j - 1).getNode().setStyle(tmpEstilo);
								seriesB.getData().get(j).setYValue(listaB.obtenerNodo(j));
								seriesB.getData().get(j - 1).setYValue(listaB.obtenerNodo(j - 1));
								break;
							}
						} catch (IndiceFueraDeRango e) {
							e.printStackTrace();
						}
					}
				}
			});
		}, 0, 1, TimeUnit.SECONDS);
		scheduledExecutorServiceS = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutorServiceS.scheduleAtFixedRate(() -> {
			Platform.runLater(() -> {
				int menor;
				int k;
				for (int i = 0; i < listaS.getLongitud() - 1; i++) {
					try {
						menor = listaS.obtenerNodo(i);
						k = i;
						for (int j = i + 1; j < listaS.getLongitud(); j++) {
							if (listaS.obtenerNodo(j) < menor) {
								menor = listaS.obtenerNodo(j);
								k = j;
							}
							break;
						}
						listaS.asignar(listaS.obtenerNodo(i), k);
						listaS.asignar(menor, i);
						String tmpEstilo = seriesS.getData().get(i).getNode().getStyle();
						String tmpEstiloDos = seriesS.getData().get(k).getNode().getStyle();
						seriesS.getData().get(k).getNode().setStyle(tmpEstilo);
						seriesS.getData().get(i).getNode().setStyle(tmpEstiloDos);
						seriesS.getData().get(k).setYValue(listaS.obtenerNodo(k));
						seriesS.getData().get(i).setYValue(listaS.obtenerNodo(i));
					} catch (IndiceFueraDeRango e) {
						e.printStackTrace();
					}
				}
			});
		}, 0, 1, TimeUnit.SECONDS);
		scheduledExecutorServiceI = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutorServiceI.scheduleAtFixedRate(() -> {
			Platform.runLater(() -> {
				int aux;
				int k;
				for (int i = 1; i < listaI.getLongitud(); i++) {
					try {
						aux = listaI.obtenerNodo(i);
						k = i - 1;
						while (k >= 0 && aux < listaI.obtenerNodo(k)) {
							listaI.asignar(listaI.obtenerNodo(k), k + 1);
							// String tmpEstilo = seriesI.getData().get(k).getNode().getStyle();
							// String tmpEstiloDos = seriesI.getData().get(k + 1).getNode().getStyle();
							// seriesI.getData().get(k + 1).getNode().setStyle(tmpEstilo);
							// seriesI.getData().get(k).getNode().setStyle(tmpEstiloDos);
							seriesI.getData().get(k + 1).setYValue(listaI.obtenerNodo(k + 1));
							// series.getData().get(k).setYValue(aux);
							k = k - 1;
							break;
						}
						listaI.asignar(aux, k + 1);
						// String tmpEstiloDos = seriesI.getData().get(aux).getNode().getStyle();
						// seriesI.getData().get(k + 1).getNode().setStyle(tmpEstiloDos);
						seriesI.getData().get(k + 1).setYValue(listaI.obtenerNodo(k + 1));
					} catch (IndiceFueraDeRango e) {
						e.printStackTrace();
					}
				}
			});
		}, 0, 1, TimeUnit.SECONDS);
		scheduledExecutorServiceQ = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutorServiceQ.scheduleAtFixedRate(() -> {
			Platform.runLater(() -> {
				try {
					reduceRecursivo(0, listaQ.getLongitud() - 1);
				} catch (IndiceFueraDeRango e) {
					e.printStackTrace();
				}
			});
		}, 0, 1, TimeUnit.SECONDS);
		scheduledExecutorServiceQI = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutorServiceQI.scheduleAtFixedRate(() -> {
			Platform.runLater(() -> {
				try {
					int tope, ini, fin, pos = 0;
					Pila<Integer> pMenor = new Pila<>();
					Pila<Integer> pMayor = new Pila<>();
					tope = 1;
					pMenor.insertar(0);
					pMayor.insertar(listaQI.getLongitud() - 1);
					while (tope > 0) {
						ini = pMenor.extraer();
						fin = pMayor.extraer();
						tope--;
						pos = reduceItertivo(ini, fin, pos);
						if (ini < (pos - 1)) {
							tope++;
							pMenor.insertar(ini);
							pMayor.insertar(pos - 1);
						}
						if (fin > (pos + 1)) {
							tope++;
							pMenor.insertar(pos + 1);
							pMayor.insertar(fin);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}, 0, 1, TimeUnit.SECONDS);
		scheduledExecutorServiceSa = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutorServiceSa.scheduleAtFixedRate(() -> {
			Platform.runLater(() -> {
				try {
					int izquierda = 1;
					int derecha = listaSa.getLongitud() - 1;
					int k = listaSa.getLongitud() - 1;
					while (derecha >= izquierda) {
						for (int i = derecha; i >= izquierda; i--) {
							if (listaSa.obtenerNodo(i - 1) > listaSa.obtenerNodo(i)) {
								Integer aux = listaSa.obtenerNodo(i - 1);
								listaSa.asignar(listaSa.obtenerNodo(i), i - 1);
								listaSa.asignar(aux, i);
								String tmpEstilo = seriesSa.getData().get(i - 1).getNode().getStyle();
								String tmpEstiloDos = seriesSa.getData().get(i).getNode().getStyle();
								seriesSa.getData().get(i - 1).getNode().setStyle(tmpEstilo);
								seriesSa.getData().get(i).getNode().setStyle(tmpEstiloDos);
								seriesSa.getData().get(i - 1).setYValue(listaSa.obtenerNodo(i - 1));
								seriesSa.getData().get(i).setYValue(listaSa.obtenerNodo(i));
								k = i;
							}
						}
						izquierda = k + 1;
						for (int i = izquierda; i <= derecha; i++) {
							if (listaSa.obtenerNodo(i - 1) > listaSa.obtenerNodo(i)) {
								Integer aux = listaSa.obtenerNodo(i - 1);
								listaSa.asignar(listaSa.obtenerNodo(i), i - 1);
								listaSa.asignar(aux, i);
								String tmpEstilo = seriesSa.getData().get(i - 1).getNode().getStyle();
								String tmpEstiloDos = seriesSa.getData().get(i).getNode().getStyle();
								seriesSa.getData().get(i - 1).getNode().setStyle(tmpEstilo);
								seriesSa.getData().get(i).getNode().setStyle(tmpEstiloDos);
								seriesSa.getData().get(i - 1).setYValue(listaSa.obtenerNodo(i - 1));
								seriesSa.getData().get(i).setYValue(listaSa.obtenerNodo(i));
								k = i;
							}
						}
						derecha = k - 1;
						break;
					}
				} catch (IndiceFueraDeRango e) {
					e.printStackTrace();
				}
			});
		}, 0, 1, TimeUnit.SECONDS);
	}

	@FXML
	public void aleatorios(ActionEvent event) throws IndiceFueraDeRango {
		numerosAleatorios();
		seriesB.getData().clear();
		seriesS.getData().clear();
		seriesSa.getData().clear();
		seriesI.getData().clear();
		seriesQ.getData().clear();
		seriesQI.getData().clear();
		for (int i = 0; i < listaB.getLongitud(); i++) {
			seriesB.getData().add(new XYChart.Data<>(String.valueOf(i), listaB.obtenerNodo(i)));
			seriesB.getData().get(i).getNode().setStyle(color[new Random().nextInt(4)]);
			seriesS.getData().add(new XYChart.Data<>(String.valueOf(i), listaB.obtenerNodo(i)));
			seriesS.getData().get(i).getNode().setStyle(color[new Random().nextInt(4)]);
			seriesSa.getData().add(new XYChart.Data<>(String.valueOf(i), listaB.obtenerNodo(i)));
			seriesSa.getData().get(i).getNode().setStyle(color[new Random().nextInt(4)]);
			seriesI.getData().add(new XYChart.Data<>(String.valueOf(i), listaB.obtenerNodo(i)));
			seriesI.getData().get(i).getNode().setStyle(color[new Random().nextInt(4)]);
			seriesQ.getData().add(new XYChart.Data<>(String.valueOf(i), listaB.obtenerNodo(i)));
			seriesQ.getData().get(i).getNode().setStyle(color[new Random().nextInt(4)]);
			seriesQI.getData().add(new XYChart.Data<>(String.valueOf(i), listaB.obtenerNodo(i)));
			seriesQI.getData().get(i).getNode().setStyle(color[new Random().nextInt(4)]);
		}
		if (scheduledExecutorServiceB != null) {
			scheduledExecutorServiceB.shutdown();
		}
		if (scheduledExecutorServiceS != null) {
			scheduledExecutorServiceS.shutdown();
		}
		if (scheduledExecutorServiceSa != null) {
			scheduledExecutorServiceSa.shutdown();
		}
		if (scheduledExecutorServiceI != null) {
			scheduledExecutorServiceI.shutdown();
		}
		if (scheduledExecutorServiceQ != null) {
			scheduledExecutorServiceQ.shutdown();
		}
		if (scheduledExecutorServiceQI != null) {
			scheduledExecutorServiceQI.shutdown();
		}
	}

	// Event Listener on Button[#btnSalir].onAction
	@FXML
	public void eventoSalir(ActionEvent event) {
		if (scheduledExecutorServiceB != null) {
			scheduledExecutorServiceB.shutdown();
		}
		if (scheduledExecutorServiceS != null) {
			scheduledExecutorServiceS.shutdown();
		}
		if (scheduledExecutorServiceSa != null) {
			scheduledExecutorServiceSa.shutdown();
		}
		if (scheduledExecutorServiceI != null) {
			scheduledExecutorServiceI.shutdown();
		}
		if (scheduledExecutorServiceQ != null) {
			scheduledExecutorServiceQ.shutdown();
		}
		if (scheduledExecutorServiceQI != null) {
			scheduledExecutorServiceQI.shutdown();
		}
		Platform.exit();
	}

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
		yAxisB.setLabel("Valor");
		yAxisB.setAnimated(false);
		xAxisB.setLabel("Datos");
		xAxisB.setAnimated(false);
		areaBurbuja.setTitle("Método Burbuja");
		areaBurbuja.setAnimated(false);
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
		yAxisS.setLabel("Valor");
		yAxisS.setAnimated(false);
		xAxisS.setLabel("Datos");
		xAxisS.setAnimated(false);
		areaSeleccion.setTitle("Método Seleccion");
		areaSeleccion.setAnimated(false);
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
		yAxisI.setLabel("Valor");
		yAxisI.setAnimated(false);
		xAxisI.setLabel("Datos");
		xAxisI.setAnimated(false);
		areaInsercion.setTitle("Método insercion");
		areaInsercion.setAnimated(false);
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
		yAxisSa.setLabel("Valor");
		yAxisSa.setAnimated(false);
		xAxisSa.setLabel("Datos");
		xAxisSa.setAnimated(false);
		areaSacudida.setTitle("Método Sacudida");
		areaSacudida.setAnimated(false);
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
		yAxisQ.setLabel("Valor");
		yAxisQ.setAnimated(false);
		xAxisQ.setLabel("Datos");
		xAxisQ.setAnimated(false);
		areaQuick.setTitle("Método Quick Sort");
		areaQuick.setAnimated(false);
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
		yAxisQI.setLabel("Valor");
		yAxisQI.setAnimated(false);
		xAxisQI.setLabel("Datos");
		xAxisQI.setAnimated(false);
		areaQuickI.setTitle("Método Quick Sort");
		areaQuickI.setAnimated(false);
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

	public void reduceRecursivo(int ini, int fin) throws IndiceFueraDeRango {
		int izq = ini;
		int der = fin;
		int pos = ini;
		int aux;
		boolean band = true;
		while (band == true) {
			band = false;
			while (listaQ.obtenerNodo(pos) <= listaQ.obtenerNodo(der) && pos != der) {
				der = der - 1;
			}
			if (pos != der) {
				aux = listaQ.obtenerNodo(pos);
				listaQ.asignar(listaQ.obtenerNodo(der), pos);
				listaQ.asignar(aux, der);
				String tmpEstilo = seriesQ.getData().get(der).getNode().getStyle();
				String tmpEstiloDos = seriesQ.getData().get(pos).getNode().getStyle();
				seriesQ.getData().get(pos).getNode().setStyle(tmpEstilo);
				seriesQ.getData().get(der).getNode().setStyle(tmpEstiloDos);
				seriesQ.getData().get(pos).setYValue(listaQ.obtenerNodo(pos));
				seriesQ.getData().get(der).setYValue(listaQ.obtenerNodo(der));
				pos = der;
				while (listaQ.obtenerNodo(pos) >= listaQ.obtenerNodo(izq) && pos != izq) {
					izq = izq + 1;
				}
				if (pos != izq) {
					band = true;
					aux = listaQ.obtenerNodo(pos);
					listaQ.asignar(listaQ.obtenerNodo(izq), pos);
					listaQ.asignar(aux, izq);
					tmpEstilo = seriesQ.getData().get(izq).getNode().getStyle();
					tmpEstiloDos = seriesQ.getData().get(pos).getNode().getStyle();
					seriesQ.getData().get(pos).getNode().setStyle(tmpEstilo);
					seriesQ.getData().get(izq).getNode().setStyle(tmpEstiloDos);
					seriesQ.getData().get(pos).setYValue(listaQ.obtenerNodo(pos));
					seriesQ.getData().get(izq).setYValue(listaQ.obtenerNodo(izq));
					pos = izq;
				}
				break;
			}
		}
		if (pos - 1 > ini) {
			reduceRecursivo(ini, pos - 1);
		}
		if (fin > (pos + 1)) {
			reduceRecursivo(pos + 1, fin);
		}
	}
	public int reduceItertivo(int ini, int fin, int pos) throws IndiceFueraDeRango {
		int izq = 0;
		int der = 0;
		int aux = 0;
		boolean band;
		izq = ini;
		der = fin;
		pos = ini;
		band = true;
		while (band == true) {
			while (listaQI.obtenerNodo(pos) <= listaQI.obtenerNodo(der) && pos != der) {
				der--;
			}
			if (pos == der) {
				band = false;
			} else {
				aux = listaQI.obtenerNodo(pos);
				listaQI.asignar(listaQI.obtenerNodo(der), pos);
				listaQI.asignar(aux, der);
				String tmpEstilo = seriesQI.getData().get(der).getNode().getStyle();
				String tmpEstiloDos = seriesQI.getData().get(pos).getNode().getStyle();
				seriesQI.getData().get(pos).getNode().setStyle(tmpEstilo);
				seriesQI.getData().get(der).getNode().setStyle(tmpEstiloDos);
				seriesQI.getData().get(pos).setYValue(listaQI.obtenerNodo(pos));
				seriesQI.getData().get(der).setYValue(listaQI.obtenerNodo(der));
				pos = der;
				while (listaQI.obtenerNodo(pos) >= listaQI.obtenerNodo(izq) && pos != izq) {
					izq++;
				}
				if (pos == izq) {
					band = false;
				} else {
					aux = listaQI.obtenerNodo(pos);
					listaQI.asignar(listaQI.obtenerNodo(izq), pos);
					listaQI.asignar(aux, izq);
					tmpEstilo = seriesQI.getData().get(izq).getNode().getStyle();
					tmpEstiloDos = seriesQI.getData().get(pos).getNode().getStyle();
					seriesQI.getData().get(pos).getNode().setStyle(tmpEstilo);
					seriesQI.getData().get(izq).getNode().setStyle(tmpEstiloDos);
					seriesQI.getData().get(pos).setYValue(listaQI.obtenerNodo(pos));
					seriesQI.getData().get(izq).setYValue(listaQI.obtenerNodo(izq));
					pos = izq;
				}
			}
			break;
		}
		return pos;
	}
}
