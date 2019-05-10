package Model;

public class GraphDTO {

    private String x_date;
    private int y_energy;

    public GraphDTO(String x_date, int y_energy) {
        this.x_date = x_date;
        this.y_energy = y_energy;
    }

    public String getX_date() {
        return x_date;
    }

    public void setX_date(String x_date) {
        this.x_date = x_date;
    }

    public int getY_energy() {
        return y_energy;
    }

    public void setY_energy(int y_energy) {
        this.y_energy = y_energy;
    }
}
