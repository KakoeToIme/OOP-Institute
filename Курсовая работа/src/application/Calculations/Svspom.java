package application.Calculations;

import application.Calculator;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Svspom implements Calculator{
	@Override
	public void showWindow (Stage primaryStage, Label lbl) {
		Stage stg = new Stage();
		
		Label bLabel = new Label("Ширина транспортного средства (см): ");
		Label cLabel = new Label("Ширина зазоров между тр.средством и стеллажами (15 - 20 см): ");
		TextField b = new TextField();
		TextField c = new TextField();
		Button calculateButton = new Button("Рассчитать");
		calculateButton.setOnAction(event -> {
			calculateAndSetLabel(lbl, b.getText(), c.getText());
		});
		
		GridPane root = new GridPane();
		root.setHgap(8);
		root.setVgap(8);
		root.setPadding(new Insets(5));
		root.addRow(0, bLabel, b);
		root.addRow(1, cLabel, c);
		root.add(calculateButton, 0, 2, 2, 1);
		root.add(lbl, 0, 3, 2, 1);
		
		Scene scn = new Scene(root, 530, 150);
		stg.setScene(scn);
		stg.setTitle("Ширина проезда транспортного средства на склад");
		stg.initOwner(primaryStage);
		stg.initModality(Modality.WINDOW_MODAL);
		stg.setResizable(false);
		stg.showAndWait();
	}
	private void calculateAndSetLabel(Label lbl, String b, String c) {
		try {
		Double B = Double.parseDouble(b);
		Double C = Double.parseDouble(c);
		Double A = ((double) 2 * B) + ((double) 3 * C); 
		String a = String.valueOf(A);
		lbl.setText("Ширина проезда транспортного средства на склад: " + a + " см");
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}
}
