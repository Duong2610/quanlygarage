package quanlygargae;

public class BTL {
    private String maBT;
    private String tenBT;
    private int time;

    public BTL(String maBT) {
        this.maBT = maBT;
    }

    public BTL(String maBT, String tenBT, int time) {
        this.maBT = maBT;
        this.tenBT = tenBT;
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public String getMaBT() {
        return maBT;
    }

    public String getTenBT() {
        return tenBT;
    }

    @Override
    public String toString() {
        return maBT + "\t" + tenBT + "\t" + time;
    }
}