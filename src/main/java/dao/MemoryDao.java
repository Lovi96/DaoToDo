package dao;

import util.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lovi on 2017.05.11..
 */
public class MemoryDao implements TodoDao {

    public static final MemoryDao INSTANCE = new MemoryDao();
    private List<Task> database = new ArrayList<>();
    private static Integer id = 1;

    public List<Task> getDatabase() {
        return database;
    }

    public void setDatabase(List<Task> database) {
        this.database = database;
    }

    public static Integer getId() {
        return id;
    }

    public static void setId(Integer id) {
        MemoryDao.id = id;
    }

    private MemoryDao(){}


    @Override
    public Task returnTaskbyId(Integer id) {
        return getDatabase()
            .stream()
            .filter(Task -> Task.getId().equals(id))
            .findAny()
            .orElse(null);
    }

    @Override
    public List<Task> returnAll() {
        return getDatabase();
    }

    @Override
    public List<Task> returnDone(String user) {
        return getDatabase()
            .stream()
            .filter(Task ->Task.isCompleted()&&Task.getUser()==user )
            .collect(Collectors.toList());
    }

    @Override
    public List<Task> returnInProgress(String user) {
        return getDatabase()
            .stream()
            .filter(Task -> !Task.isCompleted()&&Task.getUser()==user)
            .collect(Collectors.toList());
    }

    @Override
    public void addTask(String name, String user) {
        getDatabase().add(new Task(getDatabase().size()+1,name,false,user));
    }

    @Override
    public void toggleStatus(Integer id) {
        for(Task task : getDatabase()){
            if(task.getId()==id){
                task.setCompletion(!task.isCompleted());
            }
        }
    }

    @Override
    public void deleteTask(Integer id) {
        List<Task> database = getDatabase();
        for(Task task :database){
            if(task.getId()==id){
                database.remove(task);
                setDatabase(database);
            }
        }
    }
}
