package context;

import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

public class TestContext {
    private Response response;
    private final Map<String, Object> data = new HashMap<>();

    public void setResponse(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return this.response;
    }

    public void set(String key, Object value) {
        data.put(key, value);
    }

    public <T> T get(String key, Class<T> clazz) {
        return clazz.cast(data.get(key));
    }
}

