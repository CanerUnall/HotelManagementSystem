// Declare a variable that holds a reference to the datatable,
// so that we can use it in every function.
var carouselTable;

function init(){

    console.log('inside init');

    $('#carousel-form').on('submit', function(event) {
        event.preventDefault();

        var formData = new FormData();
        formData.append('carouselId', $('#carouselId').val());
        formData.append('carouselTitle', $('#carouselTitle').val());
        formData.append('carouselTitleZh', $('#carouselTitleZh').val());
        formData.append('description', $('#description').val());
        formData.append('descriptionZh', $('#descriptionZh').val());
        formData.append('imageFile', $('#carouselImg')[0].files[0]);

        $.ajax({
            url: '/api/carousel',
            type: 'POST',
            data: formData,
            processData: false,
            contentType: false,
            success: function(response) {
                // Handle success
                console.log('carousel created successfully', response);
                $('#carousel-modal').modal('hide');
                // Refresh the table or do other UI updates as necessary
                getCarouselData();
            },
            error: function(xhr, status, error) {
                // Handle error
                console.error('Error creating carousel', error);
            }
        });
    });

    $("#new-carousel-button").click(function () {
        console.log("Inside click of new-carousel-button");
        $('#carousel-modal').modal('show');
    });

    $("#edit-carousel-button").click(function() {
        console.log("Inside click of edit-carousel-button");

        if (carouselTable.row($('.selected')).data() == undefined) {
            var selectMessage = $("#select-carousel-message").text();
            alert(selectMessage);
        } else {
            var carousel = carouselTable.row($('.selected')).data();

            $("#carouselId").val(carousel.carouselId);
            $("#carouselTitle").val(carousel.carouselTitle);
            $("#carouselTitleZh").val(carousel.carouselTitleZh);
            $("#description").val(carousel.description);
            $("#descriptionZh").val(carousel.descriptionZh);
            $("#currentImage").attr("src", carousel.image);

            $('#carousel-modal').modal('show');
        }
    });



    $("#delete-carousel-button").click(function () {
        console.log("Inside click of delete-carousel-button");
        // Get the data from selected row and fill fields in modal
        var carouselCount = carouselTable.data().count();// Bu yöntemle tablo içindeki verilerin sayısını aldim.

        if (carouselTable.row($('.selected')).data() == undefined) {
            var selectMessage = $("#select-carousel-message").text();
            alert(selectMessage);
        } else if (carouselCount <= 1) {
            alert($("#carousel-cannot-be-deleted").text());
        }else {
            $('#carousel-delete-modal').modal('show');
        }
    });

    // Button in delete modal
    $("#delete-carousel-confirm-button").click(function () {
        console.log("Inside click of delete-carousel-confirm-button");
        deleteCarousel();
        $('#carousel-delete-modal').modal('hide');
    });

//    // Add submit event to form for new and edit
//    $("#carousel-form").on('submit', function() {
//        console.log("Submitting");
//        createCarousel();
//        $('#carousel-modal').modal('hide');
//    });

    initCarouselTable();
}

function initCarouselTable() {
    console.log('inside initCarouselTable');

    // Create columns (with titles) for datatable: id, name, address, age
    // Note: The 'ID' column is hidden but the value is available
    var columns = [
        {  "data": "carouselId" },
        {  "data": "carouselTitle" },
        {  "data": "carouselTitleZh" },
        { "data": "description" },
        { "data": "descriptionZh" },
        {
            "title": "image", "data": "image.imageUrl",
            "render": function(imageUrl) {

                //return '<img src="' + imageUrl + '" width="20px">';
                return `<img src="${imageUrl}" style="width:20px;height:20px; margin-right: 5px;" onclick="showImage('${imageUrl}')" />`;
            }
        }
    ];

    // Define new datatable with above columns and assign it to the variable
    carouselTable = $("#carousel-table").DataTable({
        "order": [[0, "desc"]],
        "columns": columns,
        "language": {
                             "url": languageUrl
                            }
    });

    $("#carousel-table tbody").on('click', 'tr', function () {
        console.log("Clicking on row");
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            carouselTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    });

    // Get the data from the 'backend' and refresh the table
    getCarouselData();
}

function showImage(url) {
    $('#image-modal').modal('show');
    $('#modal-image').attr('src', url);
}

function getCarouselData() {
    console.log('inside getCarouselData');

    $.ajax({
        url: "/api/carousel",
        type: "get",
        dataType: "json",  // get back from frontend
        success: function(carousel) {
            console.log(carousel);

            // Clear fields in page
            carouselTable.clear();
            carouselTable.rows.add(carousel);
            carouselTable.columns.adjust().draw();
        },
        fail: function (error) {
            console.log('Error: ' + error);
        }
    });
}

function deleteCarousel() {

    if (carouselTable.row($('.selected')).data() == undefined) {
        alert("Select carousel first");
    } else {
        var carousel = carouselTable.row($('.selected')).data();

        $.ajax({
            url: '/api/carousel/' + carousel.carouselId,
            type: "delete",
            dataType: "text",  // get back from frontend
            success: function(message) {
                console.log(message);

                // Tablo verilerini yenile
                getCarouselData();
            },
            fail: function (error) {
                console.log('Error: ' + error);
            }
        });
    }
}
