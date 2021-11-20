package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class CinemaStats {
    @JsonProperty("current_income")
    private int currentIncome;
    @JsonProperty("number_of_available_seats")
    private int numberOfAvailableSeats;
    @JsonProperty("number_of_purchased_tickets")
    private int numberOfPurchaseTickets;
    private final static String PASSWORD = "Vova";

    public void setStats(int currentIncome, int numberOfAvailableSeats, int numberOfPurchaseTickets) {
        setCurrentIncome(currentIncome);
        setNumberOfAvailableSeats(numberOfAvailableSeats);
        setNumberOfPurchaseTickets(numberOfPurchaseTickets);
    }

    public static boolean passwordCheck(String password) {
        return Objects.equals(PASSWORD, password);
    }

    public int getCurrentIncome() {
        return currentIncome;
    }

    public int getNumberOfAvailableSeats() {
        return numberOfAvailableSeats;
    }

    public int getNumberOfPurchaseTickets() {
        return numberOfPurchaseTickets;
    }

    public void setCurrentIncome(int currentIncome) {
        this.currentIncome = currentIncome;
    }

    public void setNumberOfAvailableSeats(int numberOfAvailableSeats) {
        this.numberOfAvailableSeats = numberOfAvailableSeats;
    }

    public void setNumberOfPurchaseTickets(int numberOfPurchaseTickets) {
        this.numberOfPurchaseTickets = numberOfPurchaseTickets;
    }
}
