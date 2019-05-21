package Model.user;

import java.util.ArrayList;

public class StaticsDTO {

    private ArrayList<String> categories;
    //어레이리스트

    private ArrayList<StaticsSeries> series;
    private ArrayList<Integer> pay;

    public StaticsDTO(ArrayList<String> categories, ArrayList<StaticsSeries> series, ArrayList<Integer> pay) {
        this.categories = categories;
        this.series = series;
        this.pay = pay;
    }

    public ArrayList<String> getMonth() {
        return categories;
    }

    public ArrayList<StaticsSeries> getSeries() {
        return series;
    }

    public ArrayList<Integer> getPay() {
        return pay;
    }
}
