var userTable;

function init() {
    console.log('inside init');
    $("#change-password-button").click(function() {

        clearForm(); // Formu temizle
        $('#change-password-modal').modal('show');
    });

    //  Change password form submit
    $("#change-password-form").on('submit', function(event) {
        event.preventDefault(); // Formun varsayılan submit davranışını engelle
        var user = userTable.row($('.selected')).data();
        if (!user) {
            if(language=="zh"){
                                 alert('首先选择用户');
                                   }else{
                                  alert("Select user first");
                                 }
            return;
        }

        var password = $("#change-password-newPassword").val();
        var passwordHelp = $("#passwordHelpChange");

        if (!validatePassword(password)) { //  Şifre doğrulaması
            passwordHelp.text("The password is invalid. It must contain at least 8 characters, one uppercase letter, one special character, and one number.").css("color", "red");
            return;
        }

        var userData = {
            userId: user.userId,
            email: user.email,
            newPassword: password
        };

        var userJson = JSON.stringify(userData);
        console.log(userJson);

        $.ajax({
            url: "/api/users/changePassword",
            type: "post",
            data: userJson,
            contentType: "application/json; charset=utf-8",
            dataType: "text",
            success: function(response) {
                console.log(response);

                if(language=="zh"){
                                     alert('密码修改成功！');
                                       }else{
                                       alert("Password changed successfully!");

                                     }
                $("#change-password-form")[0].reset();
                $('#change-password-modal').modal('hide');
            },
            error: function(error) {
                console.log('Error: ' + error);
                if(language=="zh"){
                   alert('更改密码时出错。');
                     }else{

                    alert("Error changing password.");
                   }

            }
        });
    });

    //  Şifre alanı için doğrulama mesajı ekleyin (Change Password formu)
    $("#change-password-newPassword").on('input', function() {
        var password = $(this).val();
        var passwordHelp = $("#passwordHelpChange");

        if (validatePassword(password)) {
            passwordHelp.text("The password is valid.").css("color", "green");
        } else {
            passwordHelp.text("The password is invalid. It must contain at least 8 characters, one uppercase letter, one special character, and one number.").css("color", "red");
        }
    });

    //  Toggle password visibility for change password
    $("#toggle-password-change").click(function() {
        var passwordField = $("#change-password-newPassword");
        var passwordFieldType = passwordField.attr('type');
        if (passwordFieldType === 'password') {
            passwordField.attr('type', 'text');
            $("#toggle-password-change").removeClass("fa-eye").addClass("fa-eye-slash");
        } else {
            passwordField.attr('type', 'password');
            $("#toggle-password-change").removeClass("fa-eye-slash").addClass("fa-eye");
        }
    });

    //  Şifre alanı için doğrulama mesajı ekleyin
    $("#password").on('input', function() {
        var password = $(this).val();
        var passwordHelp = $("#passwordHelp");

        if (validatePassword(password)) {
            passwordHelp.text("The password is valid.").css("color", "green");
        } else {
            passwordHelp.text("The password is invalid. It must contain at least 8 characters, one uppercase letter, one special character, and one number.").css("color", "red");
        }
    });

    //  Email alanı için doğrulama mesajı ekleyin
    $("#email").on('blur', function() {
        var email = $(this).val();
        var emailHelp = $("#emailHelp");

        if (email) {
            checkEmailExists(email).then(isExists => {
                if (isExists) {
                    emailHelp.text("The email address is already registered.").css("color", "red");
                } else {
                    emailHelp.text("The email address is available.").css("color", "green");
                }
            }).catch(err => {
                console.error(err);
                emailHelp.text("Email kontrolü sırasında bir hata oluştu.").css("color", "red");
            });
        }
    });

    //=====================================================PasswordShow================================================

    $("#toggle-password, #toggle-password-text").click(function() {
        var passwordField = $("#password");
        var passwordFieldType = passwordField.attr('type');
        if (passwordFieldType === 'password') {
            passwordField.attr('type', 'text');
            $("#toggle-password").removeClass("fa-eye").addClass("fa-eye-slash");
            $("#toggle-password-text").text("Hide");
        } else {
            passwordField.attr('type', 'password');
            $("#toggle-password").removeClass("fa-eye-slash").addClass("fa-eye");
            $("#toggle-password-text").text("Show");
        }
    });

    //=============================================================NewUserButton=====================================
    $("#new-user-button").click(function() {
        console.log("Inside click of new-user-button");
        $('#password-group').show(); // Password alanını göster
        $('#password').attr('required', true); // Password alanını zorunlu yap
        $('#password').val(''); // Şifre alanını temizle
        $('#passwordHelp').text(''); // Şifre yardım metnini temizle
        clearForm(); // Formu temizle
        $('#user-modal').modal('show');
    });

    //=============================================================EDITUSERBUTTON=====================================
    $("#edit-user-button").click(function() {
        console.log("Inside click of edit-user-button");
        // Hide all elements related to the password field
        $('#password').attr('type', 'hidden');
        $('#toggle-password').hide(); // Hide the toggle password icon
        $('#toggle-password-text').hide(); // Hide the toggle password text
        $('label[for="password"]').css('display', 'none');

        if (userTable.row($('.selected')).data() == undefined) {
            if(language=="zh"){
                                             alert('首先选择用户');
                                               }else{
                                              alert("Select user first");
                                             }
        } else {
            var user = userTable.row($('.selected')).data();
            console.log("User address: ", user.address);

            $("#userId").val(user.userId);
            $("#userName").val(user.userName);
            $("#userSurName").val(user.userSurName);
            $("#password").val(user.password).hide();
            $("#email").val(user.email);
            if (user.address) {
                var addressParts = user.address.split(',');
                $("#addressStreet").val(addressParts[0] ? addressParts[0].trim() : '');
                $("#houseNumber").val(addressParts[1] ? addressParts[1].replace('No:', '').trim() : '');
                $("#addressCity").val(addressParts[2] ? addressParts[2].trim() : '');
                $("#addressCountry").val(addressParts[3] ? addressParts[3].trim() : '');
                $("#addressZip").val(addressParts[4] ? addressParts[4].trim() : '');
            } else {
                // Adres tanımsızsa, alanları temizleyin
                $("#addressStreet").val('');
                $("#houseNumber").val('');
                $("#addressCity").val('');
                $("#addressCountry").val('');
                $("#addressZip").val('');
            }

            $('#email').attr('required', false); // Email alanını zorunlu olmaktan çıkar

            $("#phoneNumber").val(user.phoneNumber);
            $("#userRoleType").val(user.userRoleType);

            // Kullanıcı ID'si 1 ise, userRoleType alanını devre dışı bırak
            if (user.userId == 1) {
                $("#userRoleType").attr('disabled', true);
            } else {
                $("#userRoleType").attr('disabled', false);
            }

            $('#user-modal').modal('show');
        }
    });

    //=============================================================DELETEUSERBUTTON=====================================
    $("#delete-user-button").click(function() {
        console.log("Inside click of delete-user-button");
        if (userTable.row($('.selected')).data() == undefined) {
            if(language=="zh"){
                                             alert('首先选择用户');
                                               }else{
                                              alert("Select user first");
                                             }
        } else {

            var selectedRow = userTable.row($('.selected'));
            var rowData = selectedRow.data();

            var userId = rowData.userId; // Kullanıcı ID'sinin data'dan nasıl alındığını kontrol edin, genellikle rowData.id gibi olur
            if (userId == 1) {

                if(language=="zh"){
                                                 alert('第一个用户无法删除。');
                                                   }else{
                                                  alert('The first user cannot be deleted.');

                                                 }
            } else {
                $('#user-delete-modal').modal('show');
            }
        }
    });

    $("#delete-user-confirm-button").click(function() {
        console.log("Inside click of delete-user-confirm-button");
        deleteUser();
        $('#user-delete-modal').modal('hide');
    });

    //=============================================================Submit=====================================

     $("#user-form").on('submit', function(event) {
            const userId = $("#userId").val();
            // Email alanını form gönderiminde doğrulama
            var email = $("#email").val();
            var emailHelp = $("#emailHelp");
            var isEmailExists = false;
            checkEmailExists(email).then(isExists => {
                if (isExists) {
                    emailHelp.text("Email address is already registered.").css("color", "red");
                    isEmailExists = true;
                } else {
                    emailHelp.text("Email address is available.").css("color", "green");
                    isEmailExists = false;
                    alert("User Saved");
                }
            }).catch(err => {
                console.error(err);
                emailHelp.text("An error occurred during email verification.").css("color", "red");
                isEmailExists = true;
            });

            if (isEmailExists) {
                return false; // Email zaten kayıtlıysa formu göndermeyi durdur
            }

            if (userId > 0) {
                editUser();
            } else {
                createUser();
            }
            $('#user-modal').modal('hide');
        });

        initUserTable();
    }


