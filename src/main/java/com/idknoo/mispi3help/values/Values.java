package com.idknoo.mispi3help.values;

import java.util.Date;

public class Values implements Comparable{
    private double x;
    private double y;
    private double r;
    private boolean isCatch ;
    private Date createDate;

    public Values(double x, double y, double r, boolean isCatch, Date date) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.isCatch = isCatch;
        this.createDate = date;
    }

    public Values(double x, double y, double r, Date date) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.createDate = date;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public boolean isCatch() {
        return isCatch;
    }

    public String getStringCatch() {
        return String.valueOf(isCatch);
    }

    public void setCatch(boolean aCatch) {
        isCatch = aCatch;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public int compareTo(Object o) {
        if(this.createDate.getTime() < ((Values) o).getCreateDate().getTime()){
            return 1;
        }else if(this.createDate.getTime() > ((Values) o).getCreateDate().getTime()){
            return -1;
        }else {
            return 0;
        }
    }
}
