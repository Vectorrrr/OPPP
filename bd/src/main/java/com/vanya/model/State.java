package com.vanya.model;

import java.util.Date;

/**
 * @author Ivan Gladush
 * @since 07.11.16.
 */
public class State {
    private int index;
    //угол наклона от 0 360
    private int angel;
    //время старта открытия жалюзей
    private Date startDate;
    //время окончания периода
    private Date finishDate;
    // занятая площадь
    private int bloudedSquare;

    public State(int angel, Date startDate, Date finishDate, int bloudedSquare) {
        this.angel = angel;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.bloudedSquare = bloudedSquare;
    }
    public  State(){

    }
    public int getAngel() {
        return angel;
    }

    public void setAngel(int angel) {
        this.angel = angel;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public int getBloudedSquare() {
        return bloudedSquare;
    }

    public void setBloudedSquare(int bloudedSquare) {
        this.bloudedSquare = bloudedSquare;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "State{" +
                "index=" + index +
                ", angel=" + angel +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                ", bloudedSquare=" + bloudedSquare +
                '}';
    }

    public static boolean notNull(State state) {
        return !(state == null || state.getStartDate() == null || state.getFinishDate() == null);

    }
}
