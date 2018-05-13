package application;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import maze.Maze;

public class SampleController {
	private Maze maze;
	@FXML 
	private ToggleGroup myToggleGroup;
	
	public SampleController(){
	}
	
	public void setMaze(Maze maze) {
		this.maze = maze;
	}

	@FXML
	private void initialize(){
		myToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				RadioButton radioButton = (RadioButton) myToggleGroup.getSelectedToggle();
				if(radioButton.getText().equals("Standard Maze")){
					maze.start("file/standardmaze.txt");
				}
				else{
					maze.start("file/magicmaze.txt");
				}
			}
		});
	}
}
