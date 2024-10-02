package chess;

import java.util.Collection;
import java.util.Objects;

/**
 * For a class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame {

    ChessBoard curBoard = new ChessBoard();
    TeamColor currentColorTurn;

    public ChessGame() {

    }

    /**
     * @return Which team's turn it is
     */
    public TeamColor getTeamTurn() {
        return currentColorTurn;
    }

    /**
     * Set's which teams turn it is
     *
     * @param team the team whose turn it is
     */
    public void setTeamTurn(TeamColor team) {
        currentColorTurn = team;
    }

    /**
     * Enum identifying the 2 possible teams in a chess game
     */
    public enum TeamColor {
        WHITE,
        BLACK
    }

    /**
     * Gets a valid moves for a piece at the given location
     *
     * @param startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     * startPosition
     */
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        Collection<ChessMove> possibleMoves = curBoard.getPiece(startPosition).pieceMoves(curBoard,startPosition);
        Collection<ChessMove> validatedMoves = null;
        ChessPiece workingPiece = curBoard.getPiece(startPosition);
        for(ChessMove move: possibleMoves){
            curBoard.addPiece(move.getEndPosition(), workingPiece);
            curBoard.removePiece(startPosition);
            if(!isInCheck(workingPiece.getTeamColor())){
                validatedMoves.add(move);
            }
            curBoard.addPiece(startPosition,workingPiece);
            curBoard.removePiece(move.getEndPosition());

        }

        return validatedMoves;
    }

    /**
     * Makes a move in a chess game
     *
     * @param move chess move to preform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        ChessPiece movingPiece = curBoard.getPiece(move.startPostition);
        boolean movesMatch = false;

        for(ChessMove posMove: validMoves(move.getStartPosition())){
            if(posMove.getEndPosition() == move.getEndPosition()){
                movesMatch = true;
            }
        }

        if(movesMatch){
            if(move.getPromotionPiece() == null) {//non-promotionPiece
                curBoard.addPiece(move.endPosition, movingPiece);
            }else{//promotionPiece
                curBoard.addPiece(move.endPosition, new ChessPiece( movingPiece.getTeamColor(),move.getPromotionPiece()));
            }
            curBoard.removePiece(move.startPostition);
        }else{
            throw new InvalidMoveException("The move is invalid: " + move.toString());
        }
    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        ChessPiece checkKing = new ChessPiece(teamColor, ChessPiece.PieceType.KING);
        ChessPosition checkKingPos = null;
        boolean isInCheck = false;
        //check for king
        for(int i = 1; i <=8 ; i++){
            for(int j = 1; j <=8; j++){
                if(curBoard.getPiece(new ChessPosition(i,j)) == checkKing){
                    checkKingPos = new ChessPosition(i,j);
                }
            }
        }
        //Check all possible other team moves
        for(ChessMove move: checkTeamMoves(oppColor(teamColor))){
            if (move.endPosition == checkKingPos) {
                isInCheck = true;
            }
        }

        return isInCheck;
    }


    public Collection<ChessMove> checkTeamMoves(TeamColor teamColor){
        Collection<ChessMove> AllPossibleMoves = null;
        for(int i = 1; i <=8 ; i++) {
            for (int j = 1; j <= 8; j++) {
                AllPossibleMoves.addAll(validMoves(new ChessPosition(i,j)));
            }
        }
        return AllPossibleMoves;
    }


    public TeamColor oppColor(TeamColor teamColor){
        if (teamColor == TeamColor.WHITE){
            return TeamColor.BLACK;
        }else{
            return TeamColor.WHITE;
        }
    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {
        boolean isInCheckmate = false;
        if(isInCheck(teamColor)){
            if(checkTeamMoves(teamColor) == null){
                isInCheckmate = true;
            }
        }
        return isInCheckmate;
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        boolean isInStalemate = false;
        if(!isInCheck(teamColor)){
            if (checkTeamMoves(teamColor) == null) {
                isInStalemate = true;
            }
        }
        return isInStalemate;
    }

    /**
     * Sets this game's chessboard with a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {
        curBoard = board;
    }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() {
        return curBoard;
    }

    @Override
    public String toString() {
        return "ChessGame{" +
                "curBoard=" + curBoard +
                ", currentColorTurn=" + currentColorTurn +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessGame chessGame = (ChessGame) o;
        return Objects.equals(curBoard, chessGame.curBoard) && currentColorTurn == chessGame.currentColorTurn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(curBoard, currentColorTurn);
    }
}
