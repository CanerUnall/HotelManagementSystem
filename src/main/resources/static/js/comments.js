// Declare a variable that holds a reference to the datatable,
// so that we can use it in every function.
var commentTable;

function init(){

// Add submit event to form for new and edit
    $("#commentForm").on('submit', function() {

        createComment();
        $('#comment-modal').modal('hide');
    });

    $("#new-comment-button").click( function () {
        console.log("Inside click of new-comment-button");
        $('#comment-modal').modal('show');
    });

    $("#edit-comment-button").click(function () {
        console.log("Inside click of edit-comment-button");

        // Seçilen satırdan veriyi alın
        const selectedRow = commentTable.row($('.selected')).data();

        if (!selectedRow) {
            var selectMessage = $("#select-comments-message").text();
            alert(selectMessage);
            return; // İşlemi durdur
        }

        const comment = selectedRow;

        // Alanları doldur
        $("#commentId").val(comment.commentId);
        $("#commentContent").val(comment.commentContent);
        $("#commentContentZh").val(comment.commentContentZh);
        $("#author").val(comment.author);


        $('#comment-modal').modal('show');
    });


    $("#delete-comment-button").click( function () {
        console.log("Inside click of delete-comment-button");
        // Get the data from selected row and fill fields in modal

        if (commentTable.row($('.selected')).data() == undefined) {
            var selectMessage = $("#select-comments-message").text();
            alert(selectMessage);
        }else{
            $('#comment-delete-modal').modal('show');
        }

    });

    // Button in delete modal
    $("#delete-comment-confirm-button").click( function () {
        console.log("Inside click of delete-comment-confirm-button");
        deleteComment();
        $('#comment-delete-modal').modal('hide');
    });


    initCommentTable();
}

function initCommentTable() {
    console.log('inside initCommentTable');

    columns = [
        { data: 'commentId' },
        { data: 'author' },
        { data: 'commentContent' },
        { data: 'commentContentZh' }


    ];

    commentTable = $("#comment-table1").DataTable({
        "order": [[0, "asc"]],
        "columns": columns,
        "language": {
                             "url": languageUrl
                            }
    });

    $("#comment-table1 tbody").on('click', 'tr', function() {
        console.log("Clicking on row");
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            commentTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    });

    getCommentData();
}

// Function to show image in a larger view
function showImage(url) {
    $('#image-modal').modal('show');
    $('#modal-image').attr('src', url);
}




function createComment() {
    console.log('inside createComment' );

    // comment data'yı hazırla
    const commentData = {
        commentId: $("#commentId").val(),
        commentContent: $("#commentContent").val(),
        commentContentZh: $("#commentContentZh").val(),
        author: $("#author").val()

    };

    // comment data'yı sunucuya gönder
    $.ajax({
        url: "/api/comments",
        type: "post",
        data: JSON.stringify(commentData),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function(response) {
            console.log('comment saved:', response);
            $('#comment-modal').modal('hide');
            getCommentData();
        },
        error: function(error) {
            console.error('Error saving comment:', error);
        }
    });
}


function editComment(){

    console.log('inside editComment' );

    // Put comment data from page in Javascript object --- SIMILAR TO JSON
    var commentData = {

             commentId:$("#commentId").val(),
             commentContent: $("#commentContent").val(),
             commentContentZh: $("#commentContentZh").val(),
             author: $("#author").val()

    };

    $.ajax({
        url: "/api/comments",
        type: "post",
        data: JSON.stringify(commentData),
        contentType: "application/json",
        //dataType: "json",
        success: function(comment){
            alert("comment edited successfully");
            console.log(comment);

            $("#commentId").val(''),
            $("#commentContent").val(''),
            $("#commentContentZh").val(''),
            $("#author").val('')


            getCommentData();
        },
        error: function (jqXHR, textStatus, errorThrown) { // Use error callback for better error handling
            console.log('AJAX error: ' + textStatus + ': ' + errorThrown);
            alert('Failed to edit comment: ' + jqXHR.responseText);
        }
    });

}

function getCommentData(){

    console.log('inside getCommentData' );


    $.ajax({
        url: "/api/comments",
        type: "get",
        dataType: "json",  // get back from frontend
        // success: function(comments, textStatus, jqXHR){
        success: function(comments){

          console.log(comments);

          // Clear fields in page
          commentTable.clear();
          commentTable.rows.add(comments);
          commentTable.columns.adjust().draw();

        },

        fail: function (error) {
          console.log('Error: ' + error);
        }

    });
}

function deleteComment(){

    if (commentTable.row($('.selected')).data() == undefined) {
        alert("Select comment first");
    }else{
        var comment = commentTable.row($('.selected')).data();

            $.ajax({
                url: '/api/comments/' + comment.commentId,
                type: "delete",
                dataType: "text",  // get back from frontend
                // success: function(comment, textStatus, jqXHR){
                success: function(message){

                  console.log(message);

                  // Refresh table data
                  getCommentData();

                },

                fail: function (error) {
                  console.log('Error: ' + error);
                }

            });
    }
}
