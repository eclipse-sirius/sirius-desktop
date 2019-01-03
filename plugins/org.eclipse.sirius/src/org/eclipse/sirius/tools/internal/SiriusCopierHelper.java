/*******************************************************************************
 * Copyright (c) 2018, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tools.internal;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl;

/**
 * Utility class to ease the copy of {@link EObject} <br/>
 * The behavior of the utility methods comes from EcoreUtil, the only change it the use of a {@link SiriusCopierHelper}
 * instead of a {@link EcoreUtil.Copier}.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 *
 */
public final class SiriusCopierHelper {
    private SiriusCopierHelper() {
    }

    /**
     * Utility class used to copy object with special management for {@link EAttribute} IDENTIFIED_ELEMENT__UID.</br>
     * If the uid has not been set by the factory, it sets it using the EcoreUtil.generateUUID()
     * 
     * @see Copier
     * 
     * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
     */
    public class SiriusCopier extends Copier {

        private static final long serialVersionUID = -5736164116383051335L;

        /**
         * Creates an instance.
         */
        public SiriusCopier() {
            super();
        }

        /**
         * Creates an instance that resolves proxies or not and uses non-copied references or not as specified.
         * 
         * @param resolveProxies
         *            whether proxies should be resolved while copying.
         * @param useOriginalReferences
         *            whether non-copied references should be used while copying.
         */
        public SiriusCopier(boolean resolveProxies, boolean useOriginalReferences) {
            this.resolveProxies = resolveProxies;
            this.useOriginalReferences = useOriginalReferences;
        }

        /**
         * This method does not copy the {@link EAttribute} IDENTIFIED_ELEMENT__UID.<br/>
         * If the uid has not been set by the factory or constructor, it sets it using the EcoreUtil.generateUUID().
         * 
         * @see org.eclipse.emf.ecore.util.EcoreUtil.Copier#copyAttributeValue(EAttribute, EObject, Object, Setting)
         */
        @Override
        protected void copyAttributeValue(EAttribute eAttribute, EObject eObject, Object value, Setting setting) {
            Object valueToCopy = value;
            if (eAttribute.equals(ViewpointPackageImpl.eINSTANCE.getIdentifiedElement_Uid())) {
                // Currently, this uid is set in the {@link IdentifiedElement} constructor but if that changes the test
                // !setting.isSet() will be useful.
                if (!setting.isSet()) {
                    // See org.eclipse.emf.ecore.util.EcoreUtil.setID(EObject, String)
                    valueToCopy = EcoreUtil.generateUUID();
                }
                return;
            }
            super.copyAttributeValue(eAttribute, eObject, valueToCopy, setting);
        }
    }

    /**
     * Returns a self-contained copy of the eObject.
     * 
     * The copy will have an uid different from the copied EObject.
     * 
     * @param eObject
     *            the object to copy.
     * @param <T>
     *            a subclass of {@link EObject}
     * @return the copy
     * @see EcoreUtil#copy(EObject)
     */
    public static <T extends EObject> T copyWithNoUidDuplication(T eObject) {

        SiriusCopierHelper helper = new SiriusCopierHelper();
        Copier copier = helper.new SiriusCopier();
        EObject result = copier.copy(eObject);
        copier.copyReferences();
        @SuppressWarnings("unchecked")
        T t = (T) result;
        return t;
    }

    /**
     * Returns a collection of the self-contained copies of each {@link EObject} in eObjects.
     * 
     * This method does not copy the {@link EAttribute} IDENTIFIED_ELEMENT__UID.<br/>
     * If the uid has not been set by the factory or constructor, sets it using the EcoreUtil.generateUUID().
     * 
     * @param eObjects
     *            the collection of objects to copy.
     * @param <T>
     *            a subclass of {@link EObject}
     * @return the collection of copies.
     * @see EcoreUtil#copyAll(Collection)
     */
    public static <T> Collection<T> copyAllWithNoUidDuplication(Collection<? extends T> eObjects) {
        SiriusCopierHelper helper = new SiriusCopierHelper();
        Copier copier = helper.new SiriusCopier();
        Collection<T> result = copier.copyAll(eObjects);
        copier.copyReferences();
        return result;
    }

    /**
     * Returns a collection of copies of each {@link EObject} in eObjects.
     * 
     * This method does not copy the {@link EAttribute} IDENTIFIED_ELEMENT__UID.<br/>
     * If the uid has not been set by the factory or constructor, it sets it using the EcoreUtil.generateUUID().
     * 
     * @param eObjects
     *            the collection of objects to copy.
     * @param resolveProxies
     *            whether proxies should be resolved while copying.
     * @param useOriginalReferences
     *            whether non-copied references should be used while copying.
     * @param copyReferences
     *            whether references should be copied.
     * @return A map which keys are eObjects to copy and values are copied objects.
     * @see EcoreUtil#Copier()
     */
    public static Map<EObject, EObject> copyAllWithNoUidDuplication(Collection<? extends EObject> eObjects, boolean resolveProxies, boolean useOriginalReferences, boolean copyReferences) {
        SiriusCopierHelper helper = new SiriusCopierHelper();
        Copier copier = helper.new SiriusCopier(resolveProxies, useOriginalReferences);
        copier.copyAll(eObjects);
        if (copyReferences) {
            copier.copyReferences();
        }
        return copier;
    }
}
