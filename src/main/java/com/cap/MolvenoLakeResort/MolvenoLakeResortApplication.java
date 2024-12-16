package com.cap.MolvenoLakeResort;

import com.cap.MolvenoLakeResort.model.pages.HotelInformation;
import com.cap.MolvenoLakeResort.model.business.room.BedType;
import com.cap.MolvenoLakeResort.model.business.room.Facilities;
import com.cap.MolvenoLakeResort.model.business.room.Room;
import com.cap.MolvenoLakeResort.model.business.room.RoomType;
import com.cap.MolvenoLakeResort.model.media.Image;
import com.cap.MolvenoLakeResort.model.pages.*;
import com.cap.MolvenoLakeResort.model.user.Address;
import com.cap.MolvenoLakeResort.model.user.User;
import com.cap.MolvenoLakeResort.model.user.UserRoleType;
import com.cap.MolvenoLakeResort.payload.request.media.ImageRequestDto;
import com.cap.MolvenoLakeResort.payload.request.reservation.ReservationRequest;
import com.cap.MolvenoLakeResort.payload.request.user.UserRequestDto;
import com.cap.MolvenoLakeResort.repository.pages.HotelInformationRepository;
import com.cap.MolvenoLakeResort.repository.business.reservation.ReservationRepository;
import com.cap.MolvenoLakeResort.repository.business.room.BedTypeRepository;
import com.cap.MolvenoLakeResort.repository.business.room.FacilitiesRepository;
import com.cap.MolvenoLakeResort.repository.business.room.RoomRepository;
import com.cap.MolvenoLakeResort.repository.business.room.RoomTypeRepository;
import com.cap.MolvenoLakeResort.repository.media.ImageRepository;
import com.cap.MolvenoLakeResort.repository.pages.*;
import com.cap.MolvenoLakeResort.repository.user.UserRepository;
import com.cap.MolvenoLakeResort.repository.user.UserRoleTypeRepository;

import com.cap.MolvenoLakeResort.service.business.reservation.ReservationService;
import com.cap.MolvenoLakeResort.service.business.reservation.ReservationServiceImpl;
import com.cap.MolvenoLakeResort.service.email.EmailService;
import com.cap.MolvenoLakeResort.service.media.ImageService;
import com.cap.MolvenoLakeResort.service.user.UserService;
import com.cap.MolvenoLakeResort.service.user.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDateTime;
import java.util.*;

@SpringBootApplication
public class MolvenoLakeResortApplication implements CommandLineRunner {
    private final UserRoleTypeRepository userRoleTypeRepository;
    private final UserRepository userRepository;
    private final BedTypeRepository bedTypeRepository;
    private final FacilitiesRepository facilitiesRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final PageRepository pageRepository;
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;
    private final UserService userService;
    private final ReservationService reservationService;
    private final HotelInformationRepository hotelInformationRepository;
    private final EmailService emailService;
    private final ImageRepository imageRepository;
    private final ImageService imageService;
    private final CarouselRepository carouselRepository;
    private final AboutUsRepository aboutUsRepository;
    private final HotelAmenitiesRepository hotelAmenitiesRepository;
    private final CommentsRepository commentsRepository;

    @Autowired
    public MolvenoLakeResortApplication(UserRoleTypeRepository userRoleTypeRepository, UserRepository userRepository,
                                        BedTypeRepository bedTypeRepository, FacilitiesRepository facilitiesRepository,
                                        RoomTypeRepository roomTypeRepository, PageRepository pageRepository,
                                        RoomRepository roomRepository, ReservationRepository reservationRepository,
                                        UserServiceImpl userService, ReservationServiceImpl reservationService,
                                        HotelInformationRepository hotelInformationRepository, EmailService emailService,
                                        ImageRepository imageRepository, ImageService imageService, CarouselRepository carouselRepository,
                                        AboutUsRepository aboutUsRepository, HotelAmenitiesRepository hotelAmenitiesRepository,
                                        CommentsRepository commentsRepository) {
        this.userRoleTypeRepository = userRoleTypeRepository;
        this.userRepository = userRepository;
        this.bedTypeRepository = bedTypeRepository;
        this.facilitiesRepository = facilitiesRepository;
        this.roomTypeRepository = roomTypeRepository;
        this.pageRepository = pageRepository;
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
        this.userService = userService;
        this.reservationService = reservationService;
        this.hotelInformationRepository = hotelInformationRepository;
        this.emailService = emailService;

        this.imageRepository = imageRepository;
        this.imageService = imageService;
        this.carouselRepository = carouselRepository;
        this.aboutUsRepository = aboutUsRepository;
        this.hotelAmenitiesRepository = hotelAmenitiesRepository;
        this.commentsRepository = commentsRepository;
    }


    public static void main(String[] args) {

        ApplicationContext applicationContext = SpringApplication.run(MolvenoLakeResortApplication.class, args);

//        // Show what is autowired into ReservationController
//        for (String beanDefinitionName : applicationContext.getBeanDefinitionNames()) {
//            System.out.println(beanDefinitionName);
//            if (beanDefinitionName.startsWith("reservation")) {
//                Object object = applicationContext.getBean(beanDefinitionName);
//                System.out.println("Stop here");
//            }
//        }

    }
    //    @Autowired
//    private EmailService emailService;
//
//    @PostMapping("/reserve")
//    public String reserveRoom(@RequestBody ReservationRequest request) {
//        // Rezervasyon işlemleri burada yapılır
//        // ...
//
//        // Email gönderimi
//        Map<String, Object> model = new HashMap<>();
//        model.put("name", request.getName());
//        model.put("checkin_date", request.getCheckinDate());
//        model.put("checkout_date", request.getCheckoutDate());
//
//        try {
//            emailService.sendHtmlMessage(request.getEmail(), "Reservation Confirmation", model);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//            return "Error sending email";
//        }
//
//        return "Reservation successful";
//    }

