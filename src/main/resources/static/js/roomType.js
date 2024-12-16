// Declare a variable that holds a reference to the datatable,
// so that we can use it in every function.
var roomTypeTable;
var messages = {
    en: {
        selectRoomType: "Please select a room type." ,
        deleteConfirm: "Are you sure you want to delete this room type?"
    },
    zh: {
        selectRoomType: "请选择一个房型。" ,
        deleteConfirm: "您确定要删除此房型吗？"
    }
};
function init(){

    console.log('inside init' );

    $("#reset-button").click( function () {
        reset();
    });

    $("#new-roomType-button").click( function () {
        console.log("Inside click of new-roomType-button");
        $('#roomType-modal').modal('show');
    });

    $("#edit-roomType-button").click( function () {
        console.log("Inside click of edit-roomType-button");
        // Get the data from selected row and fill fields in modal

       if (roomTypeTable.row($('.selected')).data() == undefined) {
                       alert(messages[language].selectRoomType);
                   } else {
                       var roomType = roomTypeTable.row($('.selected')).data();
                       }

            $("#roomTypeId").val(roomType.roomTypeId);
            $("#roomTypeName").val(roomType.roomTypeName);
            $("#roomTypeNameZh").val(roomType.roomTypeNameZh);

            $('#roomType-modal').modal('show');

    });


    $("#delete-roomType-button").click( function () {
        console.log("Inside click of delete-roomType-button");
        // Get the data from selected row and fill fields in modal

         if (roomTypeTable.row($('.selected')).data() == undefined) {
                        alert(messages[language].selectRoomType);
                    } else {
                        $('#delete-confirm-message').text(messages[language].deleteConfirm);
                        $('#roomType-delete-modal').modal('show');
                    }

    });

    // Button in delete modal
    $("#delete-roomType-confirm-button").click( function () {
        console.log("Inside click of delete-roomType-confirm-button");
        deleteRoomType();
        $('#customer-delete-modal').modal('hide');
    });

    // Add submit event to form for new and edit
    $("#roomType-form").on('submit', function() {
        console.log("Submitting");
        createRoomType();
        $('#roomType-modal').modal('hide');
    });

    initRoomTypeTable();
}

function initRoomTypeTable() {

    console.log('inside initroomTypeTable' );

    // Create columns (with titles) for datatable: id, name, address, age
    // Note: The 'ID' column is hidden but the value is avaliable
    columns = [
        {"data": "roomTypeId" },
        {"data": "roomTypeName" },
        {"data": "roomTypeNameZh"}

    ];

    // Define new datatable with above columns and assign it to the variable
    roomTypeTable = $("#roomType-table").DataTable( {
        "order": [[ 0, "asc" ]],
        "columns": columns,
        "language": {
         "url": languageUrl
                                    }
    });

    $("#roomType-table tbody").on( 'click', 'tr', function () {
        console.log("Clicking on row");
        if ( $(this).hasClass('selected') ) {
          $(this).removeClass('selected');
        }
        else {
            roomTypeTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    });

    // Get the data from the 'backend' and refresh the table
    getRoomTypeData();
}

function createRoomType(){

    console.log('inside create roomType' );

    // Put roomType data from page in Javascript object --- SIMILAR TO JSON
    var roomTypeData = {

            roomTypeId: $("#roomTypeId").val(),
            roomTypeName: $("#roomTypeName").val(),
            roomTypeNameZh: $("#roomTypeNameZh").val()

    }

    // Transform Javascript object to json
    var roomTypeJson = JSON.stringify(roomTypeData);

    console.log(roomTypeJson);

    $.ajax({
        url: "/api/roomType",
        type: "post",
        data: roomTypeJson,    // json for request body
        contentType:"application/json; charset=utf-8",   // What we send to frontend
        dataType: "json",  // What get back from frontend
        // success: function(roomType, textStatus, jqXHR){
        success: function(roomType){

          // roomType object is returned but we don't use it
          console.log(roomType);

          // Clear fields in modal page
          $("#roomTypeId").val('');
          $("#roomTypeName").val('');
          $("#roomTypeNameZh").val('');


          // Refresh table data
          getRoomTypeData();

        },

        fail: function (error) {
          console.log('Error: ' + error);
        }

    });
}

function getRoomTypeData(){

    console.log('inside get roomType Data' );

    // Get roomType from the FAKE backend by using a FAKE JQuery class
    // See file myjquery.js
    // We pass a JAVASCRIPT object with everything needed for a REAL JQuery call.
    // Our FAKE JQuery only uses:
    // url
    // success (which is a function)
    // fail (which is a function)
    // When we have a REAL backend we only need to change $$ to $
    $.ajax({
        url: "/api/roomType",
        type: "get",
        dataType: "json",  // get back from frontend
        // success: function(roomType, textStatus, jqXHR){
        success: function(roomTypes){

          console.log(roomTypes);

          // Clear fields in page
          roomTypeTable.clear();
          roomTypeTable.rows.add(roomTypes);
          roomTypeTable.columns.adjust().draw();

        },

        fail: function (error) {
          console.log('Error: ' + error);
        }

    });
}

function deleteRoomType(){
    var roomType = roomTypeTable.row($('.selected')).data();

    if (!roomType) {
        alert($('#msg-selectFirst').val());
        return;
    }

    $.ajax({
        url: '/api/roomType/can-delete/' + roomType.roomTypeId,
        type: "get",
        dataType: "json",
        success: function(canDelete) {
            if (canDelete) {
                $.ajax({
                    url: '/api/roomType/' + roomType.roomTypeName,
                    type: "delete",
                    dataType: "text",
                    success: function(message) {

                        if(language=="zh"){
                        alert('房型删除成功');
                          }else{
                         alert('Room Type deleted successfully');
                        }
                        getRoomTypeData();
                    },
                    error: function(xhr, status, error) {
                        alert('Error: ' + xhr.responseText);
                    }
                });
            } else {
                alert($('#msg-cannotDelete').val());
            }
        },
        error: function(xhr, status, error) {
            alert('Error: ' + xhr.responseText);
        }
    });
}
