var guests = [];
var reservationId;
var roomNumber;
var roomChildCapacity;
var roomAdultCapacity;
var currentReservation = null;
let isAdjustingDates = false; // Flag to check if dates are being adjusted

function validateEmail(inputElement) {
    const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
    if (!emailPattern.test(inputElement.value)) {
        inputElement.setCustomValidity("Please enter a valid email address.");
    } else {
        inputElement.setCustomValidity("");
    }
}

function init() {


    console.log("init calisti");
if(language=="zh"){
$('#checkinCheckoutInfoModalZh').modal('show');
}else{
$('#checkinCheckoutInfoModal').modal('show');
}



        $('#guestEmail1').on('input', function() {
            validateEmail(this);
        });


          $('#loadMoreBtn').on('click', function() {
              if (!isLoading) {
                  displayRooms();
              }
          });

          const urlParams = new URLSearchParams(window.location.search);
          reservationId = urlParams.get('reservationId');

          if (reservationId) {
                  loadReservationData(reservationId);
                  //eger rezervasyon editleme ile bu sayfa cagrildiysa stepler aktif oluyor.
                                    $('#step2Button').removeClass('disabled btn-secondary').addClass('btn-primary');
                  //                  $('#step3Button').removeClass('disabled btn-secondary').addClass('btn-primary');
          }


         $('#reservationSummaryButton').on('click', function() {
               if (reservationId) {
                   event.preventDefault();  // Önce işlemi durdur
                   const emailInput = document.getElementById('guestEmail1');
                   validateEmail(emailInput);

                 // Eğer geçersizse, form gönderimini durdurun
                 if (!emailInput.checkValidity()) {
                     event.preventDefault();
                     event.stopPropagation();
                     alert($("#msg_emailCorrect").text());

                     return;
                 }
                 logicalOfEdit();
               }else{
                  event.preventDefault();  // Önce işlemi durdur

                  const emailInput = document.getElementById('guestEmail1');
                  validateEmail(emailInput);

                  if (!emailInput.checkValidity()) {
                  alert($("#msg_emailCorrect").text());

                      return;
                  }

                  const guestInfo = {
                      userName: $('#guestFirstName1').val(),
                      userSurName: $('#guestLastName1').val(),
                      email: $('#guestEmail1').val(),
                      phoneNumber: $('#guestPhone1').val(),
                      address: {
                          street: $('#addressStreet1').val(),
                          city: $('#addressCity1').val(),
                          country: $('#addressCountry1').val(),
                          zipCode: $('#addressZip1').val(),
                          houseNumber: $('#houseNumber1').val()
                      }
                  };

                  $.ajax({
                      url: "/api/reservation/checkUserInfo",
                      method: "POST",
                      contentType: "application/json",
                      data: JSON.stringify(guestInfo),
                      success: function(response) {
                      console.log(response);
                          // Bilgiler doğruysa summary kısmını göster
                          if (validateGuestDetails()) {
                              displayReservationSummary(guests);
                          }
                      },
                      error: function(xhr) {
                          // Eğer bilgiler farklıysa hata mesajını göster ve işlemi durdur
                          alert($("#msg_userInfoMismatch").text());
                          return;  // İşlemi sonlandır
                      }
                  });
               }
                validateReservation();
         });


        // Modal kapatma işlevi
        $('.close').on('click', function() {
          $('#myModal').hide();
          $('#galleryModal').hide();
        });

        // Accordion özelliğini etkinleştirme
        $('#accordionFilters .collapse').collapse();

        loadFacilities();
        loadBedTypes();
        loadRoomTypes();

        $('#checkIn, #checkOut').on('change', function() {
                validateDates();
        });


         $('#checkOut').on('change', function() {
             if(reservationId){
                 var roomNumber = $('#reservationSummaryButton').data('roomNumber'); // Oda ID'sini al
                 var newCheckOutDate = $(this).val(); // Yeni check-out tarihini al

                 var oldCheckOutDate = new Date(currentReservation.checkOut); // Old check-out tarihini al
                 var timezoneOffset = oldCheckOutDate.getTimezoneOffset() * 60000; // Saat dilimi farkını milisaniye cinsinden al
                 oldCheckOutDate = new Date(oldCheckOutDate.getTime() - timezoneOffset).toISOString().slice(0, 16); // Saat dilimi farkını çıkar, ISO formatına çevir ve saat ile tarih kısmını al


                 checkRoomAvailability(roomNumber, oldCheckOutDate, newCheckOutDate);
             }
         });


        $('#searchRoomsButton').on('click', function() {
            if (!validateDates()) {
                return;
            }
            // Oda arama işlemini burada devam ettirin
            searchRooms();
        });

        $('#applyFiltersButton').on('click', searchRooms);
        $('#completeReservationButton').on('click', function(event) {
            const emailInput = document.getElementById('guestEmail1');
            validateEmail(emailInput);

            // Eğer geçersizse, form gönderimini durdurun
            if (!emailInput.checkValidity()) {
                event.preventDefault();
                event.stopPropagation();
                alert($("#msg_reservationEmailCorrect").text());
                return;
            }
        completeReservation();
            // Diğer işlemler...
        });

        // Step headers click event
        $('.step-header').on('click', function() {
            $('.step-content').removeClass('active');
            var target = $(this).data('target');
            $(target).addClass('active');
        });

        $('#checkIn, #checkOut').change(function() {
                calculateTotalPrice();
        });
}

function setCheckin(){
        const originalCheckInDate = new Date(currentReservation.checkIn);
        // Orijinal check-in tarihine iki saat ekle
        const correctedCheckinDate = new Date(originalCheckInDate.getTime() + 2 * 60 * 60 * 1000);

        // ISO formatında tarih ve saati input alanına yerleştir
        $('#checkIn').val(correctedCheckinDate.toISOString().slice(0, 16));
}

function setCheckOut(){
        const originalCheckOutDate = new Date(currentReservation.checkOut);
        // Orijinal check-out tarihine iki saat ekle
        const correctedCheckOutDate = new Date(originalCheckOutDate.getTime() + 2 * 60 * 60 * 1000);

        // ISO formatında tarih ve saati input alanına yerleştir
        $('#checkOut').val(correctedCheckOutDate.toISOString().slice(0, 16));
}

