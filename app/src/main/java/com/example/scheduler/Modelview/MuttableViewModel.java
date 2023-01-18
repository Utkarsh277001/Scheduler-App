package com.example.scheduler.Modelview;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.scheduler.Task;

public class MuttableViewModel extends ViewModel {
    private final MutableLiveData<Task> selectedItem=new MutableLiveData<>();
    boolean isEdit;

    public void SelectedItem(Task task){
        selectedItem.setValue(task);
    }

    public LiveData<Task> getSelectedData(){
        return selectedItem;
    }

    public boolean isEdit() {
        return isEdit;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
    }
}
