$(document).ready(function(){
    loadDropdown();
    $('select').on('change', function() {
         getData(this.value);
    });
});

//Loads countries list to dropdown
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
            getData($('select').val());
       },
       error: function(xhr, status, error) {
             var errorMessage = xhr.status + ': ' + xhr.statusText
             alert('Error - ' + errorMessage);
            }
       });
}

//Gets data from API and draws chart
function getData(country) {
    $.ajax({
       type: "GET",
       url: "/statistic/"+country,
       dataType: "json",
       cache: false,
       contentType: "application/json",
       success: function(data) {
            var cases = "cases";
            var deaths = "deaths";
            var casesData = [];
            var deathsData = [];
            var x;
            var date;
            $.each(data, function(i, element) {
                if (cases == i) {
                    $.each(element, function(j, obj) {
                        date = j.split("-");
                        x = [conDate(date[0], date[1]), obj];
                        casesData.push(x);
                    });
                } else if (deaths == i) {
                    $.each(element, function(j, obj) {
                        date = j.split("-");
                        x = [conDate(date[0], date[1]), obj];
                        deathsData.push(x);
                    });
                };
            });
            plotChart(casesData, deathsData);
       },
       error: function(xhr, status, error) {
             var errorMessage = xhr.status + ': ' + xhr.statusText
             alert('Error - ' + errorMessage);
            }
       });
}

//Draws chart
function plotChart(cases, deaths) {
    console.log(cases);
    console.log(deaths);
    var plot = $.plot($("#Chart"),
        [
            {
              data: cases,
              label: "Cases",
              lines: {
                fill: true,
                show: true
              },
              yaxis: 1
            },
            {
              data: deaths,
              label: "Deaths",
              lines: { show: true},
              yaxis: 2
            }
        ],
        {
            legend: {
                noColumns: 0,
                position: "nw"
            },
            grid: {
                backgroundColor: { colors: ["#D1D1D1", "#7A7A7A"] }
            },
            xaxis: {
                mode: "time",
                timeformat: "%y-%m",
                color: "black",
            },
            yaxes: [
                {
                    /* First y axis */
                   min: 0,
                   position: "left",
                   color: "black",
                   axisLabel: "Cases",
                   axisLabelUseCanvas: true,
                   axisLabelFontSizePixels: 14,
                   axisLabelFontFamily: 'Verdana, Arial',
                   axisLabelPadding: 3
                },
                {
                    /* Second y axis */
                    min: 0,
                    minTickSize: 1,
                    tickDecimals: 0,
                    position: "right",
                    color: "black",
                    axisLabel: "Deaths",
                    axisLabelAngle: 90,
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 14,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 3
                }
            ]
        }
    );
};

//Calculates timestamp
function conDate(y, w) {
  var d = (1 + (w - 1) * 7); // 1st of January + 7 days for each week
  return new Date(y, 0, d);
}