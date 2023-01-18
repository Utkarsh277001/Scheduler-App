package com.example.scheduler;



import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName ="task_table")
public class Task {
    @ColumnInfo(name="task_id")
    @PrimaryKey(autoGenerate = true)
    public long taskid;
    public String task;
    public Priority priority;

    @ColumnInfo(name="due_date")
    public Date dueDate;

    @ColumnInfo(name="create_at")
    public Date dataCreated;

    @ColumnInfo(name="is_done")
    public boolean isDone;


    public Task(String task, Priority priority, Date dueDate, Date dataCreated, boolean isDone) {
        this.task = task;
        this.priority = priority;
        this.dueDate = dueDate;
        this.dataCreated = dataCreated;
        this.isDone = isDone;
    }

    public long getTaskid() {
        return taskid;
    }

    public void setTaskid(long taskid) {
        this.taskid = taskid;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getDataCreated() {
        return dataCreated;
    }

    public void setDataCreated(Date dataCreated) {
        this.dataCreated = dataCreated;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskid=" + taskid +
                ", task='" + task + '\'' +
                ", priority=" + priority +
                ", dueDate=" + dueDate +
                ", dataCreated=" + dataCreated +
                ", isDone=" + isDone +
                '}';
    }
}
