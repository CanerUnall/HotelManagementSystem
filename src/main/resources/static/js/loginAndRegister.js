//function init(){
//    createUser();
//    loginUser();
//
//}
//
//
//function createUser() {
//    // Kullanıcıdan alınan verileri JavaScript nesnesine atama
//    var userData = {
//        userName: $("#registerName").val(),
//        userSurname: $("#registerSurname").val(),
//        email: $("#registerEmail").val(),
//        password: $("#registerPassword").val(),
//        phoneNumber: $("#registerPhoneNumber").val()
//    };
//
//    // JavaScript nesnesini JSON formatına dönüştürme
//    var userJson = JSON.stringify(userData);
//
//    // AJAX isteği yapma
//    $.ajax({
//        url: "/api/user/save",
//        type: "POST",
//        data: userJson,
//        contentType: "application/json",
//        dataType: "json",
//        success: function(response) {
//            // Başarılı işlem durumunda
//            console.log("Registration successful:", response);
//            alert("Registration successful!");
//            $('#loginAndRegisterModal').modal('hide');
//        },
//        error: function(error) {
//            // Hata durumunda
//            console.error("Error registering user:", error);
//            alert("Error registering user. Please try again.");
//        }
//    });
//}
//
//function loginUser() {
//    // Kullanıcıdan alınan verileri JavaScript nesnesine atama
//    var loginData = {
//        email: $("#loginEmail").val(),
//        password: $("#loginPassword").val()
//    };
//
//    // JavaScript nesnesini JSON formatına dönüştürme
//    var loginJson = JSON.stringify(loginData);
//
//    // AJAX isteği yapma
//    $.ajax({
//        url: "/api/user/login",
//        type: "POST",
//        data: loginJson,
//        contentType: "application/json",
//        dataType: "json",
//        success: function(response) {
//            // Başarılı işlem durumunda
//            console.log("Giriş başarılı:", response);
//            alert("Giriş başarılı!");
//            // Başarılı giriş sonrası yönlendirme yapılabilir
//        },
//        error: function(error) {
//            // Hata durumunda
//            console.error("Giriş sırasında hata:", error);
//            alert("Giriş sırasında hata oluştu. Lütfen tekrar deneyin.");
//        }
//    });
//}
