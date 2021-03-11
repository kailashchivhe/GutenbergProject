package com.kai.gutenbergproject.model

import com.google.gson.annotations.SerializedName

data class Formats(
    @SerializedName("application/epub+zip") val ePubZipFormat: String,
    @SerializedName("application/pdf") val pdfFormat: String,
    @SerializedName("application/rdf+xml") val rdfXmlFormat: String,
    @SerializedName("application/x-mobipocket-ebook") val xMobiPocketEbookFormat: String,
    @SerializedName("application/zip") val zipFormat: String,
    @SerializedName("image/jpeg") val jpegFormat: String,
    @SerializedName("text/html; charset=iso-8859-1") val textHtmlCharsetIso88591Format: String,
    @SerializedName("text/html; charset=us-ascii") val textHtmlCharsetUsAsciiFormat: String,
    @SerializedName("text/html; charset=utf-8") val textHtmlCharsetUtf8Format: String,
    @SerializedName("text/plain") val textPlainFormat: String,
    @SerializedName("text/plain; charset=iso-8859-1") val textPlainCharsetIso88591Format: String,
    @SerializedName("text/plain; charset=us-ascii") val textPlainCharsetUsAsciiFormat: String,
    @SerializedName("text/plain; charset=utf-8") val textPlainCharsetUtf8Format: String,
    @SerializedName("text/rtf") val textRtfFormat: String
)