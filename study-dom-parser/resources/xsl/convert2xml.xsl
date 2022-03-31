<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/movies">
        <movies>
            <xsl:for-each select="movie">
                <xsl:element name="movie">
                    <xsl:attribute name="imdb">
                        <xsl:value-of select="title/@imdb" />
                    </xsl:attribute>
                    <xsl:element name="title">
                        <xsl:value-of select="title" />
                    </xsl:element>
                    <xsl:copy-of select="year" />
                    <xsl:copy-of select="directors" />
                    <xsl:copy-of select="genres" />
                </xsl:element>
            </xsl:for-each>
        </movies>
    </xsl:template>
</xsl:stylesheet>