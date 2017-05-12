$(document).ready(function() {
    var textContent;
    $(".add").click(function () {
        textContent = $("#inputfield").val();
    });

$(".add").click(function(){
    var li = document.createElement("li");
    li.innerHTML=textContent;
    var checkbox = document.createElement("input");
    checkbox.setAttribute("type","checkbox");
    checkbox.setAttribute("class","checkbox");
    var delBut = document.createElement("button");
    delBut.setAttribute("class","delButton");
    delBut.innerHTML = "X";

    document.getElementById("container").appendChild(checkbox);
    document.getElementById("container").appendChild(li);
    document.getElementById("container").appendChild(delBut);
})
});
