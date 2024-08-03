package ec.edu.espol.proyectoestructuras2;

import ec.edu.espol.clases.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayDeque;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static ABD arbolMain;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        ABD arbol = new ABD();
        
        //Consigo los nombres de los txt
        
        String r = "answers.txt";
        String p = "questions.txt";
        arbol.cargar(p, r);
        
        //El arbol está lleno
        
        System.out.println(arbol);
        System.out.println(arbol.altura());
        
        //Adivinar un animal
        
        //Se pide el numero de preguntas a responder y se consiguen sus respectivas respuestas
        
        //Ejemplo
        
        
        ArrayDeque<String> respuestas = new ArrayDeque<>();
        respuestas.offer("si"); respuestas.offer("si"); respuestas.offer("si");
        System.out.println(arbol.adivinarAnimal(respuestas)); //Una respuesta
        respuestas.offer("no");
        System.out.println(arbol.adivinarAnimal(respuestas));//Mas de una respuesta
        respuestas.offer("si"); respuestas.offer("si"); respuestas.offer("no");
        System.out.println(arbol.adivinarAnimal(respuestas));//No hay respuesta
        
        arbolMain = arbol;
        
        launch();
    }

}