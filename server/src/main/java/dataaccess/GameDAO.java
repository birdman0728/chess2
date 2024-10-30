package dataaccess;

import model.GameData;

public interface GameDAO {
    void insertUser(GameData u) throws DataAccessException;
    GameData readUser(GameData u) throws DataAccessException;
    void updateUser(GameData u) throws DataAccessException;
    void deleteUser(GameData u) throws DataAccessException;
}
