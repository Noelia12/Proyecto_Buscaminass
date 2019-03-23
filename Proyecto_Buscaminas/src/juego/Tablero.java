package juego;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;


public class Tablero {

    private static int divisor = 50;//define tamaño de celdas
    private static final int ancho = 1000;// ancho del root
    private static final int alto = 800;// altura del root

    private static  int filas = ancho / divisor;//celdas en x
    private static  int columnas = alto/ divisor;//celdas en y
    private celd[][] rejilla = new celd[filas][columnas];
    private Scene scene;
    
    public Tablero() {
    }

    public Parent crearcontenido() {
        Pane contenedor = new Pane();
        contenedor.setPrefSize(ancho, alto);

        for (int y = 0; y < columnas; y++) {
            for (int x = 0; x < filas; x++) {
                celd celda = new celd(x, y, Math.random() < 0.2);
                rejilla[x][y] = celda;
                contenedor.getChildren().add(celda);
            }
        }

        for (int y = 0; y < columnas; y++) {
            for (int x = 0; x < filas; x++) {
                celd celda = rejilla[x][y];

                if (celda.conmina)
                    continue;

                long mina = getVecinos(celda).stream().filter(t -> t.conmina).count();

                if (mina > 0)
                    celda.text.setText(String.valueOf(mina));
            }
        }

        return contenedor;
    }

    private List<celd> getVecinos(celd celdasv) {
        List<celd> vecinos = new ArrayList<>();

        int[] coordenadas = new int[] {-1, -1,-1, 0,-1, 1, 0, -1, 0, 1, 1, -1,1, 0, 1, 1 };

        for (int i = 0; i < coordenadas.length; i++) {
            int dx = coordenadas[i];
            int dy = coordenadas[++i];

            int nuevoX = celdasv.x + dx;
            int nuevoY = celdasv.y + dy;

            if (nuevoX >= 0 && nuevoX < filas
                    && nuevoY >= 0 && nuevoY < columnas) {
                vecinos.add(rejilla[nuevoX][nuevoY]);
            }
        }

        return vecinos;
    }

    private class celd extends StackPane {
        private int x, y;
        private boolean conmina;
        private boolean isOpen = false;

        private Rectangle rect = new Rectangle(divisor-3, divisor-3);//tamaño de las celdas
        private Text text = new Text();
        public Alert mensaje = new Alert(AlertType.CONFIRMATION);
    

        public celd(int x, int y, boolean conmina) {
            this.x = x;
            this.y = y;
            this.conmina = conmina;
            
            rect.setFill(Color.LIGHTSEAGREEN);
            rect.setStroke(Color.RED);
            mensaje.setTitle("BUSCAMINAS");
            mensaje.setHeaderText(null);
            mensaje.initStyle(StageStyle.UTILITY);
            mensaje.setContentText("perdiste!!\n¿Quieres intentarlo de nuevo ?");
            text.setFont(Font.font(25));
            text.setText(conmina ? "X" : "");
            text.setFill(Color.DODGERBLUE);
            text.setVisible(false);
  
            getChildren().addAll(text,rect);

            setTranslateX(x * divisor);
            setTranslateY(y * divisor);

            setOnMouseClicked(e -> open(e));
        }
        public void open(MouseEvent e) {
            if (isOpen)
                return;
            if(e.getButton().name().equals("PRIMARY")) {
            	

            	System.out.println("Click izquierdo");
            }
            if(e.getButton().name().equals("SECONDARY")) {
            	System.out.println("Click derecho");
            
            }
            if (conmina) {
            	Optional<ButtonType>result=mensaje.showAndWait();
            	if(result.get()==ButtonType.CANCEL) {
            		System.exit(0);
            	}else if (result.get()==ButtonType.OK) {
            		 scene.setRoot(crearcontenido());
            	}

               return;
            }
            isOpen = true;
            text.setVisible(true);
            rect.setFill(null);
            if (text.getText().isEmpty()) {
                List<celd> lista = getVecinos(this);
                for(int i = 0; i < lista.size(); i++) {
                	lista.get(i).open(e);
                }
            }
        }
    }
}

