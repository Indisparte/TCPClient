package com.indisparte.clienttcp.data.model;

import androidx.annotation.NonNull;

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
public class Pothole {
    private final String user;
    private final Double latitude, longitude,variation;

    public Pothole(@NonNull String user,
                   @NonNull Double latitude,
                   @NonNull Double longitude,
                   @NonNull Double variation
    ) {
        this.user = user;
        this.latitude = latitude;
        this.longitude = longitude;
        this.variation = variation;
    }



    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getVariation() {
        return variation;
    }

    public String getUser() {
        return user;
    }

    @NonNull
    @Override
    public String toString() {
        return "Pothole{" +
                "user='" + user + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", variation=" + variation +
                '}';
    }
}
