import com.idknoo.mispi3help.Returns;
import com.idknoo.mispi3help.areachecker.AreaCheckerBean;
import com.idknoo.mispi3help.values.Values;
import com.idknoo.mispi3help.values.ValuesManagerBean;
import net.sourceforge.jwebunit.html.Row;
import net.sourceforge.jwebunit.html.Table;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

public class Tests {

//    @Before
//    public void prepare() {
//        setScriptingEnabled(false);
//        setBaseUrl("http://localhost:8080/mispi3_war/");
//    }
//
//    @Test
//    public void testWelcomePage() {
//        beginAt("index.xhtml");
//        assertElementPresent("restimeParent");
//        assertTextPresent("Дворянчикова Дарья P3233");
//        assertTitleEquals("Web3");
//    }
//
//    @Test
//    public void testMainPage() {
//        beginAt("main.xhtml");
//        assertElementPresent("errWindowDiv");
//        assertElementPresent("xDiv");
//        assertElementPresent("yDiv");
//        assertElementPresent("rDiv");
//        assertElementPresent("checkButtonParent");
//        assertTitleEquals("Main");
//        assertElementPresent("checkButtonParent");
//        assertButtonPresent("calcForm:buttonCheck");
//        assertButtonPresent("calcForm:Back");
//
//    }
//
//    @Test
//    public void testNavigation() {
//        beginAt("index.xhtml");
//        assertTitleEquals("Web3");
//        clickButton("Click:main");
//        assertTitleEquals("Main");
//    }

    @Test
    public void testYes() {
        AreaCheckerBean checker = new AreaCheckerBean();
        Values values = new Values(1, 0.5, 2, new Date());
        Assert.assertTrue(checker.checkArea(values));
    }

    @Test
    public void testNo() {
        AreaCheckerBean checker = new AreaCheckerBean();
        Values values = new Values(-1, 0.5, 2, new Date());
        Assert.assertFalse(checker.checkArea(values));
    }
}