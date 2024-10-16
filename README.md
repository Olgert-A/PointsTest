# Points
## О проекте
Простой модуль, дающий возможность взаимодействия Пользователей с начисленными Баллами.

## Использование 
В проекте реализовано взаимодействие Рулетка, которое позволяет Пользователю поставить баллы и случайным образом их удвоить либо потратить.
```
public interface RollInteraction {
    void roll(int userID, int pointsToRoll);
}
```
Его имплементация *RollInteractionImpl* получает количество баллов пользователя из модели хранения, проверяет возможность сделать указанную ставку, затем записывает результат обратно в модель хранения.

Возможность сделать ставку проверяется валидатором, который инъектируется в *RollInteractionImpl*
```
public interface RollValidator {
    void CheckUserValid(int userID);
    void CheckPointsValid(int points);
    void CheckUserCanRollPoints(int userPoints, int pointsToRoll);
}
```
Хранение данных описано моделью, которая инъектируется в *RollInteractionImpl*
```
public interface PointsModel {
    void AddUser(int userId, int points);
    void DeleteUser(int userId);
    void SetPoints(int userId, int points);
    int GetPoints(int userId);
}
```
## Тестирование
Использованы библиотеки JUnit и Mockito. 

Т.к. *RollInteractionImpl* обращается к модели данных, интерфейс *PointsModel* мокирован. 
Первый тест использует валидные входные данные и проверяет нормальный сценарий работы.
```
void roll(int startPts, int rollPts, int expectedResult1, int expectedResult2) {
    ArgumentCaptor<Integer> argument = ArgumentCaptor.forClass(Integer.class);
    var expectedResult = new Integer[] {expectedResult1, expectedResult2};

    Mockito.when(model.GetPoints(anyInt())).thenReturn(startPts);

    RollValidator validator = new RollValidatorImpl();
    RollInteractionImpl interaction = new RollInteractionImpl(model, validator);
    interaction.roll(anyInt(), rollPts);

    Mockito.verify(model).GetPoints(anyInt());
    Mockito.verify(model).SetPoints(anyInt(), argument.capture());
    assertTrue(Arrays.asList(expectedResult).contains(argument.getValue()));
}    
```
Второй тест использует невалидные данные и проверяет вызов требуемых исключений.
```
void rollInvalidData(int startPts, int rollPts) {
    Mockito.when(model.getPoints(anyInt())).thenReturn(startPts);

    RollValidator validator = new RollValidatorImpl();
    RollInteractionImpl interaction = new RollInteractionImpl(model, validator);
    assertThrows(IllegalArgumentException.class, () -> {interaction.roll(1, rollPts);});
}
```
