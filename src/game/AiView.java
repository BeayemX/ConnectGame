package game;

public class AiView extends View {

	private Evaluator evaluator = new Evaluator(this.model);
	private int infinite = 10000;
	
	public AiView(Model m) {
		super(m);
	}

	@Override
	public void update() {
		if( model.isCurrentPlayerAI() ){
			for (ViewListener l : listener){
				l.update( evaluator.getAICol() );
			}	
		}		
	}
	
	/*
	// TODO letzten Zug mitgeben, und nur letzte Aenderung ueberpruefen
	public int miniMax(int player, int depth) {
		
		if( evaluator.isWinner(player) ){
//			return Integer.MAX_VALUE;
			return infinite;
		}
		
		if( evaluator.isWinner( nextPlayer() ) ){
//			return Integer.MIN_VALUE+1;
			return -infinite;
		}
		
		// TODO draw --> 0 
		
		// TODO feld voll / gewonnen
		if(depth == 0){
			return eval();
		}
		
		
		for(int col=0; col<model.numCols(); ++col){
			if(evaluator.isFull(col)){
				continue;				
			}
			model.setDisc(col);
			value = -miniMax( nextPlayer(player), depth-1 );
			undoMove();
			if(value > maxValue){
				maxValue = value;
			}
		}
		
	}
	//*/
}