function validateDates() {

    const checkIn = $('#checkIn').val();
    const checkOut = $('#checkOut').val();
    const checkInDate = new Date(checkIn);
    const checkOutDate = new Date(checkOut);
    const now = new Date();

    // Translate messages
    const datesInPastMessage = $("#msg_datesInPastMessage").text();
    const checkInAfterCheckOutMessage = $("#msg_checkInAfterCheckOutMessage").text();
    const checkInSameAsCheckOutMessage = $("#msg_checkInSameAsCheckOutMessage").text();

// Eğer rezervasyon düzenleniyorsa ve check-in tarihi geçmişte ise, mevcut tarihi kontrol et
    if (currentReservation) {

        const originalCheckInDate = new Date(currentReservation.checkIn);
        const originalCheckOutDate = new Date(currentReservation.checkOut);

        // Eğer mevcut check-in tarihi geçmişte ve değiştirilmemişse, kontrolü atla ve devam et
        if (originalCheckInDate < now && checkInDate.getTime() === originalCheckInDate.getTime()) {
            return true;
        }

        // Eğer kullanıcı geçmiş bir tarih seçmeye çalışırsa, uyarı ver ve orijinal tarihi geri yükle
        if (checkInDate < now) {
            alert(datesInPastMessage);

        setCheckin();

            return false;
        }

        // 1. Check-out tarihleri şimdiki tarihten eski olamaz.
            if (checkOutDate < now) {
                alert(datesInPastMessage);
            setCheckOut();

                return false;
            }

        // 2. Check-in tarihi check-out tarihinden ileri olamaz.
            if (checkInDate > checkOutDate) {
                alert(checkInAfterCheckOutMessage);
                setCheckin();
                setCheckOut();
                return false;
            }
        // 3. Check-in ve check-out tarihleri aynı ise, saatleri karşılaştır.
            if (checkInDate.toDateString() === checkOutDate.toDateString()) {
            alert(checkInDate);
            alert(checkOutDate);

                // Aynı gün içindeyse saatleri karşılaştır
                if (checkInDate.getTime() >= checkOutDate.getTime()) {
                    alert(checkInSameAsCheckOutMessage);

                    setCheckin();
                    setCheckOut();
                    return false;
                }
            }
        validateReservation();
        return true;
    }

    // 1. Check-in ve check-out tarihleri şimdiki tarihten eski olamaz.
    if (checkInDate < now || checkOutDate < now) {
        alert(datesInPastMessage);
        return false;
    }

    // 2. Check-in tarihi check-out tarihinden ileri olamaz.
    if (checkInDate > checkOutDate) {
        alert(checkInAfterCheckOutMessage);
        return false;
    }

    // 3. Check-in ve check-out tarihleri aynı ise, saatleri karşılaştır.
    if (checkInDate.toDateString() === checkOutDate.toDateString()) {
        // Aynı gün içindeyse saatleri karşılaştır
        if (checkInDate.getTime() >= checkOutDate.getTime()) {
            alert(checkInSameAsCheckOutMessage);
            return false;
        }
    }
    validateReservation();
    return true;
}


function validateReservation() {
    if (isAdjustingDates) return; // Tarihler ayarlanıyorsa çık

    const checkIn = new Date($('#checkIn').val());
    const checkOut = new Date($('#checkOut').val());

    let changesMade = false;
    if (checkIn.getHours() < 14) {
        changesMade = true;
        if ((checkIn.getDate() - 1) > (new Date()).getDate()) {
            checkIn.setDate(checkIn.getDate() - 1);
            checkIn.setHours(14, 0, 0, 0);
        }
    } else {
        if (!(checkIn.getDate() === new Date().getDate())) {
            changesMade = true;
            checkIn.setHours(14, 0, 0, 0);
        }
    }

    // Check-out saatini kontrol et ve gerekirse ayarla
    if (checkOut.getHours() < 10) {
        changesMade = true;
        checkOut.setHours(10, 0, 0, 0); // Saat 10:00 olarak ayarla
    } else if (checkOut.getHours() > 10) {
        changesMade = true;
        checkOut.setDate(checkOut.getDate() + 1); // Bir gün ileri al
        checkOut.setHours(10, 0, 0, 0); // Saat 10:00 olarak ayarla
    }else if((checkOut.getHours() === 10 && checkOut.getMinutes() > 0)){
            changesMade = true;
            checkOut.setDate(checkOut.getDate() + 1); // Bir gün ileri al
            checkOut.setHours(10, 0, 0, 0); // Saat 10:00 olarak ayarla
    }

    if (changesMade) {
        // Tarihleri input alanlarına geri yaz
        $('#checkIn').val(checkIn.toLocaleString('sv-SE').slice(0, 16));
        $('#checkOut').val(checkOut.toLocaleString('sv-SE').slice(0, 16));

        // Translate message
        //const datesAdjustedMessage = $("#msg_datesAdjustedMessage").text();

        //alert(datesAdjustedMessage);
    }
}


function calculateTotalPrice() {
    const checkIn = new Date($('#checkIn').val());
    const checkOut = new Date($('#checkOut').val());

    const roomPrice = $('#reservationSummaryButton').data('roomPrice');

    if (checkIn && checkOut && checkIn < checkOut) {
        let totalNights = 0;

        // Check-in ve check-out saatleri belirli kurallara göre ayarlanmış olmalı
        if (checkIn.getHours() < 14) {
            checkIn.setDate(checkIn.getDate() - 1);
        }
        checkIn.setHours(14, 0, 0, 0);

        if (checkOut.getHours() > 10 || (checkOut.getHours() === 10 && checkOut.getMinutes() > 0)) {
            checkOut.setDate(checkOut.getDate() + 1);
        }
        checkOut.setHours(10, 0, 0, 0);

        const timeDifference = checkOut.getTime() - checkIn.getTime();
        totalNights = Math.ceil(timeDifference / (1000 * 3600 * 24));

        // Toplam fiyat hesaplama
        const totalPrice = totalNights * roomPrice;
        return totalPrice.toFixed(2);
    }

    return "0.00";
}


function logicalOfEdit() {
    if (!validateDates() || !validateGuestDetails()) {
        return;
    }

    const checkIn = new Date($('#checkIn').val());
    const checkOut = new Date($('#checkOut').val());
    const now = new Date();

    const dateDifferenceInDays = Math.ceil((currentReservation.checkIn - now) / (1000 * 60 * 60 * 24));

    const originalCheckInDate = new Date(currentReservation.checkIn);
    const originalCheckOutDate = new Date(currentReservation.checkOut);
    const originalTotalPrice = currentReservation.totalPrice;

    const newRoomPrice = parseFloat($('#reservationSummaryButton').data('roomPrice'));
    const daysDifference = Math.ceil((checkOut - checkIn) / (1000 * 60 * 60 * 24));
    const newTotalPrice = newRoomPrice * daysDifference;

    const isCheckInAfter = checkIn >= originalCheckInDate;
    const isCheckOutBefore = checkOut <= originalCheckOutDate;
    const isNewTotalCheaper = newTotalPrice < originalTotalPrice;
    const checkinAfterOldCheckout =checkIn>=originalCheckOutDate;

    let cancellationFee = 0;
    let warningMessage = '';
    var actionRequired = false;

    // Translate messages
    const priceRecalculatedMessage = $("#msg_priceRecalculatedMessage").text();
    const cancellationFeeMessage = $("#msg_cancellationFeeMessage").text();
    const noRefundMessage = $("#msg_noRefundMessage").text();
    const cancellationFeeTextMessage = $("#msg_cancellationFeeTextMessage").text();
    const reservationModificationCancelledMessage = $("#msg_reservationModificationCancelledMessage").text();

    if (dateDifferenceInDays > 20) {
        alert(priceRecalculatedMessage);
        displayReservationSummary(guests);
    } else if (dateDifferenceInDays <= 20 && dateDifferenceInDays > 10) {
        if (isCheckInAfter || isCheckOutBefore || isNewTotalCheaper) {
            cancellationFee = (originalTotalPrice - newTotalPrice) * 0.5;

            if(checkinAfterOldCheckout){
                cancellationFee=originalTotalPrice;
                }

            warningMessage = cancellationFeeMessage.replace("{cancellationFee}", cancellationFee.toFixed(2));
            actionRequired = true;
            alert(cancellationFeeMessage.replace("{cancellationFee}", cancellationFee));
        }
    } else if (dateDifferenceInDays >0 && dateDifferenceInDays <= 10) {
        if (isNewTotalCheaper || isCheckInAfter || isCheckOutBefore ) {
            cancellationFee = originalTotalPrice - newTotalPrice;
            if(checkinAfterOldCheckout){
             cancellationFee=originalTotalPrice;
             }
            warningMessage = noRefundMessage;
            actionRequired = true;
        }
    }else{
            if (isNewTotalCheaper || isCheckInAfter || isCheckOutBefore ) {
                cancellationFee = originalTotalPrice - newTotalPrice;
                warningMessage = noRefundMessage;
                actionRequired = true;
            }
    }


    if (actionRequired) {

            currentReservation.cancellationFee = cancellationFee;

            displayReservationSummary(guests);

    } else {
        displayReservationSummary(guests);
    }
}


