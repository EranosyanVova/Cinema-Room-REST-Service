package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Cinema {
    @JsonProperty("total_rows")
    private int totalRows;
    @JsonProperty("total_columns")
    private int totalColumns ;
    @JsonProperty("available_seats")
    private List<Seat> availableSeats;

    public Cinema(int totalRows, int totalColumns,  List<Seat> availableSeats) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        this.availableSeats = availableSeats;
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
}
