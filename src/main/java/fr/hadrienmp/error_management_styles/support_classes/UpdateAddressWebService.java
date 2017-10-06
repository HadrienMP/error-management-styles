package fr.hadrienmp.error_management_styles.support_classes;

import com.jasongoodwin.monads.Try;

@FunctionalInterface
public interface UpdateAddressWebService {
    UpdateAddressWebService offline = (userId, address) -> Try.failure(new BusinessException("service unavailable"));
    UpdateAddressWebService alwaysWorking = (userId, address) -> Try.successful(null);

    Try<Void> tryUpdateUserAddress(String userId, String address);
}
