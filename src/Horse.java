public class Horse extends ChessPiece{
    public Horse(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if(line==toLine && column==toColumn)return false;
        if(chessBoard.board[toLine][toColumn]!=null)
            if(chessBoard.board[toLine][toColumn].getColor().equals(color))return false; //stop friendly fire!
        if(Math.abs(line-toLine)==2 && Math.abs(column-toColumn)==1)return true;
        if(Math.abs(line-toLine)==1 && Math.abs(column-toColumn)==2)return true;

        return false;
    }

    @Override
    public String getSymbol() {
        return "H";
    }
}
