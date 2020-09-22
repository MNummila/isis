package org.apache.isis.extensions.commandreplay.secondary.analysis;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.OrderPrecedence;
import org.apache.isis.extensions.commandlog.impl.jdo.CommandJdo;
import org.apache.isis.extensions.commandreplay.secondary.analyser.CommandReplayAnalyser;

import lombok.extern.log4j.Log4j2;

@Service
@Named("isisExtensionsCommandReplaySecondary.CommandReplayAnalysisService")
@Order(OrderPrecedence.MIDPOINT)
@Log4j2
public class CommandReplayAnalysisService {

    /**
     * if hit an issue with the command having been replayed, then mark this
     * as in error.
     * This will effectively block the running of any further commands until the adminstrator fixes the issue.
     */
    public void analyse(final CommandJdo commandJdo) {
        final String analysis = analyseReplay(commandJdo);

        commandJdo.saveAnalysis(analysis);
    }

    private String analyseReplay(final CommandJdo commandJdo) {

        for (final CommandReplayAnalyser analyser : analysers) {
            try {
                String reason = analyser.analyzeReplay(commandJdo);
                if (reason != null) {
                    return reason;
                }
            } catch(Exception ex) {
                final String className = analyser.getClass().getName();
                log.warn("{} threw exception: ", className, ex);
                return className + " threw exception: " + ex.getMessage();
            }
        }
        return null;
    }

    @Inject List<CommandReplayAnalyser> analysers;

}