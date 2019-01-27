/*
FILE_NAME: NemoReefToursMenu.java
PUREPOSE:The main/driver class The Nemo Reef Tours System which
            handles menu and navigation
DATE: 26/01/2019
*/

import java.util.Scanner;
import javax.swing.JOptionPane;

public class NemoReefToursMenu {

    final int ENTER_BOOKING = 1;
    final int DISPLAY_BOOKINGS = 2;
    final int DISPLAY_STATISTICS = 3;
    final int SEARCH_BOOKINGS = 4;
    final int SORT_BOOKINGS = 5;
    final int EXIT = 6;
    final int MAX_BOOKINGS = 10;//The maximum possible number of bookings
    int currentBooking = 0;
    Booking[] bookings = new Booking[MAX_BOOKINGS];//Array of booking objects
    Scanner inputMenuChoice = new Scanner(System.in);

    private int getMenuItem() {
        System.out.println("\nPlease select from the following");
        System.out.println(ENTER_BOOKING + ". Enter booking name and number of passengers");
        System.out.println(DISPLAY_BOOKINGS + ". Display all booking names, number of passengers and charges");
        System.out.println(DISPLAY_STATISTICS + ". Display Statistics");
        System.out.println(SEARCH_BOOKINGS + ". Search for booking");
        System.out.println(SORT_BOOKINGS + ". Sort the bookings");
        System.out.println(EXIT + ". Exit the application");
        System.out.print("Enter choice==> ");

        String choice = inputMenuChoice.nextLine();
        while (choice.equals("") || !isStringNumeric(choice)) {
            JOptionPane.showMessageDialog(null, "Error - Menu selection name cannot be blank and must be numeric",
                    "Input Menu Choice", JOptionPane.ERROR_MESSAGE);

            System.out.print("Enter choice==> ");

            choice = inputMenuChoice.nextLine();
        }

        return Integer.parseInt(choice);
    }

    //Checks if a given string is a number or not
    private boolean isStringNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private void processBookings() {
        int choice = getMenuItem();
        while (choice != EXIT) {
            switch (choice) {
                case ENTER_BOOKING:
                    enterBooking();
                    break;
                case DISPLAY_BOOKINGS:
                    displayAllBookings();
                    break;
                case DISPLAY_STATISTICS:
                    displayStatistics();
                    break;
                case SEARCH_BOOKINGS:
                    searchBookings();
                    break;
                case SORT_BOOKINGS:
                    sortBookings();
                    break;
                default:
                    System.out.println("ERROR choice not recognised");
            }
            choice = getMenuItem();
        }
    }

    //Allows for input of bookings using JOptionPane
    private void enterBooking() {
        if (currentBooking == MAX_BOOKINGS) {
            JOptionPane.showMessageDialog(
                    null,
                    "Maximum number of bookings has been reached",
                    "Nemo Reef Tours Management System",
                    JOptionPane.ERROR_MESSAGE
            );
        } else {
            String bookingName = JOptionPane.showInputDialog(
                    null,
                    "Please Enter the booking name",
                    "Input Booking Name",
                    JOptionPane.QUESTION_MESSAGE
            );
            while (bookingName.isEmpty()) {
                JOptionPane.showMessageDialog(
                        null,
                        "Error-Booking name cannot be blank",
                        "Input Booking Name",
                        JOptionPane.ERROR_MESSAGE
                );
                bookingName = JOptionPane.showInputDialog(
                        null,
                        "Please Enter the booking name",
                        "Input Booking Name",
                        JOptionPane.QUESTION_MESSAGE
                );
            }
            String passengersInput = JOptionPane.showInputDialog(
                    null,
                    "Please Enter the number of passengers",
                    "Input Number of Passengers",
                    JOptionPane.QUESTION_MESSAGE
            );
            while (passengersInput.isEmpty() || !isStringNumeric(passengersInput)) {
                JOptionPane.showMessageDialog(
                        null,
                        "Error-The number of passengers cannot be blank and must be numeric",
                        "Input Number of Passengers",
                        JOptionPane.ERROR_MESSAGE
                );
                passengersInput = JOptionPane.showInputDialog(
                        null,
                        "Please Enter the number of passengers",
                        "Input Number of Passengers",
                        JOptionPane.QUESTION_MESSAGE
                );
            }
            int passengers = Integer.parseInt(passengersInput);
            bookings[currentBooking] = new Booking(
                    bookingName,
                    passengers
            );
            displayHeading();
            System.out.printf(
                    "%-30s%-17s%-6s\n",
                    bookings[currentBooking].getBookingName(),
                    bookings[currentBooking].getPassengers(),
                    "$" + bookings[currentBooking].calculateCharge()
            );
            currentBooking += 1;
        }
    }

    private void displayHeading() {
        System.out.printf("%-30s%-17s%-6s\n", "Booking Name", "Passengers", "Charge");
    }

