/*******************************************************************************
 * Copyright (c) 2015, 2022 Obeo.
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
package org.eclipse.sirius.tests.unit.diagram.compartment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.Border;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.ScrollPane;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ResizableCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.OneLineBorder;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.common.tools.api.util.ReflectionHelper;
import org.eclipse.sirius.diagram.ContainerLayout;
import org.eclipse.sirius.diagram.ContainerStyle;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.LineStyle;
import org.eclipse.sirius.diagram.description.style.ContainerStyleDescription;
import org.eclipse.sirius.diagram.model.business.internal.query.DDiagramElementContainerExperimentalQuery;
import org.eclipse.sirius.diagram.model.business.internal.query.DNodeContainerExperimentalQuery;
import org.eclipse.sirius.diagram.ui.business.internal.edit.helpers.LabelAlignmentHelper;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramElementContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNameEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramListEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDNodeContainerCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDiagramElementContainerNameEditPart;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.figure.GradientRoundedRectangle;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutUtils;
import org.eclipse.sirius.diagram.ui.tools.internal.figure.RegionRoundedGradientRectangle;
import org.eclipse.sirius.diagram.ui.tools.internal.preferences.SiriusDiagramUiInternalPreferencesKeys;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.AlphaDropShadowBorder;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.LabelBorderStyleIds;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.RoundedCornerMarginBorder;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.SiriusWrapLabel;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.ViewNodeContainerFigureDesc;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.LabelAlignment;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.style.LabelBorderStyleDescription;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;
import org.junit.Assert;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;

/**
 * Tests defined to ensure that compartments are correctly layouted.
 * 
 * @author <a href="mailto:belqassim.djafer@obeo.fr">Belqassim Djafer</a>
 * @author mporhel
 *
 */
public class CompartmentsLayoutTest extends SiriusDiagramTestCase implements ICompartmentTests {

    private final static int[] HSTACK_FIRST_REGION_SPECIFIC_CORNERS = { PositionConstants.SOUTH_WEST };

    private final static int[] HSTACK_LAST_REGION_SPECIFIC_CORNERS = { PositionConstants.SOUTH_EAST };

    private final static int[] VSTACK_FIRST_REGION_SPECIFIC_CORNERS = new int[0];

    private final static int[] VSTACK_LAST_REGION_SPECIFIC_CORNERS = { PositionConstants.SOUTH_WEST, PositionConstants.SOUTH_EAST };

    private final static Collection<Integer> CORNERS = Collections
            .unmodifiableList(new ArrayList<Integer>(Arrays.asList(PositionConstants.NORTH_WEST, PositionConstants.NORTH_EAST, PositionConstants.SOUTH_WEST, PositionConstants.SOUTH_EAST)));

    private DDiagram diagram;

    private DiagramEditor editor;

