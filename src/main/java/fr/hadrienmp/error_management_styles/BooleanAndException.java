package fr.hadrienmp.error_management_styles;

import fr.hadrienmp.error_management_styles.support_classes.*;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BooleanAndException implements EditAddressPage {
    private UpdateAddressService webService;

    public Response updateAddress(Request request) {
        UpdateAddressForm form = new UpdateAddressForm(request);

        if (form.isValid()) {
            try {
                updateUserAddress(form);
                return successResponse();
            } catch (BusinessException e) {
                return failResponse(e.errors());
            }
        }
        return failResponse(form.errors());
    }

    private void updateUserAddress(UpdateAddressForm form) {
        webService.updateUserAddress(form.userId(), form.address());
    }

    private Response successResponse() {
        return ResponseBuilder.redirectTo(AccountPage.URL)
                .withMessage("You updated your address, congratulations !")
                .response();
    }

    private Response failResponse(String errors) {
        return ResponseBuilder.redirectTo(EditAddressPage.URL)
                .withMessage("Sorry your request was denied, it contained the following errors : " + errors)
                .response();
    }

}
