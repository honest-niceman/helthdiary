package com.company.helthdiary.web.screens.graphs;

import com.company.helthdiary.entity.Pulse;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

import javax.inject.Inject;

@UiController("helthdiary_GraphScreen")
@UiDescriptor("graph-screen.xml")
public class GraphScreen extends Screen {
    @Inject
    private CollectionLoader<Pulse> pulsesDl;

    @Subscribe
    public void onInit(InitEvent event) {
        pulsesDl.setParameter("user", AppBeans.get(UserSessionSource.class).getUserSession().getUser());
        pulsesDl.load();
    }
}