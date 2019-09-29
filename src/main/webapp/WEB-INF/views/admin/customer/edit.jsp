<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="customerAPI" value="/api/customer" />
<c:url var="customerURL" value="/admin/customer/edit" />
<c:url var="builddingAPI" value="/api-admin-building" />
<c:url var="transactionAPI" value="/api/customer/transaction" />
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
					<li><i class="ace-icon fa fa-home home-icon"></i> <a
						href="admin-home">Trang chủ</a></li>
					<li>Dach sách sản phẩm</li>
					<li>Cập nhật khách hàng</li>
				</ul>
			</div>
			<div class="page-content">
				<div class="page-header">
					<h1>Thông tin khách hàng</h1>
				</div>
			</div>

			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
						<form class="form-horizontal" role="form" id="formEdit">
							<!-- input hidden -->
							<input type="hidden" name="id" value="${customer.id}"
								id="customerId" />
							<!-- input hidden end-->
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Tên đầy đủ</b></label>
								</div>
								<div class="col-sm-7">
									<div class="fg-line">
										<input type="text" class="form-control input-sm" name="name"
											value="${customer.name}" />
									</div>
								</div>
							</div>


							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Số điện thại</b></label>
								</div>
								<div class="col-sm-7">
									<div class="fg-line">
										<input type="number" class="form-control input-sm"
											name="phoneNumber" value="${customer.phoneNumber}" />
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Email</b></label>
								</div>
								<div class="col-sm-7">
									<div class="fg-line">
										<input type="text" class="form-control input-sm" name="email"
											value="${customer.email}" />
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Tên công ty</b></label>
								</div>
								<div class="col-sm-7">
									<div class="fg-line">
										<input type="text" class="form-control input-sm"
											name="company" value="${customer.company}" />
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Tình trạng</b></label>
								</div>
								<div class="col-sm-7">
									<div class="fg-line">
										<input type="text" class="form-control input-sm" name="status"
											value="${customer.status}" />
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Nhu cầu</b></label>
								</div>
								<div class="col-sm-7">
									<div class="fg-line">
										<input type="text" class="form-control input-sm" name="need"
											value="${customer.need}" />
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Ghi chú</b></label>
								</div>
								<div class="col-sm-2">
									<div class="fg-line">
										<textarea rows="3" cols="88" name="node">${customer.node}</textarea>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-6">
									<label><b>Nhân viên phụ trách</b></label>
								</div>
								<div class="col-sm-6">
									<c:forEach var="item" items="${staffs}">
										<tbody>
											<tr>
												<td><input type="checkbox" value="${item.id}" ${item.customerChecked} name="staffId"></td>
												<td>${item.fullName}</td>
											</tr>
										</tbody>		  
										    &nbsp; &nbsp; &nbsp; 
					        		</c:forEach>
								</div>
							</div>
						</form>
						<c:if test="${customerId != null}">
							<div class="form-group">
								<div class="col-sm-1 col-sm-offset-3">
									<button class="btn btn-success" id="bntAddOrUpdateCustomer">Cập
										nhật khách hàng</button>
								</div>
							</div>
						</c:if>
						<c:if test="${customerId == null}">
							<div class="form-group">
								<div class="col-sm-1 col-sm-offset-3">
									<button class="btn btn-success" id="bntAddOrUpdateCustomer">Thêm
										khách hàng</button>
								</div>
							</div>
						</c:if>
					</div>
				</div>
			</div>

			<!-- CSKH -->
			<c:forEach var="list1" items="${transactions}">
				<div class="p/age-content">
					<div class="page-header">
						<h1>${list1.value}
							<button
								class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
								data-toggle="tooltip" title='Thêm hành động' 
								id="${list1.key}">
								<span><i class="fa fa-plus-circle sbigger-110 purple"></i></span>
							</button>
						</h1>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<table class="table table-bordered">
								<thead>
									<tr>
										<td class="col-sm-1">id</td>
										<td class="col-sm-3">Ngày tạo</td>
										<td>Ghi chú</td>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="list2" items="${customer.transactions}">
										<c:if test="${list1.key == list2.code}">
											<tr>
												<th>${list2.id}</th>
												<th>${list2.createdDate}</th>
												<th>${list2.node}</th>
											</tr>
										</c:if>
									</c:forEach>
									<tr>
										<th></th>
										<th></th>
										<th>
											<form id="node">
												<input type="text" style="width: 100%" name="node" id="id${list1.key}"/> 
												<%-- <input type="hidden" value="${list1.key}" name="code" /> --%>
											</form>
										</th>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>

<script type="text/javascript">
	$('#bntAddOrUpdateCustomer').click(function name() {
		var customerId = $('#customerId').val();
		var formData = $('#formEdit').serializeArray();
		var data = {};
		$.each(formData, function(index, v) {
			data["" + v.name + ""] = v.value;
		});
		data['customerId'] = customerId;
		var dataArray = $('input[name="staffId"]:checked').map(function() {
			return $(this).val();
		}).get();
		data['ids'] = dataArray;
		if (customerId == '') {
			addCustomer(data);
		} else {
			updateCustomer(data, customerId);
		}
	})
	function updateCustomer(data, id) {
		$.ajax({
			url : "${customerAPI}",
			data : JSON.stringify(data),
			type : 'PUT',
			contentType : 'application/json',
			success : function(data) {
				window.location.href = "${customerURL}?id="+id+ "&role=staff&message=addCustomerSuccess";
			},
			error : function() {
				window.location.href = "${customerURL}?page=1&maxPageItem=3&sortName=name&sortBy=ASC&message=errorsystem";
			}
		});
	}
	function addCustomer(data) {
		$.ajax({
			url : "${customerAPI}",
			data : JSON.stringify(data),
			type : 'POST',
			contentType : 'application/json',
			success : function(data) {
				window.location.href = "${customerURL}?action=EDIT&id="+data.id+"&role=staff&message=success";
			},
			error : function() {
				window.location.href = "${customerURL}?action=LIST&page=1&maxPageItem=3&sortName=name&sortBy=ASC&message=errorsystem";
				}
			});
		}
		//insert node

		//$('#btnCUSTOMER_CARE').on('click', function(event) {
			
		//});
	$("button").click(function() {
		var idForm = 'id'+this.id;
		var node = document.getElementById(idForm).value;
		var code = this.id;
		var customerId = $('#customerId').val();
		var data = {};
		data['node'] = node;
		data['code'] = code;
		data['customerId'] = customerId;
		insertNode(data, customerId);
	}); 

	function insertNode(data, id) {
		$.ajax({
			url : "${transactionAPI}",
			data : JSON.stringify(data),
			type : 'POST',
			contentType : 'application/json',
			success : function(data) {
				window.location.href = "${customerURL}?id="+id+ "&role=staff&message=success";
			},
			error : function() {
				window.location.href = "${customerURL}?action=LIST&page=1&maxPageItem=3&sortName=name&sortBy=ASC&message=errorsystem";
			}
		});
	}
</script>
</body>
</html>














