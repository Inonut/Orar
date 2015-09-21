package orar.guiPrimaIncercare.panel.impl;

import java.util.Optional;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import orar.guiPrimaIncercare.panel.Panel;
import orar.guiPrimaIncercare.presenter.impl.MatrixPresenter;

public class MatrixPanel extends GridPane implements Panel {
	
	private MatrixPresenter presenter;
	
	private Button add;
	
	public MatrixPanel() {
		presenter = new MatrixPresenter(this);
		/*presenter.initModel();
		
		add = new Button("Add");
		
		add.setOnAction(e -> {
			
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Nume coloana");
			dialog.setHeaderText("Dati un nume de coloana");
			dialog.setContentText("Nume:");

			Optional<String> result = dialog.showAndWait();
			result.ifPresent(name -> presenter.addColumn(name));
			
		});
		
		this.add(add,12,1);
		
		for(int i=0;i<12;i++){
			this.add(new SquarePanel(new Label("" + (8+i) + " - " + (9+i))),0,i+2);
		}*/
	}

	@Override
	public void updateModelFromView() {

	}

	@Override
	public void updateViewFromModel() {

	}

	public MatrixPresenter getPresenter() {
		return presenter;
	}

	public void setPresenter(MatrixPresenter presenter) {
		this.presenter = presenter;
	}

	public void addCell(Node squarePanel, int pos, int i) {
		this.add(squarePanel,pos,i);
	}
}
