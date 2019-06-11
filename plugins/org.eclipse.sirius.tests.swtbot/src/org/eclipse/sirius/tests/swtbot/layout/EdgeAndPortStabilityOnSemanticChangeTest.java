/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot.layout;

import static org.eclipse.sirius.tests.swtbot.support.api.matcher.geometry.PointAround.around;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.util.Collections;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.sirius.business.internal.session.danalysis.SaveSessionJob;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart.ViewEdgeFigure;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNodeEditPart;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Tests edge and border nodes layout stability on adding element using the
 * ecore file - manual refresh.
 * 
 * @author lchituc
 */
public class EdgeAndPortStabilityOnSemanticChangeTest extends AbstractSiriusSwtBotGefTestCase {

    private static final int NB_BENDPOINTS_EDGE = 3;

    private static final String REPRESENTATION_INSTANCE_NAME = "new TC2648";

    private static final String REPRESENTATION_DESCRIPTION_NAME = "TC2648";

    private static final String REPRESENTATION_DESCRIPTION_NAME_2 = "TC2675";

    private static final String REPRESENTATION_NAME_2 = "new TC2675";

    private static final String MODEL = "tc2648.ecore";

    private static final String SESSION_FILE = "tc2648.aird";

    private static final String VSM_FILE = "tc2648.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/edgeStabilityOnSemanticChange/VP-2648/";

    private static final String FILE_DIR = "/";

    private static final Point EXPECTED_INITIAL_POSITION_A = new Point(295, 183);

    private static final Point EXPECTED_INITIAL_POSITION_B = new Point(555, 115);

    private static final Point EXPECTED_INITIAL_POSITION_C = new Point(360, 183);

    private static final Point EXPECTED_INITIAL_POSITION_D = new Point(620, 115);

    private static final int ACCEPTABLE_DISTANCE = 3;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */

