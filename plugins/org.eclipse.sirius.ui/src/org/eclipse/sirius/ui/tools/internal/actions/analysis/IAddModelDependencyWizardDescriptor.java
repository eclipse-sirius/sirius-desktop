/*******************************************************************************
 * Copyright (c) 2011, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.actions.analysis;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.sirius.common.ui.SiriusTransPlugin;
import org.eclipse.sirius.ui.tools.api.actions.analysis.IAddModelDependencyWizard;

/**
 * Describes a extension as contributed to the
 * {@link IAddModelDependencyWizardRegistryListener#MODEL_DEPENDENCY_WIZARD_EXTENSION_POINT}
 * extension point.
 *
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 *
 */
public class IAddModelDependencyWizardDescriptor {

    /**
     * Name of the attribute corresponding to the contributed class's path.
     */
    public static final String RESOURCE_WIZARD_EXTENSION_CONTRIBUTED_CLASS_NAME = "class"; //$NON-NLS-1$

    /**
     * Configuration element of this descriptor .
     */
    private final IConfigurationElement element;

    /**
     * The path of the contributed class.
     */
    private String extensionClassName;

    /**
     * The {@link IAddModelDependencyWizard} described by this descriptor.
     */
    private IAddModelDependencyWizard extension;

    /**
     * Instantiates a descriptor with all information.
     *
     * @param configuration
     *            Configuration element from which to create this descriptor.
     */
    public IAddModelDependencyWizardDescriptor(IConfigurationElement configuration) {
        element = configuration;
        extensionClassName = configuration.getAttribute(IAddModelDependencyWizardDescriptor.RESOURCE_WIZARD_EXTENSION_CONTRIBUTED_CLASS_NAME);
    }

    /**
     * Creates an instance of this descriptor's
     * {@link IAddModelDependencyWizard} .
     *
     * @return A new instance of this descriptor's
     *         {@link IAddModelDependencyWizard}.
     */
    public IAddModelDependencyWizard getWizard() {
        try {
            extension = (IAddModelDependencyWizard) element.createExecutableExtension(IAddModelDependencyWizardDescriptor.RESOURCE_WIZARD_EXTENSION_CONTRIBUTED_CLASS_NAME);
        } catch (CoreException e) {
            SiriusTransPlugin.INSTANCE.error(e.getMessage(), e);
        }
        return extension;
    }

    /**
     * Returns the fully qualified name of the contributed class.
     *
     * @return the fully qualified name of the contributed class
     */
    public String getExtensionClassName() {
        return extensionClassName;
    }

}
