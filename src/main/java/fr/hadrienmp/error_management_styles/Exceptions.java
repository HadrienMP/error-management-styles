package fr.hadrienmp.error_management_styles;

import fr.hadrienmp.error_management_styles.support_classes.*;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Exceptions implements EditAddressPage {

    private UpdateAddressService webService;

    public Response updateAddress(Request request) {
        try {
            UpdateAddressForm form = UpdateAddressForm.tryToCreate(request);
            webService.updateUserAddress(form.userId(), form.address());
            return successResponse();
        } catch (BusinessException e) {
            return failResponse(e);
        }
    }

    private Response successResponse() {
        return ResponseBuilder.redirectTo(AccountPage.URL)
                .withMessage("You updated your address, congratulations !")
                .response();
    }

    private Response failResponse(Throwable e) {
        return ResponseBuilder.redirectTo(EditAddressPage.URL)
                .withMessage("Sorry your request was denied, it contained the following errors : " + e.getMessage())
                .response();
    }

}
