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
package org.eclipse.sirius.tree.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.DTreeElement;
import org.eclipse.sirius.tree.DTreeElementSynchronizer;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.DTreeItemContainer;
import org.eclipse.sirius.tree.TreeItemStyle;
import org.eclipse.sirius.tree.TreePackage;
import org.eclipse.sirius.viewpoint.BasicLabelStyle;
import org.eclipse.sirius.viewpoint.Customizable;
import org.eclipse.sirius.viewpoint.DMappingBased;
import org.eclipse.sirius.viewpoint.DRefreshable;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.DStylizable;
import org.eclipse.sirius.viewpoint.IdentifiedElement;
import org.eclipse.sirius.viewpoint.LabelStyle;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.sirius.viewpoint.description.DModelElement;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance hierarchy. It supports the call
 * {@link #doSwitch(EObject) doSwitch(object)} to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the inheritance hierarchy until a non-null result is
 * returned, which is the result of the switch. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.tree.TreePackage
 * @generated
 */
public class TreeSwitch<T> {
    /**
     * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected static TreePackage modelPackage;

    /**
     * Creates an instance of the switch. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public TreeSwitch() {
        if (TreeSwitch.modelPackage == null) {
            TreeSwitch.modelPackage = TreePackage.eINSTANCE;
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
        if (theEClass.eContainer() == TreeSwitch.modelPackage) {
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
        case TreePackage.DTREE: {
            DTree dTree = (DTree) theEObject;
            T result = caseDTree(dTree);
            if (result == null) {
                result = caseDRepresentation(dTree);
            }
            if (result == null) {
                result = caseDTreeItemContainer(dTree);
            }
            if (result == null) {
                result = caseDModelElement(dTree);
            }
            if (result == null) {
                result = caseDRefreshable(dTree);
            }
            if (result == null) {
                result = caseDSemanticDecorator(dTree);
            }
            if (result == null) {
                result = caseIdentifiedElement(dTree);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case TreePackage.DTREE_ELEMENT: {
            DTreeElement dTreeElement = (DTreeElement) theEObject;
            T result = caseDTreeElement(dTreeElement);
            if (result == null) {
                result = caseDRepresentationElement(dTreeElement);
            }
            if (result == null) {
                result = caseDMappingBased(dTreeElement);
            }
            if (result == null) {
                result = caseDStylizable(dTreeElement);
            }
            if (result == null) {
                result = caseDRefreshable(dTreeElement);
            }
            if (result == null) {
                result = caseDSemanticDecorator(dTreeElement);
            }
            if (result == null) {
                result = caseIdentifiedElement(dTreeElement);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case TreePackage.DTREE_ITEM_CONTAINER: {
            DTreeItemContainer dTreeItemContainer = (DTreeItemContainer) theEObject;
            T result = caseDTreeItemContainer(dTreeItemContainer);
            if (result == null) {
                result = caseDSemanticDecorator(dTreeItemContainer);
            }
            if (result == null) {
                result = caseIdentifiedElement(dTreeItemContainer);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case TreePackage.DTREE_ITEM: {
            DTreeItem dTreeItem = (DTreeItem) theEObject;
            T result = caseDTreeItem(dTreeItem);
            if (result == null) {
                result = caseDTreeItemContainer(dTreeItem);
            }
            if (result == null) {
                result = caseDTreeElement(dTreeItem);
            }
            if (result == null) {
                result = caseDRepresentationElement(dTreeItem);
            }
            if (result == null) {
                result = caseDSemanticDecorator(dTreeItem);
            }
            if (result == null) {
                result = caseIdentifiedElement(dTreeItem);
            }
            if (result == null) {
                result = caseDMappingBased(dTreeItem);
            }
            if (result == null) {
                result = caseDStylizable(dTreeItem);
            }
            if (result == null) {
                result = caseDRefreshable(dTreeItem);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case TreePackage.TREE_ITEM_STYLE: {
            TreeItemStyle treeItemStyle = (TreeItemStyle) theEObject;
            T result = caseTreeItemStyle(treeItemStyle);
            if (result == null) {
                result = caseStyle(treeItemStyle);
            }
            if (result == null) {
                result = caseLabelStyle(treeItemStyle);
            }
            if (result == null) {
                result = caseDRefreshable(treeItemStyle);
            }
            if (result == null) {
                result = caseBasicLabelStyle(treeItemStyle);
            }
            if (result == null) {
                result = caseIdentifiedElement(treeItemStyle);
            }
            if (result == null) {
                result = caseCustomizable(treeItemStyle);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case TreePackage.DTREE_ELEMENT_SYNCHRONIZER: {
            DTreeElementSynchronizer dTreeElementSynchronizer = (DTreeElementSynchronizer) theEObject;
            T result = caseDTreeElementSynchronizer(dTreeElementSynchronizer);
            if (result == null) {
                result = caseIdentifiedElement(dTreeElementSynchronizer);
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
     * Returns the result of interpreting the object as an instance of '<em>DTree Item Container</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DTree Item Container</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDTreeItemContainer(DTreeItemContainer object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DTree</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DTree</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDTree(DTree object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DTree Element</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DTree Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDTreeElement(DTreeElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DTree Item</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DTree Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDTreeItem(DTreeItem object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Item Style</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Item Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTreeItemStyle(TreeItemStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DTree Element Synchronizer</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DTree Element Synchronizer</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDTreeElementSynchronizer(DTreeElementSynchronizer object) {
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
     * Returns the result of interpreting the object as an instance of '<em>Customizable</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Customizable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCustomizable(Customizable object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Style</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseStyle(Style object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Basic Label Style</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Basic Label Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBasicLabelStyle(BasicLabelStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Label Style</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Label Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLabelStyle(LabelStyle object) {
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

} // TreeSwitch
