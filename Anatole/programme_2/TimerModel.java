import java.lang.*;

public class TimerModel {

    private final static long CYCLE = 1000000L; //1 000 000  nanosecondes = 1 millisecondes
    private long newStep;
    private int millisecondes;
    private int secondes;
    private int minutes;
    private int hours;


    public void setTime(){

        if(System.nanoTime() > newStep){
            newStep = System.nanoTime() + CYCLE;
            increaseMillisecondes();
            increaseSecondes();
            increaseMinutes();
            increaseHours();
        }

    }
  
    private void increaseMillisecondes(){
        millisecondes += 1;
    }

    private void increaseSecondes(){

        if( millisecondes == 999 ){
            millisecondes = 0;
            secondes += 1;
        }

    }

    private void increaseMinutes(){

        if( secondes == 60){
            secondes = 0;
            minutes += 1;
        }

    }

    private void increaseHours(){

        if( minutes == 60){
            minutes = 0;
            hours += 1;
        }

    }


    public int getmillisecondes() {
        return millisecondes;
    }

    public int getSecondes() {
        return secondes;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getHeures() {
        return hours;
    }

    public String getStringmillisecondes() {

        if(millisecondes >= 0 && millisecondes <= 9){
            return "00"+String.valueOf(millisecondes);
        }else if(millisecondes >= 10 && millisecondes <= 99){
            return "0"+String.valueOf(millisecondes);
        }else if(millisecondes >= 100 && millisecondes <= 999 ){
            return String.valueOf(millisecondes);
        }else{
            return "erreur methode getStringmillisecondes() ";
        }
    }

    public String getStringSecondes() {

        if(secondes >= 0 && secondes <= 9){
            return "0"+String.valueOf(secondes);
        }else if(secondes >= 10 && secondes <= 99){
            return String.valueOf(secondes);
        }else{
            return "erreur getStringSecondes()";
        }
    }

    public String getStringMinutes() {

        if(minutes >= 0 && minutes <= 9){
            return "0"+String.valueOf(minutes);
        }else if(minutes >= 10 && minutes <= 99){
            return String.valueOf(minutes);
        }else{
            return "erreur getStringMinutes()";
        }

    }

    public String getStirngHeures() {

        if(hours >= 0 && hours <= 9){
            return "0"+String.valueOf(hours);
        }else if(hours >= 10 && hours <= 99){
            return String.valueOf(hours);
        }else{
            return "erreur getStringSecondes()";
        }
    }

}