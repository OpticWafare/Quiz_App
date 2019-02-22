package com.github.opticwafare.quiz_app;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import com.github.opticwafare.quiz_app.R;

import com.github.opticwafare.quiz_app.listener.FCMTokenTaskCompleteListener;
import com.github.opticwafare.quiz_app.model.User;
import com.github.opticwafare.quiz_app.pageradapter.FixedTabsPagerAdapter;
import com.github.opticwafare.quiz_app.pageradapter.LoginPagerAdapter;
import com.github.opticwafare.quiz_app.pageradapter.MainPagerAdapter;
import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity {

    private FixedTabsPagerAdapter pagerAdapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO Local Storage auslesen und rausfinden, ob user eingeloggt ist

        // View Pager (Tabs) erstellen
        viewPager = (ViewPager) findViewById(R.id.view_pager);

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

        // Ausgewählten PagerAdapter im ViewPager setzen
        viewPager.setAdapter(pagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        // ViewPager im TabLayout anzeigen
        tabLayout.setupWithViewPager(viewPager);

        // FCM Token holen lassen
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new FCMTokenTaskCompleteListener());

    }

    public FixedTabsPagerAdapter getPagerAdapter() {
        return pagerAdapter;
    }

    public void setPagerAdapter(FixedTabsPagerAdapter pagerAdapter) {
        this.pagerAdapter = pagerAdapter;
        viewPager.setAdapter(this.pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
