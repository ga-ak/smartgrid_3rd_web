package Model;

public class LocationDTO {
    private  double loca_latitude;
    private  double loca_longitude;

    public LocationDTO(double loca_latitude, double loca_longitude) {
        this.loca_latitude = loca_latitude;
        this.loca_longitude = loca_longitude;
    }

    public double getLoca_latitude() {
        return loca_latitude;
    }

    public void setLoca_latitude(double loca_latitude) {
        this.loca_latitude = loca_latitude;
    }

    public double getLoca_longitude() {
        return loca_longitude;
    }

    public void setLoca_longitude(double loca_longitude) {
        this.loca_longitude = loca_longitude;
    }

    @Override
    public String toString() {
        return getLoca_latitude()+", "+getLoca_longitude();
    }
}
