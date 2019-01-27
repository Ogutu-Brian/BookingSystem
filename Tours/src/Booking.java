/*
NAME: GOPPI CHATTI
STUDENT ID: 12095772
FILE_NAME: Bookng.java
DATE: 26/01/2019
PURPOSE:Blueprint for booking objects
*/
class Booking {
    // variable
    private String bookingName;
    // variable for the number of passenger
    private int passengers;
    /// unit charge price per person
    private final double UNIT_CHARGE = 85.50;
    // Constants for percentage discounts
    private final double DISCOUNT_1 = 0.0;
    private final double DISCOUNT_2 = 0.10;
    private final double DISCOUNT_3 = 0.15;
    private final double DISCOUNT_4 = 0.20;

    // The default constructor
    public Booking() {
    }

    // The parameterised constructor
    public Booking(String bookingName, int passengers) {
        this.bookingName = bookingName;
        this.passengers = passengers;
    }

    // Sets the booking name
    public void setBookingName(String bookingName, int passengers) {
        this.bookingName = bookingName;
        this.passengers = passengers;
    }

    // sets the number of passengers
    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    // Gets the total number of passengers
    public int getPassengers() {
        return passengers;
    }

    public String getBookingName() {
        return bookingName;
    }

    // Calculates the total charge
    public double calculateCharge() {
        double discount;
        double charge = UNIT_CHARGE * passengers;
        if (passengers <= 2) {
            discount = DISCOUNT_1;
        } else if (passengers <= 5) {
            discount = DISCOUNT_2;
        } else if (passengers <= 10) {
            discount = DISCOUNT_3;
        } else {
            discount = DISCOUNT_4;
        }
        double totalCharge = passengers * UNIT_CHARGE;
        double deduction = discount * totalCharge;
        return totalCharge - deduction;
    }
}