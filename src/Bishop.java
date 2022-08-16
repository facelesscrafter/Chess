public class Bishop extends ChessPiece {
    public Bishop(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if(line==toLine || column==toColumn)return false;
        if(chessBoard.board[toLine][toColumn]!=null)
            if(chessBoard.board[toLine][toColumn].equals(color))return false; //stop friendly fire!
        if(Math.abs(line-toLine)==Math.abs(column-toColumn)){
            int i=Math.min(line,toLine);
            int j = i==line ? column : toColumn;
            int step = i==line ? toColumn : column;
            step = j<step? 1: -1;
            for(i=i+1,j=j+step;i<Math.max(line,toLine);i++,j=j+step){   //you are not a train
                if(chessBoard.board[i][j]!=null)return false;

            }
            return true;
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "B";
    }
}
