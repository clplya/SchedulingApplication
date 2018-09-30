package schedulingapplication;

import java.util.*;

public class SchedulerLoginLocalization {

    public void createLocales(String localizeSetting) {
        if (localizeSetting.equals("en")) {
            Locale us = new Locale("en", "US");
            localizeEN(us);
        } else if (localizeSetting.equals("sp")) {
            Locale spain = new Locale("sp", "SP");
            localizeSP(spain);
        }
    }

    public void localizeSP(Locale locale) {
        ResourceBundle.getBundle("sp", locale);
    }

    public void localizeEN(Locale locale) {
        ResourceBundle.getBundle("en", locale);
    }
}
