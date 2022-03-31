<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="1.0">
    <xsl:output method="xml" />
    <xsl:template match="/movies">
        <movies>
            <xsl:for-each select="movie">
                <movie>
                    <title imdb="{@imdb}">
                        <xsl:value-of select="title" />
                    </title>
                    <year>
                        <xsl:value-of select="year" />
                    </year>
                    <genres>
                        <xsl:for-each select="genres/genre">
                            <genre>
                                <xsl:value-of select="." />
                            </genre>
                        </xsl:for-each>
                    </genres>
                    <directors>
                        <xsl:for-each select="directors/director">
                            <director imdb="{@imdb}">
                                <xsl:value-of select="." />
                            </director>
                        </xsl:for-each>
                    </directors>
                </movie>
            </xsl:for-each>
        </movies>
    </xsl:template>
</xsl:stylesheet>