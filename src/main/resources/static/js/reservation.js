// Declare a variable that holds a reference to the datatable,
// so that we can use it in every function.
var reservationTable;
var cancellationFeePercentage;

function init() {
    console.log('inside init');

$('#checkIn-reservation-button').on('click', function() {
    const selectedReservation = reservationTable.row($('.selected')).data();

    if (!selectedReservation) {
    alert($("#select-reservation-message").text());

        return;
    }

    // Canceled reservation check
    if (selectedReservation.canceled) {

    alert($("#checkin-canceled-reservation").text());

        return;
    }

    // Prevent Check-In before start date
    const now = new Date();
    const startDate = new Date(selectedReservation.startDate);

    if (now < startDate) {
    alert($("#checkin-before-startdate").text());

        return;
    }

    // Prevent double Check-In
    if (selectedReservation.checkedIn) {
    alert($("#already-checkedin").text());

        return;
    }


    checkInReservation(selectedReservation.reservationId);
});

$('#checkOut-reservation-button').on('click', function() {
    const selectedReservation = reservationTable.row($('.selected')).data();

    if (!selectedReservation) {
    alert($("#select-reservation-message").text());

        return;
    }

    // Canceled reservation check
    if (selectedReservation.canceled) {
        alert($("#checkout-canceled-reservation").text());

        return;
    }

    // Prevent Check-Out before start date
    const now = new Date();
    const startDate = new Date(selectedReservation.startDate);

    if (now < startDate) {
        alert($("#checkout-before-startdate").text());

        return;
    }

    // Prevent Check-Out without Check-In
    if (!selectedReservation.checkedIn) {
        alert($("#must-checkin-first").text());

        return;
    }

    // Prevent double Check-Out
    if (selectedReservation.checkedOut) {
    alert($("#already-checkedout").text());

        return;
    }


    checkOutReservation(selectedReservation.reservationId);
});

    $("#new-reservation-button").click(function () {
        console.log("Inside click of new-reservation-button");
        const url = 'http://localhost:8080/bookNow';
        window.location.href = url;
    });

    $('#edit-reservation-button').on('click', function() {
        const selectedReservation = reservationTable.row($('.selected')).data();
        if (selectedReservation) {
            const checkInDate = new Date(selectedReservation.startDate);
            const checkOutDate = new Date(selectedReservation.endDate);
            const currentDate = new Date();

            if (selectedReservation.canceled) {
                alert($("#reservation-has-been-canceled").text());
                return;
            }

            if (checkOutDate < currentDate) {
                alert($("#check-out-passed").text());
            } else if (selectedReservation.canceled) {
                alert($("#reservation-has-been-canceled").text());
            } else {
                const url = 'http://localhost:8080/bookNow?reservationId=' + selectedReservation.reservationId;
                window.location.href = url;
            }
        } else {
            alert($("#select-reservation-message").text());
        }
    });

    $("#cancel-reservation-button").click(function () {
        console.log("Inside click of cancel-reservation-button");

        var selectedData = reservationTable.row($('.selected')).data();

        if (selectedData == undefined) {
            alert($("#select-reservation-message").text());
        } else {
            var checkOutDate = new Date(selectedData.endDate);
            var now = new Date();

            if (checkOutDate <= now) {
                alert($("#check-out-passed").text());
                return;
            }

            if (selectedData.canceled) {
                alert($("#reservation-has-been-canceled").text());
                return;
            }

            var checkInDate = new Date(selectedData.startDate);
            var timeDifference = checkInDate - now;
            var daysDifference = Math.ceil(timeDifference / (1000 * 3600 * 24));

            var businessDays = 0;
            for (var i = 0; i < daysDifference; i++) {
                var tempDate = new Date(now);
                tempDate.setDate(tempDate.getDate() + i);
                var dayOfWeek = tempDate.getDay();
                if (dayOfWeek != 0 && dayOfWeek != 6) {
                    businessDays++;
                }
            }

            cancellationFeePercentage = 0;
            if (businessDays <= 10) {
                cancellationFeePercentage = 100;
            } else if (businessDays <= 20) {
                cancellationFeePercentage = 50;
            }

            var agreedPrice = selectedData.totalPrice;
            var cancellationFee = (agreedPrice * cancellationFeePercentage) / 100;

            if (cancellationFeePercentage > 0) {
                if (confirm($("#reservation-with-cancellation-fee").text() + cancellationFee)) {
                    cancelReservation();
                }
            } else {
                if (confirm($("#reservation-without-cancellation-fee").text())) {
                    $('#reservation-cancel-modal').modal('show');
                }
            }
        }
    });

//    $("#cancel-reservation-confirm-button").click(function () {
//        console.log("Inside click of cancel-reservation-confirm-button");
//        cancelReservation();
//        $('#reservation-cancel-modal').modal('hide');
//    });

    $("#delete-reservation-button").click(function () {
        console.log("Inside click of delete-reservation-button");

        if (reservationTable.row($('.selected')).data() == undefined) {
            alert($("#select-reservation-message").text());
        } else {
            $('#reservation-delete-modal').modal('show');
        }
    });

    $("#delete-reservation-confirm-button").click(function () {
        console.log("Inside click of delete-reservation-confirm-button");
        deleteReservation();
        $('#reservation-delete-modal').modal('hide');
    });


    initReservationTable();
}

