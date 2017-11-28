/*******************************************************************************
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.common;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.ui.internal.about.ConfigurationLogDefaultSection;
import org.junit.Test;
import org.osgi.framework.Bundle;

/**
 * Dumps a text file with information about the environment in which the test is
 * executing. This includes the list of all OSGi bundles available from the test
 * context, with their full version, and the Eclipse configuration details.
 * Sometimes useful to identify the exact context in which tests failures
 * occured.
 * 
 * @author pcdavid
 */
@SuppressWarnings("restriction")
public abstract class EnvironmentReportTest {

    private final Bundle testBundle;

    private final String reportName;

    protected EnvironmentReportTest(Bundle testBundle, String suite) {
        this.testBundle = testBundle;
        this.reportName = testBundle.getSymbolicName().replaceAll(Pattern.quote("."), "_") + "-" + suite + ".txt";
    }

    @Test
    public void dump_environment_data() throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(reportName)));
        try {
            out.println("OSGi bundles available from " + toString(testBundle));
            out.println();
            List<String> bundles = new ArrayList<>();
            for (Bundle b : testBundle.getBundleContext().getBundles()) {
                bundles.add(toString(b));
            }
            Collections.sort(bundles);
            for (String s : bundles) {
                out.println(s);
            }
            // Also dump the configuration information from "Help > About >
            // Installation Details > Configuration"
            out.println();
            out.println("Eclipse Configuration Details");
            new ConfigurationLogDefaultSection().write(out);
        } finally {
            out.close();
        }
    }

    private String toString(Bundle b) {
        String name = b.getSymbolicName();
        String version = b.getVersion().toString();

        String state = "";
        switch (b.getState()) {
        case Bundle.ACTIVE:
            state = "[active]";
            break;
        case Bundle.INSTALLED:
            state = "[installed]";
            break;
        case Bundle.RESOLVED:
            state = "[resolved]";
            break;
        case Bundle.STARTING:
            state = "[starting]";
            break;
        case Bundle.STOPPING:
            state = "[stopping]";
            break;
        case Bundle.UNINSTALLED:
            state = "[uninstalled]";
            break;
        }

        String sourceRef = b.getHeaders().get("Eclipse-SourceReferences");
        if (sourceRef == null) {
            sourceRef = "";
        }
        return MessageFormat.format("{0} {1} {2} {3}", name, version, state, sourceRef);
    }
}