//=============================================================Func.InitUserTable=====================================
function initUserTable() {
    console.log('inside initUserTable');

    columns = [
        {
            "data": "userId",
            "visible": false
        },
        {
            "data": "userName"
        },
        {
            "data": "userSurName"
        },
        {
            "data": "email"
        },
        {
            "data": "address"
        },
        {
            "data": "phoneNumber"
        },
        {
            "data": "userRoleType"
        }

        //TODO adresi parcalara ayirmalisin
    ];

    userTable = $("#user-table1").DataTable({
        "order": [
            [0, "asc"]
        ],
        "columns": columns,
        "language": {
            "url": languageUrl
        }
    });

    $("#user-table1 tbody").on('click', 'tr', function() {
        console.log("Clicking on row");
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            userTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    });

    getUserData();
}

//=============================================================Fun.CreateUser=====================================
function createUser() {
    console.log('inside createUser');

    var password = $("#password").val();
    if (!validatePassword(password)) { //  Şifre doğrulaması
      if(language=="zh"){
                       alert('密码无效。它必须至少包含 8 个字符、1 个大写字母、1 个特殊字符和 1 个数字。');
                         }else{
                        alert("Password is invalid. It must contain at least 8 characters, one uppercase letter, one special character and one number.");

                       }
        return;
    }

    var address = {
        street: $("#addressStreet").val(),
        houseNumber: $("#houseNumber").val(),
        city: $("#addressCity").val(),
        country: $("#addressCountry").val(),
        zipCode: $("#addressZip").val()
    };

    var userData = {
        userId: $("#userId").val(),
        userName: $("#userName").val(),
        userSurName: $("#userSurName").val(),
        password: $("#password").val(),
        email: $("#email").val(),
        phoneNumber: $("#phoneNumber").val(),
        userRoleType: $("#userRoleType").val(),
        address: address
    };

    var userJson = JSON.stringify(userData);
    console.log(userJson);

    $.ajax({
        url: "/api/users/save",
        type: "post",
        data: userJson,
        contentType: "application/json; charset=utf-8",
        dataType: "text",
        success: function(user) {
        if(language=="zh"){
         alert('用户保存成功');
           }else{

          alert("User saved successfully");
         }

            console.log(user);
            clearForm();
            getUserData();
        },
        error: function(error) {
            console.log('Error: ' + error);
        }
    });
}

