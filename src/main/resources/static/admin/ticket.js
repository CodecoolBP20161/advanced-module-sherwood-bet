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
        document.getElementById("editDeadline").value = document.getElementById("currentTicketDeadline-" + id).innerHTML;
        document.getElementById("editDescription").value = document.getElementById("currentTicketDescription-" + id).innerHTML;
        document.getElementById("editTitle").value = document.getElementById("currentTicketTitle-" + id).innerHTML;
        document.getElementById("setMatch1").value = document.getElementById("currentTicketMatch1-" + id).innerHTML;
        document.getElementById("setMatch2").value = document.getElementById("currentTicketMatch2-" + id).innerHTML;
        document.getElementById("setMatch3").value = document.getElementById("currentTicketMatch3-" + id).innerHTML;
        document.getElementById("setMatch4").value = document.getElementById("currentTicketMatch4-" + id).innerHTML;
        document.getElementById("setMatch5").value = document.getElementById("currentTicketMatch5-" + id).innerHTML;
        document.getElementById("setMatch6").value = document.getElementById("currentTicketMatch6-" + id).innerHTML;
        document.getElementById("setMatch7").value = document.getElementById("currentTicketMatch7-" + id).innerHTML;
    }
})