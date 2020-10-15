let stompClient = null

/**
 * 根据连接状态来调整页面
 * @param connected 连接状态
 */
function setConnected(connected) {
    $("#connect").prop("disabled",connected);
    $("#disconnect").prop("disabled",!connected);
    if(connected){
        $("#conversation").show();
        $("#chat").show();
    }else{
        $("#conversation").hide();
        $("#chat").hide();
    }
    $("#greetings").html("");
}
/**
 * 建立一个WebSocket连接，要求必须输入用户名
 * 使用SockJS建立连接，然后创建一个STOMP实例发送连接请求，在连接
 * 成功的回调方法中，首先调整页面设置，然后调用STOMP中的subscribe
 * 方法订阅服务端发送回来的消息，并将服务端发送来的消息展示出来
 */
function connect(){
    if(!$("#name").val()){
        return;
    }
    let socket = new SockJS('/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({},function (frame) {
        //subscribe(destination, callback, headers)
        stompClient.subscribe('/topic/greetings',function (greeting) {
            showGreeting(JSON.parse(greeting.body))
        });
        setConnected(true);
    })
}

/**
 * 断开WebSocket连接
 */
function disconnect() {
    if(stompClient !== null){
        stompClient.disconnect();
    }
    setConnected(false);
}
function sendName() {
    //send(destination, headers, body)
    stompClient.send("/app/hello", {},
        JSON.stringify({'name': $("#name").val(), 'content': $("#content").val()}));
}
function showGreeting(message){
    $("#greetings").append("<div>"+message.name+":"+message.content+"</div>")
}

$(function () {
    $("#connect").click(function () {connect();});
    $("#disconnect").click(function () {disconnect()});
    $("#send").click(function () {sendName();});
})