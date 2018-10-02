/*******************************************************************************
 * Copyright (c) 2011, 2017 THALES GLOBAL SERVICES and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ecore.extender.business.internal.permission.descriptors;

import java.text.MessageFormat;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionProvider;
import org.eclipse.sirius.ecore.extender.business.internal.ExtenderPlugin;
import org.eclipse.sirius.ecore.extender.business.internal.Messages;
import org.eclipse.sirius.ecore.extender.business.internal.permission.PermissionProviderDescriptor;

/**
 * This descriptor is used to keep track of the Permission providers.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class EclipsePermissionProviderDescriptor extends AbstractPermissionProviderDescriptor implements PermissionProviderDescriptor {

    private static final String PROVIDER_CLASS_ATTR = "providerClass"; //$NON-NLS-1$

    private IConfigurationElement configurationElement;

    /**
     * Create a new descriptor from an Extension Point element.
     * 
     * @param id
     *            unique identifier of this extension
     * @param priority
     *            priority of this extension
     * @param configurationElement
     *            an {@link IConfigurationElement} coming from OSGI.
     */
    public EclipsePermissionProviderDescriptor(String id, int priority, final IConfigurationElement configurationElement) {
        super();
        this.id = id;
        this.priority = priority;
        this.configurationElement = configurationElement;
    }

    /**
     * Overridden to get a instance of the contributed class.
     * 
     * {@inheritDoc}
     */
    public IPermissionProvider getPermissionProvider() {
        if (permissionProvider == null) {
            try {
                permissionProvider = (IPermissionProvider) configurationElement.createExecutableExtension(EclipsePermissionProviderDescriptor.PROVIDER_CLASS_ATTR);
            } catch (final CoreException e) {
                String providerClassName = configurationElement.getAttribute(EclipsePermissionProviderDescriptor.PROVIDER_CLASS_ATTR);
                IContributor contributor = configurationElement.getDeclaringExtension().getContributor();
                IStatus status = new Status(IStatus.ERROR, ExtenderPlugin.ID,
                        MessageFormat.format(Messages.PermissionService_permissionProviderInstantiationError, providerClassName, contributor.getName()), e);
                ExtenderPlugin.getPlugin().getLog().log(status);
            }
        }
        return permissionProvider;
    }

}
