<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" indent="yes"/>

    <xsl:template match="dataList">
        <dataList>
            <xsl:apply-templates/>
        </dataList>
    </xsl:template>

    <xsl:template match="element">
        <element >
            <xsl:attribute name="Data">
                <xsl:value-of select="Data"/>
            </xsl:attribute>
        </element>
    </xsl:template>

</xsl:stylesheet>