package game;

import java.io.IOException;

public class MVC_Test {

	public static void main(String[] args) throws IOException {
		
		System.out.println("test");
		Model m = new Model(7, 5);
		ConsoleView v = new ConsoleView(m);
		Controller c = new Controller(m, v);
		

		m.setModelListener(v);
		v.setListener(c);
		
		v.printBoard();
		
		while( !m.isGameOver() ){
			v.getInput();
		}
	}

}
