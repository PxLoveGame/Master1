<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method="html" encoding="UTF-8" />

  <xsl:template match="/">
    <html>
      <body>
          <xsl:apply-templates select="//*"/> <!-- Pour tous les éléments ?-->
      </body>
    </html>
  </xsl:template>

  <xsl:template match="*"> <!-- Pour chacun des éléments ?-->
    <div>
      <div>
        <xsl:value-of select="name()"/>
      </div>
        <xsl:apply-templates select="@*"/>
    </div>
  </xsl:template>

  <xsl:template match="@*"> <!-- Pour les attributs de l'élément courant ?-->
    <xsl:value-of select="name()"/>
    :
    <xsl:value-of select="."/>
  </xsl:template>

</xsl:stylesheet>
