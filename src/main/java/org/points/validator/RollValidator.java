package org.points.validator;

public interface RollValidator {
    void checkUserValid(int userID);
    void checkPointsValid(int points);
    void checkUserCanRollPoints(int userPoints, int pointsToRoll);
}

