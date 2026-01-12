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
            <th>Name</th>
            <th>Country</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="producer" items="${producers}">
            <tr>
                <td>
                    <a href="./producers/${producer.id}" class="text-decoration-none">
                            ${producer.name}
                    </a>
                </td>
                <td style="max-width: 400px;">
                        ${producer.country}
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
