package com.idknoo.mispi3help.mbeans;

/*
MBean, определяющий средний интервал между кликами пользователя по координатной плоскости.
 */
public interface AverageIntervalMBean {
    void update(long nextInterval);

    double getAverageInterval();
}
