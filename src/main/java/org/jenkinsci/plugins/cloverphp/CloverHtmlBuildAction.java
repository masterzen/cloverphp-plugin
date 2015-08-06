package org.jenkinsci.plugins.cloverphp;

import hudson.model.DirectoryBrowserSupport;
import hudson.model.Action;
import hudson.FilePath;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;

import javax.servlet.ServletException;
import java.io.IOException;

public class CloverHtmlBuildAction implements Action {

    // location of the clover html for each build
    private final FilePath buildReportPath;

    public CloverHtmlBuildAction(FilePath buildReportPath) {
        this.buildReportPath = buildReportPath;
    }

    public String getDisplayName() {
        return Messages.CloverHtmlBuildAction_DisplayName();
    }

    public DirectoryBrowserSupport doDynamic(StaplerRequest req, StaplerResponse rsp)
            throws IOException, ServletException, InterruptedException {
        // backward compatibility 
        // since 0.3
        FilePath path = buildReportPath;
        if (!buildReportPath.exists()) {
            path = buildReportPath.getParent();
        }
        return new DirectoryBrowserSupport(this, path, "Clover Html Report",
                CloverProjectAction.ICON, false);
    }

    public String getIconFileName() {
        return CloverProjectAction.ICON;
    }

    public String getUrlName() {
        return "cloverphp-html";
    }
}
