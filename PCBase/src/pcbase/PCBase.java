/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcbase;

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
public class PCBase {

    /**
     * @param args the command line arguments
     */static ResultSet rs=null;static ResultSet rs1=null;
     
        public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException
    {
        ResultSetMetaData metaData = rs.getMetaData();
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++)
            columnNames.add(metaData.getColumnName(column));
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next())
        {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++)
                vector.add(rs.getObject(columnIndex));
            data.add(vector);
        }
        return new DefaultTableModel(data, columnNames);
    }
public static DefaultComboBoxModel buildComboBoxModel(ResultSet rs) throws SQLException
    {String [] name=new String[10];
        Vector<String> vector = new Vector<String>();
        int i=0;
        while (rs.next())
        {
           //int id = rs.getInt("id");
            //name[i++] = rs.getString("name"); 
            //vector.add(rs.getString("name"));
        }
        return new DefaultComboBoxModel(vector);
    }
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        Connection dbh = null;
               try
        {
            String user = "postgres";
            String pass = "123";
            Class.forName("org.postgresql.Driver");
            dbh = DriverManager.getConnection("jdbc:postgresql://localhost:5433/PCWarHouse", user, pass);
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
            Statement st = dbh.createStatement();
            String sql = "select \"ID\" as \"Код\",\"FullName\" as \"ФИО\",\"Post\" as \"Должность\" from \"Responsible\"";
            Statement st1 = dbh.createStatement();
            String sql1 = "select * from \"Responsible\"";
           rs = st.executeQuery(sql);
           rs1 = st1.executeQuery(sql1);
PCBaseForm Form=new PCBaseForm();
Form.setVisible(true);
    }
    
}
