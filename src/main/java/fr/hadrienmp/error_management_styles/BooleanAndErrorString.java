package fr.hadrienmp.error_management_styles;

import fr.hadrienmp.error_management_styles.support_classes.FlashMessages;
import fr.hadrienmp.error_management_styles.support_classes.AccountPage;
import fr.hadrienmp.error_management_styles.support_classes.Request;
import fr.hadrienmp.error_management_styles.support_classes.Response;
import fr.hadrienmp.error_management_styles.support_classes.UpdateAddressForm;

public class BooleanAndErrorString {
    private final FlashMessages flashMessages;

    public BooleanAndErrorString(FlashMessages flashMessages) {
        this.flashMessages = flashMessages;
    }

    public Response updateAddress(Request request) {

        UpdateAddressForm form = new UpdateAddressForm(request);

        if (form.isValid()) {
            String errors = updateUserAddress(form.userId(), form.address());
            if (!errors.equals("")) {
                flashMessages.add("You updated your address, congratulations !");
                return Response.redirectTo(AccountPage.URL);
            } else {
                flashMessages.add("Sorry your request was denied, it contained the following errors : " + errors);
            }
        } else {
            flashMessages.add("Sorry your request was denied, it contained the following errors : " + form.errors());
        }
        return Response.redirectTo(EditAdressPage.URL);
    }

    private String updateUserAddress(Object userId, Object address) {
        return "";
    }

}
