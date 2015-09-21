package orar.gui.panel.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Control;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import javafx.util.Pair;
import orar.constants.Cnst;
import orar.constants.Constants;
import orar.db.OrarDao;
import orar.db.impl.OrarDaoImpl;
import orar.gui.model.impl.ColumnModel;
import orar.gui.model.impl.GeneralModel;
import orar.gui.model.impl.PersonModel;
import orar.gui.panel.Panel;
import orar.gui.presenter.impl.GeneralPresenter;
import orar.services.OrarSevices;

public class GeneralPanel extends GridPane implements Panel {

	private GridPane profListPanel;
	private GridPane dayPanel;
	private Label profLabel;
	private Button prevDay;
	private Button nextDay;
	private Label dayLabel;
	private GridPane activityPanel;
	private Button addColumn;
	private GridPane hourePanel;
	private GridPane columnPanel;
	private StackPane tablePanel;
	private ScrollPane scrollActivityPane;
	private TableView<PersonModel> personTable;
	private Button addPerson;
	private Button save;
	private GridPane actionPanel;
	private Button generate;
	
	private OrarSevices orarServices;
 	
	private int currentDay;
	private PersonModel currentPersonModel;
	private GeneralModel model;
	
	//private GeneralPresenter presenter;

	public GeneralPanel() {
		/***********INIT*************/
		this.profLabel = new Label("Profesori");
		this.profListPanel = new GridPane();
		this.dayPanel = new GridPane();
		this.prevDay = new Button("<-");
		this.nextDay = new Button("->");
		this.dayLabel = new Label();
		this.activityPanel = new GridPane();
		this.addColumn = new Button("Adauga");
		this.hourePanel = new GridPane();
		this.columnPanel = new GridPane();
		this.tablePanel = new StackPane();
		this.scrollActivityPane = new ScrollPane();
		this.personTable = new TableView<PersonModel>();
		this.addPerson = new Button("Persoana Noua");
		this.save = new Button("Salvare");
		this.actionPanel = new GridPane();
		this.generate = new Button("Generare");
		/****************************/
		
		/**********************COMP****************/
		this.dayPanel.addRow(0,this.prevDay, this.dayLabel, this.nextDay);
		
		this.scrollActivityPane.setContent(this.activityPanel);
		this.scrollActivityPane.setFitToHeight(true);
		this.scrollActivityPane.setFitToWidth(true);
		
		this.profListPanel.add(this.personTable, 0, 0);
		this.profListPanel.add(this.addPerson, 0, 1);
		
		this.actionPanel.addRow(0, this.generate, this.save);
		
		//this.activityPanel.add(this.addColumn, 0, 0);
		//this.activityPanel.add(this.hourePanel, 0, 1);
		//this.activityPanel.add(this.columnPanel, 1, 0);
		//this.activityPanel.add(this.tablePanel, 1, 1);

		this.add(this.profLabel, 0, 0);
		this.add(this.profListPanel, 0, 1);
		this.add(this.dayPanel, 1, 0);
		this.add(this.scrollActivityPane, 1, 1);
		this.add(this.actionPanel, 0, 2,2,1);
		
		
		this.activityPanel.getRowConstraints().add(addRow(100.0/13));
		this.activityPanel.getRowConstraints().add(addRow(100.0/13));
		for(int i=0;i<Constants.NUMBER_OF_HOURS.getVal();i++){
			this.activityPanel.getRowConstraints().add(addRow(100.0/15));
			//this.activityPanel.add(new SquarePanel(new Label((8+i)+"-"+(9+i))), 0, i+1);
		}
		this.dayPanel.getColumnConstraints().addAll(addColumn(100.0/3),addColumn(100.0/3),addColumn(100.0/3));
		
		this.profListPanel.getRowConstraints().addAll(addRow(90),addRow(10));

		this.getColumnConstraints().addAll(addColumn(20), addColumn(80));
		this.getRowConstraints().addAll(addRow(10),addRow(85),addRow(5));	
		/**********************************/
		
		TableColumn<PersonModel, String> t1 = new TableColumn<PersonModel, String>("Nume");
		TableColumn<PersonModel, String> t2 = new TableColumn<PersonModel, String>("Cod");
		TableColumn<PersonModel, String> t3 = new TableColumn<PersonModel, String>("Culoare");
		t1.setCellValueFactory(cellData -> cellData.getValue().getNamePropriety());
		t2.setCellValueFactory(cellData -> cellData.getValue().getCodePropriety());
		t3.setCellValueFactory(cellData -> cellData.getValue().getCodePropriety());
		this.personTable.getColumns().addAll(t1,t2,t3);
		t3.setCellFactory(new Callback<TableColumn<PersonModel,String>, TableCell<PersonModel,String>>() {
			
			@Override
			public TableCell<PersonModel, String> call(TableColumn<PersonModel, String> param) {
				
				return new TableCell<PersonModel, String>(){
					protected void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						if(item != null && !empty){
							if(getTableRow() != null){
								PersonModel rowItem = ((PersonModel)getTableRow().getItem());
								if(rowItem != null){
									setStyle(getStyle() + "-fx-background-color: "+ Cnst.getColorFormat(rowItem.getColor()) +";\n");
								}
							}
	                    }
					};
					
				};
			}
		});
		