function initReservationTable() {
    console.log('inside initReservationTable');

    var viewGuestListText = document.getElementById('viewGuestList').textContent;
    var columns = [
        { "data": "reservationId" },
        {
            "data": "roomNumber",
            "render": function(data, type, row) {
                return `<a href="#" class="room-number-link" data-room-number="${data}">${data}</a>`;
            }
        },
        { "data": "guestEmail" },
        {
                    "data": null,
                    "render": function(data, type, row) {
                        return `<a href="#" class="view-guests" data-reservation-id="${row.reservationId}">${viewGuestListText}</a>`;
                    }
                },
        { "data": "totalPrice" },
        {
            "data": "canceled",
            "render": function(data) {
                if (language === "zh") {
                    return data ? '是' : '否';
                } else{
                    return data ? 'Yes' : 'No';
                }
            }
        },
        { "data": "cancellationFee" },
        { "data": "startDate" },
        { "data": "endDate" },
        { "data": "creationTime" },
        { "data": "checkedIn",
         "render": function(data) {
                         if (language === "zh") {
                             return data ? '是' : '否';
                         } else{
                             return data ? 'Yes' : 'No';
                         }
                     }},
        { "data": "checkedInDateTime" },
        { "data": "checkedOut",
         "render": function(data) {
                         if (language === "zh") {
                             return data ? '是' : '否';
                         } else{
                             return data ? 'Yes' : 'No';
                         }
                     }},
        { "data": "checkedOutDateTime" }
    ];

    reservationTable = $("#reservation-table1").DataTable({
        "order": [[0, "desc"]],
        "columns": columns,
        "language": {
                    "url": languageUrl
                }
    });

    // Tıklama olaylarını burada tanımlıyoruz
    $("#reservation-table1 tbody").on('click', 'a.room-number-link', function(e) {
        e.preventDefault();  // Linkin varsayılan davranışını engelle
        const roomNumber = $(this).data('room-number');

        // Room detaylarını almak için AJAX çağrısı yap
        $.ajax({
            url: `/api/room/table/${roomNumber}`, // Oda bilgilerini getiren API endpoint
            type: "get",
            dataType: "json",
            success: function(roomDetails) {
                // Facilities ve Bed Types listeleme
                const facilitiesHtml = roomDetails.facilitiesList.map(facility => `<li>${facility}</li>`).join('');
                const bedTypesHtml = roomDetails.roomBedType.map(type => {
                    const count = roomDetails.roomBedType.filter(t => t === type).length;
                    return `<li>${type} : ${count}x</li>`;
                }).join('');

                // Image URLs listeleme
                const imageUrlsHtml = roomDetails.imageUrlList.map(url => `<img src="${url}" alt="Room Image" style="width:100px;height:100px;margin-right:5px;">`).join('');

                // Oda detaylarını modal'a yerleştir
                const roomDetailsHtml = `
                    <p><strong>Room ID:</strong> ${roomDetails.roomId}</p>
                    <p><strong>Room Number:</strong> ${roomDetails.roomNumber}</p>
                    <p><strong>Room Type:</strong> ${roomDetails.roomType}</p>
                    <p><strong>Floor:</strong> ${roomDetails.thFloor}</p>
                    <p><strong>Price:</strong> $${roomDetails.price}</p>
                    <p><strong>Count Of Adults:</strong> ${roomDetails.countOfAdult}</p>
                    <p><strong>Count Of Children:</strong> ${roomDetails.countOfChild}</p>
                    <p><strong>Disabled:</strong> ${roomDetails.disabled ? 'Yes' : 'No'}</p>
                    <p><strong>Cleaned:</strong> ${roomDetails.cleaned ? 'Yes' : 'No'}</p>
                    <p><strong>Smoking:</strong> ${roomDetails.smoking ? 'Yes' : 'No'}</p>
                    <p><strong>Description:</strong> ${roomDetails.description}</p>
                    <p><strong>Facilities:</strong></p>
                    <ul>${facilitiesHtml}</ul>
                    <p><strong>Bed Types:</strong></p>
                    <ul>${bedTypesHtml}</ul>
                    <p><strong>Images:</strong></p>
                    <div>${imageUrlsHtml}</div>
                `;

                $('#room-details-modal-title').text(`Room Details: ${roomDetails.roomNumber}`);
                $('#room-details-modal-body').html(roomDetailsHtml);

                // Modal'ı göster
                $('#room-details-modal').modal('show');
            },
            error: function(error) {
                console.error('Error retrieving room details:', error);
                alert("Failed to retrieve room details.");
            }
        });

    });

   $("#reservation-table1 tbody").on('click', 'tr', function () {
       console.log("Clicking on row");
       if ($(this).hasClass('selected')) {
           $(this).removeClass('selected');
       } else {
           reservationTable.$('tr.selected').removeClass('selected');
           $(this).addClass('selected');
       }
   });


    $("#reservation-table1 tbody").on('click', '.view-guests', function () {
        var reservationId = $(this).data('reservation-id');
        viewGuestNames(reservationId);
    });


    getReservationData();
}

