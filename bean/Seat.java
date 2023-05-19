package 数据库12306.bean;

public class Seat {
    private String train_id;
    private String seat_id;
    private String seat_type;
    private String seat_location;
    private String carriage;
    private String seat_raw;
    private String seat_rest;

    public Seat() {
    }

    public Seat(String train_id, String seat_id, String seat_type, String seat_location, String carriage,String seat_raw,String seat_rest) {
        this.train_id = train_id;
        this.seat_id = seat_id;
        this.seat_type = seat_type;
        this.seat_location = seat_location;
        this.carriage = carriage;
        this.seat_raw =seat_raw;
        this.seat_rest=seat_rest;
    }

    public String getSeat_rest() {
        return seat_rest;
    }

    public void setSeat_rest(String seat_rest) {
        this.seat_rest = seat_rest;
    }

    public String getTrain_id() {
        return train_id;
    }

    public void setTrain_id(String train_id) {
        this.train_id = train_id;
    }

    public String getSeat_id() {
        return seat_id;
    }

    public void setSeat_id(String seat_id) {
        this.seat_id = seat_id;
    }

    public String getSeat_type() {
        return seat_type;
    }

    public void setSeat_type(String seat_type) {
        this.seat_type = seat_type;
    }

    public String getSeat_location() {
        return seat_location;
    }

    public void setSeat_location(String seat_location) {
        this.seat_location = seat_location;
    }

    public String getCarriage() {
        return carriage;
    }

    public void setCarriage(String carriage) {
        this.carriage = carriage;
    }

    public String getSeat_raw() {
        return seat_raw;
    }

    public void setSeat_raw(String seat_raw) {
        this.seat_raw = seat_raw;
    }

    @Override
    public String toString() {
        return
                "train_id='" + train_id + '\'' +
                ", seat_id='" + seat_id + '\'' +
                ", seat_type='" + seat_type + '\'' +
                ", seat_location='" + seat_location + '\'' +
                ", carriage='" + carriage + '\'' +
                ", seat_raw='" + seat_raw + '\'' +
                ", seat_rest='" + seat_rest + '\'';
    }
}
