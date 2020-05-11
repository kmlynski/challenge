package APITests;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;


public class BaseAPITest {
    CloseableHttpClient client;
    CloseableHttpResponse response;
    static String TEST_URL;
    static String USERNAME;
    static String GIST_ID;
    static String GIST_TOKEN;

    @BeforeSuite
    @SuppressWarnings("checked")
    public void setUp() throws Exception {
        JSONParser parser = new JSONParser();
        JSONObject config = (JSONObject) parser.parse(new FileReader("src/test/resources/conf/config.conf.json"));

        USERNAME = (String) config.get("testusername");
        TEST_URL = (String) config.get("server");
        GIST_ID = (String) config.get("gist_id");
        GIST_TOKEN = (String) config.get("token");
    }

    public String getOAuthToken() {
        String auth = USERNAME + ":" + GIST_TOKEN;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("ISO--8859-1")));
        return "Basic " + new String(encodedAuth);
    }

    public void createHeadersForAuthorizedUser(HttpPost request) {
        request.setHeader(HttpHeaders.AUTHORIZATION, getOAuthToken());
    }

    @BeforeMethod
    public void SetUp(Method testMethod){
        System.out.println("Starting test: " + testMethod.getName()+ "\n");

        client = HttpClientBuilder.create().build();
    }

    @AfterMethod
    public void closeResources() throws IOException {
        client.close();
        response.close();
    }
}
