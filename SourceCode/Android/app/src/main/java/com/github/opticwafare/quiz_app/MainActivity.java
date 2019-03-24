package com.github.opticwafare.quiz_app;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import com.github.opticwafare.quiz_app.R;

import com.github.opticwafare.quiz_app.listener.FCMTokenTaskCompleteListener;
import com.github.opticwafare.quiz_app.model.Quiz;
import com.github.opticwafare.quiz_app.model.User;
import com.github.opticwafare.quiz_app.pageradapter.FixedTabsPagerAdapter;
import com.github.opticwafare.quiz_app.pageradapter.LoginPagerAdapter;
import com.github.opticwafare.quiz_app.pageradapter.MainPagerAdapter;
import com.github.opticwafare.quiz_app.tabs.AnswerQuizTab;
import com.github.opticwafare.quiz_app.tabs.QuizTab;
import com.github.opticwafare.quiz_app.tabs.QuizzeTab;
import com.github.opticwafare.quiz_app.tabs.SuperTab;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Diese App hat nur eine Activity -> MainActivity.
 * Unterschiedliche Ansichten werden mit 2 FixedTabsPagerAdapter gesteuert.
 * Diese enthalten jeweils mehrere Tabs
 */
public class MainActivity extends AppCompatActivity {

    private FixedTabsPagerAdapter pagerAdapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private List<Quiz> quizzesForLoggedInUser = null;

    /** Wurden die quizzesForLoggedInUser schon einmal gesetzt? */
    private static boolean hasSetQuizzesForLoggedInUser = false;

    /**
     * Beginn der App
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO Local Storage auslesen und rausfinden, ob user eingeloggt ist

        // Wenn kein User eingeloggt ist...
        if (User.getLoggedInUser() == null) {
            // ... Login & Register Menü anzeigen
            pagerAdapter = new LoginPagerAdapter(this);
        }
        // Wenn User eingeloggt ist
        else {
            // ... normales Menü anzeigen
            pagerAdapter = new MainPagerAdapter(this);
        }

        // TODO test
        //pagerAdapter = new MainPagerAdapter(this);

        // View Pager (Tabs) holen
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        // Tab Layout holen
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        setPagerAdapter(pagerAdapter);

        // FCM Token holen lassen
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new FCMTokenTaskCompleteListener());
    }

    public FixedTabsPagerAdapter getPagerAdapter() {
        return pagerAdapter;
    }

    public void setPagerAdapter(FixedTabsPagerAdapter pagerAdapter) {
        this.pagerAdapter = pagerAdapter;
        // Ausgewählten PagerAdapter im ViewPager setzen
        viewPager.setAdapter(this.pagerAdapter);
        // ViewPager im TabLayout anzeigen
        tabLayout.setupWithViewPager(viewPager);
    }

    public void refreshPagerAdapter() {
        int currentTabNumber = viewPager.getCurrentItem();
        setPagerAdapter(getPagerAdapter());
        viewPager.setCurrentItem(currentTabNumber);
    }

    public List<Quiz> getQuizzesForLoggedInUser() {
        //return quizzesForLoggedInUser;
        //return (List<Quiz>) retrievePreferenceObject("QUIZDATA", "quizzesForLoggedInUser", (Type) quizzesForLoggedInUser.getClass());
        System.out.println("MainActivity - getQuizzesForLoggedInUser. quizzesForLoggedInUser = " + quizzesForLoggedInUser);
        System.out.println("\thasSetQuizzesForLoggedInUser = " + hasSetQuizzesForLoggedInUser);
        System.out.println("(logged in user =  "+User.getLoggedInUser()+")");
        if(hasSetQuizzesForLoggedInUser == false) return null;

        if((quizzesForLoggedInUser == null) || (quizzesForLoggedInUser.size() < 1)) {
            System.out.println("MainActivity - getQuizzesForLoggedInUser. quizzesForLoggedInUser was unloaded (size < 1 or null) -> load from preferences");
            quizzesForLoggedInUser = retrievePreferenceListObject("QUIZDATA", "quizzesForLoggedInUser", Quiz.class);
        }
        printQuizOrder(quizzesForLoggedInUser);
        return quizzesForLoggedInUser;
    }

    public void printQuizOrder(List<Quiz> list) {
        System.out.println("=====");
        System.out.println("Printing order or quiz list " + list);
        if(list != null) {
            for(int i = 0; i < list.size(); i++) {
                System.out.println("\t" + i + ") QuizId=" + list.get(i).getQuizid() + " ; name = " + list.get(i).getName());
            }
        }
        System.out.println("=====");
    }

    public void setQuizzesForLoggedInUser(List<Quiz> quizzesForLoggedInUser) {
        System.out.println("MainActivity - setQuizzesForLoggedInUser");
        this.quizzesForLoggedInUser = quizzesForLoggedInUser;
        hasSetQuizzesForLoggedInUser = true;
        printQuizOrder(this.quizzesForLoggedInUser);
        storePreferenceListObject("QUIZDATA", "quizzesForLoggedInUser", this.quizzesForLoggedInUser);

        if(getPagerAdapter() instanceof MainPagerAdapter) {
            MainPagerAdapter mainPagerAdapter = (MainPagerAdapter) getPagerAdapter();
            if(mainPagerAdapter.getQuizzeTab() != null) {
                System.out.println("MainActivity - setQuizzesForLoggedInUser: quizze tab vorhanden -> show quizze");
                //mainPagerAdapter.getQuizzeTab().setQuizze(quizzesForLoggedInUser);
                mainPagerAdapter.getQuizzeTab().showQuizze(quizzesForLoggedInUser);
            }
            //mainPagerAdapter.getQuizzeTab().showQuizze(quizzesForLoggedInUser);
        }
    }


    /**
     * quizze in quizzetab entfernen
     * -> quizze werden bein nächsten mal sicher neu geladen
     */
    public void resetQuizzeInQuizzeTab() {
        if(getPagerAdapter() instanceof MainPagerAdapter) {
            MainPagerAdapter mainPagerAdapter = (MainPagerAdapter) getPagerAdapter();
            if(mainPagerAdapter.getQuizzeTab() != null) {
                System.out.println("MainActivity - resetQuizzeInQuizzeTab: quizze tab vorhanden -> reset quizze");
                mainPagerAdapter.getQuizzeTab().setQuizze(null);
            }
        }
    }

