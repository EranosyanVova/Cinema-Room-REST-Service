package cinema.response;

public class Error {
    public static final String ALREADY_PURCHASED_REASON = "The ticket has been already purchased!";
    public static final String ROW_COLUMN_OUT_OF_BOUNDS_REASON = "The number of a row or a column is out of bounds!";
    public static final String WRONG_TOKEN = "Wrong token!";
    public static final String WRONG_PASSWORD = "The password is wrong!";

    String error;

    public Error(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
