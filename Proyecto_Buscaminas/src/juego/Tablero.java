package juego;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Tablero {

	protected static int divisor = 200;// define tamaño de celdas
	protected static final int ancho = 1000;// ancho del root
	protected static final int alto = 800;// altura del root

	protected static int filas = ancho / divisor;// celdas en x
	protected static int columnas = alto / divisor;// celdas en y
	protected static Celd[][] rejilla = new Celd[filas][columnas];

	static int espacio = 1;
	boolean numrand;

	double[] vector = null;
	Text minas;
	protected Celd celda;

	public Tablero() {
	}

	public Parent crearcontenido() {
		Pane contenedor = new Pane();
		contenedor.setPrefSize(ancho, alto);

		for (int y = 0; y < columnas; y++) {
			for (int x = 0; x < filas; x++) {
				numrand = Math.random() < 0.2;
				
				 celda = new Celd (x, y,numrand);
				rejilla[x][y] = celda;
				 if (numrand == true) {

						vector = new double[espacio];
						++espacio;
					}
				contenedor.getChildren().add(celda);
			}
		}   
		minas = new Text("hay "+vector.length+" minas");
		minas.setFont(Font.font(20));
		minas.setTranslateX(20);
		minas.setTranslateY(20);
		contenedor.getChildren().add(minas);

		for (int y = 0; y < columnas; y++) {
			for (int x = 0; x < filas; x++) {
				Celd celda = rejilla[x][y];

				if (celda.conmina)
					continue;

				long mina = getVecinos(celda).stream().filter(t -> t.conmina).count();

				if (mina > 0)
					celda.text.setText(String.valueOf(mina));
			}
		}

		return contenedor;
	}

	protected static List<Celd> getVecinos(Celd celdasv) {
		List<Celd> vecinos = new ArrayList<>();

		int[] coordenadas = new int[] { -1, -1, -1, 0, -1, 1, 0, -1, 0, 1, 1, -1, 1, 0, 1, 1 };

		for (int i = 0; i < coordenadas.length; i++) {
			int dx = coordenadas[i];
			int dy = coordenadas[++i];

			int nuevoX = celdasv.x + dx;
			int nuevoY = celdasv.y + dy;

			if (nuevoX >= 0 && nuevoX < filas && nuevoY >= 0 && nuevoY < columnas) {
				vecinos.add(rejilla[nuevoX][nuevoY]);
			}
		}

		return vecinos;
	}


}