    @Override
    public void run(String... args) throws Exception {
        Map<String, Object> model = new HashMap<>();
        model.put("name", "request.getName()");
        model.put("checkin_date", "request.getCheckinDate()");
        model.put("checkout_date", "request.getCheckoutDate()");


        //hotel bilgilerini olusturuyorum
        HotelInformation hotelInformation = new HotelInformation();
        hotelInformation.setHotelId(1L);
        hotelInformation.setHotelName("Molveno Lake Resort");
        hotelInformation.setFacebook("facebook Link");
        hotelInformation.setInstagram("Instagram link");
        hotelInformation.setPhoneNumber("06123123123");
        hotelInformation.setEmail("info@molvonelake.com");
        hotelInformation.setTwitter("twitter");
        hotelInformation.setRestaurant("restaurant link");

        Address address = new Address();
        address.setCity("City");
        address.setCountry("Country");
        address.setStreet("Street");
        address.setHouseNumber("HouseNumber");
        address.setZipCode("Zipcode");

        hotelInformation.setAddress(address);
        hotelInformationRepository.save(hotelInformation);


        //ROLE TYPELERI OLUSTURDUM.
        UserRoleType admin = new UserRoleType();
        admin.setUserRoleTypeName("GENERAL MANAGER");
        UserRoleType savedGeneralManager= userRoleTypeRepository.save(admin);

        UserRoleType receptionist = new UserRoleType();
        receptionist.setUserRoleTypeName("RECEPTIONIST");
        userRoleTypeRepository.save(receptionist);

        UserRoleType cleaner = new UserRoleType();
        cleaner.setUserRoleTypeName("CLEANER");
        userRoleTypeRepository.save(cleaner);

        UserRoleType guest = new UserRoleType();
        guest.setUserRoleTypeName("GUEST");
        userRoleTypeRepository.save(guest);

        //ILK ADMINI OLUSTURDUM
        User firstAdmin = new User();
        firstAdmin.setUserId(1L);
        firstAdmin.setEmail("info@molvenolake.com");
        firstAdmin.setPassword("Admin");
        firstAdmin.setUserName("John");
        firstAdmin.setUserSurName("De Witt");
        firstAdmin.setPhoneNumber("0623123123");
        firstAdmin.setAddress(address);




        firstAdmin.setUserRoleType(userRoleTypeRepository.findByUserRoleTypeName("GENERAL MANAGER"));
        userService.register(firstAdmin);

        User firstReceptionist = new User();
        firstReceptionist.setEmail("receptionist@receptionist.com");
        firstReceptionist.setPassword("receptionist");
        firstReceptionist.setUserRoleType(userRoleTypeRepository.findByUserRoleTypeName("RECEPTIONIST"));
        firstReceptionist.setUserName("Ahmet");
        firstReceptionist.setUserSurName("Kara");
        firstReceptionist.setPhoneNumber("06123123123");
        firstReceptionist.setAddress(address);
        userService.register(firstReceptionist);

        User firstCleaner = new User();
        firstCleaner.setUserName("Ayse");
        firstCleaner.setUserSurName("Atas");
        firstCleaner.setEmail("cleaner@cleaner.com");
        firstCleaner.setPassword("cleaner");
        firstCleaner.setUserRoleType(userRoleTypeRepository.findByUserRoleTypeName("CLEANER"));
        firstCleaner.setPhoneNumber("0612331122");
        firstCleaner.setAddress(address);
        userService.register(firstCleaner);

        User firstGuest = new User();
        firstGuest.setEmail("guest@guest.com");
        firstGuest.setUserName("Fatma");
        firstGuest.setUserSurName("Atas");
        firstGuest.setPassword("guest");
        firstGuest.setUserRoleType(userRoleTypeRepository.findByUserRoleTypeName("GUEST"));
        firstGuest.setPhoneNumber("0612331122");
        firstGuest.setAddress(address);
        userService.register(firstGuest);

        //BEDTYPE OLUSTURDUM
        BedType single = new BedType();
        single.setBedTypeName("SINGLE BED");
        single.setBedTypeNameZh("单人床");
        bedTypeRepository.save(single);

        BedType doubleBed = new BedType();
        doubleBed.setBedTypeName("DOUBLE BED");
        doubleBed.setBedTypeNameZh("双人床");
        bedTypeRepository.save(doubleBed);

        BedType babyBed = new BedType();
        babyBed.setBedTypeName("BABY BED");
        babyBed.setBedTypeNameZh("婴儿床");
        bedTypeRepository.save(babyBed);

        //FACILITIESLERI OLUSTURDUM
        Facilities miniFridge = new Facilities();
        miniFridge.setFacilitiesName("Mini Fridge");
        miniFridge.setFacilitiesNameZh("迷你冰箱");
        facilitiesRepository.save(miniFridge);

        Facilities tv = new Facilities();
        tv.setFacilitiesName("TV");
        tv.setFacilitiesNameZh("电视");
        facilitiesRepository.save(tv);

        Facilities airco = new Facilities();
        airco.setFacilitiesName("Airco");
        airco.setFacilitiesNameZh("空调");
        facilitiesRepository.save(airco);

        Facilities wifi = new Facilities();
        wifi.setFacilitiesName("WIFI");
        wifi.setFacilitiesNameZh("无线上网");
        facilitiesRepository.save(wifi);

        Facilities toilet = new Facilities();
        toilet.setFacilitiesName("Toilet");
        toilet.setFacilitiesNameZh("洗手间");
        facilitiesRepository.save(toilet);

        Facilities shower = new Facilities();
        shower.setFacilitiesName("Shower");
        shower.setFacilitiesNameZh("淋浴");
        facilitiesRepository.save(shower);

        Facilities phone = new Facilities();
        phone.setFacilitiesName("Phone");
        phone.setFacilitiesNameZh("电话");
        facilitiesRepository.save(phone);

        Facilities waterBoiler = new Facilities();
        waterBoiler.setFacilitiesName("Water Boiler");
        waterBoiler.setFacilitiesNameZh("开水器");
        facilitiesRepository.save(waterBoiler);

        Facilities coffeeMachine = new Facilities();
        coffeeMachine.setFacilitiesName("Coffee Machine");
        coffeeMachine.setFacilitiesNameZh("咖啡机");
        facilitiesRepository.save(coffeeMachine);

        Facilities bathtub = new Facilities();
        bathtub.setFacilitiesName("Bathtub");
        bathtub.setFacilitiesNameZh("浴缸");
        facilitiesRepository.save(bathtub);

        Facilities swimmingPool = new Facilities();
        swimmingPool.setFacilitiesName("Swimming Pool");
        swimmingPool.setFacilitiesNameZh("游泳池");
        facilitiesRepository.save(swimmingPool);

        Facilities kitchen = new Facilities();
        kitchen.setFacilitiesName("Kitchen");
        kitchen.setFacilitiesNameZh("厨房");
        facilitiesRepository.save(kitchen);

        Facilities sauna = new Facilities();
        sauna.setFacilitiesName("Sauna");
        sauna.setFacilitiesNameZh("桑拿");
        facilitiesRepository.save(sauna);

        Facilities infinitySwimmingPool = new Facilities();
        infinitySwimmingPool.setFacilitiesName("Infinity Swimming Pool");
        infinitySwimmingPool.setFacilitiesNameZh("无边泳池");
        facilitiesRepository.save(infinitySwimmingPool);

        Facilities terrace = new Facilities();
        terrace.setFacilitiesName("Terrace");
        terrace.setFacilitiesNameZh("阳台");
        facilitiesRepository.save(terrace);

        Facilities parasols = new Facilities();
        parasols.setFacilitiesName("Parasols");
        parasols.setFacilitiesNameZh("遮阳伞");
        facilitiesRepository.save(parasols);

        Facilities terraceChairsAndTables = new Facilities();
        terraceChairsAndTables.setFacilitiesNameZh("露台桌椅");
        terraceChairsAndTables.setFacilitiesName("Terrace Chairs and Tables");
        facilitiesRepository.save(terraceChairsAndTables);

        //PAGELERI OLUSTURDUM
        Page main = new Page();
        main.setPageName("MAIN");
        pageRepository.save(main);

        Page contactUs = new Page();
        contactUs.setPageName("CONTACT US");
        pageRepository.save(contactUs);


        //KATEGORILERI OLUSTURDUM
        RoomType standard = new RoomType();
        standard.setRoomTypeName("STANDARD");
        standard.setRoomTypeNameZh("标准");
        roomTypeRepository.save(standard);

        RoomType double_B = new RoomType();
        double_B.setRoomTypeName("DOUBLE-B");
        double_B.setRoomTypeNameZh("双B");
        roomTypeRepository.save(double_B);

        RoomType deluxe = new RoomType();
        deluxe.setRoomTypeName("DELUXE");
        deluxe.setRoomTypeNameZh("豪华房");
        roomTypeRepository.save(deluxe);

        RoomType pentHouse = new RoomType();
        pentHouse.setRoomTypeName("PENTHOUSE");
        pentHouse.setRoomTypeNameZh("顶层公寓");
        roomTypeRepository.save(pentHouse);


        //1 ADET ROOM OLUSTURDUM

        Room room1 = new Room();
        room1.setCountOfAdult(1);
        room1.setRoomNumber("B111");
        room1.setCountOfChild(0);
        room1.setPrice(220);
        room1.setThFloor("1");

        Room room2 = new Room();
        room2.setCountOfAdult(1);
        room2.setRoomNumber("B112");
        room2.setCountOfChild(0);
        room2.setPrice(220);
        room2.setThFloor("1");

        Room room3 = new Room();
        room3.setCountOfAdult(1);
        room3.setRoomNumber("B113");
        room3.setCountOfChild(0);
        room3.setPrice(220);
        room3.setThFloor("1");

        Room room4 = new Room();
        room4.setCountOfAdult(1);
        room4.setRoomNumber("B114");
        room4.setCountOfChild(1);
        room4.setPrice(220);
        room4.setThFloor("1");

        Room room5 = new Room();
        room5.setCountOfAdult(1);
        room5.setRoomNumber("B115");
        room5.setCountOfChild(1);
        room5.setPrice(220);
        room5.setThFloor("1");


        Room room6 = new Room();
        room6.setCountOfAdult(1);
        room6.setRoomNumber("B116");
        room6.setCountOfChild(1);
        room6.setPrice(220);
        room6.setThFloor("1");

        Room room7 = new Room();
        room7.setCountOfAdult(1);
        room7.setRoomNumber("B117");
        room7.setCountOfChild(1);
        room7.setPrice(220);
        room7.setThFloor("1");


        Room room8 = new Room();
        room8.setCountOfAdult(1);
        room8.setRoomNumber("B118");
        room8.setCountOfChild(0);
        room8.setPrice(240);
        room8.setThFloor("1");
        room8.setDisabled(true);


        Room room9 = new Room();
        room9.setCountOfAdult(1);
        room9.setRoomNumber("B119");
        room9.setCountOfChild(0);
        room9.setPrice(240);
        room9.setThFloor("1");
        room9.setDisabled(true);

        Room room10 = new Room();
        room10.setCountOfAdult(2);
        room10.setRoomNumber("B120");
        room10.setCountOfChild(0);
        room10.setPrice(380);
        room10.setThFloor("1");
        room10.setDisabled(true);

        Room room11 = new Room();
        room11.setCountOfAdult(2);
        room11.setRoomNumber("B121");
        room11.setCountOfChild(0);
        room11.setPrice(380);
        room11.setThFloor("1");

        Room room12 = new Room();
        room12.setCountOfAdult(2);
        room12.setRoomNumber("B122");
        room12.setCountOfChild(0);
        room12.setPrice(380);
        room12.setThFloor("1");

        Room room13 = new Room();
        room13.setCountOfAdult(2);
        room13.setRoomNumber("B123");
        room13.setCountOfChild(0);
        room13.setPrice(220);
        room13.setThFloor("1");

        Room room14 = new Room();
        room14.setCountOfAdult(2);
        room14.setRoomNumber("B124");
        room14.setCountOfChild(0);
        room14.setPrice(220);
        room14.setThFloor("1");

        Room room15 = new Room();
        room15.setCountOfAdult(2);
        room15.setRoomNumber("B125");
        room15.setCountOfChild(1);
        room15.setPrice(380);
        room15.setThFloor("1");

        Room room16 = new Room();
        room16.setCountOfAdult(2);
        room16.setRoomNumber("B126");
        room16.setCountOfChild(1);
        room16.setPrice(380);
        room16.setThFloor("1");

        Room room17 = new Room();
        room17.setCountOfAdult(2);
        room17.setRoomNumber("B127");
        room17.setCountOfChild(2);
        room17.setPrice(380);
        room17.setThFloor("1");

        Room room18 = new Room();
        room18.setCountOfAdult(2);
        room18.setRoomNumber("B128");
        room18.setCountOfChild(2);
        room18.setPrice(380);
        room18.setThFloor("1");

        Room room19 = new Room();
        room19.setCountOfAdult(4);
        room19.setRoomNumber("B129");
        room19.setCountOfChild(2);
        room19.setPrice(550);
        room19.setThFloor("1");

        //2.kat odalar
        Room room20 = new Room();
        room20.setCountOfAdult(1);
        room20.setRoomNumber("B211");
        room20.setCountOfChild(0);
        room20.setPrice(220);
        room20.setThFloor("2");

        Room room21 = new Room();
        room21.setCountOfAdult(1);
        room21.setRoomNumber("B212");
        room21.setCountOfChild(0);
        room21.setPrice(220);
        room21.setThFloor("2");

        Room room22 = new Room();
        room22.setCountOfAdult(1);
        room22.setRoomNumber("B213");
        room22.setCountOfChild(0);
        room22.setPrice(220);
        room22.setThFloor("2");

        Room room23 = new Room();
        room23.setCountOfAdult(1);
        room23.setRoomNumber("B214");
        room23.setCountOfChild(1);
        room23.setPrice(220);
        room23.setThFloor("2");

        Room room24 = new Room();
        room24.setCountOfAdult(1);
        room24.setRoomNumber("B215");
        room24.setCountOfChild(1);
        room24.setPrice(220);
        room24.setThFloor("2");


        Room room25 = new Room();
        room25.setCountOfAdult(1);
        room25.setRoomNumber("B216");
        room25.setCountOfChild(1);
        room25.setPrice(220);
        room25.setThFloor("2");

        Room room26 = new Room();
        room26.setCountOfAdult(1);
        room26.setRoomNumber("B217");
        room26.setCountOfChild(1);
        room26.setPrice(220);
        room26.setThFloor("2");


        Room room27 = new Room();
        room27.setCountOfAdult(1);
        room27.setRoomNumber("B218");
        room27.setCountOfChild(0);
        room27.setPrice(240);
        room27.setThFloor("2");
        room27.setDisabled(true);


        Room room28 = new Room();
        room28.setCountOfAdult(1);
        room28.setRoomNumber("B219");
        room28.setCountOfChild(0);
        room28.setPrice(240);
        room28.setThFloor("2");
        room28.setDisabled(true);

        Room room29 = new Room();
        room29.setCountOfAdult(2);
        room29.setRoomNumber("B220");
        room29.setCountOfChild(0);
        room29.setPrice(380);
        room29.setThFloor("2");
        room29.setDisabled(true);

        Room room30 = new Room();
        room30.setCountOfAdult(2);
        room30.setRoomNumber("B221");
        room30.setCountOfChild(0);
        room30.setPrice(380);
        room30.setThFloor("2");

        Room room31 = new Room();
        room31.setCountOfAdult(2);
        room31.setRoomNumber("B222");
        room31.setCountOfChild(0);
        room31.setPrice(380);
        room31.setThFloor("2");

        Room room32 = new Room();
        room32.setCountOfAdult(2);
        room32.setRoomNumber("B223");
        room32.setCountOfChild(0);
        room32.setPrice(220);
        room32.setThFloor("2");
        room32.setCleaned(false);

        Room room33 = new Room();
        room33.setCountOfAdult(2);
        room33.setRoomNumber("B224");
        room33.setCountOfChild(0);
        room33.setPrice(220);
        room33.setThFloor("2");

        Room room34 = new Room();
        room34.setCountOfAdult(2);
        room34.setRoomNumber("B225");
        room34.setCountOfChild(1);
        room34.setPrice(380);
        room34.setThFloor("2");

        Room room35 = new Room();
        room35.setCountOfAdult(2);
        room35.setRoomNumber("B226");
        room35.setCountOfChild(1);
        room35.setPrice(380);
        room35.setThFloor("2");
        room35.setCleaned(false);

        Room room36 = new Room();
        room36.setCountOfAdult(2);
        room36.setRoomNumber("B227");
        room36.setCountOfChild(2);
        room36.setPrice(380);
        room36.setThFloor("2");

        Room room37 = new Room();
        room37.setCountOfAdult(2);
        room37.setRoomNumber("B228");
        room37.setCountOfChild(2);
        room37.setPrice(380);
        room37.setThFloor("2");

        Room room38 = new Room();
        room38.setCountOfAdult(2);
        room38.setRoomNumber("B229");
        room38.setCountOfChild(2);
        room38.setPrice(550);
        room38.setThFloor("2");

        Room room39 = new Room();
        room39.setCountOfAdult(1);
        room39.setRoomNumber("B311");
        room39.setCountOfChild(0);
        room39.setPrice(220);
        room39.setThFloor("3");

        Room room40 = new Room();
        room40.setCountOfAdult(1);
        room40.setRoomNumber("B312");
        room40.setCountOfChild(0);
        room40.setPrice(220);
        room40.setThFloor("3");
        room40.setCleaned(false);

        Room room41 = new Room();
        room41.setCountOfAdult(1);
        room41.setRoomNumber("B313");
        room41.setCountOfChild(0);
        room41.setPrice(220);
        room41.setThFloor("3");

        Room room42 = new Room();
        room42.setCountOfAdult(1);
        room42.setRoomNumber("B314");
        room42.setCountOfChild(1);
        room42.setPrice(220);
        room42.setThFloor("3");

        Room room43 = new Room();
        room43.setCountOfAdult(1);
        room43.setRoomNumber("B315");
        room43.setCountOfChild(1);
        room43.setPrice(220);
        room43.setThFloor("3");
        room43.setCleaned(false);


        Room room44 = new Room();
        room44.setCountOfAdult(1);
        room44.setRoomNumber("B316");
        room44.setCountOfChild(1);
        room44.setPrice(220);
        room44.setThFloor("3");

        Room room45 = new Room();
        room45.setCountOfAdult(1);
        room45.setRoomNumber("B317");
        room45.setCountOfChild(1);
        room45.setPrice(220);
        room45.setThFloor("3");


        Room room46 = new Room();
        room46.setCountOfAdult(1);
        room46.setRoomNumber("B318");
        room46.setCountOfChild(0);
        room46.setPrice(240);
        room46.setThFloor("3");
        room46.setDisabled(true);
        room46.setCleaned(false);


        Room room47 = new Room();
        room47.setCountOfAdult(1);
        room47.setRoomNumber("B319");
        room47.setCountOfChild(0);
        room47.setPrice(240);
        room47.setThFloor("3");
        room47.setDisabled(true);

        Room room48 = new Room();
        room48.setCountOfAdult(2);
        room48.setRoomNumber("B320");
        room48.setCountOfChild(0);
        room48.setPrice(380);
        room48.setThFloor("3");
        room48.setDisabled(true);
        room48.setCleaned(false);

        Room room49 = new Room();
        room49.setCountOfAdult(2);
        room49.setRoomNumber("B321");
        room49.setCountOfChild(0);
        room49.setPrice(380);
        room49.setThFloor("3");

        Room room50 = new Room();
        room50.setCountOfAdult(2);
        room50.setRoomNumber("B322");
        room50.setCountOfChild(0);
        room50.setPrice(380);
        room50.setThFloor("3");

        Room room51 = new Room();
        room51.setCountOfAdult(2);
        room51.setRoomNumber("B323");
        room51.setCountOfChild(0);
        room51.setPrice(220);
        room51.setThFloor("3");
        room51.setCleaned(false);

        Room room52 = new Room();
        room52.setCountOfAdult(2);
        room52.setRoomNumber("3124");
        room52.setCountOfChild(0);
        room52.setPrice(220);
        room52.setThFloor("3");

        Room room53 = new Room();
        room53.setCountOfAdult(2);
        room53.setRoomNumber("B325");
        room53.setCountOfChild(1);
        room53.setPrice(380);
        room53.setThFloor("3");

        Room room54 = new Room();
        room54.setCountOfAdult(2);
        room54.setRoomNumber("B326");
        room54.setCountOfChild(1);
        room54.setPrice(380);
        room54.setThFloor("3");

        Room room55 = new Room();
        room55.setCountOfAdult(2);
        room55.setRoomNumber("B327");
        room55.setCountOfChild(2);
        room55.setPrice(380);
        room55.setThFloor("3");
        room55.setCleaned(false);

        Room room56 = new Room();
        room56.setCountOfAdult(2);
        room56.setRoomNumber("B328");
        room56.setCountOfChild(2);
        room56.setPrice(380);
        room56.setThFloor("3");

        Room room57 = new Room();
        room57.setCountOfAdult(4);
        room57.setRoomNumber("B329");
        room57.setCountOfChild(2);
        room57.setPrice(550);
        room57.setThFloor("3");

        //2.kat odalar
        Room room58 = new Room();
        room58.setCountOfAdult(1);
        room58.setRoomNumber("B411");
        room58.setCountOfChild(0);
        room58.setPrice(220);
        room58.setThFloor("4");
        room58.setSmoking(true);

        Room room59 = new Room();
        room59.setCountOfAdult(1);
        room59.setRoomNumber("B412");
        room59.setCountOfChild(0);
        room59.setPrice(220);
        room59.setThFloor("4");
        room59.setSmoking(true);
        room59.setCleaned(false);


        Room room60 = new Room();
        room60.setCountOfAdult(1);
        room60.setRoomNumber("B413");
        room60.setCountOfChild(0);
        room60.setPrice(220);
        room60.setThFloor("4");
        room60.setSmoking(true);


        Room room61 = new Room();
        room61.setCountOfAdult(1);
        room61.setRoomNumber("B414");
        room61.setCountOfChild(1);
        room61.setPrice(220);
        room61.setThFloor("4");
        room61.setSmoking(true);


        Room room62 = new Room();
        room62.setCountOfAdult(1);
        room62.setRoomNumber("B415");
        room62.setCountOfChild(1);
        room62.setPrice(220);
        room62.setThFloor("4");
        room62.setSmoking(true);



        Room room63 = new Room();
        room63.setCountOfAdult(1);
        room63.setRoomNumber("B416");
        room63.setCountOfChild(1);
        room63.setPrice(220);
        room63.setThFloor("4");
        room63.setSmoking(true);


        Room room64 = new Room();
        room64.setCountOfAdult(1);
        room64.setRoomNumber("B417");
        room64.setCountOfChild(1);
        room64.setPrice(220);
        room64.setThFloor("4");
        room64.setSmoking(true);
        room64.setCleaned(false);




        Room room65 = new Room();
        room65.setCountOfAdult(1);
        room65.setRoomNumber("B418");
        room65.setCountOfChild(0);
        room65.setPrice(240);
        room65.setThFloor("4");
        room65.setDisabled(true);
        room65.setSmoking(true);



        Room room66 = new Room();
        room66.setCountOfAdult(1);
        room66.setRoomNumber("B419");
        room66.setCountOfChild(0);
        room66.setPrice(240);
        room66.setThFloor("4");
        room66.setDisabled(true);
        room66.setSmoking(true);


        Room room67 = new Room();
        room67.setCountOfAdult(2);
        room67.setRoomNumber("B420");
        room67.setCountOfChild(0);
        room67.setPrice(380);
        room67.setThFloor("4");
        room67.setDisabled(true);
        room67.setSmoking(true);


        Room room68 = new Room();
        room68.setCountOfAdult(2);
        room68.setRoomNumber("B421");
        room68.setCountOfChild(0);
        room68.setPrice(380);
        room68.setThFloor("4");
        room68.setSmoking(true);
        room68.setCleaned(false);



        Room room69 = new Room();
        room69.setCountOfAdult(2);
        room69.setRoomNumber("B422");
        room69.setCountOfChild(0);
        room69.setPrice(380);
        room69.setThFloor("4");
        room69.setSmoking(true);


        Room room70 = new Room();
        room70.setCountOfAdult(2);
        room70.setRoomNumber("B423");
        room70.setCountOfChild(0);
        room70.setPrice(220);
        room70.setThFloor("4");
        room70.setSmoking(true);


        Room room71 = new Room();
        room71.setCountOfAdult(2);
        room71.setRoomNumber("B424");
        room71.setCountOfChild(0);
        room71.setPrice(220);
        room71.setThFloor("4");
        room71.setSmoking(true);


        Room room72 = new Room();
        room72.setCountOfAdult(2);
        room72.setRoomNumber("B425");
        room72.setCountOfChild(1);
        room72.setPrice(380);
        room72.setThFloor("4");
        room72.setSmoking(true);


        Room room73 = new Room();
        room73.setCountOfAdult(2);
        room73.setRoomNumber("B426");
        room73.setCountOfChild(1);
        room73.setPrice(380);
        room73.setThFloor("4");
        room73.setSmoking(true);


        Room room74 = new Room();
        room74.setCountOfAdult(2);
        room74.setRoomNumber("B427");
        room74.setCountOfChild(2);
        room74.setPrice(380);
        room74.setThFloor("4");
        room74.setSmoking(true);


        Room room75 = new Room();
        room75.setCountOfAdult(8);
        room75.setRoomNumber("B428");
        room75.setCountOfChild(2);
        room75.setPrice(800);
        room75.setThFloor("4");
        room75.setSmoking(true);


        Room room76 = new Room();
        room76.setCountOfAdult(8);
        room76.setRoomNumber("B429");
        room76.setCountOfChild(2);
        room76.setPrice(800);
        room76.setThFloor("4");
        room76.setSmoking(true);


        Room room77 = new Room();
        room77.setCountOfAdult(10);
        room77.setRoomNumber("B511");
        room77.setCountOfChild(2);
        room77.setPrice(3000);
        room77.setThFloor("5");
        room77.setSmoking(true);


        Room room78 = new Room();
        room78.setCountOfAdult(10);
        room78.setRoomNumber("B512");
        room78.setCountOfChild(2);
        room78.setPrice(3000);
        room78.setThFloor("5");
        room78.setSmoking(true);





        if (roomTypeRepository.findByRoomTypeName("STANDARD").isPresent()) {
            room1.setRoomType(roomTypeRepository.findByRoomTypeName("STANDARD").get());
            room1.setDescription("Basic room with essential amenities, perfect for a short stay.");
            room1.setDescriptionZh("基础房间，提供基本设施，非常适合短期入住。");
            room3.setRoomType(roomTypeRepository.findByRoomTypeName("STANDARD").get());
            room3.setDescription("Basic room with essential amenities, perfect for a short stay.");
            room3.setDescriptionZh("基础房间，提供基本设施，非常适合短期入住。");
            room4.setRoomType(roomTypeRepository.findByRoomTypeName("STANDARD").get());
            room4.setDescription("Basic room with essential amenities, perfect for a short stay.");
            room4.setDescriptionZh("基础房间，提供基本设施，非常适合短期入住。");
            room5.setRoomType(roomTypeRepository.findByRoomTypeName("STANDARD").get());
            room5.setDescription("Basic room with essential amenities, perfect for a short stay.");
            room5.setDescriptionZh("基础房间，提供基本设施，非常适合短期入住。");
            room6.setRoomType(roomTypeRepository.findByRoomTypeName("STANDARD").get());
            room6.setDescription("Basic room with essential amenities, perfect for a short stay.");
            room6.setDescriptionZh("基础房间，提供基本设施，非常适合短期入住。");
            room7.setRoomType(roomTypeRepository.findByRoomTypeName("STANDARD").get());
            room7.setDescription("Basic room with essential amenities, perfect for a short stay.");
            room7.setDescriptionZh("基础房间，提供基本设施，非常适合短期入住。");
            room13.setRoomType(roomTypeRepository.findByRoomTypeName("STANDARD").get());
            room13.setDescription("Basic room with essential amenities, perfect for a short stay.");
            room13.setDescriptionZh("基础房间，提供基本设施，非常适合短期入住。");
            room14.setRoomType(roomTypeRepository.findByRoomTypeName("STANDARD").get());
            room14.setDescription("Basic room with essential amenities, perfect for a short stay.");
            room14.setDescriptionZh("基础房间，提供基本设施，非常适合短期入住。");
            room20.setRoomType(roomTypeRepository.findByRoomTypeName("STANDARD").get());
            room20.setDescription("Basic room with essential amenities, perfect for a short stay.");
            room20.setDescriptionZh("基础房间，提供基本设施，非常适合短期入住。");
            room22.setRoomType(roomTypeRepository.findByRoomTypeName("STANDARD").get());
            room22.setDescription("Basic room with essential amenities, perfect for a short stay.");
            room22.setDescriptionZh("基础房间，提供基本设施，非常适合短期入住。");
            room23.setRoomType(roomTypeRepository.findByRoomTypeName("STANDARD").get());
            room23.setDescription("Basic room with essential amenities, perfect for a short stay.");
            room23.setDescriptionZh("基础房间，提供基本设施，非常适合短期入住。");
            room24.setRoomType(roomTypeRepository.findByRoomTypeName("STANDARD").get());
            room24.setDescription("Basic room with essential amenities, perfect for a short stay.");
            room24.setDescriptionZh("基础房间，提供基本设施，非常适合短期入住。");
            room25.setRoomType(roomTypeRepository.findByRoomTypeName("STANDARD").get());
            room25.setDescription("Basic room with essential amenities, perfect for a short stay.");
            room25.setDescriptionZh("基础房间，提供基本设施，非常适合短期入住。");
            room26.setRoomType(roomTypeRepository.findByRoomTypeName("STANDARD").get());
            room26.setDescription("Basic room with essential amenities, perfect for a short stay.");
            room26.setDescriptionZh("基础房间，提供基本设施，非常适合短期入住。");
            room32.setRoomType(roomTypeRepository.findByRoomTypeName("STANDARD").get());
            room32.setDescription("Basic room with essential amenities, perfect for a short stay.");
            room32.setDescriptionZh("基础房间，提供基本设施，非常适合短期入住。");
            room33.setRoomType(roomTypeRepository.findByRoomTypeName("STANDARD").get());
            room33.setDescription("Basic room with essential amenities, perfect for a short stay.");
            room33.setDescriptionZh("基础房间，提供基本设施，非常适合短期入住。");


            room39.setRoomType(roomTypeRepository.findByRoomTypeName("STANDARD").get());
            room39.setDescription("Basic room with essential amenities, perfect for a short stay.");
            room39.setDescriptionZh("基础房间，提供基本设施，非常适合短期入住。");
            room41.setRoomType(roomTypeRepository.findByRoomTypeName("STANDARD").get());
            room41.setDescription("Basic room with essential amenities, perfect for a short stay.");
            room41.setDescriptionZh("基础房间，提供基本设施，非常适合短期入住。");
            room42.setRoomType(roomTypeRepository.findByRoomTypeName("STANDARD").get());
            room42.setDescription("Basic room with essential amenities, perfect for a short stay.");
            room42.setDescriptionZh("基础房间，提供基本设施，非常适合短期入住。");
            room43.setRoomType(roomTypeRepository.findByRoomTypeName("STANDARD").get());
            room43.setDescription("Basic room with essential amenities, perfect for a short stay.");
            room43.setDescriptionZh("基础房间，提供基本设施，非常适合短期入住。");
            room44.setRoomType(roomTypeRepository.findByRoomTypeName("STANDARD").get());
            room44.setDescription("Basic room with essential amenities, perfect for a short stay.");
            room44.setDescriptionZh("基础房间，提供基本设施，非常适合短期入住。");
            room45.setRoomType(roomTypeRepository.findByRoomTypeName("STANDARD").get());
            room45.setDescription("Basic room with essential amenities, perfect for a short stay.");
            room45.setDescriptionZh("基础房间，提供基本设施，非常适合短期入住。");
            room51.setRoomType(roomTypeRepository.findByRoomTypeName("STANDARD").get());
            room51.setDescription("Basic room with essential amenities, perfect for a short stay.");
            room51.setDescriptionZh("基础房间，提供基本设施，非常适合短期入住。");
            room52.setRoomType(roomTypeRepository.findByRoomTypeName("STANDARD").get());
            room52.setDescription("Basic room with essential amenities, perfect for a short stay.");
            room52.setDescriptionZh("基础房间，提供基本设施，非常适合短期入住。");


            room58.setRoomType(roomTypeRepository.findByRoomTypeName("STANDARD").get());
            room58.setDescription("Basic room with essential amenities, perfect for a short stay.");
            room58.setDescriptionZh("基础房间，提供基本设施，非常适合短期入住。");
            room60.setRoomType(roomTypeRepository.findByRoomTypeName("STANDARD").get());
            room60.setDescription("Basic room with essential amenities, perfect for a short stay.");
            room60.setDescriptionZh("基础房间，提供基本设施，非常适合短期入住。");
            room61.setRoomType(roomTypeRepository.findByRoomTypeName("STANDARD").get());
            room61.setDescription("Basic room with essential amenities, perfect for a short stay.");
            room61.setDescriptionZh("基础房间，提供基本设施，非常适合短期入住。");
            room62.setRoomType(roomTypeRepository.findByRoomTypeName("STANDARD").get());
            room62.setDescription("Basic room with essential amenities, perfect for a short stay.");
            room62.setDescriptionZh("基础房间，提供基本设施，非常适合短期入住。");
            room63.setRoomType(roomTypeRepository.findByRoomTypeName("STANDARD").get());
            room63.setDescription("Basic room with essential amenities, perfect for a short stay.");
            room63.setDescriptionZh("基础房间，提供基本设施，非常适合短期入住。");
            room64.setRoomType(roomTypeRepository.findByRoomTypeName("STANDARD").get());
            room64.setDescription("Basic room with essential amenities, perfect for a short stay.");
            room64.setDescriptionZh("基础房间，提供基本设施，非常适合短期入住。");
            room70.setRoomType(roomTypeRepository.findByRoomTypeName("STANDARD").get());
            room70.setDescription("Basic room with essential amenities, perfect for a short stay.");
            room70.setDescriptionZh("基础房间，提供基本设施，非常适合短期入住。");

            room71.setRoomType(roomTypeRepository.findByRoomTypeName("STANDARD").get());
            room71.setDescription("Basic room with essential amenities, perfect for a short stay.");
            room71.setDescriptionZh("基础房间，提供基本设施，非常适合短期入住。");



        }

        if (roomTypeRepository.findByRoomTypeName("DOUBLE-B").isPresent()) {

            room8.setRoomType(roomTypeRepository.findByRoomTypeName("DOUBLE-B").get());
            room8.setDescription("The Compact bedrooms are suited for single occupancy or short-stay double occupancy as they have limited space and storage.");
            room8.setDescriptionZh("我们的豪华特大号床房设有休息区、充足的存储空间、数字保险箱和小冰箱。");

            room9.setRoomType(roomTypeRepository.findByRoomTypeName("DOUBLE-B").get());
            room9.setDescription("The Compact bedrooms are suited for single occupancy or short-stay double occupancy as they have limited space and storage.");
            room8.setDescriptionZh("我们的豪华特大号床房设有休息区、充足的存储空间、数字保险箱和小冰箱。");
            room27.setRoomType(roomTypeRepository.findByRoomTypeName("DOUBLE-B").get());
            room27.setDescription("The Compact bedrooms are suited for single occupancy or short-stay double occupancy as they have limited space and storage.");
            room8.setDescriptionZh("我们的豪华特大号床房设有休息区、充足的存储空间、数字保险箱和小冰箱。");
            room28.setRoomType(roomTypeRepository.findByRoomTypeName("DOUBLE-B").get());
            room28.setDescription("The Compact bedrooms are suited for single occupancy or short-stay double occupancy as they have limited space and storage.");
            room8.setDescriptionZh("我们的豪华特大号床房设有休息区、充足的存储空间、数字保险箱和小冰箱。");


            room46.setRoomType(roomTypeRepository.findByRoomTypeName("DOUBLE-B").get());
            room46.setDescription("The Compact bedrooms are suited for single occupancy or short-stay double occupancy as they have limited space and storage.");
            room46.setDescriptionZh("紧凑型卧室适合单人入住或短期双人入住，因为它们空间和储物有限。");
            room47.setRoomType(roomTypeRepository.findByRoomTypeName("DOUBLE-B").get());
            room47.setDescription("The Compact bedrooms are suited for single occupancy or short-stay double occupancy as they have limited space and storage.");
            room47.setDescriptionZh("紧凑型卧室适合单人入住或短期双人入住，因为它们空间和储物有限。");
            room65.setRoomType(roomTypeRepository.findByRoomTypeName("DOUBLE-B").get());
            room65.setDescription("The Compact bedrooms are suited for single occupancy or short-stay double occupancy as they have limited space and storage.");
            room65.setDescriptionZh("紧凑型卧室适合单人入住或短期双人入住，因为它们空间和储物有限。");
            room66.setRoomType(roomTypeRepository.findByRoomTypeName("DOUBLE-B").get());
            room66.setDescription("The Compact bedrooms are suited for single occupancy or short-stay double occupancy as they have limited space and storage.");
            room66.setDescriptionZh("紧凑型卧室适合单人入住或短期双人入住，因为它们空间和储物有限。");




        }

        if (roomTypeRepository.findByRoomTypeName("DELUXE").isPresent()) {
            room10.setRoomType(roomTypeRepository.findByRoomTypeName("DELUXE").get());
            room10.setDescription("Our Deluxe king size room has a seating area, ample storage, digital safe and mini fridge.");
            room10.setDescriptionZh("我们的豪华特大号床房设有休息区、充足的存储空间、数字保险箱和小冰箱。");
            room11.setRoomType(roomTypeRepository.findByRoomTypeName("DELUXE").get());
            room11.setDescription("Our Deluxe king size room has a seating area, ample storage, digital safe and mini fridge.");
            room10.setDescriptionZh("我们的豪华特大号床房设有休息区、充足的存储空间、数字保险箱和小冰箱。");


            room12.setRoomType(roomTypeRepository.findByRoomTypeName("DELUXE").get());
            room12.setDescription("Our Deluxe king size room has a seating area, ample storage, digital safe and mini fridge.");
            room10.setDescriptionZh("我们的豪华特大号床房设有休息区、充足的存储空间、数字保险箱和小冰箱。");
            room15.setRoomType(roomTypeRepository.findByRoomTypeName("DELUXE").get());
            room15.setDescription("Our Deluxe king size room has a seating area, ample storage, digital safe and mini fridge.");
            room15.setDescriptionZh("我们的豪华特大号床房设有休息区、充足的存储空间、数字保险箱和小冰箱。");
            room16.setRoomType(roomTypeRepository.findByRoomTypeName("DELUXE").get());
            room16.setDescription("Our Deluxe king size room has a seating area, ample storage, digital safe and mini fridge.");
            room16.setDescriptionZh("我们的豪华特大号床房设有休息区、充足的存储空间、数字保险箱和小冰箱。");
            room17.setRoomType(roomTypeRepository.findByRoomTypeName("DELUXE").get());
            room17.setDescription("Our Deluxe king size room has a seating area, ample storage, digital safe and mini fridge.");
            room17.setDescriptionZh("我们的豪华特大号床房设有休息区、充足的存储空间、数字保险箱和小冰箱。");
            room18.setRoomType(roomTypeRepository.findByRoomTypeName("DELUXE").get());
            room18.setDescription("Our Deluxe king size room has a seating area, ample storage, digital safe and mini fridge.");
            room18.setDescriptionZh("我们的豪华特大号床房设有休息区、充足的存储空间、数字保险箱和小冰箱。");
            room19.setRoomType(roomTypeRepository.findByRoomTypeName("DELUXE").get());
            room19.setDescription("Our Deluxe king size room has a seating area, ample storage, digital safe and mini fridge.");
            room19.setDescriptionZh("我们的豪华特大号床房设有休息区、充足的存储空间、数字保险箱和小冰箱。");
            room29.setRoomType(roomTypeRepository.findByRoomTypeName("DELUXE").get());
            room29.setDescription("Our Deluxe king size room has a seating area, ample storage, digital safe and mini fridge.");
            room29.setDescriptionZh("我们的豪华特大号床房设有休息区、充足的存储空间、数字保险箱和小冰箱。");
            room30.setRoomType(roomTypeRepository.findByRoomTypeName("DELUXE").get());
            room30.setDescription("Our Deluxe king size room has a seating area, ample storage, digital safe and mini fridge.");
            room30.setDescriptionZh("我们的豪华特大号床房设有休息区、充足的存储空间、数字保险箱和小冰箱。");
            room31.setRoomType(roomTypeRepository.findByRoomTypeName("DELUXE").get());
            room31.setDescription("Our Deluxe king size room has a seating area, ample storage, digital safe and mini fridge.");
            room31.setDescriptionZh("我们的豪华特大号床房设有休息区、充足的存储空间、数字保险箱和小冰箱。");
            room34.setRoomType(roomTypeRepository.findByRoomTypeName("DELUXE").get());
            room34.setDescription("Our Deluxe king size room has a seating area, ample storage, digital safe and mini fridge.");
            room34.setDescriptionZh("我们的豪华特大号床房设有休息区、充足的存储空间、数字保险箱和小冰箱。");
            room35.setRoomType(roomTypeRepository.findByRoomTypeName("DELUXE").get());
            room35.setDescription("Our Deluxe king size room has a seating area, ample storage, digital safe and mini fridge.");
            room35.setDescriptionZh("我们的豪华特大号床房设有休息区、充足的存储空间、数字保险箱和小冰箱。");
            room36.setRoomType(roomTypeRepository.findByRoomTypeName("DELUXE").get());
            room36.setDescription("Our Deluxe king size room has a seating area, ample storage, digital safe and mini fridge.");
            room36.setDescriptionZh("我们的豪华特大号床房设有休息区、充足的存储空间、数字保险箱和小冰箱。");
            room37.setRoomType(roomTypeRepository.findByRoomTypeName("DELUXE").get());
            room37.setDescription("Our Deluxe king size room has a seating area, ample storage, digital safe and mini fridge.");
            room37.setDescriptionZh("我们的豪华特大号床房设有休息区、充足的存储空间、数字保险箱和小冰箱。");
            room38.setRoomType(roomTypeRepository.findByRoomTypeName("DELUXE").get());
            room38.setDescription("Our Deluxe king size room has a seating area, ample storage, digital safe and mini fridge.");
            room38.setDescriptionZh("我们的豪华特大号床房设有休息区、充足的存储空间、数字保险箱和小冰箱。");

            room48.setRoomType(roomTypeRepository.findByRoomTypeName("DELUXE").get());
            room48.setDescription("Our Deluxe king size room has a seating area, ample storage, digital safe and mini fridge.");
            room48.setDescriptionZh("我们的豪华特大号床房设有休息区、充足的存储空间、数字保险箱和小冰箱。");
            room49.setRoomType(roomTypeRepository.findByRoomTypeName("DELUXE").get());
            room49.setDescription("Our Deluxe king size room has a seating area, ample storage, digital safe and mini fridge.");
            room49.setDescriptionZh("我们的豪华特大号床房设有休息区、充足的存储空间、数字保险箱和小冰箱。");
            room50.setRoomType(roomTypeRepository.findByRoomTypeName("DELUXE").get());
            room50.setDescription("Our Deluxe king size room has a seating area, ample storage, digital safe and mini fridge.");
            room50.setDescriptionZh("我们的豪华特大号床房设有休息区、充足的存储空间、数字保险箱和小冰箱。");
            room53.setRoomType(roomTypeRepository.findByRoomTypeName("DELUXE").get());
            room53.setDescription("Our Deluxe king size room has a seating area, ample storage, digital safe and mini fridge.");
            room53.setDescriptionZh("我们的豪华特大号床房设有休息区、充足的存储空间、数字保险箱和小冰箱。");
            room54.setRoomType(roomTypeRepository.findByRoomTypeName("DELUXE").get());
            room54.setDescription("Our Deluxe king size room has a seating area, ample storage, digital safe and mini fridge.");
            room54.setDescriptionZh("我们的豪华特大号床房设有休息区、充足的存储空间、数字保险箱和小冰箱。");
            room55.setRoomType(roomTypeRepository.findByRoomTypeName("DELUXE").get());
            room55.setDescription("Our Deluxe king size room has a seating area, ample storage, digital safe and mini fridge.");
            room55.setDescriptionZh("我们的豪华特大号床房设有休息区、充足的存储空间、数字保险箱和小冰箱。");
            room56.setRoomType(roomTypeRepository.findByRoomTypeName("DELUXE").get());
            room56.setDescription("Our Deluxe king size room has a seating area, ample storage, digital safe and mini fridge.");
            room56.setDescriptionZh("我们的豪华特大号床房设有休息区、充足的存储空间、数字保险箱和小冰箱。");
            room57.setRoomType(roomTypeRepository.findByRoomTypeName("DELUXE").get());
            room57.setDescription("Our Deluxe king size room has a seating area, ample storage, digital safe and mini fridge.");
            room57.setDescriptionZh("我们的豪华特大号床房设有休息区、充足的存储空间、数字保险箱和小冰箱。");

            room67.setRoomType(roomTypeRepository.findByRoomTypeName("DELUXE").get());
            room67.setDescription("Our Deluxe king size room has a seating area, ample storage, digital safe and mini fridge.");
            room67.setDescriptionZh("我们的豪华特大号床房设有休息区、充足的存储空间、数字保险箱和小冰箱。");
            room68.setRoomType(roomTypeRepository.findByRoomTypeName("DELUXE").get());
            room68.setDescription("Our Deluxe king size room has a seating area, ample storage, digital safe and mini fridge.");
            room68.setDescriptionZh("我们的豪华特大号床房设有休息区、充足的存储空间、数字保险箱和小冰箱。");
            room69.setRoomType(roomTypeRepository.findByRoomTypeName("DELUXE").get());
            room69.setDescription("Our Deluxe king size room has a seating area, ample storage, digital safe and mini fridge.");
            room69.setDescriptionZh("我们的豪华特大号床房设有休息区、充足的存储空间、数字保险箱和小冰箱。");
            room72.setRoomType(roomTypeRepository.findByRoomTypeName("DELUXE").get());
            room72.setDescription("Our Deluxe king size room has a seating area, ample storage, digital safe and mini fridge.");
            room72.setDescriptionZh("我们的豪华特大号床房设有休息区、充足的存储空间、数字保险箱和小冰箱。");
            room73.setRoomType(roomTypeRepository.findByRoomTypeName("DELUXE").get());
            room73.setDescription("Our Deluxe king size room has a seating area, ample storage, digital safe and mini fridge.");
            room73.setDescriptionZh("我们的豪华特大号床房设有休息区、充足的存储空间、数字保险箱和小冰箱。");
            room74.setRoomType(roomTypeRepository.findByRoomTypeName("DELUXE").get());
            room74.setDescription("Our Deluxe king size room has a seating area, ample storage, digital safe and mini fridge.");
            room74.setDescriptionZh("我们的豪华特大号床房设有休息区、充足的存储空间、数字保险箱和小冰箱。");





        }

        if (roomTypeRepository.findByRoomTypeName("PENTHOUSE").isPresent()) {
            room75.setRoomType(roomTypeRepository.findByRoomTypeName("PENTHOUSE").get());
            room75.setDescription("Our penthouse offers a unique combination of modern amenities and elegant design. Enjoy panoramic views, private pools and first-class service.");
            room75.setDescriptionZh("我们的顶层公寓提供现代设施和优雅设计的独特组合。享受全景视野、私人泳池和一流的服务。");
            room76.setRoomType(roomTypeRepository.findByRoomTypeName("PENTHOUSE").get());
            room76.setDescription("Our penthouse offers a unique combination of modern amenities and elegant design. Enjoy panoramic views, private pools and first-class service.");
            room76.setDescriptionZh("我们的顶层公寓提供现代设施和优雅设计的独特组合。享受全景视野、私人泳池和一流的服务。");
            room77.setRoomType(roomTypeRepository.findByRoomTypeName("PENTHOUSE").get());
            room77.setDescription("Our penthouse offers a unique combination of modern amenities and elegant design. Enjoy panoramic views, private pools and first-class service.");
            room77.setDescriptionZh("我们的顶层公寓提供现代设施和优雅设计的独特组合。享受全景视野、私人泳池和一流的服务。");
            room78.setRoomType(roomTypeRepository.findByRoomTypeName("PENTHOUSE").get());
            room78.setDescription("Our penthouse offers a unique combination of modern amenities and elegant design. Enjoy panoramic views, private pools and first-class service.");
            room78.setDescriptionZh("我们的顶层公寓提供现代设施和优雅设计的独特组合。享受全景视野、私人泳池和一流的服务。");


            if (bedTypeRepository.findByBedTypeName("SINGLE BED").isPresent()) {
                room1.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room2.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room3.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room4.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room5.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room6.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room7.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room8.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room9.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room13.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room13.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room14.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room14.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room19.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room20.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room21.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room22.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room23.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room24.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room25.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room26.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room27.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room28.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room32.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room32.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room33.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room33.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room38.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());

                room39.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room40.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room41.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room42.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room43.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room44.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room45.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room46.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room47.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());

                room51.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room51.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room52.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room52.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room57.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room58.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room59.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room60.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room61.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room62.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room63.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room64.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room65.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room66.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());

