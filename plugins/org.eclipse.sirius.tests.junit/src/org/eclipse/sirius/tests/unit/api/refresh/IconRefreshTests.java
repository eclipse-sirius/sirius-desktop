/*******************************************************************************
 * Copyright (c) 2015, 2021 Obeo.
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

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.provider.AdapterFactoryItemDelegator;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.LabelPosition;
import org.eclipse.sirius.diagram.NodeStyle;
import org.eclipse.sirius.diagram.ui.edit.api.part.IAbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramNameEditPart;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.part.IDiagramDialectGraphicalViewer;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.SiriusWrapLabel;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.ImageEquality;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.swt.graphics.Image;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.VisibilityKind;

import com.google.common.collect.Iterables;

/**
 * Test the label's icon refresh on semantic change with customized XXXItemProvider.getImage() method.<BR/>
 * Also test a bug about labels of border nodes with conditional style (bug 570055).
 *
 * @see bug 475685
 * @see bug 570055
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 * @author lredor
 */
public class IconRefreshTests extends SiriusDiagramTestCase {

    private static final String PATH = "/data/unit/refresh/iconRefresh/Bug475685/";

    private static final String MODELER_RESOURCE_NAME = "Bug475685.odesign";

    private static final String SEMANTIC_RESOURCE_NAME = "Bug475685.uml";

    private static final String SESSION_RESOURCE_NAME = "Bug475685.aird";

    private DDiagram dDiagram;

    private DiagramEditor diagramEditor;

    private TransactionalEditingDomain domain;

    private ComposedAdapterFactory adapterFactory;

    private AdapterFactoryItemDelegator adapterFactoryItemDelegator;

    private IDiagramDialectGraphicalViewer viewer;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID + PATH + MODELER_RESOURCE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + MODELER_RESOURCE_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID + PATH + SEMANTIC_RESOURCE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID + PATH + SESSION_RESOURCE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_NAME);

        genericSetUp("/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + MODELER_RESOURCE_NAME,
                "/" + TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_NAME);

        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        changeSiriusPreference(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), false);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), false);

        dDiagram = (DDiagram) DialectManager.INSTANCE.getAllRepresentations(session).iterator().next();
        diagramEditor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, dDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        domain = session.getTransactionalEditingDomain();
        adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
        adapterFactoryItemDelegator = new AdapterFactoryItemDelegator(adapterFactory);
        viewer = (IDiagramDialectGraphicalViewer) diagramEditor.getDiagramGraphicalViewer();
    }

    /**
     * Test the label's icon refresh on semantic change, i.e.
     * NamedElement.visibility attribute change.
     * 
     * @throws Exception
     *             thrown cause failure
     */
    public void testLabelIconRefreshedOnSemanticChange() throws Exception {
        CommandStack commandStack = domain.getCommandStack();
        TreeIterator<EObject> eAllContents = dDiagram.eAllContents();
        while (eAllContents.hasNext()) {
            EObject viewElt = eAllContents.next();
            if (viewElt instanceof DSemanticDecorator) {
                EObject semanticElt = ((DSemanticDecorator) viewElt).getTarget();
                assertTrue("The semantic element is not a NamedElement.", semanticElt instanceof NamedElement);
                NamedElement namedElement = (NamedElement) semanticElt;

                checkLabelIcon(namedElement);

                for (VisibilityKind visibilityKind : VisibilityKind.VALUES) {
                    Command changeVisibilityCmd = SetCommand.create(domain, namedElement, UMLPackage.Literals.NAMED_ELEMENT__VISIBILITY, visibilityKind);
                    commandStack.execute(changeVisibilityCmd);
                    TestsUtil.synchronizationWithUIThread();

                    checkLabelIcon(namedElement);

                    undo();
                    TestsUtil.synchronizationWithUIThread();

                    checkLabelIcon(namedElement);
                }
            } else {
                eAllContents.prune();
            }
        }
    }

    private void checkLabelIcon(NamedElement namedElement) {
        Object result = adapterFactoryItemDelegator.getImage(namedElement);
        ImageDescriptor imageDescriptor = ExtendedImageRegistry.getInstance().getImageDescriptor(result);
        Image expectedImage = DiagramUIPlugin.getPlugin().getImage(imageDescriptor);
        Collection<IDiagramElementEditPart> editParts = new HashSet<IDiagramElementEditPart>();
        for (IDiagramElementEditPart editPart : viewer.findEditPartsForElement(namedElement, IDiagramElementEditPart.class)) {
            DDiagramElement dDiagramElement = editPart.resolveDiagramElement();
            Style style = dDiagramElement.getStyle();
            // If LabelPosition.BORDER_LITERAL or Edge then the
            // SiriusWrapLabel is in a child IDiagramNameEditPart
            if (style instanceof NodeStyle && ((NodeStyle) style).getLabelPosition() == LabelPosition.BORDER_LITERAL || style instanceof EdgeStyle) {
                Iterables.addAll(editParts, Iterables.filter(editPart.getChildren(), IDiagramNameEditPart.class));
            } else {
                editParts.add(editPart);
            }
        }
        assertFalse("They should have EditParts to represent semantic element : " + adapterFactoryItemDelegator.getText(namedElement), editParts.isEmpty());
        for (IDiagramElementEditPart editPart : editParts) {

            SiriusWrapLabel siriusWrapLabel = null;
            if (editPart instanceof IAbstractDiagramNodeEditPart) {
                siriusWrapLabel = ((IAbstractDiagramNodeEditPart) editPart).getNodeLabel();
            } else if (editPart.getFigure() instanceof SiriusWrapLabel) {
                siriusWrapLabel = (SiriusWrapLabel) editPart.getFigure();
            }
            assertNotNull("We should have a SiriusWrapLabel to display a icon", siriusWrapLabel);
            Dimension labelSize = siriusWrapLabel.getSize();
            assertFalse("The width of the label figure, for " + siriusWrapLabel.getText() + ", should not be equal to 1 pixel.", labelSize.width() == 1);
            assertFalse("The height of the label figure, for \" + siriusWrapLabel.getText() + \", should not be equal to  1 pixel.", labelSize.height() == 1);
            Image currentImage = siriusWrapLabel.getIcon();
            assertNotNull("An image should be displayed for : " + adapterFactoryItemDelegator.getText(namedElement), currentImage);
            boolean areEqualImages = ImageEquality.areEqualImages(expectedImage, currentImage);
            assertTrue("Icon of EditPart \"" + editPart + "\" representing \"" + adapterFactoryItemDelegator.getText(namedElement) + "\" should be same as the one from XXXItemProvider",
                    areEqualImages);
        }
    }

    @Override
    protected void tearDown() throws Exception {
        viewer = null;
        adapterFactoryItemDelegator = null;
        adapterFactory.dispose();
        adapterFactory = null;
        domain = null;
        DialectUIManager.INSTANCE.closeEditor(diagramEditor, false);
        TestsUtil.synchronizationWithUIThread();
        diagramEditor = null;
        dDiagram = null;
        super.tearDown();
    }
}
