package org.pet.logic;


import org.mockito.Mock;
import org.pet.dal.PointsModel;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class RollImplTest {
    @Mock
    PointsModel model;

    @ParameterizedTest(name = "User with {0} pts roll {1} and then have +5 or -5 pts ")
    @CsvSource({
            "20, 5, 15, 25"
    })
    void roll(int startPts, int rollPts, int expectedResult1, int expectedResult2) {
        ArgumentCaptor<Integer> argument = ArgumentCaptor.forClass(Integer.class);
        var expectedResult = new Integer[] {expectedResult1, expectedResult2};

        Mockito.when(model.GetPoints(anyInt())).thenReturn(startPts);

        RollInteractionImpl interaction = new RollInteractionImpl(model);
        interaction.roll(anyInt(), rollPts);

        Mockito.verify(model).GetPoints(anyInt());
        Mockito.verify(model).SetPoints(anyInt(), argument.capture());
        assertTrue(Arrays.asList(expectedResult).contains(argument.getValue()));
    }
}