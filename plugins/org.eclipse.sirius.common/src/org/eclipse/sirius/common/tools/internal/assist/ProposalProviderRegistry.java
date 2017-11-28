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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.sirius.common.tools.api.contentassist.IProposalProvider;
import org.eclipse.sirius.common.tools.api.interpreter.CompoundInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;

import com.google.common.collect.Lists;

/**
 * This will contain all of the proposal providers that can be used by the
 * interpreters.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public final class ProposalProviderRegistry {
    /** List of providers parsed from the extension point. */
    private static final List<ProposalProviderDescriptor> DESCRIPTORS = new ArrayList<>();

    /** Utility classes don't need a default constructor. */
    private ProposalProviderRegistry() {
        // Hides default constructor
    }

    /**
     * Adds a proposal provider to the registry.
     * 
     * @param descriptor
     *            The descriptor that is to be added to the registry.
     */
    public static void addProvider(ProposalProviderDescriptor descriptor) {
        DESCRIPTORS.add(descriptor);
    }

    /** This can be used to clear the registry of all contribution. */
    public static void clearRegistry() {
        DESCRIPTORS.clear();
    }

    /**
     * Returns a list of all available proposal providers.
     * 
     * @return A list of all available proposal providers.
     */
    public static List<IProposalProvider> getAllProviders() {
        final List<IProposalProvider> providers = new ArrayList<>();

        for (ProposalProviderDescriptor descriptor : DESCRIPTORS) {
            providers.add(descriptor.createProposalProvider());
        }

        return providers;
    }

    /**
     * Tries and find all of the proposal providers registered against the given
     * interpreter's contribution.
     * 
     * @param interpreter
     *            The interpreter for which we need all proposal providers.
     * @return All proposal providers associated with the given interpreter if
     *         any, an empty list if none.
     */
    public static List<IProposalProvider> getProvidersFor(IInterpreter interpreter) {
        if (interpreter == null) {
            return Collections.emptyList();
        }

        final List<IProposalProvider> providers;
        final String interpreterID = CompoundInterpreter.INSTANCE.getInterpreterID(interpreter);
        if (interpreterID != null) {
            providers = getProvidersFor(interpreterID);
        } else {
            providers = Collections.emptyList();
        }

        return providers;
    }

    /**
     * Returns all proposal providers associated with the given interpreter.
     * 
     * @param interpreter
     *            Unique identifier of the interpreter for which we need a
     *            proposal provider.
     * @return All proposal providers associated with the given interpreter if
     *         any, an empty list if none.
     */
    public static List<IProposalProvider> getProvidersFor(String interpreter) {
        if (interpreter == null || interpreter.length() <= 0) {
            return Collections.emptyList();
        }

        final List<IProposalProvider> providers = new ArrayList<>();

        for (ProposalProviderDescriptor descriptor : DESCRIPTORS) {
            if (interpreter.equals(descriptor.getInterpreter())) {
                final IProposalProvider provider = descriptor.createProposalProvider();
                if (provider != null) {
                    providers.add(provider);
                }
            }
        }

        return providers;
    }

    /**
     * Removes a proposal provider from the registry.
     * 
     * @param className
     *            Qualified class name of the provider that is to be removed
     *            from the registry.
     */
    public static void removeProvider(String className) {
        for (ProposalProviderDescriptor descriptor : Lists.newArrayList(DESCRIPTORS)) {
            if (className.equals(descriptor.getClassName())) {
                DESCRIPTORS.remove(descriptor);
            }
        }
    }
}
