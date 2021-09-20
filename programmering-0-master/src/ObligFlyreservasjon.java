public class ObligFlyreservasjon {
    public static void main(String[] args) {



        Rad rad1 = new Rad(5);

        if ( rad1.assignSeat('A') ) {
            System.out.println("Your seat has been assignet");
        } else {
            System.out.println("Your seat is taken");
        }
        if ( rad1.assignSeat('A') ) {
            System.out.println("Your seat has been assignet");
        } else {
            System.out.println("Your seat is taken");
        }


        System.out.println(rad1.toString());


    }
}

