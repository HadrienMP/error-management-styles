package fr.hadrienmp.error_management_styles;

import fr.hadrienmp.error_management_styles.support_classes.AccountPage;
import fr.hadrienmp.error_management_styles.support_classes.Request;
import fr.hadrienmp.error_management_styles.support_classes.Response;
import fr.hadrienmp.error_management_styles.support_classes.UpdateAddressWebService;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import static fr.hadrienmp.error_management_styles.support_classes.ResponseBuilder.redirectTo;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class MonadsTest {

    private static final String ERROR = "Sorry your request was denied, it contained the following errors : ";
    private static final Request A_VALID_FORM = new RequestBuilder().withField("address", "an address").build();

    @Test
    @Parameters(method = "editAddressPages")
    public void should_redirect_to_the_account_page(EditAdressPage page) throws Exception {
        Response response = page.updateAddress(A_VALID_FORM);

        Response expectedResponse = redirectTo(AccountPage.URL).withMessage("You updated your address, congratulations !").response();
        assertThat(response).isEqualTo(expectedResponse);
    }

    public List<EditAdressPage> editAddressPages() {
        return Arrays.asList(
                new Monads2(UpdateAddressWebService.alwaysWorking),
                new Monads(UpdateAddressWebService.alwaysWorking)
        );
    }

    @Test
    public void should_redirect_to_the_edit_adress_page_when_the_form_is_invalid() throws Exception {
        EditAdressPage monads2 = new Monads2(UpdateAddressWebService.offline);

        Response response = monads2.updateAddress(new RequestBuilder().build());

        Response expectedResponse = redirectTo(EditAdressPage.URL).withMessage(ERROR + "missing address").response();
        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    public void should_redirect_to_the_edit_adress_page_when_the_update_fails() throws Exception {
        EditAdressPage monads2 = new Monads2(UpdateAddressWebService.offline);

        Response response = monads2.updateAddress(A_VALID_FORM);

        Response expectedResponse = redirectTo(EditAdressPage.URL).withMessage(ERROR + "service unavailable").response();
        assertThat(response).isEqualTo(expectedResponse);
    }
}