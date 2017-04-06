/**
 * Created by patrik on 2017.03.13..
 */


$( function() {
    $( "#datepicker" ).datepicker();
} );


document.body.addEventListener("click", function(){
    var targetId = event.target.id;
    var id = targetId.split("-")[1];
    var isEditStart = targetId.split("-")[0] == "editstart";
    if (isEditStart == true) {
        document.getElementById("editId").value = document.getElementById("currentTicketId-" + id).innerHTML;
        document.getElementById("editId2").value = document.getElementById("currentTicketId-" + id).innerHTML;
        document.getElementById("editDeadline").value = document.getElementById("currentTicketDeadline-" + id).innerHTML;
        document.getElementById("editDescription").value = document.getElementById("currentTicketDescription-" + id).innerHTML;
        document.getElementById("editTitle").value = document.getElementById("currentTicketTitle-" + id).innerHTML;
        document.getElementById("editPlayable").value = document.getElementById("currentTicketPlayable-" + id).innerHTML;
    }
})