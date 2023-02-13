import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.*;
public class AuthenticationTests {
    @BeforeAll
    public static void setup(){
        proxy("localhost", 8888);

    }
}
