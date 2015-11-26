/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.internal.queries;

import java.util.List;

import org.eclipse.core.internal.registry.ExtensionHandle;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

import com.google.common.collect.Lists;

/**
 * Queries manipulating the extension providing new shapes in the bundled image
 * shape style.
 * 
 * @author smonnier
 *
 */
public class BundledImageExtensionQuery {

    /**
     * Identifier of the extension point.
     */
    private static final String BUNDLED_IMAGE_SHAPE_EXTENSION_POINT = "org.eclipse.sirius.diagram.bundledImageShape"; //$NON-NLS-1$

    private static final String LABEL_ATTRIBUTE = "label"; //$NON-NLS-1$

    private IConfigurationElement[] extensions;

    /**
     * This default constructor initializes the list of extensions concerning
     * the bundled image shape extension point.
     */
    public BundledImageExtensionQuery() {
        getExtensions();
    }

    /**
     * Finds all extensions concerning the bundled image shape extension point.
     * 
     * @return all extensions concerning the bundled image shape extension
     *         point.
     */
    public IConfigurationElement[] getExtensions() {
        if (extensions == null) {
            this.extensions = Platform.getExtensionRegistry().getConfigurationElementsFor(BUNDLED_IMAGE_SHAPE_EXTENSION_POINT);
        }
        return extensions;
    }

    /**
     * Find the label attribute of an extension.
     * 
     * @param extension
     *            to find its label.
     * @return the label attribute of the given extension.
     */
    public String getLabel(IConfigurationElement extension) {
        return extension.getAttribute(LABEL_ATTRIBUTE);
    }

    /**
     * Find the identifier of an extension.
     * 
     * @param extension
     *            to find its identifier.
     * @return the identifier of the given extension.
     */
    public String getIdentifier(IConfigurationElement extension) {
        return ((ExtensionHandle) extension.getParent()).getSimpleIdentifier();

    }
    /**
     * Find the labels to display in the VSM concerning all provided bundled
     * image shapes. These label are the images label, declared in their
     * extensions, with a suffix concerning the plug-in name where each shape is
     * provided.
     * 
     * @return the labels to display in the VSM.
     */
    public List<String> getExtendedLabelsForVSM() {
        List<String> labels = Lists.newArrayList();
        for (IConfigurationElement configurationElement : extensions) {
            labels.add(getExtendedLabelForVSM(configurationElement));
        }
        return labels;
    }

    /**
     * Find the label to display in the VSM concerning a provided bundled image
     * shape. This label is the image label, declared in the extension, with a
     * suffix concerning the plug-in name where this shape is provided.
     * 
     * @param providedShapeURI
     *            the shape URI to compute its label.
     * @return the label to display in the VSM.
     */
    public String getExtendedLabelForVSM(String providedShapeURI) {
        for (IConfigurationElement configurationElement : extensions) {
            String identifier = ((ExtensionHandle) configurationElement.getParent()).getSimpleIdentifier();
            if (identifier != null && identifier.equals(providedShapeURI)) {
                return getExtendedLabelForVSM(configurationElement);
            }
        }
        return "Unknown URI"; //$NON-NLS-1$
    }

    private String getExtendedLabelForVSM(IConfigurationElement configurationElement) {
        return configurationElement.getAttribute(LABEL_ATTRIBUTE) + " - " + configurationElement.getNamespaceIdentifier(); //$NON-NLS-1$
    }
}
