package orar;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import orar.db.OrarDao;
import orar.db.impl.OrarDaoImpl;
import orar.domain.Prof;
import orar.gui.model.impl.GeneralModel;
import orar.gui.panel.impl.GeneralPanel;
import orar.gui.presenter.impl.GeneralPresenter;
import orar.services.OrarSevices;
import orar.services.impl.OrarServicesImpl;

public class Main extends Application{
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		//test commit
		OrarSevices orarSevices = new OrarServicesImpl();
		OrarDao orarDao = new OrarDaoImpl();
		
		BorderPane panel = new BorderPane();
		GeneralPanel generalPenal = new GeneralPanel();
		GeneralPresenter generalPresenter = new GeneralPresenter();
		GeneralModel generalModel = orarSevices.loadModel();

		generalPenal.setOrarServices(orarSevices);
		generalPenal.setModel(generalModel);
		
		//generalPenal.setPresenter(generalPresenter);
		//generalPresenter.setPanel(generalPenal);
		//generalPresenter.setModel(generalModel);
		panel.setCenter(generalPenal);
		try {
			generalPenal.updateViewFromModel();
		} catch (Exception e) {
			generalPenal.updateModelFromView();
			generalPenal.updateViewFromModel();
		}
		
		
		Scene scene = new Scene(panel,1000,600);

		stage.setTitle("Orar");
		stage.setScene(scene);
		stage.show();
	}

}
