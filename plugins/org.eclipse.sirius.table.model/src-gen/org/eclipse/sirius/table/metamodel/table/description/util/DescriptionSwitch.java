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

import java.util.List;

import org.eclipse.emf.ecore.EClass;
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
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance hierarchy. It supports the call
 * {@link #doSwitch(EObject) doSwitch(object)} to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the inheritance hierarchy until a non-null result is
 * returned, which is the result of the switch. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage
 * @generated
 */
public class DescriptionSwitch<T> {
    /**
     * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected static DescriptionPackage modelPackage;

    /**
     * Creates an instance of the switch. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public DescriptionSwitch() {
        if (DescriptionSwitch.modelPackage == null) {
            DescriptionSwitch.modelPackage = DescriptionPackage.eINSTANCE;
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that
     * result. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    public T doSwitch(EObject theEObject) {
        return doSwitch(theEObject.eClass(), theEObject);
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that
     * result. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    protected T doSwitch(EClass theEClass, EObject theEObject) {
        if (theEClass.eContainer() == DescriptionSwitch.modelPackage) {
            return doSwitch(theEClass.getClassifierID(), theEObject);
        } else {
            List<EClass> eSuperTypes = theEClass.getESuperTypes();
            return eSuperTypes.isEmpty() ? defaultCase(theEObject) : doSwitch(eSuperTypes.get(0), theEObject);
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that
     * result. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    protected T doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
        case DescriptionPackage.TABLE_DESCRIPTION: {
            TableDescription tableDescription = (TableDescription) theEObject;
            T result = caseTableDescription(tableDescription);
            if (result == null) {
                result = caseRepresentationDescription(tableDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(tableDescription);
            }
            if (result == null) {
                result = caseEndUserDocumentedElement(tableDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(tableDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.EDITION_TABLE_DESCRIPTION: {
            EditionTableDescription editionTableDescription = (EditionTableDescription) theEObject;
            T result = caseEditionTableDescription(editionTableDescription);
            if (result == null) {
                result = caseTableDescription(editionTableDescription);
            }
            if (result == null) {
                result = caseRepresentationDescription(editionTableDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(editionTableDescription);
            }
            if (result == null) {
                result = caseEndUserDocumentedElement(editionTableDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(editionTableDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.CROSS_TABLE_DESCRIPTION: {
            CrossTableDescription crossTableDescription = (CrossTableDescription) theEObject;
            T result = caseCrossTableDescription(crossTableDescription);
            if (result == null) {
                result = caseTableDescription(crossTableDescription);
            }
            if (result == null) {
                result = caseRepresentationDescription(crossTableDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(crossTableDescription);
            }
            if (result == null) {
                result = caseEndUserDocumentedElement(crossTableDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(crossTableDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.TABLE_MAPPING: {
            TableMapping tableMapping = (TableMapping) theEObject;
            T result = caseTableMapping(tableMapping);
            if (result == null) {
                result = caseRepresentationElementMapping(tableMapping);
            }
            if (result == null) {
                result = caseIdentifiedElement(tableMapping);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.LINE_MAPPING: {
            LineMapping lineMapping = (LineMapping) theEObject;
            T result = caseLineMapping(lineMapping);
            if (result == null) {
                result = caseTableMapping(lineMapping);
            }
            if (result == null) {
                result = caseStyleUpdater(lineMapping);
            }
            if (result == null) {
                result = caseRepresentationElementMapping(lineMapping);
            }
            if (result == null) {
                result = caseIdentifiedElement(lineMapping);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.COLUMN_MAPPING: {
            ColumnMapping columnMapping = (ColumnMapping) theEObject;
            T result = caseColumnMapping(columnMapping);
            if (result == null) {
                result = caseTableMapping(columnMapping);
            }
            if (result == null) {
                result = caseRepresentationElementMapping(columnMapping);
            }
            if (result == null) {
                result = caseIdentifiedElement(columnMapping);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING: {
            ElementColumnMapping elementColumnMapping = (ElementColumnMapping) theEObject;
            T result = caseElementColumnMapping(elementColumnMapping);
            if (result == null) {
                result = caseColumnMapping(elementColumnMapping);
            }
            if (result == null) {
                result = caseStyleUpdater(elementColumnMapping);
            }
            if (result == null) {
                result = caseTableMapping(elementColumnMapping);
            }
            if (result == null) {
                result = caseRepresentationElementMapping(elementColumnMapping);
            }
            if (result == null) {
                result = caseIdentifiedElement(elementColumnMapping);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.FEATURE_COLUMN_MAPPING: {
            FeatureColumnMapping featureColumnMapping = (FeatureColumnMapping) theEObject;
            T result = caseFeatureColumnMapping(featureColumnMapping);
            if (result == null) {
                result = caseColumnMapping(featureColumnMapping);
            }
            if (result == null) {
                result = caseCellUpdater(featureColumnMapping);
            }
            if (result == null) {
                result = caseStyleUpdater(featureColumnMapping);
            }
            if (result == null) {
                result = caseTableMapping(featureColumnMapping);
            }
            if (result == null) {
                result = caseRepresentationElementMapping(featureColumnMapping);
            }
            if (result == null) {
                result = caseIdentifiedElement(featureColumnMapping);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.CELL_UPDATER: {
            CellUpdater cellUpdater = (CellUpdater) theEObject;
            T result = caseCellUpdater(cellUpdater);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.STYLE_UPDATER: {
            StyleUpdater styleUpdater = (StyleUpdater) theEObject;
            T result = caseStyleUpdater(styleUpdater);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.INTERSECTION_MAPPING: {
            IntersectionMapping intersectionMapping = (IntersectionMapping) theEObject;
            T result = caseIntersectionMapping(intersectionMapping);
            if (result == null) {
                result = caseTableMapping(intersectionMapping);
            }
            if (result == null) {
                result = caseCellUpdater(intersectionMapping);
            }
            if (result == null) {
                result = caseStyleUpdater(intersectionMapping);
            }
            if (result == null) {
                result = caseRepresentationElementMapping(intersectionMapping);
            }
            if (result == null) {
                result = caseIdentifiedElement(intersectionMapping);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.TABLE_TOOL: {
            TableTool tableTool = (TableTool) theEObject;
            T result = caseTableTool(tableTool);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.LABEL_EDIT_TOOL: {
            LabelEditTool labelEditTool = (LabelEditTool) theEObject;
            T result = caseLabelEditTool(labelEditTool);
            if (result == null) {
                result = caseTableTool(labelEditTool);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.CREATE_TOOL: {
            CreateTool createTool = (CreateTool) theEObject;
            T result = caseCreateTool(createTool);
            if (result == null) {
                result = caseAbstractToolDescription(createTool);
            }
            if (result == null) {
                result = caseTableTool(createTool);
            }
            if (result == null) {
                result = caseToolEntry(createTool);
            }
            if (result == null) {
                result = caseDocumentedElement(createTool);
            }
            if (result == null) {
                result = caseIdentifiedElement(createTool);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.CREATE_COLUMN_TOOL: {
            CreateColumnTool createColumnTool = (CreateColumnTool) theEObject;
            T result = caseCreateColumnTool(createColumnTool);
            if (result == null) {
                result = caseCreateTool(createColumnTool);
            }
            if (result == null) {
                result = caseAbstractToolDescription(createColumnTool);
            }
            if (result == null) {
                result = caseTableTool(createColumnTool);
            }
            if (result == null) {
                result = caseToolEntry(createColumnTool);
            }
            if (result == null) {
                result = caseDocumentedElement(createColumnTool);
            }
            if (result == null) {
                result = caseIdentifiedElement(createColumnTool);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.CREATE_CROSS_COLUMN_TOOL: {
            CreateCrossColumnTool createCrossColumnTool = (CreateCrossColumnTool) theEObject;
            T result = caseCreateCrossColumnTool(createCrossColumnTool);
            if (result == null) {
                result = caseCreateTool(createCrossColumnTool);
            }
            if (result == null) {
                result = caseAbstractToolDescription(createCrossColumnTool);
            }
            if (result == null) {
                result = caseTableTool(createCrossColumnTool);
            }
            if (result == null) {
                result = caseToolEntry(createCrossColumnTool);
            }
            if (result == null) {
                result = caseDocumentedElement(createCrossColumnTool);
            }
            if (result == null) {
                result = caseIdentifiedElement(createCrossColumnTool);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.CREATE_LINE_TOOL: {
            CreateLineTool createLineTool = (CreateLineTool) theEObject;
            T result = caseCreateLineTool(createLineTool);
            if (result == null) {
                result = caseCreateTool(createLineTool);
            }
            if (result == null) {
                result = caseAbstractToolDescription(createLineTool);
            }
            if (result == null) {
                result = caseTableTool(createLineTool);
            }
            if (result == null) {
                result = caseToolEntry(createLineTool);
            }
            if (result == null) {
                result = caseDocumentedElement(createLineTool);
            }
            if (result == null) {
                result = caseIdentifiedElement(createLineTool);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.CREATE_CELL_TOOL: {
            CreateCellTool createCellTool = (CreateCellTool) theEObject;
            T result = caseCreateCellTool(createCellTool);
            if (result == null) {
                result = caseTableTool(createCellTool);
            }
            if (result == null) {
                result = caseAbstractToolDescription(createCellTool);
            }
            if (result == null) {
                result = caseToolEntry(createCellTool);
            }
            if (result == null) {
                result = caseDocumentedElement(createCellTool);
            }
            if (result == null) {
                result = caseIdentifiedElement(createCellTool);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.DELETE_TOOL: {
            DeleteTool deleteTool = (DeleteTool) theEObject;
            T result = caseDeleteTool(deleteTool);
            if (result == null) {
                result = caseAbstractToolDescription(deleteTool);
            }
            if (result == null) {
                result = caseTableTool(deleteTool);
            }
            if (result == null) {
                result = caseToolEntry(deleteTool);
            }
            if (result == null) {
                result = caseDocumentedElement(deleteTool);
            }
            if (result == null) {
                result = caseIdentifiedElement(deleteTool);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.DELETE_COLUMN_TOOL: {
            DeleteColumnTool deleteColumnTool = (DeleteColumnTool) theEObject;
            T result = caseDeleteColumnTool(deleteColumnTool);
            if (result == null) {
                result = caseDeleteTool(deleteColumnTool);
            }
            if (result == null) {
                result = caseAbstractToolDescription(deleteColumnTool);
            }
            if (result == null) {
                result = caseTableTool(deleteColumnTool);
            }
            if (result == null) {
                result = caseToolEntry(deleteColumnTool);
            }
            if (result == null) {
                result = caseDocumentedElement(deleteColumnTool);
            }
            if (result == null) {
                result = caseIdentifiedElement(deleteColumnTool);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.DELETE_LINE_TOOL: {
            DeleteLineTool deleteLineTool = (DeleteLineTool) theEObject;
            T result = caseDeleteLineTool(deleteLineTool);
            if (result == null) {
                result = caseDeleteTool(deleteLineTool);
            }
            if (result == null) {
                result = caseAbstractToolDescription(deleteLineTool);
            }
            if (result == null) {
                result = caseTableTool(deleteLineTool);
            }
            if (result == null) {
                result = caseToolEntry(deleteLineTool);
            }
            if (result == null) {
                result = caseDocumentedElement(deleteLineTool);
            }
            if (result == null) {
                result = caseIdentifiedElement(deleteLineTool);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.FOREGROUND_STYLE_DESCRIPTION: {
            ForegroundStyleDescription foregroundStyleDescription = (ForegroundStyleDescription) theEObject;
            T result = caseForegroundStyleDescription(foregroundStyleDescription);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.BACKGROUND_STYLE_DESCRIPTION: {
            BackgroundStyleDescription backgroundStyleDescription = (BackgroundStyleDescription) theEObject;
            T result = caseBackgroundStyleDescription(backgroundStyleDescription);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.FOREGROUND_CONDITIONAL_STYLE: {
            ForegroundConditionalStyle foregroundConditionalStyle = (ForegroundConditionalStyle) theEObject;
            T result = caseForegroundConditionalStyle(foregroundConditionalStyle);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.BACKGROUND_CONDITIONAL_STYLE: {
            BackgroundConditionalStyle backgroundConditionalStyle = (BackgroundConditionalStyle) theEObject;
            T result = caseBackgroundConditionalStyle(backgroundConditionalStyle);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.TABLE_VARIABLE: {
            TableVariable tableVariable = (TableVariable) theEObject;
            T result = caseTableVariable(tableVariable);
            if (result == null) {
                result = caseAbstractVariable(tableVariable);
            }
            if (result == null) {
                result = caseVariableContainer(tableVariable);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.TABLE_CREATION_DESCRIPTION: {
            TableCreationDescription tableCreationDescription = (TableCreationDescription) theEObject;
            T result = caseTableCreationDescription(tableCreationDescription);
            if (result == null) {
                result = caseRepresentationCreationDescription(tableCreationDescription);
            }
            if (result == null) {
                result = caseAbstractToolDescription(tableCreationDescription);
            }
            if (result == null) {
                result = caseToolEntry(tableCreationDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(tableCreationDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(tableCreationDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.TABLE_NAVIGATION_DESCRIPTION: {
            TableNavigationDescription tableNavigationDescription = (TableNavigationDescription) theEObject;
            T result = caseTableNavigationDescription(tableNavigationDescription);
            if (result == null) {
                result = caseRepresentationNavigationDescription(tableNavigationDescription);
            }
            if (result == null) {
                result = caseAbstractToolDescription(tableNavigationDescription);
            }
            if (result == null) {
                result = caseToolEntry(tableNavigationDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(tableNavigationDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(tableNavigationDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.CELL_EDITOR_TOOL: {
            CellEditorTool cellEditorTool = (CellEditorTool) theEObject;
            T result = caseCellEditorTool(cellEditorTool);
            if (result == null) {
                result = caseTableTool(cellEditorTool);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        default:
            return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Table Description</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Table Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTableDescription(TableDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Edition Table Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Edition Table Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEditionTableDescription(EditionTableDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Cross Table Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Cross Table Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCrossTableDescription(CrossTableDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Table Mapping</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Table Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTableMapping(TableMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Line Mapping</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Line Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLineMapping(LineMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Column Mapping</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Column Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseColumnMapping(ColumnMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Element Column Mapping</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Element Column Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseElementColumnMapping(ElementColumnMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Feature Column Mapping</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Feature Column Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFeatureColumnMapping(FeatureColumnMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Cell Updater</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Cell Updater</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCellUpdater(CellUpdater object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Style Updater</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Style Updater</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseStyleUpdater(StyleUpdater object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Intersection Mapping</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Intersection Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseIntersectionMapping(IntersectionMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Table Tool</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Table Tool</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTableTool(TableTool object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Label Edit Tool</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Label Edit Tool</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLabelEditTool(LabelEditTool object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Create Tool</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Create Tool</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCreateTool(CreateTool object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Create Column Tool</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Create Column Tool</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCreateColumnTool(CreateColumnTool object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Create Cross Column Tool</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Create Cross Column Tool</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCreateCrossColumnTool(CreateCrossColumnTool object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Create Line Tool</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Create Line Tool</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCreateLineTool(CreateLineTool object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Create Cell Tool</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Create Cell Tool</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCreateCellTool(CreateCellTool object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Delete Tool</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Delete Tool</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDeleteTool(DeleteTool object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Delete Column Tool</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Delete Column Tool</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDeleteColumnTool(DeleteColumnTool object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Delete Line Tool</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Delete Line Tool</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDeleteLineTool(DeleteLineTool object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Foreground Style Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Foreground Style Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseForegroundStyleDescription(ForegroundStyleDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Background Style Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Background Style Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBackgroundStyleDescription(BackgroundStyleDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Foreground Conditional Style</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Foreground Conditional Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseForegroundConditionalStyle(ForegroundConditionalStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Background Conditional Style</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Background Conditional Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBackgroundConditionalStyle(BackgroundConditionalStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Table Variable</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Table Variable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTableVariable(TableVariable object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Table Creation Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Table Creation Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTableCreationDescription(TableCreationDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Table Navigation Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Table Navigation Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTableNavigationDescription(TableNavigationDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Cell Editor Tool</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Cell Editor Tool</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCellEditorTool(CellEditorTool object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Representation Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Representation Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRepresentationDescription(RepresentationDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Representation Element Mapping</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Representation Element Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRepresentationElementMapping(RepresentationElementMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Documented Element</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Documented Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDocumentedElement(DocumentedElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>End User Documented Element</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>End User Documented Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEndUserDocumentedElement(EndUserDocumentedElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Identified Element</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Identified Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseIdentifiedElement(IdentifiedElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Entry</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Entry</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseToolEntry(ToolEntry object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Tool Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Tool Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractToolDescription(AbstractToolDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Variable</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Variable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractVariable(AbstractVariable object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Variable Container</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Variable Container</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseVariableContainer(VariableContainer object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Representation Creation Description</em>'.
     * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Representation Creation Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRepresentationCreationDescription(RepresentationCreationDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Representation Navigation Description</em>'.
     * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Representation Navigation Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRepresentationNavigationDescription(RepresentationNavigationDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch, but this is the last case
     * anyway. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    public T defaultCase(EObject object) {
        return null;
    }

} // DescriptionSwitch
