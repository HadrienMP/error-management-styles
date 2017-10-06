package fr.hadrienmp.error_management_styles;

import com.jasongoodwin.monads.Try;
import fr.hadrienmp.error_management_styles.other.FlashMessages;
import fr.hadrienmp.error_management_styles.other.Home;
import fr.hadrienmp.error_management_styles.other.Request;
import fr.hadrienmp.error_management_styles.other.Response;
import fr.hadrienmp.error_management_styles.other.UpdateAddressForm;

import java.net.URL;

public class Monads2 {
    private final FlashMessages flashMessages;

    public Monads2(FlashMessages flashMessages) {
        this.flashMessages = flashMessages;
    }

    public Response updateAddress(Request request) {
        return Try.ofFailable(() -> new UpdateAddressForm(request))
                .flatMap(form -> updateUserAddress(form.userId(), form.address()))
                .map(v -> ResponseBuilder.redirectTo(Home.URL)
                        .withMessage("You updated your address, congratulations !")
                        .response())
                .recover(e -> ResponseBuilder.redirectTo(request.referer())
                        .withMessage("Sorry your request was denied, it contained the following errors : ")
                        .response());
    }

    private Try<Void> updateUserAddress(Object userId, Object address) {
        return Try.successful(null);
    }

    private static class ResponseBuilder {
        public static ResponseBuilder redirectTo(URL url) {
            return new ResponseBuilder();
        }

        public ResponseBuilder withMessage(String message) {
            return this;
        }

        public Response response() {
            return new Response();
        }
    }
}
