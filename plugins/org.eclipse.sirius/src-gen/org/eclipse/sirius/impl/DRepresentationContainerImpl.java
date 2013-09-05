/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.sirius.DDiagramSet;
import org.eclipse.sirius.DRepresentationContainer;
import org.eclipse.sirius.DSemanticDiagram;
import org.eclipse.sirius.SiriusPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>DRepresentation Container</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.impl.DRepresentationContainerImpl#getDiagramSet
 * <em>Diagram Set</em>}</li>
 * <li>{@link org.eclipse.sirius.impl.DRepresentationContainerImpl#getModels
 * <em>Models</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class DRepresentationContainerImpl extends DViewImpl implements DRepresentationContainer {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = "Copyright (c) 2007-2013 THALES GLOBAL SERVICES\n All rights reserved.\n\n Contributors:\n     Obeo - Initial API and implementation";

    /**
     * The cached value of the '{@link #getDiagramSet() <em>Diagram Set</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getDiagramSet()
     * @generated
     * @ordered
     */
    protected EList<DDiagramSet> diagramSet;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected DRepresentationContainerImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return SiriusPackage.Literals.DREPRESENTATION_CONTAINER;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<DDiagramSet> getDiagramSet() {
        if (diagramSet == null) {
            diagramSet = new EObjectContainmentEList.Unsettable.Resolving<DDiagramSet>(DDiagramSet.class, this, SiriusPackage.DREPRESENTATION_CONTAINER__DIAGRAM_SET);
        }
        return diagramSet;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void unsetDiagramSet() {
        if (diagramSet != null)
            ((InternalEList.Unsettable<?>) diagramSet).unset();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public boolean isSetDiagramSet() {
        return diagramSet != null && ((InternalEList.Unsettable<?>) diagramSet).isSet();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<EObject> getModels() {
        // TODO: implement this method to return the 'Models' reference list
        // Ensure that you remove @generated or mark it @generated NOT
        // The list is expected to implement
        // org.eclipse.emf.ecore.util.InternalEList and
        // org.eclipse.emf.ecore.EStructuralFeature.Setting
        // so it's likely that an appropriate subclass of
        // org.eclipse.emf.ecore.util.EcoreEList should be used.
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void addSemanticDiagram(DSemanticDiagram diagram) {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case SiriusPackage.DREPRESENTATION_CONTAINER__DIAGRAM_SET:
            return ((InternalEList<?>) getDiagramSet()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case SiriusPackage.DREPRESENTATION_CONTAINER__DIAGRAM_SET:
            return getDiagramSet();
        case SiriusPackage.DREPRESENTATION_CONTAINER__MODELS:
            return getModels();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case SiriusPackage.DREPRESENTATION_CONTAINER__DIAGRAM_SET:
            getDiagramSet().clear();
            getDiagramSet().addAll((Collection<? extends DDiagramSet>) newValue);
            return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
        case SiriusPackage.DREPRESENTATION_CONTAINER__DIAGRAM_SET:
            unsetDiagramSet();
            return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
        case SiriusPackage.DREPRESENTATION_CONTAINER__DIAGRAM_SET:
            return isSetDiagramSet();
        case SiriusPackage.DREPRESENTATION_CONTAINER__MODELS:
            return !getModels().isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // DRepresentationContainerImpl
