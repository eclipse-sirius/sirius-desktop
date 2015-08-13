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
package org.eclipse.sirius.table.metamodel.table.description.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.sirius.table.business.internal.color.DefaultColorStyleDescription;
import org.eclipse.sirius.table.business.internal.metamodel.description.spec.CreateCellToolSpec;
import org.eclipse.sirius.table.business.internal.metamodel.description.spec.CreateColumnToolSpec;
import org.eclipse.sirius.table.business.internal.metamodel.description.spec.CreateLineToolSpec;
import org.eclipse.sirius.table.business.internal.metamodel.description.spec.CrossTableDescriptionSpec;
import org.eclipse.sirius.table.business.internal.metamodel.description.spec.DeleteColumnToolSpec;
import org.eclipse.sirius.table.business.internal.metamodel.description.spec.DeleteLineToolSpec;
import org.eclipse.sirius.table.business.internal.metamodel.description.spec.EditionTableDescriptionSpec;
import org.eclipse.sirius.table.business.internal.metamodel.description.spec.FeatureColumnMappingSpec;
import org.eclipse.sirius.table.business.internal.metamodel.description.spec.IntersectionMappingSpec;
import org.eclipse.sirius.table.business.internal.metamodel.description.spec.LabelEditToolSpec;
import org.eclipse.sirius.table.business.internal.metamodel.description.spec.LineMappingSpec;
import org.eclipse.sirius.table.business.internal.metamodel.description.spec.TableCreationDescriptionSpec;
import org.eclipse.sirius.table.business.internal.metamodel.description.spec.TableNavigationDescriptionSpec;
import org.eclipse.sirius.table.metamodel.table.description.BackgroundConditionalStyle;
import org.eclipse.sirius.table.metamodel.table.description.BackgroundStyleDescription;
import org.eclipse.sirius.table.metamodel.table.description.CellUpdater;
import org.eclipse.sirius.table.metamodel.table.description.ColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.CreateCellTool;
import org.eclipse.sirius.table.metamodel.table.description.CreateColumnTool;
import org.eclipse.sirius.table.metamodel.table.description.CreateCrossColumnTool;
import org.eclipse.sirius.table.metamodel.table.description.CreateLineTool;
import org.eclipse.sirius.table.metamodel.table.description.CrossTableDescription;
import org.eclipse.sirius.table.metamodel.table.description.DeleteColumnTool;
import org.eclipse.sirius.table.metamodel.table.description.DeleteLineTool;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionFactory;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage;
import org.eclipse.sirius.table.metamodel.table.description.EditionTableDescription;
import org.eclipse.sirius.table.metamodel.table.description.ElementColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.FeatureColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.ForegroundConditionalStyle;
import org.eclipse.sirius.table.metamodel.table.description.ForegroundStyleDescription;
import org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping;
import org.eclipse.sirius.table.metamodel.table.description.LabelEditTool;
import org.eclipse.sirius.table.metamodel.table.description.LineMapping;
import org.eclipse.sirius.table.metamodel.table.description.TableCreationDescription;
import org.eclipse.sirius.table.metamodel.table.description.TableMapping;
import org.eclipse.sirius.table.metamodel.table.description.TableNavigationDescription;
import org.eclipse.sirius.table.metamodel.table.description.TableTool;
import org.eclipse.sirius.table.metamodel.table.description.TableVariable;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class DescriptionFactoryImpl extends EFactoryImpl implements DescriptionFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public static DescriptionFactory init() {
        try {
            DescriptionFactory theDescriptionFactory = (DescriptionFactory) EPackage.Registry.INSTANCE.getEFactory(DescriptionPackage.eNS_URI);
            if (theDescriptionFactory != null) {
                return theDescriptionFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new DescriptionFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public DescriptionFactoryImpl() {
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
        case DescriptionPackage.EDITION_TABLE_DESCRIPTION:
            return createEditionTableDescription();
        case DescriptionPackage.CROSS_TABLE_DESCRIPTION:
            return createCrossTableDescription();
        case DescriptionPackage.TABLE_MAPPING:
            return createTableMapping();
        case DescriptionPackage.LINE_MAPPING:
            return createLineMapping();
        case DescriptionPackage.COLUMN_MAPPING:
            return createColumnMapping();
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING:
            return createElementColumnMapping();
        case DescriptionPackage.FEATURE_COLUMN_MAPPING:
            return createFeatureColumnMapping();
        case DescriptionPackage.CELL_UPDATER:
            return createCellUpdater();
        case DescriptionPackage.INTERSECTION_MAPPING:
            return createIntersectionMapping();
        case DescriptionPackage.TABLE_TOOL:
            return createTableTool();
        case DescriptionPackage.LABEL_EDIT_TOOL:
            return createLabelEditTool();
        case DescriptionPackage.CREATE_COLUMN_TOOL:
            return createCreateColumnTool();
        case DescriptionPackage.CREATE_CROSS_COLUMN_TOOL:
            return createCreateCrossColumnTool();
        case DescriptionPackage.CREATE_LINE_TOOL:
            return createCreateLineTool();
        case DescriptionPackage.CREATE_CELL_TOOL:
            return createCreateCellTool();
        case DescriptionPackage.DELETE_COLUMN_TOOL:
            return createDeleteColumnTool();
        case DescriptionPackage.DELETE_LINE_TOOL:
            return createDeleteLineTool();
        case DescriptionPackage.FOREGROUND_STYLE_DESCRIPTION:
            return createForegroundStyleDescription();
        case DescriptionPackage.BACKGROUND_STYLE_DESCRIPTION:
            return createBackgroundStyleDescription();
        case DescriptionPackage.FOREGROUND_CONDITIONAL_STYLE:
            return createForegroundConditionalStyle();
        case DescriptionPackage.BACKGROUND_CONDITIONAL_STYLE:
            return createBackgroundConditionalStyle();
        case DescriptionPackage.TABLE_VARIABLE:
            return createTableVariable();
        case DescriptionPackage.TABLE_CREATION_DESCRIPTION:
            return createTableCreationDescription();
        case DescriptionPackage.TABLE_NAVIGATION_DESCRIPTION:
            return createTableNavigationDescription();
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
    public EditionTableDescription createEditionTableDescription() {
        EditionTableDescriptionImpl editionTableDescription = new EditionTableDescriptionSpec();
        return editionTableDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public CrossTableDescription createCrossTableDescription() {
        CrossTableDescriptionImpl crossTableDescription = new CrossTableDescriptionSpec();
        return crossTableDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public TableMapping createTableMapping() {
        TableMappingImpl tableMapping = new TableMappingImpl();
        return tableMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    @Override
    public LineMapping createLineMapping() {
        LineMappingImpl lineMapping = new LineMappingSpec();
        return lineMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ColumnMapping createColumnMapping() {
        ColumnMappingImpl columnMapping = new ColumnMappingImpl();
        return columnMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public FeatureColumnMapping createFeatureColumnMapping() {
        final FeatureColumnMappingImpl featureColumnMapping = new FeatureColumnMappingSpec();
        return featureColumnMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public CellUpdater createCellUpdater() {
        CellUpdaterImpl cellUpdater = new CellUpdaterImpl();
        return cellUpdater;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ElementColumnMapping createElementColumnMapping() {
        ElementColumnMappingImpl elementColumnMapping = new ElementColumnMappingImpl();
        return elementColumnMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public IntersectionMapping createIntersectionMapping() {
        final IntersectionMappingImpl intersectionMapping = new IntersectionMappingSpec();
        return intersectionMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public TableTool createTableTool() {
        TableToolImpl tableTool = new TableToolImpl();
        return tableTool;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public LabelEditTool createLabelEditTool() {
        final LabelEditToolImpl labelEditTool = new LabelEditToolSpec();
        return labelEditTool;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public CreateColumnTool createCreateColumnTool() {
        CreateColumnToolImpl createColumnTool = new CreateColumnToolSpec();
        return createColumnTool;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public CreateCrossColumnTool createCreateCrossColumnTool() {
        CreateCrossColumnToolImpl createCrossColumnTool = new CreateCrossColumnToolImpl();
        return createCrossColumnTool;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public CreateLineTool createCreateLineTool() {
        CreateLineToolImpl createLineTool = new CreateLineToolSpec();
        return createLineTool;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public CreateCellTool createCreateCellTool() {
        final CreateCellToolImpl createCellTool = new CreateCellToolSpec();
        return createCellTool;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public DeleteColumnTool createDeleteColumnTool() {
        DeleteColumnToolImpl deleteColumnTool = new DeleteColumnToolSpec();
        return deleteColumnTool;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public DeleteLineTool createDeleteLineTool() {
        DeleteLineToolImpl deleteLineTool = new DeleteLineToolSpec();
        return deleteLineTool;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public ForegroundStyleDescription createForegroundStyleDescription() {
        ForegroundStyleDescriptionImpl foregroundStyleDescription = new ForegroundStyleDescriptionImpl();
        new DefaultColorStyleDescription().setDefaultColors(foregroundStyleDescription);
        return foregroundStyleDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public BackgroundStyleDescription createBackgroundStyleDescription() {
        BackgroundStyleDescription backgroundStyleDescription = new BackgroundStyleDescriptionImpl();
        new DefaultColorStyleDescription().setDefaultColors(backgroundStyleDescription);
        return backgroundStyleDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ForegroundConditionalStyle createForegroundConditionalStyle() {
        ForegroundConditionalStyleImpl foregroundConditionalStyle = new ForegroundConditionalStyleImpl();
        return foregroundConditionalStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public BackgroundConditionalStyle createBackgroundConditionalStyle() {
        BackgroundConditionalStyleImpl backgroundConditionalStyle = new BackgroundConditionalStyleImpl();
        return backgroundConditionalStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public TableVariable createTableVariable() {
        TableVariableImpl tableVariable = new TableVariableImpl();
        return tableVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public TableCreationDescription createTableCreationDescription() {
        TableCreationDescriptionImpl tableCreationDescription = new TableCreationDescriptionSpec();
        return tableCreationDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public TableNavigationDescription createTableNavigationDescription() {
        TableNavigationDescriptionImpl tableNavigationDescription = new TableNavigationDescriptionSpec();
        return tableNavigationDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public DescriptionPackage getDescriptionPackage() {
        return (DescriptionPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @deprecated
     * @generated
     */
    @Deprecated
    public static DescriptionPackage getPackage() {
        return DescriptionPackage.eINSTANCE;
    }

} // DescriptionFactoryImpl
