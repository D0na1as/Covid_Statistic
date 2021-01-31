$(document).ready(function(){
    loadDropdown();
    $('select').on('change', function() {
      console.log(this.value);
                  getData(this.value);
    });
});

function loadDropdown() {
    $.ajax({
       type: "GET",
       url: "/statistic/places",
       dataType: "json",
       cache: false,
       contentType: "application/json",
       success: function(data) {
            $('#Dropdown').append('<option selected value="'+ data[0]  + '">' + data[0] + '</option>');
            for ( var i=1; i<data.length; i++) {
                $('#Dropdown').append('<option value="'+ data[i]  + '">' + data[i] + '</option>');
            };
       },
       error: function(xhr, status, error) {
                alert(xhr.status);
            }
       });
}

function getData(country) {
    $.ajax({
       type: "GET",
       url: "/statistic/"+country,
       dataType: "json",
       cache: false,
       contentType: "application/json",
       success: function(data) {
            var get = "cases";
            $.each(data, function(i, element) {
                if (get == i) {
                console.log(get);
                    $.each(element, function(j, obj) {
                      console.log(j + ' : ' + obj);
                    });
                };
            });
       },
       error: function(xhr, status, error) {
                alert(xhr.status);
            }
       });
}