    // Displays all bookings that are in the record
    private void displayAllBookings() {
        if (currentBooking == 0) {
            JOptionPane.showMessageDialog(
                    null,
                    "You must enter at least one Booking",
                    "Display All Bookings",
                    JOptionPane.ERROR_MESSAGE
            );
        } else {
            displayHeading();
            int index = 0;
            for (Booking item : bookings) {
                if (index == currentBooking) {
                    break;
                }
                System.out.printf(
                        "%-30s%-17s%-6s\n",
                        item.getBookingName(),
                        item.getPassengers(),
                        "$" + item.calculateCharge()
                );
                index += 1;
            }
        }
    }

    //Analysing of bookings data
    private void displayStatistics() {
        if (currentBooking == 0) {
            JOptionPane.showMessageDialog(
                    null,
                    "You must enter at least one Booking",
                    "Display Statistics",
                    JOptionPane.ERROR_MESSAGE
            );
        } else {
            int index = 0;
            Booking leadingBooking = bookings[0];
            Booking leastBooking = bookings[0];
            double totalCharges = 0.00;
            int totalPassengers = 0;
            double averagePassengers;
            for (Booking item : bookings) {
                if (index == currentBooking) {
                    break;
                }
                if (item.getPassengers() > leadingBooking.getPassengers()) {
                    leadingBooking = item;
                }
                if (item.getPassengers() < leastBooking.getPassengers()) {
                    leastBooking = item;
                }
                totalCharges += item.calculateCharge();
                totalPassengers += item.getPassengers();
                index += 1;
            }
            averagePassengers = totalPassengers / (new Double(currentBooking));
            System.out.println("Statistics for Nemo Reef Tours");
            System.out.println(leadingBooking.getBookingName() + " has the maximum number of " + leadingBooking.getPassengers() + " passenger(s)");
            System.out.println(leastBooking.getBookingName() + " has the minimum number of " + leastBooking.getBookingName() + " passenger(s)");
            System.out.println("Average number of passengers is " + averagePassengers);
            System.out.println("The total charges are: $" + totalCharges);
        }
    }

    //Sorting of bookings using selection sort algorithm
    private void searchBookings() {
        if (currentBooking == 0) {
            JOptionPane.showMessageDialog(
                    null,
                    "You must enter at least One Booking",
                    "Search Bookings",
                    JOptionPane.ERROR_MESSAGE
            );
        } else {
            String searchBooking = JOptionPane.showInputDialog(
                    null,
                    "Enter Booking Name",
                    "Search Booking",
                    JOptionPane.QUESTION_MESSAGE
            );
            while (searchBooking.isEmpty()) {
                JOptionPane.showMessageDialog(
                        null,
                        "Please Ebnter Booking Name",
                        "Search Booking",
                        JOptionPane.ERROR_MESSAGE
                );
                searchBooking = JOptionPane.showInputDialog(
                        null,
                        "Enter Booking Name",
                        "Search Booking",
                        JOptionPane.QUESTION_MESSAGE
                );
            }
            //Selection sort algorithm
            int index = 0;
            boolean found = false;
            Booking booking = null;
            for (Booking item : bookings) {
                if (index == currentBooking) {
                    break;
                }
                if (searchBooking.toLowerCase().equals(
                        item.getBookingName().toLowerCase())) {
                    found = true;
                    booking = item;
                    break;
                }
                index += 1;
            }
            if (found) {
                System.out.printf("%-30s%-17s%-6s\n", "Booking Found", "Passengers", "Charge");
                System.out.printf(
                        "%-30s%-17s%-6s\n",
                        booking.getBookingName(),
                        booking.getPassengers(),
                        "$" + booking.calculateCharge()
                );
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        searchBooking + " not found",
                        "Search Booking",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    //Sorting of bookings using selection sort
    private void sortBookings() {
        if (currentBooking == 0) {
            JOptionPane.showMessageDialog(
                    null,
                    "Sort Bookings",
                    "Sort Bookings",
                    JOptionPane.ERROR_MESSAGE
            );
        } else {
            for (int i = 0; i < currentBooking - 1; i++) {
                Booking min = bookings[i];
                int currentMinIndex = i;
                for (int j = i + 1; j < currentBooking; j++) {
                    if (min.getBookingName().compareTo(bookings[j].getBookingName()) > 0) {
                        min = bookings[j];
                        currentMinIndex = j;
                    }
                }
                if (currentMinIndex != i) {
                    bookings[currentMinIndex] = bookings[i];
                    bookings[i] = min;
                }
            }
            displayHeading();
            int index = 0;
            for (Booking item : bookings) {
                if (index == currentBooking) {
                    break;
                }
                System.out.printf(
                        "%-30s%-17s%-6s\n",
                        item.getBookingName(),
                        item.getPassengers(),
                        item.calculateCharge()
                );
                index += 1;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Nemo Reef Tours Management System");
        NemoReefToursMenu app = new NemoReefToursMenu();
        app.processBookings();
        System.out.println("Thank you for using the Nemo Reef Tours Management Program");
    }
}
