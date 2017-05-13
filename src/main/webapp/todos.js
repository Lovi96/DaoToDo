$(document).ready(function () {
    $.get("/LoginServlet", function (data) {
        loadAll(data);
        console.log("started")
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


// $(".add").click(function(){
//     var li = document.createElement("li");
//     li.innerHTML=textContent;
//     var checkbox = document.createElement("input");
//     checkbox.setAttribute("type","checkbox");
//     checkbox.setAttribute("class","checkbox");
//     var delBut = document.createElement("button");
//     delBut.setAttribute("class","delButton");
//     delBut.innerHTML = "X";
//
//     document.getElementById("container").appendChild(checkbox);
//     document.getElementById("container").appendChild(li);
//     document.getElementById("container").appendChild(delBut);
// })
    });

});
function loadAll(data) {
    // var database = JSON.parse(json);
    console.log(data);
    // document.getElementById("container").innerHTML="";
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
        delBut.setAttribute("class", "delButton");
        delBut.setAttribute("onclick", "deleteButton("+task.id+")");
        delBut.innerHTML = "X";
        var div = document.createElement("div");
        // div.setAttribute("id","div");
        document.getElementById("container").appendChild(checkbox);
        document.getElementById("container").appendChild(li);
        document.getElementById("container").appendChild(delBut);
        $("#" + task.id).text(task.name);
        // document.getElementById("container").appendChild(div);
    }
    console.log("fori után");
}
function deleteButton(id){
    $.get("/todosServlet",{delete:id},function(data){
        loadAll(data)
    })
}
function toggleStatus(id){
    $.get("/todosServlet",{toggle:id})
}
