package com.idknoo.mispi3help.mbeans;


import com.idknoo.mispi3help.Shot;
import com.idknoo.mispi3help.values.Values;

/*
MBean, считающий общее число установленных пользователем точек, а также число точек, попадающих в область.
В случае, если пользователь совершил 4 "промаха" подряд, разработанный MBean должен
отправлять оповещение об этом событии.
 */
public interface ShotCounterMBean {
    void addShot(Values values);

    long getCountOfAllShots();

    //long getCountOfSuccessfulShots();
}