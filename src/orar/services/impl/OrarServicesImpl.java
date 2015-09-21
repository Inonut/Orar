package orar.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point3D;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import orar.constants.Cnst;
import orar.db.OrarDao;
import orar.db.impl.OrarDaoImpl;
import orar.gui.model.impl.ColumnModel;
import orar.gui.model.impl.GeneralModel;
import orar.gui.model.impl.PersonModel;
import orar.gui.panel.impl.SquarePanel;
import orar.services.OrarSevices;
import orar.services.impl.util.MoreConditionComparator;
import orar.services.impl.util.MoreHouresComparator;

public class OrarServicesImpl implements OrarSevices{
	
	private OrarDao orarDao;
	
	public OrarServicesImpl() {
		this.orarDao = new OrarDaoImpl();
	}

	@Override
	public void save(GeneralModel model) {
		orarDao.save(model);
	}
	

	@Override
	public PersonModel genereteOrar(GeneralModel model) {
		
		
		int ll=0;
		List<PersonModel> personModelValid = null;
		do{
			for(PersonModel personModel: model.getPersonData()){
				personModel.setCondition(getCondition(personModel.getActivity()));
			}
			model.getPersonData().sort(new MoreConditionComparator());
			
			Point3D point;// = model.getPersonData().get(0).getNextPoint();
			int ii,jj,kk;
			ii=jj=kk=0;
			do{
				point = new Point3D(ii,jj,kk);
				personModelValid = getValidPerson(model.getPersonData(),point);
				
				/*if(ii==2 && jj==5 && kk==10){
					System.out.println("sdcsdf");
				}*/
				
				//System.out.println(ii+" "+jj+" "+kk);
				kk++;
				if(kk==model.getPersonData().get(0).getNrClasses()){
					kk=0;
				}else{
					continue;
				}
				
				ii++;
				if(ii==model.getPersonData().get(0).getNrDay()){
					ii=0;
				}else{
					continue;
				}
				
				jj++;
				if(jj==model.getPersonData().get(0).getNrHoure()){
					jj=0;
				}else{
					continue;
				}
				
				break;
				
			}while(personModelValid == null || personModelValid.size() <= 0);
			
			
			if(personModelValid == null || personModelValid.size() <= 0){
				System.out.println("Stop");
				break;
			}
			
			System.out.println("--- "+ii+" "+jj+" "+kk + " "+ ++ll);
			
			personModelValid.sort(new MoreHouresComparator(point));
			
			int x = (int)point.getX();
			int y = (int)point.getY();
			int z = (int)point.getZ();
				
			personModelValid.get(0).get(x, y, z).setVisited(true);
			
			//adancime
			for(int i=1;i<personModelValid.size();i++){
				personModelValid.get(i).get(x, y, z).setClicked(false);
				//int norma = Integer.parseInt(personModelValid.get(i).getNorma().get(z).getText());
				//personModelValid.get(i).getNorma().get(z).setText(""+(norma-1));
				//personModelValid.get(i).setCondition(personModelValid.get(i).getCondition()-1);
			}
			
			//latime
			for(int i=0;i<personModelValid.get(0).getNrClasses();i++){
				int norma;
				try{
					norma = Integer.parseInt(personModelValid.get(0).getNorma().get(i).getText());
				}catch(Exception e){
					norma=0;
				}
				if(i!=z && norma>0 && personModelValid.get(0).get(x, y, i).isClicked()){
					personModelValid.get(0).get(x, y, i).setClicked(false);
					//personModelValid.get(0).getNorma().get(i).setText(""+(norma-1));
					//personModelValid.get(0).setCondition(personModelValid.get(0).getCondition()-1);
					if(x==2 && y==5 && z==10){
						System.out.println("sdcsdf");
					}
				}
			}
			
			//inaltime
			int norma = Integer.parseInt(personModelValid.get(0).getNorma().get(z).getText())-1;
			personModelValid.get(0).getNorma().get(z).setText(""+(norma));
			if(z==10){
				System.out.println("sdcsdf");
			}
			if(norma<=0){
				for(int i=0;i<personModelValid.get(0).getNrDay();i++){
					for(int k=0;k<personModelValid.get(0).getNrHoure();k++){
						if(!personModelValid.get(0).get(i, k, z).isVisited()){
							
							personModelValid.get(0).get(i, k, z).setClicked(false);
						}
					}
				}
			}
			/*for(int i=1;i<personModelValid.size();i++){
				personModelValid.get(i).getActivity()[(int)point.getX()][(int)point.getY()].getCells().get((int)point.getZ()).setClicked(false);
				int x = 0;
			    try{
			    	x = Integer.parseInt(personModelValid.get(i).getNorma().get((int)point.getZ()).getText())-1;
			    }catch(Exception e){
			    	x = 0;
			    }
			    personModelValid.get(i).getNorma().get((int)point.getZ()).setText(""+x);
				personModelValid.get(i).setCondition(personModelValid.get(i).getCondition()-1);
			}
			
			for(int i=0;i<personModelValid.get(0).getActivity()[(int)point.getX()][(int)point.getY()].getCells().size();i++){
				if(personModelValid.get(0).getActivity()[(int)point.getX()][(int)point.getY()].getCells().get(i).isClicked()){
					if(i != (int)point.getZ()){
						personModelValid.get(0).getActivity()[(int)point.getX()][(int)point.getY()].getCells().get(i).setClicked(false);
					}
					
					int x = 0;
				    try{
				    	x = Integer.parseInt(personModelValid.get(0).getNorma().get(i).getText())-1;
				    }catch(Exception e){
				    	x = 0;
				    }
				    personModelValid.get(0).getNorma().get(i).setText(""+x);
					personModelValid.get(0).setCondition(personModelValid.get(0).getCondition()-1);
				}
			}
			
			int x = 0;
			try{
		    	x = Integer.parseInt(personModelValid.get(0).getNorma().get((int)point.getZ()).getText());
		    }catch(Exception e){
		    	x = 0;
		    }
			if(x<=0){
				for(int day = 0;day<5;day++){
					for(int i=(int) point.getY()+1;i<personModelValid.get(0).getActivity()[(int)point.getX()].length;i++){
						if(personModelValid.get(0).getActivity()[day][i].getCells().get((int)point.getZ()).isClicked()){
							if(i != (int)point.getY()){
								personModelValid.get(0).getActivity()[day][i].getCells().get((int)point.getZ()).setClicked(false);
							}
							
							
						    try{
						    	x = Integer.parseInt(personModelValid.get(0).getNorma().get((int)point.getZ()).getText())-1;
						    }catch(Exception e){
						    	x = 0;
						    }
						    personModelValid.get(0).getNorma().get((int)point.getZ()).setText(""+x);
							personModelValid.get(0).setCondition(personModelValid.get(0).getCondition()-1);
						}
					}
				}
			}*/
			
			
		}while(true);
		
		PersonModel personModel = new PersonModel("TOTAL"," ");
		
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
		
		for(PersonModel pModel: model.getPersonData()){
			for(int i=0;i<pModel.getActivity().length;i++){
				for(int j=0;j<pModel.getActivity()[i].length;j++){
					for(int k=0;k<pModel.getActivity()[i][j].getCells().size();k++){
						if(pModel.getActivity()[i][j].getCells().get(k).isClicked()){
							personModel.getActivity()[i][j].getCells().get(k).setColor(pModel.getActivity()[i][j].getCells().get(k).getColor());
							personModel.getActivity()[i][j].getCells().get(k).setClicked(true);
							personModel.getActivity()[i][j].getCells().get(k).setObj(new Label(pModel.getCode()));
						}
						
					}
				}
			}
		}
		
		
		for(PersonModel pModel: model.getPersonData()){
			int k=0;
			for(TextField textField: pModel.getNorma()){
				if(textField.getText().compareTo("")!=0 && Integer.parseInt(textField.getText()) > 0){
					System.out.println(pModel.getCode() + " " + model.getColNames().get(k));
				}
				k++;
			}
			
		}
		
		return personModel;
	}

