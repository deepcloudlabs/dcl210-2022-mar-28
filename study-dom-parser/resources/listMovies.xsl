<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>
    
    <xsl:template match="/">
        <html>
            <head>
                <title>digiWood - Movie List</title>
				<script type="text/javascript" src="jquery-1.4.4.min.js"></script>          
				<script type="text/javascript">                                         
					$(document).ready(function() {
				        $('#movies tr td img').mouseover(function(){
						   $(this).attr("width",'144');
					    });
				        $('#movies tr td img').mouseout(function(){
						   $(this).attr("width",'128');
					    });
				        $('#movies tr').mouseover(function(){
						    $(this).addClass('zebraHover');							
						});
				        $('#movies tr').mouseout(function(){
						    $(this).removeClass('zebraHover');
						});				    });
				</script>    
				<style rel="stylesheet">
                    tr.zebraHover { background-color: #FFFACD; }
					largeImage { width: 512px; height=512px; }
				</style>
            </head>
            <body>
			<div id="movies">				
                <table border="0" style="font-family: Tahoma, Verdana, Arial;font-size:10pt;">
                    <tr>
                        <th style="background: url(bg.jpg)" align="left">No</th>
                        <th style="background: url(bg.jpg)" align="left">Title</th>
                        <th style="background: url(bg.jpg)" align="left">Directors</th>
                        <th style="background: url(bg.jpg)" align="left">Genres</th>
                        <th style="background: url(bg.jpg)" align="left">Year</th>
                    </tr>
                    <xsl:for-each select="/movies/movie[ year = 1973]">
                        <xsl:variable name="i" select="position() mod 2"/>
						<xsl:variable name="backgroundcolor">
							<xsl:choose>
								<xsl:when test="$i = 0"><xsl:text>DFDFDF</xsl:text></xsl:when>
								<xsl:otherwise><xsl:text>EFEFEF</xsl:text></xsl:otherwise>
							</xsl:choose>	
						</xsl:variable>						
                        <tr bgcolor="#{$backgroundcolor}">
                            <td align="center">
                                <xsl:number value="position()" format="1" />
                            </td>
                            <td align="left" valign="center">
                                <a href="http://www.imdb.com/title/{title/@imdb}">
                                    <xsl:value-of select="title"/>
                                </a>
                            </td>
                            <td>
                                <xsl:for-each select="directors/director[position() &lt; last()]">
                                    <a href="http://www.imdb.com/name/{@imdb}">
                                        <xsl:value-of select="."/>
                                    </a>,
                                </xsl:for-each>
                                <a href="http://www.imdb.com/name/{directors/director[last()]/@imdb}">
                                    <xsl:value-of select="directors/director[last()]"/>
                                </a>
                            </td>
                            <td>
                                <xsl:for-each select="genres/genre[position() &lt; last()]">
                                        <xsl:value-of select="."/>,
                                </xsl:for-each>
                                    <xsl:value-of select="genres/genre[last()]"/>                            							    
                            </td>
                            <td align="center" valign="center">
                                <xsl:value-of select="year"/>
                            </td>
                        </tr>
                        
                    </xsl:for-each>
                </table>
            </div>                       
		   </body>
        </html>
    </xsl:template>
</xsl:stylesheet>