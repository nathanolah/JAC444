/**********************************************
Workshop 6
Course: JAC444 - Semester 4
Last Name: Olah 
First Name: Nathan  
ID: 124723198 
Section: NBB 
This assignment represents my own work in accordance with Seneca Academic Policy.
Signature Date: 2020/07/10
**********************************************/

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Task1 extends Application {
	Name babyName = new Name();
	public boolean clearFields = false; 
	
	// Displays an alert message
	public static void alert(String errMessage) {
		Alert errorAlert = new Alert(AlertType.INFORMATION);
		errorAlert.setTitle("Invalid Input");
		errorAlert.setHeaderText("Input not valid");
		errorAlert.setContentText(errMessage);
		errorAlert.showAndWait();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER_LEFT);
		
		pane.setHgap(10);
		pane.setVgap(10);
		
		pane.setPadding(new Insets(20, 20, 20, 20));
		Scene scene = new Scene(pane, 400, 270);
		
		// Year
		Label yearLabel = new Label("Enter the Year: ");
		pane.add(yearLabel, 0, 1);
		final TextField yearField = new TextField();
		pane.add(yearField, 1, 1);
		
		// Gender
		Label genderLabel = new Label("Enter the Gender: ");
		pane.add(genderLabel, 0, 2);
		final TextField genderField = new TextField();
		genderField.setPrefWidth(30);
        genderField.setMaxWidth(30);
		pane.add(genderField, 1, 2);
		
		// Name
		Label nameLabel = new Label("Enter the Name: ");
		pane.add(nameLabel, 0, 3);
		final TextField nameField = new TextField();
		pane.add(nameField, 1, 3);
		
		// Sets width of buttons
        VBox vBox = new VBox();
        vBox.setPrefWidth(65);
        
        Button submitBtn = new Button("Submit Query");
        Button exitBtn = new Button("Exit");
        
        submitBtn.setMinWidth(vBox.getPrefWidth());
        exitBtn.setMinWidth(vBox.getPrefWidth());
        
		HBox hBox = new HBox(10);
		hBox.setAlignment(Pos.CENTER);
		hBox.getChildren().addAll(submitBtn, exitBtn);
		
		// Validate fields
		yearField.focusedProperty().addListener((arg0, oldValue, newValue) -> {
			if (!newValue) {
				if (!yearField.getText().matches("20(09|1[0-8])|^\\s*$")) { // matches between 2009 - 2018
						yearField.setText("");
						alert("Years must be between 2009 - 2018");						
				}
			}
		});
		
		genderField.focusedProperty().addListener((arg0, oldValue, newValue) -> {
			if (!newValue) {
				if (!genderField.getText().matches("[mMfF]|^\\s*$")) {
					genderField.setText("");
					alert("Field must be 'M' for Male or 'F' for Female");												
				}
			}
			
		});
		
		submitBtn.setOnAction(e -> {
			String year = yearField.getText();
			String gender = genderField.getText();
			String name = nameField.getText();
			
			// Open a new window passing these values
			String rank = babyName.readFile(year, gender, name);
			
			clearFields = rankingScreen(rank, year, name, gender);
			if (clearFields == true) {
				yearField.setText("");
				genderField.setText("");
				nameField.setText("");
			}
			
		});
		
		// Exit Application
		exitBtn.setOnAction(e -> { Platform.exit(); });
		
		pane.add(hBox, 1, 6);
		primaryStage.setTitle("Search Name Ranking Application");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	// Displays Rank of name
	public boolean rankingScreen(String rank, String year, String name, String gender) {
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(10);
		pane.setVgap(10);
		
		pane.setPadding(new Insets(20, 20, 20, 20));
		Scene scene = new Scene(pane, 400, 270);
		Stage stage = new Stage();
		
		Text text1 = new Text();
		Text text2 = new Text();
		
		String child_gender = (gender.charAt(0) == 'm' || gender.charAt(0) == 'M') ? "Boy" : "Girl";
		if (rank.compareToIgnoreCase("Not Found") != 0) {
			text1.setText(child_gender + " name " + name + " is ranked #" + rank + " in " + year);			
		} else {
			text1.setText(name + " is not found.");
		}
		
		text2.setText("Do you want to search for another name:");
		
		pane.add(text1, 1, 1);
		pane.add(text2, 1, 4);
		
		// Sets width of buttons
		VBox vBox = new VBox();
		vBox.setPrefWidth(65);
		
		Button yesBtn = new Button("Yes");
		Button noBtn = new Button("No");
		
        yesBtn.setMinWidth(vBox.getPrefWidth());
        noBtn.setMinWidth(vBox.getPrefWidth());
        
        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(yesBtn, noBtn);
        pane.add(hBox, 1, 6);

        yesBtn.setOnAction(e -> {
        	clearFields = true;
        	stage.close();
        });
        
        // Exit Application
        noBtn.setOnAction(e -> { Platform.exit(); });
        
		stage.setTitle("Search Name Ranking Application");  
		stage.setScene(scene);   
		stage.show();
		
		return clearFields;	
	}
	
}


class Name {
	ArrayList<String> ranks = new ArrayList<String>();
	ArrayList<String> maleNames = new ArrayList<String>();
	ArrayList<String> m_occurences = new ArrayList<String>();
	ArrayList<String> femaleNames = new ArrayList<String>();
	ArrayList<String> f_occurences = new ArrayList<String>();
	
	// Searches for the name rank
	public String findName(String gender, String name) {
		String rank = "Not Found";
		
		if (gender.charAt(0) == 'm' || gender.charAt(0) == 'M') {
			for (int i = 0; i < maleNames.size(); i++) {	
				if (name.compareToIgnoreCase(maleNames.get(i)) == 0) {
					rank = ranks.get(i);
				}
			}
			
		} else if (gender.charAt(0) == 'f' || gender.charAt(0) == 'F') {
			for (int i = 0; i < femaleNames.size(); i++) {
				if (name.compareToIgnoreCase(femaleNames.get(i)) == 0) {
					rank = ranks.get(i);
				}
			}
		}
		
		return rank;
	}
	
	// Reads all the fields from the file
	public String readFile(String year, String gender, String name) {
		String rank = "";
		
		// Reads the file
		try (RandomAccessFile file = new RandomAccessFile("./Babynames files/babynamesranking" + year + ".txt", "r")) {
			String str;
			while ((str = file.readLine()) != null) {
				String[] str_words = str.split("\\s+|\t");
				ranks.add(str_words[0]);
				maleNames.add(str_words[1]);
				m_occurences.add(str_words[2]);
				femaleNames.add(str_words[3]);
				f_occurences.add(str_words[4]);
			}
			
			rank = findName(gender, name);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return rank;
	}
	
}
