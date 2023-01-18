 package com.example.scheduler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Group;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.scheduler.Modelview.ModelView;
import com.example.scheduler.Modelview.MuttableViewModel;
import com.example.scheduler.RecyclerView.RecyclerAdapter;
import com.example.scheduler.RecyclerView.RecyclerOnclick;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.chip.Chip;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;
import java.util.Date;

 public class MainActivity extends AppCompatActivity implements RecyclerOnclick {

     private ModelView modelView;
     private int counter=0;
     private RecyclerView recyclerView;
     private RecyclerAdapter recyclerAdapter;
     public Date dueDate;
     Priority priority=Priority.LOW;

     private MuttableViewModel muttableViewModel;

     BottomSheetDialog bottomSheetFrag;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        recyclerView=findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        modelView=new ViewModelProvider.AndroidViewModelFactory(MainActivity.this.getApplication()).
                create(ModelView.class);

        muttableViewModel=new ViewModelProvider(this).get(MuttableViewModel.class);

        modelView.getAllTask().observe(this,tasks -> {
            recyclerAdapter=new RecyclerAdapter(tasks,this);
            recyclerView.setAdapter(recyclerAdapter);
        });

        FloatingActionButton fab=findViewById(R.id.Fabtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//           Task task = new Task("task"+counter++,Priority.HIGH, Calendar.getInstance().getTime(),Calendar.getInstance().getTime(),false );
//           ModelView.insert(task);

                showBottomDialog(0);
            }

        });

    }




     private void showBottomDialog (int x) {

        bottomSheetFrag=new BottomSheetDialog(this,R.style.AppBottomSheetDialogTheme);
        View view= LayoutInflater.from(MainActivity.this).inflate(R.layout.bottomsheetfrag,
                (ConstraintLayout)findViewById(R.id.BottomSheet));

         bottomSheetFrag.setContentView(view);

        bottomSheetFrag.show();

        EditText entertask=bottomSheetFrag.findViewById(R.id.enter_task);
        ImageButton calender=bottomSheetFrag.findViewById(R.id.Calenderbtn);
        ImageButton Prioritybtn=bottomSheetFrag.findViewById(R.id.priority_todo_button);
        ImageButton saveTask=bottomSheetFrag.findViewById(R.id.save_Task);
        Group calendergroup=bottomSheetFrag.findViewById(R.id.calendar_group);
        CalendarView calenderview=bottomSheetFrag.findViewById(R.id.calendar_view);
        RadioGroup radiobtn=bottomSheetFrag.findViewById(R.id.radioGroup_priority);
        RadioButton radioHighbtn=bottomSheetFrag.findViewById(R.id.radioButton_high);
        RadioButton radioMedbtn=bottomSheetFrag.findViewById(R.id.radioButton_med);
        RadioButton radioLowbtn=bottomSheetFrag.findViewById(R.id.radioButton_low);
        Chip todaybtn=bottomSheetFrag.findViewById(R.id.today_chip);
        Chip tomorrowbtn=bottomSheetFrag.findViewById(R.id.tomorrow_chip);
        Chip nextWeekBtn=bottomSheetFrag.findViewById(R.id.next_week_chip);
        Calendar caalender=Calendar.getInstance();

         if(x==1){
             if(muttableViewModel.getSelectedData().getValue()!=null) {
                 Task task = muttableViewModel.getSelectedData().getValue();
                 entertask.setText(task.getTask());

             }}else{entertask.getText().clear();}

         calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendergroup.setVisibility(calendergroup.getVisibility()==View.GONE?View.VISIBLE:View.GONE);
                radiobtn.setVisibility(View.GONE);
            }
        });

         Prioritybtn.setOnClickListener(v -> {

             radiobtn.setVisibility(radiobtn.getVisibility()==View.GONE?View.VISIBLE:View.GONE);
             calendergroup.setVisibility(View.GONE);
             radiobtn.setOnCheckedChangeListener((group, checkedId) -> {
                 if(radiobtn.getVisibility()==View.VISIBLE) {
                     if (checkedId == R.id.radioButton_high) {
                         priority = Priority.HIGH;
                     } else if (checkedId == R.id.radioButton_med) {
                         priority = Priority.MEDIUM;
                     } else if (checkedId == R.id.radioButton_low) {
                         priority = Priority.LOW;
                     } else {
                         priority = Priority.LOW;
                     }
                 }
                 else{
                     priority = Priority.LOW;

                 }
             });
             priority=Priority.LOW;

         });


        todaybtn.setOnClickListener(v -> {  caalender.add(Calendar.DAY_OF_YEAR,0);
            dueDate=caalender.getTime();

        });
        tomorrowbtn.setOnClickListener(v -> { caalender.add(Calendar.DAY_OF_YEAR,1);
            dueDate=caalender.getTime();

        });
        nextWeekBtn.setOnClickListener(v -> {caalender.add(Calendar.DAY_OF_YEAR,7);
            dueDate=caalender.getTime();

        });


        calenderview.setOnDateChangeListener((calendarView, year, month, dayOfMonth) -> {
            caalender.clear();
            caalender.set(year,month,dayOfMonth);
            dueDate=caalender.getTime();

        });

        saveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String enterTask=entertask.getText().toString().trim();
                if(!TextUtils.isEmpty(enterTask)&& dueDate != null){
                    Task task=new Task(enterTask,priority,dueDate,Calendar.getInstance().getTime(),false);

                    //if is edit true and clicked from the item view  then only edit and upadate else new task is created
                    if(muttableViewModel.isEdit()&& x==1){
                        Task task1=muttableViewModel.getSelectedData().getValue();
                        dueDate=task1.getDueDate();
                        task1.setTask(enterTask);
                        task1.setDataCreated(Calendar.getInstance().getTime());
                        task1.setPriority(priority);
                        task1.setDueDate(dueDate);
                        modelView.update(task1);
                        Toast toast=Toast.makeText(getApplicationContext(),"Wooho Task Updated...",Toast.LENGTH_SHORT);
                        toast.show();
                        dueDate=null;
                        priority=Priority.LOW;
                     //   muttableViewModel.setEdit(false);

                    }else{
                        Toast toast=Toast.makeText(getApplicationContext(),"Task Created ",Toast.LENGTH_SHORT);
                        toast.show();
                    ModelView.insert(task);
                        priority=Priority.LOW;
                        dueDate=null;
                    }
                    if(bottomSheetFrag.isShowing()){
                        bottomSheetFrag.dismiss();
                    }
                }else{
                    if(dueDate==null &&x==1){
                    Toast toast=Toast.makeText(getApplicationContext(),"Enter updated Due date...",Toast.LENGTH_SHORT);
                    toast.show();
                }else{

                    Toast toast=Toast.makeText(getApplicationContext(),"Empty task.... Failed",Toast.LENGTH_SHORT);
                    toast.show();}
//                    Snackbar snackbar=Snackbar.make(saveTask,"Empty Task .... Failed",Snackbar.LENGTH_SHORT);
//                    snackbar.show();
                }
            }
        });



//     }@Override
//     public void onClick(View view){
//         int id=view.getId();
//         if(id==R.id.today_chip){
//             caalender.add(Calendar.DAY_OF_YEAR,0);
//             dueDate=caalender.getTime();
//         }else if(id==R.id.tomorrow_chip){
//             caalender.add(Calendar.DAY_OF_YEAR,1);
//             dueDate=caalender.getTime();
//         }else if(id==R.id.next_week_chip){
//             caalender.add(Calendar.DAY_OF_YEAR,7);
//             dueDate=caalender.getTime();
//         }


     }

     @Override
     public void onClickScheduler( Task task) {
     muttableViewModel.SelectedItem(task);
     muttableViewModel.setEdit(true);
         Log.d("chho", "onClickScheduler: "+ task.task);
     showBottomDialog(1);
     }

     @Override
     public void onClickRadiobtn(Task task) {
         ModelView.delete(task);
         Log.d("chho", "Deleted task ");
         recyclerAdapter.notifyDataSetChanged();
     }
 }