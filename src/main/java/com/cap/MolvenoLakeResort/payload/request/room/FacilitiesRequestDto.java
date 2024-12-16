package com.cap.MolvenoLakeResort.payload.request.room;

public class FacilitiesRequestDto {
    private Long facilitiesId;
    private String facilitiesName;

    private String facilitiesNameZh;

    public String getFacilitiesNameZh() {
        return facilitiesNameZh;
    }

    public void setFacilitiesNameZh(String facilitiesNameZh) {
        this.facilitiesNameZh = facilitiesNameZh;
    }

    public String getFacilitiesName() {
        return facilitiesName;
    }

    public void setFacilitiesName(String facilitiesName) {
        this.facilitiesName = facilitiesName;
    }

    public Long getFacilitiesId() {
        return facilitiesId;
    }

    public void setFacilitiesId(Long facilitiesId) {
        this.facilitiesId = facilitiesId;
    }
}
