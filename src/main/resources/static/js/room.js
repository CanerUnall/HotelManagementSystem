// Declare a variable that holds a reference to the datatable,
// so that we can use it in every function.
var roomTable;
var messages = {
    en: {
        successCompleted: 'Successfully Completed!',
        selectRoomFirst: 'Select room first',
        errorDuringProcess: 'An error occurred during the process.',
        facilities: 'Facilities',
        bedTypes: 'Bed Types',
        description: 'Description',
        yes: 'Yes',
        no: 'No',
        pleaseSelect: 'Please select'
    },
    zh: {
        successCompleted: '成功完成!',
        selectRoomFirst: '请先选择房间',
        errorDuringProcess: '处理过程中发生错误。',
        facilities: '设施',
        bedTypes: '床型',
        description: '描述',
        yes: '是',
        no: '否',
        pleaseSelect:'请选择'
    }
};

function init(){

    $('#roomForm').on('submit', function(e) {
        e.preventDefault();

        var formData = new FormData();
        formData.append('roomId', $('#roomId').val());
        formData.append('roomNumber', $('#roomNumber').val());
        formData.append('roomType', $('#roomType1').val());
        formData.append('countOfAdult', $('#countOfAdult').val());
        formData.append('countOfChild', $('#countOfChild').val());
        formData.append('floor', $('#floor').val());
        formData.append('price', $('#price').val());
        formData.append('facilitiesList', saveFacilities());
        formData.append('roomBedTypeList', generateBedTypeString());
        formData.append('cleaned', $('#cleaned').is(':checked'));
        formData.append('smoking', $('#smoking').is(':checked'));
        formData.append('description', $('#description').val());
        formData.append('disabled', $('#disabled').is(':checked'));

        var files = $('#photos')[0].files;
        if (files.length > 0) {
            for (var i = 0; i < files.length; i++) {
                formData.append('photos', files[i]);
            }
        }

        $.ajax({
            url: '/api/room',
            type: 'POST',
            processData: false,
            contentType: false,
            data: formData,
            success: function(response) {
               alert(messages[language].successCompleted);
                $('#room-modal').modal('hide');
                getRoomData();
            },
            error: function(jqXHR, textStatus, errorThrown) {
                // alert('İşlem sırasında bir hata meydana geldi: ' + textStatus);
            }
        });
    });

$('#roomNumber').on('input', function() {
        const roomNumber = $(this).val();

        if (roomNumber.length > 0) {
            $.ajax({
                url: `/api/room/checkRoomNumber`,
                type: 'GET',
                data: { roomNumber: roomNumber },
                success: function(isUnique) {
                    if (isUnique) {
                        $('#roomNumber-status').html('✔️').css('color', 'green');
                    } else {
                        $('#roomNumber-status').html('❌').css('color', 'red');
                    }
                },
                error: function(error) {
                    console.error('Error checking room number:', error);
                    $('#roomNumber-status').html('❓').css('color', 'orange');
                }
            });
        } else {
            $('#roomNumber-status').empty(); // Input boşsa hiçbir şey gösterme
        }
    });
    console.log('inside init' );

       loadFacilities();
       loadBedTypes();
       loadRoomTypes();

    $("#new-room-button").click( function () {
        console.log("Inside click of new-room-button");
        $('#room-modal').modal('show');
    });

    $("#edit-room-button").click(function () {
        console.log("Inside click of edit-room-button");

        // Seçilen satırdan veriyi alın
        const selectedRow = roomTable.row($('.selected')).data();

        if (!selectedRow) {
           var selectMessage = $("#select-room-message").text();
               alert(messages[language].selectRoomFirst);
            return; // İşlemi durdur
        }

        const room = selectedRow;

        // Alanları doldur
        $("#roomId").val(room.roomId);
        $("#roomNumber").val(room.roomNumber);
        $("#roomType1").val(room.roomType);
        $("#countOfAdult").val(room.countOfAdult);
        $("#countOfChild").val(room.countOfChild);
        $("#floor").val(room.thFloor); // Corrected the property from room.thFloor to room.floor
        $("#price").val(room.price);
        $("#description").val(room.description);
        $("#cleaned").prop('checked', room.cleaned);
        $("#smoking").prop('checked', room.smoking);
        $("#disabled").prop('checked', room.disabled);

        // Facilities'i modal'a yerleştir
        $('#facilities1 .form-check-input').prop('checked', false); // Önce tüm checkbox'ları temizle
        if (Array.isArray(room.facilitiesList)) {
            room.facilitiesList.forEach(facility => {
                $(`#facilities1 input[value="${facility}"]`).prop('checked', true);
            });
        }

        // Bed type'ları modal'da göstermek için
        loadAndPopulateBedTypes(room.roomBedType);


        // Image URL'leri modal'a yerleştir
        $("#imageContainer").empty(); // Önce önceki görüntüleri temizle
        if (Array.isArray(room.imageUrlList)) {
            room.imageUrlList.forEach(url => {
                // Modalda görüntüleri göstermek için bir eleman ekleme kodu
                $("#imageContainer").append(`<img src="${url}" alt="Room Image" style="width:100px;height:100px; margin-right:5px;"/>`);
            });
        }

        $('#room-modal').modal('show');
    });





    $("#delete-room-button").click( function () {
        console.log("Inside click of delete-room-button");
        // Get the data from selected row and fill fields in modal

        if (roomTable.row($('.selected')).data() == undefined) {
            var selectMessage = $("#select-room-message").text();
               alert(messages[language].selectRoomFirst);
        }else{
            $('#room-delete-modal').modal('show');
        }

    });

    // Button in delete modal
    $("#delete-room-confirm-button").click( function () {
        console.log("Inside click of delete-room-confirm-button");
        deleteRoom();
        $('#room-delete-modal').modal('hide');
    });

    initRoomTable();
}

