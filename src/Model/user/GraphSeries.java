package Model.user;

import java.util.ArrayList;

public class GraphSeries {
    private String name;
    private ArrayList<Integer> data;
    private int[] data_today;

    public GraphSeries(String name, ArrayList<Integer> data) {
        this.name = name;
        this.data = data;
    }

    public GraphSeries(String name, int[] data_today) {
        this.name = name;
        this.data_today = data_today;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Integer> getData() {
        return data;
    }
}
