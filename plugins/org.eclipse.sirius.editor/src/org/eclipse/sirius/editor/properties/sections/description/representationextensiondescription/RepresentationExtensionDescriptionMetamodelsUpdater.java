/*******************************************************************************
 * Copyright (c) 2011, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.properties.sections.description.representationextensiondescription;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.sirius.editor.properties.sections.description.representationdescription.DescriptionMetamodelsUpdater;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription;

/**
 * A class to update the
 * {@link RepresentationExtensionDescription#getMetamodel()} feature of a
 * contextual {@link RepresentationExtensionDescription}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class RepresentationExtensionDescriptionMetamodelsUpdater extends DescriptionMetamodelsUpdater {

    private RepresentationExtensionDescription representationExtensionDescription;

    /**
     * Default constructor.
     * 
     * @param representationDescription
     *            the {@link RepresentationDescription} to update
     */
    public RepresentationExtensionDescriptionMetamodelsUpdater(RepresentationExtensionDescription representationDescription) {
        super(null, DescriptionPackage.eINSTANCE.getRepresentationDescription_Metamodel());
        this.representationExtensionDescription = representationDescription;
    }

    /**
     * Add the {@link EPackage}s specified by <code>ePackageURIs</code> to the
     * contextual {@link RepresentationExtensionDescription} on the
     * {@link RepresentationExtensionDescription#getMetamodel()} feature.
     * 
     * @param ePackageURIs
     *            the specified {@link EPackage}s {@link URI}
     */
    @Override
    public void addEPackages(List<EPackage> ePackages) {
        if (!ePackages.isEmpty()) {
            Command addEPackagesCmd = AddCommand.create(editingDomain, representationExtensionDescription, DescriptionPackage.Literals.REPRESENTATION_EXTENSION_DESCRIPTION__METAMODEL, ePackages);
            editingDomain.getCommandStack().execute(addEPackagesCmd);
        }
    }

    /**
     * Remove the specified <code>ePackages</code> to the contextual
     * {@link RepresentationDescription} on the
     * {@link RepresentationDescription#getMetamodel()} feature.
     * 
     * @param ePackages
     *            the list of {@link EPackage} to remove
     */
    @Override
    public void removeEPackages(List<EPackage> ePackages) {
        if (!ePackages.isEmpty()) {
            Command removeEPackagesCmd = RemoveCommand.create(editingDomain, representationExtensionDescription, DescriptionPackage.Literals.REPRESENTATION_EXTENSION_DESCRIPTION__METAMODEL,
                    ePackages);
            editingDomain.getCommandStack().execute(removeEPackagesCmd);
            Set<Resource> relatedResourcesToRemove = getRelatedResourcesToRemove(ePackages);
            for (Resource relatedResourceToRemove : relatedResourcesToRemove) {
                editingDomain.getResourceSet().getResources().remove(relatedResourceToRemove);
            }
        }
    }

    /**
     * Retains {@link Resource} to remove from the {@link ResourceSet} only for
     * one for which all EPackages are asked to remove.
     * 
     * @param ePackagesToRemove
     *            {@link EPackage} to remove
     * 
     * @return the related {@link Resource} to remove from the
     *         {@link ResourceSet}
     */
    @Override
    protected Set<Resource> getRelatedResourcesToRemove(List<EPackage> ePackagesToRemove) {
        Set<Resource> relatedResourcesToRemove = new LinkedHashSet<Resource>();
        for (Resource ePackageToRemoveResource : getRelatedResources(ePackagesToRemove)) {
            if (ePackageToRemoveResource != null) {
                List<EPackage> ePackagesOfResourceToRemove = getEPackages(ePackageToRemoveResource);
                if (Collections.disjoint(ePackagesOfResourceToRemove, representationExtensionDescription.getMetamodel())) {
                    relatedResourcesToRemove.add(ePackageToRemoveResource);
                }
            }
        }
        return relatedResourcesToRemove;
    }

}
