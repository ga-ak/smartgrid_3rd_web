package Model.user;

import java.util.ArrayList;

public class StaticsSeries {

    private String name;
    private ArrayList<Integer> data;

    public StaticsSeries(String name, ArrayList<Integer> data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Integer> getData() {
        return data;
    }
}
