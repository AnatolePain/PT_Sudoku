import java.lang.*;
/**
 * La classe <code>TimerModel</code> gère le chronomètre.
 *  
 * @version 0.1
 * @author Anatole Pain
 */
public class TimerModel {

    private long nanosecondesStart;
    private long nanosecondes;
    private final static long CYCLE = 1000000L; //1 000 000  nanosecondes = 1 millisecondes
    private long millisecondes;
    private int secondes;
    private int minutes;
    private int hours;

    /**
     * Récupère le temps de depart en nanosecondes quand le chrono est lancé.
     */
    public void startTime(){
        nanosecondesStart = System.nanoTime();
    }

    /**
     * Récupère le temps en nanoseconde quand le chrono ce stop et fait les convertion pour
     * avoir les milisecondes, les secondes, les minutes et les heures.
     */
    public void setTime(){

        nanosecondes = System.nanoTime() - nanosecondesStart;

        millisecondes = (long)nanosecondes/CYCLE;

        secondes = (int)millisecondes/1000;
        millisecondes = millisecondes-secondes*1000;

        minutes = (int)secondes/60; 
        secondes = secondes-minutes*60; 

        hours = (int)minutes/60;
        minutes =  minutes-hours*60;

    }

    /**
     * @return renvoie une string des milisecondes avec le bon nombre de zéro devant 
     */
    private String getStringMillisecondes() {

        if(millisecondes >= 0 && millisecondes <= 9){
            return "00"+String.valueOf(millisecondes);
        }else if(millisecondes >= 10 && millisecondes <= 99){
            return "0"+String.valueOf(millisecondes);
        }else if(millisecondes >= 100 && millisecondes <= 999 ){
            return String.valueOf(millisecondes);
        }else{
            return "erreur methode getStringMillisecondes() ";
        }
    }

    /**
     * @return renvoie une string des secondes avec le bon nombre de zéro devant 
     */
    private String getStringSecondes() {

        if(secondes >= 0 && secondes <= 9){
            return "0"+String.valueOf(secondes);
        }else if(secondes >= 10 && secondes <= 99){
            return String.valueOf(secondes);
        }else{
            return "erreur getStringSecondes(): secondes = ";
        }
    }

    /**
     * @return renvoie une string des minutes avec le bon nombre de zéro devant 
     */
    private String getStringMinutes() {

        if(minutes >= 0 && minutes <= 9){
            return "0"+String.valueOf(minutes);
        }else if(minutes >= 10 && minutes <= 99){
            return String.valueOf(minutes);
        }else{
            return "erreur getStringMinutes()";
        }

    }

    /**
     * @return renvoie une string des heures avec le bon nombre de zéro devant
     */
    private String getStirngHeures() {

        if(hours >= 0 && hours <= 9){
            return "0"+String.valueOf(hours);
        }else if(hours >= 10 && hours <= 99){
            return String.valueOf(hours);
        }else{
            return "erreur getStringSecondes()";
        }
    }

    /**
     * @return renvoie la string final dans un format plus ou moins précis celon
     * l'absence ou la présence de secondes, minutes et heures
     */
    public String getString() {

        if(secondes == 0){
            return millisecondes+" millisecondes";
        }else if(minutes == 0){
            return this.getStringSecondes()+", "+ this.getStringMillisecondes()+" s";
        }else if(hours == 0){
            return this.getStringMinutes()+" m "+ this.getStringSecondes()+" s";
        }else{
            return this.getStirngHeures()+" h "+ this.getStringMinutes()+" m "
            + this.getStringSecondes()+" s ";
        }
    }
    
}