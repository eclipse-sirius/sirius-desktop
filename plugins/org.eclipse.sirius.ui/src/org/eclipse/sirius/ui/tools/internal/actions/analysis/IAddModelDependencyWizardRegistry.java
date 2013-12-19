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
package org.eclipse.sirius.ui.tools.internal.actions.analysis;

import java.util.Collection;
import java.util.Set;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.tools.api.actions.analysis.IAddModelDependencyWizard;

import com.google.common.collect.Sets;

/**
 * Registry containing all the {@link IAddModelDependencyWizard}s that have been
 * parsed from the
 * {@link IAddModelDependencyWizardRegistryListener#MODEL_DEPENDENCY_WIZARD_EXTENSION_POINT}
 * extension point.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * 
 */
public final class IAddModelDependencyWizardRegistry {

    /**
     * The registered {@link IAddModelDependencyWizard}s.
     */
    private static final Collection<IAddModelDependencyWizardDescriptor> WIZARDS = Sets.newLinkedHashSet();

    /**
     * Utility classes don't need a default constructor.
     */
    private IAddModelDependencyWizardRegistry() {

    }

    /**
     * Adds an extension to the registry, with the given behavior.
     * 
     * @param extension
     *            The extension that is to be added to the registry
     */
    public static void addWizard(IAddModelDependencyWizardDescriptor extension) {
        WIZARDS.add(extension);
    }

    /**
     * Removes all extensions from the registry. This will be called at plugin
     * stopping.
     */
    public static void clearRegistry() {
        WIZARDS.clear();
    }

    /**
     * Returns the {@link IAddModelDependencyWizard} to use, according to the
     * given available sessions.
     * 
     * @param availableSessions
     *            the available sessions
     * @return the {@link IAddModelDependencyWizard} to use, according to the
     *         given available sessions
     */
    public static IAddModelDependencyWizard getCreateOrAddModelDependencyWizard(Collection<Session> availableSessions) {
        for (IAddModelDependencyWizardDescriptor descriptor : getRegisteredExtensions()) {
            IAddModelDependencyWizard wizard = descriptor.getWizard();
            if (wizard.canApply(availableSessions)) {
                return wizard;
            }
        }

        return null;
    }

    /**
     * Returns a copy of the registered extensions list.
     * 
     * @return A copy of the registered extensions list.
     */
    private static Collection<IAddModelDependencyWizardDescriptor> getRegisteredExtensions() {
        Set<IAddModelDependencyWizardDescriptor> registeredExtensions = Sets.newHashSet();
        for (IAddModelDependencyWizardDescriptor extension : WIZARDS) {
            registeredExtensions.add(extension);
        }
        return registeredExtensions;
    }

    /**
     * Removes a phantom from the registry.
     * 
     * @param extensionClassName
     *            Qualified class name of the sync element which corresponding
     *            phantom is to be removed from the registry.
     */
    public static void removeExtension(String extensionClassName) {
        for (IAddModelDependencyWizardDescriptor extension : getRegisteredExtensions()) {
            if (extension.getExtensionClassName().equals(extensionClassName)) {
                WIZARDS.remove(extension);
            }
        }
    }
}