    /**
     * Speichert die Liste list mit dem Namen key in die Preference mit dem Namen preferenceName.
     * @param preferenceName
     * @param key
     * @param list
     */
    public void storePreferenceListObject(String preferenceName, String key, List list) {

        TreeSet<String> values = new TreeSet<>();
        Gson gson = new Gson();
        String json;
        for(int i = 0; i < list.size(); i++) {
            json = gson.toJson(list.get(i));
            values.add(json);
        }

        SharedPreferences preferences = getSharedPreferences(preferenceName, MODE_PRIVATE);
        preferences.edit().putStringSet(key, values).apply();
    }

    /**
     * Holt die Liste mit dem Namen key aus der Preference mit dem Namen preferenceName.
     * Die Liste hat Elemente vom Typ typeOfObject.
     * @param preferenceName
     * @param key
     * @param typeOfObject
     * @return
     */
    public List retrievePreferenceListObject(String preferenceName, String key, Type typeOfObject) {

        SharedPreferences preferences = getSharedPreferences(preferenceName, MODE_PRIVATE);
        if(preferences.contains(key) == false) {
            System.out.println("retrievePreferenceListObject: key " + key + " does not exist in shared preferences " + preferenceName);
            return null;
        }
        else {

            System.out.println("retrievePreferenceListObject: trying to get StringSet " + key + " from preference " + preferenceName);
            Set<String> values = preferences.getStringSet(key, null);
            if(values == null) {
                System.out.println("\tvalue set was null");
                return null;
            }
            System.out.println("\tretrieved " + values.size() + " values");

            Gson gson = new Gson();
            List list = new ArrayList();

            Object objectValue;
            for(String jsonValue : values) {
                objectValue = gson.fromJson(jsonValue, typeOfObject);
                list.add(objectValue);
            }
            Collections.sort(list);
            Collections.reverse(list);
            return list;
        }
    }

    /*
    public Object retrievePreferenceObject(String preferenceName, String key, Type classOfObject) {

        SharedPreferences preferences = getSharedPreferences(preferenceName, MODE_PRIVATE);
        if(preferences.contains(key) == false) {
            return null;
        }
        else {
            String json = preferences.getString(key, null);
            Gson gson = new Gson();
            Object o = gson.fromJson(json, classOfObject);
            return o;
        }
    }

    public void storePreferenceObject(String preferenceName, String key, Object object) {

        SharedPreferences preferences = getSharedPreferences(preferenceName, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = gson.toJson(object);

        preferences.edit().putString(key, json).apply();
    }*/

    public ViewPager getViewPager() {
        return viewPager;
    }

    /**
     * Wenn der Zuück-Button in Android geklickt wurde:
     * Von der Quiz Ansicht (QuizTab) zurück in die Quizze-Übersicht (QuizzeTab) wechseln.
     *
     * Falls gerade QuizTab nicht vorhanden/angezeigt wird -> default Aktion ausführen (App minimieren)
     */
    @Override
    public void onBackPressed() {
        System.out.println("Zurück-Button geklickt");

        boolean eventProcessed = false;
        /*
         * Wechselt vom Quiz-Tab (für ein enziges Quiz) zum Quizze Tab.
         * Neue Tab-Instanz wird erstellt und ausgetauscht
         * (aber nur wenn MainPager passt)
         */
        System.out.println("MainActivity - goToQuizzeTab");
        if(getPagerAdapter() instanceof MainPagerAdapter) {
            System.out.println("\tCurrent PagerAdapter is a MainPagerAdapter");
            MainPagerAdapter mainPagerAdapter = (MainPagerAdapter) getPagerAdapter();
            // Wenn gerade ein QuizTab angezeigt wird
            System.out.println("\tViewPager currentItem = " + getViewPager().getCurrentItem());
            if(getViewPager().getCurrentItem() == 0) {
                SuperTab tabAt0 = mainPagerAdapter.getTabAt(0); // TODO position variabel
                System.out.println("\tMainPagerAdapter: tab at 0 = " + tabAt0);
                if((tabAt0 instanceof QuizTab) || (tabAt0 instanceof AnswerQuizTab)) {

                    eventProcessed = true;
                    System.out.println("MainActivity - goToQuizzeTab: Ein QuizTab wird gerade angezeigt -> jetzt QuizzeTab anzeigen");

                    QuizzeTab quizzeTab = new QuizzeTab();
                    mainPagerAdapter.setTabAt(quizzeTab, 0); // TODO position variabel
                    refreshPagerAdapter();
                }
            }
        }

        System.out.println("MAinActivity - onBackPressed: eventProcessed = " + eventProcessed);
        if(eventProcessed == false) {
            super.onBackPressed();
        }
    }
}
