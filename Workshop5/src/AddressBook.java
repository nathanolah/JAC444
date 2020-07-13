/***********************************************  
 * Workshop # 5 
 * Course: JAC444 - Semester 4
 * Last Name: Olah 
 * First Name: Nathan 
 * ID: 124723198
 * Section: B 
 * This assignment represents my own work in accordance with Seneca Academic Policy. 
 * Signature Date: 2020/07/04 
 **********************************************/ 

import java.io.IOException;
import java.io.RandomAccessFile;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;

public class AddressBook extends Application {
	private Address address = new Address();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER_LEFT);
		
		// Set the gap between controls
		pane.setHgap(10);
		pane.setVgap(10);
		
		pane.setPadding(new Insets(20, 20, 20, 20));
		Scene scene = new Scene(pane, 570, 270);
        
		// Fields
		// First Name
        Label fName = new Label("First Name: ");
        pane.add(fName, 0, 1);
        final TextField fNameField = new TextField();
        fNameField.setPrefWidth(370);
        fNameField.setMaxWidth(370);
        pane.add(fNameField, 1, 1);
        
        // Last Name
        Label lName = new Label("Last Name: ");
        pane.add(lName,0,2);
        final TextField lNameField = new TextField();
        lNameField.setPrefWidth(370);
        lNameField.setMaxWidth(370);
        pane.add(lNameField, 1, 2);
        
        // City 
        Label city = new Label("City: ");
        pane.add(city,0,3);
        final TextField cityField = new TextField();
        cityField.setPrefWidth(100);
        cityField.setMaxWidth(100);
        pane.add(cityField, 1, 3);
        
        // Province
        Label province = new Label("Province: ");
        pane.add(province, 0, 4);
        ComboBox<String> provinceBox = new ComboBox<String>();
        provinceBox.getItems().addAll("Ontario", "Quebec", "Alberta", "Saskatchewan", "Britsh Columbia", "Manitoba", "Yukon",
        		"Northwest Territories", "Nunavut", "Nova Scotia", "Prince Edward Island", "New Brunswick", "Newfoundland and Labrador");
        pane.add(provinceBox, 1, 4);
        
        // Postal Code
        Label postalCode = new Label("Postal Code: ");
        pane.add(postalCode, 0, 5);
        final TextField postalCodeField = new TextField();
        postalCodeField.setPrefWidth(100);
        postalCodeField.setMaxWidth(100);
        pane.add(postalCodeField, 1, 5); 
        
        // Sets width of buttons
        VBox vBox = new VBox();
        vBox.setPrefWidth(65);
        
		// Buttons
		Button addBtn = new Button("Add");
		Button firstBtn = new Button("First");
		Button nextBtn = new Button("Next");
		Button previousBtn = new Button("Previous");
		Button lastBtn = new Button("Last");
		Button updateBtn = new Button("Update");
        
		addBtn.setMinWidth(vBox.getPrefWidth());
		firstBtn.setMinWidth(vBox.getPrefWidth());
		nextBtn.setMinWidth(vBox.getPrefWidth());
		previousBtn.setMinWidth(vBox.getPrefWidth());
		lastBtn.setMinWidth(vBox.getPrefWidth());
		updateBtn.setMinWidth(vBox.getPrefWidth());
		
        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);
        
		hBox.getChildren().addAll(addBtn, firstBtn, nextBtn, previousBtn, lastBtn, updateBtn);
		
		firstBtn.setOnAction(e -> {
			String str = "";
			str = address.firstAddress();
			String[] strArr = str.split(",");
			fNameField.setText(strArr[0]);
			lNameField.setText(strArr[1]);
			cityField.setText(strArr[2]);
			provinceBox.setValue(strArr[3]);
			postalCodeField.setText(strArr[4]);
		});
		
		lastBtn.setOnAction(e -> {
			String str = "";
			str = address.lastAddress();
			String[] strArr = str.split(",");
			fNameField.setText(strArr[0]);
			lNameField.setText(strArr[1]);
			cityField.setText(strArr[2]);
			provinceBox.setValue(strArr[3]);
			postalCodeField.setText(strArr[4]);
			
		});
		
		addBtn.setOnAction(e -> {
			String str = fNameField.getText() + "," + lNameField.getText() + "," + cityField.getText() + "," +
					provinceBox.getValue() + "," + postalCodeField.getText() + '\n';
			address.addAddress(str);
		});
		
		nextBtn.setOnAction(e -> {
			String str = "";
			str = address.nextAddress();
			if (str != null) {
				String[] strArr = str.split(",");
				fNameField.setText(strArr[0]);
				lNameField.setText(strArr[1]);
				cityField.setText(strArr[2]);
				provinceBox.setValue(strArr[3]);
				postalCodeField.setText(strArr[4]);				
			}
			
		});
		
		previousBtn.setOnAction(e -> {
			String str = "";
			str = address.previousAddress();
			if (str != null && str != "") {
				String[] strArr = str.split(",");
				fNameField.setText(strArr[0]);
				lNameField.setText(strArr[1]);
				cityField.setText(strArr[2]);
				provinceBox.setValue(strArr[3]);
				postalCodeField.setText(strArr[4]);				
			}
		});
		
		updateBtn.setOnAction(e -> {
			String str = fNameField.getText() + "," + lNameField.getText() + "," + cityField.getText() + "," +
					provinceBox.getValue() + "," + postalCodeField.getText() + '\n';
			address.updateAddress(str);
		});
		
        pane.add(hBox, 1, 6);
		primaryStage.setTitle("Address Book");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

}

