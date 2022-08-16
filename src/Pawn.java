public class Pawn extends ChessPiece{
    public Pawn(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if(line==toLine && column==toColumn)return false;
        if(color.equals("White")&&(line>=toLine))return false; // Not one step back! For the glory!
        if(color.equals("Black")&&(line<=toLine))return false; // For the Emperor!
        //off with his head
        if(column!=toColumn){
            if( Math.abs(line-toLine)==1 && Math.abs(column-toColumn)==1)
                if(chessBoard.board[toLine][toColumn]!=null)
                    //stop friendly fire!
                    if(!chessBoard.board[toLine][toColumn].getColor().equals(color))return true;
            return false;
        }
        if(Math.abs(line-toLine)==1 && chessBoard.board[toLine][toColumn]==null)return true;
        //если я правильно помню, пешка не может прыгать через фигуру во время первого хода
        //некоторые правила описывают ход пешек под названием "взятие на проходе" - в данной игре не реализовано
        if(Math.abs(line-toLine)==2 && chessBoard.board[toLine][toColumn]==null && chessBoard.board[line+1][column]==null && color.equals("White") && line==1)return true;
        if(Math.abs(line-toLine)==2 && chessBoard.board[toLine][toColumn]==null && chessBoard.board[line-1][column]==null && color.equals("Black") && line==6)return true;
        return false;
    }

    @Override
    public String getSymbol() {
        return "P";
    }
}