//=============================================================Func.EditUser=====================================
function editUser() {
    console.log('inside editUser');

    var password = $("#password").val();
    console.log(password);
    if (password) { //  Şifre alanı boş değilse kontrol et
        if (!validatePassword(password)) {
        if(language=="zh"){
                 alert('密码无效。它必须至少包含 8 个字符、1 个大写字母、1 个特殊字符和 1 个数字。');
                   }else{
                  alert("Password is invalid. It must contain at least 8 characters, one uppercase letter, one special character and one number.");

                 }

            return;
        }
    }

    var address = {
        street: $("#addressStreet").val(),
        houseNumber: $("#houseNumber").val(),
        city: $("#addressCity").val(),
        country: $("#addressCountry").val(),
        zipCode: $("#addressZip").val()
    };

    var userData = {
        userId: $("#userId").val(),
        userName: $("#userName").val(),
        userSurName: $("#userSurName").val(),
        password: $("#password").val(),
        email: $("#email").val(),
        phoneNumber: $("#phoneNumber").val(),
        userRoleType: $("#userRoleType").val(),
        address: address
    };

    var userJson = JSON.stringify(userData);
    console.log(userJson);

    $.ajax({
        url: "/api/users/edit/" + userData.email,
        type: "put",
        data: userJson,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function(user) {
            console.log(user);
            clearForm();
            getUserData();
        },
        error: function(error) {
            console.log('Error: ' + error);
        }
    });
}