function validateGuestDetails() {
    const adults = $('#adults').val();
    const children = $('#children').val();
    let valid = true;
    guests = [];

    // Translate messages
    const primaryGuestMissingMessage = $("#msg_primaryGuestMissingMessage").text();
    const guestMissingMessage = $("#msg_guestMissingMessage").text();
    const childMissingMessage = $("#msg_childMissingMessage").text();

    // Primary Guest bilgileri için zorunlu alan kontrolü
    if ($('#guestFirstName1').val() === '' || $('#guestLastName1').val() === '' || $('#guestPhone1').val() === '' ||
        $('#guestEmail1').val() === '' || $('#addressStreet1').val() === '') {
        valid = false;
        alert(primaryGuestMissingMessage);
    } else {
        // Kullanıcı bilgilerinden User oluştur ve guests listesine ekle
        let primaryGuest = {
            userName: $('#guestFirstName1').val(),
            userSurName: $('#guestLastName1').val(),
            email: $('#guestEmail1').val(),
            address: {
                country: $('#addressCountry1').val(),
                city: $('#addressCity1').val(),
                street: $('#addressStreet1').val(),
                zipCode: $('#addressZip1').val(),
                houseNumber: $('#houseNumber1').val()
            },
            adult: true,
            phoneNumber: $('#guestPhone1').val()
        };
        guests.push(primaryGuest);
    }

    // Diğer misafirler için zorunlu alan kontrolü
    for (let i = 2; i <= adults; i++) {
        if ($('#guestFirstName' + i).val() === '' || $('#guestLastName' + i).val() === '') {
            valid = false;
            alert(guestMissingMessage.replace('{i}', i));
            break;
        } else {
            // Kullanıcı bilgilerinden User oluştur ve guests listesine ekle
            let guest = {
                userName: $('#guestFirstName' + i).val(),
                adult: true,
                userSurName: $('#guestLastName' + i).val()
            };
            guests.push(guest);
        }
    }

    // Çocuklar için zorunlu alan kontrolü
    for (let i = 1; i <= children; i++) {
        const childIndex = parseInt(adults) + i;
        if ($('#childFirstName' + childIndex).val() === '' || $('#childLastName' + childIndex).val() === '') {
            valid = false;
            alert(childMissingMessage.replace('{i}', i));
            break;
        } else {
            // Kullanıcı bilgilerinden User oluştur ve guests listesine ekle
            let child = {
                adult: false,
                userName: $('#childFirstName' + childIndex).val(),
                userSurName: $('#childLastName' + childIndex).val()
            };
            guests.push(child);
        }
    }

    if (!valid) {
        return false;
    }

    return true;
}



let roomsLoaded = new Set();
let currentPage = 0;
let roomsPerPage = 3;  // Her seferinde kaç oda gösterileceğini belirleyin
let allRooms = [];  // AJAX çağrısından dönen tüm odaları tutacak
let isLoading = false;  // Verilerin yüklenip yüklenmediğini kontrol eder


function searchRooms() {
    validateReservation();
    if (isLoading) return;
    isLoading = true;
    $('#loader').show();

    const checkIn = $('#checkIn').val();
    const checkOut = $('#checkOut').val();
    const adults = $('#adults').val();
    var adultsNumber = parseInt($("#adults").val());

    // Translate messages
    const fillRequiredFieldsMessage = $("#msg_fillRequiredFieldsMessage").text();
    const adultsMinimumMessage = $("#msg_adultsMinimumMessage").text();
    const noRoomsFoundMessage = $("#msg_noRoomsFoundMessage").text();

    // Gerekli alanları kontrol et
    if (!checkIn || !checkOut || !adults || (adultsNumber == 0)) {
        alert(fillRequiredFieldsMessage);
        $('#loader').hide();
        isLoading = false;
        return;
    }

    // Check-in ve check-out tarihlerini Date nesnesine çevir
    const checkInDate = new Date(checkIn);
    const checkOutDate = new Date(checkOut);
    const now = new Date();

    // 4. Yetişkin sayısı en az 1 olmalıdır.
    if (adultsNumber < 1) {
        alert(adultsMinimumMessage);
        $('#loader').hide();
        isLoading = false;
        return;
    }

    const children = $('#children').val();
    const roomType = $('#roomType').val();

    const smokingChecked = $('#smokingCheckbox').prop('checked');
    const selectedFacilities = [];
    $('#facilities input:checked').each(function() {
        selectedFacilities.push($(this).val());
    });
    const selectedBedTypes = [];
    $('#bedTypes input:checked').each(function() {
        selectedBedTypes.push($(this).val());
    });
    const floor = $('#floor').val();
    // Form verilerini toplarken disabledFriendly kontrolü
    const disabledFriendly = $('#disabledFriendly').prop('checked') ? true : null;

    const filters = {
        checkIn,
        checkOut,
        adults,
        children,
        roomType,
        smoking: smokingChecked,
        facilities: selectedFacilities,
        bedTypes: selectedBedTypes,
        floor,
        disabledFriendly
    };

    // Oda listesini temizle
    $('#roomList').empty();
    $('#loadMoreContainer').hide();
    var lang = getLanguageFromCookie();

    $.ajax({
        url: "/api/room/search/"+lang,
        method: 'post',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(filters),
        success: function(data) {
            // AJAX çağrısından dönen tüm verileri kaydedin
            allRooms = data;
            roomsLoaded.clear();
            currentPage = 0;

            if (data.length === 0) {
                $('#roomList').append(`<div class="col-12 text-center"><p>${noRoomsFoundMessage}</p></div>`);
            } else {
                if (reservationId) {
                    // Mevcut rezervasyon odasını listenin başına ekle
                    loadSelectedRoomData(currentReservation.roomNumber, function(roomData) {
                        // Odalar arasında mevcut oda zaten varsa eklemeyin
                        const roomExists = allRooms.some(room => room.roomNumber === roomData.roomNumber);
                        if (!roomExists) {
                            allRooms.unshift(roomData); // Mevcut rezervasyon odasını listenin başına ekle
                        }
                        displayRooms(); // Odaları ekranda göster
                    });
                } else {
                    displayRooms(); // Normal oda listesini göster
                }
            }

            $('#loader').hide();
            isLoading = false;

            // Eğer gösterilecek daha fazla oda varsa, "Load More" butonunu gösterin
            if (allRooms.length > roomsLoaded.size) {
                $('#loadMoreContainer').show();
            } else {
                $('#loadMoreContainer').hide();
            }
        },
        error: function(error) {
            console.error('Error loading room data:', error);
            $('#loader').hide();
            isLoading = false;
        }
    });
}


