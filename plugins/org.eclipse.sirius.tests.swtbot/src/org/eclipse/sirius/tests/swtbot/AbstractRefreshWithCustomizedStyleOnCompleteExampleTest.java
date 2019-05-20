/*******************************************************************************
 * Copyright (c) 2010, 2018 THALES GLOBAL SERVICES and other.
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
package org.eclipse.sirius.tests.swtbot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.eclipse.core.runtime.Path;
import org.eclipse.draw2d.Border;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.ConnectionRouter;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor.PropertyValueWrapper;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DescriptionCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ResizableCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.TextCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderedNodeFigure;
import org.eclipse.gmf.runtime.diagram.ui.figures.NoteFigure;
import org.eclipse.gmf.runtime.diagram.ui.internal.editparts.NoteAttachmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.editparts.TextEditPart;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramColorRegistry;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.draw2d.ui.internal.routers.RectilinearRouter;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.ArrowType;
import org.eclipse.gmf.runtime.notation.LineType;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.TextAlignment;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.notation.datatype.GradientData;
import org.eclipse.sirius.business.api.metamodel.helper.FontFormatHelper;
import org.eclipse.sirius.common.tools.api.resource.FileProvider;
import org.eclipse.sirius.common.tools.api.util.ReflectionHelper;
import org.eclipse.sirius.diagram.AlignmentKind;
import org.eclipse.sirius.diagram.BundledImageShape;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.EdgeArrows;
import org.eclipse.sirius.diagram.EdgeRouting;
import org.eclipse.sirius.diagram.GaugeCompositeStyle;
import org.eclipse.sirius.diagram.LabelPosition;
import org.eclipse.sirius.diagram.LineStyle;
import org.eclipse.sirius.diagram.ShapeContainerStyle;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.diagram.description.FoldingStyle;
import org.eclipse.sirius.diagram.ui.business.internal.bracket.BracketConnectionQuery;
import org.eclipse.sirius.diagram.ui.business.internal.bracket.Direction;
import org.eclipse.sirius.diagram.ui.business.internal.bracket.DirectionUtil;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart.ViewEdgeFigure;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNameEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramListEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.graphical.edit.part.specific.BracketEdgeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.BundledImageEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DotEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DotEditPart.DotFigure;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.GaugeCompositeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.NoteEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.WorkspaceImageEditPart;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.figure.BundledImageFigure;
import org.eclipse.sirius.diagram.ui.tools.api.figure.FoldingToggleImageFigure;
import org.eclipse.sirius.diagram.ui.tools.internal.routers.DForestRouter;
import org.eclipse.sirius.diagram.ui.tools.internal.routers.DTreeRouter;
import org.eclipse.sirius.ext.draw2d.figure.PolygoneAndPolylineDecoraction;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.GaugeCompositeFigure;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.SiriusWrapLabel;
import org.eclipse.sirius.tests.support.api.ImageEquality;
import org.eclipse.sirius.tests.support.api.SiriusAssert;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.api.widget.BackgroundColorFigureGetter;
import org.eclipse.sirius.tests.swtbot.support.api.widget.NodeFigureGradientDataGetter;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.LabelAlignment;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.keyboard.Keystrokes;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.AbstractSWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCCombo;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCheckBox;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToolbarButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * An abstract class providing set-up and facilities for testing the style customization features.
 * 
 * <p>
 * Relevant issues :
 * <ul>
 * <li>VP-3535: finer style customization</li>
 * </ul>
 * </p>
 * 
 * @author alagarde
 */
@SuppressWarnings({ "restriction" })
public class AbstractRefreshWithCustomizedStyleOnCompleteExampleTest extends AbstractRefreshWithCustomizedStyleTest {

    private static final String REPRESENTATION_NAME = "VP-3535_Diagram";

    private static final String PATH = "/data/unit/refresh/VP-3535/";

    private static final String MODELER_RESOURCE_NAME = "VP-3535.odesign";

    private static final String SEMANTIC_RESOURCE_NAME = "VP-3535.ecore";

    private static final String SESSION_RESOURCE_NAME = "VP-3535.aird";

    private static final String IMAGE_NAME = "image.bmp";

    protected static final String NEW_IMAGE_NAME = "image.jpg";

    private static final String PROPERTIES = "Properties";

    private static final String STYLE_TAB_NAME = "Style";

    private static final String APPEARANCE_TAB_NAME = "Appearance";

    protected SWTBotGefEditPart eClass1WithSquareStyleBot;

    protected SWTBotGefEditPart eClass1WithLozengeStyleBot;

    protected SWTBotGefEditPart eClass1WithEllipseStyleBot;

    protected SWTBotGefEditPart eClass1WithBundledImageStyleBot;

    protected SWTBotGefEditPart eClass1WithBundledImageStyleErrorBot;

    protected SWTBotGefEditPart eClass1WithNoteStyleBot;

    protected SWTBotGefEditPart eClass1WithDotStyleBot;

    protected SWTBotGefEditPart eClass1WithGaugeStyleBot;

    protected SWTBotGefEditPart eClass1WithWorkspaceImageStyleBot;

    protected SWTBotGefEditPart package1WithFlatContainerStyleBot;

    protected SWTBotGefEditPart package1WithShapeContainerStyleBot;

    protected SWTBotGefEditPart package1WithWorkspaceImageStyleBot;

    protected SWTBotGefEditPart superTypeEditPartBot;

    protected SWTBotGefEditPart referenceEditPartBot;

    protected SWTBotGefEditPart noteEditPartBot;

    protected SWTBotGefConnectionEditPart noteAttachmentEditPartBot;

    protected SWTBotGefEditPart textEditPartBot;

    private SWTBotView propertiesBot;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();

