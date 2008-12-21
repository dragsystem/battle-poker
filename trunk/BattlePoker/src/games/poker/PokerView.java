package games.poker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Parcel;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

public class PokerView extends View {
	
	public static final boolean DEBUG = true;
	
	public static final int TITLE = 0;
	public static final int INTERIM = 1;
	public static final int PLAYER_SELECT = 2;
	public static final int WAITING_FOR_OPPONENT = 3;
	public static final int END_GAME = 4;
	
	public static final int NUM_MODIFIERS = 0;
	
	public static final int HIGH_CARD_VALUE = 0;
	public static final int PAIR_VALUE = 1;
	public static final int TW0_PAIR_VALUE = 20;
	public static final int THREE_KIND_VALUE = 46;
	public static final int STRAIGHT_VALUE = 254;
	public static final int FLUSH_VALUE = 507;
	public static final int FULL_HOUSE_VALUE = 693;
	public static final int FOUR_KIND_VALUE = 4166;
	public static final int STRAIGHT_FLUSH_VALUE = 71941;
	public static final int ROYAL_FLUSH_VALUE = 649350;
	
	private class Card implements Comparable<Card>{
		
		public short suit;
		public short num;
		public short modifier;
		public String desc;
		public Card(){
			suit = -1;
			num = -1;
			modifier = -1;
			desc = null;
		}
		
		/*public Card(int i){
			short s = -1, n = -1, m = -1;
			if(i != -1){
				s = (short)(i%10);
				if(s >= 4) s = -1;
				if(s != -1)
				{
					n = (short)((i%1000)/10);
					if(n >= 13) n = -1;
					if(n != -1)
					{
						m = (short)(i/1000);
						if( m >= NUM_MODIFIERS) m = -1;
					}
				}
			}
			suit = s;
			num = n;
			modifier = m;
			StringBuilder sb = new StringBuilder();
			Resources res = getContext().getResources();
			switch(n){
			case 0:
				sb.append(res.getString(R.string.ace));
				break;
			case 1:
				sb.append(res.getString(R.string.two));
				break;
			case 2:
				sb.append(res.getString(R.string.three));
				break;
			case 3:
				sb.append(res.getString(R.string.four));
				break;
			case 4:
				sb.append(res.getString(R.string.five));
				break;
			case 5:
				sb.append(res.getString(R.string.six));
				break;
			case 6:
				sb.append(res.getString(R.string.seven));
				break;
			case 7:
				sb.append(res.getString(R.string.eight));
				break;
			case 8:
				sb.append(res.getString(R.string.nine));
				break;
			case 9:
				sb.append(res.getString(R.string.jack));
				break;
			case 10:
				sb.append(res.getString(R.string.ten));
				break;
			case 11:
				sb.append(res.getString(R.string.queen));
				break;
			case 12:
				sb.append(res.getString(R.string.king));
				break;
			default:
				Log.e("CardDes","Unknown Number");
				break;
			}
			sb.append(" of ");
			switch(s){
			case 0: sb.append(res.getString(R.string.diamond));
				break;
			case 1: sb.append(res.getString(R.string.club));
				break;
			case 2: sb.append(res.getString(R.string.heart));
				break;
			case 3: sb.append(res.getString(R.string.spade));
				break;
			default:
				Log.e("CardDes","Unknown Suit");
				break;
			}
			desc = sb.toString();
		}
		*/
		
		public Card(short s, short n, short m){
			suit = s;
			num = n;
			modifier = m;
			StringBuilder sb = new StringBuilder();
			Resources res = getContext().getResources();
			switch(n){
			case 0:
				sb.append(res.getString(R.string.ace));
				break;
			case 1:
				sb.append(res.getString(R.string.two));
				break;
			case 2:
				sb.append(res.getString(R.string.three));
				break;
			case 3:
				sb.append(res.getString(R.string.four));
				break;
			case 4:
				sb.append(res.getString(R.string.five));
				break;
			case 5:
				sb.append(res.getString(R.string.six));
				break;
			case 6:
				sb.append(res.getString(R.string.seven));
				break;
			case 7:
				sb.append(res.getString(R.string.eight));
				break;
			case 8:
				sb.append(res.getString(R.string.nine));
				break;
			case 9:
				sb.append(res.getString(R.string.jack));
				break;
			case 10:
				sb.append(res.getString(R.string.ten));
				break;
			case 11:
				sb.append(res.getString(R.string.queen));
				break;
			case 12:
				sb.append(res.getString(R.string.king));
				break;
			default:
				Log.e("CardDes","Unknown Number");
				break;
			}
			sb.append(" of ");
			switch(s){
			case 0: sb.append(res.getString(R.string.diamond));
				break;
			case 1: sb.append(res.getString(R.string.club));
				break;
			case 2: sb.append(res.getString(R.string.heart));
				break;
			case 3: sb.append(res.getString(R.string.spade));
				break;
			default:
				Log.e("CardDes","Unknown Suit");
				break;
			}
			desc = sb.toString();
		}

