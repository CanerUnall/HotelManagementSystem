// Declare a variable that holds a reference to the datatable,
// so that we can use it in every function.
var aboutUsTable;

function init(){

    console.log('inside init');

    $('#aboutUs-form').on('submit', function(event) {
        event.preventDefault();

        var formData = new FormData();
        formData.append('aboutUsId', $('#aboutUsId').val());
        formData.append('description', $('#description').val());
        formData.append('descriptionZh', $('#descriptionZh').val());
        formData.append('image', $('#image')[0].files[0]);

        $.ajax({
            url: '/api/aboutUs',
            type: 'POST',
            data: formData,
            processData: false,
            contentType: false,
            success: function(response) {
                // Handle success
                console.log('aboutUs created successfully', response);
                $('#aboutUs-modal').modal('hide');
                // Refresh the table or do other UI updates as necessary
                getAboutUsData();
            },
            error: function(xhr, status, error) {
                // Handle error
                console.error('Error creating aboutUs', error);
            }
        });
    });

    $("#new-aboutUs-button").click(function () {
        console.log("Inside click of new-aboutUs-button");
        $('#aboutUs-modal').modal('show');
    });

    $("#edit-aboutUs-button").click(function() {
        console.log("Inside click of edit-aboutUs-button");

        if (aboutUsTable.row($('.selected')).data() == undefined) {
            var selectMessage = $("#select-aboutUs-message").text();
            alert(selectMessage);
        } else {
            var aboutUs = aboutUsTable.row($('.selected')).data();

            $("#aboutUsId").val(aboutUs.aboutUsId);
            $("#description").val(aboutUs.description);
            $("#descriptionZh").val(aboutUs.descriptionZh);
            $("#currentImage").attr("src", aboutUs.image);

            $('#aboutUs-modal').modal('show');
        }
    });

    initAboutUsTable();
}

function initAboutUsTable() {
    console.log('inside  init aboutUs');

    // Create columns (with titles) for datatable: id, name, address, age
    // Note: The 'ID' column is hidden but the value is available
    var columns = [
        { "data": "aboutUsId" },

        { "data": "description" },
        { "data": "descriptionZh" },
        {
            "data": "image.imageUrl",
            "render": function(imageUrl) {
            console.log( "XXXXX " + imageUrl);
                return `<img src="${imageUrl}" style="width:20px;height:20px; margin-right: 5px;" onclick="showImage('${imageUrl}')" />`;
            }
        }
    ];

    // Define new datatable with above columns and assign it to the variable
    aboutUsTable = $("#aboutUs-table").DataTable({
        "order": [[0, "desc"]],
        "columns": columns,
        "language": {
                     "url": languageUrl
                    }
    });

    $("#aboutUs-table tbody").on('click', 'tr', function () {
        console.log("Clicking on row");
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            aboutUsTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    });

    // Get the data from the 'backend' and refresh the table
    getAboutUsData();
}

function showImage(url) {
    $('#image-modal').modal('show');
    $('#modal-image').attr('src', url);
}

function getAboutUsData() {
    console.log('inside getAboutUsData');

    $.ajax({
        url: "/api/aboutUs",
        type: "get",
        dataType: "json",  // get back from frontend
        success: function(aboutUs) {
            console.log(aboutUs);

            // Clear fields in page
            aboutUsTable.clear();
            aboutUsTable.rows.add(aboutUs);
            aboutUsTable.columns.adjust().draw();
        },
        fail: function (error) {
            console.log('Error: ' + error);
        }
    });
}
