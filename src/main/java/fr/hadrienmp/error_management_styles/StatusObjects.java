package fr.hadrienmp.error_management_styles;

import fr.hadrienmp.error_management_styles.support_classes.FlashMessages;
import fr.hadrienmp.error_management_styles.support_classes.Request;
import fr.hadrienmp.error_management_styles.support_classes.Response;
import fr.hadrienmp.error_management_styles.support_classes.UpdateAddressForm;

public class StatusObjects {
    private final FlashMessages flashMessages;

    public StatusObjects(FlashMessages flashMessages) {
        this.flashMessages = flashMessages;
    }

    public Response updateAddress(Request request) {

        UpdateAddressForm form = new UpdateAddressForm(request);

        if (form.isValid()) {
            Status status = updateUserAddress(form.userId(), form.address());
            if (status.failed()) {
                flashMessages.add("Sorry your request was denied, it contained the following errors : " + status.errors());
            }
        } else {
            flashMessages.add("Sorry your request was denied, it contained the following errors : " + form.errors());
        }
        return Response.redirectTo(EditAdressPage.URL);
    }

    private Status updateUserAddress(Object userId, Object address) {
        return new Status();
    }

    private class Status {
        public boolean failed() {
            return false;
        }

        public String errors() {
            return null;
        }
    }
}
