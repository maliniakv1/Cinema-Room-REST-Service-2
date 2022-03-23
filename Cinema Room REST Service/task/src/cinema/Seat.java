package cinema;


import com.fasterxml.jackson.annotation.JsonIgnore;

public class Seat {
    private int row;
    private int column;
    private int price;
    @JsonIgnore
    private boolean isAvaliable;

    public Seat() {
        super();
    }

    public Seat(int row, int column){
        this.row = row;
        this.column = column;
        setPrice(column);
        this.isAvaliable = true;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getPrice() {
        return price;
    }
    private void setPrice(int row) {
        this.price = row > 4 ? 8 : 10;
    }
    public void setIsAvaliable(boolean isAvaliavle) {
        this.isAvaliable = isAvaliavle;
    }
    public boolean getIsAvaliable() {
        return isAvaliable;
    }
}