function initRoomTable() {
        console.log('inside initRoomTable');
var viewDescriptionText = document.getElementById('viewDescription').textContent;
var viewFacilitiesText = document.getElementById('viewFacilities').textContent;
var viewBedTypeText = document.getElementById('viewBedType').textContent;
        columns = [
            { data: 'roomId' },
            { data: 'roomNumber' },
            { data: 'roomType' },
            { data: 'countOfAdult' },
            { data: 'countOfChild' },
            { data: 'thFloor' },
            { data: 'price' },
            {data: 'description',
                   render: function(data) {
                       return `<a href="#" class="view-description" data-description="${data}">${viewDescriptionText}</a>`;
                   }
               },
            {data: 'facilitiesList',
                render: function(data) {
                    return `<a href="#" class="view-facilities" data-facilities='${JSON.stringify(data)}'>${viewFacilitiesText}</a>`;
                }
            },
            {
                data: 'roomBedType',
                render: function(data) {
                    return `<a href="#" class="view-bed-types" data-bedtypes='${JSON.stringify(data)}'>${viewBedTypeText}</a>` ;
                }
            },
            {
                data: 'cleaned',

                            "render": function(data) {
                             if (language === "zh") {
                                 return data ? '是' : '否';
                             } else{
                                 return data ? 'Yes' : 'No';
                             }

                            }
            },
            {
                data: 'smoking',

                           "render": function(data) {
                            if (language === "zh") {
                                return data ? '是' : '否';
                            } else{
                                return data ? 'Yes' : 'No';
                            }

                           }
            },
            {
                data: 'disabled',

                           "render": function(data) {
                            if (language === "zh") {
                                return data ? '是' : '否';
                            } else{
                                return data ? 'Yes' : 'No';
                            }

                           }
            },
            {
                data: 'imageUrlList',
                render: function(data) {
                    if (Array.isArray(data)) {
                        return data.map(url =>
                            `<img src="${url}" style="width:20px;height:20px; margin-right: 5px;" onclick="showImage('${url}')" />`
                        ).join('');
                    }
                    return '';
                }
            }
        ];

        roomTable = $("#room-table1").DataTable({
            "order": [[0, "desc"]],
            "columns": columns,
            "language": {
                        "url": languageUrl
                            }
        });

        // Facilities linkine tıklandığında modal açılır ve içerik dinamik olarak yüklenir
        $('#room-table1 tbody').on('click', '.view-facilities', function(e) {
            e.preventDefault();
            var facilities = $(this).data('facilities');
            $('#details-modal-title').text(messages[language].facilities);
            $('#details-modal-body').html('<ul>' + facilities.map(f => `<li>${f}</li>`).join('') + '</ul>');
            $('#details-modal').modal('show');
        });

       // Bed Types linkine tıklandığında modal açılır ve içerik dinamik olarak yüklenir
       $('#room-table1 tbody').on('click', '.view-bed-types', function(e) {
           e.preventDefault();

           var bedTypes = $(this).data('bedtypes');

           // Bed type sayımlarını hesaplama
           const bedTypeCounts = bedTypes.reduce((acc, type) => {
               acc[type] = (acc[type] || 0) + 1;
               return acc;
           }, {});

           // Bed type'ları listelenecek formatta oluşturma
           const bedTypeListHtml = Object.keys(bedTypeCounts).map(type => {
               return `<li>${type} : ${bedTypeCounts[type]}x</li>`;
           }).join('');

           $('#details-modal-title').text(messages[language].bedType);
           $('#details-modal-body').html('<ul>' + bedTypeListHtml + '</ul>');
           $('#details-modal').modal('show');
       });

       $('#room-table1 tbody').on('click', '.view-description', function(e) {
           e.preventDefault();
           var description = $(this).data('description');
           $('#details-modal-title').text(messages[language].description);
           $('#details-modal-body').text(description);
           $('#details-modal').modal('show');
       });


    $("#room-table1 tbody").on('click', 'tr', function() {
        console.log("Clicking on row");
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            roomTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    });

    getRoomData();
}

