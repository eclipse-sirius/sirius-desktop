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
package org.eclipse.sirius.tools.api.ui.color;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.SystemColor;

/**
 * A helper for Color management.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public final class EnvironmentSystemColorFactory {

    private static EnvironmentSystemColorFactory defaultInstance;

    /**
     * Avoid instantiation. Create a new {@link EnvironmentSystemColorFactory}.
     */
    private EnvironmentSystemColorFactory() {
    }

    /**
     * Return the singleton instance.
     * 
     * @return a default instance
     */
    public static EnvironmentSystemColorFactory getDefault() {
        if (defaultInstance == null) {
            defaultInstance = new EnvironmentSystemColorFactory();
        }
        return defaultInstance;
    }

    /**
     * return the color description corresponding to the color name.
     * 
     * @param name
     *            the name of the color.
     * @return return the color description corresponding to the color name.
     */
    public SystemColor getSystemColorDescription(final String name) {
        final String uri = SiriusUtil.VIEWPOINT_ENVIRONMENT_RESOURCE_URI + "#/0/@systemColors/@entries[name='" + name + "']"; //$NON-NLS-1$ //$NON-NLS-2$
        final EObject color = EcoreUtil.create(DescriptionPackage.eINSTANCE.getSystemColor());
        final URI colorURI = URI.createURI(uri);
        ((InternalEObject) color).eSetProxyURI(colorURI);
        return (SystemColor) color;
    }

    /**
     * Clamps a value into a specified interval.
     * 
     * @param value
     *            the value
     * @param min
     *            the minimum possible value (inclusive).
     * @param max
     *            the maximum possible value (inclusive). Must be >= min.
     * @return the integer closes to <code>value</code> which is inside the
     *         inclusive interval <code>[min, max]</code>.
     * @since 0.9.0
     */
    public static int clamp(final int value, final int min, final int max) {
        return Math.min(Math.max(value, min), max);
    }
}
