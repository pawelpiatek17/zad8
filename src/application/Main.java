package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import maze.Maze;
 
public class Main extends Application {
 
    public static void main(String[] args) {
        launch(args);
    }
 
    @Override
    public void start(Stage stage) {
        Pane root = null;
        FXMLLoader loader = new FXMLLoader(
        		  getClass().getResource(
        		    "Sample.fxml"
        		  )
        		);
		try {
			root = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        SampleController sC = loader.getController();
        Scene scene = new Scene(root);
        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        stage.show();
        Maze maze = new Maze(scene);
        sC.setMaze(maze);
        maze.start("file/standardmaze.txt");
    }
    
}
