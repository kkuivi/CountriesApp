package com.apps.elikem.midtronics.country;

import org.parceler.Parcel;

/**
 * Created by elikem on 12/6/17.
 */

@Parcel
public class Country {

    String name;
    String capital;
    int population;
    double[] latlng;
    double area;
    String subregion;

    public Country() {}

    public Country(String name, String capital, int population, double[] latlng, double area, String subregion) {
        this.name = name;
        this.capital = capital;
        this.population = population;
        this.latlng = latlng;
        this.area = area;
        this.subregion = subregion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public double[] getLatlng() {
        return latlng;
    }

    public void setLatlng(double[] latlng) {
        this.latlng = latlng;
    }
}
