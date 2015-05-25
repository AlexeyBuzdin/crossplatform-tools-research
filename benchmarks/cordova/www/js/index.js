document.addEventListener('deviceready', function () {
    var result = document.getElementById('result');
    var logContainer = document.getElementById('log');

    result.innerText = 'Device ready.';
    function clean() {
        result.innerHTML = '';
        logContainer.innerHTML = '';
    }

    function log(str) {
        logContainer.innerHTML += (str + "</br>");
    }

    var logical = document.getElementById('logical');
    var graphics = document.getElementById('graphics');

    logical.addEventListener('click', function () {
        clean();
        log('Computing...');

        function testableMethod() {
            var sum = 0;
            for (var x = 1; x <= 500000; x++) {
                sum += x;
            }
        }

        var totalTime = 0;
        var iterations = 200;
        var zeroResults = 0;

        var array = [];
        for (var y = 0; y < iterations; y++) {
            var start = new Date().getTime();
            testableMethod();
            var end = new Date().getTime();
            var diff = end - start;
            if (diff == 0) {
                zeroResults++;
            }

            array.push(diff);
            totalTime += diff;
        }

        console.log(array);
        result.innerText = "Average: " + (totalTime / iterations) + "ms/op";
    });

    graphics.addEventListener('click', function () {
        clean();
        var canvas = document.createElement('canvas');
        var progress = document.createElement('div');

        var w = 100;
        var h = 100;
        canvas.setAttribute("width", "100");
        canvas.setAttribute("height", "100");
        canvas.id = 'canvas';

        result.appendChild(canvas);
        result.appendChild(progress);

        var ctx = canvas.getContext('2d');

        function getRandomColor() {
            var letters = '0123456789ABCDEF'.split('');
            var color = '#';
            for (var i = 0; i < 6; i++) {
                color += letters[Math.floor(Math.random() * 16)];
            }
            return color;
        }

        function draw() {
            for (var x = 0; x < w; x += 1) {
                for (var y = 0; y < h; y += 1) {
                    ctx.fillRect(x, y, 1, 1);
                }
            }
        }

        var totalTime = 0;
        var trials = 200;
        var i = 0;

        log("Trials: " + trials);
        log("Compute graphics...");
        var array = [];
        function work() {
            ctx.fillStyle = getRandomColor();
            var start = new Date().getTime();

            draw();

            var end = new Date().getTime();
            totalTime += (end - start);
            array.push(end - start);

            i++;
            if (i < trials) {
                progress.innerText = "" + i;
                setTimeout(work, 0);
            } else {
                var average = totalTime / trials;
                console.log(array);
                log("Total time: " + totalTime + "ms");
                log("Average time: " + average + " ms/op");
            }
        }

        setTimeout(work, 0);
    });
});
