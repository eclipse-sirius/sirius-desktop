/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.table.metamodel.table.description.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.table.metamodel.table.description.BackgroundConditionalStyle;
import org.eclipse.sirius.table.metamodel.table.description.BackgroundStyleDescription;
import org.eclipse.sirius.table.metamodel.table.description.CellEditorTool;
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
import org.eclipse.sirius.viewpoint.description.AbstractVariable;
import org.eclipse.sirius.viewpoint.description.DocumentedElement;
import org.eclipse.sirius.viewpoint.description.EndUserDocumentedElement;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolEntry;
import org.eclipse.sirius.viewpoint.description.tool.VariableContainer;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides an adapter <code>createXXX</code>
 * method for each class of the model. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage
 * @generated
 */
public class DescriptionAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected static DescriptionPackage modelPackage;

    /**
     * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public DescriptionAdapterFactory() {
        if (DescriptionAdapterFactory.modelPackage == null) {
            DescriptionAdapterFactory.modelPackage = DescriptionPackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object. <!-- begin-user-doc --> This
     * implementation returns <code>true</code> if the object is either the model's package or is an instance object of
     * the model. <!-- end-user-doc -->
     *
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(Object object) {
        if (object == DescriptionAdapterFactory.modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject) object).eClass().getEPackage() == DescriptionAdapterFactory.modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected DescriptionSwitch<Adapter> modelSwitch = new DescriptionSwitch<Adapter>() {
        @Override
        public Adapter caseTableDescription(TableDescription object) {
            return createTableDescriptionAdapter();
        }

        @Override
        public Adapter caseEditionTableDescription(EditionTableDescription object) {
            return createEditionTableDescriptionAdapter();
        }

        @Override
        public Adapter caseCrossTableDescription(CrossTableDescription object) {
            return createCrossTableDescriptionAdapter();
        }

        @Override
        public Adapter caseTableMapping(TableMapping object) {
            return createTableMappingAdapter();
        }

        @Override
        public Adapter caseLineMapping(LineMapping object) {
            return createLineMappingAdapter();
        }

        @Override
        public Adapter caseColumnMapping(ColumnMapping object) {
            return createColumnMappingAdapter();
        }

        @Override
        public Adapter caseElementColumnMapping(ElementColumnMapping object) {
            return createElementColumnMappingAdapter();
        }

        @Override
        public Adapter caseFeatureColumnMapping(FeatureColumnMapping object) {
            return createFeatureColumnMappingAdapter();
        }

        @Override
        public Adapter caseCellUpdater(CellUpdater object) {
            return createCellUpdaterAdapter();
        }

        @Override
        public Adapter caseStyleUpdater(StyleUpdater object) {
            return createStyleUpdaterAdapter();
        }

        @Override
        public Adapter caseIntersectionMapping(IntersectionMapping object) {
            return createIntersectionMappingAdapter();
        }

        @Override
        public Adapter caseTableTool(TableTool object) {
            return createTableToolAdapter();
        }

        @Override
        public Adapter caseLabelEditTool(LabelEditTool object) {
            return createLabelEditToolAdapter();
        }

        @Override
        public Adapter caseCreateTool(CreateTool object) {
            return createCreateToolAdapter();
        }

        @Override
        public Adapter caseCreateColumnTool(CreateColumnTool object) {
            return createCreateColumnToolAdapter();
        }

        @Override
        public Adapter caseCreateCrossColumnTool(CreateCrossColumnTool object) {
            return createCreateCrossColumnToolAdapter();
        }

        @Override
        public Adapter caseCreateLineTool(CreateLineTool object) {
            return createCreateLineToolAdapter();
        }

        @Override
        public Adapter caseCreateCellTool(CreateCellTool object) {
            return createCreateCellToolAdapter();
        }

        @Override
        public Adapter caseDeleteTool(DeleteTool object) {
            return createDeleteToolAdapter();
        }

        @Override
        public Adapter caseDeleteColumnTool(DeleteColumnTool object) {
            return createDeleteColumnToolAdapter();
        }

        @Override
        public Adapter caseDeleteLineTool(DeleteLineTool object) {
            return createDeleteLineToolAdapter();
        }

        @Override
        public Adapter caseForegroundStyleDescription(ForegroundStyleDescription object) {
            return createForegroundStyleDescriptionAdapter();
        }

        @Override
        public Adapter caseBackgroundStyleDescription(BackgroundStyleDescription object) {
            return createBackgroundStyleDescriptionAdapter();
        }

        @Override
        public Adapter caseForegroundConditionalStyle(ForegroundConditionalStyle object) {
            return createForegroundConditionalStyleAdapter();
        }

        @Override
        public Adapter caseBackgroundConditionalStyle(BackgroundConditionalStyle object) {
            return createBackgroundConditionalStyleAdapter();
        }

        @Override
        public Adapter caseTableVariable(TableVariable object) {
            return createTableVariableAdapter();
        }

        @Override
        public Adapter caseTableCreationDescription(TableCreationDescription object) {
            return createTableCreationDescriptionAdapter();
        }

        @Override
        public Adapter caseTableNavigationDescription(TableNavigationDescription object) {
            return createTableNavigationDescriptionAdapter();
        }

        @Override
        public Adapter caseCellEditorTool(CellEditorTool object) {
            return createCellEditorToolAdapter();
        }

        @Override
        public Adapter caseDocumentedElement(DocumentedElement object) {
            return createDocumentedElementAdapter();
        }

        @Override
        public Adapter caseEndUserDocumentedElement(EndUserDocumentedElement object) {
            return createEndUserDocumentedElementAdapter();
        }

        @Override
        public Adapter caseIdentifiedElement(IdentifiedElement object) {
            return createIdentifiedElementAdapter();
        }

        @Override
        public Adapter caseRepresentationDescription(RepresentationDescription object) {
            return createRepresentationDescriptionAdapter();
        }

        @Override
        public Adapter caseRepresentationElementMapping(RepresentationElementMapping object) {
            return createRepresentationElementMappingAdapter();
        }

        @Override
        public Adapter caseToolEntry(ToolEntry object) {
            return createToolEntryAdapter();
        }

        @Override
        public Adapter caseAbstractToolDescription(AbstractToolDescription object) {
            return createAbstractToolDescriptionAdapter();
        }

        @Override
        public Adapter caseAbstractVariable(AbstractVariable object) {
            return createAbstractVariableAdapter();
        }

        @Override
        public Adapter caseVariableContainer(VariableContainer object) {
            return createVariableContainerAdapter();
        }

        @Override
        public Adapter caseRepresentationCreationDescription(RepresentationCreationDescription object) {
            return createRepresentationCreationDescriptionAdapter();
        }

        @Override
        public Adapter caseRepresentationNavigationDescription(RepresentationNavigationDescription object) {
            return createRepresentationNavigationDescriptionAdapter();
        }

        @Override
        public Adapter defaultCase(EObject object) {
            return createEObjectAdapter();
        }
    };

    /**
     * Creates an adapter for the <code>target</code>. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param target
     *            the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter(Notifier target) {
        return modelSwitch.doSwitch((EObject) target);
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.table.metamodel.table.description.TableDescription <em>Table Description</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.table.metamodel.table.description.TableDescription
     * @generated
     */
    public Adapter createTableDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.table.metamodel.table.description.EditionTableDescription <em>Edition Table
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.table.metamodel.table.description.EditionTableDescription
     * @generated
     */
    public Adapter createEditionTableDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.table.metamodel.table.description.CrossTableDescription <em>Cross Table
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.table.metamodel.table.description.CrossTableDescription
     * @generated
     */
    public Adapter createCrossTableDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.table.metamodel.table.description.TableMapping <em>Table Mapping</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.table.metamodel.table.description.TableMapping
     * @generated
     */
    public Adapter createTableMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.table.metamodel.table.description.LineMapping <em>Line Mapping</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.table.metamodel.table.description.LineMapping
     * @generated
     */
    public Adapter createLineMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.table.metamodel.table.description.ColumnMapping <em>Column Mapping</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.table.metamodel.table.description.ColumnMapping
     * @generated
     */
    public Adapter createColumnMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.table.metamodel.table.description.ElementColumnMapping <em>Element Column
     * Mapping</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.table.metamodel.table.description.ElementColumnMapping
     * @generated
     */
    public Adapter createElementColumnMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.table.metamodel.table.description.FeatureColumnMapping <em>Feature Column
     * Mapping</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.table.metamodel.table.description.FeatureColumnMapping
     * @generated
     */
    public Adapter createFeatureColumnMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.table.metamodel.table.description.CellUpdater <em>Cell Updater</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.table.metamodel.table.description.CellUpdater
     * @generated
     */
    public Adapter createCellUpdaterAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.table.metamodel.table.description.StyleUpdater <em>Style Updater</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.table.metamodel.table.description.StyleUpdater
     * @generated
     */
    public Adapter createStyleUpdaterAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping <em>Intersection Mapping</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful
     * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping
     * @generated
     */
    public Adapter createIntersectionMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.table.metamodel.table.description.TableTool <em>Table Tool</em>}'. <!-- begin-user-doc
     * --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case
     * when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.table.metamodel.table.description.TableTool
     * @generated
     */
    public Adapter createTableToolAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.table.metamodel.table.description.LabelEditTool <em>Label Edit Tool</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.table.metamodel.table.description.LabelEditTool
     * @generated
     */
    public Adapter createLabelEditToolAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.table.metamodel.table.description.CreateTool <em>Create Tool</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.table.metamodel.table.description.CreateTool
     * @generated
     */
    public Adapter createCreateToolAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.table.metamodel.table.description.CreateColumnTool <em>Create Column Tool</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.table.metamodel.table.description.CreateColumnTool
     * @generated
     */
    public Adapter createCreateColumnToolAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.table.metamodel.table.description.CreateCrossColumnTool <em>Create Cross Column
     * Tool</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.table.metamodel.table.description.CreateCrossColumnTool
     * @generated
     */
    public Adapter createCreateCrossColumnToolAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.table.metamodel.table.description.CreateLineTool <em>Create Line Tool</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.table.metamodel.table.description.CreateLineTool
     * @generated
     */
    public Adapter createCreateLineToolAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.table.metamodel.table.description.CreateCellTool <em>Create Cell Tool</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.table.metamodel.table.description.CreateCellTool
     * @generated
     */
    public Adapter createCreateCellToolAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.table.metamodel.table.description.DeleteTool <em>Delete Tool</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.table.metamodel.table.description.DeleteTool
     * @generated
     */
    public Adapter createDeleteToolAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.table.metamodel.table.description.DeleteColumnTool <em>Delete Column Tool</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.table.metamodel.table.description.DeleteColumnTool
     * @generated
     */
    public Adapter createDeleteColumnToolAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.table.metamodel.table.description.DeleteLineTool <em>Delete Line Tool</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.table.metamodel.table.description.DeleteLineTool
     * @generated
     */
    public Adapter createDeleteLineToolAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.table.metamodel.table.description.ForegroundStyleDescription <em>Foreground Style
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.table.metamodel.table.description.ForegroundStyleDescription
     * @generated
     */
    public Adapter createForegroundStyleDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.table.metamodel.table.description.BackgroundStyleDescription <em>Background Style
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.table.metamodel.table.description.BackgroundStyleDescription
     * @generated
     */
    public Adapter createBackgroundStyleDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.table.metamodel.table.description.ForegroundConditionalStyle <em>Foreground
     * Conditional Style</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
     * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.table.metamodel.table.description.ForegroundConditionalStyle
     * @generated
     */
    public Adapter createForegroundConditionalStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.table.metamodel.table.description.BackgroundConditionalStyle <em>Background
     * Conditional Style</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
     * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.table.metamodel.table.description.BackgroundConditionalStyle
     * @generated
     */
    public Adapter createBackgroundConditionalStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.table.metamodel.table.description.TableVariable <em>Table Variable</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.table.metamodel.table.description.TableVariable
     * @generated
     */
    public Adapter createTableVariableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.table.metamodel.table.description.TableCreationDescription <em>Table Creation
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.table.metamodel.table.description.TableCreationDescription
     * @generated
     */
    public Adapter createTableCreationDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.table.metamodel.table.description.TableNavigationDescription <em>Table Navigation
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.table.metamodel.table.description.TableNavigationDescription
     * @generated
     */
    public Adapter createTableNavigationDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.table.metamodel.table.description.CellEditorTool <em>Cell Editor Tool</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.table.metamodel.table.description.CellEditorTool
     * @generated
     */
    public Adapter createCellEditorToolAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.viewpoint.description.RepresentationDescription <em>Representation Description</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful
     * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.RepresentationDescription
     * @generated
     */
    public Adapter createRepresentationDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.viewpoint.description.RepresentationElementMapping <em>Representation Element
     * Mapping</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.RepresentationElementMapping
     * @generated
     */
    public Adapter createRepresentationElementMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.description.DocumentedElement
     * <em>Documented Element</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.DocumentedElement
     * @generated
     */
    public Adapter createDocumentedElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.viewpoint.description.EndUserDocumentedElement <em>End User Documented Element</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful
     * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.EndUserDocumentedElement
     * @generated
     */
    public Adapter createEndUserDocumentedElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.description.IdentifiedElement
     * <em>Identified Element</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.IdentifiedElement
     * @generated
     */
    public Adapter createIdentifiedElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.description.tool.ToolEntry
     * <em>Entry</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolEntry
     * @generated
     */
    public Adapter createToolEntryAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription <em>Abstract Tool
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription
     * @generated
     */
    public Adapter createAbstractToolDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.description.AbstractVariable
     * <em>Abstract Variable</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.AbstractVariable
     * @generated
     */
    public Adapter createAbstractVariableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.VariableContainer <em>Variable Container</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.VariableContainer
     * @generated
     */
    public Adapter createVariableContainerAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription <em>Representation
     * Creation Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription
     * @generated
     */
    public Adapter createRepresentationCreationDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription <em>Representation
     * Navigation Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription
     * @generated
     */
    public Adapter createRepresentationNavigationDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case. <!-- begin-user-doc --> This default implementation returns null.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} // DescriptionAdapterFactory
