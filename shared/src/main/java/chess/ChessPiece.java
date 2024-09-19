package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {

    ChessGame.TeamColor pieceColor;
    ChessPiece.PieceType type;

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.pieceColor = pieceColor;
        this.type = type;
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return pieceColor;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return type;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        int x = myPosition.getColumn();
        int y = myPosition.getRow();

        Collection <ChessMove> validMoves = new HashSet<>();
//        validMoves.add(new ChessMove(new ChessPosition(x,y), new ChessPosition(x,y),promotionPiece));
//        ChessPiece tempPiece = board.getPiece()
        switch (type){


            case KING:
                //up
                if(y<8){
                    ChessPiece tempPiece = board.getPiece(new ChessPosition(y+1,x));
                    if(tempPiece == null || tempPiece.getTeamColor() != pieceColor){
                        validMoves.add(new ChessMove(new ChessPosition(y,x), new ChessPosition(y+1,x),null));
                    }
                }
                //down
                if(y>1){
                    ChessPiece tempPiece = board.getPiece(new ChessPosition(y-1,x));
                    if(tempPiece == null || tempPiece.getTeamColor() != pieceColor){
                        validMoves.add(new ChessMove(new ChessPosition(y,x), new ChessPosition(y-1,x),null));
                    }
                }
                //right
                if(x<8){
                    ChessPiece tempPiece = board.getPiece(new ChessPosition(y,x+1));
                    if(tempPiece == null || tempPiece.getTeamColor() != pieceColor){
                        validMoves.add(new ChessMove(new ChessPosition(y,x), new ChessPosition(y,x+1),null));
                    }
                }
                //left
                if(x>1){
                    ChessPiece tempPiece = board.getPiece(new ChessPosition(y,x-1));
                    if(tempPiece == null || tempPiece.getTeamColor() != pieceColor){
                        validMoves.add(new ChessMove(new ChessPosition(y,x), new ChessPosition(y,x-1),null));
                    }
                }
                //up-right
                if(x<8 && y<8){
                    ChessPiece tempPiece = board.getPiece(new ChessPosition(y+1,x+1));
                    if(tempPiece == null || tempPiece.getTeamColor() != pieceColor){
                        validMoves.add(new ChessMove(new ChessPosition(y,x), new ChessPosition(y+1,x+1),null));
                    }
                }
                //up-left
                if(x>1 && y<8){
                    ChessPiece tempPiece = board.getPiece(new ChessPosition(y-1,x+1));
                    if(tempPiece == null || tempPiece.getTeamColor() != pieceColor){
                        validMoves.add(new ChessMove(new ChessPosition(y,x), new ChessPosition(y-1,x+1),null));
                    }
                }
                //down-right
                if(x<8 && y>1){
                    ChessPiece tempPiece = board.getPiece(new ChessPosition(y-1,x+1));
                    if(tempPiece == null || tempPiece.getTeamColor() != pieceColor){
                        validMoves.add(new ChessMove(new ChessPosition(y,x), new ChessPosition(y-1,x+1),null));
                    }
                }
                //down-left
                if(x>1 && y>1){
                    ChessPiece tempPiece = board.getPiece(new ChessPosition(y-1,x-1));
                    if(tempPiece == null || tempPiece.getTeamColor() != pieceColor){
                        validMoves.add(new ChessMove(new ChessPosition(y,x), new ChessPosition(y-1,x-1),null));
                    }
                }

                break;

            case QUEEN:
                //up
                //down
                //right
                //left
                //up-right
                //up-left
                //down-right
                //down-left
                break;

            case KNIGHT:
                //up-right
                //up-left
                //right-up
                //right-down
                //down-right
                //down-left
                //left-up
                //left-down
                break;

            case ROOK:
                //up
                //down
                //right
                //left
                break;

            case BISHOP:
                //up-right
                //up-left
                //down-right
                //down-left
                break;

            case PAWN:
                //one-forward
                //two-forward
                //capture-right
                //capture-left
                //promotion
                break;


        }

        return validMoves;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessPiece that = (ChessPiece) o;
        return pieceColor == that.pieceColor && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceColor, type);
    }

    @Override
    public String toString() {
        return "ChessPiece{" +
                "pieceColor=" + pieceColor +
                ", type=" + type +
                '}';
    }
}
