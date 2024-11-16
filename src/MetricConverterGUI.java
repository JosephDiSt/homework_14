import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MetricConverterGUI extends Application {
    @SuppressWarnings("unused")
    @Override
    public void start(Stage primaryStage) {
        // Labels
        Label inputLabel = new Label("Enter Value:");
        Label fromUnitLabel = new Label("From Unit:");
        Label toUnitLabel = new Label("To Unit:");
        Label resultLabel = new Label("Result:");

        // Input Fields
        TextField inputField = new TextField();
        ComboBox<String> fromUnitCombo = new ComboBox<>();
        ComboBox<String> toUnitCombo = new ComboBox<>();
        Label resultDisplay = new Label();

        // Populate ComboBoxes
        fromUnitCombo.getItems().addAll("kg", "km", "mm", "gram");
        toUnitCombo.getItems().addAll("lb", "mile", "inch", "ounce");

        // Button
        Button convertButton = new Button("Convert");

        // Button Action
        convertButton.setOnAction(e -> {
            try {
                double value = Double.parseDouble(inputField.getText());
                String fromUnit = fromUnitCombo.getValue();
                String toUnit = toUnitCombo.getValue();

                if (fromUnit == null || toUnit == null) {
                    resultDisplay.setText("Please select both units.");
                    return;
                }

                double result = performConversion(value, fromUnit, toUnit);
                resultDisplay.setText(String.format("%.2f %s = %.2f %s", value, fromUnit, result, toUnit));
            } catch (NumberFormatException ex) {
                resultDisplay.setText("Invalid input. Please enter a number.");
            }
        });

        // Layout
        VBox root = new VBox(10);
        root.setPadding(new Insets(15));
        root.getChildren().addAll(
            inputLabel, inputField,
            fromUnitLabel, fromUnitCombo,
            toUnitLabel, toUnitCombo,
            convertButton,
            resultLabel, resultDisplay
        );

        // Scene and Stage
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Metric Converter");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Perform the metric conversion based on the selected units.
     */
    private double performConversion(double value, String fromUnit, String toUnit) {
        switch (fromUnit + " " + toUnit) {
            case "kg lb": return value * 2.20462;
            case "km mile": return value * 0.621371;
            case "mm inch": return value * 0.0393701;
            case "gram ounce": return value * 0.035274;
            default: throw new IllegalArgumentException("Conversion not supported.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
