package 数据库12306.socket.Massage;

public class Massage {
    private String Tag;
    private String Data[];

    public Massage() {
    }

    public String[] getData() {
        return Data;
    }

    public void setData(String[] data) {
        Data = data;
    }

    public String getTag() {
        return Tag;
    }

    public void setTag(String tag) {
        Tag = tag;
    }

    public Massage(String tag, String[] data) {
        Tag = tag;
        Data = data;
    }
}
