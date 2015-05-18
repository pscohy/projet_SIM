package patientmanager;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * Implementation of the table model for a ResultSet
 *
 * @author Arnaud Schenkel
 */
public class ResultSetTableModel extends AbstractTableModel {

    private ResultSetMetaData metaData;
    private ResultSet resultSet;
    private boolean validData;
    private int rowsNumber;
    
    public ResultSetTableModel() {
        rowsNumber = 0;
        validData = false;
    }
    
    public int getRowCount() {
        return rowsNumber;
    }

    public int getColumnCount() {
        if (!validData) 
            return 0;
        
        try {
            return metaData.getColumnCount(); 
        }
        catch (SQLException ex) {
            Logger.getLogger(ResultSetTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

    public String getColumnName(int columnIndex) {
        if (!validData)
            return "";

        try {
            return metaData.getColumnName(columnIndex + 1);  
        }
        catch (SQLException ex) {
            Logger.getLogger(ResultSetTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "";
    }
    
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (!validData) 
            return "";

        try {
            resultSet.absolute(rowIndex + 1);
            return resultSet.getObject(columnIndex + 1);
        }
        catch (SQLException ex) {
            Logger.getLogger(ResultSetTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "";
    }

    /**
     * Set a ResultSet and display it on a JTable.
     *
     * @param resultSet
     * @throws SQLException
     */
    public void setResultSet(ResultSet resultSet) throws SQLException {

        // Get the ResultSet MetaData to set the columHeader
        validData = (resultSet != null || resultSet.first());
        this.resultSet = resultSet;
        metaData = resultSet.getMetaData();
        
        // Determine number of rows in ResultSet
        if (!validData)
        {
            rowsNumber = 0;
            throw new UnknownError("Cannot access to the resultset data");
        }
        else {
            resultSet.last(); // Move to last row
            rowsNumber = resultSet.getRow(); // Get row number
        }
        
        // Notify that the content of the table changed
        fireTableChanged(null);
    }

}
