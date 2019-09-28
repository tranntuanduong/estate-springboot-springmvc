<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url value="/api/building/handover" var = "handOVerAPI" />
<c:url value="/api/customer/staffInCharge" var = "staffInChargeAPI" />
<c:url value = "/admin/building" var = "buildingURL" />
<c:url value = "/admin/customer" var = "customerURL"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<!--reset model  -->
	<script type="text/javascript">
   	 $('body').on('hidden.bs.modal', '.modal', function () {
        $(this).removeData('bs.modal');
      });
 
	</script>
</head>
<body>

<input type="hidden" value="${model.buildingId}" id="buildingId" name="buildingId"/>
<input type="hidden" value="${model.customerId}" id="customerId" name="customerId"/>

	<div class="modal-header">
        <h3 class="modal-title" id="exampleModalLabel">
        	<c:if test="${model.action == 'assignment'}">Giao tòa nhà cho nhân viên</c:if>.
        	<c:if test="${model.action == 'staffInCharge'}">Chọn nhân viên quản lý</c:if>
        </h3>
	      	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
	      	 	<span aria-hidden="true">&times;</span>
	      	</button>
			</div>
			     <div class="modal-body">
			        <table class="table table-condensed">
						<thead>
							<tr>
							   <th>Chọn nhân viênn</th>
							   <th>Tên nhân viên</th>	   			       
							</tr>
						</thead>		       				

		        		<c:forEach var="item" items="${users.listResult}" >		        			
							    <tbody>			    
							      <tr> 
							      	<td>	
							      	
										<c:if test="${model.action == 'assignment'}">
											${item.id}.<input type="checkbox" value="${item.id}"  ${item.buildingChecked}>
										</c:if>		
										<c:if test="${model.action == 'staffInCharge'}">
											${item.id}.<input type="checkbox" value="${item.id}"  ${item.customerChecked}>					
										</c:if>																																								
									</td>
							      	<td>${item.fullName}</td>		 
							      </tr>
							    </tbody>		   
		        		</c:forEach>
		        		
        			</table>
			     </div>
		     <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
        ${ model.action}
        <c:if test="${model.action == 'assignment'}"><button type="button" class="btn btn-primary" id="handOverBuilding">Lưu-1</button></c:if>
        <c:if test="${model.action == 'staffInCharge'}"><button type="button" class="btn btn-primary" id=chooseStaffInCharge>Lưu-2</button></c:if>
</div>
<script type="text/javascript">
$('#handOverBuilding').click(function name() {
	var dataArray = $(' tbody input[type=checkbox]:checked').map(function () {
		return $(this).val();			
	}).get();
	var data = {};
	data['ids'] = dataArray;	
	var buildingId = $('#buildingId').val();
	data['id'] = buildingId;
	handOverBuilding(data, buildingId);	
})
function handOverBuilding(data, id) {
	$.ajax({
		url : "${handOVerAPI}",
		data: JSON.stringify(data),
		type: 'POST',	
		contentType: 'application/json',
		success: function(data) {
			window.location.href = "${buildingURL}?action=LIST&page=1&maxPageItem=3&sortName=name&sortBy=ASC&message=handOver_success";
		},		
		error: function() {
			window.location.href = "${buildingURL}?action=LIST&page=1&maxPageItem=3&sortName=name&sortBy=ASC&message=hanOverErrorsystem";
		}
	});
}

$('#chooseStaffInCharge').click(function name() {
	var dataArray = $(' tbody input[type=checkbox]:checked').map(function () {
		return $(this).val();			
	}).get();
	var data = {};
	data['ids'] = dataArray;	
	var customerId = $('#customerId').val();
	data['id'] = customerId;
	chooseStaffInCharge(data, customerId);	
})

function chooseStaffInCharge(data) {
	$.ajax({
		url : "${staffInChargeAPI}",
		data: JSON.stringify(data),
		type: 'POST',	
		contentType: 'application/json',
		success: function(data) {
			window.location.href = "${customerURL}?action=LIST&page=1&maxPageItem=3&sortName=name&sortBy=ASC&message=staffInCharge_Success";
		},		
		error: function() {
			window.location.href = "${customerURL}?action=LIST&page=1&maxPageItem=3&sortName=name&sortBy=ASC&message=errorsystem";
		}
	});
}

</script>
</body>
</html>