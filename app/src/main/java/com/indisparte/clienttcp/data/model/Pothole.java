package com.indisparte.clienttcp.data.model;

import androidx.annotation.NonNull;

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
public class Pothole {
    private final String username;
    private final Double latitude, longitude,variation;

    public Pothole(@NonNull String username,
                   @NonNull Double latitude,
                   @NonNull Double longitude,
                   @NonNull Double variation
    ) {
        this.username = username;
        this.latitude = latitude;
        this.longitude = longitude;
        this.variation = variation;
    }

    public String getUsername() {
        return username;
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

    @NonNull
    @Override
    public String toString() {
        return "Pothole{" +
                "username='" + username + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", variation=" + variation +
                '}';
    }
}
