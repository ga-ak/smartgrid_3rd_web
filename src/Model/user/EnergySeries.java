package Model.user;

import java.util.ArrayList;

public class EnergySeries {
    private String name;
    private ArrayList<Integer> energy;

    public EnergySeries(String name, ArrayList<Integer> energy) {
        this.name = name;
        this.energy = energy;
    }

    public String getName() {
        return name;
    }


    public ArrayList<Integer> getEnergy() {
        return energy;
    }

}
