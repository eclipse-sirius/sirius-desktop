/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.internal.assist;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.contentassist.IProposalProvider;

/**
 * Describes a proposal provider as contributed to the extension point.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class ProposalProviderDescriptor {
    /** Name of the extension point's proposalProvider tag "class" attribute. */
    public static final String PROPOSAL_PROVIDER_ATTRIBUTE_CLASS = "class"; //$NON-NLS-1$

    /**
     * Name of the extension point's proposalProvider tag "interpreter"
     * attribute.
     */
    private static final String PROPOSAL_PROVIDER_ATTRIBUTE_INTERPRETER = "interpreter"; //$NON-NLS-1$

    /** Configuration element of this descriptor. */
    private final IConfigurationElement element;

    /** Qualified class name of this proposal provider. */
    private final String proposalProviderClassName;

    /**
     * ID of the interpreter for which this proposal provider can provide
     * content assist.
     */
    private final String interpreter;

    /**
     * Instantiates a descriptor for the given configuration element.
     * 
     * @param element
     *            The configuration element from which to create this
     *            descriptor.
     */
    public ProposalProviderDescriptor(IConfigurationElement element) {
        this.element = element;
        this.interpreter = element.getAttribute(PROPOSAL_PROVIDER_ATTRIBUTE_INTERPRETER);
        this.proposalProviderClassName = element.getAttribute(PROPOSAL_PROVIDER_ATTRIBUTE_CLASS);
    }

    /**
     * Returns the class name of this proposal provider.
     * 
     * @return The class name of this proposal provider.
     */
    public String getClassName() {
        return proposalProviderClassName;
    }

    /**
     * Returns the identifier of the interpreter for which this can provide
     * content assist.
     * 
     * @return The identifier of the interpreter for which this can provide
     *         content assist.
     */
    public String getInterpreter() {
        return interpreter;
    }

    /**
     * Creates an instance of the described proposal provider.
     * 
     * @return A new instance of the described proposal provider.
     */
    public IProposalProvider createProposalProvider() {
        try {
            Object instance = element.createExecutableExtension(PROPOSAL_PROVIDER_ATTRIBUTE_CLASS);
            if (instance instanceof IProposalProvider) {
                return (IProposalProvider) instance;
            }
        } catch (CoreException e) {
            DslCommonPlugin.getDefault().error(e.getMessage(), e);
        }
        return null;
    }
}