		//this.activityPanel.getColumnConstraints().addAll(addColumn(10), addColumn(90));
		//this.activityPanel.getRowConstraints().addAll(addRow(10),addRow(90));
		
		
		
		/*****CSS****/
		for (Node n : this.getChildren()) {
			if (n instanceof Control) {
				Control control = (Control) n;
				control.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
				control.setStyle("-fx-background-color: cornsilk; -fx-alignment: center;");
			}
			if (n instanceof Pane) {
				Pane pane = (Pane) n;
				pane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
				pane.setStyle("-fx-background-color: cornsilk; -fx-alignment: center;");
			}
		}

		this.setStyle("-fx-background-color: palegreen; -fx-padding: 2; -fx-hgap: 2; -fx-vgap: 2;");
		this.setSnapToPixel(false);
		/*******************************/
		
		/***********************ACTION****************/
		this.addColumn.setOnAction(e -> {
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Nume coloana");
			dialog.setHeaderText("Dati un nume de coloana");
			dialog.setContentText("Nume:");

			Optional<String> result = dialog.showAndWait();
			result.ifPresent(name -> {
				//updateModelFromView();
				model.getColNames().add(name);
				
				if(model.getPersonData() != null){
					for(PersonModel personModel : model.getPersonData()){
						for(int i=0;i<personModel.getActivity().length;i++){
							for(int j=0;j<personModel.getActivity()[i].length;j++){
								if(personModel.getActivity()[i][j] == null){
									personModel.getActivity()[i][j] = new ColumnModel();
									personModel.getActivity()[i][j].setCells(new ArrayList<SquarePanel>());
								}
								SquarePanel squarePanel = new SquarePanel();
								squarePanel.setColor(personModel.getColor());
								personModel.getActivity()[i][j].getCells().add(squarePanel);
							}
						}
						if(personModel.getNorma() == null){
							personModel.setNorma(new ArrayList<TextField>());
						}
						TextField textField = new TextField();
						textField.setPrefWidth(30);
						personModel.getNorma().add(textField);
					}
				}
				updateViewFromModel();
				if(currentPersonModel != null){
					displayActivity(currentPersonModel.getActivity()[currentDay]);
				}
				
			});
		});
		
