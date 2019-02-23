package com.github.opticwafare.quiz_app.elements;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.opticwafare.quiz_app.R;
import com.github.opticwafare.quiz_app.model.RankUser;

import java.time.format.TextStyle;

public class RankUserElement extends UIElement {

    public RankUserElement() {
        super(R.layout.element_rankuser);
    }

    public ViewGroup show(LayoutInflater layoutInflater, ViewGroup container, RankUser rankUser, int maxNumberPoints) {
        ViewGroup layout = super.show(layoutInflater, container);

        TextView textViewRankNumber = (TextView) layout.findViewById(R.id.textView_rankuser_ranknumber);
        TextView textViewUsername = (TextView) layout.findViewById(R.id.textView_rankuser_name);
        TextView textView1 = (TextView) layout.findViewById(R.id.textView_rankuser_1);
        TextView textView2 = (TextView) layout.findViewById(R.id.textView_rankuser_2);

        if(rankUser.getRank() == -1) {

            textView1.setText("Noch nicht ausgefüllt");
            textView1.setTypeface(textView1.getTypeface(), Typeface.ITALIC);

            textView2.setText("");

            textViewRankNumber.setText("?");
        }
        else {
            textViewRankNumber.setText(rankUser.getRank()+".");

            textView1.setText("Punkte: "+rankUser.getPointsAchieved()+" von "+maxNumberPoints);
            textView2.setText("Zeitpunkt: "+rankUser.getAnsweredDateAsString()+" um "+rankUser.getAnsweredTimeAsString()+" Uhr");
        }

        textViewUsername.setText(rankUser.getUsername());

        return layout;
    }
}