        copyFileToTestProject(Activator.PLUGIN_ID, PATH, SESSION_RESOURCE_NAME, SEMANTIC_RESOURCE_NAME, MODELER_RESOURCE_NAME, IMAGE_NAME, NEW_IMAGE_NAME);
        UIResource representationsResource = new UIResource(designerProject, "/", SESSION_RESOURCE_NAME);
        localSession = designerPerspective.openSessionFromFile(representationsResource);
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, "new " + REPRESENTATION_NAME, DDiagram.class);

        eClass1WithSquareStyleBot = editor.getEditPart("EClass1WithSquareStyle", AbstractDiagramNodeEditPart.class);
        eClass1WithLozengeStyleBot = editor.getEditPart("EClass1WithLozengeStyle", AbstractDiagramNodeEditPart.class);
        eClass1WithEllipseStyleBot = editor.getEditPart("EClass1WithEllipseStyle", AbstractDiagramNodeEditPart.class);
        eClass1WithBundledImageStyleBot = editor.getEditPart("EClass1WithBundledImageStyle", AbstractDiagramNodeEditPart.class);
        eClass1WithBundledImageStyleErrorBot = editor.getEditPart("EClass1WithBundledImageStyleError", AbstractDiagramNodeEditPart.class);
        eClass1WithNoteStyleBot = editor.getEditPart("EClass1WithNoteStyle", AbstractDiagramNodeEditPart.class);
        eClass1WithDotStyleBot = editor.getEditPart("EClass1WithDotStyle", AbstractDiagramNodeEditPart.class);
        eClass1WithGaugeStyleBot = editor.getEditPart("EClass1WithGaugeStyle", AbstractDiagramNodeEditPart.class);
        eClass1WithWorkspaceImageStyleBot = editor.getEditPart("EClass1WithWorkspaceImageStyle", AbstractDiagramNodeEditPart.class);

        superTypeEditPartBot = editor.getEditPart("centerSuperTypeEClass2", BracketEdgeEditPart.class);
        referenceEditPartBot = editor.getEditPart("eClass2", AbstractDiagramEdgeEditPart.class);

        package1WithFlatContainerStyleBot = editor.getEditPart("EPackage1WithFlatContainerStyle", AbstractDiagramContainerEditPart.class);
        package1WithShapeContainerStyleBot = editor.getEditPart("EPackage1WithShapeContainerStyle", AbstractDiagramContainerEditPart.class);
        package1WithWorkspaceImageStyleBot = editor.getEditPart("EPackage1WithWorkspaceImageStyle", AbstractDiagramContainerEditPart.class);

        noteEditPartBot = editor.getEditPart("Text", org.eclipse.gmf.runtime.diagram.ui.editparts.NoteEditPart.class);
        noteAttachmentEditPartBot = noteEditPartBot.targetConnections().get(0);
        textEditPartBot = noteAttachmentEditPartBot.source();

        propertiesBot = bot.viewByTitle(PROPERTIES);
    }

    protected boolean isContainmentReference(EStructuralFeature feature) {
        return feature instanceof EReference && ((EReference) feature).isContainment();
    }

    protected void resetStylePropertiesToDefault(SWTBotGefEditPart swtBotGefEditPart, boolean launchResetFromTabbar) {
        swtBotGefEditPart.select();
        AbstractSWTBot<? extends Widget> resetStylePropertiesToDefaultButton = getResetStylePropertiesToDefaultValuesButton(launchResetFromTabbar, true);
        click(resetStylePropertiesToDefaultButton);
        editor.setFocus();
        editor.scrollTo(0, 0);
        editor.click(5, 5);
        SWTBotUtils.waitAllUiEvents();
    }

    @Override
    protected void click(AbstractSWTBot<? extends Widget> button) {
        button.setFocus();
        if (button instanceof SWTBotToolbarButton) {
            ((SWTBotToolbarButton) button).click();
        } else if (button instanceof SWTBotButton) {
            ((SWTBotButton) button).click();
        }
    }

    /**
     * Customize a Sirius style property using the Style tab of the property view for the specified selected
     * {@link SWTBotGefEditPart}.
     * 
     * @param swtBotGefEditPart
     *            the specified selected {@link SWTBotGefEditPart}.
     * @param style
     *            the {@link Style}
     * @param feature
     *            the {@link EStructuralFeature} to customize
     * @return true if the specifed {@link EStructuralFeature} has been customized
     */
    protected boolean customizeSiriusStylePropertyFromStyleTab(SWTBotGefEditPart swtBotGefEditPart, Style style, EStructuralFeature feature) {
        boolean customized = false;
        editor.reveal(swtBotGefEditPart.part());
        swtBotGefEditPart.select();
        SWTBotSiriusHelper.selectPropertyTabItem(STYLE_TAB_NAME, propertiesBot.bot());
        AdapterFactory adapterFactory = DiagramUIPlugin.getPlugin().getItemProvidersAdapterFactory();
        IItemPropertySource itemPropertySource = (IItemPropertySource) adapterFactory.adapt(style, IItemPropertySource.class);
        IItemPropertyDescriptor propertyDescriptor = itemPropertySource.getPropertyDescriptor(style, feature.getName());
        if (propertyDescriptor != null) {
            customized = true;
            String category = propertyDescriptor.getCategory(style);
            if (category == null) {
                category = "Misc";
            }
            String displayName = propertyDescriptor.getDisplayName(style);
            Object propertyValue = propertyDescriptor.getPropertyValue(style);
            Object value = null;
            Collection<?> choiceOfValues = null;
            if (propertyValue instanceof PropertyValueWrapper) {
                PropertyValueWrapper propertyValueWrapper = (PropertyValueWrapper) propertyValue;
                choiceOfValues = propertyDescriptor.getChoiceOfValues(style);
                value = propertyValueWrapper.getEditableValue(style);
            }
            SWTBotTree tree = propertiesBot.bot().tree();
            SWTBotTreeItem treeItem = tree.expandNode(category).select().getNode(displayName);
            treeItem.doubleClick();
            if (feature instanceof EAttribute) {
                if (feature.getEType() instanceof EEnum && value instanceof List && ((List) value).isEmpty()) {
                    // For label format, we make the change from tabbar (more
                    // simple)...
                    editor.setFocus();
                    editor.bot().toolbarToggleButtonWithTooltip("Bold Font Style").click();
                    SWTBotUtils.waitAllUiEvents();
                } else if (feature.getEType() instanceof EEnum && value instanceof Enumerator) {
                    SWTBotCCombo ccomboBox = propertiesBot.bot().ccomboBox();
                    Enumerator newValueLiteral = getNewValueLiteral(value, choiceOfValues);
                    String text = propertyDescriptor.getLabelProvider(style).getText(newValueLiteral);
                    ccomboBox.setSelection(text);
                    // In photon we need to press ENTER to leave the combo
                    if (TestsUtil.isPhotonPlatformOrLater()) {
                        ccomboBox.pressShortcut(Keystrokes.CR);
                    }
                } else if (feature.getEType() == EcorePackage.Literals.EBOOLEAN) {

                    // In photon, the feature widget is not a combo anymore.
                    if (TestsUtil.isPhotonPlatformOrLater()) {
                        SWTBotCheckBox botCheckBox = propertiesBot.bot().checkBox();
                        if (value == Boolean.TRUE) {
                            botCheckBox.deselect();
                        } else {
                            botCheckBox.select();
                        }
                    } else {
                        SWTBotCCombo ccomboBox = propertiesBot.bot().ccomboBox();
                        String newSelection = Boolean.TRUE.toString();
                        if (value == Boolean.TRUE) {
                            newSelection = Boolean.FALSE.toString();
                        }
                        ccomboBox.setSelection(newSelection);
                    }
                } else if (feature == ViewpointPackage.Literals.BASIC_LABEL_STYLE__ICON_PATH) {
                    propertiesBot.bot().text().setText("/" + getProjectName() + "/" + NEW_IMAGE_NAME);
                } else if (feature == ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_COLOR || feature == DiagramPackage.Literals.BORDERED_STYLE__BORDER_COLOR
                        || feature == DiagramPackage.Literals.SHAPE_CONTAINER_STYLE__BACKGROUND_COLOR || feature == DiagramPackage.Literals.FLAT_CONTAINER_STYLE__BACKGROUND_COLOR
                        || feature == DiagramPackage.Literals.FLAT_CONTAINER_STYLE__FOREGROUND_COLOR || feature == DiagramPackage.Literals.ELLIPSE__COLOR
                        || feature == DiagramPackage.Literals.SQUARE__COLOR || feature == DiagramPackage.Literals.DOT__BACKGROUND_COLOR || feature == DiagramPackage.Literals.EDGE_STYLE__STROKE_COLOR
                        || feature == DiagramPackage.Literals.NOTE__COLOR || feature == DiagramPackage.Literals.BUNDLED_IMAGE__COLOR || feature == DiagramPackage.Literals.LOZENGE__COLOR) {
                    propertiesBot.bot().text().setText("100,100,100");
                } else if (feature == DiagramPackage.Literals.BORDERED_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION) {
                    // FIXME VP-3559 : the customization of borderSize
                    // doesn't works because the
                    // borderSizeComputationExpression change doesn't
                    // update the borderSize attribute
                    propertiesBot.bot().text().setText("20");
                } else if (feature.getEType() == EcorePackage.Literals.EINTEGER_OBJECT || feature.getEType() == EcorePackage.Literals.EINT) {
                    propertiesBot.bot().text().setText("20");
                } else if (feature == DiagramPackage.Literals.WORKSPACE_IMAGE__WORKSPACE_PATH) {
                    propertiesBot.bot().text().setText("/" + getProjectName() + "/" + NEW_IMAGE_NAME);
                }
            } else if (feature instanceof EReference) {
                EReference eReference = (EReference) feature;
                // FIXME : color of BundledImage : the proposed color have
                // duplicates
                if (eReference.isContainment()) {
                    SWTBotCCombo ccomboBox = propertiesBot.bot().ccomboBox();
                    ccomboBox.setSelection(2);
                } else {
                    propertiesBot.bot().ccomboBox().setSelection(1);
                }
            }
            editor.scrollTo(0, 0);
            editor.click(5, 5);
            editor.setFocus();
            editor.reveal(swtBotGefEditPart.part());
            SWTBotUtils.waitAllUiEvents();
        }
        return customized;
    }

    /**
     * Customize a GMF style property using the Appearance tab of the property view for the specified selected
     * {@link SWTBotGefEditPart}.
     * 
     * @param swtBotGefEditPart
     *            the specified selected {@link SWTBotGefEditPart}.
     * @param gmfStyle
     *            the {@link org.eclipse.gmf.runtime.notation.Style}
     * @param gmfStyleEAttribute
     *            the {@link EAttribute} to customize
     * @return true if the specified {@link EAttribute} has been customized
     */
    protected boolean customizeGMFStyleProperty(SWTBotGefEditPart swtBotGefEditPart, org.eclipse.gmf.runtime.notation.Style gmfStyle, EAttribute gmfStyleEAttribute) {
        boolean customized = false;
        editor.reveal(swtBotGefEditPart.part());
        swtBotGefEditPart.select();
        // NOTE : for Text, Note and NoteAttachment the Appearance tab is empty
        // then use the tabbar, contextual menu or Diagram menu
        if (!isSiriusElt(swtBotGefEditPart)) {
            if (gmfStyleEAttribute == NotationPackage.Literals.FILL_STYLE__FILL_COLOR) {
                // FIXME : the fillColor doesn't works for TextEditPart
                if (!(swtBotGefEditPart.part() instanceof TextEditPart)) {
                    editor.bot().toolbarDropDownButtonWithTooltip("Fill &Color").menuItem("Magenta").click();
                    SWTBotUtils.waitAllUiEvents();
                    customized = true;
                }
            } else if (gmfStyleEAttribute == NotationPackage.Literals.FILL_STYLE__TRANSPARENCY) {
                // FIXME : the transparency doesn't works for TextEditPart
                if (!(swtBotGefEditPart.part() instanceof TextEditPart)) {
                    int transparancy = (Integer) gmfStyle.eGet(gmfStyleEAttribute);
                    TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(gmfStyle);
                    Command setTransparancyCmd = SetCommand.create(domain, gmfStyle, gmfStyleEAttribute, (transparancy + 50) % 100);
                    domain.getCommandStack().execute(setTransparancyCmd);
                    SWTBotUtils.waitAllUiEvents();
                    customized = true;
                }
            } else if (gmfStyleEAttribute == NotationPackage.Literals.FILL_STYLE__GRADIENT) {
                // FIXME : the gradient doesn't works for TextEditPart
                if (!(swtBotGefEditPart.part() instanceof TextEditPart)) {
                    TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(gmfStyle);
                    GradientData newGradientData = GradientData.getDefaultGradientData();
                    Command setGradientDataCmd = SetCommand.create(domain, gmfStyle, gmfStyleEAttribute, newGradientData);
                    domain.getCommandStack().execute(setGradientDataCmd);
                    SWTBotUtils.waitAllUiEvents();
                    customized = true;
                }
            } else if (gmfStyleEAttribute == NotationPackage.Literals.LINE_STYLE__LINE_COLOR) {
                editor.bot().toolbarDropDownButtonWithTooltip("Li&ne Color").menuItem("Magenta").click();
                SWTBotUtils.waitAllUiEvents();
                customized = true;
            } else if (gmfStyleEAttribute == NotationPackage.Literals.LINE_STYLE__LINE_WIDTH) {
                // FIXME : lineWidth change doesn't works for TextEditPart
                if (!(swtBotGefEditPart.part() instanceof TextEditPart)) {
                    if (swtBotGefEditPart.part() instanceof NoteAttachmentEditPart) {
                        TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(gmfStyle);
                        int lineWidth = (Integer) gmfStyle.eGet(gmfStyleEAttribute);
                        Command changeLineWidthCmd = SetCommand.create(domain, gmfStyle, gmfStyleEAttribute, lineWidth + 10);
                        domain.getCommandStack().execute(changeLineWidthCmd);
                        SWTBotUtils.waitAllUiEvents();
                        customized = true;
                    } else {
                        editor.clickContextMenu("Five Points");
                        customized = true;
                    }
                }
            } else if (gmfStyleEAttribute == NotationPackage.Literals.FONT_STYLE__FONT_COLOR) {
                editor.bot().toolbarDropDownButtonWithTooltip("Font Color").menuItem("Magenta").click();
                SWTBotUtils.waitAllUiEvents();
                customized = true;
            } else if (gmfStyleEAttribute == NotationPackage.Literals.FONT_STYLE__FONT_NAME) {
                TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(gmfStyle);
                String newFontName = "Arial";
                Command changeFontNameCmd = SetCommand.create(domain, gmfStyle, gmfStyleEAttribute, newFontName);
                domain.getCommandStack().execute(changeFontNameCmd);
                SWTBotUtils.waitAllUiEvents();
                customized = true;
            } else if (gmfStyleEAttribute == NotationPackage.Literals.FONT_STYLE__FONT_HEIGHT) {
                TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(gmfStyle);
                int fontHeight = (Integer) gmfStyle.eGet(gmfStyleEAttribute);
                int newFontHeight = 2 * fontHeight;
                Command changeFontHeightCmd = SetCommand.create(domain, gmfStyle, gmfStyleEAttribute, newFontHeight);
                domain.getCommandStack().execute(changeFontHeightCmd);
                SWTBotUtils.waitAllUiEvents();
                customized = true;
            } else if (gmfStyleEAttribute == NotationPackage.Literals.FONT_STYLE__BOLD) {
                editor.bot().toolbarToggleButtonWithTooltip("Bold Font Style").click();
                SWTBotUtils.waitAllUiEvents();
                customized = true;
            } else if (gmfStyleEAttribute == NotationPackage.Literals.FONT_STYLE__ITALIC) {
                editor.bot().toolbarToggleButtonWithTooltip("Italic Font Style").click();
                SWTBotUtils.waitAllUiEvents();
                customized = true;
            } else if (gmfStyleEAttribute == NotationPackage.Literals.FONT_STYLE__UNDERLINE) {
                boolean isUnderline = (Boolean) gmfStyle.eGet(gmfStyleEAttribute);
                TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(gmfStyle);
                Command setUnderlineCmd = SetCommand.create(domain, gmfStyle, gmfStyleEAttribute, !isUnderline);
                domain.getCommandStack().execute(setUnderlineCmd);
                SWTBotUtils.waitAllUiEvents();
                customized = true;
            } else if (gmfStyleEAttribute == NotationPackage.Literals.FONT_STYLE__STRIKE_THROUGH) {
                boolean isStrikeThrough = (Boolean) gmfStyle.eGet(gmfStyleEAttribute);
                TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(gmfStyle);
                Command setStrikeThroughCmd = SetCommand.create(domain, gmfStyle, gmfStyleEAttribute, !isStrikeThrough);
                domain.getCommandStack().execute(setStrikeThroughCmd);
                SWTBotUtils.waitAllUiEvents();
                customized = true;
            } else if (gmfStyleEAttribute == NotationPackage.Literals.TITLE_STYLE__SHOW_TITLE) {
                boolean isShowTitle = (Boolean) gmfStyle.eGet(gmfStyleEAttribute);
                TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(gmfStyle);
                Command setShowTitleCmd = SetCommand.create(domain, gmfStyle, gmfStyleEAttribute, !isShowTitle);
                domain.getCommandStack().execute(setShowTitleCmd);
                SWTBotUtils.waitAllUiEvents();
                customized = true;
            } else if (gmfStyleEAttribute == NotationPackage.Literals.TEXT_STYLE__TEXT_ALIGNMENT) {
                // editor.clickContextMenu("Right");
                // FIXME : doesn't works
                SWTBotUtils.waitAllUiEvents();
            } else if (gmfStyleEAttribute == NotationPackage.Literals.DESCRIPTION_STYLE__DESCRIPTION) {
                String description = (String) gmfStyle.eGet(gmfStyleEAttribute);
                TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(gmfStyle);
                Command setDescriptionCmd = SetCommand.create(domain, gmfStyle, gmfStyleEAttribute, description + "_Description");
                domain.getCommandStack().execute(setDescriptionCmd);
                SWTBotUtils.waitAllUiEvents();
                customized = true;
            } else if (gmfStyleEAttribute == NotationPackage.Literals.LINE_TYPE_STYLE__LINE_TYPE) {
                editor.clickContextMenu("Dash Dot Dot");
                SWTBotUtils.waitAllUiEvents();
                customized = true;
            } else if (gmfStyleEAttribute == NotationPackage.Literals.ARROW_STYLE__ARROW_SOURCE) {
                editor.clickContextMenu("Solid Arrow");
                SWTBotUtils.waitAllUiEvents();
                customized = true;
            } else if (gmfStyleEAttribute == NotationPackage.Literals.ARROW_STYLE__ARROW_TARGET) {
                editor.clickContextMenu("Solid Arrow");
                SWTBotUtils.waitAllUiEvents();
                customized = true;
            }
        } else {
            SWTBotSiriusHelper.selectPropertyTabItem(APPEARANCE_TAB_NAME);
            bot.viewByTitle("Properties");
        }
        return customized;
    }

    /**
     * Tells if the specified {@link SWTBotGefEditPart} represents a Sirius element or a GMF specific element (Text,
     * Note or NoteAttachment).
     * 
     * @param swtBotGefEditPart
     *            the specified {@link SWTBotGefEditPart}
     * @return true if the specified {@link SWTBotGefEditPart} represents a Sirius element or a GMF specific element
     *         (Text, Note or NoteAttachment), false else
     */
    protected boolean isSiriusElt(SWTBotGefEditPart swtBotGefEditPart) {
        boolean isSiriusElt = false;
        View correspondingView = (View) swtBotGefEditPart.part().getModel();
        isSiriusElt = correspondingView.getElement() instanceof DDiagramElement;
        return isSiriusElt;
    }

    /**
     * Check that the draw2d part is conforms to what is defined in the model.
     * 
     * @param swtBotGefEditPart
     *            the {@link SWTBotGefEditPart} for which to check
     * @param feature
     *            the {@link EStructuralFeature} to check
     * @throws Exception
     *             throws in failure
     */
    protected void checkFigure(SWTBotGefEditPart swtBotGefEditPart, EStructuralFeature feature) throws Exception {
        if (isGMFStyleProperty(feature)) {
            checkFigureAccordingToGMFStyleProperty(swtBotGefEditPart, feature);
        } else {
            checkFigureAccordingToSiriusStyleProperty(swtBotGefEditPart, feature);
        }
    }

    private boolean isGMFStyleProperty(EStructuralFeature feature) {
        boolean isSiriusStyleProperty = feature.getEContainingClass().getEPackage() == NotationPackage.eINSTANCE;
        return isSiriusStyleProperty;
    }

    private void checkFigureAccordingToSiriusStyleProperty(SWTBotGefEditPart swtBotGefEditPart, EStructuralFeature feature) throws Exception {
        SWTBotUtils.waitAllUiEvents();
        IFigure figure = null;
        DDiagramElement dDiagramElement = getDDiagramElement(swtBotGefEditPart);
        Style viewpointStyle = dDiagramElement.getStyle();
        List<SWTBotGefEditPart> descendants = swtBotGefEditPart.descendants(IsInstanceOf.instanceOf(AbstractDiagramNameEditPart.class));
        EditPart editPart = swtBotGefEditPart.part();
        LabelPosition draw2dLabelPosition = null;
        if (descendants.isEmpty()) {
            IDiagramNodeEditPart nodeEditPart = (IDiagramNodeEditPart) swtBotGefEditPart.part();
            figure = nodeEditPart.getNodeLabel();
            draw2dLabelPosition = LabelPosition.NODE_LITERAL;
        } else {
            figure = ((AbstractDiagramNameEditPart) descendants.get(0).part()).getFigure();
            draw2dLabelPosition = LabelPosition.BORDER_LITERAL;
        }
        if (feature == DiagramPackage.Literals.NODE_STYLE__LABEL_POSITION) {
            LabelPosition labelPosition = (LabelPosition) viewpointStyle.eGet(feature);
            assertEquals("The label position on draw2d part doesn't corresponds to style labelPosition", labelPosition, draw2dLabelPosition);
        }
        if (figure instanceof SiriusWrapLabel) {
            final SiriusWrapLabel viewpointWrapLabel = (SiriusWrapLabel) figure;
            Font font = viewpointWrapLabel.getFont();
            FontData[] fontData = font.getFontData();
            if (feature == ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_SIZE) {
                int labelSize = (Integer) viewpointStyle.eGet(feature);
                int height = fontData[0].getHeight();
                assertEquals("The label height of the figure should corresponds to the labelSize feature value of the customized style", labelSize, height);
            } else if (feature == ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_FORMAT) {
                // FIXME : the labelFormat doesn't works with gauge style
                if (viewpointStyle instanceof GaugeCompositeStyle) {
                    return;
                }
                List<FontFormat> fontFormat = (List<FontFormat>) viewpointStyle.eGet(feature);
                List<FontFormat> swtFontFormat = getFigureFontFormat(font);
                assertEquals("The label format of the figure should corresponds to the labelFormat feature value of the customized style", fontFormat, swtFontFormat);
            } else if (feature == ViewpointPackage.Literals.BASIC_LABEL_STYLE__SHOW_ICON) {
                boolean showIcon = (Boolean) viewpointStyle.eGet(feature);
                assertEquals("If the BasicLabelStyle.showIcon is at true, then the figure should display a icon, if is at false, the figure should'nt display the figure", showIcon,
                        viewpointWrapLabel.getIcon() != null);
            } else if (feature == ViewpointPackage.Literals.BASIC_LABEL_STYLE__ICON_PATH) {
                String iconPath = (String) viewpointStyle.eGet(feature);
                final Image imageFromStyle = DiagramUIPlugin.Implementation.findImageDescriptor(FileProvider.getDefault().getFile(new Path(iconPath)).toURI().toURL()).createImage();
                bot.waitUntil(new ICondition() {
                    @Override
                    public boolean test() throws Exception {
                        return ImageEquality.areEqualImages(imageFromStyle, viewpointWrapLabel.getIcon());
                    }

                    @Override
                    public void init(SWTBot bot) {
                    }

                    @Override
                    public String getFailureMessage() {
                        return "The icon of the figure should corresponds to the iconPath feature value of the customized style";
                    }
                });
            } else if (feature == ViewpointPackage.Literals.LABEL_STYLE__LABEL_ALIGNMENT && (editPart instanceof IDiagramContainerEditPart || editPart instanceof IDiagramListEditPart)) {
                IGraphicalEditPart abstractDiagramContainerEditPart = (IGraphicalEditPart) editPart;
                IFigure contentPane = abstractDiagramContainerEditPart.getContentPane();
                assertTrue(contentPane != null && contentPane.getLayoutManager() instanceof ConstrainedToolbarLayout);
                ConstrainedToolbarLayout ctl = (ConstrainedToolbarLayout) contentPane.getLayoutManager();
                LabelAlignment figureLabelAlignment = getFigureLabelAlignment(ctl.getMinorAlignment());
                LabelAlignment labelAlignment = (LabelAlignment) viewpointStyle.eGet(feature);
                // FIXME VP-2033 : for container with workspaceImage style the
                // labelAlignement is not take into account
                // FIXME : the reset of label alignment from RIGHT to LEFT
                // (because the description has defined LEFT as default label
                // alignment), but on the figure the label is centered
                if (viewpointStyle instanceof WorkspaceImage || viewpointStyle instanceof ShapeContainerStyle) {
                    return;
                }
                assertEquals("The label alignement of the figure should corresponds to the label alignementPath feature value of the customized style", labelAlignment, figureLabelAlignment);
            } else if (feature == DiagramPackage.Literals.FLAT_CONTAINER_STYLE__BACKGROUND_STYLE) {
                // FIXME : customization of backgroundStyle doesn't works

            } else if (feature == DiagramPackage.Literals.ELLIPSE__HORIZONTAL_DIAMETER) {
                // FIXME : customization of horizontalDiameter doesn't works

            } else if (feature == DiagramPackage.Literals.ELLIPSE__VERTICAL_DIAMETER) {
                // FIXME : customization of verticalDiameter doesn't works

            } else if (feature == DiagramPackage.Literals.BUNDLED_IMAGE__SHAPE) {
                BundledImageShape bundledImageShape = (BundledImageShape) viewpointStyle.eGet(feature);
                BundledImageShape figureBundledImageShape = getFigureBundledImageShape(swtBotGefEditPart);
                assertEquals("The BundledImage shape of the figure should corresponds to the BundledImage shape feature value of the customized style", bundledImageShape, figureBundledImageShape);
            } else if (feature == DiagramPackage.Literals.GAUGE_COMPOSITE_STYLE__ALIGNMENT) {
                AlignmentKind alignmentKind = (AlignmentKind) viewpointStyle.eGet(feature);
                AlignmentKind figureAlignmentKind = getFigureAlignmentKind(swtBotGefEditPart);
                assertEquals("The GaugeCompositeStyle aligment of the figure should corresponds to the GaugeCompositeStyle aligment feature value of the customized style", alignmentKind,
                        figureAlignmentKind);
            } else if (feature == DiagramPackage.Literals.WORKSPACE_IMAGE__WORKSPACE_PATH) {
                String workspacePath = (String) viewpointStyle.eGet(feature);
                Image icon = getWorkspaceImage(swtBotGefEditPart);
                Image imageFromStyle = DiagramUIPlugin.Implementation.findImageDescriptor(FileProvider.getDefault().getFile(new Path(workspacePath)).toURI().toURL()).createImage();
                assertTrue("The workspace image of the figure should corresponds to the workspacePath feature value of the customized style", ImageEquality.areEqualImages(imageFromStyle, icon));
            } else if (feature == DiagramPackage.Literals.EDGE_STYLE__LINE_STYLE) {
                LineStyle lineStyle = (LineStyle) viewpointStyle.eGet(feature);
                LineStyle figureLineStyle = getFigureLineStyle(swtBotGefEditPart);
                assertEquals("The line style of the figure should corresponds to the lineStyle feature value of the customized style", lineStyle, figureLineStyle);
            } else if (feature == DiagramPackage.Literals.EDGE_STYLE__SOURCE_ARROW) {
                EdgeArrows sourceArrow = (EdgeArrows) viewpointStyle.eGet(feature);
                EdgeArrows figureSourceArrow = getFigureSourceArrow(swtBotGefEditPart);
                assertEquals("The source arrow of the figure should corresponds to the sourceArrow feature value of the customized style", sourceArrow, figureSourceArrow);
            } else if (feature == DiagramPackage.Literals.EDGE_STYLE__TARGET_ARROW) {
                EdgeArrows sourceArrow = (EdgeArrows) viewpointStyle.eGet(feature);
                EdgeArrows figureSourceArrow = getFigureTargetArrow(swtBotGefEditPart);
                assertEquals("The target arrow of the figure should corresponds to the targetArrow feature value of the customized style", sourceArrow, figureSourceArrow);
            } else if (feature == DiagramPackage.Literals.EDGE_STYLE__FOLDING_STYLE) {
                FoldingStyle foldingStyle = (FoldingStyle) viewpointStyle.eGet(feature);
                FoldingStyle figureFoldingStyle = getFigureFoldingStyle(swtBotGefEditPart);
                assertEquals("The folding style of the figure should corresponds to the foldingStyle feature value of the customized style", foldingStyle, figureFoldingStyle);
            } else if (feature == DiagramPackage.Literals.EDGE_STYLE__SIZE) {
                Integer edgeWidth = (Integer) viewpointStyle.eGet(feature);
                Integer figureEdgeWidth = getFigureEdgeWidth(swtBotGefEditPart);
                assertEquals("The edge width of the figure should corresponds to the size feature value of the customized style", edgeWidth, figureEdgeWidth);
            } else if (feature == DiagramPackage.Literals.EDGE_STYLE__ROUTING_STYLE) {
                EdgeRouting edgeRouting = (EdgeRouting) viewpointStyle.eGet(feature);
                EdgeRouting figureEdgeRouting = getFigureEdgeRouting(swtBotGefEditPart);
                // assertEquals("The edge routing of the figure should
                // corresponds to the edgeRouting feature value of the
                // customized style", edgeRouting, figureEdgeRouting);
                // FIXME : changing the routingStyle:EdgeRouting doesn't update
                // the GMF routing the neither the figure connection router
            } else if (feature == DiagramPackage.Literals.NOTE__COLOR) {
                RGBValues noteColor = (RGBValues) viewpointStyle.eGet(feature);
                RGBValues figureNoteColor = getFigureNoteColor(swtBotGefEditPart);
                SiriusAssert.assertSameRGB("The note color of the figure should corresponds to the note color feature value of the customized style", noteColor, figureNoteColor);
            } else if (feature == DiagramPackage.Literals.BUNDLED_IMAGE__COLOR) {
                RGBValues bundledImageColor = (RGBValues) viewpointStyle.eGet(feature);
                RGBValues figureBundledImageColor = getFigureBundledImageColor(swtBotGefEditPart);
                SiriusAssert.assertSameRGB("The bundledImage color of the figure should corresponds to the bundledImage color feature value of the customized style", bundledImageColor,
                        figureBundledImageColor);
            } else if (feature == DiagramPackage.Literals.DOT__BACKGROUND_COLOR) {
                RGBValues dotBackgroundColor = (RGBValues) viewpointStyle.eGet(feature);
                RGBValues figureDotBackgroundColor = getFigureDotBackgroundColor(swtBotGefEditPart);
                SiriusAssert.assertSameRGB("The dot backgroundColor of the figure should corresponds to the dot backgroundColor feature value of the customized style", dotBackgroundColor,
                        figureDotBackgroundColor);
            }
        }
    }

    private void checkFigureAccordingToGMFStyleProperty(SWTBotGefEditPart swtBotGefEditPart, EStructuralFeature feature) {
        View correspondingView = (View) swtBotGefEditPart.part().getModel();
        org.eclipse.gmf.runtime.notation.Style gmfStyle = correspondingView.getStyle(feature.getEContainingClass());
        if (feature == NotationPackage.Literals.FILL_STYLE__FILL_COLOR) {
            Color backgroundColor = DiagramColorRegistry.getInstance().getColor((Integer) gmfStyle.eGet(feature));
            Color figureBackgroundColor = getFigureBackgroundColor(swtBotGefEditPart);
            assertEquals("The backgroundColor of the figure should corresponds to the fillColor feature value of the customized GMF style", backgroundColor, figureBackgroundColor);
        } else if (feature == NotationPackage.Literals.FILL_STYLE__TRANSPARENCY) {
            int transparency = (Integer) gmfStyle.eGet(feature);
            int figureTransparency = getFigureTransparancy(swtBotGefEditPart);
            assertEquals("The transparency of the figure should corresponds to the transparency feature value of the customized GMF style", transparency, figureTransparency);
        } else if (feature == NotationPackage.Literals.FILL_STYLE__GRADIENT) {
            GradientData gradientData = (GradientData) gmfStyle.eGet(feature);
            GradientData figureGradientData = getFigureGradientData(swtBotGefEditPart);
            assertEquals("The gradient of the figure should corresponds to the gradient feature value of the customized GMF style", gradientData, figureGradientData);
        } else if (feature == NotationPackage.Literals.LINE_STYLE__LINE_COLOR) {
            Color lineColor = DiagramColorRegistry.getInstance().getColor((Integer) gmfStyle.eGet(feature));
            Color figureLineColor = getFigureLineColor(swtBotGefEditPart);
            assertEquals("The lineColor of the figure should corresponds to the lineColor feature value of the customized GMF style", lineColor, figureLineColor);
        } else if (feature == NotationPackage.Literals.LINE_STYLE__LINE_WIDTH) {
            int lineWidth = (Integer) gmfStyle.eGet(feature);
            int figureLineWidth = getFigureLineWidth(swtBotGefEditPart);
            assertEquals("The lineWidth of the figure should corresponds to the lineWidth feature value of the customized GMF style", lineWidth, figureLineWidth);
        } else if (feature == NotationPackage.Literals.FONT_STYLE__FONT_COLOR) {
            Color fontColor = DiagramColorRegistry.getInstance().getColor((Integer) gmfStyle.eGet(feature));
            Color figureFontColor = getFigureFontColor(swtBotGefEditPart);
            assertEquals("The fontColor of the figure should corresponds to the fontColor feature value of the customized GMF style", fontColor, figureFontColor);
        } else if (feature == NotationPackage.Literals.FONT_STYLE__FONT_NAME) {
            String fontName = (String) gmfStyle.eGet(feature);
            String figureFontName = getFigureFontName(swtBotGefEditPart);
            assertEquals("The fontName of the figure should corresponds to the fontName feature value of the customized GMF style", fontName, figureFontName);
        } else if (feature == NotationPackage.Literals.FONT_STYLE__FONT_HEIGHT) {
            int fontHeight = (Integer) gmfStyle.eGet(feature);
            int figureFontHeight = getFigureFontHeight(swtBotGefEditPart);
            assertEquals("The fontHeight of the figure should corresponds to the fontHeight feature value of the customized GMF style", fontHeight, figureFontHeight);
        } else if (feature == NotationPackage.Literals.FONT_STYLE__BOLD) {
            boolean isBold = (Boolean) gmfStyle.eGet(feature);
            boolean figureBoldState = hasFigureFontBold(swtBotGefEditPart);
            assertEquals("The bold state of the figure font should corresponds to the bold feature value of the customized GMF style", isBold, figureBoldState);
        } else if (feature == NotationPackage.Literals.FONT_STYLE__ITALIC) {
            boolean isItalic = (Boolean) gmfStyle.eGet(feature);
            boolean figureItalicState = hasFigureFontItalic(swtBotGefEditPart);
            assertEquals("The italic state of the figure font should corresponds to the italic feature value of the customized GMF style", isItalic, figureItalicState);
        } else if (feature == NotationPackage.Literals.FONT_STYLE__UNDERLINE) {
            boolean isUnderline = (Boolean) gmfStyle.eGet(feature);
            boolean figureUnderlineState = hasFigureFontUnderline(swtBotGefEditPart);
            assertEquals("The underline state of the figure font should corresponds to the underline feature value of the customized GMF style", isUnderline, figureUnderlineState);
        } else if (feature == NotationPackage.Literals.FONT_STYLE__STRIKE_THROUGH) {
            boolean isStrikeThrough = (Boolean) gmfStyle.eGet(feature);
            boolean figureStrikeThroughState = hasFigureFontStrikeThrough(swtBotGefEditPart);
            assertEquals("The strikeThrough state of the figure font should corresponds to the strikeThrough feature value of the customized GMF style", isStrikeThrough, figureStrikeThroughState);
        } else if (feature == NotationPackage.Literals.TITLE_STYLE__SHOW_TITLE) {
            boolean isShowTitle = (Boolean) gmfStyle.eGet(feature);
            boolean figureShowTitleState = hasFigureShowTitle(swtBotGefEditPart);
            assertEquals("The showTitle state of the figure font should corresponds to the showTitle feature value of the customized GMF style", isShowTitle, figureShowTitleState);
        } else if (feature == NotationPackage.Literals.TEXT_STYLE__TEXT_ALIGNMENT) {
            TextAlignment textAlignment = (TextAlignment) gmfStyle.eGet(feature);
            TextAlignment figureTextAlignment = getFigureTextAlignment(swtBotGefEditPart);
            // FIXME : TextAlignment change doesn't works
            // assertEquals("The textAlignment of the figure font should
            // corresponds to the textAlignment feature value of the customized
            // GMF style", textAlignment, figureTextAlignment);
        } else if (feature == NotationPackage.Literals.DESCRIPTION_STYLE__DESCRIPTION) {
            String description = (String) gmfStyle.eGet(feature);
            String figureDescription = getFigureNoteDescription(swtBotGefEditPart);
            assertEquals("The description of the NoteFigure should corresponds to the description feature value of the customized GMF Note", description, figureDescription);
        } else if (feature == NotationPackage.Literals.LINE_TYPE_STYLE__LINE_TYPE) {
            LineType lineType = (LineType) gmfStyle.eGet(feature);
            LineType figureLineType = getFigureLineType(swtBotGefEditPart);
            assertEquals("The lineType of the figure should corresponds to the lineType feature value of the customized GMF Note", lineType, figureLineType);
        } else if (feature == NotationPackage.Literals.ARROW_STYLE__ARROW_SOURCE) {
            ArrowType sourceArrowType = (ArrowType) gmfStyle.eGet(feature);
            ArrowType figureArrowType = getFigureArrowType(swtBotGefEditPart, true);
            assertEquals("The arrowSource of the figure should corresponds to the arrowSource feature value of the customized GMF Note", sourceArrowType, figureArrowType);
        } else if (feature == NotationPackage.Literals.ARROW_STYLE__ARROW_TARGET) {
            ArrowType sourceArrowType = (ArrowType) gmfStyle.eGet(feature);
            ArrowType figureArrowType = getFigureArrowType(swtBotGefEditPart, false);
            assertEquals("The arrowTarget of the figure should corresponds to the arrowTarget feature value of the customized GMF Note", sourceArrowType, figureArrowType);
        }
    }

    protected void selectAndRefreshDiagram() {
        editor.scrollTo(0, 0);
        editor.click(5, 5);
        SWTBotUtils.waitAllUiEvents();
        manualRefresh();
    }

    protected DDiagramElement getDDiagramElement(SWTBotGefEditPart swtBotGefEditPart) {
        DDiagramElement dDiagramElement = null;
        EditPart editPart = swtBotGefEditPart.part();
        if (editPart instanceof AbstractDiagramNodeEditPart) {
            AbstractDiagramNodeEditPart abstractDiagramNodeEditPart = (AbstractDiagramNodeEditPart) editPart;
            dDiagramElement = abstractDiagramNodeEditPart.resolveDiagramElement();
        } else if (editPart instanceof AbstractDiagramEdgeEditPart) {
            AbstractDiagramEdgeEditPart abstractDiagramEdgeEditPart = (AbstractDiagramEdgeEditPart) editPart;
            dDiagramElement = abstractDiagramEdgeEditPart.resolveDiagramElement();
        } else if (editPart instanceof AbstractDiagramContainerEditPart) {
            AbstractDiagramContainerEditPart abstractDiagramContainerEditPart = (AbstractDiagramContainerEditPart) editPart;
            dDiagramElement = abstractDiagramContainerEditPart.resolveDiagramElement();
        }
        return dDiagramElement;
    }

    Enumerator getNewValueLiteral(Object value, Collection<?> choiceOfValues) {
        Enumerator newValueLiteral = null;
        boolean found = false;
        for (Object choice : choiceOfValues) {
            if (found && choice instanceof Enumerator) {
                newValueLiteral = (Enumerator) choice;
                break;
            }
            if (choice == value) {
                found = true;
            }
        }
        if (newValueLiteral == null) {
            newValueLiteral = (Enumerator) choiceOfValues.iterator().next();
        }
        return newValueLiteral;
    }

    RGBValues getFigureDotBackgroundColor(SWTBotGefEditPart swtBotGefEditPart) {
        DotEditPart dotEditPart = (DotEditPart) swtBotGefEditPart.descendants(IsInstanceOf.instanceOf(DotEditPart.class)).get(0).part();
        DotFigure primaryShape = dotEditPart.getPrimaryShape();
        Color backgroundColor = primaryShape.getBackgroundColor();
        RGBValues figureDotBackgroundColor = RGBValues.create(backgroundColor.getRed(), backgroundColor.getGreen(), backgroundColor.getBlue());
        return figureDotBackgroundColor;
    }

    Color getFigureBackgroundColor(SWTBotGefEditPart swtBotGefEditPart) {
        BackgroundColorFigureGetter backgroundColorFigureGetter = new BackgroundColorFigureGetter(((IGraphicalEditPart) swtBotGefEditPart.part()).getFigure());
        Display.getDefault().syncExec(backgroundColorFigureGetter);
        Color backgroundColor = backgroundColorFigureGetter.getResult();
        return backgroundColor;
    }

    private Color getFigureLineColor(SWTBotGefEditPart swtBotGefEditPart) {
        Color figureLineColor = ((IGraphicalEditPart) swtBotGefEditPart.part()).getFigure().getForegroundColor();
        return figureLineColor;
    }

    private int getFigureLineWidth(SWTBotGefEditPart swtBotGefEditPart) {
        int figureLineWidth = -1;
        if (swtBotGefEditPart.part() instanceof ConnectionEditPart) {
            ConnectionEditPart connectionEditPart = (ConnectionEditPart) swtBotGefEditPart.part();
            figureLineWidth = ((Shape) connectionEditPart.getConnectionFigure()).getLineWidth();
            // lineWidth of 1 in draw2d is equivalent to -1 in model
            if (figureLineWidth == 1) {
                figureLineWidth = -1;
            }
        } else if (swtBotGefEditPart.part() instanceof org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart) {
            org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart shapeNodeEditPart = (org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart) swtBotGefEditPart.part();
            NodeFigure nodeFigure = (NodeFigure) shapeNodeEditPart.getFigure();
            figureLineWidth = nodeFigure.getLineWidth();
        } else {
            List<SWTBotGefEditPart> resizableCompartmentEditPartBots = swtBotGefEditPart.descendants(IsInstanceOf.instanceOf(ResizableCompartmentEditPart.class));
            assertTrue(!resizableCompartmentEditPartBots.isEmpty());
            ResizableCompartmentEditPart resizableCompartmentEditPart = (ResizableCompartmentEditPart) resizableCompartmentEditPartBots.get(0).part();
            Border border = resizableCompartmentEditPart.getFigure().getBorder();
            assertTrue(border instanceof LineBorder);
            LineBorder lineBorder = (LineBorder) border;
            figureLineWidth = lineBorder.getWidth();
        }

        return figureLineWidth;
    }

    private ArrowType getFigureArrowType(SWTBotGefEditPart swtBotGefEditPart, boolean source) {
        ArrowType figureArrowType = null;
        if (swtBotGefEditPart.part() instanceof ConnectionEditPart) {
            ConnectionEditPart connectionEditPart = (ConnectionEditPart) swtBotGefEditPart.part();
            Connection connectionFigure = connectionEditPart.getConnectionFigure();
            Optional<Object> rotatableDecoration = ReflectionHelper.getFieldValueWithoutException(connectionFigure, (source ? "startDecoration" : "endDecoration"));
            if (!rotatableDecoration.isPresent()) {
                figureArrowType = ArrowType.NONE_LITERAL;
            } else if (rotatableDecoration.get() instanceof PolygonDecoration) {
                figureArrowType = ArrowType.SOLID_ARROW_LITERAL;
            } else if (rotatableDecoration.get() instanceof PolylineDecoration) {
                figureArrowType = ArrowType.OPEN_ARROW_LITERAL;

            }
        }
        return figureArrowType;
    }

    private TextAlignment getFigureTextAlignment(SWTBotGefEditPart swtBotGefEditPart) {
        TextAlignment figureTextAlignment = null;
        List<SWTBotGefEditPart> descendants = swtBotGefEditPart.descendants(IsInstanceOf.instanceOf(TextCompartmentEditPart.class));
        TextCompartmentEditPart textCompartmentEditPart = (TextCompartmentEditPart) descendants.get(0).part();
        int textJustification = textCompartmentEditPart.getLabelDelegate().getTextJustification();
        switch (textJustification) {
        case PositionConstants.RIGHT:
            figureTextAlignment = TextAlignment.RIGHT_LITERAL;
            break;
        case PositionConstants.CENTER:
            figureTextAlignment = TextAlignment.CENTER_LITERAL;
            break;
        case PositionConstants.LEFT:
            figureTextAlignment = TextAlignment.LEFT_LITERAL;
            break;
        }
        return figureTextAlignment;
    }

    private Color getFigureFontColor(SWTBotGefEditPart swtBotGefEditPart) {
        List<SWTBotGefEditPart> descendants = swtBotGefEditPart.descendants(IsInstanceOf.instanceOf(ITextAwareEditPart.class));
        assertTrue(!descendants.isEmpty());
        SWTBotGefEditPart textEditPartBot = descendants.get(0);
        Color figureFontColor = ((IGraphicalEditPart) textEditPartBot.part()).getFigure().getForegroundColor();
        return figureFontColor;
    }

    private String getFigureFontName(SWTBotGefEditPart swtBotGefEditPart) {
        String figureFontName = ((IGraphicalEditPart) swtBotGefEditPart.part()).getFigure().getFont().getFontData()[0].getName();
        return figureFontName;
    }

    private int getFigureFontHeight(SWTBotGefEditPart swtBotGefEditPart) {
        int figureFontHeight = ((IGraphicalEditPart) swtBotGefEditPart.part()).getFigure().getFont().getFontData()[0].getHeight();
        return figureFontHeight;
    }

    private boolean hasFigureFontBold(SWTBotGefEditPart swtBotGefEditPart) {
        boolean hasFigureFontBold = (((IGraphicalEditPart) swtBotGefEditPart.part()).getFigure().getFont().getFontData()[0].getStyle() & SWT.BOLD) != 0;
        return hasFigureFontBold;
    }

    private boolean hasFigureFontItalic(SWTBotGefEditPart swtBotGefEditPart) {
        boolean hasFigureFontItalic = (((IGraphicalEditPart) swtBotGefEditPart.part()).getFigure().getFont().getFontData()[0].getStyle() & SWT.ITALIC) != 0;
        return hasFigureFontItalic;
    }

    private boolean hasFigureFontUnderline(SWTBotGefEditPart swtBotGefEditPart) {
        boolean hasFigureFontUnderline = false;
        List<SWTBotGefEditPart> descendants = swtBotGefEditPart.descendants(IsInstanceOf.instanceOf(ITextAwareEditPart.class));
        assertTrue(!descendants.isEmpty());
        SWTBotGefEditPart textEditPartBot = descendants.get(0);
        WrappingLabel wrappingLabel = (WrappingLabel) ((IGraphicalEditPart) textEditPartBot.part()).getFigure();
        hasFigureFontUnderline = wrappingLabel.isTextUnderlined();
        return hasFigureFontUnderline;
    }

    private boolean hasFigureFontStrikeThrough(SWTBotGefEditPart swtBotGefEditPart) {
        boolean hasFigureFontStrikeThrough = false;
        List<SWTBotGefEditPart> descendants = swtBotGefEditPart.descendants(IsInstanceOf.instanceOf(ITextAwareEditPart.class));
        assertTrue(!descendants.isEmpty());
        SWTBotGefEditPart textEditPartBot = descendants.get(0);
        WrappingLabel wrappingLabel = (WrappingLabel) ((IGraphicalEditPart) textEditPartBot.part()).getFigure();
        hasFigureFontStrikeThrough = wrappingLabel.isTextStrikedThrough();
        return hasFigureFontStrikeThrough;
    }

    private boolean hasFigureShowTitle(SWTBotGefEditPart swtBotGefEditPart) {
        boolean hasFigureShowTitle = false;
        List<SWTBotGefEditPart> resizableCompartmentEditPartBots = swtBotGefEditPart.descendants(IsInstanceOf.instanceOf(ResizableCompartmentEditPart.class));
        assertTrue(!resizableCompartmentEditPartBots.isEmpty());
        ResizableCompartmentEditPart resizableCompartmentEditPart = (ResizableCompartmentEditPart) resizableCompartmentEditPartBots.get(0).part();
        hasFigureShowTitle = resizableCompartmentEditPart.getCompartmentFigure().getTextPane().isVisible();
        return hasFigureShowTitle;
    }

    private LineType getFigureLineType(SWTBotGefEditPart swtBotGefEditPart) {
        LineType figureLineType = null;
        int lineStyle = -1;
        if (swtBotGefEditPart.part() instanceof org.eclipse.gmf.runtime.diagram.ui.editparts.NoteEditPart) {
            org.eclipse.gmf.runtime.diagram.ui.editparts.NoteEditPart noteEditPart = (org.eclipse.gmf.runtime.diagram.ui.editparts.NoteEditPart) swtBotGefEditPart.part();
            NoteFigure noteFigure = (NoteFigure) noteEditPart.getFigure();
            lineStyle = noteFigure.getLineStyle();
        } else if (swtBotGefEditPart.part() instanceof NoteAttachmentEditPart) {
            NoteAttachmentEditPart noteAttachmentEditPart = (NoteAttachmentEditPart) swtBotGefEditPart.part();
            Shape shape = (Shape) noteAttachmentEditPart.getConnectionFigure();
            lineStyle = shape.getLineStyle();
        }
        switch (lineStyle) {
        case Graphics.LINE_SOLID:
            figureLineType = LineType.SOLID_LITERAL;
            break;
        case Graphics.LINE_DASH:
            figureLineType = LineType.DASH_LITERAL;
            break;
        case Graphics.LINE_DOT:
            figureLineType = LineType.DOT_LITERAL;
            break;
        case Graphics.LINE_DASHDOT:
            figureLineType = LineType.DASH_DOT_LITERAL;
            break;
        case Graphics.LINE_DASHDOTDOT:
            figureLineType = LineType.DASH_DOT_DOT_LITERAL;
            break;
        }
        return figureLineType;
    }

    private String getFigureNoteDescription(SWTBotGefEditPart swtBotGefEditPart) {
        String figureNoteDescription = null;
        List<SWTBotGefEditPart> descendants = swtBotGefEditPart.descendants(IsInstanceOf.instanceOf(DescriptionCompartmentEditPart.class));
        assertTrue(!descendants.isEmpty());
        SWTBotGefEditPart descriptionCompartmentEditPartBot = descendants.get(0);
        WrappingLabel wrappingLabel = (WrappingLabel) ((IGraphicalEditPart) descriptionCompartmentEditPartBot.part()).getFigure();
        figureNoteDescription = wrappingLabel.getText();
        return figureNoteDescription;
    }

    int getFigureTransparancy(SWTBotGefEditPart swtBotGefEditPart) {
        int figureTransparancy = -1;
        IFigure figure = ((IGraphicalEditPart) swtBotGefEditPart.part()).getFigure();
        assertTrue("The transparency apply only on NodeFigure", figure instanceof NodeFigure);
        NodeFigure nodeFigure = (NodeFigure) figure;
        figureTransparancy = nodeFigure.getTransparency();
        return figureTransparancy;
    }

    GradientData getFigureGradientData(SWTBotGefEditPart swtBotGefEditPart) {
        GradientData figureGradientData = null;
        IFigure figure = ((IGraphicalEditPart) swtBotGefEditPart.part()).getFigure();
        assertTrue("The transparency apply only on NodeFigure", figure instanceof NodeFigure);
        NodeFigure nodeFigure = (NodeFigure) figure;
        NodeFigureGradientDataGetter nodeFigureGradientDataGetter = new NodeFigureGradientDataGetter(nodeFigure);
        Display.getDefault().syncExec(nodeFigureGradientDataGetter);
        figureGradientData = nodeFigureGradientDataGetter.getResult();
        return figureGradientData;
    }

    RGBValues getFigureBundledImageColor(SWTBotGefEditPart swtBotGefEditPart) {
        RGBValues figureBundledImageColor = null;
        BundledImageEditPart bundledImageEditPart = (BundledImageEditPart) swtBotGefEditPart.descendants(IsInstanceOf.instanceOf(BundledImageEditPart.class)).get(0).part();
        BundledImageFigure primaryShape = bundledImageEditPart.getPrimaryShape();
        String mainGradientColor = (String) ReflectionHelper.getFieldValueWithoutException(primaryShape, "mainGradientColor").get();
        if (mainGradientColor.length() == 6) {
            String redHexa = mainGradientColor.substring(0, 2);
            String greenHexa = mainGradientColor.substring(2, 4);
            String blueHexa = mainGradientColor.substring(4, 6);
            figureBundledImageColor = RGBValues.create(Integer.parseInt(redHexa, 16), Integer.parseInt(greenHexa, 16), Integer.parseInt(blueHexa, 16));
        }
        return figureBundledImageColor;
    }

    RGBValues getFigureNoteColor(SWTBotGefEditPart swtBotGefEditPart) {
        NoteEditPart noteEditPart = (NoteEditPart) swtBotGefEditPart.descendants(IsInstanceOf.instanceOf(NoteEditPart.class)).get(0).part();
        NoteFigure primaryShape = noteEditPart.getPrimaryShape();
        Color backgroundColor = primaryShape.getBackgroundColor();
        RGBValues figureNoteColor = RGBValues.create(backgroundColor.getRed(), backgroundColor.getGreen(), backgroundColor.getBlue());
        return figureNoteColor;
    }

    EdgeRouting getFigureEdgeRouting(SWTBotGefEditPart swtBotGefEditPart) {
        EdgeRouting figureEdgeRouting = null;
        DEdgeEditPart dEdgeEditPart = (DEdgeEditPart) swtBotGefEditPart.part();
        ViewEdgeFigure primaryShape = dEdgeEditPart.getPrimaryShape();
        ConnectionRouter connectionRouter = primaryShape.getConnectionRouter();
        if (connectionRouter instanceof RectilinearRouter) {
            figureEdgeRouting = EdgeRouting.MANHATTAN_LITERAL;
        } else if (connectionRouter instanceof DForestRouter) {
            figureEdgeRouting = EdgeRouting.STRAIGHT_LITERAL;
        } else if (connectionRouter instanceof DTreeRouter) {
            figureEdgeRouting = EdgeRouting.TREE_LITERAL;
        }
        return figureEdgeRouting;
    }

    Integer getFigureEdgeWidth(SWTBotGefEditPart swtBotGefEditPart) {
        Integer figureEdgeWidth = null;
        AbstractDiagramEdgeEditPart abstractDiagramEdgeEditPart = (AbstractDiagramEdgeEditPart) swtBotGefEditPart.part();
        ViewEdgeFigure primaryShape = (ViewEdgeFigure) abstractDiagramEdgeEditPart.getFigure();
        figureEdgeWidth = primaryShape.getLineWidth();
        return figureEdgeWidth;
    }

    FoldingStyle getFigureFoldingStyle(SWTBotGefEditPart swtBotGefEditPart) throws Exception {
        FoldingStyle figureFoldingStyle = null;
        Thread.sleep(1000);
        editor.reveal(swtBotGefEditPart.part());
        AbstractDiagramEdgeEditPart abstractDiagramEdgeEditPart = (AbstractDiagramEdgeEditPart) swtBotGefEditPart.part();
        GraphicalEditPart sourceEditPart = (GraphicalEditPart) abstractDiagramEdgeEditPart.getSource();
        GraphicalEditPart targetEditPart = (GraphicalEditPart) abstractDiagramEdgeEditPart.getTarget();
        BorderedNodeFigure sourceFigure = (BorderedNodeFigure) sourceEditPart.getFigure();
        BorderedNodeFigure targetFigure = (BorderedNodeFigure) targetEditPart.getFigure();
        FoldingToggleImageFigure sourceFoldingToggleImageFigure = (FoldingToggleImageFigure) sourceFigure.getBorderItemContainer().getChildren().get(0);
        FoldingToggleImageFigure targetFoldingToggleImageFigure = (FoldingToggleImageFigure) targetFigure.getBorderItemContainer().getChildren().get(0);
        Image sourceImageWFocus = sourceFoldingToggleImageFigure.getImageWFocus();
        Image targetImageWFocus = targetFoldingToggleImageFigure.getImageWFocus();
        if (sourceImageWFocus == null && targetImageWFocus == null) {
            figureFoldingStyle = FoldingStyle.NONE_LITERAL;
        } else if (sourceImageWFocus != null) {
            figureFoldingStyle = FoldingStyle.SOURCE_LITERAL;
        } else if (targetImageWFocus != null) {
            figureFoldingStyle = FoldingStyle.TARGET_LITERAL;
        }
        return figureFoldingStyle;
    }

    EdgeArrows getFigureSourceArrow(SWTBotGefEditPart swtBotGefEditPart) {
        editor.reveal(swtBotGefEditPart.part());
        EdgeArrows figureSourceArrow = null;
        AbstractDiagramEdgeEditPart abstractDiagramEdgeEditPart = (AbstractDiagramEdgeEditPart) swtBotGefEditPart.part();
        ViewEdgeFigure primaryShape = (ViewEdgeFigure) abstractDiagramEdgeEditPart.getFigure();
        RotatableDecoration sourceDecoration = primaryShape.getSourceDecoration();
        ConnectionAnchor sourceAnchor = primaryShape.getSourceAnchor();
        ConnectionAnchor targetAnchor = primaryShape.getTargetAnchor();
        Rectangle sourceOwnerBounds = sourceAnchor.getOwner().getBounds();
        Point location = sourceAnchor.getLocation(targetAnchor.getReferencePoint());
        sourceAnchor.getOwner().translateToRelative(location);
        Direction direction = DirectionUtil.getDirection(location, sourceOwnerBounds.getCenter());
        if (abstractDiagramEdgeEditPart instanceof BracketEdgeEditPart) {
            figureSourceArrow = getBracketFigureArrow(primaryShape, sourceDecoration, direction, location, sourceOwnerBounds, BracketConnectionQuery.ORIGIN_POINT_INDEX);
        } else {
            figureSourceArrow = getFigureArrow(sourceDecoration, direction, location, sourceOwnerBounds, BracketConnectionQuery.ORIGIN_POINT_INDEX);
        }
        return figureSourceArrow;
    }

    EdgeArrows getFigureTargetArrow(SWTBotGefEditPart swtBotGefEditPart) {
        editor.reveal(swtBotGefEditPart.part());
        EdgeArrows figureTargetArrow = null;
        AbstractDiagramEdgeEditPart abstractDiagramEdgeEditPart = (AbstractDiagramEdgeEditPart) swtBotGefEditPart.part();
        ViewEdgeFigure primaryShape = (ViewEdgeFigure) abstractDiagramEdgeEditPart.getFigure();
        ConnectionAnchor sourceAnchor = primaryShape.getSourceAnchor();
        ConnectionAnchor targetAnchor = primaryShape.getTargetAnchor();
        Rectangle targetOwnerBounds = targetAnchor.getOwner().getBounds();
        Point location = targetAnchor.getLocation(sourceAnchor.getReferencePoint());
        targetAnchor.getOwner().translateToRelative(location);
        Direction direction = DirectionUtil.getDirection(location, targetOwnerBounds.getCenter());
        RotatableDecoration targetDecoration = primaryShape.getTargetDecoration();
        if (abstractDiagramEdgeEditPart instanceof BracketEdgeEditPart) {
            figureTargetArrow = getBracketFigureArrow(primaryShape, targetDecoration, direction, location, targetOwnerBounds, BracketConnectionQuery.TARGET_POINT_INDEX);
        } else {
            figureTargetArrow = getFigureArrow(targetDecoration, direction, location, targetOwnerBounds, BracketConnectionQuery.TARGET_POINT_INDEX);
        }
        return figureTargetArrow;
    }

    private EdgeArrows getFigureArrow(RotatableDecoration decoration, Direction direction, Point location, Rectangle ownerBounds, int pointIndex) {
        EdgeArrows figureArrow = null;
        if (decoration == null) {
            figureArrow = EdgeArrows.NO_DECORATION_LITERAL;
        } else if (decoration instanceof PolygonDecoration) {
            PolygonDecoration polygonDecoration = (PolygonDecoration) decoration;
            PointList points = polygonDecoration.getPoints();
            if (points.size() == 3) {
                if (pointIndex == BracketConnectionQuery.TARGET_POINT_INDEX) {
                    if (ownerBounds.contains(points.getFirstPoint()) && ownerBounds.contains(points.getPoint(1)) && !ownerBounds.contains(points.getLastPoint())) {
                        figureArrow = EdgeArrows.OUTPUT_CLOSED_ARROW_LITERAL;
                    } else if (points.getFirstPoint().x == points.getLastPoint().x) {
                        figureArrow = EdgeArrows.INPUT_CLOSED_ARROW_LITERAL;
                    } else if (points.getFirstPoint().x == points.getLastPoint().x) {
                        figureArrow = EdgeArrows.OUTPUT_FILL_CLOSED_ARROW_LITERAL;
                    } else if (points.getFirstPoint().x == points.getLastPoint().x) {
                        figureArrow = EdgeArrows.INPUT_FILL_CLOSED_ARROW_LITERAL;
                    }
                } else if (pointIndex == BracketConnectionQuery.ORIGIN_POINT_INDEX) {
                    if (ownerBounds.contains(points.getFirstPoint()) && ownerBounds.contains(points.getPoint(1)) && !ownerBounds.contains(points.getLastPoint())) {
                        figureArrow = EdgeArrows.OUTPUT_CLOSED_ARROW_LITERAL;
                    } else if (points.getFirstPoint().x == points.getLastPoint().x) {
                        figureArrow = EdgeArrows.INPUT_CLOSED_ARROW_LITERAL;
                    } else if (points.getFirstPoint().x == points.getLastPoint().x) {
                        figureArrow = EdgeArrows.OUTPUT_FILL_CLOSED_ARROW_LITERAL;
                    } else if (points.getFirstPoint().x == points.getLastPoint().x) {
                        figureArrow = EdgeArrows.INPUT_FILL_CLOSED_ARROW_LITERAL;
                    }
                }
            } else if (points.size() == 4) {
                if (points.getFirstPoint().x == points.getLastPoint().x) {
                    figureArrow = EdgeArrows.DIAMOND_LITERAL;
                } else if (points.getFirstPoint().x == points.getLastPoint().x) {
                    figureArrow = EdgeArrows.FILL_DIAMOND_LITERAL;
                }
            }
        } else if (decoration instanceof PolylineDecoration) {
            PolylineDecoration polylineDecoration = (PolylineDecoration) decoration;
            PointList points = polylineDecoration.getPoints();
            if (points.size() == 3) {
                if (ownerBounds.contains(points.getPoint(1)) && !ownerBounds.contains(points.getFirstPoint()) && !ownerBounds.contains(points.getLastPoint())) {
                    figureArrow = EdgeArrows.INPUT_ARROW_LITERAL;
                } else if (points.getPoint(1).equals(location) && ownerBounds.contains(points.getFirstPoint()) && ownerBounds.contains(points.getLastPoint())) {
                    figureArrow = EdgeArrows.OUTPUT_ARROW_LITERAL;
                }
            }
        } else if (decoration instanceof PolygoneAndPolylineDecoraction) {
            if (decoration.getBackgroundColor() == ColorConstants.white) {
                figureArrow = EdgeArrows.INPUT_ARROW_WITH_DIAMOND_LITERAL;
            } else if (decoration.getBackgroundColor() == ColorConstants.black) {
                figureArrow = EdgeArrows.INPUT_ARROW_WITH_FILL_DIAMOND_LITERAL;
            }
        }
        return figureArrow;
    }

    private EdgeArrows getBracketFigureArrow(ViewEdgeFigure primaryShape, RotatableDecoration decoration, Direction direction, Point location, Rectangle ownerBounds, int pointIndex) {
        EdgeArrows figureArrow = null;
        PointList connectionPoints = primaryShape.getPoints();
        if (decoration == null) {
            figureArrow = EdgeArrows.NO_DECORATION_LITERAL;
        } else if (decoration instanceof PolygonDecoration) {
            PolygonDecoration polygonDecoration = (PolygonDecoration) decoration;
            PointList points = polygonDecoration.getPoints();
            if (points.size() == 3) {
                if (connectionPoints.getPoint(pointIndex).x == points.getPoint(0).x && connectionPoints.getPoint(pointIndex).x == points.getPoint(1).x
                        && connectionPoints.getPoint(pointIndex).y == points.getPoint(2).y) {
                    figureArrow = EdgeArrows.OUTPUT_CLOSED_ARROW_LITERAL;
                } else if (connectionPoints.getPoint(pointIndex).x == points.getPoint(0).x && connectionPoints.getPoint(pointIndex).x == points.getPoint(1).x
                        && connectionPoints.getPoint(pointIndex).y == points.getPoint(2).y) {
                    figureArrow = EdgeArrows.INPUT_CLOSED_ARROW_LITERAL;
                } else if (points.getFirstPoint().x == points.getLastPoint().x) {
                    figureArrow = EdgeArrows.OUTPUT_FILL_CLOSED_ARROW_LITERAL;
                } else if (points.getFirstPoint().x == points.getLastPoint().x) {
                    figureArrow = EdgeArrows.INPUT_FILL_CLOSED_ARROW_LITERAL;
                }
            } else if (points.size() == 4) {
                if (points.getFirstPoint().x == points.getLastPoint().x) {
                    figureArrow = EdgeArrows.DIAMOND_LITERAL;
                } else if (points.getFirstPoint().x == points.getLastPoint().x) {
                    figureArrow = EdgeArrows.FILL_DIAMOND_LITERAL;
                }
            }
        } else if (decoration instanceof PolylineDecoration) {
            PolylineDecoration polylineDecoration = (PolylineDecoration) decoration;
            PointList points = polylineDecoration.getPoints();
            if (points.size() == 3) {
                if (pointIndex == BracketConnectionQuery.TARGET_POINT_INDEX) {
                    if (connectionPoints.getPoint(pointIndex).equals(points.getPoint(1)) && connectionPoints.getPoint(pointIndex).x < points.getFirstPoint().x
                            && connectionPoints.getPoint(pointIndex).x < points.getLastPoint().x) {
                        figureArrow = EdgeArrows.INPUT_ARROW_LITERAL;
                    } else if (connectionPoints.getPoint(pointIndex).equals(points.getPoint(1)) && connectionPoints.getPoint(pointIndex).x > points.getFirstPoint().x
                            && connectionPoints.getPoint(pointIndex).x > points.getLastPoint().x) {
                        figureArrow = EdgeArrows.OUTPUT_ARROW_LITERAL;
                    }
                } else if (pointIndex == BracketConnectionQuery.ORIGIN_POINT_INDEX) {
                    if (connectionPoints.getPoint(pointIndex).equals(points.getPoint(1)) && connectionPoints.getPoint(pointIndex).x > points.getFirstPoint().x
                            && connectionPoints.getPoint(pointIndex).x > points.getLastPoint().x) {
                        figureArrow = EdgeArrows.INPUT_ARROW_LITERAL;
                    } else if (connectionPoints.getPoint(pointIndex).equals(points.getPoint(1)) && connectionPoints.getPoint(pointIndex).x < points.getFirstPoint().x
                            && connectionPoints.getPoint(pointIndex).x < points.getLastPoint().x) {
                        figureArrow = EdgeArrows.OUTPUT_ARROW_LITERAL;
                    }
                }
            }
        } else if (decoration instanceof PolygoneAndPolylineDecoraction) {
            if (decoration.getBackgroundColor() == ColorConstants.white) {
                figureArrow = EdgeArrows.INPUT_ARROW_WITH_DIAMOND_LITERAL;
            } else if (decoration.getBackgroundColor() == ColorConstants.black) {
                figureArrow = EdgeArrows.INPUT_ARROW_WITH_FILL_DIAMOND_LITERAL;
            }
        }
        return figureArrow;
    }

    LineStyle getFigureLineStyle(SWTBotGefEditPart swtBotGefEditPart) {
        LineStyle figureLineStyle = null;
        AbstractDiagramEdgeEditPart abstractDiagramEdgeEditPart = (AbstractDiagramEdgeEditPart) swtBotGefEditPart.part();
        ViewEdgeFigure primaryShape = (ViewEdgeFigure) abstractDiagramEdgeEditPart.getFigure();
        int lineStyle = primaryShape.getLineStyle();
        switch (lineStyle) {
        case SWT.LINE_SOLID:
            figureLineStyle = LineStyle.SOLID_LITERAL;
            break;
        case SWT.LINE_CUSTOM:
            figureLineStyle = LineStyle.DASH_LITERAL;
            break;
        case SWT.LINE_DOT:
            figureLineStyle = LineStyle.DOT_LITERAL;
            break;
        case SWT.LINE_DASHDOT:
            figureLineStyle = LineStyle.DASH_DOT_LITERAL;
            break;
        default:
            figureLineStyle = LineStyle.SOLID_LITERAL;
            break;
        }
        return figureLineStyle;
    }

    Image getWorkspaceImage(SWTBotGefEditPart swtBotGefEditPart) {
        Image workspaceImage = null;
        List<SWTBotGefEditPart> descendants = swtBotGefEditPart.descendants(IsInstanceOf.instanceOf(WorkspaceImageEditPart.class));
        if (!descendants.isEmpty()) {
            SWTBotGefEditPart workspaceImageEditPartBot = descendants.get(0);
            WorkspaceImageEditPart workspaceImageEditPart = (WorkspaceImageEditPart) workspaceImageEditPartBot.part();
            ImageFigure primaryShape = (ImageFigure) workspaceImageEditPart.getPrimaryShape();
            workspaceImage = primaryShape.getImage();
        } else if (swtBotGefEditPart.part() instanceof AbstractDiagramContainerEditPart) {
            AbstractDiagramContainerEditPart abstractDiagramContainerEditPart = (AbstractDiagramContainerEditPart) swtBotGefEditPart.part();
            ImageFigure backgroundFigure = (ImageFigure) abstractDiagramContainerEditPart.getBackgroundFigure();
            workspaceImage = backgroundFigure.getImage();
        }
        return workspaceImage;
    }

    AlignmentKind getFigureAlignmentKind(SWTBotGefEditPart swtBotGefEditPart) throws Exception {
        AlignmentKind figureAlignmentKind = null;
        List<SWTBotGefEditPart> descendants = swtBotGefEditPart.descendants(IsInstanceOf.instanceOf(GaugeCompositeEditPart.class));
        if (!descendants.isEmpty()) {
            SWTBotGefEditPart gaugeCompositeEditPartBot = descendants.get(0);
            GaugeCompositeEditPart gaugeCompositeEditPart = (GaugeCompositeEditPart) gaugeCompositeEditPartBot.part();
            GaugeCompositeFigure primaryShape = gaugeCompositeEditPart.getPrimaryShape();
            Object object = ReflectionHelper.getFieldValueWithoutException(primaryShape, "alignment").get();
            assertTrue(object instanceof String);
            figureAlignmentKind = AlignmentKind.getByName((String) object);
        }
        return figureAlignmentKind;
    }

    BundledImageShape getFigureBundledImageShape(SWTBotGefEditPart swtBotGefEditPart) {
        BundledImageShape bundledImageShape = null;
        List<SWTBotGefEditPart> descendants = swtBotGefEditPart.descendants(IsInstanceOf.instanceOf(BundledImageEditPart.class));
        if (!descendants.isEmpty()) {
            SWTBotGefEditPart bundledImageEditPartBot = descendants.get(0);
            BundledImageEditPart bundledImageEditPart = (BundledImageEditPart) bundledImageEditPartBot.part();
            BundledImageFigure primaryShape = bundledImageEditPart.getPrimaryShape();
            String uri = primaryShape.getURI();
            URI createdURI = URI.createURI(uri);
            String shapeName = createdURI.trimFileExtension().lastSegment();
            bundledImageShape = BundledImageShape.get(shapeName);
        }
        return bundledImageShape;
    }

    LabelAlignment getFigureLabelAlignment(int minorAlignment) {
        LabelAlignment figureLabelAlignment = null;
        switch (minorAlignment) {
        case ToolbarLayout.ALIGN_TOPLEFT:
            figureLabelAlignment = LabelAlignment.LEFT;
            break;
        case ToolbarLayout.ALIGN_CENTER:
            figureLabelAlignment = LabelAlignment.CENTER;
            break;
        case ToolbarLayout.ALIGN_BOTTOMRIGHT:
            figureLabelAlignment = LabelAlignment.RIGHT;
            break;
        default:
            figureLabelAlignment = LabelAlignment.CENTER;
        }
        return figureLabelAlignment;
    }

    List<FontFormat> getFigureFontFormat(Font font) {
        List<FontFormat> fontFormat = new ArrayList<FontFormat>();
        if (font.getFontData().length > 0) {
            switch (font.getFontData()[0].getStyle()) {
            case SWT.BOLD:
                FontFormatHelper.setFontFormat(fontFormat, FontFormat.BOLD_LITERAL);
                break;
            case SWT.ITALIC:
                FontFormatHelper.setFontFormat(fontFormat, FontFormat.ITALIC_LITERAL);
                break;
            case SWT.BOLD | SWT.ITALIC:
                FontFormatHelper.setFontFormat(fontFormat, FontFormat.BOLD_LITERAL);
                FontFormatHelper.setFontFormat(fontFormat, FontFormat.ITALIC_LITERAL);
                break;
            default:
                break;
            }
        }
        return fontFormat;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        bot.activeEditor().close();
        propertiesBot = null;
        eClass1WithSquareStyleBot = null;
        eClass1WithLozengeStyleBot = null;
        eClass1WithEllipseStyleBot = null;
        eClass1WithBundledImageStyleBot = null;
        eClass1WithBundledImageStyleErrorBot = null;
        eClass1WithNoteStyleBot = null;
        eClass1WithDotStyleBot = null;
        eClass1WithGaugeStyleBot = null;
        eClass1WithWorkspaceImageStyleBot = null;
        package1WithFlatContainerStyleBot = null;
        package1WithShapeContainerStyleBot = null;
        package1WithWorkspaceImageStyleBot = null;
        superTypeEditPartBot = null;
        referenceEditPartBot = null;
        propertiesBot = null;
        super.tearDown();
    }
}
