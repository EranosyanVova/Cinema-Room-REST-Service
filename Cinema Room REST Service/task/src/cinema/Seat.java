package cinema;

public class Seat {
    private int row;
    private int column;
    private int price;

    public Seat() {
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

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }

        if (!(otherObject instanceof Seat)) {
            return false;
        }

        Seat seat = (Seat) otherObject;

        return getRow() == seat.getRow() && getColumn() == seat.getColumn();
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + row;
        result = 31 * result + column;
        result = 31 * result + price;
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