	private List<PersonModel> getValidPerson(ObservableList<PersonModel> personData, Point3D point) {
		
		if(point == null){
			return null;
		}
		
		if(point.getZ()==10){
			System.out.println("sdcsdf");
		}

		List<PersonModel> result =  new ArrayList<PersonModel>();
		
		for(PersonModel personModel : personData){
			ColumnModel[][] activity = personModel.getActivity();
			int x = 0;
		    try{
		    	x = Integer.parseInt(personModel.getNorma().get((int)point.getZ()).getText());
		    }catch(Exception e){
		    	x = 0;
		    }
			if(activity[(int)point.getX()][(int)point.getY()].getCells().get((int)point.getZ()).isClicked() && x>0 &&
					!activity[(int)point.getX()][(int)point.getY()].getCells().get((int)point.getZ()).isVisited()){
				result.add(personModel);
			}
		}
		
		
		return result;
	}

	private int getCondition(ColumnModel[][] activity) {

		int count = 0;
		
		for(int i=0;i<activity.length;i++){
			for(int j=0;j<activity[i].length;j++){
				for(SquarePanel squarePanel:activity[i][j].getCells()){
					if(squarePanel.isClicked()){
						count++;
					}
				}
			}
		}
		
		return count;
	}

	@Override
	public GeneralModel loadModel() {
		return orarDao.loadModel();
	}

	public OrarDao getOrarDao() {
		return orarDao;
	}

	public void setOrarDao(OrarDao orarDao) {
		this.orarDao = orarDao;
	}

	
}
