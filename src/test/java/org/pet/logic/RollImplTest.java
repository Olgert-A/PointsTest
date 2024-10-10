package org.pet.logic;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.pet.dal.PointsModel;

class RollImplTest {
    @Mock
    PointsModel model;

    @Test
    void roll() {
        int userID = 1;
        Mockito.when(model.GetPoints(userID)).thenReturn(10);

        RollInteractionImpl interaction = new RollInteractionImpl(model);
        interaction.roll(userID, 5);

        Mockito.verify(model).SetPoints(userID, 5);
        Mockito.verify(model).SetPoints(userID, -5);
    }
}