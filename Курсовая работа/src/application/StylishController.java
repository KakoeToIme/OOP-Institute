package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import application.Calculations.AmountDayOfWork;
import application.Calculations.EcoEffect;
import application.Calculations.SumArea;
import application.Calculations.Svspom;
import application.Calculations.TochkaBezub;
import application.Calculations.UdMarzgDoh;
import application.Calculations.KofIsp;
import application.Calculations.KofNerovn;
import application.Calculations.KofVarGruzPot;
import application.Calculations.KolTrPodRaz;
import application.Calculations.KolTrPodRazDay;
import application.Calculations.KritOb;
import application.Calculations.KromNaFactOb;
import application.Calculations.KromSecur;
import application.Calculations.LenPogrFr;
import application.Calculations.LenPogrFrAvto;
import application.Calculations.MarzgDoh;
import application.Calculations.Nstel;
import application.Calculations.NyaObsh;
import application.Calculations.PrimOtpr;
import application.Calculations.Spolezn;
import application.Calculations.SredGruzotpr;
import application.Calculations.SredGruzper;
import application.Calculations.SredGruzprib;
import application.Calculations.Sredvnper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class StylishController {

	String url = "jdbc:mysql://127.0.0.1:3306/warehouse";
	String user = "root";
	String password = "HybridsN11";

	private Calculator currentCalculator;
	Label lbl = new Label();
	Stage primaryStage;
	Stage PrimaryStage;

	@FXML
	private TitledPane counter;

	@FXML
	private TitledPane dbase;

	@FXML
	private AnchorPane TextSheild;

	@FXML
	private ScrollPane scroll;

	@FXML
	private TitledPane ftsearch;

	@FXML
	private TitledPane rep;

	@FXML
	private TitledPane stat;

	@FXML
	private TitledPane stock;

	@FXML
	private TextField valuename;

	@FXML
	private Button newitem;

	@FXML
	private Button redactoritem;

	@FXML
	private GridPane TextForDb;

	@FXML
	private Button showthetable;

	@FXML
	private TextField idfs;

	@FXML
	private TextField namefs;

	@FXML
	private Button statday;

	@FXML
	private Button statmonth;

	@FXML
	private Button dateupdate;

	public void ChangeData() {
		try {

			 LocalDate currentDate = LocalDate.now();
	         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	         String formattedDate = currentDate.format(formatter);

			updateDateInTable(formattedDate);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void updateDateInTable(String newDate) throws SQLException {
		Connection connection = DriverManager.getConnection(url, user, password);

		String updateQuery = "UPDATE stats SET date =?, quantityoff = 0, quantityon = 0";

		try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
			preparedStatement.setString(1, newDate);
			preparedStatement.executeUpdate();
		}
}

	public void setCalculator(Calculator calculator) {
		this.currentCalculator = calculator;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void initialize() {
		dateupdate.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (mouseEvent.getButton() == MouseButton.PRIMARY) {
					ChangeData();
				}
			}
		});
		
		statday.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (mouseEvent.getButton() == MouseButton.PRIMARY) {
					StatsPerDay.showWindow(primaryStage);
				}
			}
		});

		if (idfs.getText().isEmpty()) {
			idfs.setOnKeyPressed(new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent keyEvent) {
					if (keyEvent.getCode() == KeyCode.ENTER) {
						String id = idfs.getText();
						if (id != null && !id.isEmpty()) {
							FastSearch.showIdWindow(primaryStage, lbl, id);
						}
					}
				}
			});
		}

		if (namefs.getText().isEmpty()) {
			namefs.setOnKeyPressed(new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent keyEvent) {
					if (keyEvent.getCode() == KeyCode.ENTER) {
						String name = namefs.getText();
						if (name != null && !name.isEmpty()) {
							FastSearch.showNameWindow(primaryStage, lbl, name);
						}
					}
				}
			});
		}

		redactoritem.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (mouseEvent.getButton() == MouseButton.PRIMARY) {
					RedactorItemDb redactItem = new RedactorItemDb();
					redactItem.showWindow(primaryStage, PrimaryStage, lbl);
				}
			}
		});

		newitem.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (mouseEvent.getButton() == MouseButton.PRIMARY) {
					NewRowInDataBase newRow = new NewRowInDataBase();
					newRow.showWindow(primaryStage, lbl);
				}
			}
		});

		showthetable.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (mouseEvent.getButton() == MouseButton.PRIMARY) {
					showTableWindow();
				}
			}
		});

		valuename.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyEvent) {
				if (keyEvent.getCode() == KeyCode.ENTER) {
					String vln = valuename.getText();
					switch (vln) {
					case "Экономический эффект":
						setCalculator(new EcoEffect());
						if (currentCalculator != null) {
							currentCalculator.showWindow(primaryStage, lbl);
						}
						break;
					case "Полная площадь (тяжелый груз)":
						setCalculator(new SumArea());
						if (currentCalculator != null) {
							currentCalculator.showWindow(primaryStage, lbl);
						}
						break;
					case "Коэффициент использования общей площади склада":
						setCalculator(new KofIsp());
						if (currentCalculator != null) {
							currentCalculator.showWindow(primaryStage, lbl);
						}
						break;
					case "Полезная площадь склада":
						setCalculator(new Spolezn());
						if (currentCalculator != null) {
							currentCalculator.showWindow(primaryStage, lbl);
						}
						break;
					case "Количество стеллажей для хранения материала":
						setCalculator(new Nstel());
						if (currentCalculator != null) {
							currentCalculator.showWindow(primaryStage, lbl);
						}
						break;
					case "Количество ячеек стеллажей, необходимое для хранения максимального запаса":
						setCalculator(new NyaObsh());
						if (currentCalculator != null) {
							currentCalculator.showWindow(primaryStage, lbl);
						}
						break;
					case "Площадь приемо-отправочных площадок":
						setCalculator(new PrimOtpr());
						if (currentCalculator != null) {
							currentCalculator.showWindow(primaryStage, lbl);
						}
						break;
					case "Ширина проезда транспортного средства на склад":
						setCalculator(new Svspom());
						if (currentCalculator != null) {
							currentCalculator.showWindow(primaryStage, lbl);
						}
						break;
					case "Среднесуточная грузопереработка":
						setCalculator(new SredGruzper());
						if (currentCalculator != null) {
							currentCalculator.showWindow(primaryStage, lbl);
						}
						break;
					case "Среднесуточный грузопоток прибытия":
						setCalculator(new SredGruzprib());
						if (currentCalculator != null) {
							currentCalculator.showWindow(primaryStage, lbl);
						}
						break;
					case "Среднесуточный поток по отправлению":
						setCalculator(new SredGruzotpr());
						if (currentCalculator != null) {
							currentCalculator.showWindow(primaryStage, lbl);
						}
						break;
					case "Среднесуточная внутрискладская грузопереработка":
						setCalculator(new Sredvnper());
						if (currentCalculator != null) {
							currentCalculator.showWindow(primaryStage, lbl);
						}
						break;
					case "Коэффициент внутрискладских перевалок":
						setCalculator(new KofNerovn());
						if (currentCalculator != null) {
							currentCalculator.showWindow(primaryStage, lbl);
						}
						break;
					case "Коэффициент вариации грузопотока":
						setCalculator(new KofVarGruzPot());
						if (currentCalculator != null) {
							currentCalculator.showWindow(primaryStage, lbl);
						}
						break;
					case "Число дней работы склада":
						setCalculator(new AmountDayOfWork());
						if (currentCalculator != null) {
							currentCalculator.showWindow(primaryStage, lbl);
						}
						break;
					case "Длина погрузочно-разгрузочного фронта":
						setCalculator(new LenPogrFr());
						if (currentCalculator != null) {
							currentCalculator.showWindow(primaryStage, lbl);
						}
						break;
					case "Количество транспортных средств, одновременно подаваемых под разгрузку":
						setCalculator(new KolTrPodRaz());
						if (currentCalculator != null) {
							currentCalculator.showWindow(primaryStage, lbl);
						}
						break;
					case "Число транспортных средств на разгрузку в сутки":
						setCalculator(new KolTrPodRazDay());
						if (currentCalculator != null) {
							currentCalculator.showWindow(primaryStage, lbl);
						}
						break;
					case "Длина погрузочно-разгрузочного фронта автомобильной платформы":
						setCalculator(new LenPogrFrAvto());
						if (currentCalculator != null) {
							currentCalculator.showWindow(primaryStage, lbl);
						}
						break;
					case "Маржинальный доход":
						setCalculator(new MarzgDoh());
						if (currentCalculator != null) {
							currentCalculator.showWindow(primaryStage, lbl);
						}
						break;
					case "Удельный маржинальный доход":
						setCalculator(new UdMarzgDoh());
						if (currentCalculator != null) {
							currentCalculator.showWindow(primaryStage, lbl);
						}
						break;
					case "Точка безубыточности":
						setCalculator(new TochkaBezub());
						if (currentCalculator != null) {
							currentCalculator.showWindow(primaryStage, lbl);
						}
						break;
					case "Критический обьем производства и реализации продукции":
						setCalculator(new KritOb());
						if (currentCalculator != null) {
							currentCalculator.showWindow(primaryStage, lbl);
						}
						break;
					case "Кромка безопасности":
						setCalculator(new KromSecur());
						if (currentCalculator != null) {
							currentCalculator.showWindow(primaryStage, lbl);
						}
						break;
					case "Отношение кромки безопасности к фактическому обьему":
						setCalculator(new KromNaFactOb());
						if (currentCalculator != null) {
							currentCalculator.showWindow(primaryStage, lbl);
						}
						break;
					}
				}
			}
		});
	}

	public static class BdStat {
		private int id;
		private String product_name;
		private int quantityoff;
		private int quantityon;
		private String date;

		public BdStat(int id, String product_name, int quantityoff, int quantityon, String date) {
			this.id = id;
			this.product_name = product_name;
			this.quantityoff = quantityoff;
			this.quantityon = quantityon;
			this.date = date;
		}

		public int getId() {
			return id;
		}

		public String getProductName() {
			return product_name;
		}

		public int getQuantityOff() {
			return quantityoff;
		}

		public int getQuantityOn() {
			return quantityon;
		}

		public String getDate() {
			return date;
		}

	}

	public static class Bd {
		private int id;
		private String name;
		private int cpucore;
		private int cputhreads;
		private String memorytype;
		private double clockspeed;
		private double cashesize;
		private int tdp;
		private int amount;
		private double price;

		public Bd(int id, String name, int cpucore, int cputhreads, String memorytype, double clockspeed,
				double cashesize, int tdp, int amount, double price) {
			this.id = id;
			this.name = name;
			this.cpucore = cpucore;
			this.cputhreads = cputhreads;
			this.memorytype = memorytype;
			this.clockspeed = clockspeed;
			this.cashesize = cashesize;
			this.tdp = tdp;
			this.amount = amount;
			this.price = price;
		}

		public int getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public int getCpucore() {
			return cpucore;
		}

		public int getCputhreads() {
			return cputhreads;
		}

		public String getMemorytype() {
			return memorytype;
		}

		public double getClockspeed() {
			return clockspeed;
		}

		public double getCashesize() {
			return cashesize;
		}

		public int getTdp() {
			return tdp;
		}

		public int getAmount() {
			return amount;
		}

		public double getPrice() {
			return price;
		}

		public void setId(int id) {
			this.id = id;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setCpucore(int cpucore) {
			this.cpucore = cpucore;
		}

		public void setCputhreads(int cputhreads) {
			this.cputhreads = cputhreads;
		}

		public void setMemorytype(String memorytype) {
			this.memorytype = memorytype;
		}

		public void setClockspeed(double clockspeed) {
			this.clockspeed = clockspeed;
		}

		public void setCashesize(double cashesize) {
			this.cashesize = cashesize;
		}

		public void setTdp(int tdp) {
			this.tdp = tdp;
		}

		public void setAmount(int amount) {
			this.amount = amount;
		}

		public void setPrice(double price) {
			this.price = price;
		}
	}

	private void showTableWindow() {
		Stage stg = new Stage();

		TableView<Bd> tableView = new TableView<Bd>();
		TableColumn<Bd, Integer> idCol = new TableColumn<>("Id");
		idCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
		TableColumn<Bd, String> nameCol = new TableColumn<>("Название");
		nameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
		TableColumn<Bd, Integer> cpucoreCol = new TableColumn<>("Количество ядер");
		cpucoreCol.setCellValueFactory(
				cellData -> new SimpleIntegerProperty(cellData.getValue().getCpucore()).asObject());
		TableColumn<Bd, Integer> cputhreadsCol = new TableColumn<>("Количество потоков");
		cputhreadsCol.setCellValueFactory(
				cellData -> new SimpleIntegerProperty(cellData.getValue().getCputhreads()).asObject());
		TableColumn<Bd, String> memorytypeCol = new TableColumn<>("Тип данных");
		memorytypeCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMemorytype()));
		TableColumn<Bd, Double> clockspeedCol = new TableColumn<>("Тактовая частота");
		clockspeedCol.setCellValueFactory(
				cellData -> new SimpleDoubleProperty(cellData.getValue().getClockspeed()).asObject());
		TableColumn<Bd, Double> cashesizeCol = new TableColumn<>("Обьем кэша");
		cashesizeCol.setCellValueFactory(
				cellData -> new SimpleDoubleProperty(cellData.getValue().getCashesize()).asObject());
		TableColumn<Bd, Integer> tdpCol = new TableColumn<>("Мощность");
		tdpCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTdp()).asObject());
		TableColumn<Bd, Integer> amountCol = new TableColumn<>("Количество на складе");
		amountCol
				.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getAmount()).asObject());
		TableColumn<Bd, Double> priceCol = new TableColumn<>("Цена");
		priceCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());

		List<TableColumn<Bd, ?>> columns = Arrays.asList(idCol, nameCol, cpucoreCol, cputhreadsCol, memorytypeCol,
				clockspeedCol, cashesizeCol, tdpCol, amountCol, priceCol);

		tableView.getColumns().addAll(columns);

		ObservableList<Bd> data = FXCollections.observableArrayList(DbInventory.getAllDataFromDatabase());
		tableView.setItems(data);

		Scene scn = new Scene(tableView, 990, 600);
		stg.setScene(scn);
		stg.setTitle("База данных склада");
		stg.initOwner(primaryStage);
		stg.initModality(Modality.WINDOW_MODAL);
		stg.setResizable(true);
		stg.showAndWait();
	}
}
