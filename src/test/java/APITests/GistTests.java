package APITests;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

public class GistTests extends BaseAPITest{

    @DataProvider
    private Object[][] publicEndpoints(){
        return new Object[][]{
                {"/gists/" + GIST_ID}
        };
    }

    @Test(dataProvider = "publicEndpoints")
    public void verifyThatPublicEndpointsReturns200(String endpoint) throws IOException {

        HttpGet get = new HttpGet(TEST_URL + endpoint);

        response = client.execute(get);

        int responseCode = response.getStatusLine().getStatusCode();

        assertEquals(responseCode, 200);
    }

    @Test
    public void verifyThatCommentCanBeAdd() throws Exception {
        HttpPost request = new HttpPost(TEST_URL + "/gists/" + GIST_ID + "/comments");

        JSONObject jo = new JSONObject();
        jo.put("body","Initial Comment 4");
        String json = jo.toJSONString();

        createHeadersForAuthorizedUser(request);
        request.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));
        response = client.execute(request);

        int actualStatusCode = response.getStatusLine().getStatusCode();
        assertEquals(actualStatusCode, 201);
    }
}
