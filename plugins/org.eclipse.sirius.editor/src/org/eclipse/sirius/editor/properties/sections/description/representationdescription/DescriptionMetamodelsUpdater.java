/*******************************************************************************
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.properties.sections.description.representationdescription;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Descriptor;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.sirius.editor.editorPlugin.SiriusEditorPlugin;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;

public class DescriptionMetamodelsUpdater {
    private static ResourceSet resourceSetCache;

    private EObject description;

    private EReference metamodelEReference;

    protected EditingDomain editingDomain;

    /**
     * Default constructor.
     * 
     * @param description
     *            the description to update
     */
    public DescriptionMetamodelsUpdater(EObject description, EReference metamodelEReference) {
        this.description = description;
        this.metamodelEReference = metamodelEReference;
    }

    public void setEditingDomain(EditingDomain editingDomain) {
        this.editingDomain = editingDomain;
    }

    /**
     * Add all {@link EPackage}s contained in the specified ecore
     * {@link Resource}'s {@link URI}s to the contextual description on the
     * metamodel feature.
     * 
     * @param ecoreResourceURIs
     *            the specified ecore {@link Resource}'s {@link URI}s containing
     *            all {@link EPackage}s to add
     */
    public void addEPackagesFromEcoreResource(List<URI> ecoreResourceURIs) {
        List<EPackage> ecoreResourceEPackages = getEPackagesFromEcoreResource(ecoreResourceURIs);
        addEPackages(ecoreResourceEPackages);
    }

    /**
     * Add the {@link EPackage}s specified by <code>ePackageURIs</code> to the
     * contextual description on the metamodel feature.
     * 
     * @param ePackageURIs
     *            the specified {@link EPackage}s {@link URI}
     */
    public void addEPackagesFromURI(List<URI> ePackageURIs) {
        List<EPackage> ePackages = getEPackages(ePackageURIs);
        addEPackages(ePackages);
    }

    /**
     * Add the {@link EPackage}s specified by <code>ePackageURIs</code> to the
     * contextual description on the metamodel feature.
     * 
     * @param ePackageURIs
     *            the specified {@link EPackage}s {@link URI}
     */
    public void addEPackages(List<EPackage> ePackages) {
        if (!ePackages.isEmpty()) {
            Command addEPackagesCmd = AddCommand.create(editingDomain, description, metamodelEReference, ePackages);
            editingDomain.getCommandStack().execute(addEPackagesCmd);
        }
    }

    /**
     * Get a list of {@link EPackage}s from a list of ecore resource URIs.
     * 
     * @param ecoreResourceURIs
     *            the list of ecore resource URIs
     * @return a list of {@link EPackage}s
     */
    public List<EPackage> getEPackagesFromEcoreResource(List<URI> ecoreResourceURIs) {
        List<EPackage> ecoreResourceEPackages = new ArrayList<EPackage>();
        ResourceSet resourceSet = editingDomain.getResourceSet();
        for (URI ecoreResourceURI : ecoreResourceURIs) {
            Resource ecoreResource = resourceSet.getResource(ecoreResourceURI, true);
            ecoreResourceEPackages.addAll(getEPackages(ecoreResource));
        }
        return ecoreResourceEPackages;
    }

    public void setDescription(EObject description) {
        this.description = description;
    }

    protected List<EPackage> getEPackages(Resource ecoreResource) {
        List<EPackage> ePackages = new ArrayList<EPackage>();
        for (EObject content : ecoreResource.getContents()) {
            if (content instanceof EPackage) {
                EPackage ePackage = (EPackage) content;
                ePackages.add(ePackage);
                ePackages.addAll(getSubPackages(ePackage));
            }
        }
        return ePackages;
    }

    private List<EPackage> getSubPackages(EPackage ePackage) {
        List<EPackage> ePackages = new ArrayList<EPackage>();
        ePackages.addAll(ePackage.getESubpackages());
        for (EPackage subEPackage : ePackage.getESubpackages()) {
            ePackages.addAll(subEPackage.getESubpackages());
        }
        return ePackages;
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
    protected Set<Resource> getRelatedResourcesToRemove(List<EPackage> ePackagesToRemove) {
        Set<Resource> relatedResourcesToRemove = new LinkedHashSet<Resource>();
        for (Resource ePackageToRemoveResource : getRelatedResources(ePackagesToRemove)) {
            if (ePackageToRemoveResource != null) {
                List<EPackage> ePackagesOfResourceToRemove = getEPackages(ePackageToRemoveResource);
                Object metamodels = this.description.eGet(this.metamodelEReference);
                if (metamodels instanceof Collection<?>) {
                    if (Collections.disjoint(ePackagesOfResourceToRemove, (Collection<?>) metamodels)) {
                        relatedResourcesToRemove.add(ePackageToRemoveResource);
                    }
                }
            }
        }
        return relatedResourcesToRemove;
    }

    protected Set<Resource> getRelatedResources(List<EPackage> ePackages) {
        Set<Resource> relatedResources = new LinkedHashSet<Resource>();
        for (EPackage ePackage : ePackages) {
            Resource ePackageResource = ePackage.eResource();
            if (ePackageResource != null) {
                relatedResources.add(ePackageResource);
            }
        }
        return relatedResources;
    }

    /**
     * Get a list of {@link EPackage}s from a list of platform plugin
     * {@link URI}s to the {@link EPackage}s.
     * 
     * @param ePackageURIs
     *            list of platform plugin {@link URI}s to the {@link EPackage}s
     * @return list of {@link EPackage}s
     */
    public List<EPackage> getEPackages(List<URI> ePackageURIs) {
        List<EPackage> ePackages = new ArrayList<EPackage>();
        ResourceSet resourceSet = editingDomain.getResourceSet();
        for (URI ePackageURI : ePackageURIs) {
            EObject eObject = resourceSet.getEObject(ePackageURI, true);
            if (eObject instanceof EPackage) {
                EPackage ePackage = (EPackage) eObject;
                ePackages.add(ePackage);
            }
        }
        return ePackages;
    }

    /**
     * Remove the specified <code>ePackages</code> to the contextual
     * {@link RepresentationDescription} on the
     * {@link RepresentationDescription#getMetamodel()} feature.
     * 
     * @param ePackages
     *            the list of {@link EPackage} to remove
     */
    public void removeEPackages(List<EPackage> ePackages) {
        if (!ePackages.isEmpty()) {
            Command removeEPackagesCmd = RemoveCommand.create(editingDomain, description, metamodelEReference, ePackages);
            editingDomain.getCommandStack().execute(removeEPackagesCmd);
            Set<Resource> relatedResourcesToRemove = getRelatedResourcesToRemove(ePackages);
            for (Resource relatedResourceToRemove : relatedResourcesToRemove) {
                editingDomain.getResourceSet().getResources().remove(relatedResourceToRemove);
            }
        }
    }

    /**
     * Get list of {@link URI}s from a specified path and file names.
     * 
     * @param path
     *            the specified path
     * @param fileNames
     *            the specified file names
     * @return list of {@link URI}s
     */
    public List<URI> getURIs(String path, String[] fileNames) {
        List<URI> uris = new ArrayList<URI>();
        for (int i = 0, len = fileNames.length; i < len; i++) {
            URI uri = URI.createFileURI(path + File.separator + fileNames[i]);
            uris.add(uri);
        }
        return uris;
    }

    /**
     * Get a list of {@link URI}s corresponding to the specified list of
     * {@link IResource}.
     * 
     * @param ecoreResources
     *            the specified list of {@link IResource}
     * @return list of {@link URI}s
     */
    public List<URI> getURIs(List<IResource> ecoreResources) {
        List<URI> uris = new ArrayList<URI>();
        for (IResource resource : ecoreResources) {
            IPath fullPath = resource.getFullPath();
            URI ecoreResourceURI = URI.createPlatformResourceURI(fullPath.toString(), true);
            uris.add(ecoreResourceURI);
        }
        return uris;
    }

    /**
     * Get a list of {@link EPackage}s from the {@link Registry} from a array of
     * nsURI.
     * 
     * @param result
     *            array of {@link EPackage#getNsURI()}s
     * @return a list of {@link EPackage}s from the {@link Registry}
     */
    public List<EPackage> getEPackagesFromNsURI(Object[] result) {
        List<EPackage> ePackages = new ArrayList<EPackage>();
        for (int i = 0, length = result.length; i < length; i++) {
            Object object = EPackage.Registry.INSTANCE.get(result[i]);
            if (object instanceof EPackage) {
                EPackage ePackage = (EPackage) object;
                ePackages.add(ePackage);
            } else if (object instanceof Descriptor) {
                Descriptor ePackageDescriptor = (Descriptor) object;
                EPackage ePackage = ePackageDescriptor.getEPackage();
                if (ePackage != null) {
                    ePackages.add(ePackage);
                }
            }
        }
        return ePackages;
    }

    /**
     * Get the resource {@link URI} with fragment of the specified
     * {@link EPackage}.
     * 
     * @param ePackage
     *            the {@link EPackage}
     * @return the resource {@link URI} with fragment
     */
    public URI getCompleteURIToEPackage(EPackage ePackage) {
        URI completeURIToEPackage = null;
        Resource ePackageResource = ePackage.eResource();
        URI ePackageResourceURI = ePackageResource.getURI();
        if (!ePackageResourceURI.isPlatform() && !ePackageResourceURI.isFile()) {
            completeURIToEPackage = getRealURI(ePackage);
        } else {
            String uriFragment = ePackageResource.getURIFragment(ePackage);
            completeURIToEPackage = ePackageResourceURI.appendFragment(uriFragment);
        }
        return completeURIToEPackage;
    }

    private URI getRealURI(EPackage ePackage) {
        URI realURI = null;
        getResourceSetCache();
        URI genModelResourceURI = EcorePlugin.getEPackageNsURIToGenModelLocationMap().get(ePackage.getNsURI());
        if (genModelResourceURI != null) {
            EPackage realEPackage = getEPackageFromCache(ePackage, genModelResourceURI);
            if (realEPackage != null) {
                Resource ePackageResource = realEPackage.eResource();
                realURI = ePackageResource.getURI().appendFragment(ePackageResource.getURIFragment(realEPackage));
            }

        }
        return realURI;
    }

    private EPackage getEPackageFromCache(EPackage ePackage, URI genModelResourceURI) {
        EPackage ePackageFromCache = null;
        try {
            getResourceSetCache().getResource(genModelResourceURI, true);
        } catch (Exception e) {
            SiriusEditorPlugin.getPlugin().getLog().log(new Status(IStatus.WARNING, SiriusEditorPlugin.PLUGIN_ID, genModelResourceURI.toString() + " not found"));
        }
        EcoreUtil.resolveAll(getResourceSetCache());
        for (Resource resource : getResourceSetCache().getResources()) {
            EcoreUtil.resolveAll(resource);
            TreeIterator<?> treeIterator = new EcoreUtil.ContentTreeIterator<Object>(resource.getContents()) {
                private static final long serialVersionUID = 1L;

                @Override
                protected Iterator<? extends EObject> getEObjectChildren(EObject eObject) {
                    return eObject instanceof EPackage ? ((EPackage) eObject).getESubpackages().iterator() : Collections.<EObject> emptyList().iterator();
                }
            };

            while (treeIterator.hasNext()) {
                Object content = treeIterator.next();
                if (content instanceof EPackage) {
                    EPackage currentEPackage = (EPackage) content;
                    if (ePackage.getNsURI().equals(currentEPackage.getNsURI())) {
                        ePackageFromCache = currentEPackage;
                        break;
                    }
                }
            }
            if (ePackageFromCache != null) {
                break;
            }
        }
        return ePackageFromCache;
    }

    public ResourceSet getResourceSetCache() {
        if (resourceSetCache == null) {
            resourceSetCache = new ResourceSetImpl();
            resourceSetCache.getURIConverter().getURIMap().putAll(EcorePlugin.computePlatformURIMap());
        }
        return resourceSetCache;
    }

    public void dispose() {
        description = null;
        metamodelEReference = null;
        editingDomain = null;
    }
}
