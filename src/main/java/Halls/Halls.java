package Halls;
//
//import ConfigurationControl.Configuration;
//import ConfigurationControl.ConfigurationList;
//import CustomOutputStream.CustomOutputStream;
import Movie.Movie;
import SessionHandler.SessionHandler;
import User.Client;
import Halls.*;
import java.io.*;
import java.sql.Date;
import java.sql.Time;

public class Halls implements Serializable {
    private int seatRows, seatColumns;
    private Seats[][] HallSeats;
    private int hallNumber;
    private Movie movie = new Movie();

    public int getHallNumber() {
        return hallNumber;
    }

    public void setHallNumber(int hallNumber) {
        this.hallNumber = hallNumber;
    }

    public Halls(int seatRows, int seatColumns){
        this.seatColumns = seatColumns;
        this.seatRows = seatRows;
        int number = 0;
        HallSeats = new Seats[this.seatRows][this.seatColumns];
        for(int i = 0; i< HallSeats.length; i++){
            for(int j = 0; j<HallSeats[0].length; j++){
                HallSeats[i][j] = new Seats(Seats.tiers.Normal, number);
                number++;
            }
        }
    }

    public Halls(int seatRows, int seatColumns, Movie movie){
        this(seatRows, seatColumns);
        this.movie = movie;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Seats getSeat(int seatNumber){
        for(int i = 0; i< HallSeats.length; i++){
            for(int j = 0; j<HallSeats[0].length; j++){
                if(HallSeats[i][j].getSeatNumber() == seatNumber)
                    return HallSeats[i][j];
            }
        }
        return null;
    }

    public int getSeatColumns() {
        return seatColumns;
    }

    public int getSeatRows() {
        return seatRows;
    }

    public Seats[][] getHallSeats() {
        return HallSeats;
    }

    public void setTaken(int row, int column, boolean taken){
        HallSeats[row][column].setTaken(taken);
    }
    public void setTaken(int seatNumber, boolean taken){
        for(int i = 0; i< HallSeats.length; i++){
            for(int j = 0; j< HallSeats[0].length; j++){
                if(seatNumber == HallSeats[i][j].getSeatNumber()){
                    HallSeats[i][j].setTaken(taken);
                    return;
                }
            }
        }
    }
    @Override
    public String toString() {
        StringBuffer SeatsReturn = new StringBuffer();
        SeatsReturn.append("Movie: " + movie.getName()+"\n");
        for(int i = 0; i<HallSeats.length; i++){
            for(int j = 0; j<HallSeats[0].length; j++){
                SeatsReturn.append(HallSeats[i][j].isTaken() ? "1" : "0");
            }
            SeatsReturn.append("\n");
        }
        return SeatsReturn.toString();
    }
//    public void writeHall(){
//        File file = new File("Halls.Bin");
//        if(file.length() == 0){
//            try {
//                ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("Halls.Bin", true));
//                this.setHallNumber(0);
//                new Configuration(ConfigurationList.currentMaxHallId, 0).writeConfiguration();
//                os.writeObject(this);
//                os.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            //System.out.println("successful wrote Hall no: " + getHallNumber());
//        }
//        else{
//            try {
//                CustomOutputStream cs = null;
//                cs = new CustomOutputStream(new FileOutputStream("Halls.Bin", true));
//                Configuration config = Configuration.getConfiguration(ConfigurationList.currentMaxHallId);
//                int newNumber = config.getNumericProperty1();
//                ++newNumber;
//                config.setNumericProperty1(newNumber);
//                Configuration.editConfiguration(config);
//                this.setHallNumber(newNumber);
//                cs.writeObject(this);
//                cs.close();
//            } catch (IOException | ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//            //System.out.println("successful wrote Hall no: " + getHallNumber());
//        }
//    }

    public static void readHalls() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("Halls.Bin");
        try {
            ObjectInputStream input = new ObjectInputStream(fileInputStream);
            while(fileInputStream.available() != 0){
                Halls hall = (Halls) input.readObject();
                //System.out.println("Hall Number: " + hall.getHallNumber()+"\n"+ hall);
            }
            input.close();
        } catch (EOFException e){
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void deleteHall(Halls hl) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("Halls.Bin");
        File halls = new File("Halls.Bin");
        File temp = new File("TempHall.Bin");
        File halls2 = new File("Halls.Bin");
        try {
            ObjectInputStream input = new ObjectInputStream(fileInputStream);
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("TempHall.Bin", true));
            while(fileInputStream.available() != 0){
                Halls hall = (Halls) input.readObject();
                //System.out.println("Hall no : " + hall.getHallNumber());
                if(hall.getHallNumber() == hl.getHallNumber()){
                    //System.out.println("skipped hall" + hall.getHallNumber());
                    continue;
                }
                else
                    os.writeObject(hall);
            }
            input.close();
            os.close();
        } catch (EOFException e) {
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        fileInputStream.close();
        halls.delete();
        if(temp.renameTo(halls2)){
            //System.out.println("Successfully renamed file");
        }
    }
    public static void editHall(Halls h1) throws IOException{
        FileInputStream fileInputStream = new FileInputStream("Halls.Bin");
        File halls = new File("Halls.Bin");
        File temp = new File("Temp.Bin");
        File halls2 = new File("Halls.Bin");
        try{
            ObjectInputStream input = new ObjectInputStream(fileInputStream);
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("Temp.bin", true));
            while(fileInputStream.available() != 0){
                Halls hall = (Halls) input.readObject();
                //System.out.println("Hall no: "+hall.getHallNumber());
                if(hall.getHallNumber() == h1.getHallNumber()){
                    //System.out.println("Overwriting "+hall.getHallNumber());
                    os.writeObject(h1);
                }
                else
                    os.writeObject(hall);
            }
            input.close();
            os.close();
        } catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        fileInputStream.close();
        halls.delete();
        if(temp.renameTo(halls2)){
            //System.out.println("Successfully renamed file");
        }
    }
    private static int getNextHall() throws IOException {
        int number = -1;
        FileInputStream fileInputStream = new FileInputStream("Halls.Bin");
        try {
            ObjectInputStream input = new ObjectInputStream(fileInputStream);
            while(fileInputStream.available() != 0){
                Halls hall = (Halls) input.readObject();
                number = hall.getHallNumber();
            }
            input.close();
        } catch (EOFException e){
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ++number;
    }
    public void printHallSeats(){
        for(int i = 0; i< HallSeats.length; i++) {
            for (int j = 0; j < HallSeats[i].length; j++) {
                System.out.print(" ___ ");
            }

            System.out.println();
            for (int j = 0; j < HallSeats[i].length; j++) {
                System.out.print("| " + HallSeats[i][j].getSeatNumber() + " |");
            }
            System.out.println();
            for (int j = 0; j < HallSeats[i].length; j++) {
                System.out.print(" ``` ");
            }
            System.out.println();
        }
    }
//    public void printHallSeats(Date date, Time time) throws FileNotFoundException {
//        for(int i = 0; i< HallSeats.length; i++) {
//            for (int j = 0; j < HallSeats[i].length; j++) {
//                System.out.print(" ___ ");
//            }
//
//            System.out.println();
//            for (int j = 0; j < HallSeats[i].length; j++) {
//                if(!checkSeatAvailability(date, time, this, HallSeats[i][j])){
//                    System.out.print("\u001B[31m" +"| " + HallSeats[i][j].getSeatNumber() + " |");
//                    System.out.print("\u001B[37m");
//                    HallSeats[i][j].setTaken(true);
//                }
//                else
//                    System.out.print("| " + HallSeats[i][j].getSeatNumber() + " |");
//            }
//            System.out.println();
//            for (int j = 0; j < HallSeats[i].length; j++) {
//                System.out.print(" ``` ");
//            }
//            System.out.println();
//        }
//    }
    public static boolean checkSeatAvailability(Date date, Time time, Halls hall, Seats seat) throws FileNotFoundException {
        boolean isAvailable = true;
        FileInputStream fileInputStream = new FileInputStream("Clients.Bin");
        try {
            ObjectInputStream input = new ObjectInputStream(fileInputStream);
            while(fileInputStream.available() != 0){
                Client client = (Client) input.readObject();
                if(client.tickets != null){
                    for(int i = 0; i < client.tickets.size(); i++){
                        if(client.tickets.get(i).getHall().getHallNumber() == hall.getHallNumber() && client.tickets.get(i).getPlayingDate().equals(date) && client.tickets.get(i).getPlayingTime().getHours() == (time.getHours()) && client.tickets.get(i).getSeat().getSeatNumber() == seat.getSeatNumber()){
                            isAvailable = false;

                        }
                    }
                }
            }
            input.close();
        } catch (EOFException e) {
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return isAvailable;
    }
}
