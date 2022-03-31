<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
   <xsl:output method="xml"/>
   <xsl:template match="/resultset">
       <countries>
       <xsl:for-each select="row">
           <country>
           <xsl:for-each select="field">
               <xsl:element name="{@name}" >
                   <xsl:value-of select="."/>
               </xsl:element>
           </xsl:for-each>
           </country>
       </xsl:for-each>
       </countries>
   </xsl:template>
</xsl:stylesheet>