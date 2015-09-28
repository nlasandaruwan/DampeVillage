<html>
<head>
<title>Photo Gallery</title>

<link rel="stylesheet" type="text/css" href="css/gallery/gallery.css" />

<script type="text/javascript" src="js/gallery/jquery-1.3.2.js"></script>
<script src="js/gallery/effects.core.js"></script>
<script src="js/gallery/effects.slide.js"></script>
<script type="text/javascript" src="js/gallery/supersized.2.0.js"></script>
	
<script type="text/javascript">  
		$(function(){
			$.fn.supersized.options = {  
				startwidth: 600,  
				startheight: 350,
				vertical_center: 1,
				slideshow: 1,
				navigation: 1,
				transition: 3, //0-None, 1-Fade, 2-slide top, 3-slide right, 4-slide bottom, 5-slide left
				pause_hover: 1,
				slide_counter: 1,
				slide_captions: 1,
				slide_interval: 4000  
			};
	        $('#supersize').supersized(); 
	    });
	</script>
	
</head>

<body>

<!--Loading display while images load-->
<div id="loading">&nbsp;</div>

<!--Slides-->
<div id="supersize">
	<img src="images/fw/1.jpg" class="active" />
                <a href=""><img src="images/gallery/2.jpg" title="Bird On A Branch"/></a>
                <a href=""><img src="images/gallery/3.jpg" title="Bird On A Branch"/></a>
                <a href=""><img src="images/gallery/4.jpg" title="Bird On A Branch"/></a>
                <a href=""><img src="images/gallery/5.jpg" title="Bird On A Branch"/></a>
                <a href=""><img src="images/gallery/6.jpg" title="Bird On A Branch"/></a>
                <a href=""><img src="images/gallery/7.jpg" title="Bird On A Branch"/></a>
                <a href=""><img src="images/gallery/8.jpg" title="Bird On A Branch"/></a>
                <a href=""><img src="images/gallery/9.jpg" title="Bird On A Branch"/></a>
                <a href=""><img src="images/gallery/10.jpg" title="Bird On A Branch"/></a>
                <a href=""><img src="images/gallery/11.jpg" title="Bird On A Branch"/></a>
                <a href=""><img src="images/gallery/12.jpg" title="Bird On A Branch"/></a>
                <a href=""><img src="images/gallery/13.jpg" title="Bird On A Branch"/></a>
</div>

<!--Content area that hovers on top-->
<div id="content">
	<div id="contentframe">
	
		<div id="slidecounter"><!--Slide counter-->
			<span class="slidenumber"></span>/<span class="totalslides"></span>
		</div>
		<div id="slidecaption"><!--Slide captions displayed here--></div>
		<!--Logo-->
	
		<!--Navigation-->
		<div id="navigation">
			<a href="#" id="prevslide"><img src="images/Gallery_images/back_dull.gif"/></a>
			<a href="#" id="pauseplay"><img src="images/Gallery_images/pause_dull.gif"/></a>
			<a href="#" id="nextslide"><img src="images/Gallery_images/forward_dull.gif"/></a>
		</div>
		
	</div>
</div>

</body>
</html>
