/*******************************************************************************
 * Copyright (c) 2012, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.business.internal.image;

import java.text.MessageFormat;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.sirius.diagram.tools.api.DiagramPlugin;
import org.eclipse.sirius.diagram.ui.business.api.image.ImageSelector;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;

/**
 * {@link ImageSelectorDescriptor} for Eclipse contributions.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class EclipseImageSelectorDescriptor extends AbstractImageSelectorDescriptor implements ImageSelectorDescriptor {

    /** Configuration element of this descriptor. */
    private IConfigurationElement element;

    /**
     * Instantiates a descriptor with all information.
     * 
     * @param configuration
     *            Configuration element from which to create this descriptor.
     */
    public EclipseImageSelectorDescriptor(IConfigurationElement configuration) {
        super();
        this.id = configuration.getDeclaringExtension().getUniqueIdentifier();
        this.overrideValue = configuration.getAttribute(IMAGE_SELECTOR_OVERRIDE_ATTRIBUTE);
        this.element = configuration;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImageSelector getImageSelector() {
        if (imageSelector == null) {
            if (Platform.isRunning()) {
                try {
                    imageSelector = (ImageSelector) element.createExecutableExtension(IMAGE_SELECTOR_CLASS_ATTRIBUTE);
                } catch (CoreException e) {
                    SiriusEditPlugin.getPlugin().getLog().log(new Status(IStatus.ERROR, DiagramPlugin.ID,
                            MessageFormat.format(Messages.EclipseImageSelectorDescriptor_extensionLoadingError, element.getDeclaringExtension().getUniqueIdentifier()), e));
                }
            }
        }
        return imageSelector;
    }

}
