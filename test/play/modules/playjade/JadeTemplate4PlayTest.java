package play.modules.playjade;

import org.junit.*;

import play.*;

public class JadeTemplate4PlayTest extends play.test.UnitTest {
    
    @Test
    public void testPlayUnitTest() {
        if (Play.configuration == null) {
            fail("Play.configurationが初期化されていません。"
                    + "application.confが存在しない可能性があります");
        }
    }
}
