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
package org.eclipse.sirius.tests.unit.diagram.filter;

import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.AppliedCompositeFilters;
import org.eclipse.sirius.diagram.CollapseFilter;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.IndirectlyCollapseFilter;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Tests to check the behavior of graphical filters computation.
 * 
 * @author mporhel
 */
public class CompositeFilterTest extends SiriusDiagramTestCase {

    private static final String CREATE_OPERATION_TOOL = "createOperation";

    private static final String CREATE_CLASS_TOOL = "createClass";

    private static final String CREATE_CLASS_TOOL_FORCE_REFRESH = "createClassForceRefresh";

    private static final String HIDE_CONTENTS = "HideContents";

    private static final String COLLAPSE_CONTENTS = "CollapseContents";

    private static final String HIDE_REFERENCE_FILTER_NAME = "HideReference";

    private static final String REFERENCE_CREATE_TOOL_NAME = "Reference";

    private static final String EDGE_PB_REPRESENTATION_DESC_NAME = "Diag2399";

    private static final String COLLAPSE_HIDE_CONTENTS = "CollapseHideContents";

    private static final String COLLAPSE_HIDE_CONDITION_CONTENTS = "CollapseHideContentsCondition";

    private static final String HIDE_ATT_CONDITION_CONTENTS = "activateCollapseAtt";

    private static final String HIDE_REF_CONDITION_CONTENTS = "activateHideRef";

    private static final String HIDE_EATTRIBUTES = "HideAttributes";

    private static final String COLLAPSE_EATTRIBUTES = "CollapseAttributes";

    private static final String HIDE_ECLASSES = "HideClasses";

    private static final String COLLAPSE_ECLASSES = "CollapseClasses";

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/compositefilter/ticket2174/tc2174.ecore";

    private static final String SESSION_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/compositefilter/ticket2174/tc2174.aird";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/compositefilter/ticket2174/tc2174.odesign";

    private static final String REPRESENTATION_DESC_NAME = "tc2174";

    private DDiagram diagram;

