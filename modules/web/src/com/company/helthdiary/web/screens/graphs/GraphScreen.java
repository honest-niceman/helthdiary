package com.company.helthdiary.web.screens.graphs;

import com.company.helthdiary.entity.Glucose;
import com.company.helthdiary.entity.Pressure;
import com.company.helthdiary.entity.Pulse;
import com.haulmont.charts.gui.amcharts.model.charts.SerialChart;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

import javax.inject.Inject;
import java.util.Map;
import java.util.UUID;

@UiController("helthdiary_GraphScreen")
@UiDescriptor("graph-screen.xml")
public class GraphScreen extends Screen {
    @Inject
    private CollectionLoader<Pulse> pulsesDl;
    @Inject
    private CollectionLoader<Glucose> glucoseDl;
    @Inject
    private CollectionLoader<Pressure> pressuresDl;
    @Subscribe
    public void onInit(InitEvent event) {
        pulsesDl.setParameter("user", AppBeans.get(UserSessionSource.class).getUserSession().getUser());
        pulsesDl.load();
        glucoseDl.setParameter("user", AppBeans.get(UserSessionSource.class).getUserSession().getUser());
        glucoseDl.load();
        pressuresDl.setParameter("user", AppBeans.get(UserSessionSource.class).getUserSession().getUser());
        pressuresDl.load();
    }

}