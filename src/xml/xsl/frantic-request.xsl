<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:output method="html" indent="yes"/>
  <!-- **********************************************************************
 Render header and footer
     ********************************************************************** -->
  <xsl:template match="/">
   <html>
<head>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>FusionReactor Request LogReport</title>
<style>
{
  margin: 0;
  padding: 0;
 }

 body {
  margin: 20px 0;
  background: #212B35;
 }

 body, th, td, input, textarea {
  font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
  font-size: 12px;
  color: #666666;
  }

 

 form {
 }

 fieldset {
 }

 input, textarea {
  font-weight: bold;
 }

 input.text {
  padding: 2px 5px;
  background: #F8FAEB;
  border-top: 1px solid #4D5719;
  border-left: 1px solid #4D5719;
  border-right: 1px solid #626456;
  border-bottom: 1px solid #626456;
 }

 input.button {
  background: #3C7BCF;
  border-top: 1px solid #3C7BCF;
  border-left: 1px solid #3C7BCF;
  border-right: 1px solid #3C7BCF;
  border-bottom: 1px solid #3C7BCF;
  color: #FFFFFF;
 }

 h1, h2, h3 {
  margin-top: 1.5em;
  color: #626456;
 }

 h1 {
  letter-spacing: -.075em;
  font-size: 3em;
 }

 h2 {
  letter-spacing: -.05em;
  text-transform: uppercase;
  font-size: 1.1em;
  font-weight: bold;
  color: #8D8E85;
 }

 h3 {
  font-size: 1em;
 }

 p, ul, ol {
  margin-top: 1.5em;
  line-height: 1.8em;
  font-size: 1.1em;
 }

 ul, ol {
  margin-left: 3em;
 }

 blockquote {
  margin-left: 3em;
  margin-right: 3em;
 }

 a {
  text-decoration: none;
  color: #3C7BCF;
 }

 a:hover {
  border: none;
 }

 h1 a, h2 a, h3 a {
  border: none;
  text-decoration: none;
  color: #626456;
 }

 h1 a:hover, h2 a:hover, h3 a:hover {
  background: none;
  color: #3C7BCF;
 }

 hr {
  display: none;
 }

 /* Wrapper */

 #wrapper {
 }

 /* Header */

 #header {
  width: 100%;
  height: 15px;
  margin: 0 auto;
 }

 #menu {
  float: left;
 }

 #menu ul {
  margin: 0;
  padding: 0;
  list-style: none;
  line-height: normal;
 }

 #menu li {
  float: left;
 }

 #menu a {
  display: block;
  float: left;
  height: 25px;
  margin-right: 1px;
  padding: 10px 20px 0 20px;
  text-decoration: none;
  font-size: 1.1em;
  font-weight: bold;
  color: #8C8F7D;
 }

 #menu a:hover {
  background:  #F3F3F3;
  color: #2C2E22;
 }

 #menu .current_page_item a {
  background:  #F3F3F3;
  color: #2C2E22;
 }

 #search {
  float: right;
  width: 260px;
  padding-top: 7px;
 }

 #search fieldset {
  border: none;
 }

 #search #s {
  width: 160px;
 }

 #search #x {
  width: 80px;
 }

 /* Logo */

 #logo {
  width: 978px;
  height: 200px;
  margin: 0 auto;
  background: url(images/img12.jpg) no-repeat left top;
  height: 263px;
  border: 20px solid #F3F3F3;
 }

 #logo h1, #logo h2 {
  float: left;
  margin: 0;
  padding: 0;
 }

 #logo h1 {
  padding: 100px 5px 0 20px;
  color: #2C2E22;
 }

 #logo h2 {
  padding: 115px 0 0 0;
  font-style: italic;
 }

 #logo p {
  clear: left;
  margin: 0;
  padding: 0 0 0 20px;
  line-height: normal;
  font-size: 1.2em;
  font-weight: bold;
  color: #C2C5B1;
 }

 #logo a {
  color: #FFFFFF;
 }

 /* Page */

 #page {
  width: 978px;
  margin: 0 auto;
  padding: 20px 0px 0 0px;
  background: #FFFFFF;
  border: 20px solid #F3F3F3;
  border-top: none;
 }

 /* Content */

 #content {
  float: left;
  width: 425px;
  padding: 0px 0px 0px 20px;
 }

 .post {
 }

 .post .title {
  margin: 0;
  font-weight: normal;
 }

 .post h1.title {
  padding: 15px 0  0 15px;
  height: 20px;
  font-size: 2.4em;
 }

 .post .entry {
  padding: 0 10px 30px 15px;
 }

 .post .meta {
  margin: 0;
  padding: 5px 0px 0px 15px;
  background: url(images/img04.gif) repeat-x left bottom;
  text-transform: uppercase;
  font-family: Arial, Helvetica, sans-serif;
  font-size: 10px;
  color: #CFCFCF;
 }

 .post .meta a {
  color: #CFCFCF;
 }

 .post .links {
  margin: 0;
 }

 .post .tags {
  margin: 0;
  text-transform: uppercase;
  font-size: .8em;
  font-weight: bold;
  background: url(images/img04.gif) repeat-x;
 }

 .post .links a, .post .tags a {
  border: none;
 }

 /* Recent Posts */

 #recent-posts {
 }

 #recent-posts h2 {
  margin: 0;
  background: url(images/img13.gif) no-repeat left top;
  padding: 15px 0  0 15px;
  height: 30px;
 }

 #recent-posts h3 {
  margin: 0;
  font-size: 13px;
  padding: 15px 0  0 15px;
  background: url(images/img04.gif) repeat-x left bottom;
 }

 #recent-posts p {
  margin: 0 0 10px 0;
  padding: 15px 0  0 15px;
  line-height: 22px;
  font-size: 13px;
 }

 /* Sidebars */

 .sidebar {
  float: left;
 }

 .sidebar ul {
  margin: 0;
  padding: 0;
  list-style: none;
 }

 .sidebar li {
  margin-bottom: 2em;
 }

 .sidebar li ul {
 }

 .sidebar li li {
  margin: 0;
 }

 .sidebar li h2 {
  margin: 0 0 1em 0;
 }

 #sidebar1 {
  width: 230px;
  padding: 0px 0px 0px 20px;
 }

 #sidebar2 {
  width: 240px;
  padding: 0px 20px 0px 20px;
 }

 #sidebar2 li h2 {
  margin: 0 0 1em 0;
  background: url(images/img11.gif) no-repeat left top;
  padding: 15px 0  0 15px;
  height: 30px;
 }

 #sidebar2 li ul {
  line-height: normal;
  background: url(images/img04.gif) repeat-x;
 }

 #sidebar2 li li {
  padding: 5px;
  background: url(images/img04.gif) repeat-x left bottom;
  font-size: .8em;
 }

 #sidebar2 li a {
  border: none;
  padding-left: 10px;
 }

 #sidebar2 li a:hover {
  border: none;
  padding-left: 10px;
 }


 /* Calendar */

 #calendar {
  margin: 0 auto;
 }

 #calendar caption {
  font-weight: bold;
 }

 #calendar table {
  width: 220px;
  text-align: center;
  border-collapse: collapse;
 }

 #calendar thead th {
  background: #CCCCCC;
  color: #FFFFFF;
 }

 #calendar tbody td {
  background: #EEEEEE;
 }

 #calendar #today {
  background: #B8D03B;
  font-weight: bold;
  color: #FFFFFF;
 }

 #calendar a {
  font-weight: bold;
 }

 #calendar #prev {
  text-align: left;
 }

 #calendar #next {
  text-align: right;
 }

 /* Footer */

 #footer {
  text-align: center;
  font-size: 9px;
}

      </style>
