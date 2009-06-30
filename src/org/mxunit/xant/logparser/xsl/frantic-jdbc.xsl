<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:output method="html" indent="yes"/>
  <!-- **********************************************************************
 Render header and footer
     ********************************************************************** -->
  <xsl:template match="/">
    <html>
      <head>
        <title>ColdFusion LogReport</title>
        <style>
      body  {
        font-family: verdana, arial, helvetica, sans-serif;
        background-color: #FFFFFF;
        font-size: 12px;
        margin-top: 10px;
        margin-left: 10px;
      }

      table {
        font-size: 11px;
        font-family: Verdana, arial, helvetica, sans-serif;
        width: 90%;
      }

      th {
        padding: 6px;
        font-size: 12px;
        background-color: #cccccc;
      }

      td {
        padding: 6px;
        background-color: #eeeeee;
        vertical-align : top;
      }

      code {
        color: #000099 ;
      }
    #modelink{font-size: 10pt; font-family: Verdana; position:absolute; padding: 10px;}
      </style>
        <script><![CDATA[
      
      
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
      
      ]]></script>
      </head>
      <body style="font-family: arial, helvetica, sans-serif;">
        <H2 align="center">ColdFusion Application LogReport</H2>
        <table style="border:1px ridge gray">
          <tr>
              <th width="46">Date</th>
              <th width="54">Time</th>
              <th width="54">Application</th>
              <th>Severity</th>
            </tr>
            <xsl:apply-templates />
        </table>
      </body>
    </html>
  </xsl:template>
  

<xsl:template name="details" match="/frantic/entry">
 <tr>
    <td width="46"><xsl:value-of select="rownumber" /></td>
    <td width="46"><xsl:value-of select="Date" /></td>
    <td width="54"><xsl:value-of select="Time" /></td>
    <td width="54"><xsl:value-of select="Application" /></td>
    <td><xsl:value-of select="Severity" /></td>
  </tr>  
  <tr>
    <td align="right"><em>Message</em></td><td colspan="3"><code><xsl:value-of select="Message" /></code></td>
  </tr>
  
</xsl:template> 
  
  
</xsl:stylesheet>
