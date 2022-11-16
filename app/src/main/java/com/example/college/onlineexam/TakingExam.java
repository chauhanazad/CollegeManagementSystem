package com.example.college.onlineexam;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.college.R;
import com.example.college.network.APIclient;
import com.example.college.network.MyRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TakingExam extends AppCompatActivity implements View.OnClickListener ,Runnable{

    Button b1, b2, b3, b4;
    TextView t1,t2;
    Retrofit retrofit;
    APIclient api;
    String exam_id;
    String result_str;
    int totalquestion,cur=1;
    int right=0,wrong=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taking_exam);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Question");
        toolbar.setTitleTextColor(Color.BLACK);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        t1=findViewById(R.id.tv_exam_question_no);
        t2=findViewById(R.id.tv_question);
        b1=findViewById(R.id.op1);
        b2=findViewById(R.id.op2);
        b3=findViewById(R.id.op3);
        b4=findViewById(R.id.op4);

        exam_id=getIntent().getStringExtra("exam_id");
        retrofit=MyRetrofit.getRetrofit();
        api=retrofit.create(APIclient.class);
        Call<String> call=api.getTotalQuestions(exam_id);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response!=null)
                {
                    totalquestion=Integer.parseInt(response.body());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
        request();
    }

    void request()
    {
        retrofit= MyRetrofit.getRetrofit();
        api=retrofit.create(APIclient.class);
        Call<Exams> call=api.getQuestions(exam_id,cur+"");
        call.enqueue(new Callback<Exams>() {
            @Override
            public void onResponse(Call<Exams> call, Response<Exams> response) {
                b1.setBackgroundColor(Color.TRANSPARENT);
                b2.setBackgroundColor(Color.TRANSPARENT);
                b3.setBackgroundColor(Color.TRANSPARENT);
                b4.setBackgroundColor(Color.TRANSPARENT);

                if(response!=null) {
                    Exams question = response.body();
                    t1.setText(cur+": ");
                    t2.setText(question.getQuestion());
                    b1.setText(question.getOp1());
                    b2.setText(question.getOp2());
                    b3.setText(question.getOp3());
                    b4.setText(question.getOp4());

                    result_str = question.getAns();

                    register(TakingExam.this);
                }
            }

            @Override
            public void onFailure(Call<Exams> call, Throwable t) {
                Toast.makeText(TakingExam.this, ""+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void register(View.OnClickListener obj) {
        //here obj means either this or null
        b1.setOnClickListener(obj);
        b2.setOnClickListener(obj);
        b3.setOnClickListener(obj);
        b4.setOnClickListener(obj);
    }

    @Override
    public void onClick(View v) {
        Button b = (Button) v; //view is casted to button so that answer can be checked if right or wrong
        register(null); //all buttons are unregistered so the user cannot click anymore, thus argument = null
        cur++; //as the current question has been answered, so cursor value is incremented
        if (result_str.equals(b.getText())) //if option i.e. button clicked has same answer as stored result
        {
            right++; // if answer is correct then pos is incremented which is total number of correct answers
            b.setBackgroundColor(Color.GREEN);
        } else {
            wrong++;// if answer is incorrect then pos is incremented which is total number of wrong answers
            b.setBackgroundColor(Color.RED);
        }
        Handler h = new Handler();
        h.postDelayed(this, 1000);
        //handler is used so that if the cursor has reached total number of records then resultactivity is shown
        // and if the cursor has not reached total number of records then next question is displayed
    }

    @Override
    public void run() {
        if (cur <= totalquestion) {
            Log.d("run", cur + "");
            request();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(TakingExam.this);
            builder.setTitle("Score");
            builder.setMessage("Your exam score:\nNo. of right answers: "+right+"\nNo. of wrong answers: "+wrong+"\nMarks: "+right+"/"+totalquestion)
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
//                            Call<Exams> call = api.insertOnlineExamMarks(subId,examName,eNo,totalQues,pos);
//                            call.enqueue(new Callback<Exams>() {
//                                @Override
//                                public void onResponse(Call<Exams> call, Response<Exams> response) {
//                                    if(response!=null)
//                                    {
//                                        Toast.makeText(TakingExam.this, "Your score was stored", Toast.LENGTH_SHORT).show();
//                                        finish();
//                                    }
//                                }
//
//                                @Override
//                                public void onFailure(Call<Exams> call, Throwable t) {
//                                    Toast.makeText(TakingExam.this, "Error storing score!", Toast.LENGTH_SHORT).show();
//                                    Log.d("my_errorss",t.toString());
//                                    finish();
//                                }
//                            });
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        if (cur <= totalquestion) {
            AlertDialog.Builder builder = new AlertDialog.Builder(TakingExam.this);
            builder.setMessage("Are you sure want to quit the exam?")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        } else {
            super.onBackPressed();
        }
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}