    private String oldFont;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(MODEL_PATH, VSM_PATH, SESSION_PATH);
        TestsUtil.synchronizationWithUIThread();

        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        oldFont = changeDefaultFontName("Comic Sans MS");
    }

    @Override
    protected void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        if (oldFont != null) {
            changeDefaultFontName(oldFont);
        }
        super.tearDown();
    }

    /**
     * Ensure that labels in a horizontal compartment stack are correctly layouted.
     */
    public void testLabelAlignmentInHorizontalStack() {
        diagram = (DDiagram) getRepresentations(HORIZONTAL_STACK_REPRESENTATION_NAME).iterator().next();
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        doTestLabelAlignment();
    }

    /**
     * Ensure that labels in a vertical compartment stack are correctly layouted.
     */
    public void testLabelAlignmentInVerticalStack() {
        diagram = (DDiagram) getRepresentations(VERTICAL_STACK_REPRESENTATION_NAME).iterator().next();
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        doTestLabelAlignment();
    }

    private void doTestLabelAlignment() {
        checkAndChangeLabelAlignment(FIRST_REGION_CONTAINER_NAME, LabelAlignment.CENTER, LabelAlignment.LEFT);
        checkAndChangeLabelAlignment(LEFT_CLASS_NAME, LabelAlignment.LEFT, LabelAlignment.RIGHT);
        checkAndChangeLabelAlignment(CENTER_CLASS_NAME, LabelAlignment.CENTER, LabelAlignment.LEFT);
        checkAndChangeLabelAlignment(RIGHT_CLASS_NAME, LabelAlignment.RIGHT, LabelAlignment.CENTER);
        checkAndChangeLabelAlignment(LEFT_PKG_NAME, LabelAlignment.LEFT, LabelAlignment.CENTER);
        checkAndChangeLabelAlignment(CENTER_PKG_NAME, LabelAlignment.CENTER, LabelAlignment.RIGHT);
        checkAndChangeLabelAlignment(RIGHT_PKG_NAME, LabelAlignment.RIGHT, LabelAlignment.LEFT);
    }

    private void checkAndChangeLabelAlignment(String editPartName, LabelAlignment initialExpectedValue, LabelAlignment labelAlignement) {
        checkLabelAlignment(editPartName, initialExpectedValue);

        ContainerStyle containerStyle = getDiagramElementsFromLabel(diagram, editPartName, DDiagramElementContainer.class).get(0).getOwnedStyle();
        changeLabelAlignement(containerStyle, labelAlignement);
        TestsUtil.synchronizationWithUIThread();

        checkLabelAlignment(editPartName, labelAlignement);
    }

    /**
     * Change the label alignment of the given style.
     */
    private void changeLabelAlignement(final ContainerStyle containerStyle, final LabelAlignment labelAlignement) {
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        domain.getCommandStack().execute(new RecordingCommand(domain) {
            @Override
            protected void doExecute() {
                containerStyle.setLabelAlignment(labelAlignement);
                containerStyle.getCustomFeatures().add(ViewpointPackage.eINSTANCE.getLabelStyle_LabelAlignment().getName());
            }
        });
    }

    private void checkLabelAlignment(String label, LabelAlignment labelAlignement) {
        DDiagramElementContainer ddec = getDiagramElementsFromLabel(diagram, label, DDiagramElementContainer.class).get(0);
        assertEquals("Style label alignment is not consistent for " + label, labelAlignement, ddec.getOwnedStyle().getLabelAlignment());

        AbstractDiagramElementContainerNameEditPart namePart = (AbstractDiagramElementContainerNameEditPart) getEditPart(ddec, editor).getChildren().get(0);

        int actualLabelAlignment = getActualLabelAlignment(namePart);
        assertEquals("Figure alignment is not consistent with style for " + label, LabelAlignmentHelper.getAsCTLMinorAlignment(labelAlignement), actualLabelAlignment);
    }

    /**
     * Test the initial expected layout (non auto-sized).
     */
    public void testHorizontalStackInitialLayout() {
        diagram = (DDiagram) getRepresentations(HORIZONTAL_STACK_REPRESENTATION_NAME).iterator().next();
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        assertEquals("Session should not be dirty.", SessionStatus.SYNC, session.getStatus());

        checkBounds(FIRST_REGION_CONTAINER_NAME, new Rectangle(0, 0, -1, -1), INITIAL_HORIZONTAL_FIRST_REGION_CONTAINER_BOUNDS, 0, 1);
        checkBounds(LEFT_CLASS_NAME, INITIAL_HORIZONTAL_LEFT_CLASS_BOUNDS, INITIAL_HORIZONTAL_LEFT_CLASS_BOUNDS);
        checkBounds(CENTER_CLASS_NAME, INITIAL_HORIZONTAL_CENTER_CLASS_BOUNDS, INITIAL_HORIZONTAL_CENTER_CLASS_BOUNDS);
        checkBounds(RIGHT_CLASS_NAME, INITIAL_HORIZONTAL_RIGHT_CLASS_BOUNDS, INITIAL_HORIZONTAL_RIGHT_CLASS_BOUNDS);
        checkBounds(LEFT_PKG_NAME, INITIAL_HORIZONTAL_LEFT_PKG_BOUNDS, INITIAL_HORIZONTAL_LEFT_PKG_BOUNDS);
        checkBounds(CENTER_PKG_NAME, INITIAL_HORIZONTAL_CENTER_PKG_BOUNDS, INITIAL_HORIZONTAL_CENTER_PKG_BOUNDS);
        checkBounds(RIGHT_PKG_NAME, INITIAL_HORIZONTAL_RIGHT_PKG_BOUNDS, INITIAL_HORIZONTAL_RIGHT_PKG_BOUNDS);

        doTestInitialLayout(ContainerLayout.HORIZONTAL_STACK, 4, 20, 2);
    }

    /**
     * Test the initial expected layout (auto-sized).
     */
    public void testVerticalStackInitialLayout() {
        diagram = (DDiagram) getRepresentations(VERTICAL_STACK_REPRESENTATION_NAME).iterator().next();
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        assertEquals("Session should not be dirty.", SessionStatus.SYNC, session.getStatus());

        // Each vertical region can have a delta according to what is expected
        // because of font height on each OS. Height deltas of each region have
        // an incidence on the global container height. We must cumulate them to
        // make a reasonable assert at the end.
        int heightDeltas = 0;

        // Delta of 5 pixels because there are 5 lines (with potential 1 delta
        // pixel)
        Rectangle leftClassBounds = checkBounds(LEFT_CLASS_NAME, new Rectangle(0, 0, -1, -1), INITIAL_VERTICAL_LEFT_CLASS_BOUNDS, 0, 5);
        heightDeltas += leftClassBounds.height - INITIAL_VERTICAL_LEFT_CLASS_BOUNDS.height;
        // Delta of 5 pixels because there are 5 lines (with potential 1 delta
        // pixel)
        Rectangle centerClassBounds = checkBounds(CENTER_CLASS_NAME, new Rectangle(0, INITIAL_VERTICAL_CENTER_CLASS_BOUNDS.y, -1, -1),
                INITIAL_VERTICAL_CENTER_CLASS_BOUNDS.getTranslated(0, heightDeltas), 0, 5);
        heightDeltas += centerClassBounds.height - INITIAL_VERTICAL_CENTER_CLASS_BOUNDS.height;
        // Delta of 2 pixels because there are 2 lines (with potential 1 delta
        // pixel)
        Rectangle rightClassBounds = checkBounds(RIGHT_CLASS_NAME, new Rectangle(0, INITIAL_VERTICAL_RIGHT_CLASS_BOUNDS.y, -1, -1), INITIAL_VERTICAL_RIGHT_CLASS_BOUNDS.getTranslated(0, heightDeltas),
                0, 2);
        heightDeltas += rightClassBounds.height - INITIAL_VERTICAL_RIGHT_CLASS_BOUNDS.height;
        Rectangle leftPkgBounds = checkBounds(LEFT_PKG_NAME, new Rectangle(0, INITIAL_VERTICAL_LEFT_PKG_BOUNDS.y, -1, -1), INITIAL_VERTICAL_LEFT_PKG_BOUNDS.getTranslated(0, heightDeltas));
        heightDeltas += leftPkgBounds.height - INITIAL_VERTICAL_LEFT_PKG_BOUNDS.height;
        // Delta of 1 pixel because there is 1 line (with potential 1 delta
        // pixel)
        Rectangle centerPkgBounds = checkBounds(CENTER_PKG_NAME, new Rectangle(0, INITIAL_VERTICAL_CENTER_PKG_BOUNDS.y, -1, -1), INITIAL_VERTICAL_CENTER_PKG_BOUNDS.getTranslated(0, heightDeltas), 0,
                1);
        heightDeltas += centerPkgBounds.height - INITIAL_VERTICAL_CENTER_PKG_BOUNDS.height;
        Rectangle rightPkgBounds = checkBounds(RIGHT_PKG_NAME, new Rectangle(0, INITIAL_VERTICAL_RIGHT_PKG_BOUNDS.y, -1, -1), INITIAL_VERTICAL_RIGHT_PKG_BOUNDS.getTranslated(0, heightDeltas));
        heightDeltas += rightPkgBounds.height - INITIAL_VERTICAL_RIGHT_PKG_BOUNDS.height;
        Rectangle firstRegionBounds = checkBounds(FIRST_REGION_CONTAINER_NAME,
                new Rectangle(INITIAL_VERTICAL_FIRST_REGION_CONTAINER_BOUNDS.x, INITIAL_VERTICAL_FIRST_REGION_CONTAINER_BOUNDS.y, -1, -1), new Rectangle(64, 16, 141, -1));
        assertEquals("Wrong Draw2D height for " + FIRST_REGION_CONTAINER_NAME, INITIAL_VERTICAL_FIRST_REGION_CONTAINER_BOUNDS.height + heightDeltas, firstRegionBounds.height(), 1);

        doTestInitialLayout(ContainerLayout.VERTICAL_STACK, 5, 10, 1);
    }

    /**
     * Check that the bounds (GMF and Draw2D) are as expected.
     *
     * @param label
     *            Label of the container to check.
     * @param expectedGmfBounds
     *            The GMF expected bounds
     * @param expectedFigureBounds
     *            The draw2d expected bounds. If the x, y , width or height in this bounds is equal to -1, we don't
     *            check it. This is useful in case of size that depends on Font (with different result according to OS).
     * @return the current DrawD2 bounds
     */
    private Rectangle checkBounds(String label, Rectangle expectedGmfBounds, Rectangle expectedFigureBounds) {
        return checkBounds(label, expectedGmfBounds, expectedFigureBounds, 0, 0);
    }

    /**
     * Check that the bounds (GMF and Draw2D) are as expected.
     *
     * @param label
     *            Label of the container to check.
     * @param expectedGmfBounds
     *            The GMF expected bounds
     * @param expectedFigureBounds
     *            The draw2d expected bounds. If the x, y , width or height in this bounds is equal to -1, we don't
     *            check it. This is useful in case of size that depends on Font (with different result according to OS).
     * @param widthDelta
     *            The width delta to consider the width as equal (because of font size that can be slightly different on
     *            each OS).
     * @param heightDelta
     *            The height delta to consider the height as equal (because of font size that can be slightly different
     *            on each OS).
     * @return the current DrawD2 bounds
     */
    private Rectangle checkBounds(String label, Rectangle expectedGmfBounds, Rectangle expectedFigureBounds, int widthDelta, int heightDelta) {
        DDiagramElementContainer region = getDiagramElementsFromLabel(diagram, label, DDiagramElementContainer.class).get(0);
        AbstractDiagramElementContainerEditPart editPart = (AbstractDiagramElementContainerEditPart) getEditPart(region);

        IFigure mainFigure = editPart.getMainFigure();
        assertEquals("Wrong GMF bounds for " + label, expectedGmfBounds, getBounds((Node) editPart.getNotationView()));
        if (expectedFigureBounds.x() != -1 && expectedFigureBounds.y() != -1 && expectedFigureBounds.width() != -1 && expectedFigureBounds.height() != -1) {
            if (widthDelta == 0 && heightDelta == 0) {
                assertEquals("Wrong Draw2D bounds for " + label, expectedFigureBounds, mainFigure.getBounds());
            } else {
                assertEquals("Wrong Draw2D location for " + label, expectedFigureBounds.getLocation(), mainFigure.getBounds().getLocation());
                assertEquals("Wrong Draw2D width for " + label, expectedFigureBounds.width(), mainFigure.getBounds().width(), widthDelta);
                assertEquals("Wrong Draw2D height for " + label, expectedFigureBounds.height(), mainFigure.getBounds().height(), heightDelta);
            }
        } else {
            if (expectedFigureBounds.x() != -1) {
                assertEquals("Wrong Draw2D x for " + label, expectedFigureBounds.x(), mainFigure.getBounds().x());
            }
            if (expectedFigureBounds.y() != -1) {
                assertEquals("Wrong Draw2D y for " + label, expectedFigureBounds.y(), mainFigure.getBounds().y());
            }
            if (expectedFigureBounds.width() != -1) {
                assertEquals("Wrong Draw2D width for " + label, expectedFigureBounds.width(), mainFigure.getBounds().width(), widthDelta);
            }
            if (expectedFigureBounds.height() != -1) {
                assertEquals("Wrong Draw2D height for " + label, expectedFigureBounds.height(), mainFigure.getBounds().height(), heightDelta);
            }
        }

        ResizableCompartmentEditPart compartmentEditPart = (ResizableCompartmentEditPart) editPart.getChildren().get(1);
        Border border = compartmentEditPart.getContentPane().getBorder();

        if (editPart instanceof IDiagramListEditPart) {
            assertEquals("Wrong border margin for " + label, new Insets(0, 4, editPart.isRegion() ? 0 : 1, 4), border.getInsets(null));
        } else if (editPart instanceof IDiagramContainerEditPart) {
            assertNull("Wrong border for " + label + ": the BorderItemsAwareFreeFormLayer used as content pane of a freeform region should not have a border.", border);
        }

        return mainFigure.getBounds();
    }

    private Rectangle getBounds(Node notationView) {
        Rectangle bounds = new Rectangle();
        LayoutConstraint layoutConstraint = notationView.getLayoutConstraint();
        if (layoutConstraint instanceof Location) {
            Location location = (Location) layoutConstraint;
            bounds.setLocation(location.getX(), location.getY());
        }
        if (layoutConstraint instanceof Size) {
            Size size = (Size) layoutConstraint;
            bounds.setSize(size.getWidth(), size.getHeight());
        }
        return bounds;
    }

    private void doTestInitialLayout(ContainerLayout stackDirection, int regionContainerBorderSize, int regionContainerCorners, int regionBorderSize) {
        int regionCorners = 0;

        checkRegionContainer(FIRST_REGION_CONTAINER_NAME, regionContainerBorderSize, LineStyle.SOLID_LITERAL, regionContainerCorners);
        checkRegion(LEFT_CLASS_NAME, 0, regionBorderSize, LineStyle.SOLID_LITERAL, regionCorners, regionContainerCorners, getFirstRegionExpectedSpecificCorners(stackDirection));
        checkRegion(CENTER_CLASS_NAME, 1, regionBorderSize, LineStyle.SOLID_LITERAL, regionCorners, regionContainerCorners);
        checkRegion(RIGHT_CLASS_NAME, 2, regionBorderSize, LineStyle.DASH_LITERAL, regionCorners, regionContainerCorners);
        checkRegion(LEFT_PKG_NAME, 3, regionBorderSize, LineStyle.DOT_LITERAL, regionCorners, regionContainerCorners);
        checkRegion(CENTER_PKG_NAME, 4, regionBorderSize, LineStyle.DASH_DOT_LITERAL, regionCorners, regionContainerCorners);
        checkRegion(RIGHT_PKG_NAME, 5, regionBorderSize, LineStyle.SOLID_LITERAL, regionCorners, regionContainerCorners, getLastRegionExpectedSpecificCorners(stackDirection));
    }

    private int[] getFirstRegionExpectedSpecificCorners(ContainerLayout stackDirection) {
        int[] result = null;
        if (ContainerLayout.VERTICAL_STACK == stackDirection) {
            result = VSTACK_FIRST_REGION_SPECIFIC_CORNERS;
        } else if (ContainerLayout.HORIZONTAL_STACK == stackDirection) {
            result = HSTACK_FIRST_REGION_SPECIFIC_CORNERS;
        }
        return result;
    }

    private int[] getLastRegionExpectedSpecificCorners(ContainerLayout stackDirection) {
        int[] result = null;
        if (ContainerLayout.VERTICAL_STACK == stackDirection) {
            result = VSTACK_LAST_REGION_SPECIFIC_CORNERS;
        } else if (ContainerLayout.HORIZONTAL_STACK == stackDirection) {
            result = HSTACK_LAST_REGION_SPECIFIC_CORNERS;
        }
        return result;
    }

    /**
     * Ensure that borders changes in a horizontal compartment stack are correctly done.
     */
    public void testHorizontalStackBorderChanges() {
        diagram = (DDiagram) getRepresentations(HORIZONTAL_STACK_REPRESENTATION_NAME).iterator().next();
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        doTestBorderChanges(ContainerLayout.HORIZONTAL_STACK, 4, 20, 2);
    }

    /**
     * Ensure that borders changes in a vertical compartment stack are correctly done.
     */
    public void testVerticalStackBorderChanges() {
        diagram = (DDiagram) getRepresentations(VERTICAL_STACK_REPRESENTATION_NAME).iterator().next();
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        doTestBorderChanges(ContainerLayout.VERTICAL_STACK, 5, 10, 1);
    }

    private void doTestBorderChanges(ContainerLayout stackDirection, int regionContainerBorderSize, int regionContainerCorners, int regionBorderSize) {
        int regionCorners = 0;

        changeBorderSpecification(FIRST_REGION_CONTAINER_NAME, 10, LineStyle.DASH_DOT_LITERAL);
        TestsUtil.synchronizationWithUIThread();

        checkRegionContainer(FIRST_REGION_CONTAINER_NAME, 10, LineStyle.DASH_DOT_LITERAL, regionContainerCorners);
        checkRegion(LEFT_CLASS_NAME, 0, regionBorderSize, LineStyle.SOLID_LITERAL, regionCorners, regionContainerCorners, getFirstRegionExpectedSpecificCorners(stackDirection));
        checkRegion(CENTER_CLASS_NAME, 1, regionBorderSize, LineStyle.SOLID_LITERAL, regionCorners, regionContainerCorners);
        checkRegion(RIGHT_CLASS_NAME, 2, regionBorderSize, LineStyle.DASH_LITERAL, regionCorners, regionContainerCorners);
        checkRegion(LEFT_PKG_NAME, 3, regionBorderSize, LineStyle.DOT_LITERAL, regionCorners, regionContainerCorners);
        checkRegion(CENTER_PKG_NAME, 4, regionBorderSize, LineStyle.DASH_DOT_LITERAL, regionCorners, regionContainerCorners);
        checkRegion(RIGHT_PKG_NAME, 5, regionBorderSize, LineStyle.SOLID_LITERAL, regionCorners, regionContainerCorners, getLastRegionExpectedSpecificCorners(stackDirection));

        changeBorderSpecification(FIRST_REGION_CONTAINER_NAME, 10, LineStyle.DOT_LITERAL);
        changeBorderSpecification(CENTER_CLASS_NAME, 10, LineStyle.DASH_DOT_LITERAL);
        changeBorderSpecification(RIGHT_PKG_NAME, 10, LineStyle.DASH_DOT_LITERAL);
        TestsUtil.synchronizationWithUIThread();

        checkRegionContainer(FIRST_REGION_CONTAINER_NAME, 10, LineStyle.DOT_LITERAL, regionContainerCorners);
        checkRegion(LEFT_CLASS_NAME, 0, regionBorderSize, LineStyle.SOLID_LITERAL, regionCorners, regionContainerCorners, getFirstRegionExpectedSpecificCorners(stackDirection));
        checkRegion(CENTER_CLASS_NAME, 1, 10, LineStyle.DASH_DOT_LITERAL, regionCorners, regionContainerCorners);
        checkRegion(RIGHT_CLASS_NAME, 2, regionBorderSize, LineStyle.DASH_LITERAL, regionCorners, regionContainerCorners);
        checkRegion(LEFT_PKG_NAME, 3, regionBorderSize, LineStyle.DOT_LITERAL, regionCorners, regionContainerCorners);
        checkRegion(CENTER_PKG_NAME, 4, regionBorderSize, LineStyle.DASH_DOT_LITERAL, regionCorners, regionContainerCorners);
        checkRegion(RIGHT_PKG_NAME, 5, 10, LineStyle.DASH_DOT_LITERAL, regionCorners, regionContainerCorners, getLastRegionExpectedSpecificCorners(stackDirection));

        changeBorderSpecification(FIRST_REGION_CONTAINER_NAME, 0, LineStyle.SOLID_LITERAL);
        changeBorderSpecification(CENTER_CLASS_NAME, 0, LineStyle.SOLID_LITERAL);
        changeBorderSpecification(RIGHT_PKG_NAME, 0, LineStyle.SOLID_LITERAL);
        TestsUtil.synchronizationWithUIThread();

        checkRegionContainer(FIRST_REGION_CONTAINER_NAME, 0, LineStyle.SOLID_LITERAL, regionContainerCorners);
        checkRegion(LEFT_CLASS_NAME, 0, regionBorderSize, LineStyle.SOLID_LITERAL, regionCorners, regionContainerCorners, getFirstRegionExpectedSpecificCorners(stackDirection));
        checkRegion(CENTER_CLASS_NAME, 1, 0, LineStyle.SOLID_LITERAL, regionCorners, regionContainerCorners);
        checkRegion(RIGHT_CLASS_NAME, 2, regionBorderSize, LineStyle.DASH_LITERAL, regionCorners, regionContainerCorners);
        checkRegion(LEFT_PKG_NAME, 3, regionBorderSize, LineStyle.DOT_LITERAL, regionCorners, regionContainerCorners);
        checkRegion(CENTER_PKG_NAME, 4, regionBorderSize, LineStyle.DASH_DOT_LITERAL, regionCorners, regionContainerCorners);
        checkRegion(RIGHT_PKG_NAME, 5, 0, LineStyle.SOLID_LITERAL, regionCorners, regionContainerCorners, getLastRegionExpectedSpecificCorners(stackDirection));
    }

    /**
     * Ensure that borders changes in a horizontal compartment stack are correctly done.
     */
    public void testHorizontalStackCornerChanges() {
        diagram = (DDiagram) getRepresentations(HORIZONTAL_STACK_REPRESENTATION_NAME).iterator().next();
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        doTestCornerChanges(ContainerLayout.HORIZONTAL_STACK, 4, 2);
    }

    /**
     * Ensure that borders changes in a vertical compartment stack are correctly done.
     */
    public void testVerticalStackCornerChanges() {
        diagram = (DDiagram) getRepresentations(VERTICAL_STACK_REPRESENTATION_NAME).iterator().next();
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        doTestCornerChanges(ContainerLayout.VERTICAL_STACK, 5, 1);
    }

    private void doTestCornerChanges(ContainerLayout stackDirection, int regionContainerBorderSize, int regionBorderSize) {
        int regionContainerCorners = 0;
        int regionCorners = 0;

        // Default behavior: rounded rc corner, region without rounded: already
        // tested in initial layout tests.

        // Step 1: no rounded corner at all: no overlap
        changeCornerSpecification(FIRST_REGION_CONTAINER_NAME, false, regionContainerCorners);
        changeCornerSpecification(LEFT_CLASS_NAME, false, regionCorners);
        changeCornerSpecification(CENTER_CLASS_NAME, false, regionCorners);
        changeCornerSpecification(RIGHT_CLASS_NAME, false, regionCorners);
        changeCornerSpecification(LEFT_PKG_NAME, false, regionCorners);
        changeCornerSpecification(CENTER_PKG_NAME, false, regionCorners);
        changeCornerSpecification(RIGHT_PKG_NAME, false, regionCorners);
        TestsUtil.synchronizationWithUIThread();

        checkRegionContainer(FIRST_REGION_CONTAINER_NAME, regionContainerBorderSize, LineStyle.SOLID_LITERAL, regionContainerCorners);
        checkRegion(LEFT_CLASS_NAME, 0, regionBorderSize, LineStyle.SOLID_LITERAL, regionCorners, regionContainerCorners);
        checkRegion(CENTER_CLASS_NAME, 1, regionBorderSize, LineStyle.SOLID_LITERAL, regionCorners, regionContainerCorners);
        checkRegion(RIGHT_CLASS_NAME, 2, regionBorderSize, LineStyle.DASH_LITERAL, regionCorners, regionContainerCorners);
        checkRegion(LEFT_PKG_NAME, 3, regionBorderSize, LineStyle.DOT_LITERAL, regionCorners, regionContainerCorners);
        checkRegion(CENTER_PKG_NAME, 4, regionBorderSize, LineStyle.DASH_DOT_LITERAL, regionCorners, regionContainerCorners);
        checkRegion(RIGHT_PKG_NAME, 5, regionBorderSize, LineStyle.SOLID_LITERAL, regionCorners, regionContainerCorners);

        // Step 2: rounded corners on region only (10 pix): no overlap
        regionCorners = 10;
        changeCornerSpecification(LEFT_CLASS_NAME, true, regionCorners);
        changeCornerSpecification(CENTER_CLASS_NAME, true, regionCorners);
        changeCornerSpecification(RIGHT_CLASS_NAME, true, regionCorners);
        changeCornerSpecification(LEFT_PKG_NAME, true, regionCorners);
        changeCornerSpecification(CENTER_PKG_NAME, true, regionCorners);
        changeCornerSpecification(RIGHT_PKG_NAME, true, regionCorners);
        TestsUtil.synchronizationWithUIThread();

        checkRegionContainer(FIRST_REGION_CONTAINER_NAME, regionContainerBorderSize, LineStyle.SOLID_LITERAL, regionContainerCorners);
        checkRegion(LEFT_CLASS_NAME, 0, regionBorderSize, LineStyle.SOLID_LITERAL, regionCorners, regionContainerCorners);
        checkRegion(CENTER_CLASS_NAME, 1, regionBorderSize, LineStyle.SOLID_LITERAL, regionCorners, regionContainerCorners);
        checkRegion(RIGHT_CLASS_NAME, 2, regionBorderSize, LineStyle.DASH_LITERAL, regionCorners, regionContainerCorners);
        checkRegion(LEFT_PKG_NAME, 3, regionBorderSize, LineStyle.DOT_LITERAL, regionCorners, regionContainerCorners);
        checkRegion(CENTER_PKG_NAME, 4, regionBorderSize, LineStyle.DASH_DOT_LITERAL, regionCorners, regionContainerCorners);
        checkRegion(RIGHT_PKG_NAME, 5, regionBorderSize, LineStyle.SOLID_LITERAL, regionCorners, regionContainerCorners);

        // Step 3: rounded RC corners (20 pix) and rounded region corners (10
        // pix): first/last region should not overlap the region container
        // corners.
        regionContainerCorners = 20;
        changeCornerSpecification(FIRST_REGION_CONTAINER_NAME, true, regionContainerCorners);
        TestsUtil.synchronizationWithUIThread();

        checkRegionContainer(FIRST_REGION_CONTAINER_NAME, regionContainerBorderSize, LineStyle.SOLID_LITERAL, regionContainerCorners);
        checkRegion(LEFT_CLASS_NAME, 0, regionBorderSize, LineStyle.SOLID_LITERAL, regionCorners, regionContainerCorners, getFirstRegionExpectedSpecificCorners(stackDirection));
        checkRegion(CENTER_CLASS_NAME, 1, regionBorderSize, LineStyle.SOLID_LITERAL, regionCorners, regionContainerCorners);
        checkRegion(RIGHT_CLASS_NAME, 2, regionBorderSize, LineStyle.DASH_LITERAL, regionCorners, regionContainerCorners);
        checkRegion(LEFT_PKG_NAME, 3, regionBorderSize, LineStyle.DOT_LITERAL, regionCorners, regionContainerCorners);
        checkRegion(CENTER_PKG_NAME, 4, regionBorderSize, LineStyle.DASH_DOT_LITERAL, regionCorners, regionContainerCorners);
        checkRegion(RIGHT_PKG_NAME, 5, regionBorderSize, LineStyle.SOLID_LITERAL, regionCorners, regionContainerCorners, getLastRegionExpectedSpecificCorners(stackDirection));

        // Step 4: rounded RC corners (10 pix) and rounded region corners (20
        // pix): no specific corner
        regionContainerCorners = 10;
        regionCorners = 20;
        changeCornerSpecification(FIRST_REGION_CONTAINER_NAME, true, regionContainerCorners);
        changeCornerSpecification(LEFT_CLASS_NAME, true, regionCorners);
        changeCornerSpecification(CENTER_CLASS_NAME, true, regionCorners);
        changeCornerSpecification(RIGHT_CLASS_NAME, true, regionCorners);
        changeCornerSpecification(LEFT_PKG_NAME, true, regionCorners);
        changeCornerSpecification(CENTER_PKG_NAME, true, regionCorners);
        changeCornerSpecification(RIGHT_PKG_NAME, true, regionCorners);
        TestsUtil.synchronizationWithUIThread();

        checkRegionContainer(FIRST_REGION_CONTAINER_NAME, regionContainerBorderSize, LineStyle.SOLID_LITERAL, regionContainerCorners);
        checkRegion(LEFT_CLASS_NAME, 0, regionBorderSize, LineStyle.SOLID_LITERAL, regionCorners, regionContainerCorners);
        checkRegion(CENTER_CLASS_NAME, 1, regionBorderSize, LineStyle.SOLID_LITERAL, regionCorners, regionContainerCorners);
        checkRegion(RIGHT_CLASS_NAME, 2, regionBorderSize, LineStyle.DASH_LITERAL, regionCorners, regionContainerCorners);
        checkRegion(LEFT_PKG_NAME, 3, regionBorderSize, LineStyle.DOT_LITERAL, regionCorners, regionContainerCorners);
        checkRegion(CENTER_PKG_NAME, 4, regionBorderSize, LineStyle.DASH_DOT_LITERAL, regionCorners, regionContainerCorners);
        checkRegion(RIGHT_PKG_NAME, 5, regionBorderSize, LineStyle.SOLID_LITERAL, regionCorners, regionContainerCorners);
    }

    /**
     * Test the content panes.
     */
    public void testHorizontalStackContentPanes() {
        diagram = (DDiagram) getRepresentations(HORIZONTAL_STACK_REPRESENTATION_NAME).iterator().next();
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        doTestContentPanes(ContainerLayout.HORIZONTAL_STACK);
    }

    /**
     * Test the content panes.
     */
    public void testVerticalStackContentPanes() {
        diagram = (DDiagram) getRepresentations(VERTICAL_STACK_REPRESENTATION_NAME).iterator().next();
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        doTestContentPanes(ContainerLayout.VERTICAL_STACK);
    }

    private void doTestContentPanes(ContainerLayout verticalStack) {
        // Step 1: no rounded corner at all : no overlap
        checkContentPanes(FIRST_REGION_CONTAINER_NAME, true, true);
        checkContentPanes(LEFT_CLASS_NAME, true, true);
        checkContentPanes(CENTER_CLASS_NAME, true, false);
        checkContentPanes(LEFT_PKG_NAME, false, false);
        checkContentPanes(CENTER_PKG_NAME, true, true);

        // Step 2: Change elements names to change the style description
        // selection (and the label border style)
        // No change on RC as the border will always be shown for RC's content
        // pane.
        String newClass1Name = "Center_class1";
        String newClass2Name = "Left_class2";
        String newPkg3Name = "Center_p3";
        String newPkg4Name = "Left_p4";
        changeElementName(LEFT_CLASS_NAME, newClass1Name);
        changeElementName(CENTER_CLASS_NAME, newClass2Name);
        changeElementName(LEFT_PKG_NAME, newPkg3Name);
        changeElementName(CENTER_PKG_NAME, newPkg4Name);
        TestsUtil.synchronizationWithUIThread();

        checkContentPanes(FIRST_REGION_CONTAINER_NAME, true, true);
        checkContentPanes(newClass1Name, true, false);
        checkContentPanes(newClass2Name, true, true);
        checkContentPanes(newPkg3Name, true, true);
        checkContentPanes(newPkg4Name, false, false);
    }

    /**
     * Test the region reorder from semantic changes.
     */
    public void testReorderHorizontalStack() {
        diagram = (DDiagram) getRepresentations(HORIZONTAL_STACK_REPRESENTATION_NAME).iterator().next();
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        changeSemanticOrder();

        // See initial indexes and bounds in testHorizontalStackInitialLayout
        // and doTestInitialLayout

        doTestReorderedLayout(ContainerLayout.HORIZONTAL_STACK, 4, 20, 2);

        checkBounds(FIRST_REGION_CONTAINER_NAME, new Rectangle(0, 0, -1, -1), INITIAL_HORIZONTAL_FIRST_REGION_CONTAINER_BOUNDS, 0, 1);
        Rectangle centerClassBoundsExpectedAfterReorder = new Rectangle(INITIAL_HORIZONTAL_LEFT_CLASS_BOUNDS.x, INITIAL_HORIZONTAL_CENTER_CLASS_BOUNDS.y, INITIAL_HORIZONTAL_CENTER_CLASS_BOUNDS.width,
                INITIAL_HORIZONTAL_CENTER_CLASS_BOUNDS.height);
        checkBounds(CENTER_CLASS_NAME, centerClassBoundsExpectedAfterReorder, centerClassBoundsExpectedAfterReorder);
        Rectangle leftClassBoundsExpectedAfterReorder = new Rectangle(INITIAL_HORIZONTAL_CENTER_CLASS_BOUNDS.width, INITIAL_HORIZONTAL_LEFT_CLASS_BOUNDS.y, INITIAL_HORIZONTAL_LEFT_CLASS_BOUNDS.width,
                INITIAL_HORIZONTAL_LEFT_CLASS_BOUNDS.height);
        checkBounds(LEFT_CLASS_NAME, leftClassBoundsExpectedAfterReorder, leftClassBoundsExpectedAfterReorder);
        checkBounds(RIGHT_CLASS_NAME, INITIAL_HORIZONTAL_RIGHT_CLASS_BOUNDS, INITIAL_HORIZONTAL_RIGHT_CLASS_BOUNDS);
        Rectangle rightPackageBoundsExpectedAfterReorder = new Rectangle(INITIAL_HORIZONTAL_LEFT_PKG_BOUNDS.x, INITIAL_HORIZONTAL_RIGHT_PKG_BOUNDS.y, INITIAL_HORIZONTAL_RIGHT_PKG_BOUNDS.width,
                INITIAL_HORIZONTAL_RIGHT_PKG_BOUNDS.height);
        checkBounds(RIGHT_PKG_NAME, rightPackageBoundsExpectedAfterReorder, rightPackageBoundsExpectedAfterReorder);
        Rectangle leftPackageBoundsExpectedAfterReorder = new Rectangle(INITIAL_HORIZONTAL_LEFT_PKG_BOUNDS.x + INITIAL_HORIZONTAL_RIGHT_PKG_BOUNDS.width, INITIAL_HORIZONTAL_LEFT_PKG_BOUNDS.y,
                INITIAL_HORIZONTAL_LEFT_PKG_BOUNDS.width, INITIAL_HORIZONTAL_LEFT_PKG_BOUNDS.height);
        checkBounds(LEFT_PKG_NAME, leftPackageBoundsExpectedAfterReorder, leftPackageBoundsExpectedAfterReorder);
        Rectangle centerPackageBoundsExpectedAfterReorder = new Rectangle(INITIAL_HORIZONTAL_LEFT_PKG_BOUNDS.x + INITIAL_HORIZONTAL_RIGHT_PKG_BOUNDS.width + INITIAL_HORIZONTAL_LEFT_PKG_BOUNDS.width,
                INITIAL_HORIZONTAL_CENTER_PKG_BOUNDS.y, INITIAL_HORIZONTAL_CENTER_PKG_BOUNDS.width, INITIAL_HORIZONTAL_CENTER_PKG_BOUNDS.height);
        checkBounds(CENTER_PKG_NAME, centerPackageBoundsExpectedAfterReorder, centerPackageBoundsExpectedAfterReorder);

    }

    /**
     * Test the region reorder from semantic changes.
     */
    public void testReorderVerticalStack() {
        diagram = (DDiagram) getRepresentations(VERTICAL_STACK_REPRESENTATION_NAME).iterator().next();
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        changeSemanticOrder();

        // See initial indexes and bounds in testVerticalStackInitialLayout and
        // doTestInitialLayout

        doTestReorderedLayout(ContainerLayout.VERTICAL_STACK, 5, 10, 1);

        // Each vertical region can have a delta according to what is expected
        // because of font height on each OS. Height deltas of each region have
        // an incidence on the global container height. We must cumulate them to
        // make a reasonable assert at the end.
        int heightDeltas = 0;

        // Delta of 5 pixels because there are 5 lines (with potential 1 delta
        // pixel)
        // 1 is removed to the INITIAL_VERTICAL_CENTER_CLASS_BOUNDS.height as
        // this region is now the first one. This is because of bug 496742 that
        // will be fixed later.
        Rectangle centerClassBounds = checkBounds(CENTER_CLASS_NAME, new Rectangle(0, 0, -1, -1),
                new Rectangle(0, 0, INITIAL_VERTICAL_CENTER_CLASS_BOUNDS.width, INITIAL_VERTICAL_CENTER_CLASS_BOUNDS.height - 1), 0, 5);
        heightDeltas += centerClassBounds.height - INITIAL_VERTICAL_CENTER_CLASS_BOUNDS.height - 1;
        // Delta of 5 pixels because there are 5 lines (with potential 1 delta
        // pixel)
        Rectangle leftClassBounds = checkBounds(LEFT_CLASS_NAME, new Rectangle(0, centerClassBounds.y + centerClassBounds.height + 1, -1, -1),
                INITIAL_VERTICAL_LEFT_CLASS_BOUNDS.getTranslated(0, centerClassBounds.height).getResized(0, 1), 0, 5);
        heightDeltas += leftClassBounds.height - INITIAL_VERTICAL_LEFT_CLASS_BOUNDS.height + 1;
        // Delta of 2 pixels because there are 2 lines (with potential 1 delta
        // pixel)
        Rectangle rightClassBounds = checkBounds(RIGHT_CLASS_NAME, new Rectangle(0, leftClassBounds.y + leftClassBounds.height, -1, -1),
                INITIAL_VERTICAL_RIGHT_CLASS_BOUNDS.getTranslated(0, heightDeltas), 0, 2);
        heightDeltas += rightClassBounds.height - INITIAL_VERTICAL_RIGHT_CLASS_BOUNDS.height;
        Rectangle rightPkgBounds = checkBounds(RIGHT_PKG_NAME, new Rectangle(0, rightClassBounds.y + rightClassBounds.height, -1, -1),
                INITIAL_VERTICAL_LEFT_PKG_BOUNDS.getTranslated(0, heightDeltas).setSize(INITIAL_VERTICAL_RIGHT_PKG_BOUNDS.width, INITIAL_VERTICAL_RIGHT_PKG_BOUNDS.height));
        heightDeltas += rightPkgBounds.height - INITIAL_VERTICAL_RIGHT_PKG_BOUNDS.height;
        Rectangle leftPkgBounds = checkBounds(LEFT_PKG_NAME, new Rectangle(0, rightPkgBounds.y + rightPkgBounds.height, -1, -1),
                INITIAL_VERTICAL_LEFT_PKG_BOUNDS.getCopy().setLocation(0, rightPkgBounds.y + rightPkgBounds.height));
        heightDeltas += leftPkgBounds.height - INITIAL_VERTICAL_LEFT_PKG_BOUNDS.height;
        // Delta of 1 pixel because there is 1 line (with potential 1 delta
        // pixel)
        Rectangle centerPkgBounds = checkBounds(CENTER_PKG_NAME, new Rectangle(0, leftPkgBounds.y + leftPkgBounds.height, -1, -1),
                INITIAL_VERTICAL_CENTER_PKG_BOUNDS.getCopy().setLocation(0, leftPkgBounds.y + leftPkgBounds.height), 0, 1);
        heightDeltas += centerPkgBounds.height - INITIAL_VERTICAL_CENTER_PKG_BOUNDS.height;
        Rectangle firstRegionBounds = checkBounds(FIRST_REGION_CONTAINER_NAME, new Rectangle(64, 16, -1, -1), new Rectangle(64, 16, 141, -1));
        assertEquals("Wrong Draw2D height for " + FIRST_REGION_CONTAINER_NAME, INITIAL_VERTICAL_FIRST_REGION_CONTAINER_BOUNDS.height + heightDeltas, firstRegionBounds.height(), 1);
    }

    /**
     * Test the layout after a deletion of a semantic element represented by the first region of a HStack container.
     */
    public void testRemoveSemanticElementInHorizontalStack() {
        diagram = (DDiagram) getRepresentations(HORIZONTAL_STACK_REPRESENTATION_NAME).iterator().next();
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        removeOneSemanticElementRepresentedByRegion();

        doTestRemoveSemantic(ContainerLayout.HORIZONTAL_STACK, 4, 20, 2);

        checkBounds(FIRST_REGION_CONTAINER_NAME, new Rectangle(0, 0, -1, -1), INITIAL_HORIZONTAL_FIRST_REGION_CONTAINER_BOUNDS.getResized(-INITIAL_HORIZONTAL_LEFT_CLASS_BOUNDS.width, 0), 0, 1);
        checkBounds(CENTER_CLASS_NAME, new Rectangle(0, 0, 136, 211), new Rectangle(0, 0, 136, 211));
        checkBounds(RIGHT_CLASS_NAME, new Rectangle(136, 0, 130, 211), new Rectangle(136, 0, 130, 211));
        checkBounds(LEFT_PKG_NAME, new Rectangle(266, 0, 122, 211), new Rectangle(266, 0, 122, 211));
        checkBounds(CENTER_PKG_NAME, new Rectangle(388, 0, 156, 211), new Rectangle(388, 0, 156, 211));
        checkBounds(RIGHT_PKG_NAME, new Rectangle(544, 0, 112, 211), new Rectangle(544, 0, 112, 211));
    }

    /**
     * Test the layout after a deletion of a semantic element represented by the first region of a VStack container.
     */
    public void testRemoveSemanticElementInVerticalStack() {
        diagram = (DDiagram) getRepresentations(VERTICAL_STACK_REPRESENTATION_NAME).iterator().next();
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        removeOneSemanticElementRepresentedByRegion();

        doTestRemoveSemantic(ContainerLayout.VERTICAL_STACK, 5, 10, 1);

        // Each vertical region can have a delta according to what is expected
        // because of font height on each OS. Height deltas of each region have
        // an incidence on the global container height. We must cumulate them to
        // make a reasonable assert at the end.
        int heightDeltas = 0;

        // Delta of 5 pixels because there are 5 lines (with potential 1 delta
        // pixel)
        Rectangle centerClassBounds = checkBounds(CENTER_CLASS_NAME, new Rectangle(0, 0, -1, -1), INITIAL_VERTICAL_CENTER_CLASS_BOUNDS.getCopy().setLocation(0, 0).getResized(0, -1), 0, 5);
        heightDeltas += centerClassBounds.height - INITIAL_VERTICAL_CENTER_CLASS_BOUNDS.height;
        // Delta of 2 pixels because there are 2 lines (with potential 1 delta
        // pixel)
        Rectangle rightClassBounds = checkBounds(RIGHT_CLASS_NAME, new Rectangle(0, centerClassBounds.y + centerClassBounds.height + 1, -1, -1),
                INITIAL_VERTICAL_RIGHT_CLASS_BOUNDS.getTranslated(0, heightDeltas - INITIAL_VERTICAL_LEFT_CLASS_BOUNDS.height), 0, 2);
        heightDeltas += rightClassBounds.height - INITIAL_VERTICAL_RIGHT_CLASS_BOUNDS.height;
        Rectangle leftPkgBounds = checkBounds(LEFT_PKG_NAME, new Rectangle(0, rightClassBounds.y + rightClassBounds.height + 1, -1, -1),
                INITIAL_VERTICAL_LEFT_PKG_BOUNDS.getTranslated(0, heightDeltas - INITIAL_VERTICAL_LEFT_CLASS_BOUNDS.height));
        heightDeltas += leftPkgBounds.height - INITIAL_VERTICAL_LEFT_PKG_BOUNDS.height;
        // Delta of 1 pixel because there is 1 line (with potential 1 delta
        // pixel)
        Rectangle centerPkgBounds = checkBounds(CENTER_PKG_NAME, new Rectangle(0, leftPkgBounds.y + leftPkgBounds.height + 1, -1, -1),
                INITIAL_VERTICAL_CENTER_PKG_BOUNDS.getTranslated(0, heightDeltas - INITIAL_VERTICAL_LEFT_CLASS_BOUNDS.height), 0, 1);
        heightDeltas += centerPkgBounds.height - INITIAL_VERTICAL_CENTER_PKG_BOUNDS.height;
        Rectangle rightPkgBounds = checkBounds(RIGHT_PKG_NAME, new Rectangle(0, centerPkgBounds.y + centerPkgBounds.height + 1, -1, -1),
                INITIAL_VERTICAL_RIGHT_PKG_BOUNDS.getTranslated(0, heightDeltas - INITIAL_VERTICAL_LEFT_CLASS_BOUNDS.height));
        heightDeltas += rightPkgBounds.height - INITIAL_VERTICAL_RIGHT_PKG_BOUNDS.height;
        Rectangle firstRegionBounds = checkBounds(FIRST_REGION_CONTAINER_NAME, new Rectangle(64, 16, -1, -1), new Rectangle(64, 16, 141, -1));
        assertEquals("Wrong Draw2D height for " + FIRST_REGION_CONTAINER_NAME, INITIAL_VERTICAL_FIRST_REGION_CONTAINER_BOUNDS.height + heightDeltas - INITIAL_VERTICAL_LEFT_CLASS_BOUNDS.height,
                firstRegionBounds.height(), 1);
    }

    /**
     * Test the layout after an add of a semantic element represented by a region of a HStack container.
     */
    public void testAddSemanticElementInHorizontalStack() {
        diagram = (DDiagram) getRepresentations(HORIZONTAL_STACK_REPRESENTATION_NAME).iterator().next();
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        addOneSemanticElementRepresentedByRegion();

        doTestAddSemantic(ContainerLayout.HORIZONTAL_STACK, 4, 20, 2);

        // We add 40 pixels (default region size for a new list) at each x GMF
        // location after inserted element.
        checkBounds(FIRST_REGION_CONTAINER_NAME, new Rectangle(0, 0, -1, -1),
                INITIAL_HORIZONTAL_FIRST_REGION_CONTAINER_BOUNDS.getResized(-INITIAL_HORIZONTAL_FIRST_REGION_CONTAINER_BOUNDS.width() - 1, 0), 0, 1);
        Rectangle leftClassBounds = checkBounds(LEFT_CLASS_NAME, INITIAL_HORIZONTAL_LEFT_CLASS_BOUNDS, INITIAL_HORIZONTAL_LEFT_CLASS_BOUNDS);
        Rectangle leftClass2Bounds = checkBounds(LEFT_CLASS2_NAME, new Rectangle(leftClassBounds.width, 0, -1, -1), new Rectangle(leftClassBounds.width, 0, -1, 211));
        Rectangle centerClassBounds = checkBounds(CENTER_CLASS_NAME, new Rectangle(INITIAL_HORIZONTAL_CENTER_CLASS_BOUNDS.x + LayoutUtils.NEW_DEFAULT_CONTAINER_DIMENSION.width, 0, 136, 211),
                new Rectangle(leftClass2Bounds.x + leftClass2Bounds.width, 0, 136, 211));
        Rectangle rightClassBounds = checkBounds(RIGHT_CLASS_NAME, new Rectangle(INITIAL_HORIZONTAL_RIGHT_CLASS_BOUNDS.x + LayoutUtils.NEW_DEFAULT_CONTAINER_DIMENSION.width, 0, 130, 211),
                new Rectangle(centerClassBounds.x + centerClassBounds.width, 0, 130, 211));
        Rectangle leftPkgBounds = checkBounds(LEFT_PKG_NAME, new Rectangle(INITIAL_HORIZONTAL_LEFT_PKG_BOUNDS.x + LayoutUtils.NEW_DEFAULT_CONTAINER_DIMENSION.width, 0, 122, 211),
                new Rectangle(rightClassBounds.x + rightClassBounds.width, 0, 122, 211));
        Rectangle centerPkgBounds = checkBounds(CENTER_PKG_NAME, new Rectangle(INITIAL_HORIZONTAL_CENTER_PKG_BOUNDS.x + LayoutUtils.NEW_DEFAULT_CONTAINER_DIMENSION.width, 0, 156, 211),
                new Rectangle(leftPkgBounds.x + leftPkgBounds.width, 0, 156, 211));
        checkBounds(RIGHT_PKG_NAME, new Rectangle(INITIAL_HORIZONTAL_RIGHT_PKG_BOUNDS.x + LayoutUtils.NEW_DEFAULT_CONTAINER_DIMENSION.width, 0, 112, 211),
                new Rectangle(centerPkgBounds.x + centerPkgBounds.width, 0, 112, 211));
    }

    /**
     * Test the layout after an add of a semantic element represented by a region of a VStack container.
     */
    public void testAddSemanticElementInVerticalStack() {
        diagram = (DDiagram) getRepresentations(VERTICAL_STACK_REPRESENTATION_NAME).iterator().next();
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        addOneSemanticElementRepresentedByRegion();

        doTestAddSemantic(ContainerLayout.VERTICAL_STACK, 5, 10, 1);

        // Each vertical region can have a delta according to what is expected
        // because of font height on each OS. Height deltas of each region have
        // an incidence on the global container height. We must cumulate them to
        // make a reasonable assert at the end.
        int heightDeltas = 0;

        // Delta of 5 pixels because there are 5 lines (with potential 1 delta
        // pixel)
        Rectangle leftClassBounds = checkBounds(LEFT_CLASS_NAME, new Rectangle(0, 0, -1, -1), INITIAL_VERTICAL_LEFT_CLASS_BOUNDS, 0, 5);
        heightDeltas += leftClassBounds.height - INITIAL_VERTICAL_LEFT_CLASS_BOUNDS.height;
        // Delta of 1 pixel because there is 1 line (with potential 1 delta
        // pixel)
        Rectangle leftClass2Bounds = checkBounds(LEFT_CLASS2_NAME, new Rectangle(0, leftClassBounds.height, -1, -1), INITIAL_VERTICAL_LEFT_CLASS2_BOUNDS.getTranslated(0, heightDeltas), 0, 1);
        heightDeltas += leftClass2Bounds.height - INITIAL_VERTICAL_LEFT_CLASS2_BOUNDS.height;
        // Delta of 5 pixels because there are 5 lines (with potential 1 delta
        // pixel)
        Rectangle centerClassBounds = checkBounds(CENTER_CLASS_NAME, new Rectangle(0, leftClass2Bounds.y + leftClass2Bounds.height, -1, -1),
                INITIAL_VERTICAL_CENTER_CLASS_BOUNDS.getTranslated(0, heightDeltas + leftClass2Bounds.height), 0, 5);
        heightDeltas += centerClassBounds.height - INITIAL_VERTICAL_CENTER_CLASS_BOUNDS.height;
        // Delta of 2 pixels because there are 2 lines (with potential 1 delta
        // pixel)
        Rectangle rightClassBounds = checkBounds(RIGHT_CLASS_NAME, new Rectangle(0, centerClassBounds.y + centerClassBounds.height, -1, -1),
                INITIAL_VERTICAL_RIGHT_CLASS_BOUNDS.getTranslated(0, heightDeltas + leftClass2Bounds.height), 0, 2);
        heightDeltas += rightClassBounds.height - INITIAL_VERTICAL_RIGHT_CLASS_BOUNDS.height;
        Rectangle leftPkgBounds = checkBounds(LEFT_PKG_NAME, new Rectangle(0, rightClassBounds.y + rightClassBounds.height, -1, -1),
                INITIAL_VERTICAL_LEFT_PKG_BOUNDS.getTranslated(0, heightDeltas + leftClass2Bounds.height));
        heightDeltas += leftPkgBounds.height - INITIAL_VERTICAL_LEFT_PKG_BOUNDS.height;
        // Delta of 1 pixel because there is 1 line (with potential 1 delta
        // pixel)
        Rectangle centerPkgBounds = checkBounds(CENTER_PKG_NAME, new Rectangle(0, leftPkgBounds.y + leftPkgBounds.height, -1, -1),
                INITIAL_VERTICAL_CENTER_PKG_BOUNDS.getTranslated(0, heightDeltas + leftClass2Bounds.height), 0, 1);
        heightDeltas += centerPkgBounds.height - INITIAL_VERTICAL_CENTER_PKG_BOUNDS.height;
        Rectangle rightPkgBounds = checkBounds(RIGHT_PKG_NAME, new Rectangle(0, centerPkgBounds.y + centerPkgBounds.height, -1, -1),
                INITIAL_VERTICAL_RIGHT_PKG_BOUNDS.getTranslated(0, heightDeltas + leftClass2Bounds.height));
        heightDeltas += rightPkgBounds.height - INITIAL_VERTICAL_RIGHT_PKG_BOUNDS.height;
        Rectangle firstRegionBounds = checkBounds(FIRST_REGION_CONTAINER_NAME, new Rectangle(64, 16, -1, -1), new Rectangle(64, 16, 141, -1));
        assertEquals("Wrong Draw2D height for " + FIRST_REGION_CONTAINER_NAME, INITIAL_VERTICAL_FIRST_REGION_CONTAINER_BOUNDS.height + heightDeltas + leftClass2Bounds.height,
                firstRegionBounds.height(), 1);
    }

    public void testLayoutAtCreationHStackWithoutLabelWithAListRegionWithDefinedSize() {
        testLayoutAtCreationHStackWithoutLabelWithAListRegionWithDefinedSize(new Rectangle(0, 0, -1, -1), new Rectangle(0, 0, 100, 50));
    }
    
    public void testLayoutAtCreationHStackWithoutLabelWithAListRegionWithDefinedSize_autoSizeAtArrangeDisabled() {
        //Change preference to disable the auto size at arrange.
        changeDiagramUIPreference(SiriusDiagramUiInternalPreferencesKeys.PREF_AUTOSIZE_ON_ARRANGE.name(), false);
        // Launch same test but with another value for "autosize" pref and another expected
        testLayoutAtCreationHStackWithoutLabelWithAListRegionWithDefinedSize(new Rectangle(0, 0, 100, 50), new Rectangle(0, 0, 100, 50));
    }
    
    public void testLayoutAtCreationHStackWithoutLabelWithAListRegionWithDefinedSize(Rectangle expectedGmfBounds, Rectangle expectedDraw2dBounds) {
        // We create a new diagram
        EObject root = session.getSemanticResources().stream().findFirst().get().getContents().get(0);
        assertTrue("The root of the semantic model must be an EPackage", root instanceof EPackage);
        EPackage semanticRootForDiagram = ((EPackage) root).getESubpackages().get(1);
        diagram = (DDiagram) createRepresentation("DiagHStackWithSizedList", semanticRootForDiagram);
        // We open the editor
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        // Check that bounds for the 2 lists are the same
        checkSameBounds("Cl1", "Cl2");
        // Check the list size at opening
        checkBounds("Cl1", expectedGmfBounds, expectedDraw2dBounds);
        // Save and close
        session.save(new NullProgressMonitor());
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
        // Reopen the editor and check the size again
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        // Check that bounds for the 2 lists are the same
        checkSameBounds("Cl1", "Cl2");
        checkBounds("Cl1", expectedGmfBounds, expectedDraw2dBounds);
    }
    
    public void testLayoutAtCreationHStackWithoutLabelWithAListRegionWithAutoSize() {
        // We create a new diagram
        EObject root = session.getSemanticResources().stream().findFirst().get().getContents().get(0);
        assertTrue("The root of the semantic model must be an EPackage", root instanceof EPackage);
        EPackage semanticRootForDiagram = ((EPackage) root).getESubpackages().get(1);
        diagram = (DDiagram) createRepresentation("DiagHStackWithAutoSizedList", semanticRootForDiagram);
        // We open the editor
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        // Check that bounds for the 2 lists are the same
        checkSameBounds("Cl1", "Cl2");
        // Check the list size at opening
        Rectangle autoSizeBounds = new Rectangle(0, 0, -1, -1);
        Rectangle expectedBounds = new Rectangle(new Point(0, 0), LayoutUtils.NEW_DEFAULT_CONTAINER_DIMENSION);
        checkBounds("Cl1", autoSizeBounds, expectedBounds);
        // Save and close
        session.save(new NullProgressMonitor());
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
        // Reopen the editor and check the size again
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        // Check that bounds for the 2 lists are the same
        checkSameBounds("Cl1", "Cl2");
        checkBounds("Cl1", autoSizeBounds, expectedBounds);
    }

    public void testLayoutAtCreationHStackWithoutLabelWithAListRegionWithAutoSize_autoSizeAtArrangeDisabled() {
        // Change preference to disable the auto size at arrange.
        changeDiagramUIPreference(SiriusDiagramUiInternalPreferencesKeys.PREF_AUTOSIZE_ON_ARRANGE.name(), false);
        // Launch same test but with another value for "autosize" pref
        testLayoutAtCreationHStackWithoutLabelWithAListRegionWithAutoSize();
    }

    public void testLayoutAtCreationHStackWithLabelWithAListRegionWithDefinedSize() {
        // The current borders width ("computed" from the current draw2d construction, a kind of magic number...)
        int bordersWidth = 4;
        // We create a new diagram
        EObject root = session.getSemanticResources().stream().findFirst().get().getContents().get(0);
        assertTrue("The root of the semantic model must be an EPackage", root instanceof EPackage);
        EPackage semanticRootForDiagram = ((EPackage) root).getESubpackages().get(1);
        diagram = (DDiagram) createRepresentation("DiagHStackWithLabelWithAutoSizedList", semanticRootForDiagram);
        // We open the editor
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        // Check the list size, according to its container, at opening
        Rectangle autoSizeBounds = new Rectangle(0, 0, -1, -1);
        // The expected bounds is the same width than its container (minus the border size, here 4 pixels).
        DDiagramElementContainer container1 = getDiagramElementsFromLabel(diagram, "diagHStackWithSizedList1", DDiagramElementContainer.class).get(0);
        AbstractDiagramElementContainerEditPart editPart = (AbstractDiagramElementContainerEditPart) getEditPart(container1);
        IFigure container1Figure = editPart.getFigure();

        Rectangle expectedBounds = new Rectangle(new Point(0, 0), new Dimension(container1Figure.getBounds().width() - bordersWidth, LayoutUtils.NEW_DEFAULT_CONTAINER_DIMENSION.height()));
        checkBounds("Cl1", autoSizeBounds, expectedBounds);
        // Save and close
        session.save(new NullProgressMonitor());
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
        // Reopen the editor and check the size again
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        // Check the list size, according to its container
        checkBounds("Cl1", autoSizeBounds, expectedBounds);
    }

    private void checkSameBounds(String labelOfFirstElementToCompare, String labelOfSecondElementToCompare) {
        DDiagramElementContainer region = getDiagramElementsFromLabel(diagram, labelOfSecondElementToCompare, DDiagramElementContainer.class).get(0);
        AbstractDiagramElementContainerEditPart editPart = (AbstractDiagramElementContainerEditPart) getEditPart(region);
        IFigure mainFigure = editPart.getMainFigure();
        checkBounds(labelOfFirstElementToCompare, getBounds((Node) editPart.getNotationView()), mainFigure.getBounds());
    }

    private void changeSemanticOrder() {
        final EClass cl2 = (EClass) getDiagramElementsFromLabel(diagram, CENTER_CLASS_NAME).get(0).getTarget();
        final EPackage pkg5 = (EPackage) getDiagramElementsFromLabel(diagram, RIGHT_PKG_NAME).get(0).getTarget();
        final EPackage pkg = cl2.getEPackage();
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        domain.getCommandStack().execute(new RecordingCommand(domain) {
            @Override
            protected void doExecute() {
                pkg.getEClassifiers().move(0, cl2);
                pkg.getESubpackages().move(0, pkg5);
            }
        });
        TestsUtil.synchronizationWithUIThread();
    }

    private void removeOneSemanticElementRepresentedByRegion() {
        final EClass cl1 = (EClass) getDiagramElementsFromLabel(diagram, LEFT_CLASS_NAME).get(0).getTarget();
        final EPackage pkg = cl1.getEPackage();
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        domain.getCommandStack().execute(new RecordingCommand(domain) {
            @Override
            protected void doExecute() {
                pkg.getEClassifiers().remove(cl1);
            }
        });
        TestsUtil.synchronizationWithUIThread();
    }

    private void addOneSemanticElementRepresentedByRegion() {
        final EPackage pkg = (EPackage) getDiagramElementsFromLabel(diagram, FIRST_REGION_CONTAINER_NAME).get(0).getTarget();
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        domain.getCommandStack().execute(new RecordingCommand(domain) {
            @Override
            protected void doExecute() {
                EClass newClass = EcoreFactory.eINSTANCE.createEClass();
                newClass.setName(LEFT_CLASS2_NAME);
                pkg.getEClassifiers().add(1, newClass);
            }
        });
        TestsUtil.synchronizationWithUIThread();
    }

    private void doTestReorderedLayout(ContainerLayout stackDirection, int regionContainerBorderSize, int regionContainerCorners, int regionBorderSize) {
        int regionCorners = 0;

        checkRegionContainer(FIRST_REGION_CONTAINER_NAME, regionContainerBorderSize, LineStyle.SOLID_LITERAL, regionContainerCorners);
        checkRegion(LEFT_CLASS_NAME, 1, regionBorderSize, LineStyle.SOLID_LITERAL, regionCorners, regionContainerCorners);
        checkRegion(CENTER_CLASS_NAME, 0, regionBorderSize, LineStyle.SOLID_LITERAL, regionCorners, regionContainerCorners, getFirstRegionExpectedSpecificCorners(stackDirection));
        checkRegion(RIGHT_CLASS_NAME, 2, regionBorderSize, LineStyle.DASH_LITERAL, regionCorners, regionContainerCorners);
        checkRegion(LEFT_PKG_NAME, 4, regionBorderSize, LineStyle.DOT_LITERAL, regionCorners, regionContainerCorners);
        checkRegion(CENTER_PKG_NAME, 5, regionBorderSize, LineStyle.DASH_DOT_LITERAL, regionCorners, regionContainerCorners, getLastRegionExpectedSpecificCorners(stackDirection));
        checkRegion(RIGHT_PKG_NAME, 3, regionBorderSize, LineStyle.SOLID_LITERAL, regionCorners, regionContainerCorners);
    }

    private void doTestRemoveSemantic(ContainerLayout stackDirection, int regionContainerBorderSize, int regionContainerCorners, int regionBorderSize) {
        int regionCorners = 0;

        checkRegionContainer(FIRST_REGION_CONTAINER_NAME, regionContainerBorderSize, LineStyle.SOLID_LITERAL, regionContainerCorners);
        checkRegion(CENTER_CLASS_NAME, 0, regionBorderSize, LineStyle.SOLID_LITERAL, regionCorners, regionContainerCorners, getFirstRegionExpectedSpecificCorners(stackDirection));
        checkRegion(RIGHT_CLASS_NAME, 1, regionBorderSize, LineStyle.DASH_LITERAL, regionCorners, regionContainerCorners);
        checkRegion(LEFT_PKG_NAME, 2, regionBorderSize, LineStyle.DOT_LITERAL, regionCorners, regionContainerCorners);
        checkRegion(CENTER_PKG_NAME, 3, regionBorderSize, LineStyle.DASH_DOT_LITERAL, regionCorners, regionContainerCorners);
        checkRegion(RIGHT_PKG_NAME, 4, regionBorderSize, LineStyle.SOLID_LITERAL, regionCorners, regionContainerCorners, getLastRegionExpectedSpecificCorners(stackDirection));
    }

    private void doTestAddSemantic(ContainerLayout stackDirection, int regionContainerBorderSize, int regionContainerCorners, int regionBorderSize) {
        int regionCorners = 0;

        checkRegionContainer(FIRST_REGION_CONTAINER_NAME, regionContainerBorderSize, LineStyle.SOLID_LITERAL, regionContainerCorners);
        checkRegion(LEFT_CLASS_NAME, 0, regionBorderSize, LineStyle.SOLID_LITERAL, regionCorners, regionContainerCorners, getFirstRegionExpectedSpecificCorners(stackDirection));
        checkRegion(LEFT_CLASS2_NAME, 1, regionBorderSize, LineStyle.SOLID_LITERAL, regionCorners, regionContainerCorners);
        checkRegion(CENTER_CLASS_NAME, 2, regionBorderSize, LineStyle.SOLID_LITERAL, regionCorners, regionContainerCorners);
        checkRegion(RIGHT_CLASS_NAME, 3, regionBorderSize, LineStyle.DASH_LITERAL, regionCorners, regionContainerCorners);
        checkRegion(LEFT_PKG_NAME, 4, regionBorderSize, LineStyle.DOT_LITERAL, regionCorners, regionContainerCorners);
        checkRegion(CENTER_PKG_NAME, 5, regionBorderSize, LineStyle.DASH_DOT_LITERAL, regionCorners, regionContainerCorners);
        checkRegion(RIGHT_PKG_NAME, 6, regionBorderSize, LineStyle.SOLID_LITERAL, regionCorners, regionContainerCorners, getLastRegionExpectedSpecificCorners(stackDirection));
    }

    private void changeElementName(String label, final String newName) {
        DDiagramElementContainer ddec = getDiagramElementsFromLabel(diagram, label, DDiagramElementContainer.class).get(0);
        final ENamedElement namedElt = (ENamedElement) ddec.getTarget();

        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        domain.getCommandStack().execute(new RecordingCommand(domain) {
            @Override
            protected void doExecute() {
                namedElt.setName(newName);
            }
        });
    }

    private void checkContentPanes(String label, boolean separatedContentPane, boolean borderedContentPane) {
        DDiagramElementContainer ddec = getDiagramElementsFromLabel(diagram, label, DDiagramElementContainer.class).get(0);
        AbstractDiagramElementContainerEditPart editPart = (AbstractDiagramElementContainerEditPart) getEditPart(ddec);

        IFigure mainFigure = editPart.getMainFigure();
        IFigure primaryShape = editPart.getPrimaryShape();
        assertEquals(label + " should have two children parts.", 2, editPart.getChildren().size());

        ResizableCompartmentEditPart contentPaneEP = (ResizableCompartmentEditPart) editPart.getChildren().get(1);
        ResizableCompartmentFigure compartmentFigure = (ResizableCompartmentFigure) contentPaneEP.getFigure();
        IFigure parent = compartmentFigure.getParent();
        if (separatedContentPane) {
            assertEquals("Wrong content pane figure for compartment part of " + label, primaryShape, parent);
        } else {
            assertEquals("Wrong content pane figure for compartment part of " + label, mainFigure, parent);
            assertFalse("Border color refresh should be checked as this case was not expected before.", borderedContentPane);
        }
        assertTrue("Wrong layout manager for primary shape of " + label, primaryShape.getLayoutManager() instanceof ConstrainedToolbarLayout);
        assertTrue("Wrong layout manager for main figure of " + label, mainFigure.getLayoutManager() instanceof StackLayout);

        Border cpBorder = compartmentFigure.getBorder();
        if (borderedContentPane) {
            assertTrue(label + "should have a visible one side line border on its content pane.", cpBorder instanceof OneLineBorder);
            OneLineBorder oneLineBorder = (OneLineBorder) cpBorder;
            assertEquals("Wrong position for the content pane line border of" + label, PositionConstants.TOP, oneLineBorder.getPosition());
            assertEquals("Wrong content pane line border size for " + label, 1, oneLineBorder.getWidth());
            assertEquals("Wrong content pane line style for " + label, SWT.LINE_SOLID, oneLineBorder.getStyle());
            assertEquals("Wrong content pane border margin for " + label, new Insets(1, 0, 0, 0), oneLineBorder.getInsets(parent));
        } else {
            assertTrue(label + "'s content pane should have a non visible MarginBorder.", cpBorder instanceof MarginBorder);
            assertEquals("Wrong insets for the content pane of " + label, new Insets(1, 0, 0, 0), cpBorder.getInsets(parent));
        }

        // Check scroll pane margin
        ScrollPane scrollPane = compartmentFigure.getScrollPane();
        if (ddec instanceof DNodeContainer) {
            Insets expectedScrollPaneInsets = getExpectedScrollPaneInsets((DNodeContainer) ddec);
            assertEquals("Wrong scroll pane margin for " + label, expectedScrollPaneInsets, scrollPane.getBorder().getInsets(parent));
        } else {
            assertNull("List scroll pane border of " + label + " are expected to be null, review the corresponding margin behavior.", scrollPane.getBorder());
        }
    }

    private Insets getExpectedScrollPaneInsets(DNodeContainer dnc) {
        DDiagramElementContainerExperimentalQuery query = new DDiagramElementContainerExperimentalQuery(dnc);
        Option<LabelBorderStyleDescription> labelBorderStyle = query.getLabelBorderStyle();
        final int defaultMargin = AbstractDNodeContainerCompartmentEditPart.DEFAULT_MARGIN;

        int borderSize = 0;
        if (dnc.getOwnedStyle().getBorderSize() != null) {
            borderSize = dnc.getOwnedStyle().getBorderSize().intValue();
        }

        Insets insets = new Insets(1, 0, 0, 0);
        if (new DNodeContainerExperimentalQuery(dnc).isRegionContainer()) {
            insets = new Insets(0, 0, -1, -1);
        } else if (labelBorderStyle.some() && LabelBorderStyleIds.LABEL_FULL_BORDER_STYLE_FOR_CONTAINER_ID.equals(labelBorderStyle.get().getId())) {
            insets = new Insets(defaultMargin, defaultMargin, defaultMargin, defaultMargin);
        } else if (query.isRegionInVerticalStack()) {
            insets = new Insets(borderSize + defaultMargin, defaultMargin, defaultMargin, defaultMargin);
        } else if (query.isRegionInHorizontalStack()) {
            insets = new Insets(defaultMargin, borderSize + defaultMargin, defaultMargin, defaultMargin);
        }
        return insets;
    }

    private void changeCornerSpecification(String label, final boolean roundedCorner, final int cornerDim) {
        DDiagramElementContainer ddec = getDiagramElementsFromLabel(diagram, label, DDiagramElementContainer.class).get(0);
        final ContainerStyleDescription csd = (ContainerStyleDescription) ddec.getOwnedStyle().getDescription();

        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        domain.getCommandStack().execute(new RecordingCommand(domain) {
            @Override
            protected void doExecute() {
                csd.setRoundedCorner(roundedCorner);
                csd.setArcHeight(cornerDim);
                csd.setArcWidth(cornerDim);
            }
        });

        // We simulate a corner dimension change which is a change in the
        // description without impact on the Sirius model: there triggered edit
        // part refresh but here we just want to check the behavior of the
        // refresh visual.
        // In the real life, user will only see this kind of change after
        // something like layer activation, style customization (VSM), new
        // conditional style: ie a change in the Sirius model.
        getEditPart(ddec).refresh();
        for (DDiagramElement dde : ddec.getElements()) {
            getEditPart(dde).refresh();
        }
    }

    private void changeBorderSpecification(String label, final int borderSize, final LineStyle borderLineStyle) {
        final ContainerStyle containerStyle = getDiagramElementsFromLabel(diagram, label, DDiagramElementContainer.class).get(0).getOwnedStyle();

        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        domain.getCommandStack().execute(new RecordingCommand(domain) {
            @Override
            protected void doExecute() {
                containerStyle.setBorderSize(borderSize);
                containerStyle.setBorderLineStyle(borderLineStyle);
                containerStyle.getCustomFeatures().add(DiagramPackage.eINSTANCE.getBorderedStyle_BorderSize().getName());
                containerStyle.getCustomFeatures().add(DiagramPackage.eINSTANCE.getBorderedStyle_BorderLineStyle().getName());
            }
        });
    }

    private void checkRegionContainer(String label, int expectedBorderSize, LineStyle expectedBorderStyle, int expectedCorners) {
        DNodeContainer regionContainer = getDiagramElementsFromLabel(diagram, label, DNodeContainer.class).get(0);
        AbstractDiagramContainerEditPart editPart = (AbstractDiagramContainerEditPart) getEditPart(regionContainer);

        IFigure mainFigure = editPart.getMainFigure();
        Border border = mainFigure.getBorder();
        assertTrue("Standard region container " + label + " should have an alpha drop shadow border.", border instanceof AlphaDropShadowBorder);

        ViewNodeContainerFigureDesc primaryShape = editPart.getPrimaryShape();

        assertTrue(label + " primary shape should be a GradientRoundedRectangle.", primaryShape instanceof GradientRoundedRectangle);
        GradientRoundedRectangle grr = (GradientRoundedRectangle) primaryShape;
        assertEquals("Wrong corner dimensions for " + label, new Dimension(expectedCorners, expectedCorners), grr.getCornerDimensions());

        checkBorder(regionContainer, editPart, primaryShape, expectedBorderSize, expectedBorderStyle, 0);
    }

    private void checkRegion(String label, int expectedIndex, int expectedBorderSize, LineStyle expectedBorderStyle, int expectedRegionCorners, int expectedRegionRcCorners, int... specificCorners) {
        DDiagramElementContainer region = getDiagramElementsFromLabel(diagram, label, DDiagramElementContainer.class).get(0);
        AbstractDiagramElementContainerEditPart editPart = (AbstractDiagramElementContainerEditPart) getEditPart(region);

        View notationView = editPart.getNotationView();
        assertEquals("The " + label + " notation view index does not correspond to expected order (mapping order and semantic candidates order).", expectedIndex,
                ((View) notationView.eContainer()).getChildren().indexOf(notationView));
        assertEquals("The " + label + " edit part index does not correspond to model order (mapping order and semantic candidates order).", expectedIndex,
                editPart.getParent().getChildren().indexOf(editPart));

        IFigure mainFigure = editPart.getMainFigure();
        Border border = mainFigure.getBorder();
        assertNull("The region " + label + " should not have an alpha drop shadow border.", border);

        ViewNodeContainerFigureDesc primaryShape = editPart.getPrimaryShape();

        checkBorder(region, editPart, primaryShape, expectedBorderSize, expectedBorderStyle, expectedRegionCorners);
        checkRegionShape(region, editPart, primaryShape, expectedRegionCorners, expectedRegionRcCorners, specificCorners);
    }

    private void checkBorder(DDiagramElementContainer ddec, AbstractDiagramElementContainerEditPart editPart, ViewNodeContainerFigureDesc primaryShape, int expectedBorderSize,
            LineStyle expectedBorderStyle, int expectedBorderCorners) {
        assertEquals("Container border size is not consistent with style for " + ddec.getName(), expectedBorderSize, ddec.getOwnedStyle().getBorderSize().intValue());

        Border primaryShapeBorder = primaryShape.getBorder();
        int parentStackDirection = editPart.getParentStackDirection();
        if (PositionConstants.NONE == parentStackDirection) {
            // RegionContainer case
            assertTrue(ddec.getName() + " is not a region, its primary shape should have a MarginBorder border.", primaryShapeBorder.getClass() == MarginBorder.class);
            assertEquals("Wrong insets detected for " + ddec.getName() + " non-region primary shape.",
                    new Insets(5 + expectedBorderSize - 1, expectedBorderSize, expectedBorderSize, expectedBorderSize), primaryShape.getInsets());
            checkShapeLineStyle(ddec, primaryShape, expectedBorderSize, expectedBorderStyle, true);
        } else {
            checkShapeLineStyle(ddec, primaryShape, expectedBorderSize, expectedBorderStyle, false);

            // Check the border
            int regionIndex = Lists.newArrayList(Iterables.filter(editPart.getParent().getChildren(), AbstractDiagramElementContainerEditPart.class)).indexOf(editPart);
            if (expectedBorderSize == 0 || 0 == regionIndex) {
                // No border or First Region
                assertTrue(ddec.getName() + " is a first region or has a 0pix border size: it should have a MarginBorder.", primaryShapeBorder.getClass() == MarginBorder.class);
                assertEquals("Wrong version for " + ddec.getName() + " (expected to be first region or region with 0pix border size.", new Insets(5, 0, 0, 0), primaryShape.getInsets());
            } else {
                assertTrue(ddec.getName() + " should have a rounded corner margin border.", primaryShapeBorder instanceof RoundedCornerMarginBorder);
                RoundedCornerMarginBorder rcmb = (RoundedCornerMarginBorder) primaryShapeBorder;
                Dimension borderCorners = (Dimension) ReflectionHelper.getFieldValueWithoutException(rcmb, "corner").get();
                assertEquals("Wrong corner dimension for the border of " + ddec.getName(), new Dimension(expectedBorderCorners, expectedBorderCorners), borderCorners);

                if (PositionConstants.NORTH_SOUTH == parentStackDirection) {
                    assertEquals("Wrong margin for the border of " + ddec.getName(), new Insets(5 + expectedBorderSize, 0, 0, 0), primaryShape.getInsets());
                    assertEquals("Wrong position for the border of " + ddec.getName(), PositionConstants.TOP, rcmb.getPosition());
                } else if (PositionConstants.EAST_WEST == parentStackDirection) {
                    assertEquals("Wrong margin for the border of " + ddec.getName(), new Insets(5, expectedBorderSize, 0, 0), primaryShape.getInsets());
                    assertEquals("Wrong position for the border of " + ddec.getName(), PositionConstants.LEFT, rcmb.getPosition());
                } else {
                    fail("Invalid case.");
                }
            }
        }
    }

    private void checkShapeLineStyle(DDiagramElementContainer ddec, ViewNodeContainerFigureDesc primaryShape, int expectedBorderSize, LineStyle expectedBorderStyle, boolean regionContainer) {
        if (primaryShape instanceof Shape) {
            Shape shape = (Shape) primaryShape;
            assertEquals("Wrong line width for " + ddec.getName(), expectedBorderSize, shape.getLineWidth());
            assertEquals("Wrong line style for " + ddec.getName(), getSwtLineStyle(expectedBorderStyle), shape.getLineStyle());
            assertEquals("Wrong outline shape mode for " + ddec.getName(), expectedBorderSize != 0 && regionContainer,
                    ((Boolean) ReflectionHelper.getFieldValueWithoutException(shape, "outline").get()).booleanValue());
        } else if (primaryShape instanceof NodeFigure) {
            fail("Needs to be completed");
        }
    }

    private void checkRegionShape(DDiagramElementContainer ddec, AbstractDiagramElementContainerEditPart editPart, ViewNodeContainerFigureDesc primaryShape, int expectedCorners,
            int expectedSpecificCorners, int... specificCorners) {
        Dimension expectedCornersDim = new Dimension(expectedCorners, expectedCorners);

        assertTrue(ddec.getName() + "should have a RegionRoundedGradientRectangle as primary shape.", primaryShape instanceof RegionRoundedGradientRectangle);
        RegionRoundedGradientRectangle regionRoundedGradientRectangle = (RegionRoundedGradientRectangle) primaryShape;
        BitSet additionalCorners = regionRoundedGradientRectangle.getAdditionalDimensionCorners();
        Dimension additionalCornerDimensions = regionRoundedGradientRectangle.getAdditionalCornerDimensions();

        if (specificCorners.length == 0) {
            assertEquals("Wrong specific corner dimension for " + ddec.getName(), expectedCornersDim, additionalCornerDimensions);
            assertEquals("Wrong corner dimension for " + ddec.getName(), expectedCornersDim, regionRoundedGradientRectangle.getCornerDimensions());
            assertTrue("The specifc corner algo should be checked for " + ddec.getName() + " (" + additionalCorners.cardinality() + ")", additionalCorners.isEmpty());
        } else {
            Dimension expectedSpecificCornersDim = new Dimension(expectedSpecificCorners, expectedSpecificCorners);
            assertEquals("Wrong additional corner dimension for " + ddec.getName(), expectedCornersDim, additionalCornerDimensions);
            assertEquals("Wrong corner dimension for " + ddec.getName(), expectedSpecificCornersDim, regionRoundedGradientRectangle.getCornerDimensions());
            assertTrue(ddec.getName() + " should have more specific corners than small corners.", expectedSpecificCorners > expectedCorners);
            assertFalse(ddec.getName() + "should have different size of corners.", additionalCorners.isEmpty() || additionalCorners.cardinality() == 4);
            assertTrue(ddec.getName() + "should have 1, 2 or 3 corners with its specified corner dimension.", additionalCorners.cardinality() < 4);
            List<Integer> specificCornersList = Ints.asList(specificCorners);
            for (int corner : CORNERS) {
                assertEquals("Wrong specifc corner distribution for " + ddec.getName() + " (check PositionConstants=" + corner + ").", !specificCornersList.contains(corner),
                        additionalCorners.get(corner));
            }
        }
    }

    /**
     * Return the actual label alignment of the given part. See LabelAlignmentHelper.
     * 
     * @param abstractDiagramNameEditPart
     *            the given part
     * @return the label alignment of the given part
     */
    private int getActualLabelAlignment(AbstractDiagramNameEditPart abstractDiagramNameEditPart) {
        int actualLabelAlignment = 0;
        IFigure abstractGraphicalEditPartFigure = abstractDiagramNameEditPart.getFigure();
        Assert.assertTrue("This figure should be a SiriusWrapLabel.", abstractGraphicalEditPartFigure instanceof SiriusWrapLabel);
        SiriusWrapLabel abstractGraphicalEditPartWrappingLabel = (SiriusWrapLabel) abstractGraphicalEditPartFigure;
        // if (abstractGraphicalEditPart.getParent() instanceof
        // AbstractDiagramContainerEditPart) {
        LayoutManager layoutManager = abstractGraphicalEditPartWrappingLabel.getParent().getLayoutManager();
        if (layoutManager instanceof ToolbarLayout) {
            ToolbarLayout ePackage1WrappingLabelParentLayoutManager = (ToolbarLayout) abstractGraphicalEditPartWrappingLabel.getParent().getLayoutManager();
            actualLabelAlignment = ePackage1WrappingLabelParentLayoutManager.getMinorAlignment();

            // Check figure consistency with its parent layout manager
            // (refreshed)
            int labelRefX = 0;
            int parentRefX = 0;
            if (ToolbarLayout.ALIGN_TOPLEFT == actualLabelAlignment) {
                labelRefX = abstractGraphicalEditPartWrappingLabel.getBounds().getLeft().x;
                parentRefX = abstractGraphicalEditPartWrappingLabel.getParent().getBounds().getLeft().x;
            } else if (ToolbarLayout.ALIGN_BOTTOMRIGHT == actualLabelAlignment) {
                labelRefX = abstractGraphicalEditPartWrappingLabel.getBounds().getRight().x;
                parentRefX = abstractGraphicalEditPartWrappingLabel.getParent().getBounds().getRight().x;
            } else if (ToolbarLayout.ALIGN_CENTER == actualLabelAlignment) {
                labelRefX = abstractGraphicalEditPartWrappingLabel.getBounds().getCenter().x;
                parentRefX = abstractGraphicalEditPartWrappingLabel.getParent().getBounds().getCenter().x;
            }
            assertEquals("Figure alignment is not consitent with style", parentRefX, labelRefX, 5);

        } else {
            Assert.fail(" a AbstractGraphicalEditPart should have a ToolbarLayout on its main figure");
        }
        return actualLabelAlignment;
    }

    private int getSwtLineStyle(LineStyle expectedLineStyle) {
        // Line style.
        int effectiveExpectedSWTValue = SWT.LINE_SOLID;
        if (LineStyle.DOT_LITERAL == expectedLineStyle) {
            effectiveExpectedSWTValue = SWT.LINE_DOT;
        } else if (LineStyle.DASH_LITERAL == expectedLineStyle) {
            effectiveExpectedSWTValue = SWT.LINE_DASH;
        } else if (LineStyle.DASH_DOT_LITERAL == expectedLineStyle) {
            effectiveExpectedSWTValue = SWT.LINE_DASHDOT;
        }
        return effectiveExpectedSWTValue;
    }

    /**
     * Change the default font.
     *
     * @param fontName
     *            the font name to set as default.
     * @return the previous default font name.
     */
    protected String changeDefaultFontName(String fontName) {
        IPreferenceStore preferenceStore = (IPreferenceStore) DiagramUIPlugin.DIAGRAM_PREFERENCES_HINT.getPreferenceStore();
        FontData fontData = PreferenceConverter.getFontData(preferenceStore, IPreferenceConstants.PREF_DEFAULT_FONT);

        // Get the actual font.
        String oldName = fontData.getName();

        // Change the font.
        fontData.setName(fontName);
        PreferenceConverter.setDefault(preferenceStore, IPreferenceConstants.PREF_DEFAULT_FONT, fontData);
        return oldName;
    }
}
