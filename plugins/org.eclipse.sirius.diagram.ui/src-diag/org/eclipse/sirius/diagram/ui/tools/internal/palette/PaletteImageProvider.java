/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.palette;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.common.tools.api.resource.FileProvider;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription;
import org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription;
import org.eclipse.sirius.diagram.description.tool.NodeCreationDescription;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.MetaClassNotFoundException;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.swt.graphics.Image;

/**
 * Provides image for the palette.
 * 
 * @author ymortier
 */
public class PaletteImageProvider {

    private static final String ICON_PATH = "iconPath"; //$NON-NLS-1$

    /**
     * Returns the image descriptor from corresponding to the tool description
     * argument.
     * 
     * @param abstractToolDescription
     *            the tool description.
     * @return the image.
     */
    public ImageDescriptor getImageDescriptor(final AbstractToolDescription abstractToolDescription) {
        String path = null;
        if (abstractToolDescription.eClass().getEStructuralFeature(ICON_PATH) != null) {
            path = (String) abstractToolDescription.eGet(abstractToolDescription.eClass().getEStructuralFeature(ICON_PATH));
        }
        if (path == null || StringUtil.isEmpty(path.trim())) {
            /* try to find the image descriptor with mappings */
            final String domainClassToUse = PaletteImageProvider.getDomainClass(PaletteImageProvider.mappings(abstractToolDescription));
            if (domainClassToUse != null) {
                /*
                 * we can find the image of the tool with the domain class :)
                 */
                try {
                    final EObject anInstance = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(abstractToolDescription).createInstance(domainClassToUse);
                    return DiagramUIPlugin.getPlugin().getItemImageDescriptor(anInstance);
                } catch (final MetaClassNotFoundException e) {
                    SiriusPlugin.getDefault().warning("No icon is available for the tool " + abstractToolDescription.getName() + ". A default icon has been set instead.", null);
                }
            }
            if (abstractToolDescription instanceof EdgeCreationDescription) {
                path = DiagramImagesPath.PALETTE_EDGE_PATH;
            } else {
                path = DiagramImagesPath.PALETTE_FACTORY_DEFAULT_PATH;
            }
        }

        final Image res = getImageFromPath(path);

        ImageDescriptor desc;

        if (res == null) {
            desc = DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.PALETTE_FACTORY_ERROR_PATH);
        } else {
            desc = ImageDescriptor.createFromImage(res);
        }
        return desc;
    }

    private Image getImageFromPath(final String path) {

        final File imageFile = FileProvider.getDefault().getFile(new Path(path));
        ImageDescriptor desc = null;
        if (imageFile != null && imageFile.exists() && imageFile.canRead()) {
            try {
                desc = DiagramUIPlugin.Implementation.findImageDescriptor(imageFile.toURI().toURL());
            } catch (MalformedURLException e) {
                // do nothing
            }
        }
        return flyWeightImage(desc);
    }

    private Image flyWeightImage(final ImageDescriptor desc) {
        if (desc != null) {
            return DiagramUIPlugin.getPlugin().getImage(desc);
        } else {
            return null;
        }
    }

    private static List<? extends DiagramElementMapping> mappings(final AbstractToolDescription tool) {

        List<? extends DiagramElementMapping> result;

        if (tool instanceof NodeCreationDescription) {
            result = ((NodeCreationDescription) tool).getNodeMappings();
        } else if (tool instanceof EdgeCreationDescription) {
            result = ((EdgeCreationDescription) tool).getEdgeMappings();
        } else if (tool instanceof ContainerCreationDescription) {
            result = ((ContainerCreationDescription) tool).getContainerMappings();
        } else {
            result = Collections.emptyList();
        }
        return result;
    }

    private static String getDomainClass(final List<? extends DiagramElementMapping> mappings) {

        String domainClassToUse = null;

        boolean again = true;
        final Iterator<? extends DiagramElementMapping> itMappings = mappings.iterator();

        while (itMappings.hasNext() && again) {
            final DiagramElementMapping currentMapping = itMappings.next();
            String domainClass = PaletteImageProvider.getDomainClass(currentMapping);
            if (domainClass != null && !StringUtil.isEmpty(domainClass.trim())) {
                if (domainClassToUse == null || domainClassToUse.equals(domainClass)) {
                    domainClassToUse = domainClass;
                } else {
                    domainClassToUse = null;
                    again = false;
                }
            } else {
                domainClass = null;
                again = false;
            }
        }

        return domainClassToUse;
    }

    private static String getDomainClass(final DiagramElementMapping mapping) {

        String domainClass = null;

        if (mapping instanceof NodeMapping) {
            domainClass = ((NodeMapping) mapping).getDomainClass();
        } else if (mapping instanceof EdgeMapping) {
            final EdgeMapping edgeMapping = (EdgeMapping) mapping;
            if (edgeMapping.isUseDomainElement()) {
                domainClass = edgeMapping.getDomainClass();
            }
        } else if (mapping instanceof ContainerMapping) {
            domainClass = ((ContainerMapping) mapping).getDomainClass();
        }
        return domainClass;
    }

}
