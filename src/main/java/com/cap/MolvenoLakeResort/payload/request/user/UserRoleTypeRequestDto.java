package com.cap.MolvenoLakeResort.payload.request.user;

public class UserRoleTypeRequestDto {
    private Long userRoleTypeID;
    private String userRoleTypeName;

    // Getter ve Setter metodları
    public Long getUserRoleTypeID() {
        return userRoleTypeID;
    }

    public void setUserRoleTypeID(Long userRoleTypeID) {
        this.userRoleTypeID = userRoleTypeID;
    }

    public String getUserRoleTypeName() {
        return userRoleTypeName;
    }

    public void setUserRoleTypeName(String userRoleTypeName) {
        this.userRoleTypeName = userRoleTypeName;
    }

    // Eksik olan getRoleName metodu
    public String getRoleName() {
        return userRoleTypeName;
    }
}
