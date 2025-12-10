<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Tour Details - ${tour.tourName}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <h1 class="mb-4 text-center">Tour Details: ${tour.tourName}</h1>

    <div class="card shadow-sm mb-4">
        <div class="card-body">
            <h5 class="card-title">${tour.tourName}</h5>
            <c:if test="${not empty tour.concerts}">
                <p class="mt-3"><strong>Schedule:</strong></p>
                <div class="table-responsive">
                    <table class="table table-striped table-bordered table-hover">
                        <thead class="table-light">
                        <tr>
                            <th>Venue</th>
                            <th>Type</th>
                            <th>Date</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="concert" items="${tour.concerts}">
                            <tr>
                                <td>${concert.venue}</td>
                                <td>${concert.type}</td>
                                <td><fmt:formatDate pattern="dd MMM yyyy" value="${concert.date}"/></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>

            <a href="../bands" class="btn btn-primary mt-3">Back to Band List</a>
        </div>
    </div>
</div>

</body>
</html>
