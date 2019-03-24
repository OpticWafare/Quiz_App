package com.github.opticwafare.quiz_app.elements;

import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Super-Klasse für alle UIElemente.
 * UIElemente werden von XML-Dateien erstellt,
 * sind jedoch keine Activities oder Tabs.
 * Stattdessen sind sie ein kleinerer Bestandteil eines Tabs/Activity.
 */
public class UIElement {

    private LayoutInflater layoutInflater;
    private int xmlLayout;

    public UIElement(int xmlLayout) {
        this.xmlLayout = xmlLayout;
    }

    /**
     * Gibt ein GUI-Objekt zurück, das in eine andere GUI (Tab bzw. Activity)
     * eingebunden werden kann
     * @param layoutInflater
     * @param container
     * @return
     */
    public ViewGroup show(LayoutInflater layoutInflater, ViewGroup container) {
        this.layoutInflater = layoutInflater;

        // XML in eine lauffähige GUI verwandeln
        ViewGroup layout = (ViewGroup) layoutInflater.inflate(xmlLayout, container, false);
        return layout;
    }

    public int getXmlLayout() {
        return xmlLayout;
    }

    public void setXmlLayout(int xmlLayout) {
        this.xmlLayout = xmlLayout;
    }
}
