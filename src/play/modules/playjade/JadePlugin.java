package play.modules.playjade;

import org.apache.commons.io.*;

import play.*;
import play.templates.*;
import play.vfs.*;

public class JadePlugin extends PlayPlugin {
    
    static {
        System.out.println("====JadePlugin loadded-==");
    }
    
    @Override
    public Template loadTemplate(final VirtualFile file) {
        if (isJadable(file)) {
            final VirtualFile jadeFilePath = getJadeFilePath(file);
            return execJadingVer01(jadeFilePath);
        }
        return null;
    }
    
    private Template execJadingVer01(final VirtualFile jadeFile) {
        //return new GroovyTemplateCompiler().compile(jadeFilePath);
        
        final String templateName = jadeFile.relativePath();
        final String templateSource = null;
        final JadeTemplate4Play jade4playTemplate = new JadeTemplate4Play(templateName,
                                                                          templateSource);
        
        return jade4playTemplate;
    }
    
    boolean isJadable(final VirtualFile file) {
        if (file.getName().endsWith(".html")) {
            Logger.info("file.relativePath() > " + file.relativePath());
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
