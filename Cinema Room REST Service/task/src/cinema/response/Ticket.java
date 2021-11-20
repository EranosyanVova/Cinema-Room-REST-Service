package cinema.response;

import cinema.Seat;

import java.util.UUID;

public class Ticket {
    private UUID token;
    private Seat ticket;

    public Ticket(UUID token, Seat ticket) {
        this.token = token;
        this.ticket = ticket;
    }

    public UUID getToken() {
        return token;
    }

    public Seat getTicket() {
        return ticket;
    }
}