function displayRooms() {
    let roomHtml = '';
    const roomsToShow = allRooms.slice(currentPage * roomsPerPage, (currentPage + 1) * roomsPerPage);

    // Translate messages with fallback
    const priceLabel = $("#msg_priceLabel").text();
    const descriptionLabel = $("#msg_descriptionLabel").text();
    const facilitiesLabel = $("#msg_facilitiesLabel").text();
    const smokingLabel = $("#msg_smokingLabel").text();
    const smokingAllowedMessage = $("#msg_smokingAllowedMessage").text();
    const smokingNotAllowedMessage = $("#msg_smokingNotAllowedMessage").text();
    const bedsLabel = $("#msg_bedsLabel").text();
    const adultsLabel = $("#msg_adultsLabel").text();
    const childrenLabel = $("#msg_childrenLabel").text() ;
    const floorLabel = $("#msg_floorLabel").text();
    const selectRoomLabel = $("#msg_selectRoomLabel").text();
    const selectedRoomLabel = $("#msg_selectedRoomLabel").text() ;
    const additionalPhotosLabel = $("#msg_additionalPhotosLabel").text();
    const disabledLabel = $("#msg_displaySelectedRoom_disabledLabel").text();


    roomsToShow.forEach(function(room) {
        if (!roomsLoaded.has(room.roomId)) {
            roomsLoaded.add(room.roomId);
            const formattedBedTypes = formatBedTypes(room.roomBedType);
            const disabledStatus = room.disabled ? $("#msg_yes").text() : $("#msg_no").text();
            let additionalPhotos = '';
            if (room.imageUrlList.length > 5) {
                additionalPhotos = `<div class="additional-photos">${additionalPhotosLabel.replace('{count}', room.imageUrlList.length - 5)}</div>`;
            }

            roomHtml += `
                <div class="col-12 room-container">
                    <div class="room-card">
                        <div class="room-images">
                            ${room.imageUrlList.slice(0, 5).map(url => `<img src="${url}" alt="Room Image" class="room-image" onclick="openModal('${room.imageUrlList.join(',')}')">`).join('')}
                            ${additionalPhotos}
                        </div>
                        <div class="room-details">
                            <h4>${room.roomType} - ${room.roomNumber}</h4>
                            <p><strong>${priceLabel}:</strong> $${room.price}</p>
                            <p><strong>${descriptionLabel}:</strong> ${room.description}</p>
                            <p><strong>${facilitiesLabel}:</strong></p>
                            <div class="facilities">
                                ${room.facilitiesList.map(facility => `<div class="facility">${facility}</div>`).join('')}
                            </div>
                            <p><strong>${smokingLabel}:</strong> ${room.smoking ? smokingAllowedMessage : smokingNotAllowedMessage}</p>
                            <p><strong>${bedsLabel}:</strong> ${formattedBedTypes}</p>
                            <p><strong>${adultsLabel}:</strong> ${room.countOfAdult}</p>
                            <p><strong>${childrenLabel}:</strong> ${room.countOfChild}</p>
                            <p><strong>${floorLabel}:</strong> ${room.thFloor}</p>
                            <p><strong>${disabledLabel}:</strong> ${disabledStatus}</p>
                            <button class="select-room-btn btn-primary"
                                    data-room-number="${room.roomNumber}"
                                    data-room-id="${room.roomId}"
                                    data-room-price="${room.price}"
                                    data-count-of-child="${room.countOfChild}"
                                    data-count-of-adult="${room.countOfAdult}">
                                ${selectRoomLabel}
                            </button>
                        </div>
                    </div>
                </div>
            `;
        }
    });

    $('#roomList').append(roomHtml);
    currentPage++;

    // Eğer gösterilecek daha fazla oda yoksa "Load More" butonunu gizle
    if (roomsLoaded.size >= allRooms.length) {
        $('#loadMoreContainer').hide();
    }

    // Modal ve butonlar için gerekli eventleri tekrar tanımlayın
    attachEventListeners();
}



function attachEventListeners() {
    $('.room-image').on('click', function() {
        const src = $(this).attr('src');
        $('#modalImg').attr('src', src);
        $('#myModal').show();
    });

    $('.additional-photos').on('click', function() {
        const roomImages = $(this).siblings('img').map((_, img) => $(img).attr('src')).get();
        const additionalImages = $(this).parent().siblings('.room-images').map((_, img) => $(img).attr('src')).get();
        const allImages = roomImages.concat(additionalImages);

        openModal(allImages.join(','));
    });

    $('.select-room-btn').on('click', function() {
        const roomNumber = $(this).data('roomNumber');

        roomChildCapacity = $(this).data('countOfChild');

        roomAdultCapacity = $(this).data('countOfAdult');

        const roomPrice = $(this).data('roomPrice');
        selectRoom(roomNumber, roomPrice);
    });

}

function openModal(imageUrls) {
  const urls = imageUrls.split(',');
  let galleryHtml = '';
  urls.forEach((src, index) => {
    galleryHtml += `<img src="${src}" alt="Room Image" class="gallery-image">`;
  });

  $('#galleryModalContent').html(galleryHtml);
  $('#myModal').show();
  showSlides(1);
}

function showSlides(n) {
  let i;
  const slides = document.getElementsByClassName('gallery-image');
  if (n > slides.length) { slideIndex = 1 }
  if (n < 1) { slideIndex = slides.length }
  for (i = 0; i < slides.length; i++) {
    slides[i].style.display = 'none';
  }
  slides[slideIndex - 1].style.display = 'block';
}

let slideIndex = 1;
function plusSlides(n) {
  showSlides(slideIndex += n);
}

