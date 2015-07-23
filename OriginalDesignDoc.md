TODO: edit doc to reflect this-
"so then essentially the doc changes are: no hold space, discard gives you 10% dmg reduction per card, and all card powers are now on discard, and we have negative card powers now too"

# Introduction #

This is the original design document used for the game.  Some of the details may have changed since it was originally written.


# Details #

**Basic Concept**

two players have 5 (or 7) cards in their hand at all times, and draw from a single shared deck of playing cards. Each player has a given amount of life points. Players take turns playing poker hands, but are not comparing it to each others hands. A player plays a hand, and that hand does damage to the other player, depending on what hand it is and what cards are used to make that hand. For instance, a full house does more damage than a pair of 8s, and a pair of 8s does more damage than a pair of 2s. After a player plays their cards, they draw cards to make up for the cards they used, until they have the normal hand size, and then the next player takes their turn. The person that puts the enemy to 0 life points first wins. After a deck is used, the cards not held by players are shuffled and put back into the pile.

**Discarding Cards**

If you have a bad hand, or you want to save a couple of cards and toss some other ones to try to get a better hand, you can discard cards. Instead of taking your turn, you may discard any number of cards to draw new ones. This does use your turn, but each discarded card does a minimal amount of damage to the enemy, so it's not a total loss.

**Card abilities**

At random, when a player draws a card, there will be an ability on that card. There are a limited amount of abilities that can appear in a game (generally 5 abilities in a given game). An example of an ability would be swapping two players hands, or viewing the enemy's hand. The way you use this card ability is to play it as part of your hand. So if you had a card with the ability "view enemy's hand" on an 8, and you played it as one of your two cards for a pair of 8s, you would do the damage from that pair, and you would get to see the enemy's hand. Abilities in one play DO NOT stack (ie if you have a pair of 3s and each card has the ability "damage x2" it would not do 4x damage, only 2x).

**The "hold" space**

Say you have a great play, but you don't want to use it just yet. Well you can set that play aside in a special "hold" area. Now during any turn, you may use that play you set aside instead of the one in your hand. You can even use it with a play in your hand, for a combo bonus. It's not all good things for the hold space though. You may only set aside one play at a time, and you can't set aside another play until you use any play held in the hold space first. Holding a play aside also uses a turn. There are a few card abilities related to the hold space too. There is a card ability that forces an enemy's hold space cards to be discarded. ~~There is also a "counter" card ability, where if that card is part of a play you have held in your play space, you may use that play when an enemy plays a hand. If you do, that enemy's play gets discarded instead of getting played, and your hand does half the damage it would have done to the enemy. If an enemy has a play in his hold space with a counter ability though, he can counter your counter (but his first hand was already lost).~~

You can "combo" cards in a hold space with cards in your hand. This is done by first selecting cards for a play from your hand, then, if you have a play in your hold space, you press the hold button, and it will automatically play the selected cards in your hand followed by the cards in your hold space. They are treated as two separate plays. If you have the same ability in both your hand's play and the hold space play for a combo, the abilities only affect their respective hand (ie. if you have two "damage x2" bonuses, one in your hand's play, and one in your hold space play, then each one affects it's own play's damage, they do not both affect the total amount of damage dealt between both plays in a combo).

**Game Modes**

_Standard battle_
Fight either a random or chosen cpu enemy in a single battle to the death.

_Multiplayer (if time/ability permits)_
Fight another player, either random (automatchmaking), or chosen player. Choose number of matches, starting life, and a few other options.

_Tournament/Story mode_
Extremely Simple ridiculous story, like nes style 1-2 screen story (on purpose) giving a reason for fighting a bunch of cpu guys in a row. Single images of enemy you fight before and after battle, also spouts text quotes during battle at specific times. If mode is beaten also provides an ending screen or two.

**Abilities that may appear in a given battle**

For now let's say that up to 5 abilities may appear in a given battle at a time. These will be listed on a screen right before the match. 3 abilities are static, and will always appear regardless of who you fight against. The other two depend on which cpu character you are fighting, or in the case of multiplayer, are randomized.


