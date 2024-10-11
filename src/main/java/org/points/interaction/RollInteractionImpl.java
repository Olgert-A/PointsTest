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
        validator.CheckUserValid(userID);
        validator.CheckPointsValid(pointsToRoll);

        int userPoints = this.model.GetPoints(userID);
        validator.CheckUserCanRollPoints(userPoints, pointsToRoll);

        int[] rollSigns = new int[] {-1, 1};
        int randomIndex = new Random().nextInt(rollSigns.length);
        int rollSign = rollSigns[randomIndex];
        int rollResultPoints = rollSign * pointsToRoll;

        this.model.SetPoints(userID, userPoints + rollResultPoints);
    }
}