function selectRoom(roomNumber, roomPrice) {

    $('#step2Button').removeClass('disabled btn-secondary').addClass('btn-primary'); //butonu aktif yaptım
    $('#guestDetails').show();
    $('#reservationSummaryButton').data('roomNumber', roomNumber);
    $('#reservationSummaryButton').data('roomPrice', roomPrice);

    // Translate messages
    const selectedRoomLabel = $("#msg_selectRoom_selectedRoomLabel").text();
    const selectRoomLabel = $("#msg_selectRoom_selectRoomLabel").text();
    const maxAdultsReachedMessage = $("#msg_selectRoom_maxAdultsReachedMessage").text();
    const maxChildrenReachedMessage = $("#msg_selectRoom_maxChildrenReachedMessage").text();
    const primaryGuestLabel = $("#msg_selectRoom_primaryGuestLabel").text();
    const firstNameLabel = $("#msg_selectRoom_firstNameLabel").text();
    const lastNameLabel = $("#msg_selectRoom_lastNameLabel").text();
    const phoneNumberLabel = $("#msg_selectRoom_phoneNumberLabel").text();
    const emailLabel = $("#msg_selectRoom_emailLabel").text();
    const streetLabel = $("#msg_selectRoom_streetLabel").text();
    const cityLabel = $("#msg_selectRoom_cityLabel").text();
    const countryLabel = $("#msg_selectRoom_countryLabel").text();
    const zipCodeLabel = $("#msg_selectRoom_zipCodeLabel").text();
    const houseNumberLabel = $("#msg_selectRoom_houseNumberLabel").text();
    const addAdultGuestLabel = $("#msg_selectRoom_addAdultGuestLabel").text();
    const addChildLabel = $("#msg_selectRoom_addChildLabel").text();

    // Seçilen odanın butonunu bulun ve metnini değiştirin
    $('.select-room-btn').each(function() {
        if ($(this).data('room-number') === roomNumber) {
            $(this).text(selectedRoomLabel);
            $(this).removeClass('btn-primary').addClass('btn-success'); // Görsel olarak da farklılaştırmak için
            $(this).prop('disabled', true); // Tekrar tıklanmayı engellemek için butonu devre dışı bırak
        } else {
            $(this).text(selectRoomLabel);
            $(this).removeClass('btn-success').addClass('btn-primary'); // Diğer butonları varsayılan duruma döndür
            $(this).prop('disabled', false); // Diğer butonları aktif hale getir
        }
    });

    const currentAdults = parseInt($('#adults').val());
    const currentChildren = parseInt($('#children').val());

    const maxAdults = roomAdultCapacity; // Oda kapasitesi
    const maxChildren = roomChildCapacity; // Oda kapasitesi

    let guestInfoFormHtml = `
        <div id="guestAccordion">
            <div class="card">
                <div class="card-header" id="heading1">
                    <h5 class="mb-0">
                        <button class="btn btn-link" data-toggle="collapse" data-target="#collapse1" aria-expanded="true" aria-controls="collapse1">
                            ${primaryGuestLabel}
                        </button>
                    </h5>
                </div>
                <div id="collapse1" class="collapse show" aria-labelledby="heading1" data-parent="#guestAccordion">
                    <div class="card-body">
                        <div class="form-group">
                            <label for="guestFirstName1">${firstNameLabel}</label>
                            <input type="text" id="guestFirstName1" class="form-control guest-firstname" required />
                        </div>
                        <div class="form-group">
                            <label for="guestLastName1">${lastNameLabel}</label>
                            <input type="text" id="guestLastName1" class="form-control guest-lastname" required />
                        </div>
                        <div class="form-group">
                            <label for="guestPhone1">${phoneNumberLabel}</label>
                            <input type="number" id="guestPhone1" class="form-control guest-phone" required />
                        </div>
                        <div class="form-group">
                            <label for="guestEmail1">${emailLabel}</label>
                            <input type="email" id="guestEmail1" class="form-control guest-email" required />
                        </div>
                        <div class="form-group">
                            <label for="addressStreet1">${streetLabel}:</label>
                            <input type="text" class="form-control" id="addressStreet1" required >
                        </div>
                        <div class="form-group">
                            <label for="addressCity1">${cityLabel}:</label>
                            <input type="text" class="form-control" id="addressCity1" required>
                        </div>
                        <div class="form-group">
                            <label for="addressCountry1">${countryLabel}:</label>
                            <input type="text" class="form-control" id="addressCountry1" required>
                        </div>
                        <div class="form-group">
                            <label for="addressZip1">${zipCodeLabel}:</label>
                            <input type="text" class="form-control" id="addressZip1" required>
                        </div>
                        <div class="form-group">
                            <label for="houseNumber1">${houseNumberLabel}:</label>
                            <input type="text" class="form-control" id="houseNumber1">
                        </div>
                    </div>
                </div>
            </div>
    `;

    // Var olan adult sayısına göre misafir kartlarını ekleyin
    for (let i = 2; i <= currentAdults; i++) {
        guestInfoFormHtml += createGuestCard(i, 'adult');
    }

    // Var olan child sayısına göre çocuk kartlarını ekleyin
    for (let i = 1; i <= currentChildren; i++) {
        const childIndex = currentAdults + i;
        guestInfoFormHtml += createGuestCard(childIndex, 'child');
    }

    guestInfoFormHtml += `</div>`;

    // yetiskinler icin add butonu
    guestInfoFormHtml += `
         <button id="addAdultGuest" class="btn btn-primary mt-3">${addAdultGuestLabel}</button>
     `;

    // cocuklar icin add butonu
    guestInfoFormHtml += `
         <button id="addChildGuest" class="btn btn-primary mt-3">${addChildLabel}</button>
     `;

    $('#guestInfoForm').html(guestInfoFormHtml);
    fetchUserEmailAndPopulateForm();
    if (reservationId) {
        loadGuestInfo(currentReservation.adults, currentReservation.children);
        disablePrimaryGuestFields();
    }

    // Add event listeners for adding and removing guests
    $('#addAdultGuest').on('click', function() {
        if ($('.guest-firstname').length < maxAdults) {
            addGuestForm(true);
        } else {
            alert(maxAdultsReachedMessage);
        }
    });

    $('#addChildGuest').on('click', function() {
        if ($('.child-firstname').length < maxChildren) {
            addGuestForm(false);
        } else {
            alert(maxChildrenReachedMessage);
        }
    });

    // Remove guest event listener
    $('.remove-guest-btn').on('click', function() {
        const guestIndex = $(this).data('guest-index');
        const guestType = $(this).data('guest-type');
        removeGuestForm(guestIndex, guestType);
    });

    $('.step-content').removeClass('active');
    $('#step2').addClass('active');
}


function createGuestCard(index, type) {
    const guestTypeLabel = type === 'adult' ? $("#msg_createGuestCard_adultLabel").text() : $("#msg_createGuestCard_childLabel").text();
    const guestTypeIdPrefix = type === 'adult' ? 'guest' : 'child';
    const guestClass = type === 'adult' ? 'guest-firstname' : 'child-firstname';
    const guestLastNameClass = type === 'adult' ? 'guest-lastname' : 'child-lastname';

    const collapseId = `${guestTypeIdPrefix}Collapse${index}`;

    const firstNameLabel = $("#msg_createGuestCard_firstNameLabel").text();
    const lastNameLabel = $("#msg_createGuestCard_lastNameLabel").text();
    const removeLabel = $("#msg_createGuestCard_removeLabel").text();

    return `
        <div class="card" id="${guestTypeIdPrefix}GuestCard${index}">
            <div class="card-header" id="heading${index}">
                <h5 class="mb-0">
                    <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#${collapseId}" aria-expanded="false" aria-controls="${collapseId}">
                        ${guestTypeLabel} ${index}
                    </button>
                    <button class="btn btn-danger btn-sm float-right remove-guest-btn" data-guest-index="${index}" data-guest-type="${type}">${removeLabel}</button>
                </h5>
            </div>
            <div id="${collapseId}" class="collapse" aria-labelledby="heading${index}" data-parent="#guestAccordion">
                <div class="card-body">
                    <div class="form-group">
                        <label for="${guestTypeIdPrefix}FirstName${index}">${firstNameLabel}</label>
                        <input type="text" id="${guestTypeIdPrefix}FirstName${index}" name="${guestTypeIdPrefix}FirstName${index}" class="form-control ${guestClass}" required />
                    </div>
                    <div class="form-group">
                        <label for="${guestTypeIdPrefix}LastName${index}">${lastNameLabel}</label>
                        <input type="text" id="${guestTypeIdPrefix}LastName${index}" name="${guestTypeIdPrefix}LastName${index}" class="form-control ${guestLastNameClass}" required />
                    </div>
                </div>
            </div>
        </div>
    `;
}

function disablePrimaryGuestFields() {
    $('#guestFirstName1').prop('disabled', true);
    $('#guestLastName1').prop('disabled', true);
    $('#guestPhone1').prop('disabled', true);
    $('#guestEmail1').prop('disabled', true);
    $('#addressStreet1').prop('disabled', true);
    $('#addressCity1').prop('disabled', true);
    $('#addressCountry1').prop('disabled', true);
    $('#addressZip1').prop('disabled', true);
    $('#houseNumber1').prop('disabled', true);
}

function addGuestForm(isAdult) {
    const guestAccordion = $('#guestAccordion');
    let guestFormHtml = '';

    const guestIndex = isAdult ? $('.guest-firstname').length + 1 : $('.child-firstname').length + 1;

    if (isAdult) {
        guestFormHtml = createGuestCard(guestIndex, 'adult');
    } else {
        guestFormHtml = createGuestCard(guestIndex, 'child');
    }

    guestAccordion.append(guestFormHtml);

    // Attach the remove guest event listener for the newly added guest
    $('.remove-guest-btn').off('click').on('click', function() {
        const guestIndex = $(this).data('guest-index');
        const guestType = $(this).data('guest-type');
        removeGuestForm(guestIndex, guestType);
    });
}

