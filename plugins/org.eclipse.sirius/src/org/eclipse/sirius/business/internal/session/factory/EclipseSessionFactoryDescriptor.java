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
package org.eclipse.sirius.business.internal.session.factory;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;

import org.eclipse.sirius.SiriusPlugin;
import org.eclipse.sirius.business.api.session.factory.SessionFactory;

/**
 * {@link SessionFactoryDescriptor} for Eclipse contributions.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class EclipseSessionFactoryDescriptor extends AbstractSessionFactoryDescriptor implements SessionFactoryDescriptor {

    /** Configuration element of this descriptor. */
    private IConfigurationElement element;

    /**
     * Instantiates a descriptor with all information.
     * 
     * @param configuration
     *            Configuration element from which to create this descriptor.
     */
    public EclipseSessionFactoryDescriptor(IConfigurationElement configuration) {
        super();
        this.id = configuration.getDeclaringExtension().getUniqueIdentifier();
        this.overrideValue = configuration.getAttribute(SESSION_FACTORY_OVERRIDE_ATTRIBUTE);
        this.element = configuration;
    }

    /**
     * {@inheritDoc}
     */
    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            if (Platform.isRunning()) {
                try {
                    sessionFactory = (SessionFactory) element.createExecutableExtension(SESSION_FACTORY_CLASS_ATTRIBUTE);
                } catch (CoreException e) {
                    SiriusPlugin.getDefault().getLog()
                            .log(new Status(IStatus.ERROR, SiriusPlugin.ID, "Error while loading the extension " + element.getDeclaringExtension().getUniqueIdentifier(), e));
                }
            }
        }
        return sessionFactory;
    }

}
