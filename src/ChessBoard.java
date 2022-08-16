public class ChessBoard {
    public ChessPiece[][] board = new ChessPiece[8][8]; // creating a field for game
    String nowPlayer;
    boolean kingDeath=false;
    int wKing[] = {0,4};
    int bKing[] = {7,4};

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {
        return this.nowPlayer;
    }


    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        if (checkPos(startLine) && checkPos(startColumn)) {
            if (board[startLine][startColumn]==null) {
                System.out.println("Вы пытаетесь сдвинуть пустое место");
                return false;
            }
            if (!nowPlayer.equals(board[startLine][startColumn].getColor())) {
                System.out.println("Это не Ваша фигура");
                return false;
            }
            if (board[startLine][startColumn].canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {
                if(board[endLine][endColumn]!=null)
                    if(board[endLine][endColumn].getSymbol().equals("K"))kingDeath=true;
                board[endLine][endColumn] = board[startLine][startColumn]; // if piece can move, we moved a piece
                board[startLine][startColumn] = null; // set null to previous cell
                if (board[endLine][endColumn].getSymbol().equals("K") && this.nowPlayerColor().equals("White")) {
                    this.wKing[0]=endLine;
                    this.wKing[1]=endColumn;
                }
                if (board[endLine][endColumn].getSymbol().equals("K") && this.nowPlayerColor().equals("Black")) {
                    this.bKing[0]=endLine;
                    this.bKing[1]=endColumn;
                }
                //Check for king?
                if (!board[endLine][endColumn].getSymbol().equals("K") && this.nowPlayerColor().equals("Black"))
                    if(new King("White").isUnderAttack(this, this.wKing[0],this.wKing[1]))System.out.println("Шах белому королю! Спасите короля!");
                if (!board[endLine][endColumn].getSymbol().equals("K") && this.nowPlayerColor().equals("White"))
                    if(new King("Black").isUnderAttack(this, this.bKing[0],this.bKing[1]))System.out.println("Шах черному королю! Спасите короля!");
                if(!this.kingDeath)this.nowPlayer = this.nowPlayerColor().equals("White") ? "Black" : "White";
                board[endLine][endColumn].check=false; //why just for rook and king? Do this for every piece;
                return true;
            } else return false;
        } else return false;
    }

    public void printBoard() {  //print board in console
        if(!this.kingDeath)System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("Player 2(Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");
        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol() + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Player 1(White)");
        if(!this.kingDeath)System.out.println("---------\nCurrent player is "+nowPlayer);
        else System.out.println("---------\nИгрок "+ nowPlayer+ " поставил мат и таким образом победил!!! ");
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }

    public boolean castling0() {
        if (nowPlayer.equals("White")) {
            if (board[0][0] == null || board[0][4] == null) return false;
            if (board[0][0].getSymbol().equals("R") && board[0][4].getSymbol().equals("K") && // check that King and Rook
                    board[0][1] == null && board[0][2] == null && board[0][3] == null) {              // never moved
                if (board[0][0].getColor().equals("White") && board[0][4].getColor().equals("White") &&
                        board[0][0].check && board[0][4].check &&
                        !new King("White").isUnderAttack(this, 0, 2)) { // check that position not in under attack
                    board[0][4] = null;
                    board[0][2] = new King("White");   // move King
                    board[0][2].check = false;
                    this.wKing[0]=0;
                    this.wKing[1]=2;
                    board[0][0] = null;
                    board[0][3] = new Rook("White");   // move Rook
                    board[0][3].check = false;
                    nowPlayer = "Black";  // next turn
                    return true;
                } else return false;
            } else return false;
        } else {
            if (board[7][0] == null || board[7][4] == null) return false;
            if (board[7][0].getSymbol().equals("R") && board[7][4].getSymbol().equals("K") && // check that King and Rook
                    board[7][1] == null && board[7][2] == null && board[7][3] == null) {              // never moved
                if (board[7][0].getColor().equals("Black") && board[7][4].getColor().equals("Black") &&
                        board[7][0].check && board[7][4].check &&
                        !new King("Black").isUnderAttack(this, 7, 2)) { // check that position not in under attack
                    board[7][4] = null;
                    board[7][2] = new King("Black");   // move King
                    board[7][2].check = false;
                    this.bKing[0]=7;
                    this.bKing[1]=2;
                    board[7][0] = null;
                    board[7][3] = new Rook("Black");   // move Rook
                    board[7][3].check = false;
                    nowPlayer = "White";  // next turn
                    return true;
                } else return false;
            } else return false;
        }
    }
    public boolean castling7() {
        if (nowPlayer.equals("White")) {
            if (board[0][7] == null || board[0][4] == null) return false;
            if (board[0][7].getSymbol().equals("R") && board[0][4].getSymbol().equals("K") && // check that King and Rook
                    board[0][5] == null && board[0][6] == null ) {              // never moved
                if (board[0][7].getColor().equals("White") && board[0][4].getColor().equals("White") &&
                        board[0][7].check && board[0][4].check &&
                        !new King("White").isUnderAttack(this, 0, 6)) { // check that position not in under attack
                    board[0][4] = null;
                    board[0][6] = new King("White");   // move King
                    board[0][6].check = false;
                    this.wKing[0]=0;
                    this.wKing[1]=6;
                    board[0][7] = null;
                    board[0][5] = new Rook("White");   // move Rook
                    board[0][5].check = false;
                    nowPlayer = "Black";  // next turn
                    return true;
                } else return false;
            } else return false;
        } else {
            if (board[7][7] == null || board[7][4] == null) return false;
            if (board[7][7].getSymbol().equals("R") && board[7][4].getSymbol().equals("K") && // check that King and Rook
                    board[7][5] == null && board[7][6] == null ) {              // never moved
                if (board[7][7].getColor().equals("Black") && board[7][4].getColor().equals("Black") &&
                        board[7][7].check && board[7][4].check &&
                        !new King("Black").isUnderAttack(this, 7, 6)) { // check that position not in under attack
                    board[7][4] = null;
                    board[7][6] = new King("Black");   // move King
                    board[7][6].check = false;
                    this.bKing[0]=7;
                    this.bKing[1]=6;
                    board[7][7] = null;
                    board[7][5] = new Rook("Black");   // move Rook
                    board[7][5].check = false;
                    nowPlayer = "White";  // next turn
                    return true;
                } else return false;
            } else return false;
        }
    }

}
