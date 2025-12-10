<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Details - ${user.uid}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <h1 class="mb-4 text-center">User Page</h1>

    <div class="card shadow-sm mb-4">
        <div class="card-body">
            <h5 class="card-title">${user.name}</h5>
            <p><strong>UID:</strong> ${user.uid}</p>
            <p><strong>Description:</strong> ${user.description}</p>
        </div>
    </div>
</div>

</body>
</html>
