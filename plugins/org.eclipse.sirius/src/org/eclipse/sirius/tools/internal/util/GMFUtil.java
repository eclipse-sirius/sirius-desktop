/*******************************************************************************
 * Copyright (c) 2008, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tools.internal.util;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * Useful operations linked to GMF.
 * 
 * @author mchauvin
 */
@Deprecated
public final class GMFUtil {

    /**
     * Avoid instantiation
     */
    private GMFUtil() {

    }

    /**
     * This method comes from GMF. Tears down references to the object that we
     * are destroying, from all other objects in the resource set.
     * 
     * @param destructee
     *            the object being destroyed
     */
    public static void tearDownIncomingReferences(final EObject destructee) {
        final CrossReferenceAdapter crossReferencer = CrossReferenceAdapter.getExistingCrossReferenceAdapter(destructee);
        if (crossReferencer != null) {
            final Collection<Setting> inverseReferences = crossReferencer.getInverseReferences(destructee);
            if (inverseReferences != null) {
                final int size = inverseReferences.size();
                if (size > 0) {
                    Setting setting;
                    EReference eRef;
                    final Setting[] settings = inverseReferences.toArray(new Setting[size]);
                    for (int i = 0; i < size; ++i) {
                        setting = settings[i];
                        eRef = (EReference) setting.getEStructuralFeature();
                        if (eRef.isChangeable() && (!eRef.isDerived()) && (!eRef.isContainment()) && (!eRef.isContainer())) {
                            EcoreUtil.remove(setting.getEObject(), eRef, destructee);
                        }
                    }
                }
            }
        }
    }

    /**
     * This method comes from GMF. Tears down outgoing unidirectional references
     * from the object being destroyed to all other elements in the resource
     * set. This is required so that reverse-reference queries will not find the
     * destroyed object.
     * 
     * @param destructee
     *            the object being destroyed
     */
    public static void tearDownOutgoingReferences(final EObject destructee) {
        final Iterator<EReference> iter = destructee.eClass().getEAllReferences().iterator();
        while (iter.hasNext()) {
            final EReference reference = iter.next();

            // container/containment features are handled separately, and
            // bidirectional references were handled via incomings
            final boolean changeable = reference.isChangeable() && !reference.isDerived();
            if (changeable && !reference.isContainer() && !reference.isContainment() && (reference.getEOpposite() == null)) {

                if (destructee.eIsSet(reference)) {
                    destructee.eUnset(reference);
                }
            }
        }
    }
}
