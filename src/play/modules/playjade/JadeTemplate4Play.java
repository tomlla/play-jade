package play.modules.playjade;

import java.util.*;

import play.*;
import play.templates.*;
import de.neuland.jade4j.*;
import de.neuland.jade4j.template.*;

public class JadeTemplate4Play extends Template {
    
    // nameとsourceが実ファイルをさしていなければJateTemplate
    
    public JadeTemplate4Play(final String name, final String source) {
        this.name = name;
        this.source = source;
    }
    
    @Override
    public void compile() {
        // TODO Auto-generated method stub
    }
    
    @Override
    protected String internalRender(final Map<String, Object> args) {
        final JadeConfiguration jade4jConfig = new JadeConfiguration();
        jade4jConfig.setPrettyPrint(true);
        jade4jConfig.setCaching(false);
        System.out.println("==== jade template filename: " + name);
        System.out.println("==== jade template source: " + source);
        System.out.println("==== Model Map : " + args);
        try {
            final String filePathForJade4j = Play.applicationPath + name;
            final JadeTemplate template = jade4jConfig.getTemplate(filePathForJade4j);
            System.out.println("==== Getting JadeTemplate success.");
            return jade4jConfig.renderTemplate(template, args);
        }
        catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }
}
