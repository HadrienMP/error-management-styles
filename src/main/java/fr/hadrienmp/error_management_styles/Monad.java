package fr.hadrienmp.error_management_styles;

import com.jasongoodwin.monads.Try;
import fr.hadrienmp.error_management_styles.support_classes.*;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Monad implements EditAddressPage {
    private UpdateAddressService webService;

    @Override
    public Response updateAddress(Request request) {
        return UpdateAddressForm.from(request)
                .flatMap(form -> updateUserAddress(form.userId(), form.address()))
                .map(this::successResponse)
                .recover(this::failResponse);
    }

    private Response successResponse(Void v) {
        return ResponseBuilder.redirectTo(AccountPage.URL)
                .withMessage("You updated your address, congratulations !")
                .response();
    }

    private Response failResponse(Throwable e) {
        return ResponseBuilder.redirectTo(EditAddressPage.URL)
                .withMessage("Sorry your request was denied, it contained the following errors : " + e.getMessage())
                .response();
    }

    private Try<Void> updateUserAddress(String userId, String address) {
        return Try.ofFailable(() -> {
            webService.updateUserAddress(userId, address);
            return null;
        });
    }

}
