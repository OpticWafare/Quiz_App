package com.github.opticwafare.quiz_app.tabs;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.github.opticwafare.quiz_app.MainActivity;
import com.github.opticwafare.quiz_app.R;
import com.github.opticwafare.quiz_app.elements.QuizPreviewElement;
import com.github.opticwafare.quiz_app.model.Question;
import com.github.opticwafare.quiz_app.model.Quiz;
import com.github.opticwafare.quiz_app.model.QuizForUser;
import com.github.opticwafare.quiz_app.pageradapter.MainPagerAdapter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class QuizzeTab extends SuperTab implements ViewPager.OnPageChangeListener {

    public QuizzeTab() {
        super("Quizze", R.layout.activity_quizze);
    }

    private List<Quiz> quizze;

    @Override
    public void init(MainActivity mainActivity) {
        this.mainActivity = mainActivity;

        mainActivity.getViewPager().removeOnPageChangeListener(this);
        mainActivity.getViewPager().addOnPageChangeListener(this);

        List<Quiz> quizze = mainActivity.getQuizzesForLoggedInUser();
        if(quizze != null) {
            showQuizze(quizze);
        }
    }

    public void showQuizze(List<Quiz> quizze) {

        this.quizze = quizze;

        //List<Quiz> quizze = new ArrayList<Quiz>();

        // Test Code Beginn
        /*Date dateJetzt = Calendar.getInstance().getTime();
        Timestamp timestampJetzt = new Timestamp(dateJetzt.getTime());

        Quiz testQuiz = new Quiz(null, "Test Quiz", timestampJetzt, timestampJetzt,
                new ArrayList<Question>(), new ArrayList<QuizForUser>());

        quizze.add(testQuiz);*/
        // Test Code Ende

        LinearLayout linearLayout = (LinearLayout) mainActivity.findViewById(R.id.linearlayout_quizze);

        if(linearLayout == null) {
            System.out.println("QuizzeTab - LinearLayout war null");
            return;
        }

        // LayoutInflater erstellen. Wird verwendet um ein XML-Dokument in eine lauffähige GUI zu verwandeln
        LayoutInflater inflater = LayoutInflater.from(mainActivity);

        int numberViews = linearLayout.getChildCount();
        if(numberViews > 1) {

            //linearLayout.removeViews(1, numberViews);
            for(int i = 1; i < numberViews; i++) {
                try {
                    linearLayout.removeViewAt(i);
                }
                catch (NullPointerException e) {
                    System.out.println("QuizzeTab - Quizze entfernen: View nr " + i + " war null");
                }
            }
        }

        for(int i = 0; i < quizze.size(); i++) {

            QuizPreviewElement quizPreviewElement = new QuizPreviewElement();
            ViewGroup quizPreview = quizPreviewElement.show(inflater, linearLayout, quizze.get(i), mainActivity);
            linearLayout.addView(quizPreview);
        }
    }

    public List<Quiz> getQuizze() {
        return quizze;
    }

    public void setQuizze(List<Quiz> quizze) {
        this.quizze = quizze;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        // TODO standartisieren
        if(position == 0 && mainActivity.getPagerAdapter() instanceof MainPagerAdapter) {
            //showQuizze(this.quizze); // TODO
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
