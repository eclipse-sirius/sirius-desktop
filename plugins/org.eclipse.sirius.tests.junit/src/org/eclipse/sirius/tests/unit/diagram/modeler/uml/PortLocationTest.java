/*******************************************************************************
 * Copyright (c) 2010, 2017 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.modeler.uml;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.gmf.runtime.common.ui.services.action.contributionitem.ContributionItemService;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.sirius.business.internal.session.danalysis.SaveSessionJob;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactoryProvider;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tools.api.command.ui.NoUICallback;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLFactory;

/**
 * Tests that the port location (layoutConstraint) is not set to 0,0 for each
 * port. Indeed, if this is the case, when a port is moved or deleted, the other
 * port are moved automatically.
 * 
 * This test also check the location and size of collapsed port (see VP-3833).
 * 
 * @author lredor
 */
public class PortLocationTest extends SiriusDiagramTestCase {
    
    
    private static final String RESOURCE_PATH = "/org.eclipse.sirius.tests.junit/data/unit/refresh/grid/";

    private static final String SEMANTIC_MODEL_PATH = RESOURCE_PATH + "noderefresh.uml";

    private static final String MODELER_PATH = RESOURCE_PATH + "noderefresh.odesign";

    private static final String VIEWPOINT_NAME = "UML2";

    private static final String NODE_REPRESENTATION_DESC_NAME = "Node Class and Package Diagram with Ports";

    private static final String CONTAINER_REPRESENTATION_DESC_NAME = "Container Class and Package Diagram with Ports";

