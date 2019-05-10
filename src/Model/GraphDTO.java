package Model;

import java.util.ArrayList;

public class GraphDTO {

    private ArrayList<String> categories;
    private ArrayList<GraphSeries> series;

    public GraphDTO(ArrayList<String> categories, ArrayList<GraphSeries> series) {
        this.categories = categories;
        this.series = series;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public ArrayList<GraphSeries> getSeries() {
        return series;
    }
}
