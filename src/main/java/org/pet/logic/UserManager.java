package org.pet.logic;

public interface UserManager {
    void AddUser(int userId, int points) throws IllegalArgumentException;
    void DeleteUser(int userId) throws IllegalArgumentException;
}
