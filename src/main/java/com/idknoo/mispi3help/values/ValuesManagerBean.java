package com.idknoo.mispi3help.values;

import com.idknoo.mispi3help.dbwork.DBWorking;
import com.idknoo.mispi3help.mbeans.AverageInterval;
import com.idknoo.mispi3help.mbeans.AverageIntervalMBean;
import com.idknoo.mispi3help.mbeans.ShotCounter;
import com.idknoo.mispi3help.mbeans.ShotCounterMBean;

import javax.annotation.PostConstruct;
import javax.management.JMException;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.sql.SQLException;
import java.util.*;

public class ValuesManagerBean implements ValuesManaging {
    private List<Values> valuesList = Collections.synchronizedList(new ArrayList<Values>());
    private List<Values> notSynchronizedValues = Collections.synchronizedList(new ArrayList<Values>());
    private DBWorking dbWorking;
    private boolean successStartSynchronise;

    private final ShotCounterMBean shotCounterMBean = new ShotCounter();
    private final AverageIntervalMBean averageIntervalMBean = new AverageInterval();

//    ValuesManagerBean() {
//        initMBeans();
//    }

    @PostConstruct
    public void startLoading() {
        initMBeans();
        addLastRequests();
    }


    public boolean addLastRequests() {
        if (dbWorking.isConnectionValid()) {
            try {
                List<Values> lastValues = dbWorking.getLastValues();
                valuesList.addAll(lastValues);
                Collections.sort(valuesList);
                successStartSynchronise = true;
                return true;
            } catch (Exception e) {
                successStartSynchronise = false;
                return false;
            }
        } else {
            successStartSynchronise = false;
            return false;
        }
    }


    @Override
    public void addValue(Values values) {
        notSynchronizedValues.add(values);
        shotCounterMBean.addShot(values);
    }

    @Override
    public List<Values> getAllValues() {
        List<Values> valuesList = new ArrayList<Values>(this.valuesList);
        valuesList.addAll(notSynchronizedValues);
        Collections.sort(valuesList);
        return valuesList;
    }

    @Override
    public boolean clearLastRequests() {
        valuesList.clear();
        notSynchronizedValues.clear();
        try {
            dbWorking.clearLastRequests();
        } catch (SQLException throwables) {
            return false;
        }
        return true;
    }

    @Override
    public synchronized boolean synchronize() {
        if (dbWorking.isConnectionValid()) {
            if (!successStartSynchronise) {
                boolean success = addLastRequests();
                if (success) {
                    successStartSynchronise = true;
                }
            }
            Iterator iterator = notSynchronizedValues.iterator();
            while (iterator.hasNext()) {
                Values values = (Values) iterator.next();
                try {
                    dbWorking.addValues(values);
                    valuesList.add(values);
                    iterator.remove();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public DBWorking getDbWorking() {
        return dbWorking;
    }

    public void setDbWorking(DBWorking dbWorking) {
        this.dbWorking = dbWorking;
    }

    public List<Values> getValuesList() {
        return valuesList;
    }

    public void setValuesList(List<Values> valuesList) {
        this.valuesList = valuesList;
    }

    private void initMBeans() {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        try {
            mbs.registerMBean(shotCounterMBean,
                    new ObjectName("com.idknoo.mispi3help.mbeans:type=ShotCounter"));
            mbs.registerMBean(averageIntervalMBean,
                    new ObjectName("com.idknoo.mispi3help.mbeans:type=AverageInterval"));
        } catch (JMException e) {
            e.printStackTrace();
        }
    }
}