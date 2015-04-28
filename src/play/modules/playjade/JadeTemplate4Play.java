package play.modules.playjade;

import java.util.*;

import play.*;
import play.modules.playjade.jade4jhelpers.*;
import play.templates.*;
import play.vfs.*;
import de.neuland.jade4j.*;
import de.neuland.jade4j.parser.node.*;
import de.neuland.jade4j.template.*;

public class JadeTemplate4Play extends Template {
    
    private final VirtualFile jadeFile;
    
    /**
     * @param jadeFile : VirtualFile (e.g. /app/views/Application/index.jade)
     * @param invokeHtmlPath : String (e.g. /app/views/Application/index.html)
     * @param source : String よくわからない。<br>
     * おそらくGroovyTemplaの場合はcompile()するためにsourceを使っている。とりあえずnullで<br>
     * <br>
     * Play! uses this.name(invokeHtmlPath) as MimeType resolution.
     */
    public JadeTemplate4Play(final VirtualFile jadeFile,
            final String invokeHtmlPath,
            final String source) {
        
        this.jadeFile = jadeFile;
        this.name = invokeHtmlPath;
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
        try {
            final String filePathForJade4j = Play.applicationPath
                    + jadeFile.relativePath();
            final JadeTemplate template = jade4jConfig.getTemplate(filePathForJade4j);
            
            printNodeTree(template.getRootNode(), 0);
            
            addHelperModel(args);
            Logger.warn("models to JadeTemplate %s", args);
            return jade4jConfig.renderTemplate(template, args);
        }
        catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    private void addHelperModel(final Map<String, Object> args) {
        // GroovyTemplate:
        //   @{Application.index}
        //   @{Application.index(userName)}
        // Jade4PlayTemplate:
        //   @.url("Appliaction.index")     // need to write double quotation
        //   @.url("Appliaction.index(userName)")
        args.put("@", new RelativeUrlResolveHelper());
        
        //  @ resolve as relative url.
        // @@ resolve as absolute url.
        args.put("@@", new AbsoluteUrlResolveHelper());
        
        // for Debug
        args.put("R", new RelativeUrlResolveHelper());
        args.put("A", new AbsoluteUrlResolveHelper());
    }
    
    void printNodeTree(final Node node, final int nest) {
        System.out.printf("%3d: [%14s] %s | nodesList.size: %3d | value: %s  \n",
                          nest,
                          node.getClass().getSimpleName(),
                          node.hasBlock()
                                  ? "HasBlock"
                                  : "--------",
                          node.getNodes().size(),
                          node.getValue());
        if (node.hasBlock()) {
            printNodeTree(node.getBlock(), nest + 1);
        }
        for (final Node nodeList : node.getNodes()) {
            printNodeTree(nodeList, nest + 1);
        }
    }
}