function removeGuestForm(guestIndex, guestType) {
    if (guestType === 'adult') {
        $(`#guestGuestCard${guestIndex}`).remove(); // Yetişkin misafir kartını kaldır
    } else if (guestType === 'child') {
        $(`#childGuestCard${guestIndex}`).remove(); // Çocuk misafir kartını kaldır
    }

    // Oda kapasitesine uygun olarak güncellenmesi için misafir sayısını yeniden kontrol et
    const remainingAdults = $('.guest-firstname').length;
    const remainingChildren = $('.child-firstname').length;
    // Gerekirse misafir sayısı alanlarını güncelleyin
    $('#adults').val(remainingAdults);
    $('#children').val(remainingChildren);

}

function displayReservationSummary(guests) {
    const totalPrice = calculateTotalPrice(); // Toplam fiyatı hesapla

    const roomNumber = $('#reservationSummaryButton').data('roomNumber');
    const checkInDate = $('#checkIn').val();
    const checkOutDate = $('#checkOut').val();
    const roomPrice = $('#reservationSummaryButton').data('roomPrice');
    const cancellationFee = currentReservation ? currentReservation.cancellationFee : 0;

    // Translate messages
    const roomNumberLabel = $("#msg_displayReservationSummary_roomNumberLabel").text();
    const checkInDateLabel = $("#msg_displayReservationSummary_checkInDateLabel").text();
    const checkOutDateLabel = $("#msg_displayReservationSummary_checkOutDateLabel").text();
    const pricePerNightLabel = $("#msg_displayReservationSummary_pricePerNightLabel").text();
    const totalPriceLabel = $("#msg_displayReservationSummary_totalPriceLabel").text();
    const cancellationFeeLabel = $("#msg_displayReservationSummary_cancellationFeeLabel").text();
    const guestsLabel = $("#msg_displayReservationSummary_guestsLabel").text();
    const childrenLabel = $("#msg_displayReservationSummary_childrenLabel").text();
    const completeReservationLabel = $("#msg_displayReservationSummary_completeReservationLabel").text();

    // Misafir bilgilerini toplama ve guests dizisine ekleme
    const guestInfo = [];
    $('.guest-firstname').each(function(index) {
        const firstName = $(this).val();
        const lastName = $(`#guestLastName${index + 1}`).val();

        // Mevcut misafiri guestInfo dizisine ekliyoruz
        guestInfo.push({ firstName: firstName, lastName: lastName });

        // Misafir bilgisi guests dizisinde mevcut mu kontrol ediyoruz
        const isGuestExist = guests.some(guest => guest.userName === firstName && guest.userSurName === lastName);

        // Eğer misafir guests dizisinde yoksa, onu ekliyoruz
        if (!isGuestExist) {
            guests.push({
                userName: firstName,
                userSurName: lastName,
                adult: true // Yetişkin misafir olduğu varsayılıyor
            });
        }
    });

    // Çocuk bilgilerini toplama ve guests dizisine ekleme
    const childrenInfo = [];
    $('.child-firstname').each(function(index) {
        const childIndex = index + 1; // Çocuklar için index'i düzenleme
        const firstName = $(this).val();
        const lastName = $(`#childLastName${childIndex}`).val();

        // Mevcut çocuğu childrenInfo dizisine ekliyoruz
        childrenInfo.push({ firstName: firstName, lastName: lastName });

        // Çocuk bilgisi guests dizisinde mevcut mu kontrol ediyoruz
        const isChildExist = guests.some(guest => guest.userName === firstName && guest.userSurName === lastName);

        // Eğer çocuk guests dizisinde yoksa, onu ekliyoruz
        if (!isChildExist) {
            guests.push({
                userName: firstName,
                userSurName: lastName,
                adult: false // Çocuk misafir olduğu varsayılıyor
            });
        }
    });

    // Rezervasyon özetini oluştur
    let summaryHtml = `
        <div>
            <p><strong>${roomNumberLabel}:</strong> ${roomNumber}</p>
            <p><strong>${checkInDateLabel}:</strong> ${checkInDate}</p>
            <p><strong>${checkOutDateLabel}:</strong> ${checkOutDate}</p>
            <p><strong>${pricePerNightLabel}:</strong> $${roomPrice}</p>
            <p><strong>${totalPriceLabel}:</strong> $${totalPrice}</p>
    `;

    if (cancellationFee > 0) {
        summaryHtml += `<p><strong>${cancellationFeeLabel}:</strong> $${cancellationFee.toFixed(2)}</p>`;
    }

    summaryHtml += `
            <h5>${guestsLabel}:</h5>
            <ul>
    `;

    guestInfo.forEach((guest, index) => {
        summaryHtml += `<li>${guest.firstName} ${guest.lastName}</li>`;
    });

    summaryHtml += `
            </ul>
            <h5>${childrenLabel}:</h5>
            <ul>
    `;

    childrenInfo.forEach((child, index) => {
        summaryHtml += `<li>${child.firstName} ${child.lastName}</li>`;
    });

    summaryHtml += `
            </ul>
            <button id="completeReservationButton" class="btn btn-primary">${completeReservationLabel}</button>
        </div>
    `;
    $("#children").val(childrenInfo.length);
    $("#adults").val(guestInfo.length);
    $('#reservationMessage').html(summaryHtml);
    $('#reservationMessage').show();

    $('#step3Button').removeClass('disabled btn-secondary').addClass('btn-primary'); //üçüncü butonu aktif ettim.

    // Rezervasyonu Tamamla butonuna tıklanma olayı ekle
    $('#completeReservationButton').on('click', function() {
        completeReservation(roomNumber, guests, checkInDate, checkOutDate, roomPrice, totalPrice);
    });

    // Adım 3: Rezervasyon Özeti'ne geçiş yap
    $('.step-content').removeClass('active');
    $('#step3').addClass('active');
}

function completeReservation(roomNumber, guests, checkInDate, checkOutDate, roomPrice, totalPrice) {
    const filteredFacilitiesList = [];
    $('#facilities input:checked').each(function() {
        filteredFacilitiesList.push($(this).val());
    });

    const filteredBedTypeList = [];
    $('#bedTypes input:checked').each(function() {
        filteredBedTypeList.push($(this).val());
    });

    const countOfAdult = parseInt($("#adults").val());
    const countOfChild = parseInt($("#children").val());
    const filteredRoomType = $('#roomType').val();
    const filteredSmoking = $('#smokingCheckbox').prop('checked');
    const filteredFloor = $('#floor').val();
    const filteredDisabled = $('#disabledFriendly').prop('checked');
    reservationId = reservationId ? reservationId : 0;
    let cancellationFee = currentReservation==null ? 0 : currentReservation.cancellationFee;
    const reservationDetails = {
        reservationId: reservationId,
        roomNumber: roomNumber,
        check_in: checkInDate,
        check_out: checkOutDate,
        guestList: guests,
        countOfAdult: countOfAdult,
        countOfChild: countOfChild,
        filteredRoomType: filteredRoomType,
        filteredSmoking: filteredSmoking,
        filteredFacilitiesList: filteredFacilitiesList,
        filteredBedTypeList: filteredBedTypeList,
        filteredFloor: filteredFloor,
        filteredDisabled: filteredDisabled,
        totalPrice: totalPrice,
        cancellationFee:cancellationFee
    };

    $.ajax({
        url: "/api/reservation/bookNow",
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(reservationDetails),
        success: function(response) {


            if (response === 'Reservation created successfully !!!') {
                $('.step-content').removeClass('active');
                $('#step3').addClass('active');
                // Translate messages
                if(reservationId){

                const reservationEditSuccessMessage = $("#reservationEditSuccessMessage").text();

                alert(reservationEditSuccessMessage);
                }else{
                const reservationSuccessMessage = $("#reservationSuccessMessage").text();

                alert(reservationSuccessMessage);
                }



                const url = 'http://localhost:8080/Home';
                window.location.href = url;


            } else {
                console.error("Reservation failed: ", response.html);
            }
        },
        error: function(xhr, status, error) {
            console.log("Reservation failed: ", error);
        }
    });
}


