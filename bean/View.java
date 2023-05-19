package 数据库12306.bean;

public class View {
    private String train_id;
    private String view_id;
    private String city_name;
    private String arrive_time;
    private String mileage;
    private String stop_time;

    public View() {
    }

    public View(String train_id, String view_id, String city_name, String arrive_time, String mileage, String stop_time) {
        this.train_id = train_id;
        this.view_id = view_id;
        this.city_name = city_name;
        this.arrive_time = arrive_time;
        this.mileage = mileage;
        this.stop_time = stop_time;
    }

    public String getTrain_id() {
        return train_id;
    }

    public void setTrain_id(String train_id) {
        this.train_id = train_id;
    }

    public String getView_id() {
        return view_id;
    }

    public void setView_id(String view_id) {
        this.view_id = view_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getArrive_time() {
        return arrive_time;
    }

    public void setArrive_time(String arrive_time) {
        this.arrive_time = arrive_time;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getStop_time() {
        return stop_time;
    }

    public void setStop_time(String stop_time) {
        this.stop_time = stop_time;
    }
}
