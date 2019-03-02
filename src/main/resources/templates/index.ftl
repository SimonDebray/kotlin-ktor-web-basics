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
                <a class="nav-link" href="/">Home <span class="sr-only">(current)</span></a>
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
            <#list articles as article>
                <div class="col-sm-6">
                    <article>
                        <div class="card" style="width: 18rem;">
                            <img class="card-img-top" src="http://wac.2f9ad.chicdn.net/802F9AD/u/joyent.wme/public/wme/assets/6128a9b2-7ada-11e6-96e0-8905cd656caf.jpg?v=43" alt="Card image cap">
                            <div class="card-body">
                                <h5 class="md-2  my-0 font-weight-normal">
                                    ${article.title}
                                </h5>
                                <a href="article/${article.id}" class="btn btn-primary">Read more</a>
                            </div>
                        </div>
                    </article>
                </div>
            </#list>
        </div>
    </div>
</main>
</body>
</html>