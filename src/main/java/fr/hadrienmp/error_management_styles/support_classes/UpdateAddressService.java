package fr.hadrienmp.error_management_styles.support_classes;

public interface UpdateAddressService {
    void updateUserAddress(String userId, String address);

    UpdateAddressService offline = (userId, address) -> {throw new BusinessException("service unavailable");};
    UpdateAddressService alwaysWorking = (userId, address) -> {};
}
