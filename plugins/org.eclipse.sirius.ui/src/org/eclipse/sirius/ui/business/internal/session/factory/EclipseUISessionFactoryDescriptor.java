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
package org.eclipse.sirius.ui.business.internal.session.factory;

import java.text.MessageFormat;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.sirius.ui.business.api.session.factory.UISessionFactory;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;

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
        this.overrideValue = configuration.getAttribute(UISessionFactoryDescriptor.UI_SESSION_FACTORY_OVERRIDE_ATTRIBUTE);
        this.element = configuration;
    }

    @Override
    public UISessionFactory getUISessionFactory() {
        if (uiSessionFactory == null) {
            if (Platform.isRunning()) {
                try {
                    uiSessionFactory = (UISessionFactory) element.createExecutableExtension(UISessionFactoryDescriptor.UI_SESSION_FACTORY_CLASS_ATTRIBUTE);
                } catch (CoreException e) {
                    SiriusEditPlugin.getPlugin().getLog()
                    .log(new Status(IStatus.ERROR, SiriusEditPlugin.ID, MessageFormat.format(Messages.EclipseUISessionFactoryDescriptor_extensionLoadingError, element.getDeclaringExtension().getUniqueIdentifier()), e));
                }
            }
        }
        return uiSessionFactory;
    }

}
