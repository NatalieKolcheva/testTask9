import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import service.UserService;

public class RestApiTest {

    private static final Logger LOG = Logger.getLogger(RestApiTest.class);


    @Test(description = "Positive, GET all users")
    public void getAllUsersPositive() {
        Response response = getAllUsers();
        verifyResponseStatus(response.getStatusCode(), 200);
        ResponseBody responseBody = response.getBody();
        verifyResponseBodyIsNotEmpty(responseBody);
    }

    @Test(description = "Positive, GET User by ID")
    public void GetByIdUserPositive() {
        Integer testId = 2073;
        Response response = getUserById(testId);
        verifyResponseStatus(response.getStatusCode(), 200);
        ResponseBody responseBody = response.getBody();
        verifyResponseBodyIsNotEmpty(responseBody);
    }

    @Test(description = "Negative, GET User by ID")
    public void GetByIdGenreNegative() {
        Response response = getUserById(0);
        verifyResponseStatus(response.getStatusCode(), 404);
    }

    //Steps
    public String getResponseBody(ResponseBody responseBody) {
        return responseBody.asString();
    }

    public Response getAllUsers() {
        LOG.info("Get all users request.");
        Response response = new UserService().getUsers();
        LOG.info("Get all users response: " + getResponseBody(response.getBody()));
        return response;
    }

    public Response getUserById(Integer id) {
        Response response = new UserService().getUser(id);
        LOG.info("Get genre by id response: " + getResponseBody(response.getBody()));
        return response;
    }

    public void verifyResponseStatus(int actual, int expected) {
        Assert.assertEquals(actual, expected, "Invalid status code.");
    }

    public void verifyResponseBodyIsNotEmpty(ResponseBody responseBody) {
        Assert.assertTrue(responseBody != null, "Response body is empty.");
    }
}
