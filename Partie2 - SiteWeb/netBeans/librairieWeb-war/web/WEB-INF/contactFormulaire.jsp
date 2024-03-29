<!doctype html>
<html lang="fr">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="https://fonts.googleapis.com/css?family=Roboto:400,700,900&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="Ressources/CSS/bootstrap.min.css">
        <link rel="stylesheet" href="Ressources/CSS/style.css">
        <title>Contactez nous !</title>
    </head>
    <body>
        <div class="content my-auto">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-md-10">
                        <div class="row justify-content-center">
                            <div class="col-md-6">
                                <h3 class="heading mb-4">Dites nous tout !</h3>
                                <p><img src="Ressources/Images/undraw_Reading_re_29f8.svg" alt="Image" class="img-fluid"></p>
                            </div>
                            <div class="col-md-6">
                                <form class="mb-5" method="post" id="contactForm" name="contactForm">
                                    <div class="row">
                                        <div class="col-md-12 form-group">
                                            <input type="text" class="form-control contContact my-3" name="name" id="name" placeholder="Votre nom">
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12 form-group">
                                            <input type="text" class="form-control contContact my-3" name="email" id="email" placeholder="Email">
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12 form-group">
                                            <input type="text" class="form-control contContact my-3" name="subject" id="subject" placeholder="Objet">
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12 form-group">
                                            <textarea class="form-control contContact my-3" name="message" id="message" cols="30" rows="7" placeholder="�crivez votre message" style="height: 167px"></textarea>
                                        </div>
                                    </div>  
                                    <div class="row">
                                        <div class="col-12">
                                            <button type="submit" class="btn btn-custom rounded-0 py-2 px-4">Envoyer</button>
                                            <span class="submitting"></span>
                                        </div>
                                    </div>
                                </form>

                                <div id="form-message-warning" class=" mt-4"></div> 
                                <div id="form-message-success">
                                    Your message was sent, thank you!
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.validate.min.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>
