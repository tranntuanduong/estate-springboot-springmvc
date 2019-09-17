<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="builddingAPI" value="/api-admin-building"/>
<c:url var = "buildingURL" value = "/admin-building"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="main-content">
		<div class="main-content-inner">
			<div class="breadcrumbs ace-save-state" id="breadcrumbs">
				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Trang chủ</a></li>
					<li><a href="#">Sản phẩm</a></li>
					<li>Thêm sản phẩm</li>
				</ul>
				<!-- /.breadcrumb -->
			</div>
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
						<form class="form-horizontal" role="form" id="formEdit">
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Tên Sản phẩm</b></label>
								</div>
								<div class="col-sm-9">
									<div class="fg-line">
										<input type="text" class="form-control input-sm" name="name" value="${model.name}"/>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Hình ảnh cho sản phẩm</b></label>						
								</div>
								<div class="col-sm-3">
									<div class="fg-line">
										<label class="block clearfix">
											<input type="file" name="file" class="btn btn-info">
										</label>
									</div>
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Người quản lí sản phẩm</b></label>
								</div>
								<div class="col-sm-3">
									<div class="fg-line">
										<c:forEach var="item" items="${users.listResult}" >		        			
											   	<tbody>			    
											      <tr> 
											      	<td>		
														<input type="checkbox" value="${item.id}"  ${item.buildingChecked} name="staffId">																																						
													</td>
											      	<td>${item.fullName}</td>		 
											      </tr>
											    </tbody>		   
						        		</c:forEach>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Quận</b></label>
								</div>
								<div class="col-sm-3">
									<div class="fg-line">
										<select class="form-control" name="district" >
											<option value="" selected>Chọn quận</option>
											<c:forEach var="item" items="${districts}">
												<option value="${item.key}" ${(item.key==model.district)?'selected':''}>${item.value}</option>
											</c:forEach>		
										</select>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Phường</b></label>
								</div>
								<div class="col-sm-9">
									<div class="fg-line">
										<input type="text" class="form-control input-sm" name="ward" value="${model.ward}"/>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Đường</b></label>
								</div>
								<div class="col-sm-9">
									<div class="fg-line">
										<input type="text" class="form-control input-sm" name="street" value="${model.street}"/>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Kết cấu</b></label>
								</div>
								<div class="col-sm-9">
									<div class="fg-line">
										<input type="text" class="form-control input-sm" name="structure" value="${model.structure}"/>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Số tầng hầm</b></label>
								</div>
								<div class="col-sm-9">
									<div class="fg-line">
										<input type="number" class="form-control input-sm" name="numberOfBasement" value="${model.numberOfBasement}"/>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Diện tích sàn</b></label>
								</div>
								<div class="col-sm-9">
									<div class="fg-line">
										<input type="number" class="form-control input-sm" name="buildingArea" value="${model.buildingArea}" id="buildingArea"/>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Hướng</b></label>
								</div>
								<div class="col-sm-9">
									<div class="fg-line">
										<input type="text" class="form-control input-sm" name="direction" value="${model.direction}"/>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Hạng</b></label>
								</div>
								<div class="col-sm-9">
									<div class="fg-line">
										<input type="text" class="form-control input-sm" name="level" value="${model.level}"/>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Diện tích thuê</b></label>
								</div>
								<div class="col-sm-9">
									<div class="fg-line">
										<input type="text" class="form-control input-sm" name="rentArea" value="${model.rentArea}"/>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Mô tả diện tích</b></label>
								</div>
								<div class="col-sm-9">
									<div class="fg-line">
										<textarea class="form-control" id="form-field-8" placeholder="Nhập phần mô tả" 
										style="margin: 0px 15.6563px 0px 0px; height: 85px;  width: 832px;"></textarea>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Giá thuê</b></label>
								</div>
								<div class="col-sm-9">
									<div class="fg-line">
										<input type="number" class="form-control input-sm" name="costRent" value="${model.costRent}"/>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Mô tả giá</b></label>
								</div>
								<div class="col-sm-9">
									<div class="fg-line">
										<input type="text" class="form-control input-sm" name="costDescription" value="${model.costDescription}"/>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Phí ô tô</b></label>
								</div>
								<div class="col-sm-9">
									<div class="fg-line">
										<input type="text" class="form-control input-sm" name="carCost" value="${model.carCost}"/>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Phí mô tô</b></label>
								</div>
								<div class="col-sm-9">
									<div class="fg-line">
										<input type="text" class="form-control input-sm" name="motorbikeCost" value="${model.motorbikeCost}"/>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Phí ngoài giờ</b></label>
								</div>
								<div class="col-sm-9">
									<div class="fg-line">
										<input type="text" class="form-control input-sm"  name="overtimeCost" value="${model.overtimeCost}"/>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Tiền điện</b></label>
								</div>
								<div class="col-sm-9">
									<div class="fg-line">
										<input type="text" class="form-control input-sm"  name="electricityCost" value="${model.electricityCost}"/>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Đặt cọc</b></label>
								</div>
								<div class="col-sm-9">
									<div class="fg-line">
										<input type="text" class="form-control input-sm"  name="deposit" value="${model.deposit}"/>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Thanh toán</b></label>
								</div>
								<div class="col-sm-9">
									<div class="fg-line">
										<input type="text" class="form-control input-sm"  name="payment" value="${model.payment}"/>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Thời hạn thuê</b></label>
								</div>
								<div class="col-sm-9">
									<div class="fg-line">
										<input type="text" class="form-control input-sm"  name="timeContract" value="${model.timeContract}"/>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Thời gian trang trí</b></label>
								</div>
								<div class="col-sm-9">
									<div class="fg-line">
										<input type="text" class="form-control input-sm"  name="timeDecorator" value="${model.timeDecorator}"/>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Tên quản lí</b></label>
								</div>
								<div class="col-sm-9">
									<div class="fg-line">
										<input type="text" class="form-control input-sm"  name="managerName" value="${model.managerName}"/>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>SĐT quản lí</b></label>
								</div>
								<div class="col-sm-9">
									<div class="fg-line">
										<input type="text" class="form-control input-sm"  name="managerPhone" value="${model.managerPhone}"/>
									</div>
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Loại sản phẩm</b></label>
								</div>
								<div class="col-sm-9">
									<div class="fg-line">
											<div class="checkbox">
												<c:forEach var="item" items="${buildingtypes}">
													<label>
														<input type="checkbox" value="${item.key}" name="buildingTypes" ${fn:contains(model.type,item.key) ? 'checked':'' }><b>${item.value}</b>
													</label>
												</c:forEach>
											</div>		
									</div>
								</div>
							</div>		
							<input type="hidden" name="id" value="${model.id}" id="buildingId"/>
							</form>
							<div class="form-group">
								<c:if test="${empty model.id}">
									<div class = "col-sm-1 col-sm-offset-3">
										<button class = "btn btn-success" id="btnAddOrUpdateBuilding">Thêm sản phẩm</button>
									</div>
								</c:if>
								<c:if test="${not empty model.id}">
									<div class = "col-sm-1 col-sm-offset-3">
										<button class = "btn btn-success" id="btnAddOrUpdateBuilding">Cập nhật sản phẩm</button>
									</div>
								</c:if>
							</div>		
									
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- /.main-content -->
<script type="text/javascript">
	$( "#btnAddOrUpdateBuilding" ).click(function() {
	  	addOrUpdateBuilding();
	});	
	
	function addOrUpdateBuilding() {
		var buildingId = $('#buildingId').val();
		var formData = $('#formEdit').serializeArray();
		var data = {};
		var buildingTypes = [];
		$.each(formData, function (index, v ) {
			if (v.name == 'buildingTypes') {
				buildingTypes.push(v.value);
			} else {
				data[""+v.name+""] = v.value;
			}			
		});
		data['buildingTypes'] = buildingTypes;
		var dataArray = $('input[name="staffId"]:checked').map(function () {
			return $(this).val();			
		}).get();
		data['ids'] = dataArray;
		if (buildingId =='') {
			addBuilding(data);
		} else {
			editBuilding(data, buildingId);
		}
	}
	
	function addBuilding(data,id) {
		$.ajax({
			//url: '${builddingAPI}',
			url : 'http://localhost:8087/api/building',
			data: JSON.stringify(data),
			type: 'POST',	
			contentType: 'application/json',
			dataType: 'json',
	
			success: function(data) {
				window.location.href = "${buildingURL}?action=EDIT&id="+data.id+"&message=insert_success";
			},		
			error: function() {
				window.location.href = "${buildingURL}?action=LIST&message=insert_success";
			}
		});
	}
	function editBuilding(data, id) {
		$.ajax({
			//url: '${builddingAPI}',
			url : 'http://localhost:8087/api/building',
			data: JSON.stringify(data),
			type: 'PUT',	
			contentType: 'application/json',	
			success: function(data) {
				window.location.href = "${buildingURL}?action=EDIT&id="+id+"&message=update_success";
			},		
			error: function() {
				window.location.href = "${buildingURL}?action=LIST&message=errorsystem";
			}
		});
	}	
</script>
</body>
</html>














