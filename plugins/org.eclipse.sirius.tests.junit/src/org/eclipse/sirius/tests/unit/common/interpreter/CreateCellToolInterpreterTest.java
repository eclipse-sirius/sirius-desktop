/*******************************************************************************
 * Copyright (c) 2014, 2021 Obeo.
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
package org.eclipse.sirius.tests.unit.common.interpreter;

import java.util.List;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.common.tools.api.contentassist.ContentContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentProposal;
import org.eclipse.sirius.common.tools.api.interpreter.CompoundInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext;
import org.eclipse.sirius.table.business.internal.dialect.description.TableToolVariables;
import org.eclipse.sirius.table.metamodel.table.description.CreateCellTool;
import org.eclipse.sirius.table.metamodel.table.description.CrossTableDescription;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionFactory;
import org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping;
import org.eclipse.sirius.tests.unit.table.unit.common.TableTestCase;
import org.eclipse.sirius.tools.internal.interpreter.SiriusInterpreterContextFactory;
import org.eclipse.sirius.viewpoint.description.tool.ChangeContext;
import org.eclipse.sirius.viewpoint.description.tool.EditMaskVariables;
import org.eclipse.sirius.viewpoint.description.tool.ToolFactory;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * Interpreter tests for {@link CreateCellTool}.
 * 
 * @author <a href="mailto:mickael.lanoe@obeo.fr">Mickael LANOE</a>
 * 
 */
public class CreateCellToolInterpreterTest extends TableTestCase {
    /**
     * Ensures that the "Arg0" variable for the edit mask is available in auto-completion for a Create Cell Tool. See
     * https://bugs.eclipse.org/bugs/show_bug.cgi?id=428759
     */
    public void testEditMaskVariable() {
        final TransactionalEditingDomain domain = session.getTransactionalEditingDomain();

        // Get the cross table description
        final CrossTableDescription crossTableDescription = this.findCrossTable("Cross Table Colors");

        // Create the ChangeContext operation in order to test the
        // auto-completion on it.
        final ChangeContext changeContext = ToolFactory.eINSTANCE.createChangeContext();
        domain.getCommandStack().execute(new RecordingCommand(domain) {
            @Override
            protected void doExecute() {
                // Get the first intersection mapping
                assertTrue(TableTestCase.THE_UNIT_TEST_DATA_SEEMS_INCORRECT, crossTableDescription.getIntersection().size() > 0);
                final IntersectionMapping intersectionMapping = crossTableDescription.getIntersection().get(0);

                // Create a CreateCellTool
                CreateCellTool createCellTool = DescriptionFactory.eINSTANCE.createCreateCellTool();
                new TableToolVariables().doSwitch(createCellTool);
                intersectionMapping.setCreate(createCellTool);

                // Add an EditMaskVariables
                EditMaskVariables mask = ToolFactory.eINSTANCE.createEditMaskVariables();
                mask.setMask("{0}");
                createCellTool.setMask(mask);

                // Add the ChangeContext operation
                changeContext.setBrowseExpression("var:arg0");
                createCellTool.setFirstModelOperation(changeContext);
            }

        });

        // Test the auto-completion
        IInterpreterContext interpreterContext = SiriusInterpreterContextFactory.createInterpreterContext(changeContext, ToolPackage.Literals.CHANGE_CONTEXT__BROWSE_EXPRESSION);
        ContentContext context = new ContentContext("var:", 4, interpreterContext);
        List<ContentProposal> proposals = CompoundInterpreter.INSTANCE.getProposals(CompoundInterpreter.INSTANCE, context);

        // Ensure that arg0 exists
        assertTrue(Iterables.any(proposals, new Predicate<ContentProposal>() {
            @Override
            public boolean apply(ContentProposal proposal) {
                return proposal.getProposal().equals("arg0");
            }
        }));

        // Check validation
        Diagnostic diagnostic = Diagnostician.INSTANCE.validate(changeContext);
        assertEquals(diagnostic.getSeverity(), Diagnostic.OK);
        assertTrue(diagnostic.getChildren().isEmpty());
    }
}
