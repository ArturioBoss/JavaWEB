<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="/WEB-INF/views/menustile.jsp" />
    <title>Товары</title>
</head>

<body>

<jsp:include page="/WEB-INF/views/menu-navigation.jsp" />

<div class="container">
    <div class="row py-2">
        <div class="col-12">
<form method="post" action="products_jstl">
    <input type="hidden" id="id" name="id" value="${product.id}">

    <div class="form-group">
        <label>Наименование</label>
        <input type="text" class="form-control" id="name" name="name" value="${product.name}" placeholder="Введите наименование">
    </div>
    <div class="form-group">
        <label>Описание</label>
        <input type="text" class="form-control" id="description" name="description" value="${product.description}" placeholder="Введите описание">
    </div>
    <div class="form-group">
        <label>Цена</label>
        <input type="number" class="form-control" id="price" name="price" value="${product.price}" placeholder="Введите цену">
    </div>
    <input  class="btn btn-primary" type="submit" value="Сохранить">
    <input  class="btn btn-primary" type="button" value="Отменить" onclick="window.location.href='products_jstl'">
</form>
        </div>
    </div>
</div>

</body>