		public int compareTo(Card arg0) {
			//Ignores suit ordering
			return (this.num - arg0.num);
		}
		
		/*public int encodeCard(){ //returns an integer version of the card, for networking: MMMNNS
			return this.modifier*1000 + this.num * 10+ this.suit;
		}*/
		
		public Parcel encodeCard(){
			Parcel p = Parcel.obtain();
			p.writeInt(suit);
			p.writeInt(num);
			p.writeInt(modifier);
			p.writeString(desc);
			return p;
		}
		
		public void decodeCard(Parcel p){
			suit = (short)p.readInt();
			num = (short)p.readInt();
			modifier = (short)p.readInt();
			desc = p.readString();
		}
	}
	private class Deck{
		final boolean cards[][] = new boolean[4][13];
		public Deck(){
			shuffle();
		}
		public void shuffle(){
			Arrays.fill(cards[0], false);
			Arrays.fill(cards[1], false);
			Arrays.fill(cards[2], false);
			Arrays.fill(cards[3], false);
			for(int i = 0; i< 5; i++){
				Card c;
				c = playerHand[i];
				if(c.suit != -1)
					cards[c.suit][c.num] = true;
				c = playerHold[i];
				if(c.suit != -1)
					cards[c.suit][c.num] = true;
				c = oppHand[i];
				if(c.suit != -1)
					cards[c.suit][c.num] = true;
				c = oppHold[i];
				if(c.suit != -1)
					cards[c.suit][c.num] = true;
			}
		}
		
		public int cardsLeft(){
			int count = 0;
				for(int j = 0; j<13; j++){
					if(!cards[0][j])
						count++;
					if(!cards[1][j])
						count++;
					if(!cards[2][j])
						count++;
					if(!cards[3][j])
						count++;
				}
			return count;
		}
		
		public Card draw(){
			Card c = null;
			short s = -1;
			short n = -1;
			short m = -1;
			if(cardsLeft() == 0)
				shuffle();
			
			do {
				s = (short)Math.floor(Math.random() * 4);
				n = (short)Math.floor(Math.random() * 13);
				//m = (short)Math.floor(Math.random() * (NUM_MODIFIERS+1));
			}while(s == 4 || n == 13 || cards[s][n]);
			c = new Card(s,n,m);
			cards[s][n] = true;
			return c;
		}
		
		/*For network function -- to update deck when a network player draws*/
		public Parcel encodeDeck(){
			Parcel p = Parcel.obtain();
			for(int i = 0; i < 4; i++)
				p.writeBooleanArray(cards[i]);
			return p;
		}
		
		public void decodeDeck(Parcel p){
			for(int i = 0; i<4; i++){
				p.readBooleanArray(cards[i]);
			}
		}
	}
	
	protected interface Modifier{
		public void doEffect(boolean opponent);
		public boolean doCheck(boolean opponent);
	}
	
	protected Modifier[] MODS ={
		new Modifier(){//double damage
			public void doEffect(boolean opponent) {
				currentDamage *=2;
			}
			public boolean doCheck(boolean opponent){//always works
				return true;
			}
		},
		
	};
	
	/*Network functions*/
	public void updateStateRemote(){
		
	}
	
	public void sendStateRemote(){
		Bundle map = new Bundle();
		/*List of things to send
		 * playerHand
		 * playerHold
		 * oppHand
		 * oppHold
		 * playerLife
		 * opponentLife
		 * mDeck
		 * */
	}
	
	public final Card[] playerHand = new Card[5];
	public final Card[] playerHold = new Card[5];
	public final Card[] oppHand = new Card[5];
	public final Card[] oppHold = new Card[5];
	private Deck mDeck;
	
