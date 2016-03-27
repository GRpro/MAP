
var webSocketClient = {};

webSocketClient.stompClient = null;
webSocketClient.setProcessed = function (processed) {
    document.getElementById('startProcess').disabled = processed;
    document.getElementById('stopProcess').disabled = !processed;
    document.getElementById('circuitBoard').style.visibility = processed ? 'visible' : 'hidden';
};

webSocketClient.connect = function () {
    var socket = new SockJS('/process');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/circuit', function(matrix){
            console.log(matrix)
            console.log(matrix.body)
            console.log(JSON.parse(matrix.body))
            webSocketClient.updateCircuitView(JSON.parse(matrix.body));
        });
    });
};

webSocketClient.disconnect = function () {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
};

webSocketClient.sendGraph = function (graph) {
    stompClient.send("/app/process/graph", {}, JSON.stringify(graph));
    this.setProcessed(true);
};

webSocketClient.startProcess = function () {
    stompClient.send("/app/process/start", {});
    this.setProcessed(true);
};


webSocketClient.stopProcess = function () {
    stompClient.send("/app/process/stop", {});
    this.setProcessed(false);
};

webSocketClient.updateCircuitView = function (mat) {
    var sketchEl = document.getElementById('sketch');
    sketchEl.innerHTML = '';
    var p = new LogicMatrix('sketch');
    p.loadMat(mat);
    p.update()
};