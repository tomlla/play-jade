package play.modules.playjade.jade4jhelpers;

import play.*;
import play.mvc.*;

public class RelativeUrlResolveHelper {
    private static boolean ABSOLUTE_FLAG = false;
    
    public String url(final String action) {
        Logger.warn("RelativeUrlResolver called");
        return Router.reverseWithCheck(action,
                                       Play.getVirtualFile(action),
                                       ABSOLUTE_FLAG);
    }
}
