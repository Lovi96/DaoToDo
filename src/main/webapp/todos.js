$(document).ready(function() {
    $.get("/LoginServlet",function (data) {
        loadAll(data);
    });
    var textContent;
    $(".add").click(function () {
        textContent = $("#inputfield").val();
        $.get("/todosServlet",{"input":textContent},function( data ){
            loadAll(data);
        });
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
function loadAll(json){
    // var database = JSON.parse(json);
    console.log(json);
    // document.getElementById("container").innerHTML="";
    for(var i = 0; i < json.length ; i++ ) {
        var task = json[i];
        console.log(task.name);
        console.log(task.id);
        console.log(task.completion);
        console.log(task.user);
        var li = document.createElement("li");
        li.innerHTML=task.name;
        li.setAttribute("id",task.id);
        li.setAttribute("completion",task.completion);
        li.setAttribute("user",task.user);
        var checkbox = document.createElement("input");
        checkbox.setAttribute("type","checkbox");
        checkbox.setAttribute("class","checkbox");
        if(task.completion==="true"){
            checkbox.checked=true;
        }
        var delBut = document.createElement("button");
        delBut.setAttribute("class","delButton");
        delBut.setAttribute("id",task.id);
        delBut.innerHTML = "X";
        var div = document.createElement("div");
        // div.setAttribute("id","div");
        document.getElementById("container").appendChild(checkbox);
        document.getElementById("container").appendChild(li);
        document.getElementById("container").appendChild(delBut);
        $("#" + json[i].id).text(json[i].name);
        // document.getElementById("container").appendChild(div);
        }
        console.log("fori után");
}
