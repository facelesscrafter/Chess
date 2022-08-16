public class Rook extends ChessPiece{
    public Rook(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if(line==toLine&&column==toColumn)return false;
        if(chessBoard.board[toLine][toColumn]!=null)
            if(chessBoard.board[toLine][toColumn].getColor().equals(color)) {
                return false; //stop friendly fire!
            }
        if(line==toLine&&column!=toColumn) {
            for(int i=Math.min(column,toColumn)+1;i<Math.max(column,toColumn);i++){
                if(chessBoard.board[line][i]!=null)return false;//hey, you are not a train
            }
            return true;
        }
        if(line!=toLine&&column==toColumn) {
            for(int i=Math.min(line,toLine)+1;i<Math.max(line,toLine);i++){
                if(chessBoard.board[i][column]!=null)return false; //you are not a train (2)

            }
            return true;
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "R";
    }
}