function loadRoomTypes() {
    $.ajax({
        url: "/api/roomType/forBookNow",
        type: "GET",
        data: { lang: language },
        success: function(data) {
            var select = $('#roomType');
            select.empty(); // Mevcut seçenekleri temizle

            // Boş bir seçenek ekle
            var defaultOption = $('<option></option>').attr('value', '').text('');
            select.append(defaultOption);

            if (!Array.isArray(data)) {
                console.error('Unexpected response format:', data);
                return;
            }

            $.each(data, function(index, item) {
                if (item && item.roomTypeName) {
                    var option = $('<option></option>').attr('value', item.roomTypeName).text(item.roomTypeName.charAt(0).toUpperCase() + item.roomTypeName.slice(1).toLowerCase());
                    select.append(option);
                } else {
                    console.warn('Skipped item with invalid value:', item);
                }
            });
        },
        error: function() {
            console.error('Failed to fetch room types');
        }
    });
}

function loadFacilities() {
    const facilitiesContainer = $('#facilities');
    facilitiesContainer.empty(); // Mevcut içeriği temizle

    const defaultCheckedFacilities = ["Mini Fridge", "TV", "Airco", "WIFI", "Toilet", "Shower", "Phone", "Water Boiler",
     "迷你冰箱", "电视", "空调", "无线上网", "洗手间", "淋浴", "电话", "开水器"];

    $.ajax({
      url: "http://localhost:8080/api/facilities/forBookNow", // Backend API endpoint'i
      method: 'GET',
      data: { lang: language },
      success: function(facilitiesData) {
        facilitiesData.forEach(facility => {
          const isChecked = defaultCheckedFacilities.includes(facility.facilitiesName);
          const disabledAttribute = isChecked ? 'disabled' : '';
          const checkedAttribute = isChecked ? 'checked' : '';

          const facilityHtml = `
            <div class="form-check">
              <input class="form-check-input" type="checkbox" id="${facility.facilitiesName}" value="${facility.facilitiesName}" ${checkedAttribute} ${disabledAttribute}>
              <label class="form-check-label" for="${facility.facilitiesName}">${facility.facilitiesName}</label>
            </div>
          `;
          facilitiesContainer.append(facilityHtml); // Checkboxları container'a ekle
        });
      },
      error: function(error) {
        console.error('Error loading facilities:', error);
      }
    });
  }

 function loadBedTypes() {
     const bedTypeContainer = $('#bedTypes');
     bedTypeContainer.empty(); // Mevcut içeriği temizle

     $.ajax({
         url: "http://localhost:8080/api/bedType/forBookNow", // Backend API endpoint'i
         method: 'GET',
         data: { lang: language },
         success: function(bedTypesData) {
             bedTypesData.forEach(bedType => {
                 const bedTypeHtml = `
                     <div class="form-check">
                         <input class="form-check-input" type="checkbox" id="${bedType.bedTypeName}" value="${bedType.bedTypeName}">
                         <label class="form-check-label" for="${bedType.bedTypeName}">${bedType.bedTypeName}</label>
                     </div>
                 `;
                 bedTypeContainer.append(bedTypeHtml); // Checkboxları container'a ekle
             });
         },
         error: function(error) {
             console.error('Error loading bed types:', error);
         }
     });
 }

//burada urldeki parametreyi aliyorum.
function getQueryParams() {
      const params = new URLSearchParams(window.location.search);
      const queryParams = {};
      for (const [key, value] of params.entries()) {
        queryParams[key] = value;
      }
      return queryParams;
    }

//burada id ile rezervasyon bilgilerini cekiyorum.
function loadReservationData(reservationId) {
    $.ajax({
        url: `/api/reservation/${reservationId}`,
        method: 'GET',
        data: { lang: language },
        success: function(data) {
            currentReservation = createReservationObject(data);  // Reservation objesini oluştur ve ata
            populateReservationForm(data);
            console.log("currentReservation:", currentReservation);  // currentReservation objesini konsola yazdır
        },
        error: function(xhr, status, error) {
            console.error("Failed to load reservation data: ", error);
        }
    });
}

function populateReservationForm(data) {
    // Step 1: Room Selection
    $('#checkIn').val(data.startDate); // check_in alanını doldur
    $('#checkOut').val(data.endDate); // check_out alanını doldur
    $('#adults').val(data.countOfAdult); // countOfAdult alanını doldur
    $('#children').val(data.countOfChild); // countOfChild alanını doldur
    $('#roomType').val(data.filteredRoomType); // filteredRoomType alanını doldur
    $('#smokingCheckbox').prop('checked', data.filteredSmoking); // filteredSmoking alanını kontrol edip işaretle

    // Apply facilities and bed types filters
    data.filteredFacilitiesList.forEach(facility => {
        $(`#facilities input[value="${facility}"]`).prop('checked', true); // filteredFacilitiesList alanını kontrol edip işaretle
    });

    data.filteredBedTypeList.forEach(bedType => {
        $(`#bedTypes input[value="${bedType}"]`).prop('checked', true); // filteredBedTypeList alanını kontrol edip işaretle
    });

    $('#floor').val(data.filteredFloor); // filteredFloor alanını doldur
    $('#disabledFriendly').prop('checked', data.filteredDisabled); // filteredDisabled alanını kontrol edip işaretle

    // Step 2: Guest Information
    const adultList = data.adultList;
    const childList = data.childList;
    roomChildCapacity= data.roomChildCapacity;
    roomAdultCapacity = data.roomAdultCapacity;
    selectRoom(data.roomNumber, data.roomPrice); // This will generate the guest form with roomPrice

     // Oda bilgilerini getirme
     const selectedRoomNumber = data.roomNumber;
     loadSelectedRoomData(selectedRoomNumber);

    //misafir bilgilerini yukluyorum
    loadGuestInfo(adultList,childList);

    // Move to summary step if necessary
    $('.step-content').removeClass('active');
    $('#step2').addClass('active');
}

function loadGuestInfo(adultList,childList){
    let adultCounter = 0;
    adultList.forEach((guest, index) => {
        // Yetişkin ise yetişkin formunu doldur.
        if (guest.email != null) {
            adultCounter++;
            $(`#guestFirstName${adultCounter}`).val(guest.userName); // userName alanını doldur
            $(`#guestLastName${adultCounter}`).val(guest.userSurName); // userSurName alanını doldur
            $(`#guestPhone${adultCounter}`).val(guest.phoneNumber); // phoneNumber alanını doldur
            $(`#guestEmail${adultCounter}`).val(guest.email); // email alanını doldur
            $(`#addressStreet${adultCounter}`).val(guest.address.street); // address.street alanını doldur
            $(`#addressCity${adultCounter}`).val(guest.address.city); // address.city alanını doldur
            $(`#addressCountry${adultCounter}`).val(guest.address.country); // address.country alanını doldur
            $(`#addressZip${adultCounter}`).val(guest.address.zipCode); // address.zipCode alanını doldur
            $(`#houseNumber${adultCounter}`).val(guest.address.houseNumber); // address.houseNumber alanını doldur
        } else {
            adultCounter++;
            $(`#guestFirstName${adultCounter}`).val(guest.userName); // userName alanını doldur
            $(`#guestLastName${adultCounter}`).val(guest.userSurName); // userSurName alanını doldur
        }
    });

    let childCounter = parseInt($('#adults').val()) + 1;
    childList.forEach((guest, index) => {
        // Çocuk ise çocuk formunu doldur
        $(`#childFirstName${childCounter}`).val(guest.userName); // userName alanını doldur
        $(`#childLastName${childCounter}`).val(guest.userSurName); // userSurName alanını doldur
        childCounter++;
    });
}

