package app.anjali.com.anjaliapp.model;

/**
 * Created by GB on 11/14/2015.
 */
public class Train {
    public String getTrainno() {
        return trainno;
    }

    public Train(String trainno, String trainname, String stationcode, String stationname) {
        this.trainno = trainno;
        this.trainname = trainname;
        this.stationcode = stationcode;
        Stationname = stationname;
    }

    public void setTrainno(String trainno) {
        this.trainno = trainno;
    }

    public String getTrainname() {
        return trainname;
    }

    public void setTrainname(String trainname) {
        this.trainname = trainname;
    }

    public String getStationcode() {
        return stationcode;
    }

    public void setStationcode(String stationcode) {
        this.stationcode = stationcode;
    }

    public String getStationname() {
        return Stationname;
    }

    public void setStationname(String stationname) {
        Stationname = stationname;
    }

    String trainno;
    String trainname;
    String stationcode;
    String Stationname;


}
