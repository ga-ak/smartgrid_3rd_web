package Model.user;

import java.util.ArrayList;

public class StaticsDTO {

    private ArrayList<String> month;
    //어레이리스트

    private ArrayList<StaticsSeries> series;
    private ArrayList<Integer> pay;

    public StaticsDTO(ArrayList<String> month, ArrayList<StaticsSeries> series, ArrayList<Integer> pay) {
        this.month = month;
        this.series = series;
        this.pay = pay;
    }

    public ArrayList<String> getMonth() {
        return month;
    }

    public ArrayList<StaticsSeries> getSeries() {
        return series;
    }

    public ArrayList<Integer> getPay() {
        return pay;
    }
}
