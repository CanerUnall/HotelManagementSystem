package com.cap.MolvenoLakeResort.payload.request.room;

public class BedTypeRequestDto {
    private Long bedTypeId;
    private String bedTypeName;
    private String bedTypeNameZh;

    public String getBedTypeNameZh() {
        return bedTypeNameZh;
    }

    public void setBedTypeNameZh(String bedTypeNameZh) {
        this.bedTypeNameZh = bedTypeNameZh;
    }

    public String getBedTypeName() {
        return bedTypeName;
    }

    public void setBedTypeName(String bedTypeName) {
        this.bedTypeName = bedTypeName;
    }

    public Long getBedTypeId() {
        return bedTypeId;
    }

    public void setBedTypeId(Long bedTypeId) {
        this.bedTypeId = bedTypeId;
    }
}
