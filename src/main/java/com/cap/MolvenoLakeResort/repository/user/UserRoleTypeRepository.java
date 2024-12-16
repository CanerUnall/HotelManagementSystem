package com.cap.MolvenoLakeResort.repository.user;

import com.cap.MolvenoLakeResort.model.user.UserRoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleTypeRepository extends JpaRepository<UserRoleType,Long> {

    UserRoleType findByUserRoleTypeName(String userRoleTypeName);
}
