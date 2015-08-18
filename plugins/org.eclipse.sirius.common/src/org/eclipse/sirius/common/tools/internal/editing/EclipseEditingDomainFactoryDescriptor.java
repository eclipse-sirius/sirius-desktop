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
package org.eclipse.sirius.common.tools.internal.editing;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;

import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.editing.EditingDomainFactoryDescriptor;
import org.eclipse.sirius.common.tools.api.editing.IEditingDomainFactory;

/**
 * Describes a extension as contributed to the
 * "org.eclipse.sirius.common.editingDomainFactory" extension point.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 * 
 * @since 0.9.0
 */
public class EclipseEditingDomainFactoryDescriptor implements EditingDomainFactoryDescriptor {

    /**
     * Name of the editingDomainFactory extension point's tag "extension"
     * attribute.
     */
    public static final String EDITING_DOMAIN_FACTORY_CLASS_ATTRIBUTE = "class"; //$NON-NLS-1$

    /** id of this descriptor. */
    private final String id;
    
    /** Configuration element of this descriptor. */
    private final IConfigurationElement element;

    /**
     * the override attribute value of this {@link IEditingDomainFactory}.
     */
    private String overrideValue;

    /**
     * We only need to create the instance once, this will keep reference to it.
     */
    private IEditingDomainFactory extension;

    /**
     * Instantiates a descriptor with all information.
     * 
     * @param id Id of this descriptor
     * 
     * @param configuration
     *            Configuration element from which to create this descriptor.
     * @param overrideValue
     *            the override attribute value, id of the extension to override
     *            or null if any to override
     */
    public EclipseEditingDomainFactoryDescriptor(String id, String overrideValue, IConfigurationElement configuration) {
        this.id = id;
        this.overrideValue = overrideValue;
        this.element = configuration;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getOverrideValue() {
        return overrideValue;
    }

    @Override
    public IEditingDomainFactory getEditingDomainFactory() {
        if (extension == null) {
            if (Platform.isRunning()) {
                try {
                    extension = (IEditingDomainFactory) element.createExecutableExtension(EDITING_DOMAIN_FACTORY_CLASS_ATTRIBUTE);
                } catch (CoreException e) {
                    DslCommonPlugin.getDefault().getLog()
                            .log(new Status(IStatus.ERROR, DslCommonPlugin.PLUGIN_ID, "Error while loading the extension " + element.getDeclaringExtension().getUniqueIdentifier(), e));
                }
            }
        }
        return extension;
    }
}
