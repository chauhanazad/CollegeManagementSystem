package com.example.college.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.college.faq.FaqActivity;
import com.example.college.R;
import com.example.college.assignment.viewAssignmentsList;
import com.example.college.event.EventList;
import com.example.college.faculty.viewFacultyList;
import com.example.college.faq.ViewFaqList;
import com.example.college.library.ViewIssueList;
import com.example.college.oldpapers.OldPaperCategory;
import com.example.college.onlineexam.ViewOnlineExamList;
import com.example.college.subject.SubjectSemCAt;

public class HomeFragment extends Fragment {

    Intent intent;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        CardView event = v.findViewById(R.id.home_event);

        CardView assignment = v.findViewById(R.id.home_assignment);
        CardView onlinExam=v.findViewById(R.id.onlinexamcard);
//        CardView faq=v.findViewById(R.id.home_faq);
        CardView library=v.findViewById(R.id.home_library);
        CardView faculty=v.findViewById(R.id.faculty_card);
        CardView subject=v.findViewById(R.id.subject_card);
        CardView oldpaper=v.findViewById(R.id.home_oldpapers);

        oldpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), OldPaperCategory.class);
                startActivity(intent);
            }
        });


        subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), SubjectSemCAt.class);
                startActivity(intent);
            }
        });

        faculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), viewFacultyList.class);
                startActivity(intent);
            }
        });


        library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), ViewIssueList.class);
                startActivity(intent);
            }
        });

//        faq.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                intent = new Intent(getActivity(), ViewFaqList.class);
//                startActivity(intent);
//            }
//        });

        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), EventList.class);
                startActivity(intent);
            }
        });



        assignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), viewAssignmentsList.class);
                startActivity(intent);
            }
        });

        onlinExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), ViewOnlineExamList.class);
                startActivity(intent);
            }
        });

        return v;
    }
}