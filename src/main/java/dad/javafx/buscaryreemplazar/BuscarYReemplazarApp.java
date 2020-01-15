package dad.javafx.buscaryreemplazar;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class BuscarYReemplazarApp extends Application {

	private Label searchLb;
	private Label replaceLb;
	private TextField searchTF;
	private TextField replaceTF;
	private CheckBox minMayCB; // minisuclas y mayusculas
	private CheckBox searchBackCB;
	private CheckBox regularCB;
	private CheckBox highligthCB;
	private Button searchBtn;
	private Button replaceBtn;
	private Button replaceAllBtn;
	private Button closeBtn;
	private Button helpBtn;

	@Override
	public void start(Stage primaryStage) throws Exception {

		searchLb = new Label("Buscar:");
		replaceLb = new Label("Reemplazar con:");

		searchTF = new TextField();
		replaceTF = new TextField();

		minMayCB = new CheckBox("Mayúsculas y minúsculas");
		minMayCB.setPadding(new Insets(0, 5, 0, 0));
		searchBackCB = new CheckBox("Buscar hacia atrás");
		searchBackCB.setPadding(new Insets(0, 5, 0, 0));
		regularCB = new CheckBox("Expresión regular");
		highligthCB = new CheckBox("Resaltar resultados");

		searchBtn = new Button("Buscar");
		searchBtn.setPadding(new Insets(5));
		searchBtn.setMaxWidth(Double.MAX_VALUE);
		replaceBtn = new Button("Reemplazar");
		replaceBtn.setPadding(new Insets(5));
		replaceBtn.setMaxWidth(Double.MAX_VALUE);
		replaceAllBtn = new Button("Reemplazar todo");
		replaceAllBtn.setPadding(new Insets(5));
		replaceAllBtn.setMaxWidth(Double.MAX_VALUE);
		closeBtn = new Button("Cerrar");
		closeBtn.setPadding(new Insets(5));
		closeBtn.setMaxWidth(Double.MAX_VALUE);
		helpBtn = new Button("Ayuda");
		helpBtn.setPadding(new Insets(5));
		helpBtn.setMaxWidth(Double.MAX_VALUE);

		GridPane gridPane = new GridPane();
//		gridPane.setGridLinesVisible(true);
		gridPane.addRow(0, searchLb, searchTF);
		gridPane.addRow(1, replaceLb, replaceTF);
		gridPane.addRow(2, new Label(""), minMayCB, searchBackCB);
		gridPane.addRow(3, new Label(""), regularCB, highligthCB);
		
		ColumnConstraints[] cols = { new ColumnConstraints(), new ColumnConstraints(),new ColumnConstraints() };

		cols[2].setHgrow(Priority.ALWAYS);
		cols[2].setHalignment(HPos.LEFT);
		cols[2].setFillWidth(true);

		gridPane.getColumnConstraints().setAll(cols);

		gridPane.setHgap(5); 
        gridPane.setVgap(5); 
		gridPane.setPadding(new Insets(3, 3, 3, 3));
		GridPane.setColumnSpan(searchTF, 2);
		GridPane.setColumnSpan(replaceTF, 2);

		GridPane gridPaneBtns = new GridPane();
//		gridPaneBtns.setGridLinesVisible(true);
		gridPaneBtns.addRow(0, searchBtn);
		gridPaneBtns.addRow(1, replaceBtn);
		gridPaneBtns.addRow(2, replaceAllBtn);
		gridPaneBtns.addRow(3, closeBtn);
		gridPaneBtns.addRow(4, helpBtn);
		
		ColumnConstraints[] colsBtns = { new ColumnConstraints() };

		colsBtns[0].setFillWidth(true);
		
		gridPaneBtns.getColumnConstraints().setAll(colsBtns);

		gridPaneBtns.setHgap(5); 
        gridPaneBtns.setVgap(5);
        gridPaneBtns.setPadding(new Insets(3, 3, 3, 3));
		
		BorderPane root = new BorderPane();
		root.setCenter(gridPane);
		root.setRight(gridPaneBtns);

		Scene scene = new Scene(root, 500, 200);

		primaryStage.setTitle("Buscar y reemplazar");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
