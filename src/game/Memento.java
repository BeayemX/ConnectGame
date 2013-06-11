package game;

public interface Memento {

	public void reset();

	public void seal();

	public void putCell(CellState state);

	public CellState nextCell();

}
