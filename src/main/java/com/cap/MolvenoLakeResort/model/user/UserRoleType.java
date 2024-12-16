package com.cap.MolvenoLakeResort.model.user;

import jakarta.persistence.*;

@Entity
public class UserRoleType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userRoleTypeId;

    private String userRoleTypeName;

//    @OneToMany(mappedBy = "userRoleType", cascade = CascadeType.ALL)
//    private List<User> userList = new ArrayList<>();

    public Long getUserRoleTypeId() {
        return userRoleTypeId;
    }

    public void setUserRoleTypeId(Long userRoleTypeId) {
        this.userRoleTypeId = userRoleTypeId;
    }

    public String getUserRoleTypeName() {
        return userRoleTypeName;
    }

    public void setUserRoleTypeName(String userRoleTypeName) {
        this.userRoleTypeName = userRoleTypeName;
    }

//    public List<User> getUserList() {
//        return userList;
//    }
//
//    public void setUserList(List<User> userList) {
//        this.userList = userList;
//    }

    public UserRoleType(Long userRoleTypeId, String userRoleTypeName) {
        this.userRoleTypeId = userRoleTypeId;
        this.userRoleTypeName = userRoleTypeName;
//        this.userList = userList;
    }

    public UserRoleType() {
    }
}
