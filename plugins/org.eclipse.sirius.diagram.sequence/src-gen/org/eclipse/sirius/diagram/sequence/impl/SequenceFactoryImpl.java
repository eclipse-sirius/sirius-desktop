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
package org.eclipse.sirius.diagram.sequence.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.SequenceFactory;
import org.eclipse.sirius.diagram.sequence.SequencePackage;
import org.eclipse.sirius.diagram.sequence.business.internal.metamodel.SequenceDDiagramSpec;
import org.eclipse.sirius.diagram.sequence.ordering.EventEndsOrdering;
import org.eclipse.sirius.diagram.sequence.ordering.InstanceRolesOrdering;
import org.eclipse.sirius.diagram.sequence.ordering.OrderingFactory;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class SequenceFactoryImpl extends EFactoryImpl implements SequenceFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public static SequenceFactory init() {
        try {
            SequenceFactory theSequenceFactory = (SequenceFactory) EPackage.Registry.INSTANCE.getEFactory(SequencePackage.eNS_URI);
            if (theSequenceFactory != null) {
                return theSequenceFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new SequenceFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public SequenceFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
        case SequencePackage.SEQUENCE_DDIAGRAM:
            return createSequenceDDiagram();
        default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public SequenceDDiagram createSequenceDDiagram() {
        SequenceDDiagramSpec sequenceDDiagram = new SequenceDDiagramSpec();
        EventEndsOrdering smo = OrderingFactory.eINSTANCE.createEventEndsOrdering();
        sequenceDDiagram.setSemanticOrdering(smo);
        smo.setSequenceDiagram(sequenceDDiagram);
        EventEndsOrdering gro = OrderingFactory.eINSTANCE.createEventEndsOrdering();
        sequenceDDiagram.setGraphicalOrdering(gro);
        gro.setSequenceDiagram(sequenceDDiagram);
        InstanceRolesOrdering irso = OrderingFactory.eINSTANCE.createInstanceRolesOrdering();
        sequenceDDiagram.setInstanceRoleSemanticOrdering(irso);
        return sequenceDDiagram;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public SequencePackage getSequencePackage() {
        return (SequencePackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @deprecated
     * @generated
     */
    @Deprecated
    public static SequencePackage getPackage() {
        return SequencePackage.eINSTANCE;
    }

} // SequenceFactoryImpl
