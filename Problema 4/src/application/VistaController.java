package application;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.control.ChoiceBox;
import fes.aragon.problemaCuatro.modelo.Puntos;
import fes.aragon.utilerias.dinamicas.pila.*;

public class VistaController implements Initializable {
	@FXML
	private Pane panel;
	@FXML
	private Pane controlPane;
	@FXML
	private ImageView imagen;
	@FXML
	private ImageView imagenAux;
	@FXML
	private Label lblLlamada;
	@FXML
	private Button btnLimpiar;
	@FXML
	private TextField txtLlamadas;
	private PixelReader pixel;
	private WritableImage escribir;
	private PixelWriter pixelEscribir;
	@FXML
	private ChoiceBox<String> cmbLLamada;
	@FXML
	private ColorPicker colorPicker;

	@FXML
	public void eventoRaton(MouseEvent event) {
		Color color = pixel.getColor((int) event.getX(), (int) event.getY());
		/*
		 * System.out.println(event.getX() + "," + event.getY() + "," + color.getRed() +
		 * "," + color.getGreen() + "," + color.getBlue() + "," + color.getOpacity());
		 */
		int seleccion = cmbLLamada.getSelectionModel().getSelectedIndex();
		int llamadas = Integer.parseInt(txtLlamadas.getText());
		// System.out.println(seleccion);
		if (seleccion == -1 || seleccion == 0) {
			llenar((int) event.getX(), (int) event.getY(), llamadas, "inicio");
		} else {
			llenarPila((int) event.getX(), (int) event.getY(), llamadas, "inicio");
		}
	}

	/**
	 * Metodo que pinta la imagen mediante llamadas recursivas
	 * 
	 * @param x       Punto x
	 * @param y       Punto y
	 * @param llamada Cantidad de llamadas
	 * @param lectura Lugar del punto
	 */
	public void llenar(int x, int y, int llamada, String lectura) {
		if ((x < escribir.getWidth() && y < escribir.getHeight()) && (x >= 0 && y >= 0)) {
			Color colores = pixel.getColor(x, y);
			if (llamada <= 0) {
				return;
			} else {
				if (colores.equals(new Color(1, 1, 1, 1))) {
					Color d = colorPicker.getValue();
					pixelEscribir.setColor(x, y, d);
					llenar(x + 1, y, --llamada, "der");
					llenar(x - 1, y, --llamada, "izq");
					llenar(x, y + 1, --llamada, "abajo");
					llenar(x, y - 1, --llamada, "arriba");
					llenar(x - 1, y - 1, --llamada, "arriba iz");
					llenar(x + 1, y - 1, --llamada, "arriba der");
					llenar(x - 1, y + 1, --llamada, "abajo iz");
					llenar(x + 1, y + 1, --llamada, "abajo der");
				} else {
					return;
				}
			}
		}
	}

	/**
	 * Metodo que pints ls imsgrn mediante llamadas iterativas y pilas
	 * 
	 * @param x       Punto x
	 * @param y       Punto y
	 * @param puntos  Cantidad de puntos
	 * @param lectura Lugar del punto
	 */
	public void llenarPila(int x, int y, int puntos, String lectura) {
		Pila<Puntos> pila = new Pila<Puntos>();
		pila.insertar(new Puntos(x, y));
		try {
			do {
				Puntos p = pila.extraer();
				Color colores = pixel.getColor(p.getXx(), p.getYy());
				if (colores.equals(new Color(1, 1, 1, 1))) {
					Color d = colorPicker.getValue();
					pixelEscribir.setColor(p.getXx(), p.getYy(), d);
					// derecha
					pila.insertar(new Puntos(p.getXx() + 1, p.getYy()));
					// izquierda
					pila.insertar(new Puntos(p.getXx() - 1, p.getYy()));
					// arriba
					pila.insertar(new Puntos(p.getXx(), p.getYy() - 1));
					// abajo
					pila.insertar(new Puntos(p.getXx(), p.getYy() + 1));
					// arriba izquiera
					pila.insertar(new Puntos(p.getXx() - 1, p.getYy() - 1));
					// Arriba derecha
					pila.insertar(new Puntos(p.getXx() + 1, p.getYy() - 1));
					// Abajo izquiera
					pila.insertar(new Puntos(p.getXx() - 1, p.getYy() + 1));
					// Abajo derecha
					pila.insertar(new Puntos(p.getXx() + 1, p.getYy() + 1));
				}
			} while (true && pila.getDatos() < puntos * 8);
		} catch (Exception e) {
			// System.out.println("Se termino");
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cmbLLamada.getItems().add("Recursiva");
		cmbLLamada.getItems().add("Iterativa");
		cmbLLamada.setValue("Recusriva");
		txtLlamadas.setText("20000");
		panel.setMaxWidth(imagen.getImage().getWidth());
		panel.setMaxHeight(imagen.getImage().getHeight() + controlPane.getHeight());
		controlPane.setLayoutY(imagen.getImage().getHeight());
		colorPicker.setValue(Color.RED);
		imagenAux = new ImageView(imagen.getImage());
		pixel = imagen.getImage().getPixelReader();
		escribir = new WritableImage((int) imagen.getImage().getWidth(), (int) imagen.getImage().getHeight());
		pixelEscribir = escribir.getPixelWriter();
		for (int x = 0; x < imagen.getImage().getWidth(); x++) {
			for (int y = 0; y < imagen.getImage().getHeight(); y++) {
				Color color = pixel.getColor(x, y);
				pixelEscribir.setColor(x, y, color);
			}
		}
		imagen.setImage(escribir);
		pixel = imagen.getImage().getPixelReader();
		JOptionPane.showMessageDialog(null, "El uso de llamadas excesivas podria colapsar el programa", "Advertencia",
				JOptionPane.INFORMATION_MESSAGE);

	}

	@FXML
	void limpiar(ActionEvent event) {
		pixel = imagenAux.getImage().getPixelReader();
		escribir = new WritableImage((int) imagen.getImage().getWidth(), (int) imagen.getImage().getHeight());
		pixelEscribir = escribir.getPixelWriter();
		for (int x = 0; x < imagen.getImage().getWidth(); x++) {
			for (int y = 0; y < imagen.getImage().getHeight(); y++) {
				Color color = pixel.getColor(x, y);
				pixelEscribir.setColor(x, y, color);
			}
		}
		imagen.setImage(escribir);
		pixel = imagen.getImage().getPixelReader();

	}
}
