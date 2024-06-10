class PlayingCards {
    String  manufacturer
    String  size
    String  finish
    String  material
    boolean hasDoubleBacker
    boolean hasBlankCard
}


PlayingCards playingCards = new PlayingCards('USPCC', 'Poker', 'Air-Cushion', 'paper', true, true)

println("${playingCards.manufacturer} ${playingCards.size} ${playingCards.finish}")