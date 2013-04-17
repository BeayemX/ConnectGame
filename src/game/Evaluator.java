package game;

public class Evaluator {

	// TODO copy of the original model to test the different possibilities
	private Model model;
	
	public Evaluator(Model m){
		this.model = m;
	}
	
	public int getAICol(){
//		for is just for testing if right returns are created
//		for (int i=0; i<220; ++i){
//			System.out.print( (int)( Math.random() * (this.model.getCols()) ) + ", ");
//		}
		return (int) ( Math.random() * ( this.model.getCols() ) );
	}
}
