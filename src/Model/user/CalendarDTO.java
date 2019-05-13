package Model.user;

import java.util.ArrayList;

public class CalendarDTO {

    private ArrayList<String> dates;
    private ArrayList<EnergySeries> energy_data;

    public CalendarDTO(ArrayList<String> dates, ArrayList<EnergySeries> energy_data) {
        this.dates = dates;
        this.energy_data = energy_data;
    }

    public ArrayList<String> getDates() {
        return dates;
    }

    public ArrayList<EnergySeries> getEnergy_data() {
        return energy_data;
    }
}
