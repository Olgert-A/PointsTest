package org.points.validator;

public interface RollValidator {
    void checkUserValid(int userID) throws IllegalArgumentException;
    void checkPointsValid(int points) throws IllegalArgumentException;
    void checkUserCanRollPoints(int userPoints, int pointsToRoll) throws IllegalArgumentException;
}

