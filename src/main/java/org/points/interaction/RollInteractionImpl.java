package org.points.interaction;
import org.points.dal.PointsModel;
import org.points.validator.RollValidator;

import java.util.Random;

public class RollInteractionImpl implements RollInteraction {
    private final PointsModel model;
    private final RollValidator validator;

    public RollInteractionImpl(PointsModel model, RollValidator validator) {
        this.model = model;
        this.validator = validator;
    }

    @Override
    public void roll(int userID, int pointsToRoll) throws IllegalArgumentException {
        validator.checkUserValid(userID);
        validator.checkPointsValid(pointsToRoll);

        int userPoints = this.model.getPoints(userID);
        validator.checkUserCanRollPoints(userPoints, pointsToRoll);

        int[] rollSigns = new int[] {-1, 1};
        int randomIndex = new Random().nextInt(rollSigns.length);
        int rollSign = rollSigns[randomIndex];
        int rollResultPoints = rollSign * pointsToRoll;

        this.model.setPoints(userID, userPoints + rollResultPoints);
    }
}
