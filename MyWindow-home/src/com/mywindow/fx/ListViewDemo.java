package com.mywindow.fx;

import javafx.application.*;
import javafx.beans.value.*;
import javafx.beans.value.ChangeListener;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.stage.*;

public class ListViewDemo extends Application {

	Label response;
	
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stg) {
		stg.setTitle("ListView Demo");
		FlowPane rootNode = new FlowPane(10, 10);
		rootNode.setAlignment(Pos.CENTER);
		
		Scene myScene = new Scene(rootNode, 300, 200);
		stg.setScene(myScene);
		
		response = new Label("Select computer type");
		
		ObservableList<String> computerTypes = FXCollections.observableArrayList(
				"Smartphone", "Tablet", "Notebook", "Desktop");
		ListView<String> lvComputers = new ListView<String>(computerTypes);
		lvComputers.setPrefSize(100, 70);
		
		MultipleSelectionModel<String> lvSelModel = lvComputers.getSelectionModel();
		
		lvSelModel.selectedItemProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> changed, String oldV, String newV) {
				response.setText("Computer selected is: " + newV);
			}
		});
		
		rootNode.getChildren().addAll(lvComputers, response);
		stg.show();
	}

}
