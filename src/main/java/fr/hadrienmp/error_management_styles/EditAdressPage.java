package fr.hadrienmp.error_management_styles;

import fr.hadrienmp.error_management_styles.support_classes.Request;
import fr.hadrienmp.error_management_styles.support_classes.Response;

public interface EditAdressPage {
    String URL = "url de la page d'édition d'adresse";

    Response updateAddress(Request request);
}
