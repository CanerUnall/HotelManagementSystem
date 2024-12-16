// home.js

$(document).ready(function () {

    $.ajax({
        url: '/api/hotelInformation',
        method: 'GET',
        async: false,
        success: function(data) {
            console.log(data);

            $('#hotelNameFooter').text(data.hotelName);
            $('#streetFooter').text(data.address.street);
            $('#cityFooter').text(data.address.city);
            $('#countryFooter').text(data.address.country);
            $('#emailFooter').attr('href', 'mailto:' + data.email).text(data.email);
            $('#phoneFooter').attr('href', 'tel:' + data.phoneNumber).text(data.phoneNumber);

            $('#facebook').attr('href', data.facebook);
            $('#twitter').attr('href', data.twitter);
            $('#instagram').attr('href', data.instagram);


            $('#map').attr('src', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2771.2906938258433!2d10.965460315380253!3d46.14032897911447!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4779315b2a7d57d7%3A0x5090e8c0ff74b8a2!2s' + encodeURIComponent(data.address.street) + '%2C%20' + encodeURIComponent(data.address.city) + '%2C%20' + encodeURIComponent(data.address.country) + '!5e0!3m2!1sen!2sus!4v1690906356884!5m2!1sen!2sus');
        },
        error: function(error) {
            console.log('Error:', error);
        }
    });

    // Şifre alanı için doğrulama mesajı ekleyin
    $("#registerPassword").on('input', function() {
        var password = $(this).val();
        var passwordHelp = $("#passwordHelpRegister");

        if (validatePassword(password)) {
            passwordHelp.text("The password is valid.").css("color", "green");
        } else {
            passwordHelp.text("The password is invalid. It must contain at least 8 characters, one uppercase letter, one special character, and one number.").css("color", "red");
        }
    });

    // Email alanı için doğrulama mesajı ekleyin
    $("#registerEmail").on('blur', function() {
        var email = $(this).val();
        var emailHelp = $("#emailHelpRegister");

        if (email) {
            checkEmailExists(email).then(isExists => {
                if (isExists) {
                    emailHelp.text("The email address is already registered.").css("color", "red");
                } else {
                    emailHelp.text("The email address is available.").css("color", "green");
                }
            }).catch(err => {
                console.error(err);
                emailHelp.text("An error occurred while checking the email.").css("color", "red");
            });
        }
    });

    // Toggle password visibility
    $("#toggle-password-register").click(function() {
        var passwordField = $("#registerPassword");
        var passwordFieldType = passwordField.attr('type');
        if (passwordFieldType === 'password') {
            passwordField.attr('type', 'text');
            $("#toggle-password-register").removeClass("fa-eye").addClass("fa-eye-slash");
        } else {
            passwordField.attr('type', 'password');
            $("#toggle-password-register").removeClass("fa-eye-slash").addClass("fa-eye");
        }
    });

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


   $('.dropdown-toggle').hover(
          function() {
              $(this).next('.dropdown-menu').addClass('show');
          },
          function() {
              $(this).next('.dropdown-menu').removeClass('show');
          }
   );

   $('.dropdown-menu').hover(
       function() {
           $(this).addClass('show');
       },
       function() {
           $(this).removeClass('show');
       }
   );

    var language = getLanguageFromCookie();
    console.log("Loading About Us data for language: " + language);
    loadAboutUs(language);
    loadCarousel(language);
    loadTestimonials(language);
    loadServices(language);


  // Formun submit olayını yakalayın
    $('#contactFormId').on('submit', function(event) {
      event.preventDefault(); // Sayfanın yeniden yüklenmesini engelleyin

      // Form verilerini toplayın
      var contactMessage = {
        author: $('#name').val(),
        email: $('#emailForMessages').val(),
        message: $('#message').val()

      };

      // AJAX isteğini gönderin
      $.ajax({
        url: '/api/contactMessages',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(contactMessage),
        success: function(response) {
          // Başarı durumunda kullanıcıya mesaj gösterin
          alert('Your message has been sent successfully!');
        $('#name').val("");
        $('#emailForMessages').val("");
        $('#message').val("");
          // Formu sıfırlayın
          $('form')[0].reset();
        },
        error: function(xhr, status, error) {
          // Hata durumunda kullanıcıya mesaj gösterin
          alert('There was an error sending your message. Please try again later.');
        }
      });
    });
});

function loadCarousel(language) {
    $.ajax({
      url: '/api/carousel/home', // Backend API endpoint URL
      method: 'GET',
      data: { lang: language }, // Dil bilgisini gönderme
      success: function(data) {
        var indicatorsHtml = '';
        var itemsHtml = '';

        data.forEach(function(item, index) {
          // Indicators
          indicatorsHtml += `
            <li data-target="#carouselExampleIndicators" data-slide-to="${index}" ${index === 0 ? 'class="active"' : ''}></li>
          `;

          // Carousel items
          itemsHtml += `
            <div class="carousel-item ${index === 0 ? 'active' : ''}">
              <img class="d-block w-100" src="${item.image.imageUrl}" alt="${item.title}">
              <div class="carousel-caption d-none d-md-block">
                <h5>${item.title}</h5>
                <p>${item.description}</p>
              </div>
            </div>
          `;
        });

        $('.carousel-indicators').html(indicatorsHtml);
        $('.carousel-inner').html(itemsHtml);
      },
      error: function(error) {
        console.error('Error loading carousel data:', error);
      }
    });
}

function loadAboutUs(language) {
        var language = getLanguageFromCookie();
        $.ajax({
            url: '/api/aboutUs/1',
            method: 'GET',
            data: { lang: language }, // Dil parametresini ekleyin
            success: function (aboutUsData) {
                if (aboutUsData && aboutUsData.description && aboutUsData.image && aboutUsData.image.imageUrl) {
                    $('#aboutUsContent').text(aboutUsData.description);
                    $('#aboutUs_img').attr('src', aboutUsData.image.imageUrl);
                } else {
                    console.error('Invalid data structure:', aboutUsData);
                }
            },
            error: function (error) {
                console.error('Error loading About Us data:', error);
            }
        });
}


function loadServices(language) {
  $.ajax({
    url: '/api/hotelAmenities/home',
    method: 'GET',
    data: { lang: language },
    success: function (data) {
      var servicesHtml = '';

      data.forEach(function (service) {
        servicesHtml += `
          <div class="service-box">
            <i class="fas fa-${service.icon} fa-3x"></i>
            <h4>${service.hotelAmenitiesName}</h4>
            <p>${service.hotelAmenitiesContent}</p>
          </div>
        `;
      });

      // Container'ı HTML ile doldur
      var $container = $('#services_container');
      $container.html('<div class="services-carousel-inner">' + servicesHtml + '</div>');

      // Kaydırma animasyonu
      var $inner = $container.find('.services-carousel-inner');
      var itemWidth = $container.find('.service-box').outerWidth(true);
      var visibleItems = Math.floor($container.width() / itemWidth); // Görünen eleman sayısını hesapla

      function slide() {
        $inner.animate({
          marginLeft: -itemWidth
        }, 1000, function() {
          $inner.css('margin-left', 0).find('.service-box:first').appendTo($inner);
        });
      }

      // Her 5 saniyede bir kaydır
      setInterval(slide, 5000);
    },
    error: function (error) {
      console.error('Error loading services data:', error);
    }
  });
}



var currentSlide = 0;
var testimonialsGroups = [];

function loadTestimonials(language) {
  $.ajax({
    url: '/api/comments/home',
    method: 'GET',
    data: { lang: language }, // Dil parametresini ekleyin
    success: function (data) {
      // Yorumları 3'erli gruplara ayır
      for (var i = 0; i < data.length; i += 3) {
        testimonialsGroups.push(data.slice(i, i + 3));
      }
      showTestimonials(currentSlide);
      setInterval(nextSlide, 5000); // Her 5 saniyede bir sonraki slayta geç
    },
    error: function (error) {
      console.error('Error loading testimonials data:', error);
    }
  });
}

function showTestimonials(slideIndex) {
  var testimonialsHtml = '';

  testimonialsGroups[slideIndex].forEach(function (testimonial) {
    testimonialsHtml += `
      <div class="col-md-4 mb-4">
        <div class="testimonial-box p-4">
          <p>"${testimonial.commentContent || 'No comment provided'}"</p>
          <footer>${testimonial.author || 'Anonymous'}</footer>
        </div>
      </div>
    `;
  });

  $('#testimonials_container').html(testimonialsHtml);
}

function nextSlide() {
  currentSlide = (currentSlide + 1) % testimonialsGroups.length;
  showTestimonials(currentSlide);
}

