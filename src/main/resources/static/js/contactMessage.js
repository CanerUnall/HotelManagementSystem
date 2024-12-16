// Declare a variable that holds a reference to the datatable,
// so that we can use it in every function.
var contactMessageTable;

function init(){


    $("#delete-contactMessage-button").click( function () {
        console.log("Inside click of delete-contactMessage-button");
        // Get the data from selected row and fill fields in modal

        if (contactMessageTable.row($('.selected')).data() == undefined) {
            var selectMessage = $("#select-contact-message").text();
                        alert(selectMessage);
        }else{
            $('#contactMessage-delete-modal').modal('show');
        }

    });

    // Button in delete modal
    $("#delete-contactMessage-confirm-button").click( function () {
        console.log("Inside click of delete-contactMessage-confirm-button");
        deleteContactMessage();
        $('#contactMessage-delete-modal').modal('hide');
    });


    initContactMessageTable();
}

function initContactMessageTable() {
    console.log('inside initContactMessageTable');

    columns = [
        { data: 'contactMessageId' },
        { data: 'author' },
        { data: 'email' },
        { data: 'message' },
        { data: 'creationDateTime' }

    ];

    contactMessageTable = $("#contactMessage-table1").DataTable({
        "order": [[0, "asc"]],
        "columns": columns,
        "language": {
                             "url": languageUrl
                            }
    });

    $("#contactMessage-table1 tbody").on('click', 'tr', function() {
        console.log("Clicking on row");
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            contactMessageTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    });

    getContactMessageData();
}

// Function to show image in a larger view
function showImage(url) {
    $('#image-modal').modal('show');
    $('#modal-image').attr('src', url);
}

function getContactMessageData(){

    console.log('inside getContactMessageData' );

    // Get contactMessages from the FAKE backend by using a FAKE JQuery class
    // See file myjquery.js
    // We pass a JAVASCRIPT object with everything needed for a REAL JQuery call.
    // Our FAKE JQuery only uses:
    // url
    // success (which is a function)
    // fail (which is a function)
    // When we have a REAL backend we only need to change $$ to $
    $.ajax({
        url: "/api/contactMessages",
        type: "get",
        dataType: "json",  // get back from frontend
        // success: function(contactMessages, textStatus, jqXHR){
        success: function(contactMessages){

          console.log(contactMessages);

          // Clear fields in contactMessage
          contactMessageTable.clear();
          contactMessageTable.rows.add(contactMessages);
          contactMessageTable.columns.adjust().draw();

        },

        fail: function (error) {
          console.log('Error: ' + error);
        }

    });
}

function deleteContactMessage(){

        var contactMessage = contactMessageTable.row($('.selected')).data();

            $.ajax({
                url: '/api/contactMessages/' + contactMessage.contactMessageId,
                type: "delete",
                dataType: "text",  // get back from frontend
                // success: function(contactMessage, textStatus, jqXHR){
                success: function(message){

                  console.log(message);

                  // Refresh table data
                  getContactMessageData();

                },

                fail: function (error) {
                  console.log('Error: ' + error);
                }

            });

}
