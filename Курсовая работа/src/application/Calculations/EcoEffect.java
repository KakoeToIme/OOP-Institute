package application.Calculations;

import application.Calculator;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class EcoEffect implements Calculator{
	@Override
    public void showWindow(Stage primaryStage, Label lbl) {
        Stage stg = new Stage();

        Label resultLabel = new Label("Результат (руб): ");
        Label costLabel = new Label("Затраты (руб): ");
        TextField res = new TextField();
        TextField zat = new TextField();
        Button calculateButton = new Button("Рассчитать");
        calculateButton.setOnAction(event -> {
            calculateAndSetLabel(lbl, res.getText(), zat.getText());
        });

        GridPane root = new GridPane();
        root.setHgap(8);
        root.setVgap(8);
        root.setPadding(new Insets(5));
        root.addRow(0, resultLabel, res);
        root.addRow(1, costLabel, zat);
        root.add(calculateButton, 0, 2, 2, 1);
        root.add(lbl, 0, 3, 2, 1);

        Scene scn = new Scene(root, 370, 150);
        stg.setScene(scn);
        stg.setTitle("Рассчёт экономического эффекта");
        stg.initOwner(primaryStage);
        stg.initModality(Modality.WINDOW_MODAL);
        stg.setResizable(false);
        stg.showAndWait();
    }

    private void calculateAndSetLabel(Label lbl, String resl, String zatr) {
        try {
            double rez = Double.parseDouble(resl);
            double zatrt = Double.parseDouble(zatr);
            double eff = rez - zatrt;
            String ef = String.valueOf(eff);
            lbl.setText("Экономический эффект: " + ef + " руб");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
