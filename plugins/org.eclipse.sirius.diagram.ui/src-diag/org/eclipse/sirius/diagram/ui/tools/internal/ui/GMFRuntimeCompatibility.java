/*******************************************************************************
 * Copyright (c) 2002, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *      Cedric Brun    (Obeo) <cedric.brun@obeo.fr>  - Initial API and implementation
 *******************************************************************************/
//CHECKSTYLE:OFF
package org.eclipse.sirius.diagram.ui.tools.internal.ui;

import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Version;

/**
 * Utility class to retrieve GMF runtime versions..
 * 
 * @author <a href="mailto:cedric.brun@obeo.fr">Cedric Brun</a>
 * 
 */
public final class GMFRuntimeCompatibility {
    /**
     * Check if we are using GMF "org.eclipse.gmf.runtime.diagram.ui" plugin
     * version between 1.2.0 and 1.3.3 limit included.
     * 
     * @return true if we are using GMF "org.eclipse.gmf.runtime.diagram.ui"
     *         plugin version between 1.2.0 and 1.3.3 limit included, false
     *         otherwise
     */
    public static boolean hasGMFPluginReleaseBetween1_2_0_And_1_3_3() {
        boolean hasGMFPluginReleaseBetween1_2_0_And_1_3_3 = false;
        Version gmfVersion1_2_0 = new Version(1, 2, 0);
        Version gmfVersion1_3_3 = new Version(1, 3, 3);
        Version version = null;
        String stringVersion = Platform.getBundle("org.eclipse.gmf.runtime.diagram.ui").getHeaders().get(org.osgi.framework.Constants.BUNDLE_VERSION); //$NON-NLS-1$
        try {
            version = Version.parseVersion(stringVersion);
            if (compareTo(gmfVersion1_2_0, version) <= 0 && compareTo(gmfVersion1_3_3, version) >= 0) {
                hasGMFPluginReleaseBetween1_2_0_And_1_3_3 = true;
            }
        } catch (IllegalArgumentException iae) {
            // Do nothing
        }
        return hasGMFPluginReleaseBetween1_2_0_And_1_3_3;
    }

    /**
     * Compares the a <code>Version</code> object to another object. <b> You
     * should not call directly Version.compareTo as the binary compatibility is
     * broken between Eclipse 3.6 and 3.7.</b>
     * 
     * <p>
     * A version is considered to be <b>less than </b> another version if its
     * major component is less than the other version's major component, or the
     * major components are equal and its minor component is less than the other
     * version's minor component, or the major and minor components are equal
     * and its micro component is less than the other version's micro component,
     * or the major, minor and micro components are equal and it's qualifier
     * component is less than the other version's qualifier component (using
     * <code>String.compareTo</code>).
     * 
     * <p>
     * A version is considered to be <b>equal to</b> another version if the
     * major, minor and micro components are equal and the qualifier component
     * is equal (using <code>String.compareTo</code>).
     * 
     * @param object
     *            The <code>Version</code> object to be compared.
     * @return A negative integer, zero, or a positive integer if this object is
     *         less than, equal to, or greater than the specified
     *         <code>Version</code> object.
     * @throws ClassCastException
     *             If the specified object is not a <code>Version</code>.
     */
    private static int compareTo(Version a, Version other) {

        if (other == a) { // quicktest
            return 0;
        }

        int result = a.getMajor() - other.getMajor();
        if (result != 0) {
            return result;
        }

        result = a.getMinor() - other.getMinor();
        if (result != 0) {
            return result;
        }

        result = a.getMicro() - other.getMicro();
        if (result != 0) {
            return result;
        }

        return a.getQualifier().compareTo(other.getQualifier());
    }

}
// CHECKSTYLE:ON
