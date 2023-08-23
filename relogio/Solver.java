package relogio;

import java.util.*;

class Time {

    private int hour;
    private int minute;
    private int second;
    

    public Time(int hour, int minute, int second) {
        this.hour = 0;
        this.minute = 0;
        this.second = 0;
        setHour(hour);
        setMinute(minute);
        setSecond(second);
    }

    public int getHour() {
        return hour;
    }
    public void setHour(int hour) {
        if(hour < 00 || hour > 23) {
            System.out.println("fail: hora invalida");
            return;
        }
        this.hour = hour;
    }
    public int getMinute() {
        return minute;
    }
    public void setMinute(int minute) {
        if (minute < 0 || minute > 59) {
            System.out.println("fail: minuto invalido");
            return;
        }
        this.minute = minute;
    }
    public int getSecond() {
        return second;
    }
    public void setSecond(int second) {
        if (second < 0 || second > 59) {
            System.out.println("fail: segundo invalido");
            return;
        }
        this.second = second;
    }

    public void nextSecond() {
        this.second++;
        if(this.second >= 60){
            this.second -= 60;
            this.minute++;
        }
        if(this.minute >= 60){
            this.minute -= 60;
            this.hour++;
        }
        if(this.hour >= 24){
            this.hour -= 24;
        }
    }

    @Override
    public String toString() {
        String saida = "";
        saida += (this.hour < 10) ? "0" + this.hour : this.hour;
        saida += ":";
        saida += (this.minute < 10) ? "0" + this.minute : this.minute;
        saida += ":";
        saida += (this.second < 10) ? "0" + this.second : this.second;
        return saida;
    }

    

}

public class Solver {
    public static void main(String[] a) {
        Time time = new Time(0, 0, 0);
        
        while (true) {
            var line = input();
            write("$" + line);
            var args = line.split(" ");

            if      (args[0].equals("end"))   { 
                break; 
            }
            else if (args[0].equals("show"))  { 
                System.out.println(time); 
            }
            else if (args[0].equals("next"))  { 
                time.nextSecond(); 
            }
            else if (args[0].equals("set")) {
                time.setHour((int)number(args[1]));
                time.setMinute((int)number(args[2]));
                time.setSecond((int)number(args[3]));
            }
            else if (args[0].equals("init")) {
                time = new Time((int)number(args[1]), (int)number(args[2]), (int)number(args[3]));
            }
            else { 
                write("fail: comando invalido"); 
            }
        }
    }

    private static Scanner scanner = new Scanner(System.in);
    private static String  input()              { return scanner.nextLine(); }
    private static double  number(String value) { return Double.parseDouble(value); }
    private static void    write(String value)  { System.out.println(value); }
}