package Movies;
//
//import ConfigurationControl.Configuration;
//import ConfigurationControl.ConfigurationList;
//import CustomOutputStream.CustomOutputStream;
import Halls.Halls;
import User.Client;
import java.io.Serializable;
import java.io.*;
import java.util.Arrays;

public class Movies implements Serializable{
    //Member variables
    private String name;
    private int duration;
    private String description;
    private float score;
    private String[] genres;
    private String trailer; //*A url or relative path to the local video file* (//TODO : To be added to constructors)
    private int id=0;
    private Halls hall;

    //Parameterized constructor
    public Movies(String name, int duration, String description, float score, String[] genres) {
        this.name = name;
        this.duration = duration;
        this.description = description;
        this.score = score;
        this.genres = genres;
        id++;
    }
    public void setHall(Halls hall){
        this.hall = hall;
    }
    public Halls getHall(){
        return hall;
    }

    //No arg constructor (for testing) //TODO : to be deleted ?
    public Movies() {
        this("TEST", 120, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam malesuada.", 4.5f, new String[]{"Horror", "Comedy"});
        id++;
    }


    //Getters
    public float getScore() {
        return score;
    }

    public int getDuration() {
        return duration;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getTrailer() {
        return trailer;
    }

    public String[] getGenres() {
        return genres;
    }

    public int getId() {return id;}
    //endGetters

    //Setters
    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    private void setId(int id) {this.id=id;}
    // endSetters

    //Writes object to Movies.BIN
//    public void writeMovies() throws ClassNotFoundException {
//        File file = new File("Movies.Bin");
//        if(file.length() == 0){
//            try {
//                new Configuration(ConfigurationList.currentMaxMovieId,0).writeConfiguration();
//                ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("Movies.Bin", true));
//                this.setId(0);
//                os.writeObject(this);
//                os.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        else{
//            try {
//                CustomOutputStream cs = null;
//                cs = new CustomOutputStream(new FileOutputStream("Movies.Bin", true));
//                Configuration configuration = Configuration.getConfiguration(ConfigurationList.currentMaxMovieId);
//                int newID=configuration.getNumericProperty1();
//                newID++;
//                configuration.setNumericProperty1(newID);
//                Configuration.editConfiguration(configuration);
//                this.setId(newID);
//                cs.writeObject(this);
//                cs.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        //System.out.println("successful wrote " + getName());
//        //System.out.println("ID AFTER GET nxt ID: "+ id); TODO: delete
//    }

    public static void readMovies() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("Movies.Bin");
        try {
            ObjectInputStream input = new ObjectInputStream(fileInputStream);
            System.out.format("+----+-----------------+---------------------------+----------+---------+%n");
            System.out.format("| ID | Movie           | Genre                     | Duration | Rating  |%n");
            System.out.format("+----+-----------------+---------------------------+----------+---------+%n");
            while(fileInputStream.available() != 0){
                Movies movie = (Movies) input.readObject();
                movie.printMovie(); //TODO: delete
                //System.out.println("ID: "+movie.getId());
            }
            input.close();
        } catch (EOFException e) {
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Movies readMovie(int id) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("Movies.Bin");
        try {
            ObjectInputStream input = new ObjectInputStream(fileInputStream);
            while(fileInputStream.available() != 0){
                Movies movie = (Movies) input.readObject();
                if(movie.id==id){
                    fileInputStream.close();
                    input.close();
                    return movie;
                }
            }
            input.close();
        } catch (EOFException e) {
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void deleteMovies(Movies mo) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("Movies.Bin");
        File movies = new File("Movies.Bin");
        File temp = new File("Temp.Bin");
        File movies2 = new File("Movies.Bin");
        try {
            ObjectInputStream input = new ObjectInputStream(fileInputStream);
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("Temp.Bin", true));
            while(fileInputStream.available() != 0){
                Movies movie = (Movies) input.readObject();
                //System.out.println("Movie name : " + movie.getName() + " id : " + movie.getId());
                if(movie.getId() == mo.getId()){
                    //System.out.println("skipped " + movie.getName()); //TODO delete
                    continue;
                }
                else
                    os.writeObject(movie);
            }
            input.close();
            os.close();
        } catch (EOFException e) {
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        fileInputStream.close();
        movies.delete();
        if(temp.renameTo(movies2)){
            //System.out.println("Successfully renamed file"); TODO delete
        }
    }

    public static void editMovies(Movies mo) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("Movies.Bin");
        File movies = new File("Movies.Bin");
        File temp = new File("Temp.Bin");
        File movies2 = new File("Movies.Bin");
        try {
            ObjectInputStream input = new ObjectInputStream(fileInputStream);
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("Temp.Bin", true));
            while(fileInputStream.available() != 0){
                Movies movie=(Movies) input.readObject();
                //System.out.println("Movie name : " + movie.getName() + " id : " + movie.getId());
                if(movie.getId() == mo.getId()){
                    //System.out.println("Overwriting " + movie.getName()); TODO delete
                    os.writeObject(mo);
                }
                else
                    os.writeObject(movie);
            }
            input.close();
            os.close();
        } catch (EOFException e) {
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        fileInputStream.close();
        movies.delete();
        if(temp.renameTo(movies2)){
            //System.out.println("Successfully renamed file"); TODO delete
        }
    }
    private static int getNextMoviesId() throws IOException {
        int id = -1;
        FileInputStream fileInputStream = new FileInputStream("Movies.Bin");
        try {
            ObjectInputStream input = new ObjectInputStream(fileInputStream);
            while(fileInputStream.available() != 0){
                Movies movie = (Movies) input.readObject();
                id = movie.getId();
            }
            input.close();
        } catch (EOFException e) {
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ++id;
    }

    //Prints object
    public void printMovie() {
        String leftAlignFormat = "| %-2d | %-15s | %-25s | %-8s | %-7.1f |%n";


        System.out.format(leftAlignFormat,getId(),getName() ,Arrays.toString(getGenres()),(getDuration()+"min"),getScore());

        System.out.format("+----+-----------------+---------------------------+----------+---------+%n");
//        System.out.println("Name: " + getName());
//        System.out.println("Genre(s): "+Arrays.toString(getGenres()));
//        System.out.println("Duration: " + getDuration() + "min, IMDB Score: " + getScore());
//        System.out.println("Description: " + getDescription());
    }
}