		this.addPerson.setOnAction(e -> {
			Dialog<Pair<String, String>> dialog = new Dialog<>();
			dialog.setTitle("Profesor nou");

			ButtonType okButtonType = new ButtonType("Ok", ButtonData.OK_DONE);
			dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

			GridPane grid = new GridPane();
			grid.setHgap(10);
			grid.setVgap(10);
			grid.setPadding(new Insets(20, 150, 10, 10));

			TextField username = new TextField();
			username.setPromptText("Nume");
			TextField usercode = new TextField();
			usercode.setPromptText("Cod");

			grid.add(new Label("Nume:"), 0, 0);
			grid.add(username, 1, 0);
			grid.add(new Label("Cod:"), 0, 1);
			grid.add(usercode, 1, 1);

			Node loginButton = dialog.getDialogPane().lookupButton(okButtonType);
			usercode.textProperty().addListener((observable, oldValue, newValue) -> {
			    loginButton.setDisable(newValue.trim().isEmpty());
			});
			
			loginButton.setDisable(true);

			dialog.getDialogPane().setContent(grid);

			Platform.runLater(() -> usercode.requestFocus());

			dialog.setResultConverter(dialogButton -> {
			    if (dialogButton == okButtonType) {
			        return new Pair<>(username.getText(), usercode.getText());
			    }
			    return null;
			});

			Optional<Pair<String, String>> result = dialog.showAndWait();

			result.ifPresent(person -> {
			    PersonModel personModel = new PersonModel(person.getKey(),person.getValue());
			   // presenter.addNewPerson(personModel);
			    if(model.getPersonData() == null){
					model.setPersonData(FXCollections.observableArrayList());
					personTable.setItems(model.getPersonData());
				}
				
				personModel.setColor(new Color(new Random().nextDouble(), new Random().nextDouble(), new Random().nextDouble(), 0));
				if(model.getColNames() != null){
					for(int i=0;i<personModel.getActivity().length;i++){
						for(int j=0;j<personModel.getActivity()[i].length;j++){
							List<SquarePanel> cells =  new ArrayList<SquarePanel>();
							
							for(String colName : model.getColNames()){
								SquarePanel squarePanel = new SquarePanel();
								squarePanel.setColor(personModel.getColor());
								cells.add(squarePanel);
							}
							personModel.getActivity()[i][j] = new ColumnModel();
							personModel.getActivity()[i][j].setCells(cells);
							
						}
					}
				}
				List<TextField> norma = new ArrayList<TextField>();
				for(String colName : model.getColNames()){
					TextField textField = new TextField();
					textField.setPrefWidth(30);
					norma.add(textField);
				}
				personModel.setNorma(norma);
				
				model.getPersonData().add(personModel);
			});
		});
		
