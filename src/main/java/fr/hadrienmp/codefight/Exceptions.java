package fr.hadrienmp.codefight;

import fr.hadrienmp.katas.exceptions.other.BusinessException;
import fr.hadrienmp.katas.exceptions.other.FlashMessages;
import fr.hadrienmp.katas.exceptions.other.Home;
import fr.hadrienmp.katas.exceptions.other.Request;
import fr.hadrienmp.katas.exceptions.other.Response;
import fr.hadrienmp.katas.exceptions.other.UpdateAddressForm;

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
