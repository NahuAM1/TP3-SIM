import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Interfaz extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Interfaz.class.getResource("Ventana.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1120, 915);
        stage.setTitle("Interfaz Trabajo Practico N-1");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
