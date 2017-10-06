package fr.hadrienmp.error_management_styles.support_classes;

import com.jasongoodwin.monads.Try;

import java.util.Optional;

public class UpdateAddressForm {
    private String address = "";
    private String errors = "";

    public UpdateAddressForm(Request request) {
        Optional<String> address = request.formField("address");
        if (address.isPresent()) {
            this.address = address.get();
        } else {
            errors = "missing address";
        }
    }

    public boolean isValid() {
        return errors.isEmpty();
    }

    // TODO HMP tester avec des objets indépendants
    public String errors() {
        return errors;
    }

    public String userId() {
        return null;
    }

    public String address() {
        return address;
    }

    public static Try<UpdateAddressForm> from(Request request) {
        UpdateAddressForm updateAddressForm = new UpdateAddressForm(request);
        if (updateAddressForm.isValid()) {
            return Try.successful(updateAddressForm);
        } else {
            return Try.failure(new BusinessException(updateAddressForm.errors()));
        }
    }
}
