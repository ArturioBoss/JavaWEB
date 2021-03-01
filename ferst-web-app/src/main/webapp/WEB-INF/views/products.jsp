<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <jsp:include page="/WEB-INF/views/menustile.jsp" />
    <title>Товары</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>

<jsp:include page="/WEB-INF/views/menu-navigation.jsp" />

<div class="container">
    <div class="row py-2">
        <div class="col-12">
            <a class="btn btn-primary" href="products_jstl?command=NEW">Добавить товар</a>
        </div>

        <div class="col-12">
            <table class="table table-bordered my-2">
                <thead>
                <tr>
                    <th scope="col">№</th>
                    <th scope="col">Наименование</th>
                    <th scope="col">Описание</th>
                    <th scope="col">Цена</th>
                    <th scope="col">Действия</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.products}" var="product">

                    <tr>
                        <td>${product.id}</td>
                        <td>${product.name}</td>
                        <td>${product.description}</td>
                        <td>${product.price}</td>
                    </tr>

                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

</body>
</html>
