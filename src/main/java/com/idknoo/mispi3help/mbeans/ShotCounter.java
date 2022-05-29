package com.idknoo.mispi3help.mbeans;

import com.idknoo.mispi3help.values.Values;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

public class ShotCounter extends NotificationBroadcasterSupport implements ShotCounterMBean {
    private long countOfAllShots = 0;
    //    private long countOfSuccessfulShots = 0;
    //private long missesInRow = 0;

    @Override
    public void addShot(Values values) {
        countOfAllShots++;
        if (values.isCatch()) {
            countOfAllShots++;
//            missesInRow = 0;
        } else {
            countOfAllShots++;
            if (countOfAllShots >= 5) {
                Notification notification = new Notification(
                        "fiveShotsCompleted", this, countOfAllShots,
                        System.currentTimeMillis(), "5 shots were completed"
                );
                this.sendNotification(notification);
            }
        }
    }

    @Override
    public long getCountOfAllShots() {
        return countOfAllShots;

    }
}

//    @Override
//    public long getCountOfSuccessfulShots() {
//        return countOfSuccessfulShots;
//    }
//}
