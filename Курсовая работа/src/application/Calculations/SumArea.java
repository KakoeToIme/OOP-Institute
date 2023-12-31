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

public class SumArea implements Calculator{
	Label lbl1 = new Label();
	@Override 
	public void showWindow(Stage primaryStage, Label lbl) {
		Stage stg = new Stage();
		
		Label zmaxLabel = new Label("Максимальный размер запасов, подлежащих хранению (тонны): ");
		Label qdopLabel = new Label("Допустимая нагрузка на 1 м^2 полезной площади склада (тонны): ");
		Label kispLabel = new Label("Коэффициент использования общей площади склада: ");
		TextField zmax = new TextField();
		TextField qdop = new TextField();
		TextField kisp = new TextField();
		Button calculateButton = new Button("Рассчитать");
        calculateButton.setOnAction(event -> {
            calculateAndSetLabel(lbl, zmax.getText(), qdop.getText(), kisp.getText());
        });
        
        GridPane root = new GridPane();
        root.setHgap(8);
        root.setVgap(8);
        root.setPadding(new Insets(5));
        root.addRow(0, zmaxLabel, zmax);
        root.addRow(1, qdopLabel, qdop);
        root.addRow(2, kispLabel, kisp);
        root.add(calculateButton, 0, 3, 2, 1);
        root.add(lbl, 0, 4, 2, 1);
        root.add(lbl1, 0, 5, 2, 1);
        
        Scene scn = new Scene(root, 600, 250);
        stg.setScene(scn);
        stg.setTitle("Общая площадь склада (метод удельных нагрузок)");
        stg.initOwner(primaryStage);
        stg.initModality(Modality.WINDOW_MODAL);
        stg.setResizable(false);
        stg.showAndWait();
	}
	
	private void calculateAndSetLabel(Label lbl, String zmax, String qdop, String kisp) {
		try {
		Double Zmax = Double.parseDouble(zmax);
		Double Qdop = Double.parseDouble(qdop);
		Double Spol = Zmax/Qdop;
		Double Kisp = Double.parseDouble(kisp);
		String spol = String.valueOf(Spol);
		Double Sobsh = Spol/Kisp;
		String sobsh = String.valueOf(Sobsh);
		lbl.setText("Полезная площадь: " + spol + " м^2");
		lbl1.setText("Общая площадь: " + sobsh + " м^2");
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}
}
