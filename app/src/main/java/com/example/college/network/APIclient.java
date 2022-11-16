package com.example.college.network;



import com.example.college.assignment.assignment;
import com.example.college.event.Events;
import com.example.college.faculty.faculty_details;
import com.example.college.faq.Faq;
import com.example.college.library.Library;
import com.example.college.notice.Notice_Details;
import com.example.college.oldpapers.OldPaper;
import com.example.college.onlineexam.Exam_Detail;
import com.example.college.onlineexam.Exams;
import com.example.college.student.student_details;
import com.example.college.subject.Subjects;
import com.example.college.ui.gallery.Images;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface APIclient {




    @FormUrlEncoded
    @POST("student.php")
    Call<student_details> login(@Field("email") String username, @Field("password") String password);

    @GET("get_event.php")
    Call<List<Events>> getEvent();

    @FormUrlEncoded
    @POST("get_assignments_student.php")
    Call<List<assignment>> getAssignmentsForStudent(@Field("email") String email);


    //online Exam
    @FormUrlEncoded
    @POST("getExamsStudent.php")
    Call<List<Exam_Detail>> getExamsForStudent(@Field("email") String email);

    @FormUrlEncoded
    @POST("getquestions.php")
    Call<Exams> getQuestions(@Field("exam_id") String exam_id,@Field("question_no") String question_no);

    @FormUrlEncoded
    @POST("gettotalquestions.php")
    Call<String> getTotalQuestions(@Field("exam_id") String exam_id);


    //Settings......
    @FormUrlEncoded
    @POST("change_password_student.php")
    Call<String> changePassword(@Field("email") String email,@Field("oldpass") String oldpass,@Field("newpass") String newpass);

    @FormUrlEncoded
    @POST("send_mail.php")
    Call<String> sendMail(@Field("email") String email);

    @FormUrlEncoded
    @POST("change_email_student.php")
    Call<String> changeEmailOfStudent(@Field("newemail") String newemail,@Field("oldemail") String oldemail);


    //library
    @FormUrlEncoded
    @POST("get_issued_books_student.php")
    Call<List<Library>> getIssuedBookOfStudent(@Field("email") String email);


    //faq
    @FormUrlEncoded
    @POST("insertfaq.php")
    Call<String> insertFaq(@Field("question") String question,@Field("title") String title,@Field("email") String email);

    @GET("getFaqStudent.php")
    Call<List<Faq>> getFaq(@Query("email") String email,@Query("faq_id") String faq_id);

    @GET("getFaqList.php")
    Call<List<Faq>> getFaqList();

    //notice

    @POST("get_notice.php")
    Call<List<Notice_Details>> viewNotice();


    //gallery
    @POST("get_images.php")
    Call<List<Images>> getImages();

    //faculty
    @POST("getfaculty.php")
    Call<List<faculty_details>> getFaculty();

//subject
    @GET("get_subjects.php")
    Call<List<Subjects>>getSujects(@Query("sem_name") String sem_name);

    //oldpapers

    @FormUrlEncoded
    @POST("get_old_paper.php")
    Call<List<OldPaper>> getOldPapers(@Field("sub_name") String sub_name);




    //notification

    @FormUrlEncoded
    @POST("insertToken.php")
    Call<String> insertKey(@Field("token") String token,@Field("email") String email);

    //forgotpassword

    @FormUrlEncoded
    @POST("forgot_password_student.php")
    Call<String> forgotPassword(@Field("email") String email);

    @FormUrlEncoded
    @POST("reset_password_student.php")
    Call<String> resetPassword(@Field("email") String email,@Field("password") String password);


    @FormUrlEncoded
    @POST("checkExistence.php")
    Call<String> checkExits(@Field("email") String email);

//    @FormUrlEncoded
//    @POST("forgot_password.php")
//    Call<String> forgotPassword(@Field("email") String email);
//
//    @FormUrlEncoded
//    @POST("reset_password.php")
//    Call<String> resetPassword(@Field("email") String email, @Field("password") String password);
//
//    @FormUrlEncoded
//    @POST("send_mail.php")
//    Call<String> sendMail(@Field("email") String email);
//
//    @FormUrlEncoded
//    @POST("change_email.php")
//    Call<String> changeEmail(@Field("newemail") String newemail, @Field("oldemail") String oldemail);
//
//    @FormUrlEncoded
//    @POST("change_password.php")
//    Call<String> changePassword(@Field("email") String email, @Field("oldpass") String oldpass, @Field("newpass") String newpass);
//    //
////    @FormUrlEncoded
////    @POST("update_email.php")
////    Call<allDetails> changeEmail(@Field("email") String email, @Field("new_email") String newEmail);
////
////    @FormUrlEncoded
////    @POST("update_password.php")
////    Call<allDetails> changePassword(@Field("email") String email, @Field("password") String password);
////
////    @FormUrlEncoded
////    @POST("update_contact_no.php")
////    Call<allDetails> changeContactNo(@Field("email") String email, @Field("mobile_no") String contactNo);
////
////    @FormUrlEncoded
////    @POST("update_image.php")
////    Call<allDetails> changeImage(@Field("email") String email, @Field("title") String title, @Field("path") String path);
////
//    @POST("getfaculty.php")
//    Call<List<faculty_details>> getFaculty();
//    //
//
//    @Multipart
//    @POST("updatefaculty.php")
//    Call<faculty_details> updatefaculty(@Part MultipartBody.Part filename, @Part("staff_id") RequestBody staff_id, @Part("firstname") RequestBody firstname, @Part("middlename") RequestBody middlename, @Part("lastname") RequestBody lastname, @Part("dob") RequestBody dob, @Part("age") RequestBody age, @Part("gender") RequestBody gender, @Part("permanent_address") RequestBody permanent_address, @Part("cor_address") RequestBody cor_address, @Part("city") RequestBody city, @Part("state") RequestBody state, @Part("pincode") RequestBody pincode, @Part("mobile_no") RequestBody number, @Part("cor_mobile_no") RequestBody cor_mobile_no, @Part("email") RequestBody email, @Part("nationality") RequestBody nationality, @Part("religion") RequestBody religion, @Part("image_path") RequestBody image_path, @Part("num") RequestBody num, @Part("qualification") RequestBody qualification, @Part("experience") RequestBody experience, @Part("designation") RequestBody designation);
//
//
////    @FormUrlEncoded
////    @POST("updatefaculty.php")
////    Call<faculty_details> updatefaculty(@Field("staff_id") String staff_id, @Field("firstname") String firstname, @Field("middlename") String middlename, @Field("lastname") String lastname, @Field("dob") String dob, @Field("age") String age, @Field("gender") String gender, @Field("permanent_address") String permanent_address, @Field("cor_address") String cor_address, @Field("city") String city, @Field("state") String state, @Field("pincode") String pincode, @Field("mobile_no") String number, @Field("cor_mobile_no") String cor_mobile_no, @Field("email") String email, @Field("nationality") String nationality, @Field("religion") String religion, @Field("image_path") String image_path, @Field("num") int num, @Field("name") String name,@Field("qualification") String qualification,@Field("experience") String experience,@Field("designation") String designation);
//    //
//    @FormUrlEncoded
//    @POST("delete_faculty.php")
//    Call<faculty_details> deleteFaculty(@Field("email") String email);
//    //
//    @FormUrlEncoded
//    @POST("getFacultyspec.php")
//    Call<faculty_details> viewFaculty(@Field("email") String email);
//
//    @FormUrlEncoded
//    @POST("allocate_subject.php")
//    Call<String> allocate_Subject(@Field("sub_name") String sub_name, @Field("email") String email);
////
//    @FormUrlEncoded
//    @POST("get_allocated_subject.php")
//    Call<List<Subjects>> getAllocatedSubject(@Field("email") String email);
//    //
//    @FormUrlEncoded
//    @POST("delete_allocated_subject.php")
//    Call<String> deleteAllocatedSubject(@Field("sub_name") String sub_name);
//    //
//    @GET("getstudent.php")
//    Call<List<student_details>>getstudent(@Query("sem_name") String sem_name);
////
////    @FormUrlEncoded
////    @POST("updatestudent.php")
////    Call<student_details> updateStudent(@Field("enrollment_no") String enrollment_no, @Field("firstname") String firstname, @Field("middlename") String middlename, @Field("lastname") String lastname,@Field("per_address") String per_address, @Field("per_city") String per_city,@Field("per_pincode") String per_pincode,@Field("mobile_no") String mobile_no,@Field("cor_address") String cor_address,@Field("cor_city") String cor_city,@Field("cor_pincode") String cor_pincode,@Field("cor_mobile_no") String cor_mobile_no,@Field("state") String state,@Field("dob") String dob, @Field("gender") String gender,@Field("nationality") String nationality,@Field("caste")String caste,@Field("subcaste")String subcaste,@Field("minority")String minority,@Field("religion")String religion,@Field("email") String email,@Field("image_path") String image_path,@Field("aadharcard_no") String aadharcard_no, @Field("num") int num, @Field("name") String name);
//
//
//    @Multipart
//    @POST("updatestudent.php")
//    Call<student_details> updateStudent(@Part MultipartBody.Part filename, @Part("enrollment_no") RequestBody enrollment_no, @Part("firstname") RequestBody firstname, @Part("middlename") RequestBody middlename, @Part("lastname") RequestBody lastname, @Part("per_address") RequestBody per_address, @Part("per_city") RequestBody per_city, @Part("per_pincode") RequestBody per_pincode, @Part("mobile_no") RequestBody mobile_no, @Part("cor_address") RequestBody cor_address, @Part("cor_city") RequestBody cor_city, @Part("cor_pincode") RequestBody cor_pincode, @Part("cor_mobile_no") RequestBody cor_mobile_no, @Part("state") RequestBody state, @Part("dob") RequestBody dob, @Part("gender") RequestBody gender, @Part("nationality") RequestBody nationality, @Part("caste") RequestBody caste, @Part("subcaste") RequestBody subcaste, @Part("minority") RequestBody minority, @Part("religion") RequestBody religion, @Part("email") RequestBody email, @Part("image_path") RequestBody image_path, @Part("aadharcard_no") RequestBody aadharcard_no, @Part("num") RequestBody num);
//    //
//    @FormUrlEncoded
//    @POST("deletestudent.php")
//    Call<student_details> deleteStudent(@Field("enrollment_no") String enrollment_no);
////
//    @FormUrlEncoded
//    @POST("getStudentspec.php")
//    Call<student_details> viewStudent(@Field("enrollment_no") String enrollment_no);
//
//    @FormUrlEncoded
//    @POST("change_sem.php")
//    Call<String> changeSemester(@Field("studentsemlist") String studentsemlist, @Field("sem_name") String sem_name, @Field("oldsem_name") String oldsem_name);
//
//
////    @Multipart
////    @POST("add_event.php")
////    Call<Events> addEvent(@Field("name") String name, @Field("place") String place, @Field("start_date") String start_date, @Field("end_date") String end_date, @Field("description") String description, @Part MultipartBody.Part file, @Part("file")RequestBody filename);
//
//    @FormUrlEncoded
//    @POST("addevent.php")
//    Call<String> addEvent(@Field("event_name") String event_name, @Field("filepath") String filepath);
//
//    @GET("get_event.php")
//    Call<List<Events>> getEvent();
//
//
//    @GET("delete_event.php")
//    Call<String> deleteEvent(@Query("event_id") String event_id);
////
//
//    @GET("get_subjects.php")
//    Call<List<Subjects>>getSujects(@Query("sem_name") String sem_name);
//
////    @POST("get_event.php")
////    Call<List<Events>> getEvent();
////
////    @FormUrlEncoded
////    @POST("view_event.php")
////    Call<Events> viewEvent(@Field("event_id") int eventId);
////
//    @POST("get_images.php")
//    Call<List<Images>> getImages();
////
////    @FormUrlEncoded
////    @POST("upload_image.php")
////    Call<Images> uploadImage(@Field("title") String title, @Field("image_path") String image_path);
//
//    @Multipart
//    @POST("upload_image.php")
//    Call<Images> upload(@Part MultipartBody.Part filename, @Part("filename1") RequestBody name1);
////
//    @FormUrlEncoded
//    @POST("delete_image.php")
//    Call<Images> deleteImage(@Field("gallery_id") String gallery_id);
////
//    @FormUrlEncoded
//    @POST("getfacultysem.php")
//    Call<List<Subject__Allocate>> getFacultySemesters(@Field("email") String email);
////
//    @FormUrlEncoded
//    @POST("getfacultysub.php")
//    Call<List<Subject__Allocate>> getFacultySubjects(@Field("email") String email, @Field("sem_id") String semId);
////
////    @FormUrlEncoded
////    @POST("view_attendance.php")
////    Call<List<studentDetails>> viewAttendance(@Field("sub_id") String subId, @Field("date") String date);
////
//    @FormUrlEncoded
//    @POST("take_attendance.php")
//    Call<student_details> takeAttendance(@Field("attendance_sheet") String attendance_sheet);
//
//    @FormUrlEncoded
//    @POST("view_attendance.php")
//    Call<List<Attendance>> viewAttendance(@Field("sem_name") String sem_name, @Field("sub_name") String sub_name, @Field("date1") String date1);
//
////
////    @FormUrlEncoded
////    @POST("get_result_list.php")
////    Call<List<studentDetails>> getResultList(@Field("sem_id") String semId);
////
////    @FormUrlEncoded
////    @POST("add_assignment.php")
////    Call<allDetails> addAssignment(@Field("email") String email, @Field("sub_id") String subId, @Field("description") String desc, @Field("submission_date") String submissionDate);
////
//    @FormUrlEncoded
//    @POST("get_assignments_faculty.php")
//    Call<List<assignment>> getAssignmentsForFaculty(@Field("email") String email);
//
//
//    @GET("get_assignments_admin.php")
//    Call<List<assignment>> getAssignments();
//
//    @FormUrlEncoded
//    @POST("add_assignments.php")
//    Call<assignment> addAssignment(@Field("email") String email, @Field("sub_name") String sub_name, @Field("image_path") String image_path);
//
//
//    @FormUrlEncoded
//    @POST("delete_assignments.php")
//    Call<assignment> deleteAssignment(@Field("assignment_id") String assignment_id);
//
//    @FormUrlEncoded
//    @POST("insertResult.php")
//    Call<result> insertResult(@Field("enrollment_no") String enrollment_no, @Field("sem_name") String sem_name, @Field("year") String year, @Field("grade") String grade, @Field("classab") String classab);
//
//    @FormUrlEncoded
//    @POST("addquestions.php")
//    Call<String> addExamQuestions(@Field("questionlist") String questionlist, @Field("exam_name") String exam_name, @Field("email") String email, @Field("sub_id") String sub_id, @Field("date") String date);
//
//
//    @GET("getExamsAdmin.php")
//    Call<List<Exam_Detail>> getExamsforadmin();
//
//    @FormUrlEncoded
//    @POST("getExamsFaculty.php")
//    Call<List<Exam_Detail>> getExamsforfaculty(@Field("email") String email);
//
//    @FormUrlEncoded
//    @POST("getQuestionList.php")
//    Call<List<Exams>> getQuestionList(@Field("exam_id") String exam_id);
//
//    @FormUrlEncoded
//    @POST("delete_exam.php")
//    Call<String> deleteExam(@Field("exam_id") String exam_id);
//
//    @FormUrlEncoded
//    @POST("update_question.php")
//    Call<String> updateQuestion(@Field("exam_id") String exam_id, @Field("question_no") String question_no, @Field("question") String question, @Field("op1") String op1, @Field("op2") String op2, @Field("op3") String op3, @Field("op4") String op4, @Field("ans") String ans);
}
