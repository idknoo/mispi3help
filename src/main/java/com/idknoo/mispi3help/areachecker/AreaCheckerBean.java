package com.idknoo.mispi3help.areachecker;

import com.idknoo.mispi3help.values.Values;

public class AreaCheckerBean implements AreaChecking {
    @Override
    public boolean checkArea(Values values) {
        if (values.getX() < 0 && values.getY() > 0) {
            return false;
        } else if (values.getX() <= 0 && values.getY() <= 0) {
            if (values.getX() >= -values.getR() && values.getY() >= -values.getR()/2) {
                return true;
            } else {
                return false;
            }
        } else if (values.getX() >= 0 && values.getY() <= 0) {
            if (Math.pow(Math.abs(values.getX()), 2) + Math.pow(Math.abs(values.getY()), 2) <= Math.pow(values.getR()/2, 2)) {
                return true;
            } else {
                return false;
            }
        } else if (values.getX() >= 0 && values.getY() >= 0) {
            if (values.getX() + values.getY() - values.getR()  <= 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
