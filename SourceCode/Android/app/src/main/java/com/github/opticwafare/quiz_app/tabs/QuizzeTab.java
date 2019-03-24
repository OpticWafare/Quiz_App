package com.github.opticwafare.quiz_app.tabs;

import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
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

        final List<Quiz> quizze = mainActivity.getQuizzesForLoggedInUser();
        System.out.println("QuizzeTab init: quizze from mainacitivity: " + quizze);
        if(quizze != null) {
            System.out.println("\tQuizzeTab init: quizze not null ; size=" + quizze.size());
            System.out.println("\tshowQuizze");

            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    // Code here will run in UI thread
                    showQuizze(quizze);
                }
            });
        }
    }

    public void showQuizze(List<Quiz> quizze) {

        System.out.println("QuizzeTab - showQuizze");
        System.out.println("\tOld Quizzes = " + this.quizze);
        System.out.println("\tNew Quizzes = " + quizze);

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
        System.out.println("QuizzeTab: linearLayout numberViews: " + numberViews);

        if(numberViews > 1) {

            System.out.println("\tnumberviews > 1 => quizzes have not been unloaded => only update GUI if quiz-list has been changed");
            System.out.println("\tcomparing quizzes");
            if((this.quizze != null) && (this.quizze.equals(quizze))) {
                System.out.println("\told and new quiz are equal! -> not updating UI");
                this.quizze = quizze;
                return;
            }
            System.out.println("\told and new quiz are not equal -> updating ui");
            this.quizze = quizze;

            View headline = linearLayout.getChildAt(0);
            linearLayout.removeAllViews();
            linearLayout.addView(headline, 0);

            /*
            //linearLayout.removeViews(1, numberViews);
            for(int i = 1; i < numberViews; i++) {
                try {
                    linearLayout.removeViewAt(i);
                }
                catch (NullPointerException e) {
                    System.out.println("QuizzeTab - Quizze entfernen: View nr " + i + " war null");
                }
            }*/
        }

        System.out.println("QuizzeTab: quizze list = " + quizze + " ; size = " + quizze.size());
        for(int i = 0; i < quizze.size(); i++) {

            QuizPreviewElement quizPreviewElement = new QuizPreviewElement();
            ViewGroup quizPreview = quizPreviewElement.show(inflater, linearLayout, quizze.get(i), mainActivity);
            linearLayout.addView(quizPreview);
            System.out.println("\t" + i + " Quiz zu QuizzeTab hinzugefügt");
        }
        System.out.println("All quizzes added to quizzeTab. NumberViews = " + linearLayout.getChildCount());
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

        System.out.println("QuizzeTab("+this+") - onPageSelected. Position: " + position);
        // TODO standartisieren
        if(position == 0 && mainActivity.getPagerAdapter() instanceof MainPagerAdapter) {
            //showQuizze(this.quizze); // TODO
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
