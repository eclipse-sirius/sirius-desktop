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
package org.eclipse.sirius.business.api.helper.task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.query.ResourceQuery;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.FeatureNotFoundException;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.MetaClassNotFoundException;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;

/**
 * A {@link ICommandTask} to remove dangling references between semantic
 * {@link EObject}s.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class RemoveSemanticDanglingReferenceTask extends AbstractCommandTask implements ICommandTask {

    private IPermissionAuthority permissionAuthority;

    private Set<EObject> semanticElements;

    private UICallBack uiCallBack;

    /**
     * Default constructor.
     * 
     * @param permissionAuthority
     *            the {@link IPermissionAuthority}
     * 
     * @param semanticElements
     *            semantic elements
     * @param uiCallBack
     *            {@link UICallBack}
     */
    public RemoveSemanticDanglingReferenceTask(IPermissionAuthority permissionAuthority, Set<EObject> semanticElements, UICallBack uiCallBack) {
        this.permissionAuthority = permissionAuthority;
        this.semanticElements = semanticElements;
        this.uiCallBack = uiCallBack;
    }

    /**
     * {@inheritDoc}
     * 
     * @see viewpoint.command2.tasks.ICommandTask#getLabel()
     */
    public String getLabel() {
        return "remove semanitc dangling references task";
    }

    /**
     * {@inheritDoc}
     * 
     * @see viewpoint.command2.tasks.ICommandTask#execute()
     */
    public void execute() throws MetaClassNotFoundException, FeatureNotFoundException {

        EcoreUtil.CrossReferencer semanticCrossReferencer = new SemanticCrossReferencer(semanticElements);

        final List<Reference> toRemove = new ArrayList<Reference>();
        final List<Reference> lockedRef = new ArrayList<Reference>();
        final Iterator<Entry<EObject, Collection<Setting>>> i = semanticCrossReferencer.entrySet().iterator();
        while (i.hasNext()) {
            final Map.Entry<EObject, Collection<Setting>> entry = i.next();
            final Iterator<Setting> j = entry.getValue().iterator();
            while (j.hasNext()) {
                try {
                    final EStructuralFeature.Setting value = j.next();

                    final Reference ref = new Reference(value, entry.getKey());

                    /*
                     * premature ending if we don't have the right.
                     */
                    if (value.getEObject() != null && !permissionAuthority.canEditInstance(value.getEObject())) {
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

        boolean override = false;
        if (lockedRef.size() > 0) {

            final Collection<EObject> eObjects = new LinkedHashSet<EObject>();
            for (final Reference ref : lockedRef) {
                final EStructuralFeature.Setting value = ref.value;
                if (value.getEObject() != null) {
                    eObjects.add(value.getEObject());
                }
            }

            override = uiCallBack
                    .openEObjectsDialogMessage(eObjects, "Permission issue",
                            "One of the objects referencing a deleted one is locked and will have a dangling reference, should we continue ? \n If you continue you'll have to remove these dangling references by hand.");
            if (!override) {
                throw new OperationCanceledException();
            }
        }
        if (lockedRef.size() == 0 || override) {
            for (final Reference ref : toRemove) {
                try {
                    EcoreUtil.remove(ref.value, ref.obj);
                } catch (final UnsupportedOperationException e) {
                    // we know some time the setting is unsettable, just ignore
                    // that cases
                }
            }
        }
    }

    private final class SemanticCrossReferencer extends EcoreUtil.CrossReferencer {

        private static final long serialVersionUID = 1L;

        /**
         * Default constructor.
         * 
         * @param emfObjects
         *            {@link EObject} for which find semantic cross references.
         * 
         *            {@inheritDoc}
         */
        public SemanticCrossReferencer(Collection<?> emfObjects) {
            super(emfObjects);
            crossReference();
            done();
        }

        /**
         * Overridden to tell to the {@link EcoreUtil.CrossReferencer} to ignore
         * references from session resource.
         * 
         * {@inheritDoc}
         */
        @Override
        protected boolean crossReference(final EObject eObject, final EReference eReference, final EObject crossReferencedEObject) {
            return eReference.isChangeable() && crossReferencedEObject.eResource() == null && eObject.eResource() != null && !(new ResourceQuery(eObject.eResource()).isRepresentationsResource());
        }

    }

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
