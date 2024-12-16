// Declare a variable that holds a reference to the datatable,
// so that we can use it in every function.
var dirtyRoomTable;

function init(){
    console.log('inside init' );

    $("#edit-dirtyRoom-button").click(function() {
        console.log("Inside click of edit-dirtyRoom-button");
        // Get the data from selected row and fill fields in modal

        if (dirtyRoomTable.row($('.selected')).data() == undefined) {
             alert(selectDirtyRoomMessage);
        } else {
            var dirtyRoom = dirtyRoomTable.row($('.selected')).data();

            $("#roomId").val(dirtyRoom.roomId);
            $("#roomNumber").val(dirtyRoom.roomNumber);
            $("#thFloor").val(dirtyRoom.thFloor);
            $("#cleaned").prop('checked', dirtyRoom.cleaned);

            $('#dirtyRoom-modal').modal('show');
        }
    });

    // Add submit event to form for new and edit
    $("#dirtyRoom-form").on('submit', function() {
        console.log("Submitting");
        editDirtyRoom();
        $('#dirtyRoom-modal').modal('hide');
    });

    initDirtyRoomTable();
}

function initDirtyRoomTable() {
    console.log('inside initTable' );

    // Create columns (with titles) for datatable: id, name, address, age
    // Note: The 'ID' column is hidden but the value is available
    columns = [
        {"data": "roomId"},
        {"data": "roomNumber"},
        {"data": "thFloor"},
        {"data": "cleaned",
            "render": function(data) {
                return data ? (language === "zh" ? '是' : 'Yes') : (language === "zh" ? '否' : 'No');
            }
        }
    ];

    // Define new datatable with above columns and assign it to the variable
    dirtyRoomTable = $("#dirtyRoom-table").DataTable({
        "order": [[0, "asc"]],
        "columns": columns,
        "language": {"url": languageUrl}
    });

    $("#dirtyRoom-table tbody").on('click', 'tr', function() {
        console.log("Clicking on row");
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            dirtyRoomTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    });

    // Get the data from the 'backend' and refresh the table
    getDirtyRoomData();
}

function editDirtyRoom(){
    console.log('inside createDirtyRoom' );

    // Put user data from page in Javascript object --- SIMILAR TO JSON
    var dirtyRoomData = {
        roomId: $("#roomId").val(),
        cleaned: $("#cleaned").prop('checked')
    }

    // Transform Javascript object to json
    var dirtyRoomJson = JSON.stringify(dirtyRoomData);

    $.ajax({
        url: "/api/room/editRoomStatus",
        type: "put",
        data: dirtyRoomJson,    // json for request body
        contentType:"application/json; charset=utf-8",   // What we send to frontend
        dataType: "json",  // What get back from frontend
        success: function(dirtyRoom){
            // Clear fields in modal page
            $("#roomId").val('');
            $("#roomNumber").val('');
            $("#thFloor").val('');
            $("#cleaned").val('');

            // Refresh table data
            getDirtyRoomData();
        },
        fail: function (error) {
            console.log('Error: ' + error);
        }
    });
}

function getDirtyRoomData(){
    console.log('inside getDirtyRoomData' );

    $.ajax({
        url: "/api/room/dirtyRooms",
        type: "get",
        dataType: "json",  // get back from frontend
        success: function(dirtyRoom){
            // Clear fields in page
            dirtyRoomTable.clear();
            dirtyRoomTable.rows.add(dirtyRoom);
            dirtyRoomTable.columns.adjust().draw();
        },
        fail: function (error) {
            console.log('Error: ' + error);
        }
    });
}
