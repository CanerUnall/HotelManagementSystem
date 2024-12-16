// Declare a variable that holds a reference to the datatable,
// so that we can use it in every function.
var hotelAmenitiesTable;

function init(){

    console.log('inside init');


    $("#new-hotelAmenities-button").click(function () {
        console.log("Inside click of new-hotelAmenities-button");
        $('#hotelAmenities-modal').modal('show');
    });

    $("#edit-hotelAmenities-button").click(function() {
        console.log("Inside click of edit-hotelAmenities-button");

        if (hotelAmenitiesTable.row($('.selected')).data() == undefined) {
            var selectMessage = $("#select-hotelAmenities-message").text();
            alert(selectMessage);
        } else {
            var hotelAmenities = hotelAmenitiesTable.row($('.selected')).data();

            $("#hotelAmenitiesId").val(hotelAmenities.hotelAmenitiesId);
            $("#hotelAmenitiesName").val(hotelAmenities.hotelAmenitiesName);
            $("#hotelAmenitiesNameZh").val(hotelAmenities.hotelAmenitiesNameZh);
            $("#hotelAmenitiesContent").val(hotelAmenities.hotelAmenitiesContent);
            $("#hotelAmenitiesContentZh").val(hotelAmenities.hotelAmenitiesContentZh);

            $("#icon").val(hotelAmenities.icon);

            $('#hotelAmenities-modal').modal('show');
        }
    });



    $("#delete-hotelAmenities-button").click(function () {
        console.log("Inside click of delete-hotelAmenities-button");
        // Get the data from selected row and fill fields in modal

        if (hotelAmenitiesTable.row($('.selected')).data() == undefined) {
            var selectMessage = $("#select-hotelAmenities-message").text();
            alert(selectMessage);
        } else {
            $('#hotelAmenities-delete-modal').modal('show');
        }
    });

    // Button in delete modal
    $("#delete-hotelAmenities-confirm-button").click(function () {
        console.log("Inside click of delete-hotelAmenities-confirm-button");
        deleteHotelAmenities();
        $('#hotelAmenities-delete-modal').modal('hide');
    });

    // Add submit event to form for new and edit
    $("#hotelAmenities-form").on('submit', function() {
        console.log("Submitting");
        createHotelAmenities();
        $('#hotelAmenities-modal').modal('hide');
    });

    initHotelAmenitiesTable();
}

function initHotelAmenitiesTable() {
    console.log('inside initHotelAmenitiesTable');

    // Create columns (with titles) for datatable: id, name, address, age
    // Note: The 'ID' column is hidden but the value is available
    var columns = [
        { "data": "hotelAmenitiesId" },
        { "data": "hotelAmenitiesName" },
        { "data": "hotelAmenitiesNameZh" },
        { "data": "hotelAmenitiesContent" },
        { "data": "hotelAmenitiesContentZh" },
        { "data": "icon" }

    ];

    // Define new datatable with above columns and assign it to the variable
    hotelAmenitiesTable = $("#hotelAmenities-table").DataTable({
        "order": [[0, "asc"]],
        "columns": columns,
        "language": {
                             "url": languageUrl
                            }
    });

    $("#hotelAmenities-table tbody").on('click', 'tr', function () {
        console.log("Clicking on row");
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            hotelAmenitiesTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    });

    // Get the data from the 'backend' and refresh the table
    getHotelAmenitiesData();
}


//function showImage(url) {
//    $('#image-modal').modal('show');
//    $('#modal-image').attr('src', url);
//}

function createHotelAmenities() {
    console.log('inside createHotelAmenities');

    // Put hotelAmenities data from page in Javascript object --- SIMILAR TO JSON
    var hotelAmenitiesData = {
        hotelAmenitiesId: $("#hotelAmenitiesId").val(),
        hotelAmenitiesName: $("#hotelAmenitiesName").val(),
        hotelAmenitiesNameZh: $("#hotelAmenitiesNameZh").val(),
        hotelAmenitiesContent: $("#hotelAmenitiesContent").val(),
        hotelAmenitiesContentZh: $("#hotelAmenitiesContentZh").val(),
        icon: $("#icon").val()
    };

    // Transform Javascript object to json
    var hotelAmenitiesJson = JSON.stringify(hotelAmenitiesData);

    console.log(hotelAmenitiesJson);

    $.ajax({
        url: "/api/hotelAmenities",
        type: "post",
        data: hotelAmenitiesJson,    // json for request body
        contentType: "application/json; charset=utf-8",   // What we send to frontend
        dataType: "json",  // What get back from frontend
        success: function(hotelAmenities) {
            // hotelAmenities object is returned but we don't use it
            console.log(hotelAmenities);

            // Clear fields in modal page
            $("#hotelAmenitiesId").val('');
            $("#hotelAmenitiesName").val('');
            $("#hotelAmenitiesNameZh").val('');
            $("#hotelAmenitiesContent").val('');
            $("#hotelAmenitiesContentZh").val('');
            $("#icon").val('');

            // Refresh table data
            getHotelAmenitiesData();
        },
        fail: function (error) {
            console.log('Error: ' + error);
        }
    });
}

function getHotelAmenitiesData() {
    console.log('inside getHotelAmenitiesData');

    $.ajax({
        url: "/api/hotelAmenities",
        type: "get",
        dataType: "json",  // get back from frontend
        success: function(hotelAmenities) {
            console.log(hotelAmenities);

            // Clear fields in page
            hotelAmenitiesTable.clear();
            hotelAmenitiesTable.rows.add(hotelAmenities);
            hotelAmenitiesTable.columns.adjust().draw();
        },
        fail: function (error) {
            console.log('Error: ' + error);
        }
    });
}

function deleteHotelAmenities() {
    if (hotelAmenitiesTable.row($('.selected')).data() == undefined) {
        alert("Select hotelAmenities first");
    } else {
        var hotelAmenities = hotelAmenitiesTable.row($('.selected')).data();

        $.ajax({
            url: '/api/hotelAmenities/' + hotelAmenities.hotelAmenitiesId,
            type: "delete",
            dataType: "text",  // get back from frontend
            success: function(message) {
                console.log(message);

                // Refresh table data
                getHotelAmenitiesData();
            },
            fail: function (error) {
                console.log('Error: ' + error);
            }
        });
    }
}
