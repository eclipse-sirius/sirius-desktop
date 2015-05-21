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
package org.eclipse.sirius.tests.sample.migration.migrationmodeler.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractNode;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractNodeRepresentation;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractRepresentation;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.BasicLabelStyle;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Bordered;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.BorderedRepresentation;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.BorderedStyle;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.BundledImage;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Color;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Container;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.ContainerRepresentation;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.ContainerStyle;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Diagram;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Dot;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Edge;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeRepresentation;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeStyle;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Ellipse;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Filter;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.FlatContainerStyle;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.GaugeCompositeStyle;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.GaugeSection;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.GraphicalElement;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.LabelStyle;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Layer;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Layout;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Lozenge;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Node;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.NodeRepresentation;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.NodeStyle;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Note;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Point;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Representation;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.ShapeContainerStyle;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Square;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.TestCase;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.WorkspaceImage;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance
 * hierarchy. It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the
 * inheritance hierarchy until a non-null result is returned, which is the
 * result of the switch. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage
 * @generated
 */
public class MigrationmodelerSwitch<T> {
    /**
     * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected static MigrationmodelerPackage modelPackage;

    /**
     * Creates an instance of the switch. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    public MigrationmodelerSwitch() {
        if (MigrationmodelerSwitch.modelPackage == null) {
            MigrationmodelerSwitch.modelPackage = MigrationmodelerPackage.eINSTANCE;
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns
     * a non null result; it yields that result. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the first non-null result returned by a <code>caseXXX</code>
     *         call.
     * @generated
     */
    public T doSwitch(EObject theEObject) {
        return doSwitch(theEObject.eClass(), theEObject);
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns
     * a non null result; it yields that result. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the first non-null result returned by a <code>caseXXX</code>
     *         call.
     * @generated
     */
    protected T doSwitch(EClass theEClass, EObject theEObject) {
        if (theEClass.eContainer() == MigrationmodelerSwitch.modelPackage) {
            return doSwitch(theEClass.getClassifierID(), theEObject);
        } else {
            List<EClass> eSuperTypes = theEClass.getESuperTypes();
            return eSuperTypes.isEmpty() ? defaultCase(theEObject) : doSwitch(eSuperTypes.get(0), theEObject);
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns
     * a non null result; it yields that result. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the first non-null result returned by a <code>caseXXX</code>
     *         call.
     * @generated
     */
    protected T doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
        case MigrationmodelerPackage.DIAGRAM: {
            Diagram diagram = (Diagram) theEObject;
            T result = caseDiagram(diagram);
            if (result == null) {
                result = caseRepresentation(diagram);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case MigrationmodelerPackage.GRAPHICAL_ELEMENT: {
            GraphicalElement graphicalElement = (GraphicalElement) theEObject;
            T result = caseGraphicalElement(graphicalElement);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case MigrationmodelerPackage.ABSTRACT_NODE: {
            AbstractNode abstractNode = (AbstractNode) theEObject;
            T result = caseAbstractNode(abstractNode);
            if (result == null) {
                result = caseGraphicalElement(abstractNode);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case MigrationmodelerPackage.NODE: {
            Node node = (Node) theEObject;
            T result = caseNode(node);
            if (result == null) {
                result = caseAbstractNode(node);
            }
            if (result == null) {
                result = caseGraphicalElement(node);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case MigrationmodelerPackage.BORDERED: {
            Bordered bordered = (Bordered) theEObject;
            T result = caseBordered(bordered);
            if (result == null) {
                result = caseAbstractNode(bordered);
            }
            if (result == null) {
                result = caseGraphicalElement(bordered);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case MigrationmodelerPackage.CONTAINER: {
            Container container = (Container) theEObject;
            T result = caseContainer(container);
            if (result == null) {
                result = caseGraphicalElement(container);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case MigrationmodelerPackage.EDGE: {
            Edge edge = (Edge) theEObject;
            T result = caseEdge(edge);
            if (result == null) {
                result = caseGraphicalElement(edge);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case MigrationmodelerPackage.ABSTRACT_REPRESENTATION: {
            AbstractRepresentation abstractRepresentation = (AbstractRepresentation) theEObject;
            T result = caseAbstractRepresentation(abstractRepresentation);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case MigrationmodelerPackage.EDGE_REPRESENTATION: {
            EdgeRepresentation edgeRepresentation = (EdgeRepresentation) theEObject;
            T result = caseEdgeRepresentation(edgeRepresentation);
            if (result == null) {
                result = caseAbstractRepresentation(edgeRepresentation);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case MigrationmodelerPackage.ABSTRACT_NODE_REPRESENTATION: {
            AbstractNodeRepresentation abstractNodeRepresentation = (AbstractNodeRepresentation) theEObject;
            T result = caseAbstractNodeRepresentation(abstractNodeRepresentation);
            if (result == null) {
                result = caseAbstractRepresentation(abstractNodeRepresentation);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case MigrationmodelerPackage.NODE_REPRESENTATION: {
            NodeRepresentation nodeRepresentation = (NodeRepresentation) theEObject;
            T result = caseNodeRepresentation(nodeRepresentation);
            if (result == null) {
                result = caseAbstractNodeRepresentation(nodeRepresentation);
            }
            if (result == null) {
                result = caseAbstractRepresentation(nodeRepresentation);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case MigrationmodelerPackage.BORDERED_REPRESENTATION: {
            BorderedRepresentation borderedRepresentation = (BorderedRepresentation) theEObject;
            T result = caseBorderedRepresentation(borderedRepresentation);
            if (result == null) {
                result = caseAbstractNodeRepresentation(borderedRepresentation);
            }
            if (result == null) {
                result = caseAbstractRepresentation(borderedRepresentation);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case MigrationmodelerPackage.CONTAINER_REPRESENTATION: {
            ContainerRepresentation containerRepresentation = (ContainerRepresentation) theEObject;
            T result = caseContainerRepresentation(containerRepresentation);
            if (result == null) {
                result = caseAbstractRepresentation(containerRepresentation);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case MigrationmodelerPackage.EDGE_STYLE: {
            EdgeStyle edgeStyle = (EdgeStyle) theEObject;
            T result = caseEdgeStyle(edgeStyle);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case MigrationmodelerPackage.LAYOUT: {
            Layout layout = (Layout) theEObject;
            T result = caseLayout(layout);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case MigrationmodelerPackage.COLOR: {
            Color color = (Color) theEObject;
            T result = caseColor(color);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case MigrationmodelerPackage.POINT: {
            Point point = (Point) theEObject;
            T result = casePoint(point);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case MigrationmodelerPackage.TEST_CASE: {
            TestCase testCase = (TestCase) theEObject;
            T result = caseTestCase(testCase);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case MigrationmodelerPackage.REPRESENTATION: {
            Representation representation = (Representation) theEObject;
            T result = caseRepresentation(representation);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case MigrationmodelerPackage.BORDERED_STYLE: {
            BorderedStyle borderedStyle = (BorderedStyle) theEObject;
            T result = caseBorderedStyle(borderedStyle);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case MigrationmodelerPackage.FILTER: {
            Filter filter = (Filter) theEObject;
            T result = caseFilter(filter);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case MigrationmodelerPackage.LAYER: {
            Layer layer = (Layer) theEObject;
            T result = caseLayer(layer);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case MigrationmodelerPackage.NODE_STYLE: {
            NodeStyle nodeStyle = (NodeStyle) theEObject;
            T result = caseNodeStyle(nodeStyle);
            if (result == null) {
                result = caseLabelStyle(nodeStyle);
            }
            if (result == null) {
                result = caseBorderedStyle(nodeStyle);
            }
            if (result == null) {
                result = caseBasicLabelStyle(nodeStyle);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case MigrationmodelerPackage.BASIC_LABEL_STYLE: {
            BasicLabelStyle basicLabelStyle = (BasicLabelStyle) theEObject;
            T result = caseBasicLabelStyle(basicLabelStyle);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case MigrationmodelerPackage.CONTAINER_STYLE: {
            ContainerStyle containerStyle = (ContainerStyle) theEObject;
            T result = caseContainerStyle(containerStyle);
            if (result == null) {
                result = caseLabelStyle(containerStyle);
            }
            if (result == null) {
                result = caseBorderedStyle(containerStyle);
            }
            if (result == null) {
                result = caseBasicLabelStyle(containerStyle);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case MigrationmodelerPackage.LABEL_STYLE: {
            LabelStyle labelStyle = (LabelStyle) theEObject;
            T result = caseLabelStyle(labelStyle);
            if (result == null) {
                result = caseBasicLabelStyle(labelStyle);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case MigrationmodelerPackage.DOT: {
            Dot dot = (Dot) theEObject;
            T result = caseDot(dot);
            if (result == null) {
                result = caseNodeStyle(dot);
            }
            if (result == null) {
                result = caseLabelStyle(dot);
            }
            if (result == null) {
                result = caseBorderedStyle(dot);
            }
            if (result == null) {
                result = caseBasicLabelStyle(dot);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case MigrationmodelerPackage.GAUGE_SECTION: {
            GaugeSection gaugeSection = (GaugeSection) theEObject;
            T result = caseGaugeSection(gaugeSection);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case MigrationmodelerPackage.FLAT_CONTAINER_STYLE: {
            FlatContainerStyle flatContainerStyle = (FlatContainerStyle) theEObject;
            T result = caseFlatContainerStyle(flatContainerStyle);
            if (result == null) {
                result = caseContainerStyle(flatContainerStyle);
            }
            if (result == null) {
                result = caseLabelStyle(flatContainerStyle);
            }
            if (result == null) {
                result = caseBorderedStyle(flatContainerStyle);
            }
            if (result == null) {
                result = caseBasicLabelStyle(flatContainerStyle);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case MigrationmodelerPackage.SHAPE_CONTAINER_STYLE: {
            ShapeContainerStyle shapeContainerStyle = (ShapeContainerStyle) theEObject;
            T result = caseShapeContainerStyle(shapeContainerStyle);
            if (result == null) {
                result = caseContainerStyle(shapeContainerStyle);
            }
            if (result == null) {
                result = caseLabelStyle(shapeContainerStyle);
            }
            if (result == null) {
                result = caseBorderedStyle(shapeContainerStyle);
            }
            if (result == null) {
                result = caseBasicLabelStyle(shapeContainerStyle);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case MigrationmodelerPackage.SQUARE: {
            Square square = (Square) theEObject;
            T result = caseSquare(square);
            if (result == null) {
                result = caseNodeStyle(square);
            }
            if (result == null) {
                result = caseLabelStyle(square);
            }
            if (result == null) {
                result = caseBorderedStyle(square);
            }
            if (result == null) {
                result = caseBasicLabelStyle(square);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case MigrationmodelerPackage.ELLIPSE: {
            Ellipse ellipse = (Ellipse) theEObject;
            T result = caseEllipse(ellipse);
            if (result == null) {
                result = caseNodeStyle(ellipse);
            }
            if (result == null) {
                result = caseLabelStyle(ellipse);
            }
            if (result == null) {
                result = caseBorderedStyle(ellipse);
            }
            if (result == null) {
                result = caseBasicLabelStyle(ellipse);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case MigrationmodelerPackage.LOZENGE: {
            Lozenge lozenge = (Lozenge) theEObject;
            T result = caseLozenge(lozenge);
            if (result == null) {
                result = caseNodeStyle(lozenge);
            }
            if (result == null) {
                result = caseLabelStyle(lozenge);
            }
            if (result == null) {
                result = caseBorderedStyle(lozenge);
            }
            if (result == null) {
                result = caseBasicLabelStyle(lozenge);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case MigrationmodelerPackage.BUNDLED_IMAGE: {
            BundledImage bundledImage = (BundledImage) theEObject;
            T result = caseBundledImage(bundledImage);
            if (result == null) {
                result = caseNodeStyle(bundledImage);
            }
            if (result == null) {
                result = caseLabelStyle(bundledImage);
            }
            if (result == null) {
                result = caseBorderedStyle(bundledImage);
            }
            if (result == null) {
                result = caseBasicLabelStyle(bundledImage);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case MigrationmodelerPackage.WORKSPACE_IMAGE: {
            WorkspaceImage workspaceImage = (WorkspaceImage) theEObject;
            T result = caseWorkspaceImage(workspaceImage);
            if (result == null) {
                result = caseNodeStyle(workspaceImage);
            }
            if (result == null) {
                result = caseContainerStyle(workspaceImage);
            }
            if (result == null) {
                result = caseLabelStyle(workspaceImage);
            }
            if (result == null) {
                result = caseBorderedStyle(workspaceImage);
            }
            if (result == null) {
                result = caseBasicLabelStyle(workspaceImage);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case MigrationmodelerPackage.GAUGE_COMPOSITE_STYLE: {
            GaugeCompositeStyle gaugeCompositeStyle = (GaugeCompositeStyle) theEObject;
            T result = caseGaugeCompositeStyle(gaugeCompositeStyle);
            if (result == null) {
                result = caseNodeStyle(gaugeCompositeStyle);
            }
            if (result == null) {
                result = caseLabelStyle(gaugeCompositeStyle);
            }
            if (result == null) {
                result = caseBorderedStyle(gaugeCompositeStyle);
            }
            if (result == null) {
                result = caseBasicLabelStyle(gaugeCompositeStyle);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case MigrationmodelerPackage.NOTE: {
            Note note = (Note) theEObject;
            T result = caseNote(note);
            if (result == null) {
                result = caseNodeStyle(note);
            }
            if (result == null) {
                result = caseLabelStyle(note);
            }
            if (result == null) {
                result = caseBorderedStyle(note);
            }
            if (result == null) {
                result = caseBasicLabelStyle(note);
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
     * Returns the result of interpreting the object as an instance of '
     * <em>Node</em>'. <!-- begin-user-doc --> This implementation returns null;
     * returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Node</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNode(Node object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Bordered</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Bordered</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBordered(Bordered object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Container</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Container</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseContainer(Container object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Graphical Element</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Graphical Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGraphicalElement(GraphicalElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Edge</em>'. <!-- begin-user-doc --> This implementation returns null;
     * returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Edge</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEdge(Edge object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Abstract Representation</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Abstract Representation</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractRepresentation(AbstractRepresentation object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Edge Representation</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Edge Representation</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEdgeRepresentation(EdgeRepresentation object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Abstract Node Representation</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Abstract Node Representation</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractNodeRepresentation(AbstractNodeRepresentation object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Node Representation</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Node Representation</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNodeRepresentation(NodeRepresentation object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Bordered Representation</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Bordered Representation</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBorderedRepresentation(BorderedRepresentation object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Container Representation</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Container Representation</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseContainerRepresentation(ContainerRepresentation object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Diagram</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Diagram</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDiagram(Diagram object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Edge Style</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Edge Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEdgeStyle(EdgeStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Layout</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Layout</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLayout(Layout object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Color</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Color</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseColor(Color object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Point</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Point</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T casePoint(Point object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Node Style</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Node Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNodeStyle(NodeStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Basic Label Style</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Basic Label Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBasicLabelStyle(BasicLabelStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Container Style</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Container Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseContainerStyle(ContainerStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Label Style</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Label Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLabelStyle(LabelStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Dot</em>'. <!-- begin-user-doc --> This implementation returns null;
     * returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Dot</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDot(Dot object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Gauge Section</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Gauge Section</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGaugeSection(GaugeSection object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Flat Container Style</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Flat Container Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFlatContainerStyle(FlatContainerStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Shape Container Style</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Shape Container Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseShapeContainerStyle(ShapeContainerStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Square</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Square</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSquare(Square object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Ellipse</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Ellipse</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEllipse(Ellipse object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Lozenge</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Lozenge</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLozenge(Lozenge object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Bundled Image</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Bundled Image</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBundledImage(BundledImage object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Workspace Image</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Workspace Image</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseWorkspaceImage(WorkspaceImage object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Gauge Composite Style</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Gauge Composite Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGaugeCompositeStyle(GaugeCompositeStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Note</em>'. <!-- begin-user-doc --> This implementation returns null;
     * returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Note</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNote(Note object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Abstract Node</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Abstract Node</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractNode(AbstractNode object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Test Case</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Test Case</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTestCase(TestCase object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Representation</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Representation</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRepresentation(Representation object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Bordered Style</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Bordered Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBorderedStyle(BorderedStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Filter</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Filter</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFilter(Filter object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Layer</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Layer</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLayer(Layer object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>EObject</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch, but this is
     * the last case anyway. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    public T defaultCase(EObject object) {
        return null;
    }

} // MigrationmodelerSwitch
