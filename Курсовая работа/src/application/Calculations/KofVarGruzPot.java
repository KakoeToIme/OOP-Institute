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

public class KofVarGruzPot implements Calculator{
	@Override
	public void showWindow(Stage PrimaryStage, Label lbl) {
		Stage stg = new Stage();

		Label sigqLabel = new Label("Среднеквадратичное отклонение грузопотока: ");
		Label matqLabel = new Label("Математическое ожиадение: ");
		TextField SigQ = new TextField();
		TextField MatQ = new TextField();
		Button calculateButton = new Button("Рассчитать");
		calculateButton.setOnAction(event -> {
			calculateAndSetLabel(lbl, SigQ.getText(), MatQ.getText());
		});

		GridPane root = new GridPane();
		root.setHgap(8);
		root.setVgap(8);
		root.setPadding(new Insets(5));
		root.addRow(0, sigqLabel, SigQ);
		root.addRow(1, matqLabel, MatQ);
		root.add(calculateButton, 0, 2, 2, 1);
		root.add(lbl, 0, 3, 2, 1);

		Scene scn = new Scene(root, 500, 130);
		stg.setScene(scn);
		stg.setTitle("Коэффициент вариации грузопотока");
		stg.initOwner(PrimaryStage);
		stg.initModality(Modality.WINDOW_MODAL);
		stg.setResizable(false);
		stg.showAndWait();
	}
	public void calculateAndSetLabel(Label lbl, String sigq, String matq) {
		try {
		Double SigQ = Double.parseDouble(sigq);
		Double MatQ = Double.parseDouble(matq);
		Double vQ = SigQ/MatQ; 
		String vq = String.valueOf(vQ);
		lbl.setText("Коэффициент вариации грузопотока: " + vq);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
	}
}