		this.personTable.getSelectionModel().selectedItemProperty().addListener( new ChangeListener() {

			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				if(newValue != null){
					currentDay = 0;
					dayLabel.setText(Cnst.getDayName(currentDay));
					currentPersonModel = (PersonModel)newValue;
					//updateModelFromView();
					updateViewFromModel();
					displayActivity(currentPersonModel.getActivity()[currentDay]);
				}
				
			}
        });
		
		prevDay.setOnAction(e -> {
			currentDay=(currentDay-1<0?4:currentDay-1)%5;
			dayLabel.setText(Cnst.getDayName(currentDay));
			if(currentPersonModel != null){
				//updateModelFromView();
				updateViewFromModel();
				displayActivity(currentPersonModel.getActivity()[currentDay]);
			}
		});
		
		nextDay.setOnAction(e -> {
			currentDay=(currentDay+1)%5;
			dayLabel.setText(Cnst.getDayName(currentDay));
			if(currentPersonModel != null){
				//updateModelFromView();
				updateViewFromModel();
				displayActivity(currentPersonModel.getActivity()[currentDay]);
			}
		});
		
		this.save.setOnAction(e -> {
			this.orarServices.save(model);
		});
		
		this.generate.setOnAction(e -> {
			this.orarServices.save(model);
			model = orarServices.loadModel();
			PersonModel pm = orarServices.genereteOrar(this.model);
			
			model = orarServices.loadModel();
			this.updateViewFromModel();
			model.getPersonData().add(pm);
		});
		
		/*this.personTable.setRowFactory(new Callback<TableView<PersonModel>, TableRow<PersonModel>>() {
	        @Override
	        public TableRow<PersonModel> call(TableView<PersonModel> tableView) {
	            
	        	final TableRow<PersonModel> row = new TableRow<PersonModel>() {
	                @Override
	                protected void updateItem(PersonModel person, boolean empty){
	                    super.updateItem(person, empty);
	                    if(person != null && !empty){
	                    	setStyle(getStyle() + "-fx-background-color: "+ Cnst.getColorFormat(person.getColor()) +";\n");
	                    }
	                }
	            };
	            presenter.getModel().getPersonData().addListener(new ListChangeListener<PersonModel>() {
					@Override
					public void onChanged(Change<? extends PersonModel> c) {
						
						if (presenter.getModel().getPersonData().contains(row.getIndex())) {
							setStyle(getStyle() + "-fx-background-color: "+ Cnst.getColorFormat(row.getItem().getColor()) +";\n");
	                    } 
						
					}
	            });
	            return row;
	        }
	    });*/
	}
	
	private ColumnConstraints addColumn(double procent){
		ColumnConstraints column = new ColumnConstraints();
		if(procent >=0){
			column.setPercentWidth(procent);
		}
		column.setHalignment(HPos.CENTER);
		return column;
	}
	
	private RowConstraints addRow(double procent){
		RowConstraints row = new RowConstraints();
		row.setPercentHeight(procent);
		row.setValignment(VPos.CENTER);
		return row;
	}

	public void addColumn(String name) {
		//activityPanel.addRow(this.columnPanel.getColumnConstraints().size(), new SquarePanel(new Label(name)));
		//this.activityPanel.getColumnConstraints().addAll(addColumn(-1));
		activityPanel.add(new SquarePanel(new Label(name)), activityPanel.getColumnConstraints().size(), 0);
		activityPanel.add(new SquarePanel(new Label(name)), activityPanel.getColumnConstraints().size(), 1);
	}

	public TableView<PersonModel> getPersonTable() {
		return personTable;
	}

	public void setPersonTable(TableView<PersonModel> personTable) {
		this.personTable = personTable;
	}


	public void updateModelFromView() {
		
		List<String> colNames = new ArrayList<String>();
		for(Node n : this.activityPanel.getChildren()){
			if(n instanceof SquarePanel && this.activityPanel.getRowIndex((SquarePanel)n)==0){
				n = ((SquarePanel)n).getObj();
				if(n instanceof Label){
					colNames.add(((Label)n).getText());
				}
			}
		}
		this.model.setColNames(colNames);
		
	}

	public void updateViewFromModel() {

		/*for(Node n : this.activityPanel.getChildren()){
			if(n instanceof SquarePanel && this.activityPanel.getRowIndex((SquarePanel)n).intValue()==0){
				this.activityPanel.getChildren().remove(n);
			}
		}*/
		
		if(model.getPersonData() != null){
			personTable.setItems(model.getPersonData());
		}
		
		this.activityPanel.getChildren().clear();
		for(int i=0;i<Constants.NUMBER_OF_HOURS.getVal();i++){
			this.activityPanel.add(new SquarePanel(new Label((8+i)+"-"+(9+i))), 0, i+2);
		}
		this.activityPanel.add(this.addColumn, 0, 0,1,2);
		int i=1;
		for(String name: this.model.getColNames()){
			activityPanel.add(new SquarePanel(new Label(name)), i, 0);
			if(this.currentPersonModel != null){
				if(this.currentPersonModel.getNorma() != null){
					activityPanel.add(new SquarePanel(this.currentPersonModel.getNorma().get(i-1)), i, 1);
				}
			}
			i++;
			
		}
		dayLabel.setText(Cnst.getDayName(currentDay));
	}

	public void displayActivity(ColumnModel[] columnModels) {
		
		int i=2;
		for(ColumnModel row : columnModels ){
			int j=1;
			for(SquarePanel squarePanel:row.getCells()){
				activityPanel.add(squarePanel, j++, i);
			}
			i++;
		}
	}

	public GeneralModel getModel() {
		return model;
	}

	public void setModel(GeneralModel model) {
		this.model = model;
	}

	public OrarSevices getOrarServices() {
		return orarServices;
	}

	public void setOrarServices(OrarSevices orarServices) {
		this.orarServices = orarServices;
	}
	

}
