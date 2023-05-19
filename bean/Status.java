package 数据库12306.bean;

public class Status {
    private String train_id;
    private String date;
    private String status;

    public Status() {
    }

    public Status(String train_id, String date, String status) {
        this.train_id = train_id;
        this.date = date;
        this.status = status;
    }

    public String getTrain_id() {
        return train_id;
    }

    public void setTrain_id(String train_id) {
        this.train_id = train_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
