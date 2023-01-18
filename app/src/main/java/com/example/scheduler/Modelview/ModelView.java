package com.example.scheduler.Modelview;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.scheduler.Data.SchedulerRepo;
import com.example.scheduler.Task;

import java.util.List;
import java.util.Objects;

public class ModelView extends AndroidViewModel {
    public static SchedulerRepo Repo;
    public final LiveData<List<Task>> allTask;

    public ModelView(@NonNull Application application){
        super(application);
        Repo=new SchedulerRepo(application);
        allTask=Repo.getAllTask();
    }

    public LiveData<List<Task>>getAllTask(){
        return allTask;
    }
    public static void insert(Task task){
        Repo.insert(task);
    }
    public LiveData<Task>get(long id){return Repo.get(id);}
    public static void update(Task task){
        Repo.update(task);
    }
    public static void delete(Task task){
        Repo.delete(task);
    }

}
