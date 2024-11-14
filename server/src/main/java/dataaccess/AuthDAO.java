package dataaccess;

import model.AuthData;

public interface AuthDAO {
    void createAuth(AuthData u) throws DataAccessException;
    AuthData getAuth(String Auth) throws DataAccessException;
    void deleteAuth(AuthData u) throws DataAccessException;

    //createAuth
    //getAuth
    //deleteAuth
}
