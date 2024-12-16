package com.cap.MolvenoLakeResort.service.user;

import com.cap.MolvenoLakeResort.model.user.PasswordResetToken;
import com.cap.MolvenoLakeResort.model.user.User;
import com.cap.MolvenoLakeResort.payload.mappers.UserMapper;
import com.cap.MolvenoLakeResort.payload.request.user.UserRequestDto;
import com.cap.MolvenoLakeResort.payload.response.user.UserResponseDto;
import com.cap.MolvenoLakeResort.repository.user.PasswordResetTokenRepository;
import com.cap.MolvenoLakeResort.repository.user.UserRepository;
import com.cap.MolvenoLakeResort.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final EmailService emailService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserRoleTypeService userRoleTypeService;
    private final PasswordEncoder passwordEncoder;
    private final PasswordResetTokenRepository tokenRepository;

    @Autowired
    public UserServiceImpl(EmailService emailService, UserRepository userRepository, UserMapper userMapper, UserRoleTypeService userRoleTypeService, PasswordEncoder passwordEncoder, PasswordResetTokenRepository tokenRepository) {
        this.emailService = emailService;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userRoleTypeService = userRoleTypeService;
        this.passwordEncoder = passwordEncoder;
        this.tokenRepository = tokenRepository;
    }
    @Override
    public boolean checkEmailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return userMapper.mapUserListToUserResponseDtoList(userList);
    }
    @Override
    public String deleteUserByEmail(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return "User deleted successfully!";
        } else {
            return "User Not Found";
        }
    }

    @Override
    public String save(UserRequestDto userRequestDto) {
        Optional<User> existingUser = userRepository.findByEmail(userRequestDto.getEmail());
        if (existingUser.isPresent()) {
            return "This email is already in the system, please register with another email.";
        } else {
            userRepository.save(userMapper.mapUserRequestDtoToUser(userRequestDto));


            return "User registered successfully! 用户注册成功！";
        }
    }

    @Override
    public String updateUser(String  email, UserRequestDto userDetails) {
        Optional<User> existingUser = userRepository.findById(userDetails.getUserId());
        if (existingUser.isPresent()) {

            User user = existingUser.get();
            user.setUserId(userDetails.getUserId());
            user.setUserName(userDetails.getUserName());
            user.setUserSurName(userDetails.getUserSurName());
            user.setEmail(userDetails.getEmail());
            user.setAddress(userDetails.getAddress());
            user.setUserRoleType(userRoleTypeService.getUserRoleTypeByRoleName(userDetails.getUserRoleType()));
            user.setPassword(userDetails.getPassword());
            user.setPhoneNumber(userDetails.getPhoneNumber());

            userRepository.save(user);
            return "User updated successfully!";
        } else {
            return "User Not Found";
        }
    }
