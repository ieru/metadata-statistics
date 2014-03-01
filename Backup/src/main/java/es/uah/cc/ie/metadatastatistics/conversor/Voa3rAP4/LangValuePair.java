package es.uah.cc.ie.metadatastatistics.conversor.Voa3rAP4;


public class LangValuePair {
  
  private final String lang;
  private final String value;

  public LangValuePair(String lang, String value) {
    this.lang = lang;
    this.value = value;
  }

  public String getLang() {
    return this.lang;
  }

  public String getValue() {
    return this.value;
  }
}