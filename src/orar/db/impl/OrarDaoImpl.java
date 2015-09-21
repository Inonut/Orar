package orar.db.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import javafx.collections.FXCollections;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import com.sun.javafx.perf.PerformanceTracker;

import orar.constants.Cnst;
import orar.constants.Constants;
import orar.db.OrarDao;
import orar.gui.model.impl.ColumnModel;
import orar.gui.model.impl.GeneralModel;
import orar.gui.model.impl.PersonModel;
import orar.gui.panel.impl.SquarePanel;

public class OrarDaoImpl implements OrarDao {

	@Override
	public void save(GeneralModel generalModel) {
		PrintWriter printWriter = null;
		try {
			printWriter = new PrintWriter(new FileOutputStream(Cnst.URL));
			
			for(String colName:generalModel.getColNames()){
				printWriter.print(colName+" ");
			}
			printWriter.println();
			
			for(PersonModel personModel: generalModel.getPersonData()){
				if(personModel.getName().compareTo("TOTAL")==0){
					continue;
				}
				printWriter.print((personModel.getName().isEmpty()?"null":personModel.getName()) + " " +
			                      personModel.getCode() + " " +
						          personModel.getColor().getRed() + " " +
						          personModel.getColor().getGreen() + " " +
						          personModel.getColor().getBlue() + " ");
				
				printWriter.println();
				for(TextField field:personModel.getNorma()){
					printWriter.print((field.getText().isEmpty()?"null":field.getText())+" ");
				}
				printWriter.println();
				for(int i=0;i<personModel.getActivity().length;i++){
					for(int j=0;j<personModel.getActivity()[i].length;j++){
						for(SquarePanel squarePanel:personModel.getActivity()[i][j].getCells()){
							//rand
							printWriter.print((squarePanel.isClicked()?1:0) +" ");
						}
						printWriter.println();
					}
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally{
			printWriter.close();
		}
	}

	@Override
	public GeneralModel loadModel() {
		GeneralModel generalModel = new GeneralModel();
		Scanner sc =null;
		StringTokenizer line = null;
		try {
			sc = new Scanner(new FileInputStream(Cnst.URL));
			
			if(!sc.hasNextLine()){
				return new GeneralModel();
			}
			
			line = new StringTokenizer(sc.nextLine()," ");
			
			List<String> colNames = new ArrayList<String>();
			while(line.hasMoreElements()){
				colNames.add(line.nextElement().toString());
			}
			generalModel.setColNames(colNames);
			
			generalModel.setPersonData(FXCollections.observableArrayList());
			PersonModel personModel;
			while(sc.hasNextLine()){
				line = new StringTokenizer(sc.nextLine()," ");
				
				String elem = line.nextElement().toString();
				
				personModel = new PersonModel(elem.compareTo("null")==0?"":elem, line.nextElement().toString());
				
				Color color = new Color(Double.parseDouble(line.nextElement().toString()),
										Double.parseDouble(line.nextElement().toString()),
										Double.parseDouble(line.nextElement().toString()),0);
				personModel.setColor(color);
				
				line = new StringTokenizer(sc.nextLine()," ");
				List<TextField> norma = new ArrayList<TextField>();
				while(line.hasMoreElements()){
					elem = line.nextElement().toString();
					TextField textField = new TextField(elem.compareTo("null")==0?"":elem);
					textField.setPrefWidth(30);
					norma.add(textField);
				}
				personModel.setNorma(norma);
				
				ColumnModel[][] activity = new ColumnModel[Constants.NUMBER_OF_DAY.getVal()][Constants.NUMBER_OF_HOURS.getVal()];
				for(int i=0;i<Constants.NUMBER_OF_DAY.getVal();i++){
					for(int j=0;j<Constants.NUMBER_OF_HOURS.getVal();j++){
						line = new StringTokenizer(sc.nextLine()," ");
						
						activity[i][j] = new ColumnModel();
						activity[i][j].setCells(new ArrayList<SquarePanel>());
						while(line.hasMoreElements()){
							SquarePanel squarePanel = new SquarePanel();
							if(line.nextElement().toString().compareTo("1")==0){
								squarePanel.setBackGroundColor(color);
								squarePanel.setClicked(true);
							}
							squarePanel.setColor(color);
							activity[i][j].getCells().add(squarePanel);
						}
					}
				}
				personModel.setActivity(activity);
				
				generalModel.getPersonData().add(personModel);
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally{
			sc.close();
		}
		return generalModel;
		
	}
}
