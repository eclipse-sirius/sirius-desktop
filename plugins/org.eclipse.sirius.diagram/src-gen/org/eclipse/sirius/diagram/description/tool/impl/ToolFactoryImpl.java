/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.diagram.description.tool.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.tool.spec.ContainerDropDescriptionSpec;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.tool.spec.DeleteElementDescriptionSpec;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.tool.spec.DiagramCreationDescriptionSpec;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.tool.spec.DiagramNavigationDescriptionSpec;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.tool.spec.DirectEditLabelSpec;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.tool.spec.EdgeCreationDescriptionSpec;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.tool.spec.ReconnectEdgeDescriptionSpec;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.tool.spec.ToolSectionSpec;
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
import org.eclipse.sirius.diagram.description.tool.ReconnectionKind;
import org.eclipse.sirius.diagram.description.tool.RequestDescription;
import org.eclipse.sirius.diagram.description.tool.SourceEdgeCreationVariable;
import org.eclipse.sirius.diagram.description.tool.SourceEdgeViewCreationVariable;
import org.eclipse.sirius.diagram.description.tool.TargetEdgeCreationVariable;
import org.eclipse.sirius.diagram.description.tool.TargetEdgeViewCreationVariable;
import org.eclipse.sirius.diagram.description.tool.ToolFactory;
import org.eclipse.sirius.diagram.description.tool.ToolGroup;
import org.eclipse.sirius.diagram.description.tool.ToolGroupExtension;
import org.eclipse.sirius.diagram.description.tool.ToolPackage;
import org.eclipse.sirius.diagram.description.tool.ToolSection;
import org.eclipse.sirius.viewpoint.description.tool.ContainerViewVariable;
import org.eclipse.sirius.viewpoint.description.tool.DropContainerVariable;
import org.eclipse.sirius.viewpoint.description.tool.EditMaskVariables;
import org.eclipse.sirius.viewpoint.description.tool.ElementDeleteVariable;
import org.eclipse.sirius.viewpoint.description.tool.ElementDropVariable;
import org.eclipse.sirius.viewpoint.description.tool.ElementSelectVariable;
import org.eclipse.sirius.viewpoint.description.tool.InitEdgeCreationOperation;
import org.eclipse.sirius.viewpoint.description.tool.InitialContainerDropOperation;
import org.eclipse.sirius.viewpoint.description.tool.InitialNodeCreationOperation;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;
import org.eclipse.sirius.viewpoint.description.tool.NameVariable;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 *
 * @generated
 */
