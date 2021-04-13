/*******************************************************************************
 * Copyright (c) 2021 Obeo.
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
package org.eclipse.sirius.tests.swtbot.suite;

import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.DeleteHookTests;
import org.eclipse.sirius.tests.swtbot.DiagramCreationDescriptionTest;
import org.eclipse.sirius.tests.swtbot.DndWorkspaceToAirdEditorTest;
import org.eclipse.sirius.tests.swtbot.DragAndDropWithSnapToGridTest;
import org.eclipse.sirius.tests.swtbot.DragNDropTest;
import org.eclipse.sirius.tests.swtbot.EdgeWithBorderNodeCreationPositionWithSnapToGridTest;
import org.eclipse.sirius.tests.swtbot.ExportDiagramAsImageWhenManyRepresentationsHaveSameNameTest;
import org.eclipse.sirius.tests.swtbot.ExportDiagramsAsImagesAndHtmlTest;
import org.eclipse.sirius.tests.swtbot.ExportDiagramsAsImagesTest;
import org.eclipse.sirius.tests.swtbot.GroupingContentProviderByContainingTest;
import org.eclipse.sirius.tests.swtbot.GroupingContentProviderTest;
import org.eclipse.sirius.tests.swtbot.HideRevealDiagramElementsLabelsTest;
import org.eclipse.sirius.tests.swtbot.InitializeSessionTest;
import org.eclipse.sirius.tests.swtbot.OpenMultipleRepresentationsTest;
import org.eclipse.sirius.tests.swtbot.RenameProjectWithSessionTest;
import org.eclipse.sirius.tests.swtbot.SetStyleToWorkspaceImageTests;
import org.eclipse.sirius.tests.swtbot.editor.vsm.CompletionProposalInVSMTest;
import org.eclipse.sirius.tests.swtbot.editor.vsm.CustomizationPropertySectionsTests;
import org.eclipse.sirius.tests.swtbot.editor.vsm.MigrationOnVsmEditorReloadTest;
import org.eclipse.sirius.tests.swtbot.editor.vsm.ServiceNavigationTest;
import org.eclipse.sirius.tests.swtbot.editor.vsm.VSMFieldTest;
import org.eclipse.sirius.tests.swtbot.editor.vsm.ViewpointSpecificationProjectCreationTest;
import org.eclipse.sirius.tests.swtbot.std.STD006;
import org.eclipse.sirius.tests.swtbot.std.STD007;
import org.eclipse.sirius.tests.swtbot.std.STD008;
import org.eclipse.sirius.tests.swtbot.std.STD010;
import org.eclipse.sirius.tests.swtbot.tabbar.TabBarTest;
import org.eclipse.sirius.tests.swtbot.table.HideRevealTableColumnsTest;
import org.eclipse.sirius.tests.swtbot.table.HideRevealTableLinesTest;
import org.eclipse.sirius.tests.swtbot.table.TableUIRefreshTests;
import org.eclipse.sirius.tests.unit.common.EnvironmentReportTest;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * All SWTBot unreliable tests.
 * 
 * @author lredor
 */
public class AllUnreliableTestSuite extends TestCase {

    /**
     * Launches the test with the given arguments.
     * 
     * @param args
     *            Arguments of the testCase.
     */
    public static void main(final String[] args) {
        TestRunner.run(suite());
    }

    @SuppressWarnings("javadoc")
    public static class SWTBotBundlesReport extends EnvironmentReportTest {
        public SWTBotBundlesReport() {
            super(Activator.getDefault().getBundle(), "SWTBot unreliable");
        }
    }

    /**
     * Creates the {@link junit.framework.TestSuite TestSuite} for all the test classes that have at least one test
     * using {@link TestsUtil#shouldSkipUnreliableTests()}.<BR>
     * This suite can also launch a specific test by setting the environment variable TEST_CLASS_NAME to the qualified
     * name of the expected class to launch.
     * 
     * @return The {@link junit.framework.TestSuite} containing all the tests
     */
    @SuppressWarnings("unchecked")
    public static Test suite() {
        TestSuite suite = new TestSuite("Sirius SwtBot unreliable tests");

        String testClassQualifiedName = System.getenv("TEST_CLASS_NAME"); //$NON-NLS-1$
        if (testClassQualifiedName != null && testClassQualifiedName.length() > 0) {
            try {
                Class<?> testToLaunch = Activator.getDefault().getBundle().loadClass(testClassQualifiedName);
                if (TestCase.class.isAssignableFrom(testToLaunch)) {
                    suite.addTestSuite((Class<? extends TestCase>) testToLaunch);
                }
            } catch (ClassNotFoundException e) {
                fail("The class " + testClassQualifiedName + ", to launch for test specific case, has not been found.");
            }
        } else {
            suite.addTest(new JUnit4TestAdapter(SWTBotBundlesReport.class));
            if (TestsUtil.shouldRunUnreliableTests()) {
                suite.addTestSuite(CompletionProposalInVSMTest.class);
                suite.addTestSuite(CustomizationPropertySectionsTests.class);
                suite.addTestSuite(DeleteHookTests.class);
                suite.addTestSuite(DiagramCreationDescriptionTest.class);
                suite.addTest(new JUnit4TestAdapter(DndWorkspaceToAirdEditorTest.class));
                suite.addTest(new JUnit4TestAdapter(DragAndDropWithSnapToGridTest.class));
                suite.addTest(new JUnit4TestAdapter(DragNDropTest.class));
                suite.addTestSuite(EdgeWithBorderNodeCreationPositionWithSnapToGridTest.class);
                suite.addTestSuite(ExportDiagramAsImageWhenManyRepresentationsHaveSameNameTest.class);
                suite.addTestSuite(ExportDiagramsAsImagesAndHtmlTest.class);
                suite.addTestSuite(ExportDiagramsAsImagesTest.class);
                suite.addTestSuite(GroupingContentProviderByContainingTest.class);
                suite.addTestSuite(GroupingContentProviderTest.class);
                suite.addTestSuite(HideRevealDiagramElementsLabelsTest.class);
                suite.addTestSuite(HideRevealTableColumnsTest.class);
                suite.addTestSuite(HideRevealTableLinesTest.class);
                suite.addTestSuite(InitializeSessionTest.class);
                suite.addTestSuite(MigrationOnVsmEditorReloadTest.class);
                suite.addTestSuite(OpenMultipleRepresentationsTest.class);
                suite.addTestSuite(RenameProjectWithSessionTest.class);
                suite.addTestSuite(ServiceNavigationTest.class);
                suite.addTestSuite(SetStyleToWorkspaceImageTests.class);
                suite.addTestSuite(STD006.class);
                if (!(System.getProperty("os.name").contains("Linux") && TestsUtil.is202003Platform())) {
                    suite.addTestSuite(STD007.class);
                    suite.addTestSuite(STD008.class);
                    suite.addTestSuite(STD010.class);
                }
                suite.addTestSuite(TabBarTest.class);
                suite.addTestSuite(TableUIRefreshTests.class);
                suite.addTestSuite(ViewpointSpecificationProjectCreationTest.class);
                suite.addTestSuite(VSMFieldTest.class);
            }
        }
        return suite;
    }
}
