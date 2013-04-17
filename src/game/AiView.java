package game;

public class AiView extends View {

	private Evaluator evaluator = new Evaluator(this.model);
	
	public AiView(Model m) {
		super(m);
	}

	@Override
	public void update() {
		if( model.isCurrentPlayerIsAI() ){
			for (ViewListener l : listener){
				l.update( evaluator.getAICol() );
			}	
		}		
	}

}
