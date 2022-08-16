public class King extends ChessPiece{
    public King(String color) {
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
        if((line==toLine&&column!=toColumn&&(Math.abs(column-toColumn)==1)) ||
            (line!=toLine&&column==toColumn&&(Math.abs(line-toLine)==1)) ||
            (Math.abs(line-toLine)==Math.abs(column-toColumn)&&Math.abs(line-toLine)==1)) {
            if(this.isUnderAttack(chessBoard,toLine,toColumn)){
                System.out.println("Король не может ходить на поле, находящееся под ударом фигуры соперника");
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "K";
    }
    public boolean isUnderAttack(ChessBoard chessBoard, int line, int column){
        //придется для каждой вражеской фигуры вызывать canMoveToPosition
        //ох, и не люблю я шахматы...
        //СЛАВА ГЕРОЯМ МЕЧА и МАГИИ III!
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++){
                if(chessBoard.board[i][j]!=null)
                    if(!chessBoard.board[i][j].getColor().equals(color))
                        if(chessBoard.board[i][j].canMoveToPosition(chessBoard,i,j,line,column))return true;
            }
        return false;
    }
}
