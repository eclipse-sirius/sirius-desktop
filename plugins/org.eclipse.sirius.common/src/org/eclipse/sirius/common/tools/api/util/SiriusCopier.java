/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.common.tools.api.util;

import java.util.Collection;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;

/**
 * Utility class used to copy object with special management for {@link EAttribute} that are id attribute.</br>
 * This copier ensure that id attribute of copied object will have a different value from the original Object.
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
     * Creates an instance that resolves proxies or not as specified.
     * 
     * @param resolveProxies
     *            whether proxies should be resolved while copying.
     */
    public SiriusCopier(boolean resolveProxies) {
        this.resolveProxies = resolveProxies;
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
     * This method does not copy {@link EAttribute} that is an id (see {@link EAttribute#isID()}).<br/>
     * If the id has not been set by the factory and if the attribute type is String, sets it using the
     * EcoreUtil.generateUUID().
     * 
     * @see org.eclipse.emf.ecore.util.EcoreUtil.Copier#copyAttributeValue(EAttribute, EObject, Object, Setting)
     */
    @Override
    protected void copyAttributeValue(EAttribute eAttribute, EObject eObject, Object value, Setting setting) {
        Object valueToCopy = value;
        if (eAttribute.isID()) {
            if (!setting.isSet() && String.class.equals(eAttribute.getEAttributeType().getInstanceClass())) {
                // See org.eclipse.emf.ecore.util.EcoreUtil.setID(EObject, String)
                valueToCopy = EcoreUtil.generateUUID();
            }
            return;
        }
        super.copyAttributeValue(eAttribute, eObject, valueToCopy, setting);
    }

    /**
     * Utility to ease the use of the {@link SiriusCopier}. <br/>
     * The behavior of the utility methods comes from EcoreUtil, the only change it the use of a {@link SiriusCopier}
     * instead of a {@link EcoreUtil.Copier}.
     */
    public static class Helper {

        /**
         * Returns a self-contained copy of the eObject.
         * 
         * The copy will have an id different from the copied EObject, see {@link SiriusCopier}.
         * 
         * @param eObject
         *            the object to copy.
         * @param <T>
         *            a subclass of {@link EObject}
         * @return the copy
         * @see SiriusCopier
         * @see EcoreUtil#copy(EObject)
         */
        public static <T extends EObject> T copy(T eObject) {

            Copier copier = new SiriusCopier();
            EObject result = copier.copy(eObject);
            copier.copyReferences();

            @SuppressWarnings("unchecked")
            T t = (T) result;
            return t;
        }

        /**
         * Returns a collection of the self-contained copies of each {@link EObject} in eObjects.
         * 
         * The copies will have an id different from the copied EObjects, see {@link SiriusCopier}.
         * 
         * @param eObjects
         *            the collection of objects to copy.
         * @param <T>
         *            a subclass of {@link EObject}
         * @return the collection of copies.
         * @see SiriusCopier
         * @see EcoreUtil#copyAll(Collection)
         */
        public static <T> Collection<T> copyAll(Collection<? extends T> eObjects) {
            Copier copier = new SiriusCopier();
            Collection<T> result = copier.copyAll(eObjects);
            copier.copyReferences();
            return result;
        }
    }

}
