$(document).ready(function () {
    $.get("/LoginServlet", function (data) {
        console.log("started");
        loadAll(data);
    });
    var textContent;
    $(".add").click(function () {
        textContent = $("#inputfield").val();
        $.ajax({
            type: "GET",
            url: "/todosServlet",
            data: {"input": textContent},
            success: function (data) {
                console.log("data elment");
                loadAll(data);
            }
        });
    });
    $(".all").click(function () {
        $.get("/todosServlet", function (data) {
            loadAll(data)
        })
    });
    $(".done").click(function () {
        $.get("/todosServlet", {"getDone":""},function (data) {
            loadAll(data)
        })
    });
    $(".incomplete").click(function () {
        $.get("/todosServlet",{"getInProgress":""},function (data) {
            loadAll(data)
        })
    })

});
function loadAll(data) {
    console.log(data);
    document.getElementById("container").innerHTML="";
    for (var i in data) {
        var task = data[i].valueOf();
        console.log(task.name);
        console.log(task.id);
        console.log(task.completion);
        console.log(task.user);
        var li = document.createElement("li");
        li.innerHTML = task.name;
        li.setAttribute("id", task.id);
        li.setAttribute("completion", task.completion);
        li.setAttribute("user", task.user);
        var checkbox = document.createElement("input");
        checkbox.setAttribute("type", "checkbox");
        checkbox.setAttribute("class", "checkbox");
        checkbox.setAttribute("onclick","toggleStatus(" + task.id + ")");
        if (task.completion === true) {
            checkbox.checked = true;
        }
        var delBut = document.createElement("button");
        delBut.setAttribute("type", "button");
        delBut.setAttribute("onclick", "deleteButton("+task.id+")");
        delBut.innerHTML = "X";
        var br = document.createElement("br");
        var div = document.createElement("div");
        div.appendChild(checkbox);
        div.appendChild(li);
        div.appendChild(delBut);
        $("#" + task.id).text(task.name);
        document.getElementById("container").appendChild(div);
        document.getElementById("container").appendChild(br);
    }
    console.log("foreach után");
}
function deleteButton(id){
    $.ajax({
        type:"DELETE",
        url:"/todosServlet/"+id
    });
    $.get("/todosServlet",function (data) {
        loadAll(data)
    })
}
function toggleStatus(id){
    $.get("/todosServlet",{toggle:id})
}
