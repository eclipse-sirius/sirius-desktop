/*******************************************************************************
 * Copyright (c) 2010, 2018 THALES GLOBAL SERVICES.
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

import org.eclipse.gmf.runtime.notation.JumpLinkStatus;
import org.eclipse.gmf.runtime.notation.JumpLinkType;
import org.eclipse.gmf.runtime.notation.Routing;
import org.eclipse.gmf.runtime.notation.Smoothness;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

/**
 * Tests ensuring that customizing styles through the appearance section works
 * as expected.
 * 
 * @author alagarde
 */
public class RefreshWithCustomizedStyleFromAppearanceTabTests extends AbstractRefreshWithCustomizedStyleOnCompleteExampleTest {

    /**
     * Ensures that changing the routing style for an edge from the appearance
     * page works as expected (and also tests that the style is considered as
     * custom).
     * 
     * @throws Exception
     *             Test error.
     */
    public void testChangeRoutingStyleFromAppearanceSection() throws Exception {
        editor.reveal(referenceEditPartBot.part());
        referenceEditPartBot.select();

        final Predicate<SWTBotGefEditPart> modifiedStatePredicate = new Predicate<SWTBotGefEditPart>() {

            @Override
            public boolean apply(SWTBotGefEditPart input) {
                Routing routing = ((org.eclipse.gmf.runtime.notation.ConnectorStyle) ((View) input.part().getModel()).getStyles().iterator().next()).getRouting();
                return routing.getValue() == Routing.TREE;
            }
        };
        final Predicate<SWTBotGefEditPart> initialStatePredicate = Predicates.not(modifiedStatePredicate);
        doTestStyleCustomizationThroughRadioInAppearanceSection(referenceEditPartBot, "eClass2", initialStatePredicate, modifiedStatePredicate, "Styles:", 2);
    }

    /**
     * Ensures that changing the routing by enabling 'avoid obstructions' for an
     * edge from the appearance page works as expected (and also tests that the
     * style is considered as custom).
     * 
     * @throws Exception
     *             Test error.
     */
    public void testChangeRoutingObstructionsFromAppearanceSection() throws Exception {
        editor.reveal(referenceEditPartBot.part());
        referenceEditPartBot.select();

        final Predicate<SWTBotGefEditPart> modifiedStatePredicate = new Predicate<SWTBotGefEditPart>() {

            @Override
            public boolean apply(SWTBotGefEditPart input) {
                Boolean avoidObstructions = ((org.eclipse.gmf.runtime.notation.ConnectorStyle) ((View) input.part().getModel()).getStyles().iterator().next()).isAvoidObstructions();
                return avoidObstructions;
            }
        };
        final Predicate<SWTBotGefEditPart> initialStatePredicate = Predicates.not(modifiedStatePredicate);
        doTestStyleCustomizationThroughCheckboxInAppearanceSection(referenceEditPartBot, "eClass2", initialStatePredicate, modifiedStatePredicate, "Routing", 0);
    }

    /**
     * Ensures that changing the routing by enabling 'closest distance' for an
     * edge from the appearance page works as expected (and also tests that the
     * style is considered as custom).
     * 
     * @throws Exception
     *             Test error.
     */
    public void testChangeRoutingClosestDistanceFromAppearanceSection() throws Exception {
        editor.reveal(referenceEditPartBot.part());
        referenceEditPartBot.select();

        final Predicate<SWTBotGefEditPart> modifiedStatePredicate = new Predicate<SWTBotGefEditPart>() {

            @Override
            public boolean apply(SWTBotGefEditPart input) {
                Boolean closesDistance = ((org.eclipse.gmf.runtime.notation.ConnectorStyle) ((View) input.part().getModel()).getStyles().iterator().next()).isClosestDistance();
                return closesDistance;
            }
        };
        final Predicate<SWTBotGefEditPart> initialStatePredicate = Predicates.not(modifiedStatePredicate);
        doTestStyleCustomizationThroughCheckboxInAppearanceSection(referenceEditPartBot, "eClass2", initialStatePredicate, modifiedStatePredicate, "Routing", 1);
    }

    /**
     * Ensures that changing the Jump links by enabling 'reverse jump links' for
     * an edge from the appearance page works as expected (and also tests that
     * the style is considered as custom).
     * 
     * @throws Exception
     *             Test error.
     */
    public void testChangeJumpLinksReverseFromAppearanceSection() throws Exception {
        editor.reveal(referenceEditPartBot.part());
        referenceEditPartBot.select();

        final Predicate<SWTBotGefEditPart> modifiedStatePredicate = new Predicate<SWTBotGefEditPart>() {

            @Override
            public boolean apply(SWTBotGefEditPart input) {
                return ((org.eclipse.gmf.runtime.notation.ConnectorStyle) ((View) input.part().getModel()).getStyles().iterator().next()).isJumpLinksReverse();
            }
        };
        final Predicate<SWTBotGefEditPart> initialStatePredicate = Predicates.not(modifiedStatePredicate);
        doTestStyleCustomizationThroughCheckboxInAppearanceSection(referenceEditPartBot, "eClass2", initialStatePredicate, modifiedStatePredicate, "Jump links", 0);
    }

