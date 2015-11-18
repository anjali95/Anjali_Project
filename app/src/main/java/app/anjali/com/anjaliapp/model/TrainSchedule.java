package app.anjali.com.anjaliapp.model;

/**
 * Created by GB on 11/14/2015.
 */
public class TrainSchedule {

    String trainno;
    String trainname;
    String stationcode;
    String stationname;
    String arrivaltime;
    String departuretime;
    String distance;

    public String getTrainno() {
        return trainno;
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
        return stationname;
    }

    public void setStationname(String stationname) {
        this.stationname = stationname;
    }

    public String getArrivaltime() {
        return arrivaltime;
    }

    public void setArrivaltime(String arrivaltime) {
        this.arrivaltime = arrivaltime;
    }

    public String getDeparturetime() {
        return departuretime;
    }

    public void setDeparturetime(String departuretime) {
        this.departuretime = departuretime;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getSourcestationcode() {
        return sourcestationcode;
    }

    public void setSourcestationcode(String sourcestationcode) {
        this.sourcestationcode = sourcestationcode;
    }

    public String getSourcestationname() {
        return sourcestationname;
    }

    public void setSourcestationname(String sourcestationname) {
        this.sourcestationname = sourcestationname;
    }

    public String getDestinationstationcode() {
        return destinationstationcode;
    }

    public void setDestinationstationcode(String destinationstationcode) {
        this.destinationstationcode = destinationstationcode;
    }

    public String getDestinationStationname() {
        return destinationStationname;
    }

    public void setDestinationStationname(String destinationStationname) {
        this.destinationStationname = destinationStationname;
    }

    public TrainSchedule(String trainno, String trainname, String stationcode, String stationname, String arrivaltime, String departuretime, String distance, String sourcestationcode, String sourcestationname, String destinationstationcode, String destinationStationname) {
        this.trainno = trainno;
        this.trainname = trainname;
        this.stationcode = stationcode;
        this.stationname = stationname;
        this.arrivaltime = arrivaltime;
        this.departuretime = departuretime;
        this.distance = distance;
        this.sourcestationcode = sourcestationcode;
        this.sourcestationname = sourcestationname;
        this.destinationstationcode = destinationstationcode;
        this.destinationStationname = destinationStationname;
    }

    String sourcestationcode;
    String sourcestationname;
    String destinationstationcode;
    String destinationStationname;


}
