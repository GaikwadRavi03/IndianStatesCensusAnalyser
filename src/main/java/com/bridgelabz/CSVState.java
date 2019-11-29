package com.bridgelabz;

import com.opencsv.bean.CsvBindByName;

public class CSVState {
    @CsvBindByName(column = "State")
    private String state;

    @CsvBindByName(column = "Population")
    private String population;

    @CsvBindByName(column = "AreaInSqKm")
    private String areaInSqKm;

    @CsvBindByName(column = "DensityPerSqKm")
    private String densityPerSqKm;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getAreaInSqKm() {
        return areaInSqKm;
    }

    public void setAreaInSqKm(String areaInSqKm) {
        this.areaInSqKm = areaInSqKm;
    }

    public String getDensityPerSqKm() {
        return densityPerSqKm;
    }

    public void setDensityPerSqKm(String densityPerSqKm) {
        this.densityPerSqKm = densityPerSqKm;
    }
}
