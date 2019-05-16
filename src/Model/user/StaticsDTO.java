package Model.user;

import java.util.ArrayList;

public class StaticsDTO {

    private ArrayList<String> month;
    private ArrayList<Integer> energy;
    private ArrayList<Integer> month_pay;

    public StaticsDTO(ArrayList<String> month, ArrayList<Integer> energy, ArrayList<Integer> month_pay) {
        this.month = month;
        this.energy = energy;
        this.month_pay = month_pay;
    }

    public ArrayList<String> getMonth() {
        return month;
    }

    public ArrayList<Integer> getEnergy() {
        return energy;
    }

    public ArrayList<Integer> getMonth_pay() {
        return month_pay;
    }
}
