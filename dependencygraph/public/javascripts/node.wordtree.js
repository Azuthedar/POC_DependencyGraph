google.charts.load('current', {packages:['orgchart']});
        google.charts.setOnLoadCallback(drawChart);

        function drawChart() {
            var data = google.visualization.arrayToDataTable(
                [ ['Phrases'],
                  ['cats are better than dogs'],
                  ['cats eat kibble'],
                  ['cats are better than hamsters'],
                  ['cats are awesome'],
                  ['cats are people too'],
                  ['cats eat mice'],
                  ['cats meowing'],
                  ['cats in the cradle'],
                  ['cats eat mice'],
                  ['cats in the cradle lyrics'],
                  ['cats eat kibble'],
                  ['cats for adoption'],
                  ['cats are family'],
                  ['cats eat mice'],
                  ['cats are better than kittens'],
                  ['cats are evil'],
                  ['cats are weird'],
                  ['cats eat mice'],
                ]
              );

            var options = {
                wordtree: {
                    format: 'implicit',
                    word: 'cats'
                }
            }

            var chart = new google.visualization.WordTree(document.getElementById('wordtree_basic'))
            chart.draw(data, options)
        }