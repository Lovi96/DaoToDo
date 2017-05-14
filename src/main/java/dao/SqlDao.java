package dao;

import util.ConnectionUtil;
import util.Task;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lovi on 2017.05.11..
 */
public class SqlDao implements TodoDao{
    public static final SqlDao INSTANCE = new SqlDao();
    public static Connection connection = ConnectionUtil.getConnection(ConnectionUtil.DatabaseName.todos);
    private static Integer idOnClient = 1;
    private SqlDao(){}


    @Override
    public Task returnTaskbyId(Integer id) {
        return null;
    }

    @Override
    public List<Task> returnAll(String user) {
        String query = "SELECT * FROM todo WHERE owner = '"+user+"';";
        List<Task> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
            String todo_name = resultSet.getString("todo_name");
            Integer id = resultSet.getInt("id");
            String userName = resultSet.getString("owner");
            boolean completion = true;
            if(resultSet.getInt("completion")==0){
                completion = false;
            }
            result.add(new Task(id,todo_name,completion,userName));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Task> returnDoneTasks(String user) {
        String query = "SELECT * FROM todo WHERE owner = '"+user+"' AND completion = "+"1"+";";
        List<Task> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                String todo_name = resultSet.getString("todo_name");
                Integer id = resultSet.getInt("id");
                String userName = resultSet.getString("owner");
                result.add(new Task(id,todo_name,true,userName));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Task> returnInProgress(String user){
        String query = "SELECT * FROM todo WHERE owner = '"+user+"' AND completion = "+"0"+";";
        List<Task> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                String todo_name = resultSet.getString("todo_name");
                Integer id = resultSet.getInt("id");
                String userName = resultSet.getString("owner");
                result.add(new Task(id,todo_name,false,userName));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void addTask(String name, String user) {
        String query = "INSERT INTO todo (todo_name, id, owner, completion) VALUES (\"" + name + "\","+ idOnClient++ +",\"" + user + "\","+0+")";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void toggleStatus(Integer id) {
        String toggleStatus = "UPDATE todo SET completion = !completion WHERE id =" + id + ";";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(toggleStatus);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTask(Integer id) {
        String deleteStatement = "DELETE FROM todo WHERE id =" + id + ";";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(deleteStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
