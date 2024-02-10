package app;

import utils.DynamicArrayList;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author zemlicka
 */
public class ShoppingList {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DynamicArrayList shoppingList = new DynamicArrayList();

        System.out.println("Welcome to your Shopping List app.");
        System.out.println("Please enter how many entries you would like to\n" +
                "add to your shopping list:");
        int sizeOfList = getValidChoice(sc);

        for (int i = 0; i < sizeOfList; i++){
            System.out.print("Enter " + (i+1) + ". : ");
            String item = sc.nextLine();
            shoppingList.add(item);
        }

        System.out.println("Your shopping list :");
        for (int i = 0; i < shoppingList.size(); i++){
            System.out.println((i + 1) + ". " + shoppingList.get(i));
        }

        while(true){
            System.out.println("Menu:\n" +
                    "1. Remove an item\n" +
                    "2. Add an item\n" +
                    "3. Set an item at position\n" +
                    "4. Get the size of shopping list\n" +
                    "5. Get item at position\n" +
                    "6. Display shopping list\n" +
                    "7. Remove an item at position\n" +
                    "8. Last index of value\n" +
                    "9. Get position of a value\n" +
                    "10. Exit");

            System.out.println("Choose an option (1-10):");
            int choice = getValidChoice(sc);

            switch (choice) {
                case 1: //Remove by item
                    System.out.println("Please enter item you want to remove: ");
                    String removeItem = sc.nextLine();
                    boolean removed = shoppingList.remove(removeItem);
                    if (removed) {
                        System.out.println("First instance of '" + removeItem + "' removed from the list.");
                    } else {
                        System.out.println("'" + removeItem + "' not found in the list.");
                    }
                    break;
                case 2: //Add item
                    System.out.println("Enter an item you want to add: ");
                    String addItem = sc.nextLine();
                    shoppingList.add(addItem);
                    break;
                case 3: //Set an item at position
                    System.out.println("Enter an item to set: ");
                    String setItem = sc.nextLine();
                    System.out.println("Enter a position to set the item: ");
                    int setPosition = getValidChoice(sc);
                    shoppingList.set(setPosition, setItem);
                    break;
                case 4: //Get size of shopping list
                    System.out.println("Size of your shopping list: " + shoppingList.size() + " items.");
                    break;
                case 5: // Get item at position
                    System.out.print("Enter the position to get the item: ");
                    int getPosition = getValidChoice(sc);
                    System.out.println("Item at position " + getPosition + ": " + shoppingList.get(getPosition - 1));
                    break;
                case 6: //Display the shopping list
                    for (int i = 0; i < shoppingList.size(); i++){
                        System.out.println((i + 1) + ". " + shoppingList.get(i));
                    }
                    break;
                case 7: //Remove an item at position
                    System.out.println("Enter position you want to delete: ");
                    int index = getValidChoice(sc);
                    shoppingList.remove(index);
                    break;
                case 8: //Last index of value
                    System.out.println("Enter the item to find the last position of: ");
                    String item = sc.nextLine();
                    int lastIndex = shoppingList.lastIndexOf(item);
                    if (lastIndex != -1) {
                        System.out.println("Last index of '" + item + "': " + (lastIndex + 1));
                    } else {
                        System.out.println("'" + item + "' not found in the list.");
                    }
                    break;
                case 9: //Get position of a value
                    System.out.print("Enter the item to find the index of: ");
                    String valueToFindIndex = sc.nextLine();
                    int foundIndex = shoppingList.indexOf(valueToFindIndex);
                    if (foundIndex != -1) {
                        System.out.println("Index of '" + valueToFindIndex + "': " + (foundIndex + 1));
                    } else {
                        System.out.println("'" + valueToFindIndex + "' not found in the list.");
                    }
                    break;
                case 10: //Exit the program
                    System.out.println("Program is shutting down..");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please choose a valid option (1-10).");
            }
        }
    }

    public static int getValidChoice(Scanner scanner){
        int choice = 0;

        while(choice == 0) {
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }

        return choice;
    }
}
