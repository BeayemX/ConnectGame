package game;

import java.util.ArrayList;

public abstract class View implements ModelListener{

	protected Model model; 
	protected ArrayList <ViewListener> listener = new ArrayList<ViewListener>();
	
	
	public View(Model m){
		this.model = m;
	}

	public void setListener(ViewListener l){
		listener.add(l);
	}
	
	@Override
	public abstract void update();
		
}
