package Model.user;

public class ComparisonDTO {

    private int usage;
    private int sales_rate;

    public ComparisonDTO(int usage, int sales_rate) {
        this.usage = usage;
        this.sales_rate = sales_rate;
    }

    public int getUsage() {
        return usage;
    }

    public int getSales_rate() {
        return sales_rate;
    }
}
