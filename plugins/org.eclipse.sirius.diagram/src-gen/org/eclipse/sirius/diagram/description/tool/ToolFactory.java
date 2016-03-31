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
package org.eclipse.sirius.diagram.description.tool;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a
 * create method for each non-abstract class of the model. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.diagram.description.tool.ToolPackage
 * @generated
 */
public interface ToolFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    ToolFactory eINSTANCE = org.eclipse.sirius.diagram.description.tool.impl.ToolFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Section</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Section</em>'.
     * @generated
     */
    ToolSection createToolSection();

    /**
     * Returns a new object of class '<em>Group</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Group</em>'.
     * @generated
     */
    ToolGroup createToolGroup();

    /**
     * Returns a new object of class '<em>Group Extension</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Group Extension</em>'.
     * @generated
     */
    ToolGroupExtension createToolGroupExtension();

    /**
     * Returns a new object of class '<em>Node Creation Description</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Node Creation Description</em>'.
     * @generated
     */
    NodeCreationDescription createNodeCreationDescription();

    /**
     * Returns a new object of class '<em>Edge Creation Description</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Edge Creation Description</em>'.
     * @generated
     */
    EdgeCreationDescription createEdgeCreationDescription();

    /**
     * Returns a new object of class '<em>Container Creation Description</em>'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Container Creation Description</em>'.
     * @generated
     */
    ContainerCreationDescription createContainerCreationDescription();

    /**
     * Returns a new object of class '<em>Delete Element Description</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Delete Element Description</em>'.
     * @generated
     */
    DeleteElementDescription createDeleteElementDescription();

    /**
     * Returns a new object of class '<em>Double Click Description</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Double Click Description</em>'.
     * @generated
     */
    DoubleClickDescription createDoubleClickDescription();

    /**
     * Returns a new object of class '<em>Delete Hook</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Delete Hook</em>'.
     * @generated
     */
    DeleteHook createDeleteHook();

    /**
     * Returns a new object of class '<em>Delete Hook Parameter</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Delete Hook Parameter</em>'.
     * @generated
     */
    DeleteHookParameter createDeleteHookParameter();

    /**
     * Returns a new object of class '<em>Reconnect Edge Description</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Reconnect Edge Description</em>'.
     * @generated
     */
    ReconnectEdgeDescription createReconnectEdgeDescription();

    /**
     * Returns a new object of class '<em>Request Description</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Request Description</em>'.
     * @generated
     */
    RequestDescription createRequestDescription();

    /**
     * Returns a new object of class '<em>Direct Edit Label</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Direct Edit Label</em>'.
     * @generated
     */
    DirectEditLabel createDirectEditLabel();

    /**
     * Returns a new object of class '<em>Behavior Tool</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Behavior Tool</em>'.
     * @generated
     */
    BehaviorTool createBehaviorTool();

    /**
     * Returns a new object of class '<em>Source Edge Creation Variable</em>'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Source Edge Creation Variable</em>'.
     * @generated
     */
    SourceEdgeCreationVariable createSourceEdgeCreationVariable();

    /**
     * Returns a new object of class '
     * <em>Source Edge View Creation Variable</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return a new object of class '
     *         <em>Source Edge View Creation Variable</em>'.
     * @generated
     */
    SourceEdgeViewCreationVariable createSourceEdgeViewCreationVariable();

    /**
     * Returns a new object of class '<em>Target Edge Creation Variable</em>'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Target Edge Creation Variable</em>'.
     * @generated
     */
    TargetEdgeCreationVariable createTargetEdgeCreationVariable();

    /**
     * Returns a new object of class '
     * <em>Target Edge View Creation Variable</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return a new object of class '
     *         <em>Target Edge View Creation Variable</em>'.
     * @generated
     */
    TargetEdgeViewCreationVariable createTargetEdgeViewCreationVariable();

    /**
     * Returns a new object of class '<em>Element Double Click Variable</em>'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Element Double Click Variable</em>'.
     * @generated
     */
    ElementDoubleClickVariable createElementDoubleClickVariable();

    /**
     * Returns a new object of class '<em>Node Creation Variable</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Node Creation Variable</em>'.
     * @generated
     */
    NodeCreationVariable createNodeCreationVariable();

    /**
     * Returns a new object of class '<em>Create View</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Create View</em>'.
     * @generated
     */
    CreateView createCreateView();

    /**
     * Returns a new object of class '<em>Create Edge View</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Create Edge View</em>'.
     * @generated
     */
    CreateEdgeView createCreateEdgeView();

    /**
     * Returns a new object of class '<em>Navigation</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Navigation</em>'.
     * @generated
     */
    Navigation createNavigation();

    /**
     * Returns a new object of class '<em>Diagram Creation Description</em>'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Diagram Creation Description</em>'.
     * @generated
     */
    DiagramCreationDescription createDiagramCreationDescription();

    /**
     * Returns a new object of class '<em>Diagram Navigation Description</em>'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Diagram Navigation Description</em>'.
     * @generated
     */
    DiagramNavigationDescription createDiagramNavigationDescription();

    /**
     * Returns a new object of class '<em>Container Drop Description</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Container Drop Description</em>'.
     * @generated
     */
    ContainerDropDescription createContainerDropDescription();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return the package supported by this factory.
     * @generated
     */
    ToolPackage getToolPackage();

} // ToolFactory
