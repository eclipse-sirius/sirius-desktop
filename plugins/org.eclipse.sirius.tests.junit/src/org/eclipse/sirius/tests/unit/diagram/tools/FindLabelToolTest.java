/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.tools;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.common.tools.api.find.AbstractFindLabelEngine;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.ui.tools.api.action.RevealFindLabelDialog;
import org.eclipse.sirius.diagram.ui.tools.internal.find.BasicFindLabelEngine;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

/**
 * This class tests the tool allowing to find and select a diagram element in a
 * diagram.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class FindLabelToolTest extends SiriusDiagramTestCase {
	DDiagram diagram;

	DiagramEditor editor;

	private static final String PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/tool/note/tc_VP-2121_many/";

	private static final String SEMANTIC_MODEL_PATH = PATH + "tc_VP-2121.ecore";

	private static final String REPRESENTATION_MODEL_PATH = PATH + "tc_VP-2121.aird";

	private static final String REPRESENTATION_DESC_NAME = "Entities";

	private static final String CONTAINER_TO_SEARCH = "P2";

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		TestsUtil.emptyEventsFromUIThread();

		genericSetUp(SEMANTIC_MODEL_PATH, org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler.MODELER_PATH,
				REPRESENTATION_MODEL_PATH);

		changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
		changeSiriusPreference(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), true);

		DialectUIManager.INSTANCE.closeEditor(editor, false);
		TestsUtil.synchronizationWithUIThread();
		diagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME).toArray()[0];
		assertNotNull(diagram);
		editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
		TestsUtil.synchronizationWithUIThread();
		assertNotNull(editor);

	}

	/**
	 * Stub class to access the protected method selectElement().
	 * 
	 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
	 *
	 */
	private class RevealFindLabelDialogForTest extends RevealFindLabelDialog {

		public RevealFindLabelDialogForTest(Shell parentShell, AbstractFindLabelEngine engine,
				DiagramEditor currentEditor) {
			super(parentShell, engine, currentEditor);
		}

		/**
		 * Select the element in the engine with the given string.
		 * 
		 * @param str
		 *            the string to find in diagram editor
		 * @param engine
		 *            the search engine to use
		 */
		public void search(String str, AbstractFindLabelEngine engine) {
			engine.initFind(str);
			selectElement(engine.getNext());
		}
	}

	@Override
	protected void tearDown() throws Exception {
		DialectUIManager.INSTANCE.closeEditor(editor, false);
		TestsUtil.synchronizationWithUIThread();
		diagram = null;
		editor = null;
		super.tearDown();
	}

	/**
	 * Tests that the label search hitting a container do work without causing any
	 * exception.
	 */
	public void testContainerLabelSearch() {
		Shell shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
		assertNotNull(shell);
		BasicFindLabelEngine engine = new BasicFindLabelEngine(editor);

		RevealFindLabelDialogForTest dialog = new RevealFindLabelDialogForTest(shell, engine, editor);
		assertNotNull(dialog);

		dialog.search(CONTAINER_TO_SEARCH, engine);
		List<DSemanticDecorator> selections = new ArrayList<DSemanticDecorator>(
				DialectUIManager.INSTANCE.getSelection((DialectEditor) editor));
		assertEquals(selections.size(), 1);
		EObject selection = selections.get(0);
		assertEquals(DiagramPackage.Literals.DNODE_CONTAINER, selection.eClass());
		if (selection instanceof DNodeContainer) {
			DNodeContainer dnc = (DNodeContainer) selection;
			assertEquals(dnc.getName(), CONTAINER_TO_SEARCH);
		}
	}

}
