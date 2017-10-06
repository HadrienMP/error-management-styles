package fr.hadrienmp.error_management_styles;

import fr.hadrienmp.error_management_styles.support_classes.AccountPage;
import fr.hadrienmp.error_management_styles.support_classes.Request;
import fr.hadrienmp.error_management_styles.support_classes.Response;
import fr.hadrienmp.error_management_styles.support_classes.ResponseBuilder;
import fr.hadrienmp.error_management_styles.support_classes.UpdateAddressForm;
import fr.hadrienmp.error_management_styles.support_classes.UpdateAddressWebService;

public class Monads2 implements EditAdressPage {

    private UpdateAddressWebService webService;
    public Monads2(UpdateAddressWebService webService) {
        this.webService = webService;
    }

    @Override
    public Response updateAddress(Request request) {
        return UpdateAddressForm.from(request)
                .flatMap(form -> webService.tryUpdateUserAddress(form.userId(), form.address()))
                .map(this::successResponse)
                .recover(this::failResponse);
    }

    private Response successResponse(Void v) {
        return ResponseBuilder.redirectTo(AccountPage.URL)
                .withMessage("You updated your address, congratulations !")
                .response();
    }

    private Response failResponse(Throwable e) {
        return ResponseBuilder.redirectTo(EditAdressPage.URL)
                .withMessage("Sorry your request was denied, it contained the following errors : " + e.getMessage())
                .response();
    }

}
