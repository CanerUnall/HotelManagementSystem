// Declare a variable that holds a reference to the datatable,
// so that we can use it in every function.
var facilitiesTable;

function init(){

    console.log('inside init' );

    $("#new-facilities-button").click( function () {
        console.log("Inside click of new-facilities-button");
        $('#facilities-modal').modal('show');
    });

    $("#edit-facilities-button").click( function () {
        console.log("Inside click of edit-facilities-button");
        // Get the data from selected row and fill fields in modal

        if (facilitiesTable.row($('.selected')).data() == undefined) {
             var selectMessage = $("#select-facilities-message").text();
                        alert(selectMessage);
        }else{
            var facilities = facilitiesTable.row($('.selected')).data();

            $("#facilitiesId").val(facilities.facilitiesId);
            $("#facilitiesName").val(facilities.facilitiesName);
            $("#facilitiesNameZh").val(facilities.facilitiesNameZh);

            $('#facilities-modal').modal('show');
        }
    });


    $("#delete-facilities-button").click( function () {
        console.log("Inside click of delete-facilities-button");
        // Get the data from selected row and fill fields in modal

        if (facilitiesTable.row($('.selected')).data() == undefined) {
               var selectMessage = $("#select-facilities-message").text();
               alert(selectMessage);
        }else{
            $('#facilities-delete-modal').modal('show');
        }

    });

    // Button in delete modal
    $("#delete-facilities-confirm-button").click( function () {
        console.log("Inside click of delete-facilities-confirm-button");
        deleteFacilities();
        $('#facilities-delete-modal').modal('hide');
    });

    // Add submit event to form for new and edit
    $("#facilities-form").on('submit', function() {
        console.log("Submitting");
        createFacilities();
        $('#facilities-modal').modal('hide');
    });

    initFacilitiesTable();
}

function initFacilitiesTable() {

    console.log('inside init facilitiesTable' );

    // Create columns (with titles) for datatable: id, name, address, age
    // Note: The 'ID' column is hidden but the value is avaliable
    columns = [
        {"data": "facilitiesId" },
        {"data": "facilitiesName" },
        {"data": "facilitiesNameZh"}

    ];

    // Define new datatable with above columns and assign it to the variable
    facilitiesTable = $("#facilities-table").DataTable( {
        "order": [[ 0, "desc" ]],
        "columns": columns,
         "language": {
                                    "url": languageUrl
                                }
    });

    $("#facilities-table tbody").on( 'click', 'tr', function () {
        console.log("Clicking on row");
        if ( $(this).hasClass('selected') ) {
          $(this).removeClass('selected');
        }
        else {
            facilitiesTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    });

    // Get the data from the 'backend' and refresh the table
    getFacilitiesData();
}

function createFacilities(){

    console.log('inside create facilities' );

    // Put facilities data from page in Javascript object --- SIMILAR TO JSON
    var facilitiesData = {
            facilitiesId: $("#facilitiesId").val(),
            facilitiesName: $("#facilitiesName").val(),

            facilitiesNameZh: $("#facilitiesNameZh").val()

    }

    // Transform Javascript object to json
    var facilitiesJson = JSON.stringify(facilitiesData);

    console.log(facilitiesJson);

    $.ajax({
        url: "/api/facilities",
        type: "post",
        data: facilitiesJson,    // json for request body
        contentType:"application/json; charset=utf-8",   // What we send to frontend
        dataType: "json",  // What get back from frontend
        // success: function(facilities, textStatus, jqXHR){
        success: function(facilities){

          // facilities object is returned but we don't use it
          console.log(facilities);

          // Clear fields in modal page
          $("#facilitiesId").val('');
          $("#facilitiesName").val('');
          $("#facilitiesNameZh").val('');


          // Refresh table data
          getFacilitiesData();

        },

        fail: function (error) {
          console.log('Error: ' + error);
        }

    });
}

function getFacilitiesData(){

    console.log('inside get facilities Data' );

    // Get facilities from the FAKE backend by using a FAKE JQuery class
    // See file myjquery.js
    // We pass a JAVASCRIPT object with everything needed for a REAL JQuery call.
    // Our FAKE JQuery only uses:
    // url
    // success (which is a function)
    // fail (which is a function)
    // When we have a REAL backend we only need to change $$ to $
    $.ajax({
        url: "/api/facilities",
        type: "get",
        dataType: "json",  // get back from frontend
        // success: function(facilities, textStatus, jqXHR){
        success: function(facilities){

          console.log(facilities);

          // Clear fields in page
          facilitiesTable.clear();
          facilitiesTable.rows.add(facilities);
          facilitiesTable.columns.adjust().draw();

        },

        fail: function (error) {
          console.log('Error: ' + error);
        }

    });
}

function deleteFacilities(){

            var facilities = facilitiesTable.row($('.selected')).data();

            $.ajax({
            url: '/api/facilities/can-delete/' + facilities.facilitiesId,
            type: "get",
            dataType: "json",
            success: function(canDelete) {
                if (canDelete) {

                        $.ajax({
                            url: '/api/facilities/' + facilities.facilitiesName,
                            type: "delete",
                            dataType: "text",  // get back from frontend
                            // success: function(facilities, textStatus, jqXHR){
                            success: function(message){

                              console.log(message);

                              // Refresh table data
                              getFacilitiesData();

                            },

                            fail: function (error) {


                            }

                        });
            } else {

                if(language=="zh"){
                alert("该设施已被房间使用，无法删除。");
                }else{
                alert("This facility is in use by a room and cannot be deleted.");

                }
            }
        },
        error: function(xhr, status, error) {
            alert('Error: ' + xhr.responseText);
        }
    });
}
