<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Producer Details - ${producer.name}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <h1 class="mb-4 text-center">Producer Details:</h1>

    <div class="card shadow-sm mb-4">
        <div class="card-body">
            <div class="col-md-8">
                <p><strong>Producer name:</strong> ${producer.name}</p>
                <p><strong>Country:</strong> ${producer.country}</p>
                <p><strong>Experience years:</strong> ${producer.experienceYears}</p>

                <c:if test="${not empty producer.bands}">
                    <p><strong>Bands:</strong></p>
                    <div>
                        <c:forEach var="band" items="${producer.bands}">
                            <a href="/concerttours/bands/${band.id}">${band.name}</a><br/>
                        </c:forEach>
                    </div>
                </c:if>

                <a href="../producers" class="btn btn-primary mt-3">Back to Producers List</a>
            </div>
        </div>
    </div>
</div>

</body>
</html>
