package com.idknoo.mispi3help.values;

import java.util.List;

public interface ValuesManaging {
    public boolean synchronize();
    public void addValue(Values values);
    public List<Values> getAllValues();
    public boolean clearLastRequests();
}
