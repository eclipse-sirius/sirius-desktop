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
package org.eclipse.sirius.common.ui.business.internal.views.properties.tabbed;

import java.text.MessageFormat;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.sirius.common.ui.Messages;
import org.eclipse.sirius.common.ui.SiriusTransPlugin;
import org.eclipse.sirius.common.ui.business.api.views.properties.tabbed.ILabelProviderProvider;

/**
 * {@link LabelProviderProviderDescriptor} for Eclipse contributions.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class EclipseLabelProviderProviderDescriptor extends AbstractLabelProviderProviderDescriptor implements LabelProviderProviderDescriptor {

    /** Configuration element of this descriptor. */
    private IConfigurationElement element;

    /**
     * Instantiates a descriptor with all information.
     * 
     * @param configuration
     *            Configuration element from which to create this descriptor.
     */
    public EclipseLabelProviderProviderDescriptor(IConfigurationElement configuration) {
        this.id = configuration.getDeclaringExtension().getUniqueIdentifier();
        this.element = configuration;
    }

    @Override
    public ILabelProviderProvider getLabelProviderProvider() {
        if (labelProviderProvider == null) {
            if (Platform.isRunning()) {
                try {
                    labelProviderProvider = (ILabelProviderProvider) element.createExecutableExtension(LABEL_PROVIDER_PROVIDER_CLASS_ATTRIBUTE);
                } catch (CoreException e) {
                    String message = MessageFormat.format(Messages.EclipseLabelProviderProviderDescriptor_errorLoadingExtension, element.getDeclaringExtension().getUniqueIdentifier());
                    SiriusTransPlugin.getPlugin().getLog().log(new Status(IStatus.ERROR, SiriusTransPlugin.PLUGIN_ID, message, e));
                }
            }
        }
        return labelProviderProvider;
    }

}
