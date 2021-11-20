package cinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@RestController
public class Controller {
    @Autowired
    private Cinema cinema;

    @GetMapping("/seats")
    public Cinema  returnAvailableSeats() {
        return cinema;
    }

    @PostMapping("/return")
    public Map<String, Object> returnSeat(@RequestBody Map<String, UUID> seatToken) {
        UUID token = seatToken.get("token");
        if (!isSeatsContainToken(token)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong token!");
        }
        int seatIndex = cinema.getAvailableSeats().indexOf(new Seat(token));
        System.out.println(seatIndex);
        Seat purchasedSeat = cinema.getAvailableSeats().get(seatIndex);
        if (purchasedSeat.isAvailable()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong token!");
        }
        purchasedSeat.setAvailable(true);
        return Map.of("returned_ticket", purchasedSeat);
    }

    public boolean isSeatsContainToken(UUID token) {
        return cinema.getAvailableSeats()
                .stream()
                .filter(seat -> Objects.equals(seat.getToken(), token))
                .findFirst().orElse(null) != null;
    }

    //Можно использовать ResponseEntity с возвращаемым типом Object, тогда не нужно писать ExceptionHandler

    @PostMapping("/purchase")
    public Map<String, Object> /*Object*/ setPurchase(@RequestBody Seat seat) {
        if (!cinema.getAvailableSeats().contains(seat)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The number of a row or a column is out of bounds!");
            //return new ResponseEntity<>(Map.of("error", "The number of a row or a column is out of bounds!"), HttpStatus.BAD_REQUEST);
        }
        int seatIndex = cinema.getAvailableSeats().indexOf(seat);
        Seat purchasedSeat = cinema.getAvailableSeats().get(seatIndex);
        if (!purchasedSeat.isAvailable()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The ticket has been already purchased!");
        }
        purchasedSeat.setAvailable(false);
        return purchaseSeatInfo(purchasedSeat.getToken(), purchasedSeat);
    }

    public Map<String, Object> purchaseSeatInfo(UUID seatUUID, Seat purchasedSeat) {
        Map<String, Object> returnPurchaseSeat = new LinkedHashMap<>();
        returnPurchaseSeat.put("token", seatUUID);
        returnPurchaseSeat.put("ticket", purchasedSeat);
        return returnPurchaseSeat;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ResponseStatusException.class)
    public Map<String, String> handleException(ResponseStatusException e) {
        return Map.of("error", Objects.requireNonNull(e.getReason()));
    }
}

