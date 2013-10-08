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
package org.eclipse.sirius.business.api.helper.task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.business.api.query.ResourceQuery;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

/**
 * A task which remove dangling references.
 * 
 * @author mchauvin
 */
public class RemoveDanglingReferencesTask extends AbstractCommandTask {
    /**
     * Name of the GMF View feature to ignore in the remove dangling references.
     */
    private static final String GMF_REFERENCE_NAME_TO_IGNORE = "element";

    /**
     * Container name of the GMF View feature to ignore in the remove dangling
     * references.
     */
    private static final String GMF_REFERENCE_CONTAINER_NAME_TO_IGNORE = "org.eclipse.gmf.runtime.notation.View";

    /**
     * Name of the ECore reference to ignore in the remove dangling references.
     */
    private static final String ECORE_REFERENCE_NAME_TO_IGNORE = "eFactoryInstance";

    /**
     * Container name of the ECore reference to ignore in the remove dangling
     * references.
     */
    private static final String ECORE_REFERENCE_CONTAINER_NAME_TO_IGNORE = "org.eclipse.emf.ecore.EPackage";

    private EObject root;

    private Resource resource;

    /**
     * Default constructor.
     * 
     * @param root
     *            the root object.
     */
    public RemoveDanglingReferencesTask(final EObject root) {
        this.root = root;
        this.resource = root.eResource();
    }

    /**
     * Get the current root object.
     * 
     * @return the current root object.
     */

    public EObject getRoot() {
        return root;
    }

    /**
     * {@inheritDoc}
     * 
     * @see viewpoint.command2.tasks.ICommandTask#execute()
     */
    public void execute() {
        final IPermissionAuthority authority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(root);
        if (authority != null && !authority.canEditInstance(root)) {
            return;
        }

        final ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(root);
        EcoreUtil.CrossReferencer referencer = new CustomReferencer(resource);

        final List<Reference> toRemove = new ArrayList<Reference>();
        final List<Reference> lockedRef = new ArrayList<Reference>();

        for (Map.Entry<EObject, Collection<Setting>> entry : referencer.entrySet()) {
            for (EStructuralFeature.Setting value : entry.getValue()) {
                try {
                    final Reference ref = new Reference(value, entry.getKey());

                    /*
                     * premature ending if we don't have the right.
                     */
                    if (value.getEObject() != null && accessor != null && !accessor.getPermissionAuthority().canEditInstance(value.getEObject())) {
                        lockedRef.add(ref);
                    } else {
                        toRemove.add(ref);
                    }
                } catch (final UnsupportedOperationException e) {
                    // we know some time the setting is unsettable, just ignore
                    // that cases
                }
            }
        }

        for (final Reference ref : toRemove) {
            try {
                EcoreUtil.remove(ref.value, ref.obj);
            } catch (final UnsupportedOperationException e) {
                // we know some time the setting is unsettable, just ignore
                // that cases
            }
        }

    }

    /**
     * {@inheritDoc}
     * 
     * @see viewpoint.command2.tasks.ICommandTask#getLabel()
     */
    public String getLabel() {
        return "remove dangling references task";
    }

    private final class CustomReferencer extends EcoreUtil.CrossReferencer {
        private static final long serialVersionUID = 616050158241084372L;

        private boolean automaticRefresh = Platform.getPreferencesService().getBoolean(SiriusPlugin.ID, SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false, null);

        private CustomReferencer(Resource resource) {
            super(resource);
            crossReference();
            done();
        }

        @Override
        protected boolean crossReference(final EObject eObject, final EReference eReference, final EObject crossReferencedEObject) {
            boolean crossReference = false;
            boolean impactAllReps = automaticRefresh || isContainedInRoot(eObject);
            boolean concernOnlySemantic = !(new ResourceQuery(eObject.eResource()).isRepresentationsResource());
            boolean ignore = crossReferencedEObject.eResource() == null && eReference.isChangeable() && !isReferenceToIgnore(eReference);
            crossReference = (impactAllReps || concernOnlySemantic) && ignore;
            return crossReference;
        }

        private boolean isContainedInRoot(EObject eObject) {
            boolean isContainedInRoot = false;
            EObject container = eObject;
            while (container != null && container != root) {
                container = container.eContainer();
            }
            isContainedInRoot = container == root;
            return isContainedInRoot;
        }

        private boolean isReferenceToIgnore(final EReference eReference) {
            return
            // ignoring the View.element reference
            GMF_REFERENCE_NAME_TO_IGNORE.equals(eReference.getName()) && GMF_REFERENCE_CONTAINER_NAME_TO_IGNORE.equals(eReference.getContainerClass().getName())
            // ignoring the EPackage.eFactoryInstance reference
                    || ECORE_REFERENCE_NAME_TO_IGNORE.equals(eReference.getName()) && ECORE_REFERENCE_CONTAINER_NAME_TO_IGNORE.equals(eReference.getContainerClass().getName());
        }
        // TODO : ignore any transient reference ?
    }

    /**
     * a Java struct.
     * 
     * @author mchauvin
     */
    private static final class Reference {
        private EStructuralFeature.Setting value;

        private Object obj;

        /**
         * Constructor.
         * 
         * @param value
         *            the value
         * @param obj
         *            the object
         */
        public Reference(final EStructuralFeature.Setting value, final Object obj) {
            this.value = value;
            this.obj = obj;
        }

    }
}
