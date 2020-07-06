package com.redhat.emergency.response.responder.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Responder {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("latitude")
    private Double latitude;

    @JsonProperty("longitude")
    private Double longitude;

    @JsonProperty("boatCapacity")
    private Integer boatCapacity;

    @JsonProperty("medicalKit")
    private Boolean medicalKit;

    @JsonProperty("available")
    private Boolean available;

    public Responder id(String id) {
        this.id = id;
        return this;
    }

    public String getId() {
        return id;
    }

    public Responder name(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public Responder phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Responder latitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Responder longitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Responder boatCapacity(Integer boatCapacity) {
        this.boatCapacity = boatCapacity;
        return this;
    }

    public Integer getBoatCapacity() {
        return boatCapacity;
    }

    public Responder medicalKit(Boolean medicalKit) {
        this.medicalKit = medicalKit;
        return this;
    }

    public Boolean getMedicalKit() {
        return medicalKit;
    }

    public Responder available(Boolean available) {
        this.available = available;
        return this;
    }

    public Boolean getAvailable() {
        return available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Responder responder = (Responder) o;
        return Objects.equals(this.id, responder.id) &&
                Objects.equals(this.name, responder.name) &&
                Objects.equals(this.phoneNumber, responder.phoneNumber) &&
                Objects.equals(this.latitude, responder.latitude) &&
                Objects.equals(this.longitude, responder.longitude) &&
                Objects.equals(this.boatCapacity, responder.boatCapacity) &&
                Objects.equals(this.medicalKit, responder.medicalKit) &&
                Objects.equals(this.available, responder.available);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phoneNumber, latitude, longitude, boatCapacity, medicalKit, available);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Responder {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    phoneNumber: ").append(toIndentedString(phoneNumber)).append("\n");
        sb.append("    latitude: ").append(toIndentedString(latitude)).append("\n");
        sb.append("    longitude: ").append(toIndentedString(longitude)).append("\n");
        sb.append("    boatCapacity: ").append(toIndentedString(boatCapacity)).append("\n");
        sb.append("    medicalKit: ").append(toIndentedString(medicalKit)).append("\n");
        sb.append("    available: ").append(toIndentedString(available)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

