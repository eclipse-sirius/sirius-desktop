/*******************************************************************************
 * Copyright (c) 2020 Obeo.
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
package org.eclipse.sirius.tests.unit.diagram.compartment;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramElementContainerEditPart;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.swt.graphics.FontData;

/**
 * Tests defined to ensure that labels in region container or regions are correctly wrapped in multilines and that
 * region container and regions are correctly layouted.
 * 
 * @author lfasani
 */
public class CompartmentsMultiLabelLayoutTest extends SiriusDiagramTestCase {

    private final String DATA_UNIT_DIR = "/data/unit/compartments/";

    private final String MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + DATA_UNIT_DIR + "Multiline.ecore";

    private final String SESSION_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + DATA_UNIT_DIR + "Multiline.aird";

    private final String VSM_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + DATA_UNIT_DIR + "MultilineLabels.odesign";

    private final String REPRESENTATION_NAME = "MultilineLabels";

    private final String LONG_LABEL = "LONG_LABEL_LONG_LABEL_LONG_LABEL_LONG_LABEL_LONG_LABEL";

    private final String SMALL_LABEL = "Small";

    private final String V_STACK_CONTAINER = "VStackContainer";

    private final String H_STACK_CONTAINER = "HStackContainer";

    private final String V_STACK_CONT_NO_REGION = "VStackContainerWithoutRegion";

    private final String FREE_FORM_CONTAINER = "StandardContainer";

    private final String LIST_CONTAINER = "ListCont";

    private final String H_REGION = "HRegion";

    private final String V_REGION = "VRegion";

    private final Dimension AUTO_SIZE_DIMENSION = new Dimension(-1, -1);

    private EPackage rootPackage = null;

    private EPackage region1Package = null;

    private EPackage region2Package = null;

    private DDiagram diagram;

    private DialectEditor editor;

    private String oldFont;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(MODEL_PATH, VSM_PATH, SESSION_PATH);
        TestsUtil.synchronizationWithUIThread();

        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        oldFont = changeDefaultFontName("Comic Sans MS");

        diagram = (DDiagram) getRepresentationsByName(REPRESENTATION_NAME).iterator().next();
        editor = (DialectEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        rootPackage = (EPackage) session.getSemanticResources().iterator().next().getContents().get(0);
        region1Package = rootPackage.getESubpackages().get(0);
        region2Package = rootPackage.getESubpackages().get(1);
    }

