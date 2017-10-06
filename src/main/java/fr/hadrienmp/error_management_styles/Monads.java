package fr.hadrienmp.error_management_styles;

import com.jasongoodwin.monads.Try;
import fr.hadrienmp.error_management_styles.support_classes.FlashMessages;
import fr.hadrienmp.error_management_styles.support_classes.AccountPage;
import fr.hadrienmp.error_management_styles.support_classes.Request;
import fr.hadrienmp.error_management_styles.support_classes.Response;
import fr.hadrienmp.error_management_styles.support_classes.UpdateAddressForm;
import fr.hadrienmp.error_management_styles.support_classes.UpdateAddressWebService;

public class Monads implements EditAdressPage {
    private final FlashMessages flashMessages;
    private final UpdateAddressWebService webService;

    public Monads(UpdateAddressWebService webService) {
        flashMessages = new FlashMessages();
        this.webService = webService;
    }

    @Override
    public Response updateAddress(Request request) {
        return Try.ofFailable(() -> new UpdateAddressForm(request))
                .flatMap(form -> webService.tryUpdateUserAddress(form.userId(), form.address()))
                .onSuccess(v -> flashMessages.add("You updated your address, congratulations !"))
                .onFailure(e -> flashMessages.add("Sorry your request was denied, it contained the following errors : " + e.getMessage()))
                .map(v -> Response.redirectTo(AccountPage.URL))
                .orElse(Response.redirectTo(EditAdressPage.URL));
    }
}
