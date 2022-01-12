package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Cinema {

    @JsonProperty("total_rows")
    private int totalRows;

    @JsonProperty("total_columns")
    private int totalColumns;

    @JsonProperty("available_seats")
    private List<Seat> availableSeats;

    public Cinema(int totalRows, int totalColumns){
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;

        this.availableSeats = new ArrayList<>();
        for (int i = 1; i <= totalRows; i++) {
            for (int j = 1; j <= totalColumns; j++) {
                availableSeats.add(new Seat(i, j));
            }
        }
    }
    

    public int getTotalColumns() {
        return totalColumns;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalColumns(int totalColumns) {
        this.totalColumns = totalColumns;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public List<Seat> getAvailableSeats() {
        return this.availableSeats;
    }

    public void setAvailableSeats(List<Seat> availableSeats) {
        this.availableSeats = availableSeats;
    }


}
