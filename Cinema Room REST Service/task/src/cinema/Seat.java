package cinema;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Seat {
    private int row;
    private int column;
    private int price;
    @JsonIgnore
    private boolean isAvailable;
    @JsonIgnore
    private Token token;



    @JsonCreator
    public Seat(@JsonProperty("row") int row, @JsonProperty("column") int column){
        this.row = row;
        this.column = column;
        setPrice(row);
        this.isAvailable = true;
        this.token = new Token();
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

    private void setPrice(int row) {
        this.price = row > 4 ? 8 : 10;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }


}
