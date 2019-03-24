 package juego;

import java.util.List;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;
import juego.Tablero;

class Celd extends StackPane {
	protected  int x, y;
	protected boolean conmina;
	protected boolean isOpen = false;
	protected Rectangle rect = new Rectangle(Tablero.divisor - 3, Tablero.divisor - 3);// tamaño de las celdas
	protected Text text = new Text();
	protected Text band = new Text();
	public Alert mensaje = new Alert(AlertType.INFORMATION);

	public Celd(int x, int y, boolean conmina) {
		this.x = x;
		this.y = y;
		this.conmina = conmina;
		rect.setFill(Color.LIGHTSEAGREEN);
		rect.setStroke(Color.RED);
		mensaje.setTitle("BUSCAMINAS");
		mensaje.setHeaderText(null);
		mensaje.initStyle(StageStyle.UTILITY);
		mensaje.setContentText("PERDISTE!!\nHas tocado una mina :(");
		text.setFont(Font.font(40));
		text.setText(conmina ? "X" : "");
		text.setFill(Color.GOLD);
		text.setVisible(false);
		band.setFont(Font.font(40));
		band.setText("X");
		band.setFill(Color.RED);
		band.setVisible(false);
		getChildren().addAll(text, rect, band);
		setTranslateX(x * Tablero.divisor);
		setTranslateY(y * Tablero.divisor);
		setOnMouseClicked(e -> open(e));
	}

	public void open(MouseEvent e) {
		if (isOpen)
			return;
		if (e.getButton().name().equals("PRIMARY")) {
			isOpen = true;
			System.out.println("Click izquierdo");

			if (conmina) {
				Optional<ButtonType> result = mensaje.showAndWait();

				if (result.get() == ButtonType.OK) {
					System.exit(0);
				}
				return;
			}
			isOpen = true;
			text.setVisible(true);
			rect.setFill(null);
			if (text.getText().isEmpty()) {
				List<Celd> lista = Tablero.getVecinos(this);
				for (int i = 0; i < lista.size(); i++) {
					lista.get(i).open(e);
				}
			}
		}
		if (e.getButton().name().equals("SECONDARY")) {

			isOpen = false;
			System.out.println("Click derecho");
			band.setVisible(true);
			
			if (band.isVisible()&& e.getButton().name().equals("SECONDARY")) {
				band.setVisible(false);
			}
		}

	}
}
