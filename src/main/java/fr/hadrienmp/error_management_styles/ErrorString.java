package fr.hadrienmp.error_management_styles;

import fr.hadrienmp.error_management_styles.support_classes.*;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ErrorString implements EditAddressPage {
    private UpdateAddressService webService;

    public Response updateAddress(Request request) {
        UpdateAddressForm form = new UpdateAddressForm(request);

        if (form.isValid()) {
            String errors = updateUserAddress(form.userId(), form.address());
            if (errors.isEmpty()) {
                return successResponse();
            } else {
                return failResponse(errors);
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


    private String updateUserAddress(String userId, String address) {
        try {
            webService.updateUserAddress(userId, address);
            return "";
        } catch (BusinessException e) {
            return e.errors();
        }
    }

}
