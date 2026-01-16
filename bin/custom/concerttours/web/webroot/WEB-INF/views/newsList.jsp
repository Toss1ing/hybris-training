<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>News List</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <h1 class="mb-4 text-center">News</h1>

    <table class="table table-bordered table-striped align-middle table-sm">
        <thead class="table-light">
        <tr>
            <th style="width: 150px;">Date</th>
            <th style="width: 500px;">Headline</th>
            <th>Content</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="news" items="${newsList}">
            <tr>
                <td>
                    <fmt:formatDate value="${news.date}" pattern="dd.MM.yyyy"/>
                </td>
                <td>
                    <strong>${news.headline}</strong>
                </td>
                <td>
                        ${news.content}
                </td>
            </tr>
        </c:forEach>

        <c:if test="${empty newsList}">
            <tr>
                <td colspan="3" class="text-center text-muted">
                    No news available
                </td>
            </tr>
        </c:if>
        </tbody>
    </table>
</div>

</body>
</html>
