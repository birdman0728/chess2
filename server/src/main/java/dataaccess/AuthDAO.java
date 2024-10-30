package dataaccess;

import model.AuthData;

public interface AuthDAO {
    void insertUser(AuthData u) throws DataAccessException;
    AuthData readUser(AuthData u) throws DataAccessException;
    void updateUser(AuthData u) throws DataAccessException;
    void deleteUser(AuthData u) throws DataAccessException;
}
