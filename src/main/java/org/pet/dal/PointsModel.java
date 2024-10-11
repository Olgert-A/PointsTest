package org.pet.dal;

public interface PointsModel {
    void AddUser(int userId, int points);
    void DeleteUser(int userId);
    void SetPoints(int userId, int points);
    int GetPoints(int userId);
}