//    @Override
//    public UserResponseDto login(UserRequestDto loginRequestDto) {
//        Optional<User> userOptional = userRepository.findByEmail(loginRequestDto.getEmail());
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            if (passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
//                // Kullanıcı doğrulandı
//                UserResponseDto responseDto = new UserResponseDto();
//                responseDto.setUserName(user.getUserName());
//                responseDto.setEmail(user.getEmail());
//                return responseDto;
//            }
//        }
//
//        throw new RuntimeException("Kullanıcı bilgisi veya sifre hatali. Lütfen tekrar deneyin.");
//
//    }
    @Override
    public User findByLogin(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
//    @Override
//    public UserResponseDto getUserByEmail(String email) {
//        Optional<User> userOptional = userRepository.findByEmail(email);
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            return userMapper.mapUserToUserResponseDto(user);
//        } else {
//            return null;
//        }
//    }



    @Override
    public void register(User user)  {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // TODO

        User userSaved= userRepository.save(user);

        String message = "English Version:\n\n" +
                "Hello " + userSaved.getUserName() + ",\n\n" +
                "Welcome to Molvone Lake Resort! Your registration has been successfully completed.\n\n" +
                "We are excited for you to discover the amenities and services that Molvone Lake Resort offers. Here are some helpful details for you:\n\n" +
                "• Comfortable Rooms: Enjoy a stay in our modern and cozy rooms.\n" +
                "• Delicious Meals: Discover various flavors in our restaurants.\n" +
                "• Unique Services: Take advantage of our spa, fitness center, and other facilities.\n\n" +
                "For more information about these services and to manage your reservations, please visit your User Panel.\n\n" +
                "If you have any questions, please do not hesitate to contact us at info@molvenoresort.com.\n\n" +
                "Once again, welcome and we are delighted to have you with us!\n\n" +
                "Best regards,\n" +
                "Molvone Lake Resort Team\n\n" +
                "---------------------------------------------\n\n" +
                "中文版本:\n\n" +
                "您好 " + userSaved.getUserName() + ",\n\n" +
                "欢迎来到Molvone湖度假村！您的注册已成功完成。\n\n" +
                "我们期待您发现Molvone湖度假村提供的设施和服务。以下是一些对您有用的信息：\n\n" +
                "• 舒适的房间：在我们现代而舒适的房间里享受住宿。\n" +
                "• 美味的餐点：在我们的餐厅发现各种风味。\n" +
                "• 独特的服务：利用我们的水疗中心、健身中心和其他设施。\n\n" +
                "有关这些服务的更多信息并管理您的预订，请访问您的用户面板。\n\n" +
                "如果您有任何问题，请随时通过 info@molvenoresort.com 联系我们。\n\n" +
                "再次欢迎，期待您的光临！\n\n" +
                "此致,\n" +
                "Molvone湖度假村团队";

//TODO burayi acmalisin
        // try {
        //     emailService.sendEmail(user.getEmail(), "Registration successfully", message);
        // } catch (IOException e) {
        //     e.printStackTrace();

        // }


//        emailService.sendEmail(userSaved.getEmail(),"Welcome to Molvone Lake Resort!/ 欢迎来到Molvone湖度假村！", message);
    }

    @Override
    public void createPasswordResetTokenForUser(String email, String token) {
        User user;
        if (userRepository.findByEmail(email).isPresent()){
            user = userRepository.findByEmail(email).get();
        }else {
            user= null;
        }
        PasswordResetToken myToken = new PasswordResetToken(token, user);
        tokenRepository.save(myToken);
    }

    @Override
    public PasswordResetToken getPasswordResetToken(String token) {
        return tokenRepository.findByToken(token);
    }

    @Override
    public void changeUserPassword(String token, String password) {
        PasswordResetToken resetToken = tokenRepository.findByToken(token);
        User user = resetToken.getUser();
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        userRepository.save(user);
    }


    @Override
    public UserResponseDto getUserByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            UserResponseDto userResponseDto = new UserResponseDto();
            userResponseDto.setEmail(user.getEmail());
            userResponseDto.setUserName(user.getUserName());
            userResponseDto.setUserSurName(user.getUserSurName());
            userResponseDto.setPhoneNumber(user.getPhoneNumber());
            userResponseDto.setAddress(user.getAddress());
            userResponseDto.setUserRoleType(user.getUserRoleType().getUserRoleTypeName());


            return userResponseDto;
        } else {
//            // Kullanıcı bulunamadığında yapılacak işlemler
//            throw new UserNotFoundException("User not found with email: " + email);
        }
        return null;
    }

    @Override
    public void updateProfile(String email, UserRequestDto userRequestDto) {
        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isPresent()) {

            User user = existingUser.get();
            System.out.println("user.getUserId() = " + user.getUserId());
            System.out.println(userRequestDto.getUserId());
            user.setUserName(userRequestDto.getUserName());
            System.out.println(userRequestDto.getUserName());
            user.setUserSurName(userRequestDto.getUserSurName());
            user.setEmail(userRequestDto.getEmail());
            user.setAddress(userRequestDto.getAddress());
            user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
            user.setPhoneNumber(userRequestDto.getPhoneNumber());

            userRepository.save(user);

        }
    }

    @Override
    public void changeUserPasswordByEmail(String email, String newPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        System.out.println("user.getPassword() = " + user.getPassword());

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

    }


}
