package Model.userGraph;

import java.util.ArrayList;

public class GraphDTO {

    private ArrayList<String> categories;
    private ArrayList<GraphSeries> series;
    private int payment;

    public GraphDTO(ArrayList<String> categories, ArrayList<GraphSeries> series) {
        this.categories = categories;
        this.series = series;
    }

    public GraphDTO(ArrayList<String> categories, ArrayList<GraphSeries> series, int payment) {
        this.categories = categories;
        this.series = series;
        this.payment = payment;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public ArrayList<GraphSeries> getSeries() {
        return series;
    }

    public int getPayment() { return payment;}
}
