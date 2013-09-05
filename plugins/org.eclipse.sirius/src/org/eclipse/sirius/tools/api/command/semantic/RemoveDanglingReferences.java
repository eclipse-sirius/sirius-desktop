/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tools.api.command.semantic;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreUtil.CrossReferencer;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.SiriusPlugin;
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;

/**
 * A command that is able to remove dangling reference.
 * 
 * @author cbrun
 */
public class RemoveDanglingReferences extends RecordingCommand {

    /** the root object. */
    private final EObject root;

    /**
     * Create a new {@link RemoveDanglingReferences} command.
     * 
     * @param domain
     *            the editing domain.
     * @param root
     *            the root element.
     */
    public RemoveDanglingReferences(final TransactionalEditingDomain domain, final EObject root) {
        super(domain);
        this.root = root;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
     */
    @Override
    protected void doExecute() {
        RemoveDanglingReferences.removeDanglingReferences(root);
    }

    /**
     * Remove all dangling references of all objects that are contained by the
     * root element.
     * 
     * @param root
     *            the root element
     */
    public static void removeDanglingReferences(final EObject root) {
        DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.REMOVE_DANGLING_REFERENCE_KEY);
        ModelAccessor accessor = null;
        if (root.eResource() != null && root.eResource().getResourceSet() != null) {
            accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(root);
            IPermissionAuthority authority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(root.eResource().getResourceSet());
            if (authority != null && !authority.canEditInstance(root)) {
                DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.REMOVE_DANGLING_REFERENCE_KEY);
                return;
            }
        }

        removeDanglingReferences(accessor, new DanglingReferencesDetector(root.eResource()));
        DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.REMOVE_DANGLING_REFERENCE_KEY);
    }

    /**
     * Removes all dangling references from all the elements in the given
     * resourceSet.
     * 
     * @param resourceSet
     *            The resourceSet which is to be cleaned of dangling references.
     */
    public static void removeDanglingReferences(final ResourceSet resourceSet) {
        DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.REMOVE_DANGLING_REFERENCE_KEY);
        ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(resourceSet);
        removeDanglingReferences(accessor, new DanglingReferencesDetector(resourceSet));
        DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.REMOVE_DANGLING_REFERENCE_KEY);
    }

    private static void removeDanglingReferences(ModelAccessor accessor, CrossReferencer referencer) {
        if (accessor != null) {
            for (Map.Entry<EObject, Collection<Setting>> entry : referencer.entrySet()) {
                for (EStructuralFeature.Setting value : entry.getValue()) {
                    if (value.getEStructuralFeature().isChangeable() && !value.getEStructuralFeature().isTransient() && value.getEObject() != null
                            && PermissionAuthorityRegistry.getDefault().getPermissionAuthority(value.getEObject()).canEditInstance(value.getEObject())) {
                        EcoreUtil.remove(value, entry.getKey());
                    }
                }
            }
        }
    }

    /**
     * Specific {@link CrossReferencer} to detect dangling references.
     */
    private static class DanglingReferencesDetector extends EcoreUtil.CrossReferencer {

        private static final long serialVersionUID = 616050158241084372L;

        /**
         * Creates an instance for the given resource.
         * 
         * @param resource
         *            the resource to cross reference.
         */
        public DanglingReferencesDetector(Resource eResource) {
            super(eResource);
            crossReference();
            done();
        }

        /**
         * Creates an instance for the given resource set.
         * 
         * @param resourceSet
         *            the resource set to cross reference.
         */
        public DanglingReferencesDetector(ResourceSet resourceSet) {
            super(resourceSet);
            crossReference();
            done();
        }

        @Override
        protected boolean crossReference(final EObject eObject, final EReference eReference, final EObject crossReferencedEObject) {
            return crossReferencedEObject.eResource() == null;
        }
    }
}
