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
package org.eclipse.sirius.ui.tools.internal.util;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

/**
 * Utility methods related to EMF.
 * 
 * @author mchauvin
 */
public final class EMFCoreUtil {

    private static final String EMPTY_STRING = ""; //$NON-NLS-1$

    /**
     * Avoid instantiation
     */
    private EMFCoreUtil() {

    }

    /**
     * Gets the name of an object if the object has name, returns an empty
     * string otherwise.
     * 
     * @param eObject
     *            The object.
     * @return The object's name.
     */
    public static String getName(final EObject eObject) {

        String name = EMPTY_STRING;
        if (eObject != null) {
            if (eObject.eIsProxy()) {
                name = EMFCoreUtil.getProxyName(eObject);
            } else {
                final String notProxyName = EMFCoreUtil.getNotProxyName(eObject);
                if (notProxyName != null) {
                    name = notProxyName;
                }
            }
        }
        return name;
    }

    private static String getProxyName(final EObject proxy) {
        return EMPTY_STRING;
        /*
         * Do not delete This codes comes from GMF, but as our factory do not
         * extends IExtendedResourceFactory we do not need that URI uri =
         * EcoreUtil.getURI(proxy); Resource.Factory factory =
         * Resource.Factory.Registry.INSTANCE.getFactory(uri); if (factory
         * instanceof IExtendedResourceFactory) { result =
         * ((IExtendedResourceFactory) factory).getProxyName(proxy); } if
         * (result == null) { // default algorithm result = EMPTY_STRING; }
         * return result;
         */
    }

    private static String getNotProxyName(final EObject eObject) {
        final EAttribute nameAttribute = EMFCoreUtil.getNameAttribute(eObject.eClass());
        if (nameAttribute != null) {
            final String name = (String) eObject.eGet(nameAttribute);
            if (name != null) {
                return name;
            }
        }
        return null;
    }

    /**
     * Gets the name attribute of an <code>EClass</code>.
     * 
     * @param eClass
     *            The <code>EClass</code>.
     * @return The name attribute.
     */
    public static EAttribute getNameAttribute(final EClass eClass) {
        EAttribute nameAttribute = null;
        final EStructuralFeature feature = eClass.getEStructuralFeature("name"); //$NON-NLS-1$
        if (feature != null) {
            if (feature instanceof EAttribute) {
                final EClassifier type = feature.getEType();
                if (type != null) {
                    if (type.getInstanceClass() == String.class) {
                        nameAttribute = (EAttribute) feature;
                    }
                }
            }
        }
        return nameAttribute;
    }

    /**
     * Gets the image of an object if the object has an image path, returns null
     * otherwise.
     * 
     * @param eObject
     *            The object.
     * @return The object's iamge.
     */
    public static Option<URL> getImage(final EObject eObject) {
        final String imagePath = EMFCoreUtil.getImagePath(eObject);
        if (imagePath != null && !StringUtil.isEmpty(imagePath)) {
            URI uri = URI.createURI(imagePath, true);
            final IPath path = new Path(uri.path());
            if (path.segmentCount() >= 2) {
                final IWorkspace workspace = ResourcesPlugin.getWorkspace();
                IFile file = workspace.getRoot().getFile(path);
                if (file.isAccessible()) {
                    try {
                        return Options.newSome(file.getRawLocationURI().toURL());
                    } catch (MalformedURLException e) {
                        // e.printStackTrace();
                    }
                }
            }
        }
        return Options.newNone();
    }

    private static String getImagePath(final EObject eObject) {
        final EAttribute iconAttribute = EMFCoreUtil.getIconAttribute(eObject.eClass());
        if (iconAttribute != null) {
            final String icon = (String) eObject.eGet(iconAttribute);
            if (icon != null) {
                return icon;
            }
        }
        return null;
    }

    /**
     * Gets the icon attribute of an <code>EClass</code>.
     * 
     * @param eClass
     *            The <code>EClass</code>.
     * @return The icon attribute.
     */
    private static EAttribute getIconAttribute(final EClass eClass) {
        EAttribute nameAttribute = null;
        final EStructuralFeature feature = eClass.getEStructuralFeature("icon"); //$NON-NLS-1$
        if (feature != null) {
            if (feature instanceof EAttribute) {
                final EClassifier type = feature.getEType();
                if (type != null) {
                    if (type.getInstanceClass() == String.class) {
                        nameAttribute = (EAttribute) feature;
                    }
                }
            }
        }
        return nameAttribute;
    }

}
