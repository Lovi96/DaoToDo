package dao;
import util.Task;

import java.util.List;

/**
 * Created by lovi on 2017.05.11..
 */
public interface TodoDao{
    Task returnTaskbyId(Integer id);
    List<Task> returnAll(String user);
    List<Task> returnDone(String user);
    List<Task> returnInProgress(String user);
    void addTask(String name,String user);
    void toggleStatus(Integer id);
    void deleteTask(Integer id);

}
