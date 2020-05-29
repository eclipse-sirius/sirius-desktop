/*******************************************************************************
 * Copyright (c) 2017, 2020 Obeo.
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
package org.eclipse.sirius.common.tools.api.ecore;

import java.util.ArrayList;
import java.util.List;

/**
 * Represent extra meta-data that can be associated to an EPackage but that are not modeled in Ecore.
 * 
 * @author pcdavid
 */
public class EPackageMetaData {
    /**
     * The nsURI of the EPackage this applies to. We use an nsURI instead of an EPackage to avoid loading the metamodel
     * (and the plug-in that defines it) if possible.
     */
    private final String nsURI;

    /**
     * The name to display to end-users, which may be different from the technical EPackage.name.
     */
    private String displayName;

    /**
     * User-oriented documentation for the metamodel defined by the EPackage.
     */
    private String documentation;

    /**
     * Names of the EClass which are possible DocumentRoot classes for this EPackage.
     * This is useful when working with EPackages generated from XSD.
     * Checking if an EClass is the DocumentRoot can not always be done using
     * ExtendedMetaData.INSTANCE.isDocumentRoot(eClass) depending on how
     * the EPackage was generated from XSD
     */
    private List<String> documentRootClassNames = new ArrayList<>();
    
    /**
     * Names of EClasses from the EPackage that are good candidates as root model elements.
     */
    private List<String> suggestedRoots = new ArrayList<>();

    /**
     * Create a new EPackageExtraData.
     * 
     * @param nsURI
     *            the nsURI of the EPackage this applies to..
     */
    public EPackageMetaData(String nsURI) {
        this.nsURI = nsURI;
    }

    /**
     * The nsURI of the EPackage this meta-data applies to.
     * 
     * @return the nsURI of the EPackage.
     */
    public String getNsURI() {
        return nsURI;
    }

    /**
     * Returns the name to display to end-users, which may be different from the technical EPackage.name.
     * 
     * @return the name to display to end-users.
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Set the name to display to end-users, which may be different from the technical EPackage.name.
     * 
     * @param displayName
     *            the name to display to end-users.
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Returns the user-oriented documentation for the metamodel defined by the EPackage.
     * 
     * @return the user-oriented documentation for the metamodel.
     */
    public String getDocumentation() {
        return documentation;
    }

    /**
     * Set the user-oriented documentation for the metamodel defined by the EPackage.
     * 
     * @param documentation
     *            the user-oriented documentation for the metamodel.
     */
    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }
    
    /**
     * Returns the name of the EClasses to be considered as the DocumentRoot.
     * 
     * @return the documentRootClassNames
     * @since 6.3.2
     */
    public List<String> getDocumentRootClassNames() {
        return documentRootClassNames;
    }

    /**
     * Returns the names of EClasses from the EPackage that are good candidates as root model elements.
     * 
     * @return the names of good root model types.
     */
    public List<String> getSuggestedRoots() {
        return suggestedRoots;
    }

    @Override
    public String toString() {
        return String.format("#<EPackageMetaData %s>", nsURI); //$NON-NLS-1$
    }

}
