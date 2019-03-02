<!DOCTYPE html>
<html lang="fr">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Article</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Navbar</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="/">Home</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="/admin">Admin<span class="sr-only">(current)</span></a>
            </li>
        </ul>
    </div>
</nav>
<main>
    <div class="container">
        <div class="row">
            <div class="px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
                <h1 class="display-4">${article.title}</h1>
                <p class="lead">${article.text}</p>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="row">
            <h4 class="title">Comments</h4>
        </div>
        <div class="row">
            <#list comments as comment>
                <div class="px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
                    <p class="lead">${comment.text}</p>
                </div>
            </#list>
        </div>
        <div class="row">
            <form action="" method="post">
                <div class="form-group">
                    <label for="comment">Add comment</label>
                    <textarea class="form-control" id="comment" name="comment" rows="3"></textarea>
                    <small id="commentHelp" class="form-text text-muted">Please respect each other in the comment section.</small>
                </div>
                <button type="submit" class="btn btn-primary">Publish</button>
            </form>
        </div>
    </div>
</main>
</body>
</html>