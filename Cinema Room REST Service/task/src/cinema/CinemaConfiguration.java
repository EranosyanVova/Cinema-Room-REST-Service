package cinema;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class CinemaConfiguration {
    private static final int MAX_ROW_VALUE = 9;
    private static final int MAX_COLUMN_VALUE = 9;
    private static final int PRICE_FOR_SEAT_BEFORE_FIFTH_ROW = 10;
    private static final int PRICE_FOR_SEAT_AFTER_FOURTH_ROW = 8;

    @Bean
    public Cinema availableSeats() {
        List<Seat> availableSeatsList = new ArrayList<>();
        for (int i = 1; i <= MAX_ROW_VALUE; i++) {
            for (int j = 1; j <= MAX_COLUMN_VALUE; j++) {
                if (i <= 4) {
                    availableSeatsList.add(new Seat(i, j, PRICE_FOR_SEAT_BEFORE_FIFTH_ROW));
                } else {
                    availableSeatsList.add(new Seat(i, j, PRICE_FOR_SEAT_AFTER_FOURTH_ROW));
                }
            }
        }
        return new Cinema(MAX_ROW_VALUE, MAX_COLUMN_VALUE, availableSeatsList);
    }
}
