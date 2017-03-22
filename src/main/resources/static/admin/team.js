/**
 * Created by patrik on 2017.03.06..
 */


document.body.addEventListener("click", function(){
    var targetId = event.target.id;
    var id = targetId.split("-")[1];
    var isEditStart = targetId.split("-")[0] == "editstart";
    if (isEditStart == true) {
        document.getElementById("editId").value = document.getElementById("currentTeamId-" + id).innerHTML;
        document.getElementById("editStadium").value = document.getElementById("currentTeamStadium-" + id).innerHTML;
        document.getElementById("editShortName").value = document.getElementById("currentTeamShortName-" + id).innerHTML;
        document.getElementById("editName").value = document.getElementById("currentTeamName-" + id).innerHTML;
    }
})