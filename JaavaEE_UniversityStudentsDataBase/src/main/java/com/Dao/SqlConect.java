package com.Dao;

import java.sql.*;
import java.util.ArrayList;

public class SqlConect {
    String url = "jdbc:mysql://localhost:3306/datamanage";
    String username = "root";
    String password = "root";



    public ArrayList<String> doQuery(String query, String columnName){
        ArrayList<String> items = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded!");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver in the classpath!", e);
        }


        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement st = connection.createStatement();
            String sql = ("Select * from item where title='The Godfather';");
            ResultSet resSet = st.executeQuery(query);
            while(resSet.next()){
                items.add(resSet.getString(columnName));

            }


        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }


        return items;
    }


    public ArrayList<String> getDatabaseStringItem(String tableName, String columnName){
        ArrayList<String> items = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded!");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver in the classpath!", e);
        }


        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement st = connection.createStatement();
            ResultSet resSet = st.executeQuery("Select * from " + tableName);
            while(resSet.next()){
                items.add(resSet.getString(columnName));

            }


        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }


        return items;
    }


    public void addStudent(int id, String name, int groupId, int specialityId, String performance){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded!");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver in the classpath!", e);
        }

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement ps = connection.createStatement();
            String q = String.format("insert into student(ID,Name,GroupID,SpecialityID,performance) VALUES(%d,'%s',%d,%d,'%s')",id,name,groupId,specialityId,performance);
            ps.execute(q);
            ps.close();

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }



    public void deleteStudent(int id){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded!");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver in the classpath!", e);
        }

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement st = connection.createStatement();
            String q = "DELETE FROM student WHERE id=?";
            PreparedStatement pr = connection.prepareStatement(q);
            pr.setInt(1,id);
            pr.executeUpdate();

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }


    public void updateStudent(int id, String name, int groupId, int specialityId, String performance){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded!");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver in the classpath!", e);
        }

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement ps = connection.createStatement();
            String q = String.format("UPDATE student SET name = '%s' , GroupID = %d, SpecialityID = %d, performance='%s' WHERE id = %d; ",name,groupId,specialityId,performance,id);
            ps.execute(q);
            ps.close();

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }



}