// Function to show image in a larger view
function showImage(url) {
    $('#image-modal').modal('show');
    $('#modal-image').attr('src', url);
}


function populateBedTypes() {
    const bedTypesContainer = $('#bedTypes1');
    bedTypesContainer.empty(); // Önceki içerikleri temizle

    bedTypes.forEach(bedType => {
        bedTypesContainer.append(`
            <div class="form-group bed-type-item">
                <label>${bedType.type}:</label>
                <input type="number" class="form-control bed-type-count" value="${0}" min="0" />
                <input type="hidden" class="bed-type-name" value="${bedType.type}" />
            </div>
        `);
    });
}

// Yatak türleri ve sayıları için liste oluşturma
function generateBedTypeList() {
    var bedTypeList = [];

    $('.bed-type-item').each(function() {
        var type = $(this).find('.bed-type-name').val();
        var count = parseInt($(this).find('.bed-type-count').val(), 10);

        for (var i = 0; i < count; i++) {
            bedTypeList.push(type);
        }
    });

    return bedTypeList;
}

function getRoomData(){

    console.log('inside getRoomData' );

    // Get rooms from the FAKE backend by using a FAKE JQuery class
    // See file myjquery.js
    // We pass a JAVASCRIPT object with everything needed for a REAL JQuery call.
    // Our FAKE JQuery only uses:
    // url
    // success (which is a function)
    // fail (which is a function)
    // When we have a REAL backend we only need to change $$ to $
    $.ajax({
        url: "/api/room",
        type: "get",
        dataType: "json",  // get back from frontend
        data: { lang: language }, // Dil bilgisini gönderme
        // success: function(rooms, textStatus, jqXHR){
        success: function(rooms){

          console.log(rooms);

          // Clear fields in page
          roomTable.clear();
          roomTable.rows.add(rooms);
          roomTable.columns.adjust().draw();

        },

        fail: function (error) {
          console.log('Error: ' + error);
        }

    });
}

function deleteRoom() {
    var room = roomTable.row($('.selected')).data();

    if (!room) {

        if(language=="zh"){
                      alert('请先选择房间');
                        }else{


         alert('Please select a room first');
                        }
        return;
    }

    $.ajax({
        url: '/api/room/can-delete/' + room.roomId,
        type: "get",
        dataType: "json",
        success: function(canDelete) {
            if (canDelete) {
                $.ajax({
                    url: '/api/room/deleteById/' + room.roomId,
                    type: "delete",
                    dataType: "text",
                    success: function(message) {

                        if(language=="zh"){
                                              alert('房间删除成功');
                                                }else{

                                alert('Room deleted successfully');

                                                }
                        getRoomData();
                    },
                    error: function(xhr, status, error) {
                        alert('Error: ' + xhr.responseText);
                    }
                });
            } else {
            if(language=="zh"){
                                  alert('该房间已被有效或未来预订，无法删除。');
                                    }else{

                    alert('This room has active or future reservations and cannot be deleted.');

                                    }

            }
        },
        error: function(xhr, status, error) {
            alert('Error: ' + xhr.responseText);
        }
    });
}


