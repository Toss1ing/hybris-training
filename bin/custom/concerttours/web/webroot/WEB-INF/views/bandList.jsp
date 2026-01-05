<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Band List</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <h1 class="mb-4 text-center">Band List</h1>

    <table class="table table-bordered table-striped align-middle table-sm">
        <thead>
        <tr>
            <th>Image</th>
            <th>Name</th>
            <th>History</th>
            <th>Albums Sold</th>
            <th>Genres</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="band" items="${bands}">
            <tr>
                <td>
                    <c:if test="${not empty band.smallImageURL}">
                        <div class="d-flex justify-content-center">
                            <img src="${band.smallImageURL}" alt="${band.name}" style="width: 50px; height: auto;"/>
                        </div>
                    </c:if>
                </td>
                <td>
                    <a href="./bands/${band.id}" class="text-decoration-none">
                            ${band.name}
                    </a>
                </td>
                <td style="max-width: 400px;">
                        ${band.description}
                </td>
                <td>${band.albumsSold}</td>
                <td>
                    <c:forEach var="genre" items="${band.genres}" varStatus="loop">
                        ${genre}<c:if test="${!loop.last}">, </c:if>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
