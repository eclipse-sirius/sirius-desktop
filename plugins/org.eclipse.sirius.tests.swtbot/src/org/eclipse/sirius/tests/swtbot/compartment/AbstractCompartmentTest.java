/*******************************************************************************
 * Copyright (c) 2015, 2020 Obeo.
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
package org.eclipse.sirius.tests.swtbot.compartment;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramElementContainerEditPart;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.tests.unit.diagram.compartment.ICompartmentTests;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Compartments-related tests.
 * 
 * @author <a href="mailto:belqassim.djafer@obeo.fr">Belqassim Djafer</a>
 */
public abstract class AbstractCompartmentTest extends AbstractSiriusSwtBotGefTestCase implements ICompartmentTests {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(SiriusTestsPlugin.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);

        // Activate auto refresh
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, "/", SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        SWTBotUtils.waitAllUiEvents();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        SWTBotUtils.waitAllUiEvents();
        super.tearDown();
    }

    /**
     * Open representation.
     * 
     * @param representationName
     *            the representation name
     * @param representationInstanceName
     *            the representation instance name
     */
    protected void openRepresentation(String representationName, String representationInstanceName) {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), representationName, representationInstanceName, DDiagram.class, true, true);
    }

    /**
     * Gets the semantic element associated to the given edit part.
     * 
     * @param editPartName
     *            the edit part name
     * @return the semantic element associated to the given edit part
     */
    protected DDiagramElementContainer getDiagramElementContainer(String editPartName) {
        SWTBotGefEditPart editPart = editor.getEditPart(editPartName, AbstractDiagramElementContainerEditPart.class);
        DDiagramElementContainer diagramElement = (DDiagramElementContainer) ((AbstractDiagramElementContainerEditPart) editPart.part()).resolveDiagramElement();
        return diagramElement;
    }

    /**
     * Check the GMF and Draw2d bounds (or size) of the edit part with given <code>label</code>.
     * 
     * @param label
     *            The label of the edit part to check
     * @param expectedGmfBounds
     *            The expected GMF bounds
     * @param expectedFigureBounds
     *            The expected draw2d bounds, if the width or height is equals to -1, we ignore it.
     * 
     * @return A copy of the current Draw2d bounds
     */
    protected Rectangle checkBounds(String label, Rectangle expectedGmfBounds, Rectangle expectedFigureBounds) {
        return checkBounds(label, expectedGmfBounds, expectedFigureBounds, false);
    }

    /**
     * Check the GMF and Draw2d bounds (or size) of the edit part with given <code>label</code>.
     * 
     * @param label
     *            The label of the edit part to check
     * @param expectedGmfBounds
     *            The expected GMF bounds
     * @param expectedFigureBounds
     *            The expected draw2d bounds, if the width or height is equals to -1, we ignore it.
     * @param onlyCheckSize
     *            true if only the size must be check (and not the location), false otherwise.
     * 
     * @return A copy of the current Draw2d bounds
     */
    private Rectangle checkBounds(String label, Rectangle expectedGmfBounds, Rectangle expectedFigureBounds, boolean onlyCheckSize) {
        return checkBounds(label, expectedGmfBounds, expectedFigureBounds, onlyCheckSize, 0, 0);
    }

    /**
     * Check the GMF and Draw2d bounds (or size) of the edit part with given <code>label</code>.
     * 
     * @param label
     *            The label of the edit part to check
     * @param expectedGmfBounds
     *            The expected GMF bounds
     * @param expectedFigureBounds
     *            The expected draw2d bounds, if the width or height is equals to -1, we ignore it.
     * @param widthDelta
     *            The width delta to consider the width as equal (because of font size that can be slightly different on
     *            each OS).
     * @param heightDelta
     *            The height delta to consider the height as equal (because of font size that can be slightly different
     *            on each OS).
     * 
     * @return A copy of the current Draw2d bounds
     */
    protected Rectangle checkBounds(String label, Rectangle expectedGmfBounds, Rectangle expectedFigureBounds, int widthDelta, int heightDelta) {
        return checkBounds(label, expectedGmfBounds, expectedFigureBounds, false, widthDelta, heightDelta);
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
     * @param onlyCheckSize
     *            true if only the size must be check (and not the location), false otherwise. * @param widthDelta The
     *            width delta to consider the width as equal (because of font size that can be slightly different on
     *            each OS).
     * @param widthDelta
     *            The width delta to consider the width as equal (because of font size that can be slightly different on
     *            each OS).
     * @param heightDelta
     *            The height delta to consider the height as equal (because of font size that can be slightly different
     *            on each OS).
     * @return A copy of the current DrawD2 bounds
     */
    protected Rectangle checkBounds(String label, Rectangle expectedGmfBounds, Rectangle expectedFigureBounds, boolean onlyCheckSize, int widthDelta, int heightDelta) {
        SWTBotGefEditPart swtBotEditPart = editor.getEditPart(label, AbstractDiagramElementContainerEditPart.class);
        AbstractDiagramElementContainerEditPart editPart = (AbstractDiagramElementContainerEditPart) swtBotEditPart.part();

        IFigure mainFigure = editPart.getMainFigure();

        if (onlyCheckSize) {
            if (widthDelta == 0 && heightDelta == 0) {
                assertEquals("Wrong GMF size for " + label, expectedGmfBounds.getSize(), getBounds((Node) editPart.getNotationView()).getSize());
            } else {
                Dimension gmfSize = getBounds((Node) editPart.getNotationView()).getSize();
                assertEquals("Wrong GMF width for " + label, expectedGmfBounds.width(), gmfSize.width(), widthDelta);
                assertEquals("Wrong GMF height for " + label, expectedGmfBounds.height(), gmfSize.height(), heightDelta);
            }
            if (expectedFigureBounds.width() != -1 && expectedFigureBounds.height() != -1) {
                if (widthDelta == 0 && heightDelta == 0) {
                    assertEquals("Wrong Draw2D size for " + label, expectedFigureBounds.getSize(), mainFigure.getBounds().getSize());
                } else {
                    assertEquals("Wrong Draw2D width for " + label, expectedFigureBounds.width(), mainFigure.getBounds().width(), widthDelta);
                    assertEquals("Wrong Draw2D height for " + label, expectedFigureBounds.height(), mainFigure.getBounds().height(), heightDelta);
                }
            } else {
                if (expectedFigureBounds.width() != -1) {
                    assertEquals("Wrong Draw2D width for " + label, expectedFigureBounds.width(), mainFigure.getBounds().width(), widthDelta);
                }
                if (expectedFigureBounds.height() != -1) {
                    assertEquals("Wrong Draw2D height for " + label, expectedFigureBounds.height(), mainFigure.getBounds().height(), heightDelta);
                }
            }
        } else {
            if (widthDelta == 0 && heightDelta == 0) {
                assertEquals("Wrong GMF bounds for " + label, expectedGmfBounds, getBounds((Node) editPart.getNotationView()));
            } else {
                Rectangle gmfBounds = getBounds((Node) editPart.getNotationView());
                assertEquals("Wrong GMF location for " + label, expectedGmfBounds.getLocation(), gmfBounds.getLocation());
                assertEquals("Wrong GMF width for " + label, expectedGmfBounds.width(), gmfBounds.width(), widthDelta);
                assertEquals("Wrong GMF height for " + label, expectedGmfBounds.height(), gmfBounds.height(), heightDelta);
            }
            if (expectedFigureBounds.width() != -1 && expectedFigureBounds.height() != -1) {
                if (widthDelta == 0 && heightDelta == 0) {
                    assertEquals("Wrong Draw2D bounds for " + label, expectedFigureBounds, mainFigure.getBounds());
                } else {
                    assertEquals("Wrong Draw2D x location for " + label, expectedFigureBounds.getLocation().x, mainFigure.getBounds().getLocation().x, widthDelta);
                    assertEquals("Wrong Draw2D y location for " + label, expectedFigureBounds.getLocation().y, mainFigure.getBounds().getLocation().y, heightDelta);
                    assertEquals("Wrong Draw2D width for " + label, expectedFigureBounds.width(), mainFigure.getBounds().width(), widthDelta);
                    assertEquals("Wrong Draw2D height for " + label, expectedFigureBounds.height(), mainFigure.getBounds().height(), heightDelta);
                }
            } else {
                assertEquals("Wrong Draw2D x for " + label, expectedFigureBounds.x(), mainFigure.getBounds().x());
                assertEquals("Wrong Draw2D y for " + label, expectedFigureBounds.y(), mainFigure.getBounds().y());
                if (expectedFigureBounds.width() != -1) {
                    assertEquals("Wrong Draw2D width for " + label, expectedFigureBounds.width(), mainFigure.getBounds().width(), widthDelta);
                }
                if (expectedFigureBounds.height() != -1) {
                    assertEquals("Wrong Draw2D height for " + label, expectedFigureBounds.height(), mainFigure.getBounds().height(), heightDelta);
                }
            }
        }
        return mainFigure.getBounds().getCopy();
    }

    /**
     * Check the GMF and Draw2d bounds (or size) of the edit part under the given <code>point</code>.
     * 
     * @param point
     *            A point on the edit part to check
     * @param expectedGmfBounds
     *            The expected GMF bounds
     * @param expectedFigureBounds
     *            The expected draw2d bounds, if the width or height is equals to -1, we ignore it.
     * @param onlyCheckSize
     *            true if only the size must be check (and not the location), false otherwise.
     * 
     * @return A copy of the current Draw2d bounds
     */
    private Rectangle checkBoundsWithPosition(Point point, Rectangle expectedGmfBounds, Rectangle expectedFigureBounds, boolean onlyCheckSize) {
        SWTBotGefEditPart swtBotEditPart = editor.getEditPart(point, AbstractDiagramElementContainerEditPart.class);
        AbstractDiagramElementContainerEditPart editPart = (AbstractDiagramElementContainerEditPart) swtBotEditPart.part();

        IFigure mainFigure = editPart.getMainFigure();

        if (onlyCheckSize) {
            assertEquals("Wrong GMF size for figure at position " + point, expectedGmfBounds.getSize(), getBounds((Node) editPart.getNotationView()).getSize());
            if (expectedFigureBounds.width() != -1 && expectedFigureBounds.height() != -1) {
                assertEquals("Wrong Draw2D size for figure at position " + point, expectedFigureBounds.getSize(), mainFigure.getBounds().getSize());
            } else {
                if (expectedFigureBounds.width() != -1) {
                    assertEquals("Wrong Draw2D width for figure at position " + point, expectedFigureBounds.width(), mainFigure.getBounds().width());
                }
                if (expectedFigureBounds.height() != -1) {
                    assertEquals("Wrong Draw2D height for figure at position " + point, expectedFigureBounds.height(), mainFigure.getBounds().height());
                }
            }
        } else {
            assertEquals("Wrong GMF bounds for figure at position " + point, expectedGmfBounds, getBounds((Node) editPart.getNotationView()));
            if (expectedFigureBounds.width() != -1 && expectedFigureBounds.height() != -1) {
                assertEquals("Wrong Draw2D bounds for figure at position " + point, expectedFigureBounds, mainFigure.getBounds());
            } else {
                assertEquals("Wrong Draw2D x for figure at position " + point, expectedFigureBounds.x(), mainFigure.getBounds().x());
                assertEquals("Wrong Draw2D y for figure at position " + point, expectedFigureBounds.y(), mainFigure.getBounds().y());
                if (expectedFigureBounds.width() != -1) {
                    assertEquals("Wrong Draw2D width for figure at position " + point, expectedFigureBounds.width(), mainFigure.getBounds().width());
                }
                if (expectedFigureBounds.height() != -1) {
                    assertEquals("Wrong Draw2D height for figure at position " + point, expectedFigureBounds.height(), mainFigure.getBounds().height());
                }
            }
        }
        return mainFigure.getBounds().getCopy();
    }

    /**
     * Check the GMF and Draw2d bounds of the edit part under the given <code>point</code>.
     * 
     * @param point
     *            A point on the edit part to check
     * @param expectedGmfBounds
     *            The expected GMF bounds
     * @param expectedFigureBounds
     *            The expected draw2d bounds, if the width or height is equals to -1, we ignore it.
     * 
     * @return A copy of the current Draw2d bounds
     */
    protected Rectangle checkBounds(Point point, Rectangle expectedGmfBounds, Rectangle expectedFigureBounds) {
        return checkBoundsWithPosition(point, expectedGmfBounds, expectedFigureBounds, false);
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
}
