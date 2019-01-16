/*******************************************************************************
 * Copyright (c) 2015, 2019 Obeo.
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
package org.eclipse.sirius.diagram.internal.queries;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.internal.registry.ExtensionHandle;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

/**
 * Queries manipulating the extension providing new shapes in the bundled image
 * shape style.
 * 
 * @author smonnier
 *
 */
@SuppressWarnings({ "restriction" })
public final class BundledImageExtensionQuery {
    /**
     * Identifier of the newer extension point.
     */
    public static final String CUSTOM_BUNDLED_IMAGE_SHAPE_EXTENSION_POINT = "org.eclipse.sirius.diagram.customBundledImageShape"; //$NON-NLS-1$

    /**
     * The global instance.
     */
    private static final BundledImageExtensionQuery INSTANCE = new BundledImageExtensionQuery();

    /**
     * Identifier of the extension point.
     * 
     * @deprecated
     */
    @Deprecated
    private static final String BUNDLED_IMAGE_SHAPE_EXTENSION_POINT = "org.eclipse.sirius.diagram.bundledImageShape"; //$NON-NLS-1$

    private static final String LABEL_ATTRIBUTE = "label"; //$NON-NLS-1$

    private static final String IMAGE_PATH_ATTRIBUTE = "imagePath"; //$NON-NLS-1$

    private IConfigurationElement[] extensions;

    /**
     * This default constructor initializes the list of extensions concerning
     * the bundled image shape extension point.
     */
    private BundledImageExtensionQuery() {
        getExtensions();
    }

    /**
     * Returns the global instance.
     * 
     * @return the global instance.
     */
    public static BundledImageExtensionQuery getInstance() {
        return INSTANCE;
    }

    /**
     * Finds all extensions concerning the bundled image shape extension point.
     * 
     * @return all extensions concerning the bundled image shape extension
     *         point.
     */
    public IConfigurationElement[] getExtensions() {
        if (extensions == null) {
            IConfigurationElement[] newExtensionPoints = Platform.getExtensionRegistry().getConfigurationElementsFor(CUSTOM_BUNDLED_IMAGE_SHAPE_EXTENSION_POINT);
            IConfigurationElement[] oldExtensionPoints = Platform.getExtensionRegistry().getConfigurationElementsFor(BUNDLED_IMAGE_SHAPE_EXTENSION_POINT);
            IConfigurationElement[] allExtensionPoints = new IConfigurationElement[newExtensionPoints.length + oldExtensionPoints.length];
            int extensionsCount = 0;
            for (int i = 0; i < newExtensionPoints.length; i++) {
                allExtensionPoints[extensionsCount] = newExtensionPoints[i];
                extensionsCount++;
            }
            for (int i = 0; i < oldExtensionPoints.length; i++) {
                allExtensionPoints[extensionsCount] = oldExtensionPoints[i];
                extensionsCount++;
            }
            this.extensions = allExtensionPoints;
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
     * Find the image path attribute of an extension.
     * 
     * @param extension
     *            to find its image path.
     * @return the image path attribute of the given extension.
     */
    public String getImagePath(IConfigurationElement extension) {
        return extension.getAttribute(IMAGE_PATH_ATTRIBUTE);
    }

    /**
     * Find the identifier of an extension.
     * 
     * @param extension
     *            to find its identifier.
     * @return the identifier of the given extension.
     */
    public String getIdentifier(IConfigurationElement extension) {
        return ((ExtensionHandle) extension.getParent()).getUniqueIdentifier();

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
        List<String> labels = new ArrayList<>();
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
     * @param providedShapeID
     *            the shape ID to compute its label.
     * @return the label to display in the VSM.
     */
    public String getExtendedLabelForVSM(String providedShapeID) {
        for (IConfigurationElement configurationElement : extensions) {
            String identifier = ((ExtensionHandle) configurationElement.getParent()).getUniqueIdentifier();
            if (identifier != null && identifier.equals(providedShapeID)) {
                return getExtendedLabelForVSM(configurationElement);
            }
        }
        return "Unknown ID"; //$NON-NLS-1$
    }

    private String getExtendedLabelForVSM(IConfigurationElement configurationElement) {
        return configurationElement.getAttribute(LABEL_ATTRIBUTE) + " - " + configurationElement.getNamespaceIdentifier(); //$NON-NLS-1$
    }

    /**
     * Find the extension that declares the shape with the given ID.
     * 
     * @param providedShapeID
     *            the shape ID to look for.
     * @return the extension providing the shape with the given ID.
     */
    public IConfigurationElement getExtensionDefiningProvidedShapeID(String providedShapeID) {
        for (IConfigurationElement configurationElement : extensions) {
            String identifier = ((ExtensionHandle) configurationElement.getParent()).getUniqueIdentifier();
            if (identifier != null && identifier.equals(providedShapeID)) {
                return configurationElement;
            }
        }
        return null;
    }

    /**
     * Look for an attribute with the given name in an extension.
     * 
     * @param configurationElement
     *            the extension to investigate
     * @param parameter
     *            the attribute name
     * @return the value of the
     */
    public String findParameterInExtension(IConfigurationElement configurationElement, String parameter) {
        if (configurationElement != null) {
            for (IConfigurationElement configurationElementChild : configurationElement.getChildren(parameter)) {
                if (configurationElementChild.getAttribute(parameter) != null) {
                    return configurationElementChild.getAttribute(parameter);
                }
            }
        }
        return null;
    }
}
