var name;
var surname;
var telNo;
var openerId;

$(document).ready(function() {

	$("#save").on("click", function() {

		var x = $("#userName").val();
		if (x == null || x == "") {
			alert("The name may consist of the following characters: a-z, 0-9 and can't be empty.");
			return false;
		}
		var y = $("#userSurname").val();
		if (y == null || y == "") {
			alert("The surname may consist of the following characters: a-z, 0-9 and can't be empty.");
			return false;
		}
		var z = $("#phoneNo").val();
		if (z == null || z == "") {
			alert("The phone may consist of the following characters: 0-9 and can't be empty.");
			return false;
		}
	});

	$("#phoneNo").mask("(9999)-999-99-99");

	$("#phoneNo").on("blur", function() {
		var last = $(this).val().substr($(this).val().indexOf("-") + 1);

		if (last.length == 3) {
			var move = $(this).val().substr($(this).val().indexOf("-") - 1, 1);
			var lastfour = move + last;
			var first = $(this).val().substr(0, 9);

			$(this).val(first + '-' + lastfour);
		}
	});

	$("#editPhoneNo").mask("(9999)-999/99/99");

	$("#editPhoneNo").on("blur", function() {
		var last = $(this).val().substr($(this).val().indexOf("-") + 1);

		if (last.length == 3) {
			var move = $(this).val().substr($(this).val().indexOf("-") - 1, 1);
			var lastfour = move + last;
			var first = $(this).val().substr(0, 9);

			$(this).val(first + '-' + lastfour);
		}
	});

	$(".remover").click(function() {

		var removerId = $(this).attr("id");

		removerId = removerId.substring(0, (removerId.length - 7));

		$("#dialog-confirm").dialog({
			resizable : false,
			height : 140,
			modal : true,

			buttons : [ {
				id : "btn-accept",
				text : "Delete",
				click : function() {

					$(this).dialog("close");
				}
			}, {
				id : "btn-cancel",
				text : "Cancel",
				click : function() {
					$(this).dialog("close");
				}
			} ]
		});

		$("#btn-accept").on("click", function(e) {
			$("#" + removerId + "table").hide();
			$.post("deleteUser", {
				id : removerId,
			}, function(data) {
			});

		});

	});

});

$(function() {

	$(".opener").click(function() {

		openerId = $(this).attr("id");

		openerId = openerId.substring(0, (openerId.length - 6));

		name = $("#" + openerId + "tablename").html();
		surname = $("#" + openerId + "tablesurname").html();
		telNo = $("#" + openerId + "tablephoneno").html();

		$("#editName").attr("placeholder", name);
		$("#editSurname").attr("placeholder", surname);
		$("#editPhoneNo").attr("placeholder", telNo);

		$("#dialog").dialog("open");

	});

	$("#dialog").dialog({
		autoOpen : false,
		show : {
			effect : "blind",
			duration : 1000
		},
		hide : {
			effect : "explode",
			duration : 1000
		}
	});

	$("#dialogCloseButton").on("click", function(e) {

		$("#dialog").dialog("close");

	});

	$("#dialogEditButton").on("click", function(e) {

		name = $("#" + openerId + "tablename").html();
		surname = $("#" + openerId + "tablesurname").html();
		telNo = $("#" + openerId + "tablephoneno").html();

		$("#editName").attr("placeholder", name);
		$("#editSurname").attr("placeholder", surname);
		$("#editPhoneNo").attr("placeholder", telNo);

		if ($("#editName").val() != "") {
			name = $("#editName").val();
		}
		if ($("#editSurname").val() != "") {
			surname = $("#editSurname").val();
		}
		if ($("#editPhoneNo").val() != "") {
			telNo = $("#editPhoneNo").val();
		}

		$.post("updateUser", {
			id : openerId,
			editUserName : name,
			editUserSurName : surname,
			editTel : telNo
		}, function(data) {
			$("#" + openerId + "tablename").html(name);
			$("#" + openerId + "tablesurname").html(surname);
			$("#" + openerId + "tablephoneno").html(telNo);
			$("#dialog").dialog("close");
		});

	});

});




