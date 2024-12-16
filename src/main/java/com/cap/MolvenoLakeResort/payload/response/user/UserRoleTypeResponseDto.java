package com.cap.MolvenoLakeResort.payload.response.user;

public class UserRoleTypeResponseDto {
    private Long  userRoleTypeID;
    private String userRoleTypeName;



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

    public void setId(Long userRoleTypeId) {
    }

    public void setRoleName(String userRoleTypeName) {

    }
}