	public static int playerLife = 0;
	public static int opponentLife = 0;
	
	public final static int START_LIFE = 1000;
	
	
	private TextView mStatusText;
	
	//public short selected = 0;
	public boolean[] selected;
	public short cursor = 0;
	
	public int handIsLegal;
	public int currentDamage;
	
	public int mode = INTERIM;
	public boolean opponentIsRobot = true;
	
	public PokerView(Context context, AttributeSet attrs) {
        super(context, attrs);
		setFocusable(true);
		Arrays.fill(playerHand, new Card());
		Arrays.fill(playerHold, new Card());
		Arrays.fill(oppHand, new Card());
		Arrays.fill(oppHold, new Card());
		mDeck = new Deck();
		
		selected = new boolean[5];
		Arrays.fill(selected, false);
		cursor = 0;
		handIsLegal = -1;
		opponentIsRobot = true;
	}
	
	public void initGame(){
		playerLife = START_LIFE;
		opponentLife = START_LIFE;
		if(!opponentIsRobot)
		{
			//decide who draws first
		}
		for(int i = 0; i<5; i++){
			playerHand[i] = mDeck.draw();
			if(opponentIsRobot)
				oppHand[i] = mDeck.draw();
		}
		if(!opponentIsRobot)
		{
			//wait for opponent initialize.
			//retrieve
		}
		
		setMode(PLAYER_SELECT);
	}
	
	public void setTextView(TextView view){
		mStatusText = view;
	}
	
	public void setMode(int mode){
		setMode(mode, null);
	}
	
	public void setMode(int mode, CharSequence message){
		this.mode = mode;
		invalidate();
		
		CharSequence str = "";
		if(this.mode == TITLE){
			if(message!= null){
				str = message + "\nPress Center to Continue";
			}else{
				str = "Press Center to Continue";
			}
			
		
			mStatusText.setText(str);
		}
		else if (this.mode == PLAYER_SELECT){
			mStatusText.setText(getStatus());
		}
	}
	
