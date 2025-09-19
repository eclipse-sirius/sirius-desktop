/*******************************************************************************
 * Copyright (c) 2014 Obeo.
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
package org.eclipse.sirius.tests.unit.table.unit.tools;

import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.common.acceleo.aql.business.internal.AQLSiriusInterpreter;
import org.eclipse.sirius.common.acceleo.aql.ide.proposal.AQLProposalProvider;
import org.eclipse.sirius.common.tools.api.contentassist.ContentContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentProposal;
import org.eclipse.sirius.common.tools.api.contentassist.IProposalProvider;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext;
import org.eclipse.sirius.table.metamodel.table.description.CreateCellTool;
import org.eclipse.sirius.table.metamodel.table.description.CreateColumnTool;
import org.eclipse.sirius.table.metamodel.table.description.CreateCrossColumnTool;
import org.eclipse.sirius.table.metamodel.table.description.CreateLineTool;
import org.eclipse.sirius.table.metamodel.table.description.CrossTableDescription;
import org.eclipse.sirius.table.metamodel.table.description.DeleteColumnTool;
import org.eclipse.sirius.table.metamodel.table.description.DeleteLineTool;
import org.eclipse.sirius.table.metamodel.table.description.EditionTableDescription;
import org.eclipse.sirius.table.metamodel.table.description.ElementColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping;
import org.eclipse.sirius.table.metamodel.table.description.LineMapping;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tools.internal.interpreter.SiriusInterpreterContextFactory;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * Tests to check the auto completion result of table tool's precondition.
 * 
 * See https://bugs.eclipse.org/bugs/show_bug.cgi?id=447675
 * 
 * @author <a href="mailto:mickael.lanoe@obeo.fr">Mickael LANOE</a>
 */
public class TableToolPreconditionCompletionTest extends SiriusDiagramTestCase {

    private static final String PATH = "/data/table/unit/tools/";

    private static final String SEMANTIC_MODEL_FILENAME = "tests.ecore";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/table/unit/tools/tests.odesign";

    private static final String VIEWPOINT_NAME = "TestTableTools";

    private static final String EDITION_TABLE_DESCRIPTION = "TestTableTools_Classes";

    private static final String CROSS_TABLE_DESCRIPTION = "TestTableTools_Columns";

    private EAttribute preconditionAttribute;

    private IProposalProvider acceleoPrososalProvider;

    private IInterpreter acceleoIntepreter;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        // Precondition attribute
        preconditionAttribute = ToolPackage.eINSTANCE.getAbstractToolDescription_Precondition();

        // Use the Acceleo interpreter
        acceleoPrososalProvider = new AQLProposalProvider();
        acceleoIntepreter = new AQLSiriusInterpreter();

