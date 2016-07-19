package com.kalix.framework.plugin.cg;

import com.kalix.framework.plugin.cg.api.IGenerate;
import com.kalix.framework.plugin.cg.impl.BizGenerateImpl;
import com.thoughtworks.qdox.JavaProjectBuilder;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import java.io.File;

/**
 * Created by sunlf on 2015/9/18.
 *
 * @goal biz
 * @phase compile
 * @threadSafe
 */
public class BizMojo extends AbstractBaseKalixMojo {

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        this.docBuilder = new JavaProjectBuilder();
        for (String r : sources) {
            docBuilder.addSourceTree(new File(r));
        }
        // first, find input directory and files it contains
        if (!inputDir.exists()) {
            throw new MojoExecutionException("Input directory '" + inputDir.getAbsolutePath() + "' does not exist");
        }

        //create biz code generate
        IGenerate bizGenerate = new BizGenerateImpl(attributes, inputDir, outputDir);
        bizGenerate.genJavaSource();

    }
}
