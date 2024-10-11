package org.points.validator;

public interface RollValidator {
    void CheckUserValid(int userID) throws IllegalArgumentException;
    void CheckPointsValid(int points) throws IllegalArgumentException;
    void CheckUserCanRollPoints(int userPoints, int pointsToRoll) throws IllegalArgumentException;
}