        // Create the project
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SEMANTIC_MODEL_FILENAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME);
        genericSetUp(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME, MODELER_PATH);
        initViewpoint(VIEWPOINT_NAME);
    }

    /**
     * Ensure that the auto completion is valid for {@link CreateLineTool}
     */
    public void testCreateLineTool() {
        EditionTableDescription tableDescription = getTableDescription(EDITION_TABLE_DESCRIPTION, EditionTableDescription.class);

        // Check the first create line tool owned by the table
        CreateLineTool tableCreateLineTool = tableDescription.getOwnedCreateLine().get(0);
        checkThisEObjectInCompletion(tableCreateLineTool, tableDescription.getDomainClass());

        // Check the first create line tool owned by the first line mapping of
        // the table
        LineMapping lineMapping = tableDescription.getOwnedLineMappings().get(0);
        CreateLineTool lineCreateLineTool = lineMapping.getCreate().get(0);
        checkThisEObjectInCompletion(lineCreateLineTool, lineMapping.getDomainClass());
    }

    /**
     * Ensure that the auto completion is valid for {@link DeleteLineTool}
     */
    public void testDeleteLineTool() {
        EditionTableDescription tableDescription = getTableDescription(EDITION_TABLE_DESCRIPTION, EditionTableDescription.class);
        LineMapping lineMapping = tableDescription.getOwnedLineMappings().get(0);
        DeleteLineTool deleteLineTool = lineMapping.getDelete();
        checkThisEObjectInCompletion(deleteLineTool, lineMapping.getDomainClass());
    }

    /**
     * Ensure that the auto completion is valid for {@link CreateColumnTool} &
     * {@link CreateColumnTool}
     */
    public void testCreateColumnTool() {
        CrossTableDescription tableDescription = getTableDescription(CROSS_TABLE_DESCRIPTION, CrossTableDescription.class);

        // Check the first create column tool owned by the table
        CreateCrossColumnTool tableCreateLineTool = tableDescription.getCreateColumn().get(0);
        checkThisEObjectInCompletion(tableCreateLineTool, tableDescription.getDomainClass());

        // Check the first create column tool owned by the first element column
        // mapping of the table
        ElementColumnMapping elementColumn = tableDescription.getOwnedColumnMappings().get(0);
        CreateColumnTool columnCreateColumnTool = elementColumn.getCreate().get(0);
        checkThisEObjectInCompletion(columnCreateColumnTool, elementColumn.getDomainClass());
    }

    /**
     * Ensure that the auto completion is valid for {@link DeleteColumnTool}
     */
    public void testDeleteColumnTool() {
        CrossTableDescription tableDescription = getTableDescription(CROSS_TABLE_DESCRIPTION, CrossTableDescription.class);
        ElementColumnMapping elementColumn = tableDescription.getOwnedColumnMappings().get(0);
        DeleteColumnTool deleteColumnTool = elementColumn.getDelete();
        checkThisEObjectInCompletion(deleteColumnTool, elementColumn.getDomainClass());
    }

    /**
     * Ensure that the auto completion is valid for {@link CreateCellTool}
     */
    public void testCreateCellTool() {
        CrossTableDescription tableDescription = getTableDescription(CROSS_TABLE_DESCRIPTION, CrossTableDescription.class);
        IntersectionMapping intersection = tableDescription.getIntersection().get(0);
        CreateCellTool createCellTool = intersection.getCreate();
        checkThisEObjectInCompletion(createCellTool, intersection.getLineMapping().get(0).getDomainClass());
    }

    /**
     * Check the 'thisEObject' type
     * 
     * @param element
     *            element to get proposalscreateCellTool
     * @param expectedType
     *            expected 'thisEObject' type
     */
    private void checkThisEObjectInCompletion(EObject element, final String expectedType) {
        // Compute the content context
        IInterpreterContext interContext = SiriusInterpreterContextFactory.createInterpreterContext(element, preconditionAttribute);
        ContentContext context = new ContentContext("[/]", 1, interContext);

        List<ContentProposal> contentProposals = acceleoPrososalProvider.getProposals(acceleoIntepreter, context);
        ContentProposal contentProposal = contentProposals.stream().filter(input->input.getProposal().equals("thisEObject")).findFirst().orElse(null);
        
        assertNotNull("The 'thisEObject' is not found", contentProposal);
        assertEquals("thisEObject:" + expectedType, contentProposal.getDisplay());
    }

    /**
     * Get a table description from name
     * 
     * @return the table description
     */
    private <T> T getTableDescription(String name, Class<? extends T> klass) {
        Viewpoint viewpoint = getViewpointFromName(VIEWPOINT_NAME);
        assertNotNull("No viewpoint named '" + VIEWPOINT_NAME + "' found", viewpoint);

        for (RepresentationDescription representationDescription : viewpoint.getOwnedRepresentations()) {
            if (klass.isInstance(representationDescription) && representationDescription.getName().equals(name)) {
                return klass.cast(representationDescription);
            }
        }

        fail("No table description named '" + name + "' found");

        return null;
    }
}
