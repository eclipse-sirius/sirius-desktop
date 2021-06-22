/*******************************************************************************
 * Copyright (c) 2007, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.table.metamodel.table.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DCellStyle;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DFeatureColumn;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.DTableElement;
import org.eclipse.sirius.table.metamodel.table.DTableElementStyle;
import org.eclipse.sirius.table.metamodel.table.DTargetColumn;
import org.eclipse.sirius.table.metamodel.table.LineContainer;
import org.eclipse.sirius.table.metamodel.table.TablePackage;
import org.eclipse.sirius.viewpoint.DMappingBased;
import org.eclipse.sirius.viewpoint.DRefreshable;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.DStylizable;
import org.eclipse.sirius.viewpoint.IdentifiedElement;
import org.eclipse.sirius.viewpoint.description.DModelElement;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance hierarchy. It supports the call
 * {@link #doSwitch(EObject) doSwitch(object)} to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the inheritance hierarchy until a non-null result is
 * returned, which is the result of the switch. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.table.metamodel.table.TablePackage
 * @generated
 */
public class TableSwitch<T> {
    /**
     * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected static TablePackage modelPackage;

    /**
     * Creates an instance of the switch. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public TableSwitch() {
        if (TableSwitch.modelPackage == null) {
            TableSwitch.modelPackage = TablePackage.eINSTANCE;
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
        if (theEClass.eContainer() == TableSwitch.modelPackage) {
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
        case TablePackage.DTABLE: {
            DTable dTable = (DTable) theEObject;
            T result = caseDTable(dTable);
            if (result == null) {
                result = caseDRepresentation(dTable);
            }
            if (result == null) {
                result = caseLineContainer(dTable);
            }
            if (result == null) {
                result = caseDModelElement(dTable);
            }
            if (result == null) {
                result = caseDRefreshable(dTable);
            }
            if (result == null) {
                result = caseDSemanticDecorator(dTable);
            }
            if (result == null) {
                result = caseIdentifiedElement(dTable);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case TablePackage.DTABLE_ELEMENT: {
            DTableElement dTableElement = (DTableElement) theEObject;
            T result = caseDTableElement(dTableElement);
            if (result == null) {
                result = caseDRepresentationElement(dTableElement);
            }
            if (result == null) {
                result = caseDMappingBased(dTableElement);
            }
            if (result == null) {
                result = caseDStylizable(dTableElement);
            }
            if (result == null) {
                result = caseDRefreshable(dTableElement);
            }
            if (result == null) {
                result = caseDSemanticDecorator(dTableElement);
            }
            if (result == null) {
                result = caseIdentifiedElement(dTableElement);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case TablePackage.LINE_CONTAINER: {
            LineContainer lineContainer = (LineContainer) theEObject;
            T result = caseLineContainer(lineContainer);
            if (result == null) {
                result = caseDSemanticDecorator(lineContainer);
            }
            if (result == null) {
                result = caseIdentifiedElement(lineContainer);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case TablePackage.DLINE: {
            DLine dLine = (DLine) theEObject;
            T result = caseDLine(dLine);
            if (result == null) {
                result = caseLineContainer(dLine);
            }
            if (result == null) {
                result = caseDTableElement(dLine);
            }
            if (result == null) {
                result = caseDRepresentationElement(dLine);
            }
            if (result == null) {
                result = caseDSemanticDecorator(dLine);
            }
            if (result == null) {
                result = caseIdentifiedElement(dLine);
            }
            if (result == null) {
                result = caseDMappingBased(dLine);
            }
            if (result == null) {
                result = caseDStylizable(dLine);
            }
            if (result == null) {
                result = caseDRefreshable(dLine);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case TablePackage.DCELL: {
            DCell dCell = (DCell) theEObject;
            T result = caseDCell(dCell);
            if (result == null) {
                result = caseDTableElement(dCell);
            }
            if (result == null) {
                result = caseDRepresentationElement(dCell);
            }
            if (result == null) {
                result = caseDSemanticDecorator(dCell);
            }
            if (result == null) {
                result = caseIdentifiedElement(dCell);
            }
            if (result == null) {
                result = caseDMappingBased(dCell);
            }
            if (result == null) {
                result = caseDStylizable(dCell);
            }
            if (result == null) {
                result = caseDRefreshable(dCell);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case TablePackage.DCELL_STYLE: {
            DCellStyle dCellStyle = (DCellStyle) theEObject;
            T result = caseDCellStyle(dCellStyle);
            if (result == null) {
                result = caseDTableElementStyle(dCellStyle);
            }
            if (result == null) {
                result = caseIdentifiedElement(dCellStyle);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case TablePackage.DCOLUMN: {
            DColumn dColumn = (DColumn) theEObject;
            T result = caseDColumn(dColumn);
            if (result == null) {
                result = caseDTableElement(dColumn);
            }
            if (result == null) {
                result = caseDRepresentationElement(dColumn);
            }
            if (result == null) {
                result = caseDMappingBased(dColumn);
            }
            if (result == null) {
                result = caseDStylizable(dColumn);
            }
            if (result == null) {
                result = caseDRefreshable(dColumn);
            }
            if (result == null) {
                result = caseDSemanticDecorator(dColumn);
            }
            if (result == null) {
                result = caseIdentifiedElement(dColumn);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case TablePackage.DTARGET_COLUMN: {
            DTargetColumn dTargetColumn = (DTargetColumn) theEObject;
            T result = caseDTargetColumn(dTargetColumn);
            if (result == null) {
                result = caseDColumn(dTargetColumn);
            }
            if (result == null) {
                result = caseDTableElement(dTargetColumn);
            }
            if (result == null) {
                result = caseDRepresentationElement(dTargetColumn);
            }
            if (result == null) {
                result = caseDSemanticDecorator(dTargetColumn);
            }
            if (result == null) {
                result = caseIdentifiedElement(dTargetColumn);
            }
            if (result == null) {
                result = caseDMappingBased(dTargetColumn);
            }
            if (result == null) {
                result = caseDStylizable(dTargetColumn);
            }
            if (result == null) {
                result = caseDRefreshable(dTargetColumn);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case TablePackage.DFEATURE_COLUMN: {
            DFeatureColumn dFeatureColumn = (DFeatureColumn) theEObject;
            T result = caseDFeatureColumn(dFeatureColumn);
            if (result == null) {
                result = caseDColumn(dFeatureColumn);
            }
            if (result == null) {
                result = caseDTableElement(dFeatureColumn);
            }
            if (result == null) {
                result = caseDRepresentationElement(dFeatureColumn);
            }
            if (result == null) {
                result = caseDMappingBased(dFeatureColumn);
            }
            if (result == null) {
                result = caseDStylizable(dFeatureColumn);
            }
            if (result == null) {
                result = caseDRefreshable(dFeatureColumn);
            }
            if (result == null) {
                result = caseDSemanticDecorator(dFeatureColumn);
            }
            if (result == null) {
                result = caseIdentifiedElement(dFeatureColumn);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case TablePackage.DTABLE_ELEMENT_STYLE: {
            DTableElementStyle dTableElementStyle = (DTableElementStyle) theEObject;
            T result = caseDTableElementStyle(dTableElementStyle);
            if (result == null) {
                result = caseIdentifiedElement(dTableElementStyle);
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
     * Returns the result of interpreting the object as an instance of '<em>DTable</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DTable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDTable(DTable object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DTable Element</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DTable Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDTableElement(DTableElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Line Container</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Line Container</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLineContainer(LineContainer object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DLine</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DLine</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDLine(DLine object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DCell</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DCell</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDCell(DCell object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DCell Style</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DCell Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDCellStyle(DCellStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DColumn</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DColumn</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDColumn(DColumn object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DTarget Column</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DTarget Column</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDTargetColumn(DTargetColumn object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DFeature Column</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DFeature Column</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDFeatureColumn(DFeatureColumn object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DTable Element Style</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DTable Element Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDTableElementStyle(DTableElementStyle object) {
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
     * Returns the result of interpreting the object as an instance of '<em>DRefreshable</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DRefreshable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDRefreshable(DRefreshable object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DModel Element</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DModel Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDModelElement(DModelElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DRepresentation</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DRepresentation</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDRepresentation(DRepresentation object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DSemantic Decorator</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DSemantic Decorator</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDSemanticDecorator(DSemanticDecorator object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DMapping Based</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DMapping Based</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDMappingBased(DMappingBased object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DStylizable</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DStylizable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDStylizable(DStylizable object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DRepresentation Element</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DRepresentation Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDRepresentationElement(DRepresentationElement object) {
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

} // TableSwitch
