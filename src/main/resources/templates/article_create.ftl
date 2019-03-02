<#-- @ftlvariable name="" type="fr.iim.iwm.a5.kotlin.IndexArticle" -->
<!DOCTYPE html>
<html lang="fr">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Articles list</title>
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
            <form action="" method="post">
                <div class="form-group">
                    <label for="title">Title</label>
                    <input id="title" type="text" name="title">
                    <label for="textContent">Content</label>
                    <textarea class="form-control" id="textContent" name="textContent" rows="3"></textarea>
                </div>
                <button type="submit" class="btn btn-primary">Publish</button>
            </form>
        </div>
    </div>
</main>
</body>
</html>