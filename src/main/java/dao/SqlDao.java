package dao;

import util.Task;

import java.util.List;

/**
 * Created by lovi on 2017.05.11..
 */
public class SqlDao implements TodoDao{
    @Override
    public Task returnTaskbyId(Integer id) {
        return null;
    }

    @Override
    public List<Task> returnAll(String user) {
        return null;
    }

    @Override
    public List<Task> returnDone(String user) {
        return null;
    }

    @Override
    public List<Task> returnInProgress(String user) {
        return null;
    }

    @Override
    public void addTask(String name, String user) {

    }

    @Override
    public void toggleStatus(Integer id) {

    }

    @Override
    public void deleteTask(Integer id) {

    }
}
