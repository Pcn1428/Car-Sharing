package carsharing.front;

public class Menu {

    public Menu() {
    }

    public void printOpeningMenu() {
        System.out.println("1. Log in as a manager");
        System.out.println("0. Exit");
    }

    public void printLoggedInMenu() {
        System.out.println("1. Company list");
        System.out.println("2. Create a company");
        System.out.println("0. Back");
    }
}
