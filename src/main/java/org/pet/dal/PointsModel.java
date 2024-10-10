package org.pet.dal;

public interface PointsModel {
    void AddUser(int userId, int points) throws IllegalArgumentException;
    void DeleteUser(int userId) throws IllegalArgumentException;
    void SetPoints(int userId, int points) throws IllegalArgumentException;
    int GetPoints(int userId) throws IllegalArgumentException;
}