                room70.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room70.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room71.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room71.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room75.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room75.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room75.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room75.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room76.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room76.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room76.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room76.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room77.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room77.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room77.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room77.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room78.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room78.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room78.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room78.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());

            }
            if (bedTypeRepository.findByBedTypeName("DOUBLE BED").isPresent()) {

                room10.getBedTypeList().add(bedTypeRepository.findByBedTypeName("DOUBLE BED").get());
                room11.getBedTypeList().add(bedTypeRepository.findByBedTypeName("DOUBLE BED").get());
                room12.getBedTypeList().add(bedTypeRepository.findByBedTypeName("DOUBLE BED").get());
                room15.getBedTypeList().add(bedTypeRepository.findByBedTypeName("DOUBLE BED").get());
                room16.getBedTypeList().add(bedTypeRepository.findByBedTypeName("DOUBLE BED").get());
                room17.getBedTypeList().add(bedTypeRepository.findByBedTypeName("DOUBLE BED").get());
                room18.getBedTypeList().add(bedTypeRepository.findByBedTypeName("DOUBLE BED").get());
                room19.getBedTypeList().add(bedTypeRepository.findByBedTypeName("DOUBLE BED").get());
                room19.getBedTypeList().add(bedTypeRepository.findByBedTypeName("DOUBLE BED").get());
                room29.getBedTypeList().add(bedTypeRepository.findByBedTypeName("DOUBLE BED").get());
                room30.getBedTypeList().add(bedTypeRepository.findByBedTypeName("DOUBLE BED").get());
                room31.getBedTypeList().add(bedTypeRepository.findByBedTypeName("DOUBLE BED").get());
                room34.getBedTypeList().add(bedTypeRepository.findByBedTypeName("DOUBLE BED").get());
                room35.getBedTypeList().add(bedTypeRepository.findByBedTypeName("DOUBLE BED").get());
                room36.getBedTypeList().add(bedTypeRepository.findByBedTypeName("DOUBLE BED").get());
                room37.getBedTypeList().add(bedTypeRepository.findByBedTypeName("DOUBLE BED").get());
                room38.getBedTypeList().add(bedTypeRepository.findByBedTypeName("DOUBLE BED").get());
                room38.getBedTypeList().add(bedTypeRepository.findByBedTypeName("DOUBLE BED").get());

                room48.getBedTypeList().add(bedTypeRepository.findByBedTypeName("DOUBLE BED").get());
                room49.getBedTypeList().add(bedTypeRepository.findByBedTypeName("DOUBLE BED").get());
                room50.getBedTypeList().add(bedTypeRepository.findByBedTypeName("DOUBLE BED").get());
                room53.getBedTypeList().add(bedTypeRepository.findByBedTypeName("DOUBLE BED").get());
                room54.getBedTypeList().add(bedTypeRepository.findByBedTypeName("DOUBLE BED").get());
                room55.getBedTypeList().add(bedTypeRepository.findByBedTypeName("DOUBLE BED").get());
                room56.getBedTypeList().add(bedTypeRepository.findByBedTypeName("DOUBLE BED").get());
                room57.getBedTypeList().add(bedTypeRepository.findByBedTypeName("DOUBLE BED").get());
                room57.getBedTypeList().add(bedTypeRepository.findByBedTypeName("DOUBLE BED").get());
                room67.getBedTypeList().add(bedTypeRepository.findByBedTypeName("DOUBLE BED").get());
                room68.getBedTypeList().add(bedTypeRepository.findByBedTypeName("DOUBLE BED").get());
                room69.getBedTypeList().add(bedTypeRepository.findByBedTypeName("DOUBLE BED").get());
                room72.getBedTypeList().add(bedTypeRepository.findByBedTypeName("DOUBLE BED").get());
                room73.getBedTypeList().add(bedTypeRepository.findByBedTypeName("DOUBLE BED").get());
                room74.getBedTypeList().add(bedTypeRepository.findByBedTypeName("DOUBLE BED").get());
                room75.getBedTypeList().add(bedTypeRepository.findByBedTypeName("DOUBLE BED").get());
                room75.getBedTypeList().add(bedTypeRepository.findByBedTypeName("DOUBLE BED").get());
                room76.getBedTypeList().add(bedTypeRepository.findByBedTypeName("DOUBLE BED").get());
                room76.getBedTypeList().add(bedTypeRepository.findByBedTypeName("DOUBLE BED").get());
                room77.getBedTypeList().add(bedTypeRepository.findByBedTypeName("DOUBLE BED").get());
                room77.getBedTypeList().add(bedTypeRepository.findByBedTypeName("DOUBLE BED").get());
                room77.getBedTypeList().add(bedTypeRepository.findByBedTypeName("DOUBLE BED").get());
                room78.getBedTypeList().add(bedTypeRepository.findByBedTypeName("DOUBLE BED").get());
                room78.getBedTypeList().add(bedTypeRepository.findByBedTypeName("DOUBLE BED").get());
                room78.getBedTypeList().add(bedTypeRepository.findByBedTypeName("DOUBLE BED").get());

            }

            if (bedTypeRepository.findByBedTypeName("BABY BED").isPresent()) {

                room19.getBedTypeList().add(bedTypeRepository.findByBedTypeName("BABY BED").get());
                room38.getBedTypeList().add(bedTypeRepository.findByBedTypeName("BABY BED").get());

                room57.getBedTypeList().add(bedTypeRepository.findByBedTypeName("BABY BED").get());
                room75.getBedTypeList().add(bedTypeRepository.findByBedTypeName("BABY BED").get());
                room75.getBedTypeList().add(bedTypeRepository.findByBedTypeName("BABY BED").get());
                room76.getBedTypeList().add(bedTypeRepository.findByBedTypeName("BABY BED").get());
                room76.getBedTypeList().add(bedTypeRepository.findByBedTypeName("BABY BED").get());
                room77.getBedTypeList().add(bedTypeRepository.findByBedTypeName("BABY BED").get());
                room77.getBedTypeList().add(bedTypeRepository.findByBedTypeName("BABY BED").get());
                room78.getBedTypeList().add(bedTypeRepository.findByBedTypeName("BABY BED").get());
                room78.getBedTypeList().add(bedTypeRepository.findByBedTypeName("BABY BED").get());

            }


            if (facilitiesRepository.findByFacilitiesName("Mini Fridge").isPresent()) {
                room1.getFacilitiesList().add(facilitiesRepository.findByFacilitiesName("Mini Fridge").get());
            }

            if (facilitiesRepository.findByFacilitiesName("TV").isPresent()) {
                room1.getFacilitiesList().add(facilitiesRepository.findByFacilitiesName("TV").get());
            }
            if (facilitiesRepository.findByFacilitiesName("Airco").isPresent()) {
                room1.getFacilitiesList().add(facilitiesRepository.findByFacilitiesName("Airco").get());
            }
            if (facilitiesRepository.findByFacilitiesName("WIFI").isPresent()) {
                room1.getFacilitiesList().add(facilitiesRepository.findByFacilitiesName("WIFI").get());
            }
            if (facilitiesRepository.findByFacilitiesName("Toilet").isPresent()) {
                room1.getFacilitiesList().add(facilitiesRepository.findByFacilitiesName("Toilet").get());
            }
            if (facilitiesRepository.findByFacilitiesName("Shower").isPresent()) {
                room1.getFacilitiesList().add(facilitiesRepository.findByFacilitiesName("Shower").get());
            }
            if (facilitiesRepository.findByFacilitiesName("Phone").isPresent()) {
                room1.getFacilitiesList().add(facilitiesRepository.findByFacilitiesName("Phone").get());
            }
            if (facilitiesRepository.findByFacilitiesName("Water Boiler").isPresent()) {
                room1.getFacilitiesList().add(facilitiesRepository.findByFacilitiesName("Water Boiler").get());
            }


            String url = "img/rooms/Molvenolakehotelinthemorning1.webp";
            String url2 = "img/rooms/Molvenolakeresortintheevening1.webp";
            String url3 = "img/rooms/Restaurantlook.webp";
            String url4 = "img/rooms/Molvenolakeresortintheevening2.webp";
            String url5 = "img/rooms/Molvenolatehotelinthemorning2.webp";
            String url6 = "img/rooms/Restaurantlook2.webp";
            String logoUrl = "img/pages/CompanyLogo.jpg";
            ImageRequestDto image1 = new ImageRequestDto();
            image1.setUrl(url);
            image1.setName(url);
            ImageRequestDto image2 = new ImageRequestDto();
            image2.setUrl(url2);
            image2.setName(url2);
            ImageRequestDto image3 = new ImageRequestDto();
            image3.setUrl(url3);
            image3.setName(url3);
            ImageRequestDto image4 = new ImageRequestDto();
            image4.setUrl(url4);
            image4.setName(url4);
            ImageRequestDto image5 = new ImageRequestDto();
            image5.setUrl(url5);
            image5.setName(url5);
            ImageRequestDto image6 = new ImageRequestDto();
            image6.setUrl(url6);
            image6.setName(url6);
            ImageRequestDto logo = new ImageRequestDto();
            logo.setUrl(logoUrl);
            logo.setName(logoUrl);

            imageService.saveImage2(image1, "ROOM");
            imageService.saveImage2(image2, "ROOM");
            imageService.saveImage2(image3, "ROOM");
            imageService.saveImage2(image4, "ROOM");
            imageService.saveImage2(image5, "ROOM");
            imageService.saveImage2(image6, "ROOM");


            List<Image> imageList = imageRepository.findAll();


            room1.setImageList(imageList);
            room2.setImageList(imageList);
            room3.setImageList(imageList);
            room4.setImageList(imageList);
            room5.setImageList(imageList);
            room6.setImageList(imageList);
            room7.setImageList(imageList);
            room8.setImageList(imageList);
            room9.setImageList(imageList);
            room10.setImageList(imageList);
            room11.setImageList(imageList);
            room12.setImageList(imageList);
            room13.setImageList(imageList);
            room14.setImageList(imageList);
            room15.setImageList(imageList);
            room16.setImageList(imageList);
            room17.setImageList(imageList);
            room18.setImageList(imageList);
            room19.setImageList(imageList);
            room20.setImageList(imageList);
            room21.setImageList(imageList);
            room22.setImageList(imageList);
            room23.setImageList(imageList);
            room24.setImageList(imageList);
            room25.setImageList(imageList);
            room26.setImageList(imageList);
            room27.setImageList(imageList);
            room28.setImageList(imageList);
            room29.setImageList(imageList);
            room30.setImageList(imageList);
            room31.setImageList(imageList);
            room32.setImageList(imageList);
            room33.setImageList(imageList);
            room34.setImageList(imageList);
            room35.setImageList(imageList);
            room36.setImageList(imageList);
            room37.setImageList(imageList);
            room38.setImageList(imageList);
            room39.setImageList(imageList);
            room40.setImageList(imageList);
            room41.setImageList(imageList);
            room42.setImageList(imageList);
            room43.setImageList(imageList);
            room44.setImageList(imageList);
            room45.setImageList(imageList);
            room46.setImageList(imageList);
            room47.setImageList(imageList);
            room48.setImageList(imageList);
            room49.setImageList(imageList);
            room50.setImageList(imageList);
            room51.setImageList(imageList);
            room52.setImageList(imageList);
            room53.setImageList(imageList);
            room54.setImageList(imageList);
            room55.setImageList(imageList);
            room56.setImageList(imageList);
            room57.setImageList(imageList);
            room58.setImageList(imageList);
            room59.setImageList(imageList);
            room60.setImageList(imageList);
            room61.setImageList(imageList);
            room62.setImageList(imageList);
            room63.setImageList(imageList);
            room64.setImageList(imageList);
            room65.setImageList(imageList);
            room66.setImageList(imageList);
            room67.setImageList(imageList);
            room68.setImageList(imageList);
            room69.setImageList(imageList);
            room70.setImageList(imageList);
            room71.setImageList(imageList);
            room72.setImageList(imageList);
            room73.setImageList(imageList);
            room74.setImageList(imageList);
            room75.setImageList(imageList);
            room76.setImageList(imageList);
            room77.setImageList(imageList);
            room78.setImageList(imageList);






            if (roomTypeRepository.findByRoomTypeName("STANDARD").isPresent()) {
                room2.setRoomType(roomTypeRepository.findByRoomTypeName("STANDARD").get());
                room2.setDescription("Basic room with essential amenities, perfect for a short stay.");
                room2.setDescriptionZh("基础房间，提供基本设施，非常适合短期入住。");
                room21.setRoomType(roomTypeRepository.findByRoomTypeName("STANDARD").get());
                room21.setDescription("Basic room with essential amenities, perfect for a short stay.");
                room21.setDescriptionZh("基础房间，提供基本设施，非常适合短期入住。");

                room40.setRoomType(roomTypeRepository.findByRoomTypeName("STANDARD").get());
                room40.setDescription("Basic room with essential amenities, perfect for a short stay.");
                room40.setDescriptionZh("基础房间，提供基本设施，非常适合短期入住。");

                room59.setRoomType(roomTypeRepository.findByRoomTypeName("STANDARD").get());
                room59.setDescription("Basic room with essential amenities, perfect for a short stay.");
                room59.setDescriptionZh("基础房间，提供基本设施，非常适合短期入住。");


            }

            if (bedTypeRepository.findByBedTypeName("SINGLE BED").isPresent()) {
                room2.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room21.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room40.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());
                room59.getBedTypeList().add(bedTypeRepository.findByBedTypeName("SINGLE BED").get());

            }
            List<Facilities> standartFacilitiesList = new ArrayList<>();
            List<Facilities> double_bFacilities = new ArrayList<>();
            List<Facilities> deluxeFacilities = new ArrayList<>();
            List<Facilities> pentHouseFacilities1 = new ArrayList<>();
            List<Facilities> pentHouseFacilities2 = new ArrayList<>();


            if (facilitiesRepository.findByFacilitiesName("Mini Fridge").isPresent()) {
                double_bFacilities.add(facilitiesRepository.findByFacilitiesName("Mini Fridge").get());
            }
            if (facilitiesRepository.findByFacilitiesName("Mini Fridge").isPresent()) {
                deluxeFacilities.add(facilitiesRepository.findByFacilitiesName("Mini Fridge").get());
            }
            if (facilitiesRepository.findByFacilitiesName("Mini Fridge").isPresent()) {
                pentHouseFacilities1.add(facilitiesRepository.findByFacilitiesName("Mini Fridge").get());
                pentHouseFacilities2.add(facilitiesRepository.findByFacilitiesName("Mini Fridge").get());
            }
            if (facilitiesRepository.findByFacilitiesName("Mini Fridge").isPresent()) {
                standartFacilitiesList.add(facilitiesRepository.findByFacilitiesName("Mini Fridge").get());
            }

            if (facilitiesRepository.findByFacilitiesName("TV").isPresent()) {
                standartFacilitiesList.add(facilitiesRepository.findByFacilitiesName("TV").get());
            }
            if (facilitiesRepository.findByFacilitiesName("TV").isPresent()) {
                double_bFacilities.add(facilitiesRepository.findByFacilitiesName("TV").get());
            }
            if (facilitiesRepository.findByFacilitiesName("TV").isPresent()) {
                deluxeFacilities.add(facilitiesRepository.findByFacilitiesName("TV").get());
            }
            if (facilitiesRepository.findByFacilitiesName("TV").isPresent()) {
                pentHouseFacilities1.add(facilitiesRepository.findByFacilitiesName("TV").get());

                pentHouseFacilities2.add(facilitiesRepository.findByFacilitiesName("TV").get());
            }
            if (facilitiesRepository.findByFacilitiesName("Airco").isPresent()) {
                standartFacilitiesList.add(facilitiesRepository.findByFacilitiesName("Airco").get());
            }
            if (facilitiesRepository.findByFacilitiesName("Airco").isPresent()) {
                double_bFacilities.add(facilitiesRepository.findByFacilitiesName("Airco").get());
            }
            if (facilitiesRepository.findByFacilitiesName("Airco").isPresent()) {
                deluxeFacilities.add(facilitiesRepository.findByFacilitiesName("Airco").get());
            }
            if (facilitiesRepository.findByFacilitiesName("Airco").isPresent()) {
                pentHouseFacilities1.add(facilitiesRepository.findByFacilitiesName("Airco").get());
                pentHouseFacilities2.add(facilitiesRepository.findByFacilitiesName("Airco").get());
            }
            if (facilitiesRepository.findByFacilitiesName("WIFI").isPresent()) {
                standartFacilitiesList.add(facilitiesRepository.findByFacilitiesName("WIFI").get());
            }

            if (facilitiesRepository.findByFacilitiesName("WIFI").isPresent()) {
                deluxeFacilities.add(facilitiesRepository.findByFacilitiesName("WIFI").get());
            }
            if (facilitiesRepository.findByFacilitiesName("WIFI").isPresent()) {
                double_bFacilities.add(facilitiesRepository.findByFacilitiesName("WIFI").get());
            }
            if (facilitiesRepository.findByFacilitiesName("WIFI").isPresent()) {
                pentHouseFacilities1.add(facilitiesRepository.findByFacilitiesName("WIFI").get());
                pentHouseFacilities2.add(facilitiesRepository.findByFacilitiesName("WIFI").get());
            }
            if (facilitiesRepository.findByFacilitiesName("Toilet").isPresent()) {
                standartFacilitiesList.add(facilitiesRepository.findByFacilitiesName("Toilet").get());
            }
            if (facilitiesRepository.findByFacilitiesName("Toilet").isPresent()) {
                double_bFacilities.add(facilitiesRepository.findByFacilitiesName("Toilet").get());
            }
            if (facilitiesRepository.findByFacilitiesName("Toilet").isPresent()) {
                deluxeFacilities.add(facilitiesRepository.findByFacilitiesName("Toilet").get());
            }
            if (facilitiesRepository.findByFacilitiesName("Toilet").isPresent()) {
                pentHouseFacilities1.add(facilitiesRepository.findByFacilitiesName("Toilet").get());
                pentHouseFacilities2.add(facilitiesRepository.findByFacilitiesName("Toilet").get());
            }
            if (facilitiesRepository.findByFacilitiesName("Shower").isPresent()) {
                standartFacilitiesList.add(facilitiesRepository.findByFacilitiesName("Shower").get());
            }

            if (facilitiesRepository.findByFacilitiesName("Shower").isPresent()) {
                double_bFacilities.add(facilitiesRepository.findByFacilitiesName("Shower").get());
            }
            if (facilitiesRepository.findByFacilitiesName("Shower").isPresent()) {
                deluxeFacilities.add(facilitiesRepository.findByFacilitiesName("Shower").get());
            }
            if (facilitiesRepository.findByFacilitiesName("Shower").isPresent()) {
                pentHouseFacilities1.add(facilitiesRepository.findByFacilitiesName("Shower").get());
                pentHouseFacilities2.add(facilitiesRepository.findByFacilitiesName("Shower").get());
            }
            if (facilitiesRepository.findByFacilitiesName("Phone").isPresent()) {
                standartFacilitiesList.add(facilitiesRepository.findByFacilitiesName("Phone").get());
            }
            if (facilitiesRepository.findByFacilitiesName("Phone").isPresent()) {
                double_bFacilities.add(facilitiesRepository.findByFacilitiesName("Phone").get());
            }
            if (facilitiesRepository.findByFacilitiesName("Phone").isPresent()) {
                deluxeFacilities.add(facilitiesRepository.findByFacilitiesName("Phone").get());
            }
            if (facilitiesRepository.findByFacilitiesName("Phone").isPresent()) {
                pentHouseFacilities1.add(facilitiesRepository.findByFacilitiesName("Phone").get());
                pentHouseFacilities2.add(facilitiesRepository.findByFacilitiesName("Phone").get());
            }
            if (facilitiesRepository.findByFacilitiesName("Water Boiler").isPresent()) {
                standartFacilitiesList.add(facilitiesRepository.findByFacilitiesName("Water Boiler").get());
            }
            if (facilitiesRepository.findByFacilitiesName("Water Boiler").isPresent()) {
                double_bFacilities.add(facilitiesRepository.findByFacilitiesName("Water Boiler").get());
            }
            if (facilitiesRepository.findByFacilitiesName("Water Boiler").isPresent()) {
                deluxeFacilities.add(facilitiesRepository.findByFacilitiesName("Water Boiler").get());
            }
            if (facilitiesRepository.findByFacilitiesName("Water Boiler").isPresent()) {
                pentHouseFacilities1.add(facilitiesRepository.findByFacilitiesName("Water Boiler").get());
                pentHouseFacilities2.add(facilitiesRepository.findByFacilitiesName("Water Boiler").get());
            }

            if (facilitiesRepository.findByFacilitiesName("Coffee Machine").isPresent()) {
                double_bFacilities.add(facilitiesRepository.findByFacilitiesName("Coffee Machine").get());
            }
            if (facilitiesRepository.findByFacilitiesName("Coffee Machine").isPresent()) {
                deluxeFacilities.add(facilitiesRepository.findByFacilitiesName("Coffee Machine").get());
            }
            if (facilitiesRepository.findByFacilitiesName("Coffee Machine").isPresent()) {
                pentHouseFacilities1.add(facilitiesRepository.findByFacilitiesName("Coffee Machine").get());
                pentHouseFacilities2.add(facilitiesRepository.findByFacilitiesName("Coffee Machine").get());
            }

            if (facilitiesRepository.findByFacilitiesName("Bathtub").isPresent()) {
                deluxeFacilities.add(facilitiesRepository.findByFacilitiesName("Bathtub").get());
            }
            if (facilitiesRepository.findByFacilitiesName("Bathtub").isPresent()) {
                pentHouseFacilities1.add(facilitiesRepository.findByFacilitiesName("Bathtub").get());
                pentHouseFacilities2.add(facilitiesRepository.findByFacilitiesName("Bathtub").get());
            }

            if (facilitiesRepository.findByFacilitiesName("Swimming Pool").isPresent()) {
                pentHouseFacilities1.add(facilitiesRepository.findByFacilitiesName("Swimming Pool").get());


            }
            if (facilitiesRepository.findByFacilitiesName("Infinity Swimming Pool").isPresent()) {

                pentHouseFacilities2.add(facilitiesRepository.findByFacilitiesName("Infinity Swimming Pool").get());
            }

            if (facilitiesRepository.findByFacilitiesName("Sauna").isPresent()) {
                pentHouseFacilities1.add(facilitiesRepository.findByFacilitiesName("Sauna").get());
                pentHouseFacilities2.add(facilitiesRepository.findByFacilitiesName("Sauna").get());

            }

            if (facilitiesRepository.findByFacilitiesName("Terrace").isPresent()) {
                pentHouseFacilities1.add(facilitiesRepository.findByFacilitiesName("Terrace").get());
                pentHouseFacilities2.add(facilitiesRepository.findByFacilitiesName("Terrace").get());
            }


            if (facilitiesRepository.findByFacilitiesName("Parasols").isPresent()) {
                pentHouseFacilities1.add(facilitiesRepository.findByFacilitiesName("Parasols").get());
                pentHouseFacilities2.add(facilitiesRepository.findByFacilitiesName("Parasols").get());
            }

            if (facilitiesRepository.findByFacilitiesName("Terrace Chairs and Tables").isPresent()) {
                pentHouseFacilities1.add(facilitiesRepository.findByFacilitiesName("Terrace Chairs and Tables").get());
                pentHouseFacilities2.add(facilitiesRepository.findByFacilitiesName("Terrace Chairs and Tables").get());
            }
            if (facilitiesRepository.findByFacilitiesName("Kitchen").isPresent()) {
                pentHouseFacilities2.add(facilitiesRepository.findByFacilitiesName("Kitchen").get());
            }

            room1.setFacilitiesList(standartFacilitiesList);
            room2.setFacilitiesList(standartFacilitiesList);
            room3.setFacilitiesList(standartFacilitiesList);
            room4.setFacilitiesList(standartFacilitiesList);
            room5.setFacilitiesList(standartFacilitiesList);
            room6.setFacilitiesList(standartFacilitiesList);
            room7.setFacilitiesList(standartFacilitiesList);
            room13.setFacilitiesList(standartFacilitiesList);
            room14.setFacilitiesList(standartFacilitiesList);
            room20.setFacilitiesList(standartFacilitiesList);
            room21.setFacilitiesList(standartFacilitiesList);
            room22.setFacilitiesList(standartFacilitiesList);
            room23.setFacilitiesList(standartFacilitiesList);
            room24.setFacilitiesList(standartFacilitiesList);
            room25.setFacilitiesList(standartFacilitiesList);
            room26.setFacilitiesList(standartFacilitiesList);
            room32.setFacilitiesList(standartFacilitiesList);
            room33.setFacilitiesList(standartFacilitiesList);

            room39.setFacilitiesList(standartFacilitiesList);
            room40.setFacilitiesList(standartFacilitiesList);
            room41.setFacilitiesList(standartFacilitiesList);
            room42.setFacilitiesList(standartFacilitiesList);
            room43.setFacilitiesList(standartFacilitiesList);
            room44.setFacilitiesList(standartFacilitiesList);
            room45.setFacilitiesList(standartFacilitiesList);
            room58.setFacilitiesList(standartFacilitiesList);
            room59.setFacilitiesList(standartFacilitiesList);
            room60.setFacilitiesList(standartFacilitiesList);
            room61.setFacilitiesList(standartFacilitiesList);
            room62.setFacilitiesList(standartFacilitiesList);
            room63.setFacilitiesList(standartFacilitiesList);
            room64.setFacilitiesList(standartFacilitiesList);
            room67.setFacilitiesList(standartFacilitiesList);


            room8.setFacilitiesList(double_bFacilities);
            room9.setFacilitiesList(double_bFacilities);
            room10.setFacilitiesList(deluxeFacilities);
            room11.setFacilitiesList(deluxeFacilities);
            room12.setFacilitiesList(deluxeFacilities);
            room15.setFacilitiesList(deluxeFacilities);
            room16.setFacilitiesList(deluxeFacilities);
            room17.setFacilitiesList(deluxeFacilities);
            room18.setFacilitiesList(deluxeFacilities);
            room19.setFacilitiesList(deluxeFacilities);
            room27.setFacilitiesList(double_bFacilities);
            room26.setFacilitiesList(double_bFacilities);
            room28.setFacilitiesList(double_bFacilities);
            room29.setFacilitiesList(deluxeFacilities);
            room30.setFacilitiesList(deluxeFacilities);
            room31.setFacilitiesList(deluxeFacilities);
            room34.setFacilitiesList(deluxeFacilities);
            room35.setFacilitiesList(deluxeFacilities);
            room36.setFacilitiesList(deluxeFacilities);
            room37.setFacilitiesList(deluxeFacilities);
            room38.setFacilitiesList(deluxeFacilities);

            room46.setFacilitiesList(double_bFacilities);
            room47.setFacilitiesList(double_bFacilities);
            room48.setFacilitiesList(deluxeFacilities);
            room49.setFacilitiesList(deluxeFacilities);
            room50.setFacilitiesList(deluxeFacilities);
            room65.setFacilitiesList(double_bFacilities);
            room64.setFacilitiesList(double_bFacilities);
            room66.setFacilitiesList(deluxeFacilities);
            room68.setFacilitiesList(deluxeFacilities);
            room69.setFacilitiesList(deluxeFacilities);


            room51.setFacilitiesList(standartFacilitiesList);
            room52.setFacilitiesList(standartFacilitiesList);
            room70.setFacilitiesList(standartFacilitiesList);
            room71.setFacilitiesList(standartFacilitiesList);

            room53.setFacilitiesList(deluxeFacilities);
            room54.setFacilitiesList(deluxeFacilities);
            room55.setFacilitiesList(deluxeFacilities);
            room56.setFacilitiesList(deluxeFacilities);
            room57.setFacilitiesList(deluxeFacilities);
            room72.setFacilitiesList(deluxeFacilities);
            room73.setFacilitiesList(deluxeFacilities);
            room74.setFacilitiesList(deluxeFacilities);
            room75.setFacilitiesList(pentHouseFacilities1);
            room76.setFacilitiesList(pentHouseFacilities1);
            room77.setFacilitiesList(pentHouseFacilities2);
            room78.setFacilitiesList(pentHouseFacilities2);

            roomRepository.save(room1);
            roomRepository.save(room2);
            roomRepository.save(room3);
            roomRepository.save(room4);
            roomRepository.save(room5);
            roomRepository.save(room6);
            roomRepository.save(room7);
            roomRepository.save(room8);
            roomRepository.save(room9);
            roomRepository.save(room10);
            roomRepository.save(room11);
            roomRepository.save(room12);
            roomRepository.save(room13);
            roomRepository.save(room14);
            roomRepository.save(room15);
            roomRepository.save(room16);
            roomRepository.save(room17);
            roomRepository.save(room18);
            roomRepository.save(room19);
            roomRepository.save(room20);
            roomRepository.save(room21);
            roomRepository.save(room22);
            roomRepository.save(room23);
            roomRepository.save(room24);
            roomRepository.save(room25);
            roomRepository.save(room26);
            roomRepository.save(room27);
            roomRepository.save(room28);
            roomRepository.save(room29);
            roomRepository.save(room30);
            roomRepository.save(room31);
            roomRepository.save(room32);
            roomRepository.save(room33);
            roomRepository.save(room34);
            roomRepository.save(room35);
            roomRepository.save(room36);
            roomRepository.save(room37);
            roomRepository.save(room38);

            roomRepository.save(room39);
            roomRepository.save(room40);
            roomRepository.save(room41);
            roomRepository.save(room42);
            roomRepository.save(room43);
            roomRepository.save(room44);
            roomRepository.save(room45);
            roomRepository.save(room46);
            roomRepository.save(room47);
            roomRepository.save(room48);
            roomRepository.save(room49);
            roomRepository.save(room50);
            roomRepository.save(room51);
            roomRepository.save(room52);
            roomRepository.save(room53);
            roomRepository.save(room54);
            roomRepository.save(room55);
            roomRepository.save(room56);
            roomRepository.save(room57);
            roomRepository.save(room58);
            roomRepository.save(room59);
            roomRepository.save(room60);
            roomRepository.save(room61);
            roomRepository.save(room62);
            roomRepository.save(room63);
            roomRepository.save(room64);
            roomRepository.save(room65);
            roomRepository.save(room66);
            roomRepository.save(room67);
            roomRepository.save(room68);
            roomRepository.save(room69);
            roomRepository.save(room70);
            roomRepository.save(room71);
            roomRepository.save(room72);
            roomRepository.save(room73);
            roomRepository.save(room74);
            roomRepository.save(room75);
            roomRepository.save(room76);
            roomRepository.save(room77);
            roomRepository.save(room78);

            Carousel carousel1 = new Carousel();
            carousel1.setImage(imageService.saveImage2(image1, "PAGE"));
            carousel1.setCarouselTitle("Molveno Lake Resort");
            carousel1.setCarouselTitleZh("莫尔韦诺湖度假村");
            carousel1.setDescription("Discover the beauty of Molveno Lake at our exquisite resort.");
            carousel1.setDescriptionZh("探索我们精致度假村中的莫尔韦诺湖之美。");

            Carousel carousel2 = new Carousel();
            carousel2.setImage(imageService.saveImage2(image2, "PAGE"));
            carousel2.setCarouselTitle("Molveno Lake Resort");
            carousel2.setCarouselTitleZh("莫尔韦诺湖度假村");
            carousel2.setDescription("Discover the beauty of Molveno Lake at our exquisite resort.");
            carousel2.setDescriptionZh("探索我们精致度假村中的莫尔韦诺湖之美。");
            imageService.saveImage2(image2, "ROOM");

            Carousel carousel3 = new Carousel();
            carousel3.setImage(imageService.saveImage2(image3, "PAGE"));
            carousel3.setCarouselTitle("Molveno Lake Resort");
            carousel3.setCarouselTitleZh("莫尔韦诺湖度假村");
            carousel3.setDescription("Discover the beauty of Molveno Lake at our exquisite resort.");
            carousel3.setDescriptionZh("探索我们精致度假村中的莫尔韦诺湖之美。");


            Carousel carousel4 = new Carousel();
            carousel4.setImage(imageService.saveImage2(image4, "PAGE"));
            carousel4.setCarouselTitle("Molveno Lake Resort");
            carousel4.setDescription("Discover the beauty of Molveno Lake at our exquisite resort.");
            carousel4.setDescriptionZh("探索我们精致度假村中的莫尔韦诺湖之美。");
            carousel4.setCarouselTitleZh("莫尔韦诺湖度假村");

            Carousel carousel5 = new Carousel();
            carousel5.setImage(imageService.saveImage2(image5, "PAGE"));
            carousel5.setCarouselTitle("Molveno Lake Resort");
            carousel5.setDescription("Discover the beauty of Molveno Lake at our exquisite resort.");
            carousel5.setDescriptionZh("探索我们精致度假村中的莫尔韦诺湖之美。");
            carousel5.setCarouselTitleZh("莫尔韦诺湖度假村");

            Carousel carousel6 = new Carousel();
            carousel6.setImage(imageService.saveImage2(image6, "PAGE"));
            carousel6.setCarouselTitle("Molveno Lake Resort");
            carousel6.setDescription("Discover the beauty of Molveno Lake at our exquisite resort.");
            carousel6.setDescriptionZh("探索我们精致度假村中的莫尔韦诺湖之美。");
            carousel6.setCarouselTitleZh("莫尔韦诺湖度假村");

            carouselRepository.save(carousel6);
            carouselRepository.save(carousel5);
            carouselRepository.save(carousel4);
            carouselRepository.save(carousel3);
            carouselRepository.save(carousel2);
            carouselRepository.save(carousel1);

            AboutUs aboutUs = new AboutUs();
            aboutUs.setDescription("The hotel chain was established in 1982, with a vision to create world-class hotels that offered exceptional service and luxurious accommodations. After decades of success, the chain has expanded to various parts of the world, including China.\n" +
                    "The latest addition to the chain is the hotel being built in the beautiful Wuxing District of Huzhou, Zhejiang, China. The founder, Ms. Wang, had a passion for creating a hotel that would showcase the natural beauty of the area, particularly the nearby Taihu Lake.\n" +
                    "Construction of the hotel began in 2021, and it is set to open its doors in August of this year. From the outset, the hotel was designed to be a luxurious and sustainable retreat that combined modern amenities with traditional Chinese architecture. The property features a mix of guest rooms, suites, and penthouses, all carefully positioned to offer breathtaking views of the surrounding mountains and waterways.\n" +
                    "Despite being a new addition to the region, the hotel is already generating excitement among travelers and industry professionals alike. Its commitment to sustainability and eco-friendly initiatives have earned it recognition from various environmental organizations.\n" +
                    "The staff is dedicated to providing exceptional service to guests, ensuring that every stay is unforgettable. With its opening just around the corner, the hotel is poised to become one of the premier destinations in China for travelers seeking a luxurious and sustainable retreat.\n");
            aboutUs.setDescriptionZh("酒店连锁店成立于1982年，愿景是创建世界一流的酒店，提供卓越的服务和豪华的住宿。经过几十年的成功运营，这家连锁店已扩展到世界各地，包括中国。连锁店的最新成员是正在中国浙江湖州美丽的吴兴区建设中的酒店。创始人王女士热衷于打造一家展示该地区自然美景的酒店，特别是附近的太湖。酒店于2021年开始建设，计划于今年8月开业。从一开始，酒店就被设计为一个豪华且可持续的度假胜地，结合了现代设施和传统中式建筑。酒店拥有各种客房、套房和顶层公寓，所有房间都精心布置，以提供周围山脉和水道的壮丽景色。尽管是该地区的新成员，但这家酒店已经在旅行者和业内专业人士中引起了兴奋。其对可持续性和环保措施的承诺已获得多家环保组织的认可。员工致力于为客人提供卓越的服务，确保每次入住都是难忘的。随着开业临近，这家酒店有望成为中国最顶级的豪华和可持续度假胜地之一。");
            aboutUs.setImage(imageService.saveImage2(logo,"PAGE"));


            aboutUsRepository.save(aboutUs);


            HotelAmenities accommodation  = new HotelAmenities();
            accommodation .setHotelAmenitiesName("Accommodation Services");
            accommodation .setHotelAmenitiesNameZh("住宿服务");
            accommodation .setHotelAmenitiesContent("This includes a range of guest rooms, suites, and villas with luxurious amenities such as comfortable bedding, high-speed internet access, flat-screen TVs, and minibars.");
            accommodation .setHotelAmenitiesContentZh("其中包括一系列配备豪华设施的客房、套房和别墅，例如舒适的床上用品、高速互联网接入、平面电视和迷你吧。");
            accommodation.setIcon("fas fa-bed fa-3x");
            hotelAmenitiesRepository.save(accommodation);

            HotelAmenities dining  = new HotelAmenities();
            dining.setHotelAmenitiesName("Dining Services");
            dining.setHotelAmenitiesNameZh("餐饮服务");
            dining.setHotelAmenitiesContent("The hotel may have several dining options, including a fine dining restaurant, a casual cafe, a bar, and 24-hour room service.");
            dining.setHotelAmenitiesContentZh("酒店拥有多种餐饮选择，包括高级餐厅、休闲咖啡厅、酒吧和 24 小时客房服务。");
            dining.setIcon("fas fa-utensils fa-3x");
            hotelAmenitiesRepository.save(dining);

            HotelAmenities spa = new HotelAmenities();
            spa.setHotelAmenitiesName("Wellness and Spa Services");
            spa.setHotelAmenitiesNameZh("健康和水疗服务");
            spa.setHotelAmenitiesContent("This includes a range of guest rooms, suites, and villas with luxurious amenities such as comfortable bedding, high-speed internet access, flat-screen TVs, and minibars.");
            spa.setHotelAmenitiesContentZh("其中包括一系列配备豪华设施的客房、套房和别墅，例如舒适的床上用品、高速互联网接入、平面电视和迷你吧。");
            spa.setIcon("fas fa-spa fa-3x");
            hotelAmenitiesRepository.save(spa);

            HotelAmenities business = new HotelAmenities();
            business.setHotelAmenitiesName("Business Services");
            business.setHotelAmenitiesNameZh("商业服务");
            business.setHotelAmenitiesContent("This includes meeting rooms, conference facilities, and business centers with access to high-speed internet, audiovisual equipment, and secretarial services.");
            business.setHotelAmenitiesContentZh("其中包括会议室、会议设施以及配备高速互联网、视听设备和秘书服务的商务中心。");
            business.setIcon("fas fa-briefcase fa-3x");
            hotelAmenitiesRepository.save(business);

            HotelAmenities transportation  = new HotelAmenities();
            transportation.setHotelAmenitiesName("Transportation Services");
            transportation.setHotelAmenitiesNameZh("交通服务");
            transportation.setHotelAmenitiesContent("The hotel may offer airport transfers, shuttle services, and car rentals to make it easy for guests to get around.");
            transportation.setHotelAmenitiesContentZh("酒店可提供机场接送、班车服务和汽车租赁服务，方便客人游览各处。");
            transportation.setIcon("fas fa-shuttle-van fa-3x");
            hotelAmenitiesRepository.save(transportation);

            HotelAmenities concierge  = new HotelAmenities();
            concierge.setHotelAmenitiesName("Concierge Services");
            concierge.setHotelAmenitiesNameZh("礼宾服务");
            concierge.setHotelAmenitiesContent("The concierge team can help guests with arranging tours, sightseeing, restaurant reservations, and other activities to make their stay more enjoyable.");
            concierge.setHotelAmenitiesContentZh("礼宾团队可以帮助客人安排旅游、观光、餐厅预订和其他活动，让他们的住宿更加愉快。");
            concierge.setIcon("fas fa-concierge-bell fa-3x");
            hotelAmenitiesRepository.save(concierge);

            HotelAmenities housekeeping  = new HotelAmenities();
            housekeeping.setHotelAmenitiesName("Housekeeping Services");
            housekeeping.setHotelAmenitiesNameZh("家政服务");
            housekeeping.setHotelAmenitiesContent("The hotel provides daily housekeeping services, including turndown service and laundry service.");
            housekeeping.setHotelAmenitiesContentZh("酒店提供每日清洁服务，包括夜床服务和洗衣服务。");
            housekeeping.setIcon("fas fa-broom fa-3x");
            hotelAmenitiesRepository.save(housekeeping);

            HotelAmenities entertainment = new HotelAmenities();
            entertainment.setHotelAmenitiesName("Entertainment and Leisure Services");
            entertainment.setHotelAmenitiesNameZh("娱乐休闲服务");
            entertainment.setHotelAmenitiesContent("The hotel may offer a range of leisure activities, including a swimming pool, golf course, tennis court, and other outdoor activities.");
            entertainment.setHotelAmenitiesContentZh("酒店可提供一系列休闲活动，包括游泳池、高尔夫球场、网球场和其他户外活动。");
            entertainment.setIcon("fas fa-swimmer fa-3x");
            hotelAmenitiesRepository.save(entertainment);

            HotelAmenities security = new HotelAmenities();
            security.setHotelAmenitiesName("Security Services");
            security.setHotelAmenitiesNameZh("安全服务");
            security.setHotelAmenitiesContent("The hotel has 24-hour security and surveillance systems to ensure the safety of guests and their belongings.");
            security.setHotelAmenitiesContentZh("酒店设有24小时保安及监控系统，确保客人及其财物的安全。");
            security.setIcon("fas fa-shield-alt fa-3x");
            hotelAmenitiesRepository.save(security);

            HotelAmenities sustainability = new HotelAmenities();
            sustainability.setHotelAmenitiesName("Sustainability Services");
            sustainability.setHotelAmenitiesNameZh("可持续发展服务");
            sustainability.setHotelAmenitiesContent("The hotel may have eco-friendly practices such as reducing waste, using renewable energy sources, and implementing sustainable practices throughout the property.");
            sustainability.setHotelAmenitiesContentZh("酒店可能采取环保措施，例如减少浪费、使用可再生能源以及在整个酒店实施可持续做法。");
            sustainability.setIcon("fas fa-leaf fa-3x");
            hotelAmenitiesRepository.save(sustainability);

            HotelAmenities waterSports = new HotelAmenities();
            waterSports.setHotelAmenitiesName("Water Sports and Boat/Canoe Rental Services");
            waterSports.setHotelAmenitiesNameZh("水上运动和独木舟租赁服务");
            waterSports.setHotelAmenitiesContent("The hotel may offer a range of water-based activities such as kayaking, canoeing, paddleboarding, and motorized water sports. The hotel may also provide boat and canoe rentals for guests who want to explore the nearby lake or river.");
            waterSports.setHotelAmenitiesContentZh("酒店可提供一系列水上活动，如皮划艇、独木舟、桨板运动和机动水上运动。酒店还可以为想要探索附近湖泊或河流的客人提供船只和独木舟租赁服务。");
            waterSports.setIcon("fas fa-water fa-3x");
            hotelAmenitiesRepository.save(waterSports);

            Comments comment1 = new Comments();
            comment1.setAuthor("John Doe");
            comment1.setCommentContent("Great service and very comfortable rooms!");
            comment1.setCommentContentZh("优质的服务和非常舒适的房间！");
            commentsRepository.save(comment1);

            Comments comment2 = new Comments();
            comment2.setAuthor("Jane Smith");
            comment2.setCommentContent("Loved the food and the ambiance!");
            comment2.setCommentContentZh("喜欢这里的食物和氛围！");
            commentsRepository.save(comment2);

            Comments comment3 = new Comments();
            comment3.setAuthor("Emily Brown");
            comment3.setCommentContent("The spa was amazing. Highly recommend!");
            comment3.setCommentContentZh("水疗中心很棒。强烈推荐！");
            commentsRepository.save(comment3);

