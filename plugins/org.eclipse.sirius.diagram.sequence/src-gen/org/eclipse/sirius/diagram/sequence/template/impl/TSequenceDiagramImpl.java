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
package org.eclipse.sirius.diagram.sequence.template.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.diagram.sequence.template.TLifelineMapping;
import org.eclipse.sirius.diagram.sequence.template.TMessageMapping;
import org.eclipse.sirius.diagram.sequence.template.TSequenceDiagram;
import org.eclipse.sirius.diagram.sequence.template.TTransformer;
import org.eclipse.sirius.diagram.sequence.template.TemplatePackage;
import org.eclipse.sirius.viewpoint.description.impl.RepresentationTemplateImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>TSequence Diagram</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.template.impl.TSequenceDiagramImpl#getOutputs
 * <em>Outputs</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.template.impl.TSequenceDiagramImpl#getDomainClass
 * <em>Domain Class</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.template.impl.TSequenceDiagramImpl#getEndsOrdering
 * <em>Ends Ordering</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.template.impl.TSequenceDiagramImpl#getLifelineMappings
 * <em>Lifeline Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.template.impl.TSequenceDiagramImpl#getMessageMappings
 * <em>Message Mappings</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TSequenceDiagramImpl extends RepresentationTemplateImpl implements TSequenceDiagram {
    /**
     * The cached value of the '{@link #getOutputs() <em>Outputs</em>}'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getOutputs()
     * @generated
     * @ordered
     */
    protected EList<EObject> outputs;

    /**
     * The default value of the '{@link #getDomainClass() <em>Domain Class</em>}
     * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getDomainClass()
     * @generated
     * @ordered
     */
    protected static final String DOMAIN_CLASS_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDomainClass() <em>Domain Class</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getDomainClass()
     * @generated
     * @ordered
     */
    protected String domainClass = TSequenceDiagramImpl.DOMAIN_CLASS_EDEFAULT;

    /**
     * The default value of the '{@link #getEndsOrdering()
     * <em>Ends Ordering</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getEndsOrdering()
     * @generated
     * @ordered
     */
    protected static final String ENDS_ORDERING_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getEndsOrdering()
     * <em>Ends Ordering</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getEndsOrdering()
     * @generated
     * @ordered
     */
    protected String endsOrdering = TSequenceDiagramImpl.ENDS_ORDERING_EDEFAULT;

    /**
     * The cached value of the '{@link #getLifelineMappings()
     * <em>Lifeline Mappings</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getLifelineMappings()
     * @generated
     * @ordered
     */
    protected EList<TLifelineMapping> lifelineMappings;

    /**
     * The cached value of the '{@link #getMessageMappings()
     * <em>Message Mappings</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getMessageMappings()
     * @generated
     * @ordered
     */
    protected EList<TMessageMapping> messageMappings;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected TSequenceDiagramImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TemplatePackage.Literals.TSEQUENCE_DIAGRAM;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<EObject> getOutputs() {
        if (outputs == null) {
            outputs = new EObjectResolvingEList<EObject>(EObject.class, this, TemplatePackage.TSEQUENCE_DIAGRAM__OUTPUTS);
        }
        return outputs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getEndsOrdering() {
        return endsOrdering;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setEndsOrdering(String newEndsOrdering) {
        String oldEndsOrdering = endsOrdering;
        endsOrdering = newEndsOrdering;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TSEQUENCE_DIAGRAM__ENDS_ORDERING, oldEndsOrdering, endsOrdering));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<TLifelineMapping> getLifelineMappings() {
        if (lifelineMappings == null) {
            lifelineMappings = new EObjectContainmentEList<TLifelineMapping>(TLifelineMapping.class, this, TemplatePackage.TSEQUENCE_DIAGRAM__LIFELINE_MAPPINGS);
        }
        return lifelineMappings;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<TMessageMapping> getMessageMappings() {
        if (messageMappings == null) {
            messageMappings = new EObjectContainmentEList<TMessageMapping>(TMessageMapping.class, this, TemplatePackage.TSEQUENCE_DIAGRAM__MESSAGE_MAPPINGS);
        }
        return messageMappings;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getDomainClass() {
        return domainClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setDomainClass(String newDomainClass) {
        String oldDomainClass = domainClass;
        domainClass = newDomainClass;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TSEQUENCE_DIAGRAM__DOMAIN_CLASS, oldDomainClass, domainClass));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case TemplatePackage.TSEQUENCE_DIAGRAM__LIFELINE_MAPPINGS:
            return ((InternalEList<?>) getLifelineMappings()).basicRemove(otherEnd, msgs);
        case TemplatePackage.TSEQUENCE_DIAGRAM__MESSAGE_MAPPINGS:
            return ((InternalEList<?>) getMessageMappings()).basicRemove(otherEnd, msgs);
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
        case TemplatePackage.TSEQUENCE_DIAGRAM__OUTPUTS:
            return getOutputs();
        case TemplatePackage.TSEQUENCE_DIAGRAM__DOMAIN_CLASS:
            return getDomainClass();
        case TemplatePackage.TSEQUENCE_DIAGRAM__ENDS_ORDERING:
            return getEndsOrdering();
        case TemplatePackage.TSEQUENCE_DIAGRAM__LIFELINE_MAPPINGS:
            return getLifelineMappings();
        case TemplatePackage.TSEQUENCE_DIAGRAM__MESSAGE_MAPPINGS:
            return getMessageMappings();
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
        case TemplatePackage.TSEQUENCE_DIAGRAM__OUTPUTS:
            getOutputs().clear();
            getOutputs().addAll((Collection<? extends EObject>) newValue);
            return;
        case TemplatePackage.TSEQUENCE_DIAGRAM__DOMAIN_CLASS:
            setDomainClass((String) newValue);
            return;
        case TemplatePackage.TSEQUENCE_DIAGRAM__ENDS_ORDERING:
            setEndsOrdering((String) newValue);
            return;
        case TemplatePackage.TSEQUENCE_DIAGRAM__LIFELINE_MAPPINGS:
            getLifelineMappings().clear();
            getLifelineMappings().addAll((Collection<? extends TLifelineMapping>) newValue);
            return;
        case TemplatePackage.TSEQUENCE_DIAGRAM__MESSAGE_MAPPINGS:
            getMessageMappings().clear();
            getMessageMappings().addAll((Collection<? extends TMessageMapping>) newValue);
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
        case TemplatePackage.TSEQUENCE_DIAGRAM__OUTPUTS:
            getOutputs().clear();
            return;
        case TemplatePackage.TSEQUENCE_DIAGRAM__DOMAIN_CLASS:
            setDomainClass(TSequenceDiagramImpl.DOMAIN_CLASS_EDEFAULT);
            return;
        case TemplatePackage.TSEQUENCE_DIAGRAM__ENDS_ORDERING:
            setEndsOrdering(TSequenceDiagramImpl.ENDS_ORDERING_EDEFAULT);
            return;
        case TemplatePackage.TSEQUENCE_DIAGRAM__LIFELINE_MAPPINGS:
            getLifelineMappings().clear();
            return;
        case TemplatePackage.TSEQUENCE_DIAGRAM__MESSAGE_MAPPINGS:
            getMessageMappings().clear();
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
        case TemplatePackage.TSEQUENCE_DIAGRAM__OUTPUTS:
            return outputs != null && !outputs.isEmpty();
        case TemplatePackage.TSEQUENCE_DIAGRAM__DOMAIN_CLASS:
            return TSequenceDiagramImpl.DOMAIN_CLASS_EDEFAULT == null ? domainClass != null : !TSequenceDiagramImpl.DOMAIN_CLASS_EDEFAULT.equals(domainClass);
        case TemplatePackage.TSEQUENCE_DIAGRAM__ENDS_ORDERING:
            return TSequenceDiagramImpl.ENDS_ORDERING_EDEFAULT == null ? endsOrdering != null : !TSequenceDiagramImpl.ENDS_ORDERING_EDEFAULT.equals(endsOrdering);
        case TemplatePackage.TSEQUENCE_DIAGRAM__LIFELINE_MAPPINGS:
            return lifelineMappings != null && !lifelineMappings.isEmpty();
        case TemplatePackage.TSEQUENCE_DIAGRAM__MESSAGE_MAPPINGS:
            return messageMappings != null && !messageMappings.isEmpty();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
        if (baseClass == TTransformer.class) {
            switch (derivedFeatureID) {
            case TemplatePackage.TSEQUENCE_DIAGRAM__OUTPUTS:
                return TemplatePackage.TTRANSFORMER__OUTPUTS;
            default:
                return -1;
            }
        }
        return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
        if (baseClass == TTransformer.class) {
            switch (baseFeatureID) {
            case TemplatePackage.TTRANSFORMER__OUTPUTS:
                return TemplatePackage.TSEQUENCE_DIAGRAM__OUTPUTS;
            default:
                return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) {
            return super.toString();
        }

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (domainClass: "); //$NON-NLS-1$
        result.append(domainClass);
        result.append(", endsOrdering: "); //$NON-NLS-1$
        result.append(endsOrdering);
        result.append(')');
        return result.toString();
    }

} // TSequenceDiagramImpl
