package fr.hadrienmp.error_management_styles;

import fr.hadrienmp.error_management_styles.support_classes.BusinessException;
import fr.hadrienmp.error_management_styles.support_classes.FlashMessages;
import fr.hadrienmp.error_management_styles.support_classes.AccountPage;
import fr.hadrienmp.error_management_styles.support_classes.Request;
import fr.hadrienmp.error_management_styles.support_classes.Response;
import fr.hadrienmp.error_management_styles.support_classes.UpdateAddressForm;

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
                return Response.redirectTo(AccountPage.URL);
            } catch (BusinessException e) {
                flashMessages.add("Sorry your request was denied, it contained the following errors : " + e.errors());
            }
        }
        flashMessages.add("Sorry your request was denied, it contained the following errors : " + form.errors());
        return Response.redirectTo(EditAdressPage.URL);
    }

    private boolean updateUserAddress(Object userId, Object address) {
        return false;
    }

}