//=============================================================Func.ValidPassword=====================================
function validatePassword(password) {
    const minLength = 8;
    const hasUpperCase = /[A-Z]/.test(password);
    const hasSpecialChar = /[!@#$%^&*(),.?":{}|<>]/.test(password);
    const hasNumber = /\d/.test(password); // Sayı kontrolü

    if (password.length >= minLength && hasUpperCase && hasSpecialChar && hasNumber) {
        return true;
    } else {
        return false;
    }
}

//=============================================================/Func.ValidPassword=====================================

//=============================================================Func.GetUserData=====================================
function getUserData() {
    console.log('inside getUserData');

    $.ajax({
        url: "/api/users",
        type: "get",
        dataType: "json",
        success: function(users) {
            console.log("Received users data: ", users); // JSON verisini konsolda kontrol edin
            users.forEach(user => {
                if (user.address) {
                    user.address = `${user.address.street || ''}, ${user.address.houseNumber || ''}, ${user.address.city || ''}, ${user.address.country || ''}, ${user.address.zipCode || ''}`;
                } else {
                    user.address = '';
                }
            });
            userTable.clear();
            userTable.rows.add(users);
            userTable.columns.adjust().draw();
        },
        error: function(error) {
            console.log('Error: ' + error);
        }
    });
}

function deleteUser() {
    if (userTable.row($('.selected')).data() == undefined) {
                if(language=="zh"){
                alert('首先选择用户');
                  }else{
                 alert("Select user first");
                }


    } else {
        var user = userTable.row($('.selected')).data();
        $.ajax({
            url: '/api/users/' + user.userId,
            type: "delete",
            dataType: "text",
            success: function(message) {
                console.log(message);
                getUserData();
            },
            fail: function(error) {
                console.log('Error: ' + error);
            }
        });
    }
}

//=============================================================Func.ClearForm=====================================
function clearForm() {
    $("#userId").val('');
    $("#userName").val('');
    $("#userSurName").val('');
    $("#password").val('');
    $("#email").val('');
    $("#addressStreet").val('');
    $("#addressCity").val('');
    $("#addressCountry").val('');
    $("#addressZip").val('');
    $("#phoneNumber").val('');
    $("#userRoleType").val('');
    $('#password').attr('required', true); // Password alanını zorunlu yap
    $('#password-group').show(); // Password alanını göster
}

// Şifre doğrulama fonksiyonu
function validatePassword(password) {
    const minLength = 8;
    const hasUpperCase = /[A-Z]/.test(password);
    const hasSpecialChar = /[!@#$%^&*(),.?":{}|<>]/.test(password);
    const hasNumber = /\d/.test(password); // Sayı kontrolü

    return password.length >= minLength && hasUpperCase && hasSpecialChar && hasNumber;
}

// Email doğrulama fonksiyonu
function checkEmailExists(email) {
    return new Promise((resolve, reject) => {
        $.ajax({
            url: "/api/users/check-email",
            type: "post",
            data: JSON.stringify({
                email: email
            }),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(response) {
                resolve(response.exists);
            },
            error: function(error) {
                reject(error);
            }
        });
    });
}
