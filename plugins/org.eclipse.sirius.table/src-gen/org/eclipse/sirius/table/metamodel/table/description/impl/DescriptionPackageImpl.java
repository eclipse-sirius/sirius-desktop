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

import org.eclipse.sirius.SiriusPackage;
import org.eclipse.sirius.description.contribution.ContributionPackage;
import org.eclipse.sirius.description.tool.ToolPackage;
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
import org.eclipse.sirius.table.metamodel.table.description.EditionTableExtensionDescription;
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
    public static final String copyright = "Copyright (c) 2007-2013 THALES GLOBAL SERVICES\n All rights reserved.\n\n Contributors:\n     Obeo - Initial API and implementation\n";

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
    private EClass editionTableExtensionDescriptionEClass = null;

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
        super(eNS_URI, DescriptionFactory.eINSTANCE);
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
        if (isInited)
            return (DescriptionPackage) EPackage.Registry.INSTANCE.getEPackage(DescriptionPackage.eNS_URI);

        // Obtain or create and register package
        DescriptionPackageImpl theDescriptionPackage = (DescriptionPackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof DescriptionPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
                : new DescriptionPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        SiriusPackage.eINSTANCE.eClass();

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
    public EClass getTableDescription() {
        return tableDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTableDescription_PreconditionExpression() {
        return (EAttribute) tableDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTableDescription_DomainClass() {
        return (EAttribute) tableDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTableDescription_OwnedRepresentationCreationDescriptions() {
        return (EReference) tableDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTableDescription_ReusedRepresentationCreationDescriptions() {
        return (EReference) tableDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTableDescription_AllRepresentationCreationDescriptions() {
        return (EReference) tableDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTableDescription_OwnedRepresentationNavigationDescriptions() {
        return (EReference) tableDescriptionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTableDescription_ReusedRepresentationNavigationDescriptions() {
        return (EReference) tableDescriptionEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTableDescription_AllRepresentationNavigationDescriptions() {
        return (EReference) tableDescriptionEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTableDescription_OwnedLineMappings() {
        return (EReference) tableDescriptionEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTableDescription_ReusedLineMappings() {
        return (EReference) tableDescriptionEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTableDescription_AllLineMappings() {
        return (EReference) tableDescriptionEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTableDescription_OwnedCreateLine() {
        return (EReference) tableDescriptionEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTableDescription_ReusedCreateLine() {
        return (EReference) tableDescriptionEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTableDescription_AllCreateLine() {
        return (EReference) tableDescriptionEClass.getEStructuralFeatures().get(13);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTableDescription_InitialHeaderColumnWidth() {
        return (EAttribute) tableDescriptionEClass.getEStructuralFeatures().get(14);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTableDescription_ImportedElements() {
        return (EReference) tableDescriptionEClass.getEStructuralFeatures().get(15);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getEditionTableDescription() {
        return editionTableDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEditionTableDescription_OwnedColumnMappings() {
        return (EReference) editionTableDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEditionTableDescription_ReusedColumnMappings() {
        return (EReference) editionTableDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEditionTableDescription_AllColumnMappings() {
        return (EReference) editionTableDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getEditionTableExtensionDescription() {
        return editionTableExtensionDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEditionTableExtensionDescription_OwnedLineMappings() {
        return (EReference) editionTableExtensionDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEditionTableExtensionDescription_OwnedColumnMappings() {
        return (EReference) editionTableExtensionDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEditionTableExtensionDescription_OwnedTools() {
        return (EReference) editionTableExtensionDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getEditionTableExtensionDescription_PreconditionExpression() {
        return (EAttribute) editionTableExtensionDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getEditionTableExtensionDescription_DomainClass() {
        return (EAttribute) editionTableExtensionDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getCrossTableDescription() {
        return crossTableDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getCrossTableDescription_OwnedColumnMappings() {
        return (EReference) crossTableDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getCrossTableDescription_Intersection() {
        return (EReference) crossTableDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getCrossTableDescription_CreateColumn() {
        return (EReference) crossTableDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getTableMapping() {
        return tableMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTableMapping_SemanticElements() {
        return (EAttribute) tableMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getLineMapping() {
        return lineMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getLineMapping_OwnedSubLines() {
        return (EReference) lineMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getLineMapping_ReusedSubLines() {
        return (EReference) lineMappingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getLineMapping_DomainClass() {
        return (EAttribute) lineMappingEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getLineMapping_Create() {
        return (EReference) lineMappingEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getLineMapping_Delete() {
        return (EReference) lineMappingEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getLineMapping_SemanticCandidatesExpression() {
        return (EAttribute) lineMappingEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getLineMapping_HeaderLabelExpression() {
        return (EAttribute) lineMappingEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getLineMapping_AllSubLines() {
        return (EReference) lineMappingEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getLineMapping_ReusedInMappings() {
        return (EReference) lineMappingEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getColumnMapping() {
        return columnMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getColumnMapping_HeaderLabelExpression() {
        return (EAttribute) columnMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getColumnMapping_InitialWidth() {
        return (EAttribute) columnMappingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getElementColumnMapping() {
        return elementColumnMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getElementColumnMapping_DomainClass() {
        return (EAttribute) elementColumnMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getElementColumnMapping_SemanticCandidatesExpression() {
        return (EAttribute) elementColumnMappingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getElementColumnMapping_Create() {
        return (EReference) elementColumnMappingEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getElementColumnMapping_Delete() {
        return (EReference) elementColumnMappingEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getFeatureColumnMapping() {
        return featureColumnMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getFeatureColumnMapping_FeatureName() {
        return (EAttribute) featureColumnMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getFeatureColumnMapping_LabelExpression() {
        return (EAttribute) featureColumnMappingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getFeatureColumnMapping_FeatureParentExpression() {
        return (EAttribute) featureColumnMappingEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getCellUpdater() {
        return cellUpdaterEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getCellUpdater_DirectEdit() {
        return (EReference) cellUpdaterEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getCellUpdater_CanEdit() {
        return (EAttribute) cellUpdaterEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getStyleUpdater() {
        return styleUpdaterEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getStyleUpdater_DefaultForeground() {
        return (EReference) styleUpdaterEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getStyleUpdater_ForegroundConditionalStyle() {
        return (EReference) styleUpdaterEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getStyleUpdater_DefaultBackground() {
        return (EReference) styleUpdaterEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getStyleUpdater_BackgroundConditionalStyle() {
        return (EReference) styleUpdaterEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getIntersectionMapping() {
        return intersectionMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getIntersectionMapping_LineMapping() {
        return (EReference) intersectionMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getIntersectionMapping_ColumnMapping() {
        return (EReference) intersectionMappingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getIntersectionMapping_LabelExpression() {
        return (EAttribute) intersectionMappingEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getIntersectionMapping_UseDomainClass() {
        return (EAttribute) intersectionMappingEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getIntersectionMapping_ColumnFinderExpression() {
        return (EAttribute) intersectionMappingEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getIntersectionMapping_LineFinderExpression() {
        return (EAttribute) intersectionMappingEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getIntersectionMapping_SemanticCandidatesExpression() {
        return (EAttribute) intersectionMappingEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getIntersectionMapping_DomainClass() {
        return (EAttribute) intersectionMappingEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getIntersectionMapping_PreconditionExpression() {
        return (EAttribute) intersectionMappingEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getIntersectionMapping_Create() {
        return (EReference) intersectionMappingEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getTableTool() {
        return tableToolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTableTool_Variables() {
        return (EReference) tableToolEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTableTool_FirstModelOperation() {
        return (EReference) tableToolEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getLabelEditTool() {
        return labelEditToolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getLabelEditTool_Mask() {
        return (EReference) labelEditToolEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getCreateTool() {
        return createToolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getCreateColumnTool() {
        return createColumnToolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getCreateColumnTool_Mapping() {
        return (EReference) createColumnToolEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getCreateCrossColumnTool() {
        return createCrossColumnToolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getCreateCrossColumnTool_Mapping() {
        return (EReference) createCrossColumnToolEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getCreateLineTool() {
        return createLineToolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getCreateLineTool_Mapping() {
        return (EReference) createLineToolEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getCreateCellTool() {
        return createCellToolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getCreateCellTool_Mask() {
        return (EReference) createCellToolEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getCreateCellTool_Mapping() {
        return (EReference) createCellToolEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDeleteTool() {
        return deleteToolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDeleteColumnTool() {
        return deleteColumnToolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDeleteColumnTool_Mapping() {
        return (EReference) deleteColumnToolEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDeleteLineTool() {
        return deleteLineToolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDeleteLineTool_Mapping() {
        return (EReference) deleteLineToolEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getForegroundStyleDescription() {
        return foregroundStyleDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getForegroundStyleDescription_LabelSize() {
        return (EAttribute) foregroundStyleDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getForegroundStyleDescription_LabelFormat() {
        return (EAttribute) foregroundStyleDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getForegroundStyleDescription_ForeGroundColor() {
        return (EReference) foregroundStyleDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getBackgroundStyleDescription() {
        return backgroundStyleDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getBackgroundStyleDescription_BackgroundColor() {
        return (EReference) backgroundStyleDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getForegroundConditionalStyle() {
        return foregroundConditionalStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getForegroundConditionalStyle_PredicateExpression() {
        return (EAttribute) foregroundConditionalStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getForegroundConditionalStyle_Style() {
        return (EReference) foregroundConditionalStyleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getBackgroundConditionalStyle() {
        return backgroundConditionalStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getBackgroundConditionalStyle_PredicateExpression() {
        return (EAttribute) backgroundConditionalStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getBackgroundConditionalStyle_Style() {
        return (EReference) backgroundConditionalStyleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getTableVariable() {
        return tableVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTableVariable_Documentation() {
        return (EAttribute) tableVariableEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getTableCreationDescription() {
        return tableCreationDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTableCreationDescription_TableDescription() {
        return (EReference) tableCreationDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getTableNavigationDescription() {
        return tableNavigationDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTableNavigationDescription_TableDescription() {
        return (EReference) tableNavigationDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
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
        if (isCreated)
            return;
        isCreated = true;

        // Create classes and their features
        tableDescriptionEClass = createEClass(TABLE_DESCRIPTION);
        createEAttribute(tableDescriptionEClass, TABLE_DESCRIPTION__PRECONDITION_EXPRESSION);
        createEAttribute(tableDescriptionEClass, TABLE_DESCRIPTION__DOMAIN_CLASS);
        createEReference(tableDescriptionEClass, TABLE_DESCRIPTION__OWNED_REPRESENTATION_CREATION_DESCRIPTIONS);
        createEReference(tableDescriptionEClass, TABLE_DESCRIPTION__REUSED_REPRESENTATION_CREATION_DESCRIPTIONS);
        createEReference(tableDescriptionEClass, TABLE_DESCRIPTION__ALL_REPRESENTATION_CREATION_DESCRIPTIONS);
        createEReference(tableDescriptionEClass, TABLE_DESCRIPTION__OWNED_REPRESENTATION_NAVIGATION_DESCRIPTIONS);
        createEReference(tableDescriptionEClass, TABLE_DESCRIPTION__REUSED_REPRESENTATION_NAVIGATION_DESCRIPTIONS);
        createEReference(tableDescriptionEClass, TABLE_DESCRIPTION__ALL_REPRESENTATION_NAVIGATION_DESCRIPTIONS);
        createEReference(tableDescriptionEClass, TABLE_DESCRIPTION__OWNED_LINE_MAPPINGS);
        createEReference(tableDescriptionEClass, TABLE_DESCRIPTION__REUSED_LINE_MAPPINGS);
        createEReference(tableDescriptionEClass, TABLE_DESCRIPTION__ALL_LINE_MAPPINGS);
        createEReference(tableDescriptionEClass, TABLE_DESCRIPTION__OWNED_CREATE_LINE);
        createEReference(tableDescriptionEClass, TABLE_DESCRIPTION__REUSED_CREATE_LINE);
        createEReference(tableDescriptionEClass, TABLE_DESCRIPTION__ALL_CREATE_LINE);
        createEAttribute(tableDescriptionEClass, TABLE_DESCRIPTION__INITIAL_HEADER_COLUMN_WIDTH);
        createEReference(tableDescriptionEClass, TABLE_DESCRIPTION__IMPORTED_ELEMENTS);

        editionTableDescriptionEClass = createEClass(EDITION_TABLE_DESCRIPTION);
        createEReference(editionTableDescriptionEClass, EDITION_TABLE_DESCRIPTION__OWNED_COLUMN_MAPPINGS);
        createEReference(editionTableDescriptionEClass, EDITION_TABLE_DESCRIPTION__REUSED_COLUMN_MAPPINGS);
        createEReference(editionTableDescriptionEClass, EDITION_TABLE_DESCRIPTION__ALL_COLUMN_MAPPINGS);

        editionTableExtensionDescriptionEClass = createEClass(EDITION_TABLE_EXTENSION_DESCRIPTION);
        createEReference(editionTableExtensionDescriptionEClass, EDITION_TABLE_EXTENSION_DESCRIPTION__OWNED_LINE_MAPPINGS);
        createEReference(editionTableExtensionDescriptionEClass, EDITION_TABLE_EXTENSION_DESCRIPTION__OWNED_COLUMN_MAPPINGS);
        createEReference(editionTableExtensionDescriptionEClass, EDITION_TABLE_EXTENSION_DESCRIPTION__OWNED_TOOLS);
        createEAttribute(editionTableExtensionDescriptionEClass, EDITION_TABLE_EXTENSION_DESCRIPTION__PRECONDITION_EXPRESSION);
        createEAttribute(editionTableExtensionDescriptionEClass, EDITION_TABLE_EXTENSION_DESCRIPTION__DOMAIN_CLASS);

        crossTableDescriptionEClass = createEClass(CROSS_TABLE_DESCRIPTION);
        createEReference(crossTableDescriptionEClass, CROSS_TABLE_DESCRIPTION__OWNED_COLUMN_MAPPINGS);
        createEReference(crossTableDescriptionEClass, CROSS_TABLE_DESCRIPTION__INTERSECTION);
        createEReference(crossTableDescriptionEClass, CROSS_TABLE_DESCRIPTION__CREATE_COLUMN);

        tableMappingEClass = createEClass(TABLE_MAPPING);
        createEAttribute(tableMappingEClass, TABLE_MAPPING__SEMANTIC_ELEMENTS);

        lineMappingEClass = createEClass(LINE_MAPPING);
        createEReference(lineMappingEClass, LINE_MAPPING__OWNED_SUB_LINES);
        createEReference(lineMappingEClass, LINE_MAPPING__REUSED_SUB_LINES);
        createEReference(lineMappingEClass, LINE_MAPPING__ALL_SUB_LINES);
        createEReference(lineMappingEClass, LINE_MAPPING__REUSED_IN_MAPPINGS);
        createEAttribute(lineMappingEClass, LINE_MAPPING__DOMAIN_CLASS);
        createEReference(lineMappingEClass, LINE_MAPPING__CREATE);
        createEReference(lineMappingEClass, LINE_MAPPING__DELETE);
        createEAttribute(lineMappingEClass, LINE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION);
        createEAttribute(lineMappingEClass, LINE_MAPPING__HEADER_LABEL_EXPRESSION);

        columnMappingEClass = createEClass(COLUMN_MAPPING);
        createEAttribute(columnMappingEClass, COLUMN_MAPPING__HEADER_LABEL_EXPRESSION);
        createEAttribute(columnMappingEClass, COLUMN_MAPPING__INITIAL_WIDTH);

        elementColumnMappingEClass = createEClass(ELEMENT_COLUMN_MAPPING);
        createEAttribute(elementColumnMappingEClass, ELEMENT_COLUMN_MAPPING__DOMAIN_CLASS);
        createEAttribute(elementColumnMappingEClass, ELEMENT_COLUMN_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION);
        createEReference(elementColumnMappingEClass, ELEMENT_COLUMN_MAPPING__CREATE);
        createEReference(elementColumnMappingEClass, ELEMENT_COLUMN_MAPPING__DELETE);

        featureColumnMappingEClass = createEClass(FEATURE_COLUMN_MAPPING);
        createEAttribute(featureColumnMappingEClass, FEATURE_COLUMN_MAPPING__FEATURE_NAME);
        createEAttribute(featureColumnMappingEClass, FEATURE_COLUMN_MAPPING__LABEL_EXPRESSION);
        createEAttribute(featureColumnMappingEClass, FEATURE_COLUMN_MAPPING__FEATURE_PARENT_EXPRESSION);

        cellUpdaterEClass = createEClass(CELL_UPDATER);
        createEReference(cellUpdaterEClass, CELL_UPDATER__DIRECT_EDIT);
        createEAttribute(cellUpdaterEClass, CELL_UPDATER__CAN_EDIT);

        styleUpdaterEClass = createEClass(STYLE_UPDATER);
        createEReference(styleUpdaterEClass, STYLE_UPDATER__DEFAULT_FOREGROUND);
        createEReference(styleUpdaterEClass, STYLE_UPDATER__FOREGROUND_CONDITIONAL_STYLE);
        createEReference(styleUpdaterEClass, STYLE_UPDATER__DEFAULT_BACKGROUND);
        createEReference(styleUpdaterEClass, STYLE_UPDATER__BACKGROUND_CONDITIONAL_STYLE);

        intersectionMappingEClass = createEClass(INTERSECTION_MAPPING);
        createEReference(intersectionMappingEClass, INTERSECTION_MAPPING__LINE_MAPPING);
        createEReference(intersectionMappingEClass, INTERSECTION_MAPPING__COLUMN_MAPPING);
        createEAttribute(intersectionMappingEClass, INTERSECTION_MAPPING__LABEL_EXPRESSION);
        createEAttribute(intersectionMappingEClass, INTERSECTION_MAPPING__USE_DOMAIN_CLASS);
        createEAttribute(intersectionMappingEClass, INTERSECTION_MAPPING__COLUMN_FINDER_EXPRESSION);
        createEAttribute(intersectionMappingEClass, INTERSECTION_MAPPING__LINE_FINDER_EXPRESSION);
        createEAttribute(intersectionMappingEClass, INTERSECTION_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION);
        createEAttribute(intersectionMappingEClass, INTERSECTION_MAPPING__DOMAIN_CLASS);
        createEAttribute(intersectionMappingEClass, INTERSECTION_MAPPING__PRECONDITION_EXPRESSION);
        createEReference(intersectionMappingEClass, INTERSECTION_MAPPING__CREATE);

        tableToolEClass = createEClass(TABLE_TOOL);
        createEReference(tableToolEClass, TABLE_TOOL__VARIABLES);
        createEReference(tableToolEClass, TABLE_TOOL__FIRST_MODEL_OPERATION);

        labelEditToolEClass = createEClass(LABEL_EDIT_TOOL);
        createEReference(labelEditToolEClass, LABEL_EDIT_TOOL__MASK);

        createToolEClass = createEClass(CREATE_TOOL);

        createColumnToolEClass = createEClass(CREATE_COLUMN_TOOL);
        createEReference(createColumnToolEClass, CREATE_COLUMN_TOOL__MAPPING);

        createCrossColumnToolEClass = createEClass(CREATE_CROSS_COLUMN_TOOL);
        createEReference(createCrossColumnToolEClass, CREATE_CROSS_COLUMN_TOOL__MAPPING);

        createLineToolEClass = createEClass(CREATE_LINE_TOOL);
        createEReference(createLineToolEClass, CREATE_LINE_TOOL__MAPPING);

        createCellToolEClass = createEClass(CREATE_CELL_TOOL);
        createEReference(createCellToolEClass, CREATE_CELL_TOOL__MASK);
        createEReference(createCellToolEClass, CREATE_CELL_TOOL__MAPPING);

        deleteToolEClass = createEClass(DELETE_TOOL);

        deleteColumnToolEClass = createEClass(DELETE_COLUMN_TOOL);
        createEReference(deleteColumnToolEClass, DELETE_COLUMN_TOOL__MAPPING);

        deleteLineToolEClass = createEClass(DELETE_LINE_TOOL);
        createEReference(deleteLineToolEClass, DELETE_LINE_TOOL__MAPPING);

        foregroundStyleDescriptionEClass = createEClass(FOREGROUND_STYLE_DESCRIPTION);
        createEAttribute(foregroundStyleDescriptionEClass, FOREGROUND_STYLE_DESCRIPTION__LABEL_SIZE);
        createEAttribute(foregroundStyleDescriptionEClass, FOREGROUND_STYLE_DESCRIPTION__LABEL_FORMAT);
        createEReference(foregroundStyleDescriptionEClass, FOREGROUND_STYLE_DESCRIPTION__FORE_GROUND_COLOR);

        backgroundStyleDescriptionEClass = createEClass(BACKGROUND_STYLE_DESCRIPTION);
        createEReference(backgroundStyleDescriptionEClass, BACKGROUND_STYLE_DESCRIPTION__BACKGROUND_COLOR);

        foregroundConditionalStyleEClass = createEClass(FOREGROUND_CONDITIONAL_STYLE);
        createEAttribute(foregroundConditionalStyleEClass, FOREGROUND_CONDITIONAL_STYLE__PREDICATE_EXPRESSION);
        createEReference(foregroundConditionalStyleEClass, FOREGROUND_CONDITIONAL_STYLE__STYLE);

        backgroundConditionalStyleEClass = createEClass(BACKGROUND_CONDITIONAL_STYLE);
        createEAttribute(backgroundConditionalStyleEClass, BACKGROUND_CONDITIONAL_STYLE__PREDICATE_EXPRESSION);
        createEReference(backgroundConditionalStyleEClass, BACKGROUND_CONDITIONAL_STYLE__STYLE);

        tableVariableEClass = createEClass(TABLE_VARIABLE);
        createEAttribute(tableVariableEClass, TABLE_VARIABLE__DOCUMENTATION);

        tableCreationDescriptionEClass = createEClass(TABLE_CREATION_DESCRIPTION);
        createEReference(tableCreationDescriptionEClass, TABLE_CREATION_DESCRIPTION__TABLE_DESCRIPTION);

        tableNavigationDescriptionEClass = createEClass(TABLE_NAVIGATION_DESCRIPTION);
        createEReference(tableNavigationDescriptionEClass, TABLE_NAVIGATION_DESCRIPTION__TABLE_DESCRIPTION);
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
        if (isInitialized)
            return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Obtain other dependent packages
        org.eclipse.sirius.description.DescriptionPackage theDescriptionPackage_1 = (org.eclipse.sirius.description.DescriptionPackage) EPackage.Registry.INSTANCE
                .getEPackage(org.eclipse.sirius.description.DescriptionPackage.eNS_URI);
        ToolPackage theToolPackage = (ToolPackage) EPackage.Registry.INSTANCE.getEPackage(ToolPackage.eNS_URI);
        EcorePackage theEcorePackage = (EcorePackage) EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);
        ContributionPackage theContributionPackage = (ContributionPackage) EPackage.Registry.INSTANCE.getEPackage(ContributionPackage.eNS_URI);
        SiriusPackage theSiriusPackage = (SiriusPackage) EPackage.Registry.INSTANCE.getEPackage(SiriusPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        tableDescriptionEClass.getESuperTypes().add(theDescriptionPackage_1.getRepresentationDescription());
        tableDescriptionEClass.getESuperTypes().add(theDescriptionPackage_1.getDocumentedElement());
        tableDescriptionEClass.getESuperTypes().add(theDescriptionPackage_1.getEndUserDocumentedElement());
        editionTableDescriptionEClass.getESuperTypes().add(this.getTableDescription());
        editionTableDescriptionEClass.getESuperTypes().add(theContributionPackage.getContributionProvider());
        editionTableExtensionDescriptionEClass.getESuperTypes().add(theDescriptionPackage_1.getRepresentationExtensionDescription());
        editionTableExtensionDescriptionEClass.getESuperTypes().add(theContributionPackage.getContributionProvider());
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
        initEClass(tableDescriptionEClass, TableDescription.class, "TableDescription", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getTableDescription_PreconditionExpression(), theDescriptionPackage_1.getInterpretedExpression(), "preconditionExpression", "", 0, 1, TableDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTableDescription_DomainClass(), theDescriptionPackage_1.getTypeName(), "domainClass", null, 1, 1, TableDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTableDescription_OwnedRepresentationCreationDescriptions(), theToolPackage.getRepresentationCreationDescription(), null, "ownedRepresentationCreationDescriptions", null, 0,
                -1, TableDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        getTableDescription_OwnedRepresentationCreationDescriptions().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());
        initEReference(getTableDescription_ReusedRepresentationCreationDescriptions(), theToolPackage.getRepresentationCreationDescription(), null, "reusedRepresentationCreationDescriptions", null,
                0, -1, TableDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        getTableDescription_ReusedRepresentationCreationDescriptions().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());
        initEReference(getTableDescription_AllRepresentationCreationDescriptions(), theToolPackage.getRepresentationCreationDescription(), null, "allRepresentationCreationDescriptions", null, 0, -1,
                TableDescription.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        getTableDescription_AllRepresentationCreationDescriptions().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());
        initEReference(getTableDescription_OwnedRepresentationNavigationDescriptions(), theToolPackage.getRepresentationNavigationDescription(), null, "ownedRepresentationNavigationDescriptions",
                null, 0, -1, TableDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        getTableDescription_OwnedRepresentationNavigationDescriptions().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());
        initEReference(getTableDescription_ReusedRepresentationNavigationDescriptions(), theToolPackage.getRepresentationNavigationDescription(), null, "reusedRepresentationNavigationDescriptions",
                null, 0, -1, TableDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        getTableDescription_ReusedRepresentationNavigationDescriptions().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());
        initEReference(getTableDescription_AllRepresentationNavigationDescriptions(), theToolPackage.getRepresentationNavigationDescription(), null, "allRepresentationNavigationDescriptions", null,
                0, -1, TableDescription.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        getTableDescription_AllRepresentationNavigationDescriptions().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());
        initEReference(getTableDescription_OwnedLineMappings(), this.getLineMapping(), null, "ownedLineMappings", null, 1, -1, TableDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        getTableDescription_OwnedLineMappings().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());
        initEReference(getTableDescription_ReusedLineMappings(), this.getLineMapping(), null, "reusedLineMappings", null, 0, -1, TableDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        getTableDescription_ReusedLineMappings().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());
        initEReference(getTableDescription_AllLineMappings(), this.getLineMapping(), null, "allLineMappings", null, 1, -1, TableDescription.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        getTableDescription_AllLineMappings().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());
        initEReference(getTableDescription_OwnedCreateLine(), this.getCreateLineTool(), null, "ownedCreateLine", null, 0, -1, TableDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTableDescription_ReusedCreateLine(), this.getCreateLineTool(), null, "reusedCreateLine", null, 0, -1, TableDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTableDescription_AllCreateLine(), this.getCreateLineTool(), null, "allCreateLine", null, 0, -1, TableDescription.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEAttribute(getTableDescription_InitialHeaderColumnWidth(), theEcorePackage.getEInt(), "initialHeaderColumnWidth", null, 0, 1, TableDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTableDescription_ImportedElements(), ecorePackage.getEObject(), null, "importedElements", null, 0, -1, TableDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(editionTableDescriptionEClass, EditionTableDescription.class, "EditionTableDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getEditionTableDescription_OwnedColumnMappings(), this.getFeatureColumnMapping(), null, "ownedColumnMappings", null, 1, -1, EditionTableDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        getEditionTableDescription_OwnedColumnMappings().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());
        initEReference(getEditionTableDescription_ReusedColumnMappings(), this.getFeatureColumnMapping(), null, "reusedColumnMappings", null, 0, -1, EditionTableDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        getEditionTableDescription_ReusedColumnMappings().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());
        initEReference(getEditionTableDescription_AllColumnMappings(), this.getFeatureColumnMapping(), null, "allColumnMappings", null, 1, -1, EditionTableDescription.class, IS_TRANSIENT,
                IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        getEditionTableDescription_AllColumnMappings().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());

        initEClass(editionTableExtensionDescriptionEClass, EditionTableExtensionDescription.class, "EditionTableExtensionDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getEditionTableExtensionDescription_OwnedLineMappings(), this.getLineMapping(), null, "ownedLineMappings", null, 1, -1, EditionTableExtensionDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        getEditionTableExtensionDescription_OwnedLineMappings().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());
        initEReference(getEditionTableExtensionDescription_OwnedColumnMappings(), this.getFeatureColumnMapping(), null, "ownedColumnMappings", null, 1, -1, EditionTableExtensionDescription.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        getEditionTableExtensionDescription_OwnedColumnMappings().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());
        initEReference(getEditionTableExtensionDescription_OwnedTools(), this.getTableTool(), null, "ownedTools", null, 0, -1, EditionTableExtensionDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEditionTableExtensionDescription_PreconditionExpression(), theDescriptionPackage_1.getInterpretedExpression(), "preconditionExpression", "", 0, 1,
                EditionTableExtensionDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEditionTableExtensionDescription_DomainClass(), theDescriptionPackage_1.getTypeName(), "domainClass", null, 1, 1, EditionTableExtensionDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(crossTableDescriptionEClass, CrossTableDescription.class, "CrossTableDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getCrossTableDescription_OwnedColumnMappings(), this.getElementColumnMapping(), null, "ownedColumnMappings", null, 1, -1, CrossTableDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getCrossTableDescription_Intersection(), this.getIntersectionMapping(), null, "intersection", null, 0, -1, CrossTableDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getCrossTableDescription_CreateColumn(), this.getCreateCrossColumnTool(), null, "createColumn", null, 0, -1, CrossTableDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(tableMappingEClass, TableMapping.class, "TableMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getTableMapping_SemanticElements(), theDescriptionPackage_1.getInterpretedExpression(), "semanticElements", null, 0, 1, TableMapping.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(lineMappingEClass, LineMapping.class, "LineMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getLineMapping_OwnedSubLines(), this.getLineMapping(), null, "ownedSubLines", null, 0, -1, LineMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        getLineMapping_OwnedSubLines().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());
        initEReference(getLineMapping_ReusedSubLines(), this.getLineMapping(), this.getLineMapping_ReusedInMappings(), "reusedSubLines", null, 0, -1, LineMapping.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getLineMapping_AllSubLines(), this.getLineMapping(), null, "allSubLines", null, 0, -1, LineMapping.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getLineMapping_ReusedInMappings(), this.getLineMapping(), this.getLineMapping_ReusedSubLines(), "reusedInMappings", null, 0, -1, LineMapping.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLineMapping_DomainClass(), theDescriptionPackage_1.getTypeName(), "domainClass", null, 1, 1, LineMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getLineMapping_Create(), this.getCreateLineTool(), null, "create", null, 0, -1, LineMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getLineMapping_Delete(), this.getDeleteLineTool(), this.getDeleteLineTool_Mapping(), "delete", null, 0, 1, LineMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLineMapping_SemanticCandidatesExpression(), theDescriptionPackage_1.getInterpretedExpression(), "semanticCandidatesExpression", null, 0, 1, LineMapping.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLineMapping_HeaderLabelExpression(), theDescriptionPackage_1.getInterpretedExpression(), "headerLabelExpression", null, 0, 1, LineMapping.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(columnMappingEClass, ColumnMapping.class, "ColumnMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getColumnMapping_HeaderLabelExpression(), theDescriptionPackage_1.getInterpretedExpression(), "headerLabelExpression", null, 0, 1, ColumnMapping.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getColumnMapping_InitialWidth(), theEcorePackage.getEInt(), "initialWidth", null, 0, 1, ColumnMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(elementColumnMappingEClass, ElementColumnMapping.class, "ElementColumnMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getElementColumnMapping_DomainClass(), theDescriptionPackage_1.getTypeName(), "domainClass", null, 1, 1, ElementColumnMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getElementColumnMapping_SemanticCandidatesExpression(), theDescriptionPackage_1.getInterpretedExpression(), "semanticCandidatesExpression", null, 0, 1,
                ElementColumnMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getElementColumnMapping_Create(), this.getCreateColumnTool(), this.getCreateColumnTool_Mapping(), "create", null, 0, -1, ElementColumnMapping.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getElementColumnMapping_Delete(), this.getDeleteColumnTool(), this.getDeleteColumnTool_Mapping(), "delete", null, 0, 1, ElementColumnMapping.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(featureColumnMappingEClass, FeatureColumnMapping.class, "FeatureColumnMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getFeatureColumnMapping_FeatureName(), theEcorePackage.getEString(), "featureName", null, 1, 1, FeatureColumnMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFeatureColumnMapping_LabelExpression(), theDescriptionPackage_1.getInterpretedExpression(), "labelExpression", null, 0, 1, FeatureColumnMapping.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFeatureColumnMapping_FeatureParentExpression(), theDescriptionPackage_1.getInterpretedExpression(), "featureParentExpression", null, 0, 1, FeatureColumnMapping.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(cellUpdaterEClass, CellUpdater.class, "CellUpdater", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getCellUpdater_DirectEdit(), this.getLabelEditTool(), null, "directEdit", null, 0, 1, CellUpdater.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getCellUpdater_CanEdit(), theDescriptionPackage_1.getInterpretedExpression(), "canEdit", null, 0, 1, CellUpdater.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        addEOperation(cellUpdaterEClass, theDescriptionPackage_1.getInterpretedExpression(), "getLabelComputationExpression", 1, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(cellUpdaterEClass, this.getCreateCellTool(), "getCreateCell", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(styleUpdaterEClass, StyleUpdater.class, "StyleUpdater", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getStyleUpdater_DefaultForeground(), this.getForegroundStyleDescription(), null, "defaultForeground", null, 0, 1, StyleUpdater.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getStyleUpdater_ForegroundConditionalStyle(), this.getForegroundConditionalStyle(), null, "foregroundConditionalStyle", null, 0, -1, StyleUpdater.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getStyleUpdater_DefaultBackground(), this.getBackgroundStyleDescription(), null, "defaultBackground", null, 0, 1, StyleUpdater.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getStyleUpdater_BackgroundConditionalStyle(), this.getBackgroundConditionalStyle(), null, "backgroundConditionalStyle", null, 0, -1, StyleUpdater.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(intersectionMappingEClass, IntersectionMapping.class, "IntersectionMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getIntersectionMapping_LineMapping(), this.getLineMapping(), null, "lineMapping", null, 1, -1, IntersectionMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        getIntersectionMapping_LineMapping().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());
        initEReference(getIntersectionMapping_ColumnMapping(), this.getColumnMapping(), null, "columnMapping", null, 1, 1, IntersectionMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getIntersectionMapping_LabelExpression(), theDescriptionPackage_1.getInterpretedExpression(), "labelExpression", null, 0, 1, IntersectionMapping.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getIntersectionMapping_UseDomainClass(), theEcorePackage.getEBoolean(), "useDomainClass", "false", 1, 1, IntersectionMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getIntersectionMapping_ColumnFinderExpression(), theDescriptionPackage_1.getInterpretedExpression(), "columnFinderExpression", null, 1, 1, IntersectionMapping.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getIntersectionMapping_LineFinderExpression(), theDescriptionPackage_1.getInterpretedExpression(), "lineFinderExpression", null, 0, 1, IntersectionMapping.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getIntersectionMapping_SemanticCandidatesExpression(), theDescriptionPackage_1.getInterpretedExpression(), "semanticCandidatesExpression", null, 0, 1,
                IntersectionMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getIntersectionMapping_DomainClass(), theDescriptionPackage_1.getTypeName(), "domainClass", null, 0, 1, IntersectionMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getIntersectionMapping_PreconditionExpression(), theDescriptionPackage_1.getInterpretedExpression(), "preconditionExpression", null, 0, 1, IntersectionMapping.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getIntersectionMapping_Create(), this.getCreateCellTool(), this.getCreateCellTool_Mapping(), "create", null, 0, 1, IntersectionMapping.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(tableToolEClass, TableTool.class, "TableTool", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTableTool_Variables(), this.getTableVariable(), null, "variables", null, 0, -1, TableTool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTableTool_FirstModelOperation(), theToolPackage.getModelOperation(), null, "firstModelOperation", null, 1, 1, TableTool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(labelEditToolEClass, LabelEditTool.class, "LabelEditTool", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getLabelEditTool_Mask(), theToolPackage.getEditMaskVariables(), null, "mask", null, 1, 1, LabelEditTool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(createToolEClass, CreateTool.class, "CreateTool", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(createColumnToolEClass, CreateColumnTool.class, "CreateColumnTool", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getCreateColumnTool_Mapping(), this.getElementColumnMapping(), this.getElementColumnMapping_Create(), "mapping", null, 1, 1, CreateColumnTool.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(createCrossColumnToolEClass, CreateCrossColumnTool.class, "CreateCrossColumnTool", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getCreateCrossColumnTool_Mapping(), this.getElementColumnMapping(), null, "mapping", null, 1, 1, CreateCrossColumnTool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(createLineToolEClass, CreateLineTool.class, "CreateLineTool", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getCreateLineTool_Mapping(), this.getLineMapping(), null, "mapping", null, 0, 1, CreateLineTool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(createCellToolEClass, CreateCellTool.class, "CreateCellTool", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getCreateCellTool_Mask(), theToolPackage.getEditMaskVariables(), null, "mask", null, 1, 1, CreateCellTool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getCreateCellTool_Mapping(), this.getIntersectionMapping(), this.getIntersectionMapping_Create(), "mapping", null, 1, 1, CreateCellTool.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(deleteToolEClass, DeleteTool.class, "DeleteTool", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(deleteColumnToolEClass, DeleteColumnTool.class, "DeleteColumnTool", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDeleteColumnTool_Mapping(), this.getElementColumnMapping(), this.getElementColumnMapping_Delete(), "mapping", null, 1, 1, DeleteColumnTool.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(deleteLineToolEClass, DeleteLineTool.class, "DeleteLineTool", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDeleteLineTool_Mapping(), this.getLineMapping(), this.getLineMapping_Delete(), "mapping", null, 1, 1, DeleteLineTool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(foregroundStyleDescriptionEClass, ForegroundStyleDescription.class, "ForegroundStyleDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getForegroundStyleDescription_LabelSize(), theEcorePackage.getEInt(), "labelSize", "12", 0, 1, ForegroundStyleDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getForegroundStyleDescription_LabelFormat(), theSiriusPackage.getFontFormat(), "labelFormat", "normal", 0, 1, ForegroundStyleDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getForegroundStyleDescription_ForeGroundColor(), theDescriptionPackage_1.getColorDescription(), null, "foreGroundColor", null, 1, 1, ForegroundStyleDescription.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(backgroundStyleDescriptionEClass, BackgroundStyleDescription.class, "BackgroundStyleDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getBackgroundStyleDescription_BackgroundColor(), theDescriptionPackage_1.getColorDescription(), null, "backgroundColor", null, 1, 1, BackgroundStyleDescription.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(foregroundConditionalStyleEClass, ForegroundConditionalStyle.class, "ForegroundConditionalStyle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getForegroundConditionalStyle_PredicateExpression(), theDescriptionPackage_1.getInterpretedExpression(), "predicateExpression", null, 1, 1, ForegroundConditionalStyle.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getForegroundConditionalStyle_Style(), this.getForegroundStyleDescription(), null, "style", null, 0, 1, ForegroundConditionalStyle.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(backgroundConditionalStyleEClass, BackgroundConditionalStyle.class, "BackgroundConditionalStyle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getBackgroundConditionalStyle_PredicateExpression(), theDescriptionPackage_1.getInterpretedExpression(), "predicateExpression", null, 1, 1, BackgroundConditionalStyle.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getBackgroundConditionalStyle_Style(), this.getBackgroundStyleDescription(), null, "style", null, 0, 1, BackgroundConditionalStyle.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(tableVariableEClass, TableVariable.class, "TableVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getTableVariable_Documentation(), theEcorePackage.getEString(), "documentation", null, 0, 1, TableVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(tableCreationDescriptionEClass, TableCreationDescription.class, "TableCreationDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTableCreationDescription_TableDescription(), this.getTableDescription(), null, "tableDescription", null, 1, 1, TableCreationDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(tableNavigationDescriptionEClass, TableNavigationDescription.class, "TableNavigationDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTableNavigationDescription_TableDescription(), this.getTableDescription(), null, "tableDescription", null, 1, 1, TableNavigationDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
        String source = "http://www.eclipse.org/sirius/interpreted/expression/returnType";
        addAnnotation(getTableDescription_PreconditionExpression(), source, new String[] { "returnType", "boolean" });
        addAnnotation(getEditionTableExtensionDescription_PreconditionExpression(), source, new String[] { "returnType", "boolean" });
        addAnnotation(getTableMapping_SemanticElements(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." });
        addAnnotation(getLineMapping_SemanticCandidatesExpression(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." });
        addAnnotation(getLineMapping_HeaderLabelExpression(), source, new String[] { "returnType", "a string." });
        addAnnotation(getColumnMapping_HeaderLabelExpression(), source, new String[] { "returnType", "a string." });
        addAnnotation(getElementColumnMapping_SemanticCandidatesExpression(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." });
        addAnnotation(getFeatureColumnMapping_LabelExpression(), source, new String[] { "returnType", "a string." });
        addAnnotation(getFeatureColumnMapping_FeatureParentExpression(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." });
        addAnnotation(getCellUpdater_CanEdit(), source, new String[] { "returnType", "a boolean." });
        addAnnotation(getIntersectionMapping_LabelExpression(), source, new String[] { "returnType", "a string." });
        addAnnotation(getIntersectionMapping_ColumnFinderExpression(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." });
        addAnnotation(getIntersectionMapping_LineFinderExpression(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." });
        addAnnotation(getIntersectionMapping_SemanticCandidatesExpression(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." });
        addAnnotation(getIntersectionMapping_PreconditionExpression(), source, new String[] { "returnType", "a boolean." });
        addAnnotation(getForegroundConditionalStyle_PredicateExpression(), source, new String[] { "returnType", "a boolean." });
        addAnnotation(getBackgroundConditionalStyle_PredicateExpression(), source, new String[] { "returnType", "a boolean." });
    }

    /**
     * Initializes the annotations for
     * <b>http://www.eclipse.org/sirius/interpreted/expression/variables</b>.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void createVariablesAnnotations() {
        String source = "http://www.eclipse.org/sirius/interpreted/expression/variables";
        addAnnotation(getTableDescription_PreconditionExpression(), source, new String[] {});
        addAnnotation(getEditionTableExtensionDescription_PreconditionExpression(), source, new String[] {});
        addAnnotation(getTableMapping_SemanticElements(), source, new String[] { "view", "table.DTableElement | current DTableElement (DCell, DColumn, DLine, ...).", "containerView",
                "ecore.EObject | container of the current DTableElement (variable is available if container is not null).", "container",
                "ecore.EObject | semantic target of containerView (if it is a DSemanticDecorator)." });
        addAnnotation(getLineMapping_SemanticCandidatesExpression(), source, new String[] { "viewpoint", "table.DTable | (deprecated) current DTable.", "table", "table.DTable | current DTable.",
                "root", "ecore.EObject | semantic target of $table.", "containerView", "table.LineContainer | current LineContainer (DLine or DTable).", "container",
                "ecore.EObject | semantic target of $containerView (if it is a DSemanticDecorator)." });
        addAnnotation(getLineMapping_HeaderLabelExpression(), source, new String[] {});
        addAnnotation(getColumnMapping_HeaderLabelExpression(), source, new String[] {});
        addAnnotation(getElementColumnMapping_SemanticCandidatesExpression(), source, new String[] { "viewpoint", "table.DTable | (deprecated) current DTable.", "table",
                "table.DTable | current DTable.", "containerView", "table.DTable | current DTable.", "container", "ecore.EObject | semantic element targeted by the current DTable." });
        addAnnotation(getFeatureColumnMapping_LabelExpression(), source, new String[] { "root", "ecore.EObject | semantic target of the current DTable.", "line",
                "table.DLine | DLine of the current DCell.", "lineSemantic", "ecore.EObject | semantic target of $line", "container", "ecore.EObject | semantic target of $line.", "column",
                "table.DColumn | DColumn of the current DCell.", "columnSemantic", "ecore.EObject | semantic target of $column" });
        addAnnotation(getFeatureColumnMapping_FeatureParentExpression(), source, new String[] { "root", "ecore.EObject | semantic target of the current DTable.", "container",
                "ecore.EObject | semantic target of the current DLine." });
        addAnnotation(getCellUpdater_CanEdit(), source, new String[] {});
        addAnnotation(getIntersectionMapping_LabelExpression(), source, new String[] { "root", "ecore.EObject | semantic target of the current DTable.", "line",
                "table.DLine | DLine of the current DCell.", "lineSemantic", "ecore.EObject | semantic target of $line", "container", "ecore.EObject | semantic target of $line.", "column",
                "table.DColumn | DColumn of the current DCell.", "columnSemantic", "ecore.EObject | semantic target of $column" });
        addAnnotation(getIntersectionMapping_ColumnFinderExpression(), source, new String[] {});
        addAnnotation(getIntersectionMapping_LineFinderExpression(), source, new String[] {});
        addAnnotation(getIntersectionMapping_SemanticCandidatesExpression(), source, new String[] {});
        addAnnotation(getIntersectionMapping_PreconditionExpression(), source, new String[] { "line", "table.DLine | the source view of the current potential line.", "lineSemantic",
                "ecore.EObject | the semantic element of $line.", "column", "table.DColumn | the source view of the current potential column.", "columnSemantic",
                "ecore.EObject | the semantic element of $column.", "table", "table.DTable | the current DTable." });
        addAnnotation(getForegroundConditionalStyle_PredicateExpression(), source, new String[] {});
        addAnnotation(getBackgroundConditionalStyle_PredicateExpression(), source, new String[] {});
    }

} // DescriptionPackageImpl