<script>


      function swap(id){
       el = document.getElementById("h_" + id);
      //alert("h_" + id)
       if(el.style.visibility == "visible"){
        el.style.visibility = "hidden";
        el.style.display = "none";
        document.getElementById(id).innerHTML = "+ Expand Stacktrace";
       }
       else {
        el.style.visibility = "visible";
        el.style.display = "block";
        document.getElementById(id).innerHTML = "- Collapse Stacktrace";
       }

      }

       function swapContent(id){
       //alert(id);
       el = document.getElementById("g_" + id);
       if(el.style.visibility == "visible"){
        el.style.visibility = "hidden";
        el.style.display = "none";
        document.getElementById(id).innerHTML = "+ Expand";
       }
       else {
        el.style.visibility = "visible";
        el.style.display = "block";
        document.getElementById(id).innerHTML = "- Collapse";
       }

      }

      </script>
</head>
<body>
	<div class="post">
		<div id="header" style="padding-left:16">
		  <h1 class="title">FusionReactor Request LogReport</h1>
		</div>
	</div>

    <div id="page">
    <table id="calendar" style="border:1px ridge gray" width="75%" cellpadding="0" cellspacing="0">
        <tr style="background-color:#eaeaea">
           <th width="46">Date</th>
              <th width="54">Time</th>
              <th width="54">IP</th>
              <th>URL</th>
            </tr>
            <xsl:apply-templates />
        </table>
        </div>
        <div id="footer"><a href="http://mxunit.org">mxunit.org</a></div>
      </body>
    </html>
  </xsl:template>
  

<xsl:template name="details" match="/frantic/entry">
 <tr>
    <td><xsl:value-of select="date" /></td>
    <td ><xsl:value-of select="time" /></td>
    <td><xsl:value-of select="clientip" /></td>
    <td><xsl:value-of select="requrl" /></td>
  </tr>  
  <tr>
    <td align="right"><em>Message</em></td><td colspan="3"><code><xsl:value-of select="Message" /></code></td>
  </tr>
  
</xsl:template> 
  
  
</xsl:stylesheet>
