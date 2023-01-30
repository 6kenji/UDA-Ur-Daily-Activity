package mz.co.uda_urdailyactivities.Objects;

import java.util.Date;

public class User_Activity {

    private Date initial_date, end_date;
    private String name;
    private int periodicity;

    public User_Activity(Date initial_date, Date end_date, String name, int periodicity) {
        this.initial_date = initial_date;
        this.end_date = end_date;
        this.name = name;
        this.periodicity = periodicity;
    }

    public Date getInitial_date() {
        return initial_date;
    }

    public void setInitial_date(Date initial_date) {
        this.initial_date = initial_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(int periodicity) {
        this.periodicity = periodicity;
    }
}
