import java.util.Scanner;

public class CinemaNew {
    final static Scanner scanner = new Scanner(System.in);
    static int rows;
    static int seats;
    static int choice;
    static int rowEnter;
    static int seatEnter;
    static char[][] room;
    static int[][] priceOfSeats;
    static int currentIncome;
    static int countOfPurchasedTickets;
    static int totalNumberOfSeats;
    static float percentage;
    static int totalIncome;

    public static void main(String[] args) {
        enterRowsAndSeats();
        createArrayOfSeatsNamedRoom();
        createArrayOfTicketPricesNamedPriceOfSeats();
        showMenu();

        while (choice != 0) {
            switch (choice) {
                case 1:
                    showTheSeats(room);
                    showMenu();
                    break;
                case 2:
                    buyTicket(priceOfSeats, room);
                    swap(room);
                    showMenu();
                    break;
                case 3:
                    numberOfPurchasedTickets();
                    countPercentage(room);
                    countCurrentIncome(room, priceOfSeats);
                    countTotalIncome(priceOfSeats);
                    showMenu();
                    break;
            }
        }


    }

    public static void enterRowsAndSeats() {
        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seats = scanner.nextInt();


    }

    public static void showMenu() {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        choice = scanner.nextInt();
        if (choice != 0 && choice != 1 && choice != 2 && choice != 3) {
            System.out.println("Wrong input!");
            System.out.println();
            showMenu();
        }

    }


    public static void buyTicket(int[][] priceOfSeats, char[][] room) {
        System.out.println("Enter a row number:");
        rowEnter = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        seatEnter = scanner.nextInt();
        if (rowEnter < 1 || rowEnter > rows || seatEnter < 1 || seatEnter > seats) {
            System.out.println("Wrong input!");
            System.out.println();
            buyTicket(priceOfSeats, room);

        } else if (room[rowEnter - 1][seatEnter] == 'B') {
            System.out.println("That ticket has already been purchased!");
            System.out.println();
            buyTicket(priceOfSeats, room);
        } else {
            int ticketPrice = priceOfSeats[rowEnter - 1][seatEnter];
            System.out.printf("Ticket price: $%d", ticketPrice);
            System.out.println();
        }


    }

    public static void showTheSeats(char[][] room) {

        System.out.println("Cinema:");
        System.out.print("  ");

        for (int i = 0; i < seats; i++) {
            System.out.print(i + 1 + " ");
        }
        System.out.println();
        for (int i = 0; i < room.length; i++) {
            for (int j = 0; j < room[i].length; j++) {
                System.out.print(room[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void createArrayOfSeatsNamedRoom() {
        room = new char[rows][seats + 1];
        for (int i = 0; i < room.length; i++) {
            room[i][0] = (char) ('1' + i);
        }

        for (int i = 0; i < room.length; i++) {
            for (int j = 1; j < room[i].length; j++) {
                room[i][j] = 'S';
            }
        }
    }

    public static void createArrayOfTicketPricesNamedPriceOfSeats() {
        priceOfSeats = new int[rows][seats + 1];
        int checkNumberOfSeats = 60;
        int commonNumber = rows * seats;

        if (commonNumber > checkNumberOfSeats) {
            for (int i = 0; i < priceOfSeats.length / 2; i++) {
                for (int j = 0; j < priceOfSeats[i].length; j++) {
                    priceOfSeats[i][j] = 10;
                }
            }
            for (int i = priceOfSeats.length / 2; i < priceOfSeats.length; i++) {
                for (int j = 0; j < priceOfSeats[i].length; j++) {
                    priceOfSeats[i][j] = 8;
                }
            }
        } else {
            for (int i = 0; i < priceOfSeats.length; i++) {
                for (int j = 0; j < priceOfSeats[i].length; j++) {
                    priceOfSeats[i][j] = 10;
                }
            }

        }
    }


    public static void swap(char[][] room) {
        room[rowEnter - 1][seatEnter] = 'B';
    }

    public static int countCurrentIncome(char[][] room, int[][] priceOfSeats) {
        currentIncome = 0;
        for (int i = 0; i < room.length; i++) {
            for (int j = 0; j < room[i].length; j++) {
                if (room[i][j] == 'B') {
                    currentIncome += priceOfSeats[i][j];
                }
            }
        }
        System.out.printf("Current income: $%d", currentIncome);
        System.out.println();
        return currentIncome;

    }

    public static void numberOfPurchasedTickets() {
        countOfPurchasedTickets = 0;
        totalNumberOfSeats = (rows * (seats));
        for (int i = 0; i < room.length; i++) {
            for (int j = 0; j < room[i].length; j++) {
                if (room[i][j] == 'B') {
                    countOfPurchasedTickets += 1;
                }
            }
        }
        System.out.printf("Number of purchased tickets: %d", countOfPurchasedTickets);
        System.out.println();
    }

    public static void countPercentage(char[][] room) {

        percentage =  ((float) countOfPurchasedTickets / (float) totalNumberOfSeats) * 100;
        System.out.printf("Percentage: %.2f", percentage);
        System.out.print("%");
        System.out.println();
    }

    public static void countTotalIncome(int[][] priceOfSeats) {
        totalIncome = 0;
        for (int i = 0; i < priceOfSeats.length ; i++) {
            for (int j = 1; j < priceOfSeats[i].length; j++) {
                totalIncome += priceOfSeats[i][j];
            }
        }
        System.out.printf("Total income: $%d", totalIncome);
        System.out.println();
    }




}
