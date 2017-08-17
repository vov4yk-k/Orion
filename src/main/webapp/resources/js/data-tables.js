/**
 * Created by Vova on 02.06.2017.
 */
$(document).ready(function() {
    
    var table = initApplicantsTable();

    setInterval(
        function(){
            table.ajax.reload();
        },
        30000
    );

    $('#text-to-find').on( 'keyup', function () {
        table.ajax.reload();
        table.search(this.value).draw();});

    $("#applicantsTable_filter").hide();

    showApplicantOnClick(table);

    $('#applicantModalWindow').on('hidden.bs.modal', function () {
        /*$(this)
            .find("input,textarea")
            .val(undefined)
            .end()
            .find("input[type=checkbox], input[type=radio]")
            .prop("checked", "")
            .end();
        table.ajax.reload();*/
        location.reload();
    });

    $('#invitationRecieved').click(function() {
        this.value = this.checked;
    });

    (function($) {
        $(function() {
            $('select').styler();
        });
    })(jQuery);

} );

function initApplicantsTable() {
    return $('#applicantsTable').DataTable( {
        "ajax": {
            "url": "/applicants",
            "dataSrc": ""
        },
        "columns": [
            { "data": "id",
                className: "dt-body-center",
                "width": "2%"
            },
            { "data": "name","width": "20%"},
            { "data": "nameTranslit","width": "20%"},
            { "data": "registrationDate",
                className: "dt-body-center",
                "width": "5%"
            },
            { "data": "vacancy", "width": "15%"},
            {
                "data": "invitationRecieved",
                render: function (data, type, row) {
                    if (type === 'display') {
                        return '<input type="checkbox" class="editor-active" onclick="return false">';
                    }
                    return data;
                },
                className: "dt-body-center",
                "width": "3%"
            },
            { "data": "dateOfReceivingInvitation",
                className: "dt-body-center",
                "width": "5%"
            },
            { "data": "comment","width": "15%"},
            { "data": "contact","width": "8%"},
            { "data": "recruiter","width": "7%"}
        ],
        "order": [[ 0, "desc" ]],
        //"bFilter": false,
        "bInfo": false,
        "dom": '<"top"i>rt<"bottom"flp><"clear">',
        "scrollY":        '80vh',
        "scrollX":        false,
        "responsive":     true,
        "paging":         false,
        rowCallback: function ( row, data ) {
            $('input.editor-active', row).prop('checked', data.invitationRecieved);
        }
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
                    var field = $('#'+property);
                    var fieldData = jsondata[property];
                    field.val(fieldData);
                }
                $('#invitationRecieved').prop('checked',jsondata.invitationRecieved);
                $('.jq-selectbox__select-text')[0].innerHTML = jsondata.recruiter;
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
        url: '/delete/'+$('#id').val()
    });
    $('#applicantModalWindow').modal('hide');
}

function setRecruiter(recr){
    $('#recruiter').val(recr.value);
}

function setLanguage(lan){
    $('#language').val(lan.options[lan.selectedIndex].id);
}