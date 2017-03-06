/**
 * Created by patrik on 2017.03.03..
 */



var userDetails = {
    id:"",
    userName:"",
    password:"",
    email:""
};


document.body.addEventListener("click", function(){
    var targetId = event.target.id;
    var id = targetId.split("-")[1];
    var isEditStart = targetId.split("-")[0] == "editstart";
    if (isEditStart == true) {
        document.getElementById("editId").value = document.getElementById("currentUserId-" + id).innerHTML;
        document.getElementById("editName").value = document.getElementById("currentUserName-" + id).innerHTML;
        document.getElementById("editPassword").value = document.getElementById("currentUserPassword-" + id).innerHTML;
        document.getElementById("editEmail").value = document.getElementById("currentUserEmail-" + id).innerHTML;
    }
})
