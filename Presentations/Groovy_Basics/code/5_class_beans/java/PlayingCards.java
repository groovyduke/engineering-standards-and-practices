public class PlayingCards {
  private String manufacturer;
  private String size;
  private String  finish;
  private String  material;
  private boolean hasDoubleBacker;
  private boolean hasBlankCard;

  public PlayingCards(String manufacturer, String size, String finish, String material, boolean hasDoubleBacker, boolean hasBlankCard) {
    this.manufacturer = manufacturer;
    this.size = size;
    this.finish = finish;
    this.material = material;
    this.hasDoubleBacker = hasDoubleBacker;
    this.hasBlankCard = hasBlankCard;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
  }

  public String getFinish() {
    return finish;
  }

  public void setFinish(String finish) {
    this.finish = finish;
  }

  public String getMaterial() {
    return material;
  }

  public void setMaterial(String material) {
    this.material = material;
  }

  public boolean isHasDoubleBacker() {
    return hasDoubleBacker;
  }

  public void setHasDoubleBacker(boolean hasDoubleBacker) {
    this.hasDoubleBacker = hasDoubleBacker;
  }

  public boolean isHasBlankCard() {
    return hasBlankCard;
  }

  public void setHasBlankCard(boolean hasBlankCard) {
    this.hasBlankCard = hasBlankCard;
  }
}