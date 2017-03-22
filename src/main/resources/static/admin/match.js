/**
 * Created by patrik on 2017.03.06..
 */


$( function() {
    $( "#datepicker" ).datepicker();
} );


document.body.addEventListener("click", function(){
    var targetId = event.target.id;
    var id = targetId.split("-")[1];
    var isEditStart = targetId.split("-")[0] == "editstart";
    if (isEditStart == true) {
        document.getElementById("editId").value = document.getElementById("currentMatchId-" + id).innerHTML;
        document.getElementById("editHomeTeam").value = document.getElementById("currentMatchHomeTeam-" + id).innerHTML;
        document.getElementById("editAwayTeam").value = document.getElementById("currentMatchAwayTeam-" + id).innerHTML;
        document.getElementById("editVenue").value = document.getElementById("currentMatchVenue-" + id).innerHTML;
        document.getElementById("editLeague").value = document.getElementById("currentMatchLeague-" + id).innerHTML;
        document.getElementById("editRound").value = document.getElementById("currentMatchRound-" + id).innerHTML;
        document.getElementById("editLink").value = document.getElementById("currentMatchLink-" + id).innerHTML;
        document.getElementById("editDeadLine").value = document.getElementById("currentMatchDeadLine-" + id).innerHTML;
        document.getElementById("editResult").value = document.getElementById("currentMatchResult-" + id).innerHTML;

    }
})