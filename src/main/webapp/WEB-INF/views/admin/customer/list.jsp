<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var ="customerURL" value ="/admin-customer"/>
<c:url var="customerAPI" value="/api/customer" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dach sách sản phẩm</title>
</head>
<body>
	<div class="main-content">
		<div class="main-content-inner">
			<div class="breadcrumbs ace-save-state" id="breadcrumbs">
				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a href="admin-home">Trang chủ</a></li>
					<li>Dach sách sản phẩm</li>
				</ul>
				<!-- /.breadcrumb -->
			</div>
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
					<!-- start form -->
					
						<form:form method="get" id="formSubmit" modelAttribute="model">
						<!--search box-->
							<div class="widget-box table-filter">
								<div class="widget-header">
									<h4 class="widget-title">Tìm kiếm</h4>
									<div class="widget-toolbar">
										<a href="#" data-action="collapse"> <i
											class="ace-icon fa fa-chevron-up"></i>
										</a>
									</div>
								</div>
								<div class="widget-body">
									<div class="widget-main">
										<div class="form-horizontal">
											
											
											
											
											<div class="form-group">
												<div class="col-sm-4">
													<label><b>Tên khách hàng</b></label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm"
															name="name" value="${model.name}" />
													</div>
												</div>
												<div class="col-sm-3">
													<label><b>Di động</b></label>
													<div class="fg-line">
														<input type="number" class="form-control input-sm"
															name="phoneNumber" value="${model.phoneNumber}" />
													</div>
												</div>
												<div class="col-sm-5">
													<label><b>Email</b></label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm"
															name="email" value="${model.email}" />
													</div>
												</div>
											</div>
											
											<div class="form-group">
												<div class="col-sm-4">
													<label><b>Nhân viên phụ trách</b></label>
													<select class="form-control" name="userId">
														<option value=""   selected>--Chọn nhân viên--</option>
														<c:forEach var="item" items="${staffs}">
															<option value="${item.id}" ${(item.id==model.userId)?'selected':''}>${item.fullName}</option>
														</c:forEach>
													</select>
													<br>
												</div>
											</div>
											<input type="hidden" name="action" value="LIST">
											<div class="form-group">
												<div class="col-sm-1">
													<button class="btn btn-success fa fa-search" type="submit">Tìm kiếm</button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<input type="hidden" value="1" id="page" name="page"/>
						<input type="hidden" value="3" id="maxPageItem" name="maxPageItem"/>
						<input type="hidden" value="ASC" id="sortBy" name="sortBy"/>
						<input type="hidden" value="name" id="sortName" name="sortName"/>
						</form:form>
						<!-- end form -->
						
							<!-- button add, delete -->
						<div class="table-btn-controls">
							<div class="pull-right tableTools-container">
								<div class="dt-buttons btn-overlap btn-group">
									<a  class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
										data-toggle="tooltip"
										title='Thêm khách hàng'		
										href='<c:url value="/admin/customer/edit?role=staff"/>'> <span><i class="fa fa-plus-circle sbigger-110 purple"></i></span>
									</a>
									<button type="button" id="bntDelete"
											class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
											data-toggle="tooltip" title='Xóa khách hàng'>
											<span><i class="fa fa-trash-o bigger-110 pink"></i></span>
									</button>
									
								</div>
							</div>
						</div>
									
					</div>
				</div>
				<!-- table -->
				<div class="row">
					<div class="col-xs-12">
					 	<table class="table table-bordered">
						    <thead>
						      <tr>
						      	<th><input type="checkbox" value="#"></th>
						        <th>Tên</th>
						        <th>Di động</th>
						        <th>Email</th>
						        <th>Nhu Cầu</th>
						        <th>Người nhập</th>						       
						        <th>Tình trạng</th>        
						        <th>Nhân Viên phụ trách</th>
						     	<th>Thao tác</th>			       
						      </tr>
						    </thead>
						    
						    <tbody>
						     	<c:forEach var="item" items="${model.listResult}">
							     	 <tr>
							     	 	<td><input type="checkbox" value="${item.id}" id="checkbox_${item.id}"></td>
								        <td>${item.name}</td>
								        <td>${item.phoneNumber}</td>
								        <td>${item.email}</td>
								        <td>${item.need}</td>
								        <td>${item.createdBy}</td>
								        <td>${item.status}</td>
								       	<td>${item.staffInCharge}</td>
								       						      
								      	<td>						        					
										<table>
											 <tr>
											     <td>
												     <a class="btn btn-xs btn-primary btn-edit"
														data-toggle="tooltip"
														title='Cập nhật thông tin khách hàng'
														href='<c:url value="/admin/customer/edit?id=${item.id}&role=staff"/>'>
														<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
													</a>s
													
												</td>
											    <td>
											      	<a class="btn btn-xs btn-primary btn-edit" data-toggle="modal" data-target="#exampleModal"
														data-toggle="tooltip"
														title='Chọn nhân viên quản lý'
														href='<c:url value="/staffList?action=staffInCharge&customerId=${item.id}&role=staff"/>'>
														<i class="menu-icon fa fa-list" aria-hidden="true"></i>
													</a>
											      </td>
											  </tr>
											  <tr>
											      <td>C</td>
											      <td>D</td>
											  </tr>
										</table>
								        	
								        					        
							      	</tr>
						     	</c:forEach>
						      
						    </tbody>
						  </table>
					</div>
				</div>
				<!-- paging -->
				<div class="container">
				    <nav aria-label="Page navigation">
				        <ul class="pagination" id="pagination"></ul>
				    </nav>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal -->

<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <!-- model content -->
    </div>
  </div>
</div>
	<!-- /.main-content -->
<script type="text/javascript">
	$('#bntDelete').click(function name() {
		var dataArray = $(' tbody input[type=checkbox]:checked').map(function () {
			return $(this).val();			
		}).get();
		deleteCustomer(dataArray);	
	})
	
	function deleteCustomer(data) {
		$.ajax({
			url : "${customerAPI}",
			data: JSON.stringify(data),
			type: 'DELETE',	
			contentType: 'application/json',
			success: function(data) {
				window.location.href = "${buildingURL}?action=LIST&page=1&maxPageItem=3&sortName=name&sortBy=ASC&message=delete_success";
			},		
			error: function() {
				window.location.href = "${buildingURL}?action=LIST&page=1&maxPageItem=3&sortName=name&sortBy=ASC&message=errorsystem";
			}
		});
	}
	var totalPage = ${model.totalPage};
	var currentPage = ${model.page};
	 $(function () {
	        window.pagObj = $('#pagination').twbsPagination({
	            totalPages: totalPage,
	            visiblePages: 5,      
	            startPage: currentPage,
	            onPageClick: function (event, page) {                 
	                if(currentPage != page){
	                	$('#page').val(page);
	                	$('#maxPageItem').val(3);
	                	$('#sortName').val('name');
	                	$('#sortBy').val('ASC');
	               	 	$('#formSubmit').submit();	   
	               	}             		                
	            }
	        })
	    });
	 
</script>
</body>
</html>














