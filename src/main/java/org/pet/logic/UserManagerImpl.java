package org.pet.logic;

import org.pet.dal.PointsModel;

public class UserManagerImpl implements UserManager {
    private final PointsModel model;

    public UserManagerImpl(PointsModel model) {
        this.model = model;
    }

    @Override
    public void AddUser(int userId, int points) throws IllegalArgumentException {
        this.model.AddUser(userId, points);
    }

    @Override
    public void DeleteUser(int userId) throws IllegalArgumentException {
        this.model.DeleteUser(userId);
    }
}
