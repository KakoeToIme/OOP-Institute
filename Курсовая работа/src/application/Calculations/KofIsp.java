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

public class KofIsp implements Calculator{
	@Override
	public void showWindow (Stage primaryStage, Label lbl) {
		Stage stg = new Stage();
		
		Label sobshLabel = new Label("Общая площадь склада (м^2): ");
		Label spolLabel = new Label("Полезная площадь склада (м^2): ");
		TextField sobsh = new TextField();
		TextField spol = new TextField();
		Button calculateButton = new Button("Рассчитать");
		calculateButton.setOnAction(event -> {
			calculateAndSetLabel(lbl, sobsh.getText(), spol.getText());
		});
		
		GridPane root = new GridPane();
		root.setHgap(8);
		root.setVgap(8);
		root.setPadding(new Insets(5));
		root.addRow(0, sobshLabel, sobsh);
		root.addRow(1, spolLabel, spol);
		root.add(calculateButton,0, 2, 2, 1);
		root.add(lbl,0,  3, 2, 1);
		
		Scene scn = new Scene(root, 370, 150);
		stg.setScene(scn);
		stg.setTitle("Коэффициент использования общей площади склада");
		stg.initOwner(primaryStage);
		stg.initModality(Modality.WINDOW_MODAL);
		stg.setResizable(false);
		stg.showAndWait();
	}
	private void calculateAndSetLabel(Label lbl, String sobsh, String spol) {
		try {
		Double Sobsh = Double.parseDouble(sobsh);
		Double Spol = Double.parseDouble(spol);
		Double Kofisp = Spol/Sobsh;
		String kofisp = String.valueOf(Kofisp);
		lbl.setText("Коэффициент использования общей площади: " + kofisp);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}
}
