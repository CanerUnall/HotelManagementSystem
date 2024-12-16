package com.cap.MolvenoLakeResort.repository.user;

import com.cap.MolvenoLakeResort.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUserNameAndUserSurNameAndPasswordIsNullAndEmailIsNullAndAddressIsNullAndPhoneNumberIsNullAndUserRoleTypeIsNull(String userName, String userSurName);


}
