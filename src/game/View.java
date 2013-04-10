package game;

public abstract class View implements Modellistener{

	Model model; 
	
	public View(Model m){
		this.model = m;
	}

	@Override
	public abstract void update();
		
}
