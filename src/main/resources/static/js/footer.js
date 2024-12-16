$(document).ready(function() {
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
});