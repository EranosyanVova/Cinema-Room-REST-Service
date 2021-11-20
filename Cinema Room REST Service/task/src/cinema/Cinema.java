package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Cinema {
    @JsonProperty("total_rows")
    private int totalRows;
    @JsonProperty("total_columns")
    private int totalColumns ;
    @JsonProperty("available_seats")
    private List<Seat> availableSeats;
    private Map<UUID, Seat> purchasedTickets = new HashMap<>();
    private CinemaStats stats = new CinemaStats();

    public Cinema(int totalRows, int totalColumns,  List<Seat> availableSeats) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        this.availableSeats = availableSeats;
        changeStats();
    }

    public void changeStats() {
        int currentIncome = purchasedTickets.values().stream().mapToInt(Seat::getPrice).sum();
        int numberOfAvailableSeats = availableSeats.size();
        int numberOfPurchaseTickets = purchasedTickets.size();
        stats.setStats(currentIncome, numberOfAvailableSeats, numberOfPurchaseTickets);
    }

    public int getTotalRows() {
        return totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public List<Seat> getAvailableSeats() {
        return availableSeats;
    }

    @JsonIgnore
    public Map<UUID, Seat> getPurchasedTickets() {
        return purchasedTickets;
    }

    @JsonIgnore
    public CinemaStats getStats() {
        return stats;
    }
}
