package dataaccess;

import model.GameData;

import javax.xml.crypto.Data;
import java.util.List;


public interface GameDAO {
    void createGame(GameData u);
    GameData getGame(int ID) throws DataAccessException;
    List<GameData> listGame();
    void updateGame(int gameID, GameData newGame) throws DataAccessException;

}