function createReservationObject(data) {
    return {
        id: data.reservationId,
        roomNumber: data.roomNumber,
        roomType: data.filteredRoomType,
        roomPrice: data.roomPrice,
        totalPrice: data.totalPrice,
        adults: data.adultList,
        children: data.childList,
        countOfAdults: data.countOfAdult,
        countOfChildren: data.countOfChild,
        smoking: data.filteredSmoking,
        facilities: data.filteredFacilitiesList,
        bedTypes: data.filteredBedTypeList,
        floor: data.filteredFloor,
        disabledFriendly: data.filteredDisabled,
        cancellationFee: data.cancellationFee,
        checkIn: new Date(data.startDate),
        checkOut: new Date(data.endDate),
        transactionDateTime: new Date(data.transactionDateTime)
    };
}


function checkRoomAvailability(roomNumber, oldCheckOutDate, newCheckOutDate) {
    $.ajax({
        url: '/api/reservation/availability',
        type: 'GET',
        data: {
            roomNumber: roomNumber,
            oldCheckOutDate: oldCheckOutDate,
            newCheckOutDate: newCheckOutDate
        },
        success: function(isAvailable) {
            const roomAvailableMessage = $("#msg_checkRoomAvailability_roomAvailableMessage").text();
            const roomNotAvailableMessage = $("#msg_checkRoomAvailability_roomNotAvailableMessage").text();
            const errorCheckingAvailabilityMessage = $("#msg_checkRoomAvailability_errorCheckingAvailabilityMessage").text();

            if (isAvailable) {
                //alert(roomAvailableMessage);
            } else {
                alert(roomNotAvailableMessage);

                $('#checkOut').val(oldCheckOutDate); // Eski check-out tarihini geri setle
            }
        },
        error: function() {
            alert($("#msg_checkRoomAvailability_errorCheckingAvailabilityMessage").text());
        }
    });
}


function loadSelectedRoomData(roomNumber) {
var dil= language;
    $.ajax({
        url: `/api/room/${roomNumber}?lang=${language}`, // Oda numarasına göre oda bilgisini getiren endpoint
        method: 'GET',
        success: function(roomData) {
            // Oda verisini sayfada ilgili yerlere yerleştirme
            displaySelectedRoom(roomData);
        },
        error: function(xhr, status, error) {
            console.error("Failed to load room data: ", error);
        }
    });
}

function displaySelectedRoom(roomData) {
    // Translate messages
    const priceLabel = $("#msg_displaySelectedRoom_priceLabel").text();
    const descriptionLabel = $("#msg_displaySelectedRoom_descriptionLabel").text();
    const facilitiesLabel = $("#msg_displaySelectedRoom_facilitiesLabel").text();
    const smokingLabel = $("#msg_smokingLabel").text();
    const bedsLabel = $("#msg_displaySelectedRoom_bedsLabel").text();
    const adultsLabel = $("#msg_displaySelectedRoom_adultsLabel").text();
    const childrenLabel = $("#msg_displaySelectedRoom_childrenLabel").text();
    const floorLabel = $("#msg_displaySelectedRoom_floorLabel").text();
    const selectedRoomLabel = $("#msg_displaySelectedRoom_selectedRoomLabel").text();
    const disabledLabel = $("#msg_displaySelectedRoom_disabledLabel").text();
    const disabledStatus = roomData.disabled ? $("#msg_yes").text() : $("#msg_no").text();
    const smokingAllowedMessage = $("#msg_smokingAllowedMessage").text();
    const smokingNotAllowedMessage = $("#msg_smokingNotAllowedMessage").text();
    // Oda bilgilerini ilgili div içinde gösterme ve seçilmiş olarak işaretleme
    const formattedBedTypes = formatBedTypes(roomData.roomBedType);
    const roomHtml = `
        <div class="col-12 room-container">
            <div class="room-card">
                <div class="room-images">
                    ${roomData.imageUrlList.slice(0, 5).map(url => `<img src="${url}" alt="Room Image" class="room-image">`).join('')}
                </div>
                <div class="room-details">
                    <h4>${roomData.roomType} - ${roomData.roomNumber}</h4>
                    <p><strong>${priceLabel}:</strong> $${roomData.price}</p>
                    <p><strong>${descriptionLabel}:</strong> ${roomData.description}</p>
                    <p><strong>${facilitiesLabel}:</strong></p>
                    <div class="facilities">
                        ${roomData.facilitiesList.map(facility => `<div class="facility">${facility}</div>`).join('')}
                    </div>
                    <p><strong>${smokingLabel}:</strong> ${roomData.smoking ? smokingAllowedMessage : smokingNotAllowedMessage}</p>
                    <p><strong>${bedsLabel}:</strong> ${formattedBedTypes}</p>
                    <p><strong>${adultsLabel}:</strong> ${roomData.countOfAdult}</p>
                    <p><strong>${childrenLabel}:</strong> ${roomData.countOfChild}</p>
                    <p><strong>${floorLabel}:</strong> ${roomData.thFloor}</p>
                    <p><strong>${disabledLabel}:</strong> ${disabledStatus}</p>
                    <button class="select-room-btn btn-success" disabled>
                        ${selectedRoomLabel}
                    </button>
                </div>
            </div>
        </div>
    `;

    $('#roomList').html(roomHtml); // Oda listesini bu oda ile doldur
}

function formatBedTypes(bedTypeList) {
    const bedTypeCounts = bedTypeList.reduce((acc, type) => {
        acc[type] = (acc[type] || 0) + 1;
        return acc;
    }, {});

    return Object.entries(bedTypeCounts)
        .map(([type, count]) => `${type}: ${count}x`)
        .join(', ');
}

function fetchUserEmailAndPopulateForm() {
    $.ajax({
        url: "/api/users/getEmail",
        method: "GET",
        success: function(email) {
            if (email && email.trim() !== "") {  // Eğer email varsa ve boş değilse
                checkUserRoleAndPopulateForm(email);
            } else {
                console.log("Kullanıcı anonim, form otomatik olarak doldurulmayacak.");
            }
        },
        error: function(error) {
            console.error("Error fetching user email:", error);
        }
    });
}

function checkUserRoleAndPopulateForm(email) {
    $.ajax({
        url: `/api/users/checkUserInfo?email=${email}`,
        method: 'GET',
        success: function(data) {
            if (data.userRoleType === 'GUEST') {
                populatePrimaryGuestForm(data);
            }
        },
        error: function(error) {
            console.error('Error fetching user info:', error);
        }
    });
}

function populatePrimaryGuestForm(user) {
    $('#guestFirstName1').val(user.userName);
    $('#guestLastName1').val(user.userSurName);
    $('#guestPhone1').val(user.phoneNumber);
    $('#guestEmail1').val(user.email);
    $('#addressStreet1').val(user.address.street);
    $('#addressCity1').val(user.address.city);
    $('#addressCountry1').val(user.address.country);
    $('#addressZip1').val(user.address.zipCode);
    $('#houseNumber1').val(user.address.houseNumber);
}