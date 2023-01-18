package com.example.scheduler.Data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.scheduler.Task;
import com.example.scheduler.Util.TaskRoomDatabase;

import java.util.List;

public class SchedulerRepo {
    private final TaskDao taskDao;
    private final LiveData<List<Task>>AllTask;

    public SchedulerRepo(Application application) {
        TaskRoomDatabase database =TaskRoomDatabase.getDatabase(application);
        taskDao = database.taskDao();
        AllTask = taskDao.getTasks();
    }

    public LiveData<List<Task>> getAllTask(){
        return AllTask;
    }
    public void insert(Task task){
        TaskRoomDatabase.databaseWriterExecutor.execute(()->taskDao.insertTask(task));
    }

    public LiveData<Task> get(long id){return taskDao.get(id);}

    public void update(Task task){
        TaskRoomDatabase.databaseWriterExecutor.execute(()->taskDao.update(task));
    }
    public void delete(Task task){
        TaskRoomDatabase.databaseWriterExecutor.execute(()->taskDao.delete(task));
    }
}
