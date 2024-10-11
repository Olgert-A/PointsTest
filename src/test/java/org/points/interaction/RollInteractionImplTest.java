package org.points.interaction;


import org.mockito.Mock;
import org.points.dal.PointsModel;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.points.validator.RollValidator;
import org.points.validator.RollValidatorImpl;


@ExtendWith(MockitoExtension.class)
class RollInteractionImplTest {
    @Mock
    PointsModel model;

    @ParameterizedTest(name = "User with {0} pts roll {1} and then have {2} or {3} pts ")
    @CsvSource({
            "20, 5, 15, 25",
            "1000, 1000, 2000, 0"
    })
    void roll(int startPts, int rollPts, int expectedResult1, int expectedResult2) {
        ArgumentCaptor<Integer> argument = ArgumentCaptor.forClass(Integer.class);
        var expectedResult = new Integer[] {expectedResult1, expectedResult2};

        Mockito.when(model.getPoints(anyInt())).thenReturn(startPts);

        RollValidator validator = new RollValidatorImpl();
        RollInteractionImpl interaction = new RollInteractionImpl(model, validator);
        interaction.roll(anyInt(), rollPts);

        Mockito.verify(model).getPoints(anyInt());
        Mockito.verify(model).setPoints(anyInt(), argument.capture());
        assertTrue(Arrays.asList(expectedResult).contains(argument.getValue()));
    }
}