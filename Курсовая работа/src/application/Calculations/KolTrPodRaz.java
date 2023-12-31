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

public class KolTrPodRaz implements Calculator {
	@Override
	public void showWindow(Stage PrimaryStage, Label lbl) {
		Stage stg = new Stage();

		Label ntrLabel = new Label("Число транспортных средств на разгрузку в сутки: ");
		Label rpodLabel = new Label("Число подач транспортных средств в сутки: ");
		TextField Ntr = new TextField();
		TextField Rpod = new TextField();
		Button calculateButton = new Button("Рассчитать");
		calculateButton.setOnAction(event -> {
			calculateAndSetLabel(lbl, Ntr.getText(), Rpod.getText());
		});

		GridPane root = new GridPane();
		root.setHgap(8);
		root.setVgap(8);
		root.setPadding(new Insets(5));
		root.addRow(0, ntrLabel, Ntr);
		root.addRow(1, rpodLabel, Rpod);
		root.add(calculateButton, 0, 2, 2, 1);
		root.add(lbl, 0, 3, 2, 1);

		Scene scn = new Scene(root, 500, 130);
		stg.setScene(scn);
		stg.setTitle("Количество транспортных средств, одновременно подаваемых под разгрузку");
		stg.initOwner(PrimaryStage);
		stg.initModality(Modality.WINDOW_MODAL);
		stg.setResizable(false);
		stg.showAndWait();
	}

	public void calculateAndSetLabel(Label lbl, String ntr, String rpod) {
		try {
			Double Ntr = Double.parseDouble(ntr);
			Double Rpod = Double.parseDouble(rpod);
			Double N = Ntr/Rpod;
			String n = String.valueOf(N);
			lbl.setText("Количество транспортных средств, одновременно подаваемых под разгрузку: " + n);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}
}