function loadRoomTypes() {
    $.ajax({
        url: "/api/roomType/forBookNow",
        type: "GET",
        data: { lang: language },
        success: function(data) {
            var select = $('#roomType1');
            select.empty(); // Mevcut seçenekleri temizle

            // Boş bir seçenek ekle
            var defaultOption = $('<option></option>').attr('value', '').text(messages[language].pleaseSelect);
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
    const facilitiesContainer = $('#facilities1');
    facilitiesContainer.empty(); // Mevcut içeriği temizle

    const defaultCheckedFacilities = ["Mini Fridge", "TV", "Airco", "WIFI", "Toilet", "Shower", "Phone", "Water Boiler"];

    $.ajax({
      url: "/api/facilities/forBookNow", // Backend API endpoint'i
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
    const bedTypeContainer = $('#bedTypes1');
    bedTypeContainer.empty(); // Mevcut içeriği temizle

    $.ajax({
        url: "/api/bedType/forBookNow", // Backend API endpoint'i
        method: 'GET',
        data: { lang: language },
        success: function(bedTypesData) {
            bedTypesData.forEach(bedType => {
                const bedTypeHtml = `
                    <div class="form-check form-check-inline bed-type-item">
                        <label class="form-check-label" for="${bedType.bedTypeName}">${bedType.bedTypeName}</label>
                        <input type="number" class="form-control bed-type-count" id="${bedType.bedTypeName}-count" value="0" min="0">
                        <input type="hidden" class="bed-type-name" value="${bedType.bedTypeName}" />
                    </div>
                `;
                bedTypeContainer.append(bedTypeHtml); // Sadece yatak türü ismi ve sayı girme alanı ekle
            });
        },
        error: function(error) {
            console.error('Error loading bed types:', error);
        }
    });
}




 // Kaydedilen bed types ve adetlerini alacak fonksiyon
function saveBedTypes() {
    const selectedBedTypes = [];
    $('#bedTypes1 .form-check-input:checked').each(function() {
        selectedBedTypes.push($(this).val());
    });
    return selectedBedTypes;
}

function saveFacilities() {
    const selectedFacilities = [];
    $('#facilities1 .form-check-input:checked').each(function() {
        selectedFacilities.push($(this).val());
    });
    return selectedFacilities;
}

function generateBedTypeString() {
    var bedTypeList = [];

    // Bed types form elemanlarını dolaş
    $('#bedTypes1 .bed-type-item').each(function() {
        var type = $(this).find('.bed-type-name').val();
        var count = parseInt($(this).find('.bed-type-count').val(), 10);

        // Eğer sayısal değer geçerli bir sayıysa ve 0'dan büyükse işleme devam et
        if (!isNaN(count) && count > 0) {
            // Kullanıcının girdiği sayı kadar bu bed type'ı listeye ekle
            for (var i = 0; i < count; i++) {
                bedTypeList.push(type);
            }
        }
    });

    // Listeyi virgülle ayırarak bir String'e dönüştür
    return bedTypeList.join(',');
}

function loadAndPopulateBedTypes(existingBedTypes) {
    const bedTypeContainer = $('#bedTypes1');
    bedTypeContainer.empty(); // Mevcut içeriği temizle

    $.ajax({
        url: "/api/bedType", // Backend API endpoint'i
        method: 'GET',
        success: function(bedTypesData) {
            // Yatak türü sayımlarını tespit et
            const bedTypeCounts = existingBedTypes.reduce((acc, type) => {
                acc[type] = (acc[type] || 0) + 1;
                return acc;
            }, {});

            bedTypesData.forEach(bedType => {
                const count = bedTypeCounts[bedType.bedTypeName] || 0;

                const bedTypeHtml = `
                    <div class="form-check form-check-inline bed-type-item">
                        <label class="form-check-label" for="${bedType.bedTypeName}">${bedType.bedTypeName}</label>
                        <input type="number" class="form-control bed-type-count" id="${bedType.bedTypeName}-count" value="${count}" min="0">
                        <input type="hidden" class="bed-type-name" value="${bedType.bedTypeName}" />
                    </div>
                `;
                bedTypeContainer.append(bedTypeHtml);
            });
        },
        error: function(error) {
            console.error('Error loading bed types:', error);
        }
    });
}

