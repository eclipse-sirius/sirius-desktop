/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tests.unit.api.refresh;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.EdgeArrows;
import org.eclipse.sirius.diagram.EdgeRouting;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.LineStyle;
import org.eclipse.sirius.diagram.description.style.EdgeStyleDescription;
import org.eclipse.sirius.tests.sample.docbook.Chapter;
import org.eclipse.sirius.tests.sample.docbook.DocbookPackage;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.common.DocbookTestCase;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.SystemColor;
import org.eclipse.sirius.viewpoint.description.SystemColors;
import org.eclipse.ui.IEditorPart;

/**
 * Test the conditional style of an edge
 * 
 * @author lredor
 */
public class EdgeWithConditionalStyleTest extends DocbookTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.session.addSelectedView((DView) new DRepresentationQuery(this.evoluateDiagram).getRepresentationDescriptor().eContainer(), new NullProgressMonitor());
    }

    /**
     * Checks that the "EdgeMappingSpec.createEdge()" method give an edge with
     * the correct style. <BR>
     * This test :
     * <UL>
     * <LI>creates one chapter with two bigSection (Sect1) in the diagram
     * "evoluate view",</LI>
     * <LI>creates automatically an edge between the chapter and the big section
     * with the EdgeMapping "belong To Chapter",</LI>
     * <LI>and then checks if the first bigSection has the conditional style and
     * the second bigSection has the normal style</LI>
     * </UL>
     */
    public void testEdgeMappingWithConditionalStyle() {
        // Create one chapter
        Command command = createChapterCommandInEvoluateView(evoluateDiagram);
        assertTrue("Could not create Chapter.", command.canExecute());
        session.getTransactionalEditingDomain().getCommandStack().execute(command);
        // Create one sect1
        command = createBigSectionCommandInEvoluateView(evoluateDiagram);
        assertTrue("Could not create first Big Section.", command.canExecute());
        session.getTransactionalEditingDomain().getCommandStack().execute(command);
        // Create another sect1
        command = createBigSectionCommandInEvoluateView(evoluateDiagram);
        assertTrue("Could not create second Big Section.", command.canExecute());
        session.getTransactionalEditingDomain().getCommandStack().execute(command);

        DAnalysis analysis = (DAnalysis) session.getSessionResource().getContents().get(0);

        // Test that the style for the first edge is the conditional style
        EObject firstBigSectionEdge = null;
        try {
            firstBigSectionEdge = INTERPRETER.evaluateEObject(evoluateDiagram, "aql:self.eAllContents(diagram::DEdge)->select(e | e.target.eClass().name='Sect1')->first()");
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the first edge for the mapping \"belong to Chapter\".");
            e.printStackTrace();
        }
        assertNotNull("Not possible to catch the first edge for the mapping \"belong to Chapter\".", firstBigSectionEdge);
        if (!(firstBigSectionEdge instanceof DEdge)) {
            fail("The first BigSectionEdge is not a DEdge.");
        } else {
            final DEdge edge = (DEdge) firstBigSectionEdge;
            final EdgeStyle edgeStyle = (EdgeStyle) edge.getStyle();
            checkFisrtEdge(edgeStyle, false);
        }

        // Test that the style for the second edge is the normal style
        EObject secondBigSectionEdge = null;
        try {
            secondBigSectionEdge = INTERPRETER.evaluateEObject(evoluateDiagram, "aql:(" + "self.eAllContents(diagram::DEdge)->select(e | e.target.eClass().name='Sect1') - "
                    + "Sequence{self.eAllContents(diagram::DEdge)->select(e | e.target.eClass().name='Sect1')->first()})->first()");
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the second edge for the mapping \"belong to Chapter\".");
            e.printStackTrace();
        }
        assertNotNull("Not possible to catch the second edge for the mapping \"belong to Chapter\".", secondBigSectionEdge);
        if (!(secondBigSectionEdge instanceof DEdge)) {
            fail("The second BigSectionEdge is not a DEdge.");
        } else {
            final DEdge edge = (DEdge) secondBigSectionEdge;
            final EdgeStyle edgeStyle = (EdgeStyle) edge.getStyle();
            checkSecondEdge(edgeStyle);
        }
    }

    /**
     * Checks that the "EdgeMappingSpec.createEdge()" method give an edge with
     * the correct style. <BR>
     * This test :
     * <UL>
     * <LI>creates one chapter with two bigSection (Sect1) in the diagram
     * "evoluate view",</LI>
     * <LI>creates automatically an edge between the chapter and the big section
     * with the EdgeMapping "belong To Chapter",</LI>
     * <LI>changes the style of the first edge with custom line style</LI>
     * <LI>changes a semantic element that normally cause a change of
     * conditional style
     * <LI>launch a refresh</LI>
     * <LI>and verify that the style is always the custom style</LI>
     * </UL>
     */
    public void _testEdgeMappingWithConditionalStyleAndCustomStyle() {
        // Create one chapter
        Command command = createChapterCommandInEvoluateView(evoluateDiagram);
        assertTrue("Could not create Chapter.", command.canExecute());
        session.getTransactionalEditingDomain().getCommandStack().execute(command);
        // Create one sect1
        command = createBigSectionCommandInEvoluateView(evoluateDiagram);
        assertTrue("Could not create first Big Section.", command.canExecute());
        session.getTransactionalEditingDomain().getCommandStack().execute(command);
        // Create another sect1
        command = createBigSectionCommandInEvoluateView(evoluateDiagram);
        assertTrue("Could not create second Big Section.", command.canExecute());
        session.getTransactionalEditingDomain().getCommandStack().execute(command);

        IEditorPart editorPart = DialectUIManager.INSTANCE.openEditor(session, evoluateDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        DAnalysis analysis = (DAnalysis) session.getSessionResource().getContents().get(0);

        // Test that the style for the first edge is the conditional style
        EObject firstBigSectionEdge = null;
        try {
            firstBigSectionEdge = INTERPRETER.evaluateEObject(evoluateDiagram, "aql:self.eAllContents(diagram::DEdge)->select(e | e.target.eClass().name='Sect1')->first()");
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the first edge for the mapping \"belong to Chapter\".");
            e.printStackTrace();
        }
        assertNotNull("Not possible to catch the first edge for the mapping \"belong to Chapter\".", firstBigSectionEdge);
        if (!(firstBigSectionEdge instanceof DEdge)) {
            fail("The first BigSectionEdge is not a DEdge.");
        } else {
            final DEdge edge = (DEdge) firstBigSectionEdge;
            final EdgeStyle edgeStyle = (EdgeStyle) edge.getStyle();
            // Change the style to custom
            TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
            CompoundCommand compoundCommand = new CompoundCommand();
            Command changeLineStyleCmd = SetCommand.create(domain, edgeStyle, DiagramPackage.Literals.EDGE_STYLE__LINE_STYLE, LineStyle.SOLID_LITERAL);
            compoundCommand.append(changeLineStyleCmd);
            Command setCustomCmd = AddCommand.create(domain, edgeStyle, ViewpointPackage.Literals.CUSTOMIZABLE__CUSTOM_FEATURES, DiagramPackage.Literals.EDGE_STYLE__LINE_STYLE.getName());
            compoundCommand.append(setCustomCmd);
            domain.getCommandStack().execute(compoundCommand);

            checkFisrtEdge(edgeStyle, true);

            // Change the semantic element corresponding to this edge and
            // refresh the diagram.
            final EdgeTarget target = edge.getTargetNode();
            assertTrue("The target of this edge should be a DNode.", target instanceof DNode);
            assertTrue("The target semantic element of this edge should be a Chapter.", ((DNode) target).getTarget() instanceof Chapter);

            Command changeChapterIdCmd = SetCommand.create(domain, edgeStyle, DocbookPackage.Literals.CHAPTER__ID, "newChapterId");
            domain.getCommandStack().execute(changeChapterIdCmd);

            final EdgeStyle edgeStyleAfterSemanticChange = (EdgeStyle) edge.getStyle();
            checkFisrtEdge(edgeStyleAfterSemanticChange, true);
        }
        DialectUIManager.INSTANCE.closeEditor(editorPart, false);
        editorPart.getSite().getPage().closeEditor(editorPart, false);
    }

    /**
     * The first edge must have the conditional style.
     * 
     * @param edgeStyle
     *            the style to check
     * @param cusotm
     *            if true, the line style is solid, otherwise the line style is
     *            dash
     */
    private void checkFisrtEdge(final EdgeStyle edgeStyle, final boolean custom) {
        assertFalse("Wrong showIcon for first Edge style.", edgeStyle.getCenterLabelStyle().isShowIcon());
        final EdgeStyleDescription edgeStyleDescription = (EdgeStyleDescription) edgeStyle.getDescription();
        assertFalse("Wrong showIcon for first Edge description.", edgeStyleDescription.getCenterLabelStyleDescription().isShowIcon());
        assertEquals("Wrong EdgeSourceArrow for first Edge.", EdgeArrows.INPUT_ARROW_LITERAL, edgeStyle.getSourceArrow());
        assertEquals("Wrong EdgeTargetArrow for first Edge.", EdgeArrows.NO_DECORATION_LITERAL, edgeStyle.getTargetArrow());
        List<FontFormat> fontFormat = new ArrayList<FontFormat>();
        fontFormat.add(FontFormat.ITALIC_LITERAL);
        assertEquals("Wrong LabelFormat for first Edge.", fontFormat, edgeStyle.getCenterLabelStyle().getLabelFormat());
        assertEquals("Wrong LabelSize for first Edge.", 8, edgeStyle.getCenterLabelStyle().getLabelSize());
        if (custom) {
            assertFalse("The edge style should be custom.", edgeStyle.getCustomFeatures().isEmpty());
            assertEquals("Wrong LineStyle for custom first Edge.", LineStyle.SOLID_LITERAL, edgeStyle.getLineStyle());
        } else {
            assertTrue("The edge style shouldn't be custom.", edgeStyle.getCustomFeatures().isEmpty());
            assertEquals("Wrong LineStyle for first Edge.", LineStyle.DASH_LITERAL, edgeStyle.getLineStyle());
        }
        assertEquals("Wrong RoutingStyle for first Edge.", EdgeRouting.TREE_LITERAL, edgeStyleDescription.getRoutingStyle());
        assertEquals("Wrong LabelComputationExpression for first Edge.", "ConditionalStyle", edgeStyleDescription.getCenterLabelStyleDescription().getLabelExpression());
        assertEquals("Wrong SizeComputationExpression for first Edge.", "3", edgeStyleDescription.getSizeComputationExpression());
        assertNotNull("Wrong StrokeColor (not set) for first Edge.", edgeStyle.getStrokeColor());
        if (edgeStyleDescription.getStrokeColor() instanceof SystemColor) {
            assertEquals("Wrong StrokeColor for first Edge.", SystemColors.LIGHT_BLUE_LITERAL, VisualBindingManager.getDefault().findClosestStandardColor(edgeStyle.getStrokeColor()));
        } else {
            fail("Wrong StrokeColor (not NamedColor) for first Edge.");
        }
    }

    /**
     * The second edge must have the normal style.
     * 
     * @param edgeStyle
     *            the style to check
     */
    private void checkSecondEdge(final EdgeStyle edgeStyle) {
        assertTrue("Wrong showIcon for first Edge style.", edgeStyle.getCenterLabelStyle().isShowIcon());
        final EdgeStyleDescription edgeStyleDescription = (EdgeStyleDescription) edgeStyle.getDescription();
        assertTrue("Wrong showIcon for first Edge description.", edgeStyleDescription.getCenterLabelStyleDescription().isShowIcon());
        assertEquals("Wrong EdgeSourceArrow for second Edge.", EdgeArrows.NO_DECORATION_LITERAL, edgeStyle.getSourceArrow());
        assertEquals("Wrong EdgeTargetArrow for second Edge.", EdgeArrows.INPUT_ARROW_LITERAL, edgeStyle.getTargetArrow());
        assertEquals("Wrong LabelFormat for second Edge.", new ArrayList<FontFormat>(), edgeStyle.getCenterLabelStyle().getLabelFormat());
        assertEquals("Wrong LabelSize for second Edge.", 8, edgeStyle.getCenterLabelStyle().getLabelSize());
        assertEquals("Wrong LineStyle for second Edge.", LineStyle.SOLID_LITERAL, edgeStyle.getLineStyle());
        assertEquals("Wrong RoutingStyle for second Edge.", EdgeRouting.STRAIGHT_LITERAL, edgeStyleDescription.getRoutingStyle());
        assertEquals("Wrong LabelExpression for second Edge.", "", edgeStyleDescription.getCenterLabelStyleDescription().getLabelExpression());
        assertEquals("Wrong SizeComputationExpression for second Edge.", "aql:self.eContents()->size()", edgeStyleDescription.getSizeComputationExpression());
        assertEquals("Wrong StrokeColor for second Edge, we should have the default one", "gray", ((SystemColor) edgeStyleDescription.getStrokeColor()).getName());
    }

}
