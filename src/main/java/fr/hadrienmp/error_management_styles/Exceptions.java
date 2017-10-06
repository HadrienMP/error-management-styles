package fr.hadrienmp.error_management_styles;

import fr.hadrienmp.error_management_styles.other.BusinessException;
import fr.hadrienmp.error_management_styles.other.FlashMessages;
import fr.hadrienmp.error_management_styles.other.Home;
import fr.hadrienmp.error_management_styles.other.Request;
import fr.hadrienmp.error_management_styles.other.Response;
import fr.hadrienmp.error_management_styles.other.UpdateAddressForm;

public class Exceptions {
    private final FlashMessages flashMessages;

    public Exceptions(FlashMessages flashMessages) {
        this.flashMessages = flashMessages;
    }

    public Response updateAddress(Request request) {
        try {
            UpdateAddressForm form = new UpdateAddressForm(request);
            updateUserAddress(form.userId(), form.address());
            flashMessages.add("You updated your address, congratulations !");
            return Response.redirectTo(Home.URL);
        } catch (BusinessException e) {
            flashMessages.add("Sorry your request was denied, it contained the following errors : " + e.errors());
            return Response.redirectTo(request.referer());
        }
    }

    private void updateUserAddress(Object userId, Object address) {}

}
