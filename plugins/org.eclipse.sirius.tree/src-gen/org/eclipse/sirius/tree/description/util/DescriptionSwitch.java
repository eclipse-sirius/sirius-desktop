/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tree.description.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.tree.description.ConditionalTreeItemStyleDescription;
import org.eclipse.sirius.tree.description.DescriptionPackage;
import org.eclipse.sirius.tree.description.PrecedingSiblingsVariables;
import org.eclipse.sirius.tree.description.StyleUpdater;
import org.eclipse.sirius.tree.description.TreeCreationDescription;
import org.eclipse.sirius.tree.description.TreeDescription;
import org.eclipse.sirius.tree.description.TreeItemContainerDropTool;
import org.eclipse.sirius.tree.description.TreeItemCreationTool;
import org.eclipse.sirius.tree.description.TreeItemDeletionTool;
import org.eclipse.sirius.tree.description.TreeItemDragTool;
import org.eclipse.sirius.tree.description.TreeItemEditionTool;
import org.eclipse.sirius.tree.description.TreeItemMapping;
import org.eclipse.sirius.tree.description.TreeItemMappingContainer;
import org.eclipse.sirius.tree.description.TreeItemStyleDescription;
import org.eclipse.sirius.tree.description.TreeItemTool;
import org.eclipse.sirius.tree.description.TreeItemUpdater;
import org.eclipse.sirius.tree.description.TreeMapping;
import org.eclipse.sirius.tree.description.TreeNavigationDescription;
import org.eclipse.sirius.tree.description.TreePopupMenu;
import org.eclipse.sirius.tree.description.TreeVariable;
import org.eclipse.sirius.viewpoint.description.AbstractVariable;
import org.eclipse.sirius.viewpoint.description.ConditionalStyleDescription;
import org.eclipse.sirius.viewpoint.description.DocumentedElement;
import org.eclipse.sirius.viewpoint.description.EndUserDocumentedElement;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;
import org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.LabelStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.MappingBasedToolDescription;
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
 * @see org.eclipse.sirius.tree.description.DescriptionPackage
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
        case DescriptionPackage.TREE_DESCRIPTION: {
            TreeDescription treeDescription = (TreeDescription) theEObject;
            T result = caseTreeDescription(treeDescription);
            if (result == null) {
                result = caseRepresentationDescription(treeDescription);
            }
            if (result == null) {
                result = caseTreeItemMappingContainer(treeDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(treeDescription);
            }
            if (result == null) {
                result = caseEndUserDocumentedElement(treeDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(treeDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.TREE_ITEM_MAPPING: {
            TreeItemMapping treeItemMapping = (TreeItemMapping) theEObject;
            T result = caseTreeItemMapping(treeItemMapping);
            if (result == null) {
                result = caseTreeMapping(treeItemMapping);
            }
            if (result == null) {
                result = caseStyleUpdater(treeItemMapping);
            }
            if (result == null) {
                result = caseTreeItemUpdater(treeItemMapping);
            }
            if (result == null) {
                result = caseTreeItemMappingContainer(treeItemMapping);
            }
            if (result == null) {
                result = caseRepresentationElementMapping(treeItemMapping);
            }
            if (result == null) {
                result = caseIdentifiedElement(treeItemMapping);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION: {
            TreeItemStyleDescription treeItemStyleDescription = (TreeItemStyleDescription) theEObject;
            T result = caseTreeItemStyleDescription(treeItemStyleDescription);
            if (result == null) {
                result = caseStyleDescription(treeItemStyleDescription);
            }
            if (result == null) {
                result = caseLabelStyleDescription(treeItemStyleDescription);
            }
            if (result == null) {
                result = caseBasicLabelStyleDescription(treeItemStyleDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.CONDITIONAL_TREE_ITEM_STYLE_DESCRIPTION: {
            ConditionalTreeItemStyleDescription conditionalTreeItemStyleDescription = (ConditionalTreeItemStyleDescription) theEObject;
            T result = caseConditionalTreeItemStyleDescription(conditionalTreeItemStyleDescription);
            if (result == null) {
                result = caseConditionalStyleDescription(conditionalTreeItemStyleDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.TREE_ITEM_TOOL: {
            TreeItemTool treeItemTool = (TreeItemTool) theEObject;
            T result = caseTreeItemTool(treeItemTool);
            if (result == null) {
                result = caseAbstractToolDescription(treeItemTool);
            }
            if (result == null) {
                result = caseToolEntry(treeItemTool);
            }
            if (result == null) {
                result = caseDocumentedElement(treeItemTool);
            }
            if (result == null) {
                result = caseIdentifiedElement(treeItemTool);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL: {
            TreeItemDragTool treeItemDragTool = (TreeItemDragTool) theEObject;
            T result = caseTreeItemDragTool(treeItemDragTool);
            if (result == null) {
                result = caseMappingBasedToolDescription(treeItemDragTool);
            }
            if (result == null) {
                result = caseTreeItemTool(treeItemDragTool);
            }
            if (result == null) {
                result = caseAbstractToolDescription(treeItemDragTool);
            }
            if (result == null) {
                result = caseToolEntry(treeItemDragTool);
            }
            if (result == null) {
                result = caseDocumentedElement(treeItemDragTool);
            }
            if (result == null) {
                result = caseIdentifiedElement(treeItemDragTool);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL: {
            TreeItemContainerDropTool treeItemContainerDropTool = (TreeItemContainerDropTool) theEObject;
            T result = caseTreeItemContainerDropTool(treeItemContainerDropTool);
            if (result == null) {
                result = caseMappingBasedToolDescription(treeItemContainerDropTool);
            }
            if (result == null) {
                result = caseTreeItemTool(treeItemContainerDropTool);
            }
            if (result == null) {
                result = caseAbstractToolDescription(treeItemContainerDropTool);
            }
            if (result == null) {
                result = caseToolEntry(treeItemContainerDropTool);
            }
            if (result == null) {
                result = caseDocumentedElement(treeItemContainerDropTool);
            }
            if (result == null) {
                result = caseIdentifiedElement(treeItemContainerDropTool);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.TREE_ITEM_CREATION_TOOL: {
            TreeItemCreationTool treeItemCreationTool = (TreeItemCreationTool) theEObject;
            T result = caseTreeItemCreationTool(treeItemCreationTool);
            if (result == null) {
                result = caseTreeItemTool(treeItemCreationTool);
            }
            if (result == null) {
                result = caseMappingBasedToolDescription(treeItemCreationTool);
            }
            if (result == null) {
                result = caseAbstractToolDescription(treeItemCreationTool);
            }
            if (result == null) {
                result = caseToolEntry(treeItemCreationTool);
            }
            if (result == null) {
                result = caseDocumentedElement(treeItemCreationTool);
            }
            if (result == null) {
                result = caseIdentifiedElement(treeItemCreationTool);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.TREE_ITEM_EDITION_TOOL: {
            TreeItemEditionTool treeItemEditionTool = (TreeItemEditionTool) theEObject;
            T result = caseTreeItemEditionTool(treeItemEditionTool);
            if (result == null) {
                result = caseTreeItemTool(treeItemEditionTool);
            }
            if (result == null) {
                result = caseAbstractToolDescription(treeItemEditionTool);
            }
            if (result == null) {
                result = caseToolEntry(treeItemEditionTool);
            }
            if (result == null) {
                result = caseDocumentedElement(treeItemEditionTool);
            }
            if (result == null) {
                result = caseIdentifiedElement(treeItemEditionTool);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.TREE_ITEM_DELETION_TOOL: {
            TreeItemDeletionTool treeItemDeletionTool = (TreeItemDeletionTool) theEObject;
            T result = caseTreeItemDeletionTool(treeItemDeletionTool);
            if (result == null) {
                result = caseTreeItemTool(treeItemDeletionTool);
            }
            if (result == null) {
                result = caseAbstractToolDescription(treeItemDeletionTool);
            }
            if (result == null) {
                result = caseToolEntry(treeItemDeletionTool);
            }
            if (result == null) {
                result = caseDocumentedElement(treeItemDeletionTool);
            }
            if (result == null) {
                result = caseIdentifiedElement(treeItemDeletionTool);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.TREE_CREATION_DESCRIPTION: {
            TreeCreationDescription treeCreationDescription = (TreeCreationDescription) theEObject;
            T result = caseTreeCreationDescription(treeCreationDescription);
            if (result == null) {
                result = caseRepresentationCreationDescription(treeCreationDescription);
            }
            if (result == null) {
                result = caseAbstractToolDescription(treeCreationDescription);
            }
            if (result == null) {
                result = caseToolEntry(treeCreationDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(treeCreationDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(treeCreationDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.TREE_NAVIGATION_DESCRIPTION: {
            TreeNavigationDescription treeNavigationDescription = (TreeNavigationDescription) theEObject;
            T result = caseTreeNavigationDescription(treeNavigationDescription);
            if (result == null) {
                result = caseRepresentationNavigationDescription(treeNavigationDescription);
            }
            if (result == null) {
                result = caseAbstractToolDescription(treeNavigationDescription);
            }
            if (result == null) {
                result = caseToolEntry(treeNavigationDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(treeNavigationDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(treeNavigationDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.TREE_MAPPING: {
            TreeMapping treeMapping = (TreeMapping) theEObject;
            T result = caseTreeMapping(treeMapping);
            if (result == null) {
                result = caseRepresentationElementMapping(treeMapping);
            }
            if (result == null) {
                result = caseIdentifiedElement(treeMapping);
            }
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
        case DescriptionPackage.TREE_VARIABLE: {
            TreeVariable treeVariable = (TreeVariable) theEObject;
            T result = caseTreeVariable(treeVariable);
            if (result == null) {
                result = caseAbstractVariable(treeVariable);
            }
            if (result == null) {
                result = caseVariableContainer(treeVariable);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.TREE_ITEM_UPDATER: {
            TreeItemUpdater treeItemUpdater = (TreeItemUpdater) theEObject;
            T result = caseTreeItemUpdater(treeItemUpdater);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.PRECEDING_SIBLINGS_VARIABLES: {
            PrecedingSiblingsVariables precedingSiblingsVariables = (PrecedingSiblingsVariables) theEObject;
            T result = casePrecedingSiblingsVariables(precedingSiblingsVariables);
            if (result == null) {
                result = caseTreeVariable(precedingSiblingsVariables);
            }
            if (result == null) {
                result = caseAbstractVariable(precedingSiblingsVariables);
            }
            if (result == null) {
                result = caseVariableContainer(precedingSiblingsVariables);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.TREE_ITEM_MAPPING_CONTAINER: {
            TreeItemMappingContainer treeItemMappingContainer = (TreeItemMappingContainer) theEObject;
            T result = caseTreeItemMappingContainer(treeItemMappingContainer);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.TREE_POPUP_MENU: {
            TreePopupMenu treePopupMenu = (TreePopupMenu) theEObject;
            T result = caseTreePopupMenu(treePopupMenu);
            if (result == null) {
                result = caseAbstractToolDescription(treePopupMenu);
            }
            if (result == null) {
                result = caseToolEntry(treePopupMenu);
            }
            if (result == null) {
                result = caseDocumentedElement(treePopupMenu);
            }
            if (result == null) {
                result = caseIdentifiedElement(treePopupMenu);
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
     * Returns the result of interpreting the object as an instance of '<em>Tree Description</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Tree Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTreeDescription(TreeDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Tree Item Mapping</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Tree Item Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTreeItemMapping(TreeItemMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Tree Item Style Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Tree Item Style Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTreeItemStyleDescription(TreeItemStyleDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Conditional Tree Item Style
     * Description</em>'. <!-- begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Conditional Tree Item Style
     *         Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseConditionalTreeItemStyleDescription(ConditionalTreeItemStyleDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Tree Item Tool</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Tree Item Tool</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTreeItemTool(TreeItemTool object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Tree Item Drag Tool</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Tree Item Drag Tool</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTreeItemDragTool(TreeItemDragTool object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Tree Item Container Drop Tool</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Tree Item Container Drop Tool</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTreeItemContainerDropTool(TreeItemContainerDropTool object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Tree Item Creation Tool</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Tree Item Creation Tool</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTreeItemCreationTool(TreeItemCreationTool object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Tree Item Edition Tool</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Tree Item Edition Tool</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTreeItemEditionTool(TreeItemEditionTool object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Tree Item Deletion Tool</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Tree Item Deletion Tool</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTreeItemDeletionTool(TreeItemDeletionTool object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Tree Creation Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Tree Creation Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTreeCreationDescription(TreeCreationDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Tree Navigation Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Tree Navigation Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTreeNavigationDescription(TreeNavigationDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Tree Mapping</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Tree Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTreeMapping(TreeMapping object) {
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
     * Returns the result of interpreting the object as an instance of '<em>Tree Variable</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Tree Variable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTreeVariable(TreeVariable object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Tree Item Updater</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Tree Item Updater</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTreeItemUpdater(TreeItemUpdater object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Preceding Siblings Variables</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Preceding Siblings Variables</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T casePrecedingSiblingsVariables(PrecedingSiblingsVariables object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Tree Item Mapping Container</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Tree Item Mapping Container</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTreeItemMappingContainer(TreeItemMappingContainer object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Tree Popup Menu</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Tree Popup Menu</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTreePopupMenu(TreePopupMenu object) {
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
     * Returns the result of interpreting the object as an instance of '<em>Description</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseStyleDescription(StyleDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Basic Label Style Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Basic Label Style Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBasicLabelStyleDescription(BasicLabelStyleDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Label Style Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Label Style Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLabelStyleDescription(LabelStyleDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Conditional Style Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Conditional Style Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseConditionalStyleDescription(ConditionalStyleDescription object) {
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
     * Returns the result of interpreting the object as an instance of '<em>Mapping Based Tool Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Mapping Based Tool Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMappingBasedToolDescription(MappingBasedToolDescription object) {
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
