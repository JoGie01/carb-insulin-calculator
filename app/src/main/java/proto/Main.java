package proto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import proto.model.*;
import proto.service.*;
import proto.storage.*;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        JSONUserRepository userRepo = new JSONUserRepository("user.json");
        User user = userRepo.getUser();

        JSONSingleFoodRepository repo = new JSONSingleFoodRepository("foods.json");

        if(user==null) {
            System.out.println("Please enter your name:");
            String name = scanner.nextLine();
            System.out.println("Please enter your age:");
            int age = Integer.parseInt(scanner.nextLine());
            System.out.println("Please enter your Insulin Factor:");
            float factor = Float.parseFloat(scanner.nextLine());

            user = new User(name, age, factor);
            userRepo.save(user);
        } 

        System.out.println("This is the Carb & Insulin Calculator!");
        System.out.println("Hi " + user.getName() + "!");

        while (true) {
            
            System.out.println("What would you like to do?");
            System.out.println("1: Calculate carbs and insulin for a meal");
            System.out.println("2: View foods");
            System.out.println("3: Add a new food");
            System.out.println("4: Customize user informataion");
            System.out.println("5: Exit");
            
            String input = scanner.nextLine();

            switch(input) {
                case "1":
                    List <Ingredient> ingredients;
                    Meal meal;
                    Calculator calculator;

                    System.out.println("What would you like to calculate?");
                    System.out.println("1: Calculate a quick meal");
                    System.out.println("2: Choose from saved foods");
                    System.out.println("3: Choose a recipe");
                    System.out.println("4: Go back");

                    String option = scanner.nextLine();
                    switch (option) {
                        case "1":
                            ingredients = new ArrayList<>();
                            System.out.println("Please enter the Ingredients of your meal");
                            while (true) {

                                System.out.println("Enter your ingredient (name, carbs per 100g, weight in g) or 'done'");
                                String ingredientInput = scanner.nextLine();
                                if(ingredientInput.equals("done")) break;

                                String[] mealComponent = ingredientInput.split(",");
                                if(mealComponent.length != 3) {
                                    System.out.println("Invalid input, please enter in the format: name, carbs per 100g, weight in g");
                                    continue;
                                }

                                try {
                                    String name = mealComponent[0].trim();
                                    float carbsPer100g = Float.parseFloat(mealComponent[1].trim());
                                    float weight = Float.parseFloat(mealComponent[2].trim());

                                    SingleFood food = new SingleFood(name, carbsPer100g);
                                    Ingredient ingredient = new Ingredient(food, weight);
                                    ingredients.add(ingredient);
                                } catch (NumberFormatException e) {
                                    System.out.println("Invalid number format, please try again.");
                                } catch (IllegalArgumentException e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                            meal = new Meal(ingredients);
                            calculator = new Calculator(user, meal);
                            System.out.println("Total Carbs: " + meal.getTotalCarbs());
                            System.out.println("Insulin Factor: " + user.getFactor());
                            System.out.println("Insulin Units: " + calculator.calculateInsulinDose());
                            continue;
                    
                        case "2":
                            System.out.println("All saved foods:");
                            for (SingleFood food : repo.findAll()) {
                                System.out.println("- " + food.getName() + ": " + food.getCarbsPer100g() + "g carbs per 100g");
                            }

                            ingredients = new ArrayList<>();
                        
                            while (true) {
                                System.out.println("Enter the name of the food you want to have or 'done'");
                                String foodName = scanner.nextLine();
                                if (foodName.equals("done")) break;

                                SingleFood food = repo.findByName(foodName);
                                if (food == null) {
                                    System.out.println("Food not found, please try again.");
                                    continue;
                                } else {
                                    System.out.println("Enter the weight in grams");
                                    String weightInput = scanner.nextLine();
                                    try {
                                        float weight = Float.parseFloat(weightInput);
                                        Ingredient ingredient = new Ingredient(food, weight);
                                        ingredients.add(ingredient);
                                    } catch (NumberFormatException e) {
                                        System.out.println("Invalid number format, please try again.");
                                    } catch (IllegalArgumentException e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                            }
                            meal = new Meal(ingredients);
                            calculator = new Calculator(user, meal);
                            System.out.println("Total Carbs: " + meal.getTotalCarbs());
                            System.out.println("Insulin Factor: " + user.getFactor());
                            System.out.println("Insulin Units: " + calculator.calculateInsulinDose());
                            break;

                            case "3":
                                System.out.println("Not implemented yet.");
                                continue;

                            case "4":
                                continue;
                        }
                    case "2":
                        System.out.println("What would you like to view?");
                        System.out.println("1: View all foods");
                        System.out.println("2: View all SingleFoods");
                        System.out.println("3: View all Recipes");
                        System.out.println("4: Return");

                        String viewOption = scanner.nextLine();

                        switch (viewOption) {
                            case "1":

                            case "2":
                                List<SingleFood> foodList = repo.findAll();
                                
                                for(SingleFood s: foodList) {
                                    System.out.println(s);
                                }
                                continue;

                            case "3":
                                System.out.println("Not implemented yet.");
                                continue;

                            case "4":
                                continue;
                        }

                    case "3":
                        System.out.println("What would you like to add?");
                        System.out.println("1: Single Food");
                        System.out.println("2: Recipe");
                        System.out.println("3: Return");
                        String saveChoice = scanner.nextLine();

                        switch (saveChoice) {
                            case "1":
                                System.out.println("Please enter: food name, carbs per 100g");
                                String newSingleFoodString = scanner.nextLine();
                                String[] components = newSingleFoodString.split(",");

                                if(components.length != 2) {
                                    System.out.println("Invalid input");
                                    continue;
                                }

                                try {
                                    String name = components[0].trim();
                                    float carbsPer100g = Float.parseFloat(components[1].trim());

                                    SingleFood food = new SingleFood(name, carbsPer100g);
                                    repo.save(food);

                                } catch (NumberFormatException e) {
                                    System.out.println("Invalid number format, please try again.");
                                } catch (IllegalArgumentException e) {
                                    System.out.println(e.getMessage());
                                }
                                System.out.println("SingleFood saved.");
                                continue;

                            case "2":
                                System.out.println("Not implemented yet");
                                continue;

                            case "3":
                                continue;    
                            
                        }
                        
                    case "4":
                        System.out.println("Current user information:");
                        System.out.println("Name: " + user.getName());
                        System.out.println("Age: " + user.getAge());
                        System.out.println("Insulin Factor: " + user.getFactor());

                        System.out.println("Enter new name (or press Enter to keep current):");
                        String newName = scanner.nextLine();
                        if (!newName.isEmpty()) {
                            user.setName(newName);
                        }

                        System.out.println("Enter new age (or press Enter to keep current):");
                        String newAgeInput = scanner.nextLine();
                        if (!newAgeInput.isEmpty()) {
                            try {
                                int newAge = Integer.parseInt(newAgeInput);
                                user.setAge(newAge);
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid number format");
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage() + " Age not updated.");
                            }
                        }

                        System.out.println("Enter new insulin factor (or press Enter to keep current):");
                        String newFactorInput = scanner.nextLine();
                        if (!newFactorInput.isEmpty()) {
                            try {
                                float newFactor = Float.parseFloat(newFactorInput);
                                user.setFactor(newFactor);
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid number format");
                            }
                        }

                        userRepo.save(user);
                        System.out.println("User information updated.");
                        break;

                    case "5":
                        System.out.println("Goodbye!");
                        scanner.close();
                        return;
                }
        }
    }
}