    public void testEdgeLayoutStabilityOnSemanticDelete() throws Exception {
        // Open editor
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
        // Checks the number of edges outgoing from C1 class to C2 class (nodes
        // not pinned)
        SWTBotGefEditPart c1EditPart = editor.getEditPart("C1", AbstractDiagramNodeEditPart.class);
        Rectangle c1Before = editor.getBounds(c1EditPart);
        assertEquals("Wrong number outgoing edges for " + "C1", 1, c1EditPart.sourceConnections().size());
        SWTBotGefEditPart c2EditPart = editor.getEditPart("C2", AbstractDiagramNodeEditPart.class);
        Rectangle c2Before = editor.getBounds(c2EditPart);
        SWTBotGefConnectionEditPart wirePartC1C2 = c1EditPart.sourceConnections().get(0);
        final ConnectionEditPart connectionEditPartC1C2 = wirePartC1C2.part();
        assertTrue("The figure is not a ViewEdgeFigure.", connectionEditPartC1C2.getFigure() instanceof ViewEdgeFigure);
        ViewEdgeFigure connectionFigureC1C2 = (ViewEdgeFigure) connectionEditPartC1C2.getFigure();
        assertEquals("Wrong number of points before creation of new bendpoint", NB_BENDPOINTS_EDGE, connectionFigureC1C2.getPoints().size());

        // Gets the bend-points' position of the edge between C1 class and C2
        // class
        Point fstPointC1C2 = connectionFigureC1C2.getPoints().getFirstPoint().getCopy();
        Point mdlePointC1C2 = connectionFigureC1C2.getPoints().getMidpoint().getCopy();
        Point lstPointC1C2 = connectionFigureC1C2.getPoints().getLastPoint().getCopy();

        // Checks the number of edges outgoing from C3 class to C4 class (nodes
        // pinned)
        SWTBotGefEditPart c3EditPart = editor.getEditPart("C3", AbstractDiagramNodeEditPart.class);
        Rectangle c3Before = editor.getBounds(c3EditPart);
        assertEquals("Wrong number outgoing edges for " + "C3", 1, c1EditPart.sourceConnections().size());
        SWTBotGefEditPart c4EditPart = editor.getEditPart("C4", AbstractDiagramNodeEditPart.class);
        Rectangle c4Before = editor.getBounds(c4EditPart);
        SWTBotGefConnectionEditPart wirePartC3C4 = c3EditPart.sourceConnections().get(0);

        final ConnectionEditPart connectionEditPartC3C4 = wirePartC3C4.part();
        assertTrue("The figure is not a ViewEdgeFigure.", connectionEditPartC3C4.getFigure() instanceof ViewEdgeFigure);
        ViewEdgeFigure connectionFigureC3C4 = (ViewEdgeFigure) connectionEditPartC3C4.getFigure();
        assertEquals("Wrong number of points before creation of new bendpoint", NB_BENDPOINTS_EDGE, connectionFigureC3C4.getPoints().size());

        // Gets the bend-points' position of the edge between C1 class and C2
        // class
        Point fstPointC3C4 = connectionFigureC3C4.getPoints().getFirstPoint().getCopy();
        Point mdlePointC3C4 = connectionFigureC3C4.getPoints().getMidpoint().getCopy();
        Point lstPointC3C4 = connectionFigureC3C4.getPoints().getLastPoint().getCopy();

        // Checks the number of edges outgoing from A, border node of p1
        // package, towards B, border node of p2 package (A and B are not
        // pinned)
        SWTBotGefEditPart aEditPart = editor.getEditPart("A", AbstractDiagramBorderNodeEditPart.class);
        Rectangle aBefore = editor.getBounds(aEditPart);
        assertThat("Unexpected initial position for bordered node 'A'.", aBefore.getLocation(), around(EXPECTED_INITIAL_POSITION_A, ACCEPTABLE_DISTANCE));
        Rectangle bBefore = editor.getBounds(editor.getEditPart("B", AbstractDiagramBorderNodeEditPart.class));
        assertThat("Unexpected initial position for bordered node 'B'.", bBefore.getLocation(), around(EXPECTED_INITIAL_POSITION_B, ACCEPTABLE_DISTANCE));

        assertEquals("There should be one edge going from the border node A", 1, ((NodeEditPart) aEditPart.part()).getSourceConnections().size());

        final ConnectionEditPart edgeAB = (ConnectionEditPart) ((NodeEditPart) aEditPart.part()).getSourceConnections().get(0);
        assertTrue("The figure should be a ViewEdgeFigure.", edgeAB.getFigure() instanceof ViewEdgeFigure);
        final ViewEdgeFigure connectionFigureAB = (ViewEdgeFigure) edgeAB.getFigure();
        // Checks the number of bend-points of the edge
        assertEquals("Wrong number of bendpoints before adding the p5 package", NB_BENDPOINTS_EDGE, connectionFigureAB.getPoints().size());
        // Gets the bend-points' position of the edge between A and B
        final Point firstPointAB = connectionFigureAB.getPoints().getFirstPoint().getCopy();
        final Point middlePointAB = connectionFigureAB.getPoints().getMidpoint().getCopy();
        final Point lastPointAB = connectionFigureAB.getPoints().getLastPoint().getCopy();

        // Checks the number of edges outgoing from C, border node of p1
        // package, towards D, border node of p2 package (C and D are not
        // pinned)
        SWTBotGefEditPart dEditPart = editor.getEditPart("D", AbstractDiagramBorderNodeEditPart.class);
        Rectangle dBefore = editor.getBounds(dEditPart);
        assertThat("Unexpected initial position for bordered node 'D'.", dBefore.getLocation(), around(EXPECTED_INITIAL_POSITION_D, ACCEPTABLE_DISTANCE));
        Rectangle cBefore = editor.getBounds(editor.getEditPart("C", AbstractDiagramBorderNodeEditPart.class));
        assertThat("Unexpected initial position for bordered node 'C'.", cBefore.getLocation(), around(EXPECTED_INITIAL_POSITION_C, ACCEPTABLE_DISTANCE));

        assertEquals("There should be one edge going from the border node D", 1, ((NodeEditPart) dEditPart.part()).getSourceConnections().size());

        final ConnectionEditPart edgeCD = (ConnectionEditPart) ((NodeEditPart) dEditPart.part()).getSourceConnections().get(0);
        assertTrue("The figure should be a ViewEdgeFigure.", edgeCD.getFigure() instanceof ViewEdgeFigure);
        final ViewEdgeFigure connectionFigureCD = (ViewEdgeFigure) edgeCD.getFigure();
        // Checks the number of bend-points of the edge
        assertEquals("Wrong number of bendpoints before adding the p5 package", NB_BENDPOINTS_EDGE, connectionFigureCD.getPoints().size());
        // Gets the bend-points' position of the edge between A and B
        final Point firstPointCD = connectionFigureCD.getPoints().getFirstPoint().getCopy();
        final Point middlePointCD = connectionFigureCD.getPoints().getMidpoint().getCopy();
        final Point lastPointCD = connectionFigureCD.getPoints().getLastPoint().getCopy();

        // Load the semantic resource in another resource set and add a new
        // package on the root element
        TransactionalEditingDomain domain = new TransactionalEditingDomainImpl(new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE));
        ResourceSet set = domain.getResourceSet();
        try {
            final EPackage ePackageInAnotherResourceSet = (EPackage) ModelUtils.load(localSession.getOpenedSession().getSemanticResources().iterator().next().getURI(), set);
            domain.getCommandStack().execute(new RecordingCommand(domain, "Add new package") {

                @Override
                protected void doExecute() {
                    EPackage newPackage = EcoreFactory.eINSTANCE.createEPackage();
                    newPackage.setName("p5");
                    ePackageInAnotherResourceSet.getESubpackages().get(0).getESubpackages().add(newPackage);
                }
            });
            ePackageInAnotherResourceSet.eResource().save(Collections.EMPTY_MAP);
        } catch (IOException e) {
            fail("Pb when saving the resource in another resourceSet : " + e.getMessage());
        }
        SWTBotUtils.waitAllUiEvents();

