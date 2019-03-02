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
            <li class="nav-item active">
                <a class="nav-link" href="/logout">Logout</a>
            </li>
        </ul>
    </div>
</nav>
<main>
    <div class="container">
        <div class="row">
            <ul class="list-group" style="width: 100%">
                <li>
                    <a href="admin/article/create">
                        <button class="btn btn-primary">New article</button>
                    </a>
                </li>
                <#list articles as article>
                    <li class="list-group-item">
                        <a href="/admin/article/${article.id}">
                            ${article.title}
                        </a>
                        <form action="/article/delete/${article.id}" method="post">
                            <button type="submit" class="btn btn-danger">Delete</button>
                        </form>
                    </li>
                </#list>
            </ul>
        </div>
    </div>
</main>
</body>
</html>