package org.points.dal;

public interface PointsModel {
    void addUser(int userId, int points);
    void deleteUser(int userId);
    void setPoints(int userId, int points);
    int getPoints(int userId);
}
