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
package org.eclipse.sirius.table.metamodel.table.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.sirius.table.business.internal.metamodel.spec.DCellSpec;
import org.eclipse.sirius.table.business.internal.metamodel.spec.DFeatureColumnSpec;
import org.eclipse.sirius.table.business.internal.metamodel.spec.DLineSpec;
import org.eclipse.sirius.table.business.internal.metamodel.spec.DTableSpec;
import org.eclipse.sirius.table.business.internal.metamodel.spec.DTargetColumnSpec;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DCellStyle;
import org.eclipse.sirius.table.metamodel.table.DFeatureColumn;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.DTableElementStyle;
import org.eclipse.sirius.table.metamodel.table.DTargetColumn;
import org.eclipse.sirius.table.metamodel.table.TableFactory;
import org.eclipse.sirius.table.metamodel.table.TablePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class TableFactoryImpl extends EFactoryImpl implements TableFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public static TableFactory init() {
        try {
            TableFactory theTableFactory = (TableFactory) EPackage.Registry.INSTANCE.getEFactory(TablePackage.eNS_URI);
            if (theTableFactory != null) {
                return theTableFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new TableFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public TableFactoryImpl() {
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
        case TablePackage.DTABLE:
            return createDTable();
        case TablePackage.DLINE:
            return createDLine();
        case TablePackage.DCELL:
            return createDCell();
        case TablePackage.DCELL_STYLE:
            return createDCellStyle();
        case TablePackage.DTARGET_COLUMN:
            return createDTargetColumn();
        case TablePackage.DFEATURE_COLUMN:
            return createDFeatureColumn();
        case TablePackage.DTABLE_ELEMENT_STYLE:
            return createDTableElementStyle();
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
    public DTable createDTable() {
        DTableImpl dTable = new DTableSpec();
        return dTable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public DLine createDLine() {
        DLineImpl dLine = new DLineSpec();
        return dLine;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public DCell createDCell() {
        DCellImpl dCell = new DCellSpec();
        return dCell;
    }

    /**
     * <!-- begin-user-doc --> We automaticcaly add a default BackgroundColor
     * and ForegroundColor to can display something in the properties View <!--
     * end-user-doc -->
     * 
     * @generated
     */
    @Override
    public DCellStyle createDCellStyle() {
        DCellStyleImpl dCellStyle = new DCellStyleImpl();
        return dCellStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public DTargetColumn createDTargetColumn() {
        DTargetColumnImpl dTargetColumn = new DTargetColumnSpec();
        return dTargetColumn;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public DFeatureColumn createDFeatureColumn() {
        DFeatureColumnImpl dFeatureColumn = new DFeatureColumnSpec();
        return dFeatureColumn;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public DTableElementStyle createDTableElementStyle() {
        DTableElementStyleImpl dTableElementStyle = new DTableElementStyleImpl();
        return dTableElementStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public TablePackage getTablePackage() {
        return (TablePackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @deprecated
     * @generated
     */
    @Deprecated
    public static TablePackage getPackage() {
        return TablePackage.eINSTANCE;
    }

} // TableFactoryImpl
