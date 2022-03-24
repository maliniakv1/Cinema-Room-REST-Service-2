package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
public class CinemaController {

    final private int CINEMA_ROW = 9;
    final private int CINEMA_COLUMN = 9;

    final private String PASSWORD = "super_secret";

    private Cinema cinema = new Cinema(CINEMA_ROW, CINEMA_COLUMN);

    private int currentIncome;
    private int numberOfAvailableSeats;
    private int numberOfPurchasedTickets;

    @GetMapping("/seats")
    public Cinema getSeats() {
        return cinema;
    }

    @PostMapping("/purchase")
    public ResponseEntity purchase(@RequestBody Seat seat) {

        int rowOfSeat = seat.getRow();
        int columnOfSeat = seat.getColumn();

        if (seat.getColumn() > cinema.getTotalColumns() || seat.getRow() > cinema.getTotalRows()
                || seat.getRow() <= 0 || seat.getColumn() <= 0) {
            return new ResponseEntity(Map.of("error", "The number of a row or a column is out of bounds!"), HttpStatus.BAD_REQUEST);
        }

        for (Seat s : cinema.getAvailableSeats()) {
            if (s.getRow() == rowOfSeat && s.getColumn() == columnOfSeat) {

                seat = s;
                if (!seat.getIsAvailable()) {
                    return new ResponseEntity(Map.of("error", "The ticket has been already purchased!"), HttpStatus.BAD_REQUEST);
                }

                seat.setIsAvailable(false);
                seat.getToken().setUUID();
                numberOfPurchasedTickets++;
                currentIncome += seat.getPrice();

                Map<String, Object> result = new LinkedHashMap<String, Object>();

                result.put("token", seat.getToken().getUUID());
                result.put("ticket", seat);

                return new ResponseEntity(result, HttpStatus.OK);

            }
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);

    }

    @PostMapping("/return")
    public ResponseEntity returnTicket (@RequestBody Token token) {

        for (Seat s : cinema.getAvailableSeats()) {
            if (s.getToken().getUUID() == null) {
                continue;
            }
            if (s.getToken().getUUID().equals(token.getUUID()) && !s.getIsAvailable()) {
                Map<String, Seat> result = new HashMap<String, Seat>();
                result.put("returned_ticket", s);
                s.setIsAvailable(true);
                numberOfPurchasedTickets--;
                currentIncome -= s.getPrice();
                return new ResponseEntity(result, HttpStatus.OK);

            }

        }
        return new ResponseEntity(Map.of("error", "Wrong token!"), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/stats")
    public ResponseEntity statistics(@RequestParam(value = "password", required = false) String password)  {

        if (password != null && password.equals(PASSWORD)) {

            numberOfAvailableSeats = CINEMA_ROW * CINEMA_COLUMN - numberOfPurchasedTickets;

            Map<String, Object> result = new LinkedHashMap<String, Object>();

            result.put("current_income", currentIncome);
            result.put("number_of_available_seats", numberOfAvailableSeats);
            result.put("number_of_purchased_tickets", numberOfPurchasedTickets);

            return new ResponseEntity(result, HttpStatus.OK);
        }
        return new ResponseEntity(Map.of("error", "The password is wrong!"), HttpStatus.UNAUTHORIZED);



    }
}