function viewGuestNames(reservationId) {
    $.ajax({
        url: `/api/reservation/${reservationId}/guests`,
        type: "get",
        dataType: "json",
        success: function(guestList) {
            // Misafir listelerini temizleyelim
            $('#adult-names-list').empty();
            $('#child-names-list').empty();

            // Gelen misafir listesini işleyelim
            guestList.forEach(function(guest) {
                if (guest.adult) {
                    // Yetişkin misafirleri yetişkin listesine ekleyelim
                    $('#adult-names-list').append(`<li>${guest.userName} ${guest.userSurName}</li>`);
                } else {
                    // Çocuk misafirleri çocuk listesine ekleyelim
                    $('#child-names-list').append(`<li>${guest.userName} ${guest.userSurName}</li>`);
                }
            });

            // Modal'ı gösterelim
            $('#reservation-details-modal').modal('show');
        },
        error: function(error) {
            console.log('Error: ' + error);
        }
    });
}

function getReservationData() {
    console.log('inside getReservationData');

    $.ajax({
        url: "/api/reservation",
        type: "get",
        dataType: "json",
        success: function(reservations) {
            console.log(reservations);

            reservationTable.clear();
            reservationTable.rows.add(reservations);
            reservationTable.columns.adjust().draw();
        },
        error: function (error) {
            console.log('Error: ' + error);
        }
    });
}

function cancelReservation() {
    var reservation = reservationTable.row($('.selected')).data();
    var url = '/api/reservation/cancelById/' + reservation.reservationId + '/' + true + '/' + cancellationFeePercentage;
    console.log("AJAX URL: " + url);

    $.ajax({
        url: url,
        type: "get",
        dataType: "text",
        success: function(message) {
            console.log(message);

            getReservationData();
        },
        error: function(error) {
            console.log('Error: ' + error);
        }
    });
}

function deleteReservation() {
    var reservation = reservationTable.row($('.selected')).data();

    $.ajax({
        url: '/api/reservation/deleteById/' + reservation.reservationId,
        type: "delete",
        dataType: "text",
        success: function(message) {
            console.log(message);
            getReservationData();
        },
        error: function(error) {
            console.log('Error: ' + error);
        }
    });
}

// Function to check in a reservation
function checkInReservation(reservationId) {
    $.ajax({
        url: `/api/reservation/checkIn/${reservationId}`,
        type: "POST",
        success: function(response) {

          alert($("#reservation-checkedin").text());

            getReservationData();  // Refresh the reservation table
        },
        error: function(error) {
            console.log('Error: ' + error);
            alert("Failed to check in reservation.");
        }
    });
}

// Function to check out a reservation
function checkOutReservation(reservationId) {
    $.ajax({
        url: `/api/reservation/checkOut/${reservationId}`,
        type: "POST",
        success: function(response) {
        alert($("#reservation-checkedout").text());

            getReservationData();  // Refresh the reservation table
        },
        error: function(error) {
            console.log('Error: ' + error);
            alert("Failed to check out reservation.");
        }
    });
}