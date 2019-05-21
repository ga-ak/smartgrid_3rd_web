package Model.user;

import java.util.ArrayList;

public class DataDTO {
    private ArrayList<String> time;
    private ArrayList<Integer> usage;

    public DataDTO(ArrayList<String> time, ArrayList<Integer> usage) {
        this.time = time;
        this.usage = usage;
    }

    public ArrayList<String> getTime() {
        return time;
    }

    public ArrayList<Integer> getUsage() {
        return usage;
    }
}
