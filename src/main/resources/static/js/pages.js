// Declare a variable that holds a reference to the datatable,
// so that we can use it in every function.
var pageTable;

function init(){


    $('#pageForm').on('submit', function(e) {
        e.preventDefault();

        var formData = new FormData();
        formData.append('pageId', $('#pageId').val());
        formData.append('pageName', $('#pageName').val());
        formData.append('content', $('#content').val());


        var files = $('#photos')[0].files;
        for (var i = 0; i < files.length; i++) {
            formData.append('photos', files[i]);
        }

        $.ajax({
            url: '/api/pages',
            type: 'POST',
            processData: false,
            contentType: false,
            data: formData,
            success: function(response) {
                alert('Page ve fotoğraflar başarıyla oluşturuldu!');
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert('İşlem sırasında bir hata meydana geldi: ' + textStatus);
            }
        });
    });



    $("#new-page-button").click( function () {
        console.log("Inside click of new-page-button");
        $('#page-modal').modal('show');
    });

    $("#edit-page-button").click(function () {
        console.log("Inside click of edit-page-button");

        // Seçilen satırdan veriyi alın
        const selectedRow = pageTable.row($('.selected')).data();

        if (!selectedRow) {
            alert("Select page first");
            return; // İşlemi durdur
        }

        const page = selectedRow;

        // Alanları doldur
        $("#pageId").val(page.pageId);
        $("#pageName").val(page.pageName);
        $("#content").val(page.content);

        // Image URL'leri modal'a yerleştir
        $("#imageContainer").empty(); // Önce önceki görüntüleri temizle
        if (Array.isArray(page.imageUrlList)) {
            page.imageUrlList.forEach(url => {
                // Modalda görüntüleri göstermek için bir eleman ekleme kodu
                $("#imageContainer").append(`<img src="${url}" alt="page Image" style="width:100px;height:100px; margin-right:5px;"/>`);
            });
        }

        $('#page-modal').modal('show');
    });




    $("#delete-page-button").click( function () {
        console.log("Inside click of delete-page-button");
        // Get the data from selected row and fill fields in modal

        if (pageTable.row($('.selected')).data() == undefined) {
            alert("Select page first");
        }else{
            $('#page-delete-modal').modal('show');
        }

    });

    // Button in delete modal
    $("#delete-page-confirm-button").click( function () {
        console.log("Inside click of delete-page-confirm-button");
        deletePage();
        $('#page-delete-modal').modal('hide');
    });


    initPageTable();
}

function initPageTable() {
    console.log('inside initPageTable');

    columns = [
        { title: 'Page Id', data: 'pageId' },
        { title: 'Page Name', data: 'pageName' },
        { title: 'Page Content', data: 'content' },
        {
            title: 'Image URL List',
            data: 'imageUrlList',
            render: function(data) {
                if (Array.isArray(data)) {
                    return data.map(url =>
                        `<img src="${url}" alt="Page Image" style="width:50px;height:50px; margin-right: 5px;" onclick="showImage('${url}')" />`
                    ).join('');
                }
                return '';
            }
        }



    ];

    pageTable = $("#page-table1").DataTable({
        "order": [[0, "asc"]],
        "columns": columns
    });

    $("#page-table1 tbody").on('click', 'tr', function() {
        console.log("Clicking on row");
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            pageTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    });

    getPageData();
}

// Function to show image in a larger view
function showImage(url) {
    $('#image-modal').modal('show');
    $('#modal-image').attr('src', url);
}

function getPageData(){

    console.log('inside getPageData' );

    // Get pages from the FAKE backend by using a FAKE JQuery class
    // See file myjquery.js
    // We pass a JAVASCRIPT object with everything needed for a REAL JQuery call.
    // Our FAKE JQuery only uses:
    // url
    // success (which is a function)
    // fail (which is a function)
    // When we have a REAL backend we only need to change $$ to $
    $.ajax({
        url: "/api/pages",
        type: "get",
        dataType: "json",  // get back from frontend
        // success: function(pages, textStatus, jqXHR){
        success: function(pages){

          console.log(pages);

          // Clear fields in page
          pageTable.clear();
          pageTable.rows.add(pages);
          pageTable.columns.adjust().draw();

        },

        fail: function (error) {
          console.log('Error: ' + error);
        }

    });
}

function deletePage(){

    if (pageTable.row($('.selected')).data() == undefined) {
        alert("Select page first");
    }else{
        var page = pageTable.row($('.selected')).data();

            $.ajax({
                url: '/api/pages/' + page.pageId,
                type: "delete",
                dataType: "text",  // get back from frontend
                // success: function(page, textStatus, jqXHR){
                success: function(message){

                  console.log(message);

                  // Refresh table data
                  getPageData();

                },

                fail: function (error) {
                  console.log('Error: ' + error);
                }

            });
    }
}
