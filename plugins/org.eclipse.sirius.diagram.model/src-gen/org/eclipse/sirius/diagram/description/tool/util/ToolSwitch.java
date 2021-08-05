/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.diagram.description.tool.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.description.tool.BehaviorTool;
import org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription;
import org.eclipse.sirius.diagram.description.tool.ContainerDropDescription;
import org.eclipse.sirius.diagram.description.tool.CreateEdgeView;
import org.eclipse.sirius.diagram.description.tool.CreateView;
import org.eclipse.sirius.diagram.description.tool.DeleteElementDescription;
import org.eclipse.sirius.diagram.description.tool.DeleteHook;
import org.eclipse.sirius.diagram.description.tool.DeleteHookParameter;
import org.eclipse.sirius.diagram.description.tool.DiagramCreationDescription;
import org.eclipse.sirius.diagram.description.tool.DiagramNavigationDescription;
import org.eclipse.sirius.diagram.description.tool.DirectEditLabel;
import org.eclipse.sirius.diagram.description.tool.DoubleClickDescription;
import org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription;
import org.eclipse.sirius.diagram.description.tool.ElementDoubleClickVariable;
import org.eclipse.sirius.diagram.description.tool.Navigation;
import org.eclipse.sirius.diagram.description.tool.NodeCreationDescription;
import org.eclipse.sirius.diagram.description.tool.NodeCreationVariable;
import org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription;
import org.eclipse.sirius.diagram.description.tool.RequestDescription;
import org.eclipse.sirius.diagram.description.tool.SourceEdgeCreationVariable;
import org.eclipse.sirius.diagram.description.tool.SourceEdgeViewCreationVariable;
import org.eclipse.sirius.diagram.description.tool.TargetEdgeCreationVariable;
import org.eclipse.sirius.diagram.description.tool.TargetEdgeViewCreationVariable;
import org.eclipse.sirius.diagram.description.tool.ToolGroup;
import org.eclipse.sirius.diagram.description.tool.ToolGroupExtension;
import org.eclipse.sirius.diagram.description.tool.ToolPackage;
import org.eclipse.sirius.diagram.description.tool.ToolSection;
import org.eclipse.sirius.viewpoint.description.AbstractVariable;
import org.eclipse.sirius.viewpoint.description.DocumentedElement;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.ContainerModelOperation;
import org.eclipse.sirius.viewpoint.description.tool.MappingBasedToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.ModelOperation;
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
 * @see org.eclipse.sirius.diagram.description.tool.ToolPackage
 * @generated
 */
