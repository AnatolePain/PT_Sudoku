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
        this.nanosecondesStart = System.nanoTime();
    }

    /**
     * Récupère le temps en nanoseconde quand le chrono ce stop et fait les convertion pour
     * avoir les milisecondes, les secondes, les minutes et les heurthis.es.
     */
    public void setTime(){

        this.nanosecondes = System.nanoTime() - this.nanosecondesStart;

        this.millisecondes = (long)this.nanosecondes/CYCLE;

        this.secondes = (int)this.millisecondes/1000;
        this.millisecondes = this.millisecondes - secondes*1000;

        this.minutes = (int)this.secondes/60; 
        this.secondes = this.secondes - minutes*60; 

        this.hours = (int)this.minutes/60;
        this.minutes =  this.minutes - this.hours*60;

    }

    /**
     * @return renvoie une string des milisecondes avec le bon nombre de zéro devant 
     */
    private String getStringMillisecondes() {

        if(this.millisecondes >= 0 && this.millisecondes <= 9){
            return "00"+String.valueOf(this.millisecondes);
        }else if(millisecondes >= 10 && this.millisecondes <= 99){
            return "0"+String.valueOf(this.millisecondes);
        }else if(this.millisecondes >= 100 && this.millisecondes <= 999 ){
            return String.valueOf(this.millisecondes);
        }else{
            return "erreur methode getStringMillisecondes() ";
        }
    }

    /**
     * @return renvoie une string des secondes avec le bon nombre de zéro devant 
     */
    private String getStringSecondes() {

        if(this.secondes >= 0 && this.secondes <= 9){
            return "0"+String.valueOf(this.secondes);
        }else if(this.secondes >= 10 && this.secondes <= 99){
            return String.valueOf(this.secondes);
        }else{
            return "erreur getStringSecondes(): secondes = ";
        }
    }

    /**
     * @return renvoie une string des minutes avec le bon nombre de zéro devant 
     */
    private String getStringMinutes() {

        if(this.minutes >= 0 && this.minutes <= 9){
            return "0"+String.valueOf(this.minutes);
        }else if(this.minutes >= 10 && this.minutes <= 99){
            return String.valueOf(this.minutes);
        }else{
            return "erreur getStringMinutes()";
        }

    }

    /**
     * @return renvoie une string des heures avec le bon nombre de zéro devant
     */
    private String getStirngHeures() {

        if(this.hours >= 0 && this.hours <= 9){
            return "0"+String.valueOf(this.hours);
        }else if(this.hours >= 10 && this.hours <= 99){
            return String.valueOf(this.hours);
        }else{
            return "erreur getStringSecondes()";
        }
    }

    /**
     * @return renvoie la string final dans un format plus ou moins précis celon
     * l'absence ou la présence de secondes, minutes et heures
     */
    public String getString() {

        if(this.secondes == 0){
            return this.millisecondes+" millisecondes";
        }else if(this.minutes == 0){
            return this.getStringSecondes()+", "+ this.getStringMillisecondes()+" s";
        }else if(this.hours == 0){
            return this.getStringMinutes()+" m "+ this.getStringSecondes()+" s";
        }else{
            return this.getStirngHeures()+" h "+ this.getStringMinutes()+" m "
            + this.getStringSecondes()+" s ";
        }
    }
    
}