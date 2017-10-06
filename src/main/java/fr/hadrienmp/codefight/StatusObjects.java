package fr.hadrienmp.codefight;

import fr.hadrienmp.codefight.other.FlashMessages;
import fr.hadrienmp.codefight.other.Request;
import fr.hadrienmp.codefight.other.Response;
import fr.hadrienmp.codefight.other.UpdateAddressForm;

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
        return Response.redirectTo(request.referer());
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