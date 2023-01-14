package carsharing;

import carsharing.data.CompanyDao;
import carsharing.front.Menu;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String dbName = "test";
        if (args[0].equals("-databaseFileName") &&
                args.length > 1) {
            dbName = args[1];
        }

        CompanyDao dao = new CompanyDao(dbName);
        dao.dropCompanyTable();
        dao.createCompanyTable();

        boolean isRunning = true;
        boolean isLoggedIn = false;

        Menu menu = new Menu();

        Scanner scanner = new Scanner(System.in);

        while (isRunning) {

            if (!isLoggedIn) {
                menu.printOpeningMenu();
            } else {
                menu.printLoggedInMenu();
            }

            int option = scanner.nextInt();

            // Case 0: exit
            if (!isLoggedIn && option == 0 ) isRunning = false;
            // Case 1a: manager log in
            else if (!isLoggedIn && option == 1) {
                isLoggedIn = true;
            }
            // Case 0b: return to opening menu
            else if (isLoggedIn && option == 0) {
                isLoggedIn = false;
            }
            // Case 1b: print out list of companies
            else if (isLoggedIn && option == 1) {
                ArrayList<String> list = dao.getListOfAllCompanies();
                if (list.size() == 0) {
                    System.out.println("The company list is empty!");
                } else {
                    for (String s : list) System.out.println(s);
                }
            }
            // Case 2b: add a company to the database
            else if (isLoggedIn && option == 2) {
                System.out.println("Enter the company name:");
                String companyName = scanner.nextLine();
                companyName = scanner.nextLine();
                dao.addCompany(companyName);
                System.out.println("The company was created!");
            }
        }



    }
}