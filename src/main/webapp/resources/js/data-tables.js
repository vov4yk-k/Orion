/**
 * Created by Vova on 02.06.2017.
 */
$(document).ready(function() {
    var table = $('#applicantsTable').DataTable( {
        "ajax": {
            "url": "/applicants",
            "dataSrc": ""
        },
        "columns": [
            { "data": "id"},
            { "data": "name"},
            { "data": "nameTranslit"},
            { "data": "registrationDate"},
            { "data": "vacancy"},
            { "data": "invitationRecieved"},
            { "data": "dateOfReceivingInvitation"},
            { "data": "comment"},
            { "data": "contact"},
            { "data": "recruiter"}
        ],
        "order": [[ 0, "desc" ]],
        "bFilter": false,
        "bInfo": false,
        "dom": '<"top"i>rt<"bottom"flp><"clear">',
        "scrollY":        '80vh',
        "scrollX":        false,
        "responsive":     true,
        "paging":         false
    } );

    $('#text-to-find').on( 'keyup', function () {
        table.search(this.value ).draw();});

    $("#applicantsTable_filter").hide();

} );