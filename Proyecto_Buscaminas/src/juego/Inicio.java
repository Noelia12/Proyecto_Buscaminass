package juego;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Inicio extends Application implements EventHandler<ActionEvent> {
	//atributos
	private Button jugar;
	private ComboBox<String> cbx;
	private StackPane Contenedor;
	private Scene escena, ventana2;
	private Image fondo, mina;
	private ImageView fondoo, minaa;
	private Text msg;
	private Tablero tab;
	private Stage stage2;

	@Override
	public void start(Stage p) {// escenario
		try {

			Contenedor = new StackPane();// root
			escena = new Scene(Contenedor);
			escena.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			// detalles de ventana
			p.getIcons().add(new Image("juego/mina.png"));
			p.setTitle("BUSCAMINAS");// titulo de la ventana
			p.setMinWidth(1000);
			p.setMinHeight(800);
//			fondo = new Image("juego/fondo2.jpg");
			fondo = new Image("juego/busca.jpg");
//			fondo = new Image("juego/fondo3.jpg");

			fondoo = new ImageView();
			fondoo.setFitWidth(1000);
			fondoo.setFitHeight(800);
			fondoo.setImage(fondo);
			Contenedor.getChildren().add(fondoo);
			// añadir titulo del juego
			msg = new Text("BUSCAMINAS ");
			msg.setFill(Color.WHITE);
			msg.setFont(Font.font("OCR A Extended",FontWeight.BLACK, 140));
			msg.setTranslateY(-300);
			msg.setTranslateX(70);
			Contenedor.getChildren().addAll(msg);
            // sonido
//			AudioClip audio = new AudioClip("j/audio.mp3");
//			audio.play();
//			audio.setVolume(0.85);
//			String path = "C:/Mi Musica/AC_DC/Back in Black/06 Back in Black.mp3";
//			Media media = new Media(new File(path).toURI().toString());
//			MediaPlayer mediaPlayer = new MediaPlayer(media);
//			mediaPlayer.setAutoPlay(true);
//			MediaView mediaView = new MediaView(mediaPlayer);
            //animacion 3d
			mina = new Image ( "juego/mina.png");
			minaa = new ImageView (mina);
			minaa.setTranslateX(-150);
			minaa.setFitHeight(600);
			minaa.setFitWidth(600);
			Contenedor.getChildren ().add(minaa); 
			RotateTransition rotar = new RotateTransition (); 
			rotar.setNode (minaa);
			rotar.setDuration (Duration. millis (3000));
        	rotar.setAxis(Rotate.Z_AXIS);
			rotar.setByAngle (360);
			rotar.setCycleCount (Timeline.INDEFINITE);
			rotar.setInterpolator (Interpolator. LINEAR);
			rotar.play(); 
//			//boton jugar
			Image play = new Image ("juego/boton.png");
			ImageView on = new ImageView (play);
			on.setFitHeight(100);
			on.setFitWidth(100);
			jugar = new Button();
			jugar.setTranslateX(250);
			jugar.setTranslateY(-100);
			jugar.setOnAction(this);
			jugar.setGraphic(on);
		
			// seleccionar modo de dificultad
			ObservableList<String> modos = FXCollections.observableArrayList();
			modos.addAll("principiante", "intermedio", "avanzado");
			cbx = new ComboBox<>(modos);
			cbx.setTranslateX(250);
			cbx.setTranslateY(200);
			cbx.setValue("SELECCIONA NIVEL");
			cbx.setOnAction(this);
			cbx.setMaxHeight(40);
			Contenedor.getChildren().addAll(jugar,cbx);
			p.setResizable(false);
			p.setScene(escena);
			p.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void VentanaNueva() {

		tab= new Tablero();
		stage2 = new Stage();
		ventana2 = new Scene(tab.crearcontenido());
		stage2.getIcons().add(new Image("juego/mina.png"));
		stage2.setTitle("BUSCAMINAS");
		stage2.setResizable(false);
        stage2.setScene(ventana2);      
        stage2.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void handle(ActionEvent evento) {
		if (evento.getSource()instanceof Button) {
			if((Button)evento.getSource()== jugar) {	
				VentanaNueva();
				
			}
		}		
	}
}
