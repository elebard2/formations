function RestServiceEmployee(baseUrl) {
	this.baseUrl = baseUrl
}

RestServiceEmployee.prototype = {
		getAll: function(callback) {
			$.get(this.baseUrl, callback)
		},
		
		getOne: function(id, callback) {
			$.get(`${this.baseUrl}/${id}`, callback)
		},
		
		createOrUpdate(employee, callback) {
			$.ajax(this.baseUrl, {
				method: employee.employeeId === null ? 'put' : 'post',
				dataType: 'json',
				contentType: 'application/json',
				data: JSON.stringify(employee),
				success: callback
			})
		},
		
		delete: function(id, callback) {
			$.ajax(`${this.baseUrl}/${id}`, {
				method: 'delete',
				success: () => callback()
			})
		}
}

let editedEmployee = null

function getTableRowForEmployee(employee) {
	return `<tr data-id='${employee.employeeId}'>
				<td>${employee.lastName}</td>
				<td>${employee.firstName}</td>
				<td>${employee.agenceID}</td>
				<td><div class="buttonBar">
					<a class="editButton btn-floating yellow"><i class="material-icons">edit</i></a>
					<a class="deleteButton btn-floating red"><i class="material-icons">delete</i></a>
				</div></td>
			</tr>`
}

function showTable() {
	editedEmployee = null
	
	$('#crudFlipContainer').removeClass('flip')
}

function showCreateForm() {
	editedEmployee = null
	
	$('#lastName').val('')
	$('#firstName').val('')
	$('#agenceID').val('')

	$('#crudFlipContainer').addClass('flip')
	
	$('#lastName').focus()
}

function showEditForm(employee) {
	editedEmployee = employee
	
	$('#lastName').val(employee.lastName)
	$('#firstName').val(employee.firstName)
	$('#agenceID').val(employee.agenceID)
	
	$('#crudFlipContainer').addClass('flip')
	
	$('#lastName').focus()
}

$(function() {
	let service = new RestServiceEmployee('rs/collaborateurs')
	
	service.getAll( (employees) => $('table.employees').append( $(employees.map(getTableRowForEmployee).join('')) ) )
	
	$("table.employees").on("click", ".deleteButton", function() {
		let tableRow = $(this).closest("tr[data-id]")
		let id = tableRow.attr("data-id")
		
		service.delete(id, () => tableRow.remove())
	})
	
	$("table.employees").on("click", ".editButton", function() {
		let tableRow = $(this).closest("tr[data-id]")
		let id = tableRow.attr("data-id")
		
		service.getOne(id, showEditForm)
	})
	
	$('#createButton').click(showCreateForm)
	
	$('#okButton').click(function() {
		employee = editedEmployee
		if( !employee )
			employee = { employeeId : null }
		
		employee.lastName = $('#lastName').val()
		employee.firstName = $('#firstName').val()
		employee.agenceID = $('#agenceID').val()
		
		service.createOrUpdate(employee, function(employee) {
			let rowHtml = getTableRowForEmployee(employee)
			
			let employeeRow = $(`table.employees tr[data-id=${employee.employeeId}]`)
			if( employeeRow.length )
				employeeRow.replaceWith(rowHtml)
			else
				$('table.employees').append( $(rowHtml) )
		})
		
		showTable()
	})
})