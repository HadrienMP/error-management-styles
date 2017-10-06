package fr.hadrienmp.error_management_styles;

import fr.hadrienmp.error_management_styles.other.BusinessException;
import fr.hadrienmp.error_management_styles.other.FlashMessages;
import fr.hadrienmp.error_management_styles.other.Home;
import fr.hadrienmp.error_management_styles.other.Request;
import fr.hadrienmp.error_management_styles.other.Response;
import fr.hadrienmp.error_management_styles.other.UpdateAddressForm;

public class BooleanAndException {
    private final FlashMessages flashMessages;

    public BooleanAndException(FlashMessages flashMessages) {
        this.flashMessages = flashMessages;
    }

    public Response updateAddress(Request request) {

        UpdateAddressForm form = new UpdateAddressForm(request);

        if (form.isValid()) {
            try {
                updateUserAddress(form.userId(), form.address());
                flashMessages.add("You updated your address, congratulations !");
                return Response.redirectTo(Home.URL);
            } catch (BusinessException e) {
                flashMessages.add("Sorry your request was denied, it contained the following errors : " + e.errors());
            }
        }
        flashMessages.add("Sorry your request was denied, it contained the following errors : " + form.errors());
        return Response.redirectTo(request.referer());
    }

    private boolean updateUserAddress(Object userId, Object address) {
        return false;
    }

}
