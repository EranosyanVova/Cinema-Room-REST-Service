package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;
import java.util.UUID;

public class Seat {
    private int row;
    private int column;
    private int price;
    boolean available = true;
    UUID token;

    public Seat() {
        this.token = UUID.randomUUID();
    }

    public Seat( UUID token) {
        this.token = token;
    }

    public Seat(int row, int column) {
        this();
        this.row = row;
        this.column = column;
    }

    public Seat(int row, int column, int price) {
        this(row, column);
        this.price = price;
    }

    public Seat(int row, int column, int price, boolean available) {
        this(row, column, price);
        this.available = available;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @JsonIgnore
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @JsonIgnore
    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }

        if (!(otherObject instanceof Seat)) {
            return false;
        }

        Seat seat = (Seat) otherObject;

        return getRow() == seat.getRow() && getColumn() == seat.getColumn() || Objects.equals(getToken(), seat.getToken());
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + row;
        result = 31 * result + column;
        result = 31 * result + (token == null ? 0 : token.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Seat {" +
                "row= " + row +
                ", column= " + column +
                '}';
    }
}
