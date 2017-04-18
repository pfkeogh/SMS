$(document).ready(function() {
	var pageView = getCookie("pageView");
	pageView = setView(pageView);
	$('#newStudent').click(function(){
		if(pageView.formHidden){
			$('#form').slideDown();
			pageView.formHidden=false;
		}
		else{
			pageView.formHidden=true;
			$('#form').slideUp();
		}
		setCookie("pageView", JSON.stringify(pageView), 10);
	});
	
	$('#viewStudent').click(function(){
		if(pageView.tableHidden){
			pageView.tableHidden=false;
			$('#table').slideDown();
		}
		else{
			pageView.tableHidden=true;
			$('#table').slideUp();
		}
		setCookie("pageView", JSON.stringify(pageView), 10);
	});
	
	$("#reset").click(function(){
		//$("formActual").reset();
		clear_form_elements();
		return false;
	});
	
	 $.ajax({
	        url: '/SMS/rest/students',
	        dataType: 'json'
	    }).then(function(data) {
	       console.log(data);
	    });
	$('.delete').click(function(){
		deleteItem($(this).val());
	});

});
function deleteItem(id) {
	var r = confirm("Are you sure?");
    if (r===true) {
    	deleteStudent(id)
    }
    return false;
}
function deleteStudent(id){
	$.ajax({
		type : "POST",
		url : '/SMS/delete/'+id,
		data : id,
		dataType : 'text',
		timeout : 100000,
		success : function(data) {
			location.reload();
		},
		error : function(e) {
			console.log("ERROR: ", e);
		},
		done : function(e) {
			console.log("DONE");
		}
	});
}
function setView(pageView){
	if(pageView===""){
		var formHidden = true;
		var tableHidden = false;
		pageView = {formHidden, tableHidden};
		setCookie("pageView", JSON.stringify(pageView), 10);
	}
	else{
		pageView = JSON.parse(pageView)
	}
	if(pageView.formHidden){
		$('#form').css('display','none')
	}
	if(pageView.tableHidden){
		$('#table').css('display','none')
	}
	return pageView;
}

function clear_form_elements() {
    $("#formActual").find(':input').each(function() {
        switch(this.type) {
            case 'password':
            case 'select-multiple':
            case 'select-one':
            case 'text':
            case 'textarea':
                $(this).val('');
                break;
            case 'number':
            	$(this).val(0);
            case 'checkbox':
            case 'radio':
                this.checked = false;
        }
    });
}