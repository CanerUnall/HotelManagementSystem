package com.cap.MolvenoLakeResort.service.business.reservation;

import com.cap.MolvenoLakeResort.model.business.reservation.Reservation;
import com.cap.MolvenoLakeResort.model.user.User;
import com.cap.MolvenoLakeResort.payload.mappers.ReservationMapper;
import com.cap.MolvenoLakeResort.payload.request.reservation.ReservationRequest;
import com.cap.MolvenoLakeResort.payload.request.reservation.ReservationRequestDto;
import com.cap.MolvenoLakeResort.payload.request.room.DirtyRoomRequestDto;
import com.cap.MolvenoLakeResort.payload.request.user.UserRequestDto;
import com.cap.MolvenoLakeResort.payload.response.reservation.ReservationResponse;
import com.cap.MolvenoLakeResort.payload.response.user.UserResponseDto;
import com.cap.MolvenoLakeResort.repository.business.reservation.ReservationRepository;
import com.cap.MolvenoLakeResort.repository.business.room.RoomRepository;
import com.cap.MolvenoLakeResort.repository.user.UserRepository;
import com.cap.MolvenoLakeResort.service.business.room.RoomService;
import com.cap.MolvenoLakeResort.service.email.EmailService;
import com.cap.MolvenoLakeResort.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserService userService;
    private final ReservationMapper reservationMapper;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final RoomService roomService;
    private final EmailService emailService;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, UserService userService, ReservationMapper reservationMapper, UserRepository userRepository, RoomRepository roomRepository, RoomService roomService, EmailService emailService) {
        this.reservationRepository = reservationRepository;
        this.userService = userService;
        this.reservationMapper = reservationMapper;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
        this.roomService = roomService;
        this.emailService = emailService;
    }

    @Override
    public List<ReservationResponse> getAllReservations() {
        List<Reservation> reservationList = reservationRepository.findAll();
        return reservationMapper.mapReservationListToReservationResponseDtoList(reservationList);
    }
    @Override
    public String deleteReservationById(Long id) {
        if (reservationRepository.findById(id).isPresent()) {
            reservationRepository.deleteById(id);
            return "Reservation deleted successfully !!!";
        } else {
            return "Reservation Not Found !!!";
        }
    }

    @Override
    public String editReservation(ReservationRequestDto reservationRequestDto) {
        try {
            Optional<Reservation> reservationOpt = reservationRepository.findById(reservationRequestDto.getReservationId());
            if (reservationOpt.isPresent()) {
                Reservation reservation = reservationOpt.get();
                Reservation updatedReservation = reservationMapper.mapReservationRequestDtoToReservation(reservationRequestDto);
                updatedReservation.setReservationId(reservation.getReservationId());
                reservationRepository.save(updatedReservation);
                return "Reservation updated successfully !!!";
            } else {
                return "Reservation Not Found !!!";
            }
        } catch (Exception e) {
            return "Failed to update reservation !!!";
        }
    }
    private String generateReservationSummaryEmail(ReservationRequest request) {
        StringBuilder emailMessage = new StringBuilder();

        emailMessage.append("Dear Guest,\n\n")
                .append("Thank you for your reservation. Here is your reservation summary:\n\n")
                .append("Reservation Summary\n")
                .append("Room Number: ").append(request.getRoomNumber()).append("\n")
                .append("Check-in Date: ").append(request.getCheck_in().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"))).append("\n")
                .append("Check-out Date: ").append(request.getCheck_out().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"))).append("\n")
                .append("Price per Night: $").append(request.getTotalPrice() / (request.getCheck_out().getDayOfYear() - request.getCheck_in().getDayOfYear())).append("\n")
                .append("Total Price: $").append(String.format("%.2f", request.getTotalPrice())).append("\n\n")
                .append("Guests:\n").
        append("Adults: ");
        for (UserRequestDto guest : request.getGuestList()) {
            if (guest.isAdult()){
                emailMessage.append(guest.getUserName()).append(" ").append(guest.getUserSurName()).append("\n");
            }

        }


        emailMessage.append("Children: ");
        for (UserRequestDto guest : request.getGuestList()) {
            if (!guest.isAdult()){
                emailMessage.append(guest.getUserName()).append(" ").append(guest.getUserSurName()).append("\n");
            }

        }

        if (request.getCancellationFee()>0){
            emailMessage.append("\nCancellation Fee: "+request.getCancellationFee());
        }
        emailMessage.append("\nWe look forward to welcoming you to our hotel.\n")
                .append("If you have any questions, please do not hesitate to contact us.\n\n")
                .append("Sincerely,\n")
                .append("The Hotel Management").append("------------------------------------------------\n");

        emailMessage.append("尊敬的客人，\n\n")
                .append("感谢您的预订。以下是您的预订摘要：\n\n")
                .append("预订摘要\n")
                .append("房间号: ").append(request.getRoomNumber()).append("\n")
                .append("入住日期: ").append(request.getCheck_in().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"))).append("\n")
                .append("退房日期: ").append(request.getCheck_out().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"))).append("\n")
                .append("每晚价格: $").append(request.getTotalPrice() / (request.getCheck_out().getDayOfYear() - request.getCheck_in().getDayOfYear())).append("\n")
                .append("总价: $").append(String.format("%.2f", request.getTotalPrice())).append("\n\n")
                .append("客人名单:\n")
                .append("成人: ");
        for (UserRequestDto guest : request.getGuestList()) {
            if (guest.isAdult()) {
                emailMessage.append(guest.getUserName()).append(" ").append(guest.getUserSurName()).append("\n");
            }
        }

        emailMessage.append("儿童: ");
        for (UserRequestDto guest : request.getGuestList()) {
            if (!guest.isAdult()) {
                emailMessage.append(guest.getUserName()).append(" ").append(guest.getUserSurName()).append("\n");
            }
        }
        if (request.getCancellationFee()>0){
            emailMessage.append("\n取消费用： "+request.getCancellationFee());
        }
        emailMessage.append("\n我们期待在酒店欢迎您的到来。\n")
                .append("如果您有任何问题，请随时联系我们。\n\n")
                .append("此致，\n")
                .append("酒店管理团队");

        return emailMessage.toString();
    }
    @Override
    public String create(ReservationRequest reservationRequest) {
        String email= "";
        for (UserRequestDto requestDto:reservationRequest.getGuestList()){
            if (requestDto.getEmail()!=null){
                email=requestDto.getEmail();
            }
        }
        try {
            System.out.println("burasi calisti");
            Reservation reservation = reservationMapper.mapReservationRequestToReservation(reservationRequest);
            reservationRepository.save(reservation);
            //TODO burayi acmalisin
            // try {
            //     emailService.sendEmail(email, "The reservation is complete.", generateReservationSummaryEmail(reservationRequest));
            // } catch (IOException e) {
            //     e.printStackTrace();
            //     return "Email sending error";
            // }

            return "Reservation created successfully !!!";
        } catch (Exception e) {
            System.out.println("hata burada");
            return "Failed to create reservation !!!";
        }
    }

    @Override
    public ReservationResponse getReservationById(Long id) {
        ReservationResponse reservationResponse = new ReservationResponse();
        Optional<Reservation> optionalReservation =  reservationRepository.findById(id);

        if (optionalReservation.isPresent()){

            reservationResponse =reservationMapper.mapReservationToReservationResponse(optionalReservation.get());

        }
        return reservationResponse;

    }

    @Override
    public List<ReservationResponse> getReservationListByEmail(String email) {
        return reservationMapper.mapReservationListToReservationResponseDtoList(reservationRepository.findByGuestEmail(email));

    }

    @Override
    public String cancelReservationById(Long reservationId, boolean canceled, int cancellationFeePercentage) {
        if (reservationRepository.findById(reservationId).isPresent()){
            System.out.println("burasi calisti");
            Reservation reservation =reservationRepository.findById(reservationId).get();
            reservation.setCanceled(canceled);

            double totalPrice = reservation.getTotalPrice();
            double cancellationFee = totalPrice / 100 * cancellationFeePercentage;

            DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
            symbols.setDecimalSeparator('.');
            DecimalFormat df = new DecimalFormat("#.00", symbols);

            reservation.setCancellationFee(Double.valueOf(df.format(cancellationFee))+reservation.getCancellationFee());
            reservation.setTotalPrice(0);
            reservationRepository.save(reservation);
        }

        return "";
    }

    @Override
    public boolean isRoomAvailable(String roomNumber, LocalDateTime oldCheckOutDate, LocalDateTime newCheckOutDate) {
        List<Reservation> reservations = reservationRepository.findReservationsBetweenDates(roomNumber, oldCheckOutDate, newCheckOutDate);
        System.out.println("reservations.isEmpty() = " + reservations.isEmpty());
        return reservations.isEmpty();
    }

    @Override
    public List<UserResponseDto> getAllGuestsByReservationId(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        List<User> guests = reservationRepository.findUsersByReservation(reservation);

        return guests.stream()
                .map(user -> new UserResponseDto(user.getUserName(), user.getUserSurName(), user.isAdult()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean checkInReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        if (reservation != null && !reservation.isCheckedIn()) {
            reservation.setCheckedIn(true);
            reservation.setCheckedInDateTime(LocalDateTime.now());
            reservationRepository.save(reservation);
            return true;
        }
        return false;
    }

    @Override
    public boolean checkOutReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        if (reservation != null && !reservation.isCheckedOut()) {
            reservation.setCheckedOut(true);
            reservation.setCheckedOutDateTime(LocalDateTime.now());

            DirtyRoomRequestDto dirtyRoomRequestDto = new DirtyRoomRequestDto();
            dirtyRoomRequestDto.setRoomId(reservation.getRoom().getRoomId());
            dirtyRoomRequestDto.setCleaned(false);
            roomService.editRoomStatus(dirtyRoomRequestDto);

            reservationRepository.save(reservation);
            return true;
        }
        return false;
    }
}
