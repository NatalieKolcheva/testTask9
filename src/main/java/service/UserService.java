package service;

import client.HttpClient;
import io.restassured.response.Response;
import utils.EndpointBuilder;

public class UserService {

    public Response getUser(int userId) {
        String endpoint = new EndpointBuilder().pathParameter("users").pathParameter(userId).get();
        return HttpClient.get(endpoint);
    }

    public Response getUsers() {
        EndpointBuilder endpoint = new EndpointBuilder().pathParameter("users");
        return HttpClient.get(endpoint.get());
    }
}
