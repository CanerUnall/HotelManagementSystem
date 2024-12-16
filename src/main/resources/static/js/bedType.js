// Declare a variable that holds a reference to the datatable,
// so that we can use it in every function.
var bedTypeTable;

var messages = {
    en: {
        selectBedTypeFirst: 'Select bedType first',
        newBedType: 'New Bed Type',
        editBedType: 'Edit Bed Type',
        deleteBedType: 'Delete Bed Type',
        confirmDelete: 'Confirm Delete'
    },
    zh: {
        selectBedTypeFirst: '请先选择床型',
        newBedType: '新床型',
        editBedType: '编辑床型',
        deleteBedType: '删除床型',
        confirmDelete: '确认删除'
    }
};

function init(){

    console.log('inside init' );

    $("#reset-button").click( function () {
        reset();
    });

    $("#new-bedType-button").click( function () {
        console.log("Inside click of new-bedType-button");
        $('#bedType-modal').modal('show');
    });

    $("#edit-bedType-button").click( function () {
        console.log("Inside click of edit-bedType-button");
        // Get the data from selected row and fill fields in modal

        if (bedTypeTable.row($('.selected')).data() == undefined) {
            alert(messages[language].selectBedTypeFirst);
        }else{
            var bedType = bedTypeTable.row($('.selected')).data();

            $("#bedTypeId").val(bedType.bedTypeId);
            $("#bedTypeName").val(bedType.bedTypeName);
            $("#bedTypeNameZh").val(bedType.bedTypeNameZh);

            $('#bedType-modal').modal('show');
        }
    });


    $("#delete-bedType-button").click( function () {
        console.log("Inside click of delete-bedType-button");
        // Get the data from selected row and fill fields in modal

        if (bedTypeTable.row($('.selected')).data() == undefined) {
            alert(messages[language].selectBedTypeFirst);
        }else{
            $('#bedType-delete-modal').modal('show');
        }

    });

    // Button in delete modal
    $("#delete-bedType-confirm-button").click( function () {
        console.log("Inside click of delete-bedType-confirm-button");
        deleteBedType();
        $('#customer-delete-modal').modal('hide');
    });

    // Add submit event to form for new and edit
    $("#bedType-form").on('submit', function() {
        console.log("Submitting");
        createBedType();
        $('#bedType-modal').modal('hide');
    });

    initBedTypeTable();
}

function initBedTypeTable() {

    console.log('inside initBedTypeTable' );

    // Create columns (with titles) for datatable: id, name, address, age
    // Note: The 'ID' column is hidden but the value is avaliable
    columns = [
        { "data": "bedTypeId" },
        { "data": "bedTypeName"},
        { "data": "bedTypeNameZh"}
    ];

    // Define new datatable with above columns and assign it to the variable
    bedTypeTable = $("#bedType-table").DataTable( {
        "order": [[ 0, "asc" ]],
        "columns": columns,
        "language": {
                 "url": languageUrl
                 }
    });

    $("#bedType-table tbody").on( 'click', 'tr', function () {
        console.log("Clicking on row");
        if ( $(this).hasClass('selected') ) {
          $(this).removeClass('selected');
        }
        else {
            bedTypeTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    });

    // Get the data from the 'backend' and refresh the table
    getBedTypeData();
}

function createBedType(){

    console.log('inside createbedType' );

    // Put bedType data from page in Javascript object --- SIMILAR TO JSON
    var bedTypeData = {
            bedTypeId:$("#bedTypeId").val(),
            bedTypeName: $("#bedTypeName").val(),
            bedTypeNameZh: $("#bedTypeNameZh").val()

    }

    // Transform Javascript object to json
    var bedTypeJson = JSON.stringify(bedTypeData);

    console.log(bedTypeJson);

    $.ajax({
        url: "/api/bedType",
        type: "post",
        data: bedTypeJson,    // json for request body
        contentType:"application/json; charset=utf-8",   // What we send to frontend
        dataType: "json",  // What get back from frontend
        // success: function(bedType, textStatus, jqXHR){
        success: function(bedType){

          // bedType object is returned but we don't use it
          console.log(bedType);

          // Clear fields in modal page
          $("#bedTypeId").val('');
          $("#bedTypeName").val('');
          $("#bedTypeNameZh").val('');



          // Refresh table data
          getBedTypeData();

        },

        fail: function (error) {
          console.log('Error: ' + error);
        }

    });
}

function getBedTypeData(){

    console.log('inside getbedTypeData' );

    // Get bedType from the FAKE backend by using a FAKE JQuery class
    // See file myjquery.js
    // We pass a JAVASCRIPT object with everything needed for a REAL JQuery call.
    // Our FAKE JQuery only uses:
    // url
    // success (which is a function)
    // fail (which is a function)
    // When we have a REAL backend we only need to change $$ to $
    $.ajax({
        url: "/api/bedType",
        type: "get",
        dataType: "json",  // get back from frontend
        // success: function(bedType, textStatus, jqXHR){
        success: function(bedTypes){

          console.log(bedTypes);

          // Clear fields in page
          bedTypeTable.clear();
          bedTypeTable.rows.add(bedTypes);
          bedTypeTable.columns.adjust().draw();

        },



    });
}

function deleteBedType(){
    var bedType = bedTypeTable.row($('.selected')).data();

    if (!bedType) {
        if(language=="zh"){

           alert("请选择要删除的床型");
           }else{

           alert("Please select a bed type to delete.");
           }

        return;
    }

    $.ajax({
        url: '/api/bedType/can-delete/' + bedType.bedTypeId,
        type: "get",
        dataType: "json",
        success: function(canDelete) {
            if (canDelete) {
                $.ajax({
                    url: '/api/bedType/' + bedType.bedTypeId,
                    type: "delete",
                    dataType: "text",
                    success: function(message) {

                        if(language=="zh"){
                        alert('床型删除成功');
                        }else{
                        alert('Bed Type deleted successfully');

                        }
                        getBedTypeData();
                        $('#bedType-delete-modal').modal('hide');
                    },
                    error: function(xhr, status, error) {
                        alert('Error: ' + xhr.responseText);
                    }
                });
            } else {
            if(language=="zh"){
            alert("该床型已被房间使用，无法删除。");
            }else{
            alert("This bed type is in use by a room and cannot be deleted.");

            }

            }
        },
        error: function(xhr, status, error) {
            alert('Error: ' + xhr.responseText);
        }
    });
}
