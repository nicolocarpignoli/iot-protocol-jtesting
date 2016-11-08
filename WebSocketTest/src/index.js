var ws = new WebSocket("ws://your_ip:your_port/");

ws.onopen = function() {
  //  alert("Opened!");
};


ws.onmessage = function (evt) {
      ws.send(evt.data);
};

ws.onclose = function() {
  //  alert("Closed!");
};

ws.onerror = function(err) {
  //  alert("Error: " + err);
};
