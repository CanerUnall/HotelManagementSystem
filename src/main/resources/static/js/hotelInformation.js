$(document).ready(function() {
    // Load hotel information on page load
    $.ajax({
        url: '/api/hotelInformation',
        method: 'GET',
        success: function(data) {
        console.log(data);
            $('#hotelId').text(data.hotelId || 'N/A');
            $('#hotelName').text(data.hotelName || 'N/A');
            if (data.address) {
                $('#country').text(data.address.country || 'N/A');
                $('#city').text(data.address.city || 'N/A');
                $('#street').text(data.address.street || 'N/A');
                $('#houseNumber').text(data.address.houseNumber || 'N/A');
                $('#zipCode').text(data.address.zipCode || 'N/A');
            }
            $('#phoneNumber').text(data.phoneNumber || 'N/A');
            $('#email').text(data.email || 'N/A');
            $('#facebook').text(data.facebook || 'N/A');
            $('#twitter').text(data.twitter || 'N/A');
            $('#instagram').text(data.instagram || 'N/A');
            $('#restaurant').text(data.restaurant || 'N/A');
        }
    });

    // Populate form fields when the modal is shown
    $('#editModal').on('show.bs.modal', function (event) {
        $('#editHotelId').val($('#hotelId').text());
        $('#editHotelName').val($('#hotelName').text());
        $('#editCountry').val($('#country').text());
        $('#editCity').val($('#city').text());
        $('#editStreet').val($('#street').text());
        $('#editHouseNumber').val($('#houseNumber').text());
        $('#editZipCode').val($('#zipCode').text());
        $('#editPhoneNumber').val($('#phoneNumber').text());
        $('#editEmail').val($('#email').text());
        $('#editFacebook').val($('#facebook').text());
        $('#editTwitter').val($('#twitter').text());
        $('#editInstagram').val($('#instagram').text());
        $('#editRestaurant').val($('#restaurant').text());
    });

    // Handle form submission
    $('#hotelForm').submit(function(e) {
        e.preventDefault();

        var updatedData = {
            hotelId: $('#editHotelId').val(),
            hotelName: $('#editHotelName').val(),
            address: {
                country: $('#editCountry').val(),
                city: $('#editCity').val(),
                street: $('#editStreet').val(),
                houseNumber: $('#editHouseNumber').val(),
                zipCode: $('#editZipCode').val()
            },
            phoneNumber: $('#editPhoneNumber').val(),
            email: $('#editEmail').val(),
            facebook: $('#editFacebook').val(),
            twitter: $('#editTwitter').val(),
            instagram: $('#editInstagram').val(),
            restaurant: $('#editRestaurant').val()
        };

        $.ajax({
            url: '/api/hotelInformation',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(updatedData),
            success: function(response) {
                alert('Hotel information updated successfully!');

                // Refresh the page to show the updated information
                location.reload();
            },
            error: function(xhr, status, error) {
                alert('An error occurred while updating hotel information: ' + error);
            }
        });
    });
});
