import java.util.Scanner;

public class FlyReservasjon {

	Rad[] rader;
	int antallRader;
	private static final Scanner input = new Scanner(System.in);

	FlyReservasjon() {
		this(10);
	}
	
	FlyReservasjon(int antallRader) {

		if ( antallRader > 100 ){
			System.out.println("Number of rows is limited to 100");
			return;
		}

		this.antallRader = antallRader;
		rader = new Rad[antallRader];

		for (int i = 0; i < antallRader; i++){
			rader[i] = new Rad(4);
		}
	}	

	public static void main(String[] args) {

		FlyReservasjon reservere = new FlyReservasjon(10);
		reservere.getRadInput();

	}

	public void getRadInput(){

		System.out.println("Hvilken rad(1-10) vil du sitte på?");
		int radInput = input.nextInt();
		doRadInput(radInput);

	}

	public void doRadInput(int radInput){

		if ( radInput == -1){
			System.out.print("Takk for at du brukte programmet!");

		} else if ( radOK(radInput) ){
			getSeat(radInput);

		} else {
			System.out.println("Valgt rad er ugyldig");
			getRadInput();

		}

	}

	private void getSeat(int radInput){

		System.out.println("Hvilket sete vil du sitte på? ");
		char seteInput = input.next().charAt(0);
		doSeatInput(radInput, seteInput);

	}

	private void doSeatInput(int radInput, char seteInput){

		if (  rader[0].isLegalSeat(seteInput) && reserveSeat(radInput, seteInput) ){
			show();

		} else {
			System.out.println("Valgt sete er ugyldig");

		}
		getRadInput();

	}

	public boolean radOK(int valgtRad) {

		return valgtRad <= antallRader && valgtRad >= 1;

	}
	
	public Rad getRad(int row) {

		return rader[row-1];

	}

	public boolean reserveSeat(int rad, char seat) {

		return rader[rad-1].assignSeat(seat);

	}

	public void show() {

		for ( int i = 0; i < antallRader; i++ ){
			System.out.println((i+1) + " " + rader[i].toString());
		}

	}

}