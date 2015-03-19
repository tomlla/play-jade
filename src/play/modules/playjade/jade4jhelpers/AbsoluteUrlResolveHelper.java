package play.modules.playjade.jade4jhelpers;

import play.*;
import play.mvc.*;

public class AbsoluteUrlResolveHelper {
    private static boolean ABSOLUTE_FLAG = true;
    
    public String url(final String action) {
        Logger.warn("AbsoluteUrlResolver called");
        return Router.reverseWithCheck(action,
                                       Play.getVirtualFile(action),
                                       ABSOLUTE_FLAG);
    }
}
