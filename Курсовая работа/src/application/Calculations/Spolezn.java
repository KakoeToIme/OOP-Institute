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

public class Spolezn implements Calculator {
	@Override
	public void showWindow(Stage primaryStage, Label lbl) {
		Stage stg = new Stage();

		Label sstLabel = new Label("Площадь, занимаемая одним стеллажом (м^2): ");
		Label nyastLabel = new Label("Количество ячеек в данном стеллаже: ");
		Label zmaxLabel = new Label("Максимальный размер запасов, подлежащих хранению (тонны): ");
		Label vyaLabel = new Label("Обьем ячейки стеллажа (м^2): ");
		Label gLabel = new Label("Удельный вес хранимого материала (тонна/м^3): ");
		Label koLabel = new Label("Коэффициент заполнения обьема ячейки: ");
		TextField sst = new TextField();
		TextField nyast = new TextField();
		TextField zmax = new TextField();
		TextField vya = new TextField();
		TextField g = new TextField();
		TextField ko = new TextField();
		Button calculateButton = new Button("Рассчитать");
		calculateButton.setOnAction(event -> {
			calculateAndSetLabel(lbl, sst.getText(), nyast.getText(), zmax.getText(), vya.getText(), g.getText(),
					ko.getText());
		});

		GridPane root = new GridPane();
		root.setHgap(8);
		root.setVgap(8);
		root.setPadding(new Insets(5));
		root.addRow(0, sstLabel, sst);
		root.addRow(1, nyastLabel, nyast);
		root.addRow(2, zmaxLabel, zmax);
		root.addRow(3, vyaLabel, vya);
		root.addRow(4, gLabel, g);
		root.addRow(5, koLabel, ko);
		root.add(calculateButton, 0, 6, 2, 1);
		root.add(lbl, 0, 7, 2, 1);

		Scene scn = new Scene(root, 600, 300);
		stg.setScene(scn);
		stg.setTitle("Полезная площадь склада (метод обьемных измерителей");
		stg.initOwner(primaryStage);
		stg.initModality(Modality.WINDOW_MODAL);
		stg.setResizable(false);
		stg.showAndWait();
	}

	private void calculateAndSetLabel(Label lbl, String sst, String nyast, String zmax, String vya, String g, String ko) {
		try {
		Double Sst = Double.parseDouble(sst);
		Double Nyast = Double.parseDouble(nyast);
		Double Zmax = Double.parseDouble(zmax);
		Double Vya = Double.parseDouble(vya);
		Double G = Double.parseDouble(g);
		Double Ko = Double.parseDouble(ko);
		Double Nyao = Zmax/(Vya*G*Ko);
		Double Nst = Nyao/Nyast;
		Double Spol = Sst * Nst;
		String spol = String.valueOf(Spol);
		lbl.setText("Полезная площадь склада: " + spol + " м^2");
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}
}
