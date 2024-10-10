package org.pet.logic;
import org.pet.dal.PointsModel;

import java.util.Random;

public class RollImpl implements RollInteraction {
    private final PointsModel model;

    public RollImpl(PointsModel model) {
        this.model = model;
    }

    @Override
    public void roll(int userID, int pointsToRoll) throws IllegalArgumentException {
        if (pointsToRoll <= 0 )
            throw new IllegalArgumentException("Points to roll must me > 0, get = " + pointsToRoll);

        int userPoints = this.model.GetPoints(userID);

        if (pointsToRoll > userPoints)
            throw new IllegalArgumentException("User points less then points to roll, " +
                                               "UserPoints = " + userPoints +
                                               " Points to roll = " + pointsToRoll);

        int[] rollSigns = new int[] {-1, 1};
        int randomIndex = new Random().nextInt(rollSigns.length);
        int rollSign = rollSigns[randomIndex];
        int rollResultPoints = rollSign * userPoints;

        this.model.SetPoints(userID, rollResultPoints);
    }
}
