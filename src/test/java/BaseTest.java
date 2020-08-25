import framework.PageManager;
import org.junit.jupiter.api.BeforeAll;


public class BaseTest {

    @BeforeAll
    public static void loadAllPageModel(){
        PageManager.loadAllPageModel();
    }
}