public class ToolSwitch<T> {
    /**
     * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected static ToolPackage modelPackage;

    /**
     * Creates an instance of the switch. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ToolSwitch() {
        if (ToolSwitch.modelPackage == null) {
            ToolSwitch.modelPackage = ToolPackage.eINSTANCE;
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
        if (theEClass.eContainer() == ToolSwitch.modelPackage) {
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
        case ToolPackage.TOOL_SECTION: {
            ToolSection toolSection = (ToolSection) theEObject;
            T result = caseToolSection(toolSection);
            if (result == null) {
                result = caseDocumentedElement(toolSection);
            }
            if (result == null) {
                result = caseIdentifiedElement(toolSection);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.TOOL_GROUP: {
            ToolGroup toolGroup = (ToolGroup) theEObject;
            T result = caseToolGroup(toolGroup);
            if (result == null) {
                result = caseToolEntry(toolGroup);
            }
            if (result == null) {
                result = caseDocumentedElement(toolGroup);
            }
            if (result == null) {
                result = caseIdentifiedElement(toolGroup);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.TOOL_GROUP_EXTENSION: {
            ToolGroupExtension toolGroupExtension = (ToolGroupExtension) theEObject;
            T result = caseToolGroupExtension(toolGroupExtension);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.NODE_CREATION_DESCRIPTION: {
            NodeCreationDescription nodeCreationDescription = (NodeCreationDescription) theEObject;
            T result = caseNodeCreationDescription(nodeCreationDescription);
            if (result == null) {
                result = caseMappingBasedToolDescription(nodeCreationDescription);
            }
            if (result == null) {
                result = caseAbstractToolDescription(nodeCreationDescription);
            }
            if (result == null) {
                result = caseToolEntry(nodeCreationDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(nodeCreationDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(nodeCreationDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.EDGE_CREATION_DESCRIPTION: {
            EdgeCreationDescription edgeCreationDescription = (EdgeCreationDescription) theEObject;
            T result = caseEdgeCreationDescription(edgeCreationDescription);
            if (result == null) {
                result = caseMappingBasedToolDescription(edgeCreationDescription);
            }
            if (result == null) {
                result = caseAbstractToolDescription(edgeCreationDescription);
            }
            if (result == null) {
                result = caseToolEntry(edgeCreationDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(edgeCreationDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(edgeCreationDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.CONTAINER_CREATION_DESCRIPTION: {
            ContainerCreationDescription containerCreationDescription = (ContainerCreationDescription) theEObject;
            T result = caseContainerCreationDescription(containerCreationDescription);
            if (result == null) {
                result = caseMappingBasedToolDescription(containerCreationDescription);
            }
            if (result == null) {
                result = caseAbstractToolDescription(containerCreationDescription);
            }
            if (result == null) {
                result = caseToolEntry(containerCreationDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(containerCreationDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(containerCreationDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.DELETE_ELEMENT_DESCRIPTION: {
            DeleteElementDescription deleteElementDescription = (DeleteElementDescription) theEObject;
            T result = caseDeleteElementDescription(deleteElementDescription);
            if (result == null) {
                result = caseMappingBasedToolDescription(deleteElementDescription);
            }
            if (result == null) {
                result = caseAbstractToolDescription(deleteElementDescription);
            }
            if (result == null) {
                result = caseToolEntry(deleteElementDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(deleteElementDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(deleteElementDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.DOUBLE_CLICK_DESCRIPTION: {
            DoubleClickDescription doubleClickDescription = (DoubleClickDescription) theEObject;
            T result = caseDoubleClickDescription(doubleClickDescription);
            if (result == null) {
                result = caseMappingBasedToolDescription(doubleClickDescription);
            }
            if (result == null) {
                result = caseAbstractToolDescription(doubleClickDescription);
            }
            if (result == null) {
                result = caseToolEntry(doubleClickDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(doubleClickDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(doubleClickDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.DELETE_HOOK: {
            DeleteHook deleteHook = (DeleteHook) theEObject;
            T result = caseDeleteHook(deleteHook);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.DELETE_HOOK_PARAMETER: {
            DeleteHookParameter deleteHookParameter = (DeleteHookParameter) theEObject;
            T result = caseDeleteHookParameter(deleteHookParameter);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.RECONNECT_EDGE_DESCRIPTION: {
            ReconnectEdgeDescription reconnectEdgeDescription = (ReconnectEdgeDescription) theEObject;
            T result = caseReconnectEdgeDescription(reconnectEdgeDescription);
            if (result == null) {
                result = caseMappingBasedToolDescription(reconnectEdgeDescription);
            }
            if (result == null) {
                result = caseAbstractToolDescription(reconnectEdgeDescription);
            }
            if (result == null) {
                result = caseToolEntry(reconnectEdgeDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(reconnectEdgeDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(reconnectEdgeDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.REQUEST_DESCRIPTION: {
            RequestDescription requestDescription = (RequestDescription) theEObject;
            T result = caseRequestDescription(requestDescription);
            if (result == null) {
                result = caseAbstractToolDescription(requestDescription);
            }
            if (result == null) {
                result = caseToolEntry(requestDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(requestDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(requestDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.DIRECT_EDIT_LABEL: {
            DirectEditLabel directEditLabel = (DirectEditLabel) theEObject;
            T result = caseDirectEditLabel(directEditLabel);
            if (result == null) {
                result = caseMappingBasedToolDescription(directEditLabel);
            }
            if (result == null) {
                result = caseAbstractToolDescription(directEditLabel);
            }
            if (result == null) {
                result = caseToolEntry(directEditLabel);
            }
            if (result == null) {
                result = caseDocumentedElement(directEditLabel);
            }
            if (result == null) {
                result = caseIdentifiedElement(directEditLabel);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.BEHAVIOR_TOOL: {
            BehaviorTool behaviorTool = (BehaviorTool) theEObject;
            T result = caseBehaviorTool(behaviorTool);
            if (result == null) {
                result = caseAbstractToolDescription(behaviorTool);
            }
            if (result == null) {
                result = caseToolEntry(behaviorTool);
            }
            if (result == null) {
                result = caseDocumentedElement(behaviorTool);
            }
            if (result == null) {
                result = caseIdentifiedElement(behaviorTool);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.SOURCE_EDGE_CREATION_VARIABLE: {
            SourceEdgeCreationVariable sourceEdgeCreationVariable = (SourceEdgeCreationVariable) theEObject;
            T result = caseSourceEdgeCreationVariable(sourceEdgeCreationVariable);
            if (result == null) {
                result = caseAbstractVariable(sourceEdgeCreationVariable);
            }
            if (result == null) {
                result = caseVariableContainer(sourceEdgeCreationVariable);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.SOURCE_EDGE_VIEW_CREATION_VARIABLE: {
            SourceEdgeViewCreationVariable sourceEdgeViewCreationVariable = (SourceEdgeViewCreationVariable) theEObject;
            T result = caseSourceEdgeViewCreationVariable(sourceEdgeViewCreationVariable);
            if (result == null) {
                result = caseAbstractVariable(sourceEdgeViewCreationVariable);
            }
            if (result == null) {
                result = caseVariableContainer(sourceEdgeViewCreationVariable);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.TARGET_EDGE_CREATION_VARIABLE: {
            TargetEdgeCreationVariable targetEdgeCreationVariable = (TargetEdgeCreationVariable) theEObject;
            T result = caseTargetEdgeCreationVariable(targetEdgeCreationVariable);
            if (result == null) {
                result = caseAbstractVariable(targetEdgeCreationVariable);
            }
            if (result == null) {
                result = caseVariableContainer(targetEdgeCreationVariable);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.TARGET_EDGE_VIEW_CREATION_VARIABLE: {
            TargetEdgeViewCreationVariable targetEdgeViewCreationVariable = (TargetEdgeViewCreationVariable) theEObject;
            T result = caseTargetEdgeViewCreationVariable(targetEdgeViewCreationVariable);
            if (result == null) {
                result = caseAbstractVariable(targetEdgeViewCreationVariable);
            }
            if (result == null) {
                result = caseVariableContainer(targetEdgeViewCreationVariable);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.ELEMENT_DOUBLE_CLICK_VARIABLE: {
            ElementDoubleClickVariable elementDoubleClickVariable = (ElementDoubleClickVariable) theEObject;
            T result = caseElementDoubleClickVariable(elementDoubleClickVariable);
            if (result == null) {
                result = caseAbstractVariable(elementDoubleClickVariable);
            }
            if (result == null) {
                result = caseVariableContainer(elementDoubleClickVariable);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.NODE_CREATION_VARIABLE: {
            NodeCreationVariable nodeCreationVariable = (NodeCreationVariable) theEObject;
            T result = caseNodeCreationVariable(nodeCreationVariable);
            if (result == null) {
                result = caseAbstractVariable(nodeCreationVariable);
            }
            if (result == null) {
                result = caseVariableContainer(nodeCreationVariable);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.CREATE_VIEW: {
            CreateView createView = (CreateView) theEObject;
            T result = caseCreateView(createView);
            if (result == null) {
                result = caseContainerModelOperation(createView);
            }
            if (result == null) {
                result = caseModelOperation(createView);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.CREATE_EDGE_VIEW: {
            CreateEdgeView createEdgeView = (CreateEdgeView) theEObject;
            T result = caseCreateEdgeView(createEdgeView);
            if (result == null) {
                result = caseCreateView(createEdgeView);
            }
            if (result == null) {
                result = caseContainerModelOperation(createEdgeView);
            }
            if (result == null) {
                result = caseModelOperation(createEdgeView);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.NAVIGATION: {
            Navigation navigation = (Navigation) theEObject;
            T result = caseNavigation(navigation);
            if (result == null) {
                result = caseContainerModelOperation(navigation);
            }
            if (result == null) {
                result = caseModelOperation(navigation);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.DIAGRAM_CREATION_DESCRIPTION: {
            DiagramCreationDescription diagramCreationDescription = (DiagramCreationDescription) theEObject;
            T result = caseDiagramCreationDescription(diagramCreationDescription);
            if (result == null) {
                result = caseRepresentationCreationDescription(diagramCreationDescription);
            }
            if (result == null) {
                result = caseAbstractToolDescription(diagramCreationDescription);
            }
            if (result == null) {
                result = caseToolEntry(diagramCreationDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(diagramCreationDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(diagramCreationDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.DIAGRAM_NAVIGATION_DESCRIPTION: {
            DiagramNavigationDescription diagramNavigationDescription = (DiagramNavigationDescription) theEObject;
            T result = caseDiagramNavigationDescription(diagramNavigationDescription);
            if (result == null) {
                result = caseRepresentationNavigationDescription(diagramNavigationDescription);
            }
            if (result == null) {
                result = caseAbstractToolDescription(diagramNavigationDescription);
            }
            if (result == null) {
                result = caseToolEntry(diagramNavigationDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(diagramNavigationDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(diagramNavigationDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.CONTAINER_DROP_DESCRIPTION: {
            ContainerDropDescription containerDropDescription = (ContainerDropDescription) theEObject;
            T result = caseContainerDropDescription(containerDropDescription);
            if (result == null) {
                result = caseMappingBasedToolDescription(containerDropDescription);
            }
            if (result == null) {
                result = caseAbstractToolDescription(containerDropDescription);
            }
            if (result == null) {
                result = caseToolEntry(containerDropDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(containerDropDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(containerDropDescription);
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
     * Returns the result of interpreting the object as an instance of '<em>Section</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Section</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseToolSection(ToolSection object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Group</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Group</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseToolGroup(ToolGroup object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Group Extension</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Group Extension</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseToolGroupExtension(ToolGroupExtension object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Node Creation Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Node Creation Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNodeCreationDescription(NodeCreationDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Edge Creation Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Edge Creation Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEdgeCreationDescription(EdgeCreationDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Container Creation Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Container Creation Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseContainerCreationDescription(ContainerCreationDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Delete Element Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Delete Element Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDeleteElementDescription(DeleteElementDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Double Click Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Double Click Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDoubleClickDescription(DoubleClickDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Delete Hook</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Delete Hook</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDeleteHook(DeleteHook object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Delete Hook Parameter</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Delete Hook Parameter</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDeleteHookParameter(DeleteHookParameter object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Reconnect Edge Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Reconnect Edge Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseReconnectEdgeDescription(ReconnectEdgeDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Request Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Request Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRequestDescription(RequestDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Direct Edit Label</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Direct Edit Label</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDirectEditLabel(DirectEditLabel object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Behavior Tool</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Behavior Tool</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBehaviorTool(BehaviorTool object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Source Edge Creation Variable</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Source Edge Creation Variable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSourceEdgeCreationVariable(SourceEdgeCreationVariable object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Source Edge View Creation Variable</em>'.
     * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Source Edge View Creation Variable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSourceEdgeViewCreationVariable(SourceEdgeViewCreationVariable object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Target Edge Creation Variable</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Target Edge Creation Variable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTargetEdgeCreationVariable(TargetEdgeCreationVariable object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Target Edge View Creation Variable</em>'.
     * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Target Edge View Creation Variable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTargetEdgeViewCreationVariable(TargetEdgeViewCreationVariable object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Element Double Click Variable</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Element Double Click Variable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseElementDoubleClickVariable(ElementDoubleClickVariable object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Node Creation Variable</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Node Creation Variable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNodeCreationVariable(NodeCreationVariable object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Create View</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Create View</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCreateView(CreateView object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Create Edge View</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Create Edge View</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCreateEdgeView(CreateEdgeView object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Navigation</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Navigation</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNavigation(Navigation object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Diagram Creation Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Diagram Creation Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDiagramCreationDescription(DiagramCreationDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Diagram Navigation Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Diagram Navigation Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDiagramNavigationDescription(DiagramNavigationDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Container Drop Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Container Drop Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseContainerDropDescription(ContainerDropDescription object) {
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
     * Returns the result of interpreting the object as an instance of '<em>Model Operation</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Model Operation</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseModelOperation(ModelOperation object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Container Model Operation</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Container Model Operation</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseContainerModelOperation(ContainerModelOperation object) {
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

} // ToolSwitch
