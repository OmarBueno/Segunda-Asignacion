package fes.aragon.herramientas;

import fes.aragon.except.IndiceFueraDeRango;
import fes.aragon.utilerias.dinamicas.listasimple.ListaSimple;
import fes.aragon.utilerias.dinamicas.pila.Pila;
import javafx.scene.chart.XYChart;

public class Ordenamientos {
	
	public static void burbuja(ListaSimple<Integer> listaB, XYChart.Series<String, Number> seriesB) {
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
	}
	public static void seleccion(ListaSimple<Integer> listaS, XYChart.Series<String, Number> seriesS) {
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
						break;
					}
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
	}
	public static void insercion(ListaSimple<Integer> listaI, XYChart.Series<String, Number> seriesI) {
		int aux;
		int k;
		for (int i = 1; i < listaI.getLongitud(); i++) {
			try {
				aux = listaI.obtenerNodo(i);
				k = i - 1;
				while (k >= 0 && aux < listaI.obtenerNodo(k)) {
					listaI.asignar(listaI.obtenerNodo(k), k + 1);
					seriesI.getData().get(k + 1).setYValue(listaI.obtenerNodo(k + 1));
					k = k - 1;
					break;
				}
				listaI.asignar(aux, k + 1);
				seriesI.getData().get(k + 1).setYValue(listaI.obtenerNodo(k + 1));
			} catch (IndiceFueraDeRango e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void sacudida(ListaSimple<Integer> listaSa, XYChart.Series<String, Number> seriesSa) {
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
	}

	public static void reduceRecursivo(int ini, int fin, ListaSimple<Integer> listaQ,
			XYChart.Series<String, Number> seriesQ) throws IndiceFueraDeRango {
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
			reduceRecursivo(ini, pos - 1, listaQ, seriesQ);
		}
		if (fin > (pos + 1)) {
			reduceRecursivo(pos + 1, fin, listaQ, seriesQ);
		}
	}

	public static void rapidoIterativo(ListaSimple<Integer> listaQI, XYChart.Series<String, Number> seriesQI) {
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
				pos = Ordenamientos.reduceItertivo(ini, fin, pos, listaQI, seriesQI);
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
	}

	public static int reduceItertivo(int ini, int fin, int pos, ListaSimple<Integer> listaQI,
			XYChart.Series<String, Number> seriesQI) throws IndiceFueraDeRango {
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
