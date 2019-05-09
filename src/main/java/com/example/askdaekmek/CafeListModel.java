package com.example.askdaekmek;

public class CafeListModel {
    String CafeName;
    String CityName;
    String Meal;
    int LocationSign;
    String Location;

    public CafeListModel(String cafeName, String cityName, String meal, int locationSign, String location) {
        CafeName = cafeName;
        CityName = cityName;
        Meal = meal;
        LocationSign = locationSign;
        Location = location;
    }

    public String getCafeName() {
        return CafeName;
    }

    public void setCafeName(String cafeName) {
        CafeName = cafeName;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    public String getMeal() {
        return Meal;
    }

    public void setMeal(String meal) {
        Meal = meal;
    }

    public int getLocationSign() {
        return LocationSign;
    }

    public void setLocationSign(int locationSign) {
        LocationSign = locationSign;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }
}
