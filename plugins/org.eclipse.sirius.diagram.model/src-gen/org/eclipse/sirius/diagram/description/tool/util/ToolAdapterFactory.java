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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
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
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides an adapter <code>createXXX</code>
 * method for each class of the model. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.diagram.description.tool.ToolPackage
 * @generated
 */
public class ToolAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected static ToolPackage modelPackage;

    /**
     * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ToolAdapterFactory() {
        if (ToolAdapterFactory.modelPackage == null) {
            ToolAdapterFactory.modelPackage = ToolPackage.eINSTANCE;
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
        if (object == ToolAdapterFactory.modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject) object).eClass().getEPackage() == ToolAdapterFactory.modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ToolSwitch<Adapter> modelSwitch = new ToolSwitch<Adapter>() {
        @Override
        public Adapter caseToolSection(ToolSection object) {
            return createToolSectionAdapter();
        }

        @Override
        public Adapter caseToolGroup(ToolGroup object) {
            return createToolGroupAdapter();
        }

        @Override
        public Adapter caseToolGroupExtension(ToolGroupExtension object) {
            return createToolGroupExtensionAdapter();
        }

        @Override
        public Adapter caseNodeCreationDescription(NodeCreationDescription object) {
            return createNodeCreationDescriptionAdapter();
        }

        @Override
        public Adapter caseEdgeCreationDescription(EdgeCreationDescription object) {
            return createEdgeCreationDescriptionAdapter();
        }

        @Override
        public Adapter caseContainerCreationDescription(ContainerCreationDescription object) {
            return createContainerCreationDescriptionAdapter();
        }

        @Override
        public Adapter caseDeleteElementDescription(DeleteElementDescription object) {
            return createDeleteElementDescriptionAdapter();
        }

        @Override
        public Adapter caseDoubleClickDescription(DoubleClickDescription object) {
            return createDoubleClickDescriptionAdapter();
        }

        @Override
        public Adapter caseDeleteHook(DeleteHook object) {
            return createDeleteHookAdapter();
        }

        @Override
        public Adapter caseDeleteHookParameter(DeleteHookParameter object) {
            return createDeleteHookParameterAdapter();
        }

        @Override
        public Adapter caseReconnectEdgeDescription(ReconnectEdgeDescription object) {
            return createReconnectEdgeDescriptionAdapter();
        }

        @Override
        public Adapter caseRequestDescription(RequestDescription object) {
            return createRequestDescriptionAdapter();
        }

        @Override
        public Adapter caseDirectEditLabel(DirectEditLabel object) {
            return createDirectEditLabelAdapter();
        }

        @Override
        public Adapter caseBehaviorTool(BehaviorTool object) {
            return createBehaviorToolAdapter();
        }

        @Override
        public Adapter caseSourceEdgeCreationVariable(SourceEdgeCreationVariable object) {
            return createSourceEdgeCreationVariableAdapter();
        }

        @Override
        public Adapter caseSourceEdgeViewCreationVariable(SourceEdgeViewCreationVariable object) {
            return createSourceEdgeViewCreationVariableAdapter();
        }

        @Override
        public Adapter caseTargetEdgeCreationVariable(TargetEdgeCreationVariable object) {
            return createTargetEdgeCreationVariableAdapter();
        }

        @Override
        public Adapter caseTargetEdgeViewCreationVariable(TargetEdgeViewCreationVariable object) {
            return createTargetEdgeViewCreationVariableAdapter();
        }

        @Override
        public Adapter caseElementDoubleClickVariable(ElementDoubleClickVariable object) {
            return createElementDoubleClickVariableAdapter();
        }

        @Override
        public Adapter caseNodeCreationVariable(NodeCreationVariable object) {
            return createNodeCreationVariableAdapter();
        }

        @Override
        public Adapter caseCreateView(CreateView object) {
            return createCreateViewAdapter();
        }

        @Override
        public Adapter caseCreateEdgeView(CreateEdgeView object) {
            return createCreateEdgeViewAdapter();
        }

        @Override
        public Adapter caseNavigation(Navigation object) {
            return createNavigationAdapter();
        }

        @Override
        public Adapter caseDiagramCreationDescription(DiagramCreationDescription object) {
            return createDiagramCreationDescriptionAdapter();
        }

        @Override
        public Adapter caseDiagramNavigationDescription(DiagramNavigationDescription object) {
            return createDiagramNavigationDescriptionAdapter();
        }

        @Override
        public Adapter caseContainerDropDescription(ContainerDropDescription object) {
            return createContainerDropDescriptionAdapter();
        }

        @Override
        public Adapter caseDocumentedElement(DocumentedElement object) {
            return createDocumentedElementAdapter();
        }

        @Override
        public Adapter caseIdentifiedElement(IdentifiedElement object) {
            return createIdentifiedElementAdapter();
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
        public Adapter caseMappingBasedToolDescription(MappingBasedToolDescription object) {
            return createMappingBasedToolDescriptionAdapter();
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
        public Adapter caseModelOperation(ModelOperation object) {
            return createModelOperationAdapter();
        }

        @Override
        public Adapter caseContainerModelOperation(ContainerModelOperation object) {
            return createContainerModelOperationAdapter();
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
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.description.tool.ToolSection
     * <em>Section</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.tool.ToolSection
     * @generated
     */
    public Adapter createToolSectionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.description.tool.ToolGroup
     * <em>Group</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.tool.ToolGroup
     * @generated
     */
    public Adapter createToolGroupAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.diagram.description.tool.ToolGroupExtension <em>Group Extension</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.tool.ToolGroupExtension
     * @generated
     */
    public Adapter createToolGroupExtensionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.description.tool.NodeCreationDescription <em>Node Creation Description</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful
     * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.tool.NodeCreationDescription
     * @generated
     */
    public Adapter createNodeCreationDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription <em>Edge Creation Description</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful
     * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription
     * @generated
     */
    public Adapter createEdgeCreationDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription <em>Container Creation
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription
     * @generated
     */
    public Adapter createContainerCreationDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.description.tool.DeleteElementDescription <em>Delete Element
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.tool.DeleteElementDescription
     * @generated
     */
    public Adapter createDeleteElementDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.description.tool.DoubleClickDescription <em>Double Click Description</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful
     * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.tool.DoubleClickDescription
     * @generated
     */
    public Adapter createDoubleClickDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.description.tool.DeleteHook
     * <em>Delete Hook</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
     * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.tool.DeleteHook
     * @generated
     */
    public Adapter createDeleteHookAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.diagram.description.tool.DeleteHookParameter <em>Delete Hook Parameter</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.tool.DeleteHookParameter
     * @generated
     */
    public Adapter createDeleteHookParameterAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription <em>Reconnect Edge
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription
     * @generated
     */
    public Adapter createReconnectEdgeDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.diagram.description.tool.RequestDescription <em>Request Description</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.tool.RequestDescription
     * @generated
     */
    public Adapter createRequestDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.description.tool.DirectEditLabel
     * <em>Direct Edit Label</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.tool.DirectEditLabel
     * @generated
     */
    public Adapter createDirectEditLabelAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.description.tool.BehaviorTool
     * <em>Behavior Tool</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
     * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.tool.BehaviorTool
     * @generated
     */
    public Adapter createBehaviorToolAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.description.tool.SourceEdgeCreationVariable <em>Source Edge Creation
     * Variable</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.tool.SourceEdgeCreationVariable
     * @generated
     */
    public Adapter createSourceEdgeCreationVariableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.description.tool.SourceEdgeViewCreationVariable <em>Source Edge View Creation
     * Variable</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.tool.SourceEdgeViewCreationVariable
     * @generated
     */
    public Adapter createSourceEdgeViewCreationVariableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.description.tool.TargetEdgeCreationVariable <em>Target Edge Creation
     * Variable</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.tool.TargetEdgeCreationVariable
     * @generated
     */
    public Adapter createTargetEdgeCreationVariableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.description.tool.TargetEdgeViewCreationVariable <em>Target Edge View Creation
     * Variable</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.tool.TargetEdgeViewCreationVariable
     * @generated
     */
    public Adapter createTargetEdgeViewCreationVariableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.description.tool.ElementDoubleClickVariable <em>Element Double Click
     * Variable</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.tool.ElementDoubleClickVariable
     * @generated
     */
    public Adapter createElementDoubleClickVariableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.diagram.description.tool.NodeCreationVariable <em>Node Creation Variable</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.tool.NodeCreationVariable
     * @generated
     */
    public Adapter createNodeCreationVariableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.description.tool.CreateView
     * <em>Create View</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
     * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.tool.CreateView
     * @generated
     */
    public Adapter createCreateViewAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.description.tool.CreateEdgeView
     * <em>Create Edge View</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.tool.CreateEdgeView
     * @generated
     */
    public Adapter createCreateEdgeViewAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.description.tool.Navigation
     * <em>Navigation</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
     * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.tool.Navigation
     * @generated
     */
    public Adapter createNavigationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.description.tool.DiagramCreationDescription <em>Diagram Creation
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.tool.DiagramCreationDescription
     * @generated
     */
    public Adapter createDiagramCreationDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.description.tool.DiagramNavigationDescription <em>Diagram Navigation
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.tool.DiagramNavigationDescription
     * @generated
     */
    public Adapter createDiagramNavigationDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.description.tool.ContainerDropDescription <em>Container Drop
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.tool.ContainerDropDescription
     * @generated
     */
    public Adapter createContainerDropDescriptionAdapter() {
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
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.viewpoint.description.tool.MappingBasedToolDescription <em>Mapping Based Tool
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.MappingBasedToolDescription
     * @generated
     */
    public Adapter createMappingBasedToolDescriptionAdapter() {
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
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.description.tool.ModelOperation
     * <em>Model Operation</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.ModelOperation
     * @generated
     */
    public Adapter createModelOperationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.viewpoint.description.tool.ContainerModelOperation <em>Container Model
     * Operation</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.ContainerModelOperation
     * @generated
     */
    public Adapter createContainerModelOperationAdapter() {
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

} // ToolAdapterFactory