public class ToolFactoryImpl extends EFactoryImpl implements ToolFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    public static ToolFactory init() {
        try {
            ToolFactory theToolFactory = (ToolFactory) EPackage.Registry.INSTANCE.getEFactory(ToolPackage.eNS_URI);
            if (theToolFactory != null) {
                return theToolFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new ToolFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    public ToolFactoryImpl() {
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
        case ToolPackage.TOOL_SECTION:
            return createToolSection();
        case ToolPackage.TOOL_GROUP:
            return createToolGroup();
        case ToolPackage.TOOL_GROUP_EXTENSION:
            return createToolGroupExtension();
        case ToolPackage.NODE_CREATION_DESCRIPTION:
            return createNodeCreationDescription();
        case ToolPackage.EDGE_CREATION_DESCRIPTION:
            return createEdgeCreationDescription();
        case ToolPackage.CONTAINER_CREATION_DESCRIPTION:
            return createContainerCreationDescription();
        case ToolPackage.DELETE_ELEMENT_DESCRIPTION:
            return createDeleteElementDescription();
        case ToolPackage.DOUBLE_CLICK_DESCRIPTION:
            return createDoubleClickDescription();
        case ToolPackage.DELETE_HOOK:
            return createDeleteHook();
        case ToolPackage.DELETE_HOOK_PARAMETER:
            return createDeleteHookParameter();
        case ToolPackage.RECONNECT_EDGE_DESCRIPTION:
            return createReconnectEdgeDescription();
        case ToolPackage.REQUEST_DESCRIPTION:
            return createRequestDescription();
        case ToolPackage.DIRECT_EDIT_LABEL:
            return createDirectEditLabel();
        case ToolPackage.BEHAVIOR_TOOL:
            return createBehaviorTool();
        case ToolPackage.SOURCE_EDGE_CREATION_VARIABLE:
            return createSourceEdgeCreationVariable();
        case ToolPackage.SOURCE_EDGE_VIEW_CREATION_VARIABLE:
            return createSourceEdgeViewCreationVariable();
        case ToolPackage.TARGET_EDGE_CREATION_VARIABLE:
            return createTargetEdgeCreationVariable();
        case ToolPackage.TARGET_EDGE_VIEW_CREATION_VARIABLE:
            return createTargetEdgeViewCreationVariable();
        case ToolPackage.ELEMENT_DOUBLE_CLICK_VARIABLE:
            return createElementDoubleClickVariable();
        case ToolPackage.NODE_CREATION_VARIABLE:
            return createNodeCreationVariable();
        case ToolPackage.CREATE_VIEW:
            return createCreateView();
        case ToolPackage.CREATE_EDGE_VIEW:
            return createCreateEdgeView();
        case ToolPackage.NAVIGATION:
            return createNavigation();
        case ToolPackage.DIAGRAM_CREATION_DESCRIPTION:
            return createDiagramCreationDescription();
        case ToolPackage.DIAGRAM_NAVIGATION_DESCRIPTION:
            return createDiagramNavigationDescription();
        case ToolPackage.CONTAINER_DROP_DESCRIPTION:
            return createContainerDropDescription();
        default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object createFromString(EDataType eDataType, String initialValue) {
        switch (eDataType.getClassifierID()) {
        case ToolPackage.RECONNECTION_KIND:
            return createReconnectionKindFromString(eDataType, initialValue);
        default:
            throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String convertToString(EDataType eDataType, Object instanceValue) {
        switch (eDataType.getClassifierID()) {
        case ToolPackage.RECONNECTION_KIND:
            return convertReconnectionKindToString(eDataType, instanceValue);
        default:
            throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public ToolSection createToolSection() {
        ToolSectionImpl toolSection = new ToolSectionSpec();
        return toolSection;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ToolGroup createToolGroup() {
        ToolGroupImpl toolGroup = new ToolGroupImpl();
        return toolGroup;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ToolGroupExtension createToolGroupExtension() {
        ToolGroupExtensionImpl toolGroupExtension = new ToolGroupExtensionImpl();
        return toolGroupExtension;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public NodeCreationDescription createNodeCreationDescription() {
        NodeCreationDescriptionImpl nodeCreationDescription = new NodeCreationDescriptionImpl();
        NodeCreationVariable defaultVariable = createNodeCreationVariable();
        defaultVariable.setName("container"); //$NON-NLS-1$
        ContainerViewVariable containerViewVariable = org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createContainerViewVariable();
        containerViewVariable.setName("containerView"); //$NON-NLS-1$
        nodeCreationDescription.setVariable(defaultVariable);
        nodeCreationDescription.setViewVariable(containerViewVariable);
        InitialNodeCreationOperation init = org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createInitialNodeCreationOperation();
        nodeCreationDescription.setInitialOperation(init);
        return nodeCreationDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public EdgeCreationDescription createEdgeCreationDescription() {
        EdgeCreationDescriptionImpl edgeCreationDescription = new EdgeCreationDescriptionSpec();

        SourceEdgeCreationVariable sourceVariable = createSourceEdgeCreationVariable();
        sourceVariable.setName("source"); //$NON-NLS-1$

        TargetEdgeCreationVariable targetVariable = createTargetEdgeCreationVariable();
        targetVariable.setName("target"); //$NON-NLS-1$

        SourceEdgeViewCreationVariable sourceEdgeViewVariable = createSourceEdgeViewCreationVariable();
        sourceEdgeViewVariable.setName("sourceView"); //$NON-NLS-1$

        TargetEdgeViewCreationVariable targetEdgeViewVariable = createTargetEdgeViewCreationVariable();
        targetEdgeViewVariable.setName("targetView"); //$NON-NLS-1$

        edgeCreationDescription.setSourceVariable(sourceVariable);
        edgeCreationDescription.setTargetVariable(targetVariable);
        edgeCreationDescription.setSourceViewVariable(sourceEdgeViewVariable);
        edgeCreationDescription.setTargetViewVariable(targetEdgeViewVariable);

        InitEdgeCreationOperation init = org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createInitEdgeCreationOperation();
        edgeCreationDescription.setInitialOperation(init);

        return edgeCreationDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public ContainerCreationDescription createContainerCreationDescription() {
        ContainerCreationDescriptionImpl containerCreationDescription = new ContainerCreationDescriptionImpl();
        NodeCreationVariable defaultVariable = createNodeCreationVariable();
        defaultVariable.setName("container"); //$NON-NLS-1$
        ContainerViewVariable containerViewVariable = org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createContainerViewVariable();
        containerViewVariable.setName("containerView"); //$NON-NLS-1$
        containerCreationDescription.setVariable(defaultVariable);
        containerCreationDescription.setViewVariable(containerViewVariable);
        InitialNodeCreationOperation init = org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createInitialNodeCreationOperation();
        containerCreationDescription.setInitialOperation(init);
        return containerCreationDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public DeleteElementDescription createDeleteElementDescription() {
        DeleteElementDescriptionImpl deleteElementDescription = new DeleteElementDescriptionSpec();
        ElementDeleteVariable elementDeleteVariable = org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createElementDeleteVariable();
        elementDeleteVariable.setName("element"); //$NON-NLS-1$
        deleteElementDescription.setElement(elementDeleteVariable);
        ContainerViewVariable containerViewVariable = org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createContainerViewVariable();
        containerViewVariable.setName("containerView"); //$NON-NLS-1$
        deleteElementDescription.setContainerView(containerViewVariable);
        ElementDeleteVariable elementDeleteVariable2 = org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createElementDeleteVariable();
        elementDeleteVariable2.setName("elementView"); //$NON-NLS-1$
        deleteElementDescription.setElementView(elementDeleteVariable2);
        InitialOperation init = org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createInitialOperation();
        deleteElementDescription.setInitialOperation(init);
        return deleteElementDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public DoubleClickDescription createDoubleClickDescription() {
        DoubleClickDescriptionImpl doubleClickDescription = new DoubleClickDescriptionImpl();
        ElementDoubleClickVariable elementDoubleClickVariable = createElementDoubleClickVariable();
        elementDoubleClickVariable.setName("element"); //$NON-NLS-1$
        doubleClickDescription.setElement(elementDoubleClickVariable);
        ElementDoubleClickVariable elementViewDoubleClickVariable = createElementDoubleClickVariable();
        elementViewDoubleClickVariable.setName("elementView"); //$NON-NLS-1$
        doubleClickDescription.setElementView(elementViewDoubleClickVariable);
        InitialOperation init = org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createInitialOperation();
        doubleClickDescription.setInitialOperation(init);
        return doubleClickDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public DeleteHook createDeleteHook() {
        DeleteHookImpl deleteHook = new DeleteHookImpl();
        return deleteHook;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public DeleteHookParameter createDeleteHookParameter() {
        DeleteHookParameterImpl deleteHookParameter = new DeleteHookParameterImpl();
        return deleteHookParameter;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public ReconnectEdgeDescription createReconnectEdgeDescription() {
        ReconnectEdgeDescriptionImpl reconnectEdgeDescription = new ReconnectEdgeDescriptionSpec();
        ElementSelectVariable elementSelectVariable = org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createElementSelectVariable();
        elementSelectVariable.setName("element"); //$NON-NLS-1$
        reconnectEdgeDescription.setElement(elementSelectVariable);
        SourceEdgeCreationVariable sourceEdgeCreationVariable = createSourceEdgeCreationVariable();
        sourceEdgeCreationVariable.setName("source"); //$NON-NLS-1$
        reconnectEdgeDescription.setSource(sourceEdgeCreationVariable);
        SourceEdgeViewCreationVariable sourceEdgeViewCreationVariable = createSourceEdgeViewCreationVariable();
        sourceEdgeViewCreationVariable.setName("sourceView"); //$NON-NLS-1$
        reconnectEdgeDescription.setSourceView(sourceEdgeViewCreationVariable);
        TargetEdgeCreationVariable targetEdgeCreationVariable = createTargetEdgeCreationVariable();
        targetEdgeCreationVariable.setName("target"); //$NON-NLS-1$
        reconnectEdgeDescription.setTarget(targetEdgeCreationVariable);
        TargetEdgeViewCreationVariable targetEdgeViewCreationVariable = createTargetEdgeViewCreationVariable();
        targetEdgeViewCreationVariable.setName("targetView"); //$NON-NLS-1$
        reconnectEdgeDescription.setTargetView(targetEdgeViewCreationVariable);
        ElementSelectVariable edgeVariable = org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createElementSelectVariable();
        edgeVariable.setName("edgeView"); //$NON-NLS-1$
        reconnectEdgeDescription.setEdgeView(edgeVariable);
        InitialOperation initialOperation = org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createInitialOperation();
        reconnectEdgeDescription.setInitialOperation(initialOperation);
        return reconnectEdgeDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public RequestDescription createRequestDescription() {
        RequestDescriptionImpl requestDescription = new RequestDescriptionImpl();
        return requestDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public DirectEditLabel createDirectEditLabel() {
        DirectEditLabelImpl directEditLabel = new DirectEditLabelSpec();
        InitialOperation initialOperation = org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createInitialOperation();
        directEditLabel.setInitialOperation(initialOperation);
        EditMaskVariables editMaskVariables = org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createEditMaskVariables();
        directEditLabel.setMask(editMaskVariables);
        return directEditLabel;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public BehaviorTool createBehaviorTool() {
        BehaviorToolImpl behaviorTool = new BehaviorToolImpl();
        InitialOperation init = org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createInitialOperation();
        behaviorTool.setInitialOperation(init);
        return behaviorTool;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public SourceEdgeCreationVariable createSourceEdgeCreationVariable() {
        SourceEdgeCreationVariableImpl sourceEdgeCreationVariable = new SourceEdgeCreationVariableImpl();
        return sourceEdgeCreationVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public SourceEdgeViewCreationVariable createSourceEdgeViewCreationVariable() {
        SourceEdgeViewCreationVariableImpl sourceEdgeViewCreationVariable = new SourceEdgeViewCreationVariableImpl();
        return sourceEdgeViewCreationVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public TargetEdgeCreationVariable createTargetEdgeCreationVariable() {
        TargetEdgeCreationVariableImpl targetEdgeCreationVariable = new TargetEdgeCreationVariableImpl();
        return targetEdgeCreationVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public TargetEdgeViewCreationVariable createTargetEdgeViewCreationVariable() {
        TargetEdgeViewCreationVariableImpl targetEdgeViewCreationVariable = new TargetEdgeViewCreationVariableImpl();
        return targetEdgeViewCreationVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ElementDoubleClickVariable createElementDoubleClickVariable() {
        ElementDoubleClickVariableImpl elementDoubleClickVariable = new ElementDoubleClickVariableImpl();
        return elementDoubleClickVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NodeCreationVariable createNodeCreationVariable() {
        NodeCreationVariableImpl nodeCreationVariable = new NodeCreationVariableImpl();
        return nodeCreationVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public CreateView createCreateView() {
        CreateViewImpl createView = new CreateViewImpl();
        return createView;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public CreateEdgeView createCreateEdgeView() {
        CreateEdgeViewImpl createEdgeView = new CreateEdgeViewImpl();
        return createEdgeView;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Navigation createNavigation() {
        NavigationImpl navigation = new NavigationImpl();
        return navigation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public DiagramCreationDescription createDiagramCreationDescription() {
        DiagramCreationDescriptionImpl diagramCreationDescription = new DiagramCreationDescriptionSpec();
        ContainerViewVariable containerViewVariable = org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createContainerViewVariable();
        containerViewVariable.setName("containerView"); //$NON-NLS-1$
        diagramCreationDescription.setContainerViewVariable(containerViewVariable);
        NameVariable diagramNameVariable = org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createNameVariable();
        diagramNameVariable.setName("diagramName"); //$NON-NLS-1$
        diagramCreationDescription.setRepresentationNameVariable(diagramNameVariable);
        InitialOperation init = org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createInitialOperation();
        diagramCreationDescription.setInitialOperation(init);
        return diagramCreationDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public DiagramNavigationDescription createDiagramNavigationDescription() {
        DiagramNavigationDescriptionImpl diagramNavigationDescription = new DiagramNavigationDescriptionSpec();
        ContainerViewVariable containerViewVariable = org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createContainerViewVariable();
        containerViewVariable.setName("containerView"); //$NON-NLS-1$
        diagramNavigationDescription.setContainerViewVariable(containerViewVariable);
        ElementSelectVariable containerVariable = org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createElementSelectVariable();
        containerVariable.setName("container"); //$NON-NLS-1$
        diagramNavigationDescription.setContainerVariable(containerVariable);
        NameVariable diagramNameVariable = org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createNameVariable();
        diagramNameVariable.setName("diagramName"); //$NON-NLS-1$
        diagramNavigationDescription.setRepresentationNameVariable(diagramNameVariable);
        return diagramNavigationDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public ContainerDropDescription createContainerDropDescription() {
        ContainerDropDescriptionImpl containerDropDescription = new ContainerDropDescriptionSpec();
        DropContainerVariable oldContainerVariable = org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createDropContainerVariable();
        oldContainerVariable.setName("oldSemanticContainer"); //$NON-NLS-1$
        DropContainerVariable newContainerVariable = org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createDropContainerVariable();
        newContainerVariable.setName("newSemanticContainer"); //$NON-NLS-1$
        ElementDropVariable elementDropVariable = org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createElementDropVariable();
        elementDropVariable.setName("element"); //$NON-NLS-1$
        ContainerViewVariable containerViewVariable = org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createContainerViewVariable();
        containerViewVariable.setName("newContainerView"); //$NON-NLS-1$

        containerDropDescription.setElement(elementDropVariable);
        containerDropDescription.setNewContainer(newContainerVariable);
        containerDropDescription.setNewViewContainer(containerViewVariable);
        containerDropDescription.setOldContainer(oldContainerVariable);

        InitialContainerDropOperation init = org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createInitialContainerDropOperation();
        containerDropDescription.setInitialOperation(init);

        return containerDropDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ReconnectionKind createReconnectionKindFromString(EDataType eDataType, String initialValue) {
        ReconnectionKind result = ReconnectionKind.get(initialValue);
        if (result == null) {
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public String convertReconnectionKindToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ToolPackage getToolPackage() {
        return (ToolPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @deprecated
     * @generated
     */
    @Deprecated
    public static ToolPackage getPackage() {
        return ToolPackage.eINSTANCE;
    }

} // ToolFactoryImpl
