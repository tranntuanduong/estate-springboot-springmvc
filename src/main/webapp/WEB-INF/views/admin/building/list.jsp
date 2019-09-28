<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var = "buildingAPI" value = "/api/building"/>
<c:url var = "buildingURL" value = "/admin/building"/>
<c:url var="builddingAPI" value="/api/admin/building"/>
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
						<!--search box-->
						<form:form method="get" id="formSubmit" modelAttribute="model">
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
												<div class="col-sm-6">
													<label><b>Tên Sản phẩm</b></label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm"
															name="name" value="${model.name}" />
													</div>
												</div>
												<div class="col-sm-6">
													<label><b>Diện tích sàn</b></label>
													<div class="fg-line">
														<input type="number" class="form-control input-sm"
															name="buildingArea" value="${model.buildingArea}" />
													</div>
												</div>
											</div>
											
											<div class="form-group">
												<div class="col-sm-2">
													<label><b>Quận hiện có</b></label> <select
														class="form-control" name="district">
														<option value="" selected>Chọn quận</option>
														<c:forEach var="item" items="${districts}">
															<option value="${item.key}"
																${(item.key==model.district)?'selected':''}>${item.value}</option>
														</c:forEach>

													</select>
												</div>
												<div class="col-sm-4 col-sm-offset-2">
													<label><b>Phường</b></label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm"
															name="ward" value="${model.ward}" />
													</div>
												</div>
												<div class="col-sm-4">
													<label><b>Đường</b></label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm"
															name="street" value="${model.street}" />
													</div>
												</div>
											</div>
											<div class="form-group">
												<div class="col-sm-4">
													<label><b>Số tầng hầm</b></label>
													<div class="fg-line">
														<input type="number" class="form-control input-sm"
															name="numberOfBasement" value="${model.numberOfBasement}" />
													</div>
												</div>
												<div class="col-sm-4">
													<label><b>Hướng</b></label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm"
															name="direction" value="${model.direction}" />
													</div>
												</div>
												<div class="col-sm-4">
													<label><b>Hạng</b></label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm"
															name="level" value="${model.level}" />
													</div>
												</div>
											</div>
											<div class="form-group">
												<div class="col-sm-3">
													<label><b>Giá thuê từ</b></label>
													<div class="fg-line">
														<input type="number" class="form-control input-sm"
															name="costRentFrom" value="${model.costRentFrom}" />
													</div>
												</div>
												<div class="col-sm-3">
													<label><b>Giá thuê đến</b></label>
													<div class="fg-line">
														<input type="number" class="form-control input-sm"
															name="costRentTo" value="${model.costRentTo}" />
													</div>
												</div>
												<div class="col-sm-3">
													<label><b>Diện tích từ</b></label>
													<div class="fg-line">
														<input type="number" class="form-control input-sm"
															name="areaRentForm" value="${model.areaRentForm}" />
													</div>
												</div>
												<div class="col-sm-3">
													<label><b>Diện tích đến</b></label>
													<div class="fg-line">
														<input type="number" class="form-control input-sm"
															name="areaRentTo" value="${model.areaRentTo }" />
													</div>
												</div>
											</div>
											<div class="form-group">
												<div class="col-sm-4">
													<label><b>Tên quản lý</b></label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm"
															name="managerName" value="${model.managerName}" />
													</div>
												</div>
												<div class="col-sm-4">
													<label><b>Điện thoại công ty</b></label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm"
															name="managerPhone" value="${model.managerPhone}" />
													</div>
												</div>
												<div class="col-sm-4">
													<div class="col-sm-5">
														<label><b>Nhân viên quản lí: </b></label>
														<select class="form-control" id="sel1" name="user_id">
															<option value=""  selected>--Chọn nhân viên--</option>
															<c:forEach var="item" items="${staffs}">
																<option value="${item.id}" ${(item.id==model.user_id)?'selected':''} >${item.fullName}</option>
																
															</c:forEach>
														</select>							
													</div>
												</div>
											</div>
											<div class="form-group">
												<div class="col-sm-4">
													<label><b>Loại tòa nhà: </b></label>
													
														<c:forEach var="item" items="${buildingtypes}">
															<label> <input type="checkbox"
																value="${item.key}" name="buildingTypes"
																${fn:contains(fn:join(model.buildingTypes,','),item.key) ? 'checked':'' }><b>${item.value}</b>
															</label>
														</c:forEach>
													
												</div>
												
											</div>
										
											<input type="hidden" name="action" value="LIST">
											<div class="form-group">
												<div class="col-sm-1">
													<button class="btn btn-success fa fa-search" type="submit">Tìm
														kiếm</button>
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
										title='Thêm tòa nhà'		
										href='<c:url value="/admin/building/edit"/>'> <span><i class="fa fa-plus-circle sbigger-110 purple"></i></span>
									</a>
									<button type="button" id="bntDelete"
											class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
											data-toggle="tooltip" title='Xóa tòa nhà'>
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
						        <th>Tên sản phẩm</th>
						        <th>Địa chỉ</th>
						        <th>Số tầng hầm</th>
						        <th>Diện tích sàn</th>
						        <th>Diện tích thuê</th>
						        <th>Giá thuê</th>
						        <th>Loại tòa nhà</th>
						        <th>Tên quản lý</th>
						        <th>SĐT quản lí</th>
						        <th>Nhân viên</th>
						     	<th>Thao tác</th>			       
						      </tr>
						    </thead>
						    
						    <tbody>
						     	<c:forEach var="item" items="${model.listResult}">
							     	 <tr>
							     	 	<td><input type="checkbox" value="${item.id}" id="checkbox_${item.id}"></td>
								        <td>${item.name}</td>
								        <td>${item.address}</td>
								        <td>${item.numberOfBasement}</td>
								        <td>${item.buildingArea}</td>
								        <td>${item.rentArea}</td>
								        <td>${item.costRent}</td>
								        <td>${item.type}</td>
								        <td>${item.managerName}</td>
								        <td>${item.managerPhone}</td>	
								        <td>${item.staffInCharge}</td>								      
								      	<td>					        					
										<table>
											 <tr>
											     <td>
												     <a class="btn btn-xs btn-primary btn-edit"
														data-toggle="tooltip"
														title='Cập nhật tòa nhà'
														href='<c:url value="/admin/building/edit?id=${item.id}"/>'> 
														<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
													</a>
												</td>
											    <td>
											      	<a class="btn btn-xs btn-primary btn-edit" data-toggle="modal" data-target="#exampleModal"
														data-toggle="tooltip"
														title='Giao tòa nhà'
														href='<c:url value="/staffList?action=assignment&buildingId=${item.id}&role=staff"/>'>
														<i class="menu-icon fa fa-list" aria-hidden="true"></i>
													</a>
											      </td>
											  </tr>
											  <tr>
											      <td>C</td>
											      <td>D</td>
											  </tr>
										</table>
								        </td>
								        					        
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
		deleteBuilding(dataArray);	
	})
	
	function deleteBuilding(data) {
		$.ajax({
			url : '${buildingAPI}',
			data: JSON.stringify(data),
			type: 'DELETE',	
			contentType: 'application/json',
			success: function(data) {
				window.location.href = "${buildingURL}?page=1&maxPageItem=3&sortName=name&sortBy=ASC&message=deleteSuccess";
			},		
			error: function() {
				window.location.href = "${buildingURL}?page=1&maxPageItem=3&sortName=name&sortBy=ASC&message=errorsystem";
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














