package 数据库12306.bean;

public class Orders {
    private String orders_id;
    private String user_id;
    private String user_idcard;
    private String user_name;
    private String train_id;
    private String train_name;
    private String seat_type;
    private String seat_raw;
    private String seat_location;
    private String status;
    private String start_time;
    private String start;
    private String terminus;
    private String carriage;

    public Orders() {
    }

    public Orders(String orders_id, String user_id, String user_idcard, String user_name, String train_id,
                  String train_name, String seat_type, String seat_raw, String seat_location, String status,
                  String start_time, String start, String terminus, String carriage) {
        this.orders_id = orders_id;
        this.user_id = user_id;
        this.user_idcard = user_idcard;
        this.user_name = user_name;
        this.train_id = train_id;
        this.train_name = train_name;
        this.seat_type = seat_type;
        this.seat_raw = seat_raw;
        this.seat_location = seat_location;
        this.status = status;
        this.start_time = start_time;
        this.start = start;
        this.terminus = terminus;
        this.carriage = carriage;
    }

    public String getOrders_id() {
        return orders_id;
    }

    public void setOrders_id(String orders_id) {
        this.orders_id = orders_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_idcard() {
        return user_idcard;
    }

    public void setUser_idcard(String user_idcard) {
        this.user_idcard = user_idcard;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getTrain_id() {
        return train_id;
    }

    public void setTrain_id(String train_id) {
        this.train_id = train_id;
    }

    public String getTrain_name() {
        return train_name;
    }

    public void setTrain_name(String train_name) {
        this.train_name = train_name;
    }

    public String getSeat_type() {
        return seat_type;
    }

    public void setSeat_type(String seat_type) {
        this.seat_type = seat_type;
    }

    public String getSeat_raw() {
        return seat_raw;
    }

    public void setSeat_raw(String seat_raw) {
        this.seat_raw = seat_raw;
    }

    public String getSeat_location() {
        return seat_location;
    }

    public void setSeat_location(String seat_location) {
        this.seat_location = seat_location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getTerminus() {
        return terminus;
    }

    public void setTerminus(String terminus) {
        this.terminus = terminus;
    }

    public String getCarriage() {
        return carriage;
    }

    public void setCarriage(String carriage) {
        this.carriage = carriage;
    }

    @Override
    public String toString() {
        return "orders_id='" + orders_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", user_idcard='" + user_idcard + '\'' +
                ", user_name='" + user_name + '\'' +
                ", train_id='" + train_id + '\'' +
                ", train_name='" + train_name + '\'' +
                ", seat_type='" + seat_type + '\'' +
                ", seat_raw='" + seat_raw + '\'' +
                ", seat_location='" + seat_location + '\'' +
                ", status='" + status + '\'' +
                ", start_time='" + start_time + '\'' +
                ", start='" + start + '\'' +
                ", terminus='" + terminus + '\'' +
                ", carriage='" + carriage + '\'';
    }
}