        // Launching manual refresh
        editor.click(0, 500);
        editor.save();
        manualRefresh();
        editor.save();
        Job.getJobManager().join(SaveSessionJob.FAMILY, new NullProgressMonitor());

        // Asserts that p5 package was added.
        // Throw an exception if edit part is not found
        assertTrue(" p5 should be displayed on the diagram", editor.getEditPart("p5") != null);

        // Checks that the edge from C1 to C2 has the same number of
        // bend-points as before adding the p5 package and that their
        // position has not changed
        SWTBotGefEditPart c1EditPartAfter = editor.getEditPart("C1", AbstractDiagramNodeEditPart.class);
        assertEquals("Wrong number outgoing edges for " + "C1", 1, c1EditPartAfter.sourceConnections().size());
        SWTBotGefConnectionEditPart wirePartC1C2After = c1EditPartAfter.sourceConnections().get(0);

        final ConnectionEditPart connectionEditPartC1C2After = wirePartC1C2After.part();
        assertTrue("The figure is not a ViewEdgeFigure.", connectionEditPartC1C2After.getFigure() instanceof ViewEdgeFigure);
        ViewEdgeFigure connectionC1C2FigureAfter = (ViewEdgeFigure) connectionEditPartC1C2After.getFigure();
        assertEquals("Wrong number of points after adding the p5 package", NB_BENDPOINTS_EDGE, connectionC1C2FigureAfter.getPoints().size());

        Point fstPointC1C2After = connectionC1C2FigureAfter.getPoints().getFirstPoint().getCopy();
        Point mdlePointC1C2After = connectionC1C2FigureAfter.getPoints().getMidpoint().getCopy();
        Point lstPointC1C2After = connectionC1C2FigureAfter.getPoints().getLastPoint().getCopy();

        assertEquals("the first bendpoint of the edge C1 to C2 is invalid.", fstPointC1C2, fstPointC1C2After);
        assertEquals("the middle bendpoint of the edge C1 to C2 is invalid.", mdlePointC1C2, mdlePointC1C2After);
        assertEquals("the last bendpoint of the edge C1 to C2 is invalid.", lstPointC1C2, lstPointC1C2After);

        // Checks that the edge from C3 to C4 has the same number of
        // bend-points as before adding the p5 package and that their
        // position has not changed
        SWTBotGefEditPart c3EditPartAfter = editor.getEditPart("C3", AbstractDiagramNodeEditPart.class);
        assertEquals("Wrong number outgoing edges for " + "C3", 1, c3EditPartAfter.sourceConnections().size());
        SWTBotGefConnectionEditPart wirePartC3C4After = c3EditPartAfter.sourceConnections().get(0);

        final ConnectionEditPart connectionEditPartC3C4After = wirePartC3C4After.part();
        assertTrue("The figure is not a ViewEdgeFigure.", connectionEditPartC3C4After.getFigure() instanceof ViewEdgeFigure);
        ViewEdgeFigure connectionC3C4FigureAfter = (ViewEdgeFigure) connectionEditPartC3C4After.getFigure();
        assertEquals("Wrong number of points after adding the p5 package", NB_BENDPOINTS_EDGE, connectionC3C4FigureAfter.getPoints().size());