    private DDiagram diagram;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(VIEWPOINT_NAME);
    }

    /**
     * Corresponds to ticket #1811. Check that after a creation of a diagram
     * with ports (on node) from the model content view , the layoutConstraint
     * of all ports is not {0, 0}.
     * 
     * @throws Exception
     *             In case of problem
     */
    public void testPortLocationForCreationFromModelContentView() throws Exception {
        TestsUtil.synchronizationWithUIThread();
        Job.getJobManager().join(SaveSessionJob.FAMILY, new NullProgressMonitor());
        // Get the desired package
        final Model model = (Model) semanticModel;
        assertNotNull("Corrupted input data", model);
        // Get the diagram for the root of this model.
        diagram = (DDiagram) createRepresentation(NODE_REPRESENTATION_DESC_NAME, model);
        // Get the class named "Class2"
        PackageableElement element = model.getPackagedElement("Class2");
        assertTrue("Corrupted input data", element instanceof Class);
        final Class class2 = (Class) element;

        // Get the class named "Class3"
        element = model.getPackagedElement("Class3");
        assertTrue("Corrupted input data", element instanceof Class);
        final Class class3 = (Class) element;

        final Property firstProperty = class3.getOwnedAttribute("Prop3to2", class2);
        final Property secondProperty = class3.getOwnedAttribute("OtherProp3to2", class2);

        // Open the editor (and refresh it)
        final IEditorPart editorPart = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        final DDiagramElement firstPropertyDiagramElement = getFirstDiagramElement(diagram, firstProperty);
        assertNotNull("The first property has no corresponding diagramElement", firstPropertyDiagramElement);
        final Node firstPropertyNode = getGmfNode(firstPropertyDiagramElement);
        assertNotNull("The first property has no corresponding GMF node", firstPropertyNode);
        assertTrue("Bag layout constraint type", firstPropertyNode.getLayoutConstraint() instanceof Location);
        final Location firstLocation = (Location) firstPropertyNode.getLayoutConstraint();
        final DDiagramElement secondPropertyDiagramElement = getFirstDiagramElement(diagram, secondProperty);
        assertNotNull("The second property has no corresponding diagramElement", secondPropertyDiagramElement);
        final Node secondPropertyNode = getGmfNode(secondPropertyDiagramElement);
        assertNotNull("The second property has no corresponding GMF node", secondPropertyNode);
        assertTrue("Bag layout constraint type", secondPropertyNode.getLayoutConstraint() instanceof Location);
        final Location secondLocation = (Location) secondPropertyNode.getLayoutConstraint();

        assertFalse("All the layout constraints of the ports should not be in {0,0}", firstLocation.getX() == 0 && firstLocation.getY() == 0 && secondLocation.getX() == 0
                && secondLocation.getY() == 0);
        assertTrue("All the layout constraints of the ports should be different", firstLocation.getX() != secondLocation.getX() || firstLocation.getY() != secondLocation.getY());

        DialectUIManager.INSTANCE.closeEditor(editorPart, false);
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Corresponds to ticket #1811. Check that after a creation of a diagram
     * with ports (on container) from the model content view , the
     * layoutConstraint of all ports is not {0, 0}.
     * 
     * @throws Exception
     *             In case of problem
     */
    public void testPortLocationForCreationFromModelContentView_containerCase() throws Exception {
        TestsUtil.synchronizationWithUIThread();
        Job.getJobManager().join(SaveSessionJob.FAMILY, new NullProgressMonitor());
        // Get the desired package
        final Model model = (Model) semanticModel;
        assertNotNull("Corrupted input data", model);
        // Get the diagram for the root of this model.
        diagram = (DDiagram) createRepresentation(CONTAINER_REPRESENTATION_DESC_NAME, model);
        // Get the class named "Class2"
        PackageableElement element = model.getPackagedElement("Class2");
        assertTrue("Corrupted input data", element instanceof Class);
        final Class class2 = (Class) element;

        // Get the class named "Class3"
        element = model.getPackagedElement("Class3");
        assertTrue("Corrupted input data", element instanceof Class);
        final Class class3 = (Class) element;

        final Property firstProperty = class3.getOwnedAttribute("Prop3to2", class2);
        final Property secondProperty = class3.getOwnedAttribute("OtherProp3to2", class2);

        // Open the editor (and refresh it)
        final IEditorPart editorPart = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        final DDiagramElement firstPropertyDiagramElement = getFirstDiagramElement(diagram, firstProperty);
        assertNotNull("The first property has no corresponding diagramElement", firstPropertyDiagramElement);
        final Node firstPropertyNode = getGmfNode(firstPropertyDiagramElement);
        assertNotNull("The first property has no corresponding GMF node", firstPropertyNode);
        assertTrue("Bag layout constraint type", firstPropertyNode.getLayoutConstraint() instanceof Location);
        final Location firstLocation = (Location) firstPropertyNode.getLayoutConstraint();
        final DDiagramElement secondPropertyDiagramElement = getFirstDiagramElement(diagram, secondProperty);
        assertNotNull("The second property has no corresponding diagramElement", secondPropertyDiagramElement);
        final Node secondPropertyNode = getGmfNode(secondPropertyDiagramElement);
        assertNotNull("The second property has no corresponding GMF node", secondPropertyNode);
        assertTrue("Bag layout constraint type", secondPropertyNode.getLayoutConstraint() instanceof Location);
        final Location secondLocation = (Location) secondPropertyNode.getLayoutConstraint();

        assertFalse("All the layout constraints of the ports should not be in {0,0}", firstLocation.getX() == 0 && firstLocation.getY() == 0 && secondLocation.getX() == 0
                && secondLocation.getY() == 0);
        assertTrue("All the layout constraints of the ports should be different", firstLocation.getX() != secondLocation.getX() || firstLocation.getY() != secondLocation.getY());

        DialectUIManager.INSTANCE.closeEditor(editorPart, false);
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Corresponds to ticket #1811. Check that after a creation of a diagram
     * with ports from an existing diagram, the layoutConstraint of all ports is
     * not {0, 0}.
     * 
     * @throws Exception
     *             In case of problem
     */
    public void testPortLocationForCreationFromNavigation() throws Exception {
        TestsUtil.synchronizationWithUIThread();
        Job.getJobManager().join(SaveSessionJob.FAMILY, new NullProgressMonitor());
        // Get the desired package
        final Model model = (Model) semanticModel;
        assertNotNull("Corrupted input data", model);
        // Get the diagram for the root of this model.
        diagram = (DDiagram) createRepresentation(NODE_REPRESENTATION_DESC_NAME, model);

        // Open the editor (and refresh it)
        final IEditorPart editorPart = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        // Set the focus to the desired package
        assertTrue("We should have a DiagramDocumentEditor", editorPart instanceof DiagramDocumentEditor);

        final DiagramDocumentEditor diagramEditor = (DiagramDocumentEditor) editorPart;

        // Get the package named "SubPackage"
        PackageableElement element = model.getPackagedElement("SubPackage");
        assertTrue("Corrupted input data", element instanceof Package);
        final Package packageSubPackage = (Package) element;

        IMenuManager popupMenu = new MenuManager();
        popupMenu.add(new MenuManager(IWorkbenchActionConstants.MB_ADDITIONS, IWorkbenchActionConstants.MB_ADDITIONS));//$NON-NLS-1$ //$NON-NLS-2$
        popupMenu.add(new MenuManager("popup.new", "popup.new"));//$NON-NLS-1$

        // Set the focus to package SubPackage
        IGraphicalEditPart elementEditPart = getEditPart(getFirstDiagramElement(diagram, packageSubPackage));
        diagramEditor.getDiagramGraphicalViewer().setFocus(elementEditPart);

        DNode focusedElement = (DNode) ((GraphicalEditPart) diagramEditor.getDiagramGraphicalViewer().getFocusEditPart()).resolveSemanticElement();
        assertTrue("This is not the good focused element", focusedElement.getTarget() instanceof Package);
        assertEquals("This is not the good focused element", "SubPackage", ((Package) focusedElement.getTarget()).getName());

        ContributionItemService.getInstance().contributeToPopupMenu(popupMenu, diagramEditor);

        // Check the popup menu.
        IMenuManager newRepresentationMenu = (IMenuManager) popupMenu.find("popup.new");
        IContributionItem[] items = newRepresentationMenu.getItems();

        boolean inNewRepresentationToGroup = false;

        ActionContributionItem actionContribution = null;

        for (int i = 0; i < items.length; i++) {
            if (items[i] instanceof Separator) {
                Separator sep = (Separator) items[i];
                if ("createRepresentationGroup".equals(sep.getId())) {
                    inNewRepresentationToGroup = true;
                } else {
                    inNewRepresentationToGroup = false;
                }
            }
            if (inNewRepresentationToGroup && items[i] instanceof ActionContributionItem) {
                assertNull("There should be only one ActionContributionItem", actionContribution);
                actionContribution = (ActionContributionItem) items[i];
            }
        }

        assertNotNull("There should be one ActionContributionItem", actionContribution);
        final IAction action = actionContribution.getAction();

        assertEquals("Action has not the correct text", "New diagram", action.getText());

        // Disabling ui callback of diagram command factory
        final Object adapter = diagramEditor.getAdapter(IDiagramCommandFactoryProvider.class);
        final IDiagramCommandFactoryProvider diagramCmdFactoryProvider = (IDiagramCommandFactoryProvider) adapter;
        final IDiagramCommandFactory diagramCommandFactory = diagramCmdFactoryProvider.getCommandFactory(session.getTransactionalEditingDomain());
        diagramCommandFactory.setUserInterfaceCallBack(new NoUICallback());

        action.run();

        IEditorPart editor2 = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
        assertTrue("We should have a DiagramDocumentEditor", editor2 instanceof DiagramDocumentEditor);

        final DiagramDocumentEditor diagramEditor2 = (DiagramDocumentEditor) editor2;
        GraphicalEditPart diagramPart = diagramEditor2.getDiagramEditPart();
        final DSemanticDiagram diagram2 = (DSemanticDiagram) diagramPart.resolveSemanticElement();

        assertEquals("The opened diagram is not valid", diagram2.getTarget(), focusedElement.getTarget());

        // Get the class named "SubClass1"
        element = packageSubPackage.getPackagedElement("SubClass1");
        assertTrue("Corrupted input data", element instanceof Class);
        final Class subClass1 = (Class) element;

        final Property firstProperty = subClass1.getOwnedAttribute("Prop1", subClass1);
        final Property secondProperty = subClass1.getOwnedAttribute("Prop2", subClass1);

        final DDiagramElement firstPropertyDiagramElement = getFirstDiagramElement(diagram2, firstProperty);
        assertNotNull("The first property has no corresponding diagramElement", firstPropertyDiagramElement);
        final Node firstPropertyNode = getGmfNode(firstPropertyDiagramElement);
        assertNotNull("The first property has no corresponding GMF node", firstPropertyNode);
        assertTrue("Bag layout constraint type", firstPropertyNode.getLayoutConstraint() instanceof Location);
        final Location firstLocation = (Location) firstPropertyNode.getLayoutConstraint();
        final DDiagramElement secondPropertyDiagramElement = getFirstDiagramElement(diagram2, secondProperty);
        assertNotNull("The second property has no corresponding diagramElement", secondPropertyDiagramElement);
        final Node secondPropertyNode = getGmfNode(secondPropertyDiagramElement);
        assertNotNull("The second property has no corresponding GMF node", secondPropertyNode);
        assertTrue("Bag layout constraint type", secondPropertyNode.getLayoutConstraint() instanceof Location);
        final Location secondLocation = (Location) secondPropertyNode.getLayoutConstraint();

        assertFalse("All the layout constraints of the ports should not be in {0,0}", firstLocation.getX() == 0 && firstLocation.getY() == 0 && secondLocation.getX() == 0
                && secondLocation.getY() == 0);
        assertTrue("All the layout constraints of the ports should be different", firstLocation.getX() != secondLocation.getX() || firstLocation.getY() != secondLocation.getY());

        DialectUIManager.INSTANCE.closeEditor(editorPart, false);
        TestsUtil.synchronizationWithUIThread();

        DialectUIManager.INSTANCE.closeEditor(editor2, false);
        TestsUtil.synchronizationWithUIThread();

    }

    /**
     * Open the diagram, modify the semantic (causing creation of new ports),
     * check the position of new ports (not the same). See VP-3095
     */
    protected void testPortLocationAfterRefresh(boolean activateCollapseFilter) throws Exception {
        TestsUtil.synchronizationWithUIThread();
        Job.getJobManager().join(SaveSessionJob.FAMILY, new NullProgressMonitor());
        // Get the desired package
        final Model model = (Model) semanticModel;
        assertNotNull("Corrupted input data", model);
        // Get the diagram for the root of this model.
        diagram = (DDiagram) createRepresentation(NODE_REPRESENTATION_DESC_NAME, model);
        // Get the class named "Class2"
        PackageableElement element = model.getPackagedElement("Class2");
        assertTrue("Corrupted input data", element instanceof Class);
        final Class class2 = (Class) element;

        // Get the class named "Class3"
        element = model.getPackagedElement("Class3");
        assertTrue("Corrupted input data", element instanceof Class);
        final Class class3 = (Class) element;

        final Property firstProperty = class3.getOwnedAttribute("Prop3to2", class2);
        final Property secondProperty = class3.getOwnedAttribute("OtherProp3to2", class2);

        // Open the editor (and refresh it)
        final IEditorPart editorPart = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        if (activateCollapseFilter) {
            activateFilter(diagram, "FilterPropertyStartingWithA");
        }

        // Add 3 Property to class3 (that will cause the creation of 3
        // new ports during refresh).
        addProperties(class3, class2, "Prop3to2_", 3);
        // Refresh the diagram
        refresh(diagram);

        final DDiagramElement firstPropertyDiagramElement = getFirstDiagramElement(diagram, firstProperty);
        assertNotNull("The first property has no corresponding diagramElement", firstPropertyDiagramElement);
        final Node firstPropertyNode = getGmfNode(firstPropertyDiagramElement);
        assertNotNull("The first property has no corresponding GMF node", firstPropertyNode);
        assertTrue("Bag layout constraint type", firstPropertyNode.getLayoutConstraint() instanceof Location);
        final Location firstLocation = (Location) firstPropertyNode.getLayoutConstraint();
        final DDiagramElement secondPropertyDiagramElement = getFirstDiagramElement(diagram, secondProperty);
        assertNotNull("The second property has no corresponding diagramElement", secondPropertyDiagramElement);
        final Node secondPropertyNode = getGmfNode(secondPropertyDiagramElement);
        assertNotNull("The second property has no corresponding GMF node", secondPropertyNode);
        assertTrue("Bag layout constraint type", secondPropertyNode.getLayoutConstraint() instanceof Location);
        final Location secondLocation = (Location) secondPropertyNode.getLayoutConstraint();

        assertFalse("All the layout constraints of the ports should not be in {0,0}", firstLocation.getX() == 0 && firstLocation.getY() == 0 && secondLocation.getX() == 0
                && secondLocation.getY() == 0);
        assertTrue("All the layout constraints of the ports should be different", firstLocation.getX() != secondLocation.getX() || firstLocation.getY() != secondLocation.getY());

        final Property thirdProperty = class3.getOwnedAttribute("Prop3to2_1", class2);
        final Property fourthProperty = class3.getOwnedAttribute("Prop3to2_2", class2);
        final Property fifthProperty = class3.getOwnedAttribute("Prop3to2_3", class2);
        final DDiagramElement thirdPropertyDiagramElement = getFirstDiagramElement(diagram, thirdProperty);
        assertNotNull("The third property has no corresponding diagramElement", thirdPropertyDiagramElement);
        final Node thirdPropertyNode = getGmfNode(thirdPropertyDiagramElement);
        assertNotNull("The third property has no corresponding GMF node", thirdPropertyNode);
        assertTrue("Bag layout constraint type", thirdPropertyNode.getLayoutConstraint() instanceof Location);
        final Location thirdLocation = (Location) thirdPropertyNode.getLayoutConstraint();
        final DDiagramElement fourthPropertyDiagramElement = getFirstDiagramElement(diagram, fourthProperty);
        assertNotNull("The fourth property has no corresponding diagramElement", fourthPropertyDiagramElement);
        final Node fourthPropertyNode = getGmfNode(fourthPropertyDiagramElement);
        assertNotNull("The fourth property has no corresponding GMF node", fourthPropertyNode);
        assertTrue("Bag layout constraint type", fourthPropertyNode.getLayoutConstraint() instanceof Location);
        final Location fourthLocation = (Location) fourthPropertyNode.getLayoutConstraint();
        final DDiagramElement fifthPropertyDiagramElement = getFirstDiagramElement(diagram, fifthProperty);
        assertNotNull("The fifth property has no corresponding diagramElement", fifthPropertyDiagramElement);
        final Node fifthPropertyNode = getGmfNode(fifthPropertyDiagramElement);
        assertNotNull("The fifth property has no corresponding GMF node", fifthPropertyNode);
        assertTrue("Bag layout constraint type", fifthPropertyNode.getLayoutConstraint() instanceof Location);
        final Location fifthLocation = (Location) fifthPropertyNode.getLayoutConstraint();

        assertFalse("All the layout constraints of the ports should not be in {0,0}", thirdLocation.getX() == 0 && thirdLocation.getY() == 0 && fourthLocation.getX() == 0
                && fourthLocation.getY() == 0 && fifthLocation.getX() == 0 && fifthLocation.getY() == 0);
        assertTrue("All the layout constraints of the ports should be different", thirdLocation.getX() != fourthLocation.getX() || thirdLocation.getY() != fourthLocation.getY());
        assertTrue("All the layout constraints of the ports should be different", thirdLocation.getX() != fifthLocation.getX() || thirdLocation.getY() != fifthLocation.getY());
        assertTrue("All the layout constraints of the ports should be different", fifthLocation.getX() != fourthLocation.getX() || fifthLocation.getY() != fourthLocation.getY());

        DialectUIManager.INSTANCE.closeEditor(editorPart, false);
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Open the diagram, modify the semantic (causing creation of new ports),
     * check the position of new ports (not the same). See VP-3095
     * 
     * @throws Exception
     *             In case of problem
     */
    public void testPortLocationAfterRefresh() throws Exception {
        testPortLocationAfterRefresh(false);
    }

    /**
     * Open the diagram, modify the semantic (causing creation of new ports),
     * check the position of new ports (not the same). See VP-3095
     * 
     * @throws Exception
     *             In case of problem
     */
    public void testPortLocationAfterRefreshWithActivatedCollapseFilter() throws Exception {
        testPortLocationAfterRefresh(true);
    }

    /**
     * Add <code>nbToAdd</code> {@link Property} to <code>parent</code> (that
     * will cause the creation of <code>nbToAdd</code> new ports during
     * refresh).
     * 
     * @param parent
     *            The parent {@link Class} of the new {@link Property}.
     * @param typeOfProperty
     *            The type of the new Property
     * @param prefix
     *            the prefix of the name for the new property (followed by 1, 2,
     *            3, ...).
     * @param nbToAdd
     *            number of property to create
     */
    private void addProperties(final Class parent, final Class typeOfProperty, final String prefix, final int nbToAdd) {
        executeCommand(new RecordingCommand(session.getTransactionalEditingDomain(), "Add new property to have new ports") {
            @Override
            protected void doExecute() {
                for (int i = 0; i < nbToAdd; i++) {
                    Property newProperty = UMLFactory.eINSTANCE.createProperty();
                    newProperty.setName(prefix + (i + 1));
                    newProperty.setType(typeOfProperty);
                    parent.getOwnedAttributes().add(newProperty);
                }
            }
        });
    }

    /**
     * Remove <code>nbToRemove</code> {@link Property} from a {@link Class}.
     * 
     * @param parent
     *            The parent {@link Class} of the {@link Property} to remove.
     * @param nbToRemove
     *            Number of properties to remove (from the last)
     */
    private void removeProperties(final Class parent, final int nbToRemove) {
        executeCommand(new RecordingCommand(session.getTransactionalEditingDomain(), "Add new property to have new ports") {
            @Override
            protected void doExecute() {
                for (int i = 0; i < nbToRemove; i++) {
                    parent.getOwnedAttributes().remove(parent.getOwnedAttributes().size() - 1);
                }
            }
        });
    }

    /**
     * Check that :
     * <UL>
     * <LI>the size of new collapsed bordered nodes is 1x1 (and not the same as
     * not collapsed),</LI>
     * <LI>the space between two new collapsed bordered nodes is the same as
     * between two new normal bordered nodes.</LI>
     * <LI>the size of an existing bordered nodes is 1x1 after collapse filter
     * application, and its location is contains in the original bounds.</LI>
     * </UL>
     * 
     * Steps of this test:
     * <UL>
     * <LI>Open a first diagram</LI>
     * <UL>
     * <LI>modify the semantic (causing creation of new ports),</LI>
     * <LI>take the bounds of new ports (without collapse filter),</LI>
     * <LI>take the space between them,</LI>
     * <LI>apply the collapse filter,</LI>
     * <LI>check that the size of the port is 1x1 and its new location is
     * contained in the old bounds,</LI>
     * <LI>close the diagram,</LI>
     * <LI>undo the semantic modifications,</LI>
     * </UL>
     * </LI>
     * <LI>Open a new diagram,</LI>
     * <UL>
     * <LI>enable the collapse filter,</LI>
     * <LI>make the same semantic modifications (causing creation of new ports),
     * </LI>
     * <LI>compare the size ans the space with previous diagram.</LI>
     * </UL>
     * </UL>
     * See VP-3833
     * 
     * @throws Exception
     *             In case of problem
     */
    public void testPortLocationsAndMoreWithForCollapseFilter() throws Exception {
        TestsUtil.synchronizationWithUIThread();
        Job.getJobManager().join(SaveSessionJob.FAMILY, new NullProgressMonitor());
        // Get the desired package
        final Model model = (Model) semanticModel;
        assertNotNull("Corrupted input data", model);

        // Get the class named "Class2"
        PackageableElement element = model.getPackagedElement("Class2");
        assertTrue("Corrupted input data", element instanceof Class);
        final Class class2 = (Class) element;

        // Get the class named "Class3"
        element = model.getPackagedElement("Class3");
        assertTrue("Corrupted input data", element instanceof Class);
        final Class class3 = (Class) element;

        // Get the diagram for the root of this model.
        diagram = (DDiagram) createRepresentation(NODE_REPRESENTATION_DESC_NAME, model);
        // Open the editor (and refresh it)
        IEditorPart editorPart = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        // Add 3 Property to class3 (that will cause the creation of 3
        // new ports during refresh)
        String prefix = "aPort";
        addProperties(class3, class2, "aPort", 3);
        // Refresh the diagram
        refresh(diagram);

        Property thirdProperty = class3.getOwnedAttribute(prefix + 1, class2);
        DDiagramElement thirdPropertyDiagramElement = getFirstDiagramElement(diagram, thirdProperty);
        Node thirdPropertyNode = getGmfNode(thirdPropertyDiagramElement);
        final Bounds expectedThirdBounds = (Bounds) thirdPropertyNode.getLayoutConstraint();

        Property fourthProperty = class3.getOwnedAttribute(prefix + 2, class2);
        DDiagramElement fourthPropertyDiagramElement = getFirstDiagramElement(diagram, fourthProperty);
        Node fourthPropertyNode = getGmfNode(fourthPropertyDiagramElement);
        final Bounds expectedFourthBounds = (Bounds) fourthPropertyNode.getLayoutConstraint();

        Property fifthProperty = class3.getOwnedAttribute(prefix + 3, class2);
        DDiagramElement fifthPropertyDiagramElement = getFirstDiagramElement(diagram, fifthProperty);
        Node fifthPropertyNode = getGmfNode(fifthPropertyDiagramElement);
        final Bounds expectedFifthBounds = (Bounds) fifthPropertyNode.getLayoutConstraint();
        // Activate the filter
        activateFilter(diagram, "FilterPropertyStartingWithA");
        // Refresh the diagram
        refresh(diagram);
        // Check the size and the new location of one of the collapsed bordered
        // node
        final Bounds collapseThirdBounds = (Bounds) thirdPropertyNode.getLayoutConstraint();
        assertEquals("The width of the collapse bordered node is not OK.", 1, collapseThirdBounds.getWidth());
        assertEquals("The height of the collapse bordered node is not OK.", 1, collapseThirdBounds.getHeight());
        assertTrue("The new collapsed location should be contains in the not collapsed bounds.", new Rectangle(expectedThirdBounds.getX(), expectedThirdBounds.getY(), expectedThirdBounds.getWidth(),
                expectedThirdBounds.getHeight()).contains(new Point(collapseThirdBounds.getX(), collapseThirdBounds.getY())));

        DialectUIManager.INSTANCE.closeEditor(editorPart, false);
        TestsUtil.synchronizationWithUIThread();

        // Remove the 3 added properties
        removeProperties(class3, 3);

        // Get the diagram for the root of this model.
        diagram = (DDiagram) createRepresentation(NODE_REPRESENTATION_DESC_NAME, model);
        // Open the editor (and refresh it)
        editorPart = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        // Activate the filter
        activateFilter(diagram, "FilterPropertyStartingWithA");

        // Add 3 Property to class3 (that will cause the creation of 3
        // new ports during refresh)
        addProperties(class3, class2, "aPort", 3);
        // Refresh the diagram
        refresh(diagram);

        thirdProperty = class3.getOwnedAttribute(prefix + 1, class2);
        thirdPropertyDiagramElement = getFirstDiagramElement(diagram, thirdProperty);
        thirdPropertyNode = getGmfNode(thirdPropertyDiagramElement);
        Bounds thirdCollapseBounds = (Bounds) thirdPropertyNode.getLayoutConstraint();
        assertEquals("The width of a new collapsed bordered node is not correct.", 1, thirdCollapseBounds.getWidth());
        assertEquals("The height of a new collapsed bordered node is not correct.", 1, thirdCollapseBounds.getHeight());

        fourthProperty = class3.getOwnedAttribute(prefix + 2, class2);
        fourthPropertyDiagramElement = getFirstDiagramElement(diagram, fourthProperty);
        fourthPropertyNode = getGmfNode(fourthPropertyDiagramElement);
        Bounds fourthCollapseBounds = (Bounds) fourthPropertyNode.getLayoutConstraint();
        assertEquals("The width of a new collapsed bordered node is not correct.", 1, fourthCollapseBounds.getWidth());
        assertEquals("The height of a new collapsed bordered node is not correct.", 1, fourthCollapseBounds.getHeight());

        fifthProperty = class3.getOwnedAttribute(prefix + 3, class2);
        fifthPropertyDiagramElement = getFirstDiagramElement(diagram, fifthProperty);
        fifthPropertyNode = getGmfNode(fifthPropertyDiagramElement);
        Bounds fifthCollapseBounds = (Bounds) fifthPropertyNode.getLayoutConstraint();
        assertEquals("The width of a new collapsed bordered node is not correct.", 1, fifthCollapseBounds.getWidth());
        assertEquals("The height of a new collapsed bordered node is not correct.", 1, fifthCollapseBounds.getHeight());

        assertEquals("The space bewteen the collapsed bordered nodes should be the same as between the not collapsed bordered nodes.", expectedFourthBounds.getY() - expectedThirdBounds.getY(),
                fourthCollapseBounds.getY() - thirdCollapseBounds.getY());
        assertEquals("The space bewteen the collapsed bordered nodes should be the same as between the not collapsed bordered nodes.", expectedFifthBounds.getY() - expectedFourthBounds.getY(),
                fifthCollapseBounds.getY() - fourthCollapseBounds.getY());

        DialectUIManager.INSTANCE.closeEditor(editorPart, false);
        TestsUtil.synchronizationWithUIThread();
    }
}
