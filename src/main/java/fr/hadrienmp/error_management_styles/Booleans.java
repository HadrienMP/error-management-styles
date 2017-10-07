package fr.hadrienmp.error_management_styles;

import fr.hadrienmp.error_management_styles.support_classes.*;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Booleans implements EditAddressPage {
    private UpdateAddressService webService;

    public Response updateAddress(Request request) {
        UpdateAddressForm form = new UpdateAddressForm(request);

        if (form.isValid()) {
            boolean updateSucceeded = updateUserAddress(form.userId(), form.address());
            if (updateSucceeded) {
                return successResponse();
            } else {
                return failResponse("service unavailable");
            }
        } else {
            return failResponse(form.errors());
        }
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

    private boolean updateUserAddress(String userId, String address) {
        try {
            webService.updateUserAddress(userId, address);
            return true;
        } catch (BusinessException e) {
            return false;
        }
    }

}