    @Override
    protected void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        if (oldFont != null) {
            changeDefaultFontName(oldFont);
        }
        super.tearDown();
    }

    /**
     * Use case : Horizontal stack and autosize</br>
     * Check behavior with compartment and regions label changes
     */
    public void testHorizontalStackWithAutoSize() {
        checkHStackContainerDimensions(AUTO_SIZE_DIMENSION, new Dimension(451, 111), new Dimension(462, 107));

        changeLabelSize(rootPackage, LONG_LABEL);
        checkHStackContainerDimensions(AUTO_SIZE_DIMENSION, new Dimension(451, 134), new Dimension(462, 126));
        changeLabelSize(rootPackage, SMALL_LABEL);
        checkHStackContainerDimensions(AUTO_SIZE_DIMENSION, new Dimension(451, 111), new Dimension(462, 107));

        changeLabelSize(region1Package, LONG_LABEL);
        checkHStackContainerDimensions(AUTO_SIZE_DIMENSION, new Dimension(704, 111), new Dimension(783, 107));
        changeLabelSize(region1Package, SMALL_LABEL);
        checkHStackContainerDimensions(AUTO_SIZE_DIMENSION, new Dimension(451, 111), new Dimension(462, 107));
    }

    /**
     * Use case : Vertical stack and autosize</br>
     * Check behavior with compartment and regions label changes
     */
    public void testVerticalStackWithAutoSize() {
        checkVStackContainerDimensions(AUTO_SIZE_DIMENSION, new Dimension(314, 180), new Dimension(314, 176));

        changeLabelSize(rootPackage, LONG_LABEL);
        checkVStackContainerDimensions(AUTO_SIZE_DIMENSION, new Dimension(314, 226), new Dimension(314, 214));
        changeLabelSize(rootPackage, SMALL_LABEL);
        checkVStackContainerDimensions(AUTO_SIZE_DIMENSION, new Dimension(314, 180), new Dimension(314, 176));

        changeLabelSize(region1Package, LONG_LABEL);
        checkVStackContainerDimensions(AUTO_SIZE_DIMENSION, new Dimension(566, 180), new Dimension(635, 176));
        changeLabelSize(region1Package, SMALL_LABEL);
        checkVStackContainerDimensions(AUTO_SIZE_DIMENSION, new Dimension(314, 180), new Dimension(314, 176));
    }

    /**
     * Use case : FreeFormContainer and auto size</br>
     * Check behavior with main and sub-elements label changes
     */
    public void testFreeFormContainerWithAutoSize() {
        checkFreeFormContainerDimensions(AUTO_SIZE_DIMENSION, new Dimension(185, 75), new Dimension(207, 75));

        changeLabelSize(rootPackage, LONG_LABEL);
        checkFreeFormContainerDimensions(AUTO_SIZE_DIMENSION, new Dimension(639, 75), new Dimension(721, 75));
        changeLabelSize(rootPackage, SMALL_LABEL);
        checkFreeFormContainerDimensions(AUTO_SIZE_DIMENSION, new Dimension(194, 75), new Dimension(218, 75));

        changeLabelSize(region1Package, LONG_LABEL);
        checkFreeFormContainerDimensions(AUTO_SIZE_DIMENSION, new Dimension(194, 75), new Dimension(218, 75));
        changeLabelSize(region1Package, SMALL_LABEL);
        checkFreeFormContainerDimensions(AUTO_SIZE_DIMENSION, new Dimension(194, 75), new Dimension(218, 75));
    }

    /**
     * Use case : ListContainer and auto size</br>
     * Check behavior with main and sub-elements label changes
     */
    public void testListContainerWithAutoSize() {
        checkListContainerDimensions(AUTO_SIZE_DIMENSION, new Dimension(111, 84), new Dimension(120, 72));

        changeLabelSize(rootPackage, LONG_LABEL);
        checkListContainerDimensions(AUTO_SIZE_DIMENSION, new Dimension(565, 84), new Dimension(634, 72));
        changeLabelSize(rootPackage, SMALL_LABEL);
        checkListContainerDimensions(AUTO_SIZE_DIMENSION, new Dimension(120, 84), new Dimension(131, 72));

        changeLabelSize(region1Package, LONG_LABEL);
        checkListContainerDimensions(AUTO_SIZE_DIMENSION, new Dimension(515, 84), new Dimension(578, 72));
        changeLabelSize(region1Package, SMALL_LABEL);
        checkListContainerDimensions(AUTO_SIZE_DIMENSION, new Dimension(120, 84), new Dimension(131, 72));
    }

    /**
     * Use case : Vertical stack and width fixed size</br>
     * Check behavior with compartment and regions label changes
     */
    public void _testVerticalStackWithWidthFixedSize() {
        checkVStackContainerDimensions(AUTO_SIZE_DIMENSION, new Dimension(314, 180), new Dimension(462, 111));

        changeGmfDimension(V_REGION + region1Package.getName(), new Dimension(264, -1));
        changeGmfDimension(V_REGION + region2Package.getName(), new Dimension(264, -1));
        // TODO The check fail because the figure is not updated. I can not figure out why. The test is prefix by _ to
        // disable it.
        checkVStackContainerDimensions(AUTO_SIZE_DIMENSION, new Dimension(268, 180), new Dimension(462, 111));
        checkVRegionDimension(region1Package, null, new Dimension(264, 74), new Dimension(462, 111));
        checkVRegionDimension(region2Package, null, new Dimension(264, 69), new Dimension(462, 111));

        changeLabelSize(region2Package, LONG_LABEL);
        // The region2 gets higher
        checkVRegionDimension(region1Package, null, new Dimension(264, 74), new Dimension(462, 111));
        checkVRegionDimension(region2Package, null, new Dimension(264, 75), new Dimension(462, 111));

        changeLabelSize(rootPackage, LONG_LABEL);
        // The region size do not change
        checkVRegionDimension(region1Package, null, new Dimension(264, 74), new Dimension(462, 111));
        checkVRegionDimension(region2Package, null, new Dimension(264, 75), new Dimension(462, 111));
        checkVStackContainerDimensions(AUTO_SIZE_DIMENSION, new Dimension(268, 232), new Dimension(462, 111));
    }

    /**
     * Use case : Horizontal stack and width fixed size</br>
     * Check behavior with compartment and regions label changes
     */
    public void testHorizontalStackWithWidthFixedSize() {
        checkHStackContainerDimensions(AUTO_SIZE_DIMENSION, new Dimension(462, 111), new Dimension(462, 107));

        changeGmfDimension(H_REGION + region2Package.getName(), new Dimension(103, -1));
        checkHStackContainerDimensions(AUTO_SIZE_DIMENSION, new Dimension(418, 111), new Dimension(418, 107));
        checkHRegionDimension(region1Package, AUTO_SIZE_DIMENSION, new Dimension(311, 74), new Dimension(311, 74));
        checkHRegionDimension(region2Package, new Dimension(103, -1), new Dimension(103, 74), new Dimension(103, 74));

        changeLabelSize(region2Package, LONG_LABEL);
        checkHRegionDimension(region1Package, AUTO_SIZE_DIMENSION, new Dimension(311, 189), new Dimension(311, 157));
        checkHRegionDimension(region2Package, new Dimension(103, -1), new Dimension(103, 189), new Dimension(103, 157));

        changeLabelSize(rootPackage, LONG_LABEL);
        // The region size do not change
        checkHRegionDimension(region1Package, AUTO_SIZE_DIMENSION, new Dimension(311, 189), new Dimension(311, 157));
        checkHRegionDimension(region2Package, new Dimension(103, -1), new Dimension(103, 189), new Dimension(103, 157));
        checkHStackContainerDimensions(AUTO_SIZE_DIMENSION, new Dimension(418, 249), new Dimension(418, 209));
    }

    /**
     * Use case : Horizontal stack and height fixed size</br>
     * Check behavior with compartment and regions label changes
     */
    public void testHorizontalStackWithHeightFixedSize() {
        changeGmfDimension(H_REGION + region1Package.getName(), new Dimension(-1, 91));
        changeGmfDimension(H_REGION + region2Package.getName(), new Dimension(-1, 91));
        checkHStackContainerDimensions(AUTO_SIZE_DIMENSION, new Dimension(451, 128), new Dimension(462, 124));
        checkHRegionDimension(region1Package, null, new Dimension(311, 91), new Dimension(311, 91));
        checkHRegionDimension(region2Package, null, new Dimension(136, 91), new Dimension(147, 91));

        changeLabelSize(region2Package, LONG_LABEL);
        Dimension compartmentDimension = new Dimension(880, 128);
        Dimension compartmentDimensionLinux = new Dimension(948, 124);
        Dimension region1Dimension = new Dimension(311, 91);
        Dimension region2Dimension = new Dimension(565, 91);
        Dimension region1DimensionLinux = new Dimension(311, 91);
        Dimension region2DimensionLinux = new Dimension(633, 91);
        checkHStackContainerDimensions(AUTO_SIZE_DIMENSION, compartmentDimension, compartmentDimensionLinux);
        checkHRegionDimension(region1Package, null, region1Dimension, region1DimensionLinux);
        checkHRegionDimension(region2Package, null, region2Dimension, region2DimensionLinux);

        changeLabelSize(rootPackage, LONG_LABEL);
        // nothing change
        checkHStackContainerDimensions(AUTO_SIZE_DIMENSION, compartmentDimension, compartmentDimensionLinux);
        checkHRegionDimension(region1Package, null, region1Dimension, region1DimensionLinux);
        checkHRegionDimension(region2Package, null, region2Dimension, region2DimensionLinux);

        changeLabelSize(rootPackage, LONG_LABEL + LONG_LABEL);
        // The region size do not change
        checkHStackContainerDimensions(AUTO_SIZE_DIMENSION, new Dimension(880, 151), new Dimension(948, 143));
        checkHRegionDimension(region1Package, null, region1Dimension, region1DimensionLinux);
        checkHRegionDimension(region2Package, null, region2Dimension, region2DimensionLinux);
    }

    /**
     * Use case : stack container with no region</br>
     */
    public void testStackContainerWithNoRegion() {
        checkStackContainerWithNoRegionDimension(AUTO_SIZE_DIMENSION, new Dimension(276, 48), new Dimension(305, 44));

        changeLabelSize(rootPackage, LONG_LABEL);
        checkStackContainerWithNoRegionDimension(AUTO_SIZE_DIMENSION, new Dimension(730, 48), new Dimension(819, 44));
    }

    private void checkVStackContainerDimensions(Dimension expectedGmfDimension, Dimension expectedFigureDimensionWindows, Dimension expectedFigureDimensionLinux) {
        checkDimensions(V_STACK_CONTAINER + rootPackage.getName(), expectedGmfDimension, expectedFigureDimensionWindows, expectedFigureDimensionLinux);
    }

    private void checkHStackContainerDimensions(Dimension expectedGmfDimension, Dimension expectedFigureDimensionWindows, Dimension expectedFigureDimensionLinux) {
        checkDimensions(H_STACK_CONTAINER + rootPackage.getName(), expectedGmfDimension, expectedFigureDimensionWindows, expectedFigureDimensionLinux);
    }

    private void checkStackContainerWithNoRegionDimension(Dimension expectedGmfDimension, Dimension expectedFigureDimensionWindows, Dimension expectedFigureDimensionLinux) {
        checkDimensions(V_STACK_CONT_NO_REGION + rootPackage.getName(), expectedGmfDimension, expectedFigureDimensionWindows, expectedFigureDimensionLinux);
    }

    private void checkFreeFormContainerDimensions(Dimension expectedGmfDimension, Dimension expectedFigureDimensionWindows, Dimension expectedFigureDimensionLinux) {
        checkDimensions(FREE_FORM_CONTAINER + rootPackage.getName(), expectedGmfDimension, expectedFigureDimensionWindows, expectedFigureDimensionLinux);
    }

    private void checkListContainerDimensions(Dimension expectedGmfDimension, Dimension expectedFigureDimensionWindows, Dimension expectedFigureDimensionLinux) {
        checkDimensions(LIST_CONTAINER + rootPackage.getName(), expectedGmfDimension, expectedFigureDimensionWindows, expectedFigureDimensionLinux);
    }

    private void checkVRegionDimension(EPackage regionPackage, Dimension expectedGmfDimension, Dimension expectedFigureDimensionWindows, Dimension expectedFigureDimensionLinux) {
        checkDimensions(V_REGION + regionPackage.getName(), expectedGmfDimension, expectedFigureDimensionWindows, expectedFigureDimensionLinux);
    }

    private void checkHRegionDimension(EPackage regionPackage, Dimension expectedGmfDimension, Dimension expectedFigureDimensionWindows, Dimension expectedFigureDimensionLinux) {
        checkDimensions(H_REGION + regionPackage.getName(), expectedGmfDimension, expectedFigureDimensionWindows, expectedFigureDimensionLinux);
    }

    private void changeLabelSize(EPackage ePackage, String newLabel) {
        RecordingCommand changeNameCommand = new RecordingCommand(session.getTransactionalEditingDomain()) {

            @Override
            protected void doExecute() {
                ePackage.setName(newLabel);
            }
        };
        session.getTransactionalEditingDomain().getCommandStack().execute(changeNameCommand);
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Check that the bounds (GMF and Draw2D) are as expected.
     *
     * @param label
     *            Label of the container to check.
     * @param expectedGmfDimension
     *            The GMF expected bounds
     * @param expectedFigureDimensionWindows
     *            The draw2d expected bounds. If the x, y , width or height in this bounds is equal to -1, we don't
     *            check it. This is useful in case of size that depends on Font (with different result according to OS).
     * @param expectedFigureDimensionLinux
     *            Idem than expectedFigureDimensionWindows but for Linux case.
     * @return the current DrawD2 bounds
     */
    private Dimension checkDimensions(String label, Dimension expectedGmfDimension, Dimension expectedFigureDimensionWindows, Dimension expectedFigureDimensionLinux) {
        return checkDimensions(label, expectedGmfDimension, expectedFigureDimensionWindows, expectedFigureDimensionLinux, 0, 0);
    }

    /**
     * Check that the bounds (GMF and Draw2D) are as expected.
     *
     * @param label
     *            Label of the container to check.
     * @param expectedGmfDimension
     *            The GMF expected bounds
     * @param expectedFigureDimensionWindows
     *            The draw2d expected bounds. If the x, y , width or height in this bounds is equal to -1, we don't
     *            check it. This is useful in case of size that depends on Font (with different result according to OS).
     * @param expectedFigureDimensionLinux
     *            Idem than expectedFigureDimensionWindows but for Linux case.
     * @param widthDelta
     *            The width delta to consider the width as equal (because of font size that can be slightly different on
     *            each OS).
     * @param heightDelta
     *            The height delta to consider the height as equal (because of font size that can be slightly different
     *            on each OS).
     * @return the current DrawD2 bounds
     */
    private Dimension checkDimensions(String label, Dimension expectedGmfDimension, Dimension expectedFigureDimensionWindows, Dimension expectedFigureDimensionLinux, int widthDelta, int heightDelta) {
        DDiagramElementContainer region = getDiagramElementsFromLabel(diagram, label, DDiagramElementContainer.class).get(0);
        AbstractDiagramElementContainerEditPart editPart = (AbstractDiagramElementContainerEditPart) getEditPart(region);

        Dimension expectedFigureDimension = System.getProperty("os.name").equals("Linux") ? expectedFigureDimensionLinux : expectedFigureDimensionWindows;

        IFigure mainFigure = editPart.getMainFigure();
        if (expectedGmfDimension != null) {
            assertEquals("Wrong GMF bounds for " + label, expectedGmfDimension, getDimensions((Node) editPart.getNotationView()));
        }

        if (expectedFigureDimensionLinux.width() != -1) {
            assertEquals("Wrong Draw2D width for " + label, expectedFigureDimension.width(), mainFigure.getBounds().width(), widthDelta);
        }
        if (expectedFigureDimensionLinux.height() != -1) {
            assertEquals("Wrong Draw2D height for " + label, expectedFigureDimension.height(), mainFigure.getBounds().height(), heightDelta);
        }

        return mainFigure.getBounds().getSize();
    }

    private Dimension getDimensions(Node notationView) {
        Dimension dimension = new Dimension();
        LayoutConstraint layoutConstraint = notationView.getLayoutConstraint();
        if (layoutConstraint instanceof Size) {
            Size size = (Size) layoutConstraint;
            dimension.setSize(size.getWidth(), size.getHeight());
        }
        return dimension;
    }

    private void changeGmfDimension(String label, Dimension nodeSize) {
        DDiagramElementContainer region = getDiagramElementsFromLabel(diagram, label, DDiagramElementContainer.class).get(0);
        AbstractDiagramElementContainerEditPart editPart = (AbstractDiagramElementContainerEditPart) getEditPart(region);

        Node gmfNode = (Node) editPart.getNotationView();
        LayoutConstraint layoutConstraint = gmfNode.getLayoutConstraint();
        if (layoutConstraint instanceof Size) {
            RecordingCommand changeLayoutConstraintCommand = new RecordingCommand(session.getTransactionalEditingDomain()) {
                @Override
                protected void doExecute() {
                    ((Size) layoutConstraint).setWidth(nodeSize.width);
                    ((Size) layoutConstraint).setHeight(nodeSize.height);
                }
            };
            session.getTransactionalEditingDomain().getCommandStack().execute(changeLayoutConstraintCommand);
        }
        TestsUtil.synchronizationWithUIThread();
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Change the default font.
     *
     * @param fontName
     *            the font name to set as default.
     * @return the previous default font name.
     */
    protected String changeDefaultFontName(String fontName) {
        IPreferenceStore preferenceStore = (IPreferenceStore) DiagramUIPlugin.DIAGRAM_PREFERENCES_HINT.getPreferenceStore();
        FontData fontData = PreferenceConverter.getFontData(preferenceStore, IPreferenceConstants.PREF_DEFAULT_FONT);

        // Get the actual font.
        String oldName = fontData.getName();

        // Change the font.
        fontData.setName(fontName);
        PreferenceConverter.setDefault(preferenceStore, IPreferenceConstants.PREF_DEFAULT_FONT, fontData);
        return oldName;
    }
}
