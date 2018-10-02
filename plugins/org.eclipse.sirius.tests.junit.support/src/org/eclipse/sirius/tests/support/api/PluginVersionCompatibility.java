/**
 * Copyright (c) 2012, 2014 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.support.api;

import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Version;

/**
 * Utility class to compare version of a specific plug-in to an expected one.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public final class PluginVersionCompatibility {
    /**
     * The current version of the plug-in.
     */
    Version currentVersion;

    /**
     * Default constructor.
     * 
     * @param symbolicName
     *            the symbolic name of the bundle to check.
     */
    public PluginVersionCompatibility(String symbolicName) {
        String stringVersion = Platform.getBundle(symbolicName).getHeaders().get(org.osgi.framework.Constants.BUNDLE_VERSION);
        currentVersion = Version.parseVersion(stringVersion);
    }

    /**
     * Compares the currentVersion <code>Version</code> object to another
     * version.
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
     * @param other
     *            The <code>Version</code> object to be compared to.
     * @return A negative integer, zero, or a positive integer if this version
     *         is less than, equal to, or greater than the specified
     *         <code>Version</code> object.
     */
    public int compareTo(Version other) {
        int result = 0;
        if (other != currentVersion) {
            result = currentVersion.getMajor() - other.getMajor();
            if (result == 0) {
                result = currentVersion.getMinor() - other.getMinor();
                if (result == 0) {
                    result = currentVersion.getMicro() - other.getMicro();
                    if (result == 0) {
                        result = currentVersion.getQualifier().compareTo(other.getQualifier());
                    }
                }
            }
        }
        return result;
    }
}
