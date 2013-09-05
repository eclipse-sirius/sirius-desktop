/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.internal.image;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;

import org.eclipse.sirius.diagram.business.api.image.ImageSelector;
import org.eclipse.sirius.diagram.part.SiriusDiagramEditorPlugin;
import org.eclipse.sirius.provider.SiriusEditPlugin;

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
    public ImageSelector getImageSelector() {
        if (imageSelector == null) {
            if (Platform.isRunning()) {
                try {
                    imageSelector = (ImageSelector) element.createExecutableExtension(IMAGE_SELECTOR_CLASS_ATTRIBUTE);
                } catch (CoreException e) {
                    SiriusEditPlugin.getPlugin().getLog()
                            .log(new Status(IStatus.ERROR, SiriusDiagramEditorPlugin.ID, "Error while loading the extension " + element.getDeclaringExtension().getUniqueIdentifier(), e));
                }
            }
        }
        return imageSelector;
    }

}
