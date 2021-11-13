package application;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.image.ImageView;

import javax.swing.JOptionPane;

import fes.aragon.problemaDos.operaciones.Operaciones;

public class VistaController {
	@FXML
	private ImageView img;
	@FXML
	private TextField txtPosfija;
	@FXML
	private Label lblResultado;
	@FXML
	private Button btnCalcular;
	@FXML
	private Button btnSalir;
	@FXML
	private TextField txtInterfija;
	@FXML
	private Button btnConvertir;

	// Event Listener on Button[#btnCalcular].onAction
	@FXML
	public void calcular(ActionEvent event) {
		if (txtPosfija.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Campo vacio Ingrese Datos", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			double resultado = Operaciones.evaluarPosfija(txtPosfija.getText());
			lblResultado.setText("Resultado: " + resultado);
		}

	}

	// Event Listener on Button[#btnConvertir].onAction
	@FXML
	public void convertir(ActionEvent event) throws Exception {
		if (txtInterfija.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Campo vacio Ingrese Datos", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			String exp = Operaciones.interPosfija(this.txtInterfija.getText());
			this.txtPosfija.setText(exp);
		}
	}

	// Event Listener on Button[#btnSalir].onAction
	@FXML
	public void salir(ActionEvent event) {
		System.exit(0);
	}
}
