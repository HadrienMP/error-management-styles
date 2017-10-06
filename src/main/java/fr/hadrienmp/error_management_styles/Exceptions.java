package fr.hadrienmp.error_management_styles;

import fr.hadrienmp.error_management_styles.support_classes.BusinessException;
import fr.hadrienmp.error_management_styles.support_classes.FlashMessages;
import fr.hadrienmp.error_management_styles.support_classes.AccountPage;
import fr.hadrienmp.error_management_styles.support_classes.Request;
import fr.hadrienmp.error_management_styles.support_classes.Response;
import fr.hadrienmp.error_management_styles.support_classes.UpdateAddressForm;

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
            return Response.redirectTo(AccountPage.URL);
        } catch (BusinessException e) {
            flashMessages.add("Sorry your request was denied, it contained the following errors : " + e.errors());
            return Response.redirectTo(EditAdressPage.URL);
        }
    }

    private void updateUserAddress(Object userId, Object address) {}

}
