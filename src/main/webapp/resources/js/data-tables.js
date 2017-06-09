/**
 * Created by Vova on 02.06.2017.
 */
$(document).ready(function() {
    var table = initApplicantsTable();

    $('#text-to-find').on( 'keyup', function () {
        table.search(this.value ).draw();});

    $("#applicantsTable_filter").hide();

    showApplicantOnClick(table);

    $('#applicantModalWindow').on('hidden.bs.modal', function () {
        $(this)
            .find("input,textarea,select")
            .val('')
            .end()
            .find("input[type=checkbox], input[type=radio]")
            .prop("checked", "")
            .end();
    });
} );

function initApplicantsTable() {
    return $('#applicantsTable').DataTable( {
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
}

function showApplicantOnClick(table) {
    $('#applicantsTable tbody').on('click', 'tr', function () {
        var data = table.row(this).data();
        $.ajax({
            dataType: 'json',
            url: '/findApplicant/'+data.id,
            success: function(jsondata){
                for (var property in jsondata) {
                    $('#'+property).val(jsondata[property]);
                }
                $('#invitationRecieved').prop('checked',jsondata.invitationRecieved);
                showApplicantDialog();
            }
        });
    } );
}

function showApplicantDialog() {
    $('#applicantModalWindow')
        .css('display', 'block')
        .modal('show');
}

function deleteApplicant() {
    $.ajax({
        dataType: 'json',
        url: '/delete/'+$('#applicantID').val()
    });
    $('#applicantModalWindow').modal('hide');
    updateApplicantsTable();
}

function updateApplicantsTable() {
    var table = $('#applicantsTable').DataTable();
    table.ajax.reload(null, false );
}

function clearApplicantDialog() {

}