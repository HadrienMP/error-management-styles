package fr.hadrienmp.codefight;

import fr.hadrienmp.katas.exceptions.other.FlashMessages;
import fr.hadrienmp.katas.exceptions.other.Home;
import fr.hadrienmp.katas.exceptions.other.Request;
import fr.hadrienmp.katas.exceptions.other.Response;
import fr.hadrienmp.katas.exceptions.other.UpdateAddressForm;

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
                return Response.redirectTo(Home.URL);
            } else {
                flashMessages.add("Sorry your request was denied, it contained the following errors : " + errors);
            }
        } else {
            flashMessages.add("Sorry your request was denied, it contained the following errors : " + form.errors());
        }
        return Response.redirectTo(request.referer());
    }

    private String updateUserAddress(Object userId, Object address) {
        return "";
    }

}
