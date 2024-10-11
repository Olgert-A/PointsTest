package org.points.validator;

public class RollValidatorImpl implements RollValidator {
    /**
     * Always valid. Override to validate with your conditions.
     */
    @Override
    public void checkUserValid(int userID) throws IllegalArgumentException {

    }


    @Override
    public void checkPointsValid(int points) throws IllegalArgumentException {
        if (points < 0)
            throw new IllegalArgumentException("Points to roll must be > 0, get = " + points);
    }

    @Override
    public void checkUserCanRollPoints(int userPoints, int pointsToRoll) throws IllegalArgumentException {
        if (userPoints < pointsToRoll)
            throw new IllegalArgumentException("User points less then points to roll, " +
                    "UserPoints = " + userPoints +
                    " Points to roll = " + pointsToRoll);
    }
}
