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
package org.eclipse.sirius.ui.business.internal.session.factory;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;

import org.eclipse.sirius.provider.SiriusEditPlugin;
import org.eclipse.sirius.ui.business.api.session.factory.UISessionFactory;

/**
 * {@link UISessionFactoryDescriptor} for Eclipse contributions.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class EclipseUISessionFactoryDescriptor extends AbstractUISessionFactoryDescriptor implements UISessionFactoryDescriptor {

    /** Configuration element of this descriptor. */
    private IConfigurationElement element;

    /**
     * Instantiates a descriptor with all information.
     * 
     * @param configuration
     *            Configuration element from which to create this descriptor.
     */
    public EclipseUISessionFactoryDescriptor(IConfigurationElement configuration) {
        super();
        this.id = configuration.getDeclaringExtension().getUniqueIdentifier();
        this.overrideValue = configuration.getAttribute(UI_SESSION_FACTORY_OVERRIDE_ATTRIBUTE);
        this.element = configuration;
    }

    /**
     * {@inheritDoc}
     */
    public UISessionFactory getUISessionFactory() {
        if (uiSessionFactory == null) {
            if (Platform.isRunning()) {
                try {
                    uiSessionFactory = (UISessionFactory) element.createExecutableExtension(UI_SESSION_FACTORY_CLASS_ATTRIBUTE);
                } catch (CoreException e) {
                    SiriusEditPlugin.getPlugin().getLog()
                            .log(new Status(IStatus.ERROR, SiriusEditPlugin.ID, "Error while loading the extension " + element.getDeclaringExtension().getUniqueIdentifier(), e));
                }
            }
        }
        return uiSessionFactory;
    }

}
