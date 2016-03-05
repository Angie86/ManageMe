/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


window.onload = init;
var socket = new WebSocket("ws://localhost:8080/ManageMe-war/actions");

socket.onmessage = onMessage;

function onMessage(event) {
    var device = JSON.parse(event.data);
    if (device.action === "add") {
        printDeviceElement(device);
        
    }
//    if (device.action === "remove") {
//        document.getElementById(device.id).remove();
//        //device.parentNode.removeChild(device);
//    }
    if (device.action === "toggle") {
        var node = document.getElementById(device.id);
        var statusText = node.children[2];
        if (device.status === "On") {
            statusText.innerHTML = "Status: " + device.status + " (<a href=\"#\" OnClick=toggleDevice(" + device.id + ")>Turn off</a>)";
        } else if (device.status === "Off") {
            statusText.innerHTML = "Status: " + device.status + " (<a href=\"#\" OnClick=toggleDevice(" + device.id + ")>Turn on</a>)";
        }
       
    }
}

function addDevice(status,description,name,type) {
    var DeviceAction = {
        action: "add",
        status: status,
        name: name,
        type: type,
        description: description
    };
    socket.send(JSON.stringify(DeviceAction));
}

function removeDevice(element) {
    var id = element;
    var DeviceAction = {
        action: "remove",
        id: id
    };
    socket.send(JSON.stringify(DeviceAction));
    
}

function toggleDevice(element) {
    var id = element;
    var DeviceAction = {
        action: "toggle",
        id: id
    };
    socket.send(JSON.stringify(DeviceAction));
}

function printDeviceElement(device) {
    var content = document.getElementById("content");
    
    var deviceDiv = document.createElement("div");
    deviceDiv.setAttribute("id", device.id);
    deviceDiv.setAttribute("class", "device " + device.type);
    content.appendChild(deviceDiv);

    var deviceName = document.createElement("span");
    var deviceDescription = document.createElement("span");
    var deviceType = document.createElement("span");
    var d = new Date();
//    deviceName.setAttribute("class", "deviceName");
    deviceName.innerHTML = "<div class='item'><img src='"+device.name+"' alt='user image' class='offline' /><p class='message'><a class='name'><small class='text-muted pull-right'><i class='fa fa-clock-o'></i>"+"  " + d.getHours()+":"+d.getMinutes()+"    "+d.getDate()+"/"+(d.getMonth()+1)+"/"+d.getFullYear()+"</small>"+device.description+"</a>"+device.type+"</p></div>";
    deviceDiv.appendChild(deviceName);

    
//    deviceType.innerHTML = "<b>Type:</b> " + device.type;
    deviceDiv.appendChild(deviceType);

//    var deviceStatus = document.createElement("span");
//    if (device.status === "On") {
//        deviceStatus.innerHTML = "<b>Status:</b> " + device.status + " (<a href=\"#\" OnClick=toggleDevice(" + device.id + ")>Turn off</a>)";
//    } else if (device.status === "Off") {
//        deviceStatus.innerHTML = "<b>Status:</b> " + device.status + " (<a href=\"#\" OnClick=toggleDevice(" + device.id + ")>Turn on</a>)";
//        //deviceDiv.setAttribute("class", "device off");
//    }
//    deviceDiv.appendChild(deviceStatus);

    
//    deviceName.setAttribute("class", "deviceDescription");
//    deviceDescription.innerHTML = device.name+"</p></div>";
//    deviceDiv.appendChild(deviceDescription);
    scrollBottom();

//    var removeDevice = document.createElement("span");
//    removeDevice.setAttribute("class", "removeDevice");
//    removeDevice.innerHTML = "<a href=\"#\" OnClick=removeDevice(" + device.id + ")>Remove device</a>";
//    deviceDiv.appendChild(removeDevice);
}

function showForm() {
    document.getElementById("addDeviceForm").style.display = '';
}

function hideForm() {
    document.getElementById("addDeviceForm").style.display = "none";
}

function formSubmit() {
    var form = document.getElementById("addDeviceForm");
    var name = form.elements["device_name"].value;
    var type = form.elements["device_type"].value;
    var description = form.elements["device_description"].value;
    document.getElementById("addDeviceForm").reset();
    passToChatBean([{
                                name: 'message',
                                value: description
                            }
                        ]);
    
    addDevice(status,name,type,description);
    
}

function init() {
    
}

function scrollBottom(){
    
    $("#divScroll").scrollTop($("#divScroll")[0].scrollHeight);
    
}
