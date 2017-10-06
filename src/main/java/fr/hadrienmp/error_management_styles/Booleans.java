package fr.hadrienmp.error_management_styles;

import fr.hadrienmp.error_management_styles.other.FlashMessages;
import fr.hadrienmp.error_management_styles.other.Home;
import fr.hadrienmp.error_management_styles.other.Request;
import fr.hadrienmp.error_management_styles.other.Response;
import fr.hadrienmp.error_management_styles.other.UpdateAddressForm;

public class Booleans {
    private final FlashMessages flashMessages;

    public Booleans(FlashMessages flashMessages) {
        this.flashMessages = flashMessages;
    }

    public Response updateAddress(Request request) {

        UpdateAddressForm form = new UpdateAddressForm(request);

        if (form.isValid()) {
            boolean updateSucceeded = updateUserAddress(form.userId(), form.address());
            if (updateSucceeded) {
                flashMessages.add("You updated your address, congratulations !");
                return Response.redirectTo(Home.URL);
            } else {
                flashMessages.add("Sorry your request could not be processed...");
            }
        } else {
            flashMessages.add("Sorry your request was denied, it contained the following errors : " + form.errors());
        }
        return Response.redirectTo(request.referer());
    }

    private boolean updateUserAddress(Object userId, Object address) {
        return false;
    }

}
