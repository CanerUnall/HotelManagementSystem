$(document).ready(function() {
    const form = $('#profileForm');
    let originalEmail;

    // GET request to fetch profile data
    $.ajax({
        url: '/api/users/profile',
        method: 'GET',
        contentType: 'application/json',
        success: function(data) {
            $('#userId').val(data.userId);
            $('#first_name').val(data.userName);
            $('#last_name').val(data.userSurName);
            $('#email').val(data.email);
            $('#addressStreet').val(data.address.street);
            $('#addressCity').val(data.address.city);
            $('#addressCountry').val(data.address.country);
            $('#addressZip').val(data.address.zipCode);
            $('#houseNumber').val(data.address.houseNumber);
            $('#phoneNumber').val(data.phoneNumber);
            originalEmail = data.email; // Save original email to compare later
        },
        error: function(xhr, status, error) {
            console.error('Error fetching profile data:', error);
        }
    });

    // PUT request to update profile data
    form.on('submit', function(event) {
        event.preventDefault();

        const updatedProfile = {
            userId: $('#userId').val(),
            userName: $('#first_name').val(),
            userSurName: $('#last_name').val(),
            email: $('#email').val(),password,
            password: $('#password').val(),
            address: {
                street: $('#addressStreet').val(),
                city: $('#addressCity').val(),
                country: $('#addressCountry').val(),
                zipCode: $('#addressZip').val(),
                houseNumber: $('#houseNumber').val()
            },
            phoneNumber: $('#phoneNumber').val()
        };

        // Validate form data
        if (!validateForm(updatedProfile)) {
            return;
        }

        // Check if email has changed
        if (updatedProfile.email !== originalEmail) {
            checkEmailExists(updatedProfile.email).then(isExists => {
                if (isExists) {
                    alert("The email address is already registered.");
                } else {
                    updateProfile(updatedProfile);
                }
            }).catch(err => {
                console.error(err);
                alert("An error occurred while checking the email.");
            });
        } else {
            updateProfile(updatedProfile);
        }
    });

    // Şifre alanı için doğrulama mesajı ekleyin
    $("#password").on('input', function() {
        var password = $(this).val();
        var passwordHelp = $("#passwordHelp");

        if (validatePassword(password)) {
            passwordHelp.text("The password is valid.").css("color", "green");
        } else {
            passwordHelp.text("The password is invalid. It must contain at least 8 characters, one uppercase letter, one special character, and one number.").css("color", "red");
        }
    });

    // Email alanı için doğrulama mesajı ekleyin
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

    // Toggle password visibility
    $("#toggle-password").click(function() {
        var passwordField = $("#password");
        var passwordFieldType = passwordField.attr('type');
        if (passwordFieldType === 'password') {
            passwordField.attr('type', 'text');
            $("#toggle-password").removeClass("fa-eye").addClass("fa-eye-slash");
        } else {
            passwordField.attr('type', 'password');
            $("#toggle-password").removeClass("fa-eye-slash").addClass("fa-eye");
        }
    });

    function validateForm(profile) {
        if (!profile.userName || !profile.userSurName || !profile.email) {
            alert('Please fill in all required fields.');
            return false;
        }
        return true;
    }

    function updateProfile(profile) {
        $.ajax({
            url: '/api/users/profile',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(profile),
            success: function(response) {
                alert('Profile updated successfully');
            },
            error: function(xhr, status, error) {
                let errorMsg = 'Failed to update profile';
                if (xhr.responseText) {
                    errorMsg += ': ' + xhr.responseText;
                }
                alert(errorMsg);
                console.error('Error updating profile:', xhr, status, error);
            }
        });
    }

    function validatePassword(password) {
        const minLength = 8;
        const hasUpperCase = /[A-Z]/.test(password);
        const hasSpecialChar = /[!@#$%^&*(),.?":{}|<>]/.test(password);
        const hasNumber = /\d/.test(password); // Sayı kontrolü

        return password.length >= minLength && hasUpperCase && hasSpecialChar && hasNumber;
    }

    function checkEmailExists(email) {
        return new Promise((resolve, reject) => {
            $.ajax({
                url: "/api/users/check-email",
                type: "post",
                data: JSON.stringify({ email: email }),
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
});
