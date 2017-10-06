package fr.hadrienmp.error_management_styles;

import com.jasongoodwin.monads.Try;
import fr.hadrienmp.error_management_styles.other.FlashMessages;
import fr.hadrienmp.error_management_styles.other.Home;
import fr.hadrienmp.error_management_styles.other.Request;
import fr.hadrienmp.error_management_styles.other.Response;
import fr.hadrienmp.error_management_styles.other.UpdateAddressForm;

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
