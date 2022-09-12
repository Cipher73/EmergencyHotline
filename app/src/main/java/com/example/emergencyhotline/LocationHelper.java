package com.example.emergencyhotline;

public class LocationHelper {
    String Longitude;

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    String service;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    String from;
    public LocationHelper(){

    }
    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    String Latitude;

    public LocationHelper(String service, String from, String longitude, String latitude, String country, String address) {
        this.Longitude = longitude;
        this.Latitude = latitude;
        this.Country = country;
        this.Address = address;
        this.from = from;
        this.service=service;
    }

    String Country;
    String Address;
}
