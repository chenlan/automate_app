import org.junit.jupiter.api.Test;
import page.App;
import page.SearchPage;

public class SearchTest extends BaseTest{
    private SearchPage searchPage =App.getInstance().toSearch();

    @Test
    public void Search_Test(String keyWord){
        searchPage.search(keyWord);
    }
}