//            ReservationRequest reservationRequest = new ReservationRequest();
//            reservationRequest.setRoomNumber("B111");
//            reservationRequest.setCancellationFee(123);
//            reservationRequest.setFilteredSmoking(false);
//            reservationRequest.setFilteredDisabled(true);
//            reservationRequest.setCountOfAdult(2);
//            reservationRequest.setCountOfChild(1);
//            reservationRequest.setTotalPrice(123);
//            List<String > facilities = new ArrayList<>();
//            facilities.add("Mini Fridge");
//            reservationRequest.setFilteredFacilitiesList(facilities);
//            List<String> bedTypeList = new ArrayList<>();
//            bedTypeList.add("SINGLE");
//            reservationRequest.setFilteredBedTypeList(bedTypeList);
//
//
//            UserRequestDto userRequestDto = new UserRequestDto();
//            userRequestDto.setEmail("admin@admin.com");
//
//            List<UserRequestDto> userRequestDtoList = new ArrayList<>();
//            userRequestDtoList.add(userRequestDto);
//
//            reservationRequest.setGuestList(userRequestDtoList);
//
//            reservationRequest.setCheck_in(LocalDateTime.of(2024,6,12,14,00));
//            reservationRequest.setCheck_out(LocalDateTime.of(2024,6,13,14,00));
//            reservationRequest.setTransactionDateTime(LocalDateTime.now());
//
//            reservationService.create(reservationRequest);
//            reservationRequest.setRoomNumber("B112");
//            reservationService.create(reservationRequest);
//            reservationRequest.setRoomNumber("B113");
//            reservationService.create(reservationRequest);
//
//
//
            ReservationRequest reservation1 = createReservation1();
            ReservationRequest reservation2 = createReservation2();
            ReservationRequest reservation3 = createReservation3();
            ReservationRequest reservation4 = createReservation4();
            reservationService.create(reservation1);
            reservationService.create(reservation2);
            reservationService.create(reservation3);
            reservationService.create(reservation4);
        }


    }

    private static ReservationRequest createReservation1() {
        // Adres bilgisi
        Address address1 = new Address();
        address1.setCountry("United States");
        address1.setCity("New York");
        address1.setStreet("5th Avenue");
        address1.setZipCode("10001");
        address1.setHouseNumber("789");

        // Kullanıcı bilgisi
        UserRequestDto user1 = new UserRequestDto();
        user1.setUserName("John");
        user1.setUserSurName("Doe");
        user1.setPassword("password123");
        user1.setEmail("john.doe@example.com");
        user1.setAddress(address1);
        user1.setAdult(true);
        user1.setPhoneNumber("1234567890");


        // ReservationRequest
        ReservationRequest reservation1 = new ReservationRequest();

        reservation1.setRoomNumber("B111");

        reservation1.setTotalPrice(1540);
        reservation1.setGuestList(Arrays.asList(user1));
        reservation1.setFilteredFacilitiesList(Arrays.asList("Mini Fridge", "TV", "Airco", "WIFI", "Toilet", "Shower", "Phone", "Water Boiler"));
        reservation1.setFilteredBedTypeList(new ArrayList<>());
        reservation1.setFilteredDisabled(false);
        reservation1.setFilteredSmoking(false);
        reservation1.setCheck_in(LocalDateTime.of(2024, 8, 29, 14, 0));
        reservation1.setCheck_out(LocalDateTime.of(2024, 9, 5, 10, 0));
        reservation1.setTransactionDateTime(LocalDateTime.now());

        return reservation1;
    }

    private static ReservationRequest createReservation2() {
        // Adres bilgisi
        Address address2 = new Address();
        address2.setCountry("United Kingdom");
        address2.setCity("London");
        address2.setStreet("Baker Street");
        address2.setZipCode("NW1 6XE");
        address2.setHouseNumber("221B");

        // Kullanıcı bilgisi
        UserRequestDto user2 = new UserRequestDto();

        user2.setUserName("Sherlock");
        user2.setUserSurName("Holmes");
        user2.setPassword("elementary");
        user2.setEmail("sherlock.holmes@example.com");
        user2.setAddress(address2);
        user2.setAdult(true);
        user2.setPhoneNumber("0987654321");


        // ReservationRequest
        ReservationRequest reservation2 = new ReservationRequest();

        reservation2.setRoomNumber("B112");

        reservation2.setTotalPrice(800.00);
        reservation2.setGuestList(Arrays.asList(user2));
        reservation2.setFilteredFacilitiesList(Arrays.asList("Mini Fridge", "TV", "Airco", "WIFI", "Toilet", "Shower", "Phone", "Water Boiler"));
        reservation2.setFilteredBedTypeList(new ArrayList<>());
        reservation2.setFilteredDisabled(false);
        reservation2.setFilteredSmoking(false);
        reservation2.setCheck_in(LocalDateTime.of(2024, 8, 19, 14, 0));
        reservation2.setCheck_out(LocalDateTime.of(2024, 8, 26, 10, 0));
        reservation2.setTransactionDateTime(LocalDateTime.now());

        return reservation2;
    }

    private static ReservationRequest createReservation3() {
        // Adres bilgisi
        Address address3 = new Address();
        address3.setCountry("Japan");
        address3.setCity("Tokyo");
        address3.setStreet("Shibuya Crossing");
        address3.setZipCode("150-0002");
        address3.setHouseNumber("1-2-3");

        // Kullanıcı bilgisi
        UserRequestDto user3 = new UserRequestDto();
        user3.setUserName("Akira");
        user3.setUserSurName("Kurosawa");
        user3.setPassword("samurai");
        user3.setEmail("akira.kurosawa@example.com");
        user3.setAddress(address3);
        user3.setAdult(true);
        user3.setPhoneNumber("1122334455");


        // ReservationRequest
        ReservationRequest reservation3 = new ReservationRequest();
        reservation3.setRoomNumber("B111");
        reservation3.setTotalPrice(880);
        reservation3.setGuestList(Arrays.asList(user3));
        reservation3.setCountOfAdult(1);
        reservation3.setFilteredFacilitiesList(Arrays.asList("Mini Fridge", "TV", "Airco", "WIFI", "Toilet", "Shower", "Phone", "Water Boiler"));
        reservation3.setFilteredBedTypeList(new ArrayList<>());
        reservation3.setFilteredDisabled(false);
        reservation3.setFilteredSmoking(false);
        reservation3.setCheck_in(LocalDateTime.of(2024, 8, 9, 14, 0));
        reservation3.setCheck_out(LocalDateTime.of(2024, 8, 13, 10, 0));
        reservation3.setTransactionDateTime(LocalDateTime.now());

        return reservation3;
    }

    private static ReservationRequest createReservation4() {
        // Adres bilgisi
        Address address3 = new Address();
        address3.setCountry("Japan");
        address3.setCity("Tokyo");
        address3.setStreet("Shibuya Crossing");
        address3.setZipCode("150-0002");
        address3.setHouseNumber("1-2-3");

        // Kullanıcı bilgisi
        UserRequestDto user3 = new UserRequestDto();
        user3.setUserName("Akira");
        user3.setUserSurName("Kurosawa");
        user3.setPassword("samurai");
        user3.setEmail("akira.kurosawa@example.com");
        user3.setAddress(address3);
        user3.setAdult(true);
        user3.setPhoneNumber("1122334455");


        // ReservationRequest
        ReservationRequest reservation3 = new ReservationRequest();
        reservation3.setRoomNumber("B115");
        reservation3.setTotalPrice(880);
        reservation3.setGuestList(Arrays.asList(user3));
        reservation3.setCountOfAdult(1);
        reservation3.setFilteredFacilitiesList(Arrays.asList("Mini Fridge", "TV", "Airco", "WIFI", "Toilet", "Shower", "Phone", "Water Boiler"));
        reservation3.setFilteredBedTypeList(new ArrayList<>());
        reservation3.setFilteredDisabled(false);
        reservation3.setFilteredSmoking(false);
        reservation3.setCheck_in(LocalDateTime.of(2024, 8, 7, 14, 0));
        reservation3.setCheck_out(LocalDateTime.of(2024, 8, 13, 10, 0));
        reservation3.setTransactionDateTime(LocalDateTime.now());

        return reservation3;
    }

}
