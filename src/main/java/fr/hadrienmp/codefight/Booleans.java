package fr.hadrienmp.codefight;

import fr.hadrienmp.katas.exceptions.other.FlashMessages;
import fr.hadrienmp.katas.exceptions.other.Home;
import fr.hadrienmp.katas.exceptions.other.Request;
import fr.hadrienmp.katas.exceptions.other.Response;
import fr.hadrienmp.katas.exceptions.other.UpdateAddressForm;

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
