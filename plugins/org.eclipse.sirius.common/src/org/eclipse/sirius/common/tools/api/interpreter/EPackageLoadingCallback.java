/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.interpreter;

import java.util.Collection;

import org.eclipse.emf.ecore.EPackage;

/**
 * Callback one can use to trigger some behavior when an EPackage extension is
 * being searched and loaded.
 * 
 * @author Cedric Brun <cedric.brun@obeo.fr>
 */
public interface EPackageLoadingCallback {
    /**
     * Called at the end of a search or update of an EPackage with the
     * corresponding nsURI.
     * 
     * @param nsURI
     *            the EPackage nsURI.
     * 
     * @param pak
     *            the EPackage instance.
     * */
    void loaded(String nsURI, EPackage pak);

    /**
     * Called at the end of a search or update of an EPackage with the
     * corresponding nsURI.
     * 
     * @param nsURI
     *            the EPackage nsURI.
     * @param pak
     *            the EPackage instance.
     * */
    void unloaded(String nsURI, EPackage pak);

    /**
     * A value class acting as a descriptor for an EPackage declaration.
     * 
     * @author cedric
     *
     */
    class EPackageDeclaration {

        private String nsURI;

        private String className;

        private String genModelPath;

        public EPackageDeclaration(String nsURI, String className, String genModelPath) {
            super();
            this.nsURI = nsURI;
            this.className = className;
            this.genModelPath = genModelPath;
        }

        public String getNsURI() {
            return nsURI;
        }

        public String getClassName() {
            return className;
        }

        /**
         * return the genmodel path if specified by the declaration. Null
         * otherwhise.
         * 
         * @return the genmodel path if specified by the declaration. Null
         *         otherwhise.
         */
        public String getGenModelPath() {
            return genModelPath;
        }

    }

    /**
     * A source of EPackage declaration. Might be a bundle or a workspace
     * project.
     * 
     * @author cedric
     *
     */
    class EPackageDeclarationSource {
        private String symbolicName;

        private Collection<EPackageDeclaration> ePackages;

        private boolean isBundle;

        public EPackageDeclarationSource(String symbolicName, Collection<EPackageDeclaration> epackages, boolean isBundle) {
            this.symbolicName = symbolicName;
            this.ePackages = epackages;
            this.isBundle = isBundle;
        }

        public String getSymbolicName() {
            return symbolicName;
        }

        public Collection<EPackageDeclaration> getEPackageDeclarations() {
            return ePackages;
        }

        public boolean isBundle() {
            return isBundle;
        }

        @Override
        public String toString() {
            String result = ""; //$NON-NLS-1$
            if (isBundle) {
                result += "bundle :"; //$NON-NLS-1$
            } else {
                result += "project :"; //$NON-NLS-1$
            }
            result += symbolicName;
            return result;
        }

    }

}
