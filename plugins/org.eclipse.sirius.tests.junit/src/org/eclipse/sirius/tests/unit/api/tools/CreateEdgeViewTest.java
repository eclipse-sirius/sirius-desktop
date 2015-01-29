/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.api.tools;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;

public class CreateEdgeViewTest extends SiriusDiagramTestCase {

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/ticket1843/My.ecore";

    private static final String REPRESENTATION_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/ticket1843/My.aird";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/ticket1843/ticket1843.odesign";

    private static final String REPRESENTATION_DESC_NAME = "Fixture";

    private DDiagram diagram;

    private EPackage rootPkg;

    private EPackage top1;

    private EPackage top2;

    private EPackage top3;

    private EPackage sub;

    private DiagramEditor editor;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, REPRESENTATION_MODEL_PATH);

        diagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME).toArray()[0];
        assertNotNull(diagram);

        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertNotNull(editor);

        rootPkg = (EPackage) semanticModel;
        top1 = (EPackage) findElementByName(rootPkg, "Top1");
        assertNotNull(top1);
        top2 = (EPackage) findElementByName(rootPkg, "Top2");
        assertNotNull(top2);
        top3 = (EPackage) findElementByName(rootPkg, "Top3");
        assertNotNull(top3);
        sub = (EPackage) findElementByName(rootPkg, "Sub");
        assertNotNull(sub);
    }

    public void testAssociationTool_Top1_Top2() throws Exception {
        checkAssociationToolWorks(top1, top2);
    }

    public void testAssociationTool_Top1_Top3() throws Exception {
        checkAssociationToolWorks(top1, top3);
    }

    public void testAssociationTool_Top1_Sub() throws Exception {
        checkAssociationToolWorks(top1, sub);
    }

    public void testAssociationTool_Sub_Top1() throws Exception {
        checkAssociationToolWorks(top1, sub);
    }

    private void checkAssociationToolWorks(final EPackage sourcePkg, final EPackage targetPkg) {
        // Apply the tool.

        assertTrue(createAssocation(sourcePkg, targetPkg));

        // Check that all the semantic elements are created correctly.
        final EObject dt1 = findElementByName(sourcePkg, "DT1");
        assertTrue(dt1 instanceof EDataType);
        final EObject dt2 = findElementByName(targetPkg, "DT2");
        assertTrue(dt2 instanceof EDataType);
        final EObject assocObj = findElementByName(rootPkg, "Class1");
        assertTrue(assocObj instanceof EClass);
        final EClass assocClass = (EClass) assocObj;
        final EAttribute sourceAttribute = (EAttribute) getFeatureByName(assocClass, "source");
        assertSame(dt1, sourceAttribute.getEType());
        final EAttribute targetAttribute = (EAttribute) getFeatureByName(assocClass, "target");
        assertSame(dt2, targetAttribute.getEType());
        // Check that all the views are created correctly.
        assertTrue(getFirstRepresentationElement(diagram, dt1) instanceof DNode);
        assertNotNull(getGmfNode(((DNode) getFirstRepresentationElement(diagram, dt1))));
        assertTrue(getFirstRepresentationElement(diagram, dt2) instanceof DNode);
        assertNotNull(getGmfNode(((DNode) getFirstRepresentationElement(diagram, dt2))));
        assertEquals("Wrong DNode number", 2, diagram.getNodes().size());

        assertTrue(getFirstRepresentationElement(diagram, assocClass) instanceof DEdge);
        assertNotNull("The view should exist !", getGmfEdge(((DEdge) getFirstRepresentationElement(diagram, assocClass))));
    }

    private boolean createAssocation(final EObject source, final EObject target) {
        final EdgeTarget sourceEdgeTarget = (EdgeTarget) getFirstDiagramElement(diagram, source);
        final EdgeTarget targetEdgeTarget = (EdgeTarget) getFirstDiagramElement(diagram, target);
        return applyEdgeCreationTool("Association", diagram, sourceEdgeTarget, targetEdgeTarget);
    }

    private EStructuralFeature getFeatureByName(final EClass klass, final String name) {
        for (final EStructuralFeature feature : klass.getEAllStructuralFeatures()) {
            if (feature.getName().equals(name)) {
                return feature;
            }
        }
        return null;
    }

    public ENamedElement findElementByName(final ENamedElement root, final String name) {
        if (root.getName().equals(name)) {
            return root;
        } else {
            final TreeIterator<EObject> iter = root.eAllContents();
            while (iter.hasNext()) {
                final EObject obj = iter.next();
                if (obj instanceof ENamedElement && ((ENamedElement) obj).getName().equals(name)) {
                    return (ENamedElement) obj;
                }
            }
        }
        return null;
    }

    @Override
    protected void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(editor, false);

        diagram = null;
        rootPkg = null;
        top1 = null;
        top2 = null;
        top3 = null;
        sub = null;
        editor = null;

        TestsUtil.synchronizationWithUIThread();

        super.tearDown();
    }

}