class Address {
	long pos = 0;
	long totalLength = 0;
	ArrayList<Integer> lineLengths = new ArrayList<Integer>(); 
	ArrayList<Integer> positions = new ArrayList<Integer>(); 
	
	public Address() {}
	
	public void updateAddress(String address) {
		try (RandomAccessFile file = new RandomAccessFile("addressBook.txt", "rw")) {
			file.seek(pos);  
			file.writeBytes(address);
			file.close();	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Returns the previous address
	public String previousAddress() {
		String str = "";
		try (RandomAccessFile file = new RandomAccessFile("addressBook.txt", "r")) {
			if (file.readLine() != null) {
				if (!positions.isEmpty()) {
					pos = positions.get(positions.size() - 1) - lineLengths.get(lineLengths.size() - 1);
					file.seek(pos);
					str = file.readLine();
					
					if (str != null) {
						positions.remove(positions.size() - 1);
						lineLengths.remove(lineLengths.size() - 1);					
					}
					
				}
									
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return str;
		
	}
	
	
	// Returns the next address
	public String nextAddress() {
		String str = "";
		
		try (RandomAccessFile file = new RandomAccessFile("addressBook.txt", "r")) {
			if (file.readLine() != null) {
				file.seek(pos); 
				str = file.readLine();
				if (str != null) {
					lineLengths.add((str.length() + 1)); 
					pos = file.getFilePointer(); 
					positions.add((int)pos); // store positions					
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
			
			
		return str;
	}
	
	// Write an address to the text file
	public void addAddress(String address) {
		try (RandomAccessFile file = new RandomAccessFile("addressBook.txt", "rw")) {
			file.seek(file.length());
			file.writeBytes(address);
			file.close();	
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	// Read the last address
	public String lastAddress() {
		String lastLine = "";
		try (RandomAccessFile file = new RandomAccessFile("addressBook.txt", "r")) {
			int count = 0;
			
			while ((file.readLine()) != null) {
				count++;
			}
			
			file.seek(0);
			for (int i = 0; i < count - 1; i++) {	
				file.readLine();	
			}
			pos = file.getFilePointer(); // saves the last line position
			lastLine = file.readLine();
				
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return lastLine;
	}
	
	
	// Read the first Address
	public String firstAddress() {
		String str = "";
		try (RandomAccessFile file = new RandomAccessFile("addressBook.txt", "r")) {
			pos = 0;
			if (file != null) {
				file.seek(pos);
				str = file.readLine();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return str;
	}
	
	
}


