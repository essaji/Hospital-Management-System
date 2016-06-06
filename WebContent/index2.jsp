
<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/jquery-ui.min.css" rel="stylesheet" type="text/css" />
    <link href="css/bootstrap-dialog.css" rel="stylesheet" />
    <link href="css/scrolling-nav.css" rel="stylesheet" />
    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <script src="js/jquery-1.11.3.min.js"></script>
    <script src="js/jquery-ui.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/bootstrap-dialog.js"></script>
    <title>Hospital Management System</title>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<style>
	.carousel-inner>.item>a>img, .carousel-inner>.item>img{
		width:100%;
		height:600px;
	}
	.carousel-inner{
		top:90px;
	}
	.navbar-nav{
		float:right;
	}
	#hmsNav{
		width: 100%;
	}
	
</style>
<script>
	/* $(document).ready(function(){
		$(".modal").on('shown.bs.modal', function() {
			$("body.modal-open").removeAttr("style");
			$(this).css("padding-left","0px");
		 });
		$(".modal").on('hidden.bs.modal', function() {
			$("body.modal-open").removeAttr("style");
			$(this).css("padding-left","0px");
		 });
	}); */
</script>
</head>

<!-- The #page-top ID is part of the scrolling feature - the data-spy and data-target are part of the built-in Bootstrap scrollspy function -->

<body id="page-top" data-spy="scroll" data-target=".navbar-fixed-top">

    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
        <div class="container" id="hmsNav">
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand page-scroll" href="#page-top">Hospital Management System</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav navbar-nav">
                    <!-- Hidden li included to remove active class from about link when scrolled up past about section -->
                    <li class="hidden">
                        <a class="page-scroll" href="#page-top"></a>
                    </li>
                    <li>
                        <a class="page-scroll" href="#about">About</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="#services">Services</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="#contact">Contact</a>
                    </li>
                    <li><a  href="#" data-toggle="modal" data-target="#login-modal">Employee Login</a></li>
					<li><a  href="#" data-toggle="modal" data-target="#signup-modal">Patient Registration</a></li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>


        <div id="myCarousel" class="carousel slide" data-ride="carousel">
	<ol class="carousel-indicators">
		<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
		<li data-target="#myCarousel" data-slide-to="1"></li>
		<li data-target="#myCarousel" data-slide-to="2"></li>
		<li data-target="#myCarousel" data-slide-to="3"></li>
		<li data-target="#myCarousel" data-slide-to="4"></li>
	</ol>

	<div class="carousel-inner" role="listbox">
		<div class="item active">
			<img src="img/slide1.jpg" />
		</div>
		
		<div class="item">
			<img src="img/slide2.jpg" />
		</div>
		
		<div class="item">
			<img src="img/slide3.jpg" />
		</div>
		<div class="item">
			<img src="img/slide4.jpg" />
		</div>
		<div class="item">
			<img src="img/slide5.jpg" />
		</div>
	</div>
</div>

    <!-- About Section -->
    <section id="about" class="about-section">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h1>About Section</h1>
                </div>
            </div>
        </div>
    </section>

    <!-- Services Section -->
    <section id="services" class="services-section">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h1>Services Section</h1>
                </div>
            </div>
        </div>
    </section>

    <!-- Contact Section -->
    <section id="contact" class="contact-section">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h1>Contact Section</h1>
                </div>
            </div>
        </div>
    </section>

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Scrolling Nav JavaScript -->
    <script src="js/jquery.easing.min.js"></script>
    <script src="js/scrolling-nav.js"></script>
    
    
    <div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
  	  <div class="modal-dialog">
		<div class="loginmodal-container">
			<h1>Login to Your Account</h1><br>
		  <form method="POST" action="login.jsp">
			<input type="text" name="user" placeholder="Username" required>
			<input type="password" name="pass" placeholder="Password" required>
			<input type="submit" name="login" class="login loginmodal-submit" value="Login">
		  </form>
		</div>
	</div>
</div>

<div class="modal fade" id="signup-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
  	  <div class="modal-dialog">
		<div class="signupmodal-container">
			<h1>Patient Registration</h1><br>
		  <form id="signupform" method="POST" action="EditPatient?action=add">
			<input type="text" name="fullname" placeholder="Full Name" required>
			<label>Select Category</label>
			<select name="category">
				<option value="ENT">ENT</option>
				<option value="Gastrologist">Gastrologist</option>
				<option value="Psychiatrist">Psychiatrist</option>
			</select>
			<input type="text" name="dob" placeholder="Date of Birth" readonly class="dob" />
			<div class="radio">
				<label><input type="radio" name="gender" value="male" required/> Male</label>
				<label><input type="radio" name="gender" value="female"/> Female</label>
			</div>
			<input type="submit" name="signup" class="signup signupmodal-submit" value="Register">
		  </form>
		</div>
	</div>
</div>
<script src="js/script.js"></script>
</body>

</html>
