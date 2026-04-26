package proto.service;

import proto.model.User;
import proto.model.Meal;

public class Calculator {
    
    private User user;
    private Meal meal;

    public Calculator(User user, Meal meal) {
        this.user = user;
        this.meal = meal;
    }

    public int calculateInsulinDose() {
        float totalCarbs = meal.getTotalCarbs();
        return Math.round((totalCarbs / 10) * user.getFactor());
    }
}
