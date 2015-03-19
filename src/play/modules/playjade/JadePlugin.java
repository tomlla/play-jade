package play.modules.playjade;

import org.apache.commons.io.*;

import play.*;
import play.templates.*;
import play.vfs.*;

public class JadePlugin extends PlayPlugin {
    
    @Override
    public Template loadTemplate(final VirtualFile file) {
        if (isJadable(file)) {
            return execJadingVer01(file);
        }
        return null;
    }
    
    private Template execJadingVer01(final VirtualFile invokedHtmlFile) {
        final VirtualFile jadeFile = getJadeFilePath(invokedHtmlFile);
        final String templateName = invokedHtmlFile.relativePath();
        final String templateSource = null;
        final JadeTemplate4Play jade4playTemplate = new JadeTemplate4Play(jadeFile,
                                                                          templateName,
                                                                          templateSource);
        return jade4playTemplate;
    }
    
    boolean isJadable(final VirtualFile file) {
        if (file.getName().endsWith(".html")) {
            if (getJadeFilePath(file).exists()) {
                return true;
            }
        }
        return false;
    }
    
    private VirtualFile getJadeFilePath(final VirtualFile htmlFile) {
        final String jadeFilePath = FilenameUtils.removeExtension(htmlFile.getRealFile()
                                                                          .getAbsolutePath())
                + ".jade";
        final VirtualFile vfile = VirtualFile.open(jadeFilePath);
        return vfile;
    }
    
}
