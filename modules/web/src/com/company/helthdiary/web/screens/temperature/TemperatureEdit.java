package com.company.helthdiary.web.screens.temperature;

import com.haulmont.cuba.gui.screen.*;
import com.company.helthdiary.entity.Temperature;

@UiController("helthdiary_Temperature.edit")
@UiDescriptor("temperature-edit.xml")
@EditedEntityContainer("temperatureDc")
@LoadDataBeforeShow
public class TemperatureEdit extends StandardEditor<Temperature> {
}