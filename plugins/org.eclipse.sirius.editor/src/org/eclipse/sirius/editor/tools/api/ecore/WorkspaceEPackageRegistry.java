/*******************************************************************************
 * Copyright (c) 2007, 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.tools.api.ecore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.util.EclipseUtil;

import com.google.common.collect.Sets;

/**
 * EPackage registry leveraging ecore files from the workspace. Any change to
 * the Ecore file in the workspace makes the registry updates the corresponding
 * packages.
 * 
 * Make sure to dispose the package registry when you're done with it.
 * 
 * @author cbrun
 * 
 */
public class WorkspaceEPackageRegistry extends HashMap<String, Object> implements IResourceChangeListener, EPackage.Registry {
    private static final long serialVersionUID = -6543542780952654086L;

    private EPackage.Registry delegated;

    private boolean isListening;

    /**
     * Create a new workspace based registry.
     * 
     * @param delegate
     *            if true the registry will delegate its search to the runtime
     *            one.
     */
    public WorkspaceEPackageRegistry(final boolean delegate) {
        if (delegate) {
            delegated = EPackage.Registry.INSTANCE;
        }
    }

    /**
     * Init the registry.
     * 
     * @param ws
     *            the workspace to listen to.
     */
    public void init(final IWorkspace ws) {
        final List<IFile> files = EclipseUtil.getFilesFromWorkspace(null, "." + EcorePackage.eNAME);
        for (final IFile file : files) {
            newEcore(file);
        }
        ws.addResourceChangeListener(this);
        this.isListening = true;
    }

    /**
     * dispose the EPackage registry.
     * 
     * @param ws
     *            workspace.
     */
    public void dispose(final IWorkspace ws) {
        clear();
        ws.removeResourceChangeListener(this);
    }

    /**
     * {@inheritDoc}
     */
    public void resourceChanged(final IResourceChangeEvent event) {
        /* Refresh the registry when a ecore is added or removed. */
        try {
            final IResourceDelta delta = event.getDelta();
            if (delta != null) {
                this.processDelta(delta);
            }
        } catch (final CoreException e) {
            DslCommonPlugin.getDefault().error("Error while refresing the workspace EPackage registry", e);
        }
    }

    private void processDelta(final IResourceDelta resourceDelta) throws CoreException {
        if (deltaIsAboutEcorefile(resourceDelta)) {
            switch (resourceDelta.getKind()) {
            case IResourceDelta.ADDED:
            case IResourceDelta.REPLACED:
            case IResourceDelta.CHANGED:
            case IResourceDelta.CONTENT:
                this.newEcore(resourceDelta.getResource());
                break;
            case IResourceDelta.REMOVED:
                this.deletedEcore(resourceDelta.getResource());
                break;
            default:
                // do nothing.
                break;
            }
        }
        for (final IResourceDelta child : resourceDelta.getAffectedChildren()) {
            processDelta(child);
        }
    }

    private void deletedEcore(final IResource resource) {
        if (resource instanceof IFile) {
            final IFile file = (IFile) resource;
            final Collection<EPackage> packages = collectEPackages(file);
            for (final EPackage package1 : packages) {
                deleteEPackage(package1);
            }
        }
    }

    private void deleteEPackage(final EPackage package1) {
        if (containsKey(package1.getNsURI())) {
            remove(package1.getNsURI());
        }
    }

    private boolean deltaIsAboutEcorefile(final IResourceDelta resourceDelta) {
        return resourceDelta.getResource().getFileExtension() != null && EcorePackage.eNAME.equals(resourceDelta.getResource().getFileExtension());
    }

    private void newEcore(final IResource resource) {
        if (resource instanceof IFile) {
            final IFile file = (IFile) resource;
            final Collection<EPackage> packages = collectEPackages(file);
            for (final EPackage package1 : packages) {
                newEPackage(package1);
            }
        }
    }

    private Collection<EPackage> collectEPackages(final IFile file) {
        final Collection<EPackage> packages = new ArrayList<EPackage>();
        try {
            final URI fileURI = URI.createPlatformResourceURI(file.getFullPath().toOSString(), true);
            final Resource ecoreRes = new ResourceSetImpl().getResource(fileURI, true);
            final Iterator<EObject> it = ecoreRes.getAllContents();
            while (it.hasNext()) {
                final EObject cur = it.next();
                if (cur instanceof EPackage) {
                    packages.add((EPackage) cur);
                }
            }
            // CHECKSTYLE:OFF
        } catch (final Exception e) {
            // if we've got an exception, that's because the file is not
            // correct.
            // CHECKSTYLE:ON
        }
        return packages;
    }

    private void newEPackage(final EPackage ePackage) {
        if (ePackage.getNsURI() != null && !containsAlreadyEPackage(ePackage)) {
            put(ePackage.getNsURI(), ePackage);
        }
    }

    private boolean containsAlreadyEPackage(EPackage ePackage) {
        boolean containsAlreadyEPackage = false;
        if (containsKey(ePackage.getNsURI())) {
            EPackage alreadyRegistredEPackage = (EPackage) get(ePackage.getNsURI());
            Resource alreadtRegisteredResource = alreadyRegistredEPackage.eResource();
            URI alreadyRegistredEPackageResourceURI = alreadtRegisteredResource.getURI();
            Resource ePackageResource = ePackage.eResource();
            URI ePackageResourceURI = ePackageResource.getURI();
            if (alreadyRegistredEPackageResourceURI.equals(ePackageResourceURI)) {
                String alreadyRegistredEPackageURIFragment = alreadtRegisteredResource.getURIFragment(alreadyRegistredEPackage);
                String ePackageURIFragment = ePackageResource.getURIFragment(ePackage);
                if (alreadyRegistredEPackageURIFragment.equals(ePackageURIFragment)) {
                    containsAlreadyEPackage = true;
                }
            }
        }
        return containsAlreadyEPackage;
    }

    /**
     * {@inheritDoc}
     */
    public EFactory getEFactory(final String arg0) {
        final EPackage pak = getEPackage(arg0);
        if (pak != null) {
            return pak.getEFactoryInstance();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object get(final Object arg0) {
        final Object value = super.get(arg0);
        if (value == null) {
            return delegated.get(arg0);
        }
        return value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Object> values() {
        return Sets.union(Sets.newLinkedHashSet(super.values()), Sets.newLinkedHashSet(delegated.values()));
    }

    /**
     * {@inheritDoc}
     */
    public EPackage getEPackage(final String arg0) {
        EPackage workspace = (EPackage) get(arg0);
        if (workspace == null) {
            workspace = delegated.getEPackage(arg0);
        }
        return workspace;
    }

    /**
     * return true if the registry has been initialized and is listening to the
     * workspace.
     * 
     * @return true if the registry has been initialized and is listening to the
     *         workspace.
     */
    public boolean isListeningWorkspace() {
        return isListening;
    }

}
