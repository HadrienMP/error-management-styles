package fr.hadrienmp.error_management_styles;

import fr.hadrienmp.error_management_styles.support_classes.AccountPage;
import fr.hadrienmp.error_management_styles.support_classes.Request;
import fr.hadrienmp.error_management_styles.support_classes.Response;
import fr.hadrienmp.error_management_styles.support_classes.UpdateAddressService;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import static fr.hadrienmp.error_management_styles.support_classes.ResponseBuilder.redirectTo;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class EditAddressPageTest {

    private static final String ERROR = "Sorry your request was denied, it contained the following errors : ";
    private static final Request A_VALID_FORM = new RequestBuilder().withField("address", "an address").build();

    @Test
    @Parameters(method = "editAddressPages")
    public void should_redirect_to_the_account_page(EditAddressPage page) throws Exception {
        Response response = page.updateAddress(A_VALID_FORM);

        Response expectedResponse = redirectTo(AccountPage.URL)
                .withMessage("You updated your address, congratulations !")
                .response();
        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    @Parameters(method = "editAddressPages")
    public void should_redirect_to_the_edit_adress_page_when_the_form_is_invalid(EditAddressPage page) throws Exception {
        Response response = page.updateAddress(new RequestBuilder().build());

        Response expectedResponse = redirectTo(EditAddressPage.URL)
                .withMessage(ERROR + "missing address")
                .response();
        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    @Parameters(method = "editAddressPagesWithOfflineService")
    public void should_redirect_to_the_edit_adress_page_when_the_update_fails(EditAddressPage page) throws Exception {
        Response response = page.updateAddress(A_VALID_FORM);

        Response expectedResponse = redirectTo(EditAddressPage.URL)
                .withMessage(ERROR + "service unavailable")
                .response();
        assertThat(response).isEqualTo(expectedResponse);
    }

    public List<EditAddressPage> editAddressPages() {
        return Arrays.asList(
                new BooleanAndException(UpdateAddressService.alwaysWorking),
                new Booleans(UpdateAddressService.alwaysWorking),
                new ErrorString(UpdateAddressService.alwaysWorking),
                new Exceptions(UpdateAddressService.alwaysWorking),
                new Monad(UpdateAddressService.alwaysWorking)
        );
    }

    public List<EditAddressPage> editAddressPagesWithOfflineService() {
        return Arrays.asList(
                new Monad(UpdateAddressService.offline),
                new Booleans(UpdateAddressService.offline),
                new BooleanAndException(UpdateAddressService.offline),
                new ErrorString(UpdateAddressService.offline),
                new Exceptions(UpdateAddressService.offline)
        );
    }
}