public class PlayWithPlayingCards {
  public static void main(String[] args) {
    //String manufacturer, String size, String finish, String material, boolean hasDoubleBacker, boolean hasBlankCard) {
    PlayingCards playingCards = new PlayingCards('USPCC', 'Poker', 'Air-Cushion', 'paper', true, true);

    System.out.println(playingCards.getManufacturer() + " " + playingCards.getSize() + ":" + playingCards.getFinish());
  }
}
