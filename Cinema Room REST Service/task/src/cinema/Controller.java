package cinema;

import cinema.response.Error;
import cinema.response.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.Map;
import java.util.UUID;

@RestController
public class Controller {
    @Autowired
    private Cinema cinema;

    @GetMapping("/seats")
    public Cinema  getAvailableSeats() {
        return cinema;
    }

    @PostMapping("/stats")
    public Object getCinemaStats(@RequestParam(required = false) String password) {
        if (password == null || !CinemaStats.passwordCheck(password)) {
            return new ResponseEntity<>(new Error(Error.WRONG_PASSWORD), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(cinema.getStats(), HttpStatus.OK);
    }

    @PostMapping("/return")
    public Object returnSeat(@RequestBody Map<String, UUID> seatToken) {
        UUID token = seatToken.get("token");
        if (!cinema.getPurchasedTickets().containsKey(token)) {
            return new ResponseEntity<>(new Error(Error.WRONG_TOKEN), HttpStatus.BAD_REQUEST);
        }
        Seat purchasedSeat = cinema.getPurchasedTickets().get(token);
        cinema.getAvailableSeats().add(purchasedSeat);
        cinema.getAvailableSeats().sort(Comparator.comparing(Seat::getRow)
                .thenComparing(Seat::getColumn));
        cinema.getPurchasedTickets().remove(token);
        cinema.changeStats();
        return Map.of("returned_ticket", purchasedSeat);
    }

    @PostMapping("/purchase")
    public Object setPurchase(@RequestBody Seat seat) {
        int row = seat.getRow();
        int column = seat.getColumn();
        if (row < 1 || row > 9 || column < 1 || column > 9) {
            return new ResponseEntity<>(new Error(Error.ROW_COLUMN_OUT_OF_BOUNDS_REASON), HttpStatus.BAD_REQUEST);
        }
        if (!cinema.getAvailableSeats().contains(seat)) {
            return new ResponseEntity<>(new Error(Error.ALREADY_PURCHASED_REASON), HttpStatus.BAD_REQUEST);
        }
        int seatIndex = cinema.getAvailableSeats().indexOf(seat);
        Seat purchasedSeat = cinema.getAvailableSeats().get(seatIndex);
        Ticket purchasedTicket = new Ticket(UUID.randomUUID(), purchasedSeat);
        cinema.getPurchasedTickets().put(purchasedTicket.getToken(), purchasedTicket.getTicket());
        cinema.getAvailableSeats().remove(seat);
        cinema.changeStats();
        return new ResponseEntity<>(purchasedTicket, HttpStatus.OK);
    }
}