	public CharSequence describeHand(Card[] hand){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i<5; i++)
		{
			Card c = hand[i];
			if(c.num != -1){
				sb.append(c.desc);
				if(i < 4)
					sb.append(", ");
			}
		}
		return sb.toString();
	}
	
	/*public CharSequence describeSelected(){
		StringBuilder sb = new StringBuilder();
		if(selected == 0)
			sb.append("None");
		else{
			int temps = selected;
			int count = 0;
			while(temps > 0){
				if((temps % 2) > 0)
					sb.append("Card "+(count+1)+", ");
				temps /= 2;
				count++;
			}
		}
		
		return sb.toString();
	}*/
	public CharSequence describeSelected(){
		StringBuilder sb = new StringBuilder();
		boolean found = false;
		for(int i = 0; i < 5; i++){
			if(selected[i]){
				sb.append("Card "+(i+1)+", ");
				found = true;
			}
		}
		if(!found)
			sb.append("None");
		
		return sb.toString();
	}
	
	public CharSequence cursorPos(){
		Resources res = getContext().getResources();
		switch(cursor){
		case 0:
		case 1:
		case 2:
		case 3:
		case 4:
			return (res.getString(R.string.card) + (cursor+1));
		case 5:
			return res.getString(R.string.hold);
		case 6:
			return res.getString(R.string.play);
		case 7:
			return res.getString(R.string.drop);
		default:
			Log.e("cursorPos","Position Unknown");
			return res.getString(R.string.unknown);
		}
	}
	
	public CharSequence getStatus(){
		StringBuilder sb = new StringBuilder();
		sb.append("Player Hand: ");
		sb.append(describeHand(playerHand));
		sb.append("\n");
		sb.append("Opponent Hand: ");
		sb.append(describeHand(oppHand));
		sb.append("\n");
		sb.append("Player Hold: ");
		sb.append(describeHand(playerHold));
		sb.append("\n");
		sb.append("Opponent Hold: ");
		sb.append(describeHand(oppHold));
		sb.append("\n");
		
		sb.append("Player Life: ");
		sb.append(playerLife);
		sb.append("\n");
		sb.append("Opponent Life: ");
		sb.append(opponentLife);
		sb.append("\n");
		
		sb.append("Cards Left in Deck: ");
		sb.append(mDeck.cardsLeft());
		sb.append("\n");
		
		sb.append("Cursor: ");
		sb.append(cursorPos());
		sb.append("\n");
		sb.append("Selected Cards: ");
		sb.append(describeSelected());
		sb.append("\n");
		sb.append("Legality: ");
		sb.append(handIsLegal);
		sb.append("\n");
		return sb.toString();
	}
	
	public void doPlay(){
		//check if there are selected cards
		if(!isSelected()) return;
		//check if selected cards constitute a legal play
		if(handIsLegal < 0) return;
		
		//handIsLegal should contain the value of the current hand
		//use value to determine damage of play
		currentDamage = handIsLegal;
		
		//Get modifiers
		List<Modifier> l = new ArrayList<Modifier>();
		for(int i = 0; i<5; i++){
			if(selected[i])
			{
				if(playerHand[i].modifier != -1)
					l.add(MODS[i]);
			}
		}
		
		//perform modifiers 
		for(Modifier m : l){
			if(m.doCheck(false)){
				m.doEffect(false);
			}
		}
		
		//deal damages
		opponentLife = opponentLife - currentDamage;
		
		//replace selected cards
		for(int i = 0; i< 5; i++){
			if(selected[i]){
				//playerHand[i] = new Card();
				playerHand[i] = mDeck.draw();
				selected[i] = false;
			}
		}
		handIsLegal = -1;
		currentDamage = 0;
		
		//change turn to opponent
		setMode(WAITING_FOR_OPPONENT);
	}
	
	public void doHold(){
		//check for presence of hold
		boolean hasHold = isHeld();
		//check if cards were selected
		boolean hasSelected = isSelected();
		//check if selected cards constitute a legal play
		if(hasSelected && handIsLegal < 0)
			return;
		if(!hasSelected && !hasHold)
			return;
		else if(hasSelected && hasHold){
			//use holdValue and handIsLegal to do damage
			int holdvalue = holdValue();
			
			
			//get modifiers
			List<Modifier> l = new ArrayList<Modifier>();
			for(int i = 0; i<5; i++){
				if(selected[i])
				{
					if(playerHand[i].modifier != -1)
						l.add(MODS[i]);
				}
				if(playerHold[i].num != -1)
				{
					if(playerHold[i].modifier != -1)
						l.add(MODS[i]);
				}
			}
			
			//combine damage first
			currentDamage = holdvalue + handIsLegal;
			
			//perform modifiers
			for(Modifier m : l){
				if(m.doCheck(false)){
					m.doEffect(false);
				}
			}
			
			//deal damage
			opponentLife = opponentLife - currentDamage;
			
			//remove cards from hold
			Arrays.fill(playerHold, new Card());
			//replace cards from hand
			for(int i = 0; i< 5; i++){
				if(selected[i]){
					//playerHand[i] = new Card();
					playerHand[i] = mDeck.draw();
					selected[i] = false;
				}
			}
			handIsLegal = -1;
			currentDamage = 0;
		}
		else if (hasSelected){ //&&!hasHold
			//store selected into hold
			int t = 0;
			for(int i = 0; i<5; i++){
				if(selected[i]){
					playerHold[t] = playerHand[i];
					t++;
					//playerHand[i] = new Card();
					playerHand[i] = mDeck.draw();
					selected[i] = false;
				}
			}
			handIsLegal = -1;
		}
		else if (hasHold){
			//play hold as if it were normal
			currentDamage = holdValue();
			
			List<Modifier> l = new ArrayList<Modifier>();
			for(int i = 0; i<5; i++){
				if(playerHold[i].num != -1)
				{
					if(playerHold[i].modifier != -1)
						l.add(MODS[i]);
				}
			}
			
			//perform modifiers
			for(Modifier m : l){
				if(m.doCheck(false)){
					m.doEffect(false);
				}
			}
			
			//deal damage using holdValue
			opponentLife = opponentLife - currentDamage;
			
			//replace playerHold
			Arrays.fill(playerHold, new Card());
			
			currentDamage = 0;
		}
		
		if(opponentLife <= 0){
			setMode(TITLE, "You win, bitch");
		}
		else{
			//turn was actually taken
			//switch turn for opponent
			setMode(WAITING_FOR_OPPONENT);
		}
	}
	
	public void doDrop(){
		if(!isSelected()) return;
		for(int i = 0; i< 5; i++){
			if(selected[i]){
				//playerHand[i] = new Card();
				playerHand[i] = mDeck.draw();
				selected[i] = false;
			}
		}
		handIsLegal = -1;
		
		setMode(WAITING_FOR_OPPONENT);
	}
	
	public void doRobot(){
		Arrays.sort(oppHand);
		
		boolean straight = true;
		boolean flush = true;
		boolean five = true;
		for(int i = 1; i < 5; i++){
			if(straight && ((oppHand[0].num + i) != oppHand[i].num))
				straight = false;
			if(flush && (oppHand[0].suit != oppHand[i].suit))
				flush = false;
			if(five && (oppHand[0].num != oppHand[i].num))
				five = false;
			if(!(straight || flush || five))
				break;
		}
		boolean fh = false;
		if(oppHand[0].num == oppHand[1].num && oppHand[3].num == oppHand[4].num
				&&(oppHand[0].num == oppHand[2].num || oppHand[4].num == oppHand[2].num))
				fh = true;
		
		boolean rf = false;
		if(flush && oppHand[0].num == 0 && oppHand[1].num == 9 && oppHand[2].num == 10 && oppHand[3].num == 11
				&& oppHand[4].num == 12)
			rf = true;
		
		boolean four = false;
		boolean twop = false;
		int posf = -1;
		int postp = -1;
		
		if(oppHand[0].num == oppHand[1].num && oppHand[2].num == oppHand[3].num){
			//check four of a kind
			if(oppHand[0].num == oppHand[2].num)
				four = true;
			else
				twop = true;
			posf = postp = 0;
		}
		if(oppHand[1].num == oppHand[2].num && oppHand[3].num == oppHand[4].num){
			//check four of a kind
			if(oppHand[1].num == oppHand[3].num)
				four = true;
			else
				twop = true;
			posf = postp = 1;
		}
		if(oppHand[0].num == oppHand[1].num && oppHand[3].num == oppHand[4].num){
			twop = true;
			postp = 2;
		}
		
		boolean three = false;
		int posth = -1;
		for(int i = 0; i< 3; i++){
			if(oppHand[i+0].num == oppHand[i+1].num && oppHand[i+0].num == oppHand[i+2].num){
				three = true;
				posth = i;
			}
		}
		
		boolean two = false;
		int postw = -1;
		for(int i = 0; i<4; i++){
			if(oppHand[i+0].num == oppHand[i+1].num)
			{
				two = true;
				postw = i;
			}
		}
		
		//armed with what possible plays they have, and the cards they have
		//run some kind of AI routine to choose something to play
		
		{//AI routine "discard randomly"
			int cards = (int)Math.floor((Math.random() * 4)+1);
			boolean discard[] = new boolean[5];
			for(int i = 0; i < cards; i++){
				discard[(int)Math.floor((Math.random() * 4))] = true;
			}
			
			for(int j = 0; j < 5; j++){
				if(discard[j]){
					//oppHand[j] = new Card();
					oppHand[j] = mDeck.draw();
				}
			}
		}
		
		if(playerLife <= 0 ){
			setMode(TITLE, "You Lose, Bitch");
		}else{
			setMode(PLAYER_SELECT);
		}
	}
	
	public int holdValue(){
		List<Card> l = new ArrayList<Card>();
		int holdValue = -1;
		for(int i = 0; i< 5; i++){
			if(playerHold[i].num != -1)
				l.add(playerHold[i]);
		}
		Card[] selectedHand = l.toArray(new Card[l.size()]);
		if(selectedHand.length == 0)
			holdValue = -1;
		if(selectedHand.length == 1)
			holdValue = -1;
		else if(selectedHand.length == 2)
		{
			//check pair
			if(selectedHand[0].num == selectedHand[1].num)
				holdValue = PAIR_VALUE;
		}
		else if(selectedHand.length == 3)
		{
			//check three of a kind
			if(selectedHand[0].num == selectedHand[1].num && selectedHand[0].num == selectedHand[2].num)
				holdValue = THREE_KIND_VALUE;
		}
		else if(selectedHand.length == 4)
		{
			Arrays.sort(selectedHand);
			//check two pair
			if(selectedHand[0].num == selectedHand[1].num && selectedHand[2].num == selectedHand[3].num){
				//check four of a kind
				if(selectedHand[0].num == selectedHand[2].num)
					holdValue = FOUR_KIND_VALUE;
				else
					holdValue = TW0_PAIR_VALUE;
			}
		}
		else if(selectedHand.length == 5){
			Arrays.sort(selectedHand);
			//check for straight
			//check for flush
			//if not straight or flush
			//	check for full house
			//if straight and flush
				//check for Royal Flush
			boolean straight = true;
			boolean flush = true;
			boolean five = true;
			for(int i = 1; i < 5; i++){
				if(straight && ((selectedHand[0].num + i) != selectedHand[i].num))
					straight = false;
				if(flush && (selectedHand[0].suit != selectedHand[i].suit))
					flush = false;
				if(five && (selectedHand[0].num != selectedHand[i].num))
					five = false;
				if(!(straight || flush || five))
					break;
			}
			
			if(!(straight || flush || five))
			{
				if(selectedHand[0].num == selectedHand[1].num && selectedHand[3].num == selectedHand[4].num
					&&(selectedHand[0].num == selectedHand[2].num || selectedHand[4].num == selectedHand[2].num))
					holdValue = FULL_HOUSE_VALUE;
			}
			if(flush && selectedHand[0].num == 0 && selectedHand[1].num == 9 && selectedHand[2].num == 10
					&&selectedHand[3].num == 11 && selectedHand[4].num == 12)
				holdValue = ROYAL_FLUSH_VALUE;

			if(straight && flush){
				holdValue = STRAIGHT_FLUSH_VALUE;
			}
			else if(straight)
				holdValue = STRAIGHT_VALUE;
			else if(flush)
				holdValue = FLUSH_VALUE;
			else if (five)
				holdValue = Integer.MAX_VALUE;
		}
		
		return holdValue;
	}
	
	public int isLegalMove(){
		List<Card> l = new ArrayList<Card>();
		int handValue = -1;
		for(int i = 0; i<5; i++){
			if(selected[i])
				l.add(playerHand[i]);
		}
		Card[] selectedHand = l.toArray(new Card[l.size()]);
		if(selectedHand.length == 0)
			handValue = -1;
		if(selectedHand.length == 1)
			handValue = -1;
		else if(selectedHand.length == 2)
		{
			//check pair
			if(selectedHand[0].num == selectedHand[1].num)
				handValue = PAIR_VALUE;
		}
		else if(selectedHand.length == 3)
		{
			//check three of a kind
			if(selectedHand[0].num == selectedHand[1].num && selectedHand[0].num == selectedHand[2].num)
				handValue = THREE_KIND_VALUE;
		}
		else if(selectedHand.length == 4)
		{
			Arrays.sort(selectedHand);
			//check two pair
			if(selectedHand[0].num == selectedHand[1].num && selectedHand[2].num == selectedHand[3].num){
				//check four of a kind
				if(selectedHand[0].num == selectedHand[2].num)
					handValue = FOUR_KIND_VALUE;
				else
					handValue = TW0_PAIR_VALUE;
			}
		}
		else if(selectedHand.length == 5){
			Arrays.sort(selectedHand);
			//check for straight
			//check for flush
			//if not straight or flush
			//	check for full house
			//if straight and flush
				//check for Royal Flush
			boolean straight = true;
			boolean flush = true;
			boolean five = true;
			for(int i = 1; i < 5; i++){
				if(straight && (selectedHand[0].num + i != selectedHand[i].num))
					straight = false;
				if(flush && (selectedHand[0].suit != selectedHand[i].suit))
					flush = false;
				if(five && (selectedHand[0].num != selectedHand[i].num))
					five = false;
				if(!(straight || flush || five))
					break;
			}
			
			if(!(straight || flush || five))
			{
				if(selectedHand[0].num == selectedHand[1].num && selectedHand[3].num == selectedHand[4].num
					&&(selectedHand[0].num == selectedHand[2].num || selectedHand[4].num == selectedHand[2].num))
					handValue = FULL_HOUSE_VALUE;
			}
			if(flush && selectedHand[0].num == 0 && selectedHand[1].num == 9 && selectedHand[2].num == 10
					&&selectedHand[3].num == 11 && selectedHand[4].num == 12)
				handValue = ROYAL_FLUSH_VALUE;
			else if(straight && flush){
				handValue = STRAIGHT_FLUSH_VALUE;
			}
			else if(straight)
				handValue = STRAIGHT_VALUE;
			else if(flush)
				handValue = FLUSH_VALUE;
			else if (five)
				handValue = Integer.MAX_VALUE;
		}
		
		return handValue;
	}
	
	public boolean isSelected(){
		for(int i = 0; i < 5; i++){
			if(selected[i])
				return true;
		}
		return false;
	}
	
	public boolean isHeld(){
		for(int i = 0; i<5; i++){
			if(playerHold[i].num != -1)
				return true;
		}
		return false;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if(mode == WAITING_FOR_OPPONENT)
		{
			if(opponentIsRobot)
			{
				doRobot();
			}
			else {
				//wait for network stuff
			}
		}
	}
	
	/**
     * Standard override to get key events.
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent msg) {
        boolean handled = false;
        if(mode == TITLE){
        	switch(keyCode){
        	case KeyEvent.KEYCODE_DPAD_CENTER:
        		setMode(INTERIM);
        		handled = true;
        		initGame();
        		break;
        	}
        }
        else if (mode == PLAYER_SELECT){
	        switch(keyCode){
	        case KeyEvent.KEYCODE_DPAD_LEFT:
	        	if(cursor == 0) //first card -> three buttons
	        		cursor = 6;
	        	else if(cursor == 5 || cursor == 6 || cursor == 7) //three buttons -> card 5
	        		cursor = 4;
	        	else
	        		cursor--;
	        	handled = true;
	        	break;
	        case KeyEvent.KEYCODE_DPAD_RIGHT:
	        	if(cursor == 4) // last card -> three buttons
	        		cursor = 6;
	        	else if(cursor == 5 || cursor == 6 || cursor == 7) //three buttons -> card 1
	        		cursor = 0;
	        	else
	        		cursor++;
	        	handled = true;
	        	break;
	        case KeyEvent.KEYCODE_DPAD_UP:
	        	if(cursor == 7) //drop button -> play button
	        		cursor = 6;
	        	else  // play or cards -> hold button
	        		cursor = 5;
	        	//hold button -> hold button
	        	handled = true;
	        	break;
	        case KeyEvent.KEYCODE_DPAD_DOWN:
	        	if(cursor == 5) //hold button -> play button
	        		cursor = 6;
	        	else  // play or cards -> drop button
	        		cursor = 7;
	        	//drop button -> drop button
	        	handled = true;
	        	break;
	        case KeyEvent.KEYCODE_DPAD_CENTER:
	        	if(cursor < 5){
	        		selected[cursor] = !selected[cursor];
	        		handIsLegal = isLegalMove();
	        	}
	        	else if(cursor == 6)
	        		doPlay();
	        	else if(cursor == 7)
	        		doDrop();
	        	else if(cursor == 5)
	        		doHold();
	        	
	        	handled = true;
	        	break;
	        case KeyEvent.KEYCODE_1:
	        	selected[0] = !selected[0];
	        	handIsLegal = isLegalMove();
	        	handled = true;
	        	break;
	        case KeyEvent.KEYCODE_2:
	        	selected[1] = !selected[1];
	        	handIsLegal = isLegalMove();
	        	handled = true;
	        	break;
	        case KeyEvent.KEYCODE_3:
	        	selected[2] = !selected[2];
	        	handIsLegal = isLegalMove();
	        	handled = true;
	        	break;
	        case KeyEvent.KEYCODE_4:
	        	selected[3] = !selected[3];
	        	handIsLegal = isLegalMove();
	        	handled = true;
	        	break;
	        case KeyEvent.KEYCODE_5:
	        	selected[4] = !selected[4];
	        	handIsLegal = isLegalMove();
	        	handled = true;
	        	break;
	        case KeyEvent.KEYCODE_7:
	        case KeyEvent.KEYCODE_CALL:
	        	doPlay();
	        	handled = true;
	        	break;
	        case KeyEvent.KEYCODE_8:
	        	doHold();
	        	handled = true;
	        	break;
	        case KeyEvent.KEYCODE_9:
	        	doDrop();
	        	handled = true;
	        	break;
	        case KeyEvent.KEYCODE_0:
	        case KeyEvent.KEYCODE_ENDCALL:
	        	//Deselect all
	        	for(int i = 0; i<5; i++)
	        		selected[i] = false;
	        	handled = true;
	        	break;
	        default:
	        	break;
	        }
        }
        if(handled){
        	if(DEBUG)
        		mStatusText.setText(getStatus());
        	invalidate();
        }
        return handled;
    }
}
