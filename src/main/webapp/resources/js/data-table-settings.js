
$(document).ready(function() {

    var usersTable = initUsersTable();
    showUserOnClick(usersTable);

    $('#userModalWindow').on('hidden.bs.modal', function () {
        location.reload();
    });
} );

function initUsersTable() {
    return $('#usersTable').DataTable( {
        "ajax": {
            "url": "/groupMembers",
            "dataSrc": ""
        },
        "columns": [
            { "data": "id",
                className: "dt-body-center",
                "width": "2%"
            },
            { "data": "username.name",
                className: "dt-body-center",
                "width": "20%"},
            { "data": "username.language",
                className: "dt-body-center",
                "width": "7%"},
            {
                "data": "username.enabled",
                render: function (data, type, row) {
                    if (type === 'display') {
                        return '<input type="checkbox" class="editor-active" onclick="return false" '+(data?'checked':'')+'>';
                    }
                    return data;
                },
                className: "dt-body-center",
                "width": "3%"
            },
            { "data": "group.group_name",
                className: "dt-body-center",
                "width": "20%"}
        ],
        "bInfo": false,
        "dom": '<"top"i>rt<"bottom"flp><"clear">',
        "scrollY":        '80vh',
        "scrollX":        false,
        "responsive":     true,
        "paging":         false,
        "searching":       false
    } );
}

function showUserOnClick(table) {
    $('#usersTable tbody').on('click', 'tr', function () {
        var data = table.row(this).data();
        $.ajax({
            dataType: 'json',
            url: '/findGroupMember/' + data.id,
            success: function (jsondata) {
                for (var prop in jsondata) {
                    if (typeof jsondata[prop] === 'number') {
                        var field = $('#' + prop);
                        field.val(jsondata[prop]);
                    } else {
                        for (var property in jsondata[prop]) {
                            var field = $('#' + prop + '-' + property);
                            var fieldData = jsondata[prop][property];
                            field.val(fieldData);
                        }
                    }
                }
                $('#username-enabled').prop('checked', jsondata.username.enabled);
                $('.jq-selectbox__select-text')[0].innerHTML = $('#' + jsondata.username.language).val();
                $('.jq-selectbox__select-text')[1].innerHTML = $('#'+jsondata.group.id).val();
                showUserDialog();
            }
        });
    });
}

function showUserDialog() {
    $('#userModalWindow')
        .css('display', 'block')
        .modal('show');
}

function setGroup(group){
    $('#group_name').val(group.options[group.selectedIndex].value);
    $('#group-id').val(group.options[group.selectedIndex].id);
}

function newUser(){
    showUserDialog();
    var option = $('#select-group option')[0];
    $('#group_name').val(option.value);
    $('#group-id').val(option.id);
}

function deleteGroupMember(id){
    $.ajax({
        dataType: 'json',
        url: '/deleteGroupMember/'+$('#id').val()
    });
    $('#userModalWindow').modal('hide');
}