**Overview of game concepts**

  * two players have a hand of 5 playing cards
  * draw from the same deck
  * play poker plays to do damage, damage scales with quality of play
  * first one to zero life points loses
  * you may discard cards to draw new ones instead of making a play
  * discarded cards do a minimum damage to the enemy
  * cards have abilities such as viewing the enemy's hand
  * these abilities are used by playing that card as part of a poker play
  * you may hold up to 1 play in a "hold" space instead of making a play
  * this hold play may be played in place of your normal play
  * the hold play may also be played in combination with a play from your hand for bonus combo damage
  * there are cards which take advantage of the hold space (forcing discard of hold space cards, countering opponents plays)
  * There is a standard cpu battle mode, a multiplayer mode, and a tournament/story mode
  * up to 5 abilities can appear in a battle, 3 of which always appear, and 2 of which may vary


**Controls**

left=move highlight left
right = move highlight right
center = place card into play area/ confirm selected onscreen hold, discard, or OK button
1-5=move # card into play area
call=play cards, 7 = play cards
onscreen  OK button = play cards

8 = save
down= select "discard" option button
9= discard
up = select "hold" option button
0 = remove all
cancel = remove all

**Card abilities list**

  * double damage - stylized x2 - play does double damage
  * View enemy hand - eyeball logo - views enemy hand, cannot continue until you press ok to stop showing hand
  * destroy hold - red treasure chest icon? - destroy enemy hold space, discarding all cards there

NEW Screen mockup (ver. 3)

![http://docs.google.com/File?id=dz6bc95_27j6cmkxhk&.jpg](http://docs.google.com/File?id=dz6bc95_27j6cmkxhk&.jpg)

Screen mockup (ver. 2)

![http://docs.google.com/File?id=dz6bc95_23g4gxg4gt&.jpg](http://docs.google.com/File?id=dz6bc95_23g4gxg4gt&.jpg)

OLD screen mockup

![http://docs.google.com/File?id=dz6bc95_22c4shw3d2&.jpg](http://docs.google.com/File?id=dz6bc95_22c4shw3d2&.jpg)

_Process Explanation_
1) making a normal play

![http://docs.google.com/File?id=dz6bc95_24c8ghr4dg&.jpg](http://docs.google.com/File?id=dz6bc95_24c8ghr4dg&.jpg)

![http://docs.google.com/File?id=dz6bc95_25xrdhfbfp&.jpg](http://docs.google.com/File?id=dz6bc95_25xrdhfbfp&.jpg)

![http://docs.google.com/File?id=dz6bc95_268kg6d6cj&.jpg](http://docs.google.com/File?id=dz6bc95_268kg6d6cj&.jpg)

_programming notes_
-make a function that returns the possible plays from a given hand.
use this function for ai, as well as a player hint button
-function to check if a given play is a legal play (and possibly what play it makes?)
-function to check the score of a given legal play (damage calculation function) (damage is calculated as: the base damage a play deals + the minor damage each card deals (so that a pair of 8s deals more than a pair of 2s) + any damage modifiers (from card abilities))

### Concepts for fleshing this game out more (if we push development further) ###
Perhaps when building the game we keep these in mind, so they don't become impossible to fit in should we choose to.

Difficulty Settings - ability to choose how hard the enemy cpu is to fight against

Thread enemy ai - to speed up enemy turns if we write complex ai later

On-the-fly play legality notification - as you select and deselect cards, the currently selected cards turn red or green if they constitute a legal play. This way we don't necessarily need a hint button, and it will be more helpful for people not very good at poker to fiddle around and find a play more easily.

Enemy info cards - view the cpu enemy's image, name, and small sentence on them. Can also challenge that enemy in a standard battle from this card.

Expanded story mode - just make it longer, or perhaps different endings depending on difficulty, that sort of thing.

2 players vs. CPU multiplayer mode - 2 players fight one strong cpu (lots of hp probably). players have separate hand, but shared hold space. Problem is playing this with a random person sucks, would need to be able to do a local connect mode (bluetooth?)

"super attacks" - special attack a cpu gets (assigned to a character, but we may repeat to different characters). These are special moves that an enemy may use when he fills up a meter. The meter would be filled when he takes or receives damage, though receiving damage only fills it at half the normal rate. Potentially, players may also gain the use of these abilities through beating an enemy with the ability, as well as options for choosing and toggling the use of supers during multiplayer or standard matches. Examples of super attacks: "Aces wild - aces may take the place of any card for five turns" "Texas hold em - 3 cards appear face down on the field, each turn a card is revealed. If a player makes a play, the cards on the field are counted as part of the play if appropriate (if you play a pair of 2s, and theres a 2 on the field, it now counts as a three of a kind). The 3 cards disappear the turn after all 3 are revealed." "war - you and opponent place one card on the field, the player with the "high" card does a set amount of damage to the losing player"