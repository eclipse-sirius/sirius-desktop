/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.formatdata.tools.internal.util.configuration;

import java.text.MessageFormat;

import org.eclipse.sirius.diagram.formatdata.tools.Messages;
import org.eclipse.sirius.diagram.formatdata.tools.api.util.configuration.Configuration;
import org.eclipse.sirius.diagram.formatdata.tools.api.util.configuration.EdgeConfiguration;
import org.eclipse.sirius.diagram.formatdata.tools.api.util.configuration.NodeConfiguration;

/**
 * Implementation of {@link Configuration}.
 * 
 * @author dlecan
 */
public class ConfigurationImpl implements Configuration {

    private static final boolean DEFAULT_RECURSIVELY = true;

    private NodeConfiguration nodeConfiguration;

    private EdgeConfiguration edgeConfiguration;

    private boolean recursively = DEFAULT_RECURSIVELY;

    /**
     * Constructor.
     */
    public ConfigurationImpl() {
        this.nodeConfiguration = new NodeConfigurationImpl();
        this.edgeConfiguration = new EdgeConfigurationImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EdgeConfiguration getEdgeConfiguration() {
        return edgeConfiguration;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfiguration getNodeConfiguration() {
        return nodeConfiguration;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRecursively(boolean recursively) {
        this.recursively = recursively;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean isRecursive() {
        return recursively;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return MessageFormat.format(Messages.ConfigurationImpl_toString, nodeConfiguration, edgeConfiguration);
    }

}
