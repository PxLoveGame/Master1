<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method="html" encoding="UTF-8" />

  <xsl:template match="qcm">
    <html>
      <body>
        <h1> Un questionnaire sur les <xsl:value-of select="./@matière"/> </h1>
        <xsl:apply-templates select="question"/>
      </body>
    </html>
  </xsl:template>

  <xsl:template match="question">
    <div>
      <h2>
        <xsl:apply-templates select="libellé"/>
      </h2>
      <xsl:choose>
        <xsl:when test="count(./choix[@score > 0]) = 1">
          <input type="radio" name="count(./preceding::question)" value="0" checked="checked"></input> Je ne sais pas
          <xsl:apply-templates select="choix" mode="radio"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:apply-templates select="choix" mode="checkbox"/>
        </xsl:otherwise>
      </xsl:choose>
    </div>
  </xsl:template>

  <xsl:template match="libellé"> <!-- On peut s'en passer et utiliser le template par defaut qui retournera le texte ?-->
    <xsl:value-of select="."/>
  </xsl:template>

  <xsl:template match="choix" mode="checkbox">
    <div>
      <input type="checkbox" name="count(./preceding::question)" value="{./@score}"></input> <xsl:value-of select="."/>
    </div>
  </xsl:template>

  <xsl:template match="choix" mode="radio">
    <div>
      <input type="radio" name="count(./preceding::question)" value="{./@score}"></input> <xsl:value-of select="."/>
    </div>
  </xsl:template>

</xsl:stylesheet>
