$(document).ready(function () {
    var message = localStorage.getItem('message');
    var fileLoaded = localStorage.getItem('fileLoaded');
    if (message) {
        $('#message').text(message);
    }
    if (fileLoaded) {
        $('#productForm, .button').show();
    }
    $('#uploadForm').on('submit', function(event) {
        event.preventDefault();
        var formData = new FormData(this);
        $.ajax({
            type: 'POST',
            url: '/upload',
            data: formData,
            cache: false,
            contentType: false,
            processData: false,
            success: function(data) {
                localStorage.setItem('message', data);
                localStorage.setItem('fileLoaded', 'true');
                $('#message').text(data);
                $('#productForm, .button').show();
            },
            error: function(data) {
                $('#message').text(data);
            }
        });
    });

    $('#productForm').on('submit', function(event) {
        event.preventDefault();
        var productName = $('#productName').val();
        $.ajax({
            type: 'GET',
            url: '/product/' + productName,
            success: function(data) {
                window.location.href = '/product/' + productName;
            },
            error: function(jqXHR, textStatus, errorThrown) {
                if (jqXHR.status == 404) {
                    $('#message').text('Product named:  ' + productName + ' does not exist');
                } else {
                    $('#message').text('An error occurred while searching for a product');
                }
            }
        });
    });
});

$(window).on('load', function() {
    if (window.performance.navigation.type === 1) {
        localStorage.clear();
    }
});