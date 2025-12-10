<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Ticket List</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <h1 class="mb-4 text-center">Ticket List</h1>

    <table class="table table-bordered table-striped align-middle table-sm">
        <thead class="table-light">
        <tr>
            <th>Price</th>
            <th>Ticket Count</th>
            <th>Status</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="ticket" items="${tickets}">
            <tr>
                <td>${ticket.price}</td>
                <td>${ticket.ticketCount}</td>
                <td>${ticket.ticketStatus}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
