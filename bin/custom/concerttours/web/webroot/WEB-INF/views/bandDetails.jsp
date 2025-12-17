<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Band Details - ${band.name}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <h1 class="mb-4 text-center">Band Details: ${band.name}</h1>

    <div class="card shadow-sm mb-4">
        <div class="card-body">
            <div class="row">
                <c:if test="${not empty band.bigImageURL}">
                    <div class="col-md-4 text-center mb-3 mb-md-0">
                        <img src="${band.bigImageURL}" alt="${band.name}" class="img-fluid rounded"
                             style="max-height: 400px;">
                    </div>
                </c:if>

                <div class="col-md-8">
                    <h5 class="card-title">${band.name}</h5>
                    <p><strong>Description:</strong> ${band.description}</p>
                    <p><strong>Album Sales:</strong> ${band.albumsSold}</p>

                    <c:if test="${not empty band.genres}">
                        <p><strong>Genres:</strong></p>
                        <div>
                            <c:forEach var="genre" items="${band.genres}">
                                <span class="badge bg-secondary me-1">${genre}</span>
                            </c:forEach>
                        </div>
                    </c:if>

                    <c:if test="${not empty band.tours}">
                        <p class="mt-3"><strong>Tour History:</strong></p>
                        <ul class="list-unstyled">
                            <c:forEach var="tour" items="${band.tours}">
                                <li class="mb-1">
                                    <a href="/concerttours/tours/${tour.id}" class="text-decoration-none">
                                            ${tour.tourName}
                                    </a>
                                    (Number of concerts: ${tour.numberOfConcerts})
                                </li>
                            </c:forEach>
                        </ul>
                    </c:if>

                    <c:if test="${not empty band.albumSet}">
                        <p class="mt-3"><strong>Albums:</strong></p>
                        <ul class="list-unstyled">
                            <c:forEach var="album" items="${band.albumSet}">
                                <div>${album}</div>
                            </c:forEach>
                        </ul>
                    </c:if>

                    <c:if test="${not empty band.socialMediaLinks}">
                        <p class="mt-3"><strong>Social Media:</strong></p>
                        <ul class="list-unstyled">
                            <c:forEach var="entry" items="${band.socialMediaLinks}">
                                <li>
                                    <div>${entry.key} -> ${entry.value}</div>
                                </li>
                            </c:forEach>
                        </ul>
                    </c:if>

                    <a href="../bands" class="btn btn-primary mt-3">Back to Band List</a>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