    /**
     * Ensures that changing the Jump links status to 'Above' for an edge from
     * the appearance page works as expected (and also tests that the style is
     * considered as custom).
     * 
     * @throws Exception
     *             Test error.
     */
    public void testChangeJumpLinksStatusFromAppearanceSection() throws Exception {
        editor.reveal(referenceEditPartBot.part());
        referenceEditPartBot.select();

        final Predicate<SWTBotGefEditPart> modifiedStatePredicate = new Predicate<SWTBotGefEditPart>() {

            @Override
            public boolean apply(SWTBotGefEditPart input) {
                JumpLinkStatus jumpLinkStatus = ((org.eclipse.gmf.runtime.notation.ConnectorStyle) ((View) input.part().getModel()).getStyles().iterator().next()).getJumpLinkStatus();
                return jumpLinkStatus.getValue() == JumpLinkStatus.ABOVE;
            }
        };
        final Predicate<SWTBotGefEditPart> initialStatePredicate = Predicates.not(modifiedStatePredicate);
        doTestStyleCustomizationThroughRadioInAppearanceSection(referenceEditPartBot, "eClass2", initialStatePredicate, modifiedStatePredicate, "Status:", 3);
    }

    /**
     * Ensures that changing the Jump links type to 'chamfered' for an edge from
     * the appearance page works as expected (and also tests that the style is
     * considered as custom).
     * 
     * @throws Exception
     *             Test error.
     */
    public void testChangeJumpLinksTypeFromAppearanceSection() throws Exception {
        editor.reveal(referenceEditPartBot.part());
        referenceEditPartBot.select();

        final Predicate<SWTBotGefEditPart> modifiedStatePredicate = new Predicate<SWTBotGefEditPart>() {

            @Override
            public boolean apply(SWTBotGefEditPart input) {
                JumpLinkType jumpLinkType = ((org.eclipse.gmf.runtime.notation.ConnectorStyle) ((View) input.part().getModel()).getStyles().iterator().next()).getJumpLinkType();
                return jumpLinkType.getValue() == JumpLinkType.CHAMFERED;
            }
        };
        final Predicate<SWTBotGefEditPart> initialStatePredicate = Predicates.not(modifiedStatePredicate);
        doTestStyleCustomizationThroughRadioInAppearanceSection(referenceEditPartBot, "eClass2", initialStatePredicate, modifiedStatePredicate, "Type:", 2);
    }

    /**
     * Ensures that changing the smoothness to 'More' for an edge from the
     * appearance page works as expected (and also tests that the style is
     * considered as custom).
     * 
     * @throws Exception
     *             Test error.
     */
    public void testChangeEdgeSmoothnessFromAppearanceSection() throws Exception {
        editor.reveal(referenceEditPartBot.part());
        referenceEditPartBot.select();

        final Predicate<SWTBotGefEditPart> modifiedStatePredicate = new Predicate<SWTBotGefEditPart>() {

            @Override
            public boolean apply(SWTBotGefEditPart input) {
                Smoothness smoothness = ((org.eclipse.gmf.runtime.notation.ConnectorStyle) ((View) input.part().getModel()).getStyles().iterator().next()).getSmoothness();
                return smoothness.getValue() == Smoothness.MORE;
            }
        };
        final Predicate<SWTBotGefEditPart> initialStatePredicate = Predicates.not(modifiedStatePredicate);
        doTestStyleCustomizationThroughRadioInAppearanceSection(referenceEditPartBot, "eClass2", initialStatePredicate, modifiedStatePredicate, "Smoothness:", 3);
    }

    /**
     * Ensures that changing the background image of a figure from the
     * appearance page works as expected (and also tests that the style is
     * considered as custom).
     * 
     * @throws Exception
     *             Test error.
     */
    public void testChangeBackgroundImageFromAppearanceSection() throws Exception {
        editor.reveal(eClass1WithSquareStyleBot.part());
        eClass1WithSquareStyleBot.select();

        final Predicate<SWTBotGefEditPart> stateWhenBackgroundImageIsChangedPredicate = new Predicate<SWTBotGefEditPart>() {

            @Override
            public boolean apply(SWTBotGefEditPart input) {
                return getWorkspaceImage(input) != null;
            }
        };
        final Predicate<SWTBotGefEditPart> stateWithInitialBackgroundImagePredicate = Predicates.not(stateWhenBackgroundImageIsChangedPredicate);
        doTestStyleCustomizationThroughBackgroundImageFromAppearanceSection(eClass1WithSquareStyleBot, stateWithInitialBackgroundImagePredicate, stateWhenBackgroundImageIsChangedPredicate,
                NEW_IMAGE_NAME);
    }

    /**
     * Ensures that changing the background color of a figure from the appearance page works as expected (and also tests
     * that the style is considered as custom).
     * 
     * @throws Exception
     *             Test error.
     */
    public void testChangeBackgroundColorFromAppearanceSection() throws Exception {
        eClass1WithSquareStyleBot.select();
        doTestStyleCustomizationThroughColorSelectionFromAppearanceSection(eClass1WithSquareStyleBot, "Fonts and Colors:", new int[] { 0, 1, 2 }, NOT_CUSTOMIZED_PREDICATE, CUSTOMIZED_PREDICATE);
    }

}
