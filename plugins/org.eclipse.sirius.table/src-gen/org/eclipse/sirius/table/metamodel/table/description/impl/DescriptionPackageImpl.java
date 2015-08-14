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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.sirius.table.metamodel.table.TablePackage;
import org.eclipse.sirius.table.metamodel.table.description.BackgroundConditionalStyle;
import org.eclipse.sirius.table.metamodel.table.description.BackgroundStyleDescription;
import org.eclipse.sirius.table.metamodel.table.description.CellUpdater;
import org.eclipse.sirius.table.metamodel.table.description.ColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.CreateCellTool;
import org.eclipse.sirius.table.metamodel.table.description.CreateColumnTool;
import org.eclipse.sirius.table.metamodel.table.description.CreateCrossColumnTool;
import org.eclipse.sirius.table.metamodel.table.description.CreateLineTool;
import org.eclipse.sirius.table.metamodel.table.description.CreateTool;
import org.eclipse.sirius.table.metamodel.table.description.CrossTableDescription;
import org.eclipse.sirius.table.metamodel.table.description.DeleteColumnTool;
import org.eclipse.sirius.table.metamodel.table.description.DeleteLineTool;
import org.eclipse.sirius.table.metamodel.table.description.DeleteTool;
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
import org.eclipse.sirius.table.metamodel.table.description.StyleUpdater;
import org.eclipse.sirius.table.metamodel.table.description.TableCreationDescription;
import org.eclipse.sirius.table.metamodel.table.description.TableDescription;
import org.eclipse.sirius.table.metamodel.table.description.TableMapping;
import org.eclipse.sirius.table.metamodel.table.description.TableNavigationDescription;
import org.eclipse.sirius.table.metamodel.table.description.TableTool;
import org.eclipse.sirius.table.metamodel.table.description.TableVariable;
import org.eclipse.sirius.table.metamodel.table.impl.TablePackageImpl;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class DescriptionPackageImpl extends EPackageImpl implements DescriptionPackage {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass tableDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass editionTableDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass crossTableDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass tableMappingEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass lineMappingEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass columnMappingEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass elementColumnMappingEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass featureColumnMappingEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass cellUpdaterEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass styleUpdaterEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass intersectionMappingEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass tableToolEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass labelEditToolEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass createToolEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass createColumnToolEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass createCrossColumnToolEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass createLineToolEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass createCellToolEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass deleteToolEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass deleteColumnToolEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass deleteLineToolEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass foregroundStyleDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass backgroundStyleDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass foregroundConditionalStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass backgroundConditionalStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass tableVariableEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass tableCreationDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass tableNavigationDescriptionEClass = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the
     * package package URI value.
     * <p>
     * Note: the correct way to create the package is via the static factory
     * method {@link #init init()}, which also performs initialization of the
     * package, or returns the registered package, if one already exists. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private DescriptionPackageImpl() {
        super(DescriptionPackage.eNS_URI, DescriptionFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model,
     * and for any others upon which it depends.
     *
     * <p>
     * This method is used to initialize {@link DescriptionPackage#eINSTANCE}
     * when that field is accessed. Clients should not invoke it directly.
     * Instead, they should simply access that field to obtain the package. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static DescriptionPackage init() {
        if (DescriptionPackageImpl.isInited) {
            return (DescriptionPackage) EPackage.Registry.INSTANCE.getEPackage(DescriptionPackage.eNS_URI);
        }

        // Obtain or create and register package
        DescriptionPackageImpl theDescriptionPackage = (DescriptionPackageImpl) (EPackage.Registry.INSTANCE.get(DescriptionPackage.eNS_URI) instanceof DescriptionPackageImpl ? EPackage.Registry.INSTANCE
                .get(DescriptionPackage.eNS_URI) : new DescriptionPackageImpl());

        DescriptionPackageImpl.isInited = true;

        // Initialize simple dependencies
        ViewpointPackage.eINSTANCE.eClass();

        // Obtain or create and register interdependencies
        TablePackageImpl theTablePackage = (TablePackageImpl) (EPackage.Registry.INSTANCE.getEPackage(TablePackage.eNS_URI) instanceof TablePackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(TablePackage.eNS_URI) : TablePackage.eINSTANCE);

        // Create package meta-data objects
        theDescriptionPackage.createPackageContents();
        theTablePackage.createPackageContents();

        // Initialize created meta-data
        theDescriptionPackage.initializePackageContents();
        theTablePackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theDescriptionPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(DescriptionPackage.eNS_URI, theDescriptionPackage);
        return theDescriptionPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getTableDescription() {
        return tableDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getTableDescription_PreconditionExpression() {
        return (EAttribute) tableDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getTableDescription_DomainClass() {
        return (EAttribute) tableDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTableDescription_OwnedRepresentationCreationDescriptions() {
        return (EReference) tableDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTableDescription_ReusedRepresentationCreationDescriptions() {
        return (EReference) tableDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTableDescription_AllRepresentationCreationDescriptions() {
        return (EReference) tableDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTableDescription_OwnedRepresentationNavigationDescriptions() {
        return (EReference) tableDescriptionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTableDescription_ReusedRepresentationNavigationDescriptions() {
        return (EReference) tableDescriptionEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTableDescription_AllRepresentationNavigationDescriptions() {
        return (EReference) tableDescriptionEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTableDescription_OwnedLineMappings() {
        return (EReference) tableDescriptionEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTableDescription_ReusedLineMappings() {
        return (EReference) tableDescriptionEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTableDescription_AllLineMappings() {
        return (EReference) tableDescriptionEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTableDescription_OwnedCreateLine() {
        return (EReference) tableDescriptionEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTableDescription_ReusedCreateLine() {
        return (EReference) tableDescriptionEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTableDescription_AllCreateLine() {
        return (EReference) tableDescriptionEClass.getEStructuralFeatures().get(13);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getTableDescription_InitialHeaderColumnWidth() {
        return (EAttribute) tableDescriptionEClass.getEStructuralFeatures().get(14);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTableDescription_ImportedElements() {
        return (EReference) tableDescriptionEClass.getEStructuralFeatures().get(15);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getEditionTableDescription() {
        return editionTableDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getEditionTableDescription_OwnedColumnMappings() {
        return (EReference) editionTableDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getEditionTableDescription_ReusedColumnMappings() {
        return (EReference) editionTableDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getEditionTableDescription_AllColumnMappings() {
        return (EReference) editionTableDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getCrossTableDescription() {
        return crossTableDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getCrossTableDescription_OwnedColumnMappings() {
        return (EReference) crossTableDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getCrossTableDescription_Intersection() {
        return (EReference) crossTableDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getCrossTableDescription_CreateColumn() {
        return (EReference) crossTableDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getTableMapping() {
        return tableMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getTableMapping_SemanticElements() {
        return (EAttribute) tableMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getLineMapping() {
        return lineMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getLineMapping_OwnedSubLines() {
        return (EReference) lineMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getLineMapping_ReusedSubLines() {
        return (EReference) lineMappingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getLineMapping_AllSubLines() {
        return (EReference) lineMappingEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getLineMapping_ReusedInMappings() {
        return (EReference) lineMappingEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getLineMapping_DomainClass() {
        return (EAttribute) lineMappingEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getLineMapping_Create() {
        return (EReference) lineMappingEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getLineMapping_Delete() {
        return (EReference) lineMappingEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getLineMapping_SemanticCandidatesExpression() {
        return (EAttribute) lineMappingEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getLineMapping_HeaderLabelExpression() {
        return (EAttribute) lineMappingEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getColumnMapping() {
        return columnMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getColumnMapping_HeaderLabelExpression() {
        return (EAttribute) columnMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getColumnMapping_InitialWidth() {
        return (EAttribute) columnMappingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getElementColumnMapping() {
        return elementColumnMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getElementColumnMapping_DomainClass() {
        return (EAttribute) elementColumnMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getElementColumnMapping_SemanticCandidatesExpression() {
        return (EAttribute) elementColumnMappingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getElementColumnMapping_Create() {
        return (EReference) elementColumnMappingEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getElementColumnMapping_Delete() {
        return (EReference) elementColumnMappingEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getFeatureColumnMapping() {
        return featureColumnMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getFeatureColumnMapping_FeatureName() {
        return (EAttribute) featureColumnMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getFeatureColumnMapping_LabelExpression() {
        return (EAttribute) featureColumnMappingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getFeatureColumnMapping_FeatureParentExpression() {
        return (EAttribute) featureColumnMappingEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getCellUpdater() {
        return cellUpdaterEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getCellUpdater_DirectEdit() {
        return (EReference) cellUpdaterEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getCellUpdater_CanEdit() {
        return (EAttribute) cellUpdaterEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getStyleUpdater() {
        return styleUpdaterEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getStyleUpdater_DefaultForeground() {
        return (EReference) styleUpdaterEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getStyleUpdater_ForegroundConditionalStyle() {
        return (EReference) styleUpdaterEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getStyleUpdater_DefaultBackground() {
        return (EReference) styleUpdaterEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getStyleUpdater_BackgroundConditionalStyle() {
        return (EReference) styleUpdaterEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getIntersectionMapping() {
        return intersectionMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getIntersectionMapping_LineMapping() {
        return (EReference) intersectionMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getIntersectionMapping_ColumnMapping() {
        return (EReference) intersectionMappingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getIntersectionMapping_LabelExpression() {
        return (EAttribute) intersectionMappingEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getIntersectionMapping_UseDomainClass() {
        return (EAttribute) intersectionMappingEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getIntersectionMapping_ColumnFinderExpression() {
        return (EAttribute) intersectionMappingEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getIntersectionMapping_LineFinderExpression() {
        return (EAttribute) intersectionMappingEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getIntersectionMapping_SemanticCandidatesExpression() {
        return (EAttribute) intersectionMappingEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getIntersectionMapping_DomainClass() {
        return (EAttribute) intersectionMappingEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getIntersectionMapping_PreconditionExpression() {
        return (EAttribute) intersectionMappingEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getIntersectionMapping_Create() {
        return (EReference) intersectionMappingEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getTableTool() {
        return tableToolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTableTool_Variables() {
        return (EReference) tableToolEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTableTool_FirstModelOperation() {
        return (EReference) tableToolEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getLabelEditTool() {
        return labelEditToolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getLabelEditTool_Mask() {
        return (EReference) labelEditToolEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getCreateTool() {
        return createToolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getCreateColumnTool() {
        return createColumnToolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getCreateColumnTool_Mapping() {
        return (EReference) createColumnToolEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getCreateCrossColumnTool() {
        return createCrossColumnToolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getCreateCrossColumnTool_Mapping() {
        return (EReference) createCrossColumnToolEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getCreateLineTool() {
        return createLineToolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getCreateLineTool_Mapping() {
        return (EReference) createLineToolEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getCreateCellTool() {
        return createCellToolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getCreateCellTool_Mask() {
        return (EReference) createCellToolEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getCreateCellTool_Mapping() {
        return (EReference) createCellToolEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getDeleteTool() {
        return deleteToolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getDeleteColumnTool() {
        return deleteColumnToolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getDeleteColumnTool_Mapping() {
        return (EReference) deleteColumnToolEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getDeleteLineTool() {
        return deleteLineToolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getDeleteLineTool_Mapping() {
        return (EReference) deleteLineToolEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getForegroundStyleDescription() {
        return foregroundStyleDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getForegroundStyleDescription_LabelSize() {
        return (EAttribute) foregroundStyleDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getForegroundStyleDescription_LabelFormat() {
        return (EAttribute) foregroundStyleDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getForegroundStyleDescription_ForeGroundColor() {
        return (EReference) foregroundStyleDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getBackgroundStyleDescription() {
        return backgroundStyleDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getBackgroundStyleDescription_BackgroundColor() {
        return (EReference) backgroundStyleDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getForegroundConditionalStyle() {
        return foregroundConditionalStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getForegroundConditionalStyle_PredicateExpression() {
        return (EAttribute) foregroundConditionalStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getForegroundConditionalStyle_Style() {
        return (EReference) foregroundConditionalStyleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getBackgroundConditionalStyle() {
        return backgroundConditionalStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getBackgroundConditionalStyle_PredicateExpression() {
        return (EAttribute) backgroundConditionalStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getBackgroundConditionalStyle_Style() {
        return (EReference) backgroundConditionalStyleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getTableVariable() {
        return tableVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getTableVariable_Documentation() {
        return (EAttribute) tableVariableEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getTableCreationDescription() {
        return tableCreationDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTableCreationDescription_TableDescription() {
        return (EReference) tableCreationDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getTableNavigationDescription() {
        return tableNavigationDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTableNavigationDescription_TableDescription() {
        return (EReference) tableNavigationDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public DescriptionFactory getDescriptionFactory() {
        return (DescriptionFactory) getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package. This method is guarded to
     * have no affect on any invocation but its first. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void createPackageContents() {
        if (isCreated) {
            return;
        }
        isCreated = true;

        // Create classes and their features
        tableDescriptionEClass = createEClass(DescriptionPackage.TABLE_DESCRIPTION);
        createEAttribute(tableDescriptionEClass, DescriptionPackage.TABLE_DESCRIPTION__PRECONDITION_EXPRESSION);
        createEAttribute(tableDescriptionEClass, DescriptionPackage.TABLE_DESCRIPTION__DOMAIN_CLASS);
        createEReference(tableDescriptionEClass, DescriptionPackage.TABLE_DESCRIPTION__OWNED_REPRESENTATION_CREATION_DESCRIPTIONS);
        createEReference(tableDescriptionEClass, DescriptionPackage.TABLE_DESCRIPTION__REUSED_REPRESENTATION_CREATION_DESCRIPTIONS);
        createEReference(tableDescriptionEClass, DescriptionPackage.TABLE_DESCRIPTION__ALL_REPRESENTATION_CREATION_DESCRIPTIONS);
        createEReference(tableDescriptionEClass, DescriptionPackage.TABLE_DESCRIPTION__OWNED_REPRESENTATION_NAVIGATION_DESCRIPTIONS);
        createEReference(tableDescriptionEClass, DescriptionPackage.TABLE_DESCRIPTION__REUSED_REPRESENTATION_NAVIGATION_DESCRIPTIONS);
        createEReference(tableDescriptionEClass, DescriptionPackage.TABLE_DESCRIPTION__ALL_REPRESENTATION_NAVIGATION_DESCRIPTIONS);
        createEReference(tableDescriptionEClass, DescriptionPackage.TABLE_DESCRIPTION__OWNED_LINE_MAPPINGS);
        createEReference(tableDescriptionEClass, DescriptionPackage.TABLE_DESCRIPTION__REUSED_LINE_MAPPINGS);
        createEReference(tableDescriptionEClass, DescriptionPackage.TABLE_DESCRIPTION__ALL_LINE_MAPPINGS);
        createEReference(tableDescriptionEClass, DescriptionPackage.TABLE_DESCRIPTION__OWNED_CREATE_LINE);
        createEReference(tableDescriptionEClass, DescriptionPackage.TABLE_DESCRIPTION__REUSED_CREATE_LINE);
        createEReference(tableDescriptionEClass, DescriptionPackage.TABLE_DESCRIPTION__ALL_CREATE_LINE);
        createEAttribute(tableDescriptionEClass, DescriptionPackage.TABLE_DESCRIPTION__INITIAL_HEADER_COLUMN_WIDTH);
        createEReference(tableDescriptionEClass, DescriptionPackage.TABLE_DESCRIPTION__IMPORTED_ELEMENTS);

        editionTableDescriptionEClass = createEClass(DescriptionPackage.EDITION_TABLE_DESCRIPTION);
        createEReference(editionTableDescriptionEClass, DescriptionPackage.EDITION_TABLE_DESCRIPTION__OWNED_COLUMN_MAPPINGS);
        createEReference(editionTableDescriptionEClass, DescriptionPackage.EDITION_TABLE_DESCRIPTION__REUSED_COLUMN_MAPPINGS);
        createEReference(editionTableDescriptionEClass, DescriptionPackage.EDITION_TABLE_DESCRIPTION__ALL_COLUMN_MAPPINGS);

        crossTableDescriptionEClass = createEClass(DescriptionPackage.CROSS_TABLE_DESCRIPTION);
        createEReference(crossTableDescriptionEClass, DescriptionPackage.CROSS_TABLE_DESCRIPTION__OWNED_COLUMN_MAPPINGS);
        createEReference(crossTableDescriptionEClass, DescriptionPackage.CROSS_TABLE_DESCRIPTION__INTERSECTION);
        createEReference(crossTableDescriptionEClass, DescriptionPackage.CROSS_TABLE_DESCRIPTION__CREATE_COLUMN);

        tableMappingEClass = createEClass(DescriptionPackage.TABLE_MAPPING);
        createEAttribute(tableMappingEClass, DescriptionPackage.TABLE_MAPPING__SEMANTIC_ELEMENTS);

        lineMappingEClass = createEClass(DescriptionPackage.LINE_MAPPING);
        createEReference(lineMappingEClass, DescriptionPackage.LINE_MAPPING__OWNED_SUB_LINES);
        createEReference(lineMappingEClass, DescriptionPackage.LINE_MAPPING__REUSED_SUB_LINES);
        createEReference(lineMappingEClass, DescriptionPackage.LINE_MAPPING__ALL_SUB_LINES);
        createEReference(lineMappingEClass, DescriptionPackage.LINE_MAPPING__REUSED_IN_MAPPINGS);
        createEAttribute(lineMappingEClass, DescriptionPackage.LINE_MAPPING__DOMAIN_CLASS);
        createEReference(lineMappingEClass, DescriptionPackage.LINE_MAPPING__CREATE);
        createEReference(lineMappingEClass, DescriptionPackage.LINE_MAPPING__DELETE);
        createEAttribute(lineMappingEClass, DescriptionPackage.LINE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION);
        createEAttribute(lineMappingEClass, DescriptionPackage.LINE_MAPPING__HEADER_LABEL_EXPRESSION);

        columnMappingEClass = createEClass(DescriptionPackage.COLUMN_MAPPING);
        createEAttribute(columnMappingEClass, DescriptionPackage.COLUMN_MAPPING__HEADER_LABEL_EXPRESSION);
        createEAttribute(columnMappingEClass, DescriptionPackage.COLUMN_MAPPING__INITIAL_WIDTH);

        elementColumnMappingEClass = createEClass(DescriptionPackage.ELEMENT_COLUMN_MAPPING);
        createEAttribute(elementColumnMappingEClass, DescriptionPackage.ELEMENT_COLUMN_MAPPING__DOMAIN_CLASS);
        createEAttribute(elementColumnMappingEClass, DescriptionPackage.ELEMENT_COLUMN_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION);
        createEReference(elementColumnMappingEClass, DescriptionPackage.ELEMENT_COLUMN_MAPPING__CREATE);
        createEReference(elementColumnMappingEClass, DescriptionPackage.ELEMENT_COLUMN_MAPPING__DELETE);

        featureColumnMappingEClass = createEClass(DescriptionPackage.FEATURE_COLUMN_MAPPING);
        createEAttribute(featureColumnMappingEClass, DescriptionPackage.FEATURE_COLUMN_MAPPING__FEATURE_NAME);
        createEAttribute(featureColumnMappingEClass, DescriptionPackage.FEATURE_COLUMN_MAPPING__LABEL_EXPRESSION);
        createEAttribute(featureColumnMappingEClass, DescriptionPackage.FEATURE_COLUMN_MAPPING__FEATURE_PARENT_EXPRESSION);

        cellUpdaterEClass = createEClass(DescriptionPackage.CELL_UPDATER);
        createEReference(cellUpdaterEClass, DescriptionPackage.CELL_UPDATER__DIRECT_EDIT);
        createEAttribute(cellUpdaterEClass, DescriptionPackage.CELL_UPDATER__CAN_EDIT);

        styleUpdaterEClass = createEClass(DescriptionPackage.STYLE_UPDATER);
        createEReference(styleUpdaterEClass, DescriptionPackage.STYLE_UPDATER__DEFAULT_FOREGROUND);
        createEReference(styleUpdaterEClass, DescriptionPackage.STYLE_UPDATER__FOREGROUND_CONDITIONAL_STYLE);
        createEReference(styleUpdaterEClass, DescriptionPackage.STYLE_UPDATER__DEFAULT_BACKGROUND);
        createEReference(styleUpdaterEClass, DescriptionPackage.STYLE_UPDATER__BACKGROUND_CONDITIONAL_STYLE);

        intersectionMappingEClass = createEClass(DescriptionPackage.INTERSECTION_MAPPING);
        createEReference(intersectionMappingEClass, DescriptionPackage.INTERSECTION_MAPPING__LINE_MAPPING);
        createEReference(intersectionMappingEClass, DescriptionPackage.INTERSECTION_MAPPING__COLUMN_MAPPING);
        createEAttribute(intersectionMappingEClass, DescriptionPackage.INTERSECTION_MAPPING__LABEL_EXPRESSION);
        createEAttribute(intersectionMappingEClass, DescriptionPackage.INTERSECTION_MAPPING__USE_DOMAIN_CLASS);
        createEAttribute(intersectionMappingEClass, DescriptionPackage.INTERSECTION_MAPPING__COLUMN_FINDER_EXPRESSION);
        createEAttribute(intersectionMappingEClass, DescriptionPackage.INTERSECTION_MAPPING__LINE_FINDER_EXPRESSION);
        createEAttribute(intersectionMappingEClass, DescriptionPackage.INTERSECTION_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION);
        createEAttribute(intersectionMappingEClass, DescriptionPackage.INTERSECTION_MAPPING__DOMAIN_CLASS);
        createEAttribute(intersectionMappingEClass, DescriptionPackage.INTERSECTION_MAPPING__PRECONDITION_EXPRESSION);
        createEReference(intersectionMappingEClass, DescriptionPackage.INTERSECTION_MAPPING__CREATE);

        tableToolEClass = createEClass(DescriptionPackage.TABLE_TOOL);
        createEReference(tableToolEClass, DescriptionPackage.TABLE_TOOL__VARIABLES);
        createEReference(tableToolEClass, DescriptionPackage.TABLE_TOOL__FIRST_MODEL_OPERATION);

        labelEditToolEClass = createEClass(DescriptionPackage.LABEL_EDIT_TOOL);
        createEReference(labelEditToolEClass, DescriptionPackage.LABEL_EDIT_TOOL__MASK);

        createToolEClass = createEClass(DescriptionPackage.CREATE_TOOL);

        createColumnToolEClass = createEClass(DescriptionPackage.CREATE_COLUMN_TOOL);
        createEReference(createColumnToolEClass, DescriptionPackage.CREATE_COLUMN_TOOL__MAPPING);

        createCrossColumnToolEClass = createEClass(DescriptionPackage.CREATE_CROSS_COLUMN_TOOL);
        createEReference(createCrossColumnToolEClass, DescriptionPackage.CREATE_CROSS_COLUMN_TOOL__MAPPING);

        createLineToolEClass = createEClass(DescriptionPackage.CREATE_LINE_TOOL);
        createEReference(createLineToolEClass, DescriptionPackage.CREATE_LINE_TOOL__MAPPING);

        createCellToolEClass = createEClass(DescriptionPackage.CREATE_CELL_TOOL);
        createEReference(createCellToolEClass, DescriptionPackage.CREATE_CELL_TOOL__MASK);
        createEReference(createCellToolEClass, DescriptionPackage.CREATE_CELL_TOOL__MAPPING);

        deleteToolEClass = createEClass(DescriptionPackage.DELETE_TOOL);

        deleteColumnToolEClass = createEClass(DescriptionPackage.DELETE_COLUMN_TOOL);
        createEReference(deleteColumnToolEClass, DescriptionPackage.DELETE_COLUMN_TOOL__MAPPING);

        deleteLineToolEClass = createEClass(DescriptionPackage.DELETE_LINE_TOOL);
        createEReference(deleteLineToolEClass, DescriptionPackage.DELETE_LINE_TOOL__MAPPING);

        foregroundStyleDescriptionEClass = createEClass(DescriptionPackage.FOREGROUND_STYLE_DESCRIPTION);
        createEAttribute(foregroundStyleDescriptionEClass, DescriptionPackage.FOREGROUND_STYLE_DESCRIPTION__LABEL_SIZE);
        createEAttribute(foregroundStyleDescriptionEClass, DescriptionPackage.FOREGROUND_STYLE_DESCRIPTION__LABEL_FORMAT);
        createEReference(foregroundStyleDescriptionEClass, DescriptionPackage.FOREGROUND_STYLE_DESCRIPTION__FORE_GROUND_COLOR);

        backgroundStyleDescriptionEClass = createEClass(DescriptionPackage.BACKGROUND_STYLE_DESCRIPTION);
        createEReference(backgroundStyleDescriptionEClass, DescriptionPackage.BACKGROUND_STYLE_DESCRIPTION__BACKGROUND_COLOR);

        foregroundConditionalStyleEClass = createEClass(DescriptionPackage.FOREGROUND_CONDITIONAL_STYLE);
        createEAttribute(foregroundConditionalStyleEClass, DescriptionPackage.FOREGROUND_CONDITIONAL_STYLE__PREDICATE_EXPRESSION);
        createEReference(foregroundConditionalStyleEClass, DescriptionPackage.FOREGROUND_CONDITIONAL_STYLE__STYLE);

        backgroundConditionalStyleEClass = createEClass(DescriptionPackage.BACKGROUND_CONDITIONAL_STYLE);
        createEAttribute(backgroundConditionalStyleEClass, DescriptionPackage.BACKGROUND_CONDITIONAL_STYLE__PREDICATE_EXPRESSION);
        createEReference(backgroundConditionalStyleEClass, DescriptionPackage.BACKGROUND_CONDITIONAL_STYLE__STYLE);

        tableVariableEClass = createEClass(DescriptionPackage.TABLE_VARIABLE);
        createEAttribute(tableVariableEClass, DescriptionPackage.TABLE_VARIABLE__DOCUMENTATION);

        tableCreationDescriptionEClass = createEClass(DescriptionPackage.TABLE_CREATION_DESCRIPTION);
        createEReference(tableCreationDescriptionEClass, DescriptionPackage.TABLE_CREATION_DESCRIPTION__TABLE_DESCRIPTION);

        tableNavigationDescriptionEClass = createEClass(DescriptionPackage.TABLE_NAVIGATION_DESCRIPTION);
        createEReference(tableNavigationDescriptionEClass, DescriptionPackage.TABLE_NAVIGATION_DESCRIPTION__TABLE_DESCRIPTION);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model. This
     * method is guarded to have no affect on any invocation but its first. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public void initializePackageContents() {
        if (isInitialized) {
            return;
        }
        isInitialized = true;

        // Initialize package
        setName(DescriptionPackage.eNAME);
        setNsPrefix(DescriptionPackage.eNS_PREFIX);
        setNsURI(DescriptionPackage.eNS_URI);

        // Obtain other dependent packages
        org.eclipse.sirius.viewpoint.description.DescriptionPackage theDescriptionPackage_1 = (org.eclipse.sirius.viewpoint.description.DescriptionPackage) EPackage.Registry.INSTANCE
                .getEPackage(org.eclipse.sirius.viewpoint.description.DescriptionPackage.eNS_URI);
        ToolPackage theToolPackage = (ToolPackage) EPackage.Registry.INSTANCE.getEPackage(ToolPackage.eNS_URI);
        EcorePackage theEcorePackage = (EcorePackage) EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);
        ViewpointPackage theViewpointPackage = (ViewpointPackage) EPackage.Registry.INSTANCE.getEPackage(ViewpointPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        tableDescriptionEClass.getESuperTypes().add(theDescriptionPackage_1.getRepresentationDescription());
        tableDescriptionEClass.getESuperTypes().add(theDescriptionPackage_1.getDocumentedElement());
        tableDescriptionEClass.getESuperTypes().add(theDescriptionPackage_1.getEndUserDocumentedElement());
        editionTableDescriptionEClass.getESuperTypes().add(this.getTableDescription());
        crossTableDescriptionEClass.getESuperTypes().add(this.getTableDescription());
        tableMappingEClass.getESuperTypes().add(theDescriptionPackage_1.getRepresentationElementMapping());
        lineMappingEClass.getESuperTypes().add(this.getTableMapping());
        lineMappingEClass.getESuperTypes().add(this.getStyleUpdater());
        columnMappingEClass.getESuperTypes().add(this.getTableMapping());
        elementColumnMappingEClass.getESuperTypes().add(this.getColumnMapping());
        elementColumnMappingEClass.getESuperTypes().add(this.getStyleUpdater());
        featureColumnMappingEClass.getESuperTypes().add(this.getColumnMapping());
        featureColumnMappingEClass.getESuperTypes().add(this.getCellUpdater());
        featureColumnMappingEClass.getESuperTypes().add(this.getStyleUpdater());
        intersectionMappingEClass.getESuperTypes().add(this.getTableMapping());
        intersectionMappingEClass.getESuperTypes().add(this.getCellUpdater());
        intersectionMappingEClass.getESuperTypes().add(this.getStyleUpdater());
        labelEditToolEClass.getESuperTypes().add(this.getTableTool());
        createToolEClass.getESuperTypes().add(theToolPackage.getAbstractToolDescription());
        createToolEClass.getESuperTypes().add(this.getTableTool());
        createColumnToolEClass.getESuperTypes().add(this.getCreateTool());
        createCrossColumnToolEClass.getESuperTypes().add(this.getCreateTool());
        createLineToolEClass.getESuperTypes().add(this.getCreateTool());
        createCellToolEClass.getESuperTypes().add(this.getTableTool());
        createCellToolEClass.getESuperTypes().add(theToolPackage.getAbstractToolDescription());
        deleteToolEClass.getESuperTypes().add(theToolPackage.getAbstractToolDescription());
        deleteToolEClass.getESuperTypes().add(this.getTableTool());
        deleteColumnToolEClass.getESuperTypes().add(this.getDeleteTool());
        deleteLineToolEClass.getESuperTypes().add(this.getDeleteTool());
        tableVariableEClass.getESuperTypes().add(theToolPackage.getAbstractVariable());
        tableVariableEClass.getESuperTypes().add(theToolPackage.getVariableContainer());
        tableCreationDescriptionEClass.getESuperTypes().add(theToolPackage.getRepresentationCreationDescription());
        tableNavigationDescriptionEClass.getESuperTypes().add(theToolPackage.getRepresentationNavigationDescription());

        // Initialize classes and features; add operations and parameters
        initEClass(tableDescriptionEClass, TableDescription.class, "TableDescription", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getTableDescription_PreconditionExpression(),
                theDescriptionPackage_1.getInterpretedExpression(),
                "preconditionExpression", "", 0, 1, TableDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(
                getTableDescription_DomainClass(),
                theDescriptionPackage_1.getTypeName(),
                "domainClass", null, 1, 1, TableDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getTableDescription_OwnedRepresentationCreationDescriptions(),
                theToolPackage.getRepresentationCreationDescription(),
                null,
                "ownedRepresentationCreationDescriptions", null, 0, -1, TableDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        getTableDescription_OwnedRepresentationCreationDescriptions().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());
        initEReference(
                getTableDescription_ReusedRepresentationCreationDescriptions(),
                theToolPackage.getRepresentationCreationDescription(),
                null,
                "reusedRepresentationCreationDescriptions", null, 0, -1, TableDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        getTableDescription_ReusedRepresentationCreationDescriptions().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());
        initEReference(
                getTableDescription_AllRepresentationCreationDescriptions(),
                theToolPackage.getRepresentationCreationDescription(),
                null,
                "allRepresentationCreationDescriptions", null, 0, -1, TableDescription.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, !EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        getTableDescription_AllRepresentationCreationDescriptions().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());
        initEReference(
                getTableDescription_OwnedRepresentationNavigationDescriptions(),
                theToolPackage.getRepresentationNavigationDescription(),
                null,
                "ownedRepresentationNavigationDescriptions", null, 0, -1, TableDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        getTableDescription_OwnedRepresentationNavigationDescriptions().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());
        initEReference(
                getTableDescription_ReusedRepresentationNavigationDescriptions(),
                theToolPackage.getRepresentationNavigationDescription(),
                null,
                "reusedRepresentationNavigationDescriptions", null, 0, -1, TableDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        getTableDescription_ReusedRepresentationNavigationDescriptions().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());
        initEReference(
                getTableDescription_AllRepresentationNavigationDescriptions(),
                theToolPackage.getRepresentationNavigationDescription(),
                null,
                "allRepresentationNavigationDescriptions", null, 0, -1, TableDescription.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, !EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        getTableDescription_AllRepresentationNavigationDescriptions().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());
        initEReference(
                getTableDescription_OwnedLineMappings(),
                this.getLineMapping(),
                null,
                "ownedLineMappings", null, 1, -1, TableDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        getTableDescription_OwnedLineMappings().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());
        initEReference(
                getTableDescription_ReusedLineMappings(),
                this.getLineMapping(),
                null,
                "reusedLineMappings", null, 0, -1, TableDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        getTableDescription_ReusedLineMappings().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());
        initEReference(
                getTableDescription_AllLineMappings(),
                this.getLineMapping(),
                null,
                "allLineMappings", null, 1, -1, TableDescription.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, !EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        getTableDescription_AllLineMappings().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());
        initEReference(
                getTableDescription_OwnedCreateLine(),
                this.getCreateLineTool(),
                null,
                "ownedCreateLine", null, 0, -1, TableDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getTableDescription_ReusedCreateLine(),
                this.getCreateLineTool(),
                null,
                "reusedCreateLine", null, 0, -1, TableDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getTableDescription_AllCreateLine(),
                this.getCreateLineTool(),
                null,
                "allCreateLine", null, 0, -1, TableDescription.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, !EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getTableDescription_InitialHeaderColumnWidth(),
                theEcorePackage.getEInt(),
                "initialHeaderColumnWidth", null, 0, 1, TableDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getTableDescription_ImportedElements(),
                theEcorePackage.getEObject(),
                null,
                "importedElements", null, 0, -1, TableDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(editionTableDescriptionEClass, EditionTableDescription.class,
                "EditionTableDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getEditionTableDescription_OwnedColumnMappings(),
                this.getFeatureColumnMapping(),
                null,
                "ownedColumnMappings", null, 1, -1, EditionTableDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        getEditionTableDescription_OwnedColumnMappings().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());
        initEReference(
                getEditionTableDescription_ReusedColumnMappings(),
                this.getFeatureColumnMapping(),
                null,
                "reusedColumnMappings", null, 0, -1, EditionTableDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        getEditionTableDescription_ReusedColumnMappings().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());
        initEReference(
                getEditionTableDescription_AllColumnMappings(),
                this.getFeatureColumnMapping(),
                null,
                "allColumnMappings", null, 1, -1, EditionTableDescription.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, !EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        getEditionTableDescription_AllColumnMappings().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());

        initEClass(crossTableDescriptionEClass, CrossTableDescription.class, "CrossTableDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getCrossTableDescription_OwnedColumnMappings(),
                this.getElementColumnMapping(),
                null,
                "ownedColumnMappings", null, 1, -1, CrossTableDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getCrossTableDescription_Intersection(),
                this.getIntersectionMapping(),
                null,
                "intersection", null, 0, -1, CrossTableDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getCrossTableDescription_CreateColumn(),
                this.getCreateCrossColumnTool(),
                null,
                "createColumn", null, 0, -1, CrossTableDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(tableMappingEClass, TableMapping.class, "TableMapping", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getTableMapping_SemanticElements(),
                theDescriptionPackage_1.getInterpretedExpression(),
                "semanticElements", null, 0, 1, TableMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(lineMappingEClass, LineMapping.class, "LineMapping", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getLineMapping_OwnedSubLines(),
                this.getLineMapping(),
                null,
                "ownedSubLines", null, 0, -1, LineMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        getLineMapping_OwnedSubLines().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());
        initEReference(
                getLineMapping_ReusedSubLines(),
                this.getLineMapping(),
                this.getLineMapping_ReusedInMappings(),
                "reusedSubLines", null, 0, -1, LineMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getLineMapping_AllSubLines(),
                this.getLineMapping(),
                null,
                "allSubLines", null, 0, -1, LineMapping.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, !EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getLineMapping_ReusedInMappings(),
                this.getLineMapping(),
                this.getLineMapping_ReusedSubLines(),
                "reusedInMappings", null, 0, -1, LineMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getLineMapping_DomainClass(),
                theDescriptionPackage_1.getTypeName(),
                "domainClass", null, 1, 1, LineMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getLineMapping_Create(),
                this.getCreateLineTool(),
                null,
                "create", null, 0, -1, LineMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getLineMapping_Delete(),
                this.getDeleteLineTool(),
                this.getDeleteLineTool_Mapping(),
                "delete", null, 0, 1, LineMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getLineMapping_SemanticCandidatesExpression(),
                theDescriptionPackage_1.getInterpretedExpression(),
                "semanticCandidatesExpression", null, 0, 1, LineMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getLineMapping_HeaderLabelExpression(),
                theDescriptionPackage_1.getInterpretedExpression(),
                "headerLabelExpression", null, 0, 1, LineMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(columnMappingEClass, ColumnMapping.class, "ColumnMapping", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getColumnMapping_HeaderLabelExpression(),
                theDescriptionPackage_1.getInterpretedExpression(),
                "headerLabelExpression", null, 0, 1, ColumnMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getColumnMapping_InitialWidth(),
                theEcorePackage.getEInt(),
                "initialWidth", null, 0, 1, ColumnMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(elementColumnMappingEClass, ElementColumnMapping.class, "ElementColumnMapping", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getElementColumnMapping_DomainClass(),
                theDescriptionPackage_1.getTypeName(),
                "domainClass", null, 1, 1, ElementColumnMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getElementColumnMapping_SemanticCandidatesExpression(),
                theDescriptionPackage_1.getInterpretedExpression(),
                "semanticCandidatesExpression", null, 0, 1, ElementColumnMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getElementColumnMapping_Create(),
                this.getCreateColumnTool(),
                this.getCreateColumnTool_Mapping(),
                "create", null, 0, -1, ElementColumnMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getElementColumnMapping_Delete(),
                this.getDeleteColumnTool(),
                this.getDeleteColumnTool_Mapping(),
                "delete", null, 0, 1, ElementColumnMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(featureColumnMappingEClass, FeatureColumnMapping.class, "FeatureColumnMapping", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getFeatureColumnMapping_FeatureName(),
                theEcorePackage.getEString(),
                "featureName", null, 1, 1, FeatureColumnMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getFeatureColumnMapping_LabelExpression(),
                theDescriptionPackage_1.getInterpretedExpression(),
                "labelExpression", null, 0, 1, FeatureColumnMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getFeatureColumnMapping_FeatureParentExpression(),
                theDescriptionPackage_1.getInterpretedExpression(),
                "featureParentExpression", null, 0, 1, FeatureColumnMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(cellUpdaterEClass, CellUpdater.class, "CellUpdater", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getCellUpdater_DirectEdit(),
                this.getLabelEditTool(),
                null,
                "directEdit", null, 0, 1, CellUpdater.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getCellUpdater_CanEdit(),
                theDescriptionPackage_1.getInterpretedExpression(),
                "canEdit", null, 0, 1, CellUpdater.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        addEOperation(cellUpdaterEClass, theDescriptionPackage_1.getInterpretedExpression(), "getLabelComputationExpression", 1, 1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        addEOperation(cellUpdaterEClass, this.getCreateCellTool(), "getCreateCell", 0, 1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(styleUpdaterEClass, StyleUpdater.class, "StyleUpdater", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getStyleUpdater_DefaultForeground(),
                this.getForegroundStyleDescription(),
                null,
                "defaultForeground", null, 0, 1, StyleUpdater.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getStyleUpdater_ForegroundConditionalStyle(),
                this.getForegroundConditionalStyle(),
                null,
                "foregroundConditionalStyle", null, 0, -1, StyleUpdater.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getStyleUpdater_DefaultBackground(),
                this.getBackgroundStyleDescription(),
                null,
                "defaultBackground", null, 0, 1, StyleUpdater.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getStyleUpdater_BackgroundConditionalStyle(),
                this.getBackgroundConditionalStyle(),
                null,
                "backgroundConditionalStyle", null, 0, -1, StyleUpdater.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(intersectionMappingEClass, IntersectionMapping.class, "IntersectionMapping", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getIntersectionMapping_LineMapping(),
                this.getLineMapping(),
                null,
                "lineMapping", null, 1, -1, IntersectionMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        getIntersectionMapping_LineMapping().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());
        initEReference(
                getIntersectionMapping_ColumnMapping(),
                this.getColumnMapping(),
                null,
                "columnMapping", null, 1, 1, IntersectionMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getIntersectionMapping_LabelExpression(),
                theDescriptionPackage_1.getInterpretedExpression(),
                "labelExpression", null, 0, 1, IntersectionMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getIntersectionMapping_UseDomainClass(),
                theEcorePackage.getEBoolean(),
                "useDomainClass", "false", 1, 1, IntersectionMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(
                getIntersectionMapping_ColumnFinderExpression(),
                theDescriptionPackage_1.getInterpretedExpression(),
                "columnFinderExpression", null, 1, 1, IntersectionMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getIntersectionMapping_LineFinderExpression(),
                theDescriptionPackage_1.getInterpretedExpression(),
                "lineFinderExpression", null, 0, 1, IntersectionMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getIntersectionMapping_SemanticCandidatesExpression(),
                theDescriptionPackage_1.getInterpretedExpression(),
                "semanticCandidatesExpression", null, 0, 1, IntersectionMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getIntersectionMapping_DomainClass(),
                theDescriptionPackage_1.getTypeName(),
                "domainClass", null, 0, 1, IntersectionMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getIntersectionMapping_PreconditionExpression(),
                theDescriptionPackage_1.getInterpretedExpression(),
                "preconditionExpression", null, 0, 1, IntersectionMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getIntersectionMapping_Create(),
                this.getCreateCellTool(),
                this.getCreateCellTool_Mapping(),
                "create", null, 0, 1, IntersectionMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(tableToolEClass, TableTool.class, "TableTool", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getTableTool_Variables(),
                this.getTableVariable(),
                null,
                "variables", null, 0, -1, TableTool.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getTableTool_FirstModelOperation(),
                theToolPackage.getModelOperation(),
                null,
                "firstModelOperation", null, 1, 1, TableTool.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(labelEditToolEClass, LabelEditTool.class, "LabelEditTool", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getLabelEditTool_Mask(),
                theToolPackage.getEditMaskVariables(),
                null,
                "mask", null, 1, 1, LabelEditTool.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(createToolEClass, CreateTool.class, "CreateTool", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(createColumnToolEClass, CreateColumnTool.class, "CreateColumnTool", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getCreateColumnTool_Mapping(),
                this.getElementColumnMapping(),
                this.getElementColumnMapping_Create(),
                "mapping", null, 1, 1, CreateColumnTool.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(createCrossColumnToolEClass, CreateCrossColumnTool.class, "CreateCrossColumnTool", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getCreateCrossColumnTool_Mapping(),
                this.getElementColumnMapping(),
                null,
                "mapping", null, 1, 1, CreateCrossColumnTool.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(createLineToolEClass, CreateLineTool.class, "CreateLineTool", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getCreateLineTool_Mapping(),
                this.getLineMapping(),
                null,
                "mapping", null, 0, 1, CreateLineTool.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(createCellToolEClass, CreateCellTool.class, "CreateCellTool", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getCreateCellTool_Mask(),
                theToolPackage.getEditMaskVariables(),
                null,
                "mask", null, 1, 1, CreateCellTool.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getCreateCellTool_Mapping(),
                this.getIntersectionMapping(),
                this.getIntersectionMapping_Create(),
                "mapping", null, 1, 1, CreateCellTool.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(deleteToolEClass, DeleteTool.class, "DeleteTool", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(deleteColumnToolEClass, DeleteColumnTool.class, "DeleteColumnTool", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getDeleteColumnTool_Mapping(),
                this.getElementColumnMapping(),
                this.getElementColumnMapping_Delete(),
                "mapping", null, 1, 1, DeleteColumnTool.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(deleteLineToolEClass, DeleteLineTool.class, "DeleteLineTool", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getDeleteLineTool_Mapping(),
                this.getLineMapping(),
                this.getLineMapping_Delete(),
                "mapping", null, 1, 1, DeleteLineTool.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(foregroundStyleDescriptionEClass, ForegroundStyleDescription.class,
                "ForegroundStyleDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getForegroundStyleDescription_LabelSize(),
                theEcorePackage.getEInt(),
                "labelSize", "12", 0, 1, ForegroundStyleDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(
                getForegroundStyleDescription_LabelFormat(),
                theViewpointPackage.getFontFormat(),
                "labelFormat", null, 0, 4, ForegroundStyleDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getForegroundStyleDescription_ForeGroundColor(),
                theDescriptionPackage_1.getColorDescription(),
                null,
                "foreGroundColor", null, 1, 1, ForegroundStyleDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(backgroundStyleDescriptionEClass, BackgroundStyleDescription.class,
                "BackgroundStyleDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getBackgroundStyleDescription_BackgroundColor(),
                theDescriptionPackage_1.getColorDescription(),
                null,
                "backgroundColor", null, 1, 1, BackgroundStyleDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(foregroundConditionalStyleEClass, ForegroundConditionalStyle.class,
                "ForegroundConditionalStyle", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getForegroundConditionalStyle_PredicateExpression(),
                theDescriptionPackage_1.getInterpretedExpression(),
                "predicateExpression", null, 1, 1, ForegroundConditionalStyle.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getForegroundConditionalStyle_Style(),
                this.getForegroundStyleDescription(),
                null,
                "style", null, 0, 1, ForegroundConditionalStyle.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(backgroundConditionalStyleEClass, BackgroundConditionalStyle.class,
                "BackgroundConditionalStyle", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getBackgroundConditionalStyle_PredicateExpression(),
                theDescriptionPackage_1.getInterpretedExpression(),
                "predicateExpression", null, 1, 1, BackgroundConditionalStyle.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getBackgroundConditionalStyle_Style(),
                this.getBackgroundStyleDescription(),
                null,
                "style", null, 0, 1, BackgroundConditionalStyle.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(tableVariableEClass, TableVariable.class, "TableVariable", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getTableVariable_Documentation(),
                theEcorePackage.getEString(),
                "documentation", null, 0, 1, TableVariable.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(tableCreationDescriptionEClass, TableCreationDescription.class,
                "TableCreationDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getTableCreationDescription_TableDescription(),
                this.getTableDescription(),
                null,
                "tableDescription", null, 1, 1, TableCreationDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(tableNavigationDescriptionEClass, TableNavigationDescription.class,
                "TableNavigationDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getTableNavigationDescription_TableDescription(),
                this.getTableDescription(),
                null,
                "tableDescription", null, 1, 1, TableNavigationDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        // Create annotations
        // http://www.eclipse.org/sirius/interpreted/expression/returnType
        createReturnTypeAnnotations();
        // http://www.eclipse.org/sirius/interpreted/expression/variables
        createVariablesAnnotations();
    }

    /**
     * Initializes the annotations for
     * <b>http://www.eclipse.org/sirius/interpreted/expression/returnType</b>.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void createReturnTypeAnnotations() {
        String source = "http://www.eclipse.org/sirius/interpreted/expression/returnType"; //$NON-NLS-1$
        addAnnotation(getTableDescription_PreconditionExpression(), source, new String[] { "returnType", "boolean" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getTableMapping_SemanticElements(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getLineMapping_SemanticCandidatesExpression(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getLineMapping_HeaderLabelExpression(), source, new String[] { "returnType", "a string." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getColumnMapping_HeaderLabelExpression(), source, new String[] { "returnType", "a string." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getElementColumnMapping_SemanticCandidatesExpression(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getFeatureColumnMapping_LabelExpression(), source, new String[] { "returnType", "a string." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getFeatureColumnMapping_FeatureParentExpression(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getCellUpdater_CanEdit(), source, new String[] { "returnType", "a boolean." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getIntersectionMapping_LabelExpression(), source, new String[] { "returnType", "a string." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getIntersectionMapping_ColumnFinderExpression(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getIntersectionMapping_LineFinderExpression(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getIntersectionMapping_SemanticCandidatesExpression(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getIntersectionMapping_PreconditionExpression(), source, new String[] { "returnType", "a boolean." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getForegroundConditionalStyle_PredicateExpression(), source, new String[] { "returnType", "a boolean." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getBackgroundConditionalStyle_PredicateExpression(), source, new String[] { "returnType", "a boolean." //$NON-NLS-1$ //$NON-NLS-2$
        });
    }

    /**
     * Initializes the annotations for
     * <b>http://www.eclipse.org/sirius/interpreted/expression/variables</b>.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void createVariablesAnnotations() {
        String source = "http://www.eclipse.org/sirius/interpreted/expression/variables"; //$NON-NLS-1$
        addAnnotation(getTableDescription_PreconditionExpression(), source, new String[] {});
        addAnnotation(getTableMapping_SemanticElements(), source, new String[] { "view", "table.DTableElement | current DTableElement (DCell, DColumn, DLine, ...).", //$NON-NLS-1$ //$NON-NLS-2$
            "containerView", "ecore.EObject | container of the current DTableElement (variable is available if container is not null).", //$NON-NLS-1$ //$NON-NLS-2$
            "container", "ecore.EObject | semantic target of containerView (if it is a DSemanticDecorator)." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getLineMapping_SemanticCandidatesExpression(), source, new String[] { "viewpoint", "table.DTable | (deprecated) current DTable.", //$NON-NLS-1$ //$NON-NLS-2$
            "table", "table.DTable | current DTable.", //$NON-NLS-1$ //$NON-NLS-2$
            "root", "ecore.EObject | semantic target of $table.", //$NON-NLS-1$ //$NON-NLS-2$
            "containerView", "table.LineContainer | current LineContainer (DLine or DTable).", //$NON-NLS-1$ //$NON-NLS-2$
            "container", "ecore.EObject | semantic target of $containerView (if it is a DSemanticDecorator)." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getLineMapping_HeaderLabelExpression(), source, new String[] {});
        addAnnotation(getColumnMapping_HeaderLabelExpression(), source, new String[] {});
        addAnnotation(getElementColumnMapping_SemanticCandidatesExpression(), source, new String[] { "viewpoint", "table.DTable | (deprecated) current DTable.", //$NON-NLS-1$ //$NON-NLS-2$
            "table", "table.DTable | current DTable.", //$NON-NLS-1$ //$NON-NLS-2$
            "containerView", "table.DTable | current DTable.", //$NON-NLS-1$ //$NON-NLS-2$
            "container", "ecore.EObject | semantic element targeted by the current DTable." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getFeatureColumnMapping_LabelExpression(), source, new String[] { "root", "ecore.EObject | semantic target of the current DTable.", //$NON-NLS-1$ //$NON-NLS-2$
            "line", "table.DLine | DLine of the current DCell.", //$NON-NLS-1$ //$NON-NLS-2$
            "lineSemantic", "ecore.EObject | semantic target of $line", //$NON-NLS-1$ //$NON-NLS-2$
            "container", "ecore.EObject | semantic target of $line.", //$NON-NLS-1$ //$NON-NLS-2$
            "column", "table.DColumn | DColumn of the current DCell.", //$NON-NLS-1$ //$NON-NLS-2$
            "columnSemantic", "ecore.EObject | semantic target of $column" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getFeatureColumnMapping_FeatureParentExpression(), source, new String[] { "root", "ecore.EObject | semantic target of the current DTable.", //$NON-NLS-1$ //$NON-NLS-2$
            "container", "ecore.EObject | semantic target of the current DLine." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getCellUpdater_CanEdit(), source, new String[] {});
        addAnnotation(getIntersectionMapping_LabelExpression(), source, new String[] { "root", "ecore.EObject | semantic target of the current DTable.", //$NON-NLS-1$ //$NON-NLS-2$
            "line", "table.DLine | DLine of the current DCell.", //$NON-NLS-1$ //$NON-NLS-2$
            "lineSemantic", "ecore.EObject | semantic target of $line", //$NON-NLS-1$ //$NON-NLS-2$
            "container", "ecore.EObject | semantic target of $line.", //$NON-NLS-1$ //$NON-NLS-2$
            "column", "table.DColumn | DColumn of the current DCell.", //$NON-NLS-1$ //$NON-NLS-2$
            "columnSemantic", "ecore.EObject | semantic target of $column" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getIntersectionMapping_ColumnFinderExpression(), source, new String[] {});
        addAnnotation(getIntersectionMapping_LineFinderExpression(), source, new String[] {});
        addAnnotation(getIntersectionMapping_SemanticCandidatesExpression(), source, new String[] {});
        addAnnotation(getIntersectionMapping_PreconditionExpression(), source, new String[] { "line", "table.DLine | the source view of the current potential line.", //$NON-NLS-1$ //$NON-NLS-2$
            "lineSemantic", "ecore.EObject | the semantic element of $line.", //$NON-NLS-1$ //$NON-NLS-2$
            "column", "table.DColumn | the source view of the current potential column.", //$NON-NLS-1$ //$NON-NLS-2$
            "columnSemantic", "ecore.EObject | the semantic element of $column.", //$NON-NLS-1$ //$NON-NLS-2$
            "table", "table.DTable | the current DTable." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getForegroundConditionalStyle_PredicateExpression(), source, new String[] {});
        addAnnotation(getBackgroundConditionalStyle_PredicateExpression(), source, new String[] {});
    }
} // DescriptionPackageImpl
