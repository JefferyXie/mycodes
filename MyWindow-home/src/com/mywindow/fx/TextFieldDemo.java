package com.mywindow.fx;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class TextFieldDemo extends Application {
	
	TextField tf;
	Label response;
	
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stg) {
		stg.setTitle("TextField demo");
		FlowPane rootNode = new FlowPane(10, 10);
		rootNode.setAlignment(Pos.CENTER);
		
		Scene myScene = new Scene(rootNode, 300, 200);
		stg.setScene(myScene);
		
		response = new Label("Enter name:");
		
		Button btnGetText = new Button("Get Name");
		tf = new TextField();
		tf.setPromptText("Enter a name.");
		tf.setPrefColumnCount(15);
		tf.setOnAction((ae) -> response.setText("Enter pressed. Name is: " + tf.getText()));
		
		btnGetText.setOnAction(ae -> response.setText("Button pressed. Name is: " + tf.getText()));
		
		Separator separator = new Separator();
		separator.setPrefWidth(200);
		
		rootNode.getChildren().addAll(tf, btnGetText, separator, response);
		
		stg.show();
	}

}
