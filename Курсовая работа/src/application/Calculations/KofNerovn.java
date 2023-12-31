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

public class KofNerovn implements Calculator{
	@Override
	public void showWindow(Stage PrimaryStage, Label lbl) {
		Stage stg = new Stage();

		Label vqLabel = new Label("Коэффициент вариации грузопотока: ");
		TextField vQ = new TextField();
		Button calculateButton = new Button("Рассчитать");
		calculateButton.setOnAction(event -> {
			calculateAndSetLabel(lbl, vQ.getText());
		});

		GridPane root = new GridPane();
		root.setHgap(8);
		root.setVgap(8);
		root.setPadding(new Insets(5));
		root.addRow(0, vqLabel, vQ);
		root.add(calculateButton, 0, 1, 2, 1);
		root.add(lbl, 0, 2, 2, 1);

		Scene scn = new Scene(root, 400, 100);
		stg.setScene(scn);
		stg.setTitle("Коэффициент внутрискладских перевалок");
		stg.initOwner(PrimaryStage);
		stg.initModality(Modality.WINDOW_MODAL);
		stg.setResizable(false);
		stg.showAndWait();
	}
	public void calculateAndSetLabel(Label lbl, String vq) {
		try {
		Double vQ = Double.parseDouble(vq);
		Double Kper = (double) 1 + vQ; 
		String kper = String.valueOf(Kper);
		lbl.setText("Коэффициент внутрискладских перевалок: " + kper);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
	}
}
