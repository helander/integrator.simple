<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
<xsl:text disable-output-escaping="yes">
&lt;</xsl:text>SOAP-ENV:Envelope
xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/"
xmlns:SOAP-ENC="http://schemas.xmlsoap.org/soap/encoding/"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xmlns:xsd="http://www.w3.org/2001/XMLSchema"<xsl:text disable-output-escaping="yes">&gt;</xsl:text>
<xsl:text disable-output-escaping="yes">&lt;</xsl:text>SOAP-ENV:Body<xsl:text disable-output-escaping="yes">&gt;</xsl:text>

<xsl:copy-of select="*" />

<xsl:text disable-output-escaping="yes">&lt;</xsl:text>/SOAP-ENV:Body<xsl:text disable-output-escaping="yes">&gt;</xsl:text>
<xsl:text disable-output-escaping="yes">&lt;</xsl:text>/SOAP-ENV:Envelope<xsl:text disable-output-escaping="yes">&gt;</xsl:text>
</xsl:template>

</xsl:stylesheet>