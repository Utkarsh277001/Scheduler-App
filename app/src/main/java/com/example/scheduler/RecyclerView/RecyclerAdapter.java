package com.example.scheduler.RecyclerView;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.TextViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scheduler.Dateconverter;
import com.example.scheduler.Modelview.ModelView;
import com.example.scheduler.R;
import com.example.scheduler.Task;
import com.google.android.material.chip.Chip;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    private final List<Task>tasklist;
    private final RecyclerOnclick onrecyclerOnclick;

    public RecyclerAdapter(List<Task> tasklist,RecyclerOnclick onrecyclerOnclick) {
        this.tasklist = tasklist;
        this.onrecyclerOnclick = onrecyclerOnclick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
           Task task=tasklist.get(position);
           String formatted= Dateconverter.formatDate(task.getDueDate());

        ColorStateList colorStateList=new ColorStateList(new int[][]{
                new int[]{-android.R.attr.state_enabled},
                new int[]{android.R.attr.state_enabled}
        },new int[]{
                Color.LTGRAY,//state disabled
                Dateconverter.PriorityColor(task)
        });

           holder.taskView.setText(task.getTask());
           holder.chipp.setText(formatted);
           holder.chipp.setTextColor(Dateconverter.PriorityColor(task));
           holder.chipp.setChipIconTint(colorStateList);
           holder.radioBtn.setButtonTintList(colorStateList);

    }

    @Override
    public int getItemCount() {
        return tasklist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public AppCompatRadioButton radioBtn;
        public AppCompatTextView taskView;
        public Chip chipp;
        RecyclerOnclick recyclerOnclick;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            radioBtn=itemView.findViewById(R.id.radioButton);
            taskView=itemView.findViewById(R.id.textView);
            chipp=itemView.findViewById(R.id.chip);
            this.recyclerOnclick=onrecyclerOnclick;
            radioBtn.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Task currTask=tasklist.get(getAdapterPosition());
            int id = v.getId();
            if(id==R.id.SchedulerRow){
                recyclerOnclick.onClickScheduler(currTask);
            }else if (id==R.id.radioButton){
                recyclerOnclick.onClickRadiobtn(currTask);
            }

        }
    }
}
