package 数据库12306.bean;

public class Train {
    private String train_id;
    private String train_name;
    private String train_type;
    private String start;
    private String terminus;

    public Train() {
    }

    public Train(String train_id, String train_name, String train_type, String start, String terminus) {
        this.train_id = train_id;
        this.train_name = train_name;
        this.train_type = train_type;
        this.start = start;
        this.terminus = terminus;
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

    public String getTrain_type() {
        return train_type;
    }

    public void setTrain_type(String train_type) {
        this.train_type = train_type;
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

    @Override
    public String toString() {
        return "train_id='" + train_id + '\'' +
                ", train_name='" + train_name + '\'' +
                ", train_type='" + train_type + '\'' +
                ", start='" + start + '\'' +
                ", terminus='" + terminus + '\'';
    }

}
