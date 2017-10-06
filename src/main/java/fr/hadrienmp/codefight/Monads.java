package fr.hadrienmp.codefight;

import com.jasongoodwin.monads.Try;
import fr.hadrienmp.katas.exceptions.other.FlashMessages;
import fr.hadrienmp.katas.exceptions.other.Home;
import fr.hadrienmp.katas.exceptions.other.Request;
import fr.hadrienmp.katas.exceptions.other.Response;
import fr.hadrienmp.katas.exceptions.other.UpdateAddressForm;

public class Monads {
    private final FlashMessages flashMessages;

    public Monads(FlashMessages flashMessages) {
        this.flashMessages = flashMessages;
    }

    public Response updateAddress(Request request) {
        return Try.ofFailable(() -> new UpdateAddressForm(request))
                .flatMap(form -> updateUserAddress(form.userId(), form.address()))
                .onSuccess(v -> flashMessages.add("You updated your address, congratulations !"))
                .onFailure(e -> flashMessages.add("Sorry your request was denied, it contained the following errors : " + e.getMessage()))
                .map(v -> Response.redirectTo(Home.URL))
                .orElse(Response.redirectTo(request.referer()));
    }

    private Try<Void> updateUserAddress(Object userId, Object address) {
        return Try.successful(null);
    }
}