    private DiagramEditor editor;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        TestsUtil.emptyEventsFromUIThread();

        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, SESSION_MODEL_PATH);

        diagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME).toArray()[0];
        assertNotNull(diagram);
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertNotNull(editor);
    }

    /**
     * Tests.
     * 
     * @throws Exception
     */
    public void testNoFilterApplicationWhenCollapse() throws Exception {
        checkNoFilterApplicationForDiagramElements(true);
        checkNoCollapseApplicationForDiagramElements();

        activateFilter(diagram, COLLAPSE_EATTRIBUTES);

        checkNoFilterApplicationForDiagramElements(true);

        deactivateFilter(diagram, COLLAPSE_EATTRIBUTES);

        checkNoFilterApplicationForDiagramElements(true);
        checkNoCollapseApplicationForDiagramElements();
    }

    /**
     * Tests.
     * 
     * @throws Exception
     */
    public void testNoCollapseApplicationWhenFilter() throws Exception {
        checkNoFilterApplicationForDiagramElements(true);
        checkNoCollapseApplicationForDiagramElements();

        activateFilter(diagram, HIDE_EATTRIBUTES);

        checkNoCollapseApplicationForDiagramElements();

        deactivateFilter(diagram, HIDE_EATTRIBUTES);

        checkNoFilterApplicationForDiagramElements(true);
        checkNoCollapseApplicationForDiagramElements();
    }

    /**
     * Tests.
     * 
     * @throws Exception
     */
    public void testCompositeFilterActivation() throws Exception {
        checkNoFilterApplicationForDiagramElements(true);
        checkNoCollapseApplicationForDiagramElements();

        activateFilter(diagram, HIDE_CONTENTS);

        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 0, 2, true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEAttribute(), false, getFilter(diagram, HIDE_CONTENTS));
        checkFilterApplication(EcorePackage.eINSTANCE.getEReference(), false, getFilter(diagram, HIDE_CONTENTS));
        checkFilterApplication(EcorePackage.eINSTANCE.getEOperation(), false, getFilter(diagram, HIDE_CONTENTS));
        checkNoCollapseApplicationForDiagramElements();

        activateFilter(diagram, HIDE_EATTRIBUTES);

        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 0, 2, true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEAttribute(), false, getFilter(diagram, HIDE_CONTENTS), getFilter(diagram, HIDE_EATTRIBUTES));
        checkFilterApplication(EcorePackage.eINSTANCE.getEReference(), false, getFilter(diagram, HIDE_CONTENTS));
        checkFilterApplication(EcorePackage.eINSTANCE.getEOperation(), false, getFilter(diagram, HIDE_CONTENTS));
        checkNoCollapseApplicationForDiagramElements();

        deactivateFilter(diagram, HIDE_CONTENTS);

        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 0, 2, true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEAttribute(), false, getFilter(diagram, HIDE_EATTRIBUTES));
        checkFilterApplication(EcorePackage.eINSTANCE.getEReference(), false);
        checkFilterApplication(EcorePackage.eINSTANCE.getEOperation(), false);
        checkNoCollapseApplicationForDiagramElements();

        deactivateFilter(diagram, HIDE_EATTRIBUTES);

        checkNoFilterApplicationForDiagramElements(true);
        checkNoCollapseApplicationForDiagramElements();
    }

    /**
     * Tests.
     * 
     * @throws Exception
     */
    public void testCompositeFilterActivationOnParent() throws Exception {
        checkNoFilterApplicationForDiagramElements(true);
        checkNoCollapseApplicationForDiagramElements();

        activateFilter(diagram, HIDE_ECLASSES);

        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 0, 2, false, getFilter(diagram, HIDE_ECLASSES));
        checkFilterApplication(EcorePackage.eINSTANCE.getEAttribute(), true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEReference(), true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEOperation(), true);
        checkNoCollapseApplicationForDiagramElements();

        activateFilter(diagram, HIDE_EATTRIBUTES);

        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 0, 2, false, getFilter(diagram, HIDE_ECLASSES));
        checkFilterApplication(EcorePackage.eINSTANCE.getEAttribute(), false, getFilter(diagram, HIDE_EATTRIBUTES));
        checkFilterApplication(EcorePackage.eINSTANCE.getEReference(), true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEOperation(), true);
        checkNoCollapseApplicationForDiagramElements();

        activateFilter(diagram, HIDE_CONTENTS);

        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 0, 2, false, getFilter(diagram, HIDE_ECLASSES));
        checkFilterApplication(EcorePackage.eINSTANCE.getEAttribute(), false, getFilter(diagram, HIDE_CONTENTS), getFilter(diagram, HIDE_EATTRIBUTES));
        checkFilterApplication(EcorePackage.eINSTANCE.getEReference(), false, getFilter(diagram, HIDE_CONTENTS));
        checkFilterApplication(EcorePackage.eINSTANCE.getEOperation(), false, getFilter(diagram, HIDE_CONTENTS));
        checkNoCollapseApplicationForDiagramElements();

        deactivateFilter(diagram, HIDE_ECLASSES);

        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 0, 2, true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEAttribute(), false, getFilter(diagram, HIDE_CONTENTS), getFilter(diagram, HIDE_EATTRIBUTES));
        checkFilterApplication(EcorePackage.eINSTANCE.getEReference(), false, getFilter(diagram, HIDE_CONTENTS));
        checkFilterApplication(EcorePackage.eINSTANCE.getEOperation(), false, getFilter(diagram, HIDE_CONTENTS));
        checkNoCollapseApplicationForDiagramElements();

        deactivateFilter(diagram, HIDE_CONTENTS);

        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 0, 2, true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEAttribute(), false, getFilter(diagram, HIDE_EATTRIBUTES));
        checkFilterApplication(EcorePackage.eINSTANCE.getEReference(), true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEOperation(), true);
        checkNoCollapseApplicationForDiagramElements();

        deactivateFilter(diagram, HIDE_EATTRIBUTES);

        checkNoFilterApplicationForDiagramElements(true);
        checkNoCollapseApplicationForDiagramElements();
    }

    /**
     * Test.
     * 
     * @throws Exception
     */
    public void testCollapseActivation() throws Exception {
        checkNoFilterApplicationForDiagramElements(true);
        checkNoCollapseApplicationForDiagramElements();

        activateFilter(diagram, COLLAPSE_CONTENTS);

        checkCollapseApplication(EcorePackage.eINSTANCE.getEClass(), 2);
        checkCollapseApplication(EcorePackage.eINSTANCE.getEAttribute(), getFilter(diagram, COLLAPSE_CONTENTS));
        checkCollapseApplication(EcorePackage.eINSTANCE.getEReference(), getFilter(diagram, COLLAPSE_CONTENTS));
        checkCollapseApplication(EcorePackage.eINSTANCE.getEOperation(), getFilter(diagram, COLLAPSE_CONTENTS));
        checkNoFilterApplicationForDiagramElements(true);

        activateFilter(diagram, COLLAPSE_EATTRIBUTES);

        checkCollapseApplication(EcorePackage.eINSTANCE.getEClass(), 2);
        checkCollapseApplication(EcorePackage.eINSTANCE.getEAttribute(), getFilter(diagram, COLLAPSE_CONTENTS), getFilter(diagram, COLLAPSE_EATTRIBUTES));
        checkCollapseApplication(EcorePackage.eINSTANCE.getEReference(), getFilter(diagram, COLLAPSE_CONTENTS));
        checkCollapseApplication(EcorePackage.eINSTANCE.getEOperation(), getFilter(diagram, COLLAPSE_CONTENTS));
        checkNoFilterApplicationForDiagramElements(true);

        deactivateFilter(diagram, COLLAPSE_CONTENTS);

        checkCollapseApplication(EcorePackage.eINSTANCE.getEClass(), 2);
        checkCollapseApplication(EcorePackage.eINSTANCE.getEAttribute(), getFilter(diagram, COLLAPSE_EATTRIBUTES));
        checkCollapseApplication(EcorePackage.eINSTANCE.getEReference());
        checkCollapseApplication(EcorePackage.eINSTANCE.getEOperation());
        checkNoFilterApplicationForDiagramElements(true);

        deactivateFilter(diagram, COLLAPSE_EATTRIBUTES);

        checkNoFilterApplicationForDiagramElements(true);
        checkNoCollapseApplicationForDiagramElements();
    }

    /**
     * Test.
     * 
     * @throws Exception
     */
    public void testHideCollapseActivation() throws Exception {
        checkNoFilterApplicationForDiagramElements(true);
        checkNoCollapseApplicationForDiagramElements();

        activateFilter(diagram, COLLAPSE_HIDE_CONTENTS);

        checkCollapseApplication(EcorePackage.eINSTANCE.getEClass(), 2);
        checkCollapseApplication(EcorePackage.eINSTANCE.getEAttribute(), getFilter(diagram, COLLAPSE_HIDE_CONTENTS));
        checkCollapseApplication(EcorePackage.eINSTANCE.getEReference());
        checkCollapseApplication(EcorePackage.eINSTANCE.getEOperation());

        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 0, 2, true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEAttribute(), true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEReference(), false, getFilter(diagram, COLLAPSE_HIDE_CONTENTS));
        checkFilterApplication(EcorePackage.eINSTANCE.getEOperation(), true);

        deactivateFilter(diagram, COLLAPSE_HIDE_CONTENTS);

        checkNoFilterApplicationForDiagramElements(true);
        checkNoCollapseApplicationForDiagramElements();
    }

    /**
     * Test.
     * 
     * @throws Exception
     */
    public void testHideCollapseWithConditionActivationWithAutoRefresh() throws Exception {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);

        checkNoFilterApplicationForDiagramElements(true);
        checkNoCollapseApplicationForDiagramElements();

        activateFilter(diagram, COLLAPSE_HIDE_CONDITION_CONTENTS);

        checkNoFilterApplicationForDiagramElements(true);
        checkNoCollapseApplicationForDiagramElements();

        deactivateFilter(diagram, COLLAPSE_HIDE_CONDITION_CONTENTS);

        checkNoFilterApplicationForDiagramElements(true);
        checkNoCollapseApplicationForDiagramElements();

        activateFilter(diagram, COLLAPSE_HIDE_CONDITION_CONTENTS);

        checkNoFilterApplicationForDiagramElements(true);
        checkNoCollapseApplicationForDiagramElements();

        // activate att
        setName(EcorePackage.eINSTANCE.getEAttribute(), HIDE_ATT_CONDITION_CONTENTS);

        checkCollapseApplication(EcorePackage.eINSTANCE.getEClass(), 2);
        checkCollapseApplication(EcorePackage.eINSTANCE.getEAttribute(), getFilter(diagram, COLLAPSE_HIDE_CONDITION_CONTENTS));
        checkCollapseApplication(EcorePackage.eINSTANCE.getEReference());
        checkCollapseApplication(EcorePackage.eINSTANCE.getEOperation());
        checkNoFilterApplicationForDiagramElements(true);

        // activate ref
        setName(EcorePackage.eINSTANCE.getEReference(), HIDE_REF_CONDITION_CONTENTS);

        checkCollapseApplication(EcorePackage.eINSTANCE.getEClass(), 2);
        checkCollapseApplication(EcorePackage.eINSTANCE.getEAttribute(), getFilter(diagram, COLLAPSE_HIDE_CONDITION_CONTENTS));
        checkCollapseApplication(EcorePackage.eINSTANCE.getEReference());
        checkCollapseApplication(EcorePackage.eINSTANCE.getEOperation());
        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 0, 2, true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEAttribute(), true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEReference(), false, getFilter(diagram, COLLAPSE_HIDE_CONDITION_CONTENTS));
        checkFilterApplication(EcorePackage.eINSTANCE.getEOperation(), true);

        deactivateFilter(diagram, COLLAPSE_HIDE_CONDITION_CONTENTS);

        checkNoFilterApplicationForDiagramElements(true);
        checkNoCollapseApplicationForDiagramElements();

        activateFilter(diagram, COLLAPSE_HIDE_CONDITION_CONTENTS);

        checkCollapseApplication(EcorePackage.eINSTANCE.getEClass(), 2);
        checkCollapseApplication(EcorePackage.eINSTANCE.getEAttribute(), getFilter(diagram, COLLAPSE_HIDE_CONDITION_CONTENTS));
        checkCollapseApplication(EcorePackage.eINSTANCE.getEReference());
        checkCollapseApplication(EcorePackage.eINSTANCE.getEOperation());
        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 0, 2, true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEAttribute(), true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEReference(), false, getFilter(diagram, COLLAPSE_HIDE_CONDITION_CONTENTS));
        checkFilterApplication(EcorePackage.eINSTANCE.getEOperation(), true);

        // deactivate att
        setName(EcorePackage.eINSTANCE.getEAttribute(), "att");

        checkNoCollapseApplicationForDiagramElements();
        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 0, 2, true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEAttribute(), true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEReference(), false, getFilter(diagram, COLLAPSE_HIDE_CONDITION_CONTENTS));
        checkFilterApplication(EcorePackage.eINSTANCE.getEOperation(), true);

        // deactivate ref
        setName(EcorePackage.eINSTANCE.getEReference(), "ref");

        checkNoCollapseApplicationForDiagramElements();
        checkNoFilterApplicationForDiagramElements(true);

        deactivateFilter(diagram, COLLAPSE_HIDE_CONDITION_CONTENTS);

        checkNoFilterApplicationForDiagramElements(true);
        checkNoCollapseApplicationForDiagramElements();
    }

    /**
     * Test.
     * 
     * @throws Exception
     */
    public void testHideCollapseWithConditionActivationWithoutAutoRefresh() throws Exception {
        checkNoFilterApplicationForDiagramElements(true);
        checkNoCollapseApplicationForDiagramElements();

        activateFilter(diagram, COLLAPSE_HIDE_CONDITION_CONTENTS);

        checkNoFilterApplicationForDiagramElements(true);
        checkNoCollapseApplicationForDiagramElements();

        deactivateFilter(diagram, COLLAPSE_HIDE_CONDITION_CONTENTS);

        checkNoFilterApplicationForDiagramElements(true);
        checkNoCollapseApplicationForDiagramElements();

        activateFilter(diagram, COLLAPSE_HIDE_CONDITION_CONTENTS);

        checkNoFilterApplicationForDiagramElements(true);
        checkNoCollapseApplicationForDiagramElements();

        // activate att
        setName(EcorePackage.eINSTANCE.getEAttribute(), HIDE_ATT_CONDITION_CONTENTS);

        checkNoFilterApplicationForDiagramElements(true);
        checkCollapseApplication(EcorePackage.eINSTANCE.getEClass(), 2);
        checkCollapseApplication(EcorePackage.eINSTANCE.getEAttribute(), getFilter(diagram, COLLAPSE_HIDE_CONDITION_CONTENTS));
        checkCollapseApplication(EcorePackage.eINSTANCE.getEReference());
        checkCollapseApplication(EcorePackage.eINSTANCE.getEOperation());

        // activate ref
        setName(EcorePackage.eINSTANCE.getEReference(), HIDE_REF_CONDITION_CONTENTS);

        checkCollapseApplication(EcorePackage.eINSTANCE.getEClass(), 2);
        checkCollapseApplication(EcorePackage.eINSTANCE.getEAttribute(), getFilter(diagram, COLLAPSE_HIDE_CONDITION_CONTENTS));
        checkCollapseApplication(EcorePackage.eINSTANCE.getEReference());
        checkCollapseApplication(EcorePackage.eINSTANCE.getEOperation());
        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 0, 2, true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEAttribute(), true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEReference(), true, getFilter(diagram, COLLAPSE_HIDE_CONDITION_CONTENTS));
        checkFilterApplication(EcorePackage.eINSTANCE.getEOperation(), true);

        refresh(diagram);

        checkCollapseApplication(EcorePackage.eINSTANCE.getEClass(), 2);
        checkCollapseApplication(EcorePackage.eINSTANCE.getEAttribute(), getFilter(diagram, COLLAPSE_HIDE_CONDITION_CONTENTS));
        checkCollapseApplication(EcorePackage.eINSTANCE.getEReference());
        checkCollapseApplication(EcorePackage.eINSTANCE.getEOperation());
        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 0, 2, true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEAttribute(), true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEReference(), false, getFilter(diagram, COLLAPSE_HIDE_CONDITION_CONTENTS));
        checkFilterApplication(EcorePackage.eINSTANCE.getEOperation(), true);

        deactivateFilter(diagram, COLLAPSE_HIDE_CONDITION_CONTENTS);

        checkNoFilterApplicationForDiagramElements(true);
        checkNoCollapseApplicationForDiagramElements();

        activateFilter(diagram, COLLAPSE_HIDE_CONDITION_CONTENTS);

        checkCollapseApplication(EcorePackage.eINSTANCE.getEClass(), 2);
        checkCollapseApplication(EcorePackage.eINSTANCE.getEAttribute(), getFilter(diagram, COLLAPSE_HIDE_CONDITION_CONTENTS));
        checkCollapseApplication(EcorePackage.eINSTANCE.getEReference());
        checkCollapseApplication(EcorePackage.eINSTANCE.getEOperation());
        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 0, 2, true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEAttribute(), true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEReference(), false, getFilter(diagram, COLLAPSE_HIDE_CONDITION_CONTENTS));
        checkFilterApplication(EcorePackage.eINSTANCE.getEOperation(), true);

        // deactivate att
        setName(EcorePackage.eINSTANCE.getEAttribute(), "att");

        checkNoCollapseApplicationForDiagramElements();
        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 0, 2, true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEAttribute(), true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEReference(), false, getFilter(diagram, COLLAPSE_HIDE_CONDITION_CONTENTS));
        checkFilterApplication(EcorePackage.eINSTANCE.getEOperation(), true);

        // deactivate ref
        setName(EcorePackage.eINSTANCE.getEReference(), "ref");

        checkNoCollapseApplicationForDiagramElements();
        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 0, 2, true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEAttribute(), true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEReference(), false);
        checkFilterApplication(EcorePackage.eINSTANCE.getEOperation(), true);

        deactivateFilter(diagram, COLLAPSE_HIDE_CONDITION_CONTENTS);

        checkNoFilterApplicationForDiagramElements(true);
        checkNoCollapseApplicationForDiagramElements();
    }

    /**
     * Tests.
     * 
     * @throws Exception
     */
    public void testCollapseActivationOnParent() throws Exception {
        checkNoFilterApplicationForDiagramElements(true);
        checkNoCollapseApplicationForDiagramElements();

        activateFilter(diagram, COLLAPSE_ECLASSES);

        checkCollapseApplication(EcorePackage.eINSTANCE.getEClass(), 2, getFilter(diagram, COLLAPSE_ECLASSES));
        checkCollapseApplication(EcorePackage.eINSTANCE.getEAttribute());
        checkCollapseApplication(EcorePackage.eINSTANCE.getEReference());
        checkCollapseApplication(EcorePackage.eINSTANCE.getEOperation());
        checkPureIndirectCollapseApplication(EcorePackage.eINSTANCE.getEAttribute());
        checkPureIndirectCollapseApplication(EcorePackage.eINSTANCE.getEReference());
        checkPureIndirectCollapseApplication(EcorePackage.eINSTANCE.getEOperation());
        checkNoFilterApplicationForDiagramElements(true);

        activateFilter(diagram, COLLAPSE_EATTRIBUTES);

        checkCollapseApplication(EcorePackage.eINSTANCE.getEClass(), 2, getFilter(diagram, COLLAPSE_ECLASSES));
        checkCollapseApplication(EcorePackage.eINSTANCE.getEAttribute(), getFilter(diagram, COLLAPSE_EATTRIBUTES));
        checkCollapseApplication(EcorePackage.eINSTANCE.getEReference());
        checkCollapseApplication(EcorePackage.eINSTANCE.getEOperation());
        checkPureIndirectCollapseApplication(EcorePackage.eINSTANCE.getEReference());
        checkPureIndirectCollapseApplication(EcorePackage.eINSTANCE.getEOperation());
        checkNoFilterApplicationForDiagramElements(true);

        activateFilter(diagram, COLLAPSE_CONTENTS);

        checkCollapseApplication(EcorePackage.eINSTANCE.getEClass(), 2, getFilter(diagram, COLLAPSE_ECLASSES));
        checkCollapseApplication(EcorePackage.eINSTANCE.getEAttribute(), getFilter(diagram, COLLAPSE_CONTENTS), getFilter(diagram, COLLAPSE_EATTRIBUTES));
        checkCollapseApplication(EcorePackage.eINSTANCE.getEReference(), getFilter(diagram, COLLAPSE_CONTENTS));
        checkCollapseApplication(EcorePackage.eINSTANCE.getEOperation(), getFilter(diagram, COLLAPSE_CONTENTS));
        checkNoFilterApplicationForDiagramElements(true);

        deactivateFilter(diagram, COLLAPSE_ECLASSES);

        checkCollapseApplication(EcorePackage.eINSTANCE.getEClass(), 2);
        checkCollapseApplication(EcorePackage.eINSTANCE.getEAttribute(), getFilter(diagram, COLLAPSE_CONTENTS), getFilter(diagram, COLLAPSE_EATTRIBUTES));
        checkCollapseApplication(EcorePackage.eINSTANCE.getEReference(), getFilter(diagram, COLLAPSE_CONTENTS));
        checkCollapseApplication(EcorePackage.eINSTANCE.getEOperation(), getFilter(diagram, COLLAPSE_CONTENTS));
        checkNoPureIndirectCollapseApplication(EcorePackage.eINSTANCE.getEAttribute());
        checkNoPureIndirectCollapseApplication(EcorePackage.eINSTANCE.getEReference());
        checkNoPureIndirectCollapseApplication(EcorePackage.eINSTANCE.getEOperation());
        checkNoFilterApplicationForDiagramElements(true);

        deactivateFilter(diagram, COLLAPSE_CONTENTS);

        checkCollapseApplication(EcorePackage.eINSTANCE.getEClass(), 2);
        checkCollapseApplication(EcorePackage.eINSTANCE.getEAttribute(), getFilter(diagram, COLLAPSE_EATTRIBUTES));
        checkCollapseApplication(EcorePackage.eINSTANCE.getEReference());
        checkCollapseApplication(EcorePackage.eINSTANCE.getEOperation());
        checkNoPureIndirectCollapseApplication(EcorePackage.eINSTANCE.getEAttribute());
        checkNoPureIndirectCollapseApplication(EcorePackage.eINSTANCE.getEReference());
        checkNoPureIndirectCollapseApplication(EcorePackage.eINSTANCE.getEOperation());
        checkNoFilterApplicationForDiagramElements(true);

        deactivateFilter(diagram, COLLAPSE_EATTRIBUTES);

        checkNoFilterApplicationForDiagramElements(true);
        checkNoCollapseApplicationForDiagramElements();
    }

    /**
     * Tests.
     * 
     * @throws Exception
     */
    public void testFilteredElementCreationWithAutoRefresh() throws Exception {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);

        checkNoFilterApplicationForDiagramElements(true);
        checkNoCollapseApplicationForDiagramElements();

        activateFilter(diagram, HIDE_ECLASSES);

        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 0, 2, false, getFilter(diagram, HIDE_ECLASSES));
        checkFilterApplication(EcorePackage.eINSTANCE.getEAttribute(), false);
        checkFilterApplication(EcorePackage.eINSTANCE.getEReference(), false);
        checkFilterApplication(EcorePackage.eINSTANCE.getEOperation(), false);

        applyNodeCreationTool(CREATE_CLASS_TOOL, diagram, diagram);

        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 0, 3, false, getFilter(diagram, HIDE_ECLASSES));
        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 1, 3, false, getFilter(diagram, HIDE_ECLASSES));
        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 2, 3, false, getFilter(diagram, HIDE_ECLASSES));
        checkFilterApplication(EcorePackage.eINSTANCE.getEAttribute(), false);
        checkFilterApplication(EcorePackage.eINSTANCE.getEReference(), false);
        checkFilterApplication(EcorePackage.eINSTANCE.getEOperation(), false);

        applyNodeCreationTool(CREATE_CLASS_TOOL_FORCE_REFRESH, diagram, diagram);

        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 0, 4, false, getFilter(diagram, HIDE_ECLASSES));
        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 2, 4, false, getFilter(diagram, HIDE_ECLASSES));
        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 2, 4, false, getFilter(diagram, HIDE_ECLASSES));
        checkFilterApplication(EcorePackage.eINSTANCE.getEAttribute(), false);
        checkFilterApplication(EcorePackage.eINSTANCE.getEReference(), false);
        checkFilterApplication(EcorePackage.eINSTANCE.getEOperation(), false);

        deactivateFilter(diagram, HIDE_ECLASSES);

        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 0, 4, true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 2, 4, true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 2, 4, true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEAttribute(), true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEReference(), true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEOperation(), true);

        activateFilter(diagram, HIDE_CONTENTS);

        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 0, 4, true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 2, 4, true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 2, 4, true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEAttribute(), false, getFilter(diagram, HIDE_CONTENTS));
        checkFilterApplication(EcorePackage.eINSTANCE.getEReference(), false, getFilter(diagram, HIDE_CONTENTS));
        checkFilterApplication(EcorePackage.eINSTANCE.getEOperation(), false, getFilter(diagram, HIDE_CONTENTS));

        applyNodeCreationTool(CREATE_OPERATION_TOOL, diagram, getDiagramElements(EcorePackage.eINSTANCE.getEClass()).get(1));

        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 0, 4, true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 2, 4, true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 2, 4, true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEAttribute(), false, getFilter(diagram, HIDE_CONTENTS));
        checkFilterApplication(EcorePackage.eINSTANCE.getEReference(), false, getFilter(diagram, HIDE_CONTENTS));
        checkFilterApplication(EcorePackage.eINSTANCE.getEOperation(), 0, 2, false, getFilter(diagram, HIDE_CONTENTS));
        checkFilterApplication(EcorePackage.eINSTANCE.getEOperation(), 1, 2, false, getFilter(diagram, HIDE_CONTENTS));

        deactivateFilter(diagram, HIDE_CONTENTS);

        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 0, 4, true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 2, 4, true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 2, 4, true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEAttribute(), true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEReference(), true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEOperation(), 0, 2, true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEOperation(), 1, 2, true);

    }

    /**
     * Tests.
     * 
     * @throws Exception
     */
    public void testFilteredContainedElementCreationWithAutoRefresh() throws Exception {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);

        checkNoFilterApplicationForDiagramElements(true);
        checkNoCollapseApplicationForDiagramElements();

        activateFilter(diagram, HIDE_CONTENTS);

        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 0, 2, true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEAttribute(), false, getFilter(diagram, HIDE_CONTENTS));
        checkFilterApplication(EcorePackage.eINSTANCE.getEReference(), false, getFilter(diagram, HIDE_CONTENTS));
        checkFilterApplication(EcorePackage.eINSTANCE.getEOperation(), false, getFilter(diagram, HIDE_CONTENTS));

        applyNodeCreationTool(CREATE_OPERATION_TOOL, diagram, getDiagramElements(EcorePackage.eINSTANCE.getEClass()).get(0));

        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 0, 2, true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEAttribute(), false, getFilter(diagram, HIDE_CONTENTS));
        checkFilterApplication(EcorePackage.eINSTANCE.getEReference(), false, getFilter(diagram, HIDE_CONTENTS));
        checkFilterApplication(EcorePackage.eINSTANCE.getEOperation(), 0, 2, false, getFilter(diagram, HIDE_CONTENTS));
        checkFilterApplication(EcorePackage.eINSTANCE.getEOperation(), 1, 2, false, getFilter(diagram, HIDE_CONTENTS));

        deactivateFilter(diagram, HIDE_CONTENTS);

        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 0, 2, true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEAttribute(), true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEReference(), true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEOperation(), 0, 2, true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEOperation(), 1, 2, true);
    }

    /**
     * Tests.
     * 
     * @throws Exception
     */
    public void testFilteredElementCreationWithoutAutoRefresh() throws Exception {
        checkNoFilterApplicationForDiagramElements(true);
        checkNoCollapseApplicationForDiagramElements();

        activateFilter(diagram, HIDE_ECLASSES);

        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 0, 2, false, getFilter(diagram, HIDE_ECLASSES));
        checkFilterApplication(EcorePackage.eINSTANCE.getEAttribute(), false);
        checkFilterApplication(EcorePackage.eINSTANCE.getEReference(), false);
        checkFilterApplication(EcorePackage.eINSTANCE.getEOperation(), false);

        applyNodeCreationTool(CREATE_CLASS_TOOL, diagram, diagram);

        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 0, 3, false, getFilter(diagram, HIDE_ECLASSES));
        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 2, 3, true, getFilter(diagram, HIDE_ECLASSES));
        checkFilterApplication(EcorePackage.eINSTANCE.getEAttribute(), false);
        checkFilterApplication(EcorePackage.eINSTANCE.getEReference(), false);
        checkFilterApplication(EcorePackage.eINSTANCE.getEOperation(), false);

        refresh(diagram);

        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 0, 3, false, getFilter(diagram, HIDE_ECLASSES));
        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 2, 3, false, getFilter(diagram, HIDE_ECLASSES));
        checkFilterApplication(EcorePackage.eINSTANCE.getEAttribute(), false);
        checkFilterApplication(EcorePackage.eINSTANCE.getEReference(), false);
        checkFilterApplication(EcorePackage.eINSTANCE.getEOperation(), false);

        applyNodeCreationTool(CREATE_CLASS_TOOL_FORCE_REFRESH, diagram, diagram);

        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 0, 4, false, getFilter(diagram, HIDE_ECLASSES));
        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 2, 4, false, getFilter(diagram, HIDE_ECLASSES));
        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 3, 4, false, getFilter(diagram, HIDE_ECLASSES));
        checkFilterApplication(EcorePackage.eINSTANCE.getEAttribute(), false);
        checkFilterApplication(EcorePackage.eINSTANCE.getEReference(), false);
        checkFilterApplication(EcorePackage.eINSTANCE.getEOperation(), false);

        deactivateFilter(diagram, HIDE_ECLASSES);

        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 0, 4, true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 2, 4, true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 3, 4, true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEAttribute(), true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEReference(), true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEOperation(), true);
    }

    /**
     * Tests.
     * 
     * @throws Exception
     */
    public void testDiagramOpenningWithoutAutoRefresh() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();

        activateFilter(diagram, COLLAPSE_HIDE_CONTENTS);

        TestsUtil.synchronizationWithUIThread();

        checkNoFilterApplicationForDiagramElements(true);
        checkNoCollapseApplicationForDiagramElements();

        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertNotNull(editor);

        checkCollapseApplication(EcorePackage.eINSTANCE.getEClass(), 2);
        checkCollapseApplication(EcorePackage.eINSTANCE.getEAttribute(), getFilter(diagram, COLLAPSE_HIDE_CONTENTS));
        checkCollapseApplication(EcorePackage.eINSTANCE.getEReference());
        checkCollapseApplication(EcorePackage.eINSTANCE.getEOperation());

        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 0, 2, true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEAttribute(), true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEReference(), false, getFilter(diagram, COLLAPSE_HIDE_CONTENTS));
        checkFilterApplication(EcorePackage.eINSTANCE.getEOperation(), true);

    }

    /**
     * Tests.
     * 
     * @throws Exception
     */
    public void testDiagramOpenningWithAutoRefresh() throws Exception {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);

        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();

        activateFilter(diagram, COLLAPSE_HIDE_CONTENTS);

        TestsUtil.synchronizationWithUIThread();

        checkNoFilterApplicationForDiagramElements(true);
        checkNoCollapseApplicationForDiagramElements();

        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertNotNull(editor);

        checkCollapseApplication(EcorePackage.eINSTANCE.getEClass(), 2);
        checkCollapseApplication(EcorePackage.eINSTANCE.getEAttribute(), getFilter(diagram, COLLAPSE_HIDE_CONTENTS));
        checkCollapseApplication(EcorePackage.eINSTANCE.getEReference());
        checkCollapseApplication(EcorePackage.eINSTANCE.getEOperation());

        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 0, 2, true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEAttribute(), true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEReference(), false, getFilter(diagram, COLLAPSE_HIDE_CONTENTS));
        checkFilterApplication(EcorePackage.eINSTANCE.getEOperation(), true);
    }

    /**
     * Tests.
     * 
     * @throws Exception
     */
    public void testDiagramOpenningWithoutAutoRefreshWithoutRefreshOnOpening() throws Exception {
        changeSiriusPreference(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), false);

        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();

        activateFilter(diagram, COLLAPSE_HIDE_CONTENTS);

        TestsUtil.synchronizationWithUIThread();

        checkNoFilterApplicationForDiagramElements(true);
        checkNoCollapseApplicationForDiagramElements();

        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertNotNull(editor);

        checkCollapseApplication(EcorePackage.eINSTANCE.getEClass(), 2);
        checkCollapseApplication(EcorePackage.eINSTANCE.getEAttribute(), getFilter(diagram, COLLAPSE_HIDE_CONTENTS));
        checkCollapseApplication(EcorePackage.eINSTANCE.getEReference());
        checkCollapseApplication(EcorePackage.eINSTANCE.getEOperation());

        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 0, 2, true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEAttribute(), true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEReference(), false, getFilter(diagram, COLLAPSE_HIDE_CONTENTS));
        checkFilterApplication(EcorePackage.eINSTANCE.getEOperation(), true);
    }

    /**
     * Tests.
     * 
     * @throws Exception
     */
    public void testDiagramOpenningWithAutoRefreshWithoutRefreshOnOpening() throws Exception {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        changeSiriusPreference(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), false);

        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();

        activateFilter(diagram, COLLAPSE_HIDE_CONTENTS);

        TestsUtil.synchronizationWithUIThread();

        checkNoFilterApplicationForDiagramElements(true);
        checkNoCollapseApplicationForDiagramElements();

        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertNotNull(editor);

        checkCollapseApplication(EcorePackage.eINSTANCE.getEClass(), 2);
        checkCollapseApplication(EcorePackage.eINSTANCE.getEAttribute(), getFilter(diagram, COLLAPSE_HIDE_CONTENTS));
        checkCollapseApplication(EcorePackage.eINSTANCE.getEReference());
        checkCollapseApplication(EcorePackage.eINSTANCE.getEOperation());

        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 0, 2, true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEAttribute(), true);
        checkFilterApplication(EcorePackage.eINSTANCE.getEReference(), false, getFilter(diagram, COLLAPSE_HIDE_CONTENTS));
        checkFilterApplication(EcorePackage.eINSTANCE.getEOperation(), true);
    }

    public void testEdgeEditPartCreationAfterFilterDeactivation() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        changeSiriusPreference(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), true);

        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
        diagram = (DDiagram) getRepresentations(EDGE_PB_REPRESENTATION_DESC_NAME).toArray()[0];
        assertNotNull(diagram);
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertNotNull(editor);

        // Create a new reference
        List<DNode> nodeClasses = Lists.newArrayList(Iterables.filter(diagram.getDiagramElements(), DNode.class));
        assertEquals("Bad input data : wrong class number", 2, nodeClasses.size());

        applyEdgeCreationTool(REFERENCE_CREATE_TOOL_NAME, diagram, nodeClasses.get(0), nodeClasses.get(1));
        // refresh(diagram);
        // Check that an edge has been created in the DDiagram and in the GMF
        // diagram
        assertEquals("Bad refresh : wrong DEdge number", 1, diagram.getEdges().size());
        Edge edge = getGmfEdge(diagram.getEdges().get(0));
        assertTrue("Bad refresh : A GMF edge must be create in this case.", edge != null);
        // Check that there is no edge edit part created for the new reference
        // (hide by
        // filter that is currently enabled in the diagram))
        IGraphicalEditPart edgeEditPart = getEditPart(diagram.getEdges().get(0));
        assertTrue("No edit part must be create at this time (the edge is not visible because it is hidden by a filter).", edgeEditPart == null);
        // Deactivate the filter
        deactivateFilter(diagram, HIDE_REFERENCE_FILTER_NAME);
        // Check that the edge is now visible (GMF view AND editPart)
        edgeEditPart = getEditPart(diagram.getEdges().get(0));
        assertTrue("An edit part must be create at this time (the edge is now visible because it is not hidden by the filter).", edgeEditPart != null);
        if (edgeEditPart instanceof AbstractDiagramEdgeEditPart) {
            assertTrue("The sourceEditPart of the edgeEditpart must be initialized.", ((AbstractDiagramEdgeEditPart) edgeEditPart).getSource() != null);
            assertTrue("The targetEditPart of the edgeEditpart must be initialized.", ((AbstractDiagramEdgeEditPart) edgeEditPart).getTarget() != null);
        }
    }

    private void checkFilterApplication(final EClass type, boolean visible, FilterDescription... filters) {
        checkFilterApplication(type, 0, 1, visible, filters);
    }

    private void checkFilterApplication(EClass type, int index, int typedElementNumber, boolean visible, FilterDescription... filters) {
        List<DDiagramElement> elementsFromType = getDiagramElements(type);

        assertEquals(typedElementNumber, elementsFromType.size());
        assertTrue(typedElementNumber != 0);
        assertTrue(index < typedElementNumber);

        DDiagramElement element = elementsFromType.get(index);
        // One AppliedCompositeFilter per DDiagramElement
        assertEquals(filters.length == 0 ? 0 : 1, Iterables.size(Iterables.filter(element.getGraphicalFilters(), AppliedCompositeFilters.class)));

        DDiagramElementQuery elementQuery = new DDiagramElementQuery(element);
        Option<AppliedCompositeFilters> filterApplication = elementQuery.getAppliedCompositeFilters();

        assertEquals(filters.length != 0, filterApplication.some());

        if (filters.length == 0)
            return;

        assertEquals(filters.length, filterApplication.get().getCompositeFilterDescriptions().size());
        assertEquals(visible, element.isVisible());

        for (FilterDescription filter : filters) {
            assertTrue(diagram.getActivatedFilters().contains(filter));
            assertTrue(filterApplication.get().getCompositeFilterDescriptions().contains(filter));
        }

        // GMF
        Node gmfNode = getGmfNode(element);
        assertNotNull(gmfNode);
        assertEquals(visible, gmfNode.isVisible());

    }

    private void checkNoFilterApplicationForDiagramElements(boolean visible) {
        checkFilterApplication(EcorePackage.eINSTANCE.getEClass(), 0, 2, visible);
        checkFilterApplication(EcorePackage.eINSTANCE.getEAttribute(), visible);
        checkFilterApplication(EcorePackage.eINSTANCE.getEReference(), visible);
        checkFilterApplication(EcorePackage.eINSTANCE.getEOperation(), visible);
    }

    private void checkNoCollapseApplicationForDiagramElements() {
        checkCollapseApplication(EcorePackage.eINSTANCE.getEClass(), 2);
        checkCollapseApplication(EcorePackage.eINSTANCE.getEAttribute());
        checkCollapseApplication(EcorePackage.eINSTANCE.getEReference());
        checkCollapseApplication(EcorePackage.eINSTANCE.getEOperation());
    }

    private void checkCollapseApplication(EClass type, FilterDescription... filters) {
        checkCollapseApplication(type, 1, filters);

    }

    private void checkCollapseApplication(EClass type, int expectedNumberOfElements, FilterDescription... filters) {
        List<DDiagramElement> elementsFromType = getDiagramElements(type);

        assertEquals(expectedNumberOfElements, elementsFromType.size());

        DDiagramElement element = elementsFromType.get(0);
        // One CollapseApplication per DDiagramElement
        assertEquals(
                filters.length == 0 ? 0 : 1,
                Iterables.size(Iterables.filter(element.getGraphicalFilters(),
                        Predicates.and(Predicates.instanceOf(CollapseFilter.class), Predicates.not(Predicates.instanceOf(IndirectlyCollapseFilter.class))))));

        DDiagramElementQuery elementQuery = new DDiagramElementQuery(element);

        assertEquals(filters.length != 0, elementQuery.isCollapsed());

        if (filters.length == 0)
            return;

        for (FilterDescription filter : filters) {
            assertTrue(diagram.getActivatedFilters().contains(filter));
        }
    }

    private void checkPureIndirectCollapseApplication(EClass type) {
        List<DDiagramElement> elementsFromType = getDiagramElements(type);

        assertEquals(1, elementsFromType.size());

        DDiagramElement element = elementsFromType.get(0);
        // One CollapseApplication per DDiagramElement

        DDiagramElementQuery elementQuery = new DDiagramElementQuery(element);

        assertTrue(elementQuery.isIndirectlyCollapsed());
        assertFalse(elementQuery.isCollapsed());
    }

    private void checkNoPureIndirectCollapseApplication(EClass type) {
        List<DDiagramElement> elementsFromType = getDiagramElements(type);

        assertEquals(1, elementsFromType.size());

        DDiagramElement element = elementsFromType.get(0);
        // One CollapseApplication per DDiagramElement

        DDiagramElementQuery elementQuery = new DDiagramElementQuery(element);

        assertFalse(elementQuery.isIndirectlyCollapsed() && !elementQuery.isCollapsed());
    }

    private void setName(EClass type, final String newName) {
        List<DDiagramElement> elementsFromType = getDiagramElements(type);

        assertEquals(1, elementsFromType.size());

        DDiagramElement element = elementsFromType.get(0);

        final EObject target = element.getTarget();
        if (target instanceof ENamedElement) {
            session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {

                @Override
                protected void doExecute() {
                    ((ENamedElement) target).setName(newName);
                }
            });

        }
    }

    private List<DDiagramElement> getDiagramElements(final EClass type) {
        Predicate<DDiagramElement> expectedType = new Predicate<DDiagramElement>() {
            public boolean apply(DDiagramElement input) {
                return type.isInstance(input.getTarget());
            }
        };
        return Lists.newArrayList(Iterables.filter(diagram.getDiagramElements(), expectedType));
    }

    @Override
    protected void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
        super.tearDown();
    }

}
