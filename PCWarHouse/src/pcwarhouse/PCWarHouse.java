/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcwarhouse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */


public class PCWarHouse {

    /**
     * @param args the command line arguments
     */
    public static Connection PGConect = null;
    static ResultSet rs=null;
    static ResultSet rs1=null;
    
public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException
    {
        ResultSetMetaData metaData = rs.getMetaData();
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 2; column <= columnCount; column++)
            columnNames.add(metaData.getColumnName(column));
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next())
        {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 2; columnIndex <= columnCount; columnIndex++)
                vector.add(rs.getObject(columnIndex));
            data.add(vector);
        }
        return new DefaultTableModel(data, columnNames);
    }
public static DefaultComboBoxModel buildComboBoxModel(ResultSet rs,String Column) throws SQLException
    {
        Vector<String> vector = new Vector<String>();
        while (rs.next())
        {
           vector.add(rs.getString(Column));
        }
        return new DefaultComboBoxModel(vector);
    }
    

    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        try
        {
            String user = "postgres";
            String pass = "123";
            Class.forName("org.postgresql.Driver");
            PGConect = DriverManager.getConnection("jdbc:postgresql://localhost:5433/PCWarHouse",user,pass);
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
         /*  Statement st = PGConect.createStatement();
           String sql = "select * from \"Goods\"";
           Statement st1 = PGConect.createStatement();
           String sql1 = "select * from \"Goods\"";
           rs = st.executeQuery(sql);
           rs1 = st1.executeQuery(sql1);*/
        MainForm Form=new MainForm();
        Form.setLocationByPlatform(true);
        Form.setVisible(true);
    }
    
}
