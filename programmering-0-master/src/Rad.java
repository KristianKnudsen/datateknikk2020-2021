public class Rad {
	private char[] rad;
	private int noOfSeats = 4;

	public Rad() {
		this(4);		
	}
	public Rad(int noOfS) {

		if ( noOfS > 10 ){
			System.out.println("Number of seats is limited to 10");
			return;
		}

		noOfSeats = noOfS;
		rad = new char[noOfS];

		for ( int i = 0; i < noOfS; i ++){
			rad[i] = (char) ('A' + i);
		}

	}
	
	public boolean isLegalSeat(char seat) {
		return Character.toUpperCase(seat) <= rad[noOfSeats-1];
	}
	
	public boolean assignSeat(char seat) {
		int seatIndex = Character.toUpperCase(seat) - 'A';

		if ( rad[seatIndex] == 'X'){
			return false;
		}

		rad[seatIndex] = 'X';
		return true;
	}
	
	public int getNoofSeats() {
		return noOfSeats;
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (char c : rad) {
			s.append(c).append(" ");
		}

		return s.toString();
	}
}
