package com.idknoo.mispi3help.dbwork;

import com.idknoo.mispi3help.values.Values;

import javax.faces.context.FacesContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBWorkerBean implements DBWorking {

    private Connector connector = new Connector();
    private Connection connection;

    public DBWorkerBean() {
        try {
            connection = connector.connect();
        } catch (SQLException throwables) {
            connection = null;
        }
    }

    @Override
    public void addValues(Values values) throws SQLException {
        String select = "INSERT INTO HITS (x, y, r, hit, time) VALUES (?, ?, ?, ?, ?)";
        FacesContext facesContext = FacesContext.getCurrentInstance();
        System.out.println("lllll" + values.getX());
//        String id = facesContext.getExternalContext().getSessionId(false);
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.setDouble(1, values.getX());
            preparedStatement.setDouble(2, values.getY());
            preparedStatement.setDouble(3, values.getR());
            preparedStatement.setBoolean(4, true);
            preparedStatement.setLong(5, values.getCreateDate().getTime());
            System.out.println(preparedStatement.executeUpdate());
            preparedStatement.close();
            connection.commit();
            if (preparedStatement.executeUpdate() != 0) return;
        } catch (SQLException e) {
            System.out.println("Ошибка при добавлении бина в базу" + e.getMessage());
        } catch (ClassCastException e) {
            System.out.println("ClassCastException при добавлении в базу");
        }
        return;
    }

//        PreparedStatement preparedStatement = (PreparedStatement) connection.createStatement();
//        preparedStatement.executeUpdate(stringBuilder.toString());
//        preparedStatement.close();
//        connection.commit();
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("INSERT INTO HITS (x,y,r,time) VALUES (");
//        stringBuilder.append(values.getX());
//        stringBuilder.append(",");
//        stringBuilder.append(values.getY());
//        stringBuilder.append(",");
//        stringBuilder.append(values.getR());
//        stringBuilder.append(",");
//        stringBuilder.append(values.getCreateDate().getTime());
//        stringBuilder.append(")");
//
//        PreparedStatement preparedStatement = (PreparedStatement) connection.createStatement();
//        preparedStatement.executeUpdate(stringBuilder.toString());
//        preparedStatement.close();
//        connection.commit();




//    String select = "INSERT INTO HITS (x, y, r, time) VALUES (?, ?, ?, ?, ?)";
//    FacesContext facesContext = FacesContext.getCurrentInstance();
//    String id = facesContext.getExternalContext().getSessionId(false);
//        try {
//        PreparedStatement preparedStatement = connection.prepareStatement(select);
//        preparedStatement.setDouble(1, values.getX());
//        preparedStatement.setDouble(2, values.getY());
//        preparedStatement.setDouble(3, values.getR());
//
//        preparedStatement.setLong(4, values.getCreateDate().getTime());
//        if (preparedStatement.executeUpdate() != 0) return ;
//    } catch (SQLException e) {
//        System.out.println("Ошибка при добавлении бина в базу" + e.getMessage());
//    } catch (ClassCastException e) {
//        System.out.println("ClassCastException при добавлении в базу");
//    }
//        connection.commit();
    @Override
    public List<Values> getLastValues() throws Exception {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM HITS");
        List<Values> valuesList = new ArrayList<Values>();
        while (resultSet.next()) {
            valuesList.add(new Values(resultSet.getDouble("X"), resultSet.getDouble("Y"), resultSet.getDouble("R"), new Date(resultSet.getLong("TIME"))));
        }
        return valuesList;
    }

    @Override
    public boolean isConnectionValid() {
        try {
            if (connection != null && connection.isValid(0)) {
                return true;
            } else {
                if (connection != null) {
                    connection.close();
                }
                connection = connector.connect();
                return connection.isValid(0);
            }
        } catch (SQLException throwables) {
            return false;
        }
    }

    @Override
    public void clearLastRequests() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("TRUNCATE TABLE HITS");
        statement.close();
        connection.commit();
    }
}
