/*******************************************************************************
 * Copyright (c) 2011, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.internal.helper.delete;

import java.text.MessageFormat;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.sirius.business.api.delete.IDeleteHook;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.tools.api.SiriusPlugin;

/**
 * Describes a extension as contributed to the "org.eclipse.sirius.deleteHook" extension point.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class EclipseDeleteHookDescriptor implements IDeleteHookDescriptor {

    /** id of this descriptor. */
    private final String id;

    /** Configuration element of this descriptor. */
    private final IConfigurationElement element;

    /**
     * We only need to create the instance once, this will keep reference to it.
     */
    private IDeleteHook extension;

    /**
     * Instantiates a descriptor with all information.
     * 
     * @param configuration
     *            Configuration element from which to create this descriptor.
     */
    public EclipseDeleteHookDescriptor(IConfigurationElement configuration) {
        this.id = configuration.getAttribute(DELETE_HOOK_ID_ATTRIBUTE);
        this.element = configuration;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IDeleteHook getIDeleteHook() {
        if (extension == null) {
            if (Platform.isRunning()) {
                try {
                    extension = (IDeleteHook) element.createExecutableExtension(DELETE_HOOK_CLASS_ATTRIBUTE);
                } catch (CoreException e) {
                    SiriusPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, SiriusPlugin.ID,
                            MessageFormat.format(Messages.EclipseDeleteHookDescriptor_extensionLoadingErrorMsg, element.getDeclaringExtension().getUniqueIdentifier()), e));
                }
            }
        }
        return extension;
    }
}
