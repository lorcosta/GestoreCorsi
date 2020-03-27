package it.polito.tdp.corsi.model;

public class TestModel {

	
	public void run() {
		Model model= new Model();
		System.out.println(model.getCorsiByPeriodo(1));
		
		System.out.println("********Stampo iscritti per secondo periodo didattico******");
		System.out.println(model.getIscrittiByPeriodo(2));
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestModel model=new TestModel();
		model.run();
		
	}

}
