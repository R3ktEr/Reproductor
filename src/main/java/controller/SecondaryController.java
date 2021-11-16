package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class SecondaryController {

	@FXML
	private TextField tNewName;
	
	@FXML
	private TextField tListDescription;
	
	@FXML
	private Button bDone;
	
	private String name;
	private String description;
	
    @FXML
    public void typedName(ActionEvent event) {
    	name=tNewName.getText();
    	description=tListDescription.getText();
    	Stage stage=(Stage) this.bDone.getScene().getWindow();
		stage.close();
    }
    
    @FXML
    public void checkFields(KeyEvent event) {
    	if(!this.tNewName.getText().isEmpty()&&!this.tListDescription.getText().isEmpty()) {
    		this.bDone.setDisable(false);
    	}else{
    		this.bDone.setDisable(true);
    	}
    }

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public void setFieldsToNull() {
		this.name=null;
		this.description=null;
	}
}