        Point fstPointC3C4After = connectionC3C4FigureAfter.getPoints().getFirstPoint().getCopy();
        Point mdlePointC3C4After = connectionC3C4FigureAfter.getPoints().getMidpoint().getCopy();
        Point lstPointC3C4After = connectionC3C4FigureAfter.getPoints().getLastPoint().getCopy();

        assertEquals("the first bendpoint of the edge C3 to C4 is invalid.", fstPointC3C4, fstPointC3C4After);
        assertEquals("the middle bendpoint of the edge C3 to C4 is invalid.", mdlePointC3C4, mdlePointC3C4After);
        assertEquals("the last bendpoint of the edge C3 to C4 is invalid.", lstPointC3C4, lstPointC3C4After);

        // Checks that the edge from A to B has the same number of
        // bend-points as before adding p5 package and that their
        // position has not changed
        SWTBotGefEditPart aEditPartAfter = editor.getEditPart("A", AbstractDiagramBorderNodeEditPart.class);
        assertEquals("There should be one going from the border node A ", 1, ((NodeEditPart) aEditPartAfter.part()).getSourceConnections().size());
        final ConnectionEditPart edgeABAfter = (ConnectionEditPart) ((NodeEditPart) aEditPartAfter.part()).getSourceConnections().get(0);
        assertTrue("The figure should be a ViewEdgeFigure.", edgeABAfter.getFigure() instanceof ViewEdgeFigure);
        ViewEdgeFigure connectionFigureABAfter = (ViewEdgeFigure) edgeABAfter.getFigure();
        // Checks the number of bend-points of the edge
        assertEquals("Wrong number of bendpoints after adding the p5 package", NB_BENDPOINTS_EDGE, connectionFigureABAfter.getPoints().size());
        // Gets the bend-points' position of the edge between A and B after
        // adding p5 package
        Point firstPointABAfter = connectionFigureABAfter.getPoints().getFirstPoint().getCopy();
        Point middlePointABAfter = connectionFigureABAfter.getPoints().getMidpoint().getCopy();
        Point lastPointABAfter = connectionFigureABAfter.getPoints().getLastPoint().getCopy();

        assertEquals("The first bendpoint of the edge A to B is invalid.", firstPointAB, firstPointABAfter);
        assertEquals("The middle bendpoint of the edge A to B is invalid.", middlePointAB, middlePointABAfter);
        assertEquals("The last bendpoint of the edge A to B is invalid.", lastPointAB, lastPointABAfter);

        // Checks that the edge from C to D has the same number of
        // bend-points as before adding p5 package and that their
        // position has not changed
        SWTBotGefEditPart dEditPartAfter = editor.getEditPart("D", AbstractDiagramBorderNodeEditPart.class);
        assertEquals("There should be one going from the border node D ", 1, ((NodeEditPart) dEditPartAfter.part()).getSourceConnections().size());
        final ConnectionEditPart edgeCDAfter = (ConnectionEditPart) ((NodeEditPart) dEditPartAfter.part()).getSourceConnections().get(0);
        assertTrue("The figure should be a ViewEdgeFigure.", edgeCDAfter.getFigure() instanceof ViewEdgeFigure);
        ViewEdgeFigure connectionFigureCDAfter = (ViewEdgeFigure) edgeCDAfter.getFigure();
        // Checks the number of bend-points of the edge
        assertEquals("Wrong number of bendpoints after adding the p5 package", NB_BENDPOINTS_EDGE, connectionFigureCDAfter.getPoints().size());
        // Gets the bend-points' position of the edge between A and B after
        // adding p5 package
        Point firstPointCDAfter = connectionFigureCDAfter.getPoints().getFirstPoint().getCopy();
        Point middlePointCDAfter = connectionFigureCDAfter.getPoints().getMidpoint().getCopy();
        Point lastPointCDAfter = connectionFigureCDAfter.getPoints().getLastPoint().getCopy();

        assertEquals("The first bendpoint of the edge C to D is invalid.", firstPointCD, firstPointCDAfter);
        assertEquals("The middle bendpoint of the edge C to D is invalid.", middlePointCD, middlePointCDAfter);
        assertEquals("The last bendpoint of the edge C to D is invalid.", lastPointCD, lastPointCDAfter);

        // Asserts that the position of all the ports has not changed after
        // adding p5 and launching manual refresh
        final Rectangle aBoundsAfter = editor.getBounds(aEditPartAfter);
        assertEquals("Adding the new package changed the position of port A but it shouldn't!", aBefore, aBoundsAfter);

        final Rectangle bBoundsAfter = editor.getBounds(editor.getEditPart("B", AbstractDiagramBorderNodeEditPart.class));
        assertEquals("Adding the new package changed the position of port B but it shouldn't!", bBefore, bBoundsAfter);

        final Rectangle cBoundsAfter = editor.getBounds(editor.getEditPart("C", AbstractDiagramBorderNodeEditPart.class));
        assertEquals("Adding the new package changed the position of port C but it shouldn't!", cBefore, cBoundsAfter);

        final Rectangle dBoundsAfter = editor.getBounds(dEditPartAfter);
        assertEquals("Adding the new package changed the position of port D but it shouldn't!", dBefore, dBoundsAfter);

        // Asserts that the position of all the nodes classes has not changed
        // after adding p5 and launching manual refresh
        final Rectangle c1BoundsAfter = editor.getBounds(c1EditPartAfter);
        assertEquals("Adding the new package changed the position of port C1 but it shouldn't!", c1Before, c1BoundsAfter);

        final Rectangle c2BoundsAfter = editor.getBounds(editor.getEditPart("C2", AbstractDiagramNodeEditPart.class));
        assertEquals("Adding the new package changed the position of port C2 but it shouldn't!", c2Before, c2BoundsAfter);

        final Rectangle c3BoundsAfter = editor.getBounds(c3EditPartAfter);
        assertEquals("Adding the new package changed the position of port C3 but it shouldn't!", c3Before, c3BoundsAfter);

        final Rectangle c4BoundsAfter = editor.getBounds(editor.getEditPart("C4", AbstractDiagramNodeEditPart.class));
        assertEquals("Adding the new package changed the position of port C4 but it shouldn't!", c4Before, c4BoundsAfter);
    }

    /**
     * Test that the existing port does not move when the editor is open with
     * refresh at opening enabled. See VP-2676.
     * 
     * @throws Exception
     *             Test error.
     */

    public void testPortLayoutStabilityOnSemanticAddPort() throws Exception {
        // Open editor
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_2, REPRESENTATION_NAME_2, DDiagram.class);

        SWTBotGefEditPart p1EditPart = editor.getEditPart("p1", AbstractDiagramContainerEditPart.class);
        SWTBotGefEditPart aEditPart = p1EditPart.children().get(2);
        Rectangle aBefore = editor.getBounds(aEditPart);

        // Close the diagram
        editor.close();
        SWTBotUtils.waitAllUiEvents();

        // Load the semantic resource in another resource set and add a new
        // class to the package root/test2/p1
        TransactionalEditingDomain domain = new TransactionalEditingDomainImpl(new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE));
        ResourceSet set = domain.getResourceSet();
        try {
            final EPackage ePackageInAnotherResourceSet = (EPackage) ModelUtils.load(localSession.getOpenedSession().getSemanticResources().iterator().next().getURI(), set);
            domain.getCommandStack().execute(new RecordingCommand(domain, "Add new package") {

                @Override
                protected void doExecute() {
                    EClass newClass = EcoreFactory.eINSTANCE.createEClass();
                    newClass.setName("NewClass");
                    ePackageInAnotherResourceSet.getESubpackages().get(1).getESubpackages().get(0).getEClassifiers().add(newClass);
                }
            });
            ePackageInAnotherResourceSet.eResource().save(Collections.EMPTY_MAP);
        } catch (IOException e) {
            fail("Pb when saving the resource in another resourceSet : " + e.getMessage());
        }
        SWTBotUtils.waitAllUiEvents();

        // Open the diagram
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_2, REPRESENTATION_NAME_2, DDiagram.class);

        p1EditPart = editor.getEditPart("p1", AbstractDiagramContainerEditPart.class);
        aEditPart = p1EditPart.children().get(2);
        Rectangle aAfter = editor.getBounds(aEditPart);
        assertThat("Unexpected initial position for bordered node 'A'.", aAfter.getLocation(), around(aBefore.getLocation(), ACCEPTABLE_DISTANCE));